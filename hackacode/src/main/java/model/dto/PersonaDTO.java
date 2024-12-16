package model.dto;

import java.sql.Date;

public class PersonaDTO {
	private Long UUID;
	private String nombre;
	private String apellido;
	private String direccion;
	private String dni;
	private Date fecha_nac;
	private String nacionalidad;
	private String celular;
	private String email;
	
	public PersonaDTO(Long UUID, String nombre, String apellido, String direccion, String dni, Date fecha_nac,
			String nacionalidad, String celular, String email) {
		this.UUID = UUID;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.dni = dni;
		this.fecha_nac = fecha_nac;
		this.nacionalidad = nacionalidad;
		this.celular = celular;
		this.email = email;
	}
}
