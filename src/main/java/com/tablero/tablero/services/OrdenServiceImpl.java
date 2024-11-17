package com.tablero.tablero.services;

import com.tablero.tablero.domains.Orden;
import com.tablero.tablero.repositories.OrdenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenServiceImpl implements OrdenService{

    private final OrdenRepository ordenRepository;

    public OrdenServiceImpl(OrdenRepository ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    // Método para cambiar el estado de la orden
    public void cambiarEstado(Long id, int estado) {
        // Buscar la orden por id
        Orden orden = ordenRepository.findById(id).orElse(null);  // Usamos orElse para manejar el caso de que no se encuentre la orden

        if (orden != null) {
            // Actualizar el estado de la orden
            orden.setEstado(estado);
            // Guardar la entidad orden actualizada
            ordenRepository.save(orden);
        } else {
            // Manejar el caso en que la orden no existe
            System.out.println("Orden no encontrada");
        }
    }

    @Override
    public Iterable<Orden> findAll() {
        return ordenRepository.findAll();
    }

    @Override
    public Optional<Orden> findById(Long id) {
        return ordenRepository.findById(id);
    }

    @Override
    public List<Orden> findByClienteId(Long id) {
        return ordenRepository.findByClienteId(id);
    }

    @Override
    public List<Orden> findByEncargadoId(Long id) {
        return ordenRepository.findByEncargadoId(id);
    }

    @Override
    public Orden crearOrden(Orden orden) {
        return ordenRepository.save(orden);
    }
}
