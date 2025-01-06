package hackacode.model.dto;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hackacode.model.entity.PaqueteTuristico;
import hackacode.model.entity.ServicioTuristico;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VentaDTO {
	
	@JsonIgnore
	private Long UUID;	
	private LocalDate fecha_venta;
	private double monto_total;
	private Long cliente_uuid;
	private Long empleado_uuid;
	private Long servicio_uuid;
	private Long paquete_uuid;
}
