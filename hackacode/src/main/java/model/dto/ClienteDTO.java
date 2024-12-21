package model.dto;

import java.util.Date;

public class ClienteDTO extends PersonaDTO {

	public ClienteDTO(String nombre, String apellido, String direccion, String dni, Date fecha_nac, String nacionalidad,
			String celular, String email) {
		super(nombre, apellido, direccion, dni, fecha_nac, nacionalidad, celular, email);
	}
	
	public ClienteDTO() {
	}

}
