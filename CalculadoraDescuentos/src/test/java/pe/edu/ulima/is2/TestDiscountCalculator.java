package pe.edu.ulima.is2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestDiscountCalculator {
    private DiscountCalculator discountCalculator;


    @BeforeEach
    public void setup(){
        System.out.println("Se inicia prueba");
        discountCalculator = new DiscountCalculator();

    }

    @Test
    public void testPrecioYDescueentoValido(){
        double montoConDescuento = discountCalculator.calculateDiscount(100,60);
        double montoEsperado = 40;
        assertEquals(montoEsperado,montoConDescuento);

    }

    @Test
    public void testPrecioValidoDescuentoCero(){
        double montoConDescuento = discountCalculator.calculateDiscount(100,0);
        double montoEsperado = 100;
        assertEquals(montoEsperado,montoConDescuento);
    }

    @Test
    public void testPrecioValidoDescuento100(){
        double montoConDescuento = discountCalculator.calculateDiscount(100,100);
        double montoEsperado = 0;
        assertEquals(montoEsperado,montoConDescuento);

    }

    @Test
    public void testPrecioValidoIgual0(){
        double montoConDescuento = discountCalculator.calculateDiscount(0,20);
        double montoEsperado = 0;
        assertEquals(montoEsperado,montoConDescuento);

    }

    @Test
    public void testPrecioNegativo(){
        assertThrows(IllegalArgumentException.class,
                ()-> discountCalculator.calculateDiscount(-100,20));
    }

    @Test
    public void testDescuentoNegativo(){
        assertThrows(IllegalArgumentException.class,
                ()-> discountCalculator.calculateDiscount(150,-44));

    }

    @Test
    public void testDescuentoMayorA100(){
        assertThrows(IllegalArgumentException.class,
                ()-> discountCalculator.calculateDiscount(120,120));

    }

    @AfterAll
    public static void tearDown(){
        System.out.println("a");
    }
}
