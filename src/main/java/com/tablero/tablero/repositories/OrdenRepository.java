package com.tablero.tablero.repositories;

import com.tablero.tablero.domains.Orden;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdenRepository extends CrudRepository<Orden, Long> {
    List<Orden> findByClienteId(Long encargadoId);
    List<Orden> findByEncargadoId(Long encargadoId);
}
