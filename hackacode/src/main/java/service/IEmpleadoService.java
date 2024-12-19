package service;

import java.util.List;

import model.dto.EmpleadoDTO;

public interface IEmpleadoService {
	EmpleadoDTO guardar(EmpleadoDTO empleadoDTO);

	List<EmpleadoDTO> obtenerTodos();

	EmpleadoDTO obtenerPorId(Long id);

	EmpleadoDTO actualizar(Long id, EmpleadoDTO empleadoDTO);

	void eliminar(Long id);
}
