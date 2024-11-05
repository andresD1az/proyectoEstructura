package org.example.Util;

import org.example.Dominio.nodo;

import java.util.Collection;
import java.util.Iterator;

public class ListaEnlazada<tipoDato> {
    int tamaño;
    private nodo<tipoDato> inicial;

    public ListaEnlazada() {
        tamaño=0;
        inicial=null;
    }

    public void add(tipoDato dato){
        nodo<tipoDato> nuevo = new nodo(dato);
        if(inicial==null){
            this.inicial = nuevo;
        }else {
            nodo<tipoDato> puntero = this.inicial;
            while (puntero.getNodoSiguiente() != null) {
                puntero = puntero.getNodoSiguiente();
            }
            puntero.setNodoSiguiente(nuevo);
        }
        tamaño++;
    }
    public void add(int pos, tipoDato dato){
        nodo<tipoDato> nuevo = new nodo(dato);
        if (tamaño > pos){
            nodo<tipoDato> puntero = this.inicial;
            for (int i = 0; i < pos; i++) {
                puntero = puntero.getNodoSiguiente();
            }
            nuevo.setNodoSiguiente(puntero.getNodoSiguiente());
            puntero.setNodoSiguiente(nuevo);
        }
    }
    public void addll(Collection<tipoDato> list){
        for (tipoDato item: list) {
            nodo<tipoDato> nuevo = new nodo<>(item);
            if(inicial==null){
                this.inicial = nuevo;
            }else{
                nodo<tipoDato> puntero = this.inicial;
                while (puntero.getNodoSiguiente() != null) {
                    puntero = puntero.getNodoSiguiente();
                }
                puntero.setNodoSiguiente(nuevo);
            }
            tamaño++;
        }
    }
    public void delete() {
        if (inicial != null) {
            if (inicial.getNodoSiguiente() == null) {
                // Si solo hay un nodo, lo eliminamos estableciendo inicial en null
                inicial = null;
            } else {
                nodo<tipoDato> puntero = this.inicial;
                while (puntero.getNodoSiguiente().getNodoSiguiente() != null) {
                    puntero = puntero.getNodoSiguiente();
                }
                // Eliminar el último nodo
                puntero.setNodoSiguiente(null);
            }
            tamaño--;
        }
    }
    public void delete(int pos){
        if (pos < tamaño || pos > tamaño){
            System.out.println("No se puede eliminar el elemento");
        }
        if (pos ==0){
            inicial= inicial.getNodoSiguiente();
        }
        if (tamaño > pos) {
            nodo<tipoDato> puntero = this.inicial;
            for (int i = 0; i < pos; i++) {
                puntero = puntero.getNodoSiguiente();
            }
            puntero.setNodoSiguiente(puntero.getNodoSiguiente().getNodoSiguiente());
            tamaño--;
        }
    }
    public tipoDato get(int pos){
        tipoDato dato = null;
        nodo<tipoDato> puntero = this.inicial;
        for (int i = 0; i < pos; i++) {
            puntero = puntero.getNodoSiguiente();
        }
        dato = puntero.getDato();
        return dato;
    }
    public int size(){
        return tamaño;
    }
    public void reverse(){
        nodo<tipoDato> anterior = null;
        nodo<tipoDato> actual= inicial;
        nodo<tipoDato> sigiente = null;
        while (actual.getNodoSiguiente()!= null) {
            sigiente = actual.getNodoSiguiente();
            anterior = actual;
            actual.setNodoSiguiente(anterior);
            anterior = actual;
            actual = sigiente;
        }
        inicial =anterior;
    }
    public void set(int pos,tipoDato dato){
        if (pos < tamaño || pos > tamaño){
            System.out.println("No se puede actualizar el elemento");
        }
        if (pos ==0){
            inicial.setDato(dato);
        }
        if (tamaño > pos) {
            nodo<tipoDato> puntero = this.inicial;
            for (int i = 0; i < pos; i++) {
                puntero = puntero.getNodoSiguiente();
            }
            puntero.setDato(dato);
        }
    }

}
