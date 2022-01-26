/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Afiliado;
import bo.usfx.nuevoAmanecer.model.domain.Familia;
import bo.usfx.nuevoAmanecer.model.domain.Familiar;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormularios;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormulariosPermisosUsuarios;
import bo.usfx.nuevoAmanecer.model.domain.Tarjeta;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.utils.ComparadorIdFormulario;
import bo.usfx.utils.FormUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.GuiAfiliadoBuscador;
import view.GuiEccd;
import view.GuiFamilia;
import view.GuiGeneralidades;
import view.GuiObservaciones;
import view.GuiSaludPublica;
import view.GuiTarjeta;
import view.GuiTarjetaBuscador;
import view.GuiVacuna;
import view.GuiVerTarjetaFamiliar;


public class CGuiTarjeta implements ActionListener{
    GuiTarjeta formTarjeta;
    private CommonDao dao;
    private int NumeroProyecto  = 1;
    private Usuarios usuario;
    private Connection conexion;
    private int idFormulario;

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    

    public void setNumeroProyecto(int NumeroProyecto) {
        this.NumeroProyecto = NumeroProyecto;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    public CGuiTarjeta(GuiTarjeta formTarjeta) {
        this.formTarjeta = formTarjeta;
        this.formTarjeta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }

    public void onFormShown()
    {
        this.formTarjeta.getBtnECCD().setVisible(false);
        this.formTarjeta.getBtnFamilia().setVisible(false);
        this.formTarjeta.getBtnGeneralidades().setVisible(false);
        this.formTarjeta.getBtnObservaciones().setVisible(false);
        this.formTarjeta.getBtnSaludPublica().setVisible(false);
        this.formTarjeta.getBtnVacuna().setVisible(false);


        if(usuario.getCodigo_tipo_usuario().compareTo("R") == 0)
        {
            formTarjeta.getBtnRegistrar().setEnabled(true);
            formTarjeta.getBtnModificar().setEnabled(true);
            formTarjeta.getBtnEliminar().setEnabled(true);

            formTarjeta.getBtnVerTarjetaFamiliar().setEnabled(true);
            formTarjeta.getBtnDarBajaAfiliado().setEnabled(false);
            formTarjeta.getBtnDarBajaFamiliar().setEnabled(false);
            formTarjeta.getBtnDarBajaTarjetaFamiliar().setEnabled(false);

            formTarjeta.getBtnGeneralidades().setEnabled(false);
            formTarjeta.getBtnFamilia().setEnabled(false);
            formTarjeta.getBtnECCD().setEnabled(true);
            formTarjeta.getBtnVacuna().setEnabled(true);
            formTarjeta.getBtnSaludPublica().setEnabled(true);
            formTarjeta.getBtnObservaciones().setEnabled(true);
        }

        configuracionFormulario();

    }
    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();
       /* if(accion.compareTo(formTarjeta.getBtnAceptar().getActionCommand())== 0)
        {

        }
        if(accion.compareTo(formTarjeta.getBtnBuscar().getActionCommand())== 0)
        {

        }*/
        if(accion.compareTo(formTarjeta.getBtnECCD().getActionCommand())== 0)
        {
            GuiEccd formEccd = new GuiEccd();
            formEccd.control.setDao(dao);            
            formEccd.control.onFormShown();
            FormUtilities.centrar2(formEccd, formTarjeta);
            formEccd.setVisible(true);
        }if(accion.compareTo(formTarjeta.getBtnFamilia().getActionCommand())== 0)
        {
            GuiFamilia familia=new GuiFamilia();
            familia.control.setDao(dao);
            familia.control.setNumeroProyecto(NumeroProyecto);
            familia.control.onFormShown();            
            FormUtilities.centrar2(familia, formTarjeta);
            familia.setVisible(true);
        }
        if(accion.compareTo(formTarjeta.getBtnGeneralidades().getActionCommand())== 0)
        {
            GuiGeneralidades generalidades=new GuiGeneralidades();
            generalidades.control.setDao(dao);
            generalidades.control.setNumeroProyecto(NumeroProyecto);            
            generalidades.control.onFormShow();
            FormUtilities.centrar2(generalidades, formTarjeta);
            generalidades.setVisible(true);
        }
        if(accion.compareTo(formTarjeta.getBtnObservaciones().getActionCommand())== 0)
        {
            GuiObservaciones formObservaciones = new GuiObservaciones();
            formObservaciones.control.setDao(dao);
            formObservaciones.control.onFormShown();
            FormUtilities.centrar2(formObservaciones, formTarjeta);
            formObservaciones.setVisible(true);
        }
        if(accion.compareTo(formTarjeta.getBtnSaludPublica().getActionCommand())== 0)
        {
            GuiSaludPublica formSaludPublica = new GuiSaludPublica();
            formSaludPublica.control.setDao(dao);
            formSaludPublica.control.onFormShown();
            FormUtilities.centrar2(formSaludPublica, formTarjeta);
            formSaludPublica.setVisible(true);
        }
        if(accion.compareTo(formTarjeta.getBtnSaludPublica().getActionCommand())== 0)
        {

        }
        if(accion.compareTo(formTarjeta.getBtnVacuna().getActionCommand())== 0)
        {
            GuiVacuna formVacuna = new GuiVacuna();
            formVacuna.control.setDao(dao);
            formVacuna.control.onFormShown();
            FormUtilities.centrar2(formVacuna, formTarjeta);
            formVacuna.setVisible(true);
        }
        


        if(accion.compareTo(formTarjeta.getBtnDarBajaAfiliado().getActionCommand())== 0){
            formTarjeta.getBtnGeneralidades().setVisible(false);
            formTarjeta.getBtnECCD().setVisible(false);
            formTarjeta.getBtnFamilia().setVisible(false);
            formTarjeta.getBtnObservaciones().setVisible(false);
            formTarjeta.getBtnSaludPublica().setVisible(false);
            formTarjeta.getBtnVacuna().setVisible(false);

            //mostrar buscador y preguntar la baja completa
            GuiAfiliadoBuscador formBuscador = new GuiAfiliadoBuscador(formTarjeta, true);
            formBuscador.control.setDao(dao);
            formBuscador.control.onFormShown();
            formBuscador.control.tipoBusqueda = 'A';
            FormUtilities.centrar2(formBuscador, formTarjeta);
            formBuscador.setVisible(true);
            Afiliado afiliado = (Afiliado)formBuscador.control.getPatrocinadorSeleccionado();
            formBuscador.dispose();
            if(afiliado != null)
            {
                if(JOptionPane.showConfirmDialog(formTarjeta, "¿Se encuentra seguro de Dar de Baja a " + afiliado.getNombreCompleto() +" ?", "Dar de Baja ", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    afiliado.setCodigo_estado_familia("B");
                    try {
                        dao.edit(afiliado);
                        JOptionPane.showMessageDialog(formBuscador, "Operación realizada correctamente");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(formBuscador, "No se pudo completar la operación debido a la siguiente excepción " + ex.getMessage());
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(formTarjeta, "No ha Seleccionado ningún Afiliado");
                
            }
        }

        if(accion.compareTo(formTarjeta.getBtnDarBajaFamiliar().getActionCommand())== 0) {
            formTarjeta.getBtnGeneralidades().setVisible(false);
            formTarjeta.getBtnECCD().setVisible(false);
            formTarjeta.getBtnFamilia().setVisible(false);
            formTarjeta.getBtnObservaciones().setVisible(false);
            formTarjeta.getBtnSaludPublica().setVisible(false);
            formTarjeta.getBtnVacuna().setVisible(false);

            //mostrar buscador y preguntar la baja completa
            GuiAfiliadoBuscador formBuscador = new GuiAfiliadoBuscador(formTarjeta, true);
            formBuscador.control.setDao(dao);
            formBuscador.control.onFormShown();
            formBuscador.control.tipoBusqueda = 'F';
            FormUtilities.centrar2(formBuscador, formTarjeta);
            formBuscador.setVisible(true);
            Familiar familiar = (Familiar)formBuscador.control.getPatrocinadorSeleccionado();
            formBuscador.dispose();
            if(familiar != null)
            {
                if(JOptionPane.showConfirmDialog(formTarjeta, "¿Se encuentra seguro de Dar de Baja a " + familiar.getNombreCompleto() +" ?", "Dar de Baja ", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    familiar.setCodigo_estado_familia("B");
                    try {
                        dao.edit(familiar);
                        JOptionPane.showMessageDialog(formBuscador, "Operación realizada correctamente");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(formBuscador, "No se pudo completar la operación debido a la siguiente excepción " + ex.getMessage());
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(formTarjeta, "No ha Seleccionado ningún Familiar");
            }
        }

        if(accion.compareTo(formTarjeta.getBtnDarBajaTarjetaFamiliar().getActionCommand())== 0) {
            formTarjeta.getBtnGeneralidades().setVisible(false);
            formTarjeta.getBtnECCD().setVisible(false);
            formTarjeta.getBtnFamilia().setVisible(false);
            formTarjeta.getBtnObservaciones().setVisible(false);
            formTarjeta.getBtnSaludPublica().setVisible(false);
            formTarjeta.getBtnVacuna().setVisible(false);

            //mostrar buscador y pregutar la baja completa
            GuiTarjetaBuscador formBuscador = new GuiTarjetaBuscador(formTarjeta, true);
            formBuscador.control.setDao(dao);
            formBuscador.control.onFormShown();            
            FormUtilities.centrar2(formBuscador, formTarjeta);
            formBuscador.setVisible(true);
            Tarjeta tarjeta = (Tarjeta)formBuscador.control.getTarjetaSeleccionada();
            formBuscador.dispose();
            if(tarjeta != null)
            {
                if(JOptionPane.showConfirmDialog(formTarjeta, "¿Se encuentra seguro de Dar de Baja a la Tarjeta Familiar  " + tarjeta.getNumero_familia() +" ?", "Dar de Baja ", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    try {
                        
                        tarjeta.setCodigo_estado_tarjeta("B");
                        tarjeta = (Tarjeta)dao.obtenerObjeto(tarjeta);
                        dao.edit(tarjeta);
                        JOptionPane.showMessageDialog(formBuscador, "Operación realizada correctamente, Baja de la TARJETA nro " + tarjeta.getNumero_familia());
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(formBuscador, "No se pudo completar la operación debido a la siguiente excepción " + ex.getMessage());
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(formTarjeta, "No ha Seleccionado ningún miembro de una Familia");
            }

        }

        if(accion.compareTo(formTarjeta.getBtnEliminar().getActionCommand())== 0) {
            formTarjeta.getBtnGeneralidades().setVisible(true);
            formTarjeta.getBtnECCD().setVisible(true);
            formTarjeta.getBtnFamilia().setVisible(false);
            formTarjeta.getBtnObservaciones().setVisible(true);
            formTarjeta.getBtnSaludPublica().setVisible(true);
            formTarjeta.getBtnVacuna().setVisible(true);
        }

        if(accion.compareTo(formTarjeta.getBtnModificar().getActionCommand())== 0) {
            formTarjeta.getBtnECCD().setVisible(true);
            formTarjeta.getBtnGeneralidades().setVisible(true);
            formTarjeta.getBtnFamilia().setVisible(true);
            formTarjeta.getBtnObservaciones().setVisible(true);
            formTarjeta.getBtnSaludPublica().setVisible(true);
            formTarjeta.getBtnVacuna().setVisible(true);
        }

        if(accion.compareTo(formTarjeta.getBtnRegistrar().getActionCommand())== 0) {
            formTarjeta.getBtnECCD().setVisible(true);
            formTarjeta.getBtnGeneralidades().setVisible(true);
            formTarjeta.getBtnFamilia().setVisible(true);
            formTarjeta.getBtnObservaciones().setVisible(true);
            formTarjeta.getBtnSaludPublica().setVisible(true);
            formTarjeta.getBtnVacuna().setVisible(true);
        }

        if(accion.compareTo(formTarjeta.getBtnVerTarjetaFamiliar().getActionCommand())== 0) {
            GuiVerTarjetaFamiliar formVerTarjeta = new GuiVerTarjetaFamiliar();
            formVerTarjeta.control.setDao(dao);
            formVerTarjeta.control.onFormShown();
            FormUtilities.centrar2(formVerTarjeta, formTarjeta);
            formVerTarjeta.setVisible(true);
        }

    }

    public void configuracionFormulario() {
        if (usuario != null) {
            int indice = -1;
            SistemaFormularios formulario = new SistemaFormularios();
            formulario.setNombre_formulario(FormUtilities.getClassName(formTarjeta.getClass()));
            SistemaFormulariosPermisosUsuarios permisosTarjeta = new SistemaFormulariosPermisosUsuarios();
            permisosTarjeta.setSistemaFormulario(formulario);

            indice = Collections.binarySearch(usuario.getListadoInterfacesPermisos(), permisosTarjeta, new ComparadorIdFormulario());
            if (indice >= 0) {
                permisosTarjeta = usuario.getListadoInterfacesPermisos().get(indice);

                formTarjeta.getBtnDarBajaAfiliado().setVisible(permisosTarjeta.isPermitir_anular());
                formTarjeta.getBtnDarBajaFamiliar().setVisible(permisosTarjeta.isPermitir_anular());
                formTarjeta.getBtnDarBajaTarjetaFamiliar().setVisible(permisosTarjeta.isPermitir_anular());
                formTarjeta.getBtnEliminar().setVisible(permisosTarjeta.isPermitir_anular());

                formTarjeta.getBtnModificar().setVisible(permisosTarjeta.isPermitir_editar());

                formTarjeta.getBtnRegistrar().setVisible(permisosTarjeta.isPermitir_insertar());

                
            } else {
                System.out.println("no tiene permisos, nombre de la Clase " + formulario.getNombre_formulario());
            }
        }
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }



}
