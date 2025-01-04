package hackacode.model.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class ServicioTuristicoDTO {
    private Long codigoServicio;
    private String nombre;
    private String descripcionBreve;
    private String destinoServicio;
    private LocalDate fechaServicio;
    private Double costoServicio;
}
