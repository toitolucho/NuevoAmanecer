CREATE OR REPLACE FUNCTION funcion_validar_correspondencia() RETURNS trigger AS $funcion_validar_correspondencia$
    BEGIN
    
	IF NEW.codigo_padrino IS NULL or NEW.codigo_padrino < 0 THEN
            RAISE EXCEPTION 'El Codigo que corresponde al patrocinador no puede ser Nulo o Negativo';
        END IF;
        IF NEW.numero_caso IS NULL or NEW.numero_caso < 0 THEN
            RAISE EXCEPTION 'El Numero de Caso correspondiente al Afiliado Actual no puede ser Nulo o Negativo';
        END IF;

        IF ( (SELECT COUNT(*) FROM caso WHERE numero_caso = NEW.numero_caso) <= 0 )THEN
            RAISE EXCEPTION 'El numero de Caso % del Afiliado actual se encuentra asociado a un  Patrocinador', NEW.numero_caso;
        END IF;


        IF ( (SELECT COUNT(*) FROM caso WHERE numero_caso = NEW.numero_caso and codigo_padrino = NEW.codigo_padrino) <= 0 )THEN
            RAISE EXCEPTION 'El numero de Caso % del Afiliado actual se encuentra asignado al Patrocinador %', NEW.numero_caso, NEW.codigo_padrino;
        END IF;

        

        RETURN NEW;
    END;
$funcion_validar_correspondencia$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_validar_correspondencia BEFORE INSERT OR UPDATE ON correspondencia
    FOR EACH ROW EXECUTE PROCEDURE funcion_validar_correspondencia();


    SELECT COUNT(*) FROM caso WHERE numero_caso = 4 and codigo_padrino = 3