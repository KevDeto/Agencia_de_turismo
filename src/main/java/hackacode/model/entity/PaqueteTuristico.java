package hackacode.model.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PaqueteTuristico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UUID;
	
	private double costo_paquete;
	
	@ManyToMany
	@JoinTable(
			name = "paquete_servicio",
			joinColumns = @JoinColumn(name = "paquete_uuid"),
			inverseJoinColumns = @JoinColumn(name  = "servicio_uuid")
			)
	private Set<ServicioTuristico> servicios;
	
	
}
