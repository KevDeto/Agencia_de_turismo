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

import model.dto.EmpleadoDTO;
import service.IEmpleadoService;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

	@Autowired
	private IEmpleadoService empleadoService;

	@PostMapping
	public ResponseEntity<EmpleadoDTO> crearEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
		EmpleadoDTO nuevoEmpleado = empleadoService.guardar(empleadoDTO);
		return ResponseEntity.ok(nuevoEmpleado);
	}

	@GetMapping
	public ResponseEntity<List<EmpleadoDTO>> obtenerEmpleados() {
		List<EmpleadoDTO> empleados = empleadoService.obtenerTodos();
		return ResponseEntity.ok(empleados);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmpleadoDTO> obtenerEmpleadoPorId(@PathVariable Long id) {
		EmpleadoDTO empleado = empleadoService.obtenerPorId(id);
		return ResponseEntity.ok(empleado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@PathVariable Long id, @RequestBody EmpleadoDTO empleadoDTO) {
		EmpleadoDTO empleadoActualizado = empleadoService.actualizar(id, empleadoDTO);
		return ResponseEntity.ok(empleadoActualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarEmpleado(@PathVariable Long id) {
		empleadoService.eliminar(id);
		return ResponseEntity.ok("Empleado eliminado correctamente.");
	}
}
