package com.fsabando.historial.estructura;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias de la {@link Pila}.
 *
 * <p>Cada test cubre exactamente una de las operaciones fundamentales pedidas
 * por la Semana 7 (agregar, eliminar, consultar siguiente, verificar si esta
 * vacia y consultar cantidad) o un caso limite que el modelo debe rechazar.</p>
 */
@DisplayName("Pila (LIFO) generica")
class PilaTest {

    @Test
    @DisplayName("Una pila nueva esta vacia y tiene cantidad 0")
    void pilaNuevaEstaVacia() {
        Pila<String> pila = new Pila<>();

        assertTrue(pila.estaVacia());
        assertEquals(0, pila.cantidad());
    }

    @Test
    @DisplayName("apilar agrega y actualiza la cantidad")
    void apilarAumentaCantidadYNoEsVacia() {
        Pila<Integer> pila = new Pila<>();

        pila.apilar(10);
        pila.apilar(20);

        assertFalse(pila.estaVacia());
        assertEquals(2, pila.cantidad());
    }

    @Test
    @DisplayName("cima devuelve el ultimo apilado sin removerlo")
    void cimaDevuelveElUltimoApiladoSinRemover() {
        Pila<String> pila = new Pila<>();
        pila.apilar("A");
        pila.apilar("B");

        assertEquals("B", pila.cima());
        assertEquals(2, pila.cantidad(), "cima no debe modificar la cantidad");
    }

    @Test
    @DisplayName("desapilar devuelve y remueve el elemento del tope (orden LIFO)")
    void desapilarSigueOrdenLifo() {
        Pila<String> pila = new Pila<>();
        pila.apilar("primero");
        pila.apilar("segundo");
        pila.apilar("tercero");

        assertEquals("tercero", pila.desapilar());
        assertEquals("segundo", pila.desapilar());
        assertEquals("primero", pila.desapilar());
        assertTrue(pila.estaVacia());
    }

    @Test
    @DisplayName("desapilar en una pila vacia lanza NoSuchElementException")
    void desapilarEnPilaVaciaLanza() {
        Pila<String> pila = new Pila<>();

        assertThrows(NoSuchElementException.class, pila::desapilar);
    }

    @Test
    @DisplayName("cima en una pila vacia lanza NoSuchElementException")
    void cimaEnPilaVaciaLanza() {
        Pila<String> pila = new Pila<>();

        assertThrows(NoSuchElementException.class, pila::cima);
    }

    @Test
    @DisplayName("apilar null lanza IllegalArgumentException")
    void apilarNullLanza() {
        Pila<String> pila = new Pila<>();

        assertThrows(IllegalArgumentException.class, () -> pila.apilar(null));
        assertTrue(pila.estaVacia(), "una operacion invalida no debe alterar la pila");
    }

    @Test
    @DisplayName("vaciar deja la pila en el estado inicial")
    void vaciarDejaLaPilaEnEstadoInicial() {
        Pila<Integer> pila = new Pila<>();
        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);

        pila.vaciar();

        assertTrue(pila.estaVacia());
        assertEquals(0, pila.cantidad());
    }

    @Test
    @DisplayName("La pila es generica: acepta cualquier tipo declarado")
    void pilaGenericaConDistintosTipos() {
        Pila<Double> numeros = new Pila<>();
        numeros.apilar(3.14);
        numeros.apilar(2.71);

        assertEquals(2.71, numeros.cima());
        assertEquals(2, numeros.cantidad());
    }
}
