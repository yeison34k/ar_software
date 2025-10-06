package com.sistema.infrastructure.adapter.in.web;

import com.sistema.application.dto.TransaccionInputDto;
import com.sistema.domain.model.Transaccion;
import com.sistema.domain.port.in.TransaccionUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
@Tag(name = "Transacciones", description = "API para gestión de transacciones")
public class TransaccionController {

    private final TransaccionUseCase transaccionUseCase;

    public TransaccionController(TransaccionUseCase transaccionUseCase) {
        this.transaccionUseCase = transaccionUseCase;
    }

    @GetMapping
    @Operation(summary = "Obtener transacciones", description = "Obtiene transacciones con filtros opcionales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de transacciones")
    })
    public ResponseEntity<List<Transaccion>> obtenerTransacciones(
            @Parameter(description = "ID del cliente (opcional)") @RequestParam(required = false) Long clienteId,
            @Parameter(description = "Fecha de inicio (formato: yyyy-MM-dd)") 
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @Parameter(description = "Fecha de fin (formato: yyyy-MM-dd)") 
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        List<Transaccion> transacciones;

        if (clienteId != null && fechaInicio != null && fechaFin != null) {
            transacciones = transaccionUseCase.obtenerTransaccionesPorClienteYFecha(clienteId, fechaInicio, fechaFin);
        } else if (clienteId != null) {
            transacciones = transaccionUseCase.obtenerTransaccionesPorCliente(clienteId);
        } else if (fechaInicio != null && fechaFin != null) {
            transacciones = transaccionUseCase.obtenerTransaccionesPorFecha(fechaInicio, fechaFin);
        } else {
            transacciones = transaccionUseCase.obtenerTransacciones();
        }

        return ResponseEntity.ok(transacciones);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener transacción por ID", description = "Obtiene una transacción específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transacción encontrada"),
            @ApiResponse(responseCode = "404", description = "Transacción no encontrada")
    })
    public ResponseEntity<Transaccion> obtenerTransaccionPorId(
            @Parameter(description = "ID de la transacción") @PathVariable Long id) {
        
        return transaccionUseCase.obtenerTransaccionPorId(id)
                .map(transaccion -> ResponseEntity.ok(transaccion))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear nueva transacción", description = "Crea una nueva transacción en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transacción creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<Transaccion> crearTransaccion(@Valid @RequestBody TransaccionInputDto transaccionInputDto) {
        Transaccion transaccion = convertirDtoAEntidad(transaccionInputDto);
        Transaccion transaccionCreada = transaccionUseCase.crearTransaccion(transaccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaccionCreada);
    }

    private Transaccion convertirDtoAEntidad(TransaccionInputDto dto) {
        return new Transaccion(
                dto.getClienteId(),
                dto.getTipo(),
                dto.getMonto(),
                dto.getDescripcion(),
                dto.getEstado()
        );
    }
}