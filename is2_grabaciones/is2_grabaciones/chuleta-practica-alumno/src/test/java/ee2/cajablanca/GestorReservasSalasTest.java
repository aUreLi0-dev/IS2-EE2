package ee2.cajablanca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * ============================================================================
 * EJERCICIO 1 — CAJA BLANCA: grafo → V(G) → caminos → 1 test por camino
 * ============================================================================
 * Trabaja EN ESTE ORDEN (primero en papel, como en el examen):
 *
 * (a) ETIQUETA LOS NODOS de evaluarReserva() en un papel.
 *     Pistas: las sentencias secuenciales consecutivas colapsan en UN nodo;
 *     el `for` tiene su propio nodo de condición; y OJO con la condición
 *     compuesta `a || b`... ¿cuántos nodos predicado genera?
 *
 * (b) DIBUJA EL GRAFO (sí, dibujarlo — el profesor confirmó que va en el
 *     examen) y calcula V(G) por LAS TRES fórmulas. Deben coincidir:
 *         - Regiones           = ____
 *         - A − N + 2          = ____ − ____ + 2 = ____
 *         - P + 1              = ____ + 1 = ____
 *
 * (c) COMPLETA tu tabla de caminos independientes (agrega las filas que
 *     tu V(G) diga):
 *     | # | Camino (nodos) | Condición que lo fuerza | Datos | Esperado |
 *     |---|----------------|--------------------------|-------|----------|
 *     |   |                |                          |       |          |
 *     ⚠️ Preguntas de control: ¿incluiste el camino donde el bucle se
 *     ejecuta CERO veces? ¿Cada dato fuerza UN SOLO camino (no cumple dos
 *     condiciones a la vez)?
 *
 * (d) IMPLEMENTA un @Test por cada camino de tu tabla. Patrones:
 *         assertEquals("VALOR_ESPERADO", gestor.evaluarReserva(...));
 *         assertThrows(IllegalStateException.class, () -> gestor.evaluarReserva(...));
 *
 * ✅ AUTOEVALUACIÓN: borra el test todo_pendiente(), corre `mvn test` (debe
 *    quedar verde) y recién entonces compara con ..\chuleta-practica (resuelto).
 */
public class GestorReservasSalasTest {

    private GestorReservasSalas gestor;

    @BeforeEach
    void setUp() {
        gestor = new GestorReservasSalas();
    }

    @Test
    void todo_pendiente() {
        fail("Borra este test cuando tengas un @Test por cada camino independiente (mínimo V(G) tests)");
    }

    // TODO (d): implementa aquí tus tests — uno por camino, nombrados por lo que fuerzan:
    // @Test
    // void camino1_loQueFuerza() {
    //     ...
    // }
}
