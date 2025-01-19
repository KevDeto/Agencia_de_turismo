package hackacode.controller;

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

import hackacode.model.dto.ClienteDTO;
import hackacode.model.entity.Cliente;
import hackacode.model.payload.MensajeResponse;
import hackacode.service.IClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("clientes")
    public ResponseEntity<MensajeResponse> showAll() {
        List<ClienteDTO> listaClientes = clienteService.listarClientes();
        if (listaClientes.isEmpty()) {
        	return ResponseEntity.ok(MensajeResponse.builder()
        			.mensaje("No se han encontrado registros.")
        			.objeto(listaClientes)
        			.build());
        }
        return ResponseEntity.ok(MensajeResponse.builder()
                .mensaje("Clientes recuperados correctamente.")
                .objeto(listaClientes)
                .build());
    }

    @PostMapping("cliente")
    public ResponseEntity<MensajeResponse> create(@RequestBody ClienteDTO clienteDTO) {
        try {
        	ClienteDTO clienteSave = clienteService.crearCliente(clienteDTO);
        	return ResponseEntity.status(HttpStatus.CREATED)
        			.body(MensajeResponse.builder()
        					.mensaje("Cliente creado correctamente.")
        					.objeto(clienteSave)
        					.build());

        } catch (DataAccessException e) {
        	return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
        			.body(MensajeResponse.builder()
        					.mensaje("Error al crear cliente: " + e.getMessage())
        					.objeto(null)
        					.build());
        }
    }

    @PutMapping("cliente/{id}")
    public ResponseEntity<MensajeResponse> update(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id) {
    	if(!clienteService.existeClientePorId(id)) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body(MensajeResponse.builder()
    						.mensaje("Cliente con ID " + id + " no encontrado.")
    						.objeto(null)
    						.build());
    	}
    	try {
			clienteDTO.setUUID(id);
			ClienteDTO clienteUpdate = clienteService.actualizarCliente(id, clienteDTO);
			return ResponseEntity.ok(MensajeResponse.builder()
							.mensaje("Cliente modificado correctamente.")
							.objeto(clienteUpdate)
							.build());
		} catch (DataAccessException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(MensajeResponse.builder()
							.mensaje("Error al modificar cliente: " + e.getMessage())
							.objeto(null)
							.build());
		}
    }

    @DeleteMapping("cliente/{id}")
    public ResponseEntity<MensajeResponse> delete(@PathVariable Long id) {
    	if(!clienteService.existeClientePorId(id)) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body(MensajeResponse.builder()
    						.mensaje("Cliente con ID " + id + " no encontrado.")
    						.objeto(null)
    						.build());
    	}
        try {
//            ClienteDTO clienteDelete = clienteService.obtenerClientePorId(id);
            clienteService.eliminarCliente(id);
            
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        
        } catch (DataAccessException e) {
        	return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
        			.body(MensajeResponse.builder()
        					.mensaje("Error al eliminar cliente:" + e.getMessage())
        					.objeto(null)
        					.build());
        }
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<MensajeResponse> showById(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.obtenerClientePorId(id);
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            		.body(MensajeResponse.builder()
            				.mensaje("Cliente con ID " + id + " no encontrado.")
            				.objeto(null)
            				.build());
        }
        return ResponseEntity.ok(MensajeResponse.builder()
        		.mensaje("Cliente recuperado correctamente.")
        		.objeto(cliente)
        		.build());
    }
}
