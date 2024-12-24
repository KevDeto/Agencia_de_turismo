package hackacode.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import hackacode.model.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long>{
    
}
