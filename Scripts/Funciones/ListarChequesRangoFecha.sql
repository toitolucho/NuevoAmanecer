SELECT 
  afiliado.numero_caso, 
  afiliado.codigo_persona,
  TRIM(familia.nombres) || ' ' || TRIM(familia.apellido_paterno) || ' ' || TRIM(familia.apellido_materno) as Nombre_completo_afiliado, 
  afiliado.nombre_corto,  
  CASE WHEN familia.sexo = 'F' THEN 'FEMENINO' ELSE 'MASCULINO' END AS sexo, 
  familia.fecha_nacimiento, 
  padrino.numero_padrino, 
  TRIM(padrino.nombre) || ' ' || TRIM(padrino.apellido_paterno) || ' ' || TRIM(padrino.apellido_materno) AS nombre_completo_patrocinador, 
  caso.fecha_registro
FROM 
  public.familia 
  INNER JOIN public.afiliado
  ON afiliado.codigo_persona = familia.codigo_persona
  LEFT JOIN public.caso
  ON caso.numero_caso = afiliado.numero_caso
  LEFT JOIN public.padrino
  ON caso.codigo_padrino = padrino.codigo_padrino

ListarAfiliadosPatrocinadoresRangoFecha


--DROP FUNCTION ListarChequesRangoFecha(date, date);
CREATE OR REPLACE FUNCTION ListarChequesRangoFecha(date, date) RETURNS setof record
AS
$body$
   declare      
   fecha_inicio ALIAS FOR $1;   
   fecha_fin ALIAS FOR $2;   
   sql text;   
   registro record;

   
   begin
	
	   if(fecha_inicio is null and fecha_fin is null) then
		sql = '
		  SELECT 
			  afiliado.numero_caso, 
			  afiliado.codigo_persona,
			  TRIM(familia.nombres) || '' '' || TRIM(familia.apellido_paterno) || '' '' || TRIM(familia.apellido_materno) as Nombre_completo_afiliado, 
			  afiliado.nombre_corto,  
			  CASE WHEN familia.sexo = ''F'' THEN ''FEMENINO'' ELSE ''MASCULINO'' END AS sexo, 
			  familia.fecha_nacimiento, 
			  padrino.numero_padrino, 
			  TRIM(padrino.nombre) || '' '' || TRIM(padrino.apellido_paterno) || '' '' || TRIM(padrino.apellido_materno) AS nombre_completo_patrocinador, 
			  caso.fecha_registro
		   FROM 
			  public.familia 
			  INNER JOIN public.afiliado
			  ON afiliado.codigo_persona = familia.codigo_persona
			  LEFT JOIN public.caso
			  ON caso.numero_caso = afiliado.numero_caso
			  LEFT JOIN public.padrino
			  ON caso.codigo_padrino = padrino.codigo_padrino';
	   else
		sql = 'SELECT 
			  afiliado.numero_caso, 
			  afiliado.codigo_persona,
			  TRIM(familia.nombres) || '' '' || TRIM(familia.apellido_paterno) || '' '' || TRIM(familia.apellido_materno) as Nombre_completo_afiliado, 
			  afiliado.nombre_corto,  
			  CASE WHEN familia.sexo = ''F'' THEN ''FEMENINO'' ELSE ''MASCULINO'' END AS sexo, 
			  familia.fecha_nacimiento, 
			  padrino.numero_padrino, 
			  TRIM(padrino.nombre) || '' '' || TRIM(padrino.apellido_paterno) || '' '' || TRIM(padrino.apellido_materno) AS nombre_completo_patrocinador, 
			  caso.fecha_registro
		   FROM 
			  public.familia 
			  INNER JOIN public.afiliado
			  ON afiliado.codigo_persona = familia.codigo_persona
			  LEFT JOIN public.caso
			  ON caso.numero_caso = afiliado.numero_caso
			  LEFT JOIN public.padrino
			  ON caso.codigo_padrino = padrino.codigo_padrino
		   WHERE caso.fecha_registro BETWEEN ''' || fecha_inicio  ||''' AND ''' || fecha_fin ||
		   ''' ORDER BY afiliado.numero_caso';
	   end if;
	
	FOR registro IN execute sql
	loop
	RETURN next registro;
	end loop;
	RETURN;
   end;
$body$ LANGUAGE 'plpgsql' VOLATILE;


SELECT * FROM ListarChequesRangoFecha(NULL,NULL) AS
(
	numero_caso 			INTEGER, 
	codigo_persona 			INTEGER, 
	Nombre_completo_afiliado 	TEXT, 
	nombre_corto 			CHARACTER VARYING(15), 
	sexo 				TEXT, 
	fecha_nacimiento 		DATE, 	
	numero_padrino 			INTEGER, 
	nombre_completo_patrocinador 	TEXT, 
	fecha_registro 			DATE	
)
 