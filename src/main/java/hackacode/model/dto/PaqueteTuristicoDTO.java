package hackacode.model.dto;

<<<<<<< HEAD
import java.util.Set;

=======
import java.util.List;

>>>>>>> pruebas2
import lombok.Builder;
import lombok.Data;

@Data
<<<<<<< HEAD
@ToString
@Builder
public class PaqueteTuristicoDTO {
	private Long UUID;
	private double costo_paquete;
	private Set<Long> servicio_turistico;
=======
@Builder
public class PaqueteTuristicoDTO {
    private Long codigoPaquete;
    private List<ServicioTuristicoDTO> listaServiciosIncluidos;
    private Double costoPaquete;
>>>>>>> pruebas2
}
