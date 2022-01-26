DROP FUNCTION obtenerTablaProgramasDetalle(INT, INT, INT)
CREATE OR REPLACE FUNCTION obtenerTablaProgramasDetalle(INT, DATE, DATE)
  RETURNS SETOF RECORD 
AS
$BODY$
DECLARE
	   cod_programa		ALIAS FOR $1;
	   fecha_inicio		ALIAS FOR $2;
	   fecha_fin		ALIAS FOR $3;	   
	   SQL			TEXT;
	   registros2		RECORD;
BEGIN
	SQL ='select *
		from
		(
			select p.codigo_programa, p.nombre_actividad, p.fecha_programa, p.fecha_culminacion,
				UPPER(TRIM(e.nombres || '' '' || e.apellido_paterno || '' '' ||e.apellido_materno)) as nombre_persona,
				''Encargado'' as rol
			from programa p
			inner join imparte i
			on p.codigo_programa = i.codigo_programa
			inner join encargado e
			on i.ci = e.ci
						
			union all

			select p.codigo_programa, p.nombre_actividad, p.fecha_programa, p.fecha_culminacion, 
				UPPER(TRIM(ig.nombres || '' '' || ig.apellido_paterno || '' '' ||ig.apellido_materno)) as nombre_encargado,
				''Integrante''
			from programa p
			inner join integra it
			on p.codigo_programa = it.codigo_programa
			inner join integrante ig
			on it.codigo_integrante = ig.codigo_integrante
			
			union all

			select p.codigo_programa, p.nombre_actividad, p.fecha_programa, p.fecha_culminacion, 
				UPPER(TRIM(familia.nombres || '' '' || familia.apellido_paterno || '' '' ||familia.apellido_materno)) as nombre_encargado,
				''Participante''
			from programa p
			inner join participa par
			on p.codigo_programa = par.codigo_programa
			inner JOIN public.afiliado
			ON par.codigo_persona = afiliado.codigo_persona
			inner JOIN public.familia
			ON afiliado.codigo_persona = familia.codigo_persona
		) tabla';
		

		
	
	IF(fecha_inicio IS NOT NULL AND fecha_fin IS NULL) then
		SQL = SQL || ' WHERE fecha_programa BETWEEN ''' || CAST(fecha_inicio AS CHAR(10)) || ''' AND ''' || CAST(fecha_fin AS CHAR(10)) || '''';
	
	ELSIF (cod_programa IS NOT NULL) then
		SQL = SQL || ' WHERE codigo_programa = ' || CAST(cod_programa AS CHAR(100));
	END IF;	

	SQL = SQL || ' order by codigo_programa, rol, nombre_persona';
	FOR registros2 IN EXECUTE SQL			
	LOOP		
		RETURN next registros2;
	END LOOP;
	RETURN;
	
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE
COST 100;

ALTER FUNCTION obtenerTablaProgramasDetalle(INT, DATE, DATE) OWNER TO postgres;	


SELECT * FROM obtenerTablaProgramasDetalle(1, null,null) 
AS
(	
	codigo_program 		INTEGER,
	nombre_actividad 	CHARACTER VARYING(50),
	fecha_program		DATE,
	fecha_culminacion	DATE,
	nombre_persona 		TEXT,
	rol			TEXT
)


select *
from
(
	select p.codigo_programa, p.nombre_actividad, p.fecha_programa, p.fecha_culminacion,
		UPPER(TRIM(e.nombres || ' ' || e.apellido_paterno || ' ' ||e.apellido_materno)) as nombre_persona,
		'encargado' as rol
	from programa p
	inner join imparte i
	on p.codigo_programa = i.codigo_programa
	inner join encargado e
	on i.ci = e.ci

	union all

	select p.codigo_programa, p.nombre_actividad, p.fecha_programa, p.fecha_culminacion, 
		UPPER(TRIM(ig.nombres || ' ' || ig.apellido_paterno || ' ' ||ig.apellido_materno)) as nombre_encargado,
		'integrante'
	from programa p
	inner join integra it
	on p.codigo_programa = it.codigo_programa
	inner join integrante ig
	on it.codigo_integrante = ig.codigo_integrante
	
	union all

	select p.codigo_programa, p.nombre_actividad, p.fecha_programa, p.fecha_culminacion, 
		UPPER(TRIM(familia.nombres || ' ' || familia.apellido_paterno || ' ' ||familia.apellido_materno)) as nombre_encargado,
		'participante'
	from programa p
	inner join participa par
	on p.codigo_programa = par.codigo_programa
	inner JOIN public.afiliado
	ON par.codigo_persona = afiliado.codigo_persona
	inner JOIN public.familia
	ON afiliado.codigo_persona = familia.codigo_persona
) tabla
order by codigo_programa, rol, nombre_persona



WHERE p.codigo_programa = 1
	and p.fecha_programa between '' and ''