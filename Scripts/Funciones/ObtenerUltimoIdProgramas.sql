
CREATE OR REPLACE FUNCTION ObtenerUltimoIdProgramas(character varying)
  RETURNS INTEGER AS
$BODY$
DECLARE	
	est ALIAS for $1;
	CodigoProg integer;	
BEGIN    
   SELECT codigo_programa into CodigoProg 
   FROM programa 
   ORDER BY codigo_programa 
   DESC LIMIT 1 OFFSET 0;
   	   
   IF(CodigoProg IS NULL) THEN
	CodigoProg := -1;
   END IF;
   RETURN CodigoProg;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION ObtenerUltimoIdProgramas(character varying) OWNER TO postgres;
