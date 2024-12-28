package hackacode.utils;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hackacode.model.dao.IServicioTuristicoDao;
import hackacode.model.entity.ServicioTuristico;

@Component
public class RelacionadorServicios {
	
    @Autowired
    private IServicioTuristicoDao servicioTuristicoDao;

    public Set<ServicioTuristico> obtenerServiciosRelacionados(Set<String> UUIDs) {
        return Optional.ofNullable(UUIDs)
            .orElse(Collections.emptySet())
            .stream()
            .map(id -> {
                try {
                    return servicioTuristicoDao.findById(Long.parseLong(id)).orElse(null);
                } catch (NumberFormatException e) {
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());
    }
}
