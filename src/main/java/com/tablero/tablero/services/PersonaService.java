package com.tablero.tablero.services;

import com.tablero.tablero.domains.Persona;

public interface PersonaService {
    Iterable <Persona> findAll();
    Persona crearPersona(Persona persona);
}
