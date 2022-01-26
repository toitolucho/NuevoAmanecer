/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;

import java.util.Date;


public class Familia implements Comparable<Familia> {
    protected int codigo_persona;
    private int ci;
    private String nombres;
    private String apellido_paterno;
    private String apellido_materno;
    private String parentesco;
    private String sexo;
    private Date fecha_nacimiento;
    private boolean  con_certificado;
    private boolean alfabetizado;
    private Date fecha_alfetizacion;
    private String codigo_actividad_educactiva;
    private int codigo_ocupacion;
    private String tipo_ocu;
    private String codigo_evento_vital;
    private String codigo_causa_muerte;
    private int numero_familia;
    private String codigo_estado_familia;
    private String ruta_archivo_imagen;

    private Ocupaciones ocupacion;
    private ActividadEducativa actividadEducativa;
    private EventosVitales eventoVital;
    private CausasMuerte causaMuerte;

    public Familia(int codigo_persona, int ci, String nombres, String apellido_paterno, String apellido_materno, String parentesco, String sexo, Date fecha_nacimiento, boolean con_certificado, boolean alfabetizado, Date fecha_alfetizacion, String codigo_actividad_educactiva, int codigo_ocupacion, String tipo_ocu, String codigo_evento_vital, String codigo_causa_muerte, int numero_familia) {
        this.codigo_persona = codigo_persona;
        this.ci = ci;
        this.nombres = nombres;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.parentesco = parentesco;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.con_certificado = con_certificado;
        this.alfabetizado = alfabetizado;
        this.fecha_alfetizacion = fecha_alfetizacion;
        this.codigo_actividad_educactiva = codigo_actividad_educactiva;
        this.codigo_ocupacion = codigo_ocupacion;
        this.tipo_ocu = tipo_ocu;
        this.codigo_evento_vital = codigo_evento_vital;
        this.codigo_causa_muerte = codigo_causa_muerte;
        this.numero_familia = numero_familia;

        ocupacion = new Ocupaciones();
    }

    

    public Familia() {
        this.codigo_persona = -1;
        this.numero_familia = -1;
        ocupacion = new Ocupaciones();
    }

    public Familia(int codigo_persona, int ci, String nombres, String apellido_paterno, String apellido_materno, String parentesco, String sexo, Date fecha_nacimiento, boolean con_certificado, boolean alfabetizado, Date fecha_alfetizacion, String codigo_actividad_educactiva, int codigo_ocupacion, String tipo_ocu, String codigo_evento_vital, String codigo_causa_muerte, int numero_familia, Ocupaciones ocupacion, ActividadEducativa actividadEducativa, EventosVitales eventoVital, CausasMuerte causaMuerte) {
        this.codigo_persona = codigo_persona;
        this.ci = ci;
        this.nombres = nombres;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.parentesco = parentesco;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.con_certificado = con_certificado;
        this.alfabetizado = alfabetizado;
        this.fecha_alfetizacion = fecha_alfetizacion;
        this.codigo_actividad_educactiva = codigo_actividad_educactiva;
        this.codigo_ocupacion = codigo_ocupacion;
        this.tipo_ocu = tipo_ocu;
        this.codigo_evento_vital = codigo_evento_vital;
        this.codigo_causa_muerte = codigo_causa_muerte;
        this.numero_familia = numero_familia;
        this.ocupacion = ocupacion;
        this.actividadEducativa = actividadEducativa;
        this.eventoVital = eventoVital;
        this.causaMuerte = causaMuerte;        
    }



    public boolean isAlfabetizado() {
        return alfabetizado;
    }

    public void setAlfabetizado(boolean alfabetizado) {
        this.alfabetizado = alfabetizado;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getCodigo_actividad_educactiva() {
        return codigo_actividad_educactiva;
    }

    public void setCodigo_actividad_educactiva(String codigo_actividad_educactiva) {
        this.codigo_actividad_educactiva = codigo_actividad_educactiva;
    }

    public String getCodigo_causa_muerte() {
        return codigo_causa_muerte;
    }

    public void setCodigo_causa_muerte(String codigo_causa_muerte) {
        this.codigo_causa_muerte = codigo_causa_muerte;
    }

    public String getCodigo_evento_vital() {
        return codigo_evento_vital;
    }

    public void setCodigo_evento_vital(String codigo_evento_vital) {
        this.codigo_evento_vital = codigo_evento_vital;
    }

    public int getCodigo_ocupacion() {
        return codigo_ocupacion;
    }

    public void setCodigo_ocupacion(int codigo_ocupacion) {
        this.codigo_ocupacion = codigo_ocupacion;
    }

    public int getCodigo_persona() {
        return codigo_persona;
    }

    public void setCodigo_persona(int codigo_persona) {
        this.codigo_persona = codigo_persona;
    }

    public boolean isCon_certificado() {
        return con_certificado;
    }

    public void setCon_certificado(boolean con_certificado) {
        this.con_certificado = con_certificado;
    }

    public Date getFecha_alfetizacion() {
        return fecha_alfetizacion;
    }

    public void setFecha_alfetizacion(Date fecha_alfetizacion) {
        this.fecha_alfetizacion = fecha_alfetizacion;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getNumero_familia() {
        return numero_familia;
    }

    public void setNumero_familia(int numero_familia) {
        this.numero_familia = numero_familia;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipo_ocu() {
        return tipo_ocu;
    }

    public void setTipo_ocu(String tipo_ocu) {
        this.tipo_ocu = tipo_ocu;
    }

    public ActividadEducativa getActividadEducativa() {
        return actividadEducativa;
    }

    public void setActividadEducativa(ActividadEducativa actividadEducativa) {
        this.actividadEducativa = actividadEducativa;
        if(actividadEducativa != null)
            this.codigo_actividad_educactiva = actividadEducativa.getCodigo_actividad_educactiva();
        else
            this.codigo_actividad_educactiva = null;

    }

    public CausasMuerte getCausaMuerte() {
        return causaMuerte;
    }

    public void setCausaMuerte(CausasMuerte causaMuerte) {       
        this.causaMuerte = causaMuerte;
        if(causaMuerte != null)
            this.codigo_causa_muerte = causaMuerte.getCodigo_causa_muerte();
        else
            this.codigo_causa_muerte = null;
    }

    public EventosVitales getEventoVital() {
        return eventoVital;
    }

    public void setEventoVital(EventosVitales eventoVital) {
        this.eventoVital = eventoVital;
        if(eventoVital != null)
            this.codigo_evento_vital = eventoVital.getCodigo_evento_vital();
        else
            this.codigo_evento_vital = null;

    }

    public Ocupaciones getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(Ocupaciones ocupacion) {
        this.ocupacion = ocupacion;
        if(ocupacion != null)
            this.codigo_ocupacion = ocupacion.getCodigo_ocupacion();
        else
            this.codigo_ocupacion = -1;
    }

    public String getCodigo_estado_familia() {
        return codigo_estado_familia;
    }

    public void setCodigo_estado_familia(String codigo_estado_familia) {
        this.codigo_estado_familia = codigo_estado_familia;
    }


    

    public String getNombreCompleto()
    {
        return ((this.apellido_paterno != null ? this.apellido_paterno.trim() : "")
                + " " +(this.apellido_materno != null ? this.apellido_materno.trim() : "")
                + " " +(this.nombres != null ? this.nombres.trim() : "")).trim().toUpperCase();
    }

    @Override
    public String toString() {
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
        if(this.numero_familia > 0)
                CadenaRetorno += String.valueOf(numero_familia) + ",";
        if (!CadenaRetorno.trim().isEmpty() && CadenaRetorno.trim().charAt(CadenaRetorno.trim().length()-1) == ',')
        {
                CadenaRetorno = CadenaRetorno.substring(0, CadenaRetorno.trim().length()-1);
        }

        System.out.println(CadenaRetorno);
        return CadenaRetorno;
    }

    public String getRepresentacionCadenaSexo()
    {
        return this.sexo.compareTo("F") == 0 ? "FEMENINO" : "MASCULINO";
    }

    public int compareTo(Familia o) {
        if(codigo_persona > o.codigo_persona)
            return 1;
        else if(codigo_persona < o.codigo_persona)
            return -1;
        else
            return 0;
    }

    public String getRuta_archivo_imagen() {
        return ruta_archivo_imagen;
    }

    public void setRuta_archivo_imagen(String ruta_archivo_imagen) {
        this.ruta_archivo_imagen = ruta_archivo_imagen;
    }

    
}
