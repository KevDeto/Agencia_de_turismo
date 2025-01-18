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

import hackacode.model.dto.EmpleadoDTO;
import hackacode.model.entity.Empleado;
import hackacode.model.payload.MensajeResponse;
import hackacode.service.IEmpleadoService;

@RestController
@RequestMapping("/api/v1")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping("empleados")
    public ResponseEntity<MensajeResponse> showAll() {
        List<Empleado> listaEmpleados = empleadoService.listarEmpleados();
        if(listaEmpleados.isEmpty()) {
        	return ResponseEntity.ok(MensajeResponse.builder()
        			.mensaje("No se han encontrado registros.")
        			.objeto(listaEmpleados)
        			.build());
        }
        return ResponseEntity.ok(MensajeResponse.builder()
                .mensaje("Empleados recuperados correctamente.")
                .objeto(listaEmpleados)
                .build());
    }

    @PostMapping("empleado")
    public ResponseEntity<MensajeResponse> create(@RequestBody EmpleadoDTO empleadoDTO) {
        try {
        	Empleado empleadoSave = empleadoService.crearEmpleado(empleadoDTO);
        	return ResponseEntity.status(HttpStatus.CREATED)
        			.body(MensajeResponse.builder()
        					.mensaje("Empleado creado correctamente.")
        					.objeto(empleadoSave)
        					.build());
        } catch (DataAccessException e) {
        	return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
        			.body(MensajeResponse.builder()
        					.mensaje("Error al crear empleado: " + e.getMessage())
        					.objeto(null)
        					.build());
        }
    }

    @PutMapping("empleado/{id}")
    public ResponseEntity<MensajeResponse> update(@RequestBody EmpleadoDTO empleadoDTO, @PathVariable Long id) {
    	if(!empleadoService.existeEmpleadoPorId(id)) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body(MensajeResponse.builder()
    						.mensaje("Empleado con ID " + id + " no encontrado.")
    						.objeto(null)
    						.build());
    	}
    	try {
			empleadoDTO.setUUID(id);
			Empleado empleadoUpdate = empleadoService.crearEmpleado(empleadoDTO);
			return ResponseEntity.ok(MensajeResponse.builder()
							.mensaje("Empleado actualizado correctamente.")
							.objeto(empleadoUpdate)
							.build());
		} catch (DataAccessException e) {
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
					.body(MensajeResponse.builder()
							.mensaje("Error al modificar empleado: " + e.getMessage())
							.objeto(null)
							.build());
		}
    }

    @DeleteMapping("empleado/{id}")
    public ResponseEntity<MensajeResponse> delete(@PathVariable Long id) {
    	if(!empleadoService.existeEmpleadoPorId(id)) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body(MensajeResponse.builder()
    						.mensaje("Empleado con ID " + id +" no encontrado.")
    						.objeto(null)
    						.build());
    	}
    	try {
            Empleado empleadoDelete = empleadoService.obtenerEmpleadoPorId(id);
            empleadoService.eliminarEmpleado(empleadoDelete);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (DataAccessException e) {
        	return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
        			.body(MensajeResponse.builder()
        					.mensaje("Error al eliminar empleado: " + e.getMessage())
        					.objeto(null)
        					.build());
        }
    }

    @GetMapping("empleado/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        Empleado empleado = empleadoService.obtenerEmpleadoPorId(id);
    	if(empleado == null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body(MensajeResponse.builder()
    						.mensaje("Empleado con ID " + id + " no encontrado.")
    						.objeto(null)
    						.build());
    	}
        return ResponseEntity.ok(MensajeResponse.builder()
        		.mensaje("Empleado recuperado correctamente.")
        		.objeto(empleado)
        		.build());
    }
}
