package ee2.cajablanca;

/**
 * ============================================================================
 * EJERCICIO 1 — CAJA BLANCA (así te la darán en el examen: código SIN casos)
 * ============================================================================
 * REGLAS DE NEGOCIO:
 * La biblioteca administra la reserva de salas de estudio. Cada usuario
 * dispone de un máximo de 10 horas de reserva por semana. Cuando una nueva
 * solicitud haría que el usuario exceda ese máximo, la solicitud se aprueba
 * igualmente "de forma extendida" solo si el usuario es de posgrado o si la
 * cantidad de horas solicitadas es pequeña (<= 2 horas); en cualquier otro
 * caso el sistema bloquea la operación lanzando una excepción.
 *
 * TU TRABAJO está en GestorReservasSalasTest (src/test). NO modifiques esta clase.
 */
public class GestorReservasSalas {

    public static final int MAX_HORAS_SEMANA = 10;
    public static final int TOLERANCIA_HORAS = 2;

    /**
     * Evalúa una solicitud de reserva de sala para la semana en curso.
     * @return "APROBADA" o "APROBADA_EXTENDIDA"
     */
    public String evaluarReserva(int[] horasReservadasSemana,
                                 int horasSolicitadas,
                                 boolean esPosgrado) {
        int acumulado = 0;
        for (int h : horasReservadasSemana) {
            acumulado += h;
        }
        int total = acumulado + horasSolicitadas;
        if (total > MAX_HORAS_SEMANA) {
            if (esPosgrado || horasSolicitadas <= TOLERANCIA_HORAS) {
                return "APROBADA_EXTENDIDA";
            }
            throw new IllegalStateException(
                    "Limite semanal excedido: " + total + " horas");
        }
        return "APROBADA";
    }
}
