package model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.dto.EmpleadoDTO;
import model.entity.Empleado;

@Repository
public interface IEmpleadoRepository extends JpaRepository<EmpleadoDTO, Long>{

}
