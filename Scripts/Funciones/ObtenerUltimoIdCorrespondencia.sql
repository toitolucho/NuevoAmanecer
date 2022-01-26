
CREATE OR REPLACE FUNCTION ObtenerUltimoIdCorrespondencia(character varying)
  RETURNS INTEGER AS
$BODY$
DECLARE	
	est ALIAS for $1;
	CodigoProg integer;	
BEGIN    
   SELECT codigo_correspondencia into CodigoProg 
   FROM correspondencia 
   ORDER BY codigo_correspondencia 
   DESC LIMIT 1 OFFSET 0;
   	   
   IF(CodigoProg IS NULL) THEN
	CodigoProg := -1;
   END IF;
   RETURN CodigoProg;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION ObtenerUltimoIdCorrespondencia(character varying) OWNER TO postgres;
