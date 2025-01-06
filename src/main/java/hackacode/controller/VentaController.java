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
import hackacode.model.entity.Venta;
import hackacode.model.payload.MensajeResponse;
import hackacode.service.IVentaService;

@RestController
@RequestMapping("/api/v1")
public class VentaController {

	@Autowired
	private IVentaService ventaService;
	
	@GetMapping("ventas")
	public ResponseEntity<?> showAll(){
		List<VentaDTO> getList = ventaService.listarVentas();
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
	
	@PostMapping("venta")
	public ResponseEntity<?> create(@RequestBody VentaDTO ventaDTO){
		VentaDTO ventaSave = null;
        try {
        	ventaSave = ventaService.registrarVenta(ventaDTO);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .objeto(ventaDTO)
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
	
	@PutMapping("venta/{id}")
	public ResponseEntity<?> update(@RequestBody VentaDTO ventaDTO, @PathVariable Long id) {
		VentaDTO ventaUpdate = null;
		try {
			VentaDTO findVenta = ventaService.buscarVentaPorId(id);
			if(findVenta != null) {
                ventaDTO.setUUID(id);
                ventaUpdate = ventaService.registrarVenta(ventaDTO);
                return new ResponseEntity<>(MensajeResponse
                        .builder()
                        .mensaje("Guardado correctamente")
                        .objeto(VentaDTO.builder()
                        		.UUID(ventaUpdate.getUUID())
                        		.fecha_venta(ventaUpdate.getFecha_venta())
                        		.monto_total(ventaUpdate.getMonto_total())
                        		.cliente_uuid(ventaUpdate.getCliente_uuid())
                        		.empleado_uuid(ventaUpdate.getEmpleado_uuid())
                        		.servicio_uuid(ventaUpdate.getServicio_uuid())
                        		.paquete_uuid(ventaUpdate.getPaquete_uuid())
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
	
	@DeleteMapping("venta/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			VentaDTO ventaDelete = ventaService.buscarVentaPorId(id);
			ventaService.eliminarVenta(ventaDelete.getUUID());
			return new ResponseEntity<>(ventaDelete, HttpStatus.NO_CONTENT);
		} catch (DataAccessException exDt) {
			return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .objeto(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
		}
	}
	
	@GetMapping("venta/{id}")
	public ResponseEntity<?> showById(@PathVariable Long id) {
		VentaDTO venta = ventaService.buscarVentaPorId(id);
		if(venta == null) {
			return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intenta buscar no existe")
                    .objeto(null)
                    .build(),
                    HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("")
                .objeto(VentaDTO.builder()
                		.UUID(venta.getUUID())
                		.fecha_venta(venta.getFecha_venta())
                		.monto_total(venta.getMonto_total())
                		.cliente_uuid(venta.getCliente_uuid())
                		.empleado_uuid(venta.getEmpleado_uuid())
                		.servicio_uuid(venta.getServicio_uuid())
                		.paquete_uuid(venta.getPaquete_uuid())
                		.build())
                .build()
                ,HttpStatus.OK);		
	}
}
