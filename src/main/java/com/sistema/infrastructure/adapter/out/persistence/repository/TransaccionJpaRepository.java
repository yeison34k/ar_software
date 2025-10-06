package com.sistema.infrastructure.adapter.out.persistence.repository;

import com.sistema.infrastructure.adapter.out.persistence.entity.TransaccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransaccionJpaRepository extends JpaRepository<TransaccionEntity, Long> {
    
    List<TransaccionEntity> findByClienteId(Long clienteId);
    
    @Query("SELECT t FROM TransaccionEntity t WHERE DATE(t.fechaTransaccion) BETWEEN :fechaInicio AND :fechaFin")
    List<TransaccionEntity> findByFechaBetween(@Param("fechaInicio") LocalDate fechaInicio, 
                                              @Param("fechaFin") LocalDate fechaFin);
    
    @Query("SELECT t FROM TransaccionEntity t WHERE t.clienteId = :clienteId AND DATE(t.fechaTransaccion) BETWEEN :fechaInicio AND :fechaFin")
    List<TransaccionEntity> findByClienteIdAndFechaBetween(@Param("clienteId") Long clienteId,
                                                          @Param("fechaInicio") LocalDate fechaInicio,
                                                          @Param("fechaFin") LocalDate fechaFin);
}