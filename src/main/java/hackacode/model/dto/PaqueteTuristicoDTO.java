package hackacode.model.dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class PaqueteTuristicoDTO {
    private Long codigoPaquete;
    private List<ServicioTuristicoDTO> listaServiciosIncluidos;
    private Double costoPaquete;
}
