package hackacode.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hackacode.model.entity.ServicioTuristico;

@Repository
public interface ServicioTuristicoRepository extends JpaRepository<ServicioTuristico, Long> {
    // Puedes añadir consultas personalizadas aquí si es necesario.
}
