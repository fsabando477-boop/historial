package com.fsabando.historial.estructura;

import java.util.NoSuchElementException;

/**
 * Pila (LIFO) generica implementada a mano con nodos enlazados.
 *
 * <p>No se apoya en {@code java.util.Stack} ni en {@code Deque}: la lista
 * enlazada se construye con la clase interna {@link Nodo}. El tope es el nodo
 * mas reciente y las operaciones {@link #apilar(Object)}, {@link #desapilar()}
 * y {@link #cima()} trabajan siempre sobre el, por lo que son O(1).</p>
 *
 * @param <T> tipo de los elementos almacenados
 */
public class Pila<T> {

    /** Nodo simple de una lista enlazada. Privado: solo lo usa la pila. */
    private static class Nodo<T> {
        final T dato;
        Nodo<T> siguiente;

        Nodo(T dato, Nodo<T> siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }
    }

    private Nodo<T> tope;
    private int cantidad;

    /** Agrega un elemento en el tope. */
    public void apilar(T dato) {
        if (dato == null) {
            throw new IllegalArgumentException("No se admite apilar null");
        }
        tope = new Nodo<>(dato, tope);
        cantidad++;
    }

    /** Elimina y devuelve el elemento del tope. */
    public T desapilar() {
        if (tope == null) {
            throw new NoSuchElementException("La pila esta vacia");
        }
        T dato = tope.dato;
        tope = tope.siguiente;
        cantidad--;
        return dato;
    }

    /** Consulta el elemento del tope sin retirarlo. */
    public T cima() {
        if (tope == null) {
            throw new NoSuchElementException("La pila esta vacia");
        }
        return tope.dato;
    }

    /** Indica si la pila no tiene elementos. */
    public boolean estaVacia() {
        return tope == null;
    }

    /** Cantidad de elementos almacenados. */
    public int cantidad() {
        return cantidad;
    }

    /** Elimina todos los elementos. */
    public void vaciar() {
        tope = null;
        cantidad = 0;
    }
}
