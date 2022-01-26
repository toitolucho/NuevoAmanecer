--DROP FUNCTION obtenerTablaAfiliadosRangoFechas(BOOLEAN, DATE, DATE)
CREATE OR REPLACE FUNCTION obtenerTablaMiembrosFamilia(INT)
  RETURNS SETOF RECORD 
AS
$BODY$
DECLARE
	   numero_familia	ALIAS FOR $1;	      
	   SQL			TEXT;
	   registros2		RECORD;
BEGIN
	SQL ='	SELECT 
		  proyecto.numero_proyecto, 
		  tarjeta.fecha_registro_tarjeta, 
		  tarjeta.numero_familia, 
		  tarjeta.comunidad, 
		  tarjeta.barrio, 
		  familia.ci, 
		  UPPER(TRIM(familia.nombres || '' '' || familia.apellido_paterno || '' '' ||familia.apellido_materno)) AS miembro,
		  CASE familia.sexo WHEN ''F'' THEN ''FEMENINO'' ELSE ''MASCULINO'' END AS sexo, 
		  familia.fecha_nacimiento, 
		  familia.fecha_registro as fecha_registro_miembro,
		  familia.parentesco,
		  familia.codigo_persona
		FROM 
		  public.tarjeta
		  INNER JOIN public.familia
		  ON tarjeta.numero_familia = familia.numero_familia
		  INNER JOIN public.proyecto
		  ON tarjeta.numero_proyecto = proyecto.numero_proyecto
		  AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = ''H''';
	
	IF(numero_familia IS NOT NULL) then
		SQL = SQL || ' WHERE tarjeta.numero_familia = ' || CAST (numero_familia AS CHAR(100));
		
	
	END IF;	
	FOR registros2 IN EXECUTE SQL			
	LOOP		
		RETURN next registros2;
	END LOOP;
	RETURN;
	
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE
COST 100;

ALTER FUNCTION obtenerTablaMiembrosFamilia(INT) OWNER TO postgres;	


SELECT 	*
FROM obtenerTablaMiembrosFamilia(NULL) 
AS
(	
  numero_proyecto 		INT,
  fecha_registro_tarjeta 	DATE,
  numero_familia 		INT,
  comunidad 			CHARACTER(12),
  barrio 			CHARACTER VARYING(50),
  ci 				INT,
  miembro 			TEXT,
  sexo				TEXT,
  fecha_nacimiento		DATE,
  fecha_registro_miembro	DATE,
  parentesco			CHARACTER VARYING(15),
  codigo_persona		INT
) ORDER BY numero_familia


SELECT 
  proyecto.numero_proyecto, 
  tarjeta.fecha_registro_tarjeta, 
  tarjeta.numero_familia, 
  tarjeta.comunidad, 
  tarjeta.barrio, 
  familia.ci, 
  familia.nombres, 
  familia.apellido_paterno, 
  familia.apellido_materno, 
  familia.sexo, 
  familia.fecha_nacimiento, 
  familia.fecha_registro,
  familia.parentesco,
  familia.codigo_persona
FROM 
  public.tarjeta
  INNER JOIN public.familia
  ON tarjeta.numero_familia = familia.numero_familia
  INNER JOIN public.proyecto
  ON tarjeta.numero_proyecto = proyecto.numero_proyecto
