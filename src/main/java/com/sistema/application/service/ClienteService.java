package com.sistema.application.service;

import com.sistema.domain.model.Cliente;
import com.sistema.domain.port.in.ClienteUseCase;
import com.sistema.domain.port.out.ClienteRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ClienteService implements ClienteUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;

    public ClienteService(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        cliente.setFechaCreacion(LocalDateTime.now());
        cliente.setFechaActualizacion(LocalDateTime.now());
        return clienteRepositoryPort.save(cliente);
    }

    @Override
    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepositoryPort.findById(id);
    }

    @Override
    public Page<Cliente> obtenerClientes(Pageable pageable) {
        return clienteRepositoryPort.findAll(pageable);
    }

    @Override
    public Cliente actualizarCliente(Long id, Cliente cliente) {
        return clienteRepositoryPort.findById(id)
                .map(clienteExistente -> {
                    clienteExistente.setNombre(cliente.getNombre());
                    clienteExistente.setApellido(cliente.getApellido());
                    clienteExistente.setEmail(cliente.getEmail());
                    clienteExistente.setTelefono(cliente.getTelefono());
                    clienteExistente.setDireccion(cliente.getDireccion());
                    clienteExistente.setFechaActualizacion(LocalDateTime.now());
                    return clienteRepositoryPort.save(clienteExistente);
                })
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    @Override
    public void eliminarCliente(Long id) {
        if (!clienteRepositoryPort.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
        clienteRepositoryPort.deleteById(id);
    }
}