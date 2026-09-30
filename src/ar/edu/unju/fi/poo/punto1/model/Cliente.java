package ar.edu.unju.fi.practico6.model;

public class Cliente {
    private Integer id;
    private String dni;
    private String domicilio;
    private String celular;

    public Cliente(Integer id, String dni, String domicilio, String celular) {
        this.id = id;
        this.dni = dni;
        this.domicilio = domicilio;
        this.celular = celular;
    }

    public void setId(Integer id) {
		this.id = id;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Integer getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getCelular() {
        return celular;
    }
}