/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;


public class CausasMuerte implements Comparable<CausasMuerte> {
    private String codigo_causa_muerte;
    private String nommbre_causa_muerte;

    public CausasMuerte(String codigo_causa_muerte, String nommbre_causa_muerte) {
        this.codigo_causa_muerte = codigo_causa_muerte;
        this.nommbre_causa_muerte = nommbre_causa_muerte;
    }

    public CausasMuerte() {
    }

    public String getCodigo_causa_muerte() {
        return codigo_causa_muerte;
    }

    public void setCodigo_causa_muerte(String codigo_causa_muerte) {
        this.codigo_causa_muerte = codigo_causa_muerte;
    }

    public String getNommbre_causa_muerte() {
        return nommbre_causa_muerte;
    }

    public void setNommbre_causa_muerte(String nommbre_causa_muerte) {
        this.nommbre_causa_muerte = nommbre_causa_muerte;
    }


    @Override
    public String toString()
    {
        return this.nommbre_causa_muerte;
    }

    public int compareTo(CausasMuerte o) {
        return this.nommbre_causa_muerte.compareTo(o.nommbre_causa_muerte);
    }

}
