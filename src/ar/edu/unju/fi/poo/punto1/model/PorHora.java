package ar.edu.unju.fi.practico6.model;

import java.util.Date;

public class PorHora extends RegistroIngresoSalida {
    private Cupon cupon;
    private double valorHora;

    public PorHora(Integer id, Date fecha, Date hora, Vehiculo vehiculo, String estado, Cupon cupon, double valorHora) {
        super(id, fecha, hora, vehiculo, estado);
        this.cupon = cupon;
        this.valorHora = valorHora;
    }

    @Override
    public Double obtenerImporte() {
        Date ahora = new Date();
        long diferenciaMilis = ahora.getTime() - getHora().getTime();
        double minutos = diferenciaMilis / (1000.0 * 60.0);

        // Sin pago fraccionado por menos de una hora (mínimo 1 hora)
        long horas = (long) Math.ceil(minutos / 60.0);
        if (horas < 1) {
            horas = 1;
        }

        double total = horas * valorHora;

        // Descuento con cupón válido
        if (cupon != null && cupon.esValido(ahora)) {
            total = total * (1.0 - (cupon.getPorcentajeDescuento() / 100.0));
        }

        return total;
    }

    public void setCupon(Cupon cupon) {
		this.cupon = cupon;
	}

	public void setValorHora(double valorHora) {
		this.valorHora = valorHora;
	}

	public Cupon getCupon() {
        return cupon;
    }

    public double getValorHora() {
        return valorHora;
    }
}