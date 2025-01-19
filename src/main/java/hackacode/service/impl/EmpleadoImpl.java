package hackacode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hackacode.exception.ResourceNotFoundException;
import hackacode.model.dto.ClienteDTO;
import hackacode.model.dto.EmpleadoDTO;
import hackacode.model.entity.Cliente;
import hackacode.model.entity.Empleado;
import hackacode.model.mapper.ClienteMapper;
import hackacode.model.mapper.EmpleadoMapper;
import hackacode.model.repository.IEmpleadoRepository;
import hackacode.service.IEmpleadoService;
import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class EmpleadoImpl implements IEmpleadoService{

	private final IEmpleadoRepository empleadoRepository;
	private final EmpleadoMapper empleadoMapper;
	
    public EmpleadoImpl(EmpleadoMapper empleadoMapper, IEmpleadoRepository empleadoRepository) {
		this.empleadoRepository = empleadoRepository;
		this.empleadoMapper = empleadoMapper;
    }
	
    @Override
    public List<EmpleadoDTO> listarEmpleados() {
    	return empleadoMapper.convertirListaEntidadEnDto(empleadoRepository.findAll());
    }
	
    @Transactional
    @Override
    public EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO) {
    	Empleado empleado = empleadoMapper.convertirDtoEnEntidad(empleadoDTO);
    	Empleado empleadoGuardado = empleadoRepository.save(empleado);
    	return empleadoMapper.convertirEntidadEnDto(empleadoGuardado);
    }

    @Transactional(readOnly = true)
    @Override
    public EmpleadoDTO obtenerEmpleadoPorId(Long id) {
    	Empleado empleado = empleadoRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Empleado con ID " + id + " no encontrado."));
        return empleadoMapper.convertirEntidadEnDto(empleado);
    }

    @Transactional
    @Override
    public void eliminarEmpleado(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado con ID " + id + " no encontrado."));
        empleadoRepository.delete(empleado);
    }

    @Override
    public boolean existeEmpleadoPorId(Long id) {
        return empleadoRepository.existsById(id);
    }

	@Override
	public EmpleadoDTO actualizarEmpleado(Long id, EmpleadoDTO empleadoDTO) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado con ID " + id + " no encontrado."));
        empleadoMapper.actualizarEntidadDesdeDto(empleadoDTO, empleado);
        Empleado empleadoActualizado = empleadoRepository.save(empleado);
        return empleadoMapper.convertirEntidadEnDto(empleadoActualizado);
	}


}
