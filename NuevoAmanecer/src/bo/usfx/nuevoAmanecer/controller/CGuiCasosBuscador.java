/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Afiliado;
import bo.usfx.nuevoAmanecer.model.domain.Casos;
import bo.usfx.utils.ModeloCaso;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import view.GuiCasosBuscador;


public class CGuiCasosBuscador extends MouseAdapter  implements ActionListener{

    private GuiCasosBuscador formBuscador;
    private CommonDao dao;
    Casos caso;
    ModeloCaso modelocaso;
    List<Casos> listacasos;
    public boolean buscarCasoss = false;

    public CGuiCasosBuscador(GuiCasosBuscador formBuscador) {
        this.formBuscador = formBuscador;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    public void actionPerformed(ActionEvent event) {
        String accion = event.getActionCommand();
        if (accion.compareTo("buscar") == 0) {
            modelocaso.clear();
            if (formBuscador.getTxtTextoBusqueda().getText().isEmpty()
                    && formBuscador.getDateFechaBusqueda().getDate() == null) {
                JOptionPane.showMessageDialog(formBuscador, "Aun no ha ingresado un texto de busqueda", "Buscador de Casos", JOptionPane.PLAIN_MESSAGE);
                formBuscador.getTxtTextoBusqueda().grabFocus();
            } else {
                caso = new Casos();
                caso.setAfiliado(new Afiliado());
                caso.getAfiliado().setNombres(this.formBuscador.getTxtTextoBusqueda().getText().trim());

                try {
                    caso.setFecha_registro(formBuscador.getDateFechaBusqueda().getDate());
                    caso.setNumero_caso(Integer.parseInt(this.formBuscador.getTxtTextoBusqueda().getText().trim()));
                    
                } catch (Exception e) {
                }
                try {
                    listacasos = dao.findObjects(caso);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if (caso != null) {
                    modelocaso.clear();

                    if (listacasos.size() > 0) {
                        for (Casos padres : listacasos) {
                            modelocaso.addCasos(padres);
                        }
                    } else {
                        modelocaso.clear();
                        JOptionPane.showMessageDialog(formBuscador, "No se encontró ningún Patrocinador con los datos provistos", "Buscador de caso", JOptionPane.ERROR_MESSAGE);
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
        modelocaso = new ModeloCaso();
        formBuscador.getjTableCasos().setModel(modelocaso);
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

    public Casos getCasoSeleccionado() {
        if (modelocaso == null || modelocaso.getRowCount() == 0
                || formBuscador.getjTableCasos().getSelectedRow() < 0) {
            return null;
        } else {
            return (Casos) modelocaso.getCasos(formBuscador.getjTableCasos().getSelectedRow());
        }
    }
}
