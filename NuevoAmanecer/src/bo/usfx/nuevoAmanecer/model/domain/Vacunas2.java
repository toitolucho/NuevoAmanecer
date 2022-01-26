/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;

/**
 *
 * @author Luis Molina
 */
public class Vacunas2 implements Comparable<Vacunas2>{
    private int codigo_vacuna2;
    private String nombre_vacuna2;
    private int  edad_meses_minima, edad_meses_maxima;
    private String descripcion_vacuna2;

    public Vacunas2(int codigo_vacuna2, String nombre_vacuna2, int edad_meses_minima, int edad_meses_maxima, String descripcion_vacuna2) {
        this.codigo_vacuna2 = codigo_vacuna2;
        this.nombre_vacuna2 = nombre_vacuna2;
        this.edad_meses_minima = edad_meses_minima;
        this.edad_meses_maxima = edad_meses_maxima;
        this.descripcion_vacuna2 = descripcion_vacuna2;
    }

    

    public Vacunas2(int codigo_vacuna2, String nombre_vacuna2, String descripcion_vacuna2) {
        this.codigo_vacuna2 = codigo_vacuna2;
        this.nombre_vacuna2 = nombre_vacuna2;
        this.descripcion_vacuna2 = descripcion_vacuna2;
    }

    public Vacunas2() {
    }

    public int getCodigo_vacuna2() {
        return codigo_vacuna2;
    }

    public void setCodigo_vacuna2(int codigo_vacuna2) {
        this.codigo_vacuna2 = codigo_vacuna2;
    }

    public String getDescripcion_vacuna2() {
        return descripcion_vacuna2;
    }

    public void setDescripcion_vacuna2(String descripcion_vacuna2) {
        this.descripcion_vacuna2 = descripcion_vacuna2;
    }

    public String getNombre_vacuna2() {
        return nombre_vacuna2;
    }

    public void setNombre_vacuna2(String nombre_vacuna2) {
        this.nombre_vacuna2 = nombre_vacuna2;
    }

    public int getEdad_meses_maxima() {
        return edad_meses_maxima;
    }

    public void setEdad_meses_maxima(int edad_meses_maxima) {
        this.edad_meses_maxima = edad_meses_maxima;
    }

    public int getEdad_meses_minima() {
        return edad_meses_minima;
    }

    public void setEdad_meses_minima(int edad_meses_minima) {
        this.edad_meses_minima = edad_meses_minima;
    }

    public String getLimiteDeMeses()
    {
        return "Entre " + edad_meses_minima + " y " + edad_meses_maxima + " meses";
    }
    
    @Override
    public String toString() {
        return nombre_vacuna2;
    }

    public int compareTo(Vacunas2 o) {
        return this.nombre_vacuna2.compareTo(o.nombre_vacuna2);
    }

    
}
