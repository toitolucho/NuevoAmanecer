
CREATE OR REPLACE FUNCTION ObtenerUltimoIdUsuarios(character varying)
  RETURNS INTEGER AS
$BODY$
DECLARE	
	est ALIAS for $1;
	CodigoProg integer;	
BEGIN    
   SELECT codigo_usuario into CodigoProg 
   FROM usuarios 
   ORDER BY codigo_usuario
   DESC LIMIT 1 OFFSET 0;
   	   
   IF(CodigoProg IS NULL) THEN
	CodigoProg := -1;
   END IF;
   RETURN CodigoProg;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION ObtenerUltimoIdUsuarios(character varying) OWNER TO postgres;

select * from usuarios