

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;

import bo.usfx.nuevoAmanecer.model.domain.Tarjeta;
import bo.usfx.utils.ModeloTarjeta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import view.GuiTarjetaBuscador;


public class CGuiTarjetaBuscador extends MouseAdapter  implements ActionListener, KeyListener{

    private GuiTarjetaBuscador formBuscador;
    private CommonDao dao;
    Tarjeta tarjeta;
    ModeloTarjeta modeloTarjetas;
    List<Tarjeta> listaTarjetas;


    public CGuiTarjetaBuscador(GuiTarjetaBuscador formBuscador) {
        this.formBuscador = formBuscador;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    public void actionPerformed(ActionEvent event) {
        String accion = event.getActionCommand();
        if (accion.compareTo("buscar") == 0) {
            modeloTarjetas.clear();
            if (formBuscador.getTxtTextoBusqueda().getText().isEmpty()
                    && formBuscador.getDateFechaBusqueda().getDate() == null) {
                JOptionPane.showMessageDialog(formBuscador, "Aun no ha ingresado un texto de busqueda", "Buscador de Tarjetas", JOptionPane.PLAIN_MESSAGE);
                formBuscador.getTxtTextoBusqueda().grabFocus();
            } else {
                tarjeta = new Tarjeta();
                tarjeta.setAplicarFiltroBusqueda(true);
                tarjeta.setDescripcion(this.formBuscador.getTxtTextoBusqueda().getText().trim());
                
                try {
                    tarjeta.setFecha_registro_tarjeta(formBuscador.getDateFechaBusqueda().getDate());
                    tarjeta.setNumero_familia(Integer.parseInt(this.formBuscador.getTxtTextoBusqueda().getText().trim()));

                } catch (Exception e) {
                }
                try {
                    listaTarjetas = dao.findObjects(tarjeta);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if (tarjeta != null) {
                    modeloTarjetas.clear();

                    if (listaTarjetas.size() > 0) {
                        for (Tarjeta padres : listaTarjetas) {
                            modeloTarjetas.addTarjeta(padres);
                        }
                    } else {                        
                        JOptionPane.showMessageDialog(formBuscador, "No se encontró ninguna Tarjeta con los datos provistos", "Buscador de Tarjeta", JOptionPane.ERROR_MESSAGE);
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
        modeloTarjetas = new ModeloTarjeta();
        modeloTarjetas.setMostrarDescripcion(true);
        formBuscador.getjTableTarjetas().setModel(modeloTarjetas);
        formBuscador.getjTableTarjetas().addMouseListener(this);
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

    public Tarjeta getTarjetaSeleccionada() {
        if (modeloTarjetas == null || modeloTarjetas.getRowCount() == 0) {
            return null;
        } else {
            return (Tarjeta) modeloTarjetas.getTarjeta(formBuscador.getjTableTarjetas().getSelectedRow());
        }
    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        JTextField componente = (JTextField) e.getSource();
        char c = e.getKeyChar();
        if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                                || (c == KeyEvent.VK_DELETE) ))) {
                        formBuscador.getToolkit().beep();
                        e.consume();
        }
    }

    public void keyPressed(KeyEvent e) {
        
    }

    public void keyReleased(KeyEvent e) {
        
    }
}
