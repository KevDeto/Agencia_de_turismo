package hackacode.service;

import java.util.List;

import hackacode.model.dto.ServicioTuristicoDTO;
import hackacode.model.entity.ServicioTuristico;

public interface ServicioTuristicoService {
    ServicioTuristicoDTO crearServicio(ServicioTuristicoDTO servicioDTO);
    ServicioTuristicoDTO actualizarServicio(Long codigoServicio, ServicioTuristicoDTO servicioDTO);
    void eliminarServicio(Long codigoServicio);
    ServicioTuristicoDTO obtenerServicioPorId(Long codigoServicio);
    List<ServicioTuristicoDTO> listarServicios();
}
