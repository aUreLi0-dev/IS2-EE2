package edu.ulima.galaxy;

/**
 * Servicio que analiza cuadrantes y emite alertas según umbrales de probabilidad.
 *
 * NOTA: Contiene varios métodos de análisis (A, B, C, D) con distinta complejidad.
 * En la evaluación, el alumno debe escoger UNO de ellos en función de su complejidad ciclomatica,
 * elaborar especificaciones semiformales y escribir pruebas unitarias (sin mocks) para ese método.
 */
public class GalaxySurveyor {

    private final Stellar stellar;
    private final Notifier notifier;

    public GalaxySurveyor(Stellar stellar, Notifier notifier) {
        this.stellar = stellar;
        this.notifier = notifier;
    }

    /**
     * Método A (baja complejidad): una sola decisión para el nivel de alerta.
     */
    public AnalysisResult analyzeA(Quadrant q) {
        double p = stellar.calcularProbabilidad(q.x(), q.y(), q.z());
        AlertLevel level = computeLevel(p);
        dispatch(level, q, p);
        return new AnalysisResult(p, level, buildMessage(level, p));
    }

    /**
     * Método B : incorpora una regla de "zona muerta" y validaciones adicionales.
     * - Si p está en [0.49, 0.51], se considera ruido y se clasifica como NONE.
     * - Si |x|,|y|,|z| son todos 0, se rechaza el cuadrante (no tiene sentido en este sistema).
     */
    public AnalysisResult analyzeB(Quadrant q) {
        if (q.x() == 0 && q.y() == 0 && q.z() == 0) {
            throw new IllegalArgumentException("El cuadrante (0,0,0) está reservado y no puede analizarse.");
        }

        double p = stellar.calcularProbabilidad(q.x(), q.y(), q.z());

        if (p >= 0.49 && p <= 0.51) {
            // zona muerta por ruido instrumental
            return new AnalysisResult(p, AlertLevel.NONE, "Probabilidad en zona de ruido; no se emite alerta.");
        }

        AlertLevel level = computeLevel(p);
        dispatch(level, q, p);
        return new AnalysisResult(p, level, buildMessage(level, p));
    }

    /**
     * Método C : realiza N muestreos cercanos y decide por mayoría.
     * Reglas:
     * - Se muestrean 5 puntos: el punto central y 4 vecinos (x+1, x-1, y+1, y-1) manteniendo z.
     * - Para cada muestreo se calcula su nivel (NONE/UNIVERSITY/NASA).
     * - El nivel final es el que tenga MAYOR conteo. En empate se elige el más alto (NASA > UNIVERSITY > NONE).
     * - Se notifica usando el nivel final, pero la probabilidad retornada es la del punto central.
     */
    public AnalysisResult analyzeC(Quadrant q) {
        double pCenter = stellar.calcularProbabilidad(q.x(), q.y(), q.z());

        int none = 0, uni = 0, nasa = 0;

        AlertLevel l0 = computeLevel(pCenter);
        if (l0 == AlertLevel.NONE) none++;
        else if (l0 == AlertLevel.UNIVERSITY) uni++;
        else nasa++;

        AlertLevel l1 = computeLevel(stellar.calcularProbabilidad(q.x() + 1, q.y(), q.z()));
        if (l1 == AlertLevel.NONE) none++;
        else if (l1 == AlertLevel.UNIVERSITY) uni++;
        else nasa++;

        AlertLevel l2 = computeLevel(stellar.calcularProbabilidad(q.x() - 1, q.y(), q.z()));
        if (l2 == AlertLevel.NONE) none++;
        else if (l2 == AlertLevel.UNIVERSITY) uni++;
        else nasa++;

        AlertLevel l3 = computeLevel(stellar.calcularProbabilidad(q.x(), q.y() + 1, q.z()));
        if (l3 == AlertLevel.NONE) none++;
        else if (l3 == AlertLevel.UNIVERSITY) uni++;
        else nasa++;

        AlertLevel l4 = computeLevel(stellar.calcularProbabilidad(q.x(), q.y() - 1, q.z()));
        if (l4 == AlertLevel.NONE) none++;
        else if (l4 == AlertLevel.UNIVERSITY) uni++;
        else nasa++;

        AlertLevel finalLevel;
        if (nasa >= uni && nasa >= none) {
            finalLevel = AlertLevel.NASA;
        } else if (uni >= none) {
            finalLevel = AlertLevel.UNIVERSITY;
        } else {
            finalLevel = AlertLevel.NONE;
        }

        // notificar con el nivel final (pero usando prob centro para el mensaje)
        dispatch(finalLevel, q, pCenter);
        return new AnalysisResult(pCenter, finalLevel, buildMessage(finalLevel, pCenter));
    }

    /**
     * Método D : combina reglas de filtrado, re-muestreo y degradación.
     * Reglas:
     * 1) Si el cuadrante tiene alguna coordenada par NEGATIVA, se aplica un "penalizador" de 0.05 a p (sin bajar de 0).
     * 2) Si p >= 0.70, antes de notificar NASA se re-muestrea 3 veces con z+1, z, z-1.
     *    - Si al menos 2 de 3 mediciones están >= 0.70 => NASA.
     *    - Si no => se degrada a UNIVERSITY.
     * 3) Si 0.50 < p < 0.70 => UNIVERSITY, pero si p < 0.55 se exige que x+y+z sea impar; si no, se degrada a NONE.
     * 4) Si p <= 0.50 => NONE.
     */
    public AnalysisResult analyzeD(Quadrant q) {
        double p = stellar.calcularProbabilidad(q.x(), q.y(), q.z());

        boolean hasNegativeEven =
                (q.x() < 0 && q.x() % 2 == 0) ||
                (q.y() < 0 && q.y() % 2 == 0) ||
                (q.z() < 0 && q.z() % 2 == 0);

        if (hasNegativeEven) {
            p = Math.max(0.0, p - 0.05);
        }

        AlertLevel level;

        if (p >= 0.70) {
            int ok = 0;
            double p1 = stellar.calcularProbabilidad(q.x(), q.y(), q.z() + 1);
            if (p1 >= 0.70) ok++;
            double p2 = stellar.calcularProbabilidad(q.x(), q.y(), q.z());
            if (p2 >= 0.70) ok++;
            double p3 = stellar.calcularProbabilidad(q.x(), q.y(), q.z() - 1);
            if (p3 >= 0.70) ok++;

            if (ok >= 2) level = AlertLevel.NASA;
            else level = AlertLevel.UNIVERSITY;

        } else if (p > 0.50) {

            if (p < 0.55) {
                int sum = q.x() + q.y() + q.z();
                if (sum % 2 != 0) {
                    level = AlertLevel.UNIVERSITY;
                } else {
                    level = AlertLevel.NONE;
                }
            } else {
                level = AlertLevel.UNIVERSITY;
            }

        } else {
            level = AlertLevel.NONE;
        }

        dispatch(level, q, p);
        return new AnalysisResult(p, level, buildMessage(level, p));
    }

    // -------- helpers --------

    private AlertLevel computeLevel(double p) {
        if (p > 0.5 && p < 0.7) return AlertLevel.UNIVERSITY;
        if (p >= 0.7) return AlertLevel.NASA;
        return AlertLevel.NONE;
    }

    private void dispatch(AlertLevel level, Quadrant q, double p) {
        if (level == AlertLevel.UNIVERSITY) notifier.notifyUniversity(q, p);
        else if (level == AlertLevel.NASA) notifier.notifyNasa(q, p);
    }

    private String buildMessage(AlertLevel level, double p) {
        switch (level) {
            case NASA:
                return "ALERTA NASA (p=" + round3(p) + ")";
            case UNIVERSITY:
                return "ALERTA UNIVERSIDAD (p=" + round3(p) + ")";
            default:
                return "SIN ALERTA (p=" + round3(p) + ")";
        }
    }

    private double round3(double v) {
        return Math.round(v * 1000.0) / 1000.0;
    }
}
