CREATE OR REPLACE FUNCTION funcion_seven() RETURNS trigger AS $funcion_seven$

	DECLARE vsexo 			CHARACTER;
		vfecha_nacimiento	DATE;
		vpeso			REAL;
		vtalla			REAL;	
    BEGIN
        
		SELECT sexo, fecha_nacimiento INTO vsexo, vfecha_nacimiento
		FROM familia f
		INNER JOIN afiliado af
		on f.codigo_persona = af.codigo_persona
		AND new.numero_caso = af.numero_caso;


		RAISE NOTICE 'Sexo = % del Afiliado, Fecha de Nacimiento : %', vsexo,  vfecha_nacimiento;	

		-- SELECT NEW.PESO, NEW.TALLA INTO VPESO, VTALLA
-- 		FROM NEW;


		UPDATE eccd
			set peso_talla = ObtenerTallaEdad(vfecha_nacimiento, vsexo, new.talla),
			sven = ObtenerPesoEdad(vfecha_nacimiento, vsexo, new.peso)
		WHERE numero_caso = NEW.numero_caso;
		
		

        RETURN NEW;
    END;
$funcion_seven$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_validar_sevem AFTER INSERT OR UPDATE ON eccd
    FOR EACH ROW EXECUTE PROCEDURE funcion_seven();


