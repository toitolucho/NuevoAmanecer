/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Eccd {
    private Date fecha_registro;
    private float peso;
    private float talla;
    private String peso_talla;
    private String sven;
    private int numero_caso;

    public Eccd(Date fecha_registro, float peso, float talla, String peso_talla, String sven, int numero_caso) {
        this.fecha_registro = fecha_registro;
        this.peso = peso;
        this.talla = talla;
        this.peso_talla = peso_talla;
        this.sven = sven;
        this.numero_caso = numero_caso;
    }

    public Eccd() {
        this.numero_caso = -1;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public int getNumero_caso() {
        return numero_caso;
    }

    public void setNumero_caso(int numero_caso) {
        this.numero_caso = numero_caso;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getPeso_talla() {
        return peso_talla;
    }

    public void setPeso_talla(String peso_talla) {
        this.peso_talla = peso_talla;
    }

    public String getSven() {
        return sven;
    }

    public void setSven(String sven) {
        this.sven = sven;
    }

    public float getTalla() {
        return talla;
    }

    public void setTalla(float talla) {
        this.talla = talla;
    }


    @Override
    public String toString()
    {
        String CadenaRetorno = "";

        if(this.fecha_registro != null)
        {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = "";
                if(fecha_registro != null)
                        fecha = df.format(this.getFecha_registro());

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
