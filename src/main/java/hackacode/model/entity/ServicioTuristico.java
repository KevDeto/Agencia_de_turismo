package hackacode.model.entity;

import java.util.Date;
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
public class ServicioTuristico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UUID;

	private String nombre;
	private String descripcion;
	private String destino_servicio;
	private Date fecha_servicio;
	private double costo_servicio;

	@ManyToMany(mappedBy = "servicios", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<PaqueteTuristico> paquetes;

//	@OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<PaqueteServicio> paqueteServicios;
}
