package hackacode.service;

import java.util.List;

import hackacode.model.dto.ClienteDTO;

public interface IClienteService {
	
	List<ClienteDTO> listarClientes();

	ClienteDTO crearCliente(ClienteDTO clienteDTO);
	
	ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO);

	ClienteDTO obtenerClientePorId(Long id);

	void eliminarCliente(Long id);

	boolean existeClientePorId(Long id);
}
