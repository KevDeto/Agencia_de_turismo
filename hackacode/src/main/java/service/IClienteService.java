package service;

import model.dto.ClienteDto;
import model.entity.Cliente;

import java.util.List;

public interface IClienteService {
    List<Cliente> listAll();
    Cliente save(ClienteDto cliente);
    Cliente findById(Long id);
    void delete(Cliente cliente);
    boolean existsById(Long id);
}
