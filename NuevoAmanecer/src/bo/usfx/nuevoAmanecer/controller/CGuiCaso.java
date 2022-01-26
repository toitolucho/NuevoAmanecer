/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Afiliado;
import bo.usfx.nuevoAmanecer.model.domain.Casos;
import bo.usfx.nuevoAmanecer.model.domain.Padrinos;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormularios;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormulariosPermisosUsuarios;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.utils.ComparadorIdFormulario;
import bo.usfx.utils.FormUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import view.GuiAfiliadoBuscador;
import view.GuiCaso;
import view.GuiCasosBuscador;
import view.GuiPatrocinadorBuscador;

/**
 *
 * @author Luis Molina
 */
public class CGuiCaso extends KeyAdapter implements ActionListener, DocumentListener{
    private GuiCaso formCaso;
    private String tipoOperacion ="";
    private CommonDao dao;
    private Usuarios usuario;

    public CGuiCaso(GuiCaso formCaso)
    {
        this.formCaso = formCaso;
    }

    public void onFormShown()
    {
        limpiarCampos();
        habilitarControles(false);
        habilitarBotones(true, false, false, false, true, false);
        configuracionFormulario();
    }

    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();
        if(accion.compareTo("nuevo")==0)
        {
            limpiarCampos();
            habilitarControles(true);
            habilitarBotones(false, false, false, true, false, true);
            tipoOperacion ="N";
        }
        if(accion.compareTo("modificar")==0)
        {
            habilitarControles(true);
            habilitarBotones(false, false, false, true, false, true);
            tipoOperacion ="E";
        }
        if(accion.compareTo("guardar")==0)
        {
            if(validarDatos())
            {
                Casos casoRegistro = new Casos();
                casoRegistro.setNumero_caso(Integer.parseInt(formCaso.getTxtNumeroNino().getText()));
                casoRegistro.setCodigo_padrino(Integer.parseInt(formCaso.getTxtCodigoPadrino().getText()));

                try {
                    if(tipoOperacion == "N") dao.insertar(casoRegistro);
                    else dao.edit(casoRegistro);
                    JOptionPane.showMessageDialog(formCaso, "Registro realizado satisfactoriamente", "Gestion de Casos", JOptionPane.INFORMATION_MESSAGE);
                    habilitarBotones(true, true, true, false, true, false);
                } catch (Exception ex) {
                    if(JOptionPane.showConfirmDialog(this.formCaso, "No se pudo ingresar correctamente el Registro, probablemente el Niño ya tiene asignado un patrocinador" +
                            "¿Desea ver mas detalladamente el error?", "Patrocinadores", JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION) == JOptionPane.YES_OPTION)
                        JOptionPane.showMessageDialog(formCaso, "Ocurrió la siguiente excepción " + ex.getMessage(), "Gestion de Casos", JOptionPane.ERROR_MESSAGE);
//                    ex.printStackTrace();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(formCaso, "Algunos datos se encuentra mal escritos, corrijalos", "Gestion de Casos", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(accion.compareTo("buscar")==0)
        {
            GuiCasosBuscador formBuscador = new GuiCasosBuscador(formCaso, true);
            formBuscador.control.setDao(dao);
            formBuscador.control.onFormShown();
            formBuscador.setVisible(true);
            cargarDatosCaso(formBuscador.control.getCasoSeleccionado());
            formBuscador.dispose();
        }

        if(accion.compareTo("buscarNino")==0)
        {
            GuiAfiliadoBuscador formBuscador = new GuiAfiliadoBuscador(formCaso, true);
            formBuscador.control.setDao(dao);
            formBuscador.control.onFormShown();
            formBuscador.control.tipoBusqueda = 'A';
            formBuscador.setVisible(true);
            Afiliado afiliado = (Afiliado) formBuscador.control.getPatrocinadorSeleccionado();
            if(afiliado != null)
            {
                formCaso.getTxtNumeroNino().setText(String.valueOf(afiliado.getNumero_caso()));
                formCaso.getTxtNombreCompletoNino().setText(afiliado.getNombreCompleto());
            }
            formBuscador.dispose();
        }

        if(accion.compareTo("buscarPadrino")==0)
        {
            GuiPatrocinadorBuscador formBuscador = new GuiPatrocinadorBuscador(formCaso, true);
            formBuscador.control.setDao(dao);
            formBuscador.control.onFormShown();
            formBuscador.setVisible(true);
            Padrinos padrino = formBuscador.control.getPatrocinadorSeleccionado();
            if(padrino != null)
            {
                formCaso.getTxtCodigoPadrino().setText(String.valueOf(padrino.getCodigo_padrino()));
                formCaso.getTxtNombreCompletoPadrino1().setText(padrino.getNombreCompleto());
                formCaso.getTxtNombreCompletoPadrino2().setText(padrino.getNombreCompleto2());
            }
            formBuscador.dispose();
        }

        if(accion.compareTo("eliminar")==0)
        {
            if(JOptionPane.showConfirmDialog(formCaso, "¿Se encuentra seguro de eliminar el registro actual?", "Gestión de Casos", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
            {
                Casos casoEliminar = new Casos();
                casoEliminar.setNumero_caso(Integer.parseInt(formCaso.getTxtNumeroNino().getText()));
                casoEliminar.setCodigo_padrino(Integer.parseInt(formCaso.getTxtCodigoPadrino().getText()));

                try {
                    dao.delete(casoEliminar);
                    limpiarCampos();
                    JOptionPane.showMessageDialog(formCaso, "Operación realizada Satisfactoriamednte", "Gestion de Casos", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formCaso, "Ocurrió la siguiente excepción " + ex.getMessage(), "Gestion de Casos", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        }
        if(accion.compareTo("cancelar")==0)
        {
            limpiarCampos();
            habilitarControles(false);
            habilitarBotones(true, false, false, false, true, false);
        }
    }

    public void cargarDatosCaso(Casos casoCargar)
    {
        if(casoCargar != null)
        {
            this.formCaso.getTxtCodigoPadrino().setText(String.valueOf(casoCargar.getCodigo_padrino()));
            this.formCaso.getTxtNumeroNino().setText(String.valueOf(casoCargar.getNumero_caso()));
            this.formCaso.getTxtNombreCompletoNino().setText(casoCargar.getAfiliado().getNombreCompleto());
            this.formCaso.getTxtNombreCompletoPadrino1().setText(casoCargar.getPatrocinador1().getNombreCompleto());
            this.formCaso.getTxtNombreCompletoPadrino2().setText(casoCargar.getPatrocinador1().getNombreCompleto2());
            habilitarBotones(true, true, true, false, true, false);
        }
        else
        {
            limpiarCampos();
            habilitarControles(false);
            habilitarBotones(true, false, false, false, true, false);
        }

    }

    public void limpiarCampos()
    {
        this.formCaso.getTxtCodigoPadrino().setText("");
        this.formCaso.getTxtNombreCompletoNino().setText("");
        this.formCaso.getTxtNombreCompletoPadrino1().setText("");
        this.formCaso.getTxtNombreCompletoPadrino2().setText("");
        this.formCaso.getTxtNumeroNino().setText("");
    }

    public void habilitarControles(boolean estadoHabilitacion)
    {
        this.formCaso.getTxtCodigoPadrino().setEnabled(estadoHabilitacion);
        this.formCaso.getTxtNumeroNino().setEnabled(estadoHabilitacion);
        this.formCaso.getBtnBuscarNino().setEnabled(estadoHabilitacion);
        this.formCaso.getBtnBuscarPadrino().setEnabled(estadoHabilitacion);
    }

    public void habilitarBotones( boolean nuevo, boolean modificar, boolean eliminar, boolean aceptar, boolean buscar, boolean cancelar)
    {
        formCaso.getBtnEliminar().setEnabled(eliminar);
        formCaso.getBtnGuardar().setEnabled(aceptar);
        formCaso.getBtnModificar().setEnabled(modificar);
        formCaso.getBtnNuevo().setEnabled(nuevo);
        formCaso.getBtnBuscar().setEnabled(buscar);
        formCaso.getBtnCancelar().setEnabled(cancelar);
    }

    public boolean  validarDatos()
    {
        if(formCaso.getTxtCodigoPadrino().getText().trim().isEmpty())
        {
            formCaso.getTxtCodigoPadrino().grabFocus();
            JOptionPane.showMessageDialog(formCaso, "Aún no ha ingresado el Codigo de padrino", "Gestion de Casos", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if(formCaso.getTxtNumeroNino().getText().trim().isEmpty())
        {
            formCaso.getTxtNumeroNino().grabFocus();
            JOptionPane.showMessageDialog(formCaso, "Aún no ha ingresado el Numero de Caso correspondiente al Nino", "Gestion de Casos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    public void insertUpdate(DocumentEvent e) {
        Object owner = e.getDocument().getProperty("numeroCaso");
         if(owner != null){
             obtenerAfiliado();
         }
        owner = e.getDocument().getProperty("codigoPadrino");
        if(owner != null){
             obtenerPatrocinador();
         }
    }

    public void removeUpdate(DocumentEvent e) {
//        if(e.getDocument() == formCaso.getTxtCodigoPadrino().getDocument())
//        {
//            obtenerPatrocinador();
//        }
//        else
//        {
//            obtenerAfiliado();
//        }

        Object owner = e.getDocument().getProperty("numeroCaso");
         if(owner != null){
             obtenerAfiliado();
         }
        owner = e.getDocument().getProperty("codigoPadrino");
        if(owner != null){
             obtenerPatrocinador();
         }
    }

    public void changedUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void obtenerPatrocinador()
    {
        if(!formCaso.getTxtCodigoPadrino().getText().trim().isEmpty())
        {
            try {
            int CodigoPadrino = Integer.parseInt(formCaso.getTxtCodigoPadrino().getText());
            Padrinos padrino = new Padrinos();
            padrino.setCodigo_padrino(CodigoPadrino);
            padrino = (Padrinos) dao.obtenerObjeto(padrino);
            if(padrino != null)
            {
                formCaso.getTxtNombreCompletoPadrino1().setText(padrino.getNombreCompleto());
                formCaso.getTxtNombreCompletoPadrino2().setText(padrino.getNombreCompleto2());
            }
            else
            {
                formCaso.getTxtNombreCompletoPadrino1().setText("");
                formCaso.getTxtNombreCompletoPadrino2().setText("");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        }
        
    }

    public void obtenerAfiliado()
    {
        if(!formCaso.getTxtNumeroNino().getText().trim().isEmpty())
        {
            try {
                int numeroCaso = Integer.parseInt(formCaso.getTxtNumeroNino().getText());
                Afiliado afiliado = new Afiliado();
                afiliado.setNumero_caso(numeroCaso);
                afiliado = (Afiliado) dao.obtenerObjeto(afiliado);
                if(afiliado != null)
                {
                    formCaso.getTxtNombreCompletoNino().setText(afiliado.getNombreCompleto());
                }
                else
                {
                    formCaso.getTxtNombreCompletoNino().setText("");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        JTextField componente = (JTextField) e.getSource();
        char c = e.getKeyChar();        
        if (componente.equals(formCaso.getTxtCodigoPadrino()) ||
              componente.equals(formCaso.getTxtNumeroNino())
              ) {

                if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                                || (c == KeyEvent.VK_DELETE) ))) {
                        formCaso.getToolkit().beep();
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
            formulario.setNombre_formulario(FormUtilities.getClassName(formCaso.getClass()));
            SistemaFormulariosPermisosUsuarios permisosCasos = new SistemaFormulariosPermisosUsuarios();
            permisosCasos.setSistemaFormulario(formulario);

            indice = Collections.binarySearch(usuario.getListadoInterfacesPermisos(), permisosCasos, new ComparadorIdFormulario());
            if (indice >= 0) {
                permisosCasos = usuario.getListadoInterfacesPermisos().get(indice);
                formCaso.getBtnBuscar().setVisible(permisosCasos.isPermitir_navegar());
                formCaso.getBtnEliminar().setVisible(permisosCasos.isPemitir_eliminar());
                formCaso.getBtnModificar().setVisible(permisosCasos.isPermitir_editar());
                formCaso.getBtnNuevo().setVisible(permisosCasos.isPermitir_insertar());

            } else {
                System.out.println("no tiene permisos, nombre de la Clase " + formulario.getNombre_formulario());
            }
        }
    }
    
}
