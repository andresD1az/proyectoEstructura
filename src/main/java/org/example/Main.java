package org.example;

import org.example.Dominio.Persona;
import org.example.Model.Carpetas;
import org.example.Util.csv.csv;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
//        System.out.println("nuevo aaa  Estructura project");
//        // Obtener la fecha actual
//        LocalDate fechaActual = LocalDate.now();
//
//        // Restar un día para obtener la fecha del día anterior
//        LocalDate fechaDiaAnterior = fechaActual.minusDays(1);
//
//        // Formatear la fecha (opcional)
//        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy_MM_dd");
//        String fechaFormateada = fechaDiaAnterior.format(formato);
//
//        System.out.println("La fecha del día anterior es: " + fechaFormateada);
//        Carpetas carpetas = new Carpetas();
//        if (carpetas.comprimirCarpeta()){
//            System.out.println("El carpeta se ha comprimido");
//            carpetas.crearCarpeta();
//        }
//        Persona p = csv.leerUnObjeto("src/main/java/org/example/archivos/SolicitudesEntrantes/cotizantes pendientes.csv");
//        System.out.println(p.toString());
//        csv.eliminarPrimeraLinea("src/main/java/org/example/archivos/SolicitudesEntrantes/cotizantes pendientes.csv");
            Carpetas.crearCarpeta();
    }
}
