package hackacode.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hackacode.model.dto.PaqueteTuristicoDTO;
import hackacode.model.dto.ServicioTuristicoDTO;
import hackacode.model.entity.PaqueteTuristico;
import hackacode.model.entity.ServicioTuristico;
import hackacode.model.repository.PaqueteTuristicoRepository;
import hackacode.model.repository.ServicioTuristicoRepository;
import hackacode.service.IPaqueteTuristicoService;

@Service
public class PaqueteTuristicoServiceImpl implements IPaqueteTuristicoService {

    @Autowired
    private PaqueteTuristicoRepository paqueteRepository;

    @Autowired
    private ServicioTuristicoRepository servicioRepository;

    @Override
    public PaqueteTuristicoDTO crearPaquete(PaqueteTuristicoDTO paqueteDTO) {
        PaqueteTuristico paquete = convertirADominio(paqueteDTO);
        PaqueteTuristico nuevoPaquete = paqueteRepository.save(paquete);
        return convertirADTO(nuevoPaquete);
    }

    @Override
    public PaqueteTuristicoDTO actualizarPaquete(Long codigoPaquete, PaqueteTuristicoDTO paqueteDTO) {
        Optional<PaqueteTuristico> paqueteExistente = paqueteRepository.findById(codigoPaquete);

        if (paqueteExistente.isEmpty()) {
            throw new RuntimeException("Paquete con código " + codigoPaquete + " no encontrado.");
        }

        PaqueteTuristico paquete = paqueteExistente.get();
        paquete.setCostoPaquete(paqueteDTO.getCostoPaquete());

        List<ServicioTuristico> servicios = paqueteDTO.getListaServiciosIncluidos().stream()
                .map(servicioDTO -> servicioRepository.findById(servicioDTO.getCodigoServicio())
                        .orElseThrow(() -> new RuntimeException(
                                "Servicio con código " + servicioDTO.getCodigoServicio() + " no encontrado.")))
                .collect(Collectors.toList());

        paquete.setListaServiciosIncluidos(servicios);

        PaqueteTuristico paqueteActualizado = paqueteRepository.save(paquete);
        return convertirADTO(paqueteActualizado);
    }

    @Override
    public void eliminarPaquete(Long codigoPaquete) {
        if (!paqueteRepository.existsById(codigoPaquete)) {
            throw new RuntimeException("Paquete con código " + codigoPaquete + " no encontrado.");
        }
        paqueteRepository.deleteById(codigoPaquete);
    }

    @Override
    public PaqueteTuristicoDTO obtenerPaquetePorId(Long codigoPaquete) {
        PaqueteTuristico paquete = paqueteRepository.findById(codigoPaquete)
                .orElseThrow(() -> new RuntimeException("Paquete con código " + codigoPaquete + " no encontrado."));
        return convertirADTO(paquete);
    }

    @Override
    public List<PaqueteTuristicoDTO> listarPaquetes() {
        return paqueteRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Métodos para conversión entre entidad y DTO
    private PaqueteTuristico convertirADominio(PaqueteTuristicoDTO paqueteDTO) {
        List<ServicioTuristico> servicios = paqueteDTO.getListaServiciosIncluidos().stream()
                .map(servicioDTO -> servicioRepository.findById(servicioDTO.getCodigoServicio())
                        .orElseThrow(() -> new RuntimeException(
                                "Servicio con código " + servicioDTO.getCodigoServicio() + " no encontrado.")))
                .collect(Collectors.toList());

        return PaqueteTuristico.builder()
                .costoPaquete(paqueteDTO.getCostoPaquete())
                .listaServiciosIncluidos(servicios)
                .build();
    }

    private PaqueteTuristicoDTO convertirADTO(PaqueteTuristico paquete) {
        return PaqueteTuristicoDTO.builder()
                .codigoPaquete(paquete.getCodigoPaquete())
                .costoPaquete(paquete.getCostoPaquete())
                .listaServiciosIncluidos(paquete.getListaServiciosIncluidos().stream()
                        .map(servicio -> ServicioTuristicoDTO.builder()
                                .codigoServicio(servicio.getCodigoServicio())
                                .nombre(servicio.getNombre())
                                .descripcionBreve(servicio.getDescripcionBreve())
                                .destinoServicio(servicio.getDestinoServicio())
                                .fechaServicio(servicio.getFechaServicio())
                                .costoServicio(servicio.getCostoServicio())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}