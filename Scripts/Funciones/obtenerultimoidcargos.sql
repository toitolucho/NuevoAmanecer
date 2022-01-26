-- Function: obtenerultimoidcargos(character varying)

-- DROP FUNCTION obtenerultimoidcargos(character varying);

CREATE OR REPLACE FUNCTION obtenerultimoidcargos(character varying)
  RETURNS integer AS
$BODY$
DECLARE	
	est ALIAS for $1;
	codigo integer;	
BEGIN    
   SELECT count(codigo_cargo) into codigo 
   FROM cargo;   
   	   
   IF(codigo IS NULL) THEN
	codigo := 1;
   END IF;
   RETURN codigo;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION obtenerultimoidcargos(character varying) OWNER TO postgres;
