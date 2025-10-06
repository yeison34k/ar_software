package com.sistema.application.service;

import com.sistema.domain.model.Transaccion;
import com.sistema.domain.port.in.TransaccionUseCase;
import com.sistema.domain.port.out.TransaccionRepositoryPort;
import com.sistema.domain.port.out.ClienteRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionService implements TransaccionUseCase {

    private final TransaccionRepositoryPort transaccionRepositoryPort;
    private final ClienteRepositoryPort clienteRepositoryPort;

    public TransaccionService(TransaccionRepositoryPort transaccionRepositoryPort, 
                             ClienteRepositoryPort clienteRepositoryPort) {
        this.transaccionRepositoryPort = transaccionRepositoryPort;
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    @Override
    public Transaccion crearTransaccion(Transaccion transaccion) {
        // Validar que el cliente existe
        if (!clienteRepositoryPort.existsById(transaccion.getClienteId())) {
            throw new RuntimeException("Cliente con ID " + transaccion.getClienteId() + " no encontrado");
        }
        transaccion.setFechaTransaccion(LocalDateTime.now());
        transaccion.setFechaCreacion(LocalDateTime.now());
        return transaccionRepositoryPort.save(transaccion);
    }

    @Override
    public Optional<Transaccion> obtenerTransaccionPorId(Long id) {
        return transaccionRepositoryPort.findById(id);
    }

    @Override
    public List<Transaccion> obtenerTransacciones() {
        return transaccionRepositoryPort.findAll();
    }

    @Override
    public List<Transaccion> obtenerTransaccionesPorCliente(Long clienteId) {
        return transaccionRepositoryPort.findByClienteId(clienteId);
    }

    @Override
    public List<Transaccion> obtenerTransaccionesPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return transaccionRepositoryPort.findByFechaBetween(fechaInicio, fechaFin);
    }

    @Override
    public List<Transaccion> obtenerTransaccionesPorClienteYFecha(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        return transaccionRepositoryPort.findByClienteIdAndFechaBetween(clienteId, fechaInicio, fechaFin);
    }
}