package hackacode.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hackacode.model.entity.ServicioTuristico;

@Repository
public interface IServicioTuristicoRepository extends JpaRepository<ServicioTuristico, Long> {
}
