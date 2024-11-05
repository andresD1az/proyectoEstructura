package org.example.Dominio;

public class CotizanteNegro {
    Cotizante cotizante;
    String fecha;

    public CotizanteNegro(Cotizante cotizante, String fecha) {
        this.cotizante = cotizante;
        this.fecha = fecha;
    }

    public Cotizante getCotizante() {
        return cotizante;
    }

    public void setCotizante(Cotizante cotizante) {
        this.cotizante = cotizante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
