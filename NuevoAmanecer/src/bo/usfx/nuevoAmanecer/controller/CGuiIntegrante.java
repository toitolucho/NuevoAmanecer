/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Integrantes;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormularios;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormulariosPermisosUsuarios;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.utils.ComparadorIdFormulario;
import bo.usfx.utils.FormUtilities;
import bo.usfx.utils.ModeloIntegrantes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.GuiIntegrante;


public class CGuiIntegrante extends KeyAdapter implements ActionListener, ListSelectionListener {
    private GuiIntegrante formIntegrante;
    private CommonDao dao;
    ModeloIntegrantes modeloIntegranteEdicion;
    ModeloIntegrantes modeloIntegranteEliminacion;
    private Usuarios usuario;

    public CGuiIntegrante(GuiIntegrante formIntegrante) {
        this.formIntegrante = formIntegrante;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    

    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();
        if(accion.compareTo(formIntegrante.getBtnNuevo().getActionCommand()) == 0)
        {
            try {
                habilitarComponentesNuevo(true);
                limpiarComponentesNuevo();
                formIntegrante.getTxtCodigoEncargado().grabFocus();
                formIntegrante.getBtnNuevo().setEnabled(false);
                formIntegrante.getBtnRegistrar().setEnabled(true);
                int CodigoNuevo = dao.obtenerUltimoObjetoTabla(new Integrantes(), "") + 1;
                this.formIntegrante.getTxtCodigoEncargado().setText(String.valueOf(CodigoNuevo));
            } catch (SQLException ex) {
                Logger.getLogger(CGuiIntegrante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(accion.compareTo(formIntegrante.getBtnRegistrar().getActionCommand()) == 0)
        {
            if(valicarComponentesNuevo())
            {
                try {
                    Integrantes integrante = new Integrantes(
                        Integer.parseInt(formIntegrante.getTxtCodigoEncargado().getText()),
                        formIntegrante.getTxtPaterno().getText(),
                        formIntegrante.getTxtMaterno().getText(),
                        formIntegrante.getTxtNombreEncargado().getText(),
                        formIntegrante.getcBoxSexo().getSelectedItem().toString().substring(0,1),
                        Integer.parseInt(formIntegrante.getTxtEdad().getText()));
                    dao.insertar(integrante);
                    habilitarComponentesNuevo(false);
                    formIntegrante.getBtnNuevo().setEnabled(true);
                    formIntegrante.getBtnRegistrar().setEnabled(false);
                    JOptionPane.showMessageDialog(formIntegrante, "Registro Satisfactorio", "Registro de Integrantes", JOptionPane.INFORMATION_MESSAGE);

                    //SUGERENCIA SIL
                    limpiarComponentesNuevo();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(formIntegrante, "Ocurrio la siguiente Excepcio" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if(accion.compareTo(formIntegrante.getBtnModificar().getActionCommand()) == 0)
        {
            habilitarComponentesEdicion(true);
            formIntegrante.getTxtNombreE().grabFocus();
            formIntegrante.getBtnModificar().setEnabled(false);
            formIntegrante.getBtnGuardarModificacion().setEnabled(true);
        }
        if(accion.compareTo(formIntegrante.getBtnGuardarModificacion().getActionCommand()) == 0)
        {
            if(valicarComponentesEdicion())
            {
                 try {
                    Integrantes integrante = new Integrantes(
                        Integer.parseInt(formIntegrante.getTxtCodigoE().getText()),
                        formIntegrante.getTxtPaternoE().getText(),
                        formIntegrante.getTxtMaternoE().getText(),
                        formIntegrante.getTxtNombreE().getText(),
                        formIntegrante.getcBoxSexoE().getSelectedItem().toString().substring(0,1),
                        Integer.parseInt(formIntegrante.getTxtEdadE().getText()));
                    dao.edit(integrante);
                    habilitarComponentesEdicion(false);
                    formIntegrante.getBtnModificar().setEnabled(true);
                    formIntegrante.getBtnGuardarModificacion().setEnabled(false);
                    JOptionPane.showMessageDialog(formIntegrante, "Actualización Satisfactoria", "Registro de Integrantes", JOptionPane.INFORMATION_MESSAGE);

                    //sugerencia sil
                    limpiarComponentesEdicion();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(formIntegrante, "Ocurrio la siguiente Excepcion" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if(accion.compareTo("buscarE") == 0)
        {
            try {
                int CodigoIntegrante = -1;
                try {
                    CodigoIntegrante = Integer.parseInt(formIntegrante.getTxtTextoBusquedaEdicion().getText());
                } catch (NumberFormatException ext) {
                }

                Integrantes integrante = new Integrantes();
                integrante.setCodigo_integrante(CodigoIntegrante);
                integrante.setNombres(formIntegrante.getTxtTextoBusquedaEdicion().getText());

                List<Integrantes> listaIntegrantes = dao.findObjects(integrante);
                modeloIntegranteEdicion.clear();
                if(listaIntegrantes != null && !listaIntegrantes.isEmpty())
                {
                    for (Integrantes integrantes : listaIntegrantes) {
                        modeloIntegranteEdicion.addIntegrantes(integrantes);
                    }
                    formIntegrante.getBtnModificar().setEnabled(false);
                }
                else
                {
                    formIntegrante.getBtnModificar().setEnabled(true);
                    JOptionPane.showMessageDialog(formIntegrante, "No se ha encontrado ningún registro con la descripción dada", "Eliminación de Integrantes", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if(accion.compareTo("buscarX") == 0)
        {
            try {
                int CodigoIntegrante = -1;
                try {
                    CodigoIntegrante = Integer.parseInt(formIntegrante.getTxtTextoBusquedaEliminar().getText());
                } catch (NumberFormatException ext) {
                }

                Integrantes integrante = new Integrantes();
                integrante.setCodigo_integrante(CodigoIntegrante);
                integrante.setNombres(formIntegrante.getTxtTextoBusquedaEliminar().getText());

                List<Integrantes> listaIntegrantes = dao.findObjects(integrante);
                modeloIntegranteEliminacion.clear();
                if(listaIntegrantes != null && !listaIntegrantes.isEmpty())
                {
                    for (Integrantes integrantes : listaIntegrantes) {
                        modeloIntegranteEliminacion.addIntegrantes(integrantes);
                    }
                    formIntegrante.getBtnEliminar().setEnabled(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(formIntegrante, "No se ha encontrado ningún registro con la descripción dada", "Eliminación de Integrantes", JOptionPane.INFORMATION_MESSAGE);
                    formIntegrante.getBtnEliminar().setEnabled(false);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if(formIntegrante.getBtnEliminar().getActionCommand().compareTo(accion)==0)
        {
            int indiceSeleccionado = formIntegrante.getjTableIntegrantesEliminar().getSelectedRow();
            if(indiceSeleccionado< 0)
            {
                JOptionPane.showMessageDialog(formIntegrante, "Aún no ha seleccionado ningun registro de la Tabla para eliminarlo", "Eliminacion de Integrantes", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(JOptionPane.showConfirmDialog(formIntegrante, "¿Se encuentra seguro de eliminar el registro seleccionado?", "Eliminación de Integrantes", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                try {
                    dao.delete(modeloIntegranteEliminacion.getIntegrantes(indiceSeleccionado));
                    modeloIntegranteEliminacion.removeIntegrantes(indiceSeleccionado);
                    JOptionPane.showMessageDialog(formIntegrante, "Operación realizada correctamente", "Eliminacion de Integrantes", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {                    
                    JOptionPane.showMessageDialog(formIntegrante, "No se pudo realizar la operacion actual, Ocurrio la siguiente excepcion "+ex, "Eliminacion de Integrantes", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
    }

    public void cargarDatosIntegrante(Integrantes inte)
    {
        if(inte != null)
        {
            formIntegrante.getTxtCodigoE().setText(String.valueOf(inte.getCodigo_integrante()));
            formIntegrante.getTxtNombreE().setText(inte.getNombres());
            formIntegrante.getTxtPaternoE().setText(inte.getApellido_paterno());
            formIntegrante.getTxtMaternoE().setText(inte.getApellido_materno());
            formIntegrante.getTxtEdadE().setText(String.valueOf(inte.getEdad()));
            formIntegrante.getcBoxSexoE().setSelectedIndex(inte.getSexo().compareTo("F")== 0 ? 1 : 2);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        JTextField componente = (JTextField) e.getSource();
        char c = e.getKeyChar();
        if (componente.equals(formIntegrante.getTxtCodigoE()) ||
              componente.equals(formIntegrante.getTxtCodigoEncargado()) ||
              componente.equals(formIntegrante.getTxtEdad()) ||
              componente.equals(formIntegrante.getTxtEdadE())
              ) {

                if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                                || (c == KeyEvent.VK_DELETE) ))) {
                        formIntegrante.getToolkit().beep();
                        e.consume();
                }
        }else if(componente.equals(formIntegrante.getTxtNombreE())
                || componente.equals(formIntegrante.getTxtNombreEncargado())
                || componente.equals(formIntegrante.getTxtPaterno())
                || componente.equals(formIntegrante.getTxtPaternoE())
                || componente.equals(formIntegrante.getTxtMaterno())
                || componente.equals(formIntegrante.getTxtMaternoE()))
        {
            if (!((Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE)
                                || (c == KeyEvent.VK_DELETE)
                                || (c == KeyEvent.VK_SPACE)))) {
                        formIntegrante.getToolkit().beep();
                        e.consume();
                }
        }
   }

    public void onFormShown()
    {
        limpiarComponentesEdicion();
        limpiarComponentesNuevo();
        habilitarComponentesEdicion(false);
        habilitarComponentesNuevo(false);

        formIntegrante.getBtnNuevo().setEnabled(true);
        formIntegrante.getBtnRegistrar().setEnabled(false);
        formIntegrante.getBtnModificar().setEnabled(true);
        formIntegrante.getBtnGuardarModificacion().setEnabled(false);

        modeloIntegranteEdicion = new ModeloIntegrantes();
        modeloIntegranteEliminacion = new ModeloIntegrantes();

        this.formIntegrante.getjTableIntegrantesEliminar().setModel(modeloIntegranteEliminacion);
        this.formIntegrante.getjTableModificar().setModel(modeloIntegranteEdicion);

        formIntegrante.getjTableModificar().getSelectionModel().addListSelectionListener(this);
        configuracionFormulario();
    }

    public void habilitarComponentesEdicion(boolean estadoHabilitacion)
    {
        formIntegrante.getTxtCodigoE().setEditable(false);
        formIntegrante.getTxtNombreE().setEditable(estadoHabilitacion);
        formIntegrante.getTxtEdadE().setEditable(estadoHabilitacion);
        formIntegrante.getTxtMaternoE().setEditable(estadoHabilitacion);
        formIntegrante.getTxtPaternoE().setEditable(estadoHabilitacion);
        formIntegrante.getcBoxSexoE().setEnabled(estadoHabilitacion);
    }
    
    public void limpiarComponentesEdicion ()
    {
        formIntegrante.getTxtCodigoE().setText("");
        formIntegrante.getTxtNombreE().setText("");
        formIntegrante.getTxtEdadE().setText("");
        formIntegrante.getTxtMaternoE().setText("");
        formIntegrante.getTxtPaternoE().setText("");
        formIntegrante.getcBoxSexoE().setSelectedIndex(0);
    }
    
    public boolean valicarComponentesEdicion()
    {
        if(formIntegrante.getTxtCodigoE().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formIntegrante, "No puede dejar en blanco el codigo o Ci del integrante", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formIntegrante.getTxtCodigoE().grabFocus();
            return false;
        }
        
        if(formIntegrante.getTxtNombreE().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formIntegrante, "No puede dejar en blanco el Nombre del integrante", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formIntegrante.getTxtNombreE().grabFocus();
            return false;
        }
        
        if(formIntegrante.getTxtPaternoE().getText().isEmpty()
                && formIntegrante.getTxtMaternoE().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formIntegrante, "No ha ingresado ninguno de los apellidos del integrante", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formIntegrante.getTxtPaternoE().grabFocus();
            return false;
        }
//
//        if(formIntegrante.getTxtMaternoE().getText().isEmpty())
//        {
//            if(JOptionPane.showConfirmDialog(formIntegrante, "No puede dejar en blanco el Apellido Materno del integrante, Desea dejar en blanco este campo", "Validación de datos", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
//            {
//                formIntegrante.getTxtMaternoE().grabFocus();
//                return false;
//            }
//        }
        
        if(formIntegrante.getTxtEdadE().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formIntegrante, "No puede dejar en blanco el Nombre del integrante", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formIntegrante.getTxtEdadE().grabFocus();
            return false;
        }

        if(Integer.parseInt(formIntegrante.getTxtEdadE().getText()) > 105)
        {
            JOptionPane.showMessageDialog(formIntegrante, "La Edad Ingresada sobrepasa la Maxima permitida", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formIntegrante.getTxtEdadE().grabFocus();
            return false;
        }
        
        if(formIntegrante.getcBoxSexoE().getSelectedIndex() <= 0)
        {
            JOptionPane.showMessageDialog(formIntegrante, "Aún no ha seleccionado el Sexo del integrante", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formIntegrante.getcBoxSexoE().grabFocus();
            return false;
        }
        return true;
    }

    public void habilitarComponentesNuevo(boolean estadoHabilitacion)
    {
        formIntegrante.getTxtCodigoEncargado().setEditable(false);
        formIntegrante.getTxtNombreEncargado().setEditable(estadoHabilitacion);
        formIntegrante.getTxtEdad().setEditable(estadoHabilitacion);
        formIntegrante.getTxtMaterno().setEditable(estadoHabilitacion);
        formIntegrante.getTxtPaterno().setEditable(estadoHabilitacion);
        formIntegrante.getcBoxSexo().setEnabled(estadoHabilitacion);
    }
    public void limpiarComponentesNuevo()
    {
        formIntegrante.getTxtCodigoEncargado().setText("");
        formIntegrante.getTxtNombreEncargado().setText("");
        formIntegrante.getTxtEdad().setText("");
        formIntegrante.getTxtMaterno().setText("");
        formIntegrante.getTxtPaterno().setText("");
        formIntegrante.getcBoxSexo().setSelectedIndex(0);
    }

    public boolean valicarComponentesNuevo()
    {
        if(formIntegrante.getTxtCodigoEncargado().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formIntegrante, "No puede dejar en blanco el codigo o Ci del integrante", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formIntegrante.getTxtCodigoEncargado().grabFocus();
            return false;
        }

        if(formIntegrante.getTxtNombreEncargado().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formIntegrante, "No puede dejar en blanco el Nombre del integrante", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formIntegrante.getTxtNombreEncargado().grabFocus();
            return false;
        }

        if(formIntegrante.getTxtPaterno().getText().isEmpty()
                && formIntegrante.getTxtMaterno().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formIntegrante, "No ha ingresado ninguno de los apellidos del integrante", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formIntegrante.getTxtPaterno().grabFocus();
            return false;
        }

//        if(formIntegrante.getTxtPaterno().getText().isEmpty())
//        {
//            JOptionPane.showMessageDialog(formIntegrante, "No puede dejar en blanco el apellido Paterno del integrante", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
//            formIntegrante.getTxtNombreEncargado().grabFocus();
//            return false;
//        }
//
//        if(formIntegrante.getTxtMaterno().getText().isEmpty())
//        {
//            if(JOptionPane.showConfirmDialog(formIntegrante, "No puede dejar en blanco el Apellido Materno del integrante, Desea dejar en blanco este campo", "Validación de datos", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
//            {
//                formIntegrante.getTxtMaterno().grabFocus();
//                return false;
//            }
//
//        }

        if(formIntegrante.getTxtEdad().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formIntegrante, "No puede dejar en blanco la Edad del Integrantes", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formIntegrante.getTxtEdad().grabFocus();
            return false;
        }

        if(Integer.parseInt(formIntegrante.getTxtEdad().getText()) > 105)
        {
            JOptionPane.showMessageDialog(formIntegrante, "La Edad del Integrante sobrepasa a la Maxima Permitida", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formIntegrante.getTxtEdad().grabFocus();
            return false;
        }

        if(formIntegrante.getcBoxSexo().getSelectedIndex() <= 0)
        {
            JOptionPane.showMessageDialog(formIntegrante, "Aún no ha seleccionado el Sexo del integrante", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formIntegrante.getcBoxSexo().grabFocus();
            return false;
        }
        return true;
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
            cargarDatosIntegrante(modeloIntegranteEdicion.getIntegrantes(indice));
            formIntegrante.getBtnModificar().setEnabled(true);
        }
        else
            formIntegrante.getBtnModificar().setEnabled(false);
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public void configuracionFormulario() {
        if (usuario != null) {
            int indice = -1;
            SistemaFormularios formulario = new SistemaFormularios();
            formulario.setNombre_formulario(FormUtilities.getClassName(formIntegrante.getClass()));
            SistemaFormulariosPermisosUsuarios permisosProgramas = new SistemaFormulariosPermisosUsuarios();
            permisosProgramas.setSistemaFormulario(formulario);

            indice = Collections.binarySearch(usuario.getListadoInterfacesPermisos(), permisosProgramas, new ComparadorIdFormulario());
            if (indice >= 0) {
                permisosProgramas = usuario.getListadoInterfacesPermisos().get(indice);

                if (!permisosProgramas.isPermitir_insertar()) {
                    formIntegrante.getjTabbedPaneIntegrantes().remove(formIntegrante.getPnlTabPageInsertar());
                }
                if (!permisosProgramas.isPermitir_editar()) {
                    formIntegrante.getjTabbedPaneIntegrantes().remove(formIntegrante.getPnlTabPageEditar());
                }
                if (!permisosProgramas.isPermitir_anular() && !permisosProgramas.isPermitir_navegar()) {
                    formIntegrante.getjTabbedPaneIntegrantes().remove(formIntegrante.getPnlTabPageEliminar());
                } else {
                    formIntegrante.getBtnBuscarEliminar().setVisible(permisosProgramas.isPermitir_navegar());
                    formIntegrante.getBtnEliminar().setVisible(permisosProgramas.isPermitir_anular());
                }


            } else {
                System.out.println("no tiene permisos, nombre de la Clase " + formulario.getNombre_formulario());
            }
        }
    }
}
