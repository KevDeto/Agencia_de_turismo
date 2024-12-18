package service;

import java.util.List;

import model.entity.Cliente;

public interface IClienteService {
	Cliente guardar(Cliente cliente);
	List<Cliente> obtenerTodos();
	Cliente obtenerPorId(Long id);
	Cliente actualizar(Long id, Cliente cliente);
	void eliminar(Long id);
}
