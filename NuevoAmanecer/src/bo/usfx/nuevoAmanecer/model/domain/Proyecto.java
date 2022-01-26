/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;


public class Proyecto {
    private int numero_proyecto;
    private String nombre_proyecto;
    private String departamento;
    private String provincia_canton;
    private String localidad;

    public Proyecto(int numero_proyecto, String nombre_proyecto, String departamento, String provincia_canton, String localidad) {
        this.numero_proyecto = numero_proyecto;
        this.nombre_proyecto = nombre_proyecto;
        this.departamento = departamento;
        this.provincia_canton = provincia_canton;
        this.localidad = localidad;
    }

    public Proyecto() {
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getNombre_proyecto() {
        return nombre_proyecto;
    }

    public void setNombre_proyecto(String nombre_proyecto) {
        this.nombre_proyecto = nombre_proyecto;
    }

    public int getNumero_proyecto() {
        return numero_proyecto;
    }

    public void setNumero_proyecto(int numero_proyecto) {
        this.numero_proyecto = numero_proyecto;
    }

    public String getProvincia_canton() {
        return provincia_canton;
    }

    public void setProvincia_canton(String provincia_canton) {
        this.provincia_canton = provincia_canton;
    }

    



}
