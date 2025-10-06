package com.sistema.domain.port.out;

import com.sistema.domain.model.Transaccion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransaccionRepositoryPort {
    Transaccion save(Transaccion transaccion);
    Optional<Transaccion> findById(Long id);
    List<Transaccion> findAll();
    List<Transaccion> findByClienteId(Long clienteId);
    List<Transaccion> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
    List<Transaccion> findByClienteIdAndFechaBetween(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin);
    void deleteById(Long id);
}