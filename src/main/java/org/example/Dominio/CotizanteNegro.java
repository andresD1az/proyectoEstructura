package org.example.Dominio;

public class CotizanteNegro {
    Persona persona;
    String fecha;

    public CotizanteNegro(Persona persona, String fecha) {
        this.persona = persona;
        this.fecha = fecha;
    }
    public Persona getPersona() {
        return persona;
    }
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "persona=" + persona +
                ", fecha='" + fecha;
    }
}
