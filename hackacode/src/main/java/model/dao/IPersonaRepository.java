package model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.dto.PersonaDTO;
import model.entity.Persona;

@Repository
public interface IPersonaRepository extends JpaRepository<PersonaDTO, Long>{

}
