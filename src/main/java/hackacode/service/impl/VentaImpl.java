package hackacode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hackacode.model.dto.VentaDTO;
import hackacode.model.entity.Venta;
import hackacode.model.repository.IClienteRepository;
import hackacode.model.repository.IEmpleadoRepository;
import hackacode.model.repository.IVentaRepository;
import hackacode.service.IVentaService;

@Service
public class VentaImpl implements IVentaService{
	
	@Autowired
	private IVentaRepository ventaRepository;
	@Autowired
	private IClienteRepository clienteRepository;
	@Autowired
	private IEmpleadoRepository empleadoRepository;

	@Override
	public List<Venta> listAll() {
		return (List<Venta>)ventaRepository.findAll();
	}

	@Transactional
	@Override
	public Venta save(VentaDTO ventaDTO) {
		Venta venta = Venta.builder()
	            .UUID(ventaDTO.getUUID())
	            .fecha_venta(ventaDTO.getFecha_venta())
	            .monto_total(ventaDTO.getMonto_total())
	            .cliente(clienteRepository.findById(ventaDTO.getCliente_uuid())
	                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado")))
	            .empleado(empleadoRepository.findById(ventaDTO.getEmpleado_uuid())
	            		.orElseThrow(() -> new RuntimeException("Empleado no encontrado")))
	            .build();
		return ventaRepository.save(venta);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Venta findById(Long id) {
		return ventaRepository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void delete(Venta venta) {
		ventaRepository.delete(venta);
	}

	@Override
	public boolean existsById(Long id) {
		return ventaRepository.existsById(id);
	}

}
