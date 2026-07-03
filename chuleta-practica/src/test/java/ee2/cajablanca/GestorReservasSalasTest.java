package ee2.cajablanca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * ============================================================================
 * CAJA BLANCA — un test por cada camino independiente (V(G) = 5 → 5 tests)
 * ============================================================================
 * Tabla de caminos y los DATOS que fuerzan exactamente cada camino:
 *
 * | # | Camino (nodos)            | Lo fuerza                                | Esperado             |
 * |---|---------------------------|------------------------------------------|----------------------|
 * | C1| N1-N2-N4-N9-N10           | arreglo VACÍO (0 iteraciones), total<=10 | "APROBADA"           |
 * | C2| N1-N2-N3-N2-N4-N9-N10     | 1 iteración del for, total<=10           | "APROBADA"           |
 * | C3| ...N4-N5-N7-N10           | total>10 y posgrado=true (|| cortocircuita, N6 NO se evalúa) | "APROBADA_EXTENDIDA" |
 * | C4| ...N4-N5-N6-N7-N10        | total>10, posgrado=false, solicitadas<=2 | "APROBADA_EXTENDIDA" |
 * | C5| ...N4-N5-N6-N8-N10        | total>10, posgrado=false, solicitadas>2  | IllegalStateException|
 *
 * ⚠️ TRAMPAS típicas (costaron puntos en exámenes pasados):
 *  - C3 debe usar solicitadas > 2 (aquí 5): si usas posgrado=true Y
 *    solicitadas<=2 a la vez, no distingues C3 de C4.
 *  - No olvidar C1 (bucle con 0 iteraciones = arreglo vacío).
 *  - assertEquals(ESPERADO, obtenido) — el primer argumento es el esperado.
 *  - assertThrows SIEMPRE con lambda: () -> ...
 */
public class GestorReservasSalasTest {

    private GestorReservasSalas gestor;

    @BeforeEach
    void setUp() {
        gestor = new GestorReservasSalas(); // sin dependencias → NO se usa Mockito
    }

    @Test // C1: sin historial (0 iteraciones), total = 4 <= 10
    void camino1_sinHistorial_dentroDelLimite_retornaAprobada() {
        assertEquals("APROBADA", gestor.evaluarReserva(new int[]{}, 4, false));
    }

    @Test // C2: 1 iteración, total = 5 + 4 = 9 <= 10
    void camino2_conHistorial_dentroDelLimite_retornaAprobada() {
        assertEquals("APROBADA", gestor.evaluarReserva(new int[]{5}, 4, false));
    }

    @Test // C3: total = 8 + 5 = 13 > 10, posgrado=true → el || cortocircuita en N5
    void camino3_excedeLimite_posgrado_retornaAprobadaExtendida() {
        assertEquals("APROBADA_EXTENDIDA", gestor.evaluarReserva(new int[]{8}, 5, true));
    }

    @Test // C4: total = 9 + 2 = 11 > 10, posgrado=false, 2 <= 2 (¡valor límite!)
    void camino4_excedeLimite_pregradoPocasHoras_retornaAprobadaExtendida() {
        assertEquals("APROBADA_EXTENDIDA", gestor.evaluarReserva(new int[]{9}, 2, false));
    }

    @Test // C5: total = 8 + 5 = 13 > 10, posgrado=false, 5 > 2 → excepción
    void camino5_excedeLimite_pregradoMuchasHoras_lanzaExcepcion() {
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> gestor.evaluarReserva(new int[]{8}, 5, false));
        assertTrue(ex.getMessage().contains("Limite semanal excedido"));
    }
}
