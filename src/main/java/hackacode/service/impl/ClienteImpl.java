package hackacode.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hackacode.model.dto.ClienteDTO;
import hackacode.model.entity.Cliente;
import hackacode.model.repository.IClienteRepository;
import hackacode.service.IClienteService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteImpl implements IClienteService {

    private final IClienteRepository clienteRepository;

    @Transactional
    @Override
    public Cliente crearCliente(ClienteDTO clienteDTO) {
        Cliente cliente = Cliente.builder()
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
        return clienteRepository.save(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

    @Override
    public boolean existeClientePorId(Long id) {
        return clienteRepository.existsById(id);
    }

    @Override
    public List<Cliente> listarClientes() {
        return (List<Cliente>)clienteRepository.findAll();
    }

}
