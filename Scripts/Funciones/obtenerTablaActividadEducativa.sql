--DROP FUNCTION obtenerTablaVacunasAfiliados(INT, INT)
CREATE OR REPLACE FUNCTION obtenerTablaActividadEducativa(character(3))
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
			f.fecha_nacimiento, obtenerEdad(f.fecha_nacimiento, now()::date) as edad,
			ae.nombre_actividad_educativa,
			CASE WHEN f.sexo = ''F'' then ''FEMENINO'' ELSE ''MASCULINO'' END AS sexo			
		FROM familia f
		INNER JOIN afiliado af
		on f.codigo_persona = af.codigo_persona
		INNER JOIN actividad_educativa ae
		on ae.codigo_actividad_educactiva = f.codigo_actividad_educactiva';
	--con ci;
	IF(parametro_filtro IS NOT NULL) then
		SQL = SQL || ' WHERE ae.codigo_actividad_educactiva = ''' || parametro_filtro || '''';
		
	END IF;	
	SQL = SQL || ' ORDER BY ae.nombre_actividad_educativa, 2 ASC';
	FOR registros2 IN EXECUTE SQL			
	LOOP		
		RETURN next registros2;
	END LOOP;
	RETURN;
	
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE
COST 100;

ALTER FUNCTION obtenerTablaActividadEducativa(character(3))OWNER TO postgres;	


SELECT * FROM obtenerTablaActividadEducativa(NULL) 
AS
(	
  numero_caso 		INT,
  nombre_completo 	CHARACTER VARYING,
  con_certificado 	BOOLEAN,
  ci			INT, 
  alfabetizado		BOOLEAN, 
  fecha_nacimiento 	DATE,
  Edad		 	INT,  
  nombre_actividad_educativa CHARACTER VARYING(30),
  sexo	 		TEXT  
)


SELECT 	af.numero_caso, obtener_nombre_completo(f.codigo_persona) as nombre_completo, f.con_certificado, 
	case when f.ci < 0 then null else f.ci end as ci, f.alfabetizado,
	f.fecha_nacimiento, obtenerEdad(f.fecha_nacimiento, now()::date) as edad,
	ae.nombre_actividad_educativa, ae.codigo_actividad_educactiva,
	CASE WHEN f.sexo = 'F' then 'FEMENINO' ELSE 'MASCULINO' END AS sexo
	
FROM familia f
INNER JOIN afiliado af
on f.codigo_persona = af.codigo_persona
INNER JOIN actividad_educativa ae
on ae.codigo_actividad_educactiva = f.codigo_actividad_educactiva