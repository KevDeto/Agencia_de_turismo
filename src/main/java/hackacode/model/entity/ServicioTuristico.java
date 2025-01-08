package hackacode.model.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "servicios_turisticos")
public class ServicioTuristico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoServicio;

	@Column(nullable = false, length = 100)
	private String nombre;

	@Column(nullable = false, length = 255)
	private String descripcionBreve;

	@Column(nullable = false, length = 100)
	private String destinoServicio;

	@Column(nullable = false)
	private LocalDate fechaServicio;

	@Column(nullable = false)
	private Double costoServicio;

	@ManyToMany(mappedBy = "listaServiciosIncluidos", cascade = CascadeType.ALL)
	private List<PaqueteTuristico> listaPaquetesIncluidos;
	
	@OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
	private List<Venta> ventas;
}
