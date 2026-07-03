package edu.ulima.galaxy;


public final class AnalysisResult {
    private final double probability;
    private final AlertLevel alertLevel;
    private final String message;

    public AnalysisResult(double probability, AlertLevel alertLevel, String message) {
        this.probability = probability;
        this.alertLevel = alertLevel;
        this.message = message;
    }

    public double probability() { return probability; }
    public AlertLevel alertLevel() { return alertLevel; }
    public String message() { return message; }

    @Override
    public String toString() {
        return "AnalysisResult{probability=" + probability + ", alertLevel=" + alertLevel + ", message='" + message + "'}";
    }
}
