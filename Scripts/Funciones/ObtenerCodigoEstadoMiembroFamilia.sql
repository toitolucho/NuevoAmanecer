
CREATE OR REPLACE FUNCTION ObtenerCodigoEstadoMiembroFamilia(INT, INT)
  RETURNS CHAR(1) AS
$BODY$
DECLARE	
	codigo_persona1 ALIAS for $1;
	numero_caso1 ALIAS for $2;
	estado CHAR(1);	
BEGIN    
   IF(codigo_persona1 IS NOT NULL) THEN
	SELECT 
	  familia.codigo_estado_familia INTO estado
	FROM 
	  public.familia 
	INNER JOIN public.afiliado
	ON afiliado.codigo_persona = familia.codigo_persona
	WHERE familia.codigo_persona = codigo_persona1;
   END IF;
   	   
   IF(codigo_persona1 IS NULL AND  numero_caso1 IS NOT NULL) THEN
	SELECT 
	  familia.codigo_estado_familia INTO estado
	FROM 
	  public.familia 
	INNER JOIN public.afiliado
	ON afiliado.codigo_persona = familia.codigo_persona
	WHERE afiliado.numero_caso = numero_caso1;
   END IF;
   RETURN estado;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION ObtenerCodigoEstadoMiembroFamilia(INT, INT) OWNER TO postgres;


