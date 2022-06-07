DROP DATABASE IF EXISTS db_control_academico_in5bm;
CREATE DATABASE db_control_academico_in5bm;
USE db_control_academicO_in5bm;

/*
* Estudiantes:
* Angel Gabriel Sanabria Morales. 2021067
* Jose Eduardo Beltran Cabrera. 2021054
*
* Grupo: 1
* Codigo Tecnico: IN5BM
* Fecha Creacion: 26/04/2022
* Fecha Modificacion: 29/04/2022
*/


DROP TABLE IF EXISTS alumnos;
CREATE TABLE alumnos(
carne VARCHAR(16) NOT NULL,
nombre1 VARCHAR(15) NOT NULL,
nombre2 VARCHAR(15), 
nombre3 VARCHAR(15), 
apellido1 VARCHAR(15) NOT NULL,
apellido2 VARCHAR(15),
PRIMARY KEY (carne) 
);

DROP TABLE IF EXISTS instructores;
CREATE TABLE instructores(
id INT NOT NULL AUTO_INCREMENT,
nombre1 VARCHAR(15) NOT NULL,
nombre2 VARCHAR(15), 
nombre3 VARCHAR(15), 
apellido1 VARCHAR(15) NOT NULL,
apellido2 VARCHAR(15),
direccion VARCHAR(45),
email VARCHAR(45) NOT NULL,
telefono VARCHAR(8) NOT NULL,
fecha_nacimiento DATE,
PRIMARY KEY (id) 
);

DROP TABLE IF EXISTS salones;
CREATE TABLE salones(
codigo_salon VARCHAR(8) NOT NULL,
descripcion VARCHAR(45),
capacidad_maxima INT NOT NULL,
edificio VARCHAR (15),
nivel INT,
PRIMARY KEY(codigo_salon)
);

DROP TABLE IF EXISTS carreras_tecnicas;
CREATE TABLE carreras_tecnicas(
codigo_tecnico VARCHAR(8) NOT NULL,
carrera VARCHAR(45) NOT NULL,
grado VARCHAR(10) NOT NULL,
seccion CHAR(1) NOT NULL,
jornada VARCHAR(10) NOT NULL,
PRIMARY KEY (codigo_tecnico)
);

DROP TABLE IF EXISTS horarios;
CREATE TABLE horarios(
id INT NOT NULL AUTO_INCREMENT,
horario_inicio TIME NOT NULL,
horario_final TIME NOT NULL,
lunes TINYINT(1),
martes TINYINT(1),
miercoles TINYINT(1),
jueves TINYINT(1),
viernes TINYINT(1),
PRIMARY KEY(id)
);

DROP TABLE IF EXISTS cursos;
CREATE TABLE cursos(
id INT NOT NULL AUTO_INCREMENT,
nombre_curso VARCHAR(255) NOT NULL,
ciclo YEAR,
cupo_maximo INT,
cupo_minimo INT,
PRIMARY KEY (id),
carreras_tecnicas_id VARCHAR(8) NOT NULL,
horario_id INT NOT NULL,
instructor_id INT NOT NULL,
salon_id VARCHAR(5) NOT NULL,
CONSTRAINT fk_carreras_tecnicas_id
FOREIGN KEY (carreras_tecnicas_id)
REFERENCES carreras_tecnicas(codigo_tecnico)
ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT fk_horario_id
FOREIGN KEY (horario_id)
REFERENCES horarios(id)
ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT fk_instructor_id
FOREIGN KEY (instructor_id)
REFERENCES instructores(id)
ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT fk_salon_id
FOREIGN KEY (salon_id)
REFERENCES salones(codigo_salon)
ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS asignacion_alumnos;
CREATE TABLE asignacion_alumnos(
id INT AUTO_INCREMENT NOT NULL,
alumno_id VARCHAR(16) NOT NULL,
curso_id INT NOT NULL,
fecha_asignacion DATETIME,
PRIMARY KEY(id),
CONSTRAINT fk_alumno_id
FOREIGN KEY (alumno_id)
REFERENCES alumnos(carne)
ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT fk_curso_id
FOREIGN KEY (curso_id)
REFERENCES cursos(id)
ON DELETE CASCADE ON UPDATE CASCADE
);


#----------------------- PROCEDIMIENTOS ALMACENADOS --------------------
-- ALUMNOS
DROP PROCEDURE IF EXISTS sp_create_alumnos;
DELIMITER $$
	CREATE PROCEDURE sp_create_alumnos(IN _carne VARCHAR(15), IN _nombre1 VARCHAR(15), IN _nombre2 VARCHAR(15),
		IN _nombre3 VARCHAR(15), IN _apellido1 VARCHAR(15), IN _apellido2 VARCHAR(15))
	BEGIN
		INSERT INTO alumnos (carne,nombre1,nombre2,nombre3,apellido1,apellido2)
		VALUES(_carne,_nombre1,_nombre2,_nombre3,_apellido1,_apellido2);
END $$

DROP PROCEDURE IF EXISTS sp_update_alumnos;
DELIMITER $$
	CREATE PROCEDURE sp_update_alumnos(IN _nombre1 VARCHAR(15), IN _nombre2 VARCHAR(15), IN _nombre3 VARCHAR(15),
		IN _apellido1 VARCHAR(15), IN _apellido2 VARCHAR(15), IN _carne VARCHAR(15))
	BEGIN
		UPDATE alumnos SET
		nombre1 = _nombre1,
		nombre2 = _nombre2,
		nombre3 = _nombre3,
		apellido1 = _apellido1,
		apellido2 = _apellido2
		WHERE carne = _carne;
END $$
    
DROP PROCEDURE IF EXISTS sp_delete_alumnos;
DELIMITER $$
	CREATE PROCEDURE sp_delete_alumnos(IN _carne VARCHAR(15))
	BEGIN
		DELETE FROM alumnos WHERE carne =_carne;
END $$ 

DROP PROCEDURE IF EXISTS sp_read_alumnos;
DELIMITER $$
	CREATE PROCEDURE sp_read_alumnos()
	BEGIN
		SELECT * FROM alumnos;
END $$ 

DROP PROCEDURE IF EXISTS sp_read_alumnos_by_id;
DELIMITER $$
	CREATE PROCEDURE sp_read_alumnos_by_id(IN _carne VARCHAR(15))
	BEGIN
		SELECT * FROM alumnos WHERE _carne = carne;
END $$ 

-- INSTRUCTORES

DROP PROCEDURE IF EXISTS sp_create_instructores;
DELIMITER $$
	CREATE PROCEDURE sp_create_instructores(IN _nombre1 VARCHAR(15), IN _nombre2 VARCHAR(15),
		IN _nombre3 VARCHAR(15), IN _apellido1 VARCHAR(15), IN _apellido2 VARCHAR(15),
		IN _direccion VARCHAR(45), IN _email VARCHAR(45), IN _telefono VARCHAR(8), IN _fecha_nacimiento DATE)
	BEGIN
		INSERT INTO instructores (nombre1,nombre2,nombre3,apellido1,apellido2,direccion,email,telefono,fecha_nacimiento)
		VALUES(_nombre1,_nombre2,_nombre3,_apellido1,_apellido2,_direccion,_email,_telefono,_fecha_nacimiento);
END $$

DROP PROCEDURE IF EXISTS sp_update_instructores;
DELIMITER $$
	CREATE PROCEDURE sp_update_instructores(IN _nombre1 VARCHAR(15), IN _nombre2 VARCHAR(15),
		IN _nombre3 VARCHAR(15), IN _apellido1 VARCHAR(15), IN _apellido2 VARCHAR(15),
		IN _direccion VARCHAR(45), IN _email VARCHAR(45), IN _telefono VARCHAR(8), IN _fecha_nacimiento DATE, IN _id INT)
	BEGIN
		UPDATE instructores SET
		nombre1 = _nombre1,
		nombre2 = _nombre2,
		nombre3 = _nombre3,
		apellido1 = _apellido1,
		apellido2 = _apellido2,
		direccion = _direccion,
		email = _email,
		telefono = _telefono,
		fecha_nacimiento =_fecha_nacimiento
		WHERE id = _id;
END $$
    
DROP PROCEDURE IF EXISTS sp_delete_instructores;
DELIMITER $$
	CREATE PROCEDURE sp_delete_instructores(IN _id INT)
	BEGIN
		DELETE FROM instructores WHERE id =_id;
END $$ 

DROP PROCEDURE IF EXISTS sp_read_instructores;
DELIMITER $$
	CREATE PROCEDURE sp_read_instructores()
	BEGIN
		SELECT * FROM instructores;
END $$

DROP PROCEDURE IF EXISTS sp_read_instructores_by_id;
DELIMITER $$
	CREATE PROCEDURE sp_read_instructores_by_id(IN _id INT)
	BEGIN
		SELECT * FROM instructores WHERE id = _id;
END $$

-- SALONES
DROP PROCEDURE IF EXISTS sp_create_salones;
DELIMITER $$
	CREATE PROCEDURE sp_create_salones(IN _codigo_salon VARCHAR(8), IN _descripcion VARCHAR(45), 
		IN _capacidad_maxima INT, IN _edificio VARCHAR (15), IN _nivel INT)
	BEGIN
		INSERT INTO salones (codigo_salon,descripcion,capacidad_maxima,edificio,nivel)
		VALUES(_codigo_salon,_descripcion,_capacidad_maxima,_edificio,_nivel);
END $$

DROP PROCEDURE IF EXISTS sp_update_salones;
DELIMITER $$
	CREATE PROCEDURE sp_update_salones(IN _descripcion VARCHAR(45), 
    IN _capacidad_maxima INT, IN _edificio VARCHAR (15), IN _nivel INT , IN _codigo_salon VARCHAR(8))
	BEGIN
	UPDATE salones SET
    descripcion = _descripcion,
    capacidad_maxima = _capacidad_maxima,
    edificio = _edificio,
    nivel = _nivel
    WHERE
    codigo_salon = _codigo_salon;
END $$

DROP PROCEDURE IF EXISTS sp_delete_salones;
DELIMITER $$
	CREATE PROCEDURE sp_delete_salones(IN _codigo_salon VARCHAR(8))
	BEGIN
		DELETE FROM salones
		WHERE
		codigo_salon = _codigo_salon;
END $$

DROP PROCEDURE IF EXISTS sp_read_salones;
DELIMITER $$
	CREATE PROCEDURE sp_read_salones()
	BEGIN
		SELECT * FROM salones;
END $$

DROP PROCEDURE IF EXISTS sp_read_salones_by_id;
DELIMITER $$
	CREATE PROCEDURE sp_read_salones_by_id(IN _codigo_salon VARCHAR(8))
	BEGIN
		SELECT * FROM salones WHERE codigo_salon = _codigo_salon;
END $$


-- Carreras Tecnicas
DROP PROCEDURE IF EXISTS sp_create_carreras;
DELIMITER $$
	CREATE PROCEDURE sp_create_carreras(IN _codigo_tecnico VARCHAR(8), IN _carrera VARCHAR(45), 
		IN _grado VARCHAR(10), IN _seccion CHAR(1), IN _jornada VARCHAR (10))
	BEGIN
		INSERT INTO carreras_tecnicas (codigo_tecnico,carrera,grado,seccion,jornada)
		VALUES(_codigo_tecnico,_carrera,_grado,_seccion,_jornada);
END $$

DROP PROCEDURE IF EXISTS sp_update_carreras;
DELIMITER $$
	CREATE PROCEDURE sp_update_carreras(IN _carrera VARCHAR(45), 
		IN _grado VARCHAR(10), IN _seccion CHAR(1), IN _jornada VARCHAR (10), IN _codigo_tecnico VARCHAR(8))
	BEGIN
		UPDATE carreras_tecnicas SET
		carrera = _carrera,
		grado = _grado,
		seccion = _seccion,
		jornada = _jornada
		WHERE
		codigo_tecnico = _codigo_tecnico;
END $$

DROP PROCEDURE IF EXISTS sp_delete_carreras;
DELIMITER $$
	CREATE PROCEDURE sp_delete_carreras(IN _codigo_tecnico VARCHAR(8))
	BEGIN
		DELETE FROM carreras_tecnicas
		WHERE
		codigo_tecnico = _codigo_tecnico;
END $$

DROP PROCEDURE IF EXISTS sp_read_carreras;
DELIMITER $$
	CREATE PROCEDURE sp_read_carreras()
	BEGIN
		SELECT * FROM carreras_tecnicas;
END $$

DROP PROCEDURE IF EXISTS sp_read_carreras_by_id;
DELIMITER $$
	CREATE PROCEDURE sp_read_carreras_by_id(IN _codigo_tecnico VARCHAR(8))
	BEGIN
		SELECT * FROM carreras_tecnicas WHERE codigo_tecnico = _codigo_tecnico;
END $$

-- HORARIOS
DROP PROCEDURE IF EXISTS sp_create_horarios;
DELIMITER $$
	CREATE PROCEDURE sp_create_horarios(IN _horario_inicio TIME, 
		IN _horario_final TIME, IN _lunes TINYINT, IN _martes TINYINT, IN _miercoles TINYINT,
		IN _jueves TINYINT, IN _viernes TINYINT)
	BEGIN
		INSERT INTO horarios (horario_inicio,horario_final,lunes,martes,miercoles,
		jueves,viernes)
		VALUES(_horario_inicio,_horario_final,_lunes,_martes,_miercoles,_jueves,_viernes);
END $$

DROP PROCEDURE IF EXISTS sp_update_horarios;
DELIMITER $$
	CREATE PROCEDURE sp_update_horarios (IN _horario_inicio TIME, 
		IN _horario_final TIME, IN _lunes TINYINT, IN _martes TINYINT, IN _miercoles TINYINT,
		IN _jueves TINYINT, IN _viernes TINYINT, IN _id INT)
	BEGIN
		UPDATE horarios SET
		horario_inicio = _horario_inicio,
		horario_final = _horario_final,
		lunes = _lunes,
		martes = _martes,
		miercoles = _miercoles,
		jueves = _jueves,
		viernes = _viernes
		WHERE
		id = _id;
END $$

DROP PROCEDURE IF EXISTS sp_delete_horarios;
DELIMITER $$
	CREATE PROCEDURE sp_delete_horarios(IN _id INT)
	BEGIN
		DELETE FROM horarios
		WHERE
		id = _id;
END $$

DROP PROCEDURE IF EXISTS sp_read_horarios;
DELIMITER $$
	CREATE PROCEDURE sp_read_horarios()
	BEGIN
		SELECT * FROM horarios;
END $$

DROP PROCEDURE IF EXISTS sp_read_horarios_by_id;
DELIMITER $$
	CREATE PROCEDURE sp_read_horarios_by_id(IN _id INT)
	BEGIN
		SELECT * FROM horarios WHERE id = _id;
END $$

-- CURSOS
DROP PROCEDURE IF EXISTS sp_create_cursos;
DELIMITER $$
	CREATE PROCEDURE sp_create_cursos(IN _nombre_curso VARCHAR(255), IN _ciclo YEAR,
		IN _cupo_maximo INT , IN _cupo_minimo INT, IN _carreras_tecnicas_id VARCHAR(8),
		IN _horario_id INT, IN _instructor_id INT, IN _salon_id VARCHAR(5))
	BEGIN
		INSERT INTO cursos (nombre_curso,ciclo,cupo_maximo,cupo_minimo,carreras_tecnicas_id,
		horario_id,instructor_id,salon_id)
		VALUES(_nombre_curso,_ciclo,_cupo_maximo,_cupo_minimo,_carreras_tecnicas_id,_horario_id
		,_instructor_id, _salon_id);
END $$

DROP PROCEDURE IF EXISTS sp_update_cursos;
DELIMITER $$
	CREATE PROCEDURE sp_update_cursos (IN _nombre_curso VARCHAR(255), IN _ciclo YEAR,
		IN _cupo_maximo INT , IN _cupo_minimo INT, IN _carreras_tecnicas_id VARCHAR(8),
		IN _horario_id INT, IN _instructor_id INT, IN _salon_id INT, IN id INT)
	BEGIN
		UPDATE cursos SET
		nombre_curso = _nombre_curso,
		ciclo = _ciclo,
		cupo_maximo = _cupo_maximo,
		cupo_minimo = _cupo_minimo,
		carreras_tecnicas_id = _carreras_tecnicas_id,
		horario_id = _horario_id,
		instructor_id = _instructor_id,
        salon_id = _salon_id
		WHERE
		id = _id;
END $$

DROP PROCEDURE IF EXISTS sp_delete_cursos;
DELIMITER $$
	CREATE PROCEDURE sp_delete_cursos(IN _id INT)
	BEGIN
		DELETE FROM cursos
		WHERE
		id = _id;
END $$

DROP PROCEDURE IF EXISTS sp_read_cursos;
DELIMITER $$
	CREATE PROCEDURE sp_read_cursos()
	BEGIN
		SELECT * FROM cursos;
END $$

DROP PROCEDURE IF EXISTS sp_read_cursos_by_id;
DELIMITER $$
	CREATE PROCEDURE sp_read_cursos_by_id(IN _id INT)
	BEGIN
		SELECT * FROM cursos WHERE id = _id;
END $$

-- ASIGNACION ALUMNOS
DROP PROCEDURE IF EXISTS sp_create_asignacion;
DELIMITER $$
	CREATE PROCEDURE sp_create_asignacion(IN _alumno_id VARCHAR(16),IN _cursos_id INT,IN _fecha_asignacion DATETIME)
	BEGIN
		INSERT INTO asignacion_alumnos (alumno_id,curso_id,fecha_asignacion)
		VALUES(_alumno_id,_cursos_id,_fecha_asignacion);
END $$

DROP PROCEDURE IF EXISTS sp_update_asignacion;
DELIMITER $$
	CREATE PROCEDURE sp_update_asignacion(IN _alumno_id VARCHAR(16), IN _cursos_id INT, IN _fecha_asignacion DATETIME,
		IN _id INT)
	BEGIN
		UPDATE asignacion_alumnos SET
		alumno_id = _alumno_id,
		cursos_id = _cursos_id,
		fecha_asignacion = _fecha_asignacion
		WHERE id = _id;
END $$
    
DROP PROCEDURE IF EXISTS sp_delete_asignacion;
DELIMITER $$
	CREATE PROCEDURE sp_delete_asignacion(IN _id INT)
	BEGIN
		DELETE FROM asignacion_alumnos WHERE id =_id;
END $$ 

DROP PROCEDURE IF EXISTS sp_read_asignacion;
DELIMITER $$
	CREATE PROCEDURE sp_read_asignacion()
	BEGIN
		SELECT * FROM asignacion_alumnos;
END $$ 

DROP PROCEDURE IF EXISTS sp_read_asignacion_by_id;
DELIMITER $$
	CREATE PROCEDURE sp_read_asignacion_by_id(IN _id INT)
	BEGIN
		SELECT * FROM asignacion_alumnos WHERE id = _id;
END $$ 

#------------------ CALLS -----------------------
-- Alumnos
CALL sp_create_alumnos("2021067", "Angel", "Gabriel", NULL, "Sanabria", "Morales");
CALL sp_create_alumnos("2021054", "David", "Josue", NULL, "Quiñonez", "Zeta");
CALL sp_create_alumnos("2021087", "Jose", "Angel", NULL, "Salvatierra", "Caal");
CALL sp_create_alumnos("2021075", "Enrique", "Lolas", NULL, "Jolon", "Tzun");
CALL sp_create_alumnos("2017067", "Miguel", "Query", NULL, "Beltran", "Espaderos");
CALL sp_create_alumnos("2021189", "Julian", "Max", NULL, "Sanabria", "Marroquin");
CALL sp_create_alumnos("2020453", "Leonel", "Luis", NULL, "Gonzalez", "Pedrozo");
CALL sp_create_alumnos("2022012", "Mateo", "Jorge", NULL, "Messi", "Monzon");
CALL sp_create_alumnos("2020132", "Eldrick", "Aldair", NULL, "Rosales", "Morales");
CALL sp_create_alumnos("2019231", "Emily", "Nicole", NULL, "Marroquin", "Guillen");

SELECT * FROM alumnos;
-- Instructores
CALL sp_create_instructores("Juan", "Jose", NULL, "Roman", "Riquelme", "Zona 11","jriquelme@gmail.com","34242343","2000-12-12");
CALL sp_create_instructores("Vanessa", "Kardashan", NULL, "Loera", "Muralles", "Zona 21","vkardashan@gmail.com","81238123","1994-06-21");
CALL sp_create_instructores("Nichole", "Mir", NULL, "Perez", "Santos", "Amatitlan","nmir@gmail.com","23459012","1992-02-22");
CALL sp_create_instructores("Hector", "Noe", NULL, "Zeta", "Gutierrez", "Zona 17","hnoe@gmail.com","84531234","1998-03-19");
CALL sp_create_instructores("Abigail", "Juliana", NULL, "Quinonez", "Bruselas", "Mixco","ajuliana@gmail.com","45093454","1999-07-15");
CALL sp_create_instructores("Juan", "Carlos", NULL, "Villeda", "Flores", "Peten","jcarlos@gmail.com","23423452","1940-10-2");
CALL sp_create_instructores("Reinaldo", "Pablo", NULL, "Salvatierra", "Perez", "Antigua","rpablo@gmail.com","81231234","1996-11-1");
CALL sp_create_instructores("Ronaldinho", "Messi", NULL, "Ambrosio", "Calel", "Izabal","rmessi@gmail.com","23458123","1992-02-22");
CALL sp_create_instructores("Oscar", "Julio", NULL, "Avila", "Bautista", "Alta verapaz","ojulio@gmail.com","56234122","1994-07-27");
CALL sp_create_instructores("Anthony", "Emanuel", NULL, "Tzun", "Cheches", "Quiche","aemanuel@gmail.com","90234321","1995-08-29");

-- SALONES
CALL sp_create_salones("C-23", "Salon grande para Informatica",23,"2-B", 3);
CALL sp_create_salones("C-22", "Salon grande para Informatica",11,"2-C", 2);
CALL sp_create_salones("C-27", "Salon grande para Electricidad",22,"2-A", 3);
CALL sp_create_salones("C-26", "Salon grande para Informatica",19,"1-B", 2);
CALL sp_create_salones("C-32", "Salon grande para Electricidad",12,"4-I", 2);
CALL sp_create_salones("C-44", "Salon grande para Electricidad",18,"2-F", 3);
CALL sp_create_salones("C-65", "Salon grande para Arquitectura",31,"2-E", 1);
CALL sp_create_salones("C-12", "Salon grande para Materias Escolares",30,"1-A", 1);
CALL sp_create_salones("C-13", "Salon grande para Materias Escolares",31,"1-C", 1);
CALL sp_create_salones("C-91", "Salon grande para Laboratorio",10,"5-B", 4);

-- CARRERAS TECNICAS
CALL sp_create_carreras("IN4AM", "Perito en Informatica", "4TO PERITO", "A","Matutina");
CALL sp_create_carreras("IN5BM", "Perito en Informatica", "5TO PERITO", "B","Matutina");
CALL sp_create_carreras("PE4BM", "Perito en Electronica", "4TO PERITO", "B","Matutina");
CALL sp_create_carreras("MV4EM", "Perito en Mecanica", "4TO PERITO", "E","Vespertina");
CALL sp_create_carreras("MV5BM", "Perito en Mecanica", "5TO PERITO", "B","Vespertina");
CALL sp_create_carreras("MV6BM", "Perito en Mecanica", "6TO PERITO", "B","Vespertina");
CALL sp_create_carreras("IN6BM", "Perito en Informatica", "6TO PERITO", "B","Matutina");
CALL sp_create_carreras("PE6BM", "Perito en Electronica", "6TO PERITO", "B","Matutina");
CALL sp_create_carreras("EB6CM", "Perito en Electricidad", "6TO PERITO", "C","Matutina");
CALL sp_create_carreras("EB4AM", "Perito en Electricidad", "4TO PERITO", "A","Matutina");

-- HORARIOS
CALL sp_create_horarios("10:30:00","13:40:00",1,1,1,0,0);
CALL sp_create_horarios("7:45:00","10:00:00",1,0,1,0,0);
CALL sp_create_horarios("9:30:00","11:05:00",0,1,1,0,0);
CALL sp_create_horarios("8:45:00","10:09:00",1,0,1,0,0);
CALL sp_create_horarios("6:35:00","9:30:00",1,0,1,0,0);
CALL sp_create_horarios("10:00:00","11:30:00",0,1,1,0,0);
CALL sp_create_horarios("11:30:00","13:30:00",0,1,1,0,0);
CALL sp_create_horarios("8:30:00","10:50:00",0,1,0,0,0);
CALL sp_create_horarios("9:30:00","11:55:00",0,1,1,0,0);
CALL sp_create_horarios("6:30:00","7:53:00",0,0,1,1,0);

-- CURSOS
CALL sp_create_cursos("Etica Profesional II",2022,30,10,"IN6BM",3,2,"C-23");
CALL sp_create_cursos("Programacion",2022,32,18,"IN4AM",1,5,"C-22");
CALL sp_create_cursos("Artes Visuales",2021,22,11,"IN5BM",4,3,"C-27");
CALL sp_create_cursos("Arquitectura",2020,30,5,"IN6BM",6,2,"C-65");
CALL sp_create_cursos("Quimica",2020,50,10,"PE6BM",9,8,"C-44");
CALL sp_create_cursos("Matematica",2020,40,12,"IN6BM",4,5,"C-12");
CALL sp_create_cursos("Fisica Fundamental",2010,18,10,"MV6BM",7,3,"C-23");
CALL sp_create_cursos("Ciencias Sociales",2021,40,3,"MV5BM",8,2,"C-44");
CALL sp_create_cursos("Mecanica",2021,50,20,"MV4EM",9,9,"C-13");
CALL sp_create_cursos("Electricidad",2022,30,15,"EB6CM",6,2,"C-32");

-- ASIGNACION ALUMNOS
CALL sp_create_asignacion("2021067",5,CURRENT_TIMESTAMP);
CALL sp_create_asignacion("2021054",1,CURRENT_TIMESTAMP);
CALL sp_create_asignacion("2021087",2,CURRENT_TIMESTAMP);
CALL sp_create_asignacion("2021075",4,CURRENT_TIMESTAMP);
CALL sp_create_asignacion("2017067",8,CURRENT_TIMESTAMP);
CALL sp_create_asignacion("2021189",6,CURRENT_TIMESTAMP);
CALL sp_create_asignacion("2019231",9,CURRENT_TIMESTAMP);
CALL sp_create_asignacion("2020453",8,CURRENT_TIMESTAMP);
CALL sp_create_asignacion("2020132",2,CURRENT_TIMESTAMP);
CALL sp_create_asignacion("2022012",3,CURRENT_TIMESTAMP);

