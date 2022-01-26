/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Vacunas {
    private Date fecha_vacuna;
    private Date bcg;
    private Date antipolio_i;
    private Date antipolio_1a;
    private Date antipolio_2a;
    private Date antipolio_3a;
    private Date antipolio_refuerzo;
    private Date pentavalente_1a;
    private Date pentavalente_2a;
    private Date pentavalente_3a;
    private Date pentavalente_ref;
    private Date sarampion;
    private int numero_caso;

    public Vacunas(Date fecha_vacuna, Date bcg, Date antipolio_i, Date antipolio_1a, Date antipolio_2a, Date antipolio_3a, Date antipolio_refuerzo, Date pentavalente_1a, Date pentavalente_2a, Date pentavalente_3a, Date pentavalente_ref, Date sarampion, int numero_caso) {
        this.fecha_vacuna = fecha_vacuna;
        this.bcg = bcg;
        this.antipolio_i = antipolio_i;
        this.antipolio_1a = antipolio_1a;
        this.antipolio_2a = antipolio_2a;
        this.antipolio_3a = antipolio_3a;
        this.antipolio_refuerzo = antipolio_refuerzo;
        this.pentavalente_1a = pentavalente_1a;
        this.pentavalente_2a = pentavalente_2a;
        this.pentavalente_3a = pentavalente_3a;
        this.pentavalente_ref = pentavalente_ref;
        this.sarampion = sarampion;
        this.numero_caso = numero_caso;
    }

    public Vacunas() {
        this.numero_caso = -1;
        fecha_vacuna = null;
    }

    public Date getAntipolio_1a() {
        return antipolio_1a;
    }

    public void setAntipolio_1a(Date antipolio_1a) {
        this.antipolio_1a = antipolio_1a;
    }

    public Date getAntipolio_2a() {
        return antipolio_2a;
    }

    public void setAntipolio_2a(Date antipolio_2a) {
        this.antipolio_2a = antipolio_2a;
    }

    public Date getAntipolio_3a() {
        return antipolio_3a;
    }

    public void setAntipolio_3a(Date antipolio_3a) {
        this.antipolio_3a = antipolio_3a;
    }

    public Date getAntipolio_i() {
        return antipolio_i;
    }

    public void setAntipolio_i(Date antipolio_i) {
        this.antipolio_i = antipolio_i;
    }

    public Date getAntipolio_refuerzo() {
        return antipolio_refuerzo;
    }

    public void setAntipolio_refuerzo(Date antipolio_refuerzo) {
        this.antipolio_refuerzo = antipolio_refuerzo;
    }

    public Date getBcg() {
        return bcg;
    }

    public void setBcg(Date bcg) {
        this.bcg = bcg;
    }

    public Date getFecha_vacuna() {
        return fecha_vacuna;
    }

    public void setFecha_vacuna(Date fecha_vacuna) {
        this.fecha_vacuna = fecha_vacuna;
    }

    public int getNumero_caso() {
        return numero_caso;
    }

    public void setNumero_caso(int numero_caso) {
        this.numero_caso = numero_caso;
    }

    public Date getPentavalente_1a() {
        return pentavalente_1a;
    }

    public void setPentavalente_1a(Date pentavalente_1a) {
        this.pentavalente_1a = pentavalente_1a;
    }

    public Date getPentavalente_2a() {
        return pentavalente_2a;
    }

    public void setPentavalente_2a(Date pentavalente_2a) {
        this.pentavalente_2a = pentavalente_2a;
    }

    public Date getPentavalente_3a() {
        return pentavalente_3a;
    }

    public void setPentavalente_3a(Date pentavalente_3a) {
        this.pentavalente_3a = pentavalente_3a;
    }

    public Date getPentavalente_ref() {
        return pentavalente_ref;
    }

    public void setPentavalente_ref(Date pentavalente_ref) {
        this.pentavalente_ref = pentavalente_ref;
    }

    public Date getSarampion() {
        return sarampion;
    }

    public void setSarampion(Date sarampion) {
        this.sarampion = sarampion;
    }

    @Override
    public String toString()
    {
        String CadenaRetorno = "";

        if(this.fecha_vacuna != null)
        {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = "";
                if(fecha_vacuna != null)
                        fecha = df.format(this.getFecha_vacuna());

                CadenaRetorno += fecha  + ",";//+","+this.persona_solicitante.getAppaterno()+","+this.persona_solicitante.getApmaterno();
        }
        if(this.numero_caso > 0)
                CadenaRetorno += String.valueOf(numero_caso) + ",";

        if (!CadenaRetorno.trim().isEmpty() && CadenaRetorno.trim().charAt(CadenaRetorno.trim().length()-1) == ',')
        {
                CadenaRetorno = CadenaRetorno.substring(0, CadenaRetorno.trim().length()-1);
        }

        System.out.println(CadenaRetorno);
        return CadenaRetorno;

    }

}
