-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS `proyecto_eventos` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `proyecto_eventos`;

-- -----------------------------------------------------
-- Tabla `rol`
-- -----------------------------------------------------
CREATE TABLE `rol` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(30) NOT NULL,
  `fecha_creacion` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_modificacion` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Tabla `permiso`
-- -----------------------------------------------------
CREATE TABLE `permiso` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `descripcion` VARCHAR(255) NULL,
  PRIMARY KEY (`id`)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Tabla `rol_permiso`
-- -----------------------------------------------------
CREATE TABLE `rol_permiso` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_rol` INT NOT NULL,
  `id_permiso` INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_rol_permiso_rol
    FOREIGN KEY (id_rol) REFERENCES rol (id),
  CONSTRAINT fk_rol_permiso_permiso
    FOREIGN KEY (id_permiso) REFERENCES permiso (id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Tabla `departamento`
-- -----------------------------------------------------
CREATE TABLE `departamento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(50) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `fecha_creacion` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_modificacion` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Tabla `municipio`
-- -----------------------------------------------------
CREATE TABLE `municipio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(50) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `id_departamento` INT NOT NULL,
  `fecha_creacion` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_modificacion` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC),
  CONSTRAINT `fk_municipio_departamento`
    FOREIGN KEY (`id_departamento`)
    REFERENCES `departamento` (`id`)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Tabla `usuario`
-- -----------------------------------------------------
CREATE TABLE `usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cedula` VARCHAR(10) NOT NULL,
  `nombre` VARCHAR(30) NOT NULL,
  `apellido` VARCHAR(30) NOT NULL,
  `telefono` VARCHAR(12) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `contraseña` VARCHAR(255) NOT NULL,
  `id_municipio` INT NOT NULL,
  `estado` TINYINT NOT NULL DEFAULT 1,
  `id_rol` INT NOT NULL,
  `fecha_creacion` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_modificacion` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `cedula_UNIQUE` (`cedula` ASC),
  UNIQUE INDEX `telefono_UNIQUE` (`telefono` ASC),
  CONSTRAINT `fk_usuario_rol`
    FOREIGN KEY (`id_rol`)
    REFERENCES `rol` (`id`),
  CONSTRAINT `fk_usuario_municipio`
    FOREIGN KEY (`id_municipio`)
    REFERENCES `municipio` (`id`)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Tabla `categoria`
-- -----------------------------------------------------
CREATE TABLE `categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `descripcion` VARCHAR(255) NULL,
  PRIMARY KEY (`id`)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Tabla `emprendimiento`
-- -----------------------------------------------------
CREATE TABLE `emprendimiento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(20) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `id_municipio` INT NOT NULL,
  `id_categoria` INT NOT NULL,
  `fecha_creacion` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_modificacion` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_emprendimiento_municipio`
    FOREIGN KEY (`id_municipio`)
    REFERENCES `municipio` (`id`),
  CONSTRAINT `fk_emprendimiento_categoria`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `categoria` (`id`)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Tabla `evento`
-- -----------------------------------------------------
CREATE TABLE `evento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `fecha` TIMESTAMP NOT NULL,
  `id_municipio` INT NOT NULL,
  `direccion` VARCHAR(255) NOT NULL,
  `descripcion` TEXT NULL,
  `fecha_creacion` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_modificacion` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_evento_municipio`
    FOREIGN KEY (`id_municipio`)
    REFERENCES `municipio` (`id`)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Tabla `usuario_emprendimiento`
-- -----------------------------------------------------
CREATE TABLE `usuario_emprendimiento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `id_emprendimiento` INT NOT NULL,
  `fecha_creacion` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_modificacion` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_usuario_emprendimiento_usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_usuario_emprendimiento_emprendimiento`
    FOREIGN KEY (`id_emprendimiento`)
    REFERENCES `emprendimiento` (`id`)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Tabla `evento_emprendimiento`
-- -----------------------------------------------------
CREATE TABLE `evento_emprendimiento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_evento` INT NOT NULL,
  `id_emprendimiento` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_evento_emprendimiento_evento`
    FOREIGN KEY (`id_evento`)
    REFERENCES `evento` (`id`),
  CONSTRAINT `fk_evento_emprendimiento_emprendimiento`
    FOREIGN KEY (`id_emprendimiento`)
    REFERENCES `emprendimiento` (`id`)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;