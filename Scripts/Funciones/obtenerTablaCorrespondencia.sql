DROP FUNCTION obtenerTablaCorrespondencia(INT, INT, INT)
CREATE OR REPLACE FUNCTION obtenerTablaCorrespondencia(INT, DATE, DATE, INT)
  RETURNS SETOF RECORD 
AS
$BODY$
DECLARE
	   numeroProyecto	ALIAS FOR $1;
	   fecha_inicio		ALIAS FOR $2;
	   fecha_fin		ALIAS FOR $3;
	   num_caso		ALIAS FOR $4;
	   SQL			TEXT;
	   registros2		RECORD;
BEGIN
	SQL ='SELECT   
		  UPPER(TRIM(familia.nombres || '' '' || familia.apellido_paterno || '' '' ||familia.apellido_materno)) AS afiliado, 
		  UPPER(TRIM(padrino.nombre || '' '' || padrino.apellido_paterno || '' '' || padrino.apellido_materno)) as padrino, 
		  afiliado.numero_caso,  
		  padrino.codigo_padrino, 
		  correspondencia.fecha,   
		  correspondencia.descripcion, 
		  CASE WHEN correspondencia.es_afiliado_remitente = TRUE THEN ''REMITENTE : AFILIADO'' ELSE ''REMITENTE : PADRINO'' END AS REMITENTE
		FROM public.familia
		INNER JOIN public.afiliado
		ON afiliado.codigo_persona = familia.codigo_persona
		LEFT JOIN public.correspondencia
		ON correspondencia.numero_caso = afiliado.numero_caso
		INNER JOIN public.padrino
		ON padrino.codigo_padrino = correspondencia.codigo_padrino
		AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = ''H''';
	
	IF(fecha_inicio IS NOT NULL AND num_caso IS NULL) then
		SQL = SQL || ' WHERE fecha BETWEEN ''' || CAST(fecha_inicio AS CHAR(10)) || ''' AND ''' || CAST(fecha_fin AS CHAR(10)) || '''';
	ELSIF (fecha_inicio IS NOT NULL AND num_caso IS NOT NULL) then
		SQL = SQL || ' WHERE fecha BETWEEN ''' || CAST(fecha_inicio AS CHAR(10)) || ''' AND ''' || CAST(fecha_fin AS CHAR(10)) || '''
		AND afiliado.numero_caso = ' || CAST(num_caso AS CHAR(100));
	ELSIF (fecha_inicio IS NULL AND num_caso IS NOT NULL) then
		SQL = SQL || ' WHERE afiliado.numero_caso = ' || CAST(num_caso AS CHAR(100));
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

ALTER FUNCTION obtenerTablaCorrespondencia(INT, DATE, DATE, INT) OWNER TO postgres;	


SELECT * FROM obtenerTablaCorrespondencia(1, '01/01/2000','31/12/2041', 4) 
AS
(	
	afiliado 	TEXT,
	padrino 	TEXT,
	numero_caso 	INTEGER,
	codigo_padrino	INTEGER,
	fecha	 	DATE,
	descripcion	CHARACTER VARYING,
	remitente	TEXT
)

SELECT   
  UPPER(TRIM(familia.nombres || ' ' || familia.apellido_paterno || ' ' ||familia.apellido_materno)) AS afiliado, 
  UPPER(TRIM(padrino.nombre || ' ' || padrino.apellido_paterno || ' ' || padrino.apellido_materno)) as padrino, 
  afiliado.numero_caso,  
  padrino.codigo_padrino, 
  correspondencia.fecha,   
  correspondencia.descripcion, 
  CASE WHEN correspondencia.es_afiliado_remitente = TRUE THEN 'REMITENTE : AFILIADO' ELSE 'REMITENTE : PADRINO' END AS REMITENTE
FROM public.familia
INNER JOIN public.afiliado
ON afiliado.codigo_persona = familia.codigo_persona
LEFT JOIN public.correspondencia
ON correspondencia.numero_caso = afiliado.numero_caso
INNER JOIN public.padrino
ON padrino.codigo_padrino = correspondencia.codigo_padrino
WHERE CAST(afiliado.numero_caso AS CHAR(100)) LIKE 
CASE WHEN num_caso is null then '%%' else '%' || cast(num_caso as CHAR(100)) || '%'
AND CAST(DATE_PART('YEAR', fecha) AS CHAR(4)) LIKE
CASE WHEN fecha_inicio IS NULL THEN '%%' ELSE '%' || cast(fecha_inicio as CHAR(4)) || '%'
	
SELECT CAST(NOW() AS CHAR(10))