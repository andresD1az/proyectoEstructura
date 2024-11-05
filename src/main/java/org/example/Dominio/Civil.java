package org.example.Dominio;

import java.util.ArrayList;

public class Civil {
    boolean condecoraciones;
    String intitucionPublica;
    ArrayList<hijos> listahijos;

    public Civil(boolean condecoraciones, String intitucionPublica) {
        this.condecoraciones = condecoraciones;
        this.intitucionPublica = intitucionPublica;
        this.listahijos = null;
    }

    public boolean isCondecoraciones() {
        return condecoraciones;
    }

    public void setCondecoraciones(boolean condecoraciones) {
        this.condecoraciones = condecoraciones;
    }

    public String getIntitucionPublica() {
        return intitucionPublica;
    }

    public void setIntitucionPublica(String intitucionPublica) {
        this.intitucionPublica = intitucionPublica;
    }

    public ArrayList<hijos> getListahijos() {
        return listahijos;
    }

    public void setListahijos(ArrayList<hijos> listahijos) {
        this.listahijos = listahijos;
    }
}
