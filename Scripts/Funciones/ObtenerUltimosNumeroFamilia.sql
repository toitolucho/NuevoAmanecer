
CREATE OR REPLACE FUNCTION obtenerultimoidtarjeta(character varying)
  RETURNS INTEGER AS
$BODY$
DECLARE	
	est ALIAS for $1;
	Numero_Tarjeta integer;	
BEGIN    
   SELECT numero_familia into Numero_Tarjeta 
   FROM tarjeta 
   ORDER BY numero_familia 
   DESC LIMIT 1 OFFSET 0;
   	   
   IF(Numero_Tarjeta IS NULL) THEN
	Numero_Tarjeta := -1;
   END IF;
   RETURN Numero_Tarjeta;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION obtenerultimoidtarjeta(character varying) OWNER TO postgres;
