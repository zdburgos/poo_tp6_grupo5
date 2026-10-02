package ar.edu.unju.fi.poo.punto2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import ar.edu.unju.fi.poo.punto2.model.*;

public class ManagerEmpleado {
	private List<Empleado> empleados;

    public ManagerEmpleado() {
        empleados = new ArrayList<>();
        // Profesionales
        Profesional p1 = new Profesional(1, 12345678, "Ana",LocalDate.of(2020, 3, 10),2);
        p1.agregarTitulo(new Titulo(2022, "Ingeniería Informática", "Universitario"));
        Profesional p2 = new Profesional(2, 23456789, "Carlos",LocalDate.of(2018, 5, 15),1);
        p2.agregarTitulo(new Titulo(2020, "Licenciatura en Sistemas", "Universitario"));
        p2.agregarTitulo(new Titulo(2023, "Programación", "Terciario"));

        // Administrativos
        Administrativo a1 = new Administrativo(3, 34567890, "Laura",LocalDate.of(2021, 2, 20),3,"A");
        Administrativo a2 = new Administrativo(4, 45678901, "Pedro",LocalDate.of(2019, 7, 1),0,"B");

        // Limpieza
        Limpieza l1 = new Limpieza(5, 56789012, "Marta",LocalDate.of(2022, 1, 10),2);
        Limpieza l2 = new Limpieza( 6, 67890123, "Juan",LocalDate.of(2017, 11, 5), 1);

        // Agregar empleados a la lista
        empleados.add(p1);
        empleados.add(p2);
        empleados.add(a1);
        empleados.add(a2);
        empleados.add(l1);
        empleados.add(l2);
    }

    //Getters and Setters
    public List<Empleado> getEmpleados() {return empleados;}
    public void setEmpleados(List<Empleado> empleados) {this.empleados = empleados;}
}