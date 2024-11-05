package org.example.Dominio;

public class nodo<tipodato> {
    tipodato dato;
    nodo<tipodato> nodoSiguiente;

    public nodo(tipodato dato) {
        this.dato = dato;
        this.nodoSiguiente = null;
    }

    public tipodato getDato() {
        return dato;
    }

    public void setDato(tipodato dato) {
        this.dato = dato;
    }

    public nodo<tipodato> getNodoSiguiente() {
        return nodoSiguiente;
    }

    public void setNodoSiguiente(nodo<tipodato> nodoSiguiente) {
        this.nodoSiguiente = nodoSiguiente;
    }
}
