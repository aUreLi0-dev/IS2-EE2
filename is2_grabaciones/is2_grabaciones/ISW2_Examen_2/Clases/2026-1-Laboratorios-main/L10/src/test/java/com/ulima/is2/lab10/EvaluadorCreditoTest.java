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
        // - Camino 1: Inicio -> B(Sí) -> Retorna RECHAZADO_EDAD *(Ej: edad = 17)*
        // Camino donde edad < 18
        String resultado = evaluador.evaluarCredito(17, 2000, false, 5000);
        assertEquals("RECHAZADO_EDAD", resultado);
    }

    @Test
    public void testCamino5_AprobadoIdeal() {
        // Camino 5: Inicio -> B(No) -> C(No) -> D(Sí) -> E(No) -> F(No) -> Retorna APROBADO *(Ej: salario = 3000 monto = 5001)*
        // Camino donde cumple todo, no tiene deudas y el monto es permitido
        String resultado = evaluador.evaluarCredito(30, 3000, false, 5000);
        assertEquals("APROBADO", resultado);
    }
    
    // ==========================================
    // TESTS PENDIENTES DE IMPLEMENTAR
    // ==========================================

    @Test
    public void testCamino2() {
        // Camino 2: Inicio -> B(No) -> C(Sí) -> Retorna RECHAZADO_SALARIO *(Ej: salario = 900)*
        String resultado = evaluador.evaluarCredito(30, 999, false, 5000);
        assertEquals("RECHAZADO_SALARIO", resultado);
    }

    @Test
    public void testCamino3() {
        // Camino 3: Inicio -> B(No) -> C(No) -> D(Sí) -> E(Sí) -> Retorna RECHAZADO_DEUDAS *(Ej: salario = 1000)*
        String resultado = evaluador.evaluarCredito(30, 1000, true, 5000);
        assertEquals("RECHAZADO_DEUDAS", resultado);
    }
    
    @Test
    public void testCamino4() {
        // Camino 4: Inicio -> B(No) -> C(No) -> D(Sí) -> E(No) -> F(Sí) -> Retorna RECHAZADO_MONTO *(Ej: monto = 1001)*
        String resultado = evaluador.evaluarCredito(30, 2000, true, 6001);
        assertEquals("RECHAZADO_MONTO", resultado);
    }

    @Test
    public void testCamino6() {
        // Camino 6: Inicio -> B(No) -> C(No) -> D(No) -> F(Sí) -> Retorna RECHAZADO_MONTO *(Ej: monto = 9001)*
        String resultado = evaluador.evaluarCredito(30, 3000, false, 9001);
        assertEquals("RECHAZADO_MONTO", resultado);
    }
}

