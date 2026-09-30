package ar.edu.unju.fi.practico6;

import ar.edu.unju.fi.practico6.model.*;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    private List<RegistroIngresoSalida> registros;

    public Manager() {
        this.registros = new ArrayList<>();
    }

    public boolean validarPatente(String patente) {
        for (RegistroIngresoSalida reg : registros) {
            if (reg.getVehiculo().getPatente().equalsIgnoreCase(patente) && "INGRESADO".equals(reg.getEstado())) {
                return false; // Ya está ingresado
            }
        }
        return true;
    }

    public void registrarIngreso(RegistroIngresoSalida registro) throws Exception {
        // a. Verificar que no esté en la playa[cite: 1]
        if (!validarPatente(registro.getVehiculo().getPatente())) {
            throw new Exception("El vehículo con patente " + registro.getVehiculo().getPatente() + " ya se encuentra en la playa.");
        }

        // b. Verificar horario si es PorHora (no ingresar después de las 21 hs)[cite: 1]
        if (registro instanceof PorHora) {
            // Convertimos la Date a LocalTime para usar .getHour() todo en minúsculas
            LocalTime horaRegistro = registro.getHora().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalTime();
            
            int horaDelDia = horaRegistro.getHour(); 

            if (horaDelDia >= 21 || horaDelDia < 8) {
                throw new Exception("Ingreso rechazado: El horario de ingreso por hora es de 8:00 a 21:00 hs.");
            }
        }

        registro.cambiarEstado("INGRESADO");
        registros.add(registro);
        System.out.println("Ingreso registrado con éxito para patente: " + registro.getVehiculo().getPatente());
    }

    public Double registrarSalida(RegistroIngresoSalida registro) throws Exception {
        if (!"INGRESADO".equals(registro.getEstado())) {
            throw new Exception("El vehículo no se encuentra actualmente dentro del estacionamiento.");
        }

        Double importe = registro.obtenerImporte();
        registro.cambiarEstado("AFUERA");
        return importe;
    }

    public RegistroIngresoSalida obtenerRegistro(Integer id) {
        for (RegistroIngresoSalida reg : registros) {
            if (reg.getId().equals(id)) {
                return reg;
            }
        }
        return null;
    }
}