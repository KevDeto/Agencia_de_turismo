package hackacode.model.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class PaqueteTuristico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UUID;
	
	private double costo_paquete;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(
			name = "paquete_servicio",
			joinColumns = @JoinColumn(name = "paquete_uuid"),
			inverseJoinColumns = @JoinColumn(name  = "servicio_uuid")
			)
	private Set<ServicioTuristico> servicios;	
//	@OneToMany(mappedBy = "paquete", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<PaqueteServicio> paqueteServicios;
}
