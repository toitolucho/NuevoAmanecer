-- Function: obtenertablaniveles_de_logros_de_aprendizaje_reporte(integer, date)

-- DROP FUNCTION obtenertablaniveles_de_logros_de_aprendizaje_reporte(integer, date);

CREATE OR REPLACE FUNCTION obtenertablaniveles_de_logros_de_aprendizaje_reporte(integer, date)
  RETURNS SETOF record AS
$BODY$
DECLARE
	   numeroProyecto	ALIAS FOR $1;
	   FechaGestion		ALIAS FOR $2;
	   sql 		TEXT; 
	   consulta 	TEXT;
	   condicion 	TEXT;  	   
	   registros2	RECORD; 
	   filtro	TEXT;
	   anio		INT;
DECLARE registros 	RECORD;
BEGIN


RAISE NOTICE 'Filtrando Salidas';

	consulta := ' ';
	sql := ' ';
	SELECT DATE_PART('YEAR', now()) INTO anio;
	FOR registros IN 
	SELECT * FROM ACTIVIDAD_EDUCATIVA  WHERE codigo_actividad_educactiva not in ('SEI','K')
	LOOP

		sql := 'SELECT COUNT(*)
			FROM public.familia 
			INNER JOIN public.tarjeta
			ON tarjeta.numero_familia = familia.numero_familia
			WHERE 
			obtenerEdad(familia.fecha_nacimiento::date, CAST(''31-12-''|| ' || CAST(anio AS CHAR(10))  ||' AS DATE)::DATE) >= 20
			AND tarjeta.numero_proyecto = ' || CAST(numeroProyecto AS CHAR(10)) || '
			AND familia.codigo_actividad_educactiva = ''' || registros.codigo_actividad_educactiva || ''' ';
			--AND familia.sexo = 'F''

		consulta :=  consulta ||	
		    'SELECT (SELECT nombre_actividad_educativa
		     FROM ACTIVIDAD_EDUCATIVA
		     WHERE codigo_actividad_educactiva = ''' || registros.codigo_actividad_educactiva || ''' ) AS nombre_actividad_educativa,'
		     || ' (' || sql ||' AND familia.sexo = ''F'') AS Total_personas_mujeres,'
		     || ' (' || sql ||' AND familia.sexo = ''M'') AS Total_personas_hombres,'		     
		     || ' (' || sql ||' ) AS Total_personas
		     UNION ';
	
	END LOOP;	
	RAISE NOTICE 'Proceso Finalizado';	
	consulta := SUBSTRING(consulta, 0, LENGTH(consulta)-6) ;

	RAISE NOTICE 'Consulta individual = %', consulta;
	FOR registros2 IN execute consulta
	LOOP		
		RETURN next registros2;
	END LOOP;
	RETURN;
	
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION obtenertablaniveles_de_logros_de_aprendizaje_reporte(integer, date) OWNER TO postgres;
