		
CREATE OR REPLACE FUNCTION listar_formularios_por_usuarios(integer)
  RETURNS SETOF sistema_formularios AS
$BODY$
declare
	id_usuario ALIAS FOR $1;
	rec sistema_formularios;
begin
	for rec in  
		select sistema_formularios.codigo_formulario, nombre_formulario, true as estado, descripcion
		from sistema_formularios 
		INNER JOIN sistema_formularios_permisos_usuarios 
		ON sistema_formularios.codigo_formulario = sistema_formularios_permisos_usuarios.codigo_formulario
		WHERE sistema_formularios_permisos_usuarios.codigo_usuario = id_usuario
		AND sistema_formularios.estado = true
		UNION
		SELECT sistema_formularios.codigo_formulario, nombre_formulario, false as estado, descripcion
		FROM sistema_formularios 
		WHERE sistema_formularios.codigo_formulario
		NOT IN (SELECT sistema_formularios.codigo_formulario
			FROM sistema_formularios 
			INNER JOIN sistema_formularios_permisos_usuarios ON
			sistema_formularios.codigo_formulario = sistema_formularios_permisos_usuarios.codigo_formulario
			WHERE sistema_formularios_permisos_usuarios.codigo_usuario = id_usuario
			AND sistema_formularios.estado = true)
	loop 
		return next rec;
	end loop;
	return;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION listar_formularios_por_usuarios(integer) OWNER TO postgres;


select listar_formularios_por_usuarios(1)