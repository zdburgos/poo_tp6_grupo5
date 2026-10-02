package ar.edu.unju.fi.poo.punto2.main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.fi.poo.punto2.ManagerEmpleado;
import ar.edu.unju.fi.poo.punto2.model.*;

public class MainEmpleados {

	public static void main(String[] args) {
		ManagerEmpleado manager = new ManagerEmpleado();
		// a. Agregar empleados de distintos tipos
		Profesional nuevoProf = new Profesional(8, 41111222, "Julio Paredes", LocalDate.of(2023, 1, 10), 1);
        nuevoProf.agregarTitulo(new Titulo(2022, "Analista en Sistemas", "Terciario"));
        boolean ag1 = manager.agregarEmpleado(nuevoProf);

        Administrativo nuevoAdm = new Administrativo(9, 42222333, "Carla Ruiz", LocalDate.of(2022, 6, 15), 2, "B");
        boolean ag2 = manager.agregarEmpleado(nuevoAdm);

        Limpieza nuevoLimp = new Limpieza(10, 43333444, "Esteban Quito", LocalDate.of(2024, 2, 1), 0);
        boolean ag3 = manager.agregarEmpleado(nuevoLimp);

        // b. Buscar un empleado por legajo y mostrar datos personales + sueldo neto
        int legajoBuscado = 1;
        Empleado empBuscado = manager.buscarLegajo(legajoBuscado);
        if (empBuscado != null) {
            System.out.println("  - Legajo: " + empBuscado.getLegajo());
            System.out.println("  - Nombre: " + empBuscado.getNombre());
            System.out.println("  - Documento: " + empBuscado.getDocumento());
            System.out.println("  - Hijos a cargo: " + empBuscado.getCantidadHijos());
            System.out.println("  - Sueldo Neto Calculado: $" + empBuscado.calcularSueldoNeto());
        } else {
            System.out.println("  - Empleado no encontrado.");
        }

        // c. Buscar un administrativo por legajo, cambiar su categoria y mostrar sueldo neto
        int legajoAdm = 3;
        Empleado empAdm = manager.buscarLegajo(legajoAdm);
        if (empAdm instanceof Administrativo) {
            Administrativo administrativo = (Administrativo) empAdm;
            System.out.println("  - Empleado: " + administrativo.getNombre() + " | Categoría anterior: " + administrativo.getCategoria());
            administrativo.setCategoria("C"); 
            System.out.println("  - Nueva categoría asignada: " + administrativo.getCategoria());
            System.out.println("  - Nuevo Sueldo Neto Actualizado: $" + administrativo.calcularSueldoNeto());
        } else {
            System.out.println("  - El empleado no es de tipo Administrativo.");
        }
        
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
		System.out.println("\n==================  TOTALES ==================");
		System.out.println("Total remunerativos bonificables: " + totalRemunerativo);
		System.out.println("Total salario familiar: " + totalSalarioFamiliar);
		System.out.println("Total descuentos: " + totalDescuentos);
		System.out.println("Total importe neto: " + totalNeto);
		System.out.println("-----------------------------------------------------------");
		
		// f. Neto acumulado por tipo
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n================== Neto Acumulado ==================");
		System.out.println("Seleccione el tipo de empleado:");
		System.out.println("1. Profesional");
		System.out.println("2. Administrativo");
		System.out.println("3. Limpieza");
		System.out.print("Opción: ");
		int opcion = scanner.nextInt();
		String tipo;
		switch (opcion) {
		    case 1:
		        tipo = "profesional";
		        break;
		    case 2:
		        tipo = "administrativo";
		        break;
		    case 3:
		        tipo = "limpieza";
		        break;
		    default:
		        tipo = "";
		        System.out.println("Opción inválida.");
		}
		if (!tipo.isEmpty()) {
		    double netoAcumulado =
		            manager.calcularNetoAcumulado(tipo);
		    System.out.println("\nTipo de empleado: " + tipo);
		    System.out.println("Importe neto acumulado: " + netoAcumulado);
		    System.out.println("-----------------------------------------------------------");
		}
		scanner.close();
	}
}