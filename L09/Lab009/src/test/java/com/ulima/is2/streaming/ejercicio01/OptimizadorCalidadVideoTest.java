package com.ulima.is2.streaming.ejercicio01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class OptimizadorCalidadVideoTest {

    private OptimizadorCalidadVideo optimizador;

    @BeforeEach
    void setUp() {
        optimizador = new OptimizadorCalidadVideo();
    }

    @Test
    @DisplayName("Debe lanzar excepcion si el ancho de banda es menor o igual a cero")
    void anchoBandaInvalido() {
        assertThrows(IllegalArgumentException.class,
                ()->optimizador.determinarCalidadOptima(0,"Basico"));
    }

    @Test
    @DisplayName("Plan Basico con ancho de banda alto debe dar 720p")
    void planBasicoConBuenAnchoBanda() {
        String calidad = optimizador.determinarCalidadOptima(6,"Basico");
        String resultado = "720p";
        assertEquals(resultado,calidad);
    }
    
    // TODO: Agrega más pruebas para cubrir todos los casos de Plan Basico y Premium.
    // ¡Ten en cuenta todos los valores límite para matar a todos los mutantes generados por PITest!
    @Test
    void planPremiumConBuenAnchoDeBanda() {
        String calidad = optimizador.determinarCalidadOptima(25,"Premium");
        String resultado = "4K";
        assertEquals(resultado,calidad);
    }

    @Test
    void planBasicoConMalAnchoDeBanda() {
        String calidad = optimizador.determinarCalidadOptima(4,"Basico");
        String resultado = "480p";
        assertEquals(resultado,calidad);
    }

    @Test
    void planPremiumConMalAnchoDeBandaPeroMayor10() {
        String calidad = optimizador.determinarCalidadOptima(10,"Premium");
        String resultado = "1080p";
        assertEquals(resultado,calidad);
    }

    @Test
    void planPremiumConMalAnchoDeBandaPeroMenor10() {
        String calidad = optimizador.determinarCalidadOptima(1,"Premium");
        String resultado = "480p";
        assertEquals(resultado,calidad);
    }

    @Test
    void planPremiumConMalAnchoDeBandaPeroMenor10dd() {
        String calidad = optimizador.determinarCalidadOptima(6,"Premium");
        String resultado = "720p";
        assertEquals(resultado,calidad);
    }
}
