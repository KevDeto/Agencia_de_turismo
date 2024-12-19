package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.dao.IClienteRepository;
import model.dto.ClienteDTO;
import model.entity.Cliente;
import service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepository clienteRepository;

	@Override
	public ClienteDTO guardar(ClienteDTO clienteDTO) {
		return clienteRepository.save(clienteDTO);
	}

	@Override
	public List<ClienteDTO> obtenerTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public ClienteDTO obtenerPorId(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
	}

	@Override
	public ClienteDTO actualizar(Long id, ClienteDTO clienteDTO) {
	    // Buscar la entidad en el repositorio
	    ClienteDTO cliente = clienteRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

	    // Actualizar los campos de la entidad
	    cliente.setNombre(clienteDTO.getNombre());
	    cliente.setApellido(clienteDTO.getApellido());
	    cliente.setDni(clienteDTO.getDni());
	    cliente.setEmail(clienteDTO.getEmail());

	    // Guardar la entidad actualizada
	    ClienteDTO clienteActualizado = clienteRepository.save(cliente);

	    // Convertir la entidad a DTO antes de devolverla
	    return entityToDTO(clienteActualizado);
	}

	// Conversión de entidad a DTO
	private ClienteDTO entityToDTO(ClienteDTO clienteActualizado) {
	    ClienteDTO dto = new ClienteDTO();
	    dto.setNombre(clienteActualizado.getNombre());
	    dto.setApellido(clienteActualizado.getApellido());
	    dto.setDni(clienteActualizado.getDni());
	    dto.setEmail(clienteActualizado.getEmail());
	    return dto;
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
	}

}
