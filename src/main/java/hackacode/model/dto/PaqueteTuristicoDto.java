package hackacode.model.dto;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaqueteTuristicoDto {
	private Long UUID;
	private double costo_paquete;
	private Set<String> servicio_turistico;
}
