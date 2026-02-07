package com.unimet.estructuras;
import com.unimet.clases.PCB;


public class Cola<T> {
    private Nodo<T> pFirst; // Cabeza de la cola
    private Nodo<T> pLast;  // Final de la cola
    private int size;       // Tamaño
    public static final int CRITERIO_PRIORIDAD = 0; // Menor número = Mayor prioridad
    public static final int CRITERIO_SRT = 1;       // Shortest Remaining Time
    public static final int CRITERIO_EDF = 2;       // Earliest Deadline First


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
    

    public void insertarOrdenado(T objeto, int criterio) {
        Nodo<T> nuevo = new Nodo<>(objeto);
        PCB pNuevo = (PCB) objeto; // Casteamos para leer datos del proceso

        // 1. Si está vacía, insertamos normal
        if (isEmpty()) {
            pFirst = nuevo;
            pLast = nuevo;
            size++;
            return;
        }

        // 2. Revisar si va ANTES del primero (Cabeza)
        PCB pCabeza = (PCB) pFirst.getContenido();
        if (esMejor(pNuevo, pCabeza, criterio)) {
            nuevo.setSiguiente(pFirst);
            pFirst = nuevo;
            size++;
            return;
        }

        // 3. Buscar posición en el medio o final
        Nodo<T> actual = pFirst;
        while (actual.getSiguiente() != null) {
            PCB pSiguiente = (PCB) actual.getSiguiente().getContenido();
            
            // Si el nuevo es "mejor" que el siguiente, lo metemos aquí
            if (esMejor(pNuevo, pSiguiente, criterio)) {
                break;
            }
            actual = actual.getSiguiente();
        }

        // Insertar nodo
        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);

        // Si insertamos al final, actualizar pLast
        if (nuevo.getSiguiente() == null) {
            pLast = nuevo;
        }
        size++;
    }

    // Método auxiliar para comparar
    private boolean esMejor(PCB nuevo, PCB existente, int criterio) {
        switch (criterio) {
            case CRITERIO_PRIORIDAD:
                // Asumimos: 1 es más importante que 3
                return nuevo.getPrioridad() < existente.getPrioridad();
            case CRITERIO_SRT:
                // Menor tiempo restante gana
                int restanteNuevo = nuevo.getInstruccionesTotales() - nuevo.getInstruccionesEjecutadas();
                int restanteExistente = existente.getInstruccionesTotales() - existente.getInstruccionesEjecutadas();
                return restanteNuevo < restanteExistente;
            case CRITERIO_EDF:
                // Menor deadline (plazo más cercano) gana
                return nuevo.getDeadline() < existente.getDeadline();
            default:
                return false;
        }
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
    // Método para imprimir todo el contenido de la cola
    @Override
    public String toString() {
        String resultado = "";
        Nodo<T> aux = pFirst;
        while (aux != null) {
            resultado += aux.getContenido().toString() + "\n";
            aux = aux.getSiguiente();
        }
        return resultado;
    }
}