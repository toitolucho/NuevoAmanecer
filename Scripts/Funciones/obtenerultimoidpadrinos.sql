-- Function: obtenerultimoidpadrinos(character varying)

-- DROP FUNCTION obtenerultimoidpadrinos(character varying);

CREATE OR REPLACE FUNCTION ObtenerUltimoIdCargos(character varying)
  RETURNS integer AS
$BODY$
DECLARE	
	est ALIAS for $1;
	codigo integer;	
BEGIN    
   SELECT count(codigo_cargo) into codigo 
   FROM cargo 
   ORDER BY codigo_cargo 
   DESC LIMIT 1 OFFSET 0;
   	   
   IF(codigo IS NULL) THEN
	codigo := 1;
   END IF;
   RETURN codigo;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION ObtenerUltimoIdCargos(character varying) OWNER TO postgres;

