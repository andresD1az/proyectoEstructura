package org.example.Dominio;

public class Cotizante extends Persona {
    private boolean embargado;
    private boolean prepencionado;
    private String empresaPensiones;
    private double semanasCotizadas;

    public Cotizante() {}
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
    public String getEmpresaPensiones() {
        return empresaPensiones;
    }
    public void setEmpresaPensiones(String empresaPensiones) {
        this.empresaPensiones = empresaPensiones;
    }
    public double getSemanasCotizadas() {
        return semanasCotizadas;
    }
    public void setSemanasCotizadas(double semanasCotizadas) {
        if (semanasCotizadas < 0) throw new IllegalArgumentException("Las semanas cotizadas no pueden ser negativas.");
        this.semanasCotizadas = semanasCotizadas;
    }
}

