CREATE OR REPLACE FUNCTION obtenerTablaSaludPublicaReporte(INT, DATE)
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
		SELECT count(*)
		FROM public.tarjeta
		INNER JOIN public.saludpublica
		ON tarjeta.numero_familia = saludpublica.numero_familia
		WHERE saludpublica.eda = true
		AND tarjeta.numero_familia = numeroProyecto
		AND EXTRACT('YEAR' FROM saludpublica.fecha_registro) = EXTRACT('YEAR' FROM FechaGestion)
		) AS cantidad_EDA,
		(
		SELECT count(*)
		FROM public.tarjeta
		INNER JOIN public.saludpublica
		ON tarjeta.numero_familia = saludpublica.numero_familia
		WHERE saludpublica.ira = true
		AND tarjeta.numero_familia = numeroProyecto
		AND EXTRACT('YEAR' FROM saludpublica.fecha_registro) = EXTRACT('YEAR' FROM FechaGestion)
		)AS Cantidad_IRA,
		(
		SELECT count(*)
		FROM public.tarjeta
		INNER JOIN public.saludpublica
		ON tarjeta.numero_familia = saludpublica.numero_familia
		WHERE saludpublica.agua = true
		AND tarjeta.numero_familia = numeroProyecto
		AND EXTRACT('YEAR' FROM saludpublica.fecha_registro) = EXTRACT('YEAR' FROM FechaGestion)
		) AS Cantidad_Acceso_agua,
		(
		SELECT count(*)
		FROM public.tarjeta
		INNER JOIN public.saludpublica
		ON tarjeta.numero_familia = saludpublica.numero_familia
		WHERE saludpublica.excretas = true
		AND tarjeta.numero_familia = numeroProyecto
		AND EXTRACT('YEAR' FROM saludpublica.fecha_registro) = EXTRACT('YEAR' FROM FechaGestion)
		) AS Cantidad_disposicion_sanitaria,
		(
		SELECT count(*)
		FROM public.tarjeta
		INNER JOIN public.saludpublica
		ON tarjeta.numero_familia = saludpublica.numero_familia
		WHERE saludpublica.vivienda = ''
		AND tarjeta.numero_familia = numeroProyecto
		AND EXTRACT('YEAR' FROM saludpublica.fecha_registro) = EXTRACT('YEAR' FROM FechaGestion)
		) AS Cantidad_vivienda_adecuada	
	LOOP		
		RETURN next registros2;
	END LOOP;
	RETURN;
	
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE
COST 100;

ALTER FUNCTION obtenerTablaSaludPublicaReporte(INT, DATE) OWNER TO postgres;	

SELECT cantidad_EDA, Cantidad_IRA, Cantidad_Acceso_agua, Cantidad_disposicion_sanitaria, Cantidad_vivienda_adecuada 
FROM obtenerTablaSaludPublicaReporte(1, now()::date)
AS
(
	cantidad_EDA			BIGINT,
	Cantidad_IRA			BIGINT,
	Cantidad_Acceso_agua		BIGINT,
	Cantidad_disposicion_sanitaria	BIGINT,
	Cantidad_vivienda_adecuada	BIGINT
) ORDER BY 1
