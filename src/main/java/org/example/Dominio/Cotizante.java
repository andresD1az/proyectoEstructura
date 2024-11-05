package org.example.Dominio;

public class Cotizante extends Persona{
    private boolean embargado;
    private boolean prepencionado;
    private String empresaPenciones;
    private double semanasCotizadas;

    public Cotizante() {
    }
    public boolean isEmbargado() {
        return embargado;
    }

    public void setEmbargado(boolean embargado) {
        this.embargado = embargado;
    }

    public boolean isPrepencionado() {
        return prepencionado;
    }

    public void setPrepencionado(boolean prepencionado) {
        this.prepencionado = prepencionado;
    }

    public String getEmpresaPenciones() {
        return empresaPenciones;
    }

    public void setEmpresaPenciones(String empresaPenciones) {
        this.empresaPenciones = empresaPenciones;
    }

    public double getSemanasCotizadas() {
        return semanasCotizadas;
    }

    public void setSemanasCotizadas(double semanasCotizadas) {
        this.semanasCotizadas = semanasCotizadas;
    }
}

