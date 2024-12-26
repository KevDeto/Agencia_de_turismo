package hackacode.model.entity;

import java.util.Date;

import hackacode.model.dto.ClienteDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UUID;
	
	private Date fecha_venta;
	private double monto_total;
	private ClienteDto clienteDto;
}
