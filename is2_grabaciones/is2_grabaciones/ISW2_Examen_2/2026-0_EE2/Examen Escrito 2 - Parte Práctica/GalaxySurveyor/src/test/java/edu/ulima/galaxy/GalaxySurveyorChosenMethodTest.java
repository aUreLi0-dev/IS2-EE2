package edu.ulima.galaxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Esqueleto de pruebas. El alumno debe:
 *  - Elegir UNO de los métodos analyzeA/analyzeB/analyzeC/analyzeD
 *    (según el análisis de complejidad ciclomatica) y probarlo.
 *  - NO usar mocks.
 */
public class GalaxySurveyorChosenMethodTest {
    private GalaxySurveyor surveyor;
    private InMemoryNotifier notifier;

    @BeforeEach
    public void setUp(){
        notifier = new InMemoryNotifier();
        surveyor = new GalaxySurveyor(new Stellar(), notifier);
    }

    @Test
    public void testCamino1_CuadranteReservado(){
        assertThrows(IllegalArgumentException.class,
                () -> surveyor.analyzeB(new Quadrant(0,0,0)));
    }

    @Test
    public void testCamino2_ZonaMuerta(){
        AnalysisResult resultado = surveyor.analyzeB(new Quadrant(0, -200, -170));
        assertEquals(AlertLevel.NONE, resultado.alertLevel());
        assertTrue(notifier.events().isEmpty());
    }

    @Test
    public void testCamino3_SinAlerta(){
        AnalysisResult resultado = surveyor.analyzeB(new Quadrant(1, 1, 1));
        assertEquals(AlertLevel.NONE, resultado.alertLevel());
        assertTrue(notifier.events().isEmpty());
    }

    @Test
    public void testCamino4_AlertaUniversidad(){
        AnalysisResult resultado = surveyor.analyzeB(new Quadrant(0, 0, -499));
        assertEquals(AlertLevel.UNIVERSITY, resultado.alertLevel());
        assertEquals(1, notifier.events().size());
        assertEquals("UNIVERSITY", notifier.events().get(0).target);
    }

    @Test
    public void testCamino5_AlertaNASA(){
        AnalysisResult resultado = surveyor.analyzeB(new Quadrant(10, 20, 30));
        assertEquals(AlertLevel.NASA, resultado.alertLevel());
        assertEquals(1, notifier.events().size());
        assertEquals("NASA", notifier.events().get(0).target);
    }

}

/*
Camino 1: q = (0,0,0) -> IllegalArgumentException
Camino 2: p en [0.49, 0.51] -> zona muerta -> NONE, sin notificación
Camino 3: p < 0.49 -> NONE, sin notificación
Camino 4: 0.51 < p < 0.7 -> UNIVERSITY, notifica universidad
Camino 5: p >= 0.7 -> NASA, notifica NASA
 */
