package hackacode.model.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long UUID;

	private double costo_paquete;

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinTable(
			name = "paquete_servicio",
			joinColumns = @JoinColumn(name = "paquete_uuid"),
			inverseJoinColumns = @JoinColumn(name = "servicio_uuid")
	)
	private Set<ServicioTuristico> servicios;
}
