CREATE FUNCTION funcion_validar_caso() RETURNS trigger AS $funcion_validar_caso$
    BEGIN
    
	IF NEW.numero_caso IS NULL or NEW.numero_caso < 0 THEN
            RAISE EXCEPTION 'El Numero de Caso no puede ser Nulo o Negativo';
        END IF;
        IF NEW.codigo_padrino IS NULL or NEW.codigo_padrino < 0 THEN
            RAISE EXCEPTION 'El codigo de Padrino no puede ser Nulo o Negativo';
        END IF;

        IF ( (SELECT COUNT(*) FROM caso WHERE numero_caso = NEW.numero_caso) > 0 )THEN
            RAISE EXCEPTION 'El numero de Caso % ya se encuentra asignado a un Patrocinador', NEW.numero_caso;
        END IF;


        RETURN NEW;
    END;
$funcion_validar_caso$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_validar_caso BEFORE INSERT ON caso
    FOR EACH ROW EXECUTE PROCEDURE funcion_validar_caso();

