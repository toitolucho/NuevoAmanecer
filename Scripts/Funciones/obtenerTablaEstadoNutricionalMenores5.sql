--DROP FUNCTION obtenerTablaEstadoNutricionalMenores5(INT, DATE, DATE)
CREATE OR REPLACE FUNCTION obtenerTablaEstadoNutricionalMenores5(INT, DATE, DATE)
  RETURNS SETOF RECORD 
AS
$BODY$
DECLARE
	   numeroProyecto	ALIAS FOR $1;
	   fecha_inicio		ALIAS FOR $2;
	   fecha_fin		ALIAS FOR $3;	   
	   SQL			TEXT;
	   cantidad_total_5	INT;
	   registros2		RECORD;
BEGIN
	SELECT 
	  count(*) INTO cantidad_total_5	  
	FROM 
	  public.afiliado, 
	  public.familia, 
	  public.tarjeta
	WHERE 
	  afiliado.codigo_persona = familia.codigo_persona AND
	  tarjeta.numero_familia = familia.numero_familia
	AND obtenerEdad(familia.fecha_nacimiento, now()::date) <= 5
	AND familia.fecha_nacimiento BETWEEN fecha_inicio AND fecha_fin;
	
	SQL ='
		SELECT TEccd.CodigoEccd, TEccd.descripcion, CASE WHEN cantidad IS NULL THEN 0 ELSE cantidad END as  cantidad, CASE WHEN (cantidad IS NULL or total = 0) THEN 0 ELSE cantidad * 100/ TOTAL END as PORCENTAJE
		FROM
		(
			SELECT 1,''NS'' as CodigoEccd, ''NUTRICION SUPERIOR'' as descripcion, '|| CAST(cantidad_total_5 AS CHAR(100)) ||' as total
			UNION SELECT 2,''N'', ''NUTRICION NORMAL'', '|| CAST(cantidad_total_5 AS CHAR(100)) ||'
			UNION SELECT 3,''DL'',''DESNUTRICIÓN LEVE 1er GRADO'', '|| CAST(cantidad_total_5 AS CHAR(100)) ||'
			UNION SELECT 4,''DM'',''DESNUTRICIÓN MODERADA 2º GRADO'', '|| CAST(cantidad_total_5 AS CHAR(100)) ||'
			UNION SELECT 5,''DS'',''DESNUTRICION SEVERA 3er GRADO'', '|| CAST(cantidad_total_5 AS CHAR(100)) ||'
		) TEccd
		LEFT JOIN
		(
			SELECT     
			  EXTRACT(''YEAR'' FROM eccd.fecha_registro),
			  eccd.sven,
			  count(eccd.sven) AS cantidad
			FROM public.tarjeta
			INNER JOIN public.familia
			ON familia.numero_familia = tarjeta.numero_familia
			INNER JOIN public.afiliado
			ON afiliado.codigo_persona = familia.codigo_persona
			INNER JOIN public.eccd
			ON afiliado.numero_caso = eccd.numero_caso
			WHERE tarjeta.numero_proyecto = '|| CAST(numeroProyecto AS CHAR(100)) ||'
			AND eccd.fecha_registro BETWEEN '' '|| CAST(fecha_inicio AS CHAR(10)) ||' '' AND '' '|| CAST(fecha_fin AS CHAR(10)) ||' ''
			AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = ''H''
			GROUP BY EXTRACT(''YEAR'' FROM eccd.fecha_registro), eccd.sven
		) TA
		ON TEccd.CodigoEccd = TA.sven
		';
	
	FOR registros2 IN EXECUTE SQL			
	LOOP		
		RETURN next registros2;
	END LOOP;
	RETURN;
	
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE
COST 100;

ALTER FUNCTION obtenerTablaEstadoNutricionalMenores5(INT, DATE, DATE) OWNER TO postgres;	



SELECT * FROM obtenerTablaEstadoNutricionalMenores5(1, '01/01/2000','31/12/2041') 
AS
(	
	CodigoEccd 	TEXT,
	descripcion 	TEXT,
	cantidad 	BIGINT,
	porcentaje	BIGINT
)


SELECT TEccd.CodigoEccd, TEccd.descripcion, CASE WHEN cantidad IS NULL THEN 0 ELSE cantidad END as  cantidad, CASE WHEN (cantidad IS NULL or total = 0) THEN 0 ELSE cantidad * 100/ TOTAL END as porcentaje
FROM
(
	SELECT 1,'NS' as CodigoEccd, 'NUTRICION SUPERIOR' as descripcion, 10 as total
	UNION SELECT 2,'N', 'NUTRICION NORMAL', 10
	UNION SELECT 3,'DL','DESNUTRICIÓN LEVE 1er GRADO', 10
	UNION SELECT 4,'DM','DESNUTRICIÓN MODERADA 2º GRADO', 10
	UNION SELECT 5,'DS','DESNUTRICION SEVERA 3er GRADO', 10
) TEccd
LEFT JOIN
(
	SELECT     
	  EXTRACT('YEAR' FROM eccd.fecha_registro),
	  eccd.sven,
	  count(eccd.sven) AS cantidad
	FROM public.tarjeta
	INNER JOIN public.familia
	ON familia.numero_familia = tarjeta.numero_familia
	INNER JOIN public.afiliado
	ON afiliado.codigo_persona = familia.codigo_persona
	INNER JOIN public.eccd
	ON afiliado.numero_caso = eccd.numero_caso
	WHERE tarjeta.numero_proyecto = 1
	--AND eccd.fecha_registro BETWEEN '' AND ''
	GROUP BY EXTRACT('YEAR' FROM eccd.fecha_registro), eccd.sven
) TA
ON TEccd.CodigoEccd = TA.sven



