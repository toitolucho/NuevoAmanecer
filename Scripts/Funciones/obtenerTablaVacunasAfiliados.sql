--DROP FUNCTION obtenerTablaVacunasAfiliados(INT, INT)
CREATE OR REPLACE FUNCTION obtenerTablaVacunasAfiliados(INT, INT)
  RETURNS SETOF RECORD 
AS
$BODY$
DECLARE
	   paramnetro_numero_caso	ALIAS FOR $1;
	   parametro_codigo_vacuna	ALIAS FOR $2;	   
	   SQL			TEXT;
	   registros2		RECORD;
BEGIN
	SQL ='	SELECT 	DISTINCT v.nombre_vacuna2, ''Entre '' || v.edad_meses_minima || '' y '' || v.edad_meses_maxima || '' Meses'' as limite_vacuna, af.numero_caso, 
		obtener_nombre_completo(f.codigo_persona) as nombre_completo, 
		obtenerEdad(f.fecha_nacimiento, now()::date) as Edad,
		obtenerEdadMeses(f.fecha_nacimiento, now()::date) as EdadMeses,
		CASE WHEN f.sexo = ''F'' then ''FEMENINO'' ELSE ''MASCULINO'' END AS sexo,
		CASE WHEN v.codigo_vacuna2 IN
		(
			SELECT v2.codigo_vacuna2 
			FROM afiliado_vacunas v2
			where v2.codigo_persona = f.codigo_persona		
		) then ''Vacunado'' else ''No Vacumnado'' end as esta_vacunado
		FROM vacuna2 v, afiliado af
		INNER JOIN familia f
		ON af.codigo_persona = f.codigo_persona';
	
	IF(paramnetro_numero_caso IS NOT NULL AND parametro_codigo_vacuna IS NOT NULL) then
		SQL = SQL || ' WHERE af.numero_caso =' || CAST(paramnetro_numero_caso AS CHAR(10)) || ' AND obtenerEdad(f.fecha_nacimiento, now()::date) <= 5  AND v.codigo_vacuna2 = ' || CAST(parametro_codigo_vacuna AS CHAR(10));
	ELSIF (paramnetro_numero_caso IS NOT NULL AND parametro_codigo_vacuna IS NULL) then
		SQL = SQL || ' WHERE af.numero_caso =' || CAST(paramnetro_numero_caso AS CHAR(10)) || ' AND obtenerEdad(f.fecha_nacimiento, now()::date) <= 5 ORDER BY 2 ';
	ELSIF (paramnetro_numero_caso IS NULL AND parametro_codigo_vacuna IS NOT NULL) then
		SQL = SQL || ' WHERE v.codigo_vacuna2 = ' || CAST(parametro_codigo_vacuna AS CHAR(10))|| ' ORDER BY 4,3';
	ELSIF (paramnetro_numero_caso IS NULL AND parametro_codigo_vacuna IS NULL) then
		SQL = SQL || '  ORDER BY 4,3 ASC';
		
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

ALTER FUNCTION obtenerTablaVacunasAfiliados(INT, INT) OWNER TO postgres;	

SELECT numero_caso,obtenerEdad(fecha_nacimiento, now()::date) 
FROM AFILIADO a 
inner join familia f 
on f.codigo_persona = a.codigo_persona

SELECT * FROM obtenerTablaVacunasAfiliados(null, 1) 
AS
(	
  nombre_vacuna2 	CHARACTER VARYING(50),
  limite_vacuna 	TEXT,
  numero_caso 		INT,
  nombre_completo 	CHARACTER VARYING,
  Edad		 	INT,
  EdadMeses	 	INT,
  sexo	 		TEXT,
  esta_vacunado		TEXT
)


SELECT 	DISTINCT v.codigo_vacuna2,v.nombre_vacuna2, 'Entre ' || v.edad_meses_minima || ' y ' || v.edad_meses_maxima || ' Meses' as limite_vacuna, af.numero_caso, 
	obtener_nombre_completo(f.codigo_persona) as nombre_completo, 
	obtenerEdad(f.fecha_nacimiento, now()::date) as Edad,
	obtenerEdadMeses(f.fecha_nacimiento, now()::date) as EdadMeses,
	CASE WHEN f.sexo = 'F' then 'FEMENINO' ELSE 'MASCULINO' END AS sexo,
	CASE WHEN v.codigo_vacuna2 IN
	(
		SELECT v2.codigo_vacuna2 
		FROM afiliado_vacunas v2
		where v2.codigo_persona = f.codigo_persona		
	) then 'Vacunado' else null end as esta_vacunado
from vacuna2 v, afiliado af
INNER JOIN familia f
on af.codigo_persona = f.codigo_persona
--AND afv.codigo_vacuna2= v.codigo_Vacuna2
ORDER BY 4,3 ASC

select current_user