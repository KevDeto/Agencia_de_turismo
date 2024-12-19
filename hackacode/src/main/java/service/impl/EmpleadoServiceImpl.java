package service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.dao.IEmpleadoRepository;
import model.dto.EmpleadoDTO;
import model.entity.Empleado;
import model.mapper.EmpleadoMapper;
import service.IEmpleadoService;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

	@Autowired
	private IEmpleadoRepository empleadoRepository;

	@Autowired
	private EmpleadoMapper empleadoMapper;

	@Override
	public EmpleadoDTO guardar(EmpleadoDTO empleadoDto) {
		Empleado empleado = empleadoMapper.convertirDtoEnEntidad(empleadoDto);
		Empleado empleadoGuardado = empleadoRepository.save(empleado);
		return empleadoMapper.convertirEntidadEnDto(empleadoGuardado);
	}

	@Override
	public List<EmpleadoDTO> obtenerTodos() {
		List<Empleado> empleados = empleadoRepository.findAll();
		return empleados.stream().map(empleadoMapper::convertirEntidadEnDto).collect(Collectors.toList());
	}

	@Override
	public EmpleadoDTO obtenerPorId(Long id) {
		Empleado empleado = empleadoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Empleado no encontrado por id: " + id));
		return empleadoMapper.convertirEntidadEnDto(empleado);
	}

	@Override
	public EmpleadoDTO actualizar(Long id, EmpleadoDTO empleadoDto) {
		Empleado empleado = empleadoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Empleado no encontrado por id: " + id));

		empleado.setNombre(empleadoDto.getNombre());
		empleado.setApellido(empleadoDto.getApellido());
		empleado.setDireccion(empleadoDto.getDireccion());
		empleado.setDni(empleadoDto.getDni());
		empleado.setFecha_nac(empleadoDto.getFecha_nac());
		empleado.setNacionalidad(empleadoDto.getNacionalidad());
		empleado.setCelular(empleadoDto.getCelular());
		empleado.setEmail(empleadoDto.getEmail());
		empleado.setCargo(empleadoDto.getCargo());
		empleado.setSueldo(empleadoDto.getSueldo());

		Empleado empleadoActualizado = empleadoRepository.save(empleado);
		return empleadoMapper.convertirEntidadEnDto(empleadoActualizado);
	}

	@Override
	public void eliminar(Long id) {
		empleadoRepository.deleteById(id);
	}

}
