package hackacode.service;

import java.util.List;

import hackacode.model.dto.ClienteDto;
import hackacode.model.dto.EmpleadoDto;
import hackacode.model.entity.Cliente;
import hackacode.model.entity.Empleado;

public interface IEmpleadoService {
    List<Empleado> listAll();
    Empleado save(EmpleadoDto empleado);
    Empleado findById(Long id);
    void delete(Empleado empleado);
    boolean existsById(Long id);
}
