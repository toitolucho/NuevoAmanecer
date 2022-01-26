CREATE OR REPLACE FUNCTION obtenerTablaALFABETIZACION(INT, DATE)
  RETURNS SETOF RECORD 
AS
$BODY$
DECLARE
	   numeroProyecto	ALIAS FOR $1;
	   FechaInicio		ALIAS FOR $2;
	   registros2		RECORD;
BEGIN
	RAISE NOTICE 'Filtrando Salidas';	
	FOR registros2 IN 
		select
			(
			SELECT COUNT(*)   
			FROM public.familia 
			INNER JOIN public.tarjeta
			ON tarjeta.numero_familia = familia.numero_familia 
			WHERE SEXO = 'M'
			AND familia.alfabetizado = true
			AND obtenerEdad(familia.fecha_nacimiento, FechaInicio::date) >= 15
			AND tarjeta.numero_proyecto = numeroProyecto
			AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
			) AS masculino,
			(
			SELECT COUNT(*)   
			FROM public.familia 
			INNER JOIN public.tarjeta
			ON tarjeta.numero_familia = familia.numero_familia 
			WHERE SEXO = 'F'
			AND familia.alfabetizado = true
			AND obtenerEdad(familia.fecha_nacimiento, FechaInicio::date) >= 15
			AND tarjeta.numero_proyecto = numeroProyecto
			AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
			) AS femenino,
			(
			SELECT count(*)
			FROM public.afiliado
			INNER JOIN public.familia
			ON afiliado.codigo_persona = familia.codigo_persona
			INNER JOIN public.tarjeta
			ON tarjeta.numero_familia = familia.numero_familia
			WHERE ObtenerEdad(familia.fecha_nacimiento::date, FechaInicio::date) >= 15
			AND sexo = 'M'
			AND tarjeta.numero_proyecto = numeroProyecto
			AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
			) 
			AS totalmasculino,
			(
			SELECT count(*)
			FROM public.afiliado
			INNER JOIN public.familia
			ON afiliado.codigo_persona = familia.codigo_persona
			INNER JOIN public.tarjeta
			ON tarjeta.numero_familia = familia.numero_familia
			WHERE ObtenerEdad(familia.fecha_nacimiento::date, FechaInicio::date) BETWEEN 16 AND 20
			AND sexo = 'F'
			AND tarjeta.numero_proyecto = numeroProyecto
			AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
			) 
			AS totalfemenino,
			(
			SELECT COUNT(*)
			FROM tarjeta
			WHERE tarjeta.numero_proyecto = numeroProyecto
			) as CantidadFamilias
	LOOP		
		RETURN next registros2;
	END LOOP;
	RETURN;	
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE
COST 100;

ALTER FUNCTION obtenerTablaALFABETIZACION(INT, DATE) OWNER TO postgres;	

SELECT 
	masculino,
	femenino,
	totalmasculino,
	totalfemenino,
	CantidadFamilias
FROM obtenerTablaALFABETIZACION(1, NOW()::date)
AS
(
	masculino	BIGINT,
	femenino	BIGINT,
	totalmasculino	BIGINT,
	totalfemenino	BIGINT,
	CantidadFamilias	BIGINT
) ORDER BY 1










