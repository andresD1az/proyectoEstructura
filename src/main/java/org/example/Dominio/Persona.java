package org.example.Dominio;

public class Persona {
    int cedula;
    String Nombres;
    String Apellidos;
    String ubicacionNacimiento;
    String UbicacionRecidencia;
    int edad;
    boolean funcionarioPublico;
    String trabajo;
    int numeroHijos;

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public Persona() {}

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

    public String getUbicacionNacimiento() {
        return ubicacionNacimiento;
    }

    public void setUbicacionNacimiento(String ubicacionNacimiento) {
        this.ubicacionNacimiento = ubicacionNacimiento;
    }

    public String getUbicacionRecidencia() {
        return UbicacionRecidencia;
    }

    public void setUbicacionRecidencia(String ubicacionRecidencia) {
        UbicacionRecidencia = ubicacionRecidencia;
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
