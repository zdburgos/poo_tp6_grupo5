package ar.edu.unju.fi.poo.punto1.main;

import ar.edu.unju.fi.poo.punto1.Manager;
import ar.edu.unju.fi.poo.punto1.model.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class MainEstacionamiento {
    private static Date toDate(LocalDateTime ldt) {
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
    public static void main(String[] args) {
        Manager manager = new Manager();
        LocalDateTime ahora = LocalDateTime.now();
        Date hoy = toDate(ahora);
        Date fechaFutura = toDate(ahora.plusDays(5));   // Cupón válido
        Date fechaPasada = toDate(ahora.minusDays(10)); // Cupón vencido
        // Objetos de prueba
        Vehiculo v1 = new Vehiculo("AA123CD", "Toyota", "Gris");
        Vehiculo v2 = new Vehiculo("BB456EF", "Ford", "Azul");
        Vehiculo v3 = new Vehiculo("CC789GH", "Chevrolet", "Rojo");
        Vehiculo v4 = new Vehiculo("DD012IJ", "Fiat", "Blanco");
        Cupon cuponValido = new Cupon("DESC20", fechaFutura, 20.0);
        Cupon cuponVencido = new Cupon("VIEJO10", fechaPasada, 10.0);
        Cliente clienteMensual = new Cliente(1, "12345678", "Av. San Martín 450", "3888123456");
        System.out.println("================ CASOS DE PRUEBA ================");
        // a. Registro de ingresos de vehículos por hora sin cupón[cite: 2]
        System.out.println("\n[Prueba A] Registro por hora sin cupón:");
        try {
            Date hora10 = toDate(ahora.withHour(10).withMinute(0));
            PorHora reg1 = new PorHora(101, hoy, hora10, v1, "AFUERA", null, 1000.0);
            manager.registrarIngreso(reg1);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        // b. Registro de ingresos por hora con cupón (válido y vencido)[cite: 2]
        System.out.println("\n[Prueba B] Registro por hora con cupón (válido y vencido):");
        try {
            Date hora11 = toDate(ahora.withHour(11).withMinute(0));
            PorHora reg2 = new PorHora(102, hoy, hora11, v2, "AFUERA", cuponValido, 1000.0);
            manager.registrarIngreso(reg2);

            PorHora reg3 = new PorHora(103, hoy, hora11, v3, "AFUERA", cuponVencido, 1000.0);
            manager.registrarIngreso(reg3);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        // c. Registro de ingresos fuera de horario (21:30 hs)[cite: 2]
        System.out.println("\n[Prueba C] Registro fuera de horario (21:30 hs):");
        try {
            Date hora2130 = toDate(ahora.withHour(21).withMinute(30));
            PorHora regFueraHorario = new PorHora(104, hoy, hora2130, new Vehiculo("EE345KL", "Renault", "Negro"), "AFUERA", null, 1000.0);
            manager.registrarIngreso(regFueraHorario);
        } catch (Exception e) {
            System.out.println("Excepción esperada: " + e.getMessage());
        }
        // d. Registro de cliente mensual[cite: 2]
        System.out.println("\n[Prueba D] Registro de cliente mensual:");
        try {
            Mensual regMensual = new Mensual(105, hoy, hoy, v4, "AFUERA", clienteMensual);
            manager.registrarIngreso(regMensual);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        // e. Buscar por ID y mostrar importe actual[cite: 2]
        System.out.println("\n[Prueba E] Buscar registro por ID (102) y consultar importe:");
        RegistroIngresoSalida regEncontrado = manager.obtenerRegistro(102);
        if (regEncontrado != null) {
            System.out.println("ID 102 | Patente: " + regEncontrado.getVehiculo().getPatente() 
                    + " | Importe actual: $" + regEncontrado.obtenerImporte());
        }
        // f. Salida de vehículo por hora[cite: 2]
        System.out.println("\n[Prueba F] Registrar salida de vehículo por hora (ID 101):");
        try {
            RegistroIngresoSalida regSalidaHora = manager.obtenerRegistro(101);
            if (regSalidaHora != null) {
                Double importe = manager.registrarSalida(regSalidaHora);
                System.out.println("Salida registrada | Patente: " + regSalidaHora.getVehiculo().getPatente() 
                        + " | Importe final: $" + importe + " | Estado actual: " + regSalidaHora.getEstado());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        // g. Salida de cliente mensual[cite: 2]
        System.out.println("\n[Prueba G] Registrar salida de vehículo cliente mensual (ID 105):");
        try {
            RegistroIngresoSalida regSalidaMensual = manager.obtenerRegistro(105);
            if (regSalidaMensual != null) {
                Double importe = manager.registrarSalida(regSalidaMensual);
                System.out.println("Salida registrada | Patente: " + regSalidaMensual.getVehiculo().getPatente() 
                        + " | Importe cobrado: $" + importe + " | Estado actual: " + regSalidaMensual.getEstado());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}