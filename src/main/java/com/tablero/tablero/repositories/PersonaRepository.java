package com.tablero.tablero.repositories;

import com.tablero.tablero.domains.Persona;
import org.springframework.data.repository.CrudRepository;

public interface PersonaRepository extends CrudRepository<Persona, Long> {
}
