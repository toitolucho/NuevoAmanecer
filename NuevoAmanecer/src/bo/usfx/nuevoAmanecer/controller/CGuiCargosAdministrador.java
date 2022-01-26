/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Cargo;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormularios;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormulariosPermisosCargos;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.utils.ModeloCargos;
import bo.usfx.utils.ModeloSistemaFormulariosParaPermisos;
import bo.usfx.utils.ModeloSistemaFormulariosPermisosCargos;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.CellEditor;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sun.tools.jar.resources.jar;
import view.GuiCargosAdministrador;

/**
 *
 * @author Luis Molina
 */
public class CGuiCargosAdministrador implements ActionListener, KeyListener, ListSelectionListener, MouseListener{

    private GuiCargosAdministrador formCargosAdministrador;
    private Usuarios usuario;
    private java.sql.Connection conexion;
    private CommonDao dao;
    private ModeloCargos modeloCargos;
    private int id_sistema_formulario;
    private ModeloSistemaFormulariosParaPermisos modeloInterfaces;
    private ModeloSistemaFormulariosPermisosCargos modeloPermisosInterfaces;
    private String TipoOperacion ="";
    private String TituloFormulario = "";
    public CGuiCargosAdministrador(GuiCargosAdministrador formCargosAdministrador)
    {
        this.formCargosAdministrador = formCargosAdministrador;
        modeloCargos = new ModeloCargos();
        modeloInterfaces = new ModeloSistemaFormulariosParaPermisos(true, false);
        modeloPermisosInterfaces = new ModeloSistemaFormulariosPermisosCargos();
        this.TituloFormulario = "Gestion de Cargos y Permisos";
        
        
        
    }

    public void habilitarComponentes(boolean  estadoHabilitacion)
    {
        this.formCargosAdministrador.getTxtNombre().setEnabled(estadoHabilitacion);
        this.formCargosAdministrador.getTxtDescripcion().setEnabled(estadoHabilitacion);
        if(estadoHabilitacion)
            this.formCargosAdministrador.getjTabbedPaneCargos().remove(formCargosAdministrador.getPnlGestionarPermisos());
        else
            this.formCargosAdministrador.getjTabbedPaneCargos().addTab("Gestionar Permisos",formCargosAdministrador.getPnlGestionarPermisos());
    }

    public void LimpiarComponentes()
    {
        formCargosAdministrador.getLblDatoCargo().setText("");
        this.formCargosAdministrador.getTxtNombre().setText("");
        this.formCargosAdministrador.getTxtDescripcion().setText("");
        this.formCargosAdministrador.getTxtCodigo().setText("");
    }

    public void habilitarBotonesRegistro(boolean nuevo, boolean editar, boolean aceptar, boolean cancelar, boolean eliminar)
    {
        this.formCargosAdministrador.getBtnNuevo().setEnabled(nuevo);
        this.formCargosAdministrador.getBtnEditar().setEnabled(editar);
        this.formCargosAdministrador.getBtnAceptar().setEnabled(aceptar);
        this.formCargosAdministrador.getBtnCancelar().setEnabled(cancelar);
        this.formCargosAdministrador.getBtnEliminar().setEnabled(eliminar);
    }

    public void habilitarBotonesPermisos(boolean modificar, boolean agregar, boolean remover, boolean confirmar,boolean descartar)
    {
        this.formCargosAdministrador.getBtnModificarPermisos().setEnabled(modificar);
        this.formCargosAdministrador.getBtnAgregar().setEnabled(agregar);
        this.formCargosAdministrador.getBtnRemover().setEnabled(remover);
        this.formCargosAdministrador.getBtnConfirmar().setEnabled(confirmar);
        this.formCargosAdministrador.getBtnDescargarPermisos().setEnabled(descartar);
    }

    public boolean validarCampos()
    {
        if(formCargosAdministrador.getTxtNombre().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formCargosAdministrador, "El Nombre del Cargo se encuentra vacio", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formCargosAdministrador.getTxtNombre().grabFocus();
            return false;
        }
        return true;
    }


    public void cargarDatosCargo(Cargo cargoMostrar)
    {
        if(cargoMostrar != null)
        {
            formCargosAdministrador.getTxtCodigo().setText(cargoMostrar.getCodigo_cargo());
            formCargosAdministrador.getTxtNombre().setText(cargoMostrar.getNombre_cargo());
            formCargosAdministrador.getTxtDescripcion().setText(cargoMostrar.getDescripcion());
            formCargosAdministrador.getLblDatoCargo().setText("Cargo Seleccionado Actualmente " +cargoMostrar.getNombre_cargo());
            if(cargoMostrar.getListaPermisos() != null)
            {
                modeloPermisosInterfaces.setDataFromArrayList(cargoMostrar.getListaPermisos());
                this.formCargosAdministrador.getjTablePermisosInterfaces().validate();
                this.formCargosAdministrador.getjTablePermisosInterfaces().invalidate();
            }
            else
            {
                modeloPermisosInterfaces.clear();
            }

            habilitarBotonesPermisos(true, false, false, false, false);
            habilitarBotonesRegistro(true, false, false, false, true);
        }
        else{
            habilitarBotonesPermisos(true, false, false, false, false);
            habilitarBotonesRegistro(true, false, false, false, false);
            LimpiarComponentes();
            habilitarComponentes(false);
        }
    }

    public void actionPerformed(ActionEvent event) {
        String accion = event.getActionCommand();
        if(accion.compareTo("nuevo") == 0)
        {
            LimpiarComponentes();
            habilitarComponentes(true);
            habilitarBotonesRegistro(false, false, true, true, false);
            TipoOperacion = "N";
            try {
                int CodigoNuevo = dao.obtenerUltimoObjetoTabla(new Cargo(), "");
                this.formCargosAdministrador.getTxtCodigo().setText(String.valueOf(CodigoNuevo + 1));
            } catch (SQLException ex) {
                Logger.getLogger(CGuiCargosAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(accion.compareTo("editar") == 0)
        {
            TipoOperacion ="E";
            habilitarBotonesRegistro(false, false, true, true, false);
            habilitarComponentes(true);
        }


        if(accion.compareTo("aceptar") == 0)
        {
            if(validarCampos())
            {
                try {
                    Cargo cargoNuevo = new Cargo(this.formCargosAdministrador.getTxtCodigo().getText(),
                        this.formCargosAdministrador.getTxtNombre().getText(),
                        this.formCargosAdministrador.getTxtDescripcion().getText());
                    if(TipoOperacion == "N")
                    {
                        System.out.println("Insertando Cargo");
                        if(!dao.VerificarExistenciaDescripcion(cargoNuevo))
                        {
                            dao.insertar(cargoNuevo);
                            modeloCargos.addCargo(cargoNuevo);
                            formCargosAdministrador.getjTableCargos().setRowSelectionInterval(modeloCargos.getRowCount() - 1, modeloCargos.getRowCount() - 1);
                        }
                        else{
                            JOptionPane.showMessageDialog(formCargosAdministrador,
                            "El nombre del cargo ya se encuentra Registrado", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                            return;

                        }


                    } else
                    {
                        dao.edit(cargoNuevo);
                        modeloCargos.editarVacunas2(cargoNuevo, formCargosAdministrador.getjTableCargos().getSelectedRow());
                    }
                    JOptionPane.showMessageDialog(formCargosAdministrador,
                            "Operación realizada correctamente", TituloFormulario, JOptionPane.INFORMATION_MESSAGE);
                    habilitarBotonesRegistro(true, true, false, false, true);
                    habilitarComponentes(false);
                    TipoOperacion ="";
                    Usuarios usuarioBackup = new Usuarios();
                    usuarioBackup.setCodigo_usuario(usuario.getCodigo_usuario());
                    usuarioBackup = (Usuarios) dao.obtenerObjeto(usuarioBackup);
                    if(usuarioBackup != null)
                        this.usuario = usuarioBackup;


                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formCargosAdministrador,
                            "Ocurrio la siguiente Excepción " +ex.getMessage(), TituloFormulario, JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }
        }

        if(accion.compareTo("cancelar") == 0)
        {
            TipoOperacion ="";
            habilitarBotonesRegistro(true, formCargosAdministrador.getjTableCargos().getSelectedRow() >= 0, false, false,
                    formCargosAdministrador.getjTableCargos().getSelectedRow() >= 0);
            habilitarComponentes(false);
            LimpiarComponentes();
        }

        if(accion.compareTo("eliminar") == 0)
        {
            if(formCargosAdministrador.getjTableCargos().getSelectedRow() < 0)
            {
                JOptionPane.showMessageDialog(formCargosAdministrador, "Aún no ha seleccionado ningun registro", "Gestión de Cargos", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(JOptionPane.showConfirmDialog(formCargosAdministrador,
                    "¿Se encuentra Seguro de Eliminar el registro Actual?", "Gestion de Cargos",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                Cargo cargoSeleccionado = modeloCargos.getCargo(formCargosAdministrador.getjTableCargos().getSelectedRow());
                try {
                    dao.delete(cargoSeleccionado);
                    modeloCargos.removeCargo(formCargosAdministrador.getjTableCargos().getSelectedRow());
                    LimpiarComponentes();
                    JOptionPane.showMessageDialog(formCargosAdministrador, "Registro Eliminado Satisfactoriamente", TituloFormulario, JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(formCargosAdministrador, "Ocurrio la Siguiente Excepcion " + ex.getMessage(),
                            TituloFormulario, JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if(accion.compareTo("cerrar") == 0)
        {
            this.formCargosAdministrador.setVisible(false);
        }

        if(accion.compareTo("modificarPermisos") == 0)
        {            
            this.formCargosAdministrador.getjScrollPaneInterfaces().setVisible(true);
            this.modeloPermisosInterfaces.setPermitirEdicion(true);
            habilitarBotonesPermisos(false, true, true, true, true);
        }
        //
        if(accion.compareTo("descargarPermisos") == 0)
        {
            this.formCargosAdministrador.getjScrollPaneInterfaces().setVisible(false);
            habilitarBotonesPermisos(true, false, false, false, false);
            cargarDatosCargo(modeloCargos.getCargo(formCargosAdministrador.getjTableCargos().getSelectedRow()));
            this.modeloPermisosInterfaces.setPermitirEdicion(false);
        }
        if(accion.compareTo("confirmar") == 0)
        {
            if(JOptionPane.showConfirmDialog(formCargosAdministrador, "Tome en cuenta que los usuarios asociados a este cargo modficaran sus permisos de acceso "
                    + "Despues de Confirmar la operación ¿Se encuentra seguro de Confirmar los Cambios?", TituloFormulario , JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {

                try {
                    int indiceSeleccionado = formCargosAdministrador.getjTableCargos().getSelectedRow();
                    Cargo cargoActual = modeloCargos.getCargo(indiceSeleccionado);
                    SistemaFormulariosPermisosCargos sistemaFormularioPermisoCargo = new SistemaFormulariosPermisosCargos();
                    sistemaFormularioPermisoCargo.setCodigo_cargo(cargoActual.getCodigo_cargo());
                    dao.delete(sistemaFormularioPermisoCargo);
                    for (SistemaFormulariosPermisosCargos permisosCargos : modeloPermisosInterfaces.getDataAsArrayList()) {
                        dao.insertar(permisosCargos);
                    }

//                    Cargo cargoProvisional = (Cargo) dao.obtenerObjeto(cargoActual);
////                    if(cargoProvisional.getListaPermisos().size() != modeloPermisosInterfaces.getDataAsArrayList().size())
////                    {
//                        for(SistemaFormulariosPermisosCargos permisosCargos2 : cargoProvisional.getListaPermisos())
//                        {
//                            if(Collections.binarySearch(modeloPermisosInterfaces.getDataAsArrayList(), permisosCargos2) < 0)
//                            {
//                                dao.delete(permisosCargos2);
//                            }
//                        }
////                    }
                    habilitarBotonesPermisos(true, false, false, false, false);
                    formCargosAdministrador.getjScrollPaneInterfaces().setVisible(false);
                    JOptionPane.showMessageDialog(formCargosAdministrador, "Operación realizada satisfactoriamente", TituloFormulario, JOptionPane.INFORMATION_MESSAGE);
                    this.modeloCargos.setDatosFromLista(dao.getObjects("Cargo"));
                    this.formCargosAdministrador.getjTableCargos().setRowSelectionInterval(indiceSeleccionado, indiceSeleccionado);
                    this.modeloPermisosInterfaces.setPermitirEdicion(false);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(formCargosAdministrador,
                            "No se pudo realizar la operación actual debido a que ocurrió la siguiente excepcion" +ex.getMessage(),
                            TituloFormulario, JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        }
        if(accion.compareTo("removerPermisos") == 0)
        {
            if(!modeloInterfaces.existeAlgunElmentoSeleccionado())
            {
                JOptionPane.showMessageDialog(formCargosAdministrador, "Aún no ha seleccionado níngun elemento", this.formCargosAdministrador.getTitle(), JOptionPane.WARNING_MESSAGE);
                return;
            }
            for (SistemaFormularios formularioSeleccionado : modeloInterfaces.getDataAsList()) {
                if(formularioSeleccionado.isEstado())
                {
                    if(modeloPermisosInterfaces.existeSistemaFormulario(formularioSeleccionado))
                        modeloPermisosInterfaces.removeSistemaFormulariosPermisosCargos(formularioSeleccionado);
                }
            }
        }
        if(accion.compareTo("agregarFormulario") == 0)
        {
            if(!modeloInterfaces.existeAlgunElmentoSeleccionado())
            {
                JOptionPane.showMessageDialog(formCargosAdministrador, "Aún no ha seleccionado níngun elemento", this.formCargosAdministrador.getTitle(), JOptionPane.WARNING_MESSAGE);
                return;
            }
            for (SistemaFormularios formularioSeleccionado : modeloInterfaces.getDataAsList()) {
                if(formularioSeleccionado.isEstado())
                {
                    if(!modeloPermisosInterfaces.existeSistemaFormulario(formularioSeleccionado))
                    {
                        modeloPermisosInterfaces.addSistemaFormulariosPermisosCargos(
                                new SistemaFormulariosPermisosCargos(formularioSeleccionado, formCargosAdministrador.getTxtCodigo().getText()));
                    }
                }
            }
        }
    }

    public void onFormShow()
    {
        try {
            formCargosAdministrador.getjTableCargos().getSelectionModel().addListSelectionListener(this);
            formCargosAdministrador.getjTableCargos().addMouseListener(this);
            List<Cargo> listaCargos = (List<Cargo>) dao.getObjects("Cargo", new Cargo());
            List<SistemaFormularios> listaFormularios = dao.getObjects("SistemaFormularios");
            modeloInterfaces.setDataFromArrayList(listaFormularios);

            this.modeloCargos.setDatosFromLista(listaCargos);
            this.formCargosAdministrador.getjTableCargos().setModel(modeloCargos);
            this.formCargosAdministrador.getjTablePermisosInterfaces().setModel(modeloPermisosInterfaces);
            this.formCargosAdministrador.getjTableInterfaces().setModel(modeloInterfaces);            
            cargarDatosCargo(null);
            this.formCargosAdministrador.getjTableCargos().getColumnModel().getColumn(0).setPreferredWidth(20);
            this.formCargosAdministrador.getjTableCargos().getColumnModel().getColumn(1).setPreferredWidth(120);

            this.formCargosAdministrador.getjScrollPaneInterfaces().setVisible(false);
            this.formCargosAdministrador.getjTableInterfaces().addMouseListener(this);

        } catch (SQLException ex) {
//            Logger.getLogger(CGuiCargosAdministrador.class.getName()).log(Level.SEVERE, null, ex);
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

    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
            if (!((Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE)
                            || (c == KeyEvent.VK_SPACE)
                            || (c == KeyEvent.VK_DELETE) ))) {
                    formCargosAdministrador.getToolkit().beep();
                    e.consume();
            }
    }

    public void keyPressed(KeyEvent e) {
        
    }

    public void keyReleased(KeyEvent e) {
        
    }

    void setIdFormulario(int id_sistema_formulario) {
        this.id_sistema_formulario= id_sistema_formulario;
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
            cargarDatosCargo(modeloCargos.getCargo(indice));
            formCargosAdministrador.getBtnEditar().setEnabled(true);
        }
        else
            formCargosAdministrador.getBtnEditar().setEnabled(false);
    }

    public void mouseClicked(MouseEvent e) {
        

        
    }

    public void mousePressed(MouseEvent e) {
        if(e.getClickCount() == 2)
        {
            if(formCargosAdministrador.getjTableCargos().isFocusOwner()
                    && formCargosAdministrador.getjTableCargos().getSelectedRow() >= 0)
            {
                formCargosAdministrador.getjTabbedPaneCargos().setSelectedIndex(1);
            }
        }

        System.out.println("Presionado el click Derecho, Evento:  " + e.isPopupTrigger());
        if(formCargosAdministrador.getjTableInterfaces().isFocusOwner() &&
                formCargosAdministrador.getjTableInterfaces().getSelectedRow() >= 0
                && e.isPopupTrigger())
        {
            Point p = new Point(e.getX(), e.getY());            
            if (formCargosAdministrador.getjPopupMenuFormularios() != null && formCargosAdministrador.getjPopupMenuFormularios().getComponentCount() > 0) {
                    formCargosAdministrador.getjPopupMenuFormularios().show(formCargosAdministrador.getjTableInterfaces(), p.x, p.y);
            }
            
        }
    }

    public void mouseReleased(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }

    private void cancelCellEditing() {
        CellEditor ce = this.formCargosAdministrador.getjTableInterfaces().getCellEditor();
        if (ce != null) {
                ce.cancelCellEditing();
        }
    }
    

}
