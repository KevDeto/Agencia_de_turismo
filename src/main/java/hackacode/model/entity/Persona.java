package hackacode.model.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
@MappedSuperclass
public class Persona implements Serializable{
	@Id
	@Column(name = "uuid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UUID;
	
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellido")
	private String apellido;
	@Column(name = "dni")
	private String dni;
	@Column(name = "celular")
	private String celular;
	@Column(name = "nacionalidad")
	private String nacionalidad;
	@Column(name = "email")
	private String email;
	@Column(name = "fecha_nac")
	private Date fecha_nac;
	@Column(name = "direccion")
	private String direccion;
}
