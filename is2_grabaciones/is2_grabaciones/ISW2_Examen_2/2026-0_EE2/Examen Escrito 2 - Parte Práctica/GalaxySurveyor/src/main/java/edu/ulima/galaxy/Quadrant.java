package edu.ulima.galaxy;

import java.util.Objects;

/**
 * Representa un cuadrante del espacio (coordenadas enteras X,Y,Z).
 */
public final class Quadrant {
    private final int x;
    private final int y;
    private final int z;

    public Quadrant(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int x() { return x; }
    public int y() { return y; }
    public int z() { return z; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quadrant)) return false;
        Quadrant quadrant = (Quadrant) o;
        return x == quadrant.x && y == quadrant.y && z == quadrant.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "Quadrant{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
}
