package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.entity.Cliente;
import service.IClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@PostMapping
	public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
		Cliente nuevoCliente = clienteService.guardar(cliente);
		return ResponseEntity.ok(nuevoCliente);
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<Cliente>> obtenerClientes() {
		List<Cliente> clientes = clienteService.obtenerTodos();
		return ResponseEntity.ok(clientes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
		Cliente cliente = clienteService.obtenerPorId(id);
		return ResponseEntity.ok(cliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
		Cliente clienteActualizado = clienteService.actualizar(id, cliente);
		return ResponseEntity.ok(clienteActualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarCliente(@PathVariable Long id) {
		clienteService.eliminar(id);
		return ResponseEntity.ok("Cliente eliminado correctamente");
	}

}