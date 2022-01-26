--vacunas completas
SELECT 	DISTINCT v.nombre_vacuna2, 'Entre ' || v.edad_meses_minima || ' y ' || v.edad_meses_maxima || ' Meses' as limite_vacuna, af.numero_caso, 
	obtener_nombre_completo(f.codigo_persona) as nombre_completo, 
	obtenerEdad(f.fecha_nacimiento, now()::date) as Edad,
	obtenerEdadMeses(f.fecha_nacimiento, now()::date) as EdadMeses,
	CASE WHEN f.sexo = 'F' then 'FEMENINO' ELSE 'MASCULINO' END AS sexo,
	CASE WHEN v.codigo_vacuna2 IN
	(
		SELECT v2.codigo_vacuna2 
		FROM afiliado_vacunas v2
		where v2.codigo_persona = f.codigo_persona		
	) then 'Vacunado' else null end as esta_vacunado
from vacuna2 v, afiliado af
INNER JOIN familia f
on af.codigo_persona = f.codigo_persona
--AND afv.codigo_vacuna2= v.codigo_Vacuna2
ORDER BY 4,3 ASC


--desnutricion
SELECT 	af.numero_caso, obtener_nombre_completo(f.codigo_persona) as nombre_completo, 
	CASE eccd.sven WHEN 'DL' THEN 'DESNUTRICION LEVE 1ºGRADO' WHEN 'NS' THEN 'NUTRICION SUPERIOR'
	WHEN 'DM' THEN 'DESNUTRICION MODERADA 2ºGRADO' WHEN 'DS' THEN 'DESNUTRICION SEVERA 3ºGRADO' ELSE 'DESCONOCIDO' END as seven, eccd.sven
FROM familia f
INNER JOIN afiliado af
on f.codigo_persona = af.codigo_persona
INNER JOIN ECCD eccd
on eccd.numero_caso = af.numero_caso

--carnet, certificado, alfabetizado
SELECT 	af.numero_caso, obtener_nombre_completo(f.codigo_persona) as nombre_completo, f.con_certificado, 
	case when f.ci < 0 then null else f.ci end as ci, f.alfabetizado, f.fecha_nacimiento, obtenerEdad(f.fecha_nacimiento, now()::date) as Edad
FROM familia f
INNER JOIN afiliado af
on f.codigo_persona = af.codigo_persona


--aCTIVIDAD EDUCATIVA
SELECT 	af.numero_caso, obtener_nombre_completo(f.codigo_persona) as nombre_completo, f.con_certificado, 
	case when f.ci < 0 then null else f.ci end as ci, f.alfabetizado, ae.nombre_actividad_educativa,
	CASE WHEN f.sexo = 'F' then 'FEMENINO' ELSE 'MASCULINO' END AS sexo
FROM familia f
INNER JOIN afiliado af
on f.codigo_persona = af.codigo_persona
INNER JOIN actividad_educativa ae
on ae.codigo_actividad_educactiva = f.codigo_actividad_educactiva


--Ocupaciones
SELECT 	af.numero_caso, obtener_nombre_completo(f.codigo_persona) as nombre_completo, f.con_certificado, 
	case when f.ci < 0 then null else f.ci end as ci, f.alfabetizado, o.nombre_ocupacion,
	CASE WHEN f.sexo = 'F' then 'FEMENINO' ELSE 'MASCULINO' END AS sexo
FROM familia f
INNER JOIN afiliado af
on f.codigo_persona = af.codigo_persona
INNER JOIN ocupaciones o
on o.codigo_ocupacion = f.codigo_ocupacion


select  t.numero_familia, t.fecha_registro_tarjeta, t.comunidad, 
	CASE WHEN sp.excretas THEN 'Cuenta con Disposicion Excretas' else 'No cuenta con Disposicion Excretas' end as excretas,
	CASE WHEN sp.eda THEN 'Familia con EDA' else 'Familia sin EDA' end as eda,
	CASE WHEN sp.ira THEN 'Familia con al Menos un Miembro con Problemas Respiratorios' else 'Familia sin problemas respiratorios' end as ira,
	CASE WHEN sp.agua THEN 'Familia con Acceso a Agua' else 'Familia sin Acceso a Agua' end as agua,
	CASE WHEN sp.cocina THEN 'Familia con Cocina propia' else 'Familia sin Cocina' end as codina,
	CASE WHEN sp.vivienda IS NULL THEN 'Sin Registro' ELSE sp.vivienda END AS vivienda, 
	CASE WHEN sp.tipo_vivienda IS NULL THEN 'Sin Registro' ELSE sp.tipo_vivienda END AS tipo_vivienda, 
	CASE WHEN sp.material_vivienda IS NULL THEN 'Sin Registro' ELSE sp.material_vivienda END AS material_vivienda
from tarjeta t
LEFT join saludpublica sp
on t.numero_familia = sp.numero_familia
