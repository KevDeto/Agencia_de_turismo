package model.entity;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@MappedSuperclass
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UUID;

	private String nombre;
	private String apellido;
	private String direccion;
	private String dni;
	private Date fecha_nac;
	private String nacionalidad;
	private String celular;
	private String email;
}
