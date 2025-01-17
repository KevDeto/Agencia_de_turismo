package hackacode.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hackacode.model.entity.PaqueteTuristico;

@Repository
public interface IPaqueteTuristicoRepository extends JpaRepository<PaqueteTuristico, Long> {
}
