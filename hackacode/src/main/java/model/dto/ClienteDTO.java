package model.dto;

import java.sql.Date;

public class ClienteDTO extends PersonaDTO{
	public ClienteDTO(Long UUID, String nombre, String apellido, String direccion, String dni, Date fecha_nac,
			String nacionalidad, String celular, String email) {
		super(UUID, nombre, apellido, direccion, dni, fecha_nac, nacionalidad, celular, email);
	}
}
