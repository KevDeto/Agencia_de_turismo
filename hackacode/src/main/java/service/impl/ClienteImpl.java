package service.impl;

import model.dto.ClienteDto;
import model.entity.Cliente;
import org.springframework.stereotype.Service;
import service.IClienteService;

import java.util.List;

@Service
public class ClienteImpl implements IClienteService {
    @Override
    public List<Cliente> listAll() {
        return List.of();
    }

    @Override
    public Cliente save(ClienteDto cliente) {
        return null;
    }

    @Override
    public Cliente findById(Integer id) {
        return null;
    }

    @Override
    public void delete(Cliente cliente) {

    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }
}
