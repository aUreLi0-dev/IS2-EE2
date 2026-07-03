package ee2.mocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * ============================================================================
 * MOCKS con Mockito — LA PLANTILLA (apréndela de memoria)
 * ============================================================================
 * PROCESO DE MOCK OBJECTS (5 pasos, en este orden):
 *   1. Crear los mocks                       -> @Mock
 *   2. Definir expectativas (stubbing)       -> when(...).thenReturn(...)
 *   3. Instanciar la clase a probar CON los mocks -> constructor en @BeforeEach
 *   4. Ejecutar el método a probar           -> service.dispensar(...)
 *   5. Verificar                             -> asserts + verify(...)
 *
 * ¿POR QUÉ SE MOCKEA EL REPOSITORIO? (respuesta de examen, 3 líneas)
 *   1. AISLAMIENTO: se prueba la lógica de negocio del Service; si el test
 *      falla, el defecto está en el Service, no en la BD. Escenarios difíciles
 *      (id inexistente, stock exacto) se crean de forma instantánea y determinista.
 *   2. INTERACCIONES: el mock registra llamadas -> verify() comprueba que se
 *      persistió en el éxito y que NO se persistió en el error (never()).
 *   3. DIP: el Service depende de la INTERFAZ y la recibe por constructor;
 *      por eso el mock es sustituible sin tocar el Service.
 *
 * ⚠️ TRAMPAS:
 *   - Sin @ExtendWith(MockitoExtension.class) el campo @Mock queda en null.
 *   - Mockito moderno usa "strict stubs": un when(...) que ningún test usa
 *     lanza UnnecessaryStubbingException → solo stubbea lo que ese test usa.
 *   - verify(repo, never()).update(any(...)) en TODOS los caminos de error.
 */
@ExtendWith(MockitoExtension.class)                      // habilita las anotaciones
public class DispensacionServiceTest {

    private DispensacionService service;                 // SUT (System Under Test)

    @Mock
    private MedicamentoRepository medicamentoRepository; // paso 1: mock de la dependencia

    @BeforeEach
    void setUp() {
        // paso 3: inyección por constructor — el SUT usa el mock, no la BD real
        service = new DispensacionService(medicamentoRepository);
    }

    /** Helper para crear el objeto que el mock "devolverá". */
    private Medicamento crearMedicamento(String nombre, double precio, int stock, boolean receta) {
        Medicamento med = new Medicamento();
        med.setId(1L);
        med.setNombre(nombre);
        med.setPrecioUnitario(precio);
        med.setStock(stock);
        med.setRequiereReceta(receta);
        return med;
    }

    @Test // RAMA 1: no encontrado → stub con Optional.empty() + assertThrows
    void medicamentoNoExiste_lanzaExcepcion() {
        when(medicamentoRepository.findById(999L)).thenReturn(Optional.empty()); // paso 2

        assertThrows(RuntimeException.class, () -> service.dispensar(999L, 1, true)); // pasos 4-5

        verify(medicamentoRepository, never()).update(any(Medicamento.class)); // error → NO persiste
    }

    @Test // RAMA 2: cantidad inválida (0) → el mock SÍ devuelve el medicamento
    void cantidadInvalida_lanzaExcepcion() {
        Medicamento med = crearMedicamento("Paracetamol 500mg", 2.5, 10, false);
        when(medicamentoRepository.findById(1L)).thenReturn(Optional.of(med));

        assertThrows(RuntimeException.class, () -> service.dispensar(1L, 0, true));

        verify(medicamentoRepository, never()).update(any(Medicamento.class));
    }

    @Test // RAMA 3: requiere receta y el cliente no la presenta
    void recetaRequeridaSinReceta_lanzaExcepcion() {
        Medicamento med = crearMedicamento("Amoxicilina 500mg", 1.8, 20, true);
        when(medicamentoRepository.findById(1L)).thenReturn(Optional.of(med));

        assertThrows(RuntimeException.class, () -> service.dispensar(1L, 2, false));

        verify(medicamentoRepository, never()).update(any(Medicamento.class));
    }

    @Test // RAMA 4: stock insuficiente (pide 5, hay 3)
    void stockInsuficiente_lanzaExcepcion() {
        Medicamento med = crearMedicamento("Ibuprofeno 400mg", 1.2, 3, false);
        when(medicamentoRepository.findById(1L)).thenReturn(Optional.of(med));

        assertThrows(RuntimeException.class, () -> service.dispensar(1L, 5, true));

        verify(medicamentoRepository, never()).update(any(Medicamento.class));
    }

    @Test // CAMINO FELIZ (valor de retorno): total = precio × cantidad = 2.5 × 4 = 10.0
    void dispensacionExitosa_calculaTotal() {
        Medicamento med = crearMedicamento("Paracetamol 500mg", 2.5, 10, false);
        when(medicamentoRepository.findById(1L)).thenReturn(Optional.of(med));

        Double total = service.dispensar(1L, 4, true);

        assertEquals(10.0, total); // exacto en punto flotante; también vale assertEquals(10.0, total, 0.001)
    }

    @Test // CAMINO FELIZ (efectos colaterales): stock 10−4=6 + update() llamado UNA vez
    void dispensacionExitosa_actualizaStockYPersiste() {
        Medicamento med = crearMedicamento("Paracetamol 500mg", 2.5, 10, false);
        when(medicamentoRepository.findById(1L)).thenReturn(Optional.of(med));

        service.dispensar(1L, 4, true);

        assertEquals(6, med.getStock());                      // el estado cambió
        verify(medicamentoRepository, times(1)).update(med);  // la INTERACCIÓN ocurrió
    }
}
