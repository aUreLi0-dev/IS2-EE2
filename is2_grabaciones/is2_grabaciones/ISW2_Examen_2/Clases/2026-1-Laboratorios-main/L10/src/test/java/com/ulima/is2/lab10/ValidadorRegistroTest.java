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
        boolean resultado = validador.registrarUsuario(
                "Jefferson Sanchez",
                "jjjangelosss@gmail.com",
                22,
                "DNI",
                "76951422"
        );

        assertTrue(resultado);
    }

    @Test
    public void testRegistro_CasoInvalido_Uno() {
        boolean resultado = validador.registrarUsuario(
                "",
                "juan@test.com",
                25,
                "DNI",
                "12345678"
        );

        assertFalse(resultado);
    }

    @Test
    public void testRegistro_CasoInvalido_Dos() {
        boolean resultado = validador.registrarUsuario(
                "Maria Lopez",
                "marialopez.test.com",
                25,
                "CE",
                "A1234567"
        );

        assertFalse(resultado);
    }

}
