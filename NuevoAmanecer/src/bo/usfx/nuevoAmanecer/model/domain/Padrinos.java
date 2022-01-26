/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;


public class Padrinos {
    private int codigo_padrino;
    private int numero_padrino;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String nombre2;
    private String apellido_paterno2;
    private String apellido_materno2;

    public Padrinos(int codigo_padrino, int numero_padrino, String nombre, String apellido_paterno, String apellido_materno) {
        this.codigo_padrino = codigo_padrino;
        this.numero_padrino = numero_padrino;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
    }

    public Padrinos(int codigo_padrino, int numero_padrino, String nombre, String apellido_paterno, String apellido_materno, String nombre2, String apellido_paterno2, String apellido_materno2) {
        this.codigo_padrino = codigo_padrino;
        this.numero_padrino = numero_padrino;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.nombre2 = nombre2;
        this.apellido_paterno2 = apellido_paterno2;
        this.apellido_materno2 = apellido_materno2;
    }

    public Padrinos() {
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getApellido_materno2() {
        return apellido_materno2;
    }

    public void setApellido_materno2(String apellido_materno2) {
        this.apellido_materno2 = apellido_materno2;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_paterno2() {
        return apellido_paterno2;
    }

    public void setApellido_paterno2(String apellido_paterno2) {
        this.apellido_paterno2 = apellido_paterno2;
    }

    public int getCodigo_padrino() {
        return codigo_padrino;
    }

    public void setCodigo_padrino(int codigo_padrino) {
        this.codigo_padrino = codigo_padrino;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public int getNumero_padrino() {
        return numero_padrino;
    }

    public void setNumero_padrino(int numero_padrino) {
        this.numero_padrino = numero_padrino;
    }

    public String getNombreCompleto()
    {
        return  ((apellido_paterno == null ? "" : apellido_paterno.trim())
                + " " + (apellido_materno == null ? "" : apellido_materno.trim())
                + " " + (nombre == null ? "" : nombre.trim())).toUpperCase();
    }

    public String getNombreCompleto2()
    {
        return  ((apellido_paterno2 == null ? "" : apellido_paterno2.trim())
                + " " + (apellido_materno2 == null ? "" : apellido_materno2.trim())
                + " " + (nombre2 == null ? "" : nombre2.trim())).toUpperCase();
    }


    @Override
    public String toString()
    {
        String CadenaRetorno = "";

        if(!this.getNombre().isEmpty())
                CadenaRetorno += getNombre() + ",";
        if(this.codigo_padrino > 0)
                CadenaRetorno += String.valueOf(codigo_padrino) + ",";

        if (!CadenaRetorno.trim().isEmpty() && CadenaRetorno.trim().charAt(CadenaRetorno.trim().length()-1) == ',')
        {
                CadenaRetorno = CadenaRetorno.substring(0, CadenaRetorno.trim().length()-1);
        }

        System.out.println(CadenaRetorno);
        return CadenaRetorno;

    }

}
