package hackacode.model.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@ManyToMany
	@JsonIgnore
	@JoinTable(
			name = "paquete_servicio",
			joinColumns = @JoinColumn(name = "paquete_uuid", referencedColumnName = "UUID"),
			inverseJoinColumns = @JoinColumn(name = "servicio_uuid", referencedColumnName = "UUID")
	)
	private Set<ServicioTuristico> servicios;
}
