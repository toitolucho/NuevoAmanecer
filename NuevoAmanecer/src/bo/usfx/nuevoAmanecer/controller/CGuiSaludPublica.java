/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.SaludPublica;
import bo.usfx.utils.ModeloSaludPublica;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import view.GuiSaludPublica;

public class CGuiSaludPublica extends KeyAdapter implements ActionListener{

    private GuiSaludPublica formSaludPublica;
    private CommonDao dao;
    private ModeloSaludPublica modeloSaludPublica;

    public CGuiSaludPublica(GuiSaludPublica formSaludPublica) {
        this.formSaludPublica = formSaludPublica;
    }

    public CommonDao getDao() {
        return dao;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    public void limpiarCamposNuevo()
    {
        formSaludPublica.getCheckEDA_NO().setSelected(false);
        formSaludPublica.getCheckEDA_SI().setSelected(false);

        formSaludPublica.getCheckER_SI().setSelected(false);
        formSaludPublica.getCheckER_NO().setSelected(false);

        formSaludPublica.getCheckAG_NO().setSelected(false);
        formSaludPublica.getCheckAG_SI().setSelected(false);

        formSaludPublica.getCheckEDA_NO().setSelected(false);
        formSaludPublica.getCheckEDA_SI().setSelected(false);

        formSaludPublica.getCheckDE_NO().setSelected(false);
        formSaludPublica.getCheckDE_SI().setSelected(false);

        formSaludPublica.getCheckCUARTOS_SI().setSelected(false);
        formSaludPublica.getCheckCUARTOS_NO().setSelected(false);

        formSaludPublica.getcBoxViviendaPropiedad().setSelectedIndex(0);
        formSaludPublica.getcBoxTipoVivienda().setSelectedIndex(0);
        formSaludPublica.getcBoxMaterialVivienda().setSelectedIndex(0);

        formSaludPublica.getTxtNroCuartos().setText("");
        formSaludPublica.getTxtNroCuartosDormir().setText("");

      
        limpiarComponentesPanel(formSaludPublica.getPnlDispocionesExcretas());
    }
    public void habilitarCamposNuevo(boolean estadoHabilitacion)
    {
        formSaludPublica.getCheckEDA_NO().setEnabled(estadoHabilitacion);
        formSaludPublica.getCheckEDA_SI().setEnabled(estadoHabilitacion);

        formSaludPublica.getCheckER_SI().setEnabled(estadoHabilitacion);
        formSaludPublica.getCheckER_NO().setEnabled(estadoHabilitacion);

        formSaludPublica.getCheckAG_NO().setEnabled(estadoHabilitacion);
        formSaludPublica.getCheckAG_SI().setEnabled(estadoHabilitacion);

        formSaludPublica.getCheckEDA_NO().setEnabled(estadoHabilitacion);
        formSaludPublica.getCheckEDA_SI().setEnabled(estadoHabilitacion);

        formSaludPublica.getCheckDE_NO().setEnabled(estadoHabilitacion);
        formSaludPublica.getCheckDE_SI().setEnabled(estadoHabilitacion);

        formSaludPublica.getCheckCUARTOS_SI().setEnabled(estadoHabilitacion);
        formSaludPublica.getCheckCUARTOS_NO().setEnabled(estadoHabilitacion);

        formSaludPublica.getcBoxViviendaPropiedad().setEnabled(estadoHabilitacion);
        formSaludPublica.getcBoxTipoVivienda().setEnabled(estadoHabilitacion);
        formSaludPublica.getcBoxMaterialVivienda().setEnabled(estadoHabilitacion);

        formSaludPublica.getTxtNroCuartos().setEnabled(estadoHabilitacion);
        formSaludPublica.getTxtNroCuartosDormir().setEnabled(estadoHabilitacion);
        habilitarComponentesPanel(formSaludPublica.getPnlDispocionesExcretas(), estadoHabilitacion);
    }
    public boolean validarCamposNuevo()
    {
        if(formSaludPublica.getDateFechaRegistro().getDate() == null)
        {
            JOptionPane.showMessageDialog(formSaludPublica, "Aun  no ha ingresado la Fecha", "Salud publica", JOptionPane.ERROR_MESSAGE);
            formSaludPublica.getDateFechaRegistro().grabFocus();
            return false;

        }


        try {



            Date FechaHoraServidor = dao.obtenerFechaHoraServidor();
            if(formSaludPublica.getDateFechaRegistro().getDate().after(FechaHoraServidor))
            {
                 JOptionPane.showMessageDialog(formSaludPublica, "La fecha ingresada supera  a la fecha Actual");
                formSaludPublica.getDateFechaRegistro().grabFocus();
                return false;
            }

//            if(formSaludPublica.getDateFechaRegistro().getDate() == null
//                    && JOptionPane.showConfirmDialog(formSaludPublica, "¿Se encuentra seguro de dejar la fecha en blanco?", "Salud Publica", JOptionPane.YES_NO_OPTION)
//                    == JOptionPane.NO_OPTION)
//            {
//                JOptionPane.showMessageDialog(formSaludPublica, "No ha ingresado la Fecha");
//                formSaludPublica.getDateFechaRegistro().grabFocus();
//                return false;
//            }
        } catch (SQLException ex) {
            Logger.getLogger(CGuiSaludPublica.class.getName()).log(Level.SEVERE, null, ex);
        }



        if(!formSaludPublica.getCheckEDA_NO().isSelected()
                && !formSaludPublica.getCheckEDA_SI().isSelected())
        {
            JOptionPane.showMessageDialog(formSaludPublica, "No ha seleccionado ninguna opción en cuanto a Enfermedades Diarreicas");
            formSaludPublica.getCheckEDA_SI().grabFocus();
            return false;
        }

        if(!formSaludPublica.getCheckER_SI().isSelected()
                && !formSaludPublica.getCheckER_NO().isSelected())
        {
            JOptionPane.showMessageDialog(formSaludPublica, "No ha seleccionado ninguna opción en cuanto a Enfermedades Respiratorias");
            formSaludPublica.getCheckER_SI().grabFocus();
            return false;
        }

        if(!formSaludPublica.getCheckAG_NO().isSelected()
                && !formSaludPublica.getCheckAG_SI().isSelected())
        {
            JOptionPane.showMessageDialog(formSaludPublica, "No ha seleccionado ninguna opción en cuanto a Acceso a Agua");
            formSaludPublica.getCheckAG_SI().grabFocus();
            return false;
        }

        if(!formSaludPublica.getCheckDE_NO().isSelected()
                && !formSaludPublica.getCheckDE_SI().isSelected())
        {
            JOptionPane.showMessageDialog(formSaludPublica, "No ha seleccionado ninguna opción en cuanto a DISPOSICIÓN DE EXCRETAS");
            formSaludPublica.getCheckDE_SI().grabFocus();
            return false;
        }

        if(!formSaludPublica.getCheckCUARTOS_SI().isSelected()
                && !formSaludPublica.getCheckCUARTOS_NO().isSelected())
        {
            JOptionPane.showMessageDialog(formSaludPublica, "No ha seleccionado ninguna opción en cuanto a cuartos de cocina");
            formSaludPublica.getCheckCUARTOS_SI().grabFocus();
            return false;
        }
        
        if(formSaludPublica.getcBoxMaterialVivienda().getSelectedIndex() == -1
                || formSaludPublica.getcBoxMaterialVivienda().getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(formSaludPublica, "Aún no ha seleccionado el Material de la vivienda");
            formSaludPublica.getcBoxMaterialVivienda().grabFocus();
            return false;
        }
        if(formSaludPublica.getcBoxTipoVivienda().getSelectedIndex() == -1
                || formSaludPublica.getcBoxTipoVivienda().getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(formSaludPublica, "Aún no ha seleccionado el Tipo vivienda");
            formSaludPublica.getcBoxTipoVivienda().grabFocus();
            return false;
        }
        if(formSaludPublica.getcBoxViviendaPropiedad().getSelectedIndex() == -1
                || formSaludPublica.getcBoxViviendaPropiedad().getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(formSaludPublica, "Aún no ha seleccionado el la caracteristica de la Vivienda");
            formSaludPublica.getcBoxViviendaPropiedad().grabFocus();
            return false;
        }

        if(formSaludPublica.getTxtNumeroFamiliaNuevo().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formSaludPublica, "No ha ingresado el Numero de Familia");
            formSaludPublica.getTxtNumeroFamiliaNuevo().grabFocus();
            return false;
        }
        if(formSaludPublica.getTxtNroCuartos().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formSaludPublica, "No ha ingresado el Numero de Cuartos");
            formSaludPublica.getTxtNroCuartos().grabFocus();
            return false;
        }
        if(formSaludPublica.getTxtNroCuartosDormir().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formSaludPublica, "No ha ingresado el Numero de Dormitorios");
            formSaludPublica.getTxtNroCuartosDormir().grabFocus();
            return false;
        }

        if(Integer.parseInt(formSaludPublica.getTxtNroCuartosDormir().getText()) >  Integer.parseInt(formSaludPublica.getTxtNroCuartos().getText())){
            JOptionPane.showMessageDialog(formSaludPublica, "El Nro de Cuartos para dormir supera al Nro de Cuartos disponibles", "Salud Pública", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(Integer.parseInt(formSaludPublica.getTxtNroCuartosDormir().getText()) + (formSaludPublica.getCheckCUARTOS_SI().isSelected() ? 1 : 0) >  Integer.parseInt(formSaludPublica.getTxtNroCuartos().getText())

                ){
            JOptionPane.showMessageDialog(formSaludPublica, "El Nro de Cuartos para dormir supera al Nro de Cuartos disponibles", "Salud Pública", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public void limpiarCamposEdcion()
    {
        formSaludPublica.getCheckEDA_NOE().setSelected(false);
        formSaludPublica.getCheckEDA_SIE().setSelected(false);

        formSaludPublica.getCheckER_SIE().setSelected(false);
        formSaludPublica.getCheckER_NOE().setSelected(false);

        formSaludPublica.getCheckAG_NOE().setSelected(false);
        formSaludPublica.getCheckAG_SIE().setSelected(false);

        formSaludPublica.getCheckEDA_NOE().setSelected(false);
        formSaludPublica.getCheckEDA_SIE().setSelected(false);

        formSaludPublica.getCheckDE_NOE().setSelected(false);
        formSaludPublica.getCheckDE_SIE().setSelected(false);

        formSaludPublica.getCheckCUARTOS_SIE().setSelected(false);
        formSaludPublica.getCheckCUARTOS_NOE().setSelected(false);

        formSaludPublica.getcBoxViviendaPropiedadE().setSelectedIndex(0);
        formSaludPublica.getcBoxTipoViviendaE().setSelectedIndex(0);
        formSaludPublica.getcBoxMaterialViviendaE().setSelectedIndex(0);

        formSaludPublica.getTxtNroCuartosE().setText("");
        formSaludPublica.getTxtNroCuartosDormirE().setText("");

    }
    public void habilitarCamposEdicion(boolean estadoHabilitacion)
    {
        formSaludPublica.getCheckEDA_NOE().setEnabled(estadoHabilitacion);
        formSaludPublica.getCheckEDA_SIE().setEnabled(estadoHabilitacion);

        formSaludPublica.getCheckER_SIE().setEnabled(estadoHabilitacion);
        formSaludPublica.getCheckER_NOE().setEnabled(estadoHabilitacion);

        formSaludPublica.getCheckAG_NOE().setEnabled(estadoHabilitacion);
        formSaludPublica.getCheckAG_SIE().setEnabled(estadoHabilitacion);

        formSaludPublica.getCheckEDA_NOE().setEnabled(estadoHabilitacion);
        formSaludPublica.getCheckEDA_SIE().setEnabled(estadoHabilitacion);

        formSaludPublica.getCheckDE_NOE().setEnabled(estadoHabilitacion);
        formSaludPublica.getCheckDE_SIE().setEnabled(estadoHabilitacion);

        formSaludPublica.getCheckCUARTOS_SIE().setEnabled(estadoHabilitacion);
        formSaludPublica.getCheckCUARTOS_NOE().setEnabled(estadoHabilitacion);

        formSaludPublica.getcBoxViviendaPropiedadE().setEnabled(estadoHabilitacion);
        formSaludPublica.getcBoxTipoViviendaE().setEnabled(estadoHabilitacion);
        formSaludPublica.getcBoxMaterialViviendaE().setEnabled(estadoHabilitacion);

        formSaludPublica.getTxtNroCuartosDormirE().setEnabled(estadoHabilitacion);
        formSaludPublica.getTxtNroCuartosE().setEnabled(estadoHabilitacion);

    }
    public boolean validarCamposEdicion()
    {
        if(formSaludPublica.getDateFechaRegistroE().getDate() == null)
        {
            JOptionPane.showMessageDialog(formSaludPublica, "Aun  no ha ingresado la Fecha", "Salud publica", JOptionPane.ERROR_MESSAGE);
            formSaludPublica.getDateFechaRegistroE().grabFocus();
            return false;

        }


//        try {
//
//
//
//            Date FechaHoraServidor = dao.obtenerFechaHoraServidor();
//            if(formSaludPublica.getDateFechaRegistroE().getDate().after(FechaHoraServidor))
//            {
//                 JOptionPane.showMessageDialog(formSaludPublica, "La fecha ingresada supera  a la fecha Actual");
//                formSaludPublica.getDateFechaRegistroE().grabFocus();
//                return false;
//            }
//
////            if(formSaludPublica.getDateFechaRegistro().getDate() == null
////                    && JOptionPane.showConfirmDialog(formSaludPublica, "¿Se encuentra seguro de dejar la fecha en blanco?", "Salud Publica", JOptionPane.YES_NO_OPTION)
////                    == JOptionPane.NO_OPTION)
////            {
////                JOptionPane.showMessageDialog(formSaludPublica, "No ha ingresado la Fecha");
////                formSaludPublica.getDateFechaRegistro().grabFocus();
////                return false;
////            }
//        } catch (SQLException ex) {
           


        if(!formSaludPublica.getCheckEDA_NOE().isSelected()
                && !formSaludPublica.getCheckEDA_SIE().isSelected())
        {
            JOptionPane.showMessageDialog(formSaludPublica, "No ha seleccionado ninguna opción en cuanto a Enfermedades Diarreicas");
            formSaludPublica.getCheckEDA_SIE().grabFocus();
            return false;
        }

        if(!formSaludPublica.getCheckER_SIE().isSelected()
                && !formSaludPublica.getCheckER_NOE().isSelected())
        {
            JOptionPane.showMessageDialog(formSaludPublica, "No ha seleccionado ninguna opción en cuanto a Enfermedades Respiratorias");
            formSaludPublica.getCheckER_SIE().grabFocus();
            return false;
        }

        if(!formSaludPublica.getCheckAG_NOE().isSelected()
                && !formSaludPublica.getCheckAG_SIE().isSelected())
        {
            JOptionPane.showMessageDialog(formSaludPublica, "No ha seleccionado ninguna opción en cuanto a Acceso a Agua");
            formSaludPublica.getCheckAG_SIE().grabFocus();
            return false;
        }

        if(!formSaludPublica.getCheckDE_NOE().isSelected()
                && !formSaludPublica.getCheckDE_SIE().isSelected())
        {
            JOptionPane.showMessageDialog(formSaludPublica, "No ha seleccionado ninguna opción en cuanto a DISPOSICIÓN DE EXCRETAS");
            formSaludPublica.getCheckDE_SIE().grabFocus();
            return false;
        }

        if(!formSaludPublica.getCheckCUARTOS_SIE().isSelected()
                && !formSaludPublica.getCheckCUARTOS_NOE().isSelected())
        {
            JOptionPane.showMessageDialog(formSaludPublica, "No ha seleccionado ninguna opción en cuanto a cuartos o Dormitorios");
            formSaludPublica.getCheckCUARTOS_SIE().grabFocus();
            return false;
        }

        if(formSaludPublica.getcBoxMaterialViviendaE().getSelectedIndex() == -1
                || formSaludPublica.getcBoxMaterialViviendaE().getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(formSaludPublica, "Aún no ha seleccionado el Material de la vivienda");
            formSaludPublica.getcBoxMaterialViviendaE().grabFocus();
            return false;
        }
        if(formSaludPublica.getcBoxTipoViviendaE().getSelectedIndex() == -1
                || formSaludPublica.getcBoxTipoViviendaE().getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(formSaludPublica, "Aún no ha seleccionado el Tipo vivienda");
            formSaludPublica.getcBoxTipoViviendaE().grabFocus();
            return false;
        }
        if(formSaludPublica.getcBoxViviendaPropiedadE().getSelectedIndex() == -1
                || formSaludPublica.getcBoxViviendaPropiedadE().getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(formSaludPublica, "Aún no ha seleccionado el la caracteristica de la Vivienda");
            formSaludPublica.getcBoxViviendaPropiedadE().grabFocus();
            return false;
        }

        if(formSaludPublica.getTxtNroCuartosE().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formSaludPublica, "No ha ingresado el Numero de Cuartos");
            formSaludPublica.getTxtNroCuartosE().grabFocus();
            return false;
        }
        if(formSaludPublica.getTxtNroCuartosDormirE().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formSaludPublica, "No ha ingresado el Numero de Dormitorios");
            formSaludPublica.getTxtNroCuartosDormirE().grabFocus();
            return false;
        }

        if(Integer.parseInt(formSaludPublica.getTxtNroCuartosDormirE().getText()) + (formSaludPublica.getCheckCUARTOS_SIE().isSelected() ? 1 : 0) >  Integer.parseInt(formSaludPublica.getTxtNroCuartosE().getText())

                ){
            JOptionPane.showMessageDialog(formSaludPublica, "El Nro de Cuartos para dormir supera al Nro de Cuartos disponibles", "Salud Pública", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();

        if(accion.compareTo(formSaludPublica.getBtnNuevo().getActionCommand()) == 0)
        {
            formSaludPublica.getBtnNuevo().setEnabled(true);
            formSaludPublica.getBtnRegistrar().setEnabled(true);

            limpiarCamposNuevo();
            habilitarCamposNuevo(true);
        }
        if(accion.compareTo(formSaludPublica.getBtnRegistrar().getActionCommand()) == 0)
        {
            if(!validarCamposNuevo())
            {
                JOptionPane.showMessageDialog(formSaludPublica, "Revise sus datos", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                boolean eda, ira, ag, de, cocina;
                eda = formSaludPublica.getCheckEDA_SI().isSelected();
                ira = formSaludPublica.getCheckER_SI().isSelected();
                ag = formSaludPublica.getCheckAG_SI().isSelected();
                cocina = formSaludPublica.getCheckCUARTOS_SI().isSelected();
                de = formSaludPublica.getCheckDE_SI().isSelected();
                int numeroFamilia =  Integer.parseInt(formSaludPublica.getTxtNumeroFamiliaNuevo().getText());


                SaludPublica saludPublica = new SaludPublica(numeroFamilia, null, eda,
                        ira, ag,
                        de, formSaludPublica.getcBoxViviendaPropiedad().getSelectedItem().toString(),
                        formSaludPublica.getcBoxTipoVivienda().getSelectedItem().toString(),
                        formSaludPublica.getcBoxMaterialVivienda().getSelectedItem().toString(),
                        Integer.parseInt(formSaludPublica.getTxtNroCuartos().getText()),
                        Integer.valueOf(formSaludPublica.getTxtNroCuartosDormir().getText())
                        , cocina);
                dao.insertar(saludPublica);
                JOptionPane.showMessageDialog(formSaludPublica, "Registro Satisfactorio", "Registro", JOptionPane.INFORMATION_MESSAGE);

                
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(formSaludPublica, "Error al momento de Registrar"
                        +". Ocurrio la siguiente Excepción " +ex.getMessage(), "Registro", JOptionPane.INFORMATION_MESSAGE);
            }

            habilitarCamposNuevo(false);
            formSaludPublica.getBtnNuevo().setEnabled(true);
            formSaludPublica.getBtnRegistrar().setEnabled(false);
        }

        if(accion.compareTo("buscarE") == 0)
        {
            try {
                if (formSaludPublica.getTxtNumeroFamiliaE().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(formSaludPublica, "No ha ingresado el Numero de Familia", "Busqueda de Datos", JOptionPane.ERROR_MESSAGE);
                    formSaludPublica.getTxtNumeroFamiliaE().grabFocus();
                    return;
                }
                SaludPublica saludPublic = new SaludPublica();
                saludPublic.setNumero_familia(Integer.parseInt(formSaludPublica.getTxtNumeroFamiliaE().getText()));
                saludPublic = (SaludPublica) dao.obtenerObjeto(saludPublic);
                if (saludPublic != null) {
                    limpiarCamposEdcion();
                    
                    cargarDatosSaludPublica(saludPublic);
                    formSaludPublica.getBtnModificar().setEnabled(true);
                }
                else
                {
                    limpiarCamposEdcion();
                    habilitarCamposEdicion(false);
                    formSaludPublica.getBtnModificar().setEnabled(false);
                    formSaludPublica.getBtnGuardar().setEnabled(false);
                    JOptionPane.showMessageDialog(formSaludPublica, "No se produjo ningún resultado con los parametros provistos");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CGuiSaludPublica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(accion.compareTo(formSaludPublica.getBtnModificar().getActionCommand()) == 0)
        {
            habilitarCamposEdicion(true);
            formSaludPublica.getBtnModificar().setEnabled(false);
            formSaludPublica.getBtnGuardar().setEnabled(true);
        }

        if(accion.compareTo("validarCocina") == 0)
        {
            int nroCuartos = Integer.parseInt(formSaludPublica.getTxtNroCuartos().getText());
            int nroCuartosDormir = Integer.parseInt(formSaludPublica.getTxtNroCuartosDormir().getText());

            if(nroCuartosDormir >= nroCuartos)
            {
                this.formSaludPublica.getCheckCUARTOS_NO().setSelected(true);
                this.formSaludPublica.getCheckCUARTOS_SI().setSelected(false);
            }
        }

        if(accion.compareTo("validarCocinaE") == 0)
        {
            int nroCuartos = Integer.parseInt(formSaludPublica.getTxtNroCuartosE().getText());
            int nroCuartosDormir = Integer.parseInt(formSaludPublica.getTxtNroCuartosDormirE().getText());

            if(nroCuartosDormir >= nroCuartos)
            {
                this.formSaludPublica.getCheckCUARTOS_NO().setSelected(true);
                this.formSaludPublica.getCheckCUARTOS_SI().setSelected(false);
            }
        }

        System.out.println("Accion Seleccionada " + accion);
        if(accion.compareTo(formSaludPublica.getBtnGuardar().getActionCommand()) == 0)
        {

            if(!validarCamposEdicion())
            {
                JOptionPane.showMessageDialog(formSaludPublica, "Algunos datos son erroneos, porfavor corrijalos", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                boolean eda, ira, ag, de, cocina;
                eda = formSaludPublica.getCheckEDA_SIE().isSelected();
                ira = formSaludPublica.getCheckER_SIE().isSelected();
                ag = formSaludPublica.getCheckAG_SIE().isSelected();
                cocina = formSaludPublica.getCheckCUARTOS_SIE().isSelected();
                de = formSaludPublica.getCheckDE_SIE().isSelected();
                int numeroFamilia =  Integer.parseInt(formSaludPublica.getTxtNumeroFamiliaE().getText());


                SaludPublica saludPublica = new SaludPublica(numeroFamilia, null, eda,
                        ira, ag,
                        de, formSaludPublica.getcBoxViviendaPropiedadE().getSelectedItem().toString(),
                        formSaludPublica.getcBoxTipoViviendaE().getSelectedItem().toString(),
                        formSaludPublica.getcBoxMaterialViviendaE().getSelectedItem().toString(),
                        Integer.parseInt(formSaludPublica.getTxtNroCuartosE().getText()),
                        Integer.valueOf(formSaludPublica.getTxtNroCuartosDormirE().getText())
                        , cocina);
                dao.edit(saludPublica);
                JOptionPane.showMessageDialog(formSaludPublica, "Actualización Correcta", "Registro", JOptionPane.INFORMATION_MESSAGE);
                formSaludPublica.getBtnModificar().setEnabled(false);
                formSaludPublica.getBtnGuardar().setEnabled(false);
                habilitarCamposEdicion(false);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(formSaludPublica, "Error al momento de Actualización"
                        +". Ocurrio la siguiente Excepción " +ex.getMessage(), "Registro", JOptionPane.INFORMATION_MESSAGE);
                formSaludPublica.getBtnModificar().setEnabled(false);
                formSaludPublica.getBtnGuardar().setEnabled(false);
            }


        }

      

      
        if(e.getSource() instanceof JCheckBox)
        {
            System.out.println("check box");
        }
    }

    public void SeleccionarPaginaEdicion(int numeroFamiliar)
    {
        this.formSaludPublica.getjTabbedPane1().setSelectedComponent(formSaludPublica.getPnlPageModificar());
        this.formSaludPublica.getTxtNumeroFamiliaE().setText(String.valueOf(numeroFamiliar));
        this.formSaludPublica.getBtnBuscarE().setVisible(false);
        this.formSaludPublica.getjTabbedPane1().remove(formSaludPublica.getPnlPageInsertar());
        this.formSaludPublica.getBtnGuardar().setEnabled(true);
        habilitarCamposEdicion(true);
        this.formSaludPublica.getBtnModificar().setVisible(false);
    }

    public void SeleccionarPaginaInsertar(int numeroFamiliar)
    {
        this.formSaludPublica.getjTabbedPane1().setSelectedComponent(formSaludPublica.getPnlPageInsertar());
        this.formSaludPublica.getTxtNumeroFamiliaNuevo().setText(String.valueOf(numeroFamiliar));
//        this.formSaludPublica.getBtn.setVisible(false);
        this.formSaludPublica.getjTabbedPane1().remove(formSaludPublica.getPnlPageModificar());
        
    }

    public void SeleccionarPaginaInsertar()
    {
        this.formSaludPublica.getjTabbedPane1().setSelectedComponent(formSaludPublica.getPnlPageInsertar());
    }

    public void cargarDatosSaludPublica(SaludPublica salud)
    {
        if (salud != null) {
            if (salud.isEda()) {
                formSaludPublica.getCheckEDA_SIE().setSelected(true);
                
            } else {
                formSaludPublica.getCheckEDA_NOE().setSelected(true);

            }
            if (salud.isIra()) {
                formSaludPublica.getCheckER_SIE().setSelected(true);
                
            } else {
                formSaludPublica.getCheckER_NOE().setSelected(true);

            }
            if (salud.isAgua()) {
                formSaludPublica.getCheckAG_SIE().setSelected(true);
                
            } else {
                formSaludPublica.getCheckAG_NOE().setSelected(true);

            }
            if (salud.isExcretas()) {
                formSaludPublica.getCheckDE_SIE().setSelected(true);
                
            } else {
                formSaludPublica.getCheckDE_NOE().setSelected(true);


            }
            if (salud.isCocina()) {
                formSaludPublica.getCheckCUARTOS_SIE().setSelected(true);
                
            } else {
                formSaludPublica.getCheckCUARTOS_NOE().setSelected(true);

                
            }
            formSaludPublica.getcBoxViviendaPropiedadE().setSelectedItem(salud.getVivienda());
            formSaludPublica.getcBoxTipoViviendaE().setSelectedItem(salud.getTipo_vivienda());
            formSaludPublica.getcBoxMaterialViviendaE().setSelectedItem(salud.getMaterial_vivienda());

            formSaludPublica.getTxtNroCuartosDormirE().setText(String.valueOf(salud.getDormitorios()));
            formSaludPublica.getTxtNroCuartosE().setText(String.valueOf(salud.getNumero_habitaciones()));
            formSaludPublica.getDateFechaRegistroE().setDate(salud.getFecha_registro());
        }else
        {
            habilitarCamposEdicion(false);
            limpiarCamposEdcion();
        }
    }

    public void onFormShown()
    {
        ButtonGroup rgroupPrueba = new ButtonGroup();
        rgroupPrueba.add(formSaludPublica.getCheckEDA_NO());
        rgroupPrueba.add(formSaludPublica.getCheckEDA_SI());

        ButtonGroup rgroupPrueba1 = new ButtonGroup();
        rgroupPrueba1.add(formSaludPublica.getCheckER_SI());
        rgroupPrueba1.add(formSaludPublica.getCheckER_NO());

        ButtonGroup rgroupPrueba2 = new ButtonGroup();
        rgroupPrueba2.add(formSaludPublica.getCheckAG_NO());
        rgroupPrueba2.add(formSaludPublica.getCheckAG_SI());

        ButtonGroup rgroupPrueba3 = new ButtonGroup();
        rgroupPrueba3.add(formSaludPublica.getCheckEDA_NO());
        rgroupPrueba3.add(formSaludPublica.getCheckEDA_SI());

        ButtonGroup rgroupPrueba4 = new ButtonGroup();
        rgroupPrueba4.add(formSaludPublica.getCheckDE_NO());
        rgroupPrueba4.add(formSaludPublica.getCheckDE_SI());

        ButtonGroup rgroupPrueba5 = new ButtonGroup();
        rgroupPrueba5.add(formSaludPublica.getCheckCUARTOS_SI());
        rgroupPrueba5.add(formSaludPublica.getCheckCUARTOS_NO());

        ButtonGroup rgroupPrueba6 = new ButtonGroup();
        rgroupPrueba6.add(formSaludPublica.getCheckEDA_NOE());
        rgroupPrueba6.add(formSaludPublica.getCheckEDA_SIE());

        ButtonGroup rgroupPrueba7 = new ButtonGroup();
        rgroupPrueba7.add(formSaludPublica.getCheckER_SIE());
        rgroupPrueba7.add(formSaludPublica.getCheckER_NOE());

        ButtonGroup rgroupPrueba8 = new ButtonGroup();
        rgroupPrueba8.add(formSaludPublica.getCheckAG_NOE());
        rgroupPrueba8.add(formSaludPublica.getCheckAG_SIE());

        ButtonGroup rgroupPrueba9 = new ButtonGroup();
        rgroupPrueba9.add(formSaludPublica.getCheckDE_NOE());
        rgroupPrueba9.add(formSaludPublica.getCheckDE_SIE());

        ButtonGroup rgroupPrueba10 = new ButtonGroup();
        rgroupPrueba10.add(formSaludPublica.getCheckCUARTOS_SIE());
        rgroupPrueba10.add(formSaludPublica.getCheckCUARTOS_NOE());

        limpiarCamposEdcion();
        limpiarCamposNuevo();
        habilitarCamposEdicion(false);
        habilitarCamposNuevo(false);

        formSaludPublica.getBtnGuardar().setEnabled(false);
        formSaludPublica.getBtnModificar().setEnabled(false);
        formSaludPublica.getBtnNuevo().setEnabled(true);
        formSaludPublica.getBtnRegistrar().setEnabled(false);

        modeloSaludPublica = new ModeloSaludPublica();
        formSaludPublica.getTxtNumeroFamiliaE().setEnabled(false);
        formSaludPublica.getTxtNumeroFamiliaNuevo().setEnabled(false);
    }

    public void habilitarComponentesPanel(JPanel contenedor, boolean estadoHabilitacion)
    {
        for(Component check : contenedor.getComponents())
        {
            if(check instanceof JCheckBox)
            {
                check.setEnabled(estadoHabilitacion);
            }
        }
    }

    public void limpiarComponentesPanel(JPanel contenedor)
    {
        for(Component check : contenedor.getComponents())
        {
            if(check instanceof JCheckBox)
            {
                ((JCheckBox)check).setSelected(false);
            }
        }
    }

    @Override
     public void keyTyped(KeyEvent e) {
         // TODO Auto-generated method stub

         char c = e.getKeyChar();
         if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                 || (c == KeyEvent.VK_DELETE)))) {
             formSaludPublica.getToolkit().beep();
             e.consume();
         }
    }
}
