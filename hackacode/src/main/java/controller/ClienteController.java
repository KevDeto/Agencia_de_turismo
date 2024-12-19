package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import model.dto.ClienteDTO;
import service.IClienteService;

@RestController
@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@PostMapping
	public ResponseEntity<ClienteDTO> crearCliente(@RequestBody ClienteDTO clienteDTO) {
		ClienteDTO nuevoCliente = clienteService.guardar(clienteDTO);
		return ResponseEntity.ok(nuevoCliente);
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> obtenerClientes() {
		List<ClienteDTO> clientes = clienteService.obtenerTodos();
		return ResponseEntity.ok(clientes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable Long id) {
		ClienteDTO cliente = clienteService.obtenerPorId(id);
		return ResponseEntity.ok(cliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
		ClienteDTO clienteActualizado = clienteService.actualizar(id, clienteDTO);
		return ResponseEntity.ok(clienteActualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarCliente(@PathVariable Long id) {
		clienteService.eliminar(id);
		return ResponseEntity.ok("Cliente eliminado correctamente");
	}

}