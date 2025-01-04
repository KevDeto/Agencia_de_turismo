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
import hackacode.model.dto.PaqueteTuristicoDTO;
import hackacode.model.entity.Cliente;
import hackacode.model.entity.PaqueteTuristico;
import hackacode.model.entity.ServicioTuristico;
import hackacode.model.payload.MensajeResponse;
import hackacode.service.IPaqueteTuristicoService;



@RestController
@RequestMapping("/api/v1")
public class PaqueteTuristicoController {

	@Autowired
	private IPaqueteTuristicoService paqueteService;
	
    @PostMapping("paquete")
    public ResponseEntity<?> create(@RequestBody PaqueteTuristicoDTO paqueteTuristicoDTO) {
        PaqueteTuristico paqueteSave = null;
        try {
        	paqueteSave = paqueteService.save(paqueteTuristicoDTO);
            return new ResponseEntity<>(MensajeResponse
                    .builder()
                    .mensaje("Guardado correctamente")
                    .objeto(paqueteTuristicoDTO)
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
    
    @GetMapping("paquete/{id}")
	public ResponseEntity<?> showById(@PathVariable Long id) {
		PaqueteTuristico paquete = paqueteService.findById(id);
		if(paquete == null) {
			return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intenta buscar no existe")
                    .objeto(null)
                    .build(),
                    HttpStatus.NOT_FOUND);
		}
		
	    Set<Long> servicios = paquete.getServicios().stream()
	            .map(servicio -> servicio.getUUID())
	            .collect(Collectors.toSet());
		
		return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("")
                .objeto(PaqueteTuristicoDTO.builder()
                		.UUID(paquete.getUUID())
                		.costo_paquete(paquete.getCosto_paquete())
                		.servicio_turistico(servicios)
                		.build())
                .build()
                ,HttpStatus.OK);		
	}
    
    @GetMapping("paquetes")
	public ResponseEntity<?> showAll(){
		List<PaqueteTuristico> getList = paqueteService.listAll();
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
    
    @PutMapping("paquete/{id}")
	public ResponseEntity<?> update(@RequestBody PaqueteTuristicoDTO paqueteTuristicoDTO, @PathVariable Long id) {
		PaqueteTuristico paqueteUpdate = null;
		try {
			PaqueteTuristico findVenta = paqueteService.findById(id);
			if(paqueteService.existsById(id)) {
				paqueteTuristicoDTO.setUUID(id);
                paqueteUpdate = paqueteService.save(paqueteTuristicoDTO);
                
        	    Set<Long> servicios = paqueteUpdate.getServicios().stream()
        	            .map(servicio -> servicio.getUUID())
        	            .collect(Collectors.toSet());
                
                return new ResponseEntity<>(MensajeResponse
                        .builder()
                        .mensaje("Guardado correctamente")
                        .objeto(PaqueteTuristicoDTO.builder()
                        		.UUID(paqueteUpdate.getUUID())
                        		.costo_paquete(paqueteUpdate.getCosto_paquete())
                        		.servicio_turistico(servicios)
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
    
	@DeleteMapping("paquete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			PaqueteTuristico paqueteDelete = paqueteService.findById(id);
			paqueteService.delete(paqueteDelete);
			return new ResponseEntity<>(paqueteDelete, HttpStatus.NO_CONTENT);
		} catch (DataAccessException exDt) {
			return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .objeto(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
		}
	}
}
