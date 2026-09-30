package com.fsabando.historial;

import com.fsabando.historial.modelo.Accion;
import com.fsabando.historial.repositorio.HistorialRepository;
import com.fsabando.historial.repositorio.HistorialRepositoryPila;

/**
 * Demostracion mininma del historial de acciones con pila + repository.
 */
public class Main {

    public static void main(String[] args) {
        HistorialRepository historial = new HistorialRepositoryPila();

        historial.registrar(new Accion(Accion.Tipo.AGREGAR, "Producto P001 - Cuaderno"));
        historial.registrar(new Accion(Accion.Tipo.AGREGAR, "Producto P002 - Boligrafo"));
        historial.registrar(new Accion(Accion.Tipo.EDITAR, "P002 -> precio 0.85"));
        historial.registrar(new Accion(Accion.Tipo.ELIMINAR, "Producto P001"));

        System.out.println("Historial actual (" + historial.cantidad() + " acciones):");
        System.out.println("  Ultima: " + historial.ultimaAccion());
        System.out.println();

        System.out.println("Deshaciendo hasta vaciar el historial...");
        while (historial.hayHistorial()) {
            Accion revertida = historial.deshacer();
            System.out.println("  -> " + revertida);
        }
        System.out.println();
        System.out.println("Historial vacio? " + !historial.hayHistorial());
    }
}
