/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;

import bo.usfx.utils.ComparadorIdFormulario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class Usuarios implements Comparable<Usuarios>{
    private int codigo_usuario;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String nombre_cuenta;
    private String contrasenia;
    private String codigo_estado;
    private java.util.Date fecha_registro;
    private String codigo_tipo_usuario;
    private List<SistemaFormulariosPermisosUsuarios>  listadoInterfacesPermisos;

    public Usuarios(int codigo_usuario, String nombres, String apellidos, String direccion, String nombre_cuenta, String contrasenia, String codigo_estado, Date fecha_registro) {
        this.codigo_usuario = codigo_usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.nombre_cuenta = nombre_cuenta;
        this.contrasenia = contrasenia;
        this.codigo_estado = codigo_estado;
        this.fecha_registro = fecha_registro;
    }

    public Usuarios() {
        codigo_usuario = 0;
        fecha_registro = null;
    }

    public Usuarios(int codigo, String tipo, String descripcion) {
        codigo_usuario = codigo;
        fecha_registro = null;
        this.codigo_tipo_usuario = tipo;
        this.nombres = descripcion;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCodigo_estado() {
        return codigo_estado;
    }

    public void setCodigo_estado(String codigo_estado) {
        this.codigo_estado = codigo_estado;
    }

    public int getCodigo_usuario() {
        return codigo_usuario;
    }

    public void setCodigo_usuario(int codigo_usuario) {
        this.codigo_usuario = codigo_usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getNombre_cuenta() {
        return nombre_cuenta;
    }

    public void setNombre_cuenta(String nombre_cuenta) {
        this.nombre_cuenta = nombre_cuenta;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean esPosibleDarBaja()
    {
        return this.codigo_estado.compareTo("B") != 0;
    }
    public String getCodigoEstadoCadena()
    {
        if(this.codigo_estado.compareTo("B") == 0)
            return "BAJA";
        else if(this.codigo_estado.compareTo("V") == 0)
            return "VIGENTE";
        else
            return "SUSPENDIDO";
    }
    @Override
    public String toString()
    {
        if(codigo_usuario >= 0)
        {
            String CadenaRetorno = "";
            if(this.fecha_registro != null)
            {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String fecha = "";
                    if(fecha_registro != null)
                            fecha = df.format(this.getFecha_registro());

                    CadenaRetorno += fecha  + ",";//+","+this.persona_solicitante.getAppaterno()+","+this.persona_solicitante.getApmaterno();
            }
            if(!this.getNombres().isEmpty())
                    CadenaRetorno += this.getNombres() + ",";
            if(this.codigo_usuario > 0)
                    CadenaRetorno += String.valueOf(codigo_usuario) + ",";
            if (!CadenaRetorno.trim().isEmpty() && CadenaRetorno.trim().charAt(CadenaRetorno.trim().length()-1) == ',')
            {
                    CadenaRetorno = CadenaRetorno.substring(0, CadenaRetorno.trim().length()-1);
            }

            System.out.println(CadenaRetorno);
            return CadenaRetorno;
        }
        else
        {
            return nombres;
        }
 
    }

    public String getNombreCompleto() {
        return  ((nombres.isEmpty() ? "" : nombres.trim())
                + " " + (apellidos.isEmpty() ? "" : apellidos.trim())).toUpperCase();
    }

    public int compareTo(Usuarios o) {
        return this.apellidos.compareTo(o.apellidos);
    }

    public String getCodigo_tipo_usuario() {
        return codigo_tipo_usuario;
    }

    public void setCodigo_tipo_usuario(String codigo_tipo_usuario) {
        this.codigo_tipo_usuario = codigo_tipo_usuario;
    }

    public Usuarios(int codigo_usuario, String nombres, String apellidos, String direccion, String telefono, String nombre_cuenta, String contrasenia, String codigo_estado, Date fecha_registro, String codigo_tipo_usuario) {
        this.codigo_usuario = codigo_usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.nombre_cuenta = nombre_cuenta;
        this.contrasenia = contrasenia;
        this.codigo_estado = codigo_estado;
        this.fecha_registro = fecha_registro;
        this.codigo_tipo_usuario = codigo_tipo_usuario;
    }

    public List<SistemaFormulariosPermisosUsuarios> getListadoInterfacesPermisos() {
        return listadoInterfacesPermisos;
    }

    public void setListadoInterfacesPermisos(List<SistemaFormulariosPermisosUsuarios> listadoInterfacesPermisos) {
        this.listadoInterfacesPermisos = listadoInterfacesPermisos;
    }
    
    public boolean tieneAlgunPermiso(SistemaFormulariosPermisosUsuarios formulario)
	{
		if(listadoInterfacesPermisos == null || listadoInterfacesPermisos.size() == 0)
			return false;
		int indice = -1;

		indice = Collections.binarySearch(this.listadoInterfacesPermisos, formulario, new ComparadorIdFormulario());

		if(indice < 0)
			return false;
		else
		{
			if(listadoInterfacesPermisos.get(indice).existeAlgunPermiso())
				return true;
			else
				return false;
		}
	}

	public boolean estaVigente()
	{
		return this.codigo_estado.compareTo("V") == 0;
	}

}
