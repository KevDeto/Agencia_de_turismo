package service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import model.dao.IClienteRepository;
import model.dto.ClienteDTO;
import model.entity.Cliente;
import model.mapper.ClienteMapper;
import service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

	  @Autowired
	    private IClienteRepository clienteRepository;

	    @Transactional
	    @Override
	    public Cliente save(ClienteDTO clienteDto) {
	        Cliente cliente = Cliente.builder()
	                .idCliente(clienteDto.getIdCliente())
	                .nombre(clienteDto.getNombre())
	                .apellido(clienteDto.getApellido())
	                .correo(clienteDto.getCorreo())
	                .fechaRegistro(clienteDto.getFechaRegistro())
	                .build();
	        return clienteDao.save(cliente);
	    }

	    @Transactional(readOnly = true)
	    @Override
	    public Cliente obtenerPorId(Integer id) {
	        return clienteRepository.findById(id).orElse(null);
	    }

	    @Transactional
	    @Override
	    public void delete(Cliente cliente) {
	        clienteDao.delete(cliente);
	    }

	    @Override
	    public boolean existsById(Integer id) {
	        return clienteDao.existsById(id);
	    }

	    @Override
	    public List<Cliente> listAll() {
	        return (List)clienteDao.findAll();
	    }

		@Override
		public Cliente guardar(ClienteDTO clienteDto) {
			// TODO Auto-generated method stub
			Cliente cliente = Cliente
			return null;
		}
	    
		@Override
		public Cliente actualizar(Long id, Cliente clienteDTO) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Cliente> obtenerTodos() {
			// TODO Auto-generated method stub
			return null;
		}



		@Override
		public Cliente obtenerPorId(Long id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void eliminar(Cliente cliente) {
			// TODO Auto-generated method stub
			
		}
}
