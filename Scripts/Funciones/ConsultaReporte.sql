
CREATE OR REPLACE FUNCTION obtenerTablaMuertesReporte(INT, DATE)
  RETURNS SETOF RECORD 
AS
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
	SELECT codigo_causa_muerte, nommbre_causa_muerte from Causas_Muerte	
	LOOP
		
		sql := 'SELECT count(*)
			FROM public.familia
			INNER JOIN public.tarjeta
			ON familia.numero_familia = tarjeta.numero_familia
			INNER JOIN public.causas_muerte
			ON causas_muerte.codigo_causa_muerte = familia.codigo_causa_muerte
			WHERE familia.codigo_evento_vital = ''M''
			AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = ''H''
			AND tarjeta.numero_proyecto = ' || CAST(numeroProyecto AS CHAR(10)) || '
			AND causas_muerte.codigo_causa_muerte = ''' || registros.codigo_causa_muerte || '''
			AND obtenerEdad(familia.fecha_nacimiento::date, CAST(''31-12-''|| ' || CAST(anio AS CHAR(10))  ||' AS DATE)::DATE)';

		consulta :=  consulta ||	
		    'SELECT (SELECT causas_muerte.nommbre_causa_muerte  		    
		     FROM causas_muerte
		     WHERE causas_muerte.codigo_causa_muerte = ''' || registros.codigo_causa_muerte || ''' ) AS causa_muerte,'
		     || ' (' || sql ||' < 1) AS menores_1_anio,'
		     || ' (' || sql ||' BETWEEN 1 AND 5) AS menores_entre_1_y_5,'
		     || ' (' || sql ||' BETWEEN 6 AND 15) AS menores_entre_5_y_15,'
		     || ' (' || sql ||' > 15) AS menores_mayores_15
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
LANGUAGE 'plpgsql' VOLATILE
COST 100;

ALTER FUNCTION obtenerTablaMuertesReporte(INT, DATE) OWNER TO postgres;	

SELECT causa_muerte, menores_1_anio, menores_entre_1_y_5, menores_entre_5_y_15, menores_mayores_15 
FROM obtenerTablaMuertesReporte(1, now()::date)
AS
(
	causa_muerte		CHARACTER VARYING(15),
	menores_1_anio		BIGINT,
	menores_entre_1_y_5	BIGINT,
	menores_entre_5_y_15	BIGINT,
	menores_mayores_15	BIGINT
) ORDER BY 1
