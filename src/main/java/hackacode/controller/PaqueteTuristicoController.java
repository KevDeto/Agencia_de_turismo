package hackacode.controller;

import java.util.HashSet;
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
import hackacode.model.dto.PaqueteTuristicoDTO;
import hackacode.model.entity.Cliente;
import hackacode.model.entity.PaqueteTuristico;
import hackacode.model.entity.ServicioTuristico;
import hackacode.model.payload.MensajeResponse;
import hackacode.service.PaqueteTuristicoService;

@RestController
@RequestMapping("/api/paquetes")
public class PaqueteTuristicoController {

    @Autowired
    private PaqueteTuristicoService paqueteService;

    @PostMapping
    public ResponseEntity<PaqueteTuristicoDTO> crearPaquete(@RequestBody PaqueteTuristicoDTO paqueteDTO) {
        PaqueteTuristicoDTO nuevoPaquete = paqueteService.crearPaquete(paqueteDTO);
        return ResponseEntity.ok(nuevoPaquete);
    }

    @PutMapping("/{codigoPaquete}")
    public ResponseEntity<PaqueteTuristicoDTO> actualizarPaquete(
            @PathVariable Long codigoPaquete,
            @RequestBody PaqueteTuristicoDTO paqueteDTO) {
        PaqueteTuristicoDTO paqueteActualizado = paqueteService.actualizarPaquete(codigoPaquete, paqueteDTO);
        return ResponseEntity.ok(paqueteActualizado);
    }

    @DeleteMapping("/{codigoPaquete}")
    public ResponseEntity<Void> eliminarPaquete(@PathVariable Long codigoPaquete) {
        paqueteService.eliminarPaquete(codigoPaquete);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{codigoPaquete}")
    public ResponseEntity<PaqueteTuristicoDTO> obtenerPaquetePorId(@PathVariable Long codigoPaquete) {
        PaqueteTuristicoDTO paquete = paqueteService.obtenerPaquetePorId(codigoPaquete);
        return ResponseEntity.ok(paquete);
    }

    @GetMapping
    public ResponseEntity<List<PaqueteTuristicoDTO>> listarPaquetes() {
        List<PaqueteTuristicoDTO> paquetes = paqueteService.listarPaquetes();
        return ResponseEntity.ok(paquetes);
    }
}