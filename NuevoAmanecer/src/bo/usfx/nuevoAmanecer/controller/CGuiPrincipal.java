/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.dao.ibatis.SqlMapCommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Casos;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormulariosPermisosUsuarios;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.utils.FormUtilities;
import bo.usfx.utils.GeneraReport;
import bo.usfx.utils.UtilidadConexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import sun.tools.jar.resources.jar;
import view.GuiEmpleadosUsuariosAdministracion;
import view.GuiParametrosReporteAfiiados;
import view.GuiParametrosReporteCorrespondencia;
import view.GuiParametrosReporteProgramas;
import view.GuiSistemaConfiguracion;
import view.GuiAfiliadoBuscador;
import view.GuiAfiliadosReportes;
import view.GuiAyuda;
import view.GuiCargosAdministrador;
import view.GuiCaso;
import view.GuiCasosBuscador;
import view.GuiCorrespondencia;
import view.GuiEccd;
import view.GuiFamilia;
import view.GuiGestionarProgramas;
import view.GuiIeei;
import view.GuiPatrocinador;
import view.GuiPatrocinadorBuscador;
import view.GuiPrincipal;
import view.GuiPrograma;
import view.GuiProgramaBuscador;
import view.GuiSaludPublica;
import view.GuiTarjeta;
import view.GuiTarjetaBuscador;
import view.GuiTarjetaFamiliarCompleto;
import view.GuiTarjetaReporte;
import view.GuiUsuarios;
import view.GuiUsuariosCambiarContrasenia;
import view.GuiVacuna;
import view.GuiVacunasAdministrador;
import view.GuiVerTarjetaFamiliar;

public class CGuiPrincipal implements ActionListener {

    GuiPrincipal formPrincipal;
    private CommonDao dao;
    int NumeroProyecto = 1;
    Connection conexion;
    UtilidadConexion utilidadConexion;
    String rutaLocal;
    GeneraReport gr;
    Usuarios usuario;
    java.util.Date fechaHoraServidor;
    java.util.Calendar FechaInicio;
    java.util.Calendar FechaFin;
    private int indiceBuscado;
    private GuiEmpleadosUsuariosAdministracion formEmpleadosUsuariosAdministracion = null;
    private GuiCasosBuscador formBuscadorCaso = null;
    private GuiCaso formCaso = null;
    private GuiUsuarios formUsuarios = null;
    private GuiCorrespondencia formCorrespondencia = null;
    private GuiGestionarProgramas formGestionarProgramas = null;
    private GuiPatrocinador formPatrocinador = null;
    private GuiTarjeta formTarjeta = null;
    private GuiSistemaConfiguracion formSistemaConfiguracion = null;
    private GuiCargosAdministrador formCargosAdministrador = null;
    private GuiVacunasAdministrador formVacunasAdministrador;
    private GuiTarjetaFamiliarCompleto formTarjetaFamiliarCompleto;
    private GuiAfiliadosReportes formAfiliadosReporte = null;
    private GuiTarjetaReporte formTarjetaReporte = null;
    private GuiIeei formVerReporteIeei = null;
    private GuiAyuda formAyuda = null;

    public CGuiPrincipal(GuiPrincipal formPrincipal) {
        this.formPrincipal = formPrincipal;
        utilidadConexion = new UtilidadConexion();
        try {
            conexion = utilidadConexion.obtenerConeccion();
            dao = new SqlMapCommonDao();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formPrincipal, "Ocurrio la Siguiente Excepción " + ex.getMessage()
                    + "\nConsulte con su Administrador", "Nuevo Amanecer", JOptionPane.ERROR_MESSAGE);
        }
        this.formPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Class myClass = GuiPrincipal.class;
        URL url = myClass.getResource("GuiPrincipal.class");
        rutaLocal = url.getPath().substring(1).replaceAll("view/GuiPrincipal.class", "").trim();
        if (rutaLocal.contains("%20")) {
            rutaLocal = rutaLocal.replace("%20", " ");
        }
        gr = new GeneraReport();        

        //String rutaLocal = GuiPrincipal.class.getResource("GuiPrincipal.class").getPath().substring(1).replaceAll("view/GuiPrincipal.class", "").trim().replace("%20", " ");
//        FormUtilities.centrar2(formPrincipal, null);
    }

    public boolean verificarPermisosFormularios(java.awt.Window formulario) {

        
        SistemaFormulariosPermisosUsuarios keySistemaFormulariosPermisos = new SistemaFormulariosPermisosUsuarios();
        keySistemaFormulariosPermisos.getSistemaFormulario().setNombre_formulario(formulario.getClass().getName().replace("view.", ""));
//        Collections.sort(usuario.getListadoInterfacesPermisos());
        indiceBuscado = Collections.binarySearch(usuario.getListadoInterfacesPermisos(), keySistemaFormulariosPermisos);

        System.out.println(formulario.getClass().getName().replace("view.", "") + "  Cantidad " + usuario.getListadoInterfacesPermisos().size() + ", Indice " + indiceBuscado);

        if (indiceBuscado < 0) {
            return false;
        } else {

            if (usuario.tieneAlgunPermiso(keySistemaFormulariosPermisos)) {
                return true;
            } else {
                return false;
            }
        }

    }

    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();

        Calendar FechaInicio = Calendar.getInstance();
        Calendar FechaFin = Calendar.getInstance();
        FechaInicio.set(Calendar.MONTH, 1);
        FechaInicio.set(Calendar.DAY_OF_YEAR, 1);

        FechaFin.set(Calendar.MONTH, 12);
        FechaFin.set(Calendar.DAY_OF_YEAR, 31);

        //reporteAfiliadosPatrocinados.jasper
        //reportePadrinos.jasper
        //reporteAfiliadosNoPatrocinados.jasper


        //copiaSeguridad

        System.out.println("accion realizada " + accion);
        if(accion.compareTo("salir") == 0)
        {
            System.exit(0);

        }

        if(accion.compareTo("ayuda") == 0)
        {           
//            formAyuda.setVisible(true);

            if (formAyuda == null) {
                formAyuda = new GuiAyuda();
                formAyuda.setLocation(0, 0);
            }
            formAyuda.setVisible(true);
        }

        if (accion.compareTo("backup") == 0) {
            System.out.println("Copia de Seguridad con backup con cambios");
            if(formSistemaConfiguracion == null)
            {
                try {
                    formSistemaConfiguracion = new GuiSistemaConfiguracion();
                    formSistemaConfiguracion.control.setDao(dao);
                    formSistemaConfiguracion.control.setUsuario(usuario);
                    formSistemaConfiguracion.control.setIdFormulario(usuario.getListadoInterfacesPermisos().get(indiceBuscado).getId_sistema_formulario());
                } catch (Exception ex) {
                    System.out.println("Ocurrio la siguiente excepcion " + ex.getMessage());
                }
            }
            
            formSistemaConfiguracion.setVisible(true);
        }
        if(accion.compareTo("cambiarContrasenia") == 0){
            GuiUsuariosCambiarContrasenia formCambiarContrasenia = new GuiUsuariosCambiarContrasenia(formPrincipal , true);
            formCambiarContrasenia.control.setDao(dao);
            formCambiarContrasenia.control.setUsuario(usuario);
            formCambiarContrasenia.control.onFormShown();
            formCambiarContrasenia.setVisible(true);
        }



        if (accion.compareTo(formPrincipal.getBtnVerIEEI().getActionCommand()) == 0) {


            if (formVerReporteIeei == null) {
                formVerReporteIeei = new GuiIeei();

//                if (!verificarPermisosFormularios(formVerReporteIeei)) {
//                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
//                    return;
//                }

                formVerReporteIeei.control.setDao(dao);
                formVerReporteIeei.control.setConexion(conexion);
                formVerReporteIeei.control.setDao(dao);                
                formVerReporteIeei.setLocation(0, 0);
            }
//            else {
//                if (!verificarPermisosFormularios(formVerReporteIeei)) {
//                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
//                    return;
//                }
//            }
            formVerReporteIeei.control.onFormShown();
            formVerReporteIeei.setVisible(true);
        }

        if (accion.compareTo("VerAfiliadosPatrocinados") == 0) {
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "reporteAfiliadosPatrocinados.jasper");
            gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }
        if (accion.compareTo("VerPatrocinadores") == 0) {
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "reportePadrinos.jasper");
            gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }
        if (accion.compareTo("VerAfiliadosNoPatrocinados") == 0) {
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "reporteAfiliadosNoPatrocinados.jasper");
            gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }
        //reporteVerTarjetaFamiliar.jasper
        if (accion.compareTo("VerTarjetaFamiliar") == 0) {
            GuiVerTarjetaFamiliar formVerTarjeta = new GuiVerTarjetaFamiliar();
            formVerTarjeta.control.setDao(dao);
            formVerTarjeta.control.onFormShown();
            FormUtilities.centrar2(formVerTarjeta, formTarjeta);
            formVerTarjeta.setVisible(true);
        }

        if (accion.compareTo(formPrincipal.getMenuItemDemografico().getActionCommand()) == 0) {
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "ReporteDemografico.jasper");
            gr.setParameters("AnioGestion", Calendar.getInstance().getTime());
            gr.setParameters("NombreProyecto", "NUEVO AMANECER");
            gr.setParameters("NumeroProyecto", new Integer(1));
            gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }

        if (accion.compareTo(formPrincipal.getMenuItemMuertes().getActionCommand()) == 0) {
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "ReporteMuertes.jasper");
            gr.setParameters("AnioGestion", Calendar.getInstance().getTime());
            gr.setParameters("NombreProyecto", "NUEVO AMANECER");
            gr.setParameters("NumeroProyecto", new Integer(1));
            gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }


        if (accion.compareTo(formPrincipal.getMenuItemEventosVitales().getActionCommand()) == 0) {
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "reporteMortalidadMaternaInfantil.jasper");
            gr.setParameters("Fecha_Gestion", Calendar.getInstance().getTime());
//            gr.setParameters("NombreProyecto","NUEVO AMANECER");
            gr.setParameters("NumeroProyecto", new Integer(1));
            gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }

        if (accion.compareTo("EducacionSuperior") == 0) {
//            gr = new GeneraReport();
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "reporteEducacionSuperior.jasper");
//            gr.setParameters("AnioGestion",Calendar.getInstance().getTime());
            gr.setParameters("NombreProyecto", "NUEVO AMANECER");
            gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
//            gr.setParameters("NumeroProyecto",new Integer(1));
            gr.fillReport(conexion);
            gr.previewReport();

        }
        if (accion.compareTo("ParticipacionEscolar") == 0) {
//            gr = new GeneraReport();
            String path = rutaLocal + "view/";
//            

            gr.loadReportJasper(path + "reporteParticipacionEscolar.jasper");
            gr.setParameters("AnioGestion", Calendar.getInstance().getTime());
            gr.setParameters("NumeroProyecto", new Integer(1));
            gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }

        if (accion.compareTo("NivelesLogro") == 0) {

            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "reporteTablaNIVELES_DE_LOGROS_DE_APRENDIZAJE.jasper");
//            gr.setParameters("AnioGestion",Calendar.getInstance().getTime());
//            gr.setParameters("NumeroProyecto",new Integer(1));
            gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }

        if (accion.compareTo(formPrincipal.getMenuItemControlNutricional().getActionCommand()) == 0) {
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "reporteECCD.jasper");
            gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
            System.out.println("Reporte Mostrado:  reporteECCD");
        }

        if (accion.compareTo("listaMaestra") == 0) {
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "reporteListaMaestraPorNumeroCaso.jasper");
            gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }

        if (accion.compareTo(formPrincipal.getMenuItemCoberturaInmunizacion().getActionCommand()) == 0) {
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "reportePromedioCoberturaInmunizacion.jasper");
            gr.setParameters("AnioGestion", Calendar.getInstance().getTime());
            gr.setParameters("NombreProyecto", "NUEVO AMANECER");
            gr.setParameters("NumeroProyecto", new Integer(1));
            gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }

        if (accion.compareTo(formPrincipal.getMenuItemSaludPublica().getActionCommand()) == 0) {
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "ReporteStatusFamilia.jasper");
            gr.setParameters("FechaInicio", null);
            gr.setParameters("FechaFin", null);
            gr.setParameters("NombreProyecto", "NUEVO AMANECER");
            gr.setParameters("NumeroProyecto", new Integer(1));
            gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }

        if (accion.compareTo(formPrincipal.getMenuItemCorrespondencia().getActionCommand()) == 0) {
            GuiParametrosReporteCorrespondencia formParametros = new GuiParametrosReporteCorrespondencia(this.formPrincipal, true);
            formParametros.setVisible(true);
            if (formParametros.control.operacionConfirmada) {
                FechaInicio = formParametros.getPnlRangoFechas().getCalendarioFechaInicio();
                FechaFin = formParametros.getPnlRangoFechas().getCalendarioFechaFin();

                if (FechaInicio == null || FechaFin == null) {
                    JOptionPane.showMessageDialog(this.formPrincipal, "Aún no ha ingresado el Rango de Fechas", "Selección de Parametros", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Casos casoSeleccionado = null;
                if (formParametros.getChecSeleccionarAfiliado().isSelected()) {
                    GuiCasosBuscador formBuscador = new GuiCasosBuscador(this.formPrincipal, true);
                    formBuscador.control.setDao(dao);
                    formBuscador.control.onFormShown();
                    formBuscador.setVisible(true);
                    casoSeleccionado = formBuscador.control.getCasoSeleccionado();
                }
                String path = rutaLocal + "view/";
                gr.loadReportJasper(path + "reporteCorrespondencia.jasper");
                gr.setParameters("FechaInicio", FechaInicio.getTime());
                gr.setParameters("FechaFin", FechaFin.getTime());
                gr.setParameters("NumeroProyecto", new Integer(1));
                gr.setParameters("NumeroCaso", casoSeleccionado == null ? null : casoSeleccionado.getNumero_caso());
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

        }



        if (accion.compareTo(formPrincipal.getMenuItemPatrocinio().getActionCommand()) == 0) {
            //reporteAfiliados.jasper
            String path = rutaLocal + "view/";
            GuiParametrosReporteAfiiados formParametros = new GuiParametrosReporteAfiiados(formPrincipal, true);
            formParametros.setVisible(true);
            if (formParametros.control.operacionConfirmada) {
                if (formParametros.getRbtnListaMaestra().isSelected()) {
                    gr.loadReportJasper(path + "reporteListaMaestraPorNumeroCaso.jasper");
                }
                if (formParametros.getRbtnFamilias().isSelected()) {
                    gr.loadReportJasper(path + "reporteMiembrosFamilia.jasper");
//                    gr.setParameters("AnioGestion",Calendar.getInstance().getTime());
//                    gr.setParameters("NombreProyecto","NUEVO AMANECER");
//                    gr.setParameters("NumeroProyecto",new Integer(1));
                }
                if (formParametros.getRbtnPatrinos().isSelected()) {
                    gr.loadReportJasper(path + "reportePadrinos.jasper");
//                    gr.setParameters("AnioGestion",Calendar.getInstance().getTime());
//                    gr.setParameters("NombreProyecto","NUEVO AMANECER");
//                    gr.setParameters("NumeroProyecto",new Integer(1));
                }
                if (formParametros.getRbtnPatrocinioCancelado().isSelected()) {
                    gr.loadReportJasper(path + "reporteAfiliadosCancelados.jasper");

//                    gr.setParameters("NombreProyecto","NUEVO AMANECER");
//                    gr.setParameters("NumeroProyecto",new Integer(1));
                }
                if (formParametros.getrBtnAfiliadosNoPatrocinados().isSelected()) {
                    gr.loadReportJasper(path + "reporteAfiliados.jasper");
                    gr.setParameters("Patrocinados", false);
                    gr.setParameters("FechaInicio", null);
                    gr.setParameters("FechaFin", null);
                    gr.setParameters("filtro_fecha_caso", null);
                    gr.setParameters("titulo", "No Patrocinados");
                }
                if (formParametros.getrBtnAfiliadosPatrocinados().isSelected()) {
                    gr.loadReportJasper(path + "reporteAfiliados.jasper");
                    gr.setParameters("Patrocinados", true);
                    gr.setParameters("FechaInicio", null);
                    gr.setParameters("FechaFin", null);
                    gr.setParameters("filtro_fecha_caso", null);
                    gr.setParameters("titulo", "Patrocinados");
                }
                if (formParametros.getrBtnRangoFechaRegistroCaso().isSelected()) {
                    if (formParametros.getjDateChooser1().getCalendar() == null || formParametros.getjDateChooser2().getCalendar() == null) {
                        JOptionPane.showMessageDialog(formParametros, "No ha Seleccionado ninguna Fecha", "Selección de Tipo de Reporte de Patrocinio", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (formParametros.getjDateChooser1().getCalendar().after(formParametros.getjDateChooser2().getCalendar() == null)) {
                        JOptionPane.showMessageDialog(formParametros, "La Fecha de Inicio es Mayor a la de Culminación", "Selección de Tipo de Reporte de Patrocinio", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    gr.loadReportJasper(path + "reporteAfiliados.jasper");
                    gr.setParameters("Patrocinados", false);
                    gr.setParameters("FechaInicio", formParametros.getjDateChooser1().getDate());
                    gr.setParameters("FechaFin", formParametros.getjDateChooser2().getDate());
                    gr.setParameters("filtro_fecha_caso", true);
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    gr.setParameters("titulo", "Patrocinados del " + df.format(formParametros.getjDateChooser1().getDate())
                            + " al " + df.format(formParametros.getjDateChooser2().getDate())  + " Por Fecha de Numero de Caso");
                }
                if (formParametros.getrBtnRangoFechaRegistroTarjeta().isSelected()) {
                    if (formParametros.getjDateChooser1().getCalendar() == null || formParametros.getjDateChooser2().getCalendar() == null) {
                        JOptionPane.showMessageDialog(formParametros, "No ha Seleccionado ninguna Fecha", "Selección de Tipo de Reporte de Patrocinio", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (formParametros.getjDateChooser1().getCalendar().after(formParametros.getjDateChooser2().getCalendar() == null)) {
                        JOptionPane.showMessageDialog(formParametros, "La Fecha de Inicio es Mayor a la de Culminación", "Selección de Tipo de Reporte de Patrocinio", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    gr.loadReportJasper(path + "reporteAfiliados.jasper");
                    gr.setParameters("Patrocinados", false);
                    gr.setParameters("FechaInicio", formParametros.getjDateChooser1().getDate());
                    gr.setParameters("FechaFin", formParametros.getjDateChooser2().getDate());
                    gr.setParameters("filtro_fecha_caso", false);
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    gr.setParameters("titulo", "Patrocinados del " + df.format(formParametros.getjDateChooser1().getDate())
                            + " al " + df.format(formParametros.getjDateChooser2().getDate()) + " Por Fecha de registro en Tarjeta");
                }
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }
            formParametros.dispose();



        }

        if (accion.compareTo("ProgramasDetalle") == 0) {
            //
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "reportProgramasDetalle.jasper");
//            gr.setParameters("FechaInicio",FechaInicio.getTime());
//            gr.setParameters("FechaFin",FechaFin.getTime());
//            gr.setParameters("NombreProyecto","NUEVO AMANECER");
//            gr.setParameters("NumeroProyecto",new Integer(1));
            gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
        }
        if (accion.compareTo("Programas") == 0) {

            GuiParametrosReporteProgramas formParametros = new GuiParametrosReporteProgramas(this.formPrincipal, true);
            formParametros.setVisible(true);
            if (formParametros.control.operacionConfirmada) {
                FechaInicio = formParametros.getPnlRangoFechas().getCalendarioFechaInicio();
                FechaFin = formParametros.getPnlRangoFechas().getCalendarioFechaFin();

//                if (FechaInicio == null || FechaFin == null) {
//                    JOptionPane.showMessageDialog(this.formPrincipal, "Aún no ha ingresado el Rango de Fechas", "Selección de Parametros", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }

                String path = rutaLocal + "view/";
                gr.loadReportJasper(path + "reporteProgramas.jasper");
                if(FechaInicio != null)
                    gr.setParameters("FechaInicio", FechaInicio == null ? null : FechaInicio.getTime());
                if(FechaFin != null)
                    gr.setParameters("FechaFin",  FechaFin == null ? null : FechaFin.getTime());
//                gr.setParameters("ProgramaActivo", formParametros.getrBtntodos().isSelected() ? null : formParametros.getrBtnEjecucion().isSelected());
                if(!formParametros.getrBtntodos().isSelected())
                    gr.setParameters("ProgramaActivo", formParametros.getrBtnEjecucion().isSelected());
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

        }

        if (accion.compareTo("reporteAfiliados") == 0) {
            formAfiliadosReporte = new GuiAfiliadosReportes(this.formPrincipal, true);
            formAfiliadosReporte.control.setConexion(conexion);
            formAfiliadosReporte.control.setDao(dao);
            formAfiliadosReporte.control.setUsuario(usuario);
            formAfiliadosReporte.control.onFormShown();
            formAfiliadosReporte.setVisible(true);
            formAfiliadosReporte.dispose();
        }

        if (accion.compareTo("tarjetaFamiliar") == 0) {
            if(formTarjetaReporte == null)
            {
                formTarjetaReporte = new GuiTarjetaReporte(this.formPrincipal, true);
                formTarjetaReporte.control.setConexion(conexion);
                formTarjetaReporte.control.setDao(dao);
                formTarjetaReporte.control.setUsuario(usuario);
                formTarjetaReporte.control.onFormShown();
            }
            formTarjetaReporte.setVisible(true);
        }


        if (accion.compareTo("eccd") == 0) {
            GuiEccd formEccd = new GuiEccd();
            formEccd.control.setDao(dao);
            formEccd.control.onFormShown();
            FormUtilities.centrar2(formEccd, formPrincipal);
            formEccd.setVisible(true);
        }

        if (accion.compareTo("familia") == 0) {
            GuiFamilia familia = new GuiFamilia();
            familia.control.setDao(dao);
            familia.control.setNumeroProyecto(NumeroProyecto);
            familia.control.onFormShown();
            FormUtilities.centrar2(familia, formPrincipal);
            familia.setVisible(true);
        }
        if (accion.compareTo("usuarios") == 0) {
            if (formUsuarios == null) {
                formUsuarios = new GuiUsuarios();

                if (!verificarPermisosFormularios(formUsuarios)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }

//                    formBuscadorCaso.control.setDao(dao);
//                    formBuscadorCaso.control.setConexion(conexion);
//                    formBuscadorCaso.control.setIdFormulario(usuario.getListadoInterfacesPermisos().get(indiceBuscado).getId_sistema_formulario());
//                    formBuscadorCaso.control.setUsuario(usuario);                
                formUsuarios.control.setDao(dao);
                formUsuarios.control.setUsuario(usuario);
                formUsuarios.control.onFormShown();
                FormUtilities.centrar2(formUsuarios, formPrincipal);
                formUsuarios.setVisible(true);

            } else {
                if (!verificarPermisosFormularios(formUsuarios)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }
            }
            formUsuarios.control.cargarDatosUsuario(null);
            formUsuarios.setVisible(true);


        }
        if (accion.compareTo("saludPublica") == 0) {
            GuiSaludPublica formSaludPublica = new GuiSaludPublica();
            formSaludPublica.control.setDao(dao);
            formSaludPublica.control.onFormShown();
            FormUtilities.centrar2(formSaludPublica, formPrincipal);
            formSaludPublica.setVisible(true);
        }
        if (accion.compareTo("vacunas") == 0) {
            GuiVacuna formVacuna = new GuiVacuna();
            formVacuna.control.setDao(dao);
            formVacuna.control.onFormShown();
            FormUtilities.centrar2(formVacuna, formPrincipal);
            formVacuna.setVisible(true);
        }

        if (accion.compareTo(formPrincipal.getBtnBuscar().getActionCommand()) == 0) {
            if (formPrincipal.getcBoxSeleccionar().getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(formPrincipal, "No ha seleccionado ninguna opción de busqueda", "Nuevo Amanecer", JOptionPane.INFORMATION_MESSAGE);
                formPrincipal.getcBoxSeleccionar().grabFocus();
                return;
            }

            switch (formPrincipal.getcBoxSeleccionar().getSelectedIndex()) {   //Afiliado
                case 1: {
                    GuiAfiliadoBuscador formBuscador = new GuiAfiliadoBuscador(formPrincipal, true);
                    formBuscador.control.setDao(dao);
                    formBuscador.control.onFormShown();
                    formBuscador.control.tipoBusqueda = 'A';
                    formBuscador.control.establecerOpcionesbusqueda(formPrincipal.getTxtTextoBusqueda().getText());
                    FormUtilities.centrar2(formBuscador, formPrincipal);
                    formBuscador.setVisible(true);
                    formBuscador.dispose();
                    break;
                }
                case 2: {//Patrocinador
                    GuiPatrocinadorBuscador formBuscador = new GuiPatrocinadorBuscador(formPrincipal, true);
                    formBuscador.control.setDao(dao);
                    formBuscador.control.onFormShown();
                    formBuscador.control.establecerOpcionesbusqueda(formPrincipal.getTxtTextoBusqueda().getText());
                    FormUtilities.centrar2(formBuscador, formPrincipal);
                    formBuscador.setVisible(true);
                    formBuscador.dispose();
                    break;
                }

                case 3: {//Familia
//                    GuiAfiliadoBuscador formVerReporteIeei = new GuiAfiliadoBuscador(formPrincipal, true);
//                    formVerReporteIeei.control.setDao(dao);
//                    formVerReporteIeei.control.onFormShown();
//                    formVerReporteIeei.control.tipoBusqueda = 'P';
//                    formVerReporteIeei.control.establecerOpcionesbusqueda(formPrincipal.getTxtTextoBusqueda().getText());
//                    FormUtilities.centrar2(formVerReporteIeei, formPrincipal);
//                    formVerReporteIeei.setVisible(true);
//                    formVerReporteIeei.dispose();
//                    break;

                    GuiTarjetaBuscador formBuscador = new GuiTarjetaBuscador(formPrincipal, true);
                    formBuscador.control.setDao(dao);
                    formBuscador.control.onFormShown();
                    formBuscador.control.establecerOpcionesbusqueda(formPrincipal.getTxtTextoBusqueda().getText());
                    FormUtilities.centrar2(formBuscador, formPrincipal);
                    formBuscador.setVisible(true);
                    formBuscador.dispose();
                    break;
                }

                case 4: {//Casos
                    GuiCasosBuscador formBuscador = new GuiCasosBuscador(formPrincipal, true);
                    formBuscador.control.setDao(dao);
                    formBuscador.control.onFormShown();
                    formBuscador.control.establecerOpcionesbusqueda(formPrincipal.getTxtTextoBusqueda().getText());
                    FormUtilities.centrar2(formBuscador, formPrincipal);
                    formBuscador.setVisible(true);
                    formBuscador.dispose();
                    break;
                }


                case 5: {//Programa
                    GuiProgramaBuscador formBuscador = new GuiProgramaBuscador(formPrincipal, true);
                    formBuscador.control.setDao(dao);
                    formBuscador.control.onFormShown();
                    formBuscador.control.establecerOpcionesbusqueda(formPrincipal.getTxtTextoBusqueda().getText());
                    FormUtilities.centrar2(formBuscador, formPrincipal);
                    formBuscador.setVisible(true);
                    formBuscador.dispose();
                    break;
                }
            }
        }

        if (accion.compareTo(formPrincipal.getBtnCaso().getActionCommand()) == 0) {
        }

        if (accion.compareTo("correspondencia") == 0) {
            if (formCorrespondencia == null) {
                formCorrespondencia = new GuiCorrespondencia();

                if (!verificarPermisosFormularios(formCorrespondencia)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }

//                    formBuscadorCaso.control.setDao(dao);
//                    formBuscadorCaso.control.setConexion(conexion);
//                    formBuscadorCaso.control.setIdFormulario(usuario.getListadoInterfacesPermisos().get(indiceBuscado).getId_sistema_formulario());
//                    formBuscadorCaso.control.setUsuario(usuario);

                formCorrespondencia.control.setDao(dao);
                formCorrespondencia.control.setConexion(conexion);
                formCorrespondencia.control.setUsuario(usuario);
                formCorrespondencia.control.setIdFormulario(usuario.getListadoInterfacesPermisos().get(indiceBuscado).getId_sistema_formulario());
                
                FormUtilities.centrar2(formCorrespondencia, formPrincipal);
//                formCorrespondencia.setVisible(true);

            } else {
                if (!verificarPermisosFormularios(formCorrespondencia)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }
            }
//            formCorrespondencia.control.setUsuario(usuario);
//            formCorrespondencia.control.configuracionFormulario();
            formCorrespondencia.control.onFormShown();
            formCorrespondencia.setVisible(true);
        }
        if (accion.compareTo("patrocinadores") == 0) {
            if (formPatrocinador == null) {
                formPatrocinador = new GuiPatrocinador();
                if (!verificarPermisosFormularios(formPatrocinador)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }
                formPatrocinador.control.setDao(dao);
                FormUtilities.centrar2(formPatrocinador, formPrincipal);
                formPatrocinador.control.setConexion(conexion);
                formPatrocinador.control.setUsuario(usuario);
                
//                formPatrocinador.setVisible(true);
            } else {
                if (!verificarPermisosFormularios(formPatrocinador)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }
            }
//            formPatrocinador.control.setUsuario(usuario);
//            formPatrocinador.control.configuracionFormulario();
            formPatrocinador.control.onFormShown();
            formPatrocinador.setVisible(true);
        }

        if (accion.compareTo(formPrincipal.getBtnCaso().getActionCommand()) == 0) {
//            if(formBuscadorCaso == null){
//                    formBuscadorCaso = new GuiCasosBuscador(formPrincipal, true);
//                    if(!verificarPermisosFormularios(formBuscadorCaso))
//                    {
//                            FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
//                            return;
//                    }
//                    formBuscadorCaso = new GuiCasosBuscador(formPrincipal, true);
//                    formBuscadorCaso.control.setDao(dao);
//                    formBuscadorCaso.control.onFormShown();
//
//            }
//            else
//            {
//                if(!verificarPermisosFormularios(formBuscadorCaso))
//                {
//                        FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
//                        return;
//                }
//            }
//            formBuscadorCaso.setVisible(true);


            if (formCaso == null) {
                formCaso = new GuiCaso();
                if (!verificarPermisosFormularios(formCaso)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }
                formCaso = new GuiCaso();
                formCaso.control.setDao(dao);
                formCaso.control.setUsuario(usuario);
                

            } else {
                if (!verificarPermisosFormularios(formCaso)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }
            }
//            formCaso.control.setUsuario(usuario);
//            formCaso.control.configuracionFormulario();
            formCaso.control.onFormShown();
            formCaso.setVisible(true);

        }
        if (accion.compareTo("programas") == 0) {
            if (formGestionarProgramas == null) {
                formGestionarProgramas = new GuiGestionarProgramas();

                if (!verificarPermisosFormularios(formGestionarProgramas)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }

//                    formBuscadorCaso.control.setDao(dao);
//                    formBuscadorCaso.control.setConexion(conexion);
//                    formBuscadorCaso.control.setIdFormulario(usuario.getListadoInterfacesPermisos().get(indiceBuscado).getId_sistema_formulario());
//                    formBuscadorCaso.control.setUsuario(usuario);

                formGestionarProgramas.control.setDao(dao);
                formGestionarProgramas.control.setConexion(conexion);
                formGestionarProgramas.control.setUsuario(usuario);
                formGestionarProgramas.setLocation(0, 0);
                formGestionarProgramas.control.onFormShown();
                FormUtilities.centrar2(formGestionarProgramas, formPrincipal);
                formGestionarProgramas.setVisible(true);

            } else {
                if (!verificarPermisosFormularios(formGestionarProgramas)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }
            }
            formGestionarProgramas.setVisible(true);
        }
//
        if (accion.compareTo("AdministracionCargos") == 0) {
            if (formCargosAdministrador == null) {
                formCargosAdministrador = new GuiCargosAdministrador();

                if (!verificarPermisosFormularios(formCargosAdministrador)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }

                formCargosAdministrador.control.setDao(dao);
                formCargosAdministrador.control.setConexion(conexion);
                formCargosAdministrador.control.setIdFormulario(usuario.getListadoInterfacesPermisos().get(indiceBuscado).getId_sistema_formulario());
                formCargosAdministrador.control.setUsuario(usuario);
                
//                        formCargosAdministrador.setVisible(true);

            } else {
                if (!verificarPermisosFormularios(formCargosAdministrador)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }
            }
            formCargosAdministrador.control.setUsuario(usuario);
            formCargosAdministrador.control.onFormShow();
            formCargosAdministrador.setVisible(true);
            try {
                Usuarios otro = new Usuarios();
                otro.setCodigo_usuario(usuario.getCodigo_usuario());
                otro = (Usuarios) dao.obtenerObjeto(otro);
                if(otro!= null)
                    this.usuario = otro;
            } catch (SQLException ex) {
                Logger.getLogger(CGuiPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }



        if (accion.compareTo(formPrincipal.getjMenuItemAdministracion().getActionCommand()) == 0) {
            if (formEmpleadosUsuariosAdministracion == null) {
                formEmpleadosUsuariosAdministracion = new GuiEmpleadosUsuariosAdministracion();

                if (!verificarPermisosFormularios(formEmpleadosUsuariosAdministracion)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }

                formEmpleadosUsuariosAdministracion.control.setDao(dao);
                formEmpleadosUsuariosAdministracion.control.setConexion(conexion);
                formEmpleadosUsuariosAdministracion.control.setIdFormulario(usuario.getListadoInterfacesPermisos().get(indiceBuscado).getId_sistema_formulario());
                formEmpleadosUsuariosAdministracion.control.setUsuario(usuario);
                formEmpleadosUsuariosAdministracion.setVisible(true);
//                        formEmpleadosUsuariosAdministracion.control.onFormShow();
            } else {
                if (!verificarPermisosFormularios(formEmpleadosUsuariosAdministracion)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }
            }
            formEmpleadosUsuariosAdministracion.setVisible(true);
        }

        if (accion.compareTo("tarjeta2") == 0) {
            if (formTarjetaFamiliarCompleto == null) {
                formTarjetaFamiliarCompleto = new GuiTarjetaFamiliarCompleto();

                if (!verificarPermisosFormularios(formTarjetaFamiliarCompleto)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }

                formTarjetaFamiliarCompleto.control.setDao(dao);
                formTarjetaFamiliarCompleto.control.setConexion(conexion);
//                        formTarjetaFamiliarCompleto.control.setIdFormulario(usuario.getListadoInterfacesPermisos().get(indiceBuscado).getId_sistema_formulario());
                formTarjetaFamiliarCompleto.control.setUsuario(usuario);

                formTarjetaFamiliarCompleto.control.onFormShown();
                formTarjetaFamiliarCompleto.setVisible(true);
            } else {
                if (!verificarPermisosFormularios(formTarjetaFamiliarCompleto)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }
            }
            formTarjetaFamiliarCompleto.control.cargarMiembroFamiliar(null);
            formTarjetaFamiliarCompleto.setVisible(true);
            // GuiTarjetaFamiliarCompleto formTarjetaFamiliar= new GuiTarjetaFamiliarCompleto();
            // formTarjetaFamiliar.control.setConexion(conexion);
            // formTarjetaFamiliar.control.setDao(dao);
            // formTarjetaFamiliar.control.setUsuario(usuario);
            // formTarjetaFamiliar.control.onFormShown();
            // formTarjetaFamiliar.setVisible(true);
        }

        if (accion.compareTo("vacunasAdministrador") == 0) {
            if (formVacunasAdministrador == null) {
                formVacunasAdministrador = new GuiVacunasAdministrador();

                if (!verificarPermisosFormularios(formVacunasAdministrador)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }

                formVacunasAdministrador.control.setDao(dao);
                formVacunasAdministrador.control.setConexion(conexion);
//                        formTarjetaFamiliarCompleto.control.setIdFormulario(usuario.getListadoInterfacesPermisos().get(indiceBuscado).getId_sistema_formulario());
                formVacunasAdministrador.control.setUsuario(usuario);
                formVacunasAdministrador.control.onFormShown();
                formVacunasAdministrador.setVisible(true);

            } else {
                if (!verificarPermisosFormularios(formVacunasAdministrador)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }
            }
//            formVacunasAdministrador.control.setUsuario(usuario);
//            formVacunasAdministrador.control.configuracionFormulario();
            formVacunasAdministrador.control.cargarDatosVacuna2(null);
            formVacunasAdministrador.setVisible(true);
            // GuiVacunasAdministrador formTarjetaFamiliar= new GuiVacunasAdministrador();
            // formTarjetaFamiliar.control.setConexion(conexion);
            // formTarjetaFamiliar.control.setDao(dao);
            // formTarjetaFamiliar.control.setUsuario(usuario);
            // formTarjetaFamiliar.control.onFormShown();
            // formTarjetaFamiliar.setVisible(true);
        }

        if (accion.compareTo("tarjeta") == 0) {
            if (formTarjeta == null) {
                formTarjeta = new GuiTarjeta();

                if (!verificarPermisosFormularios(formTarjeta)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }

//                    formBuscadorCaso.control.setDao(dao);
//                    formBuscadorCaso.control.setConexion(conexion);
//                    formBuscadorCaso.control.setIdFormulario(usuario.getListadoInterfacesPermisos().get(indiceBuscado).getId_sistema_formulario());
//                    formBuscadorCaso.control.setUsuario(usuario);

                formTarjeta.control.setDao(dao);
                formTarjeta.control.setNumeroProyecto(NumeroProyecto);
                formTarjeta.control.setUsuario(usuario);
                formTarjeta.setLocation(0, 0);
                FormUtilities.centrar2(formTarjeta, formPrincipal);
                
//                formTarjeta.setVisible(true);

            } else {
                if (!verificarPermisosFormularios(formTarjeta)) {
                    FormUtilities.showMessage("Formulario no Autorizado a su cuenta de usuario", "No Tiene permisos suficientes para poder acceder a este formulario", formPrincipal);
                    return;
                }
            }
            formTarjeta.control.onFormShown();
            formTarjeta.setVisible(true);
        }
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public void setFechaHoraServidor(Date fechaHoraServidor) {
        this.fechaHoraServidor = fechaHoraServidor;
    }

    public void onFormShwon() {

        switch (usuario.getCodigo_tipo_usuario().charAt(0)) {//'C'->COORDINADOR DE AREA,'F'->FACILITADOR DE PROGRAMAS,'R'-> RESPONSABLE DE PATROCINIO,'P'->PSICOLOGIA,'A'->ADMINISTRADOR
            case 'C':
            case 'P':
                formPrincipal.getPnlOpcionesBusqueda().setVisible(true);
                formPrincipal.getPnlVer().setVisible(true);
                formPrincipal.getPnlVerIEEI().setVisible(true);
                formPrincipal.getPnlGestionar().setVisible(false);
                formPrincipal.getPnlOpcionesSuperiorToolBar().setVisible(false);

                formPrincipal.getMenuArchivo().setVisible(true);
                formPrincipal.getMenuGestionar().setVisible(false);
                formPrincipal.getMenuReportes().setVisible(true);
                break;
            case 'F':
            case 'A':
                formPrincipal.getPnlOpcionesBusqueda().setVisible(true);
                formPrincipal.getPnlVer().setVisible(true);
                formPrincipal.getPnlVerIEEI().setVisible(true);
                formPrincipal.getPnlGestionar().setVisible(true);
                formPrincipal.getPnlGestionar().setVisible(true);

                formPrincipal.getMenuArchivo().setVisible(true);
                formPrincipal.getMenuGestionar().setVisible(true);
                formPrincipal.getMenuReportes().setVisible(true);
                break;
            case 'R':
                formPrincipal.getPnlOpcionesBusqueda().setVisible(true);
                formPrincipal.getPnlVer().setVisible(true);
                formPrincipal.getPnlVerIEEI().setVisible(true);
                formPrincipal.getPnlGestionar().setVisible(true);
                formPrincipal.getBtnTarjeta().setVisible(true);
                formPrincipal.getBtnPatrocinadores().setVisible(false);
                formPrincipal.getBtnCaso().setVisible(false);
                formPrincipal.getBtnCorrespondencia().setVisible(true);
                formPrincipal.getBtnProgramas().setVisible(true);


                formPrincipal.getPnlGestionar().setVisible(true);

                formPrincipal.getMenuArchivo().setVisible(true);
                formPrincipal.getMenuGestionar().setVisible(true);//readecuar

                formPrincipal.getjMenuItemTarjetaFamiliar().setVisible(true);
                formPrincipal.getjMenuItemPatrocinadores().setVisible(false);
                formPrincipal.getjMenuItemCaso().setVisible(false);
                formPrincipal.getjMenuItemCorrespondencias().setVisible(true);
                formPrincipal.getjMenuItemProgramas().setVisible(true);
                formPrincipal.getjMenuItemUsuarioSistema().setVisible(false);

                formPrincipal.getMenuReportes().setVisible(true);

                formPrincipal.getjBTarjetaFamiliar().setVisible(true);
                formPrincipal.getjBPatrocinador().setVisible(false);
                formPrincipal.getjBCaso().setVisible(false);
                formPrincipal.getjBCorrespondencia().setVisible(true);
                formPrincipal.getjBProgramas().setVisible(true);
                formPrincipal.getjBIee().setVisible(true);
                formPrincipal.getjBAyuda().setVisible(true);
                break;
        }

    }
}
