package com.sistema.domain.port.out;

import com.sistema.domain.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClienteRepositoryPort {
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(Long id);
    Page<Cliente> findAll(Pageable pageable);
    void deleteById(Long id);
    boolean existsById(Long id);
}