--DROP FUNCTION estaTarjetaHabilitada(INT)
CREATE OR REPLACE FUNCTION estaTarjetaHabilitada(INT)
  RETURNS BOOLEAN AS
$BODY$
DECLARE	
	numero_familias ALIAS for $1;	
	estaHabilitada	BOOLEAN;
BEGIN    
	SELECT CASE WHEN T.codigo_estado_tarjeta = 'H' THEN TRUE ELSE FALSE END INTO estaHabilitada
	FROM TARJETA T
	WHERE T.numero_familia = numero_familias;
   	   
   IF(estaHabilitada IS NULL) THEN
	estaHabilitada := FALSE;
   END IF;
   RETURN estaHabilitada;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION estaTarjetaHabilitada(INT) OWNER TO postgres;

--SELECT estaTarjetaHabilitada(1)
