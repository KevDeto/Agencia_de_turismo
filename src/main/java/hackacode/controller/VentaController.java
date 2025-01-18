package hackacode.controller;

import java.util.List;

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

import hackacode.model.dto.VentaDTO;
import hackacode.model.entity.Empleado;
import hackacode.model.entity.Venta;
import hackacode.model.payload.MensajeResponse;
import hackacode.service.IVentaService;

@RestController
@RequestMapping("/api/v1")
public class VentaController {

	@Autowired
	private IVentaService ventaService;
	
	@GetMapping("ventas")
	public ResponseEntity<MensajeResponse> showAll(){
		List<VentaDTO> listaVentas = ventaService.listarVentas();
		if(listaVentas.isEmpty()) {
			return ResponseEntity.ok(MensajeResponse.builder()
					.mensaje("No se han encontrado registros.")
					.objeto(listaVentas)
					.build());
		}
		return ResponseEntity.ok(MensajeResponse.builder()
				.mensaje("Ventas recuperadas correctamente")
				.objeto(listaVentas)
				.build());
	}
	
	@PostMapping("venta")
	public ResponseEntity<MensajeResponse> create(@RequestBody VentaDTO ventaDTO){
		try {
			VentaDTO ventaSave = ventaService.crearVenta(ventaDTO);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(MensajeResponse.builder()
							.mensaje("Venta creada correctamente.")
							.objeto(ventaSave)
							.build());
		} catch (DataAccessException e) {
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
					.body(MensajeResponse.builder()
							.mensaje("Error al crear venta: " + e.getMessage())
							.objeto(null)
							.build());
		}
	}
	
	@PutMapping("venta/{id}")
	public ResponseEntity<MensajeResponse> update(@RequestBody VentaDTO ventaDTO, @PathVariable Long id) {
    	VentaDTO venta = ventaService.obtenerVentaPorId(id);
		if(venta == null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body(MensajeResponse.builder()
    						.mensaje("Venta con ID " + id + " no encontrado.")
    						.objeto(null)
    						.build());
    	}
    	try {
			ventaDTO.setUUID(id);
			VentaDTO ventaUpdate = ventaService.crearVenta(ventaDTO);
			return ResponseEntity.ok(MensajeResponse.builder()
							.mensaje("Venta actualizada correctamente.")
							.objeto(ventaUpdate)
							.build());
		} catch (DataAccessException e) {
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
					.body(MensajeResponse.builder()
							.mensaje("Error al modificar venta: " + e.getMessage())
							.objeto(null)
							.build());
		}
	}
	
	@DeleteMapping("venta/{id}")
	public ResponseEntity<MensajeResponse> delete(@PathVariable Long id) {
		VentaDTO venta = ventaService.obtenerVentaPorId(id);
    	if(venta == null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body(MensajeResponse.builder()
    						.mensaje("Venta con ID " + id +" no encontrado.")
    						.objeto(null)
    						.build());
    	}
    	try {
			ventaService.eliminarVenta(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (DataAccessException e) {
        	return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
        			.body(MensajeResponse.builder()
        					.mensaje("Error al eliminar venta: " + e.getMessage())
        					.objeto(null)
        					.build());
        }
	}
	
	@GetMapping("venta/{id}")
	public ResponseEntity<?> showById(@PathVariable Long id) {
        VentaDTO venta = ventaService.obtenerVentaPorId(id);
    	if(venta == null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body(MensajeResponse.builder()
    						.mensaje("Venta con ID " + id + " no encontrado.")
    						.objeto(null)
    						.build());
    	}
        return ResponseEntity.ok(MensajeResponse.builder()
        		.mensaje("Venta recuperada correctamente.")
        		.objeto(venta)
        		.build());		
	}
}
