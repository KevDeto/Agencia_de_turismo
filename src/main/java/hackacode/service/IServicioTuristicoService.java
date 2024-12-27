package hackacode.service;

import java.util.List;

import hackacode.model.dto.ServicioTuristicoDto;
import hackacode.model.entity.ServicioTuristico;

public interface IServicioTuristicoService {
	List<ServicioTuristico> listAll();
	ServicioTuristico save(ServicioTuristicoDto servicioTuristico);
	ServicioTuristico findById(Long id);
	void delete(ServicioTuristico servicioTuristico);
	boolean existsById(Long id);
}
