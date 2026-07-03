package ee2.mocks;

import java.util.List;
import java.util.Optional;

/**
 * La DEPENDENCIA del servicio. En producción la implementación real va a la
 * base de datos; en el test NO existe → se reemplaza con un @Mock.
 *
 * CLAVE DE DISEÑO: el servicio depende de esta INTERFAZ (abstracción), no de
 * una implementación concreta → principio de inversión de dependencias (DIP).
 * Eso es lo que hace posible "enchufar" el mock.
 */
public interface MedicamentoRepository {
    Medicamento save(Medicamento medicamento);
    Optional<Medicamento> findById(Long id);
    List<Medicamento> findAll();
    void update(Medicamento medicamento);
    void deleteById(Long id);
}
