package com.sistema.infrastructure.config;

import com.sistema.domain.model.Cliente;
import com.sistema.domain.model.Transaccion;
import com.sistema.domain.port.out.ClienteRepositoryPort;
import com.sistema.domain.port.out.TransaccionRepositoryPort;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ClienteRepositoryPort clienteRepositoryPort;
    private final TransaccionRepositoryPort transaccionRepositoryPort;

    public DataLoader(ClienteRepositoryPort clienteRepositoryPort, 
                     TransaccionRepositoryPort transaccionRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.transaccionRepositoryPort = transaccionRepositoryPort;
    }

    @Override
    public void run(String... args) throws Exception {
        cargarClientesDummy();
        cargarTransaccionesDummy();
    }

    private void cargarClientesDummy() {
        List<Cliente> clientes = Arrays.asList(
                new Cliente("Juan", "Pérez", "juan.perez@email.com", "555-0101", "Calle 123, Ciudad A"),
                new Cliente("María", "García", "maria.garcia@email.com", "555-0102", "Avenida 456, Ciudad B"),
                new Cliente("Carlos", "López", "carlos.lopez@email.com", "555-0103", "Plaza 789, Ciudad C"),
                new Cliente("Ana", "Martínez", "ana.martinez@email.com", "555-0104", "Boulevard 321, Ciudad D"),
                new Cliente("Luis", "Rodríguez", "luis.rodriguez@email.com", "555-0105", "Paseo 654, Ciudad E"),
                new Cliente("Carmen", "Sánchez", "carmen.sanchez@email.com", "555-0106", "Ronda 987, Ciudad F"),
                new Cliente("Miguel", "Torres", "miguel.torres@email.com", "555-0107", "Callejón 147, Ciudad G"),
                new Cliente("Isabel", "Flores", "isabel.flores@email.com", "555-0108", "Travesía 258, Ciudad H"),
                new Cliente("Roberto", "Jiménez", "roberto.jimenez@email.com", "555-0109", "Camino 369, Ciudad I"),
                new Cliente("Patricia", "Morales", "patricia.morales@email.com", "555-0110", "Sendero 741, Ciudad J")
        );

        for (Cliente cliente : clientes) {
            clienteRepositoryPort.save(cliente);
        }

        System.out.println("✅ Cargados " + clientes.size() + " clientes dummy");
    }

    private void cargarTransaccionesDummy() {
        List<Transaccion> transacciones = Arrays.asList(
                new Transaccion(1L, "DEPOSITO", new BigDecimal("1500.00"), "Depósito inicial", "COMPLETADA"),
                new Transaccion(1L, "RETIRO", new BigDecimal("200.50"), "Retiro en cajero", "COMPLETADA"),
                new Transaccion(2L, "DEPOSITO", new BigDecimal("2500.75"), "Transferencia recibida", "COMPLETADA"),
                new Transaccion(2L, "PAGO", new BigDecimal("89.99"), "Pago de servicios", "COMPLETADA"),
                new Transaccion(3L, "DEPOSITO", new BigDecimal("800.00"), "Depósito en efectivo", "COMPLETADA"),
                new Transaccion(3L, "TRANSFERENCIA", new BigDecimal("300.25"), "Transferencia a tercero", "PENDIENTE"),
                new Transaccion(4L, "DEPOSITO", new BigDecimal("3200.00"), "Salario mensual", "COMPLETADA"),
                new Transaccion(4L, "PAGO", new BigDecimal("150.00"), "Pago de tarjeta", "COMPLETADA"),
                new Transaccion(5L, "RETIRO", new BigDecimal("500.00"), "Retiro en sucursal", "COMPLETADA"),
                new Transaccion(5L, "DEPOSITO", new BigDecimal("1200.50"), "Depósito por cheque", "PROCESANDO"),
                new Transaccion(6L, "TRANSFERENCIA", new BigDecimal("750.00"), "Transferencia familiar", "COMPLETADA"),
                new Transaccion(6L, "PAGO", new BigDecimal("45.30"), "Pago de suscripción", "COMPLETADA"),
                new Transaccion(7L, "DEPOSITO", new BigDecimal("950.25"), "Depósito automático", "COMPLETADA"),
                new Transaccion(7L, "RETIRO", new BigDecimal("100.00"), "Retiro de emergencia", "COMPLETADA"),
                new Transaccion(8L, "PAGO", new BigDecimal("320.75"), "Pago de factura", "COMPLETADA"),
                new Transaccion(8L, "DEPOSITO", new BigDecimal("1800.00"), "Depósito empresarial", "COMPLETADA"),
                new Transaccion(9L, "TRANSFERENCIA", new BigDecimal("600.50"), "Transferencia internacional", "PENDIENTE"),
                new Transaccion(9L, "RETIRO", new BigDecimal("250.00"), "Retiro programado", "COMPLETADA"),
                new Transaccion(10L, "DEPOSITO", new BigDecimal("2200.00"), "Depósito de inversión", "COMPLETADA"),
                new Transaccion(10L, "PAGO", new BigDecimal("75.99"), "Pago online", "COMPLETADA")
        );

        for (Transaccion transaccion : transacciones) {
            // Ajustar fechas para que sean más realistas
            LocalDateTime fechaBase = LocalDateTime.now().minusDays((long) (Math.random() * 30));
            transaccion.setFechaTransaccion(fechaBase);
            transaccion.setFechaCreacion(fechaBase);
            transaccionRepositoryPort.save(transaccion);
        }

        System.out.println("✅ Cargadas " + transacciones.size() + " transacciones dummy");
    }
}