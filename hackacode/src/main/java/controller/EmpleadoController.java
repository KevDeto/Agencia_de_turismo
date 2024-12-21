package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.dto.EmpleadoDto;
import model.entity.Empleado;
import model.payload.MensajeResponse;
import service.IEmpleadoService;

@RestController
@RequestMapping("/api/v1")
@Controller
public class EmpleadoController {
	@Autowired
	private IEmpleadoService empleadoService;

	@GetMapping("/Empleado")
	public ResponseEntity<?> mostrarTodos(){
		List<Empleado> lista = empleadoService.listAll();
		if (lista == null) {
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("No hay registros")
					.objeto(null)
					.build(),
					HttpStatus.OK);
		}
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("")
                .objeto(lista)
                .build(),
                HttpStatus.OK);
	}
	
	@PostMapping("/Empleado")
	public ResponseEntity<?> crearEmpleado(@RequestBody EmpleadoDto empleadoDto){
		Empleado empleado = null;
		try {
			empleado = empleadoService.save(empleadoDto);
			return new ResponseEntity<>(MensajeResponse
                    .builder()
                    .mensaje("Guardado correctamente")
                    .objeto(empleadoDto)
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
	
	@PutMapping("/empleado/{id}")
    public ResponseEntity<?> actualizar(@RequestBody EmpleadoDto empleadoDto, @PathVariable Long id) {
        Empleado empleadoActualizado = null;
        try {
            if (empleadoService.existsById(id)) {
                empleadoActualizado = empleadoService.save(empleadoDto);
                return new ResponseEntity<>(MensajeResponse
                        .builder()
                        .mensaje("Guardado correctamente")
                        .objeto(EmpleadoDto.builder()
                    			.nombre(empleadoDto.getNombre())
                    			.apellido(empleadoDto.getApellido())
                    			.direccion(empleadoDto.getDireccion())
                    			.dni(empleadoDto.getDni())
                    			.fecha_nac(empleadoDto.getFecha_nac())
                    			.nacionalidad(empleadoDto.getNacionalidad())
                    			.celular(empleadoDto.getCelular())
                    			.email(empleadoDto.getEmail())
                    			.cargo(empleadoDto.getCargo())
                    			.sueldo(empleadoDto.getSueldo())
                                .build())
                        .build(),
                        HttpStatus.CREATED);
            } else {
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
    
    @DeleteMapping("/Empleado/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        try {
        	Empleado empleadoBorrado = empleadoService.findById(id);
            empleadoService.delete(empleadoBorrado);
            return new ResponseEntity<>(empleadoBorrado, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .objeto(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    
    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> mostrarPorId(@PathVariable Long id) {
    	Empleado empleado = empleadoService.findById(id);
        if (empleado == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intenta buscar no existe")
                    .objeto(null)
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("")
                .objeto(EmpleadoDto.builder()
            			.nombre(empleado.getNombre())
            			.apellido(empleado.getApellido())
            			.direccion(empleado.getDireccion())
            			.dni(empleado.getDni())
            			.fecha_nac(empleado.getFecha_nac())
            			.nacionalidad(empleado.getNacionalidad())
            			.celular(empleado.getCelular())
            			.email(empleado.getEmail())
            			.cargo(empleado.getCargo())
            			.sueldo(empleado.getSueldo())
                        .build())
                .build(),
                HttpStatus.OK);
    }
}
