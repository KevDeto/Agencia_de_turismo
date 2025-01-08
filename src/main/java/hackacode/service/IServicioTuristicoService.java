package hackacode.service;

import java.util.List;

import hackacode.model.dto.ServicioTuristicoDTO;

public interface IServicioTuristicoService {

	ServicioTuristicoDTO crearServicio(ServicioTuristicoDTO servicioDTO);

	ServicioTuristicoDTO actualizarServicio(Long codigoServicio, ServicioTuristicoDTO servicioDTO);

	void eliminarServicio(Long codigoServicio);

	ServicioTuristicoDTO obtenerServicioPorId(Long codigoServicio);

	List<ServicioTuristicoDTO> listarServicios();
}
