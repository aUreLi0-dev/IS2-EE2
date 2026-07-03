package ee2.mutacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * ============================================================================
 * EJERCICIO 4 — CAZA AL MUTANTE (así fue la demo en vivo del profesor)
 * ============================================================================
 * Esta suite YA está implementada y `mvn test` la da EN VERDE... pero
 * "tests en verde" NO significa "tests suficientes". Compruébalo:
 *
 * (1) Corre las unitarias (deben pasar):
 *         mvn test
 * (2) Corre la mutación:
 *         mvn test-compile org.pitest:pitest-maven:mutationCoverage
 * (3) Abre el reporte:  target/pit-reports/index.html
 *     → verás que el mutation score NO es 100%: hay un MUTANTE VIVO. 🧟
 *
 * TU TRABAJO:
 * (a) En el reporte, identifica: ¿en qué LÍNEA de DiscountCalculator vive el
 *     mutante? ¿Qué MUTADOR es (cómo cambió la condición)?
 * (b) Razona: ¿por qué NINGUNO de los 6 tests de abajo lo detecta?
 *     (Pista: es un problema de VALOR LÍMITE... ¿qué valor de entrada no
 *     está probando nadie?)
 * (c) AGREGA el test que lo mata, re-corre los pasos (1)-(2) y confirma
 *     score 100% (todos los mutantes muertos).
 * (d) Anota la fórmula: mutation score = muertos / (total − equivalentes).
 *     Con el reporte en la mano: ¿cuál era el score antes y después?
 *
 * ✅ AUTOEVALUACIÓN: compara tu test con ..\chuleta-practica.
 */
public class DiscountCalculatorTest {

    private DiscountCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new DiscountCalculator();
    }

    @Test // camino feliz: 100 con 20% → 80
    void descuentoNormal_calculaPrecioFinal() {
        assertEquals(80.0, calculator.calculateDiscount(100.0, 20.0), 0.001);
    }

    @Test // descuento 0% → precio intacto
    void porcentajeCero_devuelvePrecioOriginal() {
        assertEquals(100.0, calculator.calculateDiscount(100.0, 0.0), 0.001);
    }

    @Test // descuento 100% → 0
    void porcentajeCien_devuelveCero() {
        assertEquals(0.0, calculator.calculateDiscount(100.0, 100.0), 0.001);
    }

    @Test // precio negativo → excepción
    void precioNegativo_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculateDiscount(-10.0, 20.0));
    }

    @Test // porcentaje negativo → excepción
    void porcentajeNegativo_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculateDiscount(100.0, -5.0));
    }

    @Test // porcentaje mayor a 100 → excepción
    void porcentajeMayorACien_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculateDiscount(100.0, 101.0));
    }

    // TODO (c): agrega aquí el test que mata al mutante superviviente 🧟
}
