package com.fsabando.historial.repositorio;

import com.fsabando.historial.modelo.Accion;

/**
 * Contrato del repositorio de historial de acciones.
 *
 * <p><b>Patron Repository.</b> Esta interfaz separa el acceso y manejo de los
 * datos (registrar, deshacer, consultar) de la logica principal de la
 * aplicacion. Quien invoque a un {@code HistorialRepository} no sabe si por
 * detras hay una pila en memoria, una base de datos, o un log en disco; solo
 * conoce las operaciones que ofrece este contrato.</p>
 */
public interface HistorialRepository {

    /** Registra una nueva accion en el historial. */
    void registrar(Accion accion);

    /**
     * Retira y devuelve la ultima accion registrada.
     *
     * @throws java.util.NoSuchElementException si el historial esta vacio
     */
    Accion deshacer();

    /**
     * Consulta la ultima accion sin retirarla.
     *
     * @throws java.util.NoSuchElementException si el historial esta vacio
     */
    Accion ultimaAccion();

    /** Indica si hay alguna accion registrada. */
    boolean hayHistorial();

    /** Cantidad total de acciones en el historial. */
    int cantidad();

    /** Elimina todo el historial. */
    void limpiar();
}
