package model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.entity.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long>{

}
