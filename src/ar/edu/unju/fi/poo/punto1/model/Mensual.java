package ar.edu.unju.fi.poo.punto1.model;

import java.util.Date;

public class Mensual extends RegistroIngresoSalida {
    private Cliente cliente;

    public Mensual(Integer id, Date fecha, Date hora, Vehiculo vehiculo, String estado, Cliente cliente) {
        super(id, fecha, hora, vehiculo, estado);
        this.cliente = cliente;
    }

    @Override
    public Double obtenerImporte() {
        return 0.0;
    }

    public Cliente getCliente() {
        return cliente;
    }

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}