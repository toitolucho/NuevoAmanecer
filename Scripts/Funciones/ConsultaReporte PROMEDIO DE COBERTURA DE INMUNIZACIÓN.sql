CREATE OR REPLACE FUNCTION obtenerTablaPROMEDIO_COBERTURA_INMUNIZACION(INT, DATE)
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
		
		SELECT
		(
		SELECT count(*)
		FROM 
		public.familia
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		INNER JOIN public.afiliado
		ON afiliado.codigo_persona = familia.codigo_persona 
		INNER JOIN public.vacuna
		ON afiliado.numero_caso = vacuna.numero_caso
		WHERE obteneredad(familia.fecha_nacimiento, FechaInicio::date) < 1
		and afiliado.vacuna_nino = true
		AND tarjeta.numero_familia = numeroProyecto
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) AS vacunas_completas_tt2,
		(
		SELECT count(*)
		FROM 
		public.familia
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		INNER JOIN public.afiliado
		ON afiliado.codigo_persona = familia.codigo_persona 
		WHERE obteneredad(familia.fecha_nacimiento, FechaInicio::date) < 1
		AND tarjeta.numero_familia = numeroProyecto
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) AS Total_ninos_nacidos_vivos,
		(
		SELECT COUNT(*)
		FROM 
		public.familia
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		INNER JOIN public.afiliado
		ON afiliado.codigo_persona = familia.codigo_persona 
		INNER JOIN public.vacuna
		ON afiliado.numero_caso = vacuna.numero_caso
		WHERE EXTRACT('YEAR' FROM vacuna.fecha_vacuna) = EXTRACT('YEAR' FROM FechaInicio)
		AND EXTRACT('YEAR' FROM vacuna.fecha_vacuna) = EXTRACT('YEAR' FROM FechaInicio)
		AND EXTRACT('YEAR' FROM vacuna.bcg) = EXTRACT('YEAR' FROM FechaInicio)
		AND EXTRACT('YEAR' FROM vacuna.antipolio_i) = EXTRACT('YEAR' FROM FechaInicio)
		AND EXTRACT('YEAR' FROM vacuna.antipolio_1a) = EXTRACT('YEAR' FROM FechaInicio)
		AND EXTRACT('YEAR' FROM vacuna.antipolio_2a) = EXTRACT('YEAR' FROM FechaInicio)
		AND EXTRACT('YEAR' FROM vacuna.antipolio_3a) = EXTRACT('YEAR' FROM FechaInicio)
		AND EXTRACT('YEAR' FROM vacuna.antipolio_refuerzo) = EXTRACT('YEAR' FROM FechaInicio)
		AND EXTRACT('YEAR' FROM vacuna.pentavalente_1a) = EXTRACT('YEAR' FROM FechaInicio)
		AND EXTRACT('YEAR' FROM vacuna.pentavalente_2a) = EXTRACT('YEAR' FROM FechaInicio)
		AND EXTRACT('YEAR' FROM vacuna.pentavalente_ref) = EXTRACT('YEAR' FROM FechaInicio)
		AND EXTRACT('YEAR' FROM vacuna.sarampion) = EXTRACT('YEAR' FROM FechaInicio)
		AND obteneredad(familia.fecha_nacimiento, FechaInicio::date) BETWEEN 1 AND 5
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) AS Total_ninos_entre_1_y_2_vacunas_completas,
		(
		SELECT count(*)
		FROM 
		public.familia
		INNER JOIN public.tarjeta
		ON tarjeta.numero_familia = familia.numero_familia
		INNER JOIN public.afiliado
		ON afiliado.codigo_persona = familia.codigo_persona 
		WHERE obteneredad(familia.fecha_nacimiento, FechaInicio::date) BETWEEN 1 AND 5
		AND tarjeta.numero_familia = numeroProyecto
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = 'H'
		) AS Total_ninos_entre_1_y_2


	LOOP		
		RETURN next registros2;
	END LOOP;
	RETURN;	
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE
COST 100;

ALTER FUNCTION obtenerTablaPROMEDIO_COBERTURA_INMUNIZACION(INT, DATE) OWNER TO postgres;	

SELECT 
	vacunas_completas_tt2,
	Total_ninos_nacidos_vivos,
	Total_ninos_entre_1_y_2_vacunas_completas,
	Total_ninos_entre_1_y_2
FROM obtenerTablaPROMEDIO_COBERTURA_INMUNIZACION(1, NOW()::date)
AS
(
	vacunas_completas_tt2				BIGINT,
	Total_ninos_nacidos_vivos			BIGINT,
	Total_ninos_entre_1_y_2_vacunas_completas	BIGINT,
	Total_ninos_entre_1_y_2				BIGINT
) ORDER BY 1
