package hackacode.model.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VentaDto {
	
	private Long UUID;	
	private Date fecha_venta;
	private double monto_total;
	private Long cliente_uuid;
}
