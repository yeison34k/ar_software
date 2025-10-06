package com.sistema.domain.port.in;

import com.sistema.domain.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClienteUseCase {
    Cliente crearCliente(Cliente cliente);
    Optional<Cliente> obtenerClientePorId(Long id);
    Page<Cliente> obtenerClientes(Pageable pageable);
    Cliente actualizarCliente(Long id, Cliente cliente);
    void eliminarCliente(Long id);
}