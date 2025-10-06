# Sistema SOA API - Spring Boot con Arquitectura Hexagonal

Este proyecto implementa una API REST para gestión de clientes y transacciones usando Spring Boot con arquitectura hexagonal.

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── com/sistema/
│   │       ├── SistemaSoaApiApplication.java          # Clase principal
│   │       ├── domain/                                # Capa de dominio
│   │       │   ├── model/                            # Entidades de dominio
│   │       │   │   ├── Cliente.java
│   │       │   │   └── Transaccion.java
│   │       │   └── port/                             # Puertos (interfaces)
│   │       │       ├── in/                           # Puertos de entrada
│   │       │       │   ├── ClienteUseCase.java
│   │       │       │   └── TransaccionUseCase.java
│   │       │       └── out/                          # Puertos de salida
│   │       │           ├── ClienteRepositoryPort.java
│   │       │           └── TransaccionRepositoryPort.java
│   │       ├── application/                          # Capa de aplicación
│   │       │   ├── dto/                              # DTOs
│   │       │   │   ├── ClienteInputDto.java
│   │       │   │   └── TransaccionInputDto.java
│   │       │   └── service/                          # Servicios (casos de uso)
│   │       │       ├── ClienteService.java
│   │       │       └── TransaccionService.java
│   │       └── infrastructure/                       # Capa de infraestructura
│   │           ├── adapter/
│   │           │   ├── in/                           # Adaptadores de entrada
│   │           │   │   └── web/                      # Controllers REST
│   │           │   │       ├── ClienteController.java
│   │           │   │       ├── TransaccionController.java
│   │           │   │       └── GlobalExceptionHandler.java
│   │           │   └── out/                          # Adaptadores de salida
│   │           │       └── persistence/              # Persistencia JPA
│   │           │           ├── ClienteRepositoryAdapter.java
│   │           │           ├── TransaccionRepositoryAdapter.java
│   │           │           ├── entity/               # Entidades JPA
│   │           │           │   ├── ClienteEntity.java
│   │           │           │   └── TransaccionEntity.java
│   │           │           └── repository/           # Repositorios JPA
│   │           │               ├── ClienteJpaRepository.java
│   │           │               └── TransaccionJpaRepository.java
│   │           └── config/                           # Configuración
│   │               ├── BeanConfiguration.java
│   │               └── DataLoader.java
│   └── resources/
│       └── application.yml                           # Configuración de la aplicación
```

## Características

- **Arquitectura Hexagonal**: Separación clara entre dominio, aplicación e infraestructura
- **Spring Boot 2.7**: Compatible con Java 8+
- **Base de datos H2**: Base de datos en memoria para desarrollo
- **Datos dummy**: Carga automática de datos de prueba al iniciar
- **Documentación OpenAPI**: Swagger UI disponible en `/swagger-ui.html`
- **Validación**: Validación de entrada usando Bean Validation
- **Manejo de errores**: Manejo global de excepciones

## Endpoints Disponibles

### Clientes
- `GET /api/clientes` - Obtener todos los clientes (con paginación)
- `GET /api/clientes/{id}` - Obtener cliente por ID
- `POST /api/clientes` - Crear nuevo cliente
- `PUT /api/clientes/{id}` - Actualizar cliente
- `DELETE /api/clientes/{id}` - Eliminar cliente

### Transacciones
- `GET /api/transacciones` - Obtener todas las transacciones (con filtros opcionales)
- `GET /api/transacciones/{id}` - Obtener transacción por ID
- `POST /api/transacciones` - Crear nueva transacción

## Filtros de Transacciones

La API permite filtrar transacciones por:
- `clienteId`: ID del cliente
- `fechaInicio`: Fecha de inicio (formato: yyyy-MM-dd)
- `fechaFin`: Fecha de fin (formato: yyyy-MM-dd)

Ejemplos:
- `/api/transacciones?clienteId=1`
- `/api/transacciones?fechaInicio=2024-01-01&fechaFin=2024-01-31`
- `/api/transacciones?clienteId=1&fechaInicio=2024-01-01&fechaFin=2024-01-31`

## Ejecución

### Opción 1: Con Gradle (recomendado)
```bash
./gradlew bootRun
```

### Opción 2: Con Java directamente
```bash
./gradlew build
java -jar build/libs/sistema-soa-api-0.0.1-SNAPSHOT.jar
```

### Opción 3: Con IDE
Ejecutar la clase `SistemaSoaApiApplication.java` desde tu IDE favorito.

## Acceso a la Aplicación

- **API Base URL**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **H2 Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: (vacío)

## Datos de Prueba

La aplicación carga automáticamente:
- 10 clientes dummy
- 20 transacciones dummy con diferentes tipos y estados

## Tecnologías Utilizadas

- Spring Boot 2.7.18
- Spring Data JPA
- Spring Web
- Spring Validation
- H2 Database
- SpringDoc OpenAPI (Swagger)
- Java 8+

## Arquitectura Hexagonal

Este proyecto sigue los principios de la arquitectura hexagonal:

1. **Dominio**: Contiene las entidades de negocio y las reglas de dominio
2. **Puertos**: Interfaces que definen los contratos entre capas
3. **Adaptadores**: Implementaciones concretas de los puertos
4. **Aplicación**: Casos de uso y lógica de aplicación
5. **Infraestructura**: Detalles técnicos como persistencia y web

Esta arquitectura permite:
- Independencia de frameworks
- Facilidad para testing
- Flexibilidad para cambiar implementaciones
- Separación clara de responsabilidades