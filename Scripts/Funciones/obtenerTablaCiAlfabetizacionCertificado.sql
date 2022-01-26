--DROP FUNCTION obtenerTablaVacunasAfiliados(INT, INT)
CREATE OR REPLACE FUNCTION obtenerTablaCiAlfabetizacionCertificado(INT, BOOLEAN)
  RETURNS SETOF RECORD 
AS
$BODY$
DECLARE
	   paramnetro_opcion	ALIAS FOR $1;
	   parametro_filtro	ALIAS FOR $2;	   
	   SQL			TEXT;
	   registros2		RECORD;
BEGIN
	SQL ='	SELECT 	af.numero_caso, obtener_nombre_completo(f.codigo_persona) as nombre_completo, 
			CASE WHEN f.con_certificado THEN ''SI'' ELSE ''NO'' END AS con_certificado, 	
			case when f.ci < 0 then null else f.ci end as ci, 
			CASE WHEN f.alfabetizado THEN ''SI'' ELSE ''NO'' END AS alfabetizado, 	
			f.fecha_nacimiento, obtenerEdad(f.fecha_nacimiento, now()::date) as Edad,
			CASE WHEN f.sexo = ''F'' then ''FEMENINO'' ELSE ''MASCULINO'' END AS sexo
		FROM familia f
		INNER JOIN afiliado af
		on f.codigo_persona = af.codigo_persona';
	--con ci;
	IF(paramnetro_opcion = 1 AND parametro_filtro IS NOT NULL) then
		SQL = SQL || ' WHERE f.ci ' || CASE WHEN parametro_filtro THEN ' > 0' ELSE '< 0 ' END;
	ELSIF (paramnetro_opcion = 2 AND parametro_filtro IS NOT NULL) then
		SQL = SQL || ' WHERE f.alfabetizado =' || CAST(parametro_filtro AS CHAR(10));
	ELSIF (paramnetro_opcion = 3 AND parametro_filtro IS NOT NULL) then
		SQL = SQL || ' WHERE f.con_certificado = ' || CAST(parametro_filtro AS CHAR(10));
		
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

ALTER FUNCTION obtenerTablaCiAlfabetizacionCertificado(INT, BOOLEAN) OWNER TO postgres;	


SELECT * FROM obtenerTablaCiAlfabetizacionCertificado(3, FALSE) 
AS
(	
  numero_caso 		INT,
  nombre_completo 	CHARACTER VARYING,
  con_certificado 	TEXT,
  ci			INT, 
  alfabetizado		TEXT, 
  fecha_nacimiento 	DATE,
  Edad		 	INT,  
  sexo	 		TEXT  
)


SELECT 	af.numero_caso, obtener_nombre_completo(f.codigo_persona) as nombre_completo, f.con_certificado, 
	case when f.ci < 0 then null else f.ci end as ci, f.alfabetizado, f.fecha_nacimiento, obtenerEdad(f.fecha_nacimiento, now()::date) as Edad
FROM familia f
INNER JOIN afiliado af
on f.codigo_persona = af.codigo_persona
