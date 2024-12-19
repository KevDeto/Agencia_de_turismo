package model.entity;

import java.sql.Date;

import jakarta.persistence.Entity;

@Entity
public class Cliente extends Persona{
	private static final long serialVersionUID = 1L;

	public Cliente(String nombre, String apellido, String direccion, String dni, Date fecha_nac, String nacionalidad,
			String celular, String email) {
		super(nombre, apellido, direccion, dni, fecha_nac, nacionalidad, celular, email);
	}

	public Cliente() {
	}
}
