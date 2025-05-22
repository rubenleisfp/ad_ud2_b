DROP SCHEMA IF EXISTS galifest;

CREATE SCHEMA galifest;

USE galifest;

CREATE TABLE Evento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombreDelEvento VARCHAR(100) NOT NULL,
    fechaHora DATETIME NOT NULL,
    ubicacion VARCHAR(150),
    descripcion TEXT,
    precioDeCoste DECIMAL(10,2) NOT NULL
);
