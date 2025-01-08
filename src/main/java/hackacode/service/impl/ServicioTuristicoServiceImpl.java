package hackacode.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hackacode.model.dto.ServicioTuristicoDTO;
import hackacode.model.entity.ServicioTuristico;
<<<<<<< HEAD
import hackacode.model.repository.ServicioTuristicoRepository;

=======
import hackacode.model.repository.IServicioTuristicoRepository;
>>>>>>> pruebas2
import hackacode.service.IServicioTuristicoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicioTuristicoServiceImpl implements IServicioTuristicoService {

    private final IServicioTuristicoRepository servicioRepository;

    @Override
    public ServicioTuristicoDTO crearServicio(ServicioTuristicoDTO servicioDTO) {
        ServicioTuristico servicio = convertirADominio(servicioDTO);
        ServicioTuristico nuevoServicio = servicioRepository.save(servicio);
        return convertirADTO(nuevoServicio);
    }

    @Override
    public ServicioTuristicoDTO actualizarServicio(Long codigoServicio, ServicioTuristicoDTO servicioDTO) {
        Optional<ServicioTuristico> servicioExistente = servicioRepository.findById(codigoServicio);

        if (servicioExistente.isEmpty()) {
            throw new RuntimeException("Servicio con código " + codigoServicio + " no encontrado.");
        }

        ServicioTuristico servicio = servicioExistente.get();
        servicio.setNombre(servicioDTO.getNombre());
        servicio.setDescripcionBreve(servicioDTO.getDescripcionBreve());
        servicio.setDestinoServicio(servicioDTO.getDestinoServicio());
        servicio.setFechaServicio(servicioDTO.getFechaServicio());
        servicio.setCostoServicio(servicioDTO.getCostoServicio());

        ServicioTuristico servicioActualizado = servicioRepository.save(servicio);
        return convertirADTO(servicioActualizado);
    }

    @Override
    public void eliminarServicio(Long codigoServicio) {
        if (!servicioRepository.existsById(codigoServicio)) {
            throw new RuntimeException("Servicio con código " + codigoServicio + " no encontrado.");
        }
        servicioRepository.deleteById(codigoServicio);
    }

    @Override
    public ServicioTuristicoDTO obtenerServicioPorId(Long codigoServicio) {
        ServicioTuristico servicio = servicioRepository.findById(codigoServicio)
                .orElseThrow(() -> new RuntimeException("Servicio con código " + codigoServicio + " no encontrado."));
        return convertirADTO(servicio);
    }

    @Override
    public List<ServicioTuristicoDTO> listarServicios() {
        return servicioRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Métodos para conversión entre entidad y DTO
    private ServicioTuristico convertirADominio(ServicioTuristicoDTO servicioDTO) {
        return ServicioTuristico.builder()
                .nombre(servicioDTO.getNombre())
                .descripcionBreve(servicioDTO.getDescripcionBreve())
                .destinoServicio(servicioDTO.getDestinoServicio())
                .fechaServicio(servicioDTO.getFechaServicio())
                .costoServicio(servicioDTO.getCostoServicio())
                .build();
    }

    private ServicioTuristicoDTO convertirADTO(ServicioTuristico servicio) {
        return ServicioTuristicoDTO.builder()
                .codigoServicio(servicio.getCodigoServicio())
                .nombre(servicio.getNombre())
                .descripcionBreve(servicio.getDescripcionBreve())
                .destinoServicio(servicio.getDestinoServicio())
                .fechaServicio(servicio.getFechaServicio())
                .costoServicio(servicio.getCostoServicio())
                .build();
    }
}