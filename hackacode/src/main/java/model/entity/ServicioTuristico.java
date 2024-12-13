package model.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ServicioTuristico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UUID;

	private String nombre;
	private String descripcion_breve;
	private String destino_servicio;
	private Date fecha_servicio;
	private double costo_servicio;
	private String tipo_servicio;
}
