package hackacode.service;

import java.util.List;

import hackacode.model.dto.ServicioTuristicoDT0;
import hackacode.model.entity.ServicioTuristico;

public interface IServicioTuristicoService {
	ServicioTuristico save(ServicioTuristicoDT0 servicioTuristico);
	ServicioTuristico findById(Long id);
	List<ServicioTuristico> listAll();
	void delete(ServicioTuristico servicioTuristico);
	boolean existsById(Long id);
}
