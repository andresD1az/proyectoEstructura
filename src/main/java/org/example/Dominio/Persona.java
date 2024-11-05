package org.example.Dominio;

public class Persona {
    String Nombres;
    String Apellidos;
    int edad;
    boolean funcionarioPublico;
    String trabajo;
    int numeroHijos;

    public Persona(String nombres, String apellidos, int edad, boolean funcionarioPublico, String trabajo, int numeroHijos) {
        Nombres = nombres;
        Apellidos = apellidos;
        this.edad = edad;
        this.funcionarioPublico = funcionarioPublico;
        this.trabajo = trabajo;
        this.numeroHijos = numeroHijos;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isFuncionarioPublico() {
        return funcionarioPublico;
    }

    public void setFuncionarioPublico(boolean funcionarioPublico) {
        this.funcionarioPublico = funcionarioPublico;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public int getNumeroHijos() {
        return numeroHijos;
    }

    public void setNumeroHijos(int numeroHijos) {
        this.numeroHijos = numeroHijos;
    }
}
