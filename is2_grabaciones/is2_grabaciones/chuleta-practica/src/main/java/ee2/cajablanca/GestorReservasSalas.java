package ee2.cajablanca;

/**
 * ============================================================================
 * CAJA BLANCA — Prueba del camino básico (McCabe)
 * ============================================================================
 * En el examen te dan una clase así, SIN casos de prueba. La receta:
 *   1. Etiquetar nodos (abajo, comentarios N1..N9).
 *      ⚠️ CADA condición simple de una condición compuesta (&&, ||) es SU
 *      PROPIO nodo predicado. Aquí `esPosgrado || horasSolicitadas <= 2`
 *      son DOS predicados (N5 y N6), no uno.
 *   2. Dibujar el grafo (aristas V/F de cada predicado + retorno del bucle
 *      + convergencia en un único nodo final).
 *   3. Calcular V(G) por las TRES fórmulas (deben coincidir):
 *      - Regiones del grafo               = 5
 *      - A − N + 2 = 13 − 10 + 2          = 5
 *      - P + 1     = 4 + 1                = 5   (P: N2, N4, N5, N6)
 *   4. Enumerar los V(G)=5 caminos independientes (cada uno agrega al menos
 *      una arista nueva). ⚠️ No olvidar el camino de 0 iteraciones del for.
 *   5. Escribir COMO MÍNIMO un test JUnit por camino (ver test).
 *
 * GRAFO (aristas):
 *   N1→N2 · N2→N3(V) · N3→N2(retorno) · N2→N4(F) · N4→N5(V) · N4→N9(F)
 *   N5→N7(V, cortocircuito del ||) · N5→N6(F) · N6→N7(V) · N6→N8(F)
 *   N7→N10 · N8→N10 · N9→N10          (13 aristas, 10 nodos)
 */
public class GestorReservasSalas {

    public static final int MAX_HORAS_SEMANA = 10;
    public static final int TOLERANCIA_HORAS = 2;

    /**
     * Evalúa una reserva de sala. Máximo 10 h/semana por usuario; si la
     * solicitud excede el máximo, se aprueba "extendida" solo para posgrado
     * o solicitudes pequeñas (<= 2 h); si no, se bloquea con excepción.
     */
    public String evaluarReserva(int[] horasReservadasSemana,
                                 int horasSolicitadas,
                                 boolean esPosgrado) {
        int acumulado = 0;                                    // N1
        for (int h : horasReservadasSemana) {                 // N2 (predicado del for)
            acumulado += h;                                   // N3 (cuerpo, regresa a N2)
        }
        int total = acumulado + horasSolicitadas;             // (secuencia, colapsa con N4)
        if (total > MAX_HORAS_SEMANA) {                       // N4 (predicado)
            if (esPosgrado                                    // N5 (predicado simple 1)
                    || horasSolicitadas <= TOLERANCIA_HORAS) { // N6 (predicado simple 2)
                return "APROBADA_EXTENDIDA";                  // N7
            }
            throw new IllegalStateException(                  // N8
                    "Limite semanal excedido: " + total + " horas");
        }
        return "APROBADA";                                    // N9  → N10 (fin único)
    }
}
