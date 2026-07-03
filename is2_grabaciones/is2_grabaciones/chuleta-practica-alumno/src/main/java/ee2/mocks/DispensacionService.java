package ee2.mocks;

/**
 * ============================================================================
 * EJERCICIO 3 — MOCKS (patrón del curso: Service + Repository)
 * ============================================================================
 * Una farmacia dispensa medicamentos. La lógica de negocio está en esta
 * clase, que depende de la interfaz MedicamentoRepository (la implementación
 * real accede a una base de datos que NO está disponible en pruebas).
 *
 * TU TRABAJO está en DispensacionServiceTest. NO modifiques esta clase.
 * (Consejo de examen: antes de escribir tests, LEE el método y anota en
 * papel cuántas ramas tiene y qué hace cada una.)
 */
public class DispensacionService {

    private final MedicamentoRepository repository;

    public DispensacionService(MedicamentoRepository repository) {
        this.repository = repository;
    }

    /**
     * Dispensa (vende) 'cantidad' unidades del medicamento 'id'.
     * 'tieneReceta' indica si el cliente presentó receta médica.
     * Retorna el importe total a pagar.
     */
    public Double dispensar(Long id, Integer cantidad, boolean tieneReceta) {
        Medicamento med = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));

        if (cantidad == null || cantidad <= 0) {
            throw new RuntimeException("Cantidad invalida");
        }
        if (med.getRequiereReceta() && !tieneReceta) {
            throw new RuntimeException("Receta requerida para: " + med.getNombre());
        }
        if (med.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente para: " + med.getNombre());
        }

        Double total = med.getPrecioUnitario() * cantidad;
        med.setStock(med.getStock() - cantidad);
        repository.update(med);
        return total;
    }
}
