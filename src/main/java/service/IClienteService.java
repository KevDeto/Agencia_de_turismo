package service;

import java.util.List;

import model.dto.ClienteDTO;
import model.entity.Cliente;

public interface IClienteService {
	Cliente actualizar(Long id, Cliente clienteDTO);
	
    List<Cliente> obtenerTodos();
    Cliente guardar(ClienteDTO clienteDto);
    Cliente obtenerPorId(Long id);
    void eliminar(Cliente cliente);
    boolean existsById(Integer id);
}
