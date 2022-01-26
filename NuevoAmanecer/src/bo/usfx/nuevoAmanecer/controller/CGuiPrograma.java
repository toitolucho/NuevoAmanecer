/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Programas;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormularios;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormulariosPermisosUsuarios;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.utils.ComparadorIdFormulario;
import bo.usfx.utils.FormUtilities;
import bo.usfx.utils.ModeloPrograma;
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
import view.GuiPrograma;
import view.GuiProgramaBuscador;


public class CGuiPrograma extends KeyAdapter implements ActionListener {
    GuiPrograma formPrograma;
    CommonDao dao;
    ModeloPrograma modeloPrograma;
    private Connection conexion;
    private int idFormulario;
    private Usuarios usuario;

    public CGuiPrograma(GuiPrograma formPrograma) {
        this.formPrograma = formPrograma;
    }



    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();
        if(accion.compareTo(formPrograma.getBtnNuevo().getActionCommand()) == 0)
        {
            habilitarControlesNuevo(true);
            limpiarCamposNuevo();
            formPrograma.getTxtNombrePrograma().grabFocus();
            formPrograma.getBtnNuevo().setEnabled(false);
            formPrograma.getBtnRegistrar().setEnabled(true);
            try {
                int Numero_Programa = dao.obtenerUltimoObjetoTabla(new Programas(), "");
                System.out.println("Numero de Programa Nuevo " + Numero_Programa);
                Numero_Programa = Numero_Programa == -1 ? 1 : ++Numero_Programa;
                formPrograma.getTxtCodigoPrograma().setText(String.valueOf(Numero_Programa));
            } catch (Exception ex) {
            }
        }

        if(accion.compareTo(formPrograma.getBtnBuscarEdicionAvanzada().getActionCommand()) == 0)
        {
                GuiProgramaBuscador formBuscado = new GuiProgramaBuscador(formPrograma, true);
                formBuscado.control.setDao(dao);
                formBuscado.control.onFormShown();
                formBuscado.setVisible(true);
                Programas programa = formBuscado.control.getProgramaSeleccionado();
                if(programa != null)
                {
                    cargarDatosPrograma(programa);
                    habilitarControlesEdicion(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(formBuscado, "No ha seleccionado ningun Programa para trabajar con el mismo", "Administrador de Programas", JOptionPane.ERROR_MESSAGE);
                    habilitarControlesEdicion(false);
                    limpiarCamposEdicion();
                }
        }

        if(accion.compareTo(formPrograma.getBtnRegistrar().getActionCommand()) == 0)
        {
            if(validarDatosNuevo())
            {
                Programas programa = new Programas(1,
                        formPrograma.getTxtNombrePrograma().getText(),
                        formPrograma.getTxtDescripcionPrograma().getText(),
                        formPrograma.getTxtJustificacionPrograma().getText(),
                        formPrograma.getDateFechaInicio().getDate(),
                        formPrograma.getDateFechaFin().getDate(),
                        formPrograma.getTxtLugarDesarollo().getText());
                programa.setArea(formPrograma.getcBoxArea().getSelectedItem().toString());
                try {
                    dao.insertar(programa);
                    habilitarControlesNuevo(false);
                    formPrograma.getBtnNuevo().setEnabled(true);
                    formPrograma.getBtnRegistrar().setEnabled(false);

                    JOptionPane.showMessageDialog(formPrograma, "Se registro Satisfactoriamente el nuevo programa", "Registro Satisfactorio", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(CGuiPrograma.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(formPrograma, "No se pudo registrar el programa debido a " + ex.getMessage(), "Error de Inserción", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        }
        if(accion.compareTo("buscarE") == 0)
        {
            try {
                int CodigoPrograma = Integer.parseInt(formPrograma.getTxtTextoBusquedaEdicion().getText());
                Programas programa = new Programas();
                programa.setCodigo_programa(CodigoPrograma);
                programa = (Programas) dao.obtenerObjeto(programa);
                if(programa != null)
                {
                    cargarDatosPrograma(programa);
                    habilitarControlesEdicion(true);
                }
                else
                {
                    habilitarControlesEdicion(false);
                    limpiarCamposEdicion();
                }

            } catch (Exception ex) {
            }
        }

        if(accion.compareTo("buscarX") == 0)
        {
            try {
                int CodigoPrograma = 0;
                try {
                    CodigoPrograma = Integer.parseInt(formPrograma.getTxtTextoBusquedaEliminacion().getText());
                } catch (NumberFormatException e2) {
                }
                
                Programas programa = new Programas();
                programa.setCodigo_programa(CodigoPrograma);
                programa.setNombre_actividad(formPrograma.getTxtTextoBusquedaEliminacion().getText());

                List<Programas> listaProgramas = dao.findObjects(programa);
                modeloPrograma.clear();
                if(listaProgramas != null && !listaProgramas.isEmpty())
                {
                    for (Programas programas : listaProgramas) {
                        modeloPrograma.addProgramas(programas);
                    }
                    formPrograma.getBtnEliminar().setEnabled(true);
                } else
                {
                    JOptionPane.showMessageDialog(formPrograma, "No se encontró ningun registro con la información provista", "Administrador de Programas", JOptionPane.INFORMATION_MESSAGE);
                    formPrograma.getBtnEliminar().setEnabled(false);
                }

            } catch (Exception ex) {
            }
        }

        if(accion.compareTo(formPrograma.getBtnEliminar().getActionCommand()) == 0)
        {
            if(modeloPrograma != null && modeloPrograma.getRowCount() > 0)
            {
                int indice = formPrograma.getjTablePrograma().getSelectedRow();
                if(indice < 0)
                {
                    JOptionPane.showMessageDialog(formPrograma, "Aún no ha seleccionado ningun Programas", "Administrador de Programas", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(indice >= 0 && JOptionPane.showConfirmDialog(formPrograma, "¿Se encuentra seguro de eliminar el Registro?", "Eliminación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    Programas programa = modeloPrograma.getProgramas(indice);
                    try {
                        dao.delete(programa);
                        modeloPrograma.removeProgramas(indice);
                        JOptionPane.showMessageDialog(formPrograma, "Registro eliminado satisfactoriamente");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(formPrograma, "Registro no eliminado debido a que ocurrio la siguiente excepcion " + ex.getMessage(), "eliminacion", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }

                }
            }

        }

        if(accion.compareTo(formPrograma.getBtnModificar().getActionCommand()) == 0)
        {
            if(validarDatosEdicion())
            {
                Programas programa = new Programas(Integer.parseInt(formPrograma.getTxtCodigoProgramaE().getText()),
                        formPrograma.getTxtNombreActividadE().getText(),
                        formPrograma.getTxtDescripcionE().getText(),
                        formPrograma.getTxtJustificacionE().getText(),
                        formPrograma.getDateFechaInicioE().getDate(),
                        formPrograma.getDateFechaFinE().getDate(),
                        formPrograma.getTxtLugarDesarrolloE().getText());
                programa.setArea(formPrograma.getcBoxAreaE().getSelectedItem().toString());
                try {
                    dao.edit(programa);
                    habilitarControlesEdicion(false);

                    JOptionPane.showMessageDialog(formPrograma, "Se actualizo Satisfactoriamente el programa", "Actualización Satisfactoria", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(CGuiPrograma.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(formPrograma, "No se pudo registrar el programa debido a " + ex.getMessage(), "Error de Actualización", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        }
    }

    public void cargarDatosPrograma(Programas program)
    {
        if(program != null)
        {
            this.formPrograma.getTxtCodigoProgramaE().setText(String.valueOf(program.getCodigo_programa()));
            this.formPrograma.getTxtNombreActividadE().setText(program.getNombre_actividad());
            this.formPrograma.getTxtDescripcionE().setText(program.getDescripcion());
            this.formPrograma.getTxtJustificacionE().setText(program.getJustificacion());
            this.formPrograma.getDateFechaInicioE().setDate(program.getFecha_programa());
            this.formPrograma.getDateFechaFinE().setDate(program.getFecha_culminacion());
            this.formPrograma.getcBoxAreaE().setSelectedItem(program.getArea());
            this.formPrograma.getTxtLugarDesarrolloE().setText(program.getLugar());
        }
    }

    public boolean validarDatosNuevo()
    {
        if(formPrograma.getTxtNombrePrograma().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formPrograma, "Aún no ha ingresado el nombre del Program", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formPrograma.getTxtNombrePrograma().grabFocus();
            return false;
        }
        if(formPrograma.getTxtDescripcionPrograma().getText().isEmpty())
        {
            if(JOptionPane.showConfirmDialog(formPrograma, "Se encuentra Seguro de Dejar en blanco la Descripción del Programa", "Validación de Datos", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
            {
                formPrograma.getTxtDescripcionPrograma().grabFocus();
                return false;
            }
        }
        if(formPrograma.getTxtJustificacionPrograma().getText().isEmpty())
        {
            if(JOptionPane.showConfirmDialog(formPrograma, "Se encuentra Seguro de Dejar en blanco la Justificación del Programa", "Validación de Datos", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
            {
                formPrograma.getTxtJustificacionPrograma().grabFocus();
                return false;
            }
        }
        if(formPrograma.getDateFechaInicio().getDate() == null)
        {
            JOptionPane.showMessageDialog(formPrograma, "Aún no ha ingresado la Fecha de Inicio", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formPrograma.getDateFechaInicio().grabFocus();
            return false;
        }

            
        if(formPrograma.getDateFechaFin().getDate() != null && formPrograma.getDateFechaInicio().getDate().after(formPrograma.getDateFechaFin().getDate()))
        {
            JOptionPane.showMessageDialog(formPrograma, "La fecha Inicio ingresada no puede ser superior a la Fecha Final", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formPrograma.getDateFechaInicio().grabFocus();
            return false;
        }

        if(formPrograma.getcBoxArea().getSelectedIndex() < 0)
        {
            JOptionPane.showMessageDialog(formPrograma, "Aún no ha seleccionado ningún area", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formPrograma.getcBoxArea().grabFocus();
            return false;
        }


        return true;
    }


     public boolean validarDatosEdicion()
    {
        if(formPrograma.getTxtNombreActividadE().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formPrograma, "Aún no ha ingresado el nombre del Program", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formPrograma.getTxtNombreActividadE().grabFocus();
            return false;
        }
        if(formPrograma.getTxtDescripcionE().getText().isEmpty())
        {
            if(JOptionPane.showConfirmDialog(formPrograma, "Se encuentra Seguro de Dejar en blanco la Descripción del Programa", "Validación de Datos", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
            {
                formPrograma.getTxtDescripcionE().grabFocus();
                return false;
            }
        }
        if(formPrograma.getTxtJustificacionE().getText().isEmpty())
        {
            if(JOptionPane.showConfirmDialog(formPrograma, "Se encuentra Seguro de Dejar en blanco la Justificación del Programa", "Validación de Datos", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
            {
                formPrograma.getTxtJustificacionE().grabFocus();
                return false;
            }
        }
        if(formPrograma.getDateFechaInicioE().getDate() == null)
        {
            JOptionPane.showMessageDialog(formPrograma, "Aún no ha ingresado la Fecha de Inicio", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formPrograma.getDateFechaInicioE().grabFocus();
            return false;
        }

         if(formPrograma.getDateFechaFinE().getDate() != null &&  formPrograma.getDateFechaInicioE().getDate().after(formPrograma.getDateFechaFinE().getDate()))
        {
            JOptionPane.showMessageDialog(formPrograma, "La fecha Inicio ingresada no puede ser superior a la Fecha Final", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formPrograma.getDateFechaInicioE().grabFocus();
            return false;
        }

        if(formPrograma.getcBoxAreaE().getSelectedIndex() < 0)
        {
            JOptionPane.showMessageDialog(formPrograma, "Aún no ha seleccionado ningún area", "Validación de Datos", JOptionPane.ERROR_MESSAGE);
            formPrograma.getcBoxAreaE().grabFocus();
            return false;
        }

        return true;
    }

    public void onFormShown()
    {
        modeloPrograma = new ModeloPrograma();
        habilitarControlesEdicion(false);
        habilitarControlesNuevo(false);
        limpiarCamposEdicion();
        limpiarCamposNuevo();
        formPrograma.getjTablePrograma().setModel(modeloPrograma);
        formPrograma.getTxtCodigoPrograma().setEnabled(false);
        formPrograma.getBtnRegistrar().setEnabled(false);
        configuracionFormulario();
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    public void limpiarCamposNuevo()
    {
        this.formPrograma.getTxtCodigoPrograma().setText("");
        this.formPrograma.getTxtNombrePrograma().setText("");
        this.formPrograma.getTxtDescripcionPrograma().setText("");
        this.formPrograma.getTxtJustificacionPrograma().setText("");
        this.formPrograma.getDateFechaInicio().setDate(null);
        this.formPrograma.getDateFechaFin().setDate(null);
        this.formPrograma.getcBoxArea().setSelectedIndex(-1);
        this.formPrograma.getTxtLugarDesarollo().setText("");
    }

    public void limpiarCamposEdicion()
    {
        this.formPrograma.getTxtCodigoProgramaE().setText("");
        this.formPrograma.getTxtNombreActividadE().setText("");
        this.formPrograma.getTxtDescripcionE().setText("");
        this.formPrograma.getTxtJustificacionE().setText("");
        this.formPrograma.getDateFechaInicioE().setDate(null);
        this.formPrograma.getDateFechaFinE().setDate(null);
        this.formPrograma.getcBoxAreaE().setSelectedIndex(-1);
        this.formPrograma.getTxtLugarDesarrolloE().setText("");
    }

    public void habilitarControlesNuevo(boolean estadoHabilitacion)
    {
        this.formPrograma.getTxtCodigoPrograma().setEditable(estadoHabilitacion);
        this.formPrograma.getTxtNombrePrograma().setEditable(estadoHabilitacion);
        this.formPrograma.getTxtDescripcionPrograma().setEditable(estadoHabilitacion);
        this.formPrograma.getTxtJustificacionPrograma().setEditable(estadoHabilitacion);
        this.formPrograma.getDateFechaInicio().setEnabled(estadoHabilitacion);
        this.formPrograma.getDateFechaFin().setEnabled(estadoHabilitacion);
        this.formPrograma.getcBoxArea().setEnabled(estadoHabilitacion);
        this.formPrograma.getTxtLugarDesarollo().setEditable(estadoHabilitacion);
    }

    public void habilitarControlesEdicion(boolean estadoHabilitacion)
    {
        this.formPrograma.getTxtCodigoProgramaE().setEditable(false);
        this.formPrograma.getTxtNombreActividadE().setEditable(estadoHabilitacion);
        this.formPrograma.getTxtDescripcionE().setEditable(estadoHabilitacion);
        this.formPrograma.getTxtJustificacionE().setEditable(estadoHabilitacion);
        this.formPrograma.getDateFechaInicioE().setEnabled(estadoHabilitacion);
        this.formPrograma.getDateFechaFinE().setEnabled(estadoHabilitacion);
        this.formPrograma.getcBoxAreaE().setEnabled(estadoHabilitacion);
        this.formPrograma.getTxtLugarDesarrolloE().setEditable(estadoHabilitacion);
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
            if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                            || (c == KeyEvent.VK_DELETE) ))) {
                    formPrograma.getToolkit().beep();
                    e.consume();
            }
    }

    public void configuracionFormulario() {
        if (usuario != null) {
            int indice = -1;
            SistemaFormularios formulario = new SistemaFormularios();
            formulario.setNombre_formulario(FormUtilities.getClassName(formPrograma.getClass()));
            SistemaFormulariosPermisosUsuarios permisosProgramas = new SistemaFormulariosPermisosUsuarios();
            permisosProgramas.setSistemaFormulario(formulario);

            indice = Collections.binarySearch(usuario.getListadoInterfacesPermisos(), permisosProgramas, new ComparadorIdFormulario());
            if (indice >= 0) {
                permisosProgramas = usuario.getListadoInterfacesPermisos().get(indice);

                if(!permisosProgramas.isPermitir_insertar())
                    formPrograma.getjTabbedPanePrograma().remove(formPrograma.getPnlTabPageInsertar());
                if(!permisosProgramas.isPermitir_editar())
                    formPrograma.getjTabbedPanePrograma().remove(formPrograma.getPnlTabPageEditar());
                if(!permisosProgramas.isPermitir_anular() && !permisosProgramas.isPermitir_navegar())
                    formPrograma.getjTabbedPanePrograma().remove(formPrograma.getPnlTabPageEliminar());
                else
                {
                    formPrograma.getBtnBuscarEliminar().setVisible(permisosProgramas.isPermitir_navegar());
                    formPrograma.getBtnEliminar().setVisible(permisosProgramas.isPermitir_anular());
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
}
