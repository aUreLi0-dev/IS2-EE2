package ee2.cajanegra;

import java.util.Set;

/**
 * ============================================================================
 * EJERCICIO 2 — CAJA NEGRA (deriva los casos desde los REQUISITOS, no del código)
 * ============================================================================
 * REQUISITOS del formulario de registro de empleados:
 *  - numeroEmpleado : entero en el RANGO 1..999
 *  - area           : debe pertenecer al CONJUNTO {VENTAS, TI, RRHH}
 *  - codigoTurno    : VALOR ESPECÍFICO — exactamente 3 caracteres
 * Si todo es válido devuelve "OK"; si algo falla lanza IllegalArgumentException.
 *
 * TU TRABAJO está en ValidadorRegistroEmpleadoTest. NO modifiques esta clase.
 */
public class ValidadorRegistroEmpleado {

    private static final Set<String> AREAS_VALIDAS = Set.of("VENTAS", "TI", "RRHH");

    /** Valida el registro; devuelve "OK" o lanza IllegalArgumentException. */
    public String validar(int numeroEmpleado, String area, String codigoTurno) {
        if (numeroEmpleado < 1 || numeroEmpleado > 999) {
            throw new IllegalArgumentException("numeroEmpleado fuera de rango (1-999)");
        }
        if (area == null || !AREAS_VALIDAS.contains(area)) {
            throw new IllegalArgumentException("area no valida");
        }
        if (codigoTurno == null || codigoTurno.length() != 3) {
            throw new IllegalArgumentException("codigoTurno debe tener exactamente 3 caracteres");
        }
        return "OK";
    }
}
