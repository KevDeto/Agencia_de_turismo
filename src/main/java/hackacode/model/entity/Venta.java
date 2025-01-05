package hackacode.model.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UUID;
	
	private Date fecha_venta;
	private double monto_total;
	
	@ManyToOne
	@JoinColumn(name = "cliente_uuid")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "empleado_uuid")
	private Empleado empleado;
}
