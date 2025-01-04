package hackacode.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hackacode.model.dao.IPaqueteTuristicoDao;
import hackacode.model.dto.PaqueteTuristicoDTO;
import hackacode.model.entity.PaqueteTuristico;
import hackacode.model.entity.ServicioTuristico;
import hackacode.service.IPaqueteTuristicoService;
import hackacode.utils.RelacionadorServicios;

@Service
public class PaqueteTuristicoImpl implements IPaqueteTuristicoService {

	@Autowired
	private IPaqueteTuristicoDao paqueteTuristicoDao;
	@Autowired
	private RelacionadorServicios relacionadorServicios;
	
	@Transactional
	@Override
	public PaqueteTuristico save(PaqueteTuristicoDTO paqueteTuristicoDTO) {
		//esto me da los servicios segun los id que ponga en el json
    	Set<ServicioTuristico> servicios = relacionadorServicios
    			.obtenerServiciosRelacionados(paqueteTuristicoDTO.getServicio_turistico());
    	
		PaqueteTuristico paqueteSave = PaqueteTuristico.builder()
				.UUID(paqueteTuristicoDTO.getUUID())
				.costo_paquete(paqueteTuristicoDTO.getCosto_paquete())
				.servicios(servicios)
				.build();
		return paqueteTuristicoDao.save(paqueteSave);
	}


	@Transactional
	@Override
	public void delete(PaqueteTuristico paqueteTuristico) {     
		paqueteTuristicoDao.delete(paqueteTuristico);

	}

	@Transactional(readOnly = true)
	@Override
	public PaqueteTuristico findById(Long id) {
		return paqueteTuristicoDao.findById(id).orElse(null);
	}

	@Override
	public List<PaqueteTuristico> listAll() {
		return paqueteTuristicoDao.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		return paqueteTuristicoDao.existsById(id);
	}
}
