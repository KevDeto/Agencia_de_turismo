package hackacode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hackacode.model.dao.IClienteDao;
import hackacode.model.dto.ClienteDto;
import hackacode.model.entity.Cliente;
import hackacode.service.IClienteService;

import java.util.List;

@Service
public class ClienteImpl implements IClienteService {

    @Autowired
    private IClienteDao clienteDao;

    @Transactional
    @Override
    public Cliente save(ClienteDto clienteDto) {
        Cliente cliente = Cliente.builder()
                .UUID(clienteDto.getUUID())
                .nombre(clienteDto.getNombre())
                .apellido(clienteDto.getApellido())
                .dni(clienteDto.getDni())
                .nacionalidad(clienteDto.getNacionalidad())
                .email(clienteDto.getEmail())
                .celular(clienteDto.getCelular())
                .fecha_nac(clienteDto.getFecha_nac())
                .direccion(clienteDto.getDireccion())
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
        return (List)clienteDao.findAll();
    }

}
