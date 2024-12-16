package model.dto;

import java.sql.Date;

public class VentaDTO {

	private Long UUID;
	private Date fecha_venta;
	private String medio_pago;

	public VentaDTO(Long UUID, Date fecha_venta, String medio_pago) {
		this.UUID = UUID;
		this.fecha_venta = fecha_venta;
		this.medio_pago = medio_pago;
	}
}
