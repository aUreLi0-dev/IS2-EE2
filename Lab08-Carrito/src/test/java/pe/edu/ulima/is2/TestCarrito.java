package pe.edu.ulima.is2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCarrito {
    private Carrito carrito;

    @BeforeEach
    public void setup(){
        carrito = new Carrito();
    }

    @Test
    public void testCarritoVacio(){
        boolean resultado = true;
        assertEquals(resultado,carrito.estaVacio());
    }

    @Test
    public void testIncrementarCantidad(){
        int resultado = 1;
        carrito.agregar("Consola", 100);
        assertEquals(resultado,carrito.cantidad());
    }

    @Test
    public void testTotalCorrecto(){
        double resultado = 15;
        carrito.agregar("Consola", 10);
        carrito.agregar("PlayStation", 5);
        assertEquals(resultado,carrito.getTotal());
    }

    @Test
    public void testEliminar(){
        boolean resultadoEliminar = true;
        int resultadoCantidad = 1;
        carrito.agregar("Consola", 10);
        carrito.agregar("PlayStation", 5);
        assertEquals(resultadoEliminar,carrito.eliminar("PlayStation", 5));
        assertEquals(resultadoCantidad,carrito.cantidad());
    }

    @Test
    public void testInexistente(){
        boolean resultadoEliminar = false;
        assertEquals(resultadoEliminar,carrito.eliminar("JJJJJ", 5));

    }

    @AfterEach
    public void tearDown(){
        System.out.println("Test finalizado. Total: " + carrito.cantidad());
    }
}
