package service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.dao.IClienteRepository;
import model.dto.ClienteDTO;
import model.entity.Cliente;
import model.mapper.ClienteMapper;
import service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepository clienteRepository;

	@Autowired
	private ClienteMapper clienteMapper;

	@Override
	public ClienteDTO guardar(ClienteDTO clienteDto) {
		Cliente cliente = clienteMapper.convertirDtoEnEntidad(clienteDto);
		Cliente clienteGuardado = clienteRepository.save(cliente);
		return clienteMapper.convertirEntidadEnDto(clienteGuardado);
	}

	@Override
	public List<ClienteDTO> obtenerTodos() {
		List<Cliente> clientes = clienteRepository.findAll();
		return clientes.stream().map(clienteMapper::convertirEntidadEnDto).collect(Collectors.toList());
	}

	@Override
	public ClienteDTO obtenerPorId(Long id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
		return clienteMapper.convertirEntidadEnDto(cliente);
	}

	@Override
	public ClienteDTO actualizar(Long id, ClienteDTO clienteDto) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

		cliente.setNombre(clienteDto.getNombre());
		cliente.setApellido(clienteDto.getApellido());
		cliente.setDireccion(clienteDto.getDireccion());
		cliente.setDni(clienteDto.getDni());
		cliente.setFecha_nac(clienteDto.getFecha_nac());
		cliente.setNacionalidad(clienteDto.getNacionalidad());
		cliente.setCelular(clienteDto.getCelular());
		cliente.setEmail(clienteDto.getEmail());

		Cliente clienteActualizado = clienteRepository.save(cliente);

		return clienteMapper.convertirEntidadEnDto(clienteActualizado);
	}

	@Override
	public void eliminar(Long id) {
		clienteRepository.deleteById(id);
	}
}
