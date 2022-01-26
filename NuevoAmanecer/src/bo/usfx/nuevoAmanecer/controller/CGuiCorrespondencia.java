/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Afiliado;
import bo.usfx.nuevoAmanecer.model.domain.Casos;
import bo.usfx.nuevoAmanecer.model.domain.Correspondencia;
import bo.usfx.nuevoAmanecer.model.domain.Padrinos;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormularios;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormulariosPermisosUsuarios;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.utils.ComparadorIdFormulario;
import bo.usfx.utils.FormUtilities;
import bo.usfx.utils.GeneraReport;
import bo.usfx.utils.ModeloCorrespondencia;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.GuiParametrosReporteCorrespondencia;
import view.GuiAfiliadoBuscador;
import view.GuiCasosBuscador;
import view.GuiCorrespondencia;
import view.GuiPatrocinadorBuscador;
import view.GuiPrincipal;


public class CGuiCorrespondencia extends KeyAdapter implements ActionListener, ListSelectionListener{
    private GuiCorrespondencia formCorrespondencia;
    private CommonDao dao;
    private ModeloCorrespondencia modeloCorrespondencia;
    private int idFormulario;
    private Usuarios usuario;
    private Connection conexion;

    public CGuiCorrespondencia(GuiCorrespondencia formCorrespondencia) {
        this.formCorrespondencia = formCorrespondencia;
        modeloCorrespondencia = new ModeloCorrespondencia();
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }


    public void cargarDatoscorrespondencia(Correspondencia correspondenciaCargado)
    {
        if(correspondenciaCargado != null)
        {
            formCorrespondencia.getTxtCodigoCorrespondenciaE().setText(String.valueOf(correspondenciaCargado.getCodigo_correspondencia()));
            formCorrespondencia.getTxtNumeroCasoE().setText(String.valueOf(correspondenciaCargado.getNumero_caso()));
            formCorrespondencia.getTxtNumeroPatrocinioE().setText(String.valueOf(correspondenciaCargado.getCodigo_padrino()));
            formCorrespondencia.getTxtDescripcionE().setText(correspondenciaCargado.getDescripcion());
            formCorrespondencia.getcBoxTipoCorrespondenciaE().setSelectedIndex(correspondenciaCargado.esTipoEnvio() ? 1 : 2);
            formCorrespondencia.getDateFechaRegistroE().setDate(correspondenciaCargado.getFecha());

            try {
                Afiliado afiliado = new Afiliado();
                afiliado.setNumero_caso(correspondenciaCargado.getNumero_caso());
                afiliado = (Afiliado) dao.obtenerObjeto(afiliado);

                Padrinos padrino = new Padrinos();
                padrino.setCodigo_padrino(correspondenciaCargado.getCodigo_padrino());
                padrino = (Padrinos) dao.obtenerObjeto(padrino);

                formCorrespondencia.getLblNombreCompletoAfiliado().setText("Afiliado: " + afiliado.getNombreCompleto());
                formCorrespondencia.getLblNombreCompletoPatrocinador().setText("Patrocionado: " + padrino.getNombreCompleto() + ", " + padrino.getNombreCompleto2());
            } catch (Exception e) {
            }
        }
        else
        {
            limpiarCamposEdicion();
            habilitarControlEdicion(false);
        }
    }

    public boolean  validarDatosEdicion()
    {
        if(formCorrespondencia.getTxtNumeroCasoE().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formCorrespondencia, "No Puede Dejar en Blanco el Nro de Caso", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formCorrespondencia.getTxtNumeroCasoE().grabFocus();
            return false;
        }

        if(formCorrespondencia.getTxtNumeroPatrocinioE().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formCorrespondencia, "No Puede Dejar en Blanco el Nro de Patrocinador", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formCorrespondencia.getTxtNumeroPatrocinioE().grabFocus();
            return false;
        }
        if(formCorrespondencia.getcBoxTipoCorrespondenciaE().getSelectedIndex() == 0
                || formCorrespondencia.getcBoxTipoCorrespondenciaE().getSelectedIndex() == -1)
        {
            JOptionPane.showMessageDialog(formCorrespondencia, "No Puede Dejar en Blanco el Tipo de Correspondencia", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formCorrespondencia.getcBoxTipoCorrespondenciaE().grabFocus();
            return false;
        }
        if(formCorrespondencia.getDateFechaRegistroE().getDate() == null)
        {
            JOptionPane.showMessageDialog(formCorrespondencia, "No Puede Dejar en Blanco la fecha de Registro", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formCorrespondencia.getDateFechaRegistroE().grabFocus();
            return false;
        }
        try {
            Date FechaHoraServidor = dao.obtenerFechaHoraServidor();
            if(formCorrespondencia.getDateFechaRegistroE().getDate().after(FechaHoraServidor))
            {
                JOptionPane.showMessageDialog(formCorrespondencia, "La fecha ingresada no puede ser superior a la Fecha Actual", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
                formCorrespondencia.getDateFechaRegistroE().grabFocus();
                return false;
            }

        } catch (SQLException ex) {
//            Logger.getLogger(CGuiCorrespondencia.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(formCorrespondencia.getDateFechaRegistroE().getDate() == null)
        {
            JOptionPane.showMessageDialog(formCorrespondencia, "No Puede Dejar en Blanco la Fecha de Registro", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formCorrespondencia.getDateFechaRegistroE().grabFocus();
            return false;
        }        
        return true;
    }

    public boolean  validarDatosNuevo()
    {
        if(formCorrespondencia.getTxtNumeroCaso().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formCorrespondencia, "No Puede Dejar en Blanco el Nro de Caso", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formCorrespondencia.getTxtNumeroCaso().grabFocus();
            return false;
        }

        if(formCorrespondencia.getTxtNumeroPatrocinador().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formCorrespondencia, "No Puede Dejar en Blanco el Nro de Patrocinador", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formCorrespondencia.getTxtNumeroPatrocinador().grabFocus();
            return false;
        }
        if(formCorrespondencia.getcBoxTipoCorrespondencia().getSelectedIndex() == 0
                || formCorrespondencia.getcBoxTipoCorrespondencia().getSelectedIndex() == -1)
        {
            JOptionPane.showMessageDialog(formCorrespondencia, "No Puede Dejar en Blanco el Tipo de Correspondencia", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formCorrespondencia.getcBoxTipoCorrespondencia().grabFocus();
            return false;
        }

        if(formCorrespondencia.getDateFechaRegistro().getDate() == null)
        {
            JOptionPane.showMessageDialog(formCorrespondencia, "No Puede Dejar en Blanco la fecha de Registro", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formCorrespondencia.getDateFechaRegistro().grabFocus();
            return false;
        }

        try {
            Date FechaHoraServidor = dao.obtenerFechaHoraServidor();
            if(formCorrespondencia.getDateFechaRegistro().getDate().after(FechaHoraServidor))
            {
                JOptionPane.showMessageDialog(formCorrespondencia, "La fecha ingresada no puede ser superior a la Fecha Actual", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
                formCorrespondencia.getDateFechaRegistro().grabFocus();
                return false;
            }

        } catch (SQLException ex) {
//            Logger.getLogger(CGuiCorrespondencia.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(formCorrespondencia.getDateFechaRegistro().getDate() == null)
        {
            JOptionPane.showMessageDialog(formCorrespondencia, "No Puede Dejar en Blanco la Fecha de Registro", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formCorrespondencia.getDateFechaRegistro().grabFocus();
            return false;
        }
        return true;
    }

    public void limpiarCamposEdicion()
    {
        this.formCorrespondencia.getTxtCodigoCorrespondenciaE().setText("");
        this.formCorrespondencia.getTxtNumeroPatrocinioE().setText("");
        this.formCorrespondencia.getTxtNumeroCasoE().setText("");
        this.formCorrespondencia.getTxtDescripcionE().setText("");
        this.formCorrespondencia.getcBoxTipoCorrespondenciaE().setSelectedIndex(0);
        this.formCorrespondencia.getLblNombreCompletoAfiliado().setText("");
        this.formCorrespondencia.getLblNombreCompletoPatrocinador().setText("");
    }

    public void limpiarCamposNuevo()
    {
        this.formCorrespondencia.getTxtCodigoCorrespondencia().setText("");
        this.formCorrespondencia.getTxtNumeroPatrocinador().setText("");
        this.formCorrespondencia.getTxtNumeroCaso().setText("");
        this.formCorrespondencia.getTxtDescripcion().setText("");
        this.formCorrespondencia.getcBoxTipoCorrespondencia().setSelectedIndex(0);
        this.formCorrespondencia.getLblNombreCompletoAfiliado().setText("");
        this.formCorrespondencia.getLblNombreCompletoPatrocinador().setText("");
    }

    public void habilitarControlNuevo(boolean estadoHabilitacion)
    {
        this.formCorrespondencia.getTxtNumeroPatrocinador().setEditable(estadoHabilitacion);
        this.formCorrespondencia.getTxtNumeroCaso().setEditable(estadoHabilitacion);
        this.formCorrespondencia.getTxtDescripcion().setEditable(estadoHabilitacion);
        this.formCorrespondencia.getcBoxTipoCorrespondencia().setEnabled(estadoHabilitacion);
        this.formCorrespondencia.getDateFechaRegistro().setEnabled(estadoHabilitacion);
        this.formCorrespondencia.getBtnBuscarCaso().setEnabled(estadoHabilitacion);
        this.formCorrespondencia.getBtnBuscarPatrocinador().setEnabled(estadoHabilitacion);
    }

     public void habilitarControlEdicion(boolean estadoHabilitacion)
    {
        this.formCorrespondencia.getTxtNumeroPatrocinioE().setEditable(estadoHabilitacion);
        this.formCorrespondencia.getTxtNumeroCasoE().setEditable(estadoHabilitacion);
        this.formCorrespondencia.getTxtDescripcionE().setEditable(estadoHabilitacion);
        this.formCorrespondencia.getcBoxTipoCorrespondenciaE().setEnabled(estadoHabilitacion);
        this.formCorrespondencia.getDateFechaRegistroE().setEnabled(estadoHabilitacion);
        this.formCorrespondencia.getBtnBuscarCasoE().setEnabled(estadoHabilitacion);
        this.formCorrespondencia.getBtnBuscarPatrocinadorE().setEnabled(estadoHabilitacion);
    }

    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();



        if(accion.compareTo("reporteCorrespondencia") == 0)
        {
            GuiParametrosReporteCorrespondencia formParametros = new GuiParametrosReporteCorrespondencia(this.formCorrespondencia, true);
            formParametros.setVisible(true);
            if(formParametros.control.operacionConfirmada)
            {
                GeneraReport gr = new GeneraReport();
                Calendar FechaInicio = formParametros.getPnlRangoFechas().getCalendarioFechaInicio();
                Calendar FechaFin = formParametros.getPnlRangoFechas().getCalendarioFechaFin();

                if(FechaInicio == null || FechaFin == null)
                {
                    JOptionPane.showMessageDialog(this.formCorrespondencia, "Aún no ha ingresado el Rango de Fechas", "Selección de Parametros", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Casos casoSeleccionado = null;
                if(formParametros.getChecSeleccionarAfiliado().isSelected())
                {
                    GuiCasosBuscador formBuscador = new GuiCasosBuscador(this.formCorrespondencia, true);
                    formBuscador.control.setDao(dao);
                    formBuscador.control.onFormShown();
                    formBuscador.setVisible(true);
                    casoSeleccionado = formBuscador.control.getCasoSeleccionado();
                }


                String path = GuiPrincipal.class.getResource("GuiPrincipal.class").getPath().substring(1).replaceAll("view/GuiPrincipal.class", "").trim().replace("%20", " ") + "view/";
                gr.loadReportJasper(path + "reporteCorrespondencia.jasper");
                gr.setParameters("FechaInicio", FechaInicio.getTime());
                gr.setParameters("FechaFin",  FechaFin.getTime());
                gr.setParameters("NumeroProyecto",new Integer(1));
                gr.setParameters("NumeroCaso", casoSeleccionado == null ? null : casoSeleccionado.getNumero_caso());
                gr.fillReport(conexion);
                gr.previewReport();
            }
        }

        if(accion.compareTo(formCorrespondencia.getBtnBuscarCaso().getActionCommand()) == 0)
        {
            GuiAfiliadoBuscador formBuscador = new GuiAfiliadoBuscador(formCorrespondencia, true);
            formBuscador.control.setDao(dao);
            formBuscador.control.onFormShown();
            formBuscador.setPreferredSize(new Dimension(500, 400));
            FormUtilities.centrar2(formBuscador, formCorrespondencia);
            formBuscador.setVisible(true);
            Afiliado afiliado = (Afiliado)formBuscador.control.getPatrocinadorSeleccionado();
            if(afiliado != null)
            {
                formCorrespondencia.getTxtNumeroCaso().setText(String.valueOf(afiliado.getNumero_caso()));
                formCorrespondencia.getLblNombreCompletoAfiliado().setText("Afiliado: " + afiliado.getNombreCompleto());
                Casos caso = new Casos();
                caso.setNumero_caso(afiliado.getNumero_caso());
                try {
                    caso = (Casos) dao.obtenerObjeto(caso);

                    if(caso != null){
                        formCorrespondencia.getTxtNumeroPatrocinador().setText(String.valueOf(caso.getCodigo_padrino()));
                        Padrinos padrino = new Padrinos();
                        padrino.setCodigo_padrino(caso.getCodigo_padrino());
                        padrino = (Padrinos) dao.obtenerObjeto(padrino);

                        formCorrespondencia.getLblNombreCompletoPatrocinador().setText("Patrocinador " + padrino.getNombreCompleto() + ", " + padrino.getNombreCompleto2());
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }else
            {
                formCorrespondencia.getTxtNumeroCaso().setText("");
                formCorrespondencia.getLblNombreCompletoAfiliado().setText("");
                formCorrespondencia.getTxtNumeroPatrocinador().setText("");
                formCorrespondencia.getLblNombreCompletoPatrocinador().setText("");
            }
        }
        if(accion.compareTo("buscarCasoTexto") == 0)
        {
            if (!formCorrespondencia.getTxtNumeroCaso().getText().isEmpty()) {
                try {
                    Afiliado afiliado = new Afiliado();
                    afiliado.setNumero_caso(Integer.parseInt(formCorrespondencia.getTxtNumeroCaso().getText()));
                    afiliado = (Afiliado) dao.obtenerObjeto(afiliado);
                    if (afiliado != null) {
                        formCorrespondencia.getTxtNumeroCaso().setText(String.valueOf(afiliado.getNumero_caso()));
                        formCorrespondencia.getLblNombreCompletoAfiliado().setText("Afiliado: " + afiliado.getNombreCompleto());
                        Casos caso = new Casos();
                        caso.setNumero_caso(afiliado.getNumero_caso());
                        try {
                            caso = (Casos) dao.obtenerObjeto(caso);
                            if (caso != null) {
                                formCorrespondencia.getTxtNumeroPatrocinador().setText(String.valueOf(caso.getCodigo_padrino()));
                                Padrinos padrino = new Padrinos();
                                padrino.setCodigo_padrino(caso.getCodigo_padrino());
                                padrino = (Padrinos) dao.obtenerObjeto(padrino);
                                formCorrespondencia.getLblNombreCompletoPatrocinador().setText("Patrocinador " + padrino.getNombreCompleto() + ", " + padrino.getNombreCompleto2());
                            }
                        } catch (SQLException ex) {
//                            ex.printStackTrace();
                        }
                    } else {
                        formCorrespondencia.getTxtNumeroCaso().setText("");
                        formCorrespondencia.getLblNombreCompletoAfiliado().setText("");
                        formCorrespondencia.getTxtNumeroPatrocinador().setText("");
                        formCorrespondencia.getLblNombreCompletoPatrocinador().setText("");
                    }
                } catch (SQLException ex) {
//                    Logger.getLogger(CGuiCorrespondencia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }


        if(accion.compareTo(formCorrespondencia.getBtnBuscarPatrocinador().getActionCommand()) == 0)
        {
            GuiPatrocinadorBuscador formBuscador = new GuiPatrocinadorBuscador(formCorrespondencia,true);
            formBuscador.control.setDao(dao);
            formBuscador.control.onFormShown();
            formBuscador.setPreferredSize(new Dimension(500, 400));
            FormUtilities.centrar2(formBuscador, formCorrespondencia);
            formBuscador.setVisible(true);
            Padrinos patrocinador = formBuscador.control.getPatrocinadorSeleccionado();
            if(patrocinador != null)
            {
                formCorrespondencia.getTxtNumeroPatrocinador().setText(String.valueOf(patrocinador.getCodigo_padrino()));
                formCorrespondencia.getLblNombreCompletoPatrocinador().setText("Patrocinador " + patrocinador.getNombreCompleto() + ", " + patrocinador.getNombreCompleto2());
            }else{
                formCorrespondencia.getTxtNumeroPatrocinador().setText("");
                formCorrespondencia.getLblNombreCompletoPatrocinador().setText("");
            }

        }


        if(accion.compareTo(formCorrespondencia.getBtnBuscarCasoE().getActionCommand()) == 0)
        {
            GuiAfiliadoBuscador formBuscador = new GuiAfiliadoBuscador(formCorrespondencia, true);
            formBuscador.control.setDao(dao);
            formBuscador.control.onFormShown();
            formBuscador.setPreferredSize(new Dimension(500, 400));
            FormUtilities.centrar2(formBuscador, formCorrespondencia);
            formBuscador.setVisible(true);
            Afiliado afiliado = (Afiliado)formBuscador.control.getPatrocinadorSeleccionado();
            if(afiliado != null)
            {
                formCorrespondencia.getTxtNumeroCasoE().setText(String.valueOf(afiliado.getNumero_caso()));
                Casos caso = new Casos();
                caso.setNumero_caso(afiliado.getNumero_caso());
                try {
                    caso = (Casos) dao.obtenerObjeto(caso);
                    if(caso != null)
                        formCorrespondencia.getTxtNumeroPatrocinioE().setText(String.valueOf(caso.getCodigo_padrino()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }else{
                formCorrespondencia.getTxtNumeroCasoE().setText("");
                formCorrespondencia.getTxtNumeroPatrocinioE().setText("");
            }
        }

        if(accion.compareTo(formCorrespondencia.getBtnBuscarPatrocinadorE().getActionCommand()) == 0)
        {
            GuiPatrocinadorBuscador formBuscador = new GuiPatrocinadorBuscador(formCorrespondencia,true);
            formBuscador.control.setDao(dao);
            formBuscador.control.onFormShown();
            formBuscador.setPreferredSize(new Dimension(500, 400));
            FormUtilities.centrar2(formBuscador, formCorrespondencia);
            formBuscador.setVisible(true);
            Padrinos patrocinador = formBuscador.control.getPatrocinadorSeleccionado();
            if(patrocinador != null)
            {
                formCorrespondencia.getTxtNumeroPatrocinioE().setText(String.valueOf(patrocinador.getCodigo_padrino()));
            }else{
                formCorrespondencia.getTxtNumeroPatrocinioE().setText("");
            }
        }

        if(accion.compareTo(formCorrespondencia.getBtnNuevo().getActionCommand()) == 0)
        {
            formCorrespondencia.getBtnRegistrar().setEnabled(true);
            formCorrespondencia.getBtnNuevo().setEnabled(false);
            limpiarCamposNuevo();
            habilitarControlNuevo(true);
            formCorrespondencia.getTxtNumeroCaso().grabFocus();
            try {
                int Codigocorrespondencia = dao.obtenerUltimoObjetoTabla(new Correspondencia(), "");
                Codigocorrespondencia = Codigocorrespondencia == -1 ? 1 : ++Codigocorrespondencia;
                formCorrespondencia.getTxtCodigoCorrespondencia().setText(String.valueOf(Codigocorrespondencia));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        if(accion.compareTo(formCorrespondencia.getBtnRegistrar().getActionCommand()) == 0)
        {
            if(validarDatosNuevo())
            {
                try {
                    Correspondencia correspondenciaNueva = new Correspondencia(
                            1,
                            formCorrespondencia.getcBoxTipoCorrespondencia().getSelectedIndex() == 1,
                            formCorrespondencia.getTxtDescripcion().getText(),
                            formCorrespondencia.getDateFechaRegistro().getDate(),
                            formCorrespondencia.getcBoxTipoCorrespondencia().getSelectedIndex() == 1 ? "E":"R",
                            Integer.parseInt(formCorrespondencia.getTxtNumeroPatrocinador().getText()),
                            Integer.parseInt(formCorrespondencia.getTxtNumeroCaso().getText()));

                    dao.insertar(correspondenciaNueva);

                    JOptionPane.showMessageDialog(formCorrespondencia, "Registro satisfactorio");
                    formCorrespondencia.getBtnRegistrar().setEnabled(false);
                    formCorrespondencia.getBtnNuevo().setEnabled(true);
                    habilitarControlNuevo(false);
                } catch (Exception ex) {
                    ex.printStackTrace();;
                    JOptionPane.showMessageDialog(formCorrespondencia, "Ocurrió la siguiente Excepción" + ex.getMessage(), "Erro de Inserción", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if(accion.compareTo(formCorrespondencia.getBtnModificar().getActionCommand()) == 0)
        {
            formCorrespondencia.getBtnModificar().setEnabled(false);
            formCorrespondencia.getBtnGuardarE().setEnabled(true);
            habilitarControlEdicion(true);
            formCorrespondencia.getTxtNumeroCasoE().grabFocus();
        }
        if(accion.compareTo(formCorrespondencia.getBtnGuardarE().getActionCommand()) == 0)
        {
            if(validarDatosEdicion())
            {
                try {
                    Correspondencia correspondenciaNueva = new Correspondencia(
                            Integer.parseInt(formCorrespondencia.getTxtCodigoCorrespondenciaE().getText()),
                            formCorrespondencia.getcBoxTipoCorrespondenciaE().getSelectedIndex() == 1,
                            formCorrespondencia.getTxtDescripcionE().getText(),
                            formCorrespondencia.getDateFechaRegistroE().getDate(),
                            formCorrespondencia.getcBoxTipoCorrespondenciaE().getSelectedIndex() == 1 ? "E":"R",
                            Integer.parseInt(formCorrespondencia.getTxtNumeroPatrocinioE().getText()),
                            Integer.parseInt(formCorrespondencia.getTxtNumeroCasoE().getText()));

                    dao.edit(correspondenciaNueva);

                    JOptionPane.showMessageDialog(formCorrespondencia, "Actualización satisfactoria");
                    formCorrespondencia.getBtnModificar().setEnabled(true);
                    formCorrespondencia.getBtnGuardarE().setEnabled(false);
                    habilitarControlEdicion(false);
                } catch (Exception ex) {
                    ex.printStackTrace();;
                    JOptionPane.showMessageDialog(formCorrespondencia, "Ocurrió la siguiente Excepción" + ex.getMessage(), "Error de Actualización", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if(accion.compareTo("buscarE") == 0)
        {
            try {

                int Codigocorrespondencia = -1;
                try {
                    Codigocorrespondencia = Integer.parseInt(formCorrespondencia.getTxtTextoBusquedaE().getText());
                } catch (NumberFormatException ext) {
                    JOptionPane.showMessageDialog(formCorrespondencia, "Aún no ha ingrasado el codigo a buscar", "Buscar Correspondencia", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Correspondencia correspondenciaBusqueda = new Correspondencia();
                correspondenciaBusqueda.setCodigo_correspondencia(Codigocorrespondencia);
//                correspondenciaBusqueda.setNombre(formCorrespondencia.getTxtTextoBusquedaE().getText());
//                correspondenciaBusqueda.setCodigo_padrino(Codigocorrespondencia);

//                List<Correspondencia> listacorrespondenciaes = dao.findObjects(correspondenciaBusqueda);
//                modelocorrespondenciaE.clear();

//                if(listacorrespondenciaes != null && !listacorrespondenciaes.isEmpty())
//                {
//                    for (Correspondencia Correspondencia : listacorrespondenciaes) {
//                        modelocorrespondenciaE.addCorrespondencia(Correspondencia);
//                    }
//                    formCorrespondencia.getBtnModificar().setEnabled(true);
//                    formCorrespondencia.getjTablecorrespondenciaE().setEnabled(true);
//                }
//                else
//                {
//                    formCorrespondencia.getBtnModificar().setEnabled(false);
//                    formCorrespondencia.getjTablecorrespondenciaE().setEnabled(false);
//                }

                correspondenciaBusqueda = (Correspondencia) dao.obtenerObjeto(correspondenciaBusqueda);
                formCorrespondencia.getBtnModificar().setEnabled(correspondenciaBusqueda != null);
                cargarDatoscorrespondencia(correspondenciaBusqueda);
                if(correspondenciaBusqueda == null)
                {
                    JOptionPane.showMessageDialog(formCorrespondencia, "No se encontró ningún registro con la información provista", "Gestion de Correspondencia", JOptionPane.WARNING_MESSAGE);
                }
                

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(accion.compareTo("buscarX") == 0)
        {
            try {

                int Codigocorrespondencia = -1;
                try {
                    Codigocorrespondencia = Integer.parseInt(formCorrespondencia.getTxtTextoBusquedaX().getText());
                } catch (NumberFormatException ext) {
                    JOptionPane.showMessageDialog(formCorrespondencia, "Aún no ha ingrasado el codigo a buscar", "Buscar Correspondencia", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Correspondencia correspondenciaBusqueda = new Correspondencia();
                correspondenciaBusqueda.setCodigo_correspondencia(Codigocorrespondencia);

                List<Correspondencia> listacorrespondenciaes = dao.findObjects(correspondenciaBusqueda);
                modeloCorrespondencia.clear();
//
                if(listacorrespondenciaes != null && !listacorrespondenciaes.isEmpty())
                {
                    for (Correspondencia Correspondencia : listacorrespondenciaes) {
                        modeloCorrespondencia.addCorrespondencia(Correspondencia);
                    }
                    formCorrespondencia.getBtnModificar().setEnabled(true);
                    formCorrespondencia.getjTableCorrespondenciaX().setEnabled(true);
                }
                else
                {
                    formCorrespondencia.getBtnModificar().setEnabled(false);
                    formCorrespondencia.getjTableCorrespondenciaX().setEnabled(false);
                    JOptionPane.showMessageDialog(formCorrespondencia, "No se encontró ningún registro con la información provista", "Gestion de Correspondencia", JOptionPane.WARNING_MESSAGE);

                }

                correspondenciaBusqueda = (Correspondencia) dao.obtenerObjeto(correspondenciaBusqueda);
                formCorrespondencia.getBtnModificar().setEnabled(correspondenciaBusqueda != null);
                formCorrespondencia.getBtnGuardarE().setEnabled(correspondenciaBusqueda == null);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(accion.compareTo(formCorrespondencia.getBtnEliminar().getActionCommand()) == 0)
        {
            if(formCorrespondencia.getjTableCorrespondenciaX().getSelectedRow() < 0)
            {
                JOptionPane.showMessageDialog(formCorrespondencia, "No ha seleccionado ningun registro", "Gestión de Correspondenci", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int indiceEliminacion = formCorrespondencia.getjTableCorrespondenciaX().getSelectedRow();
            if(indiceEliminacion >= 0
                    && JOptionPane.showConfirmDialog(formCorrespondencia, "¿Se encuentra seguro de Eliminar el Registro?",
                    "Eliminación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {

                Correspondencia correspondencia = modeloCorrespondencia.getCorrespondencia(indiceEliminacion);
                dao.delete(correspondencia);
                modeloCorrespondencia.removeCorrespondencia(indiceEliminacion);
                JOptionPane.showMessageDialog(formCorrespondencia, "Registro Eliminado");
            }
            } catch (Exception ez) {
                ez.printStackTrace();
                JOptionPane.showMessageDialog(formCorrespondencia, "Ocurrio la siguiente Excepción " + ez.getMessage(), "Eliminación", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public void onFormShown()
    {
        limpiarCamposEdicion();
        limpiarCamposNuevo();
        habilitarControlEdicion(false);
        habilitarControlNuevo(false);        
        this.formCorrespondencia.getjTableCorrespondenciaX().setModel(modeloCorrespondencia);

        this.formCorrespondencia.getBtnNuevo().setEnabled(true);
        this.formCorrespondencia.getBtnRegistrar().setEnabled(false);
        this.formCorrespondencia.getBtnModificar().setEnabled(false);
        this.formCorrespondencia.getBtnGuardarE().setEnabled(false);
        formCorrespondencia.getjTableCorrespondenciaX().getSelectionModel().addListSelectionListener(this);

        configuracionFormulario();
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
                return; // if you don't want to handle intermediate selections
        ListSelectionModel rowSM = (ListSelectionModel) e.getSource();
        int indice = rowSM.getMinSelectionIndex();
        if(indice >= 0)
        {
            cargarDatoscorrespondencia(modeloCorrespondencia.getCorrespondencia(indice));
            formCorrespondencia.getBtnModificar().setEnabled(true);
        }
        else
            formCorrespondencia.getBtnModificar().setEnabled(false);
    }

    public void configuracionFormulario() {
        if (usuario != null) {
            int indice = -1;
            SistemaFormularios formulario = new SistemaFormularios();
            formulario.setNombre_formulario(FormUtilities.getClassName(formCorrespondencia.getClass()));
            SistemaFormulariosPermisosUsuarios permisosCorrespondencia = new SistemaFormulariosPermisosUsuarios();
            permisosCorrespondencia.setSistemaFormulario(formulario);

            indice = Collections.binarySearch(usuario.getListadoInterfacesPermisos(), permisosCorrespondencia, new ComparadorIdFormulario());
            if (indice >= 0) {
                permisosCorrespondencia = usuario.getListadoInterfacesPermisos().get(indice);

//
                if(!permisosCorrespondencia.isPermitir_insertar())
                    formCorrespondencia.getjTabbedPane1().remove(formCorrespondencia.getPnlTabPageInsertar());

                if(!permisosCorrespondencia.isPermitir_editar())
                    formCorrespondencia.getjTabbedPane1().remove(formCorrespondencia.getPnlTabPageEditar());

                if(!permisosCorrespondencia.isPermitir_anular() && !permisosCorrespondencia.isPermitir_navegar())
                    formCorrespondencia.getjTabbedPane1().remove(formCorrespondencia.getPnlTabPageEliminar());
                else
                {
                    formCorrespondencia.getBtnEliminar().setVisible(permisosCorrespondencia.isPemitir_eliminar());
                    formCorrespondencia.getBtnBuscarX().setVisible(permisosCorrespondencia.isPermitir_navegar());
                }
                //formCorrespondencia.getBtnVerReporteCorrespondencia().setVisible(permisosCorrespondencia.isPermitir_reportes());
            } else {
                System.out.println("no tiene permisos, nombre de la Clase " + formulario.getNombre_formulario());
            }
        }
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        JTextField componente = (JTextField) e.getSource();
        char c = e.getKeyChar();
        if (componente.equals(formCorrespondencia.getTxtNumeroCaso()) ||
              componente.equals(formCorrespondencia.getTxtNumeroCasoE())
              || componente.equals(formCorrespondencia.getTxtNumeroPatrocinador())
              || componente.equals(formCorrespondencia.getTxtNumeroPatrocinioE())
              || componente.equals(formCorrespondencia.getTxtTextoBusquedaE())
              || componente.equals(formCorrespondencia.getTxtTextoBusquedaX())
              ) {

                if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                                || (c == KeyEvent.VK_DELETE) ))) {
                        formCorrespondencia.getToolkit().beep();
                        e.consume();
                }
        }
    }

}
