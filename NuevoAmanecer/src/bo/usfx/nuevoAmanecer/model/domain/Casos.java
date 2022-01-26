/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Casos {
    private java.util.Date fecha_registro;
    private int numero_caso;
    private int codigo_padrino;

    private Afiliado afiliado;
    private Padrinos patrocinador1;    

    public Casos(java.util.Date fecha_registro, int numero_caso, int codigo_padrino) {
        this.fecha_registro = fecha_registro;
        this.numero_caso = numero_caso;
        this.codigo_padrino = codigo_padrino;
    }

    public int getCodigo_padrino() {
        return codigo_padrino;
    }

    public void setCodigo_padrino(int codigo_padrino) {
        this.codigo_padrino = codigo_padrino;
    }

    public java.util.Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(java.util.Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public int getNumero_caso() {
        return numero_caso;
    }

    public void setNumero_caso(int numero_caso) {
        this.numero_caso = numero_caso;
    }

    public Casos() {
    }

    public Afiliado getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(Afiliado afiliado) {
        this.afiliado = afiliado;
    }

    public Padrinos getPatrocinador1() {
        return patrocinador1;
    }

    public void setPatrocinador1(Padrinos patrocinador1) {
        this.patrocinador1 = patrocinador1;
    }

    public String toString()
    {
        String CadenaRetorno = "";
        if(this.fecha_registro != null)
        {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = "";
            fecha = df.format(this.fecha_registro);
            CadenaRetorno += fecha  + ",";
        }
        if(this.numero_caso > 0)
            CadenaRetorno += String.valueOf(numero_caso) + ",";
        if(this.afiliado != null && afiliado.getNombres() != null)
            CadenaRetorno += afiliado.getNombres() + ",";
        if (!CadenaRetorno.trim().isEmpty() && CadenaRetorno.trim().charAt(CadenaRetorno.trim().length()-1) == ',')
        {
                CadenaRetorno = CadenaRetorno.substring(0, CadenaRetorno.trim().length()-1);
        }

        System.out.println(CadenaRetorno);
        return CadenaRetorno;

    }
    
}
