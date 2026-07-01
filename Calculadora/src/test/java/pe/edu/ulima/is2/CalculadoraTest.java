package pe.edu.ulima.is2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraTest {
    private Calculadora calculadora;


    @BeforeEach
    public void setup(){
        //Inicializacion
        calculadora = new Calculadora();
    }

    @Test
    public void testSumar(){
        int resultado = 25;
        int resultadoObtenido = calculadora.sumar(10,15);

        assertEquals(resultado,resultadoObtenido);
    }

    @Test
    public void testRestar(){
        int resultado = -5;
        int resultadoObtenido = calculadora.restar(10,15);

        assertEquals(resultado,resultadoObtenido);
    }

    @Test
    public void testMultiplicar(){
        int resultado = 50;
        int resultadoObtenido = calculadora.multiplicar(10,5);

        assertEquals(resultado,resultadoObtenido);
    }

    @Test
    public void testDividir(){
        int resultado = 2;
        int resultadoObtenido = calculadora.dividir(10,5);

        assertEquals(resultado,resultadoObtenido);
    }


    @AfterEach
    public void tearDown(){
        //Limpieza
        System.out.println("Test terminado");
    }
}
