package hackacode.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class EmpleadoDto implements Serializable{
	
	@JsonIgnore
    private Long UUID;
	private String nombre;
	private String apellido;
	private String dni;
	private String celular;
	private String nacionalidad;
	private String email;
	private Date fecha_nac;
	private String direccion;
    private String cargo;
    private double sueldo;
}
