
CREATE OR REPLACE FUNCTION ObtenerUltimoIdVacuna2(character varying)
  RETURNS INTEGER AS
$BODY$
DECLARE	
	est ALIAS for $1;
	CodigoProg integer;	
BEGIN    
   SELECT codigo_vacuna2 into CodigoProg 
   FROM vacuna2 
   ORDER BY codigo_vacuna2 
   DESC LIMIT 1 OFFSET 0;
   	   
   IF(CodigoProg IS NULL) THEN
	CodigoProg := -1;
   END IF;
   RETURN CodigoProg;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION ObtenerUltimoIdVacuna2(character varying) OWNER TO postgres;
