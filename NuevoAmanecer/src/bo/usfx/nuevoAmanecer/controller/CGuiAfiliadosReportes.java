/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.ActividadEducativa;
import bo.usfx.nuevoAmanecer.model.domain.Afiliado;
import bo.usfx.nuevoAmanecer.model.domain.Ocupaciones;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.nuevoAmanecer.model.domain.Vacunas2;
import bo.usfx.utils.FormUtilities;
import bo.usfx.utils.GeneraReport;
import bo.usfx.utils.ObjetoCodigoDescripcion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.GuiAfiliadoBuscador;
import view.GuiAfiliadosReportes;

/**
 *
 * @author Luis Molina
 */
public class CGuiAfiliadosReportes implements ActionListener {
    GuiAfiliadosReportes formReporte;
    private CommonDao dao;
    private Connection conexion;
    private Usuarios usuario;
    private List<Vacunas2> listadoVacunas;
    private List<ActividadEducativa> listadoActividadesEducativas;
    private List<Ocupaciones> listadoOcupaciones;
    private List<ObjetoCodigoDescripcion> listadoSeven;
    private GeneraReport gr;
    Afiliado afiliadoSeleccioando = null;

    public CGuiAfiliadosReportes(GuiAfiliadosReportes formReporte)
    {
        this.formReporte = formReporte;
        gr = new GeneraReport();
    }

    public void actionPerformed(ActionEvent event) {
        String accion = event.getActionCommand();

        formReporte.getBtnBuscarAfiliado().setEnabled(accion.compareTo("habilitarBtnBuscarAfiliado") == 0);
        formReporte.getCboxVacunas().setEnabled(accion.compareTo("habilitarCBoxVacunas") == 0);
        formReporte.getCheckVacunas().setEnabled(accion.compareTo("habilitarCBoxVacunas") == 0);
        formReporte.getCboxSeven().setEnabled(accion.compareTo("habilitarCBoxSeven") == 0);
        formReporte.getCheckSeven().setEnabled(accion.compareTo("habilitarCBoxSeven") == 0);
        formReporte.getCboxActividadEducativa().setEnabled(accion.compareTo("habilitarCBoxActividadEducativa") == 0);
        formReporte.getCheckActividadEducativa().setEnabled(accion.compareTo("habilitarCBoxActividadEducativa") == 0);
        formReporte.getCboxOcupaciones().setEnabled(accion.compareTo("habilitarCBoxOcupaciones") == 0);
        formReporte.getCheckOcupacion().setEnabled(accion.compareTo("habilitarCBoxOcupaciones") == 0);

//        if(accion.compareTo("habilitarBtnBuscarAfiliado") == 0)
//        {
//            formReporte.getBtnBuscarAfiliado().setEnabled(formReporte.getRbtnSeguimientoVacunaAfiliado().isSelected());
//        }
////        else
////        {
////            formReporte.getBtnBuscarAfiliado().setEnabled(false);
////        }
//        if(accion.compareTo("habilitarCBoxVacunas") == 0)
//        {
//            formReporte.getCboxVacunas().setEnabled(formReporte.getRbtnSeguimientoUnaVacuna().isSelected());
//            formReporte.getCheckVacunas().setEnabled(formReporte.getRbtnSeguimientoUnaVacuna().isSelected());
//        }
////        else{
////            formReporte.getCboxVacunas().setEnabled(formReporte.getRbtnSeguimientoUnaVacuna().isSelected());
////            formReporte.getCheckVacunas().setEnabled(formReporte.getRbtnSeguimientoUnaVacuna().isSelected());
////        }
//        if(accion.compareTo("habilitarCBoxSeven") == 0)
//        {
//            formReporte.getCboxSeven().setEnabled(formReporte.getRbtnEstadoDesnutricion().isSelected());
//            formReporte.getCheckSeven().setEnabled(formReporte.getRbtnEstadoDesnutricion().isSelected());
//        }//
//        if(accion.compareTo("habilitarCBoxActividadEducativa") == 0)
//        {
//            formReporte.getCboxActividadEducativa().setEnabled(formReporte.getRbtnActividadEducativa().isSelected());
//            formReporte.getCheckActividadEducativa().setEnabled(formReporte.getRbtnActividadEducativa().isSelected());
//        }
//        if(accion.compareTo("habilitarCBoxOcupaciones") == 0)
//        {
//            formReporte.getCboxOcupaciones().setEnabled(formReporte.getRbtnOcupaciones().isSelected());
//            formReporte.getCheckOcupacion().setEnabled(formReporte.getRbtnOcupaciones().isSelected());
//        }

        if(accion.compareTo(formReporte.getBtnBuscarAfiliado().getActionCommand()) == 0)
        {
            GuiAfiliadoBuscador formBuscador = new GuiAfiliadoBuscador(this.formReporte, true);
            formBuscador.control.tipoBusqueda = 'A';
            formBuscador.control.setDao(dao);
            formBuscador.control.onFormShown();
            formBuscador.setVisible(true);
            afiliadoSeleccioando = (Afiliado) formBuscador.control.getPatrocinadorSeleccionado();

        }

        if(accion.compareTo("cancelar") == 0)
        {
            formReporte.setVisible(false);
            formReporte.dispose();
        }

        if(accion.compareTo("aceptar") == 0)
        {
            String path = FormUtilities.obtenerRutaLocalReportes() + "view/";
            if(formReporte.getRbtnActividadEducativa().isSelected()){
                
                gr.loadReportJasper(path + "reporteTablaActividadEducativa.jasper");
                gr.setParameters("parametro_filtro",formReporte.getCheckActividadEducativa().isSelected() ? null : ((ActividadEducativa)formReporte.getCboxActividadEducativa().getSelectedItem()).getCodigo_actividad_educactiva());
                gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if(formReporte.getRbtnAfiliadoConCI().isSelected()){
                gr.loadReportJasper(path + "reporteTablaCi.jasper");
                gr.setParameters("TituloDocumento", "Afiliados con Carnet de Identidad");
                gr.setParameters("paramnetro_opcion", 1);
                gr.setParameters("parametro_filtro", true);
                gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if(formReporte.getRbtnAfiliadoConCertificado().isSelected()){
                gr.loadReportJasper(path + "reporteTablaCi.jasper");
                gr.setParameters("TituloDocumento", "Afiliados con Certificado de Nacimiento");
                gr.setParameters("paramnetro_opcion", 3);
                gr.setParameters("parametro_filtro", true);
                gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if(formReporte.getRbtnAfiliadoSinCI().isSelected()){
                gr.loadReportJasper(path + "reporteTablaCi.jasper");
                gr.setParameters("TituloDocumento", "Afiliados si Carnet de Identidad");
                gr.setParameters("paramnetro_opcion", 1);
                gr.setParameters("parametro_filtro", false);
                gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if(formReporte.getRbtnAfiliadoSinCertificado().isSelected()){
                gr.loadReportJasper(path + "reporteTablaCi.jasper");
                gr.setParameters("TituloDocumento", "Afiliados sin Certificado de Nacimiento");
                gr.setParameters("paramnetro_opcion", 3);
                gr.setParameters("parametro_filtro", false);
                gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if(formReporte.getRbtnConAlfabetizacion().isSelected()){
                gr.loadReportJasper(path + "reporteTablaCi.jasper");
                gr.setParameters("TituloDocumento", "Afiliados Alfabetizados");
                gr.setParameters("paramnetro_opcion", 2);
                gr.setParameters("parametro_filtro", true);
                gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if(formReporte.getRbtnEstadoDesnutricion().isSelected()){
                gr.loadReportJasper(path + "reporteTablaDesnutricion.jasper");
                gr.setParameters("parametro_filtro", formReporte.getCheckSeven().isSelected() ? null
                        :((ObjetoCodigoDescripcion) formReporte.getCboxSeven().getSelectedItem()).getCodigoObjeto());
                gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if(formReporte.getRbtnOcupaciones().isSelected()){
                gr.loadReportJasper(path + "reporteTablaOcupacion.jasper");
                gr.setParameters("parametro_filtro", formReporte.getCheckOcupacion().isSelected() ? null
                        :((Ocupaciones) formReporte.getCboxOcupaciones().getSelectedItem()).getCodigo_ocupacion());
                gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if(formReporte.getRbtnSeguimientoUnaVacuna().isSelected()){
                gr.loadReportJasper(path + "reporteTablaVacunasAfiliados2.jasper");
                gr.setParameters("parametro_codigo_vacuna", formReporte.getCheckVacunas().isSelected() ? null
                        :((Vacunas2) formReporte.getCboxVacunas().getSelectedItem()).getCodigo_vacuna2());
                gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                gr.setParameters("TituloDocumento", "Vacunas  de AFiliados");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if(formReporte.getRbtnSeguimientoVacunaAfiliado().isSelected()){
                if(afiliadoSeleccioando == null)
                {
                    JOptionPane.showMessageDialog(formReporte, "Aun no ha seleccionado ningun afiliado", "Reportes", JOptionPane.ERROR_MESSAGE);
                    formReporte.getBtnBuscarAfiliado().grabFocus();
                    return;
                }

                gr.loadReportJasper(path + "reporteTablaVacunasAfiliados1.jasper");
                gr.setParameters("paramnetro_numero_caso", afiliadoSeleccioando.getNumero_caso());
                gr.setParameters("parametro_codigo_vacuna", null);
                gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                gr.setParameters("TituloDocumento", "Vacunas  de AFiliado");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if(formReporte.getRbtnSeguimientoVacunas().isSelected()){
                gr.loadReportJasper(path + "reporteTablaVacunasAfiliados1.jasper");

                
                gr.setParameters("paramnetro_numero_caso", null);
                gr.setParameters("parametro_codigo_vacuna", null);
                gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                gr.setParameters("TituloDocumento", "Vacunas  de AFiliado");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if(formReporte.getRbtnSinAlfabetizacion().isSelected()){
                gr.loadReportJasper(path + "reporteTablaCi.jasper");
                gr.setParameters("TituloDocumento", "Afiliados sin Alfabetizados");
                gr.setParameters("paramnetro_opcion", 2);
                gr.setParameters("parametro_filtro", false);
                gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }
            formReporte.setVisible(false);
        }

    }

    public void onFormShown()
    {
        formReporte.getBtnBuscarAfiliado().setEnabled(false);
        formReporte.getCboxActividadEducativa().setEnabled(false);
        formReporte.getCboxOcupaciones().setEnabled(false);
        formReporte.getCboxSeven().setEnabled(false);
        formReporte.getCboxVacunas().setEnabled(false);
        formReporte.getCheckActividadEducativa().setEnabled(false);
        formReporte.getCheckOcupacion().setEnabled(false);
        formReporte.getCheckSeven().setEnabled(false);
        formReporte.getCheckVacunas().setEnabled(false);
        try {
            formReporte.getCboxActividadEducativa().removeAllItems();
            listadoActividadesEducativas = dao.getObjects("ActividadEducativa");
            for (ActividadEducativa actividadEducativa : listadoActividadesEducativas) {
                formReporte.getCboxActividadEducativa().addItem(actividadEducativa);
            }

            formReporte.getCboxOcupaciones().removeAllItems();
            listadoOcupaciones = dao.getObjects("Ocupaciones");
            for (Ocupaciones ocupaciones : listadoOcupaciones) {
                formReporte.getCboxOcupaciones().addItem(ocupaciones);
            }

            formReporte.getCboxVacunas().removeAllItems();
            listadoVacunas = dao.getObjects("Vacunas2", new Vacunas2());
            for (Vacunas2 vacunas2 : listadoVacunas) {
                formReporte.getCboxVacunas().addItem(vacunas2);
            }

            formReporte.getCboxSeven().removeAllItems();
            formReporte.getCboxSeven().addItem(new ObjetoCodigoDescripcion("NS", "NUTRICION SUPERIOR"));
            formReporte.getCboxSeven().addItem(new ObjetoCodigoDescripcion("N", "NUTRICION NORMAL"));
            formReporte.getCboxSeven().addItem(new ObjetoCodigoDescripcion("DL", "DESNUTRICION LEVE 1ºGRADO"));
            formReporte.getCboxSeven().addItem(new ObjetoCodigoDescripcion("DM", "DESNUTRICION MODERADA 2ºGRADO"));
            formReporte.getCboxSeven().addItem(new ObjetoCodigoDescripcion("DS", "DESNUTRICION SEVERA 3ºGRADO"));

        } catch (SQLException ex) {
//            Logger.getLogger(CGuiAfiliadosReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    

}
