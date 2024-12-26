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

import hackacode.model.dto.ClienteDto;
import hackacode.model.dto.VentaDto;
import hackacode.model.entity.Cliente;
import hackacode.model.entity.Venta;
import hackacode.model.payload.MensajeResponse;
import hackacode.service.IVentaService;

@RestController
@RequestMapping("api/v1")
public class VentaController {

	@Autowired
	private IVentaService ventaService;
	
	@GetMapping("ventas")
	public ResponseEntity<?> showAll(){
		List<Venta> getList = ventaService.listAll();
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
	public ResponseEntity<?> create(@RequestBody VentaDto ventaDto){
		Venta ventaSave = null;
        try {
        	ventaSave = ventaService.save(ventaDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .objeto(ventaDto)
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
	public ResponseEntity<?> update(@RequestBody VentaDto ventaDto, @PathVariable Long id) {
		Venta ventaUpdate = null;
		try {
			Venta findVenta = ventaService.findById(id);
			if(ventaService.existsById(id)) {
                ventaDto.setUUID(id);
                ventaUpdate = ventaService.save(ventaDto);
                return new ResponseEntity<>(MensajeResponse
                        .builder()
                        .mensaje("Guardado correctamente")
                        .objeto(VentaDto.builder()
                        		.UUID(ventaUpdate.getUUID())
                        		.fecha_venta(ventaUpdate.getFecha_venta())
                        		.monto_total(ventaUpdate.getMonto_total())
                        		.cliente_uuid(ventaUpdate.getCliente().getUUID())
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
			Venta ventaDelete = ventaService.findById(id);
			ventaService.delete(ventaDelete);
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
		Venta venta = ventaService.findById(id);
		if(venta == null) {
			return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intenta buscar no existe")
                    .objeto(null)
                    .build(),
                    HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("")
                .objeto(VentaDto.builder()
                		.UUID(venta.getUUID())
                		.fecha_venta(venta.getFecha_venta())
                		.monto_total(venta.getMonto_total())
                		.cliente_uuid(venta.getCliente().getUUID())
                		.build())
                .build()
                ,HttpStatus.OK);		
	}
}
