-- DROP FUNCTION obtenerTablaProgramas(CHAR(1), DATE, DATE)
CREATE OR REPLACE FUNCTION obtenerTablaProgramas(BOOLEAN, DATE, DATE)
  RETURNS SETOF RECORD 
AS
$BODY$
DECLARE
	   programa_activo	ALIAS FOR $1;--'F' FINALIZADOS, 'E':EJECUCION, NULL O VACIO PARA LISTAR TODOS
	   fecha_inicio		ALIAS FOR $2;
	   fecha_fin		ALIAS FOR $3;	   
	   SQL			TEXT;
	   registros2		RECORD;
BEGIN
	SQL ='SELECT * FROM Programa P';
	
	IF(fecha_inicio IS NOT NULL AND fecha_fin IS NOT NULL) then
		SQL = SQL || ' WHERE fecha_programa BETWEEN ''' || CAST(fecha_inicio AS CHAR(10)) || ''' AND ''' || CAST(fecha_fin AS CHAR(10)) || '''';
	ELSIF (programa_activo IS NOT NULL AND fecha_inicio IS NULL AND fecha_fin IS NULL) then
		SQL = SQL || ' WHERE ' ||  CASE WHEN  programa_activo = true then ' now() BETWEEN P.fecha_programa and P.fecha_culminacion ' ELSE ' NOW() > P.fecha_programa OR  NOW() > P.fecha_culminacion' END;
		
	END IF;	
	RAISE NOTICE 'Consulta individual = %', SQL;
	FOR registros2 IN EXECUTE SQL			
	LOOP		
		RETURN next registros2;
	END LOOP;
	RETURN;
	
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE
COST 100;

ALTER FUNCTION obtenerTablaProgramas(BOOLEAN, DATE, DATE) OWNER TO postgres;	


SELECT * FROM obtenerTablaProgramas(null, null, null) 
AS
(	
  codigo_programa 	INT,
  nombre_actividad 	CHARACTER VARYING(50),
  descripcion 		CHARACTER VARYING(400),
  justificacion 	CHARACTER VARYING(300),
  fecha_programa 	DATE,
  fecha_culminacion 	DATE,
  lugar 		CHARACTER VARYING(20),
  area 			CHARACTER VARYING(100)
)
