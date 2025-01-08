package hackacode.model.entity;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Empleado extends Persona{

	private String cargo;
	private double sueldo;
	
	@OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
	private List<Venta> ventas;
}
