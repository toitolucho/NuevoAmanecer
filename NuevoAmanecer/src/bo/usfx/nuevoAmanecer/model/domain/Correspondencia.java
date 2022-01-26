/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Correspondencia {
    private int codigo_correspondencia;
    private boolean es_afiliado_remitente;
    private String descripcion;
    private Date fecha;
    private String tipo;
    private int codigo_padrino;
    private int numero_caso;

    public Correspondencia(int codigo_correspondencia, boolean es_afiliado_remitente, String descripcion, Date fecha, String tipo, int codigo_padrino, int numero_caso) {
        this.codigo_correspondencia = codigo_correspondencia;
        this.es_afiliado_remitente = es_afiliado_remitente;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.tipo = tipo;
        this.codigo_padrino = codigo_padrino;
        this.numero_caso = numero_caso;
    }

    public int getCodigo_padrino() {
        return codigo_padrino;
    }

    public void setCodigo_padrino(int codigo_padrino) {
        this.codigo_padrino = codigo_padrino;
    }

    
    public int getCodigo_correspondencia() {
        return codigo_correspondencia;
    }

    public void setCodigo_correspondencia(int codigo_correspondencia) {
        this.codigo_correspondencia = codigo_correspondencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEs_afiliado_remitente() {
        return es_afiliado_remitente;
    }

    public void setEs_afiliado_remitente(boolean es_afiliado_remitente) {
        this.es_afiliado_remitente = es_afiliado_remitente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumero_caso() {
        return numero_caso;
    }

    public void setNumero_caso(int numero_caso) {
        this.numero_caso = numero_caso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Correspondencia() {
    }

    public boolean esTipoEnvio()
    {
        return tipo.compareTo("E") == 0;
    }

    @Override
    public String toString() {
        String CadenaRetorno = "";

//        if(!this.getNombre().isEmpty())
//                CadenaRetorno += getNombre() + ",";
        if(this.codigo_padrino > 0)
                CadenaRetorno += String.valueOf(codigo_padrino) + ",";
        if(this.codigo_correspondencia > 0)
                CadenaRetorno += String.valueOf(codigo_correspondencia) + ",";
        if(this.fecha != null)
        {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = "";
            if (fecha != null) {
                fecha = df.format(this.getFecha());
            }
            CadenaRetorno += fecha + ",";//+","+this.persona_solicitante.getAppaterno()+","+this.persona_solicitante.getApmaterno();
        }
        if (!CadenaRetorno.trim().isEmpty() && CadenaRetorno.trim().charAt(CadenaRetorno.trim().length()-1) == ',')
        {
                CadenaRetorno = CadenaRetorno.substring(0, CadenaRetorno.trim().length()-1);
        }

        System.out.println(CadenaRetorno);
        return CadenaRetorno;
    }



}
