/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;


public class Participa implements Comparable<Participa>{
    private int codigo_persona;
    private int codigo_programa;
    Afiliado afiliado;

    public Participa(int codigo_persona, int codigo_programa) {
        this.codigo_persona = codigo_persona;
        this.codigo_programa = codigo_programa;
    }

    public Participa() {
    }

    public int getCodigo_persona() {
        return codigo_persona;
    }

    public void setCodigo_persona(int codigo_persona) {
        this.codigo_persona = codigo_persona;
    }

    public int getCodigo_programa() {
        return codigo_programa;
    }

    public void setCodigo_programa(int codigo_programa) {
        this.codigo_programa = codigo_programa;
    }

    public Afiliado getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(Afiliado afiliado) {
        this.afiliado = afiliado;
    }
    
    @Override
    public String toString()
    {
        if(codigo_persona == -1)
            return String.valueOf(codigo_programa);
        else
        {
            return "";
        }
    }

    public int compareTo(Participa o) {
        if(this.codigo_persona > o.codigo_persona)
            return 1;
        else if(this.codigo_persona < o.codigo_persona)
            return -1;
        else
            return 0;
    }


}
