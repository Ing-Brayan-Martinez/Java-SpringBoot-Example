-- Crear la base de datos. --
CREATE DATABASE IF NOT EXISTS `Prueba` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;


-- Cargar la base de datos. --
USE `Prueba`;



-- person --
DROP TABLE IF EXISTS `Prueba`.`person`;

CREATE TABLE `person` (
    `key` int(11) NULL,
    `nombre` varchar(250) NOT NULL,
    `apellido` varchar(250) NOT NULL,
    `fechaNacimiento` date NOT NULL,
    `cedula` varchar(12) NOT NULL,
    `altura` float NOT NULL,
    `telefono` varchar(12) NOT NULL,
    `correo` varchar(250) NOT NULL,
    `fechaRegistro` date NOT NULL,
    `fechaModificacion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

ALTER TABLE `person`
    ADD PRIMARY KEY (`key`),
    ADD UNIQUE `cedula` (`cedula`),
    MODIFY `key` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=0;

-- children --
DROP TABLE IF EXISTS `Prueba`.`children`;

CREATE TABLE `children` (
    `Key` int(11) NULL,
    `nombre` varchar(250) NOT NULL,
    `apellido` varchar(250) NOT NULL,
    `fechaNacimiento` date NOT NULL,
    `tipoSangre` varchar(12) NOT NULL,
    `documentoIdentidad` varchar(12) NOT NULL,
     `KeyPersona` int(11) NULL,
    `fechaRegistro` date NOT NULL,
    `fechaModificacion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

ALTER TABLE `children`
    ADD PRIMARY KEY (`Key`),
    ADD UNIQUE `documentoIdentidad` (`documentoIdentidad`),
    ADD FOREIGN KEY (KeyPersona) REFERENCES person(`key`),
    MODIFY `key` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=0;