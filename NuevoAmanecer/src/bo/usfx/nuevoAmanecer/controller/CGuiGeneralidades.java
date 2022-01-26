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
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.GuiGeneralidades;


public class CGuiGeneralidades implements ActionListener{
    private GuiGeneralidades formGeneralidades;
    private CommonDao dao;
    private int NumeroProyecto = 1;
    String TipoOperacion = "";

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    public CGuiGeneralidades(GuiGeneralidades formGeneralidades) {
        this.formGeneralidades = formGeneralidades;
        this.formGeneralidades.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("CallToThreadDumpStack")
    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();
        if(accion.compareTo(formGeneralidades.getBtnNuevo().getActionCommand())==0)
        {
            limpiarControles();
            habilitarControles(true);
            habilitarBotones(false, false, false, true);
            try {
                int numeroTarjeta = dao.obtenerUltimoObjetoTabla(new Tarjeta(),"");
                numeroTarjeta = numeroTarjeta == -1 ? 1 : numeroTarjeta + 1;
                formGeneralidades.getTxtNumeroFamilia().setText(String.valueOf(numeroTarjeta));
                TipoOperacion = "N";
            } catch (SQLException ex) {
                Logger.getLogger(CGuiGeneralidades.class.getName()).log(Level.SEVERE, null, ex);
                System.out.print(ex.getMessage());
                ex.printStackTrace();
            }
        }

        if(accion.compareTo(formGeneralidades.getBtnEliminar().getActionCommand())==0)
        {
            String response = JOptionPane.showInputDialog(null,
                    "Ingrese el Numero de Familia?",
                    "Gestion de Tarjeta Familiar",
                    JOptionPane.QUESTION_MESSAGE);
            if(response != null )
            {
                int NumeroTarjeta = -1;
                try {
                    NumeroTarjeta = Integer.valueOf(response);
                    Tarjeta tarjeta = new Tarjeta();
                    tarjeta.setNumero_familia(NumeroTarjeta);
                    try {
                        tarjeta = (Tarjeta) dao.obtenerObjeto(tarjeta);

                    } catch (SQLException ex) {
                        Logger.getLogger(CGuiGeneralidades.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if(tarjeta != null)
                    {
                        formGeneralidades.getTxtNumeroFamilia().setText(String.valueOf(tarjeta.getNumero_familia()));
                        formGeneralidades.getcBoxSeleccionarComunidad().setSelectedItem(tarjeta.getComunidad());
                        formGeneralidades.getjDCHFechaEncuesta().setDate(tarjeta.getFecha_registro_tarjeta());
                        habilitarControles(false);
                        habilitarBotones(true, false, true, false);
                        tarjeta.setCodigo_estado_tarjeta("B");
                        if(JOptionPane.showConfirmDialog(formGeneralidades, "¿Se encuentra seguro de eliminar La Tarjeta Familiar Nro"
                                + String.valueOf(tarjeta.getNumero_familia()) + "?", "Gestión de Tarjeta Familiar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                        {
                            try {
                                dao.edit(tarjeta);
                                JOptionPane.showMessageDialog(formGeneralidades, "Eliminación correcta", "Gestión Tarjeta Familiar", JOptionPane.INFORMATION_MESSAGE);
                                limpiarControles();
                            } catch (SQLException ex) {
                                Logger.getLogger(CGuiGeneralidades.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(formGeneralidades, "No se pudo realizar correctamente la operacion Actual, ocurrió la siguiente excepcion " + ex.getMessage(), "Gestion de Tarjeta Familia", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(formGeneralidades, "No existe ningún registro con la información provista", "Gestion de Tarjeta Familia", JOptionPane.ERROR_MESSAGE);
                        formGeneralidades.getTxtNumeroFamilia().setText("");
                        formGeneralidades.getcBoxSeleccionarComunidad().setSelectedItem(null);
                        formGeneralidades.getjDCHFechaEncuesta().setDate(null);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(formGeneralidades, "No ingresó correctamente los datos Necesarios", "Gestion de Tarjeta Familia", JOptionPane.ERROR_MESSAGE);
                }
            }
             else
            {
                JOptionPane.showMessageDialog(formGeneralidades, "No ingresó correctamente los datos Necesarios", "Gestion de Tarjeta Familia", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(accion.compareTo(formGeneralidades.getBtnModificar().getActionCommand())==0)
        {
            String response = JOptionPane.showInputDialog(null,
                    "Ingrese el Numero de Familia?",
                    "Gestion de Tarjeta Familiar",
                    JOptionPane.QUESTION_MESSAGE);
            if(response != null )
            {
                int NumeroTarjeta = -1;
                try {
                    NumeroTarjeta = Integer.valueOf(response);
                    Tarjeta tarjeta = new Tarjeta();
                    tarjeta.setNumero_familia(NumeroTarjeta);
                    try {
                        tarjeta = (Tarjeta) dao.obtenerObjeto(tarjeta);

                    } catch (SQLException ex) {
                        Logger.getLogger(CGuiGeneralidades.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if(tarjeta != null)
                    {
                        formGeneralidades.getTxtNumeroFamilia().setText(String.valueOf(tarjeta.getNumero_familia()));
                        formGeneralidades.getcBoxSeleccionarComunidad().setSelectedItem(tarjeta.getComunidad());
                        formGeneralidades.getjDCHFechaEncuesta().setDate(tarjeta.getFecha_registro_tarjeta());
                        habilitarControles(true);
                        habilitarBotones(false, false, false, true);
                        TipoOperacion ="E";
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(formGeneralidades, "No existe ningún registro con la información provista", "Gestion de Tarjeta Familia", JOptionPane.ERROR_MESSAGE);
                        formGeneralidades.getTxtNumeroFamilia().setText("");
                        formGeneralidades.getcBoxSeleccionarComunidad().setSelectedItem(null);
                        formGeneralidades.getjDCHFechaEncuesta().setDate(null);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(formGeneralidades, "No ingresó correctamente los datos Necesarios", "Gestion de Tarjeta Familia", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(formGeneralidades, "No ingresó correctamente los datos Necesarios", "Gestion de Tarjeta Familia", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(accion.compareTo(formGeneralidades.getBtnRegistrar().getActionCommand())==0)
        {
            if(formGeneralidades.getcBoxSeleccionarComunidad().getSelectedIndex() == -1
                    || formGeneralidades.getcBoxSeleccionarComunidad().getSelectedIndex() == 0)
            {
                JOptionPane.showMessageDialog(formGeneralidades, "Aun no ha seleccionado la Comunidad");
            }
            else
            {
                Tarjeta tarjeta = new Tarjeta(TipoOperacion == "E" ? Integer.parseInt(formGeneralidades.getTxtNumeroFamilia().getText()) :  1, formGeneralidades.getjDCHFechaEncuesta().getDate(),
                        formGeneralidades.getcBoxSeleccionarComunidad().getSelectedItem().toString(),
                        "BARRIO", 1, "OBSERVACIONES", "PLANO", NumeroProyecto);

                habilitarControles(false);
                habilitarBotones(true, true, true, false);
                try {
                    if(TipoOperacion == "N")
                        dao.insertar(tarjeta);
                    else
                        dao.edit(tarjeta);
                    TipoOperacion ="";
                    JOptionPane.showMessageDialog(formGeneralidades, "Registro Realizado satisfactoriamente", "Tarjeta", NumeroProyecto);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(formGeneralidades, "No se pudo registrar el registro", "Tarjeta", NumeroProyecto);
                    System.out.print(ex.getMessage());
                    ex.printStackTrace();
                }

            }

        }
    }

    void setNumeroProyecto(int NumeroProyecto) {
        
    }

    public void limpiarControles()
    {
        this.formGeneralidades.getcBoxSeleccionarComunidad().setSelectedIndex(-1);
        this.formGeneralidades.getjDCHFechaEncuesta().setDate(Calendar.getInstance().getTime());
    }

    public void habilitarControles(boolean estadoHabilitacion)
    {
        this.formGeneralidades.getcBoxSeleccionarComunidad().setEnabled(estadoHabilitacion);
//        this.formGeneralidades.getjDCHFechaEncuesta().setEnabled(estadoHabilitacion);
    }

    public void habilitarBotones(boolean nuevo, boolean editar, boolean eliminar, boolean registrar)
    {
        this.formGeneralidades.getBtnNuevo().setEnabled(nuevo);
        this.formGeneralidades.getBtnModificar().setEnabled(editar);
        this.formGeneralidades.getBtnEliminar().setEnabled(eliminar);
        this.formGeneralidades.getBtnRegistrar().setEnabled(registrar);
    }
    
    public void onFormShow() {
        //throw new UnsupportedOperationException("Not yet implemented");
        habilitarControles(false);
        limpiarControles();
        habilitarBotones(true, true, true, false);
    }

}
