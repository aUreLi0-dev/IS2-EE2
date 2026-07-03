package ee2.mocks;

/**
 * ============================================================================
 * MOCKS — la clase BAJO PRUEBA (SUT): lógica de negocio con una dependencia
 * ============================================================================
 * Patrón exacto del curso (Sistema de Ventas de Videojuegos):
 *   Service --> interfaz Repository --> (implementación real: BD, NO disponible)
 *
 * El método dispensar() tiene 4 RAMAS — cada una será un test:
 *   1. medicamento no existe            -> RuntimeException
 *   2. cantidad nula o <= 0             -> RuntimeException
 *   3. requiere receta y no la trae     -> RuntimeException
 *   4. stock insuficiente               -> RuntimeException
 *   5. camino feliz: calcula total, descuenta stock y PERSISTE (update)
 */
public class DispensacionService {

    private final MedicamentoRepository repository;

    /** Inyección por CONSTRUCTOR: en producción entra el repo real, en el test entra el mock. */
    public DispensacionService(MedicamentoRepository repository) {
        this.repository = repository;
    }

    /** Dispensa 'cantidad' unidades del medicamento 'id'. Retorna el importe total. */
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
        med.setStock(med.getStock() - cantidad);   // efecto colateral 1: cambia el stock
        repository.update(med);                    // efecto colateral 2: persiste (se verifica con verify)
        return total;
    }
}
