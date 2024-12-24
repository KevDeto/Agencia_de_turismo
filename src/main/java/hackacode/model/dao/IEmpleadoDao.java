package hackacode.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import hackacode.model.entity.Empleado;

public interface IEmpleadoDao extends JpaRepository<Empleado, Long>{

}
