package hackacode.service;

import java.util.List;

import hackacode.model.dto.ClienteDTO;
import hackacode.model.entity.Cliente;

public interface IClienteService {
	
	List<Cliente> listarClientes();

	Cliente crearCliente(ClienteDTO cliente);

	Cliente obtenerClientePorId(Long id);

	void eliminarCliente(Cliente cliente);

	boolean existeClientePorId(Long id);
}
