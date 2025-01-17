package hackacode.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hackacode.model.dto.VentaDTO;
import hackacode.model.entity.Cliente;
import hackacode.model.entity.Empleado;
import hackacode.model.entity.PaqueteTuristico;
import hackacode.model.entity.ServicioTuristico;
import hackacode.model.entity.Venta;
import hackacode.model.repository.IClienteRepository;
import hackacode.model.repository.IEmpleadoRepository;
import hackacode.model.repository.IVentaRepository;
import hackacode.model.repository.IPaqueteTuristicoRepository;
import hackacode.model.repository.IServicioTuristicoRepository;

import hackacode.service.IVentaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentaImpl implements IVentaService{
	
	private final IVentaRepository ventaRepository;
	private final IClienteRepository clienteRepository;
	private final IEmpleadoRepository empleadoRepository;
	private final IServicioTuristicoRepository servicioRepository;
	private final IPaqueteTuristicoRepository paqueteRepository;
	
	@Override
	public VentaDTO crearVenta(VentaDTO ventaDTO) {
		Venta venta = convertirADominio(ventaDTO);
		Venta nuevaVenta = ventaRepository.save(venta);
		return convertirAVentaDTO(nuevaVenta);
	}

	@Override
	public VentaDTO obtenerVentaPorId(Long numVenta) {
		Venta venta = ventaRepository.findById(numVenta)
				.orElseThrow(() -> new RuntimeException("Venta no encontrada"));
		return convertirAVentaDTO(venta);
	}
	@Override
	public List<VentaDTO> listarVentas() {
		return ventaRepository.findAll().stream()
				.map(this::convertirAVentaDTO)
				.collect(Collectors.toList());
	}
	@Override
	public void eliminarVenta(Long numVenta) {
		Venta venta = ventaRepository.findById(numVenta)
				.orElseThrow(() -> new RuntimeException("Venta no encontrada"));
	
		ventaRepository.delete(venta);
	}


	private Venta convertirADominio(VentaDTO ventaDTO) {
		Cliente cliente = clienteRepository.findById(ventaDTO.getCliente_uuid())
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
		
		Empleado empleado = empleadoRepository.findById(ventaDTO.getEmpleado_uuid())
				.orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
		
		// todo ok, entonces empiezo a cargar los datos de venta
		Venta venta = new Venta();
		venta.setFecha_venta(LocalDate.now());
		venta.setMonto_total(ventaDTO.getMonto_total());
		venta.setCliente(cliente);
		venta.setEmpleado(empleado);
	
		
		if(ventaDTO.getServicio_uuid() != null && ventaDTO.getPaquete_uuid() != null) {
			if(ventaDTO.getServicio_uuid() != 0 && ventaDTO.getPaquete_uuid() != 0) {
				throw new RuntimeException("No se puede registrar una venta con un servicio y"
						+ " un paquete al mismo tiempo");
			}
		}
		
		if(ventaDTO.getServicio_uuid() != null && ventaDTO.getServicio_uuid() != 0) {
			ServicioTuristico servicio = servicioRepository.findById(ventaDTO.getServicio_uuid())
					.orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
			
			boolean existeVenta = ventaRepository.existsByClienteAndServicio(cliente, servicio);
			if(existeVenta) {
				throw new RuntimeException("El cliente ya tiene una venta registrada para este servicio");
			}
			venta.setServicio(servicio);
			
		}else if (ventaDTO.getPaquete_uuid() != null && ventaDTO.getPaquete_uuid() != 0) {
			PaqueteTuristico paquete = paqueteRepository.findById(ventaDTO.getPaquete_uuid())
					.orElseThrow(() -> new RuntimeException("Paquete no encontrado"));
			
			boolean existePaquete = ventaRepository.existsByClienteAndPaquete(cliente, paquete);
			if(existePaquete) {
				throw new RuntimeException("El cliente ya tiene una venta registrada para este paquete");
			}
			venta.setPaquete(paquete);
			
		}else {
			throw new RuntimeException("Debe asociar un servicio o un paquete a la venta");
		}
		return venta;
	}
	
	//condición ? valor_si_verdadero : valor_si_falso (deben devolver valores del mismo tipo o compatibles)	
	private VentaDTO convertirAVentaDTO(Venta venta) {
		return VentaDTO.builder()
				.UUID(venta.getUUID())
				.fecha_venta(venta.getFecha_venta())
				.monto_total(venta.getMonto_total())
				.cliente_uuid(venta.getCliente().getUUID())
				.empleado_uuid(venta.getEmpleado().getUUID())
				.servicio_uuid(venta.getServicio() != null ? venta.getServicio().getCodigoServicio() : null)
				.paquete_uuid(venta.getPaquete() != null ? venta.getPaquete().getCodigoPaquete() : null)
				.build();
	}

}
