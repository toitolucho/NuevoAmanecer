
CREATE OR REPLACE FUNCTION obtenerultimoidfamilia(character varying)
  RETURNS INTEGER AS
$BODY$
DECLARE	
	est ALIAS for $1;
	codigo integer;	
BEGIN    
   SELECT codigo_persona into codigo 
   FROM familia 
   ORDER BY codigo_persona 
   DESC LIMIT 1 OFFSET 0;
   	   
   IF(codigo IS NULL) THEN
	codigo := -1;
   END IF;
   RETURN codigo;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION obtenerultimoidfamilia(character varying) OWNER TO postgres;
