/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;


public class Integrantes {
    private int codigo_integrante;
    private String apellido_paterno;
    private String apellido_materno;
    private String nombres;
    private String sexo;
    private int edad;

    public Integrantes(int codigo_integrante, String apellido_paterno, String apellido_materno, String nombres, String sexo, int edad) {
        this.codigo_integrante = codigo_integrante;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.nombres = nombres;
        this.sexo = sexo;
        this.edad = edad;
    }

    public Integrantes() {
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

    public int getCodigo_integrante() {
        return codigo_integrante;
    }

    public void setCodigo_integrante(int codigo_integrante) {
        this.codigo_integrante = codigo_integrante;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNombreCompleto()
    {
        return  ((apellido_paterno == null ? "" : apellido_paterno.trim())
                + " " + (apellido_materno == null ? "" : apellido_materno.trim())
                + " " + (nombres == null ? "" : nombres.trim())).toUpperCase();
    }

    public String getSexoCadena()
    {
        return (sexo.compareTo("F") == 0 ? "FEMENINO" : "MASCULINO").toUpperCase();
    }



    @Override
    public String toString()
    {
        String CadenaRetorno = "";

        if(!this.getNombres().isEmpty())
                CadenaRetorno += getNombres() + ",";
        if(this.codigo_integrante > 0)
                CadenaRetorno += String.valueOf(codigo_integrante) + ",";

        if (!CadenaRetorno.trim().isEmpty() && CadenaRetorno.trim().charAt(CadenaRetorno.trim().length()-1) == ',')
        {
                CadenaRetorno = CadenaRetorno.substring(0, CadenaRetorno.trim().length()-1);
        }

        System.out.println(CadenaRetorno);
        return CadenaRetorno;

    }
}
