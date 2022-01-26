--DROP FUNCTION ObtenerTallaEdad(DATE, CHAR(1), REAL);
CREATE OR REPLACE FUNCTION ObtenerTallaEdad(DATE, CHARACTER, DOUBLE PRECISION)
  RETURNS CHAR(2) AS
$BODY$
DECLARE	
	parametro_fecha_nacimiento ALIAS for $1;
	parametro_sexo		   ALIAS for $2;
	parametro_talla		   ALIAS for $3;
	talla_edad  CHAR(2);
	vedadMeses  INT;
	vedadAnios  INT;
	vmedia_talla	float;
	vdesviacion_estandar_talla float;
	v_z	float;
	
BEGIN  
   vedadMeses = obtenerEdadMeses(parametro_fecha_nacimiento, now()::date);	
   RAISE NOTICE 'Edad Actual en Meses del Afiliado %', vedadMeses ;	
   IF(vedadMeses <= 24) THEN		
	SELECT  CASE WHEN parametro_sexo = 'M' THEN media_masculino_talla ELSE media_femenino_talla END,
		CASE WHEN parametro_sexo = 'M' THEN desviacion_estandar_masculino_talla ELSE desviacion_estandar_femenino_talla END
		INTO
		vmedia_talla, vdesviacion_estandar_talla
	FROM Seven
	WHERE edadMeses = vedadMeses;
   ELSE
	SELECT	date_part('year', age(now()::date, parametro_fecha_nacimiento)) INTO vedadAnios;
	SELECT  date_part('month', age(now()::date, parametro_fecha_nacimiento)) INTO vedadMeses;
		
	SELECT  CASE WHEN parametro_sexo = 'M' THEN media_masculino_talla ELSE media_femenino_talla END,
		CASE WHEN parametro_sexo = 'M' THEN desviacion_estandar_masculino_talla ELSE desviacion_estandar_femenino_talla END
		INTO
		vmedia_talla, vdesviacion_estandar_talla
	FROM Seven
	WHERE edadMeses = CASE WHEN vedadMeses = 6 OR vedadMeses = 0 THEN (vedadAnios * 12 + vedadMeses) ELSE (vedadAnios * 12) END;
   END IF;
   
   v_z = (parametro_talla - vmedia_talla) / vdesviacion_estandar_talla;   
   RAISE NOTICE 'X = %  DS = %,  z = %', vmedia_talla,  vdesviacion_estandar_talla, v_z;	

   IF(v_z > 1) THEN talla_edad = 'TA'; END IF;
   IF(v_z >= -1 AND v_z <= 1 ) THEN  talla_edad = 'TN'; END IF;
   IF(v_z < -1 ) THEN  talla_edad = 'TB'; END IF;
   
   RETURN talla_edad;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION ObtenerTallaEdad(DATE, CHARACTER, DOUBLE PRECISION) OWNER TO postgres;

select ObtenerTallaEdad('01/06/2008','M',60)


--DROP FUNCTION ObtenerPesoEdad(DATE, CHAR(1), REAL)
CREATE OR REPLACE FUNCTION ObtenerPesoEdad(DATE, CHAR(1), DOUBLE PRECISION)
  RETURNS CHAR(2) AS
$BODY$
DECLARE	
	parametro_fecha_nacimiento ALIAS for $1;
	parametro_sexo		   ALIAS for $2;
	parametro_peso		   ALIAS for $3;
	peso_edad CHAR(2);
	vedadMeses  INT;
	vedadAnios  INT;
	vmedia_peso	float;
	vdesviacion_estandar_peso float;
	v_z	float;	
BEGIN    
   vedadMeses = obtenerEdadMeses(parametro_fecha_nacimiento, now()::date);
   --vedadAnos =  obtenerEdad(parametro_fecha_nacimiento, now()::date);   
		
   IF(vedadMeses <= 24) THEN		
	SELECT  CASE WHEN parametro_sexo = 'M' THEN media_masculino_peso ELSE media_femenino_peso END,
		CASE WHEN parametro_sexo = 'M' THEN desviacion_estandar_masculino_peso ELSE desviacion_estandar_femenino_peso END
		INTO
		vmedia_peso, vdesviacion_estandar_peso
	FROM Seven
	WHERE edadMeses = vedadMeses;
   ELSE
	SELECT	date_part('year', age(now()::date, parametro_fecha_nacimiento)), 
		date_part('month', age(now()::date, parametro_fecha_nacimiento))
		INTO
		vedadAnios, vedadMeses;
	SELECT  CASE WHEN parametro_sexo = 'M' THEN media_masculino_peso ELSE media_femenino_peso END,
		CASE WHEN parametro_sexo = 'M' THEN desviacion_estandar_masculino_peso ELSE desviacion_estandar_femenino_peso END
		INTO
		vmedia_peso, vdesviacion_estandar_peso
	FROM Seven
	WHERE edadMeses = CASE WHEN vedadMeses IN (6,0) THEN (vedadAnios * 12 + vedadMeses) ELSE (vedadAnios * 12) END;
   END IF;

   v_z = (parametro_peso - vmedia_peso) / vdesviacion_estandar_peso;   

   IF(v_z > 1) THEN peso_edad = 'NS'; END IF;
   IF(v_z >= -1 AND v_z <= 1 ) THEN  peso_edad = 'NN'; END IF;
   IF(v_z < -1 ) THEN  peso_edad = 'D'; END IF;
   RETURN peso_edad;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION ObtenerPesoEdad(DATE, CHARACTER, DOUBLE PRECISION) OWNER TO postgres;
SELECT ObtenerPesoEdad('01/01/2011','M',60)
select ObtenerPesoEdad('01/06/2007','M',60)



--select date_part('year', age(now()::date, '1986-10-21'::date)), age(now()::date, '1986-10-21'::date) as DiferenciaTotal, date_part('month', age(now()::date, '1986-10-21'::date)) as TotalMeses; 







