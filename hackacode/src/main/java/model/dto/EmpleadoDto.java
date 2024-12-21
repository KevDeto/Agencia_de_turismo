package model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class EmpleadoDto extends PersonaDto {
	private static final long serialVersionUID = 1L;
	
	private String cargo;
	private double sueldo;
}
