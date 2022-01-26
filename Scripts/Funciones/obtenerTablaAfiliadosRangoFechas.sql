--DROP FUNCTION obtenerTablaAfiliadosRangoFechas(BOOLEAN, DATE, DATE)
CREATE OR REPLACE FUNCTION obtenerTablaAfiliadosRangoFechas(BOOLEAN, DATE, DATE, BOOLEAN)
  RETURNS SETOF RECORD 
AS
$BODY$
DECLARE
	   patrocinados		ALIAS FOR $1;
	   fecha_inicio		ALIAS FOR $2;
	   fecha_fin		ALIAS FOR $3;
	   filtro_fecha_caso	ALIAS FOR $4;	   
	   SQL			TEXT;
	   registros2		RECORD;
BEGIN
	SQL ='	SELECT 
		  afiliado.codigo_persona, 
		  proyecto.numero_proyecto, 
		  UPPER(TRIM(familia.nombres || '' '' || familia.apellido_paterno || '' '' ||familia.apellido_materno)) AS afiliado,
		  afiliado.numero_caso, 
		  afiliado.numero_nino, 
		  afiliado.nombre_corto, 
		  caso.fecha_registro AS fecha_registro_caso,
		  familia.fecha_registro AS fecha_registro_tarjeta,
		  familia.fecha_nacimiento
		FROM 
		  public.afiliado
		  INNER JOIN  public.familia
		  ON afiliado.codigo_persona = familia.codigo_persona
		  INNER JOIN public.tarjeta
		  ON familia.numero_familia = tarjeta.numero_familia
		  INNER JOIN public.proyecto
		  ON tarjeta.numero_proyecto = proyecto.numero_proyecto
		  LEFT JOIN public.caso
		  ON caso.numero_caso = afiliado.numero_caso
		  AND ObtenerCodigoEstadoMiembroFamilia(familia.codigo_persona,NULL) = ''H'' ';
	
	IF(fecha_inicio IS NOT NULL AND fecha_fin IS NOT NULL) then
		SQL = SQL || CASE WHEN filtro_fecha_caso = FALSE THEN ' WHERE familia.fecha_registro BETWEEN ''' || CAST(fecha_inicio AS CHAR(10)) || ''' AND ''' || CAST(fecha_fin AS CHAR(10)) ||''''
		ELSE ' WHERE caso.fecha_registro BETWEEN ''' || CAST(fecha_inicio AS CHAR(10)) || ''' AND ''' || CAST(fecha_fin AS CHAR(10)) ||'''' END;
	ELSIF (patrocinados IS NOT NULL AND fecha_inicio IS NULL AND fecha_fin IS NULL) then
		SQL = SQL || ' WHERE ' ||  CASE WHEN  patrocinados = true then 'caso.fecha_registro IS NOT NULL ' ELSE ' caso.fecha_registro IS NULL' END;
		
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

ALTER FUNCTION obtenerTablaAfiliadosRangoFechas(BOOLEAN, DATE, DATE, BOOLEAN) OWNER TO postgres;	


SELECT 	codigo_persona,  
	numero_proyecto,
	afiliado,
	numero_caso,
	numero_nino,
	nombre_corto,
	fecha_registro_caso,
	fecha_registro_tarjeta,
	fecha_nacimiento
FROM obtenerTablaAfiliadosRangoFechas(false, NULL, NULL, NULL) 
AS
(	
  codigo_persona 	INT,
  numero_proyecto 	INT,
  afiliado 		TEXT,
  numero_caso 		INTEGER,
  numero_nino 		INTEGER,
  nombre_corto 		CHARACTER VARYING(15),
  fecha_registro_caso 	DATE,
  fecha_registro_tarjeta	DATE,
  fecha_nacimiento	DATE
)

SELECT 
		  afiliado.codigo_persona, 
		  proyecto.numero_proyecto, 
		  UPPER(TRIM(familia.nombres || ' ' || familia.apellido_paterno || ' ' ||familia.apellido_materno)) AS afiliado,
		  afiliado.numero_caso, 
		  afiliado.numero_nino, 
		  afiliado.nombre_corto, 
		  caso.fecha_registro,
		  familia.fecha_registro,
		  familia.fecha_nacimiento
		FROM 
		  public.afiliado
		  INNER JOIN  public.familia
		  ON afiliado.codigo_persona = familia.codigo_persona
		  INNER JOIN public.tarjeta
		  ON familia.numero_familia = tarjeta.numero_familia
		  INNER JOIN public.proyecto
		  ON tarjeta.numero_proyecto = proyecto.numero_proyecto
		  LEFT JOIN public.caso
		  ON caso.numero_caso = afiliado.numero_caso