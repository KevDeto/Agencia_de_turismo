package hackacode.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import hackacode.model.dto.ClienteDTO;
import hackacode.model.dto.EmpleadoDTO;
import hackacode.model.entity.Cliente;
import hackacode.model.entity.Empleado;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EmpleadoMapper {
	
	public EmpleadoDTO convertirEntidadEnDto(Empleado empleado) {
		if(empleado == null) {
			return null;
		}	
		return EmpleadoDTO.builder()
				.UUID(empleado.getUUID())
				.nombre(empleado.getNombre())
				.apellido(empleado.getApellido())
				.dni(empleado.getDni())
				.nacionalidad(empleado.getNacionalidad())
				.email(empleado.getEmail())
				.celular(empleado.getCelular())
				.fecha_nac(empleado.getFecha_nac())
				.direccion(empleado.getDireccion())
				.cargo(empleado.getCargo())
				.sueldo(empleado.getSueldo())
				.build();
	}
	
	public Empleado convertirDtoEnEntidad(EmpleadoDTO empleadoDTO) {
		if(empleadoDTO == null) {
			return null;
		}
		return Empleado.builder()
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
	}
	
	public List<EmpleadoDTO> convertirListaEntidadEnDto(List<Empleado> listaEmpleados){
	    return listaEmpleados.stream()
	            .map(this::convertirEntidadEnDto)
	            .collect(Collectors.toList());
	}
	
	public void actualizarEntidadDesdeDto(EmpleadoDTO empleadoDTO, Empleado empleado) {
		if (empleadoDTO.getNombre() != null) empleado.setNombre(empleadoDTO.getNombre());
        if (empleadoDTO.getApellido() != null) empleado.setApellido(empleadoDTO.getApellido());
        if (empleadoDTO.getDni() != null) empleado.setDni(empleadoDTO.getDni());
        if (empleadoDTO.getNacionalidad() != null) empleado.setNacionalidad(empleadoDTO.getNacionalidad());
        if (empleadoDTO.getEmail() != null) empleado.setEmail(empleadoDTO.getEmail());
        if (empleadoDTO.getCelular() != null) empleado.setCelular(empleadoDTO.getCelular());
        if (empleadoDTO.getFecha_nac() != null) empleado.setFecha_nac(empleadoDTO.getFecha_nac());
        if (empleadoDTO.getDireccion() != null) empleado.setDireccion(empleadoDTO.getDireccion());
	}
}
