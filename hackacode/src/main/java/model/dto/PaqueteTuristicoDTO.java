package model.dto;

import java.util.List;

import model.entity.ServicioTuristico;

public class PaqueteTuristicoDTO {
	
	private Long UUID;
	private List<ServicioTuristico> lista_servicios;
	private double costo_paquete;
}
