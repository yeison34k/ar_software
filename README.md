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

## 🚀 Inicialización del Proyecto

### Prerrequisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- **Java 8 o superior** (JDK 8+)
- **Git** para clonar el repositorio
- **Maven 3.6+** o usar el wrapper incluido

### 📥 Clonar el Repositorio

```bash
git clone https://github.com/yeison34k/ar_software.git
cd ar_software
```

### ⚙️ Configuración Inicial

#### Opción 1: Usar Maven Wrapper (Recomendado)
El proyecto incluye Maven Wrapper, por lo que no necesitas instalar Maven:

**En Windows:**
```bash
./mvnw.cmd clean install
```

**En Linux/Mac:**
```bash
./mvnw clean install
```

#### Opción 2: Con Maven Instalado
Si tienes Maven instalado globalmente:

```bash
mvn clean install
```

### 🏃‍♂️ Ejecutar la Aplicación

#### Opción 1: Con Maven Wrapper (Recomendado)

**En Windows:**
```bash
./mvnw.cmd spring-boot:run
```

**En Linux/Mac:**
```bash
./mvnw spring-boot:run
```

#### Opción 2: Con Maven Instalado
```bash
mvn spring-boot:run
```

#### Opción 3: Con Java directamente
```bash
mvn clean package
java -jar target/sistema-soa-api-0.0.1-SNAPSHOT.jar
```

#### Opción 4: Con IDE
1. Importar el proyecto como proyecto Maven
2. Ejecutar la clase `SistemaSoaApiApplication.java`

### ✅ Verificar la Instalación

Una vez que la aplicación esté ejecutándose, verifica que todo funcione correctamente:

1. **Verificar que la aplicación esté corriendo:**
   ```bash
   curl http://localhost:8080/api/clientes
   ```

2. **Acceder a Swagger UI:**
   - Abrir en el navegador: http://localhost:8080/swagger-ui.html

3. **Verificar la base de datos H2:**
   - Abrir en el navegador: http://localhost:8080/h2-console
   - Usar las credenciales mencionadas en la sección "Acceso a la Aplicación"

### 🔧 Configuración Adicional

#### Variables de Entorno (Opcional)
Puedes personalizar la configuración usando variables de entorno:

```bash
# Puerto de la aplicación (por defecto: 8080)
export SERVER_PORT=8080

# Perfil de Spring (por defecto: default)
export SPRING_PROFILES_ACTIVE=dev
```

#### Configuración de Base de Datos (Opcional)
Para usar una base de datos diferente a H2, modifica el archivo `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/sistema_soa
    username: tu_usuario
    password: tu_password
    driver-class-name: org.postgresql.Driver
```

### 🧪 Probar los Endpoints

El proyecto incluye archivos JSON de ejemplo en la carpeta `ejemplos_json/`. Puedes usarlos para probar la API:

```bash
# Crear un nuevo cliente
curl -X POST http://localhost:8080/api/clientes \
  -H "Content-Type: application/json" \
  -d @ejemplos_json/cliente_nuevo.json

# Crear una nueva transacción
curl -X POST http://localhost:8080/api/transacciones \
  -H "Content-Type: application/json" \
  -d @ejemplos_json/transaccion_deposito.json
```

Para más ejemplos de cURL, consulta el archivo `CURL_ENDPOINTS.md`.

## Ejecución

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

## 🔧 Solución de Problemas Comunes

### Error: "JAVA_HOME not set"
```bash
# En Windows
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_XXX

# En Linux/Mac
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk
```

### Error: "Port 8080 already in use"
1. Cambiar el puerto en `application.yml`:
   ```yaml
   server:
     port: 8081
   ```
2. O usar variable de entorno:
   ```bash
   export SERVER_PORT=8081
   ```

### Error: "Maven command not found"
Usar Maven Wrapper incluido en el proyecto:
```bash
# Windows
./mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

### Error de codificación UTF-8 en Windows
Al usar cURL con caracteres especiales:
```bash
# Usar archivos JSON en lugar de texto directo
curl -X POST http://localhost:8080/api/clientes -d @ejemplos_json/cliente_nuevo.json -H "Content-Type: application/json"
```

### Verificar que Java 8+ esté instalado
```bash
java -version
javac -version
```

## 📞 Soporte

Si encuentras algún problema:
1. Revisa los logs de la aplicación
2. Verifica que todos los prerrequisitos estén instalados
3. Consulta la documentación de Swagger UI en http://localhost:8080/swagger-ui.html
4. Revisa el archivo `CURL_ENDPOINTS.md` para ejemplos de uso