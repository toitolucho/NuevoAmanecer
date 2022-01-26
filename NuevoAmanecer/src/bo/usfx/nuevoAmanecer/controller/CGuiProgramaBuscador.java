

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;

import bo.usfx.nuevoAmanecer.model.domain.Programas;
import bo.usfx.utils.ModeloPrograma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import view.GuiProgramaBuscador;


public class CGuiProgramaBuscador extends MouseAdapter  implements ActionListener{

    private GuiProgramaBuscador formBuscador;
    private CommonDao dao;
    Programas Programas;
    ModeloPrograma modeloProgramas;
    List<Programas> listaProgramass;
    

    public CGuiProgramaBuscador(GuiProgramaBuscador formBuscador) {
        this.formBuscador = formBuscador;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    public void actionPerformed(ActionEvent event) {
        String accion = event.getActionCommand();
        if (accion.compareTo("buscar") == 0) {
            modeloProgramas.clear();
            if (formBuscador.getTxtTextoBusqueda().getText().isEmpty()
                    && formBuscador.getDateFechaBusqueda().getDate() == null) {
                JOptionPane.showMessageDialog(formBuscador, "Aun no ha ingresado un texto de busqueda", "Buscador de Programas", JOptionPane.PLAIN_MESSAGE);
                formBuscador.getTxtTextoBusqueda().grabFocus();
            } else {
                Programas = new Programas();
                Programas.setNombre_actividad(this.formBuscador.getTxtTextoBusqueda().getText().trim());
                try {
                    Programas.setFecha_programa(formBuscador.getDateFechaBusqueda().getDate());

                } catch (Exception e) {
                }
                try {
                    Programas.setCodigo_programa(Integer.parseInt(formBuscador.getTxtTextoBusqueda().getText()));
                } catch (Exception numerException) {
                }
                try {
                    listaProgramass = dao.findObjects(Programas);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if (Programas != null) {
                    modeloProgramas.clear();

                    if (listaProgramass.size() > 0) {
                        for (Programas padres : listaProgramass) {
                            modeloProgramas.addProgramas(padres);
                        }
                    } else {
                        modeloProgramas.clear();
                        JOptionPane.showMessageDialog(formBuscador, "No se encontró ningún Programa con los datos provistos", "Buscador de Programas", JOptionPane.ERROR_MESSAGE);
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
        modeloProgramas = new ModeloPrograma();
        formBuscador.getjTableCasos().setModel(modeloProgramas);
        formBuscador.getjTableCasos().addMouseListener(this);
        formBuscador.getTxtTextoBusqueda().grabFocus();
        formBuscador.getTxtTextoBusqueda().selectAll();
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

    public Programas getProgramaSeleccionado() {
        if (modeloProgramas == null || modeloProgramas.getRowCount() == 0) {
            return null;
        } else {
            return (Programas) modeloProgramas.getProgramas(formBuscador.getjTableCasos().getSelectedRow());
        }
    }
}
