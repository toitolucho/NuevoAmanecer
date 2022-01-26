--DROP FUNCTION sePuedeAgregarAfiliado(INT)
CREATE OR REPLACE FUNCTION sePuedeAgregarAfiliado(INT)
  RETURNS BOOLEAN AS
$BODY$
DECLARE	
	num_familia ALIAS for $1;	
	sePuedeAgregar	BOOLEAN;
BEGIN    
	SELECT CASE WHEN count(*) >= 2 THEN FALSE ELSE TRUE END INTO sePuedeAgregar
	FROM afiliado
	INNER JOIN familia
	on afiliado.codigo_persona = familia.codigo_persona
	WHERE familia.numero_familia = num_familia;
   	   
   IF(sePuedeAgregar IS NULL) THEN
	sePuedeAgregar := TRUE;
   END IF;
   RETURN sePuedeAgregar;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION sePuedeAgregarAfiliado(INT) OWNER TO postgres;


SELECT sePuedeAgregarAfiliado(3)