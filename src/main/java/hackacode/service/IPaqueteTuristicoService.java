package hackacode.service;

import java.util.List;

import hackacode.model.dto.PaqueteTuristicoDTO;
import hackacode.model.entity.PaqueteTuristico;

public interface IPaqueteTuristicoService {
	PaqueteTuristico save(PaqueteTuristicoDTO paqueteTuristico);
	PaqueteTuristico findById(Long id);
	List<PaqueteTuristico> listAll();
	void delete(PaqueteTuristico paqueteTuristico);
	boolean existsById(Long id);
}
