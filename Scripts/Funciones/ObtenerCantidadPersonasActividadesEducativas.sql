SELECT AE.codigo_actividad_educactiva, AE.nombre_actividad_educativa, ObtenerCantidadPersonasActividadesEducativas(AE.codigo_actividad_educactiva,1, NOW()::DATE)
FROM ACTIVIDAD_EDUCATIVA AE
WHERE AE.codigo_actividad_educactiva IN ('K','P','S')
-- AND obtenerEdad(fecha_nacimiento::date, now()::date) <= 15
AND ObtenerNroProyectoDesdeFamilia(numero_familia) = 1



SELECT * FROM ACTIVIDAD_EDUCATIVA



SELECT AE.codigo_actividad_educactiva, AE.nombre_actividad_educativa
FROM ACTIVIDAD_EDUCATIVA AE
LEFT JOIN FAMILIA F
ON F.codigo_actividad_educactiva = AE.codigo_actividad_educactiva
WHERE AE.codigo_actividad_educactiva IN ('K','P','S')
AND ObtenerNroProyectoDesdeFamilia(numero_familia) = 1
AND obtenerEdad(fecha_nacimiento::date, now()::date) <= 15


DROP FUNCTION ObtenerCantidadPersonasActividadesEducativas(CHARACTER VARYING, INT)

CREATE OR REPLACE FUNCTION ObtenerCantidadPersonasActividadesEducativas(CHARACTER VARYING, INT, DATE)
  RETURNS INTEGER AS
$BODY$
DECLARE	
	cod_actividad_educativa ALIAS for $1;
	numero_proyecto2	ALIAS for $2;
	fecha_gestion		ALIAS for $3;
	cantidad		integer;		
BEGIN    
   SELECT COUNT(*) into cantidad 
   FROM TARJETA T
   INNER JOIN FAMILIA F
   ON T.numero_familia = F.numero_familia
   WHERE F.codigo_actividad_educactiva =cod_actividad_educativa
   AND obtenerEdad(fecha_nacimiento::date, fecha_gestion::date) <= 15 
   AND T.numero_proyecto = numero_proyecto2;
   IF(cantidad IS NULL) THEN
	cantidad := 0;
   END IF;
   RETURN cantidad;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION ObtenerCantidadPersonasActividadesEducativas(CHARACTER VARYING, INT, DATE) OWNER TO postgres;

select ObtenerCantidadPersonasActividadesEducativas('K',1, NOW()::DATE)

select * from familia
