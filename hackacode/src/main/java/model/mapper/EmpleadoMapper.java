package model.mapper;

import org.springframework.stereotype.Component;

import model.dto.EmpleadoDTO;
import model.entity.Empleado;

@Component
public class EmpleadoMapper {

	public EmpleadoDTO convertirEntidadEnDto(Empleado empleado) {
		EmpleadoDTO empleadoDto = new EmpleadoDTO();
		empleadoDto.setNombre(empleado.getNombre());
		empleadoDto.setApellido(empleado.getApellido());
		empleadoDto.setDireccion(empleado.getDireccion());
		empleadoDto.setDni(empleado.getDni());
		empleadoDto.setFecha_nac(empleado.getFecha_nac());
		empleadoDto.setNacionalidad(empleado.getNacionalidad());
		empleadoDto.setCelular(empleado.getCelular());
		empleadoDto.setEmail(empleado.getEmail());
		empleadoDto.setCargo(empleado.getCargo());
		empleadoDto.setSueldo(empleado.getSueldo());
		return empleadoDto;
	}

	public Empleado dtoToEntity(EmpleadoDTO empleadoDto) {
		Empleado empleado = new Empleado();
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
		return empleado;
	}
}
