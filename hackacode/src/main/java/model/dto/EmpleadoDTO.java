package model.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmpleadoDTO extends PersonaDTO {
	private static final long serialVersionUID = 1L;
	
	private String cargo;
	private double sueldo;
}
