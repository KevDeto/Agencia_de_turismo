package hackacode.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hackacode.model.dao.IServicioTuristicoDao;
import hackacode.model.dto.ServicioTuristicoDT0;
import hackacode.model.entity.PaqueteTuristico;
import hackacode.model.entity.ServicioTuristico;
import hackacode.service.IServicioTuristicoService;
import hackacode.utils.RelacionadorPaquetes;

@Service
public class ServicioTuristicoImpl implements IServicioTuristicoService{

	@Autowired
	private IServicioTuristicoDao servicioTuristicoDao;
	@Autowired
	private RelacionadorPaquetes relacionadorPaquetes;

	@Transactional
	@Override
	public ServicioTuristico save(ServicioTuristicoDT0 servicioTuristicoDT0) {
		//esto me da los paquetes segun el id que ponga en el json
    	Set<PaqueteTuristico> paquete = relacionadorPaquetes
    			.obtenerPaquetesRelacionados(servicioTuristicoDT0.getPaquete_turistico());

    	ServicioTuristico servicioSave = ServicioTuristico.builder()
	            .UUID(servicioTuristicoDT0.getUUID())
	            .nombre(servicioTuristicoDT0.getNombre())
	            .descripcion(servicioTuristicoDT0.getDescripcion())
	            .destino_servicio(servicioTuristicoDT0.getDestino_servicio())
	            .fecha_servicio(servicioTuristicoDT0.getFecha_servicio())
	            .costo_servicio(servicioTuristicoDT0.getCosto_servicio())
	            .paquetes(paquete)
				.build();
		return servicioTuristicoDao.save(servicioSave);
	}

	@Transactional
	@Override
	public void delete(ServicioTuristico servicioTuristico) { 
	    servicioTuristicoDao.delete(servicioTuristico);
	}

	@Transactional(readOnly = true)
	@Override
	public ServicioTuristico findById(Long id) {
		return servicioTuristicoDao.findById(id).orElse(null);
	}

	@Override
	public List<ServicioTuristico> listAll() {
		return servicioTuristicoDao.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		return servicioTuristicoDao.existsById(id);
	}
}
