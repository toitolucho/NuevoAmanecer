/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Encargados;
import bo.usfx.nuevoAmanecer.model.domain.Programas;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.utils.FormUtilities;
import bo.usfx.utils.GeneraReport;
import bo.usfx.utils.ModeloPrograma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.GuiEncargado;
import view.GuiGestionarProgramas;
import view.GuiIntegrante;
import view.GuiParametrosReporteProgramas;
import view.GuiPrograma;
import view.GuiProgramasAfiliados;
import view.GuiProgramasEncargados;
import view.GuiProgramasParticipantes;


public class CGuiGestionarProgramas extends MouseAdapter implements ActionListener{
    private GuiGestionarProgramas formGestionProgramas;
    private CommonDao dao;
    List<Programas> listaProgramas;
    ModeloPrograma modeloPrograma;
     Connection conexion;
     Usuarios usuario;
    
    public CGuiGestionarProgramas(GuiGestionarProgramas formGestionProgramas) {
        this.formGestionProgramas = formGestionProgramas;
        modeloPrograma = new ModeloPrograma();
    }

    public CommonDao getDao() {
        return dao;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    
    
    public void actionPerformed(ActionEvent event) {
        String accion = event.getActionCommand();
        if(accion.compareTo(formGestionProgramas.getBtnAdministrarEncargados().getActionCommand())==0)
        {
            GuiEncargado formEncargados = new GuiEncargado();
            formEncargados.control.setDao(dao);
            formEncargados.control.setUsuario(usuario);
            formEncargados.control.onFormShown();
            FormUtilities.centrar2(formEncargados, this.formGestionProgramas);
            formEncargados.setVisible(true);
        }
        if(accion.compareTo(formGestionProgramas.getBtnAdministrarIntegrantes().getActionCommand())==0)
        {
            GuiIntegrante formIntegrante = new GuiIntegrante();
            formIntegrante.control.setDao(dao);
            formIntegrante.control.setUsuario(usuario);
            formIntegrante.control.onFormShown();
            FormUtilities.centrar2(formIntegrante, this.formGestionProgramas);
            formIntegrante.setVisible(true);
        }
        if(accion.compareTo(formGestionProgramas.getBtnAdministrarProgramas().getActionCommand())==0)
        {
            GuiPrograma formProgramas = new GuiPrograma();
            formProgramas.control.setDao(dao);
            formProgramas.control.setUsuario(usuario);
            formProgramas.control.onFormShown();
            FormUtilities.centrar2(formProgramas, this.formGestionProgramas);
            formProgramas.setVisible(true);
        }
        if(accion.compareTo(formGestionProgramas.getBtnAsignarAfiliados().getActionCommand())==0)
        {
            if(modeloPrograma != null && modeloPrograma.getRowCount() != 0
                    && formGestionProgramas.getjTableProgramas().getSelectedRow() >= 0)
            {
                Programas programaSeleccionado = modeloPrograma.getProgramas(formGestionProgramas.getjTableProgramas().getSelectedRow());
                GuiProgramasAfiliados formProgramas = new GuiProgramasAfiliados(formGestionProgramas, true);
                formProgramas.control.setDao(dao);
                formProgramas.control.setProgramaActual(programaSeleccionado);
                formProgramas.control.onFormShown();
                FormUtilities.centrar2(formProgramas, formGestionProgramas);
                formProgramas.setVisible(true);
                formProgramas.dispose();
            }

             else
            {
                JOptionPane.showMessageDialog(formGestionProgramas, "Aún no ha seleccionado un Programa", "Gestion de Programas", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(accion.compareTo(formGestionProgramas.getBtnAsignarEncargados().getActionCommand())==0)
        {
            if(modeloPrograma != null && modeloPrograma.getRowCount() != 0
                    && formGestionProgramas.getjTableProgramas().getSelectedRow() >= 0)
            {
                Programas programaSeleccionado = modeloPrograma.getProgramas(formGestionProgramas.getjTableProgramas().getSelectedRow());
                GuiProgramasEncargados formProgramas = new GuiProgramasEncargados(formGestionProgramas, true);
                formProgramas.control.setDao(dao);
                formProgramas.control.setProgramaActual(programaSeleccionado);
                formProgramas.control.onFormShown();
                FormUtilities.centrar2(formProgramas, formGestionProgramas);
                formProgramas.setVisible(true);
                formProgramas.dispose();
            }

             else
            {
                JOptionPane.showMessageDialog(formGestionProgramas, "Aún no ha seleccionado un Programa", "Gestion de Programas", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(accion.compareTo(formGestionProgramas.getBtnAsignarParticipantes().getActionCommand())==0)
        {
            if(modeloPrograma != null && modeloPrograma.getRowCount() != 0
                    && formGestionProgramas.getjTableProgramas().getSelectedRow() >= 0)
            {
                Programas programaSeleccionado = modeloPrograma.getProgramas(formGestionProgramas.getjTableProgramas().getSelectedRow());
                GuiProgramasParticipantes formProgramas = new GuiProgramasParticipantes(formGestionProgramas, true);
                formProgramas.control.setDao(dao);
                formProgramas.control.setProgramaActual(programaSeleccionado);
                formProgramas.control.onFormShown();
                FormUtilities.centrar2(formProgramas, formGestionProgramas);
                formProgramas.setVisible(true);
                formProgramas.dispose();
            }

             else
            {
                JOptionPane.showMessageDialog(formGestionProgramas, "Aún no ha seleccionado un Programa", "Gestion de Programas", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(accion.compareTo("buscarPrograma")==0)
        {
            if(formGestionProgramas.getTxtTextoBusqueda().getText().isEmpty())
            {
                JOptionPane.showMessageDialog(formGestionProgramas, "Aún no ha ingresado un Texto para buscarlos", "Programas Sociales", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Programas programa = new Programas();
            int codigoPrograma = -1;
            try {
                codigoPrograma = Integer.parseInt(formGestionProgramas.getTxtTextoBusqueda().getText());
                programa.setCodigo_programa(codigoPrograma);
            } catch (Exception e) {
            }
            programa.setNombre_actividad(formGestionProgramas.getTxtTextoBusqueda().getText());
            try {
                listaProgramas = dao.findObjects(programa);
                modeloPrograma.clear();

                if(listaProgramas != null)
                {
                    for (Programas programas : listaProgramas) {
                        modeloPrograma.addProgramas(programas);
                    }

                    formGestionProgramas.getBtnAsignarAfiliados().setEnabled(true);
                    formGestionProgramas.getBtnAsignarEncargados().setEnabled(true);
                    formGestionProgramas.getBtnAsignarParticipantes().setEnabled(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(formGestionProgramas, "No se encontró ningún programa con los parametros provistos", "Gestion de Programas", JOptionPane.INFORMATION_MESSAGE);
                    formGestionProgramas.getBtnAsignarAfiliados().setEnabled(false);
                    formGestionProgramas.getBtnAsignarEncargados().setEnabled(false);
                    formGestionProgramas.getBtnAsignarParticipantes().setEnabled(false);
                }
                formGestionProgramas.getTxtTextoBusqueda().grabFocus();
                formGestionProgramas.getTxtTextoBusqueda().selectAll();
            } catch (SQLException ex) {
                Logger.getLogger(CGuiGestionarProgramas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void onFormShown()
    {
        formGestionProgramas.getjTableProgramas().setModel(modeloPrograma);
        formGestionProgramas.getBtnAsignarAfiliados().setEnabled(false);
        formGestionProgramas.getBtnAsignarEncargados().setEnabled(false);
        formGestionProgramas.getBtnAsignarParticipantes().setEnabled(false);

    }
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getClickCount() == 2) {
            this.formGestionProgramas.setVisible(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GuiParametrosReporteProgramas formParametros = new GuiParametrosReporteProgramas(this.formGestionProgramas, true);
        formParametros.setVisible(true);
        if (formParametros.control.operacionConfirmada) {
          
            if (formParametros.getPnlRangoFechas().getCalendarioFechaInicio() == null || formParametros.getPnlRangoFechas().getCalendarioFechaFin() == null) {
                JOptionPane.showMessageDialog(this.formGestionProgramas, "Aún no ha ingresado el Rango de Fechas para los Programas", "Selección de Parametros", JOptionPane.ERROR_MESSAGE);
                return;
            }
            GeneraReport gr;
            gr = new GeneraReport();
            Class myClass = GuiGestionarProgramas.class;
            URL url = myClass.getResource("GuiGestionarProgramas.class");
            String rutaLocal = url.getPath().substring(1).replaceAll("view/GuiGestionarProgramas.class", "").trim();
            if (rutaLocal.contains("%20"))
            {
                    rutaLocal = rutaLocal.replace("%20", " ");
            }

            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "reporteProgramas.jasper");
            gr.setParameters("FechaInicio", formParametros.getPnlRangoFechas().getCalendarioFechaInicio().getTime());
            gr.setParameters("FechaFin", formParametros.getPnlRangoFechas().getCalendarioFechaFin().getTime());
            gr.setParameters("ProgramaActivo", formParametros.getrBtntodos().isSelected() ? null : formParametros.getrBtnEjecucion().isSelected());
            gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }
    }

    public Programas getProgramaSeleccionado() {
        if (modeloPrograma == null || modeloPrograma.getRowCount() == 0) {
            return null;
        } else {
            return modeloPrograma.getProgramas(formGestionProgramas.getjTableProgramas().getSelectedRow());
        }
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    

    
    
}
