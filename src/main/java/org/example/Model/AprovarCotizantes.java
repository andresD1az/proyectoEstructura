package org.example.Model;

import org.example.Dominio.Cotizante;
import org.example.Dominio.CotizanteNegro;
import org.example.Dominio.Publico;
import org.example.Dominio.hijos;
import org.example.Util.csv.CsvCotizantesNegros;
import org.example.Util.csv.csv;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AprovarCotizantes {

<<<<<<< HEAD
    private static String rutanegra = csv.obtenerArchivoConDatos("src/main/java/org/example/archivos/ListaNegra");

    public static boolean procesocotizante(Cotizante cotizante){
        CotizanteNegro cotizanteNegro=buscarlolistaNegra(cotizante.getCedula());
=======
    public boolean procesocotizante(Cotizante cotizante){
        ArrayList<CotizanteNegro> listanegra=new ArrayList<>();
        String cotizanteNegro=buscarlolistaNegra(cotizante.getCedula());
>>>>>>> origin/master
        boolean centinela=true;
        if (cotizanteNegro != null){
            if (haPasadoMasDeSeisMeses(cotizanteNegro)){
                centinela=true;
            }else {
                centinela=false;
            }
        }if (cotizanteNegro == null && cotizante.isEmbargado()){
            String fechaActual = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
            cotizanteNegro =new CotizanteNegro(cotizante,fechaActual);
            CsvCotizantesNegros.agregarObjeto(rutanegra,cotizanteNegro);
            return false;
        }
        if (cotizante.isPrepencionado()){
            centinela=false;
        }
        return centinela;
    }

    public static boolean procesoCotizantePublico(Publico cotizante){
        boolean proceso=true;
        String rutaProceso = csv.obtenerArchivoConDatos("src/main/java/org/example/archivos/SolicitudesEnProceso");
            if(cotizante.getInstitucionPublica().equals("Armada")){
                if (cotizante.isCondecoraciones()){
                    return true;
                }else {
                    return procesoCotizanteCivil(cotizante);
                }
            }else if (cotizante.getInstitucionPublica().equals("Inpec")){
                if (cotizante.getNumeroHijos() ==0){
                    if (cotizante.isCondecoraciones()){
                        return true;
                    }else {
                        return procesoCotizanteCivil(cotizante);
                    }
                }else {
                    if (validacionHijos(cotizante.getTrabajo(), cotizante.getListaHijos())){
                        return true;
                    }else {
                        return procesoCotizanteCivil(cotizante);
                    }
                }
            }else if (cotizante.getInstitucionPublica().equals("Policia")){
                if (validacionHijos(cotizante.getTrabajo(), cotizante.getListaHijos())){
                    return true;
                }else {
                    return procesoCotizanteCivil(cotizante);
                }
            }else if (cotizante.getInstitucionPublica().equals("MinSalud") ||
                    cotizante.getInstitucionPublica().equals("MinInterior")){
                if (cotizante.isObservacionDisciplinaria()){
                    String fechaActual = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
                    CotizanteNegro cotizanteNegro =new CotizanteNegro(cotizante,fechaActual);
                    CsvCotizantesNegros.agregarObjeto(rutanegra,cotizanteNegro);
                }else{
                    return true;
                }
            }
        return proceso;
    }
    public static boolean procesoCotizanteCivil(Cotizante cotizante){
        boolean proceso=true;
        if (cotizante.getUbicacionNacimiento().equals("Bogota")||
                cotizante.getUbicacionNacimiento().equals("Medellin")||
                cotizante.getUbicacionNacimiento().equals("Cali")||
                cotizante.getUbicacionNacimiento().equals("..tan")||
                cotizante.getUbicacionResidencia().equals("Bogota")||
                cotizante.getUbicacionResidencia().equals("Medellin")||
                cotizante.getUbicacionResidencia().equals("Cali")||
                cotizante.getUbicacionResidencia().equals("..tan")
        ){
            proceso=false;
        }else{
            if (cotizante.getEdad() >= 35){
                if (cotizante.getEmpresaPensiones().equals("Porvenir")){
                    if (cotizante.getSemanasCotizadas() <800 ){
                        proceso=true;
                    }else {
                        proceso=false;
                    }
                }else if (cotizante.getEmpresaPensiones().equals("proteccion")){
                    if (cotizante.getSemanasCotizadas() <590 ){
                        proceso=true;
                    }else {
                        proceso=false;
                    }
                }else if (cotizante.getEmpresaPensiones().equals("colfondos")){
                    if (cotizante.getSemanasCotizadas() < 300){
                        proceso=true;
                    }else {
                        proceso=false;
                    }
                }else if (cotizante.getEmpresaPensiones().equals("old mutual")){
                    if (cotizante.getSemanasCotizadas() < 100){
                        proceso=true;
                    }else {
                        proceso=false;
                    }
                }else {
                    proceso=true;
                }
            }else {
                proceso=false;
            }
        }
        return proceso;
    }

    // Método para buscar la fecha en la lista negra con base en la cédula
    public static String buscarlolistaNegra(int cedula) {
        ArrayList<CotizanteNegro> listaNegra = new ArrayList<>(); // Supón que esta lista se inicializa con datos válidos.

        // Recorrer la lista para buscar la cédula
        for (CotizanteNegro cotizante : listaNegra) {
            if (cotizante.getCotizante().getCedula() == cedula) {
                // Retornar la fecha si se encuentra el cotizante
                return cotizante.getFecha();
            }
        }

        // Retornar null si no se encuentra el cotizante
        return null;
    }

    public static boolean validacionHijos(String cargo, List<hijos> listahijos){
        boolean validacion=false;
        for (hijos hijo:listahijos){
            if (cargo.equals("Policia") && hijo.getEdad()>=18){
                return true;
            }else if (cargo.equals("Policia") && hijo.getEdad()<=18){
                validacion=false;
            }
            if (cargo.equals(hijo.getTrabajo())){
                return true;
            }
        }
        return validacion;
    }

    // Método para validar si han pasado más de seis meses desde una fecha dada
    public static boolean haPasadoMasDeSeisMeses(String fechaStr) {
        if (fechaStr == null || fechaStr.isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede ser nula o vacía");
        }

        // Definir el formato de la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");

        // Convertir la fecha en String a LocalDate
        LocalDate fecha = LocalDate.parse(fechaStr, formatter);

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Calcular la diferencia en meses
        long meses = ChronoUnit.MONTHS.between(fecha, fechaActual);

        // Verificar si han pasado más de 6 meses
        return meses > 6;
    }
}
