package org.example.Model;

import org.example.Dominio.Cotizante;
import org.example.Dominio.CotizanteNegro;
import org.example.Dominio.Publico;
import org.example.Dominio.hijos;
import org.example.Util.ListaEnlazada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class AprovarCotizantes {

    public boolean procesocotizante(Cotizante cotizante){
        ArrayList<CotizanteNegro> listanegra=new ArrayList<>();
        String cotizanteNegro=buscarlolistaNegra(cotizante.getCedula());
        boolean centinela=true;
        if (cotizanteNegro != null){
            if (haPasadoMasDeSeisMeses(cotizanteNegro)){
                centinela=true;
            }else {
                centinela=false;
            }
        }
        if (cotizante.isPrepencionado()){
            centinela=false;
        }
        return centinela;
    }

    public static boolean procesoCotizantePublico(Publico cotizante){
        boolean proceso=true;
        if(cotizante.getIntitucionPublica().equals("Armada")){
            if (cotizante.isCondecoraciones()){
                return true;
            }else {
                return procesoCotizanteCivil(cotizante);
            }
        }else if (cotizante.getIntitucionPublica().equals("Inpec")){
            if (cotizante.getNumeroHijos() ==0){
                if (cotizante.isCondecoraciones()){
                    return true;
                }else {
                    return procesoCotizanteCivil(cotizante);
                }
            }else {
                if (validacionHijos(cotizante.getTrabajo(), cotizante.getListahijos())){
                    return true;
                }else {
                    return procesoCotizanteCivil(cotizante);
                }
            }
        }else if (cotizante.getIntitucionPublica().equals("Policia")){
            if (validacionHijos(cotizante.getTrabajo(), cotizante.getListahijos())){
                return true;
            }else {
                return procesoCotizanteCivil(cotizante);
            }
        }else if (cotizante.getIntitucionPublica().equals("MinSalud") ||
                cotizante.getIntitucionPublica().equals("MinInterior")){
            if (cotizante.isObserDisiplinaria()){
                //proceso de listado a la lista negra
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
                cotizante.getUbicacionRecidencia().equals("Bogota")||
                cotizante.getUbicacionRecidencia().equals("Medellin")||
                cotizante.getUbicacionRecidencia().equals("Cali")||
                cotizante.getUbicacionRecidencia().equals("..tan")
        ){
            proceso=false;
        }else{
            if (cotizante.getEdad() >= 35){
                if (cotizante.getEmpresaPenciones().equals("Porvenir")){
                    if (cotizante.getSemanasCotizadas() <800 ){
                        proceso=true;
                    }else {
                        proceso=false;
                    }
                }else if (cotizante.getEmpresaPenciones().equals("proteccion")){
                    if (cotizante.getSemanasCotizadas() <590 ){
                        proceso=true;
                    }else {
                        proceso=false;
                    }
                }else if (cotizante.getEmpresaPenciones().equals("colfondos")){
                    if (cotizante.getSemanasCotizadas() < 300){
                        proceso=true;
                    }else {
                        proceso=false;
                    }
                }else if (cotizante.getEmpresaPenciones().equals("old mutual")){
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

    public static boolean validacionHijos(String cargo, ArrayList<hijos> listahijos){
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