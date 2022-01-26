--DROP FUNCTION obtenerTablaVacunasAfiliados(INT, INT)
CREATE OR REPLACE FUNCTION obtenerTablaDesnutricion(character(2))
  RETURNS SETOF RECORD 
AS
$BODY$
DECLARE
	   parametro_filtro	ALIAS FOR $1;	   
	   SQL			TEXT;
	   registros2		RECORD;
BEGIN
	SQL ='	SELECT af.numero_caso, obtener_nombre_completo(f.codigo_persona) as nombre_completo, 
			CASE eccd.sven WHEN ''DL'' THEN ''DESNUTRICION LEVE 1ºGRADO'' WHEN ''NS'' THEN ''NUTRICION SUPERIOR''
			WHEN ''DM'' THEN ''DESNUTRICION MODERADA 2ºGRADO'' WHEN ''DS'' THEN ''DESNUTRICION SEVERA 3ºGRADO'' WHEN ''N'' THEN ''NUTRICION NORMAL''  ELSE ''DESCONOCIDO'' END as seven, eccd.sven
		FROM familia f
		INNER JOIN afiliado af
		on f.codigo_persona = af.codigo_persona
		INNER JOIN ECCD eccd
		on eccd.numero_caso = af.numero_caso';
	--con ci;
	IF(parametro_filtro IS NOT NULL) then
		SQL = SQL || ' where eccd.sven =''' || parametro_filtro || '''';
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

ALTER FUNCTION obtenerTablaDesnutricion(character(2))OWNER TO postgres;	

SELECT DISTINCT * FROM obtenerTablaDesnutricion(null)
AS
(
  numero_caso 		INT,
  nombre_completo 	CHARACTER VARYING,
  sexo	 		TEXT,
  sven	 		character(2)
)


SELECT 	af.numero_caso, obtener_nombre_completo(f.codigo_persona) as nombre_completo, 
	CASE eccd.sven WHEN 'DL' THEN 'DESNUTRICION LEVE 1ºGRADO' WHEN 'NS' THEN 'NUTRICION SUPERIOR'
	WHEN 'DM' THEN 'DESNUTRICION MODERADA 2ºGRADO' WHEN 'DS' THEN 'DESNUTRICION SEVERA 3ºGRADO'  WHEN 'N' THEN 'NUTRICION NORMAL' ELSE 'DESCONOCIDO' END as seven, eccd.sven
FROM familia f
INNER JOIN afiliado af
on f.codigo_persona = af.codigo_persona
INNER JOIN ECCD eccd
on eccd.numero_caso = af.numero_caso