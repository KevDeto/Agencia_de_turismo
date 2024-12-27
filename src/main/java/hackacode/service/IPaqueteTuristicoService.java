package hackacode.service;

import java.util.List;

import hackacode.model.dto.PaqueteTuristicoDto;
import hackacode.model.entity.PaqueteTuristico;

public interface IPaqueteTuristicoService {
	List<PaqueteTuristico> listAll();
	PaqueteTuristico save(PaqueteTuristicoDto paqueteTuristico);
	PaqueteTuristico findById(Long id);
	void delete(PaqueteTuristico paqueteTuristico);
	boolean existsById(Long id);
}
