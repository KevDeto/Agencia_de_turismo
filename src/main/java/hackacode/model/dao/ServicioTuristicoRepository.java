package hackacode.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hackacode.model.entity.ServicioTuristico;

@Repository
public interface ServicioTuristicoRepository extends JpaRepository<ServicioTuristico, Long> {
}
