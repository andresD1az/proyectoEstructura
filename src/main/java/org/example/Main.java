package org.example;

import org.example.Dominio.Cotizante;
import org.example.Dominio.CotizanteNegro;
import org.example.Dominio.Persona;
import org.example.Dominio.Publico;
import org.example.Model.AprovarCotizantes;
import org.example.Model.Carpetas;
import org.example.Util.csv.csv;

import java.text.SimpleDateFormat;
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
////
//        // Formatear la fecha (opcional)
//        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy_MM_dd");
//        String fechaFormateada = fechaDiaAnterior.format(formato);
//        System.out.println("La fecha del día anterior es: " + fechaFormateada);
//        Carpetas carpetas = new Carpetas();
//        String nombre="SolicitudesProcesadas_";
//        String fecha= new SimpleDateFormat("yyyy_MM_dd").format(new Date());
//        String nombreArchivo = nombre+ fecha;
//        if (carpetas.comprimirCarpeta()){
//            if (carpetas.eliminarCarpeta()){
//                String carpeta = carpetas.crearCarpeta();
//                csv.crearArchivoCSVVacioEnCarpeta(carpeta,nombreArchivo);
//            }
//        }else{
//            String carpeta = carpetas.crearCarpeta();
//            csv.crearArchivoCSVVacioEnCarpeta(carpeta,nombreArchivo);
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
//        String rutainicio= csv.obtenerArchivoConDatos("src/main/java/org/example/archivos/SolicitudesEntrantes");
//        String rutafinal= csv.obtenerArchivoConDatos("src/main/java/org/example/archivos/SolicitudesEnProceso");
//
//        for (int i=1; i<=5; i++){
//            Persona p= csv.leerUnObjeto(rutainicio);
//            if (csv.agregarObjeto(rutafinal,p)){
//                csv.eliminarPrimeraLinea(rutainicio);
//            }
//        }
        procesoAprovacion();
    }
    public static void procesoAprovacion(){
        String rutaEnProceso= csv.obtenerArchivoConDatos("src/main/java/org/example/archivos/SolicitudesEnProceso");
        String ubicacion = "src/main/java/org/example/archivos/";
        String nombre="SolicitudesProcesadas_";
        String fechaActual = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String nombreCarpeta = nombre+ fechaActual;
        String rutaAprobado = csv.obtenerArchivoConDatos(ubicacion+nombreCarpeta);
        Persona cotizante= csv.leerUnObjeto(rutaEnProceso);
        String mensaje="";
        if (AprovarCotizantes.procesocotizante((Cotizante) cotizante)){
            if (cotizante.isFuncionarioPublico()){
                if (AprovarCotizantes.procesoCotizantePublico((Publico) cotizante)){
                    csv.agregarObjeto(rutaAprobado,cotizante);
                    mensaje = "Se Aprobo el traslado del cotizante publico:";
                }
                else {
                    mensaje = "No se aprobo el traslado del cotizante publico:";
                }
            }else{
                if (AprovarCotizantes.procesocotizante((Cotizante) cotizante)){
                    csv.agregarObjeto(rutaAprobado,cotizante);
                    mensaje = "Se Aprobo el traslado del cotizante:";
                }else {
                    mensaje = "No se aprobo el traslado del cotizante publico:";
                }
            }
        }else {
            csv.eliminarPrimeraLinea(rutaEnProceso);
            mensaje = "No se aprobo el traslado del cotizante publico:";
        }
        System.out.println(mensaje +": " +cotizante.toString());
    }
}
