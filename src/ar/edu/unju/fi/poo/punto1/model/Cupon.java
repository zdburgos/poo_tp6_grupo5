package ar.edu.unju.fi.poo.punto1.model;

import java.util.Date;

public class Cupon {
    private String codigo;
    private Date fechaVencimiento;
    private Double porcentajeDescuento;

    public Cupon(String codigo, Date fechaVencimiento, Double porcentajeDescuento) {
        this.codigo = codigo;
        this.fechaVencimiento = fechaVencimiento;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public boolean esValido(Date fechaActual) {
        return fechaVencimiento != null && !fechaActual.after(fechaVencimiento);
    }

    public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public void setPorcentajeDescuento(Double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public String getCodigo() {
        return codigo;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public Double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }
}