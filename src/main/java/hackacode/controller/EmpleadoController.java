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
    public ResponseEntity<?> showAll() {
        List<Empleado> getList = empleadoService.listarEmpleados();
        if (getList == null) {
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

    @PostMapping("empleado")
    public ResponseEntity<?> create(@RequestBody EmpleadoDTO empleadoDTO) {
        Empleado empleadoSave = null;
        try {
        	empleadoSave = empleadoService.crearEmpleado(empleadoDTO);
            return new ResponseEntity<>(MensajeResponse
                    .builder()
                    .mensaje("Guardado correctamente")
                    .objeto(empleadoDTO)
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

    @PutMapping("empleado/{id}")
    public ResponseEntity<?> update(@RequestBody EmpleadoDTO empleadoDTO, @PathVariable Long id) {
        Empleado empleadoUpdate = null;
        try {
        	Empleado findCliente = empleadoService.obtenerEmpleadoPorId(id);
            if (empleadoService.existeEmpleadoPorId(id)) {
            	empleadoDTO.setUUID(id);
            	empleadoUpdate = empleadoService.crearEmpleado(empleadoDTO);

                return new ResponseEntity<>(MensajeResponse
                        .builder()
                        .mensaje("Guardado correctamente")
                        .objeto(EmpleadoDTO.builder()
                                .UUID(empleadoUpdate.getUUID())
                                .nombre(empleadoUpdate.getNombre())
                                .apellido(empleadoUpdate.getApellido())
                                .dni(empleadoUpdate.getDni())
                                .nacionalidad(empleadoUpdate.getNacionalidad())
                                .email(empleadoUpdate.getEmail())
                                .celular(empleadoUpdate.getCelular())
                                .fecha_nac(empleadoUpdate.getFecha_nac())
                                .direccion(empleadoUpdate.getDireccion())
                                .cargo(empleadoUpdate.getCargo())
                                .sueldo(empleadoUpdate.getSueldo())
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

    @DeleteMapping("empleado/{id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Empleado empleadoDelete = empleadoService.obtenerEmpleadoPorId(id);
            empleadoService.eliminarEmpleado(empleadoDelete);
            return new ResponseEntity<>(empleadoDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .objeto(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("empleado/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        Empleado empleado = empleadoService.obtenerEmpleadoPorId(id);
        if (empleado == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intenta buscar no existe")
                    .objeto(null)
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("")
                .objeto(EmpleadoDTO.builder()
                        .UUID(empleado.getUUID())
                        .nombre(empleado.getNombre())
                        .apellido(empleado.getApellido())
                        .dni(empleado.getDni())
                        .nacionalidad(empleado.getNacionalidad())
                        .email(empleado.getEmail())
                        .celular(empleado.getCelular())
                        .fecha_nac(empleado.getFecha_nac())
                        .direccion(empleado.getDireccion())
                        .cargo(empleado.getCargo())
                        .sueldo(empleado.getSueldo())
                        .build())
                .build(),
                HttpStatus.OK);
    }
}
