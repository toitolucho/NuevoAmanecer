/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Afiliado;
import bo.usfx.nuevoAmanecer.model.domain.Eccd;
import bo.usfx.utils.ModeloEccd;
import bo.usfx.utils.ObjetoCodigoDescripcion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import view.GuiEccd;


public class CGuiEccd extends KeyAdapter implements ActionListener, DocumentListener{
    private CommonDao dao;
    private GuiEccd formEccd;
    private Eccd eccd;
    private ModeloEccd modelo;
    public CGuiEccd(GuiEccd formEccd) {
        this.formEccd = formEccd;
        eccd = new Eccd();
    }

    public CommonDao getDao() {
        return dao;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    

    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();
        if(accion.compareTo("buscarE") == 0)
        {
            if(formEccd.getDateFechaRegistroEdicion().getDate() == null)
            {
                JOptionPane.showMessageDialog(formEccd, "No ha ingresado la Gestion en el cual desea hacer seguimiento al Afiliado");
                return;
            }

            try {
                eccd = new Eccd();
                eccd.setNumero_caso(Integer.parseInt(formEccd.getTxtNumeroCasoBusqueda().getText()));
                eccd.setFecha_registro(formEccd.getDateFechaRegistroEdicion().getDate());
                eccd = (Eccd) dao.obtenerObjeto(eccd);
                if(eccd != null)
                {
                    habilitarCamposEdicion(true);
                    cargarEccdEdicion(eccd);
                    formEccd.getTxtPesoE().grabFocus();
                }
                else
                {
                    habilitarCamposEdicion(false);
                    limpiarCamposEdicion();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        if(accion.compareTo(formEccd.getBtnNuevo().getActionCommand()) == 0)
        {
            formEccd.getBtnNuevo().setEnabled(false);
            formEccd.getBtnRegistrar().setEnabled(true);

            formEccd.getTxtNroCaso().grabFocus();
            limpiarCamposNuevo();
            habilitarCamposNuevo(true);
        }
        if(accion.compareTo(formEccd.getBtnRegistrar().getActionCommand()) == 0)
        {
            if(!validarCamposNuevo())
            {
                JOptionPane.showMessageDialog(formEccd, "Revise su datos", "Validación de datos", JOptionPane.ERROR_MESSAGE);
                return;
            }

            eccd = new Eccd( Calendar.getInstance().getTime(), Float.parseFloat(formEccd.getTxtPeso().getText()),
                    Float.parseFloat(formEccd.getTxtTalla().getText()),
                    formEccd.getTxtPesoTalla().getText(),
                    ((ObjetoCodigoDescripcion)formEccd.getcBoxSevenN().getSelectedItem()).getCodigoObjeto(),
                    Integer.parseInt(formEccd.getTxtNroCaso().getText()));
            try {

                if(dao.VerificarExistenciaGestion(eccd))
                {
                    JOptionPane.showMessageDialog(formEccd, "Ya Existe un registro para este número de caso en esta gestión, si desea modificarlo pase a la siguiente Pestaña", "Registro", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                dao.insertar(eccd);
                JOptionPane.showMessageDialog(formEccd, "Registro realizado satisfactoriamente", "Registro Completo", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(formEccd, "No se pudo registrar "
                        + "Ocurrio la siguiente excepcion " + ex.getMessage(), "Registro Incompleto", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
            formEccd.getBtnNuevo().setEnabled(true);
            formEccd.getBtnRegistrar().setEnabled(false);
        }

        if(accion.compareTo(formEccd.getBtnModificar().getActionCommand()) == 0)
        {
            System.out.println("modificar");
            if(!validarCamposEdicion())
            {
                JOptionPane.showMessageDialog(formEccd, "Revise su datos", "Validación de datos", JOptionPane.ERROR_MESSAGE);
                return;
            }

            eccd = new Eccd(eccd.getFecha_registro(), Float.parseFloat(formEccd.getTxtPesoE().getText()),
                    Float.parseFloat(formEccd.getTxtTallaE().getText()),
                    formEccd.getTxtPesoTallaE().getText(),
                    ((ObjetoCodigoDescripcion)formEccd.getcBoxSevenE().getSelectedItem()).getCodigoObjeto(),
                    Integer.parseInt(formEccd.getTxtNroCasoE().getText()));
            try {
                dao.edit(eccd);
                JOptionPane.showMessageDialog(formEccd, "Se actualizó correctamente el registro", "Registro Completo", JOptionPane.INFORMATION_MESSAGE);
                habilitarCamposEdicion(false);
                formEccd.getTxtNumeroCasoBusqueda().grabFocus();
                formEccd.getTxtNumeroCasoBusqueda().selectAll();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(formEccd, "No se pudo guardar los cambios realizados ", "Registro Incompleto", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
        if(accion.compareTo("buscarX") == 0)
        {
            if(formEccd.getTxtNumeroCasoEliminacion().getText().isEmpty())
            {
                JOptionPane.showMessageDialog(formEccd, "aún no ha ingresado un Numero de Caso", "Eliminación", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            eccd = new Eccd();
            try {
                eccd.setNumero_caso(Integer.valueOf(formEccd.getTxtNumeroCasoEliminacion().getText()));
                List<Eccd> listaEccd = dao.findObjects(eccd);
                modelo.clear();
                if(listaEccd != null && !listaEccd.isEmpty())
                {
                    for (Eccd eccd : listaEccd) {
                        modelo.addEccd(eccd);
                    }
                }
                else
                    JOptionPane.showMessageDialog(formEccd, "No se encontró ningún registro");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if(accion.compareTo(formEccd.getBtnEliminar().getActionCommand()) == 0)
        {
            if(formEccd.getjTableEliminar1().getSelectedRow() < 0)
            {
                JOptionPane.showMessageDialog(formEccd, "Aún no ha seleccionado ningun registro para su eliminación", "Eliminación", JOptionPane.ERROR_MESSAGE);
                            return;
            }
            if(modelo != null && modelo.getRowCount() > 0)
            {
                int indiceSeleccionado = formEccd.getjTableEliminar1().getSelectedRow();
                if(indiceSeleccionado>=0)
                {

                    if(JOptionPane.showConfirmDialog(formEccd, "¿Se encuentra seguro de eliminar este registro?", "Eliminación", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                            return;
                    eccd = modelo.getEccd(indiceSeleccionado);
                    try {
                        dao.delete(eccd);
                    } catch (SQLException ex) {
                        Logger.getLogger(CGuiEccd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    modelo.removeEccd(indiceSeleccionado);
                }
            }
        }
    }

    public void cargarEccdEdicion(Eccd dato)
    {
        if(dato != null)
        {
            this.formEccd.getTxtNroCasoE().setText(String.valueOf(dato.getNumero_caso()));
            Afiliado afiliado = new Afiliado();
            afiliado.setNumero_caso(dato.getNumero_caso());
            try {
                afiliado = (Afiliado) dao.obtenerObjeto(afiliado);
                 if(afiliado != null)
                {

                     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String fecha = "";
                    fecha = df.format(afiliado.getFecha_nacimiento());

                    formEccd.getTxtFechaE().setText(fecha);
                    formEccd.getTxtSexoE().setText(afiliado.getRepresentacionCadenaSexo());
                    formEccd.getTxtNombreE().setText(afiliado.getNombreCompleto());
                }
                else
                {
                    formEccd.getTxtFechaE().setText("");
                    formEccd.getTxtSexoE().setText("");
                    formEccd.getTxtNombreE().setText("");
                }
                formEccd.getTxtPesoE().setText(String.valueOf(dato.getPeso()));
                formEccd.getTxtPesoTallaE().setText(String.valueOf(dato.getPeso_talla()));
                formEccd.getTxtTallaE().setText(String.valueOf(dato.getTalla()));                
                if(dato.getSven().compareTo("NS")==0)
                	formEccd.getcBoxSevenE().setSelectedIndex(0);
                if(dato.getSven().compareTo("N")==0)
                	formEccd.getcBoxSevenE().setSelectedIndex(1);
                if(dato.getSven().compareTo("DL")==0)
                	formEccd.getcBoxSevenE().setSelectedIndex(2);
                if(dato.getSven().compareTo("DM")==0)
                	formEccd.getcBoxSevenE().setSelectedIndex(3);
                if(dato.getSven().compareTo("DS")==0)
                	formEccd.getcBoxSevenE().setSelectedIndex(4);
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void limpiarCamposNuevo()
    {
        
        this.formEccd.getTxtFecha().setText("");
        this.formEccd.getTxtNombre().setText("");        
        this.formEccd.getTxtPeso().setText("");
        this.formEccd.getTxtPesoTalla().setText("");
        this.formEccd.getTxtSexo().setText("");
        this.formEccd.getcBoxSevenN().setSelectedIndex(-1);
        this.formEccd.getTxtTalla().setText("");
        
        this.formEccd.getTxtNroCaso().setText("");
    }

    public void habilitarCamposNuevo(boolean estadoHabilitacion)
    {
        
        
        this.formEccd.getTxtNroCaso().setEditable(estadoHabilitacion);
        this.formEccd.getTxtPeso().setEditable(estadoHabilitacion);
        this.formEccd.getTxtPesoTalla().setEditable(estadoHabilitacion);
        
//        this.formEccd.getTxtSven().setEditable(estadoHabilitacion);
        this.formEccd.getcBoxSevenN().setEnabled(estadoHabilitacion);
        this.formEccd.getTxtTalla().setEditable(estadoHabilitacion);
        
    }

    public void limpiarCamposEdicion()
    {
        
        this.formEccd.getTxtFechaE().setText("");
        this.formEccd.getTxtNombreE().setText("");
        this.formEccd.getTxtNroCasoE().setText("");
        this.formEccd.getTxtPesoE().setText("");
        this.formEccd.getTxtPesoTallaE().setText("");
        this.formEccd.getTxtSexoE().setText("");
        this.formEccd.getcBoxSevenE().setSelectedIndex(-1);
        this.formEccd.getTxtTallaE().setText("");
    }

    public void habilitarCamposEdicion(boolean estadoHabilitacion)
    {
        
//        this.formEccd.getTxtFechaE().setEditable(estadoHabilitacion);
//        this.formEccd.getTxtNombreE().setEditable(estadoHabilitacion);
//        this.formEccd.getTxtNroCasoE().setEditable(estadoHabilitacion);
        this.formEccd.getTxtPesoE().setEditable(estadoHabilitacion);
        this.formEccd.getTxtPesoTallaE().setEditable(estadoHabilitacion);
//        this.formEccd.getTxtSexoE().setEditable(estadoHabilitacion);
        this.formEccd.getcBoxSevenE().setEnabled(estadoHabilitacion);
        this.formEccd.getTxtTallaE().setEditable(estadoHabilitacion);
    }

    public boolean validarCamposNuevo()
    {
        String mensajeError ="";
        if(formEccd.getTxtNroCaso().getText().isEmpty())
        {
            mensajeError = "Aún no ha ingresado el Nro de Caso";
            formEccd.getTxtNroCaso().grabFocus();
            JOptionPane.showMessageDialog(formEccd, mensajeError, "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if(formEccd.getTxtNombre().getText().isEmpty())
        {
            mensajeError = "Aún no ha ingresado el Nombre";
            formEccd.getTxtNombre().grabFocus();
            JOptionPane.showMessageDialog(formEccd, mensajeError, "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

         if(formEccd.getTxtPeso().getText().isEmpty())
        {
            mensajeError = "Aún no ha ingresado el Peso";
            formEccd.getTxtPeso().grabFocus();
            JOptionPane.showMessageDialog(formEccd, mensajeError, "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if(formEccd.getTxtTalla().getText().isEmpty())
        {
            mensajeError = "Aún no ha ingresado la Talla";
            formEccd.getTxtTalla().grabFocus();
            JOptionPane.showMessageDialog(formEccd, mensajeError, "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if(formEccd.getTxtPesoTalla().getText().isEmpty())
        {
            mensajeError = "Aún no ha ingresado la Talla/Peso";
            formEccd.getTxtPesoTalla().grabFocus();
            JOptionPane.showMessageDialog(formEccd, mensajeError, "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if(formEccd.getcBoxSevenN().getSelectedIndex() == -1)
        {
            mensajeError = "Aún no ha ingresado SVEN";
            formEccd.getcBoxSevenN().grabFocus();
            JOptionPane.showMessageDialog(formEccd, mensajeError, "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }        
        
        return true;
    }

    public boolean validarCamposEdicion()
    {
        String mensajeError ="";
        if(formEccd.getTxtNroCasoE().getText().isEmpty())
        {
            mensajeError = "Aún no ha ingresado el Nro de Caso";
            formEccd.getTxtNroCasoE().grabFocus();
            JOptionPane.showMessageDialog(formEccd, mensajeError, "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if(formEccd.getTxtNombreE().getText().isEmpty())
        {
            mensajeError = "Aún no ha ingresado el Nombre";
            formEccd.getTxtNombreE().grabFocus();
            JOptionPane.showMessageDialog(formEccd, mensajeError, "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

         if(formEccd.getTxtPesoE().getText().isEmpty())
        {
            mensajeError = "Aún no ha ingresado el Peso";
            formEccd.getTxtPesoE().grabFocus();
            JOptionPane.showMessageDialog(formEccd, mensajeError, "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if(formEccd.getTxtTallaE().getText().isEmpty())
        {
            mensajeError = "Aún no ha ingresado la Talla";
            formEccd.getTxtTallaE().grabFocus();
            JOptionPane.showMessageDialog(formEccd, mensajeError, "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if(formEccd.getTxtPesoTallaE().getText().isEmpty())
        {
            mensajeError = "Aún no ha ingresado la Talla/Peso";
            formEccd.getTxtPesoTallaE().grabFocus();
            JOptionPane.showMessageDialog(formEccd, mensajeError, "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if(formEccd.getcBoxSevenE().getSelectedIndex() == -1)
        {
            mensajeError = "Aún no ha ingresado SVEN";
            formEccd.getcBoxSevenE().grabFocus();
            JOptionPane.showMessageDialog(formEccd, mensajeError, "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        return true;
    }

    public void onFormShown()
    {
        limpiarCamposEdicion();
        limpiarCamposNuevo();
        habilitarCamposEdicion(false);
        habilitarCamposNuevo(false);

        this.formEccd.getBtnNuevo().setEnabled(true);
        this.formEccd.getBtnRegistrar().setEnabled(false);
        modelo = new ModeloEccd();
        this.formEccd.getjTableEliminar1().setModel(modelo);
        
        
        this.formEccd.getcBoxSevenE().addItem(new ObjetoCodigoDescripcion("NS", "NUTRICION SUPERIOR"));
        this.formEccd.getcBoxSevenE().addItem(new ObjetoCodigoDescripcion("N", "NUTRICION NORMAL"));
        this.formEccd.getcBoxSevenE().addItem(new ObjetoCodigoDescripcion("DL", "DESNUTRICION LEVE 1ER GRADO"));
        this.formEccd.getcBoxSevenE().addItem(new ObjetoCodigoDescripcion("DM", "DESNUTRICION MODERADA 2DO GRADO"));
        this.formEccd.getcBoxSevenE().addItem(new ObjetoCodigoDescripcion("DS", "DESNUTRICION SEVERA 3ER GRADO"));
        
        this.formEccd.getcBoxSevenN().addItem(new ObjetoCodigoDescripcion("NS", "NUTRICION SUPERIOR"));
        this.formEccd.getcBoxSevenN().addItem(new ObjetoCodigoDescripcion("N", "NUTRICION NORMAL"));
        this.formEccd.getcBoxSevenN().addItem(new ObjetoCodigoDescripcion("DL", "DESNUTRICION LEVE 1ER GRADO"));
        this.formEccd.getcBoxSevenN().addItem(new ObjetoCodigoDescripcion("DM", "DESNUTRICION MODERADA 2DO GRADO"));
        this.formEccd.getcBoxSevenN().addItem(new ObjetoCodigoDescripcion("DS", "DESNUTRICION SEVERA 3ER GRADO"));
    }

    public void cargarDatosAfiliado()
    {
        if(formEccd.getTxtNroCaso().getText().trim().isEmpty())
            return;
        Afiliado afiliado = new Afiliado();        
        try {
            try {
                afiliado.setNumero_caso(Integer.parseInt(formEccd.getTxtNroCaso().getText()));
            } catch (NumberFormatException e) {
            }

            
            afiliado = (Afiliado) dao.obtenerObjeto(afiliado);
            
            if(afiliado != null && !formEccd.getTxtNroCaso().getText().isEmpty())
            {

                int edad = dao.ObtenerEdadActual(afiliado.getFecha_nacimiento(), dao.obtenerFechaHoraServidor());
                System.out.println("Edad actual del Afiliado " + afiliado.getNombreCompleto() + " " + edad);
                if(edad > 5)
                {
                    JOptionPane.showMessageDialog(formEccd, "No puede trabajar con los datos de ECCD de un afiliado que no es considerado NIÑO");
                    formEccd.getTxtFecha().setText("");
                    formEccd.getTxtSexo().setText("");
                    formEccd.getTxtNombre().setText("");
                    return;
                }

                formEccd.getTxtFecha().setText(afiliado.getFecha_nacimiento().toString());
                formEccd.getTxtSexo().setText(afiliado.getRepresentacionCadenaSexo());
                formEccd.getTxtNombre().setText(afiliado.getNombreCompleto());


            }
            else
            {
                JOptionPane.showMessageDialog(formEccd, "No se encontró ningun registro con la información brindada", "ECCD", JOptionPane.ERROR_MESSAGE);
                formEccd.getTxtFecha().setText("");
                formEccd.getTxtSexo().setText("");
                formEccd.getTxtNombre().setText("");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }
    public void insertUpdate(DocumentEvent e) {
        
            cargarDatosAfiliado();
    }

    public void removeUpdate(DocumentEvent e) {
        if(e.getLength() > 0)
            cargarDatosAfiliado();
    }

    public void changedUpdate(DocumentEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            JTextField componente = (JTextField) e.getSource();
            if (!componente.equals(formEccd.getTxtNroCaso())) {
                    char c = e.getKeyChar();
                    if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                                    || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_COMMA)) || (c == KeyEvent.VK_PERIOD))) {
                            formEccd.getToolkit().beep();
                            e.consume();
                    }
            }else
            {
                char c = e.getKeyChar();
                    if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                                    || (c == KeyEvent.VK_DELETE) ))) {
                            formEccd.getToolkit().beep();
                            e.consume();
                    }
            }

    }
}
