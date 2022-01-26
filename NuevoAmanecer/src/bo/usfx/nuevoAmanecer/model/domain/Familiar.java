/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;

import java.util.Date;


public class Familiar  extends Familia{
    private boolean vacuna_madre;    

    public Familiar(boolean vacuna_madre, int codigo_persona) {
        this.vacuna_madre = vacuna_madre;
        this.codigo_persona = codigo_persona;
    }

    public Familiar() {
    }

    public Familiar(int codigo_persona, int ci, String nombres, String apellido_paterno, String apellido_materno, String parentesco, String sexo, Date fecha_nacimiento, boolean con_certificado, boolean alfabetizado, Date fecha_alfetizacion, String codigo_actividad_educactiva, int codigo_ocupacion, String tipo_ocu, String codigo_evento_vital, String codigo_causa_muerte, int numero_familia, boolean vacuna_madre) {
        super(codigo_persona, ci, nombres, apellido_paterno, apellido_materno, parentesco, sexo, fecha_nacimiento,
                con_certificado, alfabetizado, fecha_alfetizacion, codigo_actividad_educactiva, codigo_ocupacion,
                tipo_ocu, codigo_evento_vital, codigo_causa_muerte, numero_familia);
        this.vacuna_madre = vacuna_madre;
    }

    public Familiar(Familia familia, boolean vacuna_madre)
    {
        super(familia.getCodigo_persona(), familia.getCi(), familia.getNombres(),
                familia.getApellido_paterno(), familia.getApellido_materno(),
                familia.getParentesco(), familia.getSexo(), familia.getFecha_nacimiento(),
                familia.isCon_certificado(), familia.isAlfabetizado(), familia.getFecha_alfetizacion(),
                familia.getCodigo_actividad_educactiva(), familia.getCodigo_ocupacion(),
                familia.getTipo_ocu(), familia.getCodigo_evento_vital(),
                familia.getCodigo_causa_muerte(), familia.getNumero_familia());
        this.vacuna_madre = vacuna_madre;
        this.setRuta_archivo_imagen(familia.getRuta_archivo_imagen());
    }



    @Override
    public int getCodigo_persona() {
        return codigo_persona;
    }

    @Override
    public void setCodigo_persona(int codigo_persona) {
        this.codigo_persona = codigo_persona;
    }

    public boolean isVacuna_madre() {
        return vacuna_madre;
    }

    public void setVacuna_madre(boolean vacuna_madre) {
        this.vacuna_madre = vacuna_madre;
    }

    public String toString()
    {
        String CadenaRetorno = "";
//        if(this.fecha_registro != null)
//        {
//                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//                String fecha = "";
//                if(fecha_registro != null)
//                        fecha = df.format(this.getFecha_registro());
//
//                CadenaRetorno += fecha  + ",";//+","+this.persona_solicitante.getAppaterno()+","+this.persona_solicitante.getApmaterno();
//        }
        if(!this.getNombres().isEmpty())
                CadenaRetorno += this.getNombres() + ",";
        if(this.codigo_persona > 0)
                CadenaRetorno += String.valueOf(codigo_persona) + ",";
        if (!CadenaRetorno.trim().isEmpty() && CadenaRetorno.trim().charAt(CadenaRetorno.trim().length()-1) == ',')
        {
                CadenaRetorno = CadenaRetorno.substring(0, CadenaRetorno.trim().length()-1);
        }

        System.out.println(CadenaRetorno);
        return CadenaRetorno;
    }
    
}
