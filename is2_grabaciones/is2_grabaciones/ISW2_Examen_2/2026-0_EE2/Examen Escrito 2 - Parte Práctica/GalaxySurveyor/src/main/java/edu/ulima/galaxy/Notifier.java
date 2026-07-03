package edu.ulima.galaxy;

/**
 * Canal de notificación (simplificado).
 * No se requiere mock: en pruebas se puede usar la implementación en memoria.
 */
public interface Notifier {
    void notifyUniversity(Quadrant quadrant, double probability);
    void notifyNasa(Quadrant quadrant, double probability);
}
