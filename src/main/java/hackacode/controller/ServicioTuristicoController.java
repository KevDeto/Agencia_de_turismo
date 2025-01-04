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

import hackacode.model.dto.ClienteDto;
<<<<<<< HEAD
import hackacode.model.dto.ServicioTuristicoDT0;
=======
import hackacode.model.dto.ServicioTuristicoDTO;
>>>>>>> pruebas2
import hackacode.model.entity.Cliente;
import hackacode.model.entity.PaqueteTuristico;
import hackacode.model.entity.ServicioTuristico;
import hackacode.model.payload.MensajeResponse;
import hackacode.service.ServicioTuristicoService;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/servicios")
public class ServicioTuristicoController {
<<<<<<< HEAD
	
	@Autowired
	private IServicioTuristicoService servicioService;
	
    @PostMapping("servicio")
    public ResponseEntity<?> create(@RequestBody ServicioTuristicoDT0 servicioTuristicoDT0) {
        ServicioTuristico servicioSave = null;
        try {
        	servicioSave = servicioService.save(servicioTuristicoDT0);
            return new ResponseEntity<>(MensajeResponse
                    .builder()
                    .mensaje("Guardado correctamente")
                    .objeto(servicioTuristicoDT0)
                    .build(),
                    HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .objeto(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    
    @GetMapping("servicios")
	public ResponseEntity<?> showAll(){
		List<ServicioTuristico> getList = servicioService.listAll();
		if(getList == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No hay registros")
                    .objeto(null)
                    .build(),
                    HttpStatus.OK);
		}
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("")
                .objeto(getList)
                .build(),
                HttpStatus.OK);
	}
    
    @GetMapping("servicio/{id}")
	public ResponseEntity<?> showById(@PathVariable Long id) {
		ServicioTuristico servicio = servicioService.findById(id);
		if(servicio == null) {
			return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intenta buscar no existe")
                    .objeto(null)
                    .build(),
                    HttpStatus.NOT_FOUND);
		}
		
	    Set<Long> servicios = servicio.getPaquetes().stream()
	            .map(paquete -> paquete.getUUID())
	            .collect(Collectors.toSet());
		
		return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("")
                .objeto(ServicioTuristicoDT0.builder()
                		.UUID(servicio.getUUID())
                		.nombre(servicio.getNombre())
                		.descripcion(servicio.getDescripcion())
                		.destino_servicio(servicio.getDestino_servicio())
                		.costo_servicio(servicio.getCosto_servicio())
                		.paquete_turistico(servicios)
                		.build())
                .build()
                ,HttpStatus.OK);		
	}
    
    @PutMapping("servicio/{id}")
	public ResponseEntity<?> update(@RequestBody ServicioTuristicoDT0 servicioTuristicoDT0, @PathVariable Long id) {
		ServicioTuristico servicioUpdate = null;
		try {
			ServicioTuristico findVenta = servicioService.findById(id);
			if(servicioService.existsById(id)) {
				servicioTuristicoDT0.setUUID(id);
				servicioUpdate = servicioService.save(servicioTuristicoDT0);
				
        	    Set<Long> servicios = servicioUpdate.getPaquetes().stream()
        	            .map(paquete -> paquete.getUUID())
        	            .collect(Collectors.toSet());
				
                return new ResponseEntity<>(MensajeResponse
                        .builder()
                        .mensaje("Guardado correctamente")
                        .objeto(ServicioTuristicoDT0.builder()
                        		.UUID(servicioUpdate.getUUID())
                        		.nombre(servicioUpdate.getNombre())
                        		.descripcion(servicioUpdate.getDescripcion())
                        		.destino_servicio(servicioUpdate.getDestino_servicio())
                        		.costo_servicio(servicioUpdate.getCosto_servicio())
                        		.paquete_turistico(servicios)
                        		.build())
                        .build(),
                        HttpStatus.CREATED);
			}else {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intenta actualizar no se encuentra en la base de datos")
                        .objeto(null)
                        .build(),
                        HttpStatus.NOT_FOUND);
            }
		} catch (DataAccessException exDt) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .objeto(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
		}
	}
    
	@DeleteMapping("servicio/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			ServicioTuristico servicioDelete = servicioService.findById(id);
			servicioService.delete(servicioDelete);
			return new ResponseEntity<>(servicioDelete, HttpStatus.NO_CONTENT);
		} catch (DataAccessException exDt) {
			return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .objeto(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
		}
	}
}
=======

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
>>>>>>> pruebas2
