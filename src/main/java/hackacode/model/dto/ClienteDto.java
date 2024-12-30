package hackacode.model.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ClienteDto implements Serializable {

    private Long UUID;
	private String nombre;
	private String apellido;
	private String dni;
	private String celular;
	private String nacionalidad;
	private String email;
	private Date fecha_nac;
	private String direccion;

}
