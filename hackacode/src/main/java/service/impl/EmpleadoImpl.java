package service.impl;

import model.dao.IEmpleadoRepository;
import model.dto.EmpleadoDto;
import model.entity.Empleado;
import org.springframework.stereotype.Service;
import service.IEmpleadoService;

import java.util.List;

@Service
public class EmpleadoImpl implements IEmpleadoService {
    @Override
    public List<Empleado> listAll() {
        return List.of();
    }

    @Override
    public Empleado save(EmpleadoDto empleadoDto) {
        return null;
    }

    @Override
    public Empleado findById(Integer id) {
        return null;
    }

    @Override
    public void delete(Empleado empleado) {

    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }
}
