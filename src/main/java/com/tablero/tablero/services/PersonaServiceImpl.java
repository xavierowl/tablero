package com.tablero.tablero.services;

import com.tablero.tablero.domains.Persona;
import com.tablero.tablero.repositories.PersonaRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Iterable<Persona> findAll() {
        return personaRepository.findAll();
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return personaRepository.save(persona);
    }
}
