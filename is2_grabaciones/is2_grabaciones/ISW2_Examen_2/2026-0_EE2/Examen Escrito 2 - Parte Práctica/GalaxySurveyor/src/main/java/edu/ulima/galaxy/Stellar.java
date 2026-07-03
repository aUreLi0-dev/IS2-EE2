package edu.ulima.galaxy;

/**
 * Sustituto local de la librería stellar.jar (para esta evaluación).
 *
 * En un escenario real esto vendría como dependencia externa.
 * Aquí se implementa una función determinística y pura, para que las pruebas NO requieran mocks.
 */
public class Stellar {

    /**
     * Retorna una probabilidad [0,1] en función de (x,y,z).
     * Lanza IllegalArgumentException si alguna coordenada está fuera de [-1_000_000, 1_000_000].
     */
    public double calcularProbabilidad(int x, int y, int z) {
        validarRango(x);
        validarRango(y);
        validarRango(z);

        // Modelo determinístico: mezcla + normalización suave.
        long mix = 1469598103934665603L;
        mix ^= x; mix *= 1099511628211L;
        mix ^= y; mix *= 1099511628211L;
        mix ^= z; mix *= 1099511628211L;

        // Asegurar positividad
        long positive = mix & 0x7fffffffffffffffL;

        // Escalar a [0,1)
        double u = (positive % 1_000_000L) / 1_000_000.0;

        // Sesgo leve hacia valores medios para que existan casos en todos los umbrales
        double p = 0.15 + 0.7 * u;

        // Clamp
        if (p < 0.0) return 0.0;
        if (p > 1.0) return 1.0;
        return p;
    }

    private void validarRango(int v) {
        if (v < -1_000_000 || v > 1_000_000) {
            throw new IllegalArgumentException("Coordenada fuera de rango: " + v);
        }
    }
}
