package pe.edu.ulima.is2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculadoraTest {
    private Calculadora calculadora;

    @BeforeEach
    public void setUp(){
        // Inicialización

        // Creamos una instancia
        calculadora = new Calculadora();

        // Esto significa que para cada uno de estos test, se ejecutará este setup
        // Por lo tanto creará una nueva instancia para cada uno de los test
    }

    // Definiciones de los casos de prueba
    @Test
    public void testSuma(){
        int resEsperado = 10;

        int resObtenido = calculadora.sumar(6,4);

        // Para poder comprobar que el esperado sea igual que el obtenido
        // Se utilizan las assersiones (importar)
        assertEquals(resEsperado, resObtenido);
    }

    @Test
    public void testResta(){
        int resEsperado = 5;

        int resObtenido = calculadora.restar(10,5);

        // Para poder comprobar que el esperado sea igual que el obtenido
        // Se utilizan las assersiones (importar)
        assertEquals(resEsperado, resObtenido);
    }

    @Test
    public void testMultiplicacion(){
        int resEsperado = 24;

        int resObtenido = calculadora.multiplicar(12,2);

        // Para poder comprobar que el esperado sea igual que el obtenido
        // Se utilizan las assersiones (importar)
        assertEquals(resEsperado, resObtenido);
    }

    @Test
    public void testDivision(){
        int resEsperado = 12;

        int resObtenido = calculadora.dividir(60,5);

        // Para poder comprobar que el esperado sea igual que el obtenido
        // Se utilizan las assersiones (importar)
        assertEquals(resEsperado, resObtenido);
    }

    @Test
    public void testDivisionCero(){
        // Ya no tenemos un valor esperado, nosotros usaremos una experesión
        // Por eso ahora usaremos assertThrows
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    int resObtenido = calculadora.dividir(10,0);
                }
        );
    }

    @AfterEach
    public void tearDown(){
        // Limpieza
        System.out.println("Test terminado.");
    }
}
