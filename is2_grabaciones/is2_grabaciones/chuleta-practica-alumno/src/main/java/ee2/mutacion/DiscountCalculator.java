package ee2.mutacion;

/**
 * ============================================================================
 * EJERCICIO 4 — MUTACIÓN con PIT (la clase de la demo del profesor)
 * ============================================================================
 * Calcula el precio final tras aplicar un descuento. Lanza
 * IllegalArgumentException si el precio es negativo o si el porcentaje
 * está fuera del rango 0..100.
 *
 * TU TRABAJO está en DiscountCalculatorTest. NO modifiques esta clase.
 */
public class DiscountCalculator {

    public double calculateDiscount(double originalPrice, double discountPercentage) {
        if (originalPrice < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Porcentaje de descuento invalido");
        }
        return originalPrice - (originalPrice * discountPercentage / 100.0);
    }
}
