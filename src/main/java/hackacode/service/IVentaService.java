package hackacode.service;

import java.util.List;

import hackacode.model.dto.VentaDto;
import hackacode.model.entity.Venta;

public interface IVentaService {
	List<Venta> listAll();
	Venta save(VentaDto venta);
	Venta findById(Long id);
	void delete(Venta venta);
	boolean existsById(Long id);
}
