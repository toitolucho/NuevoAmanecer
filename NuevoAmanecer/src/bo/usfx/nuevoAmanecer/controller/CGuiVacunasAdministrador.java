/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormularios;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormulariosPermisosUsuarios;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.nuevoAmanecer.model.domain.Vacunas2;
import bo.usfx.utils.ComparadorIdFormulario;
import bo.usfx.utils.FormUtilities;
import bo.usfx.utils.ModeloVacunas2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import view.GuiVacunasAdministrador;

/**
 *
 * @author Luis Molina
 */
public class CGuiVacunasAdministrador extends KeyAdapter implements ActionListener, ListSelectionListener{
    private GuiVacunasAdministrador formVacunasAdmin;
    private Usuarios usuario;
    private Connection conexion;
    private CommonDao dao;
    private List<Vacunas2> listadoVacunas;
    private ModeloVacunas2 modeloVacunas;
    private final String TituloFormulario = "Gestion de Vacunas";
    private String tipoOperacion="";

    public CGuiVacunasAdministrador(GuiVacunasAdministrador formVacunasAdmin)
    {
        this.formVacunasAdmin = formVacunasAdmin;
        modeloVacunas = new ModeloVacunas2();
    }

    public void actionPerformed(ActionEvent event) {
        String accion = event.getActionCommand();

        if(accion.compareTo("nuevo") == 0)
        {
            try {
                tipoOperacion = "N";
                limpiarControles();
                habilitarBotones(false, true, false, true, false);
                habilitarControles(true);
                int numeroVacuna = dao.obtenerUltimoObjetoTabla(new Vacunas2(), "");
                formVacunasAdmin.getTxtCodigoVacuna().setText(String.valueOf(numeroVacuna + 1));
            } catch (SQLException ex) {
                Logger.getLogger(CGuiVacunasAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(accion.compareTo("cancelar") == 0)
        {
            tipoOperacion ="";
            limpiarControles();
            habilitarBotones(true, false, false, false, false);
            habilitarControles(false);
        }
        
        if(accion.compareTo("editar") == 0)
        {
            tipoOperacion ="E";            
            habilitarBotones(false, true, false, true, false);
            habilitarControles(true);
        }
        
        if(accion.compareTo("eliminar") == 0)
        {
            if(formVacunasAdmin.getjTableVacuna().getSelectedRow() >= 0
                    && JOptionPane.showConfirmDialog(formVacunasAdmin, "¿Se encuentra seguro de Eliminar el Registro?", 
                    TituloFormulario, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                try {
                    dao.delete(modeloVacunas.getVacunas2(formVacunasAdmin.getjTableVacuna().getSelectedRow()));
                    modeloVacunas.removeVacunas2(formVacunasAdmin.getjTableVacuna().getSelectedRow());
                    limpiarControles();
                    habilitarBotones(true, false, modeloVacunas.getRowCount() > 0, false, modeloVacunas.getRowCount() > 0);
                } catch (SQLException ex) {
                    Logger.getLogger(CGuiVacunasAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        
        if(accion.compareTo("aceptar") == 0)
        {
            if(validarDatos())
            {
                try {
                    Vacunas2 vacunaRegistro = new Vacunas2();
                    vacunaRegistro.setNombre_vacuna2(formVacunasAdmin.getTxtNombreVacuna().getText());
                    vacunaRegistro.setDescripcion_vacuna2(formVacunasAdmin.getTxtDescripcionVacuna().getText());
                    vacunaRegistro.setEdad_meses_minima(Integer.parseInt(formVacunasAdmin.getTxtEdadMinimo().getText()));
                    vacunaRegistro.setEdad_meses_maxima(Integer.parseInt(formVacunasAdmin.getTxtEdadMaximo().getText()));
                    if (tipoOperacion == "N") {
                        dao.insertar(vacunaRegistro);
                        int numeroVacuna = dao.obtenerUltimoObjetoTabla(vacunaRegistro, "");
                        vacunaRegistro.setCodigo_vacuna2(numeroVacuna);
                        modeloVacunas.addVacunas2(vacunaRegistro);
                    } else {
                        vacunaRegistro.setCodigo_vacuna2(Integer.parseInt(formVacunasAdmin.getTxtCodigoVacuna().getText()));
                        dao.edit(vacunaRegistro);
                        if(formVacunasAdmin.getjTableVacuna().getSelectedRow() >= 0)
                        {
                            modeloVacunas.removeVacunas2(formVacunasAdmin.getjTableVacuna().getSelectedRow());
                            modeloVacunas.addVacunas2(vacunaRegistro);
                        }
//                        formVacunasAdmin.getjTableVacuna().setRowSelectionInterval(formVacunasAdmin.getjTableVacuna().getSelectedRow(), formVacunasAdmin.getjTableVacuna().getSelectedRow());
                    }

                    habilitarControles(false);
                    habilitarBotones(true, false, true, false, false);
                    JOptionPane.showMessageDialog(formVacunasAdmin, "Registro realizado correctamente", TituloFormulario, JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException ex) {


                    if(JOptionPane.showConfirmDialog(formVacunasAdmin, "No se pudo ingresar la Vacuna actual, probablemente el nombre ya se encuentra registrada. ¿Desea ver a mas detalle el motivo?",
                            "Vacunas", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    {
//                        Logger.getLogger(CGuiVacunasAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(formVacunasAdmin, "Ocurrio la siguiente Excepcion " + ex.getMessage(), TituloFormulario, JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        if(accion.compareTo(formVacunasAdmin.getBtnCerrar().getActionCommand()) == 0)
        {
            this.formVacunasAdmin.setVisible(false);
        }

    }

    public void habilitarBotones(boolean nuevo, boolean cancelar, boolean editar, boolean aceptar, boolean eliminar)
    {
        this.formVacunasAdmin.getBtnNuevo().setEnabled(nuevo);
        this.formVacunasAdmin.getBtnCancelar().setEnabled(cancelar);
        this.formVacunasAdmin.getBtnEditar().setEnabled(editar);
        this.formVacunasAdmin.getBtnAceptar().setEnabled(aceptar);
        this.formVacunasAdmin.getBtnEliminar().setEnabled(eliminar);
    }

    public void limpiarControles()
    {
        this.formVacunasAdmin.getTxtCodigoVacuna().setText("");
        this.formVacunasAdmin.getTxtNombreVacuna().setText("");
        this.formVacunasAdmin.getTxtDescripcionVacuna().setText("");
        this.formVacunasAdmin.getTxtEdadMaximo().setText("");
        this.formVacunasAdmin.getTxtEdadMinimo().setText("");
    }

    public void habilitarControles(boolean estadoHabilitacion)
    {
        
        this.formVacunasAdmin.getTxtNombreVacuna().setEditable(estadoHabilitacion);
        this.formVacunasAdmin.getTxtDescripcionVacuna().setEditable(estadoHabilitacion);
        this.formVacunasAdmin.getTxtEdadMaximo().setEditable(estadoHabilitacion);
        this.formVacunasAdmin.getTxtEdadMinimo().setEditable(estadoHabilitacion);
    }

    public boolean validarDatos()
    {
        if(formVacunasAdmin.getTxtNombreVacuna().getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(formVacunasAdmin, "No puede dejar en blanco el Nombre de la Vacuna", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formVacunasAdmin.getTxtNombreVacuna().grabFocus();
            formVacunasAdmin.getTxtNombreVacuna().selectAll();
            return false;
        }
        if(formVacunasAdmin.getTxtEdadMinimo().getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(formVacunasAdmin, "No puede dejar en blanco La edad en Meses Minima para la aplicacion de la Vacuna", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formVacunasAdmin.getTxtEdadMinimo().grabFocus();
            formVacunasAdmin.getTxtEdadMinimo().selectAll();
            return false;
        }
        if(formVacunasAdmin.getTxtEdadMaximo().getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(formVacunasAdmin, "No puede dejar en blanco La edad en Meses Maxima para la aplicacion de la Vacuna", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formVacunasAdmin.getTxtEdadMaximo().grabFocus();
            formVacunasAdmin.getTxtEdadMaximo().selectAll();
            return false;
        }

        int edadMinimo = Integer.parseInt(formVacunasAdmin.getTxtEdadMinimo().getText());
        int edadMaximo = Integer.parseInt(formVacunasAdmin.getTxtEdadMaximo().getText());
        if(edadMaximo < edadMinimo)
        {
            JOptionPane.showMessageDialog(formVacunasAdmin, "La Edad Maxima no puede ser menor a la Minima", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formVacunasAdmin.getTxtEdadMaximo().grabFocus();
            formVacunasAdmin.getTxtEdadMaximo().selectAll();
            return false;
        }

        return true;
    }

    public void cargarDatosVacuna2(Vacunas2 vacuna2)
    {
        habilitarControles(false);
        if(vacuna2 != null)
        {
            this.formVacunasAdmin.getTxtCodigoVacuna().setText(String.valueOf(vacuna2.getCodigo_vacuna2()));
            this.formVacunasAdmin.getTxtNombreVacuna().setText(vacuna2.getNombre_vacuna2());
            this.formVacunasAdmin.getTxtEdadMinimo().setText(String.valueOf(vacuna2.getEdad_meses_minima()));
            this.formVacunasAdmin.getTxtEdadMaximo().setText(String.valueOf(vacuna2.getEdad_meses_maxima()));
            this.formVacunasAdmin.getTxtDescripcionVacuna().setText(vacuna2.getDescripcion_vacuna2());
            habilitarBotones(true, false, true, false, true);
        }else
        {
            limpiarControles();
            habilitarBotones(true, false, false, false, false);
        }
    }

    public void onFormShown()
    {
        //this.formTajetaFamiliar.getjTableVacunas().getSelectionModel().addListSelectionListener(this);
        this.formVacunasAdmin.getjTableVacuna().getSelectionModel().addListSelectionListener(this);
        this.formVacunasAdmin.getjTableVacuna().setModel(modeloVacunas);
        try {
            listadoVacunas = dao.getObjects("Vacunas2", new Vacunas2());
            modeloVacunas.clear();
            for (Vacunas2 vacunas2 : listadoVacunas) {
                modeloVacunas.addVacunas2(vacunas2);
            }
            cargarDatosVacuna2(null);
            configuracionFormulario();
        } catch (SQLException ex) {
            Logger.getLogger(CGuiVacunasAdministrador.class.getName()).log(Level.SEVERE, null, ex);
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

    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return; // if you don't want to handle intermediate selections
        }
        ListSelectionModel rowSM = (ListSelectionModel) e.getSource();
        int indice = rowSM.getMinSelectionIndex();
        if (this.formVacunasAdmin.getjTableVacuna().isFocusOwner()) {

            if (indice >= 0) {                
                cargarDatosVacuna2(modeloVacunas.getVacunas2(indice));
            } else {
                cargarDatosVacuna2(null);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        JTextField componente = (JTextField) e.getSource();
        char c = e.getKeyChar();
        if (componente.equals(this.formVacunasAdmin.getTxtEdadMaximo()) ||
              componente.equals(this.formVacunasAdmin.getTxtEdadMinimo())
              ) {

                if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                                || (c == KeyEvent.VK_DELETE) ))) {
                        formVacunasAdmin.getToolkit().beep();
                        e.consume();
                }
        }
    }

     public void configuracionFormulario() {
        if (usuario != null) {
            int indice = -1;
            SistemaFormularios formulario = new SistemaFormularios();
            formulario.setNombre_formulario(FormUtilities.getClassName(formVacunasAdmin.getClass()));
            SistemaFormulariosPermisosUsuarios permisosVacunasAdministrador = new SistemaFormulariosPermisosUsuarios();
            permisosVacunasAdministrador.setSistemaFormulario(formulario);

            indice = Collections.binarySearch(usuario.getListadoInterfacesPermisos(), permisosVacunasAdministrador, new ComparadorIdFormulario());
            if (indice >= 0) {
                permisosVacunasAdministrador = usuario.getListadoInterfacesPermisos().get(indice);

                formVacunasAdmin.getBtnNuevo().setVisible(permisosVacunasAdministrador.isPermitir_insertar());
                formVacunasAdmin.getBtnEditar().setVisible(permisosVacunasAdministrador.isPermitir_editar());
                formVacunasAdmin.getBtnEliminar().setVisible(permisosVacunasAdministrador.isPemitir_eliminar());
                formVacunasAdmin.getjTableVacuna().setEnabled(permisosVacunasAdministrador.isPermitir_navegar());

            } else {
                System.out.println("no tiene permisos, nombre de la Clase " + formulario.getNombre_formulario());
            }
        }
    }

}
