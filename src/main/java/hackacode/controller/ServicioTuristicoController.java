package hackacode.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hackacode.model.dto.ClienteDTO;

import hackacode.model.dto.ServicioTuristicoDTO;
import hackacode.model.entity.Cliente;
import hackacode.model.entity.PaqueteTuristico;
import hackacode.model.entity.ServicioTuristico;
import hackacode.model.payload.MensajeResponse;
import hackacode.service.ServicioTuristicoService;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/servicios")
public class ServicioTuristicoController {

    @Autowired
    private ServicioTuristicoService servicioService;

    @PostMapping
    public ResponseEntity<ServicioTuristicoDTO> crearServicio(@RequestBody ServicioTuristicoDTO servicioDTO) {
        ServicioTuristicoDTO nuevoServicio = servicioService.crearServicio(servicioDTO);
        return ResponseEntity.ok(nuevoServicio);
    }

    @PutMapping("/{codigoServicio}")
    public ResponseEntity<ServicioTuristicoDTO> actualizarServicio(
            @PathVariable Long codigoServicio,
            @RequestBody ServicioTuristicoDTO servicioDTO) {
        ServicioTuristicoDTO servicioActualizado = servicioService.actualizarServicio(codigoServicio, servicioDTO);
        return ResponseEntity.ok(servicioActualizado);
    }

    @DeleteMapping("/{codigoServicio}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long codigoServicio) {
        servicioService.eliminarServicio(codigoServicio);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{codigoServicio}")
    public ResponseEntity<ServicioTuristicoDTO> obtenerServicioPorId(@PathVariable Long codigoServicio) {
        ServicioTuristicoDTO servicio = servicioService.obtenerServicioPorId(codigoServicio);
        return ResponseEntity.ok(servicio);
    }

    @GetMapping
    public ResponseEntity<List<ServicioTuristicoDTO>> listarServicios() {
        List<ServicioTuristicoDTO> servicios = servicioService.listarServicios();
        return ResponseEntity.ok(servicios);
    }
}
