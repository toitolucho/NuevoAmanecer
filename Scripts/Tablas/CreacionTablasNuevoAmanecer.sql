-- create database bdnuevoamanecer;

DROP TABLE uSUARIOS
DROP TABLE imparte;
DROP TABLE integra;
DROP TABLE participa;
DROP TABLE caso;
DROP TABLE integrante;
DROP TABLE encargado;
DROP TABLE programa;
DROP TABLE correspondencia;
DROP TABLE padrino;
DROP TABLE saludpublica;
DROP TABLE vacuna;
DROP TABLE eccd;
DROP TABLE afiliado;
DROP TABLE familiar;
DROP TABLE familia;
DROP TABLE tarjeta;
DROP TABLE proyecto;
DROP table causas_muerte;
DROP table actividad_educativa;
DROP table ocupaciones;
DROP table eventos_vitales;


create table eventos_vitales
(
  codigo_evento_vital CHAR(2),
  nombre_evento_vital VARCHAR(35) UNIQUE,
  CONSTRAINT PK_Eventos_Vitales PRIMARY KEY(codigo_evento_vital)  
);

create table ocupaciones
(
  codigo_ocupacion 	SERIAL,
  nombre_ocupacion 	VARCHAR(25) UNIQUE,
  CONSTRAINT PK_Ocupaciones PRIMARY KEY(codigo_ocupacion)
);

create table actividad_educativa
(
 codigo_actividad_educactiva 	CHAR(3),
 nombre_actividad_educativa 	VARCHAR(30) UNIQUE,
 CONSTRAINT PK_Actividad_Educativa PRIMARY KEY(codigo_actividad_educactiva)
);

create table causas_muerte
(
 codigo_causa_muerte 	CHAR(1),
 nommbre_causa_muerte 	VARCHAR(15) UNIQUE,
 CONSTRAINT PK_causas_muerte PRIMARY KEY(codigo_causa_muerte)
);

create table vacuna2
(
 codigo_vacuna2 	SERIAL,
 nombre_vacuna2 	VARCHAR(50) UNIQUE,
 int edad_meses_minima	INT,
 int edad_meses_maxima	INT,
 descripcion_vacuna2	TEXT, 
 CONSTRAINT PK_vacuna2 PRIMARY KEY(codigo_vacuna2)
 
);

-- 
-- --------------------------------
-- CREATE TABLE proyecto
-- (
--   num_fam integer PRIMARY KEY,
--   nomb_proy varchar (21),
--   num_proy integer,
--   departamento varchar varchar (10),
--   prov_canton varchar (50),
--   localidad varchar (50),
--   fecha_encuesta date,
--   comunidad char(12),
--   barrio varchar (50),
--   num_vivienda integer,--DUDA
--   observaciones varchar(50),
--   plano varchar (100)
-- );
-- 
-- 
-- OTRA ALTERNATIVA
-- 
CREATE TABLE proyecto
(
  numero_proyecto 	INTEGER,
  nombre_proyecto 	VARCHAR (21) UNIQUE,
  departamento 		VARCHAR (10) NOT NULL,
  provincia_canton 	VARCHAR (50) NULL,
  localidad 		VARCHAR (50) NULL,
  CONSTRAINT PK_Proyecto PRIMARY KEY(numero_proyecto)
);

-- CREATE TABLE datos_familia

CREATE TABLE tarjeta 
(
  numero_familia 		SERIAL,
  descripcion			VARCHAR(250),
  fecha_registro_tarjeta 	DATE,
  comunidad 			CHAR(12),
  barrio 			VARCHAR (50),
  numero_vivienda 		INTEGER,
  observaciones 		VARCHAR(50),
  plano_vivienda 		VARCHAR (100),
  numero_proyecto 		INTEGER,
  CONSTRAINT PK_Tarjeta  PRIMARY KEY(numero_familia),
  CONSTRAINT FK_Proyecto FOREIGN KEY(numero_proyecto) REFERENCES proyecto(numero_proyecto)
);
 
---------------------------------

CREATE TABLE familia
(
  codigo_persona		SERIAL,   
  ci 				INTEGER 	NULL,             
  nombres 			VARCHAR(50) 	NOT NULL,
  apellido_paterno 		VARCHAR(50) 	NOT NULL,
  apellido_materno 		VARCHAR(50) 	NULL,  
  parentesco 			VARCHAR(15) 	NOT NULL,
  sexo 				CHAR(1) 	NOT NULL,
  fecha_nacimiento		DATE		NULL,
  con_certificado		BOOLEAN		NULL,
  alfabetizado			BOOLEAN		NULL,
  fecha_alfetizacion 		DATE		NULL,
  codigo_actividad_educactiva 	CHAR(3)		NOT NULL,		 
  codigo_ocupacion 		SMALLINT 	NOT NULL,
  tipo_ocu 			VARCHAR(10)	NOT NULL,					
  codigo_evento_vital 		CHAR(2)		NULL,
  codigo_causa_muerte 		CHAR(1)		NULL,
  numero_familia 		INTEGER		NOT NULL,
  CONSTRAINT PK_Familia PRIMARY KEY (codigo_persona),
  CONSTRAINT FK_Actividad_educativa FOREIGN KEY(codigo_actividad_educactiva)REFERENCES actividad_educativa(codigo_actividad_educactiva),
  CONSTRAINT FK_Ocupaciones	    FOREIGN KEY(codigo_ocupacion)	REFERENCES ocupaciones(codigo_ocupacion),
  CONSTRAINT FK_Eventos_vitales	    FOREIGN KEY(codigo_evento_vital)	REFERENCES eventos_vitales(codigo_evento_vital),
  CONSTRAINT FK_Causas_Muerte	    FOREIGN KEY(codigo_causa_muerte)	REFERENCES causas_muerte(codigo_causa_muerte),
  CONSTRAINT FK_Tarjeta		    FOREIGN KEY(numero_familia)		REFERENCES tarjeta(numero_familia)
  
);
sdfds

CREATE TABLE familiar
(
 vacuna_madre 		BOOLEAN,
 codigo_persona 	INTEGER,
 CONSTRAINT PK_Personas_Fmilia PRIMARY KEY(codigo_persona),
 CONSTRAINT FK_Familia	FOREIGN KEY(codigo_persona) REFERENCES familia(codigo_persona)
);

CREATE TABLE afiliado
(
 numero_caso 		INTEGER UNIQUE, 
 tipo 			VARCHAR(20),
 nombre_corto 		VARCHAR(20),
 vacuna_nino 		BOOLEAN,
 codigo_persona 	INTEGER,
 CONSTRAINT PK_afiliado PRIMARY KEY(codigo_persona),
 CONSTRAINT FK_Familia 	FOREIGN KEY(codigo_persona) REFERENCES familia(codigo_persona)
);


CREATE TABLE eccd
(
  fecha_registro	DATE,
  peso 			REAL,
  talla 		REAL,
  peso_talla 		REAL,
  sven 			CHAR(2),
  numero_caso 		INTEGER,
  CONSTRAINT PK_eccd PRIMARY KEY(fecha_registro,numero_caso),
  CONSTRAINT FK_afiliado FOREIGN KEY(numero_caso) REFERENCES afiliado(numero_caso)
);

CREATE TABLE vacuna
(
  fecha_vacuna 		DATE,
  bcg 			DATE,
  antipolio_i 		DATE,
  antipolio_1a 		DATE,
  antipolio_2a 		DATE,
  antipolio_3a 		DATE,
  antipolio_refuerzo 	DATE,
  pentavalente_1a 	DATE,
  pentavalente_2a 	DATE,
  pentavalente_3a 	DATE,
  pentavalente_ref 	DATE,
  sarampion 		DATE,
  numero_caso 		INTEGER ,
  CONSTRAINT PK_Vacuna	PRIMARY KEY(fecha_vacuna,numero_caso),
  CONSTRAINT FK_Afiliado	FOREIGN KEY(numero_caso) REFERENCES afiliado(numero_caso)
);

CREATE TABLE afiliado_vacunas
(
  codigo_persona	INT,
  codigo_vacuna2	INT,
  fecha_vacuna2		DATE,
  observaciones		TEXT,
  CONSTRAINT PK_afiliado_vacunas PRIMARY KEY(codigo_persona, codigo_vacuna2, fecha_vacuna2),
  CONSTRAINT FK_vacuna2 FOREIGN KEY(codigo_vacuna2) REFERENCES vacuna2(codigo_vacuna2),
  CONSTRAINT FK_afiliado_vac FOREIGN KEY(codigo_persona) REFERENCES afiliado(codigo_persona)
)


CREATE TABLE saludpublica
(
  numero_familia 	INTEGER,
  fecha_registro	DATE,  
  eda 			BOOLEAN,
  ira 			BOOLEAN,
  agua 			BOOLEAN,
  excretas 		BOOLEAN,
  vivienda 		VARCHAR(19),
  tipo_vivienda 	VARCHAR(12),
  material_vivienda 	VARCHAR(8),
  numero_habitaciones 	SMALLINT,
  dormitorios 		SMALLINT,
  cocina 		BOOLEAN,
  CONSTRAINT PK_SaludPublica PRIMARY KEY (numero_familia, fecha_registro),
  CONSTRAINT FK_Tarjeta FOREIGN KEY(numero_familia) REFERENCES tarjeta(numero_familia)
  
);

--me he quedado aqui,

CREATE TABLE padrino
(
  codigo_padrino 	SERIAL,
  numero_padrino 	INTEGER,
  nombre 		VARCHAR(50)	NOT NULL,
  apellido_paterno 	VARCHAR(50)	NOT NULL,
  apellido_materno 	VARCHAR(50)	NULL,
  nombre2 		VARCHAR(50)	NULL,
  apellido_paterno2 	VARCHAR(50)	NULL,
  apellido_materno2 	VARCHAR(50)	NULL,
  CONSTRAINT PK_Padrino PRIMARY KEY(codigo_padrino)
);


CREATE TABLE correspondencia
(
  codigo_correspondencia SERIAL,
  es_afiliado_remitente  BOOLEAN	NOT NULL,  
  descripcion 		 VARCHAR(100)	NULL,
  fecha 		 DATE		NOT NULL,
  tipo 			 CHAR(10)	NULL,
  codigo_padrino 	 INTEGER	NOT NULL,
  numero_caso 		 INTEGER	NOT NULL,
  CONSTRAINT PK_Correspondencia PRIMARY KEY(codigo_correspondencia),
  CONSTRAINT FK_Padrino		FOREIGN KEY(codigo_padrino) REFERENCES padrino(codigo_padrino),
  CONSTRAINT FK_Afiliado	FOREIGN KEY(numero_caso) REFERENCES afiliado(numero_caso)
);


CREATE TABLE programa --PROGRAMA
(
  codigo_programa 	SERIAL,
  nombre_actividad	VARCHAR(50),
  descripcion 		VARCHAR(400),  
  justificacion 	VARCHAR(300),
  fecha_programa	DATE,
  fecha_culminacion	DATE, 
  lugar 		VARCHAR(20),
  CONSTRAINT PK_Programa PRIMARY KEY(codigo_programa)
);


CREATE TABLE encargado 
(
  ci 			SERIAL		NOT NULL,  
  apellido_paterno 	VARCHAR(50)	NOT NULL,
  apellido_materno 	VARCHAR(50)	NULL,
  nombres 		VARCHAR(50)	NOT NULL,
  profesion 		VARCHAR(50)	NULL,
  sexo 			CHAR(1)		NOT NULL,
  edad 			SMALLINT	NULL,
  telefono 		INTEGER		NULL,
  domicilio 		VARCHAR(20)	NULL,
  CONSTRAINT PK_Encargado PRIMARY KEY(ci)
);


CREATE TABLE integrante 
(
  codigo_integrante	SERIAL,
  apellido_paterno 	VARCHAR(50)	NOT NULL,
  apellido_materno 	VARCHAR(50)	NULL,
  nombres 		VARCHAR(50)	NOT NULL, 
  sexo 			CHAR(1)		NOT NULL, 
  edad 			SMALLINT	NULL,
  CONSTRAINT PK_Integrante PRIMARY KEY(codigo_integrante)
);


CREATE TABLE caso
(
 fecha_registro 	DATE	NOT NULL,
 numero_caso 		INTEGER	NOT NULL,
 codigo_padrino 	INTEGER	NOT NULL,
 CONSTRAINT PK_Caso 	PRIMARY KEY(numero_caso,codigo_padrino),
 CONSTRAINT FK_Afiliado FOREIGN KEY(numero_caso) REFERENCES afiliado(numero_caso),
 CONSTRAINT FK_Padrino  FOREIGN KEY(codigo_padrino) REFERENCES padrino(codigo_padrino)
);

CREATE TABLE participa --AFILIADO O FAMILIAR
(
  codigo_persona 	INTEGER,
  codigo_programa 	INTEGER,
  CONSTRAINT PK_Participa PRIMARY KEY(codigo_persona,codigo_programa),
  CONSTRAINT FK_Familia   FOREIGN KEY(codigo_persona) REFERENCES familia(codigo_persona),
  CONSTRAINT FK_Programa  FOREIGN KEY(codigo_programa) REFERENCES programa(codigo_programa)
);

CREATE TABLE integra -- PERSONAS AJENAS NO AFILIADAS
(
  codigo_programa 	INTEGER REFERENCES programa(codigo_programa),
  codigo_integrante 	INTEGER REFERENCES integrante(codigo_integrante),
  CONSTRAINT PK_Integra PRIMARY KEY(codigo_programa,codigo_integrante),
  CONSTRAINT FK_Programa   FOREIGN KEY(codigo_programa)   REFERENCES programa(codigo_programa),
  CONSTRAINT FK_Integrante FOREIGN KEY(codigo_integrante) REFERENCES integrante(codigo_integrante)
);

CREATE TABLE imparte --RESPONSABLE DE LA ACTIVIDAD
(
  codigo_programa 	INTEGER,
  ci 			INTEGER,
  CONSTRAINT PK_Imparte PRIMARY KEY(codigo_programa,ci),
  CONSTRAINT FK_Programa   FOREIGN KEY(codigo_programa) REFERENCES programa(codigo_programa),
  CONSTRAINT FK_Encargado  FOREIGN KEY(ci) 		REFERENCES encargado(ci)
);



CREATE TABLE Usuarios
(
  codigo_usuario	SERIAL,
  nombres		VARCHAR(250)	NOT NULL,
  apellidos		VARCHAR(250)	NOT NULL,
  direccion		VARCHAR(100)	NULL,
  telefono		VARCHAR(50)	NULL,
  nombre_cuenta		VARCHAR(200)	UNIQUE,
  contrasenia		VARCHAR(200)	NOT NULL,
  codigo_estado		CHAR(1)		NOT NULL CHECK(codigo_estado IN ('V','B','S')),
  fecha_registro	DATE		DEFAULT NOW(),
  codigo_tipo_usuario	CHAR(10)	DEFAULT 'C' CHECK (codigo_tipo_usuario) IN ('C','F','R','P','A')), --'C'->COORDINADOR DE AREA,'F'->FACILITADOR DE PROGRAMAS,'R'-> RESPONSABLE DE PATROCINIO,'P'->PSICOLOGIA,'A'->ADMINISTRADOR
  CONSTRAINT PK_Usuarios PRIMARY KEY(codigo_usuario)
)


CREATE TABLE sistema_formularios
(
  codigo_formulario SERIAL NOT NULL,
  nombre_formulario text NOT NULL,
  estado boolean DEFAULT true,
  descripcion text,
  CONSTRAINT primary_key_formularios PRIMARY KEY (codigo_formulario)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE sistema_formularios OWNER TO postgres;
COMMENT ON TABLE sistema_formularios IS 'listado de todos los formularios principales del sistema
para poder asignar permisos dentro de la aplicación';


CREATE TABLE sistema_formularios_permisos_usuarios
(
  codigo_usuario integer NOT NULL,
  codigo_formulario integer NOT NULL,
  permitir_insertar boolean DEFAULT false,
  permitir_editar boolean DEFAULT false,
  pemitir_eliminar boolean DEFAULT false,
  permitir_navegar boolean DEFAULT false,
  permitir_reportes boolean DEFAULT false,
  permitir_anular boolean DEFAULT false  
  CONSTRAINT pk_permisos_formularios PRIMARY KEY (codigo_usuario, codigo_formulario),
  CONSTRAINT fk_usuarios FOREIGN KEY (codigo_usuario)
      REFERENCES usuarios (codigo_usuario) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_formularios FOREIGN KEY (codigo_formulario)
      REFERENCES sistema_formularios (codigo_formulario) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE sistema_formularios_permisos_usuarios OWNER TO postgres;
COMMENT ON TABLE sistema_formularios_permisos_usuarios IS 'almacena todos los permisos de los usuarios que tienen acceso al sistema';



CREATE TABLE cargo
(
codigo_cargo	CHAR(10) NOT NULL,
nombre_cargo	VARCHAR(100),
descripcion		TEXT,
CONSTRAINT PK_Cargo PRIMARY KEY(codigo_cargo)
)


CREATE TABLE sistema_formularios_permisos_cargo
(
  codigo_cargo CHAR(10) NOT NULL,
  codigo_formulario integer NOT NULL,
  permitir_insertar boolean DEFAULT false,
  permitir_editar boolean DEFAULT false,
  pemitir_eliminar boolean DEFAULT false,
  permitir_navegar boolean DEFAULT false,
  permitir_reportes boolean DEFAULT false,
  permitir_anular boolean DEFAULT false, 
  CONSTRAINT pk_plantilla_permisos_formularios PRIMARY KEY (codigo_cargo, codigo_formulario),
  CONSTRAINT fk_cargo FOREIGN KEY (codigo_cargo)
      REFERENCES cargo (codigo_cargo) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_formularios FOREIGN KEY (codigo_formulario)
      REFERENCES sistema_formularios (codigo_formulario) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE sistema_formularios_permisos_cargo OWNER TO postgres;
COMMENT ON TABLE sistema_formularios_permisos_cargo IS 'almacena todos los permisos de los usuarios que tienen acceso al sistema por medio de un cargo';

CREATE TABLE Seven
(
id				serial,
edadMeses			int,
media_masculino_talla			float check (media_masculino_talla >= 0),
desviacion_estandar_masculino_talla	float check (desviacion_estandar_masculino_talla >= 0),
media_masculino_peso			float check (media_masculino_peso >= 0),
desviacion_estandar_masculino_peso	float check (desviacion_estandar_masculino_peso >= 0),
media_femenino_talla			float check (media_femenino_talla >= 0),
desviacion_estandar_femenino_talla 	float check (desviacion_estandar_femenino_talla >= 0),
media_femenino_peso			float check (media_femenino_peso >= 0),
desviacion_estandar_femenino_peso 	float check (desviacion_estandar_femenino_peso >= 0),
CONSTRAINT PK_Seven PRIMARY KEY(id)
)


INSERT INTO usuarios(
            nombres, apellidos, direccion, telefono, nombre_cuenta, 
            contrasenia, codigo_estado)
VALUES( 'Luis Antonio','Molina Yampa', 'Oscar Alfaro','6432195', 'administrador','admin', 'V')
insert into eventos_vitales values('E','Embarazo'); 
insert into eventos_vitales values('NV','Nacidos Vivos'); 
insert into eventos_vitales values('PN','Personas Nuevas'); 
insert into eventos_vitales values('PF','Personas que se fueron'); 
insert into eventos_vitales values('M','Muertes');
insert into eventos_vitales values('N','Ninguno'); 
 
insert into ocupaciones values(1,'Albanil');
insert into ocupaciones values(2,'Chofer');
insert into ocupaciones values(3,'Comerciante minorista');
insert into ocupaciones values(4,'Cuidador o Cereno');
insert into ocupaciones values(5,'Vende en Mercado');
insert into ocupaciones values(6,'Pofesor');
insert into ocupaciones values(7,'Recolector Arenales');
insert into ocupaciones values(8,'Lavandera');
insert into ocupaciones values(9,'Empleada Domestica');
insert into ocupaciones values(10,'Vendedor Ambulante');
insert into ocupaciones values(11,'Labores de Casa');
insert into ocupaciones values(12,'Agricultor');
insert into ocupaciones values(13,'Otros');
insert into ocupaciones values(14,'Ninguno');


insert into actividad_educativa values('SEI','Educacion Inicial');
insert into actividad_educativa values('K','Kinder');
insert into actividad_educativa values('P','Primaria');
insert into actividad_educativa values('S','Secundaria');
insert into actividad_educativa values('U','Universidad');
insert into actividad_educativa values('INS','Instituto Normal Superior');
insert into actividad_educativa values('FM','Formacion Media');
insert into actividad_educativa values('FT','Diplomado de Formacion Tecnica');
insert into actividad_educativa values('N','Ninguno');
insert into actividad_educativa values('R','Repeticion');
insert into actividad_educativa values('D','Desercion');

insert into causas_muerte values('A','Accidente');
insert into causas_muerte values('B','Sida');
insert into causas_muerte values('C','IRA');
insert into causas_muerte values('D','Cancer');
insert into causas_muerte values('E','Dengue');
insert into causas_muerte values('F','Diarrea');
insert into causas_muerte values('G','Difteria');
insert into causas_muerte values('H','Malaria');
insert into causas_muerte values('I','Parto');
insert into causas_muerte values('J','Sarampion');
insert into causas_muerte values('K','Tetanos Neo');
insert into causas_muerte values('L','Vejez');
insert into causas_muerte values('M','Otros');
insert into causas_muerte values('N','Pertusis');
insert into causas_muerte values('O','TB');
insert into causas_muerte values('P','Desconocida');
insert into causas_muerte values('R','Materna');


insert into cargo values('C','COORDINADOR DE AREA','');
insert into cargo values('F','FACILITADOR DE PROGRAMAS','');
insert into cargo values('R','RESPONSABLE DE PATROCINIO','');
insert into cargo values('P','PSICOLOGIA','');
insert into cargo values('A','ADMINISTRADOR','');

INSERT INTO proyecto(
            numero_proyecto, nombre_proyecto, departamento, provincia_canton, 
            localidad)
    VALUES (1, 'NUEVO AMANECER', 'TARIJA', 'MENDEZ','TARIJA');


-- Insert into afiliado values (1,'Sil','Villa','Ra','Padre','M','2003-04-20',true,false,null,'N',14,'null','N',null,2,1,1,'Patrocinado','s');
select * from proyecto
select * from tarjeta
select * from familia