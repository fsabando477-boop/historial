package com.fsabando.historial.repositorio;

import com.fsabando.historial.modelo.Accion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias del {@link HistorialRepositoryPila}.
 *
 * <p>Los tests trabajan contra la interfaz {@link HistorialRepository}, no
 * contra la implementacion, para reforzar la idea del patron Repository: el
 * codigo cliente no depende de la pila concreta.</p>
 */
@DisplayName("HistorialRepository sobre Pila")
class HistorialRepositoryPilaTest {

    private HistorialRepository historial;

    @BeforeEach
    void nuevoHistorial() {
        historial = new HistorialRepositoryPila();
    }

    @Test
    @DisplayName("Un historial recien creado no tiene acciones")
    void historialNuevoNoTieneAcciones() {
        assertFalse(historial.hayHistorial());
        assertEquals(0, historial.cantidad());
    }

    @Test
    @DisplayName("registrar agrega la accion y aumenta la cantidad")
    void registrarAumentaLaCantidad() {
        historial.registrar(new Accion(Accion.Tipo.AGREGAR, "P001"));

        assertTrue(historial.hayHistorial());
        assertEquals(1, historial.cantidad());
    }

    @Test
    @DisplayName("deshacer retira y devuelve la ultima accion registrada")
    void deshacerRetornaLaUltimaAccion() {
        Accion primera = new Accion(Accion.Tipo.AGREGAR, "P001");
        Accion segunda = new Accion(Accion.Tipo.EDITAR, "P001 precio");
        historial.registrar(primera);
        historial.registrar(segunda);

        Accion revertida = historial.deshacer();

        assertEquals(segunda, revertida);
        assertEquals(1, historial.cantidad(), "deshacer debe reducir la cantidad");
    }

    @Test
    @DisplayName("ultimaAccion consulta el tope sin retirarlo")
    void ultimaAccionNoRemueve() {
        Accion accion = new Accion(Accion.Tipo.ELIMINAR, "P042");
        historial.registrar(accion);

        assertEquals(accion, historial.ultimaAccion());
        assertEquals(1, historial.cantidad());
    }

    @Test
    @DisplayName("deshacer en un historial vacio lanza NoSuchElementException")
    void deshacerEnHistorialVacioLanza() {
        assertThrows(NoSuchElementException.class, historial::deshacer);
    }

    @Test
    @DisplayName("ultimaAccion en un historial vacio lanza NoSuchElementException")
    void ultimaAccionEnHistorialVacioLanza() {
        assertThrows(NoSuchElementException.class, historial::ultimaAccion);
    }

    @Test
    @DisplayName("registrar null es rechazado")
    void registrarNullEsRechazado() {
        assertThrows(NullPointerException.class, () -> historial.registrar(null));
    }

    @Test
    @DisplayName("limpiar elimina todo el historial")
    void limpiarBorraTodo() {
        historial.registrar(new Accion(Accion.Tipo.AGREGAR, "A"));
        historial.registrar(new Accion(Accion.Tipo.AGREGAR, "B"));

        historial.limpiar();

        assertFalse(historial.hayHistorial());
        assertEquals(0, historial.cantidad());
    }

    @Test
    @DisplayName("Al deshacer varias veces se aplica el orden LIFO")
    void deshacerRespetaOrdenLifo() {
        Accion a1 = new Accion(Accion.Tipo.AGREGAR, "P001");
        Accion a2 = new Accion(Accion.Tipo.AGREGAR, "P002");
        Accion a3 = new Accion(Accion.Tipo.ELIMINAR, "P001");
        historial.registrar(a1);
        historial.registrar(a2);
        historial.registrar(a3);

        assertEquals(a3, historial.deshacer());
        assertEquals(a2, historial.deshacer());
        assertEquals(a1, historial.deshacer());
        assertFalse(historial.hayHistorial());
    }
}
