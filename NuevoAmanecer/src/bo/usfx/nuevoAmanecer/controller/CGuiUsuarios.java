/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Cargo;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormularios;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormulariosPermisosUsuarios;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.utils.ComparadorIdFormulario;
import bo.usfx.utils.FormUtilities;
import bo.usfx.utils.ModeloUsuarios;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import view.GuiUsuarios;


public class CGuiUsuarios extends MouseAdapter implements ActionListener, KeyListener{
    private GuiUsuarios formUsuarios;
    private CommonDao dao;
    String tipoOperacion = "";
    ModeloUsuarios modeloUsuario;
    List<Cargo> listaCargos;
    private Usuarios usuariosPermisos;
    public CGuiUsuarios(GuiUsuarios formUsuarios) {
        this.formUsuarios = formUsuarios;
        modeloUsuario = new ModeloUsuarios();
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }
    
    
    public void actionPerformed(ActionEvent event) {
        String accion = event.getActionCommand();
        if(accion.compareTo(formUsuarios.getBtnNuevo().getActionCommand()) == 0)
        {
            limpiarCampos();
            habilitarControles(true);
            habilitarBotones(false, true, false, true, false, false, false);
            tipoOperacion = "N";
            try {
                int numeroUsuario = dao.obtenerUltimoObjetoTabla(usuariosPermisos, "") + 1;
                this.formUsuarios.getTxtCodigoUsuario().setText(String.valueOf(numeroUsuario));
                this.formUsuarios.getTxtFechaRegistro().setText(FormUtilities.formatearDate(dao.obtenerFechaHoraServidor()));
            } catch (SQLException ex) {
                Logger.getLogger(CGuiUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if(accion.compareTo(formUsuarios.getBtnCancelar().getActionCommand()) == 0)
        {
            habilitarControles(false);
            habilitarBotones(true, false,
                    !formUsuarios.getTxtCodigoUsuario().getText().isEmpty(), false,
                    !formUsuarios.getTxtCodigoUsuario().getText().isEmpty(), true, true);
            if(tipoOperacion =="N")
                limpiarCampos();
            tipoOperacion = "";
        }
        if(accion.compareTo(formUsuarios.getBtnBuscar().getActionCommand()) == 0)
        {
            formUsuarios.getjTabbedPane1().setSelectedIndex(1);
            formUsuarios.getTxtTextoBusqueda().selectAll();
            formUsuarios.getTxtTextoBusqueda().grabFocus();
        }
        if(accion.compareTo(formUsuarios.getBtnModificar().getActionCommand()) == 0)
        {
            habilitarControles(true);
            habilitarBotones(false, true, false, true, false, false, false);
            tipoOperacion = "E";
        }

        if(accion.compareTo(formUsuarios.getBtnEliminar().getActionCommand()) == 0
                && JOptionPane.showConfirmDialog(formUsuarios,
                "¿Se encuentra seguro de dar de baja al usduario?", "Gestión de usuarios",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
            try {
                int CodigoUsuario = Integer.parseInt(formUsuarios.getTxtCodigoUsuario().getText());
                Usuarios usuarioEliminar = new Usuarios();
                usuarioEliminar.setCodigo_usuario(CodigoUsuario);
                dao.delete(usuarioEliminar);
                if(formUsuarios.getjTableUsuarios().getSelectedRow()>= 0)
                {
                    if(modeloUsuario.getUsuarios(formUsuarios.getjTableUsuarios().getSelectedRow()).getCodigo_usuario()
                            == usuarioEliminar.getCodigo_usuario())
                        modeloUsuario.removeUsuarios(formUsuarios.getjTableUsuarios().getSelectedRow());
                }
                JOptionPane.showMessageDialog(formUsuarios, "Operación realizada satisfactgoriamente", "Gestion de Usuarios", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                habilitarBotones(true, false, false, false, false, true, true);
                
            } catch (SQLException ex) {
                Logger.getLogger(CGuiUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(formUsuarios, "Ocurrió la siguiente Excepción " + ex.getMessage(), "Gestión de usuarios", JOptionPane.ERROR_MESSAGE);
            }

        }
        if(accion.compareTo("buscarN") == 0)
        {
            try {
                if (formUsuarios.getTxtTextoBusqueda().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(formUsuarios, "Aún no ha ingresado un texto de busqueda", "Gestión de usuarios", JOptionPane.ERROR_MESSAGE);
                    formUsuarios.getTxtTextoBusqueda().grabFocus();
                    formUsuarios.getTxtTextoBusqueda().selectAll();
                    return;
                }
                Usuarios usuarioBusqueda = new Usuarios();
                try {
                    usuarioBusqueda.setCodigo_usuario(Integer.parseInt(formUsuarios.getTxtTextoBusqueda().getText()));
                } catch (NumberFormatException e) {
                    usuarioBusqueda.setCodigo_usuario(-1);
                }
                usuarioBusqueda.setNombres(formUsuarios.getTxtTextoBusqueda().getText());
                List<Usuarios> listaUsuarios = dao.findObjects(usuarioBusqueda);
                modeloUsuario.clear();
                if(listaUsuarios != null)
                {
                    for (Usuarios usuarios : listaUsuarios) {
                        modeloUsuario.addUsuarios(usuarios);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(CGuiUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(formUsuarios, "Ocurrio la siguiente Excepción" + ex.getMessage(),
                        "Gestión de Usuarios", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(accion.compareTo(formUsuarios.getBtnGuardar().getActionCommand()) == 0)
        {
            try {
                if (!validarCampos()) {
                    JOptionPane.showMessageDialog(formUsuarios, "Algunos datos son incorrectos, porfavor proceda a corregirlos", "Gestión de Usuarios", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Usuarios usuario = new Usuarios();
                usuario.setNombres(formUsuarios.getTxtNombres().getText());
                usuario.setApellidos(formUsuarios.getTxtApellidos().getText());
                usuario.setNombre_cuenta(formUsuarios.getTxtNombreCuenta().getText());
                usuario.setContrasenia(formUsuarios.getjPasswordField1().getText());
                usuario.setTelefono(formUsuarios.getTxtTelefono().getText());
                usuario.setDireccion(formUsuarios.getTxtDireccion().getText());
                usuario.setCodigo_estado(formUsuarios.getcBoxEstadoActual().getSelectedIndex() == 2 ? "S" : "V");
//                Vusuario.setFecha_registro(Date.valueOf(formUsuarios.getTxtFechaRegistro().getText()));
                String fechaAnterior = formUsuarios.getTxtFechaRegistro().getText();


                Cargo tipoUsuario = ((Cargo)formUsuarios.getCboxTipoUsuario().getSelectedItem());
                usuario.setCodigo_tipo_usuario(tipoUsuario.getCodigo_cargo());

                if (tipoOperacion == "N") {
                    if(dao.VerificarExistenciaDescripcion(usuario))
                    {
                        JOptionPane.showMessageDialog(formUsuarios, "ya existe un Usuario con los datos provistos", "Gestión de Usuarios", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    if(usuario.getCodigoEstadoCadena().compareTo("BAJA")==0 )
                    {
                        JOptionPane.showMessageDialog(formUsuarios, "No puede crear un usuario con el Estado de Baja", "Gestión de Usuarios", JOptionPane.ERROR_MESSAGE);
                        formUsuarios.getcBoxEstadoActual().grabFocus();
                        return;
                    }
                    usuario.setFecha_registro(null);
                    dao.insertar(usuario);
                    usuario.setCodigo_usuario(dao.obtenerUltimoObjetoTabla(usuario,"a"));
                }
                else
                {
                    usuario.setCodigo_usuario(Integer.parseInt(formUsuarios.getTxtCodigoUsuario().getText()));
                    if(formUsuarios.getcBoxEstadoActual().getSelectedIndex() == 2)
                        usuario.setCodigo_estado("S");
                    else if(formUsuarios.getcBoxEstadoActual().getSelectedIndex() == 1)
                        usuario.setCodigo_estado("B");

                    if(usuario.getCodigoEstadoCadena().compareTo("BAJA")==0 &&
                            JOptionPane.showConfirmDialog(formUsuarios,
                            "¿Se encuentra seguro de Dar de Baja al Usuario?", "Gestion de Usuarios", JOptionPane.YES_NO_OPTION)
                            == JOptionPane.NO_OPTION)                        
                    {
                        formUsuarios.getcBoxEstadoActual().grabFocus();
                        return;
                    }
                    dao.edit(usuario);
                    if(formUsuarios.getjTableUsuarios().getSelectedRow() >= 0)
                        modeloUsuario.editarUsuarios(usuario, formUsuarios.getjTableUsuarios().getSelectedRow());

                }
                cargarDatosUsuario(usuario);
                formUsuarios.getTxtFechaRegistro().setText(fechaAnterior);
                habilitarControles(false);
                JOptionPane.showMessageDialog(formUsuarios, "Operación realizada satisfactoriamente", "Gestión de usuarios", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (SQLException ex) {
                Logger.getLogger(CGuiUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(formUsuarios, "Ocurrió la siguiente Excepción " + ex.getMessage(), "Gestión de usuarios", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void cargarDatosUsuario(Usuarios usuarioDato)
    {
        if(usuarioDato != null)
        {
            formUsuarios.getTxtNombres().setText(usuarioDato.getNombres());
            formUsuarios.getTxtApellidos().setText(usuarioDato.getApellidos());
            formUsuarios.getTxtNombreCuenta().setText(usuarioDato.getNombre_cuenta());
            formUsuarios.getjPasswordField1().setText(usuarioDato.getContrasenia());
            formUsuarios.getTxtCodigoUsuario().setText(String.valueOf(usuarioDato.getCodigo_usuario()));
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            
            formUsuarios.getTxtFechaRegistro().setText(usuarioDato.getFecha_registro() != null ?  df.format(usuarioDato.getFecha_registro()) : "");
            formUsuarios.getTxtDireccion().setText(usuarioDato.getDireccion());
            formUsuarios.getTxtTelefono().setText(usuarioDato.getTelefono());
            formUsuarios.getcBoxEstadoActual().setSelectedItem(usuarioDato.getCodigoEstadoCadena());

//            switch(usuarioDato.getCodigo_tipo_usuario().charAt(0))
//            {//--'C'->COORDINADOR DE AREA,'F'->FACILITADOR DE PROGRAMAS,'R'-> RESPONSABLE DE PATROCINIO,'P'->PSICOLOGIA,'A'->ADMINISTRADOR
//                case 'C':
//                    formUsuarios.getCboxTipoUsuario().setSelectedIndex(0);
//                    break;
//
//               case 'F':
//                    formUsuarios.getCboxTipoUsuario().setSelectedIndex(1);
//                    break;
//               case 'R':
//                    formUsuarios.getCboxTipoUsuario().setSelectedIndex(2);
//                    break;
//               case 'P':
//                    formUsuarios.getCboxTipoUsuario().setSelectedIndex(3);
//                    break;
//               case 'A':
//                    formUsuarios.getCboxTipoUsuario().setSelectedIndex(4);
//                    break;
//            }

            Cargo cargoUsuario = new Cargo();
            cargoUsuario.setCodigo_cargo(usuarioDato.getCodigo_tipo_usuario());
            System.out.println("Codigo Usuario " + usuarioDato.getCodigo_tipo_usuario());
            List<Cargo> listaTemporal = null;
            try {
                listaTemporal = dao.getObjects("Cargo2", cargoUsuario);
            } catch (SQLException ex) {
                Logger.getLogger(CGuiUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            cargoUsuario = listaTemporal.get(0);
            int indice2 = Collections.binarySearch(listaCargos, cargoUsuario);
            System.out.println("Codigo Busqueda " +cargoUsuario.getNombre_cargo().trim() +", Indice Encontrado " + indice2 + ",  Cantidad de elementos " + listaCargos.size() + ", Combo " + formUsuarios.getCboxTipoUsuario().getItemCount());
            formUsuarios.getCboxTipoUsuario().setSelectedIndex(Math.abs(indice2));

            habilitarBotones(true, false, true, false, usuarioDato.esPosibleDarBaja(), true, true);
            
        }
        else
        {
            limpiarCampos();
            habilitarControles(false);
            habilitarBotones(true, false, false, false, false, true, false);
        }
    }
    public void onFormShown()
    {
//        this.formUsuarios.setPreferredSize(new Dimension(350,120));
        this.formUsuarios.setBounds(0, 0, 620, 270);
        limpiarCampos();
        habilitarControles(false);
        habilitarBotones(true, false, false, false, false, true, true);
        modeloUsuario = new ModeloUsuarios();
        this.formUsuarios.getjTableUsuarios().setModel(modeloUsuario);
        this.formUsuarios.getjTableUsuarios().addMouseListener(this);
        try {
            //--'C'->COORDINADOR DE AREA,'F'->FACILITADOR DE PROGRAMAS,'R'-> RESPONSABLE DE PATROCINIO,'P'->PSICOLOGIA,'A'->ADMINISTRADOR
            //        SformUsuarios.getCboxTipoUsuario().setModel(null);
            //        formUsuarios.getCboxTipoUsuario().addItem(new Usuarios(-1,"C","CORDINADOR DE AREA"));
            //        formUsuarios.getCboxTipoUsuario().addItem(new Usuarios(-1,"F","FACILITADOR DE PROGRAMAS"));
            //        formUsuarios.getCboxTipoUsuario().addItem(new Usuarios(-1,"R","RESPONSABLE DE PATROCINIO"));
            //        formUsuarios.getCboxTipoUsuario().addItem(new Usuarios(-1,"P","PSICOLOGA"));
            //        formUsuarios.getCboxTipoUsuario().addItem(new Usuarios(-1,"A","ADMINISTRADOR"));
            listaCargos = dao.getObjects("Cargo2", new Cargo());
//            Collections.sort(listaCargos);
            for (Cargo cargo : listaCargos) {
                formUsuarios.getCboxTipoUsuario().addItem(cargo);
                System.out.println("Codigo " + cargo.getCodigo_cargo() + ", Cargo " + cargo.getNombre_cargo());
            }

        configuracionFormulario();
        } catch (SQLException ex) {
            Logger.getLogger(CGuiUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void limpiarCampos()
    {
        formUsuarios.getTxtCodigoUsuario().setText("");
        formUsuarios.getTxtNombres().setText("");
        formUsuarios.getTxtApellidos().setText("");
        formUsuarios.getTxtFechaRegistro().setText("");
        formUsuarios.getTxtNombreCuenta().setText("");
        formUsuarios.getjPasswordField1().setText("");
        formUsuarios.getcBoxEstadoActual().setSelectedIndex(-1);
        formUsuarios.getTxtDireccion().setText("");
        formUsuarios.getTxtTelefono().setText("");
        formUsuarios.getCboxTipoUsuario().setSelectedIndex(-1);
    }

    public void habilitarControles(boolean estadoHabilitacion)
    {
        for(Component control : formUsuarios.getPnlCentral().getComponents())
        {
            if(!(control instanceof JLabel))
            {
                control.setEnabled(estadoHabilitacion);
            }
        }
        this.formUsuarios.getTxtCodigoUsuario().setEnabled(false);
        this.formUsuarios.getTxtFechaRegistro().setEnabled(false);
        this.formUsuarios.getTxtFechaRegistro().setEnabled(false);
    }

    public void habilitarBotones(boolean nuevo, boolean aceptar, boolean modificar, boolean cancelar, boolean eliminar, boolean buscar, boolean reportes)
    {
        formUsuarios.getBtnNuevo().setEnabled(nuevo);
        formUsuarios.getBtnGuardar().setEnabled(aceptar);
        formUsuarios.getBtnModificar().setEnabled(modificar);
        formUsuarios.getBtnCancelar().setEnabled(cancelar);
        formUsuarios.getBtnEliminar().setEnabled(eliminar);
        formUsuarios.getBtnBuscar().setEnabled(buscar);
        
    }

    public boolean validarCampos()
    {
        if(formUsuarios.getTxtNombres().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formUsuarios, "Aún no ha ingresado el nombre del Usuario", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formUsuarios.getTxtNombres().grabFocus();
            return false;
        }
        if(formUsuarios.getTxtApellidos().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formUsuarios, "Aún no ha ingresado los Apellidos del Usuario", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formUsuarios.getTxtApellidos().grabFocus();
            return false;
        }
        if(formUsuarios.getTxtNombreCuenta().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formUsuarios, "Aún no ha ingresado el nombre de Cuenta de Usuario", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formUsuarios.getTxtNombreCuenta().grabFocus();
            return false;
        }
        if(formUsuarios.getjPasswordField1().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formUsuarios, "Aún no ha ingresado la contraseña del Usuario", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formUsuarios.getjPasswordField1().grabFocus();
            return false;
        }
        if(formUsuarios.getcBoxEstadoActual().getSelectedIndex() == -1)
        {
            JOptionPane.showMessageDialog(formUsuarios, "Aún no ha seleccionado el Estado del Usuario", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formUsuarios.getcBoxEstadoActual().grabFocus();
            return false;
        }

        if(formUsuarios.getCboxTipoUsuario().getSelectedIndex() == -1)
        {
            JOptionPane.showMessageDialog(formUsuarios, "Aún no ha seleccionado la Responsabilidad asiganada al Usuario", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formUsuarios.getCboxTipoUsuario().grabFocus();
            return false;
        }

        if(tipoOperacion == "N" && formUsuarios.getcBoxEstadoActual().getSelectedIndex() == 1)
        {
            JOptionPane.showMessageDialog(formUsuarios, "No puede registrar un Usuario con el estado actualmente seleccionado", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formUsuarios.getCboxTipoUsuario().grabFocus();
            return false;
        }
        return true;
    }

    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2 && formUsuarios.getjTableUsuarios().getSelectedRow() != -1)
        {
            int indice = formUsuarios.getjTableUsuarios().getSelectedRow();
            formUsuarios.getjTabbedPane1().setSelectedIndex(0);
            cargarDatosUsuario(modeloUsuario.getUsuarios(indice));
            habilitarControles(false);
        }
    }

    public void setUsuario(Usuarios usuariosPermisos) {
        this.usuariosPermisos = usuariosPermisos;
    }


    public void configuracionFormulario() {
        if (usuariosPermisos != null) {
            int indice = -1;
            SistemaFormularios formulario = new SistemaFormularios();
            formulario.setNombre_formulario(FormUtilities.getClassName(formUsuarios.getClass()));
            SistemaFormulariosPermisosUsuarios permisosUsuarios = new SistemaFormulariosPermisosUsuarios();
            permisosUsuarios.setSistemaFormulario(formulario);
            this.formUsuarios.getTxtTelefono().addKeyListener(this);
            indice = Collections.binarySearch(usuariosPermisos.getListadoInterfacesPermisos(), permisosUsuarios, new ComparadorIdFormulario());
            if (indice >= 0) {
                permisosUsuarios = usuariosPermisos.getListadoInterfacesPermisos().get(indice);

//                if(!permisosUsuarios.isPermitir_insertar())
//                    formUsuarios.getjTabbedPane().remove(formUsuarios.getPnlTabPageInsertar());
//                if(!permisosUsuarios.isPermitir_editar())
//                    formUsuarios.getjTabbedPanePrograma().remove(formUsuarios.getPnlTabPageEditar());
//                if(!permisosUsuarios.isPermitir_anular() && !permisosUsuarios.isPermitir_navegar())
//                    formUsuarios.getjTabbedPanePrograma().remove(formUsuarios.getPnlTabPageEliminar());
//                else
//                {
//                    formUsuarios.getBtnBuscarEliminar().setVisible(permisosUsuarios.isPermitir_navegar());
//                    formUsuarios.getBtnEliminar().setVisible(permisosUsuarios.isPermitir_anular());
//                }

                this.formUsuarios.getBtnBuscar().setVisible(permisosUsuarios.isPermitir_navegar());
                this.formUsuarios.getBtnBuscarNavegacion().setVisible(permisosUsuarios.isPermitir_navegar());
                this.formUsuarios.getTxtTextoBusqueda().setVisible(permisosUsuarios.isPermitir_navegar());

                this.formUsuarios.getBtnEliminar().setVisible(permisosUsuarios.isPemitir_eliminar());
                
                this.formUsuarios.getBtnNuevo().setVisible(permisosUsuarios.isPermitir_insertar());
                this.formUsuarios.getBtnModificar().setVisible(permisosUsuarios.isPermitir_editar());



            } else {
                System.out.println("no tiene permisos, nombre de la Clase " + formulario.getNombre_formulario());
            }
        }
    }

    public void keyTyped(KeyEvent e) {
        JTextField componente = (JTextField) e.getSource();
        char c = e.getKeyChar();
        if (componente.equals(formUsuarios.getTxtTelefono())) {

                if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                                || (c == KeyEvent.VK_DELETE) ))) {
                        formUsuarios.getToolkit().beep();
                        e.consume();
                }
        }else if(componente.equals(formUsuarios.getTxtNombres())
                || componente.equals(formUsuarios.getTxtApellidos())
                || componente.equals(formUsuarios.getTxtNombreCuenta())
                )
        {
            if (!((Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE)
                                || (c == KeyEvent.VK_DELETE)
                                || (c == KeyEvent.VK_SPACE)))) {
                        formUsuarios.getToolkit().beep();
                        e.consume();
                }
        }
    }

    public void keyPressed(KeyEvent e) {
        
    }

    public void keyReleased(KeyEvent e) {
        
    }
    
   
}
