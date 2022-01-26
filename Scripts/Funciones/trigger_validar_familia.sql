CREATE OR REPLACE FUNCTION funcion_validar_familia() RETURNS trigger AS $funcion_validar_familia$
    BEGIN
    
	IF NEW.ci <> -1 AND NEW.ci IS NOT NULL AND  (SELECT COUNT(*) FROM familia WHERE ci = NEW.ci) > 0 THEN
            RAISE EXCEPTION 'El Numero de Ci ya se encuntra registrado en otro familiar';
        END IF;
        

        RETURN NEW;
    END;
$funcion_validar_familia$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_validar_familia BEFORE INSERT ON familia
    FOR EACH ROW EXECUTE PROCEDURE funcion_validar_familia();

