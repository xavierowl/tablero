package com.tablero.tablero.services;


import com.tablero.tablero.domains.Orden;

import java.util.List;
import java.util.Optional;

public interface OrdenService {
    Iterable<Orden> findAll();
    Optional<Orden> findById(Long id);
    List<Orden> findByClienteId(Long id);
    List<Orden> findByEncargadoId(Long id);
    void cambiarEstado(Long id, int estado);
    Orden crearOrden(Orden orden);
}
