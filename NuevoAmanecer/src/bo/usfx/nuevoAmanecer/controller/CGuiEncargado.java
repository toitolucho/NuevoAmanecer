/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Encargados;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormularios;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormulariosPermisosUsuarios;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.utils.ComparadorIdFormulario;
import bo.usfx.utils.FormUtilities;
import bo.usfx.utils.ModeloEncargado;
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
import view.GuiEncargado;


public class CGuiEncargado extends KeyAdapter implements ActionListener, ListSelectionListener {

    private GuiEncargado formEncargado;
    private CommonDao dao;
    private Encargados encargado;
    ModeloEncargado modeloEncargadoE;
    ModeloEncargado modeloEncargadoX;
    private Usuarios usuario;
    

    public CGuiEncargado(GuiEncargado formEncargado) {
        this.formEncargado = formEncargado;
        encargado = new Encargados();
        modeloEncargadoE = new ModeloEncargado();
        modeloEncargadoX = new ModeloEncargado();
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }


    

    public void actionPerformed(ActionEvent event) {
        String accion = event.getActionCommand();
        if(accion.compareTo(formEncargado.getBtnNuevo().getActionCommand()) == 0)
        {
            formEncargado.getBtnRegistrar().setEnabled(true);
            formEncargado.getBtnNuevo().setEnabled(false);

            limpiarCamposNuevo();;
            habilitarComponentesNuevo(true);
        }

        if(accion.compareTo(formEncargado.getBtnRegistrar().getActionCommand()) == 0)
        {
            if(ValidarCamposNuevo())
            {
                formEncargado.getBtnRegistrar().setEnabled(false);
                formEncargado.getBtnNuevo().setEnabled(true);
                habilitarComponentesNuevo(false);

                try {
                    encargado = new Encargados();
                    encargado.setCi(formEncargado.getTxtCi().getText().isEmpty() ? 0 : Integer.parseInt(formEncargado.getTxtCi().getText()));
                    encargado.setSexo(formEncargado.getcBoxSexo().getSelectedItem().toString().substring(0,1));
                    encargado.setNombres(formEncargado.getTxtNombres().getText());
                    encargado.setApellido_materno(formEncargado.getTxtMaterno().getText());
                    encargado.setApellido_paterno(formEncargado.getTxtPaterno().getText());
                    encargado.setEdad(Integer.parseInt(formEncargado.getTxtEdad().getText()));
                    encargado.setTelefono(formEncargado.getTxtTelefono().getText().isEmpty() ? 0 : Integer.parseInt(formEncargado.getTxtTelefono().getText()));
                    encargado.setDomicilio(formEncargado.getTxtDomicilio().getText());
                    encargado.setProfesion(formEncargado.getTxtProfesion().getText());
                    dao.insertar(encargado);
                    mostrarMensajeInformacion("Registro Satisfactorio", "Se registro satisfactoriamente el registro");

                    //sugerencia de Sil
                    this.limpiarCamposNuevo();
                } catch (Exception e) {
                    mostrarMensajeError("Registro Incompleto", "No se pudo registrar satisfactoriamente el registro, ocurrio la siguiente Excepcion " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        if(accion.compareTo(formEncargado.getBtnModificarE().getActionCommand()) == 0)
        {
            formEncargado.getBtnModificarE().setEnabled(false);
            formEncargado.getBtnGuardarE().setEnabled(true);
            habilitarComponentesEdicion(true);
        }

        if(accion.compareTo(formEncargado.getBtnGuardarE().getActionCommand()) == 0)
        {
            if(ValidarCamposEdicion())
            {
                formEncargado.getBtnModificarE().setEnabled(true);
                formEncargado.getBtnGuardarE().setEnabled(false);
                habilitarComponentesEdicion(false);

                try {                    
                    encargado.setCi(Integer.parseInt(formEncargado.getTxtCiE().getText()));
                    encargado.setSexo(formEncargado.getcBoxSexoE().getSelectedItem().toString().substring(0,1));
                    encargado.setNombres(formEncargado.getTxtNombresE().getText());
                    encargado.setApellido_materno(formEncargado.getTxtMaternoE().getText());
                    encargado.setApellido_paterno(formEncargado.getTxtPaternoE().getText());
                    encargado.setEdad(Integer.parseInt(formEncargado.getTxtEdadE().getText()));
                    encargado.setTelefono(Integer.parseInt(formEncargado.getTxtTelefonoE().getText()));
                    encargado.setDomicilio(formEncargado.getTxtDomicilioE().getText());
                    encargado.setProfesion(formEncargado.getTxtProfesionE().getText());
                    dao.edit(encargado);
                    if(this.formEncargado.getjTableEncargadosE().getSelectedRow() >= 0)
                        modeloEncargadoE.editEcargado(encargado, this.formEncargado.getjTableEncargadosE().getSelectedRow());
                    mostrarMensajeInformacion("Actualización Satisfactoria", "Se actualizó satisfactoriamente el registro");


                    //sugerencia de Sil
                    this.limpiarCamposEdicion();
                } catch (Exception e) {
                    mostrarMensajeError("Actualización Incorrecta", "No se pudo actualizar satisfactoriamente el registro, ocurrio la siguiente Excepcion " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        if(accion.compareTo(formEncargado.getBtnEliminar().getActionCommand()) == 0)
        {
            if(formEncargado.getjTableEncargadosX().getSelectedRow() < 0)
            {
                mostrarMensajeError("Gestión de Encargados", "No ha seleccionado ningún registro");
                return;
            }
            int indiceEliminacion = formEncargado.getjTableEncargadosX().getSelectedRow();
            if(indiceEliminacion >= 0 &&
                    JOptionPane.showConfirmDialog(formEncargado, "¿Se Encuentra Seguro de eliminar el registro?", "Eliminación de Registro",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
            {
                try {
                    encargado = modeloEncargadoX.getEncargados(indiceEliminacion);
                    dao.delete(encargado);
                    modeloEncargadoX.removeEncargados(indiceEliminacion);
                    mostrarMensajeInformacion("Eliminación Correcta", "Se Eliminó correctamente al Encargado");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    mostrarMensajeError("Error", "No se Pudo eliminar el registro debido a " + ex.getMessage());
                }
            }
        }



        if(accion.compareTo("buscarE") == 0)
        {
            try {
                int CodigoEncargado = -1;
                try {
                    CodigoEncargado = Integer.parseInt(formEncargado.getTxtTextoBusquedaE().getText());
                } catch (NumberFormatException ext) {
                }
                Encargados encarga = new Encargados();
                encarga.setNombres(formEncargado.getTxtTextoBusquedaE().getText());
                encarga.setCi(CodigoEncargado);

                List<Encargados> listaPatrocinadores = dao.findObjects(encarga);
                modeloEncargadoE.clear();
                if(listaPatrocinadores != null && !listaPatrocinadores.isEmpty())
                {
                    for (Encargados encargaditos : listaPatrocinadores) {
                        modeloEncargadoE.addEncargados(encargaditos);
                    }
                    formEncargado.getBtnModificarE().setEnabled(true);
                    formEncargado.getjTableEncargadosE().setEnabled(true);
                }
                else
                {
                    formEncargado.getBtnModificarE().setEnabled(false);
                    formEncargado.getjTableEncargadosE().setEnabled(false);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(accion.compareTo("buscarX") == 0)
        {
            try {
                int CodigoEncargado = -1;
                try {
                    CodigoEncargado = Integer.parseInt(formEncargado.getTxtTextoBusquedaX().getText());
                } catch (NumberFormatException ext) {
                }
                Encargados patrocinador = new Encargados();
                patrocinador.setNombres(formEncargado.getTxtTextoBusquedaX().getText());
                patrocinador.setCi(CodigoEncargado);

                List<Encargados> listaPatrocinadores = dao.findObjects(patrocinador);
                modeloEncargadoX.clear();
                if(listaPatrocinadores != null && !listaPatrocinadores.isEmpty())
                {
                    for (Encargados padrinos : listaPatrocinadores) {
                        modeloEncargadoX.addEncargados(padrinos);
                    }
                    formEncargado.getBtnEliminar().setEnabled(true);

                }
                else
                {
                    JOptionPane.showMessageDialog(formEncargado, "No se encontró ningun registro con la información provista", "Administración de Encargados", JOptionPane.ERROR_MESSAGE);
                    formEncargado.getBtnEliminar().setEnabled(false);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public void onFormShown()
    {
        habilitarComponentesEdicion(false);
        habilitarComponentesNuevo(false);
        formEncargado.getBtnNuevo().setEnabled(true);
        formEncargado.getBtnRegistrar().setEnabled(false);

        formEncargado.getBtnModificarE().setEnabled(false);
        formEncargado.getBtnGuardarE().setEnabled(false);

        formEncargado.getjTableEncargadosE().setModel(modeloEncargadoE);
        formEncargado.getjTableEncargadosX().setModel(modeloEncargadoX);
        formEncargado.getjTableEncargadosE().getSelectionModel().addListSelectionListener(this);
       configuracionFormulario();
    }
    public void habilitarComponentesNuevo(boolean estadoHabilitacion)
    {
        this.formEncargado.getTxtCi().setEditable(estadoHabilitacion);
        this.formEncargado.getTxtDomicilio().setEditable(estadoHabilitacion);
        this.formEncargado.getTxtEdad().setEditable(estadoHabilitacion);
        this.formEncargado.getTxtMaterno().setEditable(estadoHabilitacion);
        this.formEncargado.getTxtNombres().setEditable(estadoHabilitacion);
        this.formEncargado.getTxtPaterno().setEditable(estadoHabilitacion);
        this.formEncargado.getTxtProfesion().setEditable(estadoHabilitacion);
        this.formEncargado.getTxtTelefono().setEditable(estadoHabilitacion);
        this.formEncargado.getcBoxSexo().setEnabled(estadoHabilitacion);
    }


    public void habilitarComponentesEdicion(boolean estadoHabilitacion)
    {
        this.formEncargado.getTxtCiE().setEditable(estadoHabilitacion);
        this.formEncargado.getTxtDomicilioE().setEditable(estadoHabilitacion);
        this.formEncargado.getTxtEdadE().setEditable(estadoHabilitacion);
        this.formEncargado.getTxtMaternoE().setEditable(estadoHabilitacion);
        this.formEncargado.getTxtNombresE().setEditable(estadoHabilitacion);
        this.formEncargado.getTxtPaternoE().setEditable(estadoHabilitacion);
        this.formEncargado.getTxtProfesionE().setEditable(estadoHabilitacion);
        this.formEncargado.getTxtTelefonoE().setEditable(estadoHabilitacion);
        this.formEncargado.getcBoxSexoE().setEnabled(estadoHabilitacion);
    }


    public void limpiarCamposEdicion()
    {
        this.formEncargado.getTxtCiE().setText("");
        this.formEncargado.getTxtDomicilioE().setText("");
        this.formEncargado.getTxtEdadE().setText("");
        this.formEncargado.getTxtMaternoE().setText("");
        this.formEncargado.getTxtNombresE().setText("");
        this.formEncargado.getTxtPaternoE().setText("");
        this.formEncargado.getTxtProfesionE().setText("");
        this.formEncargado.getTxtTelefonoE().setText("");
        this.formEncargado.getcBoxSexoE().setSelectedIndex(0);
    }

    public void limpiarCamposNuevo()
    {
        this.formEncargado.getTxtCi().setText("");
        this.formEncargado.getTxtDomicilio().setText("");
        this.formEncargado.getTxtEdad().setText("");
        this.formEncargado.getTxtMaterno().setText("");
        this.formEncargado.getTxtNombres().setText("");
        this.formEncargado.getTxtPaterno().setText("");
        this.formEncargado.getTxtProfesion().setText("");
        this.formEncargado.getTxtTelefono().setText("");
        this.formEncargado.getcBoxSexo().setSelectedIndex(0);
    }

    public boolean ValidarCamposNuevo()
    {
        if(formEncargado.getTxtCi().getText().isEmpty())
        {
            mostrarMensajeError("Validación de Datos", "Aún no ha ingresado el Ci");
            formEncargado.getTxtCi().grabFocus();
            formEncargado.getTxtCi().selectAll();
            return false;
        }
        Encargados encargadoValidacion = new Encargados();
        encargadoValidacion.setCi(Integer.valueOf(formEncargado.getTxtCi().getText()));
        try {
            if (dao.existsId(encargadoValidacion)) {
                mostrarMensajeError("Validación de Datos", "El C.I. que desea ingresar ya se encuentra registrado");
                formEncargado.getTxtCi().grabFocus();
                formEncargado.getTxtCi().selectAll();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CGuiEncargado.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(formEncargado.getTxtNombres().getText().isEmpty())
        {
            mostrarMensajeError("Validación de Datos", "Aún no ha ingresado el Nombre del Encargado");
            formEncargado.getTxtNombres().grabFocus();
            formEncargado.getTxtNombres().selectAll();
            return false;
        }
        if(formEncargado.getTxtPaterno().getText().isEmpty()
                && formEncargado.getTxtMaterno().getText().isEmpty())
        {
            mostrarMensajeError("Validación de Datos", "Aún no ha ingresado ningun apellido del Encargado");
            formEncargado.getTxtPaterno().grabFocus();
            formEncargado.getTxtPaterno().selectAll();
            return false;
        }
//        if(formEncargado.getTxtMaterno().getText().isEmpty())
//        {
//            mostrarMensajeError("Validación de Datos", "Aún no ha ingresado el Apellido Materno del encargado");
//            formEncargado.getTxtMaterno().grabFocus();
//            formEncargado.getTxtMaterno().selectAll();
//            return false;
//        }
        if(formEncargado.getTxtProfesion().getText().isEmpty())
        {
            mostrarMensajeError("Validación de Datos", "Aún no ha ingresado la Profesión del encargado");
            formEncargado.getTxtProfesion().grabFocus();
            formEncargado.getTxtProfesion().selectAll();
            return false;
        }
        if(formEncargado.getTxtEdad().getText().trim().isEmpty())
        {
            mostrarMensajeError("Validación de Datos", "Aún no ha ingresado la edad");
            formEncargado.getTxtEdad().grabFocus();
            formEncargado.getTxtEdad().selectAll();
            return false;
        }
        if(Integer.parseInt(formEncargado.getTxtEdad().getText()) > 105)
        {
            mostrarMensajeError("Validación de Datos", "La edad ingresada sobrepasa la Maxima");
            formEncargado.getTxtEdad().grabFocus();
            formEncargado.getTxtEdad().selectAll();
            return false;
        }

        if(formEncargado.getcBoxSexo().getSelectedIndex() == 0)
        {
             mostrarMensajeError("Validación de Datos", "Aún no ha seleccionado el Sexo");
            formEncargado.getcBoxSexo().grabFocus();            
            return false;
        }
        return true;
    }

    public boolean ValidarCamposEdicion()
    {
        if(formEncargado.getTxtCiE().getText().isEmpty())
        {
            mostrarMensajeError("Validación de Datos", "Aún no ha ingresado el Ci");
            formEncargado.getTxtCiE().grabFocus();
            formEncargado.getTxtCiE().selectAll();
            return false;
        }
        if(formEncargado.getTxtNombresE().getText().isEmpty())
        {
            mostrarMensajeError("Validación de Datos", "Aún no ha ingresado el Nombre del Encargado");
            formEncargado.getTxtNombresE().grabFocus();
            formEncargado.getTxtNombresE().selectAll();
            return false;
        }
        if(formEncargado.getTxtPaternoE().getText().isEmpty()
                && formEncargado.getTxtMaternoE().getText().isEmpty())
        {
            mostrarMensajeError("Validación de Datos", "Aún no ha ingresado ninguno de los apellidos del Encargado");
            formEncargado.getTxtPaternoE().grabFocus();
            formEncargado.getTxtPaternoE().selectAll();
            return false;
        }
//        if(formEncargado.getTxtMaternoE().getText().isEmpty())
//        {
//            mostrarMensajeError("Validación de Datos", "Aún no ha ingresado el Apellido Materno del encargado");
//            formEncargado.getTxtMaternoE().grabFocus();
//            formEncargado.getTxtMaternoE().selectAll();
//            return false;
//        }
        if(formEncargado.getTxtProfesionE().getText().isEmpty())
        {
            mostrarMensajeError("Validación de Datos", "Aún no ha ingresado la Profesión del encargado");
            formEncargado.getTxtProfesionE().grabFocus();
            formEncargado.getTxtProfesionE().selectAll();
            return false;
        }
        if(formEncargado.getcBoxSexoE().getSelectedIndex() == 0)
        {
             mostrarMensajeError("Validación de Datos", "Aún no ha ingresado el Sexo del Encargado");
            formEncargado.getcBoxSexo().grabFocus();
            return false;
        }

        if(Integer.parseInt(formEncargado.getTxtEdadE().getText()) > 105)
        {
            mostrarMensajeError("Validación de Datos", "La edad ingresada sobrepasa la Maxima");
            formEncargado.getTxtEdadE().grabFocus();
            formEncargado.getTxtEdadE().selectAll();
            return false;
        }

        if(formEncargado.getTxtEdadE().getText().trim().isEmpty())
        {
            mostrarMensajeError("Validación de Datos", "Aún no ha ingresado la edad");
            formEncargado.getTxtEdadE().grabFocus();
            formEncargado.getTxtEdadE().selectAll();
            return false;
        }
        return true;
    }

    public void cargarDatosEncargado(Encargados persona)
    {
        this.formEncargado.getTxtCiE().setText(String.valueOf(persona.getCi()));
        this.formEncargado.getTxtDomicilioE().setText(persona.getDomicilio());
        this.formEncargado.getTxtEdadE().setText(String.valueOf(persona.getEdad()));
        this.formEncargado.getTxtMaternoE().setText(persona.getApellido_materno());
        this.formEncargado.getTxtNombresE().setText(persona.getNombres());
        this.formEncargado.getTxtPaternoE().setText(persona.getApellido_paterno());
        this.formEncargado.getTxtProfesionE().setText(persona.getProfesion());
        this.formEncargado.getTxtTelefonoE().setText(String.valueOf(persona.getTelefono()));
        this.formEncargado.getcBoxSexoE().setSelectedIndex(persona.esMasculino() ? 2 : 1);
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
            cargarDatosEncargado(modeloEncargadoE.getEncargados(indice));
            formEncargado.getBtnModificarE().setEnabled(true);
        }
        else
            formEncargado.getBtnModificarE().setEnabled(false);
    }

    public void mostrarMensajeError(String Titulo, String Mensaje)
    {
        JOptionPane.showMessageDialog(formEncargado, Mensaje, Titulo, JOptionPane.ERROR_MESSAGE);
    }
    public void mostrarMensajeInformacion(String Titulo, String Mensaje)
    {
        JOptionPane.showMessageDialog(formEncargado, Mensaje, Titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        JTextField componente = (JTextField) e.getSource();
        char c = e.getKeyChar();
        if (componente.equals(formEncargado.getTxtCi()) ||
              componente.equals(formEncargado.getTxtCiE()) ||
              componente.equals(formEncargado.getTxtTelefono()) ||
              componente.equals(formEncargado.getTxtTelefonoE()) ||
              componente.equals(formEncargado.getTxtEdad()) ||
              componente.equals(formEncargado.getTxtEdadE())) {

                if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                                || (c == KeyEvent.VK_DELETE) ))) {
                        formEncargado.getToolkit().beep();
                        e.consume();
                }
        }else if(componente.equals(formEncargado.getTxtNombres())
                || componente.equals(formEncargado.getTxtNombresE())
                || componente.equals(formEncargado.getTxtPaterno())
                || componente.equals(formEncargado.getTxtPaternoE())
                || componente.equals(formEncargado.getTxtProfesion())
                || componente.equals(formEncargado.getTxtProfesionE())
                || componente.equals(formEncargado.getTxtMaterno())
                || componente.equals(formEncargado.getTxtMaternoE()))
        {
            if (!((Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE)
                                || (c == KeyEvent.VK_DELETE)
                                || (c == KeyEvent.VK_SPACE)))) {
                        formEncargado.getToolkit().beep();
                        e.consume();
                }
        }
   }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public void configuracionFormulario() {
        if (usuario != null) {
            int indice = -1;
            SistemaFormularios formulario = new SistemaFormularios();
            formulario.setNombre_formulario(FormUtilities.getClassName(formEncargado.getClass()));
            SistemaFormulariosPermisosUsuarios permisosProgramas = new SistemaFormulariosPermisosUsuarios();
            permisosProgramas.setSistemaFormulario(formulario);

            indice = Collections.binarySearch(usuario.getListadoInterfacesPermisos(), permisosProgramas, new ComparadorIdFormulario());
            if (indice >= 0) {
                permisosProgramas = usuario.getListadoInterfacesPermisos().get(indice);

                if (!permisosProgramas.isPermitir_insertar()) {
                    formEncargado.getjTabbedPaneEncargado().remove(formEncargado.getPnlTabPageInsertar());
                }
                if (!permisosProgramas.isPermitir_editar()) {
                    formEncargado.getjTabbedPaneEncargado().remove(formEncargado.getPnlTabPageEditar());
                }
                if (!permisosProgramas.isPermitir_anular() && !permisosProgramas.isPermitir_navegar()) {
                    formEncargado.getjTabbedPaneEncargado().remove(formEncargado.getPnlTabPageEliminar());
                } else {
                    formEncargado.getBtnBuscarX().setVisible(permisosProgramas.isPermitir_navegar());
                    formEncargado.getBtnEliminar().setVisible(permisosProgramas.isPermitir_anular());
                }


            } else {
                System.out.println("no tiene permisos, nombre de la Clase " + formulario.getNombre_formulario());
            }
        }
    }

}
