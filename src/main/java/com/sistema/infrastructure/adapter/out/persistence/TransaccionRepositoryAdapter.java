package com.sistema.infrastructure.adapter.out.persistence;

import com.sistema.domain.model.Transaccion;
import com.sistema.domain.port.out.TransaccionRepositoryPort;
import com.sistema.infrastructure.adapter.out.persistence.entity.TransaccionEntity;
import com.sistema.infrastructure.adapter.out.persistence.repository.TransaccionJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TransaccionRepositoryAdapter implements TransaccionRepositoryPort {

    private final TransaccionJpaRepository transaccionJpaRepository;

    public TransaccionRepositoryAdapter(TransaccionJpaRepository transaccionJpaRepository) {
        this.transaccionJpaRepository = transaccionJpaRepository;
    }

    @Override
    public Transaccion save(Transaccion transaccion) {
        TransaccionEntity entity = convertirDominioAEntity(transaccion);
        TransaccionEntity savedEntity = transaccionJpaRepository.save(entity);
        return convertirEntityADominio(savedEntity);
    }

    @Override
    public Optional<Transaccion> findById(Long id) {
        return transaccionJpaRepository.findById(id)
                .map(this::convertirEntityADominio);
    }

    @Override
    public List<Transaccion> findAll() {
        return transaccionJpaRepository.findAll()
                .stream()
                .map(this::convertirEntityADominio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaccion> findByClienteId(Long clienteId) {
        return transaccionJpaRepository.findByClienteId(clienteId)
                .stream()
                .map(this::convertirEntityADominio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaccion> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin) {
        return transaccionJpaRepository.findByFechaBetween(fechaInicio, fechaFin)
                .stream()
                .map(this::convertirEntityADominio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaccion> findByClienteIdAndFechaBetween(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        return transaccionJpaRepository.findByClienteIdAndFechaBetween(clienteId, fechaInicio, fechaFin)
                .stream()
                .map(this::convertirEntityADominio)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        transaccionJpaRepository.deleteById(id);
    }

    private TransaccionEntity convertirDominioAEntity(Transaccion transaccion) {
        TransaccionEntity entity = new TransaccionEntity();
        entity.setId(transaccion.getId());
        entity.setClienteId(transaccion.getClienteId());
        entity.setTipo(transaccion.getTipo());
        entity.setMonto(transaccion.getMonto());
        entity.setDescripcion(transaccion.getDescripcion());
        entity.setEstado(transaccion.getEstado());
        entity.setFechaTransaccion(transaccion.getFechaTransaccion());
        entity.setFechaCreacion(transaccion.getFechaCreacion());
        return entity;
    }

    private Transaccion convertirEntityADominio(TransaccionEntity entity) {
        return new Transaccion(
                entity.getId(),
                entity.getClienteId(),
                entity.getTipo(),
                entity.getMonto(),
                entity.getDescripcion(),
                entity.getEstado(),
                entity.getFechaTransaccion(),
                entity.getFechaCreacion()
        );
    }
}