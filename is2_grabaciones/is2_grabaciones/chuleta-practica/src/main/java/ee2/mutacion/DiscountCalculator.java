package ee2.mutacion;

/**
 * ============================================================================
 * MUTACIÓN (PIT) — la clase del ejercicio en vivo de la clase s12-a
 * ============================================================================
 * RECUERDA (el profesor lo repitió TRES veces): la mutación NO es una técnica
 * para hacer pruebas — MIDE LA CALIDAD de las pruebas unitarias que YA tienes.
 *
 * PIT genera MUTANTES = copias del programa con un fallo sembrado, p. ej.:
 *   - CONDITIONAL BOUNDARY: cambia  <  por  <=   (¡vive si no pruebas el borde!)
 *   - NEGATE CONDITIONAL:   cambia  <  por  >=
 *   - MATH: cambia  -  por  +
 *   - RETURN VALS: altera el valor devuelto
 * Si tus tests FALLAN contra el mutante → mutante MUERTO (bien).
 * Si tus tests PASAN → mutante VIVO (a tu suite le falta un caso).
 *
 * MUTATION SCORE = muertos / (total − equivalentes)
 *
 * CÓMO CORRER PIT (con este pom ya configurado):
 *   mvn test                                        (primero: unitarias en verde)
 *   mvn org.pitest:pitest-maven:mutationCoverage    (luego: mutación)
 *   reporte HTML →  target/pit-reports/index.html
 */
public class DiscountCalculator {

    /**
     * Devuelve el precio final tras aplicar el descuento.
     * Lanza IllegalArgumentException si el precio es negativo o el
     * porcentaje está fuera de 0..100.
     */
    public double calculateDiscount(double originalPrice, double discountPercentage) {
        if (originalPrice < 0) {                    // ← mutante boundary: < por <=  (lo mata el test de precio=0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (discountPercentage < 0                  // ← mutante boundary: lo mata el test de porcentaje=0
                || discountPercentage > 100) {      // ← mutante boundary: lo mata el test de porcentaje=100
            throw new IllegalArgumentException("Porcentaje de descuento invalido");
        }
        return originalPrice - (originalPrice * discountPercentage / 100.0);
    }
}
