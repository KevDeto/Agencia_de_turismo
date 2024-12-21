package model.payload;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MensajeResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private String mensaje;
	private Object objeto;
}
