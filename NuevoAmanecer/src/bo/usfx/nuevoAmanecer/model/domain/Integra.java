/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;


public class Integra implements Comparable<Integra>{
    private int codigo_programa;
    private int codigo_integrante;
    private Integrantes integrante;

    public Integra(int codigo_programa, int codigo_integrante) {
        this.codigo_programa = codigo_programa;
        this.codigo_integrante = codigo_integrante;
    }

    public Integra() {
    }

    public Integrantes getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Integrantes integrante) {
        this.integrante = integrante;
    }

    

    public int getCodigo_integrante() {
        return codigo_integrante;
    }

    public void setCodigo_integrante(int codigo_integrante) {
        this.codigo_integrante = codigo_integrante;
    }

    public int getCodigo_programa() {
        return codigo_programa;
    }

    public void setCodigo_programa(int codigo_programa) {
        this.codigo_programa = codigo_programa;
    }


    @Override
    public String toString()
    {
        if(codigo_integrante == -1)
            return String.valueOf(codigo_programa);
        else
        {
            return "";
        }
    }

    public int compareTo(Integra o) {
        if(this.codigo_integrante > o.codigo_integrante)
            return 1;
        else if(this.codigo_integrante < o.codigo_integrante)
            return -1;
        else
            return 0;
    }
}
