package org.example.Dominio;

public class Cotizante {
    private String id;
    private String nombre;
    private double ahorro;
    private boolean embargado;

    public Cotizante(String id, String nombre, double ahorro) {
        this.id = id;
        this.nombre = nombre;
        this.ahorro = ahorro;
        this.embargado = false;
    }

    // Getters y setters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public double getAhorro() { return ahorro; }
    public boolean isEmbargado() { return embargado; }

    public void embargarAhorro() {
        this.embargado = true;
        System.out.println("Ahorro embargado para el cotizante: " + nombre);
    }

    @Override
    public String toString() {
        return "Cotizante [ID=" + id + ", Nombre=" + nombre + ", Ahorro=" + ahorro + ", Embargado=" + embargado + "]";
    }
}

