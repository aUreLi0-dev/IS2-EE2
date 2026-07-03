package com.ulima.is2.lab10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EvaluadorCreditoTest {

    private final EvaluadorCredito evaluador = new EvaluadorCredito();

    // ==========================================
    // TESTS DE EJEMPLOS
    // ==========================================

    @Test
    public void testCamino1_RechazadoPorEdad() {
        // Camino donde edad < 18
        String resultado = evaluador.evaluarCredito(17, 2000, false, 5000);
        assertEquals("RECHAZADO_EDAD", resultado);
    }

    @Test
    public void testCamino6_AprobadoIdeal() {
        // Camino donde cumple todo, no tiene deudas y el monto es permitido
        String resultado = evaluador.evaluarCredito(30, 3000, false, 5000);
        assertEquals("APROBADO", resultado);
    }
    
    // ==========================================
    // TESTS PENDIENTES DE IMPLEMENTAR
    // ==========================================

    @Test
    public void testCamino2() {
        // Camino donde edad >= 18, pero salario < 1000
        String resultado = evaluador.evaluarCredito(20, 800, false, 1000);
        assertEquals("RECHAZADO_SALARIO", resultado);
    }

    @Test
    public void testCamino3() {
        // Camino donde edad >= 18, salario >= 1000 pero < 2000, y tiene deudas
        String resultado = evaluador.evaluarCredito(25, 1500, true, 1000);
        assertEquals("RECHAZADO_DEUDAS", resultado);
    }
    
    @Test
    public void testCamino4() {
        // Camino donde edad >= 18, salario >= 2000, tiene deudas, pero monto excede el triple del salario (Ej: 2500 * 3 = 7500)
        String resultado = evaluador.evaluarCredito(28, 2500, true, 8000);
        assertEquals("RECHAZADO_MONTO", resultado);
    }

    @Test
    public void testCamino5() {
        // Camino donde edad >= 18, salario >= 1000, sin deudas, pero monto excede el triple del salario (Ej: 1500 * 3 = 4500)
        String resultado = evaluador.evaluarCredito(22, 1500, false, 5000);
        assertEquals("RECHAZADO_MONTO", resultado);
    }
}
