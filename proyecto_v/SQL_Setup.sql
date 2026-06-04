-- Script para crear la base de datos y la tabla de recibos
-- Ejecutar este script en MySQL antes de usar el CRUD.

CREATE DATABASE IF NOT EXISTS bdprueba;
USE bdprueba;

CREATE TABLE IF NOT EXISTS recibos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    producto VARCHAR(150) NOT NULL,
    precio DECIMAL(12,2) NOT NULL,
    documento VARCHAR(30) NOT NULL,
    nombre VARCHAR(120) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Datos de prueba opcionales:
-- INSERT INTO recibos (producto, precio, documento, nombre)
-- VALUES ('Manguera agricola 1 pulgada', 150000.00, '1020304050', 'Marcos Chica');
-- INSERT INTO recibos (producto, precio, documento, nombre)
-- VALUES ('Acople industrial', 45000.00, '1234567890', 'Camilo Chica');

SELECT * FROM recibos;
