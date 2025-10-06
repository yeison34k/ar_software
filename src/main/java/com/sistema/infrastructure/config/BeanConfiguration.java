package com.sistema.infrastructure.config;

import com.sistema.application.service.ClienteService;
import com.sistema.application.service.TransaccionService;
import com.sistema.domain.port.in.ClienteUseCase;
import com.sistema.domain.port.in.TransaccionUseCase;
import com.sistema.domain.port.out.ClienteRepositoryPort;
import com.sistema.domain.port.out.TransaccionRepositoryPort;
import com.sistema.infrastructure.adapter.out.persistence.ClienteRepositoryAdapter;
import com.sistema.infrastructure.adapter.out.persistence.TransaccionRepositoryAdapter;
import com.sistema.infrastructure.adapter.out.persistence.repository.ClienteJpaRepository;
import com.sistema.infrastructure.adapter.out.persistence.repository.TransaccionJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ClienteRepositoryPort clienteRepositoryPort(ClienteJpaRepository clienteJpaRepository) {
        return new ClienteRepositoryAdapter(clienteJpaRepository);
    }

    @Bean
    public TransaccionRepositoryPort transaccionRepositoryPort(TransaccionJpaRepository transaccionJpaRepository) {
        return new TransaccionRepositoryAdapter(transaccionJpaRepository);
    }

    @Bean
    public ClienteUseCase clienteUseCase(ClienteRepositoryPort clienteRepositoryPort) {
        return new ClienteService(clienteRepositoryPort);
    }

    @Bean
    public TransaccionUseCase transaccionUseCase(TransaccionRepositoryPort transaccionRepositoryPort,
                                                ClienteRepositoryPort clienteRepositoryPort) {
        return new TransaccionService(transaccionRepositoryPort, clienteRepositoryPort);
    }
}