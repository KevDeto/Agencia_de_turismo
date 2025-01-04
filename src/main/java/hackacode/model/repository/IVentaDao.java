package hackacode.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hackacode.model.entity.Venta;

public interface IVentaDao extends JpaRepository<Venta, Long>{

}
