package hackacode.model.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import hackacode.model.dto.ClienteDTO;
import hackacode.model.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Component
public class ClienteMapper {
	
	public ClienteDTO convertirEntidadEnDto(Cliente cliente) {
		if(cliente == null) {
			return null;
		}	
		return ClienteDTO.builder()
				.UUID(cliente.getUUID())
				.nombre(cliente.getNombre())
				.apellido(cliente.getApellido())
				.dni(cliente.getDni())
				.nacionalidad(cliente.getNacionalidad())
				.email(cliente.getEmail())
				.celular(cliente.getCelular())
				.fecha_nac(cliente.getFecha_nac())
				.direccion(cliente.getDireccion())
				.build();
	}
	public Cliente convertirDtoEnEntidad(ClienteDTO clienteDTO) {
		if(clienteDTO == null) {
			return null;
		}
		return Cliente.builder()
				.UUID(clienteDTO.getUUID())
				.nombre(clienteDTO.getNombre())
				.apellido(clienteDTO.getApellido())
				.dni(clienteDTO.getDni())
				.nacionalidad(clienteDTO.getNacionalidad())
				.email(clienteDTO.getEmail())
				.celular(clienteDTO.getCelular())
				.fecha_nac(clienteDTO.getFecha_nac())
				.direccion(clienteDTO.getDireccion())
				.build();
	}
	
	public List<ClienteDTO> convertirListaEntidadEnDto(List<Cliente> listaClientes){
	    return listaClientes.stream()
	            .map(this::convertirEntidadEnDto)
	            .collect(Collectors.toList());
	}
	
	public void actualizarEntidadDesdeDto(ClienteDTO clienteDTO, Cliente cliente) {
		if (clienteDTO.getNombre() != null) cliente.setNombre(clienteDTO.getNombre());
        if (clienteDTO.getApellido() != null) cliente.setApellido(clienteDTO.getApellido());
        if (clienteDTO.getDni() != null) cliente.setDni(clienteDTO.getDni());
        if (clienteDTO.getNacionalidad() != null) cliente.setNacionalidad(clienteDTO.getNacionalidad());
        if (clienteDTO.getEmail() != null) cliente.setEmail(clienteDTO.getEmail());
        if (clienteDTO.getCelular() != null) cliente.setCelular(clienteDTO.getCelular());
        if (clienteDTO.getFecha_nac() != null) cliente.setFecha_nac(clienteDTO.getFecha_nac());
        if (clienteDTO.getDireccion() != null) cliente.setDireccion(clienteDTO.getDireccion());
	}
}
