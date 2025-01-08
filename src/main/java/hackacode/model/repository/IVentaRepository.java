package hackacode.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import hackacode.model.entity.Cliente;
import hackacode.model.entity.PaqueteTuristico;
import hackacode.model.entity.ServicioTuristico;
import hackacode.model.entity.Venta;

@Service
public interface IVentaRepository extends JpaRepository<Venta, Long>{
    boolean existsByClienteAndServicio(Cliente cliente, ServicioTuristico servicio);
    boolean existsByClienteAndPaquete(Cliente cliente, PaqueteTuristico paquete);
}
