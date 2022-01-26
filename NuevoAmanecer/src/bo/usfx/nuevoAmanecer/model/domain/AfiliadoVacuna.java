/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Luis Molina
 */
public class AfiliadoVacuna implements Comparable<AfiliadoVacuna> {
    private int codigo_persona;
    private int codigo_vacuna2;
    private Date fecha_vacuna2;
    private String observaciones;

    private Vacunas2 vacuna;

    public AfiliadoVacuna(int codigo_persona, int codigo_vacuna2, Date fecha_vacuna2, String observaciones) {
        this.codigo_persona = codigo_persona;
        this.codigo_vacuna2 = codigo_vacuna2;
        this.fecha_vacuna2 = fecha_vacuna2;
        this.observaciones = observaciones;
    }

    public AfiliadoVacuna() {
    }

    public int getCodigo_persona() {
        return codigo_persona;
    }

    public void setCodigo_persona(int codigo_persona) {
        this.codigo_persona = codigo_persona;
    }

    public int getCodigo_vacuna2() {
        return codigo_vacuna2;
    }

    public void setCodigo_vacuna2(int codigo_vacuna2) {
        this.codigo_vacuna2 = codigo_vacuna2;
    }

    public Date getFecha_vacuna2() {
        return fecha_vacuna2;
    }

    public void setFecha_vacuna2(Date fecha_vacuna2) {
        this.fecha_vacuna2 = fecha_vacuna2;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        String CadenaRetorno = "";
        if(this.fecha_vacuna2 != null)
        {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = "";
            fecha = df.format(this.fecha_vacuna2);
            CadenaRetorno += fecha  + ",";
        }
        if(this.codigo_persona > 0)
            CadenaRetorno += String.valueOf(codigo_persona) + ",";
        if(this.codigo_vacuna2 > 0)
            CadenaRetorno += String.valueOf(codigo_vacuna2) + ",";
        if(vacuna != null)
            CadenaRetorno += vacuna.getNombre_vacuna2() + ",";
        if (!CadenaRetorno.trim().isEmpty() && CadenaRetorno.trim().charAt(CadenaRetorno.trim().length()-1) == ',')
        {
                CadenaRetorno = CadenaRetorno.substring(0, CadenaRetorno.trim().length()-1);
        }

        System.out.println(CadenaRetorno);
        return CadenaRetorno;
    }

    public Vacunas2 getVacuna() {
        return vacuna;
    }

    public void setVacuna(Vacunas2 vacuna) {
        this.vacuna = vacuna;
    }

    public int compareTo(AfiliadoVacuna o) {
        return this.vacuna.compareTo(o.vacuna);
    }

    
    
}
