# Comandos cURL para la API Sistema SOA

## Base URL
```
http://localhost:8080
```

## 🧑‍💼 Endpoints de Clientes

### 1. Listar todos los clientes
```bash
curl -X GET "http://localhost:8080/api/clientes" \
  -H "Accept: application/json"
```

### 2. Obtener cliente por ID
```bash
curl -X GET "http://localhost:8080/api/clientes/1" \
  -H "Accept: application/json"
```

### 3. Crear nuevo cliente
```bash
curl -X POST "http://localhost:8080/api/clientes" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "nombre": "Juan",
    "apellido": "Pérez",
    "email": "juan.perez@email.com"
  }'
```

### 4. Actualizar cliente existente
```bash
curl -X PUT "http://localhost:8080/api/clientes/1" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "nombre": "Juan Carlos",
    "apellido": "Pérez García",
    "email": "juan.carlos.perez@email.com"
  }'
```

### 5. Eliminar cliente
```bash
curl -X DELETE "http://localhost:8080/api/clientes/1" \
  -H "Accept: application/json"
```

## 💰 Endpoints de Transacciones

### 1. Listar todas las transacciones
```bash
curl -X GET "http://localhost:8080/api/transacciones" \
  -H "Accept: application/json"
```

### 2. Obtener transacción por ID
```bash
curl -X GET "http://localhost:8080/api/transacciones/1" \
  -H "Accept: application/json"
```

### 3. Crear nueva transacción
```bash
curl -X POST "http://localhost:8080/api/transacciones" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "clienteId": 1,
    "tipo": "DEPOSITO",
    "monto": 1500.00,
    "descripcion": "Depósito inicial",
    "estado": "COMPLETADA"
  }'
```

### 4. Crear transacción de retiro
```bash
curl -X POST "http://localhost:8080/api/transacciones" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "clienteId": 1,
    "tipo": "RETIRO",
    "monto": 500.00,
    "descripcion": "Retiro de efectivo",
    "estado": "COMPLETADA"
  }'
```

### 5. Crear transacción pendiente
```bash
curl -X POST "http://localhost:8080/api/transacciones" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "clienteId": 1,
    "tipo": "TRANSFERENCIA",
    "monto": 750.00,
    "descripcion": "Transferencia a cuenta externa",
    "estado": "PENDIENTE"
  }'
```

## 📊 Endpoints de Documentación y Monitoreo

### 1. Swagger UI (Interfaz web)
```bash
# Abrir en navegador:
http://localhost:8080/swagger-ui.html
```

### 2. OpenAPI JSON
```bash
curl -X GET "http://localhost:8080/v3/api-docs" \
  -H "Accept: application/json"
```

### 3. Consola H2 Database (Interfaz web)
```bash
# Abrir en navegador:
http://localhost:8080/h2-console
# JDBC URL: jdbc:h2:mem:testdb
# Usuario: sa
# Contraseña: (vacía)
```

## 🔍 Ejemplos de Uso Completo

### Flujo completo: Crear cliente y transacciones
```bash
# 1. Crear cliente
CLIENT_RESPONSE=$(curl -s -X POST "http://localhost:8080/api/clientes" \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "María",
    "apellido": "González",
    "email": "maria.gonzalez@email.com"
  }')

echo "Cliente creado: $CLIENT_RESPONSE"

# 2. Crear depósito inicial
curl -X POST "http://localhost:8080/api/transacciones" \
  -H "Content-Type: application/json" \
  -d '{
    "clienteId": 2,
    "tipo": "DEPOSITO",
    "monto": 2000.00,
    "descripcion": "Depósito inicial de apertura",
    "estado": "COMPLETADA"
  }'

# 3. Crear retiro
curl -X POST "http://localhost:8080/api/transacciones" \
  -H "Content-Type: application/json" \
  -d '{
    "clienteId": 2,
    "tipo": "RETIRO",
    "monto": 300.00,
    "descripcion": "Retiro ATM",
    "estado": "COMPLETADA"
  }'

# 4. Verificar transacciones del cliente
curl -X GET "http://localhost:8080/api/transacciones" \
  -H "Accept: application/json"
```

## ✅ Endpoints Probados y Funcionando

Los siguientes comandos han sido probados exitosamente:

```bash
# ✅ Listar clientes - FUNCIONA
curl -X GET "http://localhost:8080/api/clientes" -H "Accept: application/json"

# ✅ Listar transacciones - FUNCIONA  
curl -X GET "http://localhost:8080/api/transacciones" -H "Accept: application/json"

# ✅ Crear cliente - FUNCIONA
curl -X POST "http://localhost:8080/api/clientes" \
  -H "Content-Type: application/json" \
  -d '{"nombre": "Test", "apellido": "Usuario", "email": "test.usuario@email.com"}'

# ✅ Crear transacción - FUNCIONA
curl -X POST "http://localhost:8080/api/transacciones" \
  -H "Content-Type: application/json" \
  -d '{"clienteId": 11, "tipo": "DEPOSITO", "monto": 1000.00, "descripcion": "Deposito inicial", "estado": "COMPLETADA"}'
```

## 📝 Notas Importantes

### ⚠️ Codificación UTF-8:
- En Windows, evita usar caracteres especiales (ñ, á, é, í, ó, ú) directamente en cURL
- Si necesitas usar acentos, guarda el JSON en un archivo y usa: `curl -d @archivo.json`
- Alternativa: usa `--data-raw` en lugar de `-d`

### 📁 Archivos JSON de Ejemplo:
Se han creado archivos de ejemplo en la carpeta `ejemplos_json/`:

```bash
# Usar archivo JSON para crear cliente con acentos
curl -X POST "http://localhost:8080/api/clientes" \
  -H "Content-Type: application/json" \
  -d @ejemplos_json/cliente_nuevo.json

# Usar archivo JSON para crear transacción de depósito
curl -X POST "http://localhost:8080/api/transacciones" \
  -H "Content-Type: application/json" \
  -d @ejemplos_json/transaccion_deposito.json

# Usar archivo JSON para crear transacción de retiro
curl -X POST "http://localhost:8080/api/transacciones" \
  -H "Content-Type: application/json" \
  -d @ejemplos_json/transaccion_retiro.json
```

### Tipos de Transacción Válidos:
- `DEPOSITO`
- `RETIRO`
- `TRANSFERENCIA`

### Estados de Transacción Válidos:
- `PENDIENTE`
- `COMPLETADA`
- `CANCELADA`

### Validaciones:
- **Email**: Debe ser un formato válido
- **Nombre/Apellido**: Entre 2 y 50 caracteres
- **Monto**: Debe ser positivo y máximo 2 decimales
- **Cliente ID**: Debe existir en la base de datos

### Códigos de Respuesta HTTP:
- `200 OK`: Operación exitosa
- `201 Created`: Recurso creado exitosamente
- `400 Bad Request`: Datos inválidos
- `404 Not Found`: Recurso no encontrado
- `500 Internal Server Error`: Error del servidor

## 🚀 Comandos de Prueba Rápida

### Verificar que la API está funcionando:
```bash
curl -X GET "http://localhost:8080/api/clientes" \
  -H "Accept: application/json" \
  -w "\nStatus: %{http_code}\n"
```

### Crear datos de prueba:
```bash
# Crear 3 clientes de prueba
for i in {1..3}; do
  curl -X POST "http://localhost:8080/api/clientes" \
    -H "Content-Type: application/json" \
    -d "{
      \"nombre\": \"Cliente$i\",
      \"apellido\": \"Apellido$i\",
      \"email\": \"cliente$i@test.com\"
    }"
done
```