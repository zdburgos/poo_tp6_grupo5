package ar.edu.unju.fi.poo.punto2.main;

import java.util.List;

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
		
		// e. Obtener empleados de una categoría
		List<Administrativo> administrativos = manager.obtenerAdministrativosPorCategoria("A");
		double totalRemunerativo = 0;
		double totalSalarioFamiliar = 0;
		double totalDescuentos = 0;
		double totalNeto = 0;

		System.out.println("\n-----------------------------------------------------------");
		System.out.println("========= Empleados Administrativos - Categoría A =========");
		for (Administrativo administrativo : administrativos) {
		    System.out.println("\nLegajo: " + administrativo.getLegajo());
		    System.out.println("Empleado: " + administrativo.getNombre());
		    System.out.println("Categoría: " + administrativo.getCategoria());
		    totalRemunerativo += administrativo.calcularRemunerativoBonificable();
		    totalSalarioFamiliar += administrativo.calcularSalarioFamiliar();
		    totalDescuentos += administrativo.calcularDescuentos();
		    totalNeto += administrativo.calcularSueldoNeto();
		}
		System.out.println("\n---------------- TOTALES ---------------------");
		System.out.println("Total remunerativos bonificables: " + totalRemunerativo);
		System.out.println("Total salario familiar: " + totalSalarioFamiliar);
		System.out.println("Total descuentos: " + totalDescuentos);
		System.out.println("Total importe neto: " + totalNeto);
		System.out.println("-----------------------------------------------------------");
	}
}