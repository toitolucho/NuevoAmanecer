/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;


public class Encargados {
    private int ci;
    private String apellido_paterno;
    private String apellido_materno;
    private String nombres;
    private String profesion;
    private String sexo;
    private int edad;
    private int telefono;
    private String domicilio;

    public Encargados(int ci, String apellido_paterno, String apellido_materno, String nombres, String profesion, String sexo, int edad, int telefono, String domicilio) {
        this.ci = ci;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.nombres = nombres;
        this.profesion = profesion;
        this.sexo = sexo;
        this.edad = edad;
        this.telefono = telefono;
        this.domicilio = domicilio;
    }

    public Encargados() {
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

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
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

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString()
    {
            String CadenaRetorno = "";
            if(!this.getNombres().isEmpty())
                CadenaRetorno += getNombres() + ",";
            if(this.ci > 0)
                CadenaRetorno += String.valueOf(ci) + ",";

            if (!CadenaRetorno.trim().isEmpty() && CadenaRetorno.trim().charAt(CadenaRetorno.trim().length()-1) == ',')
            {
                CadenaRetorno = CadenaRetorno.substring(0, CadenaRetorno.trim().length()-1);
            }

            System.out.println(CadenaRetorno);
            return CadenaRetorno;

    }

    public String getNombreCompleto() {
        return (nombres.trim() + " " + apellido_paterno.trim() + " " + apellido_materno.trim()).toUpperCase();
    }


    public boolean esMasculino()
    {
        return this.sexo.compareTo("M") == 0;
    }
}
