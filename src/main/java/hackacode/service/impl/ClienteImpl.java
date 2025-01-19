package hackacode.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hackacode.exception.ResourceNotFoundException;
import hackacode.model.dto.ClienteDTO;
import hackacode.model.entity.Cliente;
import hackacode.model.mapper.ClienteMapper;
import hackacode.model.repository.IClienteRepository;
import hackacode.service.IClienteService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class ClienteImpl implements IClienteService {

    private final IClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    
    public ClienteImpl(ClienteMapper clienteMapper, IClienteRepository clienteRepository) {
    	this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }
    
    @Override
    public List<ClienteDTO> listarClientes() {
    	return clienteMapper.convertirListaEntidadEnDto(clienteRepository.findAll());
    }
    
    @Transactional
    @Override
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
    	Cliente cliente = clienteMapper.convertirDtoEnEntidad(clienteDTO);
    	Cliente clienteGuardado = clienteRepository.save(cliente);
    	return clienteMapper.convertirEntidadEnDto(clienteGuardado);
    }

    @Transactional(readOnly = true)
    @Override
    public ClienteDTO obtenerClientePorId(Long id) {
    	Cliente cliente = clienteRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Cliente con ID " + id + " no encontrado."));
        return clienteMapper.convertirEntidadEnDto(cliente);
    }

    @Transactional
    @Override
    public void eliminarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente con ID " + id + " no encontrado."));
        clienteRepository.delete(cliente);
    }

    @Override
    public boolean existeClientePorId(Long id) {
        return clienteRepository.existsById(id);
    }

	@Override
	public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente con ID " + id + " no encontrado."));
        clienteMapper.actualizarEntidadDesdeDto(clienteDTO, cliente);
        Cliente clienteActualizado = clienteRepository.save(cliente);
        return clienteMapper.convertirEntidadEnDto(clienteActualizado);
	}

}
