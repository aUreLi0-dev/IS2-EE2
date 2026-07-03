package ee2.cajanegra;

import java.util.Set;

/**
 * ============================================================================
 * CAJA NEGRA — Partición de equivalencia + análisis de valores límite
 * ============================================================================
 * En caja negra NO miras el código: derivas los casos desde los REQUISITOS.
 *
 * REQUISITOS del formulario:
 *  - numeroEmpleado : entero en el RANGO 1..999
 *  - area           : debe pertenecer al CONJUNTO {VENTAS, TI, RRHH}
 *  - codigoTurno    : VALOR ESPECÍFICO — exactamente 3 caracteres
 *
 * DIRECTRICES (tabla de las diapositivas — memorizar):
 * | Tipo de condición       | Clases válidas | Clases NO válidas         |
 * |-------------------------|----------------|---------------------------|
 * | RANGO (a <= x <= b)     | 1 (dentro)     | 2 (x < a  y  x > b)       |
 * | VALOR ESPECÍFICO        | 1              | 2 (menos / más)           |
 * | CONJUNTO (pertenencia)  | 1              | 1 (no pertenece)          |
 * | BOOLEANO                | 1              | 1                         |
 *
 * TABLA DE CLASES DE EQUIVALENCIA de este validador:
 * | Nº  | Condición de entrada        | Clase                     | Tipo |
 * |-----|-----------------------------|---------------------------|------|
 * | CV1 | 1 <= numeroEmpleado <= 999  | válida                    | rango|
 * | CNV1| numeroEmpleado < 1          | NO válida                 | rango|
 * | CNV2| numeroEmpleado > 999        | NO válida                 | rango|
 * | CV2 | area ∈ {VENTAS, TI, RRHH}   | válida                    | conj.|
 * | CNV3| area ∉ conjunto (o null)    | NO válida                 | conj.|
 * | CV3 | codigoTurno de 3 caracteres | válida                    | valor|
 * | CNV4| codigoTurno < 3 caracteres  | NO válida                 | valor|
 * | CNV5| codigoTurno > 3 caracteres  | NO válida                 | valor|
 *
 * REGLAS PARA LOS CASOS DE PRUEBA:
 *  - Un solo caso puede cubrir VARIAS clases válidas a la vez.
 *  - Cada clase NO válida necesita SU PROPIO caso (con el resto de campos
 *    válidos, para que el fallo sea atribuible a UNA sola clase).
 *  - VALORES LÍMITE: probar los bordes exactos (1 y 999) y los inmediatamente
 *    fuera (0 y 1000) — los errores se aglomeran en los extremos.
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
