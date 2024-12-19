package model.mapper;

import org.springframework.stereotype.Component;

import model.dto.ClienteDTO;
import model.entity.Cliente;

@Component
public class ClienteMapper {

	public ClienteDTO convertirEntidadEnDto(Cliente cliente) {
		ClienteDTO clienteDto = new ClienteDTO();
		clienteDto.setNombre(cliente.getNombre());
		clienteDto.setApellido(cliente.getApellido());
		clienteDto.setDireccion(cliente.getDireccion());
		clienteDto.setDni(cliente.getDni());
		clienteDto.setFecha_nac(cliente.getFecha_nac());
		clienteDto.setNacionalidad(cliente.getNacionalidad());
		clienteDto.setCelular(cliente.getCelular());
		clienteDto.setEmail(cliente.getEmail());
		return clienteDto;
	}

	public Cliente convertirDtoEnEntidad(ClienteDTO clienteDto) {
		Cliente cliente = new Cliente();
		cliente.setNombre(clienteDto.getNombre());
		cliente.setApellido(clienteDto.getApellido());
		cliente.setDireccion(clienteDto.getDireccion());
		cliente.setDni(clienteDto.getDni());
		cliente.setFecha_nac(clienteDto.getFecha_nac());
		cliente.setNacionalidad(clienteDto.getNacionalidad());
		cliente.setCelular(clienteDto.getCelular());
		cliente.setEmail(clienteDto.getEmail());
		return cliente;
	}
}
