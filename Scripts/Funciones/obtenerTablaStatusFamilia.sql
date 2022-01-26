CREATE OR REPLACE FUNCTION obtenerTablaStatusFamilia(INT, DATE, DATE)
  RETURNS SETOF RECORD 
AS
$BODY$
DECLARE
	   numeroProyecto	ALIAS FOR $1;
	   FechaInicio		ALIAS FOR $2;
	   FechaFin		ALIAS FOR $3;
	   registros2		RECORD;
BEGIN
	IF(FechaInicio IS NULL) THEN
	    FechaInicio = '01/01/2000';
	END IF;

	IF(FechaFin IS NULL) THEN
	    FechaFin = now()::date;
	END IF;
	   
	RAISE NOTICE 'Filtrando Salidas';	
	FOR registros2 IN 
		SELECT 
			(
			SELECT COUNT(*)  
			FROM public.saludpublica
			INNER JOIN public.tarjeta
			ON tarjeta.numero_familia = saludpublica.numero_familia
			WHERE 
			tarjeta.numero_proyecto = 1
			AND saludpublica.eda = TRUE
			AND saludpublica.fecha_registro between FechaInicio and FechaFin
			) as eda,
			(
			SELECT COUNT(*)
			FROM public.saludpublica
			INNER JOIN public.tarjeta
			ON tarjeta.numero_familia = saludpublica.numero_familia
			WHERE 
			tarjeta.numero_proyecto = 1
			AND saludpublica.ira = TRUE
			AND saludpublica.fecha_registro between FechaInicio and FechaFin
			) AS ira,
			(
			SELECT COUNT(*)
			FROM public.saludpublica
			INNER JOIN public.tarjeta
			ON tarjeta.numero_familia = saludpublica.numero_familia
			WHERE 
			tarjeta.numero_proyecto = 1
			AND saludpublica.agua = TRUE
			AND saludpublica.fecha_registro between FechaInicio and FechaFin
			) as agua,
			(
			SELECT COUNT(*)
			FROM public.saludpublica
			INNER JOIN public.tarjeta
			ON tarjeta.numero_familia = saludpublica.numero_familia
			WHERE 
			tarjeta.numero_proyecto = 1
			AND saludpublica.excretas = TRUE
			AND saludpublica.fecha_registro between FechaInicio and FechaFin
			) as excretas,
			(
			SELECT COUNT(*)
			FROM public.saludpublica
			INNER JOIN public.tarjeta
			ON tarjeta.numero_familia = saludpublica.numero_familia
			WHERE 
			tarjeta.numero_proyecto = 1
			AND saludpublica.cocina = TRUE
			AND saludpublica.fecha_registro between FechaInicio and FechaFin
			) as cocina,
			(
			SELECT COUNT(*)
			FROM public.saludpublica
			INNER JOIN public.tarjeta
			ON tarjeta.numero_familia = saludpublica.numero_familia
			WHERE 
			tarjeta.numero_proyecto = 1
			AND saludpublica.material_vivienda = 'Ladrillo'
			AND saludpublica.fecha_registro between FechaInicio and FechaFin
			) as material_vivienda,
			(
			SELECT COUNT(*)
			FROM public.tarjeta			
			WHERE 
			tarjeta.numero_proyecto = 1			
			AND fecha_registro_tarjeta between FechaInicio and FechaFin
			) as total	
	LOOP		
		RETURN next registros2;
	END LOOP;
	RETURN;	
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE
COST 100;

ALTER FUNCTION obtenerTablaStatusFamilia(INT, DATE, DATE) OWNER TO postgres;	

SELECT 
	eda,
	ira,
	agua,
	excretas,
	cocina,
	material_vivienda,
	total
--FROM obtenerTablaStatusFamilia(1, '01/01/2000', NOW()::date)
FROM obtenerTablaStatusFamilia(1, NULL, NULL)
AS
(
	eda			BIGINT,
	ira			BIGINT,
	agua			BIGINT,
	excretas		BIGINT,
	cocina			BIGINT,
	material_vivienda	BIGINT,
	total			BIGINT
	
) ORDER BY 1








