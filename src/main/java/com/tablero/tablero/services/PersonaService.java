package com.tablero.tablero.services;

import com.tablero.tablero.domains.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaService {
    Iterable <Persona> findAll();
    List<Persona> findByTipo(int tipo);
    Optional<Persona> findById(Long id);
    Persona crearPersona(Persona persona);
}
