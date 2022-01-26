--DROP FUNCTION esAfiliado(INT)
CREATE OR REPLACE FUNCTION esAfiliado(INT)
  RETURNS BOOLEAN AS
$BODY$
DECLARE	
	cod_persona ALIAS for $1;	
	es_Afiliado	BOOLEAN;
BEGIN    
	SELECT CASE WHEN count(*) > 0 THEN TRUE ELSE FALSE END INTO es_Afiliado
	FROM afiliado
	WHERE codigo_persona = cod_persona;
   	   
   IF(es_Afiliado IS NULL) THEN
	es_Afiliado := FALSE;
   END IF;
   RETURN es_Afiliado;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION esAfiliado(INT) OWNER TO postgres;
