CREATE DATABASE recursos_humanos_iud;
USE recursos_humanos_iud;

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
  
 CREATE TABLE estado_civil (
  estado_civil_id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL,
  PRIMARY KEY (estado_civil_id)
);
  
 CREATE TABLE tipo_documento (
  tipo_documento_id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL,
  codigo VARCHAR(3) NOT NULL, -- Nuevo campo para el código
  PRIMARY KEY (tipo_documento_id)
);

-- Agrega el campo estadoCivilId
ALTER TABLE recursos_humanos_iud.funcionarios 
ADD COLUMN estado_civil_id INT NOT NULL,
-- Agrega el índice en el nuevo campo
ADD INDEX estado_civil_id_idx (estado_civil_id ASC) VISIBLE, 
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
ADD INDEX tipo_documento_id_idx (tipo_documento_id ASC) VISIBLE;

-- Agrega restricción a la clave externa
ALTER TABLE  recursos_humanos_iud.funcionarios 
ADD CONSTRAINT tipo_documento_id
  FOREIGN KEY (tipo_documento_id)
  REFERENCES recursos_humanos_iud.tipo_documento (tipo_documento_id)
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

-- Insertar datos de empleados
INSERT INTO recursos_humanos_iud.funcionarios (tipo_documento_id, numero_identificacion, nombres, apellidos, estado_civil_id, sexo, direccion, telefono, fecha_nacimiento) VALUES
(1, '123456789', 'Juan', 'Pérez', 1, 'M', 'Calle 123', '1234567890', '1990-01-01'), -- Soltero, Cédula de Ciudadanía
(1, '987654321', 'María', 'Gómez', 2, 'F', 'Carrera 456', '0987654321', '1992-05-15'), -- Casada, Cédula de Ciudadanía
(3, 'ABC123', 'Pedro', 'López', 1, 'M', 'Avenida 789', '55555555', '1985-09-20'), -- Soltero, Pasaporte
(2, '456789123', 'Ana', 'Martínez', 3, 'F', 'Plaza Principal', '666666666', '1988-11-30'), -- Divorciada, Tarjeta de Identidad
(1, '789123456', 'Luis', 'González', 2, 'M', 'Calle 456', '33333333', '1980-07-10'); -- Casado, Cédula de Ciudadanía


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





