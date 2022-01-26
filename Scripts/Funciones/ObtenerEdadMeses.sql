CREATE OR REPLACE FUNCTION ObtenerEdadMeses(date, date) RETURNS INTEGER
AS
$body$
   declare      
   fecha_inicio ALIAS FOR $1;   
   fecha_fin ALIAS FOR $2;    
   anos INTEGER; 
   meses INTEGER; 
   begin	   
	--SELECT EXTRACT(YEAR FROM  age(date '1986-10-21')) INTO edad;
	SELECT 	DATE_PART('YEAR', age(fecha_fin::date, fecha_inicio::date)) INTO anos;
	SELECT	DATE_PART('MONTH', age(fecha_fin::date, fecha_inicio::date))INTO meses; 
	RETURN anos * 12 + meses;
   end;
$body$ LANGUAGE 'plpgsql' VOLATILE;


SELECT ObtenerEdadMeses('2010-10-21',now()::date)


SELECT age(timestamp '2001-04-10', timestamp '1957-06-13')
SELECT age(now()::date )
select date_part('year', age(now()::date, now()::date)); 
select date_part('year', age(now()::date, '1986-10-21'::date)), age(now()::date, '1986-10-21'::date) as DiferenciaTotal, date_part('month', age(now()::date, '1986-10-21'::date)) as TotalMeses; 

