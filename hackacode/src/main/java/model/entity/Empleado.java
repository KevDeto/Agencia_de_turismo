package model.entity;

import java.sql.Date;

import jakarta.persistence.Entity;

@Entity
public class Empleado extends Persona {
	private static final long serialVersionUID = 1L;
	
	private String cargo;
	private double sueldo;

	public Empleado(Long UUID, String nombre, String apellido, String direccion, String dni, Date fecha_nac,
			String nacionalidad, String celular, String email, String cargo, double sueldo) {
		super(UUID, nombre, apellido, direccion, dni, fecha_nac, nacionalidad, celular, email);
		this.cargo = cargo;
		this.sueldo = sueldo;
	}

}
