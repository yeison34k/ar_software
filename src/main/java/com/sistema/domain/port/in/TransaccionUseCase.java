package com.sistema.domain.port.in;

import com.sistema.domain.model.Transaccion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransaccionUseCase {
    Transaccion crearTransaccion(Transaccion transaccion);
    Optional<Transaccion> obtenerTransaccionPorId(Long id);
    List<Transaccion> obtenerTransacciones();
    List<Transaccion> obtenerTransaccionesPorCliente(Long clienteId);
    List<Transaccion> obtenerTransaccionesPorFecha(LocalDate fechaInicio, LocalDate fechaFin);
    List<Transaccion> obtenerTransaccionesPorClienteYFecha(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin);
}