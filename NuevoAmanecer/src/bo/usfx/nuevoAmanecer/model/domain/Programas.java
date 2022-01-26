/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Programas {
    private int codigo_programa;
    private String nombre_actividad;
    private String descripcion;
    private String justificacion;
    private Date fecha_programa;
    private Date fecha_culminacion;
    private String lugar;
    private String area;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Programas(int codigo_programa, String nombre_actividad, String descripcion, String justificacion, Date fecha_programa, Date fecha_culminacion, String lugar) {
        this.codigo_programa = codigo_programa;
        this.nombre_actividad = nombre_actividad;
        this.descripcion = descripcion;
        this.justificacion = justificacion;
        this.fecha_programa = fecha_programa;
        this.fecha_culminacion = fecha_culminacion;
        this.lugar = lugar;
    }

    public Programas() {
        codigo_programa = -1;
    }

    public int getCodigo_programa() {
        return codigo_programa;
    }

    public void setCodigo_programa(int codigo_programa) {
        this.codigo_programa = codigo_programa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_culminacion() {
        return fecha_culminacion;
    }

    public void setFecha_culminacion(Date fecha_culminacion) {
        this.fecha_culminacion = fecha_culminacion;
    }

    public Date getFecha_programa() {
        return fecha_programa;
    }

    public void setFecha_programa(Date fecha_programa) {
        this.fecha_programa = fecha_programa;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getNombre_actividad() {
        return nombre_actividad;
    }

    public void setNombre_actividad(String nombre_actividad) {
        this.nombre_actividad = nombre_actividad;
    }

    @Override
    public String toString()
    {
        String CadenaRetorno = "";
        if(this.fecha_programa != null)
        {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = "";
            fecha = df.format(this.fecha_programa);
            CadenaRetorno += fecha  + ",";
        }
        if(this.codigo_programa > 0)
            CadenaRetorno += String.valueOf(codigo_programa) + ",";
        CadenaRetorno += nombre_actividad + ",";
        if (!CadenaRetorno.trim().isEmpty() && CadenaRetorno.trim().charAt(CadenaRetorno.trim().length()-1) == ',')
        {
                CadenaRetorno = CadenaRetorno.substring(0, CadenaRetorno.trim().length()-1);
        }

        System.out.println(CadenaRetorno);
        return CadenaRetorno;

    }

    public String getResumenPrograma()
    {
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat( "yyyyMMdd" );
        return "Nombre Programa : " + nombre_actividad.trim().toUpperCase() + "   Realizada del " + df.format(fecha_programa) + " al " 
                + (fecha_culminacion != null ? df.format(fecha_culminacion) : " Sin Fecha Limite");
    }
}
