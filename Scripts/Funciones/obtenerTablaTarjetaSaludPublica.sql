--DROP FUNCTION obtenerTablaTarjetaSaludPublica(INT, INT)
CREATE OR REPLACE FUNCTION obtenerTablaTarjetaSaludPublica(INT, TEXT)
  RETURNS SETOF RECORD 
AS
$BODY$
DECLARE
	   paramnetro_opcion	ALIAS FOR $1;
	   parametro_filtro	ALIAS FOR $2;	   
	   SQL			TEXT;
	   registros2		RECORD;
BEGIN
	SQL ='	select  t.numero_familia, t.fecha_registro_tarjeta, t.comunidad, 
			CASE WHEN sp.excretas THEN ''Cuenta con Disposicion Excretas'' else ''No cuenta con Disposicion Excretas'' end as excretas,
			CASE WHEN sp.eda THEN ''Familia con EDA'' else ''Familia sin EDA'' end as eda,
			CASE WHEN sp.ira THEN ''Familia con al Menos un Miembro con Problemas Respiratorios'' else ''Familia sin problemas respiratorios'' end as ira,
			CASE WHEN sp.agua THEN ''Familia con Acceso a Agua'' else ''Familia sin Acceso a Agua'' end as agua,
			CASE WHEN sp.cocina THEN ''Familia con Cocina propia'' else ''Familia sin Cocina'' end as cocina,
			CASE WHEN sp.vivienda IS NULL THEN ''Sin Registro'' ELSE sp.vivienda END AS vivienda, 
			CASE WHEN sp.tipo_vivienda IS NULL THEN ''Sin Registro'' ELSE sp.tipo_vivienda END AS tipo_vivienda, 
			CASE WHEN sp.material_vivienda IS NULL THEN ''Sin Registro'' ELSE sp.material_vivienda END AS material_vivienda
		from tarjeta t
		LEFT join saludpublica sp
		on t.numero_familia = sp.numero_familia';
	--con ci;
	IF(paramnetro_opcion = 1 AND parametro_filtro IS NOT NULL) then
		SQL = SQL || ( CASE WHEN parametro_filtro ='TRUE' THEN (' WHERE sp.excretas = ' || parametro_filtro)  ELSE  (' WHERE sp.excretas IS NULL OR sp.excretas = ' || parametro_filtro) END ) ||' ORDER BY sp.excretas, t.numero_familia';
	ELSIF (paramnetro_opcion = 2 AND parametro_filtro IS NOT NULL) then
		SQL = SQL || ( CASE WHEN parametro_filtro ='TRUE' THEN (' WHERE sp.eda = ' || parametro_filtro)  ELSE  (' WHERE sp.eda IS NULL OR sp.eda = ' || parametro_filtro) END ) ||' ORDER BY sp.eda, t.numero_familia';
		-- SQL = SQL || ' WHERE sp.eda = ' || parametro_filtro || ' ORDER BY sp.eda';
	ELSIF (paramnetro_opcion = 3 AND parametro_filtro IS NOT NULL) then
		SQL = SQL || ( CASE WHEN parametro_filtro ='TRUE' THEN (' WHERE sp.ira = ' || parametro_filtro)  ELSE  (' WHERE sp.ira IS NULL OR sp.ira = ' || parametro_filtro) END ) ||' ORDER BY sp.ira, t.numero_familia';
		-- SQL = SQL || ' WHERE sp.ira = ' || parametro_filtro || ' ORDER BY sp.ira';
	ELSIF (paramnetro_opcion = 4 AND parametro_filtro IS NOT NULL) then
		SQL = SQL || ( CASE WHEN parametro_filtro ='TRUE' THEN (' WHERE sp.agua = ' || parametro_filtro)  ELSE  (' WHERE sp.agua IS NULL OR sp.agua = ' || parametro_filtro) END ) ||' ORDER BY sp.agua, t.numero_familia';
		-- SQL = SQL || ' WHERE sp.agua = ' || parametro_filtro || ' ORDER BY sp.agua';
	ELSIF (paramnetro_opcion = 5 AND parametro_filtro IS NOT NULL) then
		SQL = SQL || ( CASE WHEN parametro_filtro ='TRUE' THEN (' WHERE sp.cocina = ' || parametro_filtro)  ELSE  (' WHERE sp.cocina IS NULL OR sp.cocina = ' || parametro_filtro) END ) ||' ORDER BY sp.cocina, t.numero_familia';
		-- SQL = SQL || ' WHERE sp.cocina = ''' || parametro_filtro || ''' ORDER BY sp.cocina';
	ELSIF (paramnetro_opcion = 6 AND parametro_filtro IS NOT NULL) then
		-- SQL = SQL || ( CASE WHEN parametro_filtro ='TRUE' THEN (' WHERE sp.vivienda = ' || parametro_filtro)  ELSE  (' WHERE sp.vivienda IS NULL OR sp.vivienda = ' || parametro_filtro) END ) ||' ORDER BY sp.vivienda, t.numero_familia';
		SQL = SQL || ' WHERE sp.vivienda = ''' || parametro_filtro || ''' ORDER BY sp.vivienda';
	ELSIF (paramnetro_opcion = 7 AND parametro_filtro IS NOT NULL) then
		-- SQL = SQL || ( CASE WHEN parametro_filtro ='TRUE' THEN (' WHERE sp.tipo_vivienda = ' || parametro_filtro)  ELSE  (' WHERE sp.tipo_vivienda IS NULL OR sp.tipo_vivienda = ' || parametro_filtro) END ) ||' ORDER BY sp.tipo_vivienda, t.numero_familia';
		SQL = SQL || ' WHERE SP.TIPO_VIVIENDA = ''' || PARAMETRO_FILTRO || ''' ORDER BY SP.TIPO_VIVIENDA';
	ELSIF (paramnetro_opcion = 8 AND parametro_filtro IS NOT NULL) then
		-- SQL = SQL || ( CASE WHEN parametro_filtro ='TRUE' THEN (' WHERE sp.material_vivienda = ' || parametro_filtro)  ELSE  (' WHERE sp.material_vivienda IS NULL OR sp.material_vivienda = ' || parametro_filtro) END ) ||' ORDER BY sp.material_vivienda, t.numero_familia';
		SQL = SQL || ' WHERE sp.material_vivienda = ''' || parametro_filtro || ''' ORDER BY sp.material_vivienda';
	ELSIF (paramnetro_opcion IS NOT NULL AND parametro_filtro IS NULL) then
		paramnetro_opcion = paramnetro_opcion + 3;
		SQL = SQL || ' ORDER BY ' || CAST(paramnetro_opcion AS CHAR(2));
	END IF;	
	RAISE NOTICE 'Consulta individual = %', SQL;
	FOR registros2 IN EXECUTE SQL			
	LOOP		
		RETURN next registros2;
	END LOOP;
	RETURN;
	
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE
COST 100;

ALTER FUNCTION obtenerTablaTarjetaSaludPublica(INT, TEXT) OWNER TO postgres;	


SELECT * FROM obtenerTablaTarjetaSaludPublica(6, 'TRUE') 
AS
(	
  numero_familia		INT,
  fecha_registro_tarjeta 	DATE,
  comunidad		 	CHARACTER(12),
  excretas			TEXT, 
  eda				TEXT, 
  ira			 	TEXT,
  agua			 	TEXT,  
  cocina	 		TEXT,
  vivienda			CHARACTER VARYING,
  tipo_vivienda			CHARACTER VARYING,
  material_vivienda		CHARACTER VARYING
)

select * from saludpublica 
select  t.numero_familia, t.fecha_registro_tarjeta, t.comunidad, 
	CASE WHEN sp.excretas THEN 'Cuenta con Disposicion Excretas' else 'No cuenta con Disposicion Excretas' end as excretas,
	CASE WHEN sp.eda THEN 'Familia con EDA' else 'Familia sin EDA' end as eda,
	CASE WHEN sp.ira THEN 'Familia con al Menos un Miembro con Problemas Respiratorios' else 'Familia sin problemas respiratorios' end as ira,
	CASE WHEN sp.agua THEN 'Familia con Acceso a Agua' else 'Familia sin Acceso a Agua' end as agua,
	CASE WHEN sp.cocina THEN 'Familia con Cocina propia' else 'Familia sin Cocina' end as cocina,
	CASE WHEN sp.vivienda IS NULL THEN 'Sin Registro' ELSE sp.vivienda END AS vivienda, 
	CASE WHEN sp.tipo_vivienda IS NULL THEN 'Sin Registro' ELSE sp.tipo_vivienda END AS tipo_vivienda, 
	CASE WHEN sp.material_vivienda IS NULL THEN 'Sin Registro' ELSE sp.material_vivienda END AS material_vivienda
from tarjeta t
LEFT join saludpublica sp
on t.numero_familia = sp.numero_familia
