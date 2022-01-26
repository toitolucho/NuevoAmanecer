/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Padrinos;
import bo.usfx.utils.ModeloPatrocinador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import view.GuiPatrocinadorBuscador;


public class CGuiPatrocinadorBuscador extends MouseAdapter implements ActionListener {

    private GuiPatrocinadorBuscador formBuscador;
    private CommonDao dao;
    ModeloPatrocinador modeloPatrocinador;
    Padrinos padrino;
    List<Padrinos> listaPadrinos;

    public CGuiPatrocinadorBuscador(GuiPatrocinadorBuscador formBuscador) {
        this.formBuscador = formBuscador;
        modeloPatrocinador = new ModeloPatrocinador();
    }

    public CommonDao getDao() {
        return dao;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }
    

    public void actionPerformed(ActionEvent event) {
        String accion = event.getActionCommand();
        if (accion.compareTo("buscar") == 0) {
            modeloPatrocinador.clear();
            if (formBuscador.getTxtTextoBusqueda().getText().isEmpty()) {
                JOptionPane.showMessageDialog(formBuscador, "Aun no ha ingresado un texto de busqueda", "Buscador de Patrocinadores", JOptionPane.PLAIN_MESSAGE);
                formBuscador.getTxtTextoBusqueda().grabFocus();
            } else {
                padrino = new Padrinos();
                padrino.setNombre(this.formBuscador.getTxtTextoBusqueda().getText().trim());
                try {
                    listaPadrinos = dao.findObjects(padrino);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if (padrino != null) {
                    modeloPatrocinador.clear();

                    if (listaPadrinos.size() > 0) {
                        for (Padrinos padres : listaPadrinos) {
                            modeloPatrocinador.addPadrinos(padres);
                        }
                    } else {
                        modeloPatrocinador.clear();
                        JOptionPane.showMessageDialog(formBuscador, "No se encontró ningún Patrocinador con los datos provistos", "Buscador de Padrinos", JOptionPane.ERROR_MESSAGE);
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
        formBuscador.getjTablePatrocinadores().setModel(modeloPatrocinador);
        formBuscador.getjTablePatrocinadores().addMouseListener(this);
        formBuscador.getTxtTextoBusqueda().grabFocus();
        formBuscador.getTxtTextoBusqueda().selectAll();
    }

    public void establecerOpcionesbusqueda(String TextoBusqueda)
    {
        this.formBuscador.getTxtTextoBusqueda().setText(TextoBusqueda);
        this.actionPerformed(new ActionEvent(this.formBuscador.getBtnBuscar(), 1, "buscar"));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getClickCount() == 2) {
            this.formBuscador.setVisible(false);
        }
    }

    public Padrinos getPatrocinadorSeleccionado() {
        if (modeloPatrocinador == null || modeloPatrocinador.getRowCount() == 0) {
            return null;
        } else {
            return modeloPatrocinador.getPadrinos(formBuscador.getjTablePatrocinadores().getSelectedRow());
        }
    }
}
