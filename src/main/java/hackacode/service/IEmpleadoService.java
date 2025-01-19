package hackacode.service;

import java.util.List;

import hackacode.model.dto.EmpleadoDTO;
import hackacode.model.entity.Empleado;

public interface IEmpleadoService {
	
	List<EmpleadoDTO> listarEmpleados();

	EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO);

	EmpleadoDTO obtenerEmpleadoPorId(Long id);
	
	EmpleadoDTO actualizarEmpleado(Long id, EmpleadoDTO empleadoDTO);

	void eliminarEmpleado(Long id);

	boolean existeEmpleadoPorId(Long id);
}
