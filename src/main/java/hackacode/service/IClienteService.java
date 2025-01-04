package hackacode.service;

import java.util.List;

import hackacode.model.dto.ClienteDTO;
import hackacode.model.entity.Cliente;

public interface IClienteService {
    List<Cliente> listAll();
    Cliente save(ClienteDTO cliente);
    Cliente findById(Long id);
    void delete(Cliente cliente);
    boolean existsById(Long id);
}
