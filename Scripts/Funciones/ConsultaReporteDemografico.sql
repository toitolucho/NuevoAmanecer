CREATE OR REPLACE FUNCTION obtenerTablaDEMOGRAFICOS(INT, DATE)
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
		FROM public.afiliado		
		INNER JOIN public.familia
		ON afiliado.codigo_persona = familia.codigo_persona
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE ObtenerEdad(familia.fecha_nacimiento::date, FechaGestion::date) < 5  
		AND sexo = 'M'
		AND tarjeta.numero_proyecto = numeroProyecto
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) AS CantidadHombresMenores5,
		(
		SELECT count(*)
		FROM public.afiliado
		INNER JOIN public.familia
		ON afiliado.codigo_persona = familia.codigo_persona
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE ObtenerEdad(familia.fecha_nacimiento::date, FechaGestion::date) BETWEEN 5 AND 15
		AND sexo = 'M'
		AND tarjeta.numero_proyecto = numeroProyecto
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) 
		AS CantidadHombresEntre5y15,
		(
		SELECT count(*)
		FROM public.afiliado
		INNER JOIN public.familia
		ON afiliado.codigo_persona = familia.codigo_persona
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE ObtenerEdad(familia.fecha_nacimiento::date, FechaGestion::date) BETWEEN 16 AND 20
		AND sexo = 'M'
		AND tarjeta.numero_proyecto = numeroProyecto
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) 
		AS CantidadHombresEntre16y20,
		(
		SELECT count(*)
		FROM public.afiliado
		INNER JOIN public.familia
		ON afiliado.codigo_persona = familia.codigo_persona
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE ObtenerEdad(familia.fecha_nacimiento::date, FechaGestion::date) > 20
		AND sexo = 'M'
		AND tarjeta.numero_proyecto = numeroProyecto
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) 
		AS CantidadHombresMayores20, 
		(
		SELECT COUNT(*)
		FROM tarjeta
		) as CantidadFamilias,
		(
		SELECT count(*)
		FROM public.afiliado
		INNER JOIN public.familia
		ON afiliado.codigo_persona = familia.codigo_persona
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE ObtenerEdad(familia.fecha_nacimiento::date,CAST('31-12-'|| CAST(DATE_PART('YEAR', FechaGestion) AS CHAR(4))  AS DATE)::DATE) = 20
		AND sexo = 'M'
		AND tarjeta.numero_proyecto = numeroProyecto
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) 
		AS CantidadHombresIgual20,
		(
		SELECT count(*)
		FROM public.afiliado
		INNER JOIN public.familia
		ON afiliado.codigo_persona = familia.codigo_persona
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE ObtenerEdad(familia.fecha_nacimiento::date, FechaGestion::date) BETWEEN 1 AND 2
		AND sexo = 'M'
		AND tarjeta.numero_proyecto = numeroProyecto
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) 
		AS CantidadHombresEntre_1_2,
		(
		SELECT count(*)
		FROM public.afiliado
		INNER JOIN public.familia
		ON afiliado.codigo_persona = familia.codigo_persona
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE ObtenerEdad(familia.fecha_nacimiento::date, FechaGestion::date) <= 0
		AND sexo = 'M'
		AND tarjeta.numero_proyecto = numeroProyecto
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		)
		AS CantidadHombresNacidosVivos,
		---
		(
		SELECT count(*)
		FROM public.afiliado
		INNER JOIN public.familia
		ON afiliado.codigo_persona = familia.codigo_persona
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE ObtenerEdad(familia.fecha_nacimiento::date, FechaGestion::date) < 5  
		AND sexo = 'F'
		AND tarjeta.numero_proyecto = numeroProyecto
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) AS CantidadMujeresMenores5,
		(
		SELECT count(*)
		FROM public.afiliado
		INNER JOIN public.familia
		ON afiliado.codigo_persona = familia.codigo_persona
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE ObtenerEdad(familia.fecha_nacimiento::date, FechaGestion::date) BETWEEN 5 AND 15
		AND sexo = 'F'
		AND tarjeta.numero_proyecto = numeroProyecto
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) 
		AS CantidadMujeresEntre5y15,
		(
		SELECT count(*)
		FROM public.afiliado
		INNER JOIN public.familia
		ON afiliado.codigo_persona = familia.codigo_persona
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE ObtenerEdad(familia.fecha_nacimiento::date, FechaGestion::date) BETWEEN 16 AND 20
		AND sexo = 'F'
		AND tarjeta.numero_proyecto = numeroProyecto
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) 
		AS CantidadMujeresEntre16y20,
		(
		SELECT count(*)
		FROM public.afiliado
		INNER JOIN public.familia
		ON afiliado.codigo_persona = familia.codigo_persona
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE ObtenerEdad(familia.fecha_nacimiento::date, FechaGestion::date) > 20
		AND sexo = 'F'
		AND tarjeta.numero_proyecto = numeroProyecto
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) 
		AS CantidadMujeresMayores20, 0 as CantidadFamilias2,
		(
		SELECT count(*)
		FROM public.afiliado
		INNER JOIN public.familia
		ON afiliado.codigo_persona = familia.codigo_persona
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE ObtenerEdad(familia.fecha_nacimiento::date,CAST('31-12-'|| CAST(DATE_PART('YEAR', FechaGestion) AS CHAR(4))  AS DATE)::DATE) = 20
		AND sexo = 'F'
		AND tarjeta.numero_proyecto = numeroProyecto
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) 
		AS CantidadMujeresIgual20,
		(
		SELECT count(*)
		FROM public.afiliado
		INNER JOIN public.familia
		ON afiliado.codigo_persona = familia.codigo_persona
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE ObtenerEdad(familia.fecha_nacimiento::date, FechaGestion::date) BETWEEN 1 AND 2
		AND sexo = 'F'
		AND tarjeta.numero_proyecto = numeroProyecto
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) 
		AS CantidadMujeresEntre_1_2,
		(
		SELECT count(*)
		FROM public.afiliado
		INNER JOIN public.familia
		ON afiliado.codigo_persona = familia.codigo_persona
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		WHERE ObtenerEdad(familia.fecha_nacimiento::date, FechaGestion::date) <= 0
		AND sexo = 'M'
		AND tarjeta.numero_proyecto = numeroProyecto
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		)
		AS CantidadMujeresNacidosVivos
	LOOP		
		RETURN next registros2;
	END LOOP;
	RETURN;
	
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE
COST 100;

ALTER FUNCTION obtenerTablaDEMOGRAFICOS(INT, DATE) OWNER TO postgres;	

SELECT 
	CantidadHombresMenores5,
	CantidadHombresEntre5y15,
	CantidadHombresEntre16y20,
	CantidadHombresMayores20,
	CantidadFamilias,
	CantidadHombresIgual20,
	CantidadHombresEntre_1_2,
	CantidadHombresMayores20,
	CantidadHombresNacidosVivos,
	CantidadMujeresMenores5,
	CantidadMujeresEntre5y15,
	CantidadMujeresEntre16y20,
	CantidadMujeresMayores20,
	CantidadFamilias2,
	CantidadMujeresIgual20,
	CantidadMujeresEntre_1_2,
	CantidadMujeresNacidosVivos
FROM obtenerTablaDEMOGRAFICOS(1, NOW()::date)
AS
(
	CantidadHombresMenores5		BIGINT,
	CantidadHombresEntre5y15	BIGINT,
	CantidadHombresEntre16y20	BIGINT,
	CantidadHombresMayores20	BIGINT,
	CantidadFamilias		BIGINT,
	CantidadHombresIgual20		BIGINT,
	CantidadHombresEntre_1_2	BIGINT,
	CantidadHombresNacidosVivos	BIGINT,
	CantidadMujeresMenores5		BIGINT,
	CantidadMujeresEntre5y15	BIGINT,
	CantidadMujeresEntre16y20	BIGINT,
	CantidadMujeresMayores20	BIGINT,
	CantidadFamilias2		INT,
	CantidadMujeresIgual20		BIGINT,
	CantidadMujeresEntre_1_2	BIGINT,
	CantidadMujeresNacidosVivos	BIGINT
) ORDER BY 1



