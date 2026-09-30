package com.fsabando.historial.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Accion realizada por el usuario sobre el catalogo.
 *
 * <p>Inmutable: sus tres atributos son {@code final} y no expone setters.
 * El registro del momento se hace en el constructor, no lo suministra quien
 * crea la accion, para que dos acciones "identicas" nunca colisionen.</p>
 */
public final class Accion {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("HH:mm:ss");

    public enum Tipo { AGREGAR, EDITAR, ELIMINAR }

    private final Tipo tipo;
    private final String descripcion;
    private final LocalDateTime momento;

    public Accion(Tipo tipo, String descripcion) {
        this.tipo = Objects.requireNonNull(tipo, "tipo es obligatorio");
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("descripcion es obligatoria");
        }
        this.descripcion = descripcion.trim();
        this.momento = LocalDateTime.now();
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getMomento() {
        return momento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Accion otra)) return false;
        return tipo == otra.tipo
                && descripcion.equals(otra.descripcion)
                && momento.equals(otra.momento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, descripcion, momento);
    }

    @Override
    public String toString() {
        return "[" + momento.format(FORMATO) + "] " + tipo + " - " + descripcion;
    }
}
