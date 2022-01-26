CREATE OR REPLACE FUNCTION obtenerTablaAlfabetizacion(INT, DATE)
  RETURNS SETOF RECORD 
AS
$BODY$
DECLARE
	   numeroProyecto	ALIAS FOR $1;
	   FechaGestion		ALIAS FOR $2;
	   registros2		RECORD;
BEGIN
	RAISE NOTICE 'Filtrando Salidas';	
	FOR registros2 IN 
	SELECT
		(
		SELECT COUNT(*)
		FROM public.familia
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE sexo = 'M'
		AND familia.alfabetizado = TRUE
		AND tarjeta.numero_proyecto = numeroProyecto
		AND obtenerEdad(familia.fecha_nacimiento, FechaGestion::date) >= 15
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) AS Total_personas_masculino_alfabetizadas,
		(
		SELECT COUNT(*)
		FROM public.familia
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE sexo = 'M'
		AND tarjeta.numero_proyecto = numeroProyecto
		AND obtenerEdad(familia.fecha_nacimiento, FechaGestion::date) >= 15
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) AS Total_personas_masculino,
		(
		SELECT COUNT(*)
		FROM public.familia
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE sexo = 'F'
		AND familia.alfabetizado = TRUE
		AND tarjeta.numero_proyecto = numeroProyecto
		AND obtenerEdad(familia.fecha_nacimiento, FechaGestion::date) >= 15
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) AS Total_personas_femenino_alfabetizadas,
		(
		SELECT COUNT(*)
		FROM public.familia
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE sexo = 'F'
		AND tarjeta.numero_proyecto = numeroProyecto
		AND obtenerEdad(familia.fecha_nacimiento, FechaGestion::date) >= 15
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) AS Total_personas_femenino,
		(
		SELECT COUNT(*)
		FROM public.familia
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE familia.alfabetizado = TRUE
		AND tarjeta.numero_proyecto = numeroProyecto
		AND obtenerEdad(familia.fecha_nacimiento, FechaGestion::date) >= 15
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) AS Total_personas_alfabetizadas,
		(
		SELECT COUNT(*)
		FROM public.familia
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE tarjeta.numero_proyecto = numeroProyecto
		AND obtenerEdad(familia.fecha_nacimiento, FechaGestion::date) >= 15
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) AS Total_personas
	LOOP		
		RETURN next registros2;
	END LOOP;
	RETURN;
	
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE
COST 100;

ALTER FUNCTION obtenerTablaAlfabetizacion(INT, DATE) OWNER TO postgres;	

SELECT Total_personas_masculino_alfabetizadas, Total_personas_masculino, Total_personas_femenino_alfabetizadas, Total_personas_femenino,
	Total_personas_alfabetizadas, Total_personas
FROM obtenerTablaAlfabetizacion(1, now()::date)
AS
(
	Total_personas_masculino_alfabetizadas	BIGINT,
	Total_personas_masculino		BIGINT,
	Total_personas_femenino_alfabetizadas	BIGINT,
	Total_personas_femenino			BIGINT,
	Total_personas_alfabetizadas		BIGINT,
	Total_personas				BIGINT
) ORDER BY 1

