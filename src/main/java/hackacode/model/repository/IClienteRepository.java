package hackacode.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hackacode.model.dto.ClienteDTO;
import hackacode.model.entity.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long>{
    
}
