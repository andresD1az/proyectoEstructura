package org.example.Dominio;

public class hijos {
    private String nombre;
    private int edad;
    private String trabajo;

    public hijos(String nombre, int edad, String trabajo) {
        if (edad < 0) throw new IllegalArgumentException("La edad no puede ser negativa.");
        this.nombre = nombre;
        this.edad = edad;
        this.trabajo = trabajo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getTrabajo() {
        return trabajo;
    }
    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public String infocsv() {
        return nombre + "/" + edad + "/" + trabajo;
    }
    public static hijos fromCsv(String csv) {
        String[] partes = csv.split("/");
        if (partes.length != 3) {
            throw new IllegalArgumentException("Formato inválido para hijo: " + csv);
        }
        return new hijos(partes[0], Integer.parseInt(partes[1]), partes[2]);
    }
}
