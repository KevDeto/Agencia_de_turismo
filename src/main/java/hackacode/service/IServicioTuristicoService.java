package hackacode.service;

import java.util.List;

import hackacode.model.dto.ServicioTuristicoDto;
import hackacode.model.entity.ServicioTuristico;

public interface IServicioTuristicoService {
	ServicioTuristico save(ServicioTuristicoDto servicioTuristico);
	ServicioTuristico findById(Long id);
	List<ServicioTuristico> listAll();
	void delete(ServicioTuristico servicioTuristico);
	boolean existsById(Long id);
}
