package model.dto;

import java.sql.Date;

public class EmpleadoDTO extends PersonaDTO{
	private String cargo;
	private double sueldo;

	public EmpleadoDTO(Long UUID, String nombre, String apellido, String direccion, String dni, Date fecha_nac,
			String nacionalidad, String celular, String email, String cargo, double sueldo) {
		super(UUID, nombre, apellido, direccion, dni, fecha_nac, nacionalidad, celular, email);
		this.cargo = cargo;
		this.sueldo = sueldo;
	}
}
