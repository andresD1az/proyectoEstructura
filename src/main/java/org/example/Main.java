package org.example;

import org.example.Dominio.CotizanteNegro;
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
        //String ruta = csv.obtenerYEliminarArchivoVacio("src/main/java/org/example/archivos/SolicitudesEntrantes");
        //Persona p = csv.leerUnObjeto(ruta);
//        System.out.println(p.toString());
//        csv.eliminarPrimeraLinea("src/main/java/org/example/archivos/SolicitudesEntrantes/cotizantes pendientes.csv");
            //Carpetas.crearCarpeta();
            //csv.agregarObjeto("src/main/java/org/example/archivos/SolicitudesProcesadas_2024_11_20/SolicitudesProcesadas_2024_11_20",p);
//        CotizanteNegro cotizanteNegro=new CotizanteNegro(p,"lol");
//        System.out.println(cotizanteNegro.toString());
        System.out.println("Ejecutando proceso cada hora: " + new Date());
        String rutainicio= csv.obtenerArchivoConDatos("src/main/java/org/example/archivos/SolicitudesEntrantes");
        String rutafinal= csv.obtenerArchivoConDatos("src/main/java/org/example/archivos/SolicitudesEnProceso");
        for (int i=1; i<=5; i++){
            Persona p= csv.leerUnObjeto(rutainicio);
            if (csv.agregarObjeto(rutafinal,p)){
                csv.eliminarPrimeraLinea(rutainicio);
            }
        }
    }
}
