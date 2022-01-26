
CREATE OR REPLACE FUNCTION ObtenerUltimoIdOcupaciones(character varying)
  RETURNS INTEGER AS
$BODY$
DECLARE	
	est ALIAS for $1;
	CodigoProg integer;	
BEGIN    
   SELECT codigo_ocupacion into CodigoProg 
   FROM ocupaciones
   ORDER BY codigo_ocupacion 
   DESC LIMIT 1 OFFSET 0;
   	   
   IF(CodigoProg IS NULL) THEN
	CodigoProg := -1;
   END IF;
   RETURN CodigoProg;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION ObtenerUltimoIdOcupaciones(character varying) OWNER TO postgres;

select ObtenerUltimoIdOcupaciones('')