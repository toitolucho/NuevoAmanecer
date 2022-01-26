/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Ocupaciones;
import bo.usfx.utils.ModeloOcupaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.GuiOcupaciones;

/**
 *
 * @author Luis Molina
 */
public class CGuiOcupaciones implements ActionListener, ListSelectionListener {
    GuiOcupaciones formOcupaciones;
    String TituloOcupacion = "Ocupaciones";
    ModeloOcupaciones modeloOcupaciones;
    String TipoOperacion = "";
    private CommonDao dao;

    public CGuiOcupaciones(GuiOcupaciones formOcupaciones){
        this.formOcupaciones = formOcupaciones;
        modeloOcupaciones = new ModeloOcupaciones();
    }


    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();
        if(accion.compareTo("nuevo")== 0)
        {
            limpiarControles();
            habilitarBotones(false, false, true, true, false);
            habilitarControles(true);
            TipoOperacion = "I";

            int ultimoId = -1;
            try {
                ultimoId = dao.obtenerUltimoObjetoTabla(new Ocupaciones(), "");
            } catch (SQLException ex) {
                Logger.getLogger(CGuiOcupaciones.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.formOcupaciones.getTxtCodigo().setText(String.valueOf(++ultimoId));

        }
        if(accion.compareTo("modificar")== 0)
        {
            habilitarBotones(false, false, true, true, false);
            habilitarControles(true);
            TipoOperacion = "E";
        }
        if(accion.compareTo("cancelar")== 0)
        {
            limpiarControles();
            habilitarBotones(true, false, false, false, false);
            habilitarControles(false);
            TipoOperacion = "";
        }
        if(accion.compareTo("aceptar")== 0)
        {
            if (validarDatos()) {
                try {
                    Ocupaciones ocupa = new Ocupaciones();
                    ocupa.setNombre_ocupacion(formOcupaciones.getTxtNombreOcupacion().getText());
                    ocupa.setCodigo_ocupacion(Integer.valueOf(formOcupaciones.getTxtCodigo().getText()));
                    if (TipoOperacion.compareTo("I") == 0) {
                        dao.insertar(ocupa);
                        modeloOcupaciones.addOcupaciones(ocupa);
                    } else {
                        dao.edit(ocupa);
                        if (formOcupaciones.getjTableOcupaciones().getSelectedRow() >= 0) {
                            modeloOcupaciones.editarOcupaciones(ocupa, formOcupaciones.getjTableOcupaciones().getSelectedRow());
                        }
                    }
                    habilitarBotones(true, true, false, false, true);
                    JOptionPane.showMessageDialog(formOcupaciones, "Operación realizada correctamente", TituloOcupacion, JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
//                    Logger.getLogger(CGuiOcupaciones.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(formOcupaciones, "No se pudo culminar la operación actual, "
                            + "Probablemente el nombre de la ocupacion ya se encuentra registrado", TituloOcupacion, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if(accion.compareTo("eliminar")== 0 &&
                formOcupaciones.getjTableOcupaciones().getSelectedRow() >= 0
                && JOptionPane.showConfirmDialog(formOcupaciones, "¿Se encuentra seguro de Eliminar el Registro actual?", TituloOcupacion,
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
            try {
                dao.delete(modeloOcupaciones.getOcupaciones(formOcupaciones.getjTableOcupaciones().getSelectedRow()));
                modeloOcupaciones.removeOcupaciones(formOcupaciones.getjTableOcupaciones().getSelectedRow());

                JOptionPane.showMessageDialog(formOcupaciones, "Operación realizada correctamente", TituloOcupacion, JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(formOcupaciones, "No se pudo culminar la operación actual, "
                            + "Probablemente la ocupacion se encuentra utilizada en otra operación", TituloOcupacion, JOptionPane.ERROR_MESSAGE);
//                Logger.getLogger(CGuiOcupaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void limpiarControles()
    {
        this.formOcupaciones.getTxtCodigo().setText("");
        this.formOcupaciones.getTxtNombreOcupacion().setText("");
    }

    public void habilitarControles(boolean estadoHabilitacion)
    {
        this.formOcupaciones.getTxtNombreOcupacion().setEnabled(estadoHabilitacion);
    }

    public void habilitarBotones(boolean nuevo, boolean editar, boolean cancelar, boolean aceptar, boolean eliminar){
        this.formOcupaciones.getBtnNuevo().setEnabled(nuevo);
        this.formOcupaciones.getBtnModificar().setEnabled(editar);
        this.formOcupaciones.getBtnCancelar().setEnabled(cancelar);
        this.formOcupaciones.getBtnAceptar().setEnabled(aceptar);
        this.formOcupaciones.getBtnEliminar().setEnabled(eliminar);
    }

    public boolean validarDatos(){
        if(formOcupaciones.getTxtNombreOcupacion().getText().isEmpty()){
            JOptionPane.showMessageDialog(formOcupaciones, "No puede dejar en blanco el Nombre de la Ocupación", TituloOcupacion, JOptionPane.WARNING_MESSAGE);
            formOcupaciones.getTxtNombreOcupacion().grabFocus();
            return false;
        }

        return true;
    }

    public void cargarDatosOcupaciones(Ocupaciones ocupacionLlenado)
    {
        if(ocupacionLlenado != null){
            this.formOcupaciones.getTxtCodigo().setText(String.valueOf(ocupacionLlenado.getCodigo_ocupacion()));
            this.formOcupaciones.getTxtNombreOcupacion().setText(ocupacionLlenado.getNombre_ocupacion());
            habilitarBotones(true, true, false, false, true);

        } else{
            limpiarControles();
            habilitarBotones(true, false, false, false, false);
        }
        habilitarControles(false);

    }

    public void onFormShown(){
        cargarDatosOcupaciones(null);
        //formIntegrante.getjTableModificar().getSelectionModel().addListSelectionListener(this);
        this.formOcupaciones.getjTableOcupaciones().setModel(modeloOcupaciones);
        this.formOcupaciones.getjTableOcupaciones().getSelectionModel().addListSelectionListener(this);
        try {
            List<Ocupaciones> listaOcupaciones = dao.getObjects("Ocupaciones");
            for (Ocupaciones ocupaciones : listaOcupaciones) {
                modeloOcupaciones.addOcupaciones(ocupaciones);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CGuiOcupaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.formOcupaciones.getBtnAceptar().setActionCommand("aceptar");
        this.formOcupaciones.getBtnCancelar().setActionCommand("cancelar");
        this.formOcupaciones.getBtnEliminar().setActionCommand("eliminar");
        this.formOcupaciones.getBtnModificar().setActionCommand("modificar");
        this.formOcupaciones.getBtnNuevo().setActionCommand("nuevo");

        this.formOcupaciones.getBtnAceptar().addActionListener(this);
        this.formOcupaciones.getBtnCancelar().addActionListener(this);
        this.formOcupaciones.getBtnEliminar().addActionListener(this);
        this.formOcupaciones.getBtnModificar().addActionListener(this);
        this.formOcupaciones.getBtnNuevo().addActionListener(this);

    }
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
                return; // if you don't want to handle intermediate selections
        ListSelectionModel rowSM = (ListSelectionModel) e.getSource();
        int indice = rowSM.getMinSelectionIndex();
        // ... // do something with selected index
        // System.out.println("from valueChanged  "+indice);
        if(indice >= 0)
        {
            cargarDatosOcupaciones(modeloOcupaciones.getOcupaciones(indice));
        }
        else
            cargarDatosOcupaciones(null);

    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }



}
