package com.sistema.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaccion {
    private Long id;
    private Long clienteId;
    private String tipo;
    private BigDecimal monto;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaTransaccion;
    private LocalDateTime fechaCreacion;

    public Transaccion() {
    }

    public Transaccion(Long clienteId, String tipo, BigDecimal monto, String descripcion, String estado) {
        this.clienteId = clienteId;
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaTransaccion = LocalDateTime.now();
        this.fechaCreacion = LocalDateTime.now();
    }

    public Transaccion(Long id, Long clienteId, String tipo, BigDecimal monto, String descripcion, 
                       String estado, LocalDateTime fechaTransaccion, LocalDateTime fechaCreacion) {
        this.id = id;
        this.clienteId = clienteId;
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaTransaccion = fechaTransaccion;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaccion that = (Transaccion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", tipo='" + tipo + '\'' +
                ", monto=" + monto +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaTransaccion=" + fechaTransaccion +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}