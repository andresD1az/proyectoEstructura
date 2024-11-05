package org.example.Util;

import java.util.Iterator;

public class ListaNegra<tipoDato>  implements Iterable<tipoDato>{
    private int tamaño;
    private nodo<tipoDato> inicial;
    private nodo<tipoDato> datofinal;



    public class nodo<tipoDato> {
        private tipoDato dato;
        private nodo<tipoDato> next;

        public nodo(tipoDato dato) {
            this.dato = dato;
            this.next = null;
        }

        public void setDato(tipoDato dato) {
            this.dato = dato;
        }

        public void setNext(nodo<tipoDato> next) {
            this.next = next;
        }

        public tipoDato getDato() {
            return dato;
        }

        public nodo<tipoDato> getNext() {
            return next;
        }
    }

    public ListaNegra() {
        this.inicial = null;
        this.datofinal = null;
        this.tamaño=0;
    }

    // Método para añadir un nodo al final de la lista
    public void add(tipoDato dato) {
        nodo<tipoDato> nodo1 = new nodo<>(dato);
        if (inicial == null) {
            this.inicial = nodo1;
        } else {
            //metodo 1
//         nodo<tipoDato> nodo2 = new nodo(nodo1);
//         nodo2.setNext(inicial);
//         inicial=nodo2;

            //metodo2

            nodo<tipoDato> puntero = inicial; // Inicia desde el nodo inicial
            while (puntero.getNext() != null) {
                puntero = puntero.getNext(); // Recorre hasta el último nodo
            }
            puntero.setNext(nodo1); // Añade el nuevo nodo al final
        }
        tamaño++;
    }

    // Método para obtener y mostrar el dato en la posición 'fin'
    public tipoDato get(int fin) {
        int posicion = 0;
        tipoDato info=null;
        nodo<tipoDato> puntero = inicial; // Empieza desde el nodo inicial
        while (posicion < fin && puntero != null) {
            puntero = puntero.getNext(); // Avanza al siguiente nodo
            posicion++;
        }
        if (puntero != null) {
            System.out.println(puntero.getDato()); // Imprime el dato en la posición 'fin'
        } else {
            System.out.println("Índice fuera de rango.");
        }
        return puntero.getDato();
    }
    public void delete(int poscion){
        nodo<tipoDato> puntero = inicial;
        nodo<tipoDato> punteroanterior = inicial;
        for (int i = 1; i < poscion-1; i++) {
            punteroanterior=puntero;
            puntero = puntero.getNext();
        }
        punteroanterior.setNext(puntero.getNext().getNext());
        tamaño--;
    }
    public int size(){
        return tamaño;
    }
    public void reverse(){
        nodo<tipoDato> anterior = null;
        nodo<tipoDato> actual= inicial;
        nodo<tipoDato> sigiente = null;
        while (actual.getNext() != null) {
            sigiente = actual.getNext();
            anterior = actual;
            actual.setNext(anterior);
            anterior = actual;
            actual = sigiente;
        }
        inicial =anterior;

    }
    @Override
    public Iterator<tipoDato> iterator() {
        return null;//new iteratorListaEnlazada<>(this.inicial);
    }

}
