package service;

import java.util.List;

import model.entity.Empleado;

public interface IEmpleadoService {
	Empleado guardar(Empleado empleado);
	List<Empleado> obtenerTodos();
	Empleado obtenerPorId(Long id);
	Empleado actualizar(Long id, Empleado empleado);
	void eliminar(Long id);
}
