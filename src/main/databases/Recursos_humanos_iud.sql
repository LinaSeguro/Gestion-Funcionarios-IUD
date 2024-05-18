/*Se crea base de datos de recursos humanos IUD y se pone como base de datos principal en uso*/
CREATE DATABASE recursos_humanos_iud;
USE recursos_humanos_iud;

/*Se crea la tabla funcionarios*/
CREATE TABLE `funcionarios`(
  `funcionario_id` INT NOT NULL AUTO_INCREMENT,
  `numero_identificacion` VARCHAR(45) NOT NULL,
  `nombres` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `sexo` VARCHAR(2) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  PRIMARY KEY (`funcionario_id`));
  
 /*Se crea la tabla estado civil*/ 
 CREATE TABLE estado_civil (
  estado_civil_id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL,
  PRIMARY KEY (estado_civil_id)
);
  
 /*Se crea la tabla tipo documento*/   
 CREATE TABLE tipo_documento (
  tipo_documento_id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL,
  codigo VARCHAR(3) NOT NULL, -- Nuevo campo para el código
  PRIMARY KEY (tipo_documento_id)
);

/*NUEVOS QUERIES*/
 /*Se crea la tabla grupo familiar*/   
CREATE TABLE recursos_humanos_iud.grupo_familiar (
	grupo_familiar_id INT NOT NULL AUTO_INCREMENT,
    nombres varchar(255) NOT NULL,
	apellidos varchar(255) NOT NULL,
	direccion varchar(255) NOT NULL,
	telefono varchar(20) NOT NULL,
    PRIMARY KEY (grupo_familiar_id)
);

 /*Se crea la tabla rol grupo familiar*/   
CREATE TABLE recursos_humanos_iud.rol_grupo_familiar(
	rol_id INT NOT NULL AUTO_INCREMENT,
    nombre varchar(100) NOT NULL,
    PRIMARY KEY (rol_id)
);

 /*Se crea la tabla formacion academica*/   
CREATE TABLE recursos_humanos_iud.formacion_academica (
    formacion_academica_id INT NOT NULL AUTO_INCREMENT,
    fecha_inicio DATE,
    fecha_fin DATE,
    estado_formacion_id INT NOT NULL,
    PRIMARY KEY (formacion_academica_id)
);


 /*Se crea la tabla nivel educativo*/   
CREATE TABLE recursos_humanos_iud.nivel_educativo (
    nivel_educativo_id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    fecha_creacion TIMESTAMP,
    fecha_actualizacion TIMESTAMP,
    PRIMARY KEY (nivel_educativo_id)
);


 /*Se crea la tabla universidad*/   
CREATE TABLE recursos_humanos_iud.universidad (
    universidad_id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    PRIMARY KEY (universidad_id)
);

 /*Se crea la tabla estado formacion*/   
CREATE TABLE recursos_humanos_iud.estado_formacion (
    estado_formacion_id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP,
    fecha_actualizacion TIMESTAMP,
    PRIMARY KEY (estado_formacion_id)
);

-- Agrega el campo estadoCivilId
ALTER TABLE recursos_humanos_iud.funcionarios 
ADD COLUMN estado_civil_id INT NOT NULL,
-- Agrega el índice en el nuevo campo
ADD INDEX estado_civil_id_idx (estado_civil_id ASC) , 
-- Agrega restricción a la clave externa
ADD CONSTRAINT estado_civil_id
  FOREIGN KEY (estado_civil_id)
  REFERENCES recursos_humanos_iud.estado_civil (estado_civil_id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
    
    
-- Agrega el campo tipoDocumentoId
ALTER TABLE  recursos_humanos_iud.funcionarios 
ADD COLUMN tipo_documento_id INT NOT NULL;


-- Agrega el índice en el nuevo campo
  ALTER TABLE  recursos_humanos_iud.funcionarios 
ADD INDEX tipo_documento_id_idx (tipo_documento_id ASC) ;


-- Agrega restricción a la clave externa
ALTER TABLE  recursos_humanos_iud.funcionarios 
ADD CONSTRAINT tipo_documento_id
  FOREIGN KEY (tipo_documento_id)
  REFERENCES recursos_humanos_iud.tipo_documento (tipo_documento_id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  
/*indices nuevos*/
/*creacion index rol_grupo_familiar*/
ALTER TABLE `recursos_humanos_iud`.`rol_grupo_familiar` 
ADD INDEX `rol_id_id_idx` (`rol_id` ASC) VISIBLE;
;

/*Creacion index funcionarios*/
ALTER TABLE `recursos_humanos_iud`.`funcionarios` 
ADD INDEX `fecha_creacion_id_idx` (`fecha_creacion` ASC) VISIBLE,
ADD INDEX `fecha_actualizacion_id_idx` (`fecha_actualizacion` ASC) VISIBLE,
ADD INDEX `funcionario_id` (`funcionario_id` ASC) VISIBLE;
ALTER TABLE `recursos_humanos_iud`.`funcionarios` ALTER INDEX `tipo_documento_id_idx` INVISIBLE;


/*nuevos indices agregarles las comillas inclinadas si saca error*/
ALTER TABLE recursos_humanos_iud.nivel_educativo 
CHANGE COLUMN fecha_creacion fecha_creacion TIMESTAMP NOT NULL ,
CHANGE COLUMN fecha_actualizacion fecha_actualizacion TIMESTAMP NOT NULL ,
ADD INDEX nivel_educativo_id_dx (nivel_educativo_id ASC) VISIBLE;
;

ALTER TABLE recursos_humanos_iud.nivel_educativo 
ADD INDEX fecha_creacion_id_alt_dx (fecha_creacion ASC) INVISIBLE,
ADD INDEX fecha_actualizacion_id_alt_idx (fecha_actualizacion ASC) VISIBLE;
;

ALTER TABLE recursos_humanos_iud.universidad 
ADD INDEX universidad_id_idx (universidad_id ASC) VISIBLE;
;

ALTER TABLE recursos_humanos_iud.estado_formacion 
ADD INDEX estado_formacion_id_idx (estado_formacion_id ASC) VISIBLE;
;

 /*nuevo*/
 -- agrega las llaves FK de funcionarios hacia grupo familiar
 ALTER TABLE `recursos_humanos_iud`.`grupo_familiar` 
ADD CONSTRAINT `rol_id_fk`
  FOREIGN KEY (`rolId`)
  REFERENCES `recursos_humanos_iud`.`rol_grupo_familiar` (`rol_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `funcionario_id_fk`
  FOREIGN KEY (`funcionarioId`)
  REFERENCES `recursos_humanos_iud`.`funcionarios` (`funcionario_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
 
/*agrega las llaves foraneas a la tabla formacion academica agregar comillas inclinadas si saca error en las tablas*/
ALTER TABLE recursos_humanos_iud.formacion_academica 
ADD CONSTRAINT estado_formacion_id_fk
  FOREIGN KEY (estado_formacion_id)
  REFERENCES recursos_humanos_iud.estado_formacion (estado_formacion_id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT nivel_educativo_id_fk
  FOREIGN KEY (nivel_educativo_id)
  REFERENCES recursos_humanos_iud.nivel_educativo (nivel_educativo_id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT universidad_id_fk
  FOREIGN KEY (universidad_id)
  REFERENCES recursos_humanos_iud.universidad (universidad_id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT funcionario_id_alt_fk2
  FOREIGN KEY (funcionario_id)
  REFERENCES recursos_humanos_iud.funcionarios (funcionario_id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


  -- Insertar datos de estados civiles
INSERT INTO recursos_humanos_iud.estado_civil (nombre) VALUES
('Soltero'),
('Casado'),
('Divorciado'),
('Viudo');

-- Insertar datos de tipos de documento
INSERT INTO recursos_humanos_iud.tipo_documento (nombre, codigo) VALUES
('Cédula de Ciudadanía','CC'),
('Cédula de Extranjeria', 'CE'),
('Tarjeta de Identidad', 'TI'),
('Pasaporte', 'PSP');

-- Insertar datos de funcionarios
INSERT INTO recursos_humanos_iud.funcionarios (tipo_documento_id, numero_identificacion, nombres, apellidos, estado_civil_id, sexo, direccion, telefono, fecha_nacimiento) VALUES
(1, '123456789', 'Juan', 'Pérez', 1, 'M', 'Calle 123', '1234567890', '1990-01-01'), -- Soltero, Cédula de Ciudadanía
(1, '987654321', 'María', 'Gómez', 2, 'F', 'Carrera 456', '0987654321', '1992-05-15'), -- Casada, Cédula de Ciudadanía
(3, 'ABC123', 'Pedro', 'López', 1, 'M', 'Avenida 789', '55555555', '1985-09-20'), -- Soltero, Pasaporte
(2, '456789123', 'Ana', 'Martínez', 3, 'F', 'Plaza Principal', '666666666', '1988-11-30'), -- Divorciada, Tarjeta de Identidad
(1, '789123456', 'Luis', 'González', 2, 'M', 'Calle 456', '33333333', '1980-07-10'); -- Casado, Cédula de Ciudadanía

-- insertar rol-grupo-familiar
INSERT INTO recursos_humanos_iud.rol_grupo_familiar (nombre)
VALUES ('Padre'), ('Madre'),('Hijo'),('Hija'), ('Abuelo');


-- insertar grupo-familiar
INSERT INTO recursos_humanos_iud.grupo_familiar (nombres, apellidos, direccion, telefono, rolId, funcionarioId)
VALUES 
    ('Juan', 'Pérez', 'Calle 123', '1234567890', 1 , 1),
    ('María', 'López', 'Avenida 456', '9876543210', 2,  1),
    ('Pedro', 'González', 'Carrera 789', '4561237890', 3,  2),
    ('Ana', 'Martínez', 'Avenida 012', '7894561230', 4,  2),
    ('Carlos', 'Sánchez', 'Calle 345', '3216549870', 5,  3);
    

-- insertar nivel educativo
INSERT INTO recursos_humanos_iud.nivel_educativo (nombre, fecha_creacion, fecha_actualizacion)
VALUES 
    ('Primaria', '2024-05-16 08:00:00', '2024-05-16 08:00:00'),
    ('Secundaria', '2024-05-16 09:00:00', '2024-05-16 09:00:00'),
    ('Bachillerato', '2024-05-16 10:00:00', '2024-05-16 10:00:00'),
    ('Universidad', '2024-05-16 11:00:00', '2024-05-16 11:00:00'),
    ('Maestría', '2024-05-16 12:00:00', '2024-05-16 12:00:00');

-- insertar universidad
INSERT INTO recursos_humanos_iud.universidad (nombre)
VALUES 
    ('Universidad Nacional Autónoma de México'),
    ('Universidad de Buenos Aires'),
    ('Universidad de São Paulo'),
    ('Universidad de Chile'),
    ('Universidad de la República');

-- insertar estado formacion
INSERT INTO recursos_humanos_iud.estado_formacion (nombre, fecha_creacion, fecha_actualizacion)
VALUES 
    ('En curso', '2024-05-16 08:00:00', '2024-05-16 08:00:00'),
    ('Completado', '2024-05-16 09:00:00', '2024-05-16 09:00:00'),
    ('Pendiente', '2024-05-16 10:00:00', '2024-05-16 10:00:00'),
    ('Abandonado', '2024-05-16 11:00:00', '2024-05-16 11:00:00'),
    ('Suspendido', '2024-05-16 12:00:00', '2024-05-16 12:00:00');

-- insertar formacion academica
INSERT INTO recursos_humanos_iud.formacion_academica (fecha_inicio, fecha_fin, estado_formacion_id, universidad_id, funcionario_id, nivel_educativo_id)
VALUES 
    ('2022-09-01', '2026-06-30', 1, 1, 1, 4),
    ('2018-03-15', '2022-11-30', 2, 3, 2, 5),
    ('2019-01-10', '2023-05-20', 2, 2, 3, 4),
    ('2020-08-20', '2024-07-15', 1, 5, 4, 3),
    ('2023-02-05', '2027-01-15', 3, 4, 5, 2);


-- Consulta para obtener funcionarios.
SELECT recursos_humanos_iud.funcionarios.funcionario_id, recursos_humanos_iud.funcionarios.numero_identificacion, 
       recursos_humanos_iud.funcionarios.nombres, recursos_humanos_iud.funcionarios.apellidos, 
       recursos_humanos_iud.funcionarios.sexo, recursos_humanos_iud.funcionarios.direccion, 
       recursos_humanos_iud.funcionarios.telefono, recursos_humanos_iud.funcionarios.fecha_nacimiento, 
       recursos_humanos_iud.estado_civil.nombre AS estado_civil, 
       recursos_humanos_iud.tipo_documento.nombre AS tipo_documento
FROM recursos_humanos_iud.funcionarios 
JOIN recursos_humanos_iud.estado_civil 
    ON recursos_humanos_iud.funcionarios.estado_civil_id = recursos_humanos_iud.estado_civil.estado_civil_id
JOIN recursos_humanos_iud.tipo_documento 
    ON recursos_humanos_iud.funcionarios.tipo_documento_id = recursos_humanos_iud.tipo_documento.tipo_documento_id;
    
-- Consulta para obtener funcionarios por id 
SELECT funcionarios.funcionario_id, 
       funcionarios.numero_identificacion, 
       funcionarios.nombres, 
       funcionarios.apellidos, 
       funcionarios.sexo, 
       funcionarios.direccion, 
       funcionarios.telefono, 
       funcionarios.fecha_nacimiento, 
       estado_civil.nombre AS estado_civil, 
       tipo_documento.nombre AS tipo_documento
FROM recursos_humanos_iud.funcionarios 
JOIN recursos_humanos_iud.estado_civil 
    ON recursos_humanos_iud.funcionarios.estado_civil_id = recursos_humanos_iud.estado_civil.estado_civil_id 
JOIN recursos_humanos_iud.tipo_documento 
    ON recursos_humanos_iud.funcionarios.tipo_documento_id = recursos_humanos_iud.tipo_documento.tipo_documento_id 
WHERE funcionarios.funcionario_id = 2;


SHOW TABLES;
DESCRIBE estado_civil;
DESCRIBE tipo_documento;
DESCRIBE funcionarios;
SELECT * FROM funcionarios;





