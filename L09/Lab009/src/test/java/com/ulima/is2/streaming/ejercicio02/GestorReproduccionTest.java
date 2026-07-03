package com.ulima.is2.streaming.ejercicio02;

import com.ulima.is2.streaming.ejercicio01.OptimizadorCalidadVideo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GestorReproduccionTest {

    @Mock
    private ServicioSuscripcion servicioSuscripcionMock;

    @Mock
    private ServicioCDN servicioCDNMock;

    @Mock
    private OptimizadorCalidadVideo optimizador;

    @InjectMocks
    private GestorReproduccion gestor;

    @Test
    @DisplayName("Debe devolver error si la cuenta esta inactiva")
    void cuentaInactiva() {
        when(servicioSuscripcionMock.cuentaEstaActiva("pepe"))
                .thenReturn(false);

        assertThrows(IllegalArgumentException.class,
                ()-> gestor.iniciarReproduccion("pepe","v123", 7));
        //assertEquals("ERROR: Cuenta inactiva o suspendida", resultadoEsperado);

        verifyNoInteractions(servicioCDNMock);
    }

    @Test
    void cdnNoDisponible() {
        when(servicioSuscripcionMock.cuentaEstaActiva("pepe"))
                .thenReturn(true);

        when(servicioCDNMock.cdnEstaDisponible())
                .thenReturn(false);

        assertThrows(IllegalArgumentException.class,
                ()-> gestor.iniciarReproduccion("pepe","v123", 7));
    }

    @Test
    void urlVacio() {
        when(servicioSuscripcionMock.cuentaEstaActiva("user123"))
                .thenReturn(true);
        when(servicioCDNMock.cdnEstaDisponible())
                .thenReturn(true);
        when(servicioSuscripcionMock.obtenerPlanSuscripcion("user123"))
                .thenReturn("Premium");
        when(optimizador.determinarCalidadOptima(25,"Premium"))
                .thenReturn("4K");
        when(servicioCDNMock.obtenerUrlVideo("video123","4K"))
                .thenReturn("");

        assertThrows(IllegalArgumentException.class,
                ()-> gestor.iniciarReproduccion("user123","video123", 25));
    }

    @Test
    @DisplayName("Debe reproducir el video correctamente si todo está bien")
    void reproduccionExitosa() {
        when(servicioSuscripcionMock.cuentaEstaActiva("user123"))
                .thenReturn(true);
        when(servicioCDNMock.cdnEstaDisponible())
                .thenReturn(true);
        when(servicioSuscripcionMock.obtenerPlanSuscripcion("user123"))
                .thenReturn("Premium");
        when(optimizador.determinarCalidadOptima(25,"Premium"))
                .thenReturn("4K");
        when(servicioCDNMock.obtenerUrlVideo("video123","4K"))
                .thenReturn("http://cdn.com/video99_4K.mp4");

        String resultado = gestor.iniciarReproduccion("user123","video123",25);

        assertEquals("Reproduciendo en 4K desde http://cdn.com/video99_4K.mp4", resultado);
    }
}
