package hackacode.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hackacode.model.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long>{
    
}
