# Proyecto CRUD de Recibos

Aplicacion Java Web local para registrar recibos de pedidos de una empresa.
El sistema guarda:

- Producto pedido
- Cantidad pedida
- Precio unitario fijo
- Total del pedido
- Documento de quien pidio
- Nombre de quien pidio

## Requisitos

- Java JDK 17 o superior
- Maven
- MySQL 8
- Apache Tomcat 11
- Puerto local: 8080

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

## Compilar y desplegar localmente

```text
mvn clean package
```

El WAR queda en:

```text
target/proyecto_v_Base.war
```

En Tomcat 11 debe abrir en:

```text
http://localhost:8080/proyecto_v_Base/
```
