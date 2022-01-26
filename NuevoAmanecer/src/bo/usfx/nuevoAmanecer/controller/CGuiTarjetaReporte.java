/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.utils.FormUtilities;
import bo.usfx.utils.GeneraReport;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;
import view.GuiTarjetaReporte;

/**
 *
 * @author Luis Molina
 */
public class CGuiTarjetaReporte implements ActionListener{

    private GuiTarjetaReporte formReporte;
    private Connection conexion;
    private CommonDao dao;
    private Usuarios usuario;
    GeneraReport gr = new GeneraReport();

    public CGuiTarjetaReporte(GuiTarjetaReporte formReporte)
    {
        this.formReporte = formReporte;
    }

    public void actionPerformed(ActionEvent event) {
        String accion = event.getActionCommand();
        this.formReporte.getCboxPorMaterialVivienda().setEnabled(formReporte.getRbtnPorMaterialVivienda().getActionCommand().compareTo(accion) == 0);
        this.formReporte.getCboxPorTipoVivienda().setEnabled(formReporte.getRbtnPorTipoVivienda().getActionCommand().compareTo(accion) == 0);
        this.formReporte.getCboxPorVivienda().setEnabled(formReporte.getRbtnPorVivienda().getActionCommand().compareTo(accion) == 0);

        this.formReporte.getCheckPorMaterialVivienda().setEnabled(formReporte.getRbtnPorMaterialVivienda().getActionCommand().compareTo(accion) == 0);
        this.formReporte.getCheckPorTipoVivienda().setEnabled(formReporte.getRbtnPorTipoVivienda().getActionCommand().compareTo(accion) == 0);
        this.formReporte.getCheckPorVivienda().setEnabled(formReporte.getRbtnPorVivienda().getActionCommand().compareTo(accion) == 0);


        if(accion.compareTo("aceptar") == 0)
        {
            String path = FormUtilities.obtenerRutaLocalReportes() + "view/";
//            if(formReporte.getRbtnConAgua().isSelected()){
//                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaAgua.jasper");
//                gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
//                gr.fillReport(conexion);
//                gr.previewReport();
//            }

             if (formReporte.getRbtnAguaTodas().isSelected()) {
                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaAgua.jasper");
                gr.setParameters("parametro_filtro", null);
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if (formReporte.getRbtnCocinaTodas().isSelected()) {
                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaCocina.jasper");
                gr.setParameters("parametro_filtro", null);
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if (formReporte.getRbtnEDATodas().isSelected()) {
                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaEDA.jasper");
                gr.setParameters("parametro_filtro", null);
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if (formReporte.getRbtnExcretasTodas().isSelected()) {
                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaExcretas.jasper");
                gr.setParameters("parametro_filtro", null);
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if (formReporte.getRbtnIRATodas().isSelected()) {
                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaIRA.jasper");
                gr.setParameters("parametro_filtro", null);
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }


            if (formReporte.getRbtnConAgua().isSelected()) {
                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaAgua.jasper");
                gr.setParameters("parametro_filtro", "TRUE");
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if (formReporte.getRbtnConCocina().isSelected()) {
                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaCocina.jasper");
                gr.setParameters("parametro_filtro", "TRUE");
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if (formReporte.getRbtnConEDA().isSelected()) {
                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaEDA.jasper");
                gr.setParameters("parametro_filtro", "TRUE");
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if (formReporte.getRbtnConExcretas().isSelected()) {
                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaExcretas.jasper");
                gr.setParameters("parametro_filtro", "TRUE");
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if (formReporte.getRbtnConIRA().isSelected()) {
                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaIRA.jasper");
                gr.setParameters("parametro_filtro", "TRUE");
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if (formReporte.getRbtnPorMaterialVivienda().isSelected()) {
                if(formReporte.getCboxPorMaterialVivienda().getSelectedIndex() <= 0 && !formReporte.getCheckPorMaterialVivienda().isSelected())
                {
                    JOptionPane.showMessageDialog(formReporte, "No ha Seleccionado ningun filtro", "Reporte", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaMaterialVivienda.jasper");
                gr.setParameters("parametro_filtro", formReporte.getCheckPorMaterialVivienda().isSelected()
                        ? null : formReporte.getCboxPorMaterialVivienda().getSelectedItem().toString());
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if (formReporte.getRbtnPorTipoVivienda().isSelected()) {
                if(formReporte.getCboxPorTipoVivienda().getSelectedIndex() <= 0 && !formReporte.getCheckPorTipoVivienda().isSelected())
                {
                    JOptionPane.showMessageDialog(formReporte, "No ha Seleccionado ningun filtro", "Reporte", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaTipoVivienda.jasper");
                gr.setParameters("parametro_filtro", formReporte.getCheckPorTipoVivienda().isSelected()
                        ? null : formReporte.getCboxPorTipoVivienda().getSelectedItem().toString());
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if (formReporte.getRbtnPorVivienda().isSelected()) {
                if(formReporte.getCboxPorVivienda().getSelectedIndex() <= 0 && !formReporte.getCheckPorVivienda().isSelected())
                {
                    JOptionPane.showMessageDialog(formReporte, "No ha Seleccionado ningun filtro", "Reporte", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaVivienda.jasper");
                gr.setParameters("parametro_filtro", formReporte.getCheckPorVivienda().isSelected()
                        ? null : formReporte.getCboxPorVivienda().getSelectedItem().toString());
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if (formReporte.getRbtnSinAgua().isSelected()) {
                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaAgua.jasper");
                gr.setParameters("parametro_filtro", "FALSE");
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if (formReporte.getRbtnSinCocina().isSelected()) {
                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaCocina.jasper");
                gr.setParameters("parametro_filtro", "FALSE");
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if (formReporte.getRbtnSinEDA().isSelected()) {
                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaEDA.jasper");
                gr.setParameters("parametro_filtro", "FALSE");
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if (formReporte.getRbtnSinExcretas().isSelected()) {
                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaExcretas.jasper");
                gr.setParameters("parametro_filtro", "FALSE");
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }

            if (formReporte.getRbtnSinIRA().isSelected()) {
                gr.loadReportJasper(path + "reporteTarjetaSaludPublicaIRA.jasper");
                gr.setParameters("parametro_filtro", "FALSE");
                gr.setParameters("RutaLogo", path + "cristianchildrenazul.jpg");
                gr.fillReport(conexion);
                gr.previewReport();
            }
            this.formReporte.setVisible(false);
        }

        if(accion.compareTo("cancelar") == 0)
        {
            this.formReporte.setVisible(false);
        }
    }

    public void onFormShown()
    {
        this.formReporte.getCboxPorMaterialVivienda().setEnabled(false);
        this.formReporte.getCboxPorTipoVivienda().setEnabled(false);
        this.formReporte.getCboxPorVivienda().setEnabled(false);
        this.formReporte.getCheckPorMaterialVivienda().setEnabled(false);
        this.formReporte.getCheckPorTipoVivienda().setEnabled(false);
        this.formReporte.getCheckPorVivienda().setEnabled(false);
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
