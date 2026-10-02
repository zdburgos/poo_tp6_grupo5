package ar.edu.unju.fi.poo.punto2.main;

import ar.edu.unju.fi.poo.punto2.ManagerEmpleado;
import ar.edu.unju.fi.poo.punto2.model.*;

public class MainEmpleados {

	public static void main(String[] args) {
		ManagerEmpleado manager = new ManagerEmpleado();
		        
		// d. Buscar profesional, agregar título
		Empleado empleado = manager.buscarLegajo(1);
		if (empleado instanceof Profesional) {
			Profesional profesional = (Profesional) empleado;
		    profesional.agregarTitulo(new Titulo(2026,"Ingeniería en Sistemas","Universitario"));
		    System.out.println("================== Datos del Empleado ==================");
		    System.out.println("Legajo: " + profesional.getLegajo());
		    System.out.println("Profesional: " + profesional.getNombre());
		    System.out.println("Títulos:");
		    for (Titulo titulo : profesional.getTitulos()) {
		        System.out.println("- " + titulo.getNombreCarrera()
		                + " (" + titulo.getNivel() + ")");
		    }
		    System.out.println("Sueldo neto: " + profesional.calcularSueldoNeto());
		} else {
		    System.out.println("El empleado no es un profesional.");
		}       
	}
}