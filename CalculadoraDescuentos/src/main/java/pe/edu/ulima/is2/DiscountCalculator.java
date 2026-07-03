package pe.edu.ulima.is2;

public class DiscountCalculator {

    public double calculateDiscount(double originalPrice , double discountPercentge){
        if (originalPrice < 0){
            throw new IllegalArgumentException("originalPrice debe ser positivo");
        }

        if(discountPercentge < 0){
            throw new IllegalArgumentException("discountPercentge debe ser positivo");
        }

        if(discountPercentge > 100){
            throw new IllegalArgumentException("discountPercentge no debe ser mayor a 100%  ");
        }

        double descuento = originalPrice*(discountPercentge /100.0);
        return originalPrice - descuento;
    }
}
