package org.example.Dominio;

public class Persona {
    private String tipoDocumento;
    private int cedula;
    private String nombres;
    private String apellidos;
    private String ubicacionNacimiento;
    private String ubicacionResidencia;
    private int edad;
    private boolean funcionarioPublico;
    private String trabajo;
    private int numeroHijos;


    public Persona() {}

    public String getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public int getCedula() {
        return cedula;
    }
    public void setCedula(int cedula) {
        if (cedula <= 0) throw new IllegalArgumentException("La cédula debe ser un número positivo.");
        this.cedula = cedula;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getUbicacionNacimiento() {
        return ubicacionNacimiento;
    }
    public void setUbicacionNacimiento(String ubicacionNacimiento) {
        this.ubicacionNacimiento = ubicacionNacimiento;
    }
    public String getUbicacionResidencia() {
        return ubicacionResidencia;
    }
    public void setUbicacionResidencia(String ubicacionResidencia) {
        this.ubicacionResidencia = ubicacionResidencia;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        if (edad < 0) throw new IllegalArgumentException("La edad no puede ser negativa.");
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
        if (numeroHijos < 0) throw new IllegalArgumentException("El número de hijos no puede ser negativo.");
        this.numeroHijos = numeroHijos;
    }
}
