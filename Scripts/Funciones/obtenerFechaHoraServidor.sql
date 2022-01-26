-- Function: obtenerFechaHoraServidor(character varying)

-- DROP FUNCTION obtenerFechaHoraServidor(character varying);

CREATE OR REPLACE FUNCTION obtenerFechaHoraServidor(character varying)
  RETURNS TIMESTAMP WITH TIME ZONE AS
$BODY$
declare	
	est ALIAS for $1;	
begin       
   return now();
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION obtenerFechaHoraServidor(character varying) OWNER TO postgres;

-- 
-- select obtenerFechaHoraServidor('T')
-- 
-- select  idsolicitud from t_solicitud order by idsolicitud desc limit 1 offset 0;
-- select  idsolicitud from t_solicitud where estado = 'I' order by idsolicitud desc limit 1 offset 0;
-- select  idsolicitud from t_solicitud where estado = 'E' order by idsolicitud desc limit 1 offset 0;
-- 

