# Proyecto CRUD de Recibos

Aplicacion Java Web local para registrar recibos de pedidos de una empresa.
El sistema guarda:

- Producto pedido
- Precio
- Documento de quien pidio
- Nombre de quien pidio

## Requisitos

- Java JDK 25
- Maven
- MySQL 8
- Apache Tomcat 11

## Base de datos

La conexion esta en:

```text
src/main/java/proyecto_v/conexion.java
```

Valores actuales:

```text
Base de datos: bdprueba
Usuario: root
Contrasena: Malumapbdb
Tabla principal: recibos
```