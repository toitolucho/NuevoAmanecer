/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Proyecto;
import bo.usfx.nuevoAmanecer.model.domain.Tarjeta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import view.GuiObservaciones;

public class CGuiObservaciones extends KeyAdapter implements ActionListener, DocumentListener{
    private GuiObservaciones formObservaciones;
    private CommonDao dao;

    public CGuiObservaciones(GuiObservaciones formObservaciones) {
        this.formObservaciones = formObservaciones;
    }

    public CommonDao getDao() {
        return dao;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    

    public void onFormShown()
    {
         this.formObservaciones.getBtnEliminar().setVisible(false);
         this.formObservaciones.getBtnRegistrar().setVisible(false);
         this.formObservaciones.getBtnModificar().setEnabled(false);

         formObservaciones.getTxtDireccionVivienda().setEditable(false);
        formObservaciones.getTxtObservaciones().setEditable(false);
    }

    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();
        if(accion.compareTo(formObservaciones.getBtnModificar().getActionCommand()) == 0)
        {
            try {
                int numeroFamilia = Integer.parseInt(formObservaciones.getTxtNroFamiliar().getText());
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.setNumero_familia(numeroFamilia);
                tarjeta.setObservaciones(formObservaciones.getTxtObservaciones().getText());
                tarjeta.setPlano_vivienda(formObservaciones.getTxtDireccionVivienda().getText());

                dao.edit(tarjeta);
                formObservaciones.getBtnModificar().setEnabled(false);
                formObservaciones.getTxtDireccionVivienda().setEditable(false);
                formObservaciones.getTxtObservaciones().setEditable(false);

                JOptionPane.showMessageDialog(formObservaciones, "Actualización Correcta");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(formObservaciones, "Ocurrio la siguiente Excepcion " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    public void insertUpdate(DocumentEvent e) {
        cargarDatos();
    }

    public void removeUpdate(DocumentEvent e) {
        cargarDatos();
    }

    public void changedUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void cargarDatos()
    {
        try {
            int numeroFamilia = Integer.parseInt(formObservaciones.getTxtNroFamiliar().getText());
            Tarjeta tarjeta = new Tarjeta();
            tarjeta.setNumero_familia(numeroFamilia);
            tarjeta = (Tarjeta) dao.obtenerObjeto(tarjeta);
            if(tarjeta != null)
            {
                formObservaciones.getTxtDireccionVivienda().setText(tarjeta.getPlano_vivienda());
                formObservaciones.getTxtObservaciones().setText(tarjeta.getObservaciones());
                formObservaciones.getBtnModificar().setEnabled(true);
                formObservaciones.getTxtDireccionVivienda().setEditable(true);
                formObservaciones.getTxtObservaciones().setEditable(true);
            }
            else
            {
                formObservaciones.getTxtDireccionVivienda().setText("");
                formObservaciones.getTxtObservaciones().setText("");
                formObservaciones.getBtnModificar().setEnabled(false);
                formObservaciones.getTxtDireccionVivienda().setEditable(false);
                formObservaciones.getTxtObservaciones().setEditable(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
            if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                            || (c == KeyEvent.VK_DELETE) ))) {
                    formObservaciones.getToolkit().beep();
                    e.consume();
            }
    }
}
