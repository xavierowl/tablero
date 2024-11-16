package com.tablero.tablero.services;


import com.tablero.tablero.domains.Orden;

public interface OrdenService {
    Iterable<Orden> findAll();
    Orden crearOrden(Orden orden);
}
