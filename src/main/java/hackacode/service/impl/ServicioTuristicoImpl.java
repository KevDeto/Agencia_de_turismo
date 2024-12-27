package hackacode.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hackacode.model.dao.IPaqueteTuristicoDao;
import hackacode.model.dao.IServicioTuristicoDao;
import hackacode.model.dto.ServicioTuristicoDto;
import hackacode.model.entity.PaqueteTuristico;
import hackacode.model.entity.ServicioTuristico;
import hackacode.service.IServicioTuristicoService;

@Service
public class ServicioTuristicoImpl implements IServicioTuristicoService{
	
    @Autowired
    private IServicioTuristicoDao servicioTuristicoDao;
    @Autowired
    private IPaqueteTuristicoDao paqueteTuristicoDao;

    @Transactional
    @Override
    public ServicioTuristico save(ServicioTuristicoDto servicioTuristicoDto) {
        Set<PaqueteTuristico> paquetes = servicioTuristicoDto.getPaquete_turistico().stream()
        		.map(UUID -> paqueteTuristicoDao.findById(Long.parseLong(UUID))
        				.orElseThrow(() -> new RuntimeException("Paquete no encontrado: " + UUID)))
                .collect(Collectors.toSet());
    	
    	ServicioTuristico paqueteTuristico = ServicioTuristico.builder()
                .UUID(servicioTuristicoDto.getUUID())
                .nombre(servicioTuristicoDto.getNombre())
                .descripcion(servicioTuristicoDto.getDescripcion())
                .destino_servicio(servicioTuristicoDto.getDestino_servicio())
                .fecha_servicio(servicioTuristicoDto.getFecha_servicio())
                .costo_servicio(servicioTuristicoDto.getCosto_servicio())
                .paquetes(paquetes)
                .build();
        return servicioTuristicoDao.save(paqueteTuristico);
    }

    @Transactional(readOnly = true)
    @Override
    public ServicioTuristico findById(Long id) {
        return servicioTuristicoDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(ServicioTuristico servicioTuristico) {
    	servicioTuristicoDao.delete(servicioTuristico);
    }

    @Override
    public boolean existsById(Long id) {
        return servicioTuristicoDao.existsById(id);
    }

    @Override
    public List<ServicioTuristico> listAll() {
        return (List<ServicioTuristico>)servicioTuristicoDao.findAll();
    }
}
