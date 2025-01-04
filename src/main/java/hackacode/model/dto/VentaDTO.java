package hackacode.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VentaDTO {
	
	@JsonIgnore
	private Long UUID;	
	private Date fecha_venta;
	private double monto_total;
	private Long cliente_uuid;
}
