package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.dao.IClienteRepository;
import model.dto.ClienteDTO;
import model.entity.Cliente;
import model.entity.Persona;
import service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepository clienteRepository;

	@Override
	public Cliente guardar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> obtenerTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente obtenerPorId(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
	}

	@Override
	public Cliente actualizar(Long id, Cliente cliente) {
		Cliente clienteActualizado = (Cliente) clienteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
		// clienteActualizado.setNombre(cliente);
//	    // Actualizamos los campos permitidos
//        cliente.setNombre(clienteDetalles.getNombre());
//        cliente.setApellido(clienteDetalles.getApellido());
//        cliente.setDni(clienteDetalles.getDni());
//        cliente.setEmail(clienteDetalles.getEmail());
//
//        return clienteDAO.save(cliente);
//    }
//
//    @Override
//    public void eliminar(Long id) {
//        clienteDAO.deleteById(id); // Método deleteById de JpaRepository
//    }
		return clienteActualizado;
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub

	}

}
