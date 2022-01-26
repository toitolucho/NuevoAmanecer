/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;

import java.util.List;

/**
 *
 * @author Luis Molina
 */
public class Cargo implements Comparable<Cargo>{
    private String codigo_cargo;
    private String nombre_cargo;
    private String descripcion;


    private List<SistemaFormulariosPermisosCargos> listaPermisos;

    public Cargo(String codigo_cargo, String nombre_cargo, String descripcion) {
        this.codigo_cargo = codigo_cargo;
        this.nombre_cargo = nombre_cargo;
        this.descripcion = descripcion;
    }

    public Cargo() {
        this.codigo_cargo = null;
    }

    public String getCodigo_cargo() {
        return codigo_cargo;
    }

    public void setCodigo_cargo(String codigo_cargo) {
        this.codigo_cargo = codigo_cargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre_cargo() {
        return nombre_cargo;
    }

    public void setNombre_cargo(String nombre_cargo) {
        this.nombre_cargo = nombre_cargo;
    }

    @Override
    public String toString()
    {
        return this.nombre_cargo.isEmpty() ? "" : this.nombre_cargo.trim();
    }

    public List<SistemaFormulariosPermisosCargos> getListaPermisos() {
        return listaPermisos;
    }

    public void setListaPermisos(List<SistemaFormulariosPermisosCargos> listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

    public int compareTo(Cargo o) {
        int indiceRetorno = this.nombre_cargo.compareTo(o.nombre_cargo);

//        System.out.println("Indice Actual " + indiceRetorno);
        return indiceRetorno;
//        return this.codigo_cargo.compareTo(o.codigo_cargo);

    }

    
}
