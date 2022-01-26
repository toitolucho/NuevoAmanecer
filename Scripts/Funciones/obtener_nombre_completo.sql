-- Function: obtener_nombre_completo(integer)

-- DROP FUNCTION obtener_nombre_completo(integer);

CREATE OR REPLACE FUNCTION obtener_nombre_completo(integer)
  RETURNS character varying AS
$BODY$
declare	
	id_persona ALIAS for $1;
	nombre_completo character varying;	
begin
   select 
	TRIM(CASE WHEN apellido_paterno IS NULL THEN  '' ELSE UPPER(apellido_paterno) END
	|| ' ' || 
	CASE WHEN apellido_materno IS NULL THEN '' ELSE UPPER(apellido_materno) END
	|| ' ' || 
	CASE WHEN nombres IS NULL THEN '' ELSE UPPER(nombres) END) into nombre_completo
   from familia 
   where codigo_persona = id_persona;
   return nombre_completo;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION obtener_nombre_completo(integer) OWNER TO postgres;
