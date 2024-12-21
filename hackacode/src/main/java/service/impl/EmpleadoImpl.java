package service.impl;

import model.dao.IEmpleadoRepository;
import model.dto.EmpleadoDto;
import model.entity.Empleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import service.IEmpleadoService;

import java.util.List;

@Service
public class EmpleadoImpl implements IEmpleadoService {
 
	@Autowired
	private IEmpleadoRepository empleadoRepository;

    @Override
    public Empleado save(EmpleadoDto empleadoDto) {
        Empleado empleado = Empleado.builder()
        		.nombre(empleadoDto.getNombre())
        		.apellido(empleadoDto.getApellido())
    			.direccion(empleadoDto.getDireccion())
    			.dni(empleadoDto.getDni())
    			.fecha_nac(empleadoDto.getFecha_nac())
    			.nacionalidad(empleadoDto.getNacionalidad())
    			.celular(empleadoDto.getCelular())
    			.email(empleadoDto.getEmail())
    			.cargo(empleadoDto.getCargo())
    			.sueldo(empleadoDto.getSueldo())
    			.build();
        return empleadoRepository.save(empleado);
    }

    @Transactional(readOnly = true)
    @Override
    public Empleado findById(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Empleado empleado) {
    	empleadoRepository.delete(empleado);
    }

    @Override
    public boolean existsById(Long id) {
        return empleadoRepository.existsById(id);
    }
    
    @Override
    public List<Empleado> listAll() {
        return empleadoRepository.findAll();
    }
}
