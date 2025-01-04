package hackacode.service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hackacode.model.dto.PaqueteTuristicoDTO;
import hackacode.model.entity.PaqueteTuristico;

public interface PaqueteTuristicoService {
    PaqueteTuristicoDTO crearPaquete(PaqueteTuristicoDTO paqueteDTO);
    PaqueteTuristicoDTO actualizarPaquete(Long codigoPaquete, PaqueteTuristicoDTO paqueteDTO);
    void eliminarPaquete(Long codigoPaquete);
    PaqueteTuristicoDTO obtenerPaquetePorId(Long codigoPaquete);
    List<PaqueteTuristicoDTO> listarPaquetes();
}
