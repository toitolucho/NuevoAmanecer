/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;


public class EventosVitales implements Comparable<EventosVitales>{
    private String codigo_evento_vital;
    private String nombre_evento_vital;

    public EventosVitales(String codigo_evento_vital, String nombre_evento_vital) {
        this.codigo_evento_vital = codigo_evento_vital;
        this.nombre_evento_vital = nombre_evento_vital;
    }

    public String getCodigo_evento_vital() {
        return codigo_evento_vital;
    }

    public void setCodigo_evento_vital(String codigo_evento_vital) {
        this.codigo_evento_vital = codigo_evento_vital;
    }

    public String getNombre_evento_vital() {
        return nombre_evento_vital;
    }

    public void setNombre_evento_vital(String nombre_evento_vital) {
        this.nombre_evento_vital = nombre_evento_vital;
    }

    public EventosVitales() {
    }

    
    @Override
    public String toString()
    {
        return this.nombre_evento_vital;
    }

    public int compareTo(EventosVitales o) {
        if(o == null)
            return -1;
        if(o.nombre_evento_vital == null)
            return -1;
        return this.nombre_evento_vital.compareTo(o.nombre_evento_vital);
    }

}
