package edu.ulima.galaxy;

/**
 * Resultado de análisis para un cuadrante.
 */
public enum AlertLevel {
    NONE,          // probabilidad <= 0.5
    UNIVERSITY,    // 0.5 < probabilidad < 0.7
    NASA           // probabilidad >= 0.7
}
