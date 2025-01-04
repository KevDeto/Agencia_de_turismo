package hackacode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hackacode.model.dto.VentaDto;
import hackacode.model.entity.Venta;
import hackacode.model.repository.IClienteDao;
import hackacode.model.repository.IVentaDao;
import hackacode.service.IVentaService;

@Service
public class VentaImpl implements IVentaService{
	
	@Autowired
	private IVentaDao ventaDao;
	@Autowired
	private IClienteDao clienteDao;

	@Override
	public List<Venta> listAll() {
		return (List<Venta>)ventaDao.findAll();
	}

	@Transactional
	@Override
	public Venta save(VentaDto ventaDto) {
		Venta venta = Venta.builder()
	            .UUID(ventaDto.getUUID())
	            .fecha_venta(ventaDto.getFecha_venta())
	            .monto_total(ventaDto.getMonto_total())
	            .cliente(clienteDao.findById(ventaDto.getCliente_uuid())
	                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado")))
	            .build();
		return ventaDao.save(venta);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Venta findById(Long id) {
		return ventaDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void delete(Venta venta) {
		ventaDao.delete(venta);
	}

	@Override
	public boolean existsById(Long id) {
		return ventaDao.existsById(id);
	}

}
