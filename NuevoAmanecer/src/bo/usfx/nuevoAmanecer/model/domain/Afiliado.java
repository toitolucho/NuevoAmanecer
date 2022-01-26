/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;

import java.util.Calendar;
import java.util.Date;


public class Afiliado extends Familia {
    private int numero_caso;    
    private String tipo;
    private String nombre_corto;
    private boolean vacuna_nino;
    private int numero_nino;
    

    public Afiliado(int numero_caso, String tipo, String nombre_corto, boolean vacuna_nino, int codigo_persona) {
        this.numero_caso = numero_caso;
        this.tipo = tipo;
        this.nombre_corto = nombre_corto;
        this.vacuna_nino = vacuna_nino;
        this.codigo_persona = codigo_persona;
    }

    public Afiliado() {        
        this.numero_caso = -1;
    }

    public Afiliado(int codigo_persona, int ci, String nombres, 
            String apellido_paterno, String apellido_materno, String parentesco,
            String sexo, Date fecha_nacimiento, boolean con_certificado, boolean alfabetizado, Date fecha_alfetizacion,
            String codigo_actividad_educactiva, int codigo_ocupacion, String tipo_ocu, String codigo_evento_vital,
            String codigo_causa_muerte, int numero_familia, int numero_caso, String tipo, String nombre_corto,
            boolean vacuna_nino, int numero_afiliado) {
        super(codigo_persona, ci, nombres, apellido_paterno, apellido_materno, parentesco, sexo, fecha_nacimiento,
                con_certificado, alfabetizado, fecha_alfetizacion, codigo_actividad_educactiva, codigo_ocupacion,
                tipo_ocu, codigo_evento_vital, codigo_causa_muerte, numero_familia);
        this.numero_caso = numero_caso;
        this.tipo = tipo;
        this.nombre_corto = nombre_corto;
        this.vacuna_nino = vacuna_nino;
    }

    public Afiliado(Familia familia, int numero_caso,
            String tipo, String nombre_corto,
            boolean vacuna_nino, int codigo_persona, int numero_afiliado)
    {
        super(familia.getCodigo_persona(), familia.getCi(), familia.getNombres(),
                familia.getApellido_paterno(), familia.getApellido_materno(),
                familia.getParentesco(), familia.getSexo(), familia.getFecha_nacimiento(),
                familia.isCon_certificado(), familia.isAlfabetizado(), familia.getFecha_alfetizacion(),
                familia.getCodigo_actividad_educactiva(), familia.getCodigo_ocupacion(),
                familia.getTipo_ocu(), familia.getCodigo_evento_vital(),
                familia.getCodigo_causa_muerte(), familia.getNumero_familia());

        this.numero_caso = numero_caso;
        this.tipo = tipo;
        this.nombre_corto = nombre_corto;
        this.vacuna_nino = vacuna_nino;        
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

    public String getNombre_corto() {
        return nombre_corto;
    }

    public void setNombre_corto(String nombre_corto) {
        this.nombre_corto = nombre_corto;
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

    public boolean isVacuna_nino() {
        return vacuna_nino;
    }

    public void setVacuna_nino(boolean vacuna_nino) {
        this.vacuna_nino = vacuna_nino;
    }

    public int getNumero_nino() {
        return numero_nino;
    }

    public void setNumero_nino(int numero_nino) {
        this.numero_nino = numero_nino;
    }

    
    
    @Override
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
        if(this.numero_caso > 0)
                CadenaRetorno += String.valueOf(numero_caso) + ",";

        if (!CadenaRetorno.trim().isEmpty() && CadenaRetorno.trim().charAt(CadenaRetorno.trim().length()-1) == ',')
        {
                CadenaRetorno = CadenaRetorno.substring(0, CadenaRetorno.trim().length()-1);
        }

        System.out.println(CadenaRetorno);
        return CadenaRetorno;
    }

    public boolean esAfiliadoNino5Anos()
    {
       
        if(this.getFecha_nacimiento() == null)
            return false;
        else
        {
            Calendar FechaActual = Calendar.getInstance();
            Calendar FechaNacimiento = Calendar.getInstance();
            FechaNacimiento.setTime(getFecha_nacimiento());
            if(FechaNacimiento.after(FechaActual))
                return false;
            else
            {
                // Initialize the age with -1 : stating a future birth date
                int edadAnos = -1;

                // Add a year until the birthdate equals now
                for(Calendar now = Calendar.getInstance(); FechaNacimiento.compareTo(now) < 0 ; FechaNacimiento.add(Calendar.YEAR, 1))
                {
                      edadAnos += 1;
                }
                System.out.println("Edad Actual " + edadAnos);
                return edadAnos <= 5;
            }
        }
    }
}
