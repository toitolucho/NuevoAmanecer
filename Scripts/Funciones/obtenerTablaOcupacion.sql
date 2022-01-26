--DROP FUNCTION obtenerTablaVacunasAfiliados(INT, INT)
CREATE OR REPLACE FUNCTION obtenerTablaOcupacion(INT)
  RETURNS SETOF RECORD 
AS
$BODY$
DECLARE   
	   parametro_filtro	ALIAS FOR $1;
	   SQL			TEXT;
	   registros2		RECORD;
BEGIN
	SQL ='	SELECT 	af.numero_caso, obtener_nombre_completo(f.codigo_persona) as nombre_completo, f.con_certificado, 
			case when f.ci < 0 then null else f.ci end as ci, f.alfabetizado, 
			f.fecha_nacimiento, obtenerEdad(f.fecha_nacimiento, now()::date) as Edad,
			o.nombre_ocupacion,
			CASE WHEN f.sexo = ''F'' then ''FEMENINO'' ELSE ''MASCULINO'' END AS sexo
		FROM familia f
		INNER JOIN afiliado af
		on f.codigo_persona = af.codigo_persona
		INNER JOIN ocupaciones o
		on o.codigo_ocupacion = f.codigo_ocupacion';
	--con ci;
	IF(parametro_filtro IS NOT NULL) then
		SQL = SQL || ' WHERE o.codigo_ocupacion = ''' || CAST(parametro_filtro AS CHAR(10)) || '''';
		
	END IF;	
	SQL = SQL || ' ORDER BY o.nombre_ocupacion, 2 ASC';
	FOR registros2 IN EXECUTE SQL			
	LOOP		
		RETURN next registros2;
	END LOOP;
	RETURN;
	
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE
COST 100;

ALTER FUNCTION obtenerTablaOcupacion(INT)OWNER TO postgres;	


SELECT * FROM obtenerTablaOcupacion(14) 
AS
(	
  numero_caso 		INT,
  nombre_completo 	CHARACTER VARYING,
  con_certificado 	BOOLEAN,
  ci			INT, 
  alfabetizado		BOOLEAN, 
  fecha_nacimiento 	DATE,
  Edad		 	INT,  
  nombre_actividad_educativa CHARACTER VARYING(25),
  sexo	 		TEXT  
)

SELECT 	af.numero_caso, obtener_nombre_completo(f.codigo_persona) as nombre_completo, f.con_certificado, 
	case when f.ci < 0 then null else f.ci end as ci, f.alfabetizado, 
	f.fecha_nacimiento, obtenerEdad(f.fecha_nacimiento, now()::date) as Edad,
	o.nombre_ocupacion, o.codigo_ocupacion,
	CASE WHEN f.sexo = 'F' then 'FEMENINO' ELSE 'MASCULINO' END AS sexo
FROM familia f
INNER JOIN afiliado af
on f.codigo_persona = af.codigo_persona
INNER JOIN ocupaciones o
on o.codigo_ocupacion = f.codigo_ocupacion