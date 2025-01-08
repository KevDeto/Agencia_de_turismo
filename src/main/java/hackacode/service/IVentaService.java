package hackacode.service;

import java.util.List;

import hackacode.model.dto.VentaDTO;
import hackacode.model.entity.Venta;

public interface IVentaService {

	VentaDTO crearVenta(VentaDTO ventaDTO);

	VentaDTO obtenerVentaPorId(Long numVenta);

	List<VentaDTO> listarVentas();

	void eliminarVenta(Long numVenta);
}
