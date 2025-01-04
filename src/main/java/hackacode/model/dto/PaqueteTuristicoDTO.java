package hackacode.model.dto;

import java.util.Set;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class PaqueteTuristicoDTO {
	private Long UUID;
	private double costo_paquete;
	private Set<Long> servicio_turistico;
}
