package hackacode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hackacode.model.dto.EmpleadoDTO;
import hackacode.model.entity.Empleado;
import hackacode.model.repository.IEmpleadoRepository;
import hackacode.service.IEmpleadoService;

@Service
public class EmpleadoImpl implements IEmpleadoService{
    @Autowired
    private IEmpleadoRepository empleadoRepository;

    @Transactional
    @Override
    public Empleado save(EmpleadoDTO empleadoDTO) {
    	Empleado cliente = Empleado.builder()
                .UUID(empleadoDTO.getUUID())
                .nombre(empleadoDTO.getNombre())
                .apellido(empleadoDTO.getApellido())
                .dni(empleadoDTO.getDni())
                .nacionalidad(empleadoDTO.getNacionalidad())
                .email(empleadoDTO.getEmail())
                .celular(empleadoDTO.getCelular())
                .fecha_nac(empleadoDTO.getFecha_nac())
                .direccion(empleadoDTO.getDireccion())
                .cargo(empleadoDTO.getCargo())
                .sueldo(empleadoDTO.getSueldo())
                .build();
        return empleadoRepository.save(cliente);
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
        return (List<Empleado>)empleadoRepository.findAll();
    }
}
