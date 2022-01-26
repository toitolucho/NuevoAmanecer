/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;


public class Imparte implements Comparable<Imparte>{
    private int codigo_programa;
    private int ci;
    private Encargados encargado;
    public Imparte(int codigo_programa, int ci) {
        this.codigo_programa = codigo_programa;
        this.ci = ci;
    }

    public Encargados getEncargado() {
        return encargado;
    }

    public void setEncargado(Encargados encargado) {
        this.encargado = encargado;
    }

    

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public int getCodigo_programa() {
        return codigo_programa;
    }

    public void setCodigo_programa(int codigo_programa) {
        this.codigo_programa = codigo_programa;
    }

    public Imparte() {
    }

     @Override
    public String toString()
    {
        if(ci == -1)
            return String.valueOf(codigo_programa);
        else
        {
            return "";
        }
    }

     public int compareTo(Imparte o) {
        if(this.ci > o.ci)
            return 1;
        else if(this.ci < o.ci)
            return -1;
        else
            return 0;
    }

}
