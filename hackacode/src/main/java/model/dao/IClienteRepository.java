package model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.dto.ClienteDTO;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteDTO, Long>{

}
