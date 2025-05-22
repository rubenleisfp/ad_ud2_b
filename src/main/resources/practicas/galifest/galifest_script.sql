DROP SCHEMA IF EXISTS galifest;

CREATE SCHEMA galifest;

USE galifest;

CREATE TABLE Evento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_del_evento VARCHAR(100),
    fecha_hora DATETIME,
    ubicacion VARCHAR(150),
    descripcion VARCHAR(250),
    precio_de_coste DECIMAL(10,2) NOT NULL
);
