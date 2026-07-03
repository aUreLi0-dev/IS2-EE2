package ee2.mutacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * ============================================================================
 * MUTACIÓN — la suite que mata a TODOS los mutantes (score 100%)
 * ============================================================================
 * FLUJO DE TRABAJO DEL EXAMEN/CURSO:
 *   1. Escribes tus pruebas unitarias normales (mínimo V(G) casos si es caja
 *      blanca; clases equivalentes si es caja negra).
 *   2. mvn test  → todo verde.
 *   3. mvn org.pitest:pitest-maven:mutationCoverage
 *   4. Abres target/pit-reports/index.html: si hay mutantes VIVOS, el reporte
 *      te dice la LÍNEA y el MUTADOR → agregas el caso que falta → repites.
 *
 * 🧪 EXPERIMENTO (hazlo una vez y no lo olvidas más):
 *   Comenta el test `limite_precioCero_*` de abajo, vuelve a correr PIT y verás
 *   SOBREVIVIR un mutante CONDITIONAL BOUNDARY en la línea del `originalPrice < 0`
 *   (cambió < por <=). Eso mismo le pasó al profesor en la demo en vivo:
 *   su suite estaba "bien" pero no probaba el VALOR LÍMITE precio = 0.
 *   Moraleja de examen: SIEMPRE probar los bordes.
 */
public class DiscountCalculatorTest {

    private DiscountCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new DiscountCalculator();
    }

    // ---------- casos "normales" (los que da el enunciado) ----------

    @Test // camino feliz: 100 con 20% → 80
    void descuentoNormal_calculaPrecioFinal() {
        assertEquals(80.0, calculator.calculateDiscount(100.0, 20.0), 0.001);
    }

    @Test // descuento 0% → precio intacto (borde inferior del porcentaje: mata el mutante <= en pct<0)
    void limite_porcentajeCero_devuelvePrecioOriginal() {
        assertEquals(100.0, calculator.calculateDiscount(100.0, 0.0), 0.001);
    }

    @Test // descuento 100% → 0 (borde superior del porcentaje: mata el mutante >= en pct>100)
    void limite_porcentajeCien_devuelveCero() {
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

    // ---------- ⭐ EL CASO QUE NADIE ESCRIBE (valor límite del precio) ----------

    @Test // precio = 0 es VÁLIDO (0 no es negativo) → mata el mutante conditional boundary (< por <=)
    void limite_precioCero_esValidoYDevuelveCero() {
        assertEquals(0.0, calculator.calculateDiscount(0.0, 50.0), 0.001);
    }
}
