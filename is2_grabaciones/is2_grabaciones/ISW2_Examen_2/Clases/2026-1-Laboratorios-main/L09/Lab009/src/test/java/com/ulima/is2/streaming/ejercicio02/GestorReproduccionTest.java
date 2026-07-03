package com.ulima.is2.streaming.ejercicio02;

import com.ulima.is2.streaming.ejercicio01.OptimizadorCalidadVideo;
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
    private OptimizadorCalidadVideo optimizadorMock;

    @InjectMocks
    private GestorReproduccion gestor;

    @Test
    @DisplayName("Debe devolver error si la cuenta esta inactiva")
    void cuentaInactiva() {
        // Arrange
        when(servicioSuscripcionMock.cuentaEstaActiva("user123")).thenReturn(false);

        // Act
        String resultado = gestor.iniciarReproduccion("user123", "video99", 15.0);

        // Assert
        assertEquals("ERROR: Cuenta inactiva o suspendida", resultado);
        verify(servicioSuscripcionMock).cuentaEstaActiva("user123");
        verifyNoMoreInteractions(servicioSuscripcionMock);
        verifyNoInteractions(servicioCDNMock, optimizadorMock);
    }

    @Test
    @DisplayName("Debe reproducir el video correctamente si todo está bien")
    void reproduccionExitosa() {
        // Arrange
        when(servicioSuscripcionMock.cuentaEstaActiva("user123")).thenReturn(true);
        when(servicioCDNMock.cdnEstaDisponible()).thenReturn(true);
        when(servicioSuscripcionMock.obtenerPlanSuscripcion("user123")).thenReturn("Premium");
        when(optimizadorMock.determinarCalidadOptima(30.0, "Premium")).thenReturn("4K");
        when(servicioCDNMock.obtenerUrlVideo("video99", "4K")).thenReturn("http://cdn.com/video99_4K.mp4");

        // Act
        String resultado = gestor.iniciarReproduccion("user123", "video99", 30.0);

        // Assert
        assertEquals("Reproduciendo en 4K desde http://cdn.com/video99_4K.mp4", resultado);
        verify(servicioSuscripcionMock).cuentaEstaActiva("user123");
        verify(servicioCDNMock).cdnEstaDisponible();
        verify(servicioSuscripcionMock).obtenerPlanSuscripcion("user123");
        verify(optimizadorMock).determinarCalidadOptima(30.0, "Premium");
        verify(servicioCDNMock).obtenerUrlVideo("video99", "4K");
    }

    @Test
    @DisplayName("Debe devolver error si el CDN no esta disponible")
    void cdnNoDisponible() {
        when(servicioSuscripcionMock.cuentaEstaActiva("user123")).thenReturn(true);
        when(servicioCDNMock.cdnEstaDisponible()).thenReturn(false);

        String resultado = gestor.iniciarReproduccion("user123", "video99", 15.0);

        assertEquals("ERROR: Servicio CDN no disponible temporalmente", resultado);
        verify(servicioSuscripcionMock).cuentaEstaActiva("user123");
        verify(servicioCDNMock).cdnEstaDisponible();
        verifyNoMoreInteractions(servicioSuscripcionMock, servicioCDNMock);
        verifyNoInteractions(optimizadorMock);
    }

    @Test
    @DisplayName("Debe devolver error si el video no existe en la calidad solicitada")
    void videoNoEncontradoUrlNula() {
        when(servicioSuscripcionMock.cuentaEstaActiva("user123")).thenReturn(true);
        when(servicioCDNMock.cdnEstaDisponible()).thenReturn(true);
        when(servicioSuscripcionMock.obtenerPlanSuscripcion("user123")).thenReturn("Premium");
        when(optimizadorMock.determinarCalidadOptima(15.0, "Premium")).thenReturn("1080p");
        when(servicioCDNMock.obtenerUrlVideo("video99", "1080p")).thenReturn(null);

        String resultado = gestor.iniciarReproduccion("user123", "video99", 15.0);

        assertEquals("ERROR: Video no encontrado en calidad 1080p", resultado);
        verify(servicioCDNMock).obtenerUrlVideo("video99", "1080p");
    }

    @Test
    @DisplayName("Debe devolver error si el CDN devuelve null")
    void videoNoEncontradoCdnRealDevuelveNull() {
        ServicioSuscripcion servicioSuscripcion = new ServicioSuscripcion() {
            @Override
            public boolean cuentaEstaActiva(String idUsuario) {
                return true;
            }

            @Override
            public String obtenerPlanSuscripcion(String idUsuario) {
                return "Premium";
            }
        };

        ServicioCDN servicioCDN = new ServicioCDN() {
            @Override
            public String obtenerUrlVideo(String idVideo, String calidad) {
                return null;
            }

            @Override
            public boolean cdnEstaDisponible() {
                return true;
            }
        };

        GestorReproduccion gestorReal = new GestorReproduccion(
                servicioSuscripcion,
                servicioCDN,
                new OptimizadorCalidadVideo()
        );

        String resultado = gestorReal.iniciarReproduccion("user123", "video99", 15.0);

        assertEquals("ERROR: Video no encontrado en calidad 1080p", resultado);
    }

    @Test
    @DisplayName("Debe devolver error si la URL del video esta vacia")
    void videoNoEncontradoUrlVacia() {
        when(servicioSuscripcionMock.cuentaEstaActiva("user123")).thenReturn(true);
        when(servicioCDNMock.cdnEstaDisponible()).thenReturn(true);
        when(servicioSuscripcionMock.obtenerPlanSuscripcion("user123")).thenReturn("Basico");
        when(optimizadorMock.determinarCalidadOptima(5.0, "Basico")).thenReturn("720p");
        when(servicioCDNMock.obtenerUrlVideo("video99", "720p")).thenReturn("");

        String resultado = gestor.iniciarReproduccion("user123", "video99", 5.0);

        assertEquals("ERROR: Video no encontrado en calidad 720p", resultado);
        verify(servicioCDNMock).obtenerUrlVideo("video99", "720p");
    }
}
