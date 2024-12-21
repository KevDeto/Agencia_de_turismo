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

import model.dto.ClienteDto;
import model.entity.Cliente;
import model.payload.MensajeResponse;
import service.IClienteService;

@RestController
@RequestMapping("/api/v1")
@Controller
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;

	@GetMapping("/cliente")
	public ResponseEntity<?> mostrarTodos(){
		List<Cliente> lista = clienteService.listAll();
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
	
	@PostMapping("/cliente")
	public ResponseEntity<?> crearCliente(@RequestBody ClienteDto clienteDto){
		Cliente cliente = null;
		try {
			cliente = clienteService.save(clienteDto);
			return new ResponseEntity<>(MensajeResponse
                    .builder()
                    .mensaje("Guardado correctamente")
                    .objeto(clienteDto)
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
	
    @PutMapping("/cliente/{id}")
    public ResponseEntity<?> actualizar(@RequestBody ClienteDto clienteDto, @PathVariable Long id) {
        Cliente clienteActualizado = null;
        try {
            if (clienteService.existsById(id)) {
            	clienteActualizado = clienteService.save(clienteDto);
                return new ResponseEntity<>(MensajeResponse
                        .builder()
                        .mensaje("Guardado correctamente")
                        .objeto(ClienteDto.builder()
                    			.nombre(clienteDto.getNombre())
                    			.apellido(clienteDto.getApellido())
                    			.direccion(clienteDto.getDireccion())
                    			.dni(clienteDto.getDni())
                    			.fecha_nac(clienteDto.getFecha_nac())
                    			.nacionalidad(clienteDto.getNacionalidad())
                    			.celular(clienteDto.getCelular())
                    			.email(clienteDto.getEmail())
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
    
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
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
        Cliente cliente = clienteService.findById(id);
        if (cliente == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intenta buscar no existe")
                    .objeto(null)
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("")
                .objeto(ClienteDto.builder()
            			.nombre(cliente.getNombre())
            			.apellido(cliente.getApellido())
            			.direccion(cliente.getDireccion())
            			.dni(cliente.getDni())
            			.fecha_nac(cliente.getFecha_nac())
            			.nacionalidad(cliente.getNacionalidad())
            			.celular(cliente.getCelular())
            			.email(cliente.getEmail())
                        .build())
                .build(),
                HttpStatus.OK);
    }
}
