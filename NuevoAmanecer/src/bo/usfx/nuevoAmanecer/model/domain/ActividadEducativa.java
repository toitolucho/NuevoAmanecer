/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;

public class ActividadEducativa implements Comparable<ActividadEducativa> {
    private String codigo_actividad_educactiva;
    private String nombre_actividad_educativa;

    public ActividadEducativa(String codigo_actividad_educactiva, String nombre_actividad_educativa) {
        this.codigo_actividad_educactiva = codigo_actividad_educactiva;
        this.nombre_actividad_educativa = nombre_actividad_educativa;
    }

    public ActividadEducativa() {
    }

    public String getCodigo_actividad_educactiva() {
        return codigo_actividad_educactiva;
    }

    public void setCodigo_actividad_educactiva(String codigo_actividad_educactiva) {
        this.codigo_actividad_educactiva = codigo_actividad_educactiva;
    }

    public String getNombre_actividad_educativa() {
        return nombre_actividad_educativa;
    }

    public void setNombre_actividad_educativa(String nombre_actividad_educativa) {
        this.nombre_actividad_educativa = nombre_actividad_educativa;
    }

    
    public String toString()
    {
        return this.nombre_actividad_educativa;
    }

    public int compareTo(ActividadEducativa o) {        
        return this.nombre_actividad_educativa != null && o != null && o.nombre_actividad_educativa != null
                ? this.nombre_actividad_educativa.compareTo(o.nombre_actividad_educativa) : -1;
    }
}
