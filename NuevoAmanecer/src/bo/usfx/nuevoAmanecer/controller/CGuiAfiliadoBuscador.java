/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Afiliado;
import bo.usfx.nuevoAmanecer.model.domain.Familia;
import bo.usfx.nuevoAmanecer.model.domain.Familiar;
import bo.usfx.utils.ModeloFamilia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import view.GuiAfiliadoBuscador;


public class CGuiAfiliadoBuscador extends MouseAdapter  implements ActionListener{

    private GuiAfiliadoBuscador formBuscador;
    private CommonDao dao;
    Familia afiliado;
    ModeloFamilia modeloAfiliado;
    List<Afiliado> listaAfiliados;
    public char tipoBusqueda = 'A';

    public CGuiAfiliadoBuscador(GuiAfiliadoBuscador formBuscador) {
        this.formBuscador = formBuscador;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    public void actionPerformed(ActionEvent event) {
        String accion = event.getActionCommand();
        if (accion.compareTo("buscar") == 0) {
            modeloAfiliado.clear();
            if (formBuscador.getTxtTextoBusqueda().getText().isEmpty()) {
                JOptionPane.showMessageDialog(formBuscador, "Aun no ha ingresado un texto de busqueda", "Buscador de Patrocinadores", JOptionPane.PLAIN_MESSAGE);
                formBuscador.getTxtTextoBusqueda().grabFocus();
            } else {
                afiliado = tipoBusqueda == 'P' ? new Familia()  :  tipoBusqueda == 'A' ? new Afiliado() : new Familiar();
                afiliado.setNombres(this.formBuscador.getTxtTextoBusqueda().getText().trim());
                try {
                    listaAfiliados = dao.findObjects(afiliado);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if (afiliado != null) {
                    modeloAfiliado.clear();

                    if (listaAfiliados.size() > 0) {
                        for (Familia padres : listaAfiliados) {
                            modeloAfiliado.addFamilia(padres);
                        }
                    } else {
                        modeloAfiliado.clear();
                        JOptionPane.showMessageDialog(formBuscador, "No se encontró " + (tipoBusqueda  == 'P' ? "ninguna Familia" :
                            (tipoBusqueda == 'F' ? "ningún Familiar" : "ningún Afiliado "))  + " con los datos provistos", "Buscador de Afiliado", JOptionPane.ERROR_MESSAGE);
                        this.formBuscador.getTxtTextoBusqueda().grabFocus();
                        this.formBuscador.getTxtTextoBusqueda().selectAll();
                    }

                }
            }
        }
        else
        {
            this.formBuscador.setVisible(false);
        }
    }

    public void onFormShown() {
        if(tipoBusqueda == 'P' ) this.formBuscador.setTitle("Buscar Familia");
        else if (tipoBusqueda == 'A') this.formBuscador.setTitle("Buscar Afiliado");
        else this.formBuscador.setTitle("Buscar Miembro Familiar");
        modeloAfiliado = new ModeloFamilia();
        formBuscador.getjTableAfiliados().setModel(modeloAfiliado);
        formBuscador.getjTableAfiliados().addMouseListener(this);
        formBuscador.getTxtTextoBusqueda().grabFocus();
        formBuscador.getTxtTextoBusqueda().selectAll();
        formBuscador.setTitle("Buscador de Personas");
    }

    public void establecerOpcionesbusqueda(String TextoBusqueda)
    {
        formBuscador.getTxtTextoBusqueda().setText(TextoBusqueda);
        this.actionPerformed(new ActionEvent(this.formBuscador.getBtnBuscar(), 1, "buscar"));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getClickCount() == 2) {
            this.formBuscador.setVisible(false);
        }
    }

    public Familia getPatrocinadorSeleccionado() {
        if (modeloAfiliado == null || modeloAfiliado.getRowCount() == 0
                || formBuscador.getjTableAfiliados().getSelectedRow() < 0) {
            return null;
        } else {
            return (Afiliado) modeloAfiliado.getFamilia(formBuscador.getjTableAfiliados().getSelectedRow());
        }
    }
}
