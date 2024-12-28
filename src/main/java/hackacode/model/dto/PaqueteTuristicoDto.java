package hackacode.model.dto;

import java.util.Set;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class PaqueteTuristicoDto {
	private Long UUID;
	private double costo_paquete;
	private Set<String> servicio_turistico;
}
