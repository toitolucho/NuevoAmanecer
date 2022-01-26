/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Padrinos;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormularios;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormulariosPermisosUsuarios;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.utils.ComparadorIdFormulario;
import bo.usfx.utils.FormUtilities;
import bo.usfx.utils.GeneraReport;
import bo.usfx.utils.ModeloPatrocinador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
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
import view.GuiPatrocinador;
import view.GuiPatrocinadorBuscador;
import view.GuiPrincipal;


public class CGuiPatrocinador extends KeyAdapter implements ActionListener , ListSelectionListener, MouseListener {

    GuiPatrocinador formPatrocinador;
    CommonDao dao;
    ModeloPatrocinador modeloPatrocinadorE;
    ModeloPatrocinador modeloPatrocinadorX;
    private Connection conexion;
    private int idFormulario;
    private Usuarios usuario;
    private GeneraReport gr;
    

    public CGuiPatrocinador(GuiPatrocinador formPatrocinador) {
        this.formPatrocinador = formPatrocinador;
        gr = new GeneraReport();
    }

    public CommonDao getDao() {
        return dao;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    public void cargarDatosPatrocinador(Padrinos patrocinador)
    {
        if(patrocinador != null)
        {
            formPatrocinador.getTxtCodigoPatrocinadorE().setText(String.valueOf(patrocinador.getCodigo_padrino()));
            formPatrocinador.getTxtNombreE().setText(patrocinador.getNombre());
            formPatrocinador.getTxtPaternoE().setText(patrocinador.getApellido_paterno());
            formPatrocinador.getTxtMaternoE().setText(patrocinador.getApellido_materno());
            formPatrocinador.getTxtNombreE2().setText(patrocinador.getNombre2());
            formPatrocinador.getTxtPaternoE2().setText(patrocinador.getApellido_paterno2());
            formPatrocinador.getTxtMaternoE2().setText(patrocinador.getApellido_materno2());
            formPatrocinador.getTxtNumeroPatrocinioE().setText(String.valueOf(patrocinador.getNumero_padrino()));
        }
    }
    
    public boolean  validarDatosEdicion()
    {
        if(formPatrocinador.getTxtCodigoPatrocinadorE().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formPatrocinador, "No Puede Dejar en Blanco el Codigo de Patrocinador", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formPatrocinador.getTxtCodigoPatrocinadorE().grabFocus();
            return false;
        }
        
        if(formPatrocinador.getTxtNombreE().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formPatrocinador, "No Puede Dejar en Blanco el Nombre del Patrocinador", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formPatrocinador.getTxtNombreE().grabFocus();
            return false;
        }

        if(formPatrocinador.getTxtPaternoE().getText().isEmpty()
                && formPatrocinador.getTxtMaternoE().getText().isEmpty()){
            JOptionPane.showMessageDialog(formPatrocinador, "Debe al menos introducir uno de los Apellidos", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formPatrocinador.getTxtPaternoE().grabFocus();
            return false;
        }

        if(formPatrocinador.getTxtPaternoE().getText().isEmpty())
        {
//            JOptionPane.showMessageDialog(formPatrocinador, "No Puede Dejar en Blanco el Apellido Paterno del Patrocinador", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            if(JOptionPane.showConfirmDialog(formPatrocinador, "Desea dejar en blanco el Apellido Paterno del Patrocinador", "Validación de datos", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
            {
                formPatrocinador.getTxtPaternoE().grabFocus();
                return false;
            }

        }

        if(formPatrocinador.getTxtMaternoE().getText().isEmpty())
        {
//            JOptionPane.showMessageDialog(formPatrocinador, "No Puede Dejar en Blanco el Apellido Materno del Patrocinador", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            if(JOptionPane.showConfirmDialog(formPatrocinador, "Desea dejar en blanco el Apellido Materno del Patrocinador", "Validación de datos", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
            {
                formPatrocinador.getTxtMaternoE().grabFocus();
                return false;
            }

        }

        if(formPatrocinador.getTxtNumeroPatrocinioE().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formPatrocinador, "No Puede Dejar en Blanco el Numero de Patrocinador", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formPatrocinador.getTxtNumeroPatrocinioE().grabFocus();
            return false;
        }
        return true;
    }

    public boolean  validarDatosNuevo()
    {
        if(formPatrocinador.getTxtCodigoPatrocinador().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formPatrocinador, "No Puede Dejar en Blanco el Codigo de Patrocinador", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formPatrocinador.getTxtCodigoPatrocinador().grabFocus();
            return false;
        }
        
        if(formPatrocinador.getTxtNombres().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formPatrocinador, "No Puede Dejar en Blanco el Nombre del Patrocinador", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formPatrocinador.getTxtNombres().grabFocus();
            return false;
        }

        if(formPatrocinador.getTxtPaterno().getText().isEmpty()
                && formPatrocinador.getTxtMaterno().getText().isEmpty()){
            JOptionPane.showMessageDialog(formPatrocinador, "Debe al menos introducir uno de los Apellidos", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formPatrocinador.getTxtPaterno().grabFocus();
            return false;
        }

        if(formPatrocinador.getTxtPaterno().getText().isEmpty())
        {
//            JOptionPane.showMessageDialog(formPatrocinador, "No Puede Dejar en Blanco el Apellido Paterno del Patrocinador", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            if(JOptionPane.showConfirmDialog(formPatrocinador, "Desea dejar en blanco el Apellido Paterno del Patrocinador", "Validación de datos", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
            {
                formPatrocinador.getTxtPaterno().grabFocus();
                return false;
            }

        }

        if(formPatrocinador.getTxtMaterno().getText().isEmpty())
        {
//            JOptionPane.showMessageDialog(formPatrocinador, "No Puede Dejar en Blanco el Apellido Materno del Patrocinador", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            if(JOptionPane.showConfirmDialog(formPatrocinador, "Desea dejar en blanco el Apellido Materno del Patrocinador", "Validación de datos", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
            {
                formPatrocinador.getTxtMaterno().grabFocus();
                return false;
            }

        }
        if(formPatrocinador.getTxtNumeroPatrocinio().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formPatrocinador, "No Puede Dejar en Blanco el Numero de Patrocinador", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formPatrocinador.getTxtNumeroPatrocinio().grabFocus();
            return false;
        }
        return true;
    }

    public void limpiarCamposEdicion()
    {
        this.formPatrocinador.getTxtCodigoPatrocinadorE().setText("");
        this.formPatrocinador.getTxtNumeroPatrocinioE().setText("");
        this.formPatrocinador.getTxtNombreE().setText("");
        this.formPatrocinador.getTxtPaternoE().setText("");
        this.formPatrocinador.getTxtMaternoE().setText("");
         this.formPatrocinador.getTxtNombreE2().setText("");
        this.formPatrocinador.getTxtPaternoE2().setText("");
        this.formPatrocinador.getTxtMaternoE2().setText("");
    }

    public void limpiarCamposNuevo()
    {
        this.formPatrocinador.getTxtCodigoPatrocinador().setText("");
        this.formPatrocinador.getTxtNumeroPatrocinio().setText("");
        this.formPatrocinador.getTxtNombres().setText("");
        this.formPatrocinador.getTxtPaterno().setText("");
        this.formPatrocinador.getTxtMaterno().setText("");
        this.formPatrocinador.getTxtNombres2().setText("");
        this.formPatrocinador.getTxtPaterno2().setText("");
        this.formPatrocinador.getTxtMaterno2().setText("");
    }

    public void habilitarControlNuevo(boolean estadoHabilitacion)
    {
//        this.formPatrocinador.getTxtCodigoPatrocinador().setEditable(estadoHabilitacion);
        this.formPatrocinador.getTxtNumeroPatrocinio().setEditable(estadoHabilitacion);
        this.formPatrocinador.getTxtNombres().setEditable(estadoHabilitacion);
        this.formPatrocinador.getTxtPaterno().setEditable(estadoHabilitacion);
        this.formPatrocinador.getTxtMaterno().setEditable(estadoHabilitacion);
        this.formPatrocinador.getTxtNombres2().setEditable(estadoHabilitacion);
        this.formPatrocinador.getTxtPaterno2().setEditable(estadoHabilitacion);
        this.formPatrocinador.getTxtMaterno2().setEditable(estadoHabilitacion);
    }

     public void habilitarControlEdicion(boolean estadoHabilitacion)
    {
//        this.formPatrocinador.getTxtCodigoPatrocinadorE().setEditable(estadoHabilitacion);
        this.formPatrocinador.getTxtNumeroPatrocinioE().setEditable(estadoHabilitacion);
        this.formPatrocinador.getTxtNombreE().setEditable(estadoHabilitacion);
        this.formPatrocinador.getTxtPaternoE().setEditable(estadoHabilitacion);
        this.formPatrocinador.getTxtMaternoE().setEditable(estadoHabilitacion);
        this.formPatrocinador.getTxtNombreE2().setEditable(estadoHabilitacion);
        this.formPatrocinador.getTxtPaternoE2().setEditable(estadoHabilitacion);
        this.formPatrocinador.getTxtMaternoE2().setEditable(estadoHabilitacion);
    }
    
    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();
        if(accion.compareTo(formPatrocinador.getBtnNuevo().getActionCommand()) == 0)
        {
            formPatrocinador.getBtnRegistrar().setEnabled(true);
            formPatrocinador.getBtnNuevo().setEnabled(false);
            limpiarCamposNuevo();
            habilitarControlNuevo(true);
            formPatrocinador.getTxtCodigoPatrocinador().grabFocus();
            try {
                int CodigoPatrocinador = dao.obtenerUltimoObjetoTabla(new Padrinos(), "");
                CodigoPatrocinador = CodigoPatrocinador == -1 ? 1 : ++CodigoPatrocinador;
                formPatrocinador.getTxtCodigoPatrocinador().setText(String.valueOf(CodigoPatrocinador));
            } catch (SQLException ex) {
                Logger.getLogger(CGuiPatrocinador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if(accion.compareTo(formPatrocinador.getBtnBuscar().getActionCommand()) == 0)
        {
            GuiPatrocinadorBuscador formBuscador = new GuiPatrocinadorBuscador(formPatrocinador, true);
            formBuscador.control.setDao(dao);
            formBuscador.control.onFormShown();
            formBuscador.control.establecerOpcionesbusqueda(formPatrocinador.getTxtTextoBusqueda().getText());
            FormUtilities.centrar2(formBuscador, formPatrocinador);
            formBuscador.setVisible(true);
            formBuscador.dispose();
            
        }

        if(accion.compareTo(formPatrocinador.getBtnRegistrar().getActionCommand()) == 0)
        {
            if(validarDatosNuevo())
            {
                try {
                    Padrinos padrino = new Padrinos(
                            1,
                            Integer.parseInt(formPatrocinador.getTxtNumeroPatrocinio().getText()),
                            formPatrocinador.getTxtNombres().getText(),
                            formPatrocinador.getTxtPaterno().getText(),
                            formPatrocinador.getTxtMaterno().getText(),
                            formPatrocinador.getTxtNombres2().getText(),
                            formPatrocinador.getTxtPaterno2().getText(),
                            formPatrocinador.getTxtMaterno2().getText());

                    dao.insertar(padrino);

                    JOptionPane.showMessageDialog(formPatrocinador, "Registro satisfactorio");
                    formPatrocinador.getBtnRegistrar().setEnabled(false);
                    formPatrocinador.getBtnNuevo().setEnabled(true);
                    habilitarControlNuevo(false);
                } catch (Exception ex) {
                    ex.printStackTrace();;
                    JOptionPane.showMessageDialog(formPatrocinador, "Ocurrió la siguiente Excepción" + ex.getMessage(), "Erro de Inserción", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if(accion.compareTo(formPatrocinador.getBtnModificar().getActionCommand()) == 0)
        {
            formPatrocinador.getBtnModificar().setEnabled(false);
            formPatrocinador.getBtnGuardarEdicion().setEnabled(true);
            formPatrocinador.getjTablePatrocinadorE().setEnabled(false);

            habilitarControlEdicion(true);
            formPatrocinador.getTxtNombreE().grabFocus();
        }
        if(accion.compareTo(formPatrocinador.getBtnGuardarEdicion().getActionCommand()) == 0)
        {
            if(validarDatosEdicion())
            {
                try {
                    Padrinos padrino = new Padrinos(
                            Integer.parseInt(formPatrocinador.getTxtCodigoPatrocinadorE().getText()),
                            Integer.parseInt(formPatrocinador.getTxtNumeroPatrocinioE().getText()),
                            formPatrocinador.getTxtNombreE().getText(),
                            formPatrocinador.getTxtPaternoE().getText(),
                            formPatrocinador.getTxtMaternoE().getText(),
                            formPatrocinador.getTxtNombreE2().getText(),
                            formPatrocinador.getTxtPaternoE2().getText(),
                            formPatrocinador.getTxtMaternoE2().getText());

                    dao.edit(padrino);

                    JOptionPane.showMessageDialog(formPatrocinador, "Actualización satisfactoria");
                    formPatrocinador.getBtnModificar().setEnabled(true);
                    formPatrocinador.getBtnGuardarEdicion().setEnabled(false);
                    habilitarControlEdicion(false);
                } catch (Exception ex) {
                    ex.printStackTrace();;
                    JOptionPane.showMessageDialog(formPatrocinador, "Ocurrió la siguiente Excepción" + ex.getMessage(), "Error de Actualización", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if(accion.compareTo("buscarE") == 0)
        {
            try {
                int CodigoPatrocinador = -1;
                try {
                    CodigoPatrocinador = Integer.parseInt(formPatrocinador.getTxtTextoBusquedaE().getText());
                } catch (NumberFormatException ext) {
                }
                Padrinos patrocinador = new Padrinos();
                patrocinador.setNombre(formPatrocinador.getTxtTextoBusquedaE().getText());
                patrocinador.setCodigo_padrino(CodigoPatrocinador);

                List<Padrinos> listaPatrocinadores = dao.findObjects(patrocinador);
                modeloPatrocinadorE.clear();
                if(listaPatrocinadores != null && !listaPatrocinadores.isEmpty())
                {
                    for (Padrinos padrinos : listaPatrocinadores) {
                        modeloPatrocinadorE.addPadrinos(padrinos);
                    }
                    formPatrocinador.getBtnModificar().setEnabled(true);
                    formPatrocinador.getjTablePatrocinadorE().setEnabled(true);
                }
                else
                {
                    formPatrocinador.getBtnModificar().setEnabled(false);
                    formPatrocinador.getjTablePatrocinadorE().setEnabled(false);
                    JOptionPane.showMessageDialog(formPatrocinador, "No se encontrón ningun registro asociado a los parametros provistos", "Patrocinadores", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(accion.compareTo("buscarX") == 0)
        {
            try {
                int CodigoPatrocinador = -1;
                try {
                    CodigoPatrocinador = Integer.parseInt(formPatrocinador.getTxtTextoBusquedaX().getText());
                } catch (NumberFormatException ext) {
                }
                Padrinos patrocinador = new Padrinos();
                patrocinador.setNombre(formPatrocinador.getTxtTextoBusquedaX().getText());
                patrocinador.setCodigo_padrino(CodigoPatrocinador);

                List<Padrinos> listaPatrocinadores = dao.findObjects(patrocinador);
                modeloPatrocinadorX.clear();
                if(listaPatrocinadores != null && !listaPatrocinadores.isEmpty())
                {
                    for (Padrinos padrinos : listaPatrocinadores) {
                        modeloPatrocinadorX.addPadrinos(padrinos);
                    }
                    formPatrocinador.getBtnEliminar().setEnabled(true);

                }
                else
                {
                    formPatrocinador.getBtnEliminar().setEnabled(false);
                    JOptionPane.showMessageDialog(formPatrocinador, "No se encontrón ningun registro asociado a los parametros provistos", "Patrocinadores", JOptionPane.WARNING_MESSAGE);
                    
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(accion.compareTo("eliminar") == 0)
        {
            System.out.println("Eliminar Padrino");
            try {
                int indiceEliminacion = formPatrocinador.getjTablePatrocinadorX().getSelectedRow();
            if(indiceEliminacion >= 0
                    && JOptionPane.showConfirmDialog(formPatrocinador, "¿Se encuentra seguro de Eliminar el Registro?",
                    "Eliminación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {

                Padrinos patrocinador = modeloPatrocinadorX.getPadrinos(indiceEliminacion);
                dao.delete(patrocinador);
                modeloPatrocinadorX.removePadrinos(indiceEliminacion);
                JOptionPane.showMessageDialog(formPatrocinador, "Registro Eliminado");
            }
            else
            {
                JOptionPane.showMessageDialog(formPatrocinador, "Aún no ha seleccionado ninguna fila en la Tabla de Busqueda", "Eliminación", JOptionPane.ERROR_MESSAGE);
            }
            } catch (Exception ez) {
                ez.printStackTrace();
                JOptionPane.showMessageDialog(formPatrocinador, "Ocurrio la siguiente Excepción " + ez.getMessage(), "Eliminación", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public void onFormShown()
    {
        limpiarCamposEdicion();
        limpiarCamposNuevo();
        habilitarControlEdicion(false);
        habilitarControlNuevo(false);
        modeloPatrocinadorE = new ModeloPatrocinador();
        this.formPatrocinador.getjTablePatrocinadorE().setModel(modeloPatrocinadorE);
        modeloPatrocinadorX = new ModeloPatrocinador();
        this.formPatrocinador.getjTablePatrocinadorX().setModel(modeloPatrocinadorX);

        this.formPatrocinador.getBtnNuevo().setEnabled(true);
        this.formPatrocinador.getBtnRegistrar().setEnabled(false);
        this.formPatrocinador.getBtnModificar().setEnabled(false);
        this.formPatrocinador.getBtnGuardarEdicion().setEnabled(false);
        formPatrocinador.getjTablePatrocinadorE().getSelectionModel().addListSelectionListener(this);
        this.formPatrocinador.getTxtCodigoPatrocinador().setEditable(false);
        this.formPatrocinador.getTxtCodigoPatrocinadorE().setEditable(false);
        configuracionFormulario();
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
            cargarDatosPatrocinador(modeloPatrocinadorE.getPadrinos(indice));
            formPatrocinador.getBtnModificar().setEnabled(true);
        }
        else
            formPatrocinador.getBtnModificar().setEnabled(false);
    }

    public void configuracionFormulario() {
        if (usuario != null) {
            int indice = -1;
            SistemaFormularios formulario = new SistemaFormularios();
            formulario.setNombre_formulario(FormUtilities.getClassName(formPatrocinador.getClass()));
            SistemaFormulariosPermisosUsuarios permisosPatrocinador = new SistemaFormulariosPermisosUsuarios();
            permisosPatrocinador.setSistemaFormulario(formulario);

            indice = Collections.binarySearch(usuario.getListadoInterfacesPermisos(), permisosPatrocinador, new ComparadorIdFormulario());
            if (indice >= 0) {
                permisosPatrocinador = usuario.getListadoInterfacesPermisos().get(indice);

                if(!permisosPatrocinador.isPermitir_insertar())
                    formPatrocinador.getjTabbedPanePatrocinadores().remove(formPatrocinador.getPnlTabPageInsertar());
                if(!permisosPatrocinador.isPermitir_editar())
                    formPatrocinador.getjTabbedPanePatrocinadores().remove(formPatrocinador.getPnlTabPageEditar());
                if(!permisosPatrocinador.isPermitir_anular() && !permisosPatrocinador.isPermitir_navegar())
                    formPatrocinador.getjTabbedPanePatrocinadores().remove(formPatrocinador.getPnlTabPageEliminar());
                else
                {
                    formPatrocinador.getBtnBuscarEliminar().setVisible(permisosPatrocinador.isPermitir_navegar());
                    formPatrocinador.getBtnEliminar().setVisible(permisosPatrocinador.isPermitir_anular());
                }


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

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }


    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        JTextField componente = (JTextField) e.getSource();
        char c = e.getKeyChar();
        if (componente.equals(formPatrocinador.getTxtCodigoPatrocinador()) ||
              componente.equals(formPatrocinador.getTxtCodigoPatrocinadorE()) ||
              componente.equals(formPatrocinador.getTxtNumeroPatrocinio()) ||
              componente.equals(formPatrocinador.getTxtNumeroPatrocinioE())) {

                if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                                || (c == KeyEvent.VK_DELETE) ))) {
                        formPatrocinador.getToolkit().beep();
                        e.consume();
                }
        }else if(componente.equals(formPatrocinador.getTxtNombres())
                || componente.equals(formPatrocinador.getTxtNombres2())
                || componente.equals(formPatrocinador.getTxtNombreE())
                || componente.equals(formPatrocinador.getTxtNombreE2())
                || componente.equals(formPatrocinador.getTxtPaterno())
                || componente.equals(formPatrocinador.getTxtPaternoE())
                || componente.equals(formPatrocinador.getTxtPaterno2())
                || componente.equals(formPatrocinador.getTxtPaternoE2())
                || componente.equals(formPatrocinador.getTxtMaterno())
                || componente.equals(formPatrocinador.getTxtMaternoE())
                || componente.equals(formPatrocinador.getTxtMaterno2())
                || componente.equals(formPatrocinador.getTxtMaternoE2())
                )
        {
            if (!((Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE)
                                || (c == KeyEvent.VK_DELETE)
                                || (c == KeyEvent.VK_SPACE)))) {
                        formPatrocinador.getToolkit().beep();
                        e.consume();
                }
        }
    }

    public void mouseClicked(MouseEvent e)
    {
            String rutaLocal = GuiPrincipal.class.getResource("GuiPrincipal.class").getPath().substring(1).replaceAll("view/GuiPrincipal.class", "").trim().replace("%20", " ");
            String path = rutaLocal + "view/";
            gr.loadReportJasper(path + "reportePadrinos.jasper");
            gr.setParameters("RutaLogo",path + "cristianchildrenazul.jpg");
            gr.fillReport(conexion);
            gr.previewReport();
    }

    public void mousePressed(MouseEvent e) {
        
    }

    public void mouseReleased(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }


}
