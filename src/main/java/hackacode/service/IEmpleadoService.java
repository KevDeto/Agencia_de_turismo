package hackacode.service;

import java.util.List;

import hackacode.model.dto.EmpleadoDTO;
import hackacode.model.entity.Empleado;

public interface IEmpleadoService {
    List<Empleado> listAll();
    Empleado save(EmpleadoDTO empleado);
    Empleado findById(Long id);
    void delete(Empleado empleado);
    boolean existsById(Long id);
}
