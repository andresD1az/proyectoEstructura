package org.example.Dominio;

import java.util.ArrayList;

public class Publico extends Cotizante{
    boolean condecoraciones;
    String intitucionPublica;
    ArrayList<hijos> listahijos;
    boolean obserDisiplinaria; //observacion diciplinaria

    public Publico() {
        super();
    }

    public Publico(String nombres, String apellidos, String ubicacionNacimiento, String ubicacionRecidencia, int edad, boolean funcionarioPublico, String trabajo, int numeroHijos, boolean embargado, boolean prepencionado, String empresaPenciones, double semanasCotizadas, boolean condecoraciones, String intitucionPublica, boolean obserDisiplinaria) {
        super();
        this.condecoraciones = condecoraciones;
        this.intitucionPublica = intitucionPublica;
        this.listahijos = null;
        this.obserDisiplinaria = obserDisiplinaria;
    }

    public boolean isCondecoraciones() {
        return condecoraciones;
    }

    public void setCondecoraciones(boolean condecoraciones) {
        this.condecoraciones = condecoraciones;
    }

    public String getIntitucionPublica() {
        return intitucionPublica;
    }

    public void setIntitucionPublica(String intitucionPublica) {
        this.intitucionPublica = intitucionPublica;
    }

    public boolean isObserDisiplinaria() {
        return obserDisiplinaria;
    }

    public void setObserDisiplinaria(boolean obserDisiplinaria) {
        this.obserDisiplinaria = obserDisiplinaria;
    }

    public ArrayList<hijos> getListahijos() {
        return listahijos;
    }

    public void setListahijos(ArrayList<hijos> listahijos) {
        this.listahijos = listahijos;
    }
}
