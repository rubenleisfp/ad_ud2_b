CREATE TABLE `gestor_artistas`.`artista` (
  `nombre` VARCHAR(100) NOT NULL,
  `salario` DECIMAL(15,2) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  PRIMARY KEY (`nombre`));
