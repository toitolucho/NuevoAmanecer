/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Tarjeta implements Comparable<Tarjeta> {
    private int numero_familia;
    private Date fecha_registro_tarjeta;
    private String comunidad;
    private String barrio;
    private int numero_vivienda;
    private String observaciones;
    private String plano_vivienda;
    private int numero_proyecto;
    private String codigo_estado_tarjeta;
    private String descripcion;

    private boolean aplicarFiltroBusqueda = false;

    public void setAplicarFiltroBusqueda(boolean aplicarFiltroBusqueda) {
        this.aplicarFiltroBusqueda = aplicarFiltroBusqueda;
    }

    public Tarjeta(int numero_familia, Date fecha_registro_tarjeta, String comunidad, String barrio, int numero_vivienda, String observaciones, String plano_vivienda, int numero_proyecto) {
        this.numero_familia = numero_familia;
        this.fecha_registro_tarjeta = fecha_registro_tarjeta;
        this.comunidad = comunidad;
        this.barrio = barrio;
        this.numero_vivienda = numero_vivienda;
        this.observaciones = observaciones;
        this.plano_vivienda = plano_vivienda;
        this.numero_proyecto = numero_proyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    

    public Tarjeta() {
        this.numero_familia = -1;
    }

    public Tarjeta(int numero_familia) {
        this.numero_familia = numero_familia;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public Date getFecha_registro_tarjeta() {
        return fecha_registro_tarjeta;
    }

    public void setFecha_registro_tarjeta(Date fecha_registro_tarjeta) {
        this.fecha_registro_tarjeta = fecha_registro_tarjeta;
    }

    public int getNumero_familia() {
        return numero_familia;
    }

    public void setNumero_familia(int numero_familia) {
        this.numero_familia = numero_familia;
    }

    public int getNumero_proyecto() {
        return numero_proyecto;
    }

    public void setNumero_proyecto(int numero_proyecto) {
        this.numero_proyecto = numero_proyecto;
    }

    public int getNumero_vivienda() {
        return numero_vivienda;
    }

    public void setNumero_vivienda(int numero_vivienda) {
        this.numero_vivienda = numero_vivienda;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPlano_vivienda() {
        return plano_vivienda;
    }

    public void setPlano_vivienda(String plano_vivienda) {
        this.plano_vivienda = plano_vivienda;
    }

    public String getCodigo_estado_tarjeta() {
        return codigo_estado_tarjeta;
    }

    public void setCodigo_estado_tarjeta(String codigo_estado_tarjeta) {
        this.codigo_estado_tarjeta = codigo_estado_tarjeta;
    }

    


    public String toString()
    {
        if (!aplicarFiltroBusqueda) {
            return String.valueOf(this.numero_familia);
        } else {
            String CadenaRetorno = "";
            if (this.fecha_registro_tarjeta != null) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = "";
                fecha = df.format(this.fecha_registro_tarjeta);
                CadenaRetorno += fecha + ",";
            }
            if (this.numero_familia > 0) {
                CadenaRetorno += String.valueOf(numero_familia) + ",";
            }
            if (this.descripcion != null) {
                CadenaRetorno += descripcion + ",";
            }
            if (!CadenaRetorno.trim().isEmpty() && CadenaRetorno.trim().charAt(CadenaRetorno.trim().length() - 1) == ',') {
                CadenaRetorno = CadenaRetorno.substring(0, CadenaRetorno.trim().length() - 1);
            }
            System.out.println(CadenaRetorno);
            return CadenaRetorno;
        }
    }

    public int compareTo(Tarjeta o) {
        return this.numero_familia > o.numero_familia ? 1 : this.numero_familia < o.numero_familia ? -1 : 0;
    }
}
