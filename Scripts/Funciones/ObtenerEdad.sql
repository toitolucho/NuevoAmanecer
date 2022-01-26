CREATE OR REPLACE FUNCTION ObtenerEdad(date, date) RETURNS INTEGER
AS
$body$
   declare      
   fecha_inicio ALIAS FOR $1;   
   fecha_fin ALIAS FOR $2;    
   edad INTEGER; 
   begin	   
	--SELECT EXTRACT(YEAR FROM  age(date '1986-10-21')) INTO edad;
	SELECT DATE_PART('YEAR', age(fecha_fin::date, fecha_inicio::date)) INTO edad; 
	RETURN edad;
   end;
$body$ LANGUAGE 'plpgsql' VOLATILE;


SELECT ObtenerEdad('1987-10-21',now()::date)


SELECT age(timestamp '2001-04-10', timestamp '1957-06-13')
SELECT age(now()::date )
select date_part('year', age(now()::date, now()::date)); 
select date_part('year', age(now()::date, '1986-10-21'::date)), age(now()::date, '1986-10-21'::date) as DiferenciaTotal, date_part('month', age(now()::date, '1986-10-21'::date)) as TotalMeses; 

