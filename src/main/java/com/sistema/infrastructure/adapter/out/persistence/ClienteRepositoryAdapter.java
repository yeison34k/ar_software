package com.sistema.infrastructure.adapter.out.persistence;

import com.sistema.domain.model.Cliente;
import com.sistema.domain.port.out.ClienteRepositoryPort;
import com.sistema.infrastructure.adapter.out.persistence.entity.ClienteEntity;
import com.sistema.infrastructure.adapter.out.persistence.repository.ClienteJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final ClienteJpaRepository clienteJpaRepository;

    public ClienteRepositoryAdapter(ClienteJpaRepository clienteJpaRepository) {
        this.clienteJpaRepository = clienteJpaRepository;
    }

    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity entity = convertirDominioAEntity(cliente);
        ClienteEntity savedEntity = clienteJpaRepository.save(entity);
        return convertirEntityADominio(savedEntity);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteJpaRepository.findById(id)
                .map(this::convertirEntityADominio);
    }

    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteJpaRepository.findAll(pageable)
                .map(this::convertirEntityADominio);
    }

    @Override
    public void deleteById(Long id) {
        clienteJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return clienteJpaRepository.existsById(id);
    }

    private ClienteEntity convertirDominioAEntity(Cliente cliente) {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(cliente.getId());
        entity.setNombre(cliente.getNombre());
        entity.setApellido(cliente.getApellido());
        entity.setEmail(cliente.getEmail());
        entity.setTelefono(cliente.getTelefono());
        entity.setDireccion(cliente.getDireccion());
        entity.setFechaCreacion(cliente.getFechaCreacion());
        entity.setFechaActualizacion(cliente.getFechaActualizacion());
        return entity;
    }

    private Cliente convertirEntityADominio(ClienteEntity entity) {
        return new Cliente(
                entity.getId(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getEmail(),
                entity.getTelefono(),
                entity.getDireccion(),
                entity.getFechaCreacion(),
                entity.getFechaActualizacion()
        );
    }
}