package com.ulima.is2.streaming.ejercicio01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class OptimizadorCalidadVideoTest {

    private OptimizadorCalidadVideo optimizador;

    @BeforeEach
    void setUp() {
        System.out.println("Se inicia la prueba.");
        optimizador = new OptimizadorCalidadVideo();
    }

    @Test
    @DisplayName("Debe lanzar excepcion si el ancho de banda es menor o igual a cero")
    void anchoBandaInvalido() {
        // TODO: Completa la prueba usando assertThrows
        assertThrows(IllegalArgumentException.class, () -> {
        optimizador.determinarCalidadOptima(0, "Basico");
        });
    }

    @Test
    @DisplayName("Plan Basico con ancho de banda alto debe dar 720p")
    void planBasicoConBuenAnchoBanda() {
        // TODO: Completa la prueba
        String resultado = optimizador.determinarCalidadOptima(6.0, "Basico");
        assertEquals("720p", resultado);
    }

    @Test
    @DisplayName("Plan Basico con ancho de banda en el limite debe dar 720p")
    void planBasicoConAnchoBandaLimite() {
        String resultado = optimizador.determinarCalidadOptima(5.0, "Basico");
        assertEquals("720p", resultado);
    }

    @Test
    @DisplayName("Plan Basico fuera del ancho de banda en el limite debe dar 480p")
    void planBasicoFueraAnchoBandaLimite() {
        String resultado = optimizador.determinarCalidadOptima(4.0, "Basico");
        assertEquals("480p", resultado);
    }

    @Test
    @DisplayName("Plan Premium con ancho de banda 25Mbps debe dar 4K")
    void PlanPremiumConBuenAnchoBanda() {
        String resultado = optimizador.determinarCalidadOptima(25.0, "Premium");
        assertEquals("4K", resultado);
    }

    @Test
    @DisplayName("Plan Premium con ancho de banda 10Mbps debe dar 1080p")
    void PlanPremiumConAnchoBandaMayorA10() {
        String resultado = optimizador.determinarCalidadOptima(10.0, "Premium");
        assertEquals("1080p", resultado);
    }

    @Test
    @DisplayName("Plan Premium con ancho de banda 5Mbps debe dar 720p")
    void PlanPremiumConAnchoBandaMayorA5() {
        String resultado = optimizador.determinarCalidadOptima(5.0, "Premium");
        assertEquals("720p", resultado);
    }

    @Test
    @DisplayName("Plan Premium fuera del ancho de banda en el limite debe dar 480p")
    void planPremiumFueraAnchoBandaLimite() {
        String resultado = optimizador.determinarCalidadOptima(4.0, "Premium");
        assertEquals("480p", resultado);
    }

    @Test
    @DisplayName("Plan no reconocido")
    void planNoReconocido() {
        assertThrows(IllegalArgumentException.class, () -> {
            optimizador.determinarCalidadOptima(15.0, "Estandar");
        });
    }

    
    // TODO: Agrega más pruebas para cubrir todos los casos de Plan Basico y Premium.
    // ¡Ten en cuenta todos los valores límite para matar a todos los mutantes generados por PITest!
}
