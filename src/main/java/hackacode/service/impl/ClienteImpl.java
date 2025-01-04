package hackacode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hackacode.model.dto.ClienteDTO;
import hackacode.model.entity.Cliente;
import hackacode.model.repository.IClienteDao;
import hackacode.service.IClienteService;

import java.util.List;

@Service
public class ClienteImpl implements IClienteService {

    @Autowired
    private IClienteDao clienteDao;

    @Transactional
    @Override
    public Cliente save(ClienteDTO clienteDTO) {
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
        return clienteDao.save(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Long id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    public boolean existsById(Long id) {
        return clienteDao.existsById(id);
    }

    @Override
    public List<Cliente> listAll() {
        return (List<Cliente>)clienteDao.findAll();
    }

}
