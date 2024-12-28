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

import hackacode.model.dto.PaqueteTuristicoDto;
import hackacode.model.entity.PaqueteTuristico;
import hackacode.model.payload.MensajeResponse;
import hackacode.service.IPaqueteTuristicoService;



@RestController
@RequestMapping("/api/v1")
public class PaqueteTuristicoController {

	@Autowired
	private IPaqueteTuristicoService paqueteTuristicoService;
	
	@GetMapping("paquetes")
	public ResponseEntity<?> showAll(){
		List<PaqueteTuristico> getList = paqueteTuristicoService.listAll();
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
	
	@PostMapping("paquete")
	public ResponseEntity<?> create(@RequestBody PaqueteTuristicoDto paqueteTuristicoDto) {
	    try {
	        PaqueteTuristico paqueteSave = paqueteTuristicoService.save(paqueteTuristicoDto);
	        Set<String> serviciosRelacionados = paqueteSave.getServicios().stream()
	                .map(servicio -> servicio.getUUID().toString())
	                .collect(Collectors.toSet());

	        return new ResponseEntity<>(MensajeResponse.builder()
	                .mensaje("Guardado correctamente")
	                .objeto(PaqueteTuristicoDto.builder()
	                        .UUID(paqueteSave.getUUID())
	                        .costo_paquete(paqueteSave.getCosto_paquete())
	                        .servicio_turistico(serviciosRelacionados)
	                        .build())
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
//	@PostMapping("paquete")
//	public ResponseEntity<?> create(@RequestBody PaqueteTuristicoDto paqueteTuristicoDto){
//		PaqueteTuristico paqueteSave = null;
//        try {
//        	paqueteSave = paqueteTuristicoService.save(paqueteTuristicoDto);
//            return new ResponseEntity<>(MensajeResponse.builder()
//                    .mensaje("Guardado correctamente")
//                    .objeto(paqueteTuristicoDto)
//                    .build(),
//                    HttpStatus.CREATED);
//		} catch (DataAccessException exDt) {
//            return new ResponseEntity<>(MensajeResponse.builder()
//                    .mensaje(exDt.getMessage())
//                    .objeto(null)
//                    .build(),
//                    HttpStatus.METHOD_NOT_ALLOWED);
//		}
//	}
	
	@PutMapping("paquete/{id}")
	public ResponseEntity<?> update(@RequestBody PaqueteTuristicoDto ventaDto, @PathVariable Long id) {
		PaqueteTuristico paqueteUpdate = null;
		try {
			PaqueteTuristico findVenta = paqueteTuristicoService.findById(id);
			if(paqueteTuristicoService.existsById(id)) {
                ventaDto.setUUID(id);
                paqueteUpdate = paqueteTuristicoService.save(ventaDto);
                
        	    Set<String> servicios = paqueteUpdate.getServicios().stream()
        	            .map(servicio -> servicio.getUUID().toString())
        	            .collect(Collectors.toSet());
                
                return new ResponseEntity<>(MensajeResponse
                        .builder()
                        .mensaje("Guardado correctamente")
                        .objeto(PaqueteTuristicoDto.builder()
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
			PaqueteTuristico paqueteDelete = paqueteTuristicoService.findById(id);
			paqueteTuristicoService.delete(paqueteDelete);
			return new ResponseEntity<>(paqueteDelete, HttpStatus.NO_CONTENT);
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
		PaqueteTuristico paquete = paqueteTuristicoService.findById(id);
		if(paquete == null) {
			return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intenta buscar no existe")
                    .objeto(null)
                    .build(),
                    HttpStatus.NOT_FOUND);
		}
		
	    Set<String> servicios = paquete.getServicios().stream()
	            .map(servicio -> servicio.getUUID().toString())
	            .collect(Collectors.toSet());
		
		return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("")
                .objeto(PaqueteTuristicoDto.builder()
                		.UUID(paquete.getUUID())
                		.costo_paquete(paquete.getCosto_paquete())
                		.servicio_turistico(servicios)
                		.build())
                .build()
                ,HttpStatus.OK);		
	}
}
