package hackacode.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hackacode.model.dto.EmpleadoDTO;
import hackacode.model.entity.Empleado;

public interface IEmpleadoRepository extends JpaRepository<Empleado, Long>{

}
