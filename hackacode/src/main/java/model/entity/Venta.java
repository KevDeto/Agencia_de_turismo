package model.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Venta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UUID;
	private Date fecha_venta;
	private String medio_pago;

	public Venta(Long UUID, Date fecha_venta, String medio_pago) {
		this.UUID = UUID;
		this.fecha_venta = fecha_venta;
		this.medio_pago = medio_pago;
	}
	
	
}
