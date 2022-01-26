/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SaludPublica {
    private int numero_familia;
    private Date fecha_registro;
    private boolean eda;
    private boolean ira;
    private boolean agua;
    private boolean excretas;
    private String vivienda;
    private String tipo_vivienda;
    private String material_vivienda;
    private int numero_habitaciones;
    private int dormitorios;
    private boolean cocina;

    public SaludPublica(int numero_familia, Date fecha_registro, boolean eda, boolean ira, boolean agua, boolean excretas, String vivienda, String tipo_vivienda, String material_vivienda, int numero_habitaciones, int dormitorios, boolean cocina) {
        this.numero_familia = numero_familia;
        this.fecha_registro = fecha_registro;
        this.eda = eda;
        this.ira = ira;
        this.agua = agua;
        this.excretas = excretas;
        this.vivienda = vivienda;
        this.tipo_vivienda = tipo_vivienda;
        this.material_vivienda = material_vivienda;
        this.numero_habitaciones = numero_habitaciones;
        this.dormitorios = dormitorios;
        this.cocina = cocina;
    }

    public SaludPublica() {
        numero_familia = 0;
    }

    public boolean isAgua() {
        return agua;
    }

    public void setAgua(boolean agua) {
        this.agua = agua;
    }

    public boolean isCocina() {
        return cocina;
    }

    public void setCocina(boolean cocina) {
        this.cocina = cocina;
    }

    public int getDormitorios() {
        return dormitorios;
    }

    public void setDormitorios(int dormitorios) {
        this.dormitorios = dormitorios;
    }

    public boolean isEda() {
        return eda;
    }

    public void setEda(boolean eda) {
        this.eda = eda;
    }

    public boolean isExcretas() {
        return excretas;
    }

    public void setExcretas(boolean excretas) {
        this.excretas = excretas;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public boolean isIra() {
        return ira;
    }

    public void setIra(boolean ira) {
        this.ira = ira;
    }

    public String getMaterial_vivienda() {
        return material_vivienda;
    }

    public void setMaterial_vivienda(String material_vivienda) {
        this.material_vivienda = material_vivienda;
    }

    public int getNumero_familia() {
        return numero_familia;
    }

    public void setNumero_familia(int numero_familia) {
        this.numero_familia = numero_familia;
    }

    public int getNumero_habitaciones() {
        return numero_habitaciones;
    }

    public void setNumero_habitaciones(int numero_habitaciones) {
        this.numero_habitaciones = numero_habitaciones;
    }

    public String getTipo_vivienda() {
        return tipo_vivienda;
    }

    public void setTipo_vivienda(String tipo_vivienda) {
        this.tipo_vivienda = tipo_vivienda;
    }

    public String getVivienda() {
        return vivienda;
    }

    public void setVivienda(String vivienda) {
        this.vivienda = vivienda;
    }

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
        if(this.numero_familia > 0)
                CadenaRetorno += String.valueOf(numero_familia) + ",";

        if (!CadenaRetorno.trim().isEmpty() && CadenaRetorno.trim().charAt(CadenaRetorno.trim().length()-1) == ',')
        {
                CadenaRetorno = CadenaRetorno.substring(0, CadenaRetorno.trim().length()-1);
        }

        
        return CadenaRetorno;

    }

}
