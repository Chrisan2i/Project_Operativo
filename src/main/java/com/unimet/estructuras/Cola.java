package com.unimet.estructuras;

public class Cola<T> {
    private Nodo<T> pFirst; // Cabeza de la cola
    private Nodo<T> pLast;  // Final de la cola
    private int size;       // Tamaño

    public Cola() {
        this.pFirst = null;
        this.pLast = null;
        this.size = 0;
    }

    // Método para agregar al final (Encolar)
    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (isEmpty()) {
            pFirst = nuevo;
            pLast = nuevo;
        } else {
            pLast.setSiguiente(nuevo);
            pLast = nuevo;
        }
        size++;
    }

    // Método para sacar del inicio (Desencolar)
    public T desencolar() {
        if (isEmpty()) {
            return null;
        }
        T dato = pFirst.getContenido();
        pFirst = pFirst.getSiguiente();
        if (pFirst == null) {
            pLast = null;
        }
        size--;
        return dato;
    }
    
    // Ver quién es el primero sin sacarlo
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return pFirst.getContenido();
    }

    public boolean isEmpty() {
        return pFirst == null;
    }

    public int getSize() {
        return size;
    }
}