package pe.edu.ulima.is2.laboratorio8a;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCarrito {
    private Carrito carrito;

    @BeforeEach // cada metodo que tenga el test
    public void setUp(){
        carrito = new Carrito();
    }

    @Test // Notación test para que lo ejecute
    public void testCarritoRecienInicializado(){
        int lenCarritoEsperada = 0; // inicializar longitud del carrito
        int lenCarritoActual = carrito.cantidad();

        assertEquals(lenCarritoEsperada,lenCarritoActual);
    }

    @Test
    public void testAgregarUnProducto(){
        int lenCarritoEsperada = 1;

        carrito.agregar("P1", 10);

        int lenCarritoActual = carrito.cantidad();

        assertEquals(lenCarritoEsperada,lenCarritoActual);
    }

    @Test
    public void testAgregarDosProductos(){
        int lenCarritoEsperada = 2;

        carrito.agregar("P1", 10);
        carrito.agregar("P2", 5);

        int lenCarritoActual = carrito.cantidad();

        assertEquals(lenCarritoEsperada,lenCarritoActual);
    }

    @Test
    public void testEliminarProductoExistente(){
        // dos condiciones, retornar true y reducir la cantidad
        carrito.agregar("P1", 10);
        carrito.agregar("P2", 5);
        // agregar nuevamente porque con cada ejecución se reinicia el carrito

        boolean resp = carrito.eliminar("P2", 5);
        int cantidadEsperada = 1;

        assertTrue(resp == true && carrito.cantidad() == cantidadEsperada);

    }

    @Test
    public void testEliminarProductoInexistente(){
        // dos condiciones, retornar true y reducir la cantidad
        carrito.agregar("P1", 10);
        carrito.agregar("P2", 5);
        // agregar nuevamente porque con cada ejecución se reinicia el carrito

        boolean resp = carrito.eliminar("P3", 5);
        int cantidadEsperada = 2;

        assertTrue(resp == false && carrito.cantidad() == cantidadEsperada);
    }

    @AfterEach
    public void tearDown(){
        System.out.println("Test finalizado.");
    }
}
