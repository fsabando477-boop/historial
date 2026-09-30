package com.fsabando.historial.repositorio;

import com.fsabando.historial.estructura.Pila;
import com.fsabando.historial.modelo.Accion;

import java.util.Objects;

/**
 * Implementacion del {@link HistorialRepository} respaldada por una
 * {@link Pila} manual de {@link Accion}.
 *
 * <p>La eleccion de la pila no es arbitraria: la ultima accion realizada es
 * la primera que se debe deshacer, que es exactamente el orden LIFO.</p>
 */
public class HistorialRepositoryPila implements HistorialRepository {

    private final Pila<Accion> pila = new Pila<>();

    @Override
    public void registrar(Accion accion) {
        pila.apilar(Objects.requireNonNull(accion, "accion es obligatoria"));
    }

    @Override
    public Accion deshacer() {
        return pila.desapilar();
    }

    @Override
    public Accion ultimaAccion() {
        return pila.cima();
    }

    @Override
    public boolean hayHistorial() {
        return !pila.estaVacia();
    }

    @Override
    public int cantidad() {
        return pila.cantidad();
    }

    @Override
    public void limpiar() {
        pila.vaciar();
    }
}
