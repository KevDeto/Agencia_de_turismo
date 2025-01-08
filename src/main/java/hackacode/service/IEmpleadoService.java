package hackacode.service;

import java.util.List;

import hackacode.model.dto.EmpleadoDTO;
import hackacode.model.entity.Empleado;

public interface IEmpleadoService {
	
	List<Empleado> listarEmpleados();

	Empleado crearEmpleado(EmpleadoDTO empleado);

	Empleado obtenerEmpleadoPorId(Long id);

	void eliminarEmpleado(Empleado empleado);

	boolean existeEmpleadoPorId(Long id);
}
