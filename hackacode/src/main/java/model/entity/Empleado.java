package model.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleado")
public class Empleado extends Persona{
	private static final long serialVersionUID = 1L;
	
	private String cargo;
	private double sueldo;

	public Empleado( String nombre, String apellido, String direccion, String dni, Date fecha_nac,
			String nacionalidad, String celular, String email, String cargo, double sueldo) {
		super( nombre, apellido, direccion, dni, fecha_nac, nacionalidad, celular, email);
		this.cargo = cargo;
		this.sueldo = sueldo;
	}
	
	public Empleado() {
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	
	
}
