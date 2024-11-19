package org.example.Dominio;


import java.util.ArrayList;
import java.util.List;

public class Publico extends Cotizante{
    private boolean condecoraciones;
    private String institucionPublica;
    private List<hijos> listaHijos = new ArrayList<>();
    private boolean observacionDisciplinaria;

    public Publico() {
        super();
    }
    public Publico(boolean condecoraciones, String institucionPublica, List<hijos> listaHijos, boolean observacionDisciplinaria) {
        this.condecoraciones = condecoraciones;
        this.institucionPublica = institucionPublica;
        this.listaHijos = listaHijos != null ? listaHijos : new ArrayList<>();
        this.observacionDisciplinaria = observacionDisciplinaria;
    }

    public boolean isCondecoraciones() {
        return condecoraciones;
    }
    public void setCondecoraciones(boolean condecoraciones) {
        this.condecoraciones = condecoraciones;
    }
    public String getInstitucionPublica() {
        return institucionPublica;
    }
    public void setInstitucionPublica(String institucionPublica) {
        this.institucionPublica = institucionPublica;
    }
    public List<hijos> getListaHijos() {
        return listaHijos;
    }
    public void setListaHijos(List<hijos> listaHijos) {
        this.listaHijos = listaHijos;
    }
    public boolean isObservacionDisciplinaria() {
        return observacionDisciplinaria;
    }
    public void setObservacionDisciplinaria(boolean observacionDisciplinaria) {
        this.observacionDisciplinaria = observacionDisciplinaria;
    }
}
