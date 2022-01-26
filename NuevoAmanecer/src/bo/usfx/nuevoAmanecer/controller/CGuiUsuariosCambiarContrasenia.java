/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;


import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.GuiUsuariosCambiarContrasenia;

/**
 *
 * @author Luis Molina
 */
public class CGuiUsuariosCambiarContrasenia implements ActionListener{

    private GuiUsuariosCambiarContrasenia formCambiarContrasenia;
    private Usuarios usuario;
    private CommonDao dao;
    private final String tituloFormulario = "Cambio de Contraseña Actual";
    public CGuiUsuariosCambiarContrasenia(GuiUsuariosCambiarContrasenia formContrasenia)
    {
        this.formCambiarContrasenia = formContrasenia;
    }

    public boolean validarDatos()
    {
//        System.out.println("Contraseña Actual del Usuario" + usuario.getContrasenia());
//        System.out.println("Contraseña Actual " + this.formCambiarContrasenia.getTxtPasswordActual().getText());
//        System.out.println("Contraseña Nueva " + this.formCambiarContrasenia.getTxtPasswordNuevo().getText());
//        System.out.println("Contraseña Confirmada " + this.formCambiarContrasenia.getTxtPasswordNuevoConfirmado().getText());
        if(this.formCambiarContrasenia.getTxtPasswordActual().getText().isEmpty())
        {
            formCambiarContrasenia.getTxtPasswordActual().grabFocus();
            formCambiarContrasenia.getTxtPasswordActual().selectAll();
            JOptionPane.showMessageDialog(formCambiarContrasenia, "Aún no ha ingresado la Contraseña actual con la que ingreso al sistema", tituloFormulario, JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.formCambiarContrasenia.getTxtPasswordNuevo().getText().isEmpty())
        {
            formCambiarContrasenia.getTxtPasswordNuevo().grabFocus();
            formCambiarContrasenia.getTxtPasswordNuevo().selectAll();
            JOptionPane.showMessageDialog(formCambiarContrasenia, "Aún no ha ingresado la nueva Contraseña", tituloFormulario, JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.formCambiarContrasenia.getTxtPasswordNuevoConfirmado().getText().isEmpty())
        {
            formCambiarContrasenia.getTxtPasswordNuevoConfirmado().grabFocus();
            formCambiarContrasenia.getTxtPasswordNuevoConfirmado().selectAll();
            JOptionPane.showMessageDialog(formCambiarContrasenia, "Aún no ha ingresado la confirmación de la Contraseña nueva", tituloFormulario, JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(usuario.getContrasenia().trim().compareTo(this.formCambiarContrasenia.getTxtPasswordActual().getText()) != 0)
        {
            formCambiarContrasenia.getTxtPasswordActual().grabFocus();
            formCambiarContrasenia.getTxtPasswordActual().selectAll();
            JOptionPane.showMessageDialog(formCambiarContrasenia, "La Contraseña Actual no coincide con la que ingreso al Sistema", tituloFormulario, JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.formCambiarContrasenia.getTxtPasswordNuevo().getText().
                compareTo(this.formCambiarContrasenia.getTxtPasswordNuevoConfirmado().getText()) != 0)
        {
            formCambiarContrasenia.getTxtPasswordNuevoConfirmado().grabFocus();
            formCambiarContrasenia.getTxtPasswordNuevoConfirmado().selectAll();
            JOptionPane.showMessageDialog(formCambiarContrasenia, "La Contraseña de confirmación no coincide con la nueva Contraseña", tituloFormulario, JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();
        if(accion.compareTo("aceptar") == 0)
        {
            if(validarDatos())
            {
                try {
                    usuario.setContrasenia(this.formCambiarContrasenia.getTxtPasswordNuevo().getText());
                    dao.edit(usuario);
                    JOptionPane.showMessageDialog(formCambiarContrasenia, "Actualización de la contraseña realizada satisfactoriamente", tituloFormulario, JOptionPane.INFORMATION_MESSAGE);
                    accion = "cancelar";
                } catch (SQLException ex) {
                    Logger.getLogger(CGuiUsuariosCambiarContrasenia.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(formCambiarContrasenia, "Ocurrio la siguiente excepción " + ex.getMessage(), tituloFormulario, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if(accion.compareTo("cancelar") == 0)
        {
            formCambiarContrasenia.setVisible(false);
            formCambiarContrasenia.dispose();
        }
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public void onFormShown()
    {
        this.formCambiarContrasenia.getTxtPasswordActual().setText("");
        this.formCambiarContrasenia.getTxtPasswordNuevo().setText("");
        this.formCambiarContrasenia.getTxtPasswordNuevoConfirmado().setText("");

        this.formCambiarContrasenia.getTxtPasswordActual().grabFocus();
    }

}
