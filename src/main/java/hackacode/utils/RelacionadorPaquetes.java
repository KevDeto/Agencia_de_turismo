package hackacode.utils;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hackacode.model.entity.PaqueteTuristico;

//@Component
public class RelacionadorPaquetes {
//
//    @Autowired
//    private IPaqueteTuristicoDao paqueteTuristicoDao;
//
//     //@return Conjunto de entidades PaqueteTuristico que existen en la base de datos.
//     
//    public Set<PaqueteTuristico> obtenerPaquetesRelacionados(Set<Long> UUIDs) {
//        return Optional.ofNullable(UUIDs)
//            .orElse(Collections.emptySet()) // si es null, inicializo un conjunto vacío
//            .stream()
//            .map(id -> {
//                try {
//                    return paqueteTuristicoDao.findById(id).orElse(null);
//                } catch (NumberFormatException e) {
//                    return null;
//                }
//            })
//            .filter(Objects::nonNull) // filtrar solo los paquetes validos no nulos
//            .collect(Collectors.toSet());
//    }
}
