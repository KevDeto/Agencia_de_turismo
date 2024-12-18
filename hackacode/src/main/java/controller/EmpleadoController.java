package controller;

import service.IEmpleadoService;

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

import model.entity.Empleado;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

	@Autowired
	private IEmpleadoService empleadoService;

	@PostMapping
	public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
		Empleado nuevoEmpleado = empleadoService.guardar(empleado);
		return ResponseEntity.ok(nuevoEmpleado);
	}

	@GetMapping
	public ResponseEntity<List<Empleado>> obtenerEmpleados() {
		List<Empleado> empleados = empleadoService.obtenerTodos();
		return ResponseEntity.ok(empleados);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id) {
		Empleado empleado = empleadoService.obtenerPorId(id);
		return ResponseEntity.ok(empleado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleado) {
		Empleado empleadoActualizado = empleadoService.actualizar(id, empleado);
		return ResponseEntity.ok(empleadoActualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarEmpleado(@PathVariable Long id) {
		empleadoService.eliminar(id);
		return ResponseEntity.ok("Empleado eliminado correctamente.");
	}
}
