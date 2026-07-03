package pe.edu.ulima.is2;

import java.util.*;

public class Carrito {
    private List<String> productos = new ArrayList<>();
    private double total = 0.0;

    public void agregar(String producto, double precio) {
        productos.add(producto);
        total += precio;
    }

    public boolean eliminar(String producto, double precio) {
        if (!productos.contains(producto)) return false;
        productos.remove(producto);
        total -= precio;
        return true;
    }

    public int cantidad()      { return productos.size(); }
    public double getTotal()   { return total; }
    public boolean estaVacio() { return productos.isEmpty(); }
}
