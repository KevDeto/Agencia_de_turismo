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

import hackacode.model.dto.ServicioTuristicoDto;
import hackacode.model.entity.PaqueteTuristico;
import hackacode.model.entity.ServicioTuristico;
import hackacode.model.payload.MensajeResponse;
import hackacode.service.IServicioTuristicoService;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1")
public class ServicioTuristicoController {

	@Autowired
	private IServicioTuristicoService servicioTuristicoService;
	
	@GetMapping("servicios")
	public ResponseEntity<?> showAll(){
		List<ServicioTuristico> getList = servicioTuristicoService.listAll();
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

	@PostMapping("servicio")
	public ResponseEntity<?> create(@RequestBody ServicioTuristicoDto servicioTuristicoDto){
		ServicioTuristico servicioSave = null;
        try {
        	servicioSave = servicioTuristicoService.save(servicioTuristicoDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .objeto(servicioTuristicoDto)
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
	
	@PutMapping("servicio/{id}")
	public ResponseEntity<?> update(@RequestBody ServicioTuristicoDto servicioTuristicoDto, @PathVariable Long id) {
		ServicioTuristico servicioUpdate = null;
		try {
			ServicioTuristico findVenta = servicioTuristicoService.findById(id);
			if(servicioTuristicoService.existsById(id)) {
				servicioTuristicoDto.setUUID(id);
				servicioUpdate = servicioTuristicoService.save(servicioTuristicoDto);
				
        	    Set<String> servicios = servicioUpdate.getPaquetes().stream()
        	            .map(paquete -> paquete.getUUID().toString())
        	            .collect(Collectors.toSet());
				
                return new ResponseEntity<>(MensajeResponse
                        .builder()
                        .mensaje("Guardado correctamente")
                        .objeto(ServicioTuristicoDto.builder()
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
			ServicioTuristico servicioDelete = servicioTuristicoService.findById(id);
			servicioTuristicoService.delete(servicioDelete);
			return new ResponseEntity<>(servicioDelete, HttpStatus.NO_CONTENT);
		} catch (DataAccessException exDt) {
			return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .objeto(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
		}
	}
	
	@GetMapping("servicio/{id}")
	public ResponseEntity<?> showById(@PathVariable Long id) {
		ServicioTuristico servicio = servicioTuristicoService.findById(id);
		if(servicio == null) {
			return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intenta buscar no existe")
                    .objeto(null)
                    .build(),
                    HttpStatus.NOT_FOUND);
		}
		
	    Set<String> servicios = servicio.getPaquetes().stream()
	            .map(paquete -> paquete.getUUID().toString())
	            .collect(Collectors.toSet());
		
		return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("")
                .objeto(ServicioTuristicoDto.builder()
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
}
