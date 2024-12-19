package service;

import java.util.List;

import model.dto.ClienteDTO;

public interface IClienteService {
	ClienteDTO guardar(ClienteDTO clienteDTO);

	List<ClienteDTO> obtenerTodos();

	ClienteDTO obtenerPorId(Long id);

	ClienteDTO actualizar(Long id, ClienteDTO clienteDTO);

	void eliminar(Long id);
}
