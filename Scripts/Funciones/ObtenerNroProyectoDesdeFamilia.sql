
CREATE OR REPLACE FUNCTION ObtenerNroProyectoDesdeFamilia(INT)
  RETURNS INTEGER AS
$BODY$
DECLARE	
	numero_familia2 ALIAS for $1;
	numero_proyecto2 integer;	
BEGIN    
   SELECT t.numero_proyecto into numero_proyecto2 
   FROM tarjeta t      
   WHERE t.numero_familia = numero_familia2;
   	   
   IF(numero_proyecto2 IS NULL) THEN
	numero_proyecto2 := -1;
   END IF;
   RETURN numero_proyecto2;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION ObtenerNroProyectoDesdeFamilia(INT) OWNER TO postgres;


SELECT ObtenerNroProyectoDesdeFamilia(1)