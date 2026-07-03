package ee2.mocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * ============================================================================
 * EJERCICIO 3 — MOCKS con Mockito (formato típico de P3: te dan el Service
 * y el Repository, y pides escribir la clase de test completa)
 * ============================================================================
 * SE PIDE (como en el examen, 1 pt por inciso):
 *
 * (a) Prepara la clase de test:
 *     - Anota la clase para habilitar Mockito con JUnit 5 (¿qué anotación
 *       va sobre la clase? ¿cuál sobre el campo del repositorio?).
 *     - Inyecta el mock al Service en el @BeforeEach (¿por dónde entra la
 *       dependencia?).
 *     - Responde en un comentario (2-3 líneas): ¿POR QUÉ se mockea el
 *       repositorio y qué principio de diseño lo hace posible?
 *
 * (b) Test: el medicamento NO existe → debe lanzar excepción.
 *     Pista: ¿qué debe devolver el mock en findById para forzar esa rama?
 *
 * (c) Test: al menos UN caso de validación que lanza excepción — elige:
 *     receta requerida sin receta, cantidad inválida, o stock insuficiente.
 *     Pista: aquí el mock SÍ devuelve un medicamento (Optional.of(...)).
 *
 * (d) Test: CAMINO FELIZ — verifica que el total retornado sea
 *     precioUnitario × cantidad (assertEquals).
 *
 * (e) Test: EFECTOS COLATERALES de la venta exitosa — el stock del objeto
 *     queda descontado Y el servicio invocó repository.update(...)
 *     exactamente UNA vez. Además, en tus tests de error verifica que
 *     update NUNCA fue llamado.
 *     Pistas de API: verify(mock, times(1)).metodo(...) · verify(mock, never()).metodo(any())
 *
 * ⚠️ TRAMPAS que debes evitar (te las sabes de la chuleta):
 *     - ¿Qué pasa si olvidas la anotación de la clase? (@Mock queda null)
 *     - Strict stubs: no configures when(...) que un test no usa.
 *     - assertThrows siempre con lambda.
 *
 * ✅ AUTOEVALUACIÓN: borra todo_pendiente(), `mvn test` verde, compara con
 *    ..\chuleta-practica (¿verificaste never() en TODOS los caminos de error?).
 */
// TODO (a): ¿qué anotación va aquí para habilitar Mockito?
public class DispensacionServiceTest {

    private DispensacionService service;

    // TODO (a): declara aquí el repositorio mockeado (¿qué anotación lleva el campo?)
    private MedicamentoRepository medicamentoRepository;

    @BeforeEach
    void setUp() {
        // TODO (a): instancia el service inyectándole el mock
        // TODO (a): responde aquí en comentario: ¿por qué se mockea el repositorio?
    }

    /** Helper sugerido: crea un medicamento de prueba con los valores que le pases. */
    private Medicamento crearMedicamento(String nombre, double precio, int stock, boolean receta) {
        Medicamento med = new Medicamento();
        med.setId(1L);
        med.setNombre(nombre);
        med.setPrecioUnitario(precio);
        med.setStock(stock);
        med.setRequiereReceta(receta);
        return med;
    }

    @Test
    void todo_pendiente() {
        fail("Borra este test cuando hayas implementado (a)-(e): no encontrado, 1+ validación, camino feliz, y efectos colaterales con verify/never");
    }

    // TODO (b): medicamento no existe → excepción
    // TODO (c): un caso de validación → excepción (+ verify never)
    // TODO (d): camino feliz → assertEquals del total
    // TODO (e): efecto colateral → stock descontado + verify(update) exactamente 1 vez
}
