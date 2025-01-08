package hackacode.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;



@NoArgsConstructor
@SuperBuilder
@Entity
public class Cliente extends Persona {
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Venta> ventas;
}
