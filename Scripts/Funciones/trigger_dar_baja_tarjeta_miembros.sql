CREATE OR REPLACE FUNCTION funcion_dar_baja_tarjeta_miembros() RETURNS trigger AS $funcion_dar_baja_tarjeta_miembros$
    BEGIN
    
	IF NEW.codigo_estado_tarjeta = 'B' THEN
            UPDATE familia
		set codigo_estado_familia = 'B'
	    WHERE NEW.numero_familia = numero_familia;
        END IF;
        

        RETURN NEW;
    END;
$funcion_dar_baja_tarjeta_miembros$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_dar_baja_tarjeta_miembros AFTER UPDATE ON tarjeta
    FOR EACH ROW EXECUTE PROCEDURE funcion_dar_baja_tarjeta_miembros();