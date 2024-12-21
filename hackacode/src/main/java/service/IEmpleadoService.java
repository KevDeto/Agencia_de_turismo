package service;

import model.dto.EmpleadoDto;
import model.entity.Empleado;

import java.util.List;

public interface IEmpleadoService {
    List<Empleado> listAll();
    Empleado save(EmpleadoDto empleadoDto);
    Empleado findById(Long id);
    void delete(Empleado empleado);
    boolean existsById(Long id);
}
