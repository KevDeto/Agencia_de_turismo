package hackacode.model.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	private LocalDate fecha_venta;
	private double monto_total;
	
	@ManyToOne
	@JoinColumn(name = "cliente_uuid")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "empleado_uuid")
	private Empleado empleado;
	
	@ManyToOne
	@JoinColumn(name = "servicio_uuid")
	private ServicioTuristico servicio;
	
	@ManyToOne
	@JoinColumn(name = "paquete_uuid")
	private PaqueteTuristico paquete;
	
	
	//garantiza la validez de la venta ya que solo puede haber
	//un servicio o un paquete, pero no ambos
//	public boolean esVentaValida() {
//		return (this.getServicio() != null && this.getPaquete() == null) ||
//				(this.getServicio() == null && this.getPaquete() != null);
//	}
}
