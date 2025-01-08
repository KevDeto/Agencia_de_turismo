package hackacode.service;

import java.util.List;

import hackacode.model.dto.PaqueteTuristicoDTO;

public interface IPaqueteTuristicoService {

	PaqueteTuristicoDTO crearPaquete(PaqueteTuristicoDTO paqueteDTO);

	PaqueteTuristicoDTO actualizarPaquete(Long codigoPaquete, PaqueteTuristicoDTO paqueteDTO);

	void eliminarPaquete(Long codigoPaquete);

	PaqueteTuristicoDTO obtenerPaquetePorId(Long codigoPaquete);

	List<PaqueteTuristicoDTO> listarPaquetes();
}
