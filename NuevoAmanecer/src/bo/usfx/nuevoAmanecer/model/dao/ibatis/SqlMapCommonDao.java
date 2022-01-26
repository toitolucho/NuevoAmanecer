package bo.usfx.nuevoAmanecer.model.dao.ibatis;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.dao.config.SqlMapClientDaoSuport;
import bo.usfx.utils.BuscadorObjetos;
import bo.usfx.utils.StringUtils;

import com.ibatis.sqlmap.client.SqlMapClient;


public class SqlMapCommonDao extends SqlMapClientDaoSuport implements CommonDao {

  /**
   * Esta clase es un generador secuencial de ID (identificadores) que esta basado en una tabla en la 
   * Base de Datos llamado 'Secuencia', el cual contiene dos columnas (nombre,siguienteId).
   * Esto debe trabajar en cualquier Gestor de base de datos.
   */
    private static SqlMapClient client = SqlMapClientDaoSuport.getSqlMapInstance();

    /**
     * Registra o actualiza objetos en la BD. <br>
     * Si el registro existe actualiza valores, caso contrario registra un nuevo valor.
     * @param obj
     * @throws java.sql.SQLException
     */
    public void edit(Object obj) throws SQLException{
    	System.out.println("EXISTE " + StringUtils.getObjectName(obj) + ", " +obj);
        if (existsId(obj))        	
            client.update("Modificar"+StringUtils.getObjectName(obj), obj);
        else
            client.insert("Insertar"+StringUtils.getObjectName(obj), obj);
    }

    public void ControlarEdadesAfiliados(Object obj)throws SQLException
    {
        System.out.println("ControlarEdadesAfiliados " + StringUtils.getObjectName(obj) + ", " +obj);
        client.update("ControlarEdadesAfiliados", obj);
    }
    
    /**
     * Registra Objetos en la BD. <br>  
     * @param obj
     * @throws java.sql.SQLException
     */
    public void insertar(Object obj) throws SQLException{
    	System.out.println(client.toString());        
        client.insert("Insertar"+StringUtils.getObjectName(obj), obj);
    }
    
    /**
     * Realiza consultas de eliminacion.
     * @param obj (Criterio de eliminacion) Clave(s) primaria(s) para su mejor uso.
     * @throws java.sql.SQLException
     */
    public void delete(Object obj) throws SQLException{
        client.delete("Eliminar"+StringUtils.getObjectName(obj), obj);
    }
    
    /**
     * Verifica si existe un objeto dentro de la lista de objetos (TABLAS).
     * 
     * @param obj Clave(s) primaria(s) para su mejor uso.
     * @return
     * @throws java.sql.SQLException
     */
    public boolean existsId(Object obj) throws SQLException{
        System.out.println("VerificarId"+StringUtils.getObjectName(obj));
        Integer i = (Integer) client.queryForObject("VerificarId"+StringUtils.getObjectName(obj),obj);
        System.out.println("cantidad Registros Existe Id " +i);
        return (i != null && i.intValue() > 0);
    }
    
   
    /**
     * Obtiene un solo objeto de acuerdo al parametro que es enviado
     * en cualquier caso llaves primarias o foraneas.
     * @param idobject Claves(s) primaria(s) para su mejor uso.
     * @return
     * @throws java.sql.SQLException
     */
    public Object obtenerObjeto(Object idobject) throws SQLException{
        System.out.println("Obtener"+StringUtils.getObjectName(idobject));
        return client.queryForObject("Obtener"+StringUtils.getObjectName(idobject), idobject);
    }
    /**
     * Realiza consultas de busqueda de cualquier tipo de objetos,
     * el criterio de busqueda depende de las propiedades no nulas
     * por ejemplo: Si Persona persona.setIdPersona("5137245756") entonces
     * realiza la busqueda solo por Idpersona.
     * @param obj Objeto de cualquier tipo.
     * @return
     * @throws java.sql.SQLException
     */

    public List getObjects(String tabla) throws SQLException{
    	System.out.println("Obtener"+tabla);
        return client.queryForList("Obtener"+tabla, null);
    }
    public List getObjects(String tabla,Object obj) throws SQLException{
        System.out.println("Obtener"+tabla);
        return client.queryForList("Obtener"+tabla, obj);
    }
    
    /**
     * Realiza consultas de busqueda de cualquier tipo de objetos,
     * el criterio de busqueda depende de las propiedades no nulas
     * por ejemplo: Si Persona persona.setIdPersona("5137245756") entonces
     * realiza la busqueda solo por Idpersona.
     * @param obj Objeto de cualquier tipo.
     * @return
     * @throws java.sql.SQLException
     */
    
    public List findObjects(Object obj) throws SQLException{
        Object parameterObject = new BuscadorObjetos(obj.toString());  
        System.out.println("Buscar clase " + StringUtils.getObjectName(obj));
        return client.queryForList("Buscar"+StringUtils.getObjectName(obj), parameterObject);
    }

    public List findObjects(Object obj, int filtroEntero) throws SQLException{
//        Object parameterObject = new BuscadorObjetos(obj.toString(),filtroEntero);
        Object parameterObject = new BuscadorObjetos(obj.toString());
        ((BuscadorObjetos)parameterObject).setFiltroEntero(filtroEntero);
        System.out.println("Buscar clase " + StringUtils.getObjectName(obj) + ", Datos Objeto " + parameterObject);
        return client.queryForList("Buscar"+StringUtils.getObjectName(obj), parameterObject);
    }
    
    

    public int obtenerUltimoObjetoTabla(Object obj) throws SQLException {
            // TODO Auto-generated method stub
            Map m = new HashMap(2);
            m.put("a", new Integer(7));
            System.out.println("obtenerUltimoId"+StringUtils.getObjectName(obj));
            Integer val = (Integer)client.queryForObject("obtenerUltimoId"+StringUtils.getObjectName(obj), m);
            return val;
    }
	

	
    /***
     * Obtiene el �ltimo Identificacdor o Id de un Ojbeto o tabla
     * mediante la condici�n incluida en el filtro
     * es decir : " tipo = 'A' "
     */
    public int obtenerUltimoObjetoTabla(Object obj, String filtro) throws SQLException
    {
            Map m = new HashMap(2);
            m.put("a", new String(filtro));
            System.out.println("obtenerUltimoId"+StringUtils.getObjectName(obj) +"  " +filtro);
            Integer val = (Integer)client.queryForObject("obtenerUltimoId"+StringUtils.getObjectName(obj), m);
            if(val != null)
                    return val.intValue();
            else
                    return -1;
    }
    
	
	
	
	
    /**
 * Realiza la Verificaci�n de la descripci�n mas importante del objeto dentro de la
 * base de Datos, es decir en el caso de una persona, se serciora de que el nombre, apellido parteno y materno
 * no se repitan y evite incoherencias
 * @param obj
 * @return
 * @throws SQLException
 */
    public boolean VerificarExistenciaDescripcion(Object obj)throws SQLException {
            Integer i = (Integer) client.queryForObject("VerificarExistenciaDescripcion"+StringUtils.getObjectName(obj),obj);
    return (i != null && i.intValue() > 0);
    }

    public boolean VerificarExistenciaGestion(Object obj)throws SQLException
    {
        Integer i = (Integer) client.queryForObject("VerificarExistenciaGestion"+StringUtils.getObjectName(obj),obj);
        return (i != null && i.intValue() > 0);
    }
	
    public Date obtenerFechaHoraServidor() throws SQLException
    {
            Date val = (Date)client.queryForObject("ObtenerFechaHoraServidor",null);
            return val;
    }
    public int ObtenerEdadActual(java.util.Date FechaNacimiento, java.util.Date FechaActual) throws SQLException
    {
        Map paremetros = new HashMap(2);
            paremetros.put("FechaNacimiento", FechaNacimiento);
            paremetros.put("FechaActual", FechaActual);
         Integer val = (Integer)client.queryForObject("ObtenerEdadActual",paremetros);
        if(val != null)
                    return val.intValue();
            else
                    return -1;
    }

    //ObtenerEdadMeses
    public int ObtenerEdadMeses(java.util.Date FechaNacimiento, java.util.Date FechaActual) throws SQLException
    {
        Map paremetros = new HashMap(2);
            paremetros.put("FechaNacimiento", FechaNacimiento);
            paremetros.put("FechaActual", FechaActual);
         Integer val = (Integer)client.queryForObject("ObtenerEdadMeses",paremetros);
        if(val != null)
                    return val.intValue();
            else
                    return -1;
    }

    public String ObtenerTallaEdad(java.util.Date FechaNacimiento, String sexo, float talla) throws SQLException
    {
        Map paremetros = new HashMap(2);
        paremetros.put("parametro_fecha_nacimiento", FechaNacimiento);
        paremetros.put("parametro_sexo", new String(sexo));
//        paremetros.put("parametro_talla", new Float(talla));
        Integer val = (Integer) client.queryForObject("ObtenerTallaEdad", paremetros);
        if (val != null) {
            switch (val.intValue()) {
                case 1:
                    return "TA";
                case 0:
                    return "TN";
                case -1:
                    return "TB";
                default:
                            return "";
            }

        } else {
            return "";
        }
    }
    public String ObtenerPesoEdad (java.util.Date FechaNacimiento, String sexo, float peso) throws SQLException
    {
        Map paremetros = new HashMap(3);
        paremetros.put("parametro_fecha_nacimiento", FechaNacimiento);
        paremetros.put("parametro_sexo", new String(sexo));
        paremetros.put("parametro_talla", new Float(peso));
        
        Integer val = (Integer) client.queryForObject("ObtenerPesoEdad", paremetros);
         if (val != null) {
            switch (val.intValue()) {
                case 1:
                    return "NS";
                case 0:
                    return "NN";
                case -1:
                    return "D";
                default:
                            return "";
            }

        } else {
            return "";
        }
    }

    public boolean esAfiliado(int codigo_persona) throws SQLException
    {
        //esAfiliado
        Map paremetros = new HashMap(2);
            paremetros.put("a", new Integer(codigo_persona));
         Boolean val = (Boolean)client.queryForObject("esAfiliado",paremetros);
        if(val != null)
                    return val.booleanValue();
            else
                    return false;
    }


    public boolean sePuedeAgregarAfiliado(int numero_familia) throws SQLException
    {
        //esAfiliado
        Map paremetros = new HashMap(2);
            paremetros.put("a", new Integer(numero_familia));
         Boolean val = (Boolean)client.queryForObject("sePuedeAgregarAfiliado",paremetros);
        if(val != null)
                    return val.booleanValue();
            else
                    return true;
    }

    public boolean isAutenticado(Object ob) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List findAdvancedObjects(Object obj, char TipoPersona) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List findObjectsSolicitud(Object obj, String estado) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean existsNro(Object ob) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
	
    public List getPermisosFormularios(Object obj,int idEmpleado) throws SQLException
    {
            return client.queryForList("ListarXUsuario"+StringUtils.getObjectName(obj), idEmpleado);
    }



}
