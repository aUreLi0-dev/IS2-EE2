package com.ulima.is2.lab10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidadorRegistroTest {

    private final ValidadorRegistro validador = new ValidadorRegistro();

    // ==========================================
    // TUS TESTS (Basados en tus 3 Casos de Prueba)
    // ==========================================
    
    @Test
    public void testRegistro_CasoValido_Ideal() {
        // TODO: Implementar el Caso de Prueba Válido (CP-V-01)
        // boolean resultado = validador.registrarUsuario(...);
        // assertTrue(resultado);
 
        // CP-V-02: Todos los campos pertenecen a sus clases de equivalencia válidas
        boolean resultado = validador.registrarUsuario("Gustabo Garcia", "ggarcia@test.com", 32, "DNI", "89456123");
        assertTrue(resultado, "El registro debería ser true con datos válidos (Gustabo Garcia)");
    }
    
    @Test
    public void testRegistro_CasoInvalido_Uno() {
        // CP-I-01: Nombre vacío
        boolean resultado = validador.registrarUsuario("", "juan@test.com", 25, "DNI", "12345678");
        assertFalse(resultado, "El registro debería ser false si el nombre está vacío");
    }

    @Test
    public void testRegistro_CasoInvalido_Dos() {
        // CP-I-02: Email sin arroba
        boolean resultado = validador.registrarUsuario("Ana Gomez", "anagomez.com", 22, "DNI", "12345678");
        assertFalse(resultado, "El registro debería ser false si el email no contiene arroba");
    }

    @Test
    public void testRegistro_CasoInvalido_Tres() {
        // CP-I-03: Número de documento con menos de 8 caracteres
        boolean resultado = validador.registrarUsuario("Luis Arce", "luis@test.com", 28, "DNI", "12345");
        assertFalse(resultado, "El registro debería ser false si el documento tiene menos de 8 caracteres");
    }

}
