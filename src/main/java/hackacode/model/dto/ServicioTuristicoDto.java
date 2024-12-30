package hackacode.model.dto;

import java.util.Date;
import java.util.Set;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ServicioTuristicoDto {
	private Long UUID;
	private String nombre;
	private String descripcion;
	private String destino_servicio;
	private Date fecha_servicio;
	private double costo_servicio;
//	private Set<String> paquete_turistico;
}
