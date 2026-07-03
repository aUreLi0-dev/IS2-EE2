package ee2.cajanegra;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * ============================================================================
 * CAJA NEGRA — casos derivados de la tabla de clases de equivalencia
 * ============================================================================
 * | Caso | Cubre      | numeroEmpleado | area        | codigoTurno | Esperado  |
 * |------|------------|----------------|-------------|-------------|-----------|
 * | CP1  | CV1+CV2+CV3| 500            | "TI"        | "T01"       | "OK"      |
 * | CP2  | CNV1       | 0              | "TI"        | "T01"       | excepción |
 * | CP3  | CNV2       | 1000           | "TI"        | "T01"       | excepción |
 * | CP4  | CNV3       | 500            | "MARKETING" | "T01"       | excepción |
 * | CP5  | CNV4       | 500            | "TI"        | "T1"        | excepción |
 * | CP6  | CNV5       | 500            | "TI"        | "T001"      | excepción |
 * | CP7  | límite inf.| 1              | "VENTAS"    | "M02"       | "OK"      |
 * | CP8  | límite sup.| 999            | "RRHH"      | "N03"       | "OK"      |
 *
 * Nota: CP2 y CP3 (0 y 1000) son a la vez clases NO válidas Y valores límite
 * externos. CP7/CP8 prueban los bordes internos del rango (1 y 999).
 */
public class ValidadorRegistroEmpleadoTest {

    private ValidadorRegistroEmpleado validador;

    @BeforeEach
    void setUp() {
        validador = new ValidadorRegistroEmpleado();
    }

    @Test // CP1: un solo caso puede cubrir TODAS las clases válidas
    void cp1_todasLasClasesValidas_retornaOk() {
        assertEquals("OK", validador.validar(500, "TI", "T01"));
    }

    @Test // CP2: CNV1 (numeroEmpleado < 1) — resto de campos válidos
    void cp2_numeroMenorQueMinimo_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> validador.validar(0, "TI", "T01"));
    }

    @Test // CP3: CNV2 (numeroEmpleado > 999)
    void cp3_numeroMayorQueMaximo_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> validador.validar(1000, "TI", "T01"));
    }

    @Test // CP4: CNV3 (area fuera del conjunto)
    void cp4_areaNoValida_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> validador.validar(500, "MARKETING", "T01"));
    }

    @Test // CP5: CNV4 (codigoTurno con menos de 3 caracteres)
    void cp5_codigoTurnoCorto_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> validador.validar(500, "TI", "T1"));
    }

    @Test // CP6: CNV5 (codigoTurno con más de 3 caracteres)
    void cp6_codigoTurnoLargo_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> validador.validar(500, "TI", "T001"));
    }

    @Test // CP7: VALOR LÍMITE inferior del rango (1 es válido)
    void cp7_limiteInferior_retornaOk() {
        assertEquals("OK", validador.validar(1, "VENTAS", "M02"));
    }

    @Test // CP8: VALOR LÍMITE superior del rango (999 es válido)
    void cp8_limiteSuperior_retornaOk() {
        assertEquals("OK", validador.validar(999, "RRHH", "N03"));
    }
}
