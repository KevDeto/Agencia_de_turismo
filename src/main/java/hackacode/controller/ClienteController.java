package hackacode.controller;

import java.util.HashMap;
import java.util.Map;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hackacode.model.dto.ClienteDto;
import hackacode.model.entity.Cliente;
import hackacode.model.payload.MensajeResponse;
import hackacode.service.IClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Controller
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("clientes")
    public ResponseEntity<?> showAll() {
        List<Cliente> getList = clienteService.listAll();
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

    @PostMapping("cliente")
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto) {
        Cliente clienteSave = null;
        try {
            clienteSave = clienteService.save(clienteDto);
            /* clienteDto = ClienteDto.builder()
                    .idCliente(clienteDto.getIdCliente())
                    .nombre(clienteDto.getNombre())
                    .apellido(clienteDto.getApellido())
                    .correo(clienteDto.getCorreo())
                    .fechaRegistro(clienteDto.getFechaRegistro())
                    .build();*/
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

    @PutMapping("cliente/{id}")
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto, @PathVariable Long id) {
        Cliente clienteUpdate = null;
        try {
            Cliente findCliente = clienteService.findById(id);
            if (clienteService.existsById(id)) {
                clienteDto.setUUID(id);
                clienteUpdate = clienteService.save(clienteDto);
                /* clienteDto = ClienteDto.builder()
                        .idCliente(clienteUpdate.getIdCliente())
                        .nombre(clienteUpdate.getNombre())
                        .apellido(clienteUpdate.getApellido())
                        .correo(clienteUpdate.getCorreo())
                        .fechaRegistro(clienteUpdate.getFechaRegistro())
                        .build();*/
                return new ResponseEntity<>(MensajeResponse
                        .builder()
                        .mensaje("Guardado correctamente")
                        .objeto(ClienteDto.builder()
                                .UUID(clienteUpdate.getUUID())
                                .nombre(clienteUpdate.getNombre())
                                .apellido(clienteUpdate.getApellido())
                                .dni(clienteUpdate.getDni())
                                .nacionalidad(clienteUpdate.getNacionalidad())
                                .email(clienteUpdate.getEmail())
                                .celular(clienteUpdate.getCelular())
                                .fecha_nac(clienteUpdate.getFecha_nac())
                                .direccion(clienteUpdate.getDireccion())
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

    @DeleteMapping("cliente/{id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        //Map<String, Object> response = new HashMap<>();
        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
//            response.put("mensaje", exDt.getMessage());
//            response.put("cliente", null);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .objeto(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
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
                        .UUID(cliente.getUUID())
                        .nombre(cliente.getNombre())
                        .apellido(cliente.getApellido())
                        .dni(cliente.getDni())
                        .nacionalidad(cliente.getNacionalidad())
                        .email(cliente.getEmail())
                        .celular(cliente.getCelular())
                        .fecha_nac(cliente.getFecha_nac())
                        .direccion(cliente.getDireccion())
                        .build())
                .build(),
                HttpStatus.OK);
    }
}
