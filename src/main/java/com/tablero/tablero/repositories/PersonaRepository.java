package com.tablero.tablero.repositories;

import com.tablero.tablero.domains.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonaRepository extends CrudRepository<Persona, Long> {
    List<Persona> findByTipo(int tipo);
}
