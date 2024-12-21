package service.impl;

import model.dao.IClienteRepository;
import model.dto.ClienteDto;
import model.entity.Cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import service.IClienteService;

@Service
public class ClienteImpl implements IClienteService {
	
	@Autowired
	private IClienteRepository clienteRepository;

    @Transactional
    @Override
    public Cliente save(ClienteDto clienteDto) {
    	Cliente cliente = Cliente.builder()
    			.nombre(clienteDto.getNombre())
    			.apellido(clienteDto.getApellido())
    			.direccion(clienteDto.getDireccion())
    			.dni(clienteDto.getDni())
    			.fecha_nac(clienteDto.getFecha_nac())
    			.nacionalidad(clienteDto.getNacionalidad())
    			.celular(clienteDto.getCelular())
    			.email(clienteDto.getEmail())
    			.build();
    	return clienteRepository.save(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Cliente cliente) {
    	clienteRepository.delete(cliente);
    }

    @Override
    public boolean existsById(Long id) {
        return clienteRepository.existsById(id);
    }
    
    @Override
    public List<Cliente> listAll() {
    	return clienteRepository.findAll();
    }
}
