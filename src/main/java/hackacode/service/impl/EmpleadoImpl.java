package hackacode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hackacode.model.dao.IEmpleadoDao;
import hackacode.model.dto.EmpleadoDto;
import hackacode.model.entity.Empleado;
import hackacode.service.IEmpleadoService;

@Service
public class EmpleadoImpl implements IEmpleadoService{
    @Autowired
    private IEmpleadoDao empleadoDao;

    @Transactional
    @Override
    public Empleado save(EmpleadoDto empleadoDto) {
    	Empleado cliente = Empleado.builder()
                .UUID(empleadoDto.getUUID())
                .nombre(empleadoDto.getNombre())
                .apellido(empleadoDto.getApellido())
                .dni(empleadoDto.getDni())
                .nacionalidad(empleadoDto.getNacionalidad())
                .email(empleadoDto.getEmail())
                .celular(empleadoDto.getCelular())
                .fecha_nac(empleadoDto.getFecha_nac())
                .direccion(empleadoDto.getDireccion())
                .cargo(empleadoDto.getCargo())
                .sueldo(empleadoDto.getSueldo())
                .build();
        return empleadoDao.save(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public Empleado findById(Long id) {
        return empleadoDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Empleado empleado) {
    	empleadoDao.delete(empleado);
    }

    @Override
    public boolean existsById(Long id) {
        return empleadoDao.existsById(id);
    }

    @Override
    public List<Empleado> listAll() {
        return (List)empleadoDao.findAll();
    }
}
