package ee2.cajanegra;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * ============================================================================
 * EJERCICIO 2 — CAJA NEGRA: partición de equivalencia + valores límite
 * ============================================================================
 * Trabaja EN ESTE ORDEN:
 *
 * (a) Recuerda (o repasa en tu chuleta) la DIRECTRIZ por tipo de condición:
 *     ¿cuántas clases válidas y NO válidas genera un RANGO? ¿un VALOR
 *     ESPECÍFICO? ¿un CONJUNTO?
 *
 * (b) COMPLETA la tabla de clases de equivalencia para los 3 campos
 *     (numeroEmpleado: rango 1..999 · area: conjunto {VENTAS,TI,RRHH} ·
 *     codigoTurno: exactamente 3 caracteres). Numera cada clase:
 *     | Nº   | Condición de entrada | Clase (válida / NO válida) |
 *     |------|----------------------|----------------------------|
 *     | CV1  |                      |                            |
 *     | CNV1 |                      |                            |
 *     | ...  |                      |                            |
 *
 * (c) DERIVA los casos de prueba con las reglas del curso:
 *     - Un caso puede cubrir VARIAS clases VÁLIDAS a la vez.
 *     - Cada clase NO válida exige SU PROPIO caso (un solo dato inválido,
 *       el resto válido — si no, no sabes qué causó el rechazo).
 *     - Agrega los VALORES LÍMITE del rango: ¿qué 4 valores probarías?
 *
 * (d) IMPLEMENTA los @Test (uno por caso de tu tabla). Patrones:
 *         assertEquals("OK", validador.validar(...));
 *         assertThrows(IllegalArgumentException.class, () -> validador.validar(...));
 *
 * ✅ AUTOEVALUACIÓN: borra todo_pendiente(), `mvn test` en verde, y compara
 *    con ..\chuleta-practica (¿cuántos casos te salieron? ¿los mismos?).
 */
public class ValidadorRegistroEmpleadoTest {

    private ValidadorRegistroEmpleado validador;

    @BeforeEach
    void setUp() {
        validador = new ValidadorRegistroEmpleado();
    }

    @Test
    void todo_pendiente() {
        fail("Borra este test cuando tengas: 1 caso cubriendo las clases válidas + 1 caso por cada clase NO válida + los valores límite del rango");
    }

    // TODO (d): implementa aquí tus casos:
    // @Test
    // void cp1_todasLasClasesValidas_retornaOk() { ... }
}
