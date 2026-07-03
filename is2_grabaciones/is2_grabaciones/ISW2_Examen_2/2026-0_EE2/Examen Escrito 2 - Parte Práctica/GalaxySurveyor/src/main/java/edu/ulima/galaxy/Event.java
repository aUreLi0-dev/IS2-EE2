package edu.ulima.galaxy;

public class Event {
    public final String target; // "UNIVERSITY" | "NASA"
    public final Quadrant quadrant;
    public final double probability;

    Event(String target, Quadrant quadrant, double probability) {
        this.target = target;
        this.quadrant = quadrant;
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "Event{target=" + target + ", quadrant=" + quadrant + ", probability=" + probability + "}";
    }
}