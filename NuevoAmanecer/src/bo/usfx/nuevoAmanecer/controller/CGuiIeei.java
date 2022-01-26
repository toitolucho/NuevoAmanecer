/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Casos;
import bo.usfx.utils.GeneraReport;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.util.Calendar;
import javax.swing.JOptionPane;
import view.GuiAfiliadosReportes;
import view.GuiCasosBuscador;
import view.GuiIeei;
import view.GuiParametrosReporteAfiiados;
import view.GuiParametrosReporteCorrespondencia;
import view.GuiParametrosReporteProgramas;
import view.GuiPrincipal;
import view.GuiTarjetaReporte;


public class CGuiIeei implements ActionListener{
    GuiIeei formIeei;
    Connection conexion;
    String rutaLocal;
    GeneraReport gr;
    private CommonDao dao;

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }



    public CGuiIeei(GuiIeei formIeei)
    {
        this.formIeei = formIeei;

        Class myClass = GuiPrincipal.class;
        URL url = myClass.getResource("GuiPrincipal.class");
        rutaLocal = url.getPath().substring(1).replaceAll("view/GuiPrincipal.class", "").trim();
        if (rutaLocal.contains("%20"))
        {
                rutaLocal = rutaLocal.replace("%20", " ");
        }
        gr = new GeneraReport();
    }

    public void actionPerformed(ActionEvent event) {
        String accion = event.getActionCommand();

         Calendar FechaInicio =Calendar.getInstance();
        Calendar FechaFin =Calendar.getInstance();
        FechaInicio.set(Calendar.MONTH, 1);
        FechaInicio.set(Calendar.DAY_OF_YEAR, 1);

        FechaFin.set(Calendar.MONTH, 12);
        FechaFin.set(Calendar.DAY_OF_YEAR, 31);

        if(accion.compareTo(formIeei.getBtnCoberturaInmunizacion().getActionCommand()) == 0)
        {
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "reportePromedioCoberturaInmunizacion.jasper");
            gr.setParameters("AnioGestion",Calendar.getInstance().getTime());
            gr.setParameters("NombreProyecto","NUEVO AMANECER");
            gr.setParameters("NumeroProyecto",new Integer(1));
            gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }

        if(accion.compareTo(formIeei.getBtnControlNutricional().getActionCommand()) == 0)
        {
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "reporteECCD.jasper");
            gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }

        if(accion.compareTo(formIeei.getBtnCorrespondencia().getActionCommand()) == 0)
        {
            GuiParametrosReporteCorrespondencia formParametros = new GuiParametrosReporteCorrespondencia(this.formIeei, true);
            formParametros.setVisible(true);
            if(formParametros.control.operacionConfirmada)
            {
                FechaInicio = formParametros.getPnlRangoFechas().getCalendarioFechaInicio();
                FechaFin = formParametros.getPnlRangoFechas().getCalendarioFechaFin();

                if(FechaInicio == null || FechaFin == null)
                {
                    JOptionPane.showMessageDialog(this.formIeei, "Aún no ha ingresado el Rango de Fechas", "Selección de Parametros", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Casos casoSeleccionado = null;
                if(formParametros.getChecSeleccionarAfiliado().isSelected())
                {
                    GuiCasosBuscador formBuscador = new GuiCasosBuscador(this.formIeei, true);
                    formBuscador.control.setDao(dao);
                    formBuscador.control.onFormShown();
                    formBuscador.setVisible(true);
                    casoSeleccionado = formBuscador.control.getCasoSeleccionado();
                }
                String path = rutaLocal + "view/";
                gr.loadReportJasper(path + "reporteCorrespondencia.jasper");
                gr.setParameters("FechaInicio", FechaInicio.getTime());
                gr.setParameters("FechaFin",  FechaFin.getTime());
                gr.setParameters("NumeroProyecto",new Integer(1));
                gr.setParameters("NumeroCaso", casoSeleccionado == null ? null : casoSeleccionado.getNumero_caso());
                gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }
        }

        if(accion.compareTo(formIeei.getBtnDemografico().getActionCommand()) == 0)
        {
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "ReporteDemografico.jasper");
            gr.setParameters("AnioGestion",Calendar.getInstance().getTime());
            gr.setParameters("NombreProyecto","NUEVO AMANECER");
            gr.setParameters("NumeroProyecto",new Integer(1));
            gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }

        if(accion.compareTo(formIeei.getBtnEducacion().getActionCommand()) == 0)
        {
            String input = "";
            
            String[] sport = new String[]{"Participacion Escolar", "Educacion Superior", "Niveles de Logro"};

            input = (String) JOptionPane.showInputDialog(formIeei,
                    "Seleccione el tipo de reporte que desea Visualizar", "Reportes", JOptionPane.INFORMATION_MESSAGE,
                    null, sport, "Participacion Escolar");

            if(input != null)
            {
                String path = rutaLocal + "view/";
                if(input.compareTo("Participacion Escolar") == 0)
                {
                    gr.loadReportJasper(path + "reporteParticipacionEscolar.jasper");
                    gr.setParameters("AnioGestion",Calendar.getInstance().getTime());
                    gr.setParameters("NumeroProyecto",new Integer(1));
                    gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                    gr.fillReport(conexion);
                    gr.previewReport();
                }
                if(input.compareTo("Educacion Superior") == 0)
                {
                    gr.loadReportJasper(path + "reporteEducacionSuperior.jasper");
                    gr.setParameters("NombreProyecto","NUEVO AMANECER");
                    gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                    gr.fillReport(conexion);
                    gr.previewReport();
                }
                if(input.compareTo("Niveles de Logro") == 0)
                {
                    gr.loadReportJasper(path + "reporteTablaNIVELES_DE_LOGROS_DE_APRENDIZAJE.jasper");
                    gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                    gr.fillReport(conexion);
                    gr.previewReport();
                }
            }
            else{
                JOptionPane.showMessageDialog(formIeei, "No ha seleccionado ningúna Opción", "Reportes", JOptionPane.ERROR_MESSAGE);
                return;
            }

        }

        if(accion.compareTo(formIeei.getBtnCoberturaInmunizacion().getActionCommand()) == 0)
        {
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "reportePromedioCoberturaInmunizacion.jasper");
            gr.setParameters("AnioGestion",Calendar.getInstance().getTime());
            gr.setParameters("NombreProyecto","NUEVO AMANECER");
            gr.setParameters("NumeroProyecto",new Integer(1));
            gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }

        if(accion.compareTo(formIeei.getBtnEventoVital().getActionCommand()) == 0)
        {
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "reporteMortalidadMaternaInfantil.jasper");
            gr.setParameters("Fecha_Gestion",Calendar.getInstance().getTime());
            gr.setParameters("NumeroProyecto",new Integer(1));
            gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }

        if(accion.compareTo(formIeei.getBtnMuertes().getActionCommand()) == 0)
        {
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "ReporteMuertes.jasper");
            gr.setParameters("AnioGestion", Calendar.getInstance().getTime());
            gr.setParameters("NombreProyecto", "NUEVO AMANECER");
            gr.setParameters("NumeroProyecto", new Integer(1));
            gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }

        if(accion.compareTo(formIeei.getBtnAfiliados().getActionCommand()) == 0)
        {
            GuiAfiliadosReportes formAfiliadosReporte = new GuiAfiliadosReportes(this.formIeei, true);
            formAfiliadosReporte.control.setConexion(conexion);
            formAfiliadosReporte.control.setDao(dao);
//            formAfiliadosReporte.control.setUsuario(usuario);
            formAfiliadosReporte.control.onFormShown();
            formAfiliadosReporte.setVisible(true);
            formAfiliadosReporte.dispose();
        }

        if(accion.compareTo(formIeei.getBtnTarjetaFamiliar().getActionCommand()) == 0)
        {
            GuiTarjetaReporte formTarjetaReporte = new GuiTarjetaReporte(this.formIeei, true);
            formTarjetaReporte.control.setConexion(conexion);
            formTarjetaReporte.control.setDao(dao);
//            formTarjetaReporte.control.setUsuario(usuario);
            formTarjetaReporte.control.onFormShown();
            formTarjetaReporte.setVisible(true);
        }

        if(accion.compareTo(formIeei.getBtnPatrocinio().getActionCommand()) == 0)
        {
            String path = rutaLocal + "view/";
            GuiParametrosReporteAfiiados formParametros = new GuiParametrosReporteAfiiados(formIeei, true);
            formParametros.setVisible(true);
            if(formParametros.control.operacionConfirmada)
            {
                if(formParametros.getRbtnListaMaestra().isSelected())
                {
                    gr.loadReportJasper(path + "reporteListaMaestraPorNumeroCaso.jasper");
                }
                if(formParametros.getRbtnFamilias().isSelected())
                {
                    gr.loadReportJasper(path + "reporteMiembrosFamilia.jasper");
                }
                if(formParametros.getRbtnPatrinos().isSelected())
                {
                    gr.loadReportJasper(path + "reportePadrinos.jasper");
                }
                if(formParametros.getRbtnPatrocinioCancelado().isSelected())
                {
//                    gr.loadReportJasper(path + "reporteListaMaestraPorNumeroCaso.jasper");
//                    gr.setParameters("AnioGestion",Calendar.getInstance().getTime());
//                    gr.setParameters("NombreProyecto","NUEVO AMANECER");
//                    gr.setParameters("NumeroProyecto",new Integer(1));
                }
                if(formParametros.getrBtnAfiliadosNoPatrocinados().isSelected())
                {
                    gr.loadReportJasper(path + "reporteAfiliados.jasper");
                    gr.setParameters("Patrocinados",false);
                    gr.setParameters("FechaInicio",null);
                    gr.setParameters("FechaFin",null);
                    gr.setParameters("filtro_fecha_caso",null);
                }
                if(formParametros.getrBtnAfiliadosPatrocinados().isSelected())
                {
                    gr.loadReportJasper(path + "reporteAfiliados.jasper");
                    gr.setParameters("Patrocinados",true);
                    gr.setParameters("FechaInicio",null);
                    gr.setParameters("FechaFin",null);
                    gr.setParameters("filtro_fecha_caso",null);
                }
                if(formParametros.getrBtnRangoFechaRegistroCaso().isSelected())
                {
                    if(formParametros.getjDateChooser1().getCalendar() == null || formParametros.getjDateChooser2().getCalendar() == null)
                    {
                        JOptionPane.showMessageDialog(formParametros, "No ha Seleccionado ninguna Fecha", "Selección de Tipo de Reporte de Patrocinio", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(formParametros.getjDateChooser1().getCalendar().after(  formParametros.getjDateChooser2().getCalendar() == null))
                    {
                        JOptionPane.showMessageDialog(formParametros, "La Fecha de Inicio es Mayor a la de Culminación", "Selección de Tipo de Reporte de Patrocinio", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    gr.loadReportJasper(path + "reporteAfiliados.jasper");
                    gr.setParameters("Patrocinados",false);
                    gr.setParameters("FechaInicio",formParametros.getjDateChooser1().getDate());
                    gr.setParameters("FechaFin",formParametros.getjDateChooser2().getDate());
                    gr.setParameters("filtro_fecha_caso",true);
                }
                if(formParametros.getrBtnRangoFechaRegistroTarjeta().isSelected())
                {
                    if(formParametros.getjDateChooser1().getCalendar() == null || formParametros.getjDateChooser2().getCalendar() == null)
                    {
                        JOptionPane.showMessageDialog(formParametros, "No ha Seleccionado ninguna Fecha", "Selección de Tipo de Reporte de Patrocinio", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(formParametros.getjDateChooser1().getCalendar().after(  formParametros.getjDateChooser2().getCalendar() == null))
                    {
                        JOptionPane.showMessageDialog(formParametros, "La Fecha de Inicio es Mayor a la de Culminación", "Selección de Tipo de Reporte de Patrocinio", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    gr.loadReportJasper(path + "reporteAfiliados.jasper");
                    gr.setParameters("Patrocinados",false);
                    gr.setParameters("FechaInicio",formParametros.getjDateChooser1().getDate());
                    gr.setParameters("FechaFin",formParametros.getjDateChooser2().getDate());
                    gr.setParameters("filtro_fecha_caso",false);
                }
                gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }
            formParametros.dispose();
        }

        if(accion.compareTo(formIeei.getBtnProgramas().getActionCommand()) == 0)
        {
            String input = "";

            String[] sport = new String[]{"Listado General", "Listado Detallado"};

            input = (String) JOptionPane.showInputDialog(formIeei,
                    "Seleccione el tipo de reporte que desea Visualizar", "Reportes", JOptionPane.INFORMATION_MESSAGE,
                    null, sport, "Listado General");

            if(input != null)
            {
                String path = rutaLocal + "view/";
                if(input.compareTo("Listado Detallado") == 0)
                {
                    gr.loadReportJasper(path + "reportProgramasDetalle.jasper");
                    gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                    gr.fillReport(conexion);
                    gr.previewReport();
                }
                if(input.compareTo("Listado General") == 0)
                {
                    GuiParametrosReporteProgramas formParametros = new GuiParametrosReporteProgramas(this.formIeei, true);
                    formParametros.setVisible(true);
                    if(formParametros.control.operacionConfirmada)
                    {
                        FechaInicio = formParametros.getPnlRangoFechas().getCalendarioFechaInicio();
                        FechaFin = formParametros.getPnlRangoFechas().getCalendarioFechaFin();

//                        if(FechaInicio == null || FechaFin == null)
//                        {
//                            JOptionPane.showMessageDialog(this.formIeei, "Aún no ha ingresado el Rango de Fechas", "Selección de Parametros", JOptionPane.ERROR_MESSAGE);
//                            return;
//                        }
                        gr.loadReportJasper(path + "reporteProgramas.jasper");
                        gr.setParameters("FechaInicio", FechaInicio != null ? FechaInicio.getTime() : null);
                        gr.setParameters("FechaFin",  FechaFin != null ? FechaFin.getTime() : null);
                        gr.setParameters("ProgramaActivo", formParametros.getrBtntodos().isSelected() ? null : formParametros.getrBtnEjecucion().isSelected());
                        gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                        gr.fillReport(conexion);
                        gr.previewReport();
                    }
               
                }
            else{
                JOptionPane.showMessageDialog(formIeei, "No ha seleccionado ningúna Opción", "Reportes", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

        if(accion.compareTo(formIeei.getBtnSaludPublica().getActionCommand()) == 0)
        {
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "ReporteStatusFamilia.jasper");
            gr.setParameters("FechaInicio",FechaInicio.getTime());
            gr.setParameters("FechaFin",FechaFin.getTime());
            gr.setParameters("NombreProyecto","NUEVO AMANECER");
            gr.setParameters("NumeroProyecto",new Integer(1));
            gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    

    public void onFormShown()
    {
        
    }
}
