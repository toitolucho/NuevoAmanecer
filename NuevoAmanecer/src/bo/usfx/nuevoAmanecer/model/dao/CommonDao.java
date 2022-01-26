package bo.usfx.nuevoAmanecer.model.dao;

import java.util.Date;
import java.util.List;
import java.sql.SQLException;


public interface CommonDao {
    /**
     * Registra o actualiza objetos en la BD. <br>
     * Si el registro existe actualiza valores, caso contrario registra un nuevo valor.
     * @param obj
     * @throws java.sql.SQLException
     */
    void edit(Object obj) throws SQLException;
    /**
     * Realiza consultas de eliminacion.
     * @param obj (Criterio de eliminacion) Clave(s) primaria(s) para su mejor uso.
     * @throws java.sql.SQLException
     */
    void delete(Object obj) throws SQLException;
    
     /**
     *  Verifica que el usuario se encuentre autenticado dentro
     *  del Sistema
     * @param ob
     * @return
     * @throws SQLException
     */
    boolean isAutenticado(Object ob) throws SQLException;
    
    /**
     * Verifica si existe un objeto dentro de la lista de objetos (TABLAS).
     * 
     * @param ob Clave(s) primaria(s) para su mejor uso.
     * @return
     * @throws java.sql.SQLException
     */
    boolean existsId(Object ob) throws SQLException;
    /**
     * Obtiene un solo objeto de acuerdo al parametro que es enviado
     * en cualquier caso llaves primarias o foraneas.
     * @param ob Claves(s) primaria(s) para su mejor uso.
     * @return
     * @throws java.sql.SQLException
     */
    Object obtenerObjeto(Object idobject) throws SQLException;
    /**
     * Realiza consultas de busqueda de cualquier tipo de objetos,
     * el criterio de busqueda depende de las propiedades no nulas
     * por ejemplo: Si Persona persona.setIdPersona("5137245756") entonces
     * realiza la busqueda solo por Idpersona.
     * @param ob Objeto de cualquier tipo.
     * @return
     * @throws java.sql.SQLException
     */    
    List findObjects(Object ob) throws SQLException;
    public List findObjects(Object obj, int filtroEntero)throws SQLException;

    public List findAdvancedObjects(Object obj, char TipoPersona) throws SQLException;
    List findObjectsSolicitud(Object obj, String estado) throws SQLException;
    
    /**
     * Realiza la busqueda de Asociaciones a  las cuales
     * se encuentra subscrito una perona
     * @param obj Asociacion
     * @return lista de asociaciones
     * @throws SQLException
     */
    

    List getObjects(String tabla) throws SQLException;
    List getObjects(String tabla,Object obj) throws SQLException;
    
    
    
    int obtenerUltimoObjetoTabla(Object obj) throws SQLException;
    int obtenerUltimoObjetoTabla(Object obj, String filtro) throws SQLException;
    
    
    public boolean VerificarExistenciaDescripcion(Object obj)throws SQLException;
    public boolean VerificarExistenciaGestion(Object obj)throws SQLException;
    
    
    public Date obtenerFechaHoraServidor() throws SQLException;
    public int ObtenerEdadActual(java.util.Date FechaNacimiento, java.util.Date FechaActual) throws SQLException;
    public int ObtenerEdadMeses(java.util.Date FechaNacimiento, java.util.Date FechaActual) throws SQLException;
    public String ObtenerTallaEdad(java.util.Date FechaNacimiento, String sexo, float talla) throws SQLException;
    public String ObtenerPesoEdad (java.util.Date FechaNacimiento, String sexo, float peso) throws SQLException;
    
    
    /**
     * Verifica si el n�mero de Autorizaci�n ingresado para alguna tabla que necesita el n�mero de autorizaci�n,
     * no se encuentre ya registrado,
     * para no Tener Repetidos, opci�n que no es v�lida en el sistema
     * @param ob
     * @return
     * @throws SQLException
     */
    boolean existsNro(Object ob) throws SQLException;
    
    /**
     * Insertar un Objeto dentro de la base de Datos
     * @param obj
     * @throws SQLException
     */
    public void insertar(Object obj) throws SQLException;
    
    List getPermisosFormularios(Object obj,int idEmpleado) throws SQLException;

    public void ControlarEdadesAfiliados(Object obj)throws SQLException;
    public boolean esAfiliado(int codigo_persona) throws SQLException;
    public boolean sePuedeAgregarAfiliado(int numero_familia) throws SQLException;
}