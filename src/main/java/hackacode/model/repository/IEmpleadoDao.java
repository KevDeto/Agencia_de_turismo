package hackacode.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hackacode.model.entity.Empleado;

public interface IEmpleadoDao extends JpaRepository<Empleado, Long>{

}
