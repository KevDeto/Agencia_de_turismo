package hackacode.model.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaqueteTuristicoDTO {
    private Long codigoPaquete;
    private List<ServicioTuristicoDTO> listaServiciosIncluidos;
    private Double costoPaquete;
}
