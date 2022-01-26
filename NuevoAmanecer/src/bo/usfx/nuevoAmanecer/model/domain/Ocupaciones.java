/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.usfx.nuevoAmanecer.model.domain;


public class Ocupaciones implements Comparable<Ocupaciones> {

    private int codigo_ocupacion;
    private String nombre_ocupacion;

    public Ocupaciones(int codigo_ocupacion, String nombre_ocupacion) {
        this.codigo_ocupacion = codigo_ocupacion;
        this.nombre_ocupacion = nombre_ocupacion;
    }

    public Ocupaciones() {
    }

    public int getCodigo_ocupacion() {
        return codigo_ocupacion;
    }

    public void setCodigo_ocupacion(int codigo_ocupacion) {
        this.codigo_ocupacion = codigo_ocupacion;
    }

    public String getNombre_ocupacion() {
        return nombre_ocupacion;
    }

    public void setNombre_ocupacion(String nombre_ocupacion) {
        this.nombre_ocupacion = nombre_ocupacion;
    }
    
    @Override
    public String toString()
    {
        return this.nombre_ocupacion;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int compareTo(Ocupaciones o) {
        return o.nombre_ocupacion != null ? this.nombre_ocupacion.compareTo(o.nombre_ocupacion) : -1;
    }


    
}
