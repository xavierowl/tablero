package com.tablero.tablero.services;

import com.tablero.tablero.domains.Orden;
import com.tablero.tablero.repositories.OrdenRepository;
import org.springframework.stereotype.Service;

@Service
public class OrdenServiceImpl implements OrdenService{

    private final OrdenRepository ordenRepository;

    public OrdenServiceImpl(OrdenRepository ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    @Override
    public Iterable<Orden> findAll() {
        return null;
    }

    @Override
    public Orden crearOrden(Orden orden) {
        return ordenRepository.save(orden);
    }
}
