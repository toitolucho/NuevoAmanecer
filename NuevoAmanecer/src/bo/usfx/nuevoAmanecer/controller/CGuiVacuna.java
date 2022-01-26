/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Afiliado;
import bo.usfx.nuevoAmanecer.model.domain.Vacunas;
import bo.usfx.utils.ModeloVacunas;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import view.GuiVacuna;


public class CGuiVacuna extends KeyAdapter implements ActionListener, DocumentListener{

    private GuiVacuna formVacuna;
    private CommonDao dao;
    private ModeloVacunas modeloVacuna;

    public CGuiVacuna(GuiVacuna formVacuna) {
        this.formVacuna = formVacuna;
    }

    public CommonDao getDao() {
        return dao;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    public boolean ValidarFechas(JPanel pnlFechas)
    {
        for(Component compFechas : pnlFechas.getComponents())
        {
            if(compFechas instanceof JDateChooser)
            {
                JDateChooser dateVacunas = (JDateChooser)compFechas;
                Calendar FechaMinima =Calendar.getInstance();
                FechaMinima.add(Calendar.YEAR, -10);
                if(dateVacunas.getCalendar().after(Calendar.getInstance())
                        || dateVacunas.getCalendar().before(FechaMinima)
                        || dateVacunas.getCalendar() == null
                        )
                {
                    dateVacunas.grabFocus();
                    dateVacunas.cleanup();
                    return false;
                }
            }
        }
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();
        if(accion.compareTo(formVacuna.getBtnRegistrarr().getActionCommand()) == 0)
        {
            if(formVacuna.getTxtNombreAfiliado().getText().isEmpty())
            {
                JOptionPane.showMessageDialog(formVacuna, "aun no ha ingresado el Numero de Caso", "Datos Faltantes", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
//
//            if(formVacuna.getDateBCG().getCalendar().after(Calendar.getInstance()))
//            {
//                JOptionPane.showMessageDialog(formVacuna, "La Fecha Ingresada no es Valida", "Validacion de Datos", JOptionPane.INFORMATION_MESSAGE);
//                formVacuna.getDateBCG().grabFocus();
//                return;
//            }

            if(!ValidarFechas(formVacuna.getPnlFechasRegistro()))
            {
                JOptionPane.showMessageDialog(formVacuna, "Existe alguna fecha que no es concondarte, Tome en cuenta que"
                        + "la misma no puede ser Superior a la Fecha Actual, o anterior en demasía a un tope de 5 años, "
                        + "ya que un afiliado es considerado niño con vacunas a sus 5 años", "Validacion de Datos", JOptionPane.INFORMATION_MESSAGE);
                return;
            }


            Vacunas vacuna = new Vacunas(null, 
                    formVacuna.getDateBCG().getDate(),
                    formVacuna.getDateAntipolioI().getDate(),
                    formVacuna.getDateAntipolio1a().getDate(), 
                    formVacuna.getDateAntipolio2a().getDate(),
                    formVacuna.getDateAntipolio3a().getDate(),
                    formVacuna.getDateAntipolioRefuerzo().getDate(),
                    formVacuna.getDatePentaValente1a().getDate(),
                    formVacuna.getDatePentaValente2a().getDate(),
                    formVacuna.getDatePentaValente3a().getDate(),
                    formVacuna.getDatePentaValenteRefuerzo().getDate(), 
                    formVacuna.getDateSarampion().getDate(),
                    Integer.parseInt(formVacuna.getTxtNroCaso().getText()));
            this.formVacuna.getBtnRegistrarr().setEnabled(false);
            this.formVacuna.getBtnNuevo().setEnabled(true);
            try {

                if(dao.VerificarExistenciaGestion(vacuna))
                {
                    JOptionPane.showMessageDialog(formVacuna, "Ya Existe un registro para este número de caso en esta gestión, si desea modificarlo pase a la siguiente Pestaña", "Registro", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                dao.insertar(vacuna);
                JOptionPane.showMessageDialog(formVacuna, "Registro Satisfactorio", "Registro", JOptionPane.INFORMATION_MESSAGE);
                habilitarControlesNuevo(false);
                formVacuna.getTxtNroCaso().setText("");
                formVacuna.getTxtNroCaso().grabFocus();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(formVacuna, "No se pudo registrar el registro de vacuna");
            }
        }

        System.out.println("Accion realizada "  + accion);

        if(accion.compareTo(formVacuna.getBtnModificar().getActionCommand()) == 0)
        {
            System.out.println("modificiar");
            if(formVacuna.getTxtNroCasoModificar().getText().isEmpty())
            {
                JOptionPane.showMessageDialog(formVacuna, "aun no ha ingresado el Numero de Caso", "Datos Faltantes", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
//
//            if(formVacuna.getDateBCG().getCalendar().after(Calendar.getInstance()))
//            {
//                JOptionPane.showMessageDialog(formVacuna, "La Fecha Ingresada no es Valida", "Validacion de Datos", JOptionPane.INFORMATION_MESSAGE);
//                formVacuna.getDateBCG().grabFocus();
//                return;
//            }

            if(!ValidarFechas(formVacuna.getPnlFechasEdicion()))
            {
                JOptionPane.showMessageDialog(formVacuna, "Existe alguna fecha que no es concondarte, Tome en cuenta que"
                        + "la misma no puede ser Superior a la Fecha Actual, o anterior en demasía a un tope de 5 años, "
                        + "ya que un afiliado es considerado niño con vacunas a sus 5 años", "Validacion de Datos", JOptionPane.INFORMATION_MESSAGE);
                return;
            }


            Vacunas vacuna = new Vacunas(formVacuna.getDateFechaRegistroEdicion().getDate() ,
                    formVacuna.getDateBCGE().getDate(),
                    formVacuna.getDateAntiPolioIE().getDate(),
                    formVacuna.getDateAntioPolio1aE().getDate(),
                    formVacuna.getDateAntioPolio2aE().getDate(),
                    formVacuna.getDateAntioPolio3aE().getDate(),
                    formVacuna.getDateAntiPolioRefuerzoE().getDate(),
                    formVacuna.getDatePentaValente1aE().getDate(),
                    formVacuna.getDatePentaValente2aE().getDate(),
                    formVacuna.getDatePentaValente3aE().getDate(),
                    formVacuna.getDatePentaValenteRefuerzoE().getDate(),
                    formVacuna.getDateSaranpionE().getDate(),
                    Integer.parseInt(formVacuna.getTxtNroCasoModificar().getText()));
            try {

//                if(dao.VerificarExistenciaGestion(vacuna))
//                {
//                    JOptionPane.showMessageDialog(formVacuna, "Ya Existe un registro para este número de caso en esta gestión, si desea modificarlo pase a la siguiente Pestaña", "Registro", JOptionPane.INFORMATION_MESSAGE);
//                    return;
//                }

                dao.edit(vacuna);
                JOptionPane.showMessageDialog(formVacuna, "Actualización Correcta", "Registro", JOptionPane.INFORMATION_MESSAGE);
                habilitarControlesEdicion(false);
                formVacuna.getTxtNroCasoEliminar().setText("");
                formVacuna.getTxtNroCasoEliminar().grabFocus();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(formVacuna, "No se pudo actualizar el registro de vacuna");
            }
        }

        if(accion.compareTo("buscarE") == 0)
        {
            try {
                if(formVacuna.getDateFechaRegistroEdicion().getDate() == null)
                {
                    JOptionPane.showMessageDialog(formVacuna, "No ha ingresado la el Año en el cual desea hacer seguimiento de Vacunas del Afiliado");
                    return;
                }

                
                int numeroCaso = Integer.parseInt(formVacuna.getTxtNroCasoModificar().getText());
                Vacunas vacuna = new Vacunas();
                vacuna.setNumero_caso(numeroCaso);
                vacuna.setFecha_vacuna(formVacuna.getDateFechaRegistroEdicion().getDate());

                vacuna = (Vacunas) dao.obtenerObjeto(vacuna);
                if(vacuna != null)
                {
                    habilitarControlesEdicion(true);
                    cargarDatosVacuna(vacuna);
                    formVacuna.getDateBCGE().grabFocus();
                }
                else
                {
                    habilitarControlesEdicion(false);
                    limpiarCamposEdicion(true);
                    JOptionPane.showMessageDialog(formVacuna, "No se encontró ningún registro con la información provista");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if(accion.compareTo("buscarX") == 0)
        {
            try {
//                 if(formVacuna.getDateFechaRegistroEliminacion().getDate() == null)
//                {
//                    JOptionPane.showMessageDialog(formVacuna, "No ha ingresado la el Año en el cual desea hacer seguimiento de Vacunas del Afiliado");
//                    formVacuna.getDateFechaRegistroEliminacion().grabFocus();
//                    return;
//                }

                if(formVacuna.getTxtNroCasoEliminar().getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(formVacuna, "Aún no ha ingresado ningun texto de busqueda", "Gestión de Vacunas", JOptionPane.ERROR_MESSAGE);
                }

                int numeroCaso = Integer.parseInt(formVacuna.getTxtNroCasoEliminar().getText());
                Vacunas vacuna = new Vacunas();
                vacuna.setNumero_caso(numeroCaso);
                vacuna.setFecha_vacuna(formVacuna.getDateFechaRegistroEdicion().getDate());

                List<Vacunas> listavacuna = dao.findObjects(vacuna);
                modeloVacuna.clear();
                if(listavacuna != null && !listavacuna.isEmpty())
                {
                    for (Vacunas vacunas : listavacuna) {
                        modeloVacuna.addVacunas(vacunas);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(formVacuna, "No existen ningún registro con los datos provistos");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if(accion.compareTo(formVacuna.getBtnNuevo().getActionCommand())== 0)
        {
            habilitarControlesNuevo(true);
            limpiarCamposNuevo();
            this.formVacuna.getBtnRegistrarr().setEnabled(true);
            this.formVacuna.getBtnNuevo().setEnabled(false);
            
        }

        if(accion.compareTo(formVacuna.getBtnEliminar().getActionCommand()) == 0)
        {
            if(formVacuna.getjTableEliminar2().getSelectedRow() < 0)
            {
                JOptionPane.showMessageDialog(formVacuna, "Aún no ha seleccionado ningún registro", "Eliminacion", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(modeloVacuna != null && modeloVacuna.getRowCount() > 0)
            {
                int indice = formVacuna.getjTableEliminar2().getSelectedRow();
                if(indice >= 0)
                {
                    if(JOptionPane.showConfirmDialog(formVacuna, "¿Se encuentra seguro de eliminar el registro?", "Eliminación", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                        return;
                    Vacunas vacuna = modeloVacuna.getVacunas(indice);
                    try {
                        dao.delete(vacuna);
                        modeloVacuna.removeVacunas(indice);
                        JOptionPane.showMessageDialog(formVacuna, "Eliminación completa");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(formVacuna, "No se pudo completar la eliminacion debido a " + ex.getMessage(), "Eliminacion", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }

                }
            }
        }
    }

    public void cargarDatosVacuna(Vacunas vac)
    {
        if(vac != null)
        {
            formVacuna.getDateBCGE().setDate(vac.getBcg());
            formVacuna.getDateAntiPolioIE().setDate(vac.getAntipolio_i());
            formVacuna.getDateAntioPolio1aE().setDate(vac.getAntipolio_1a());
            formVacuna.getDateAntioPolio2aE().setDate(vac.getAntipolio_2a());
            formVacuna.getDateAntioPolio3aE().setDate(vac.getAntipolio_3a());
            formVacuna.getDateAntiPolioRefuerzoE().setDate(vac.getAntipolio_refuerzo());
            formVacuna.getDatePentaValente1aE().setDate(vac.getPentavalente_1a());
            formVacuna.getDatePentaValente2aE().setDate(vac.getPentavalente_2a());
            formVacuna.getDatePentaValente3aE().setDate(vac.getPentavalente_3a());
            formVacuna.getDatePentaValenteRefuerzoE().setDate(vac.getPentavalente_ref());
            formVacuna.getDateSaranpionE().setDate(vac.getSarampion());
        }
    }

    public void onFormShown()
    {
       limpiarCamposEdicion(true);
       limpiarCamposNuevo();
       
       habilitarControlesEdicion(false);
       habilitarControlesNuevo(false);

       this.formVacuna.getBtnNuevo().setEnabled(true);
       this.formVacuna.getBtnRegistrarr().setEnabled(false);

       modeloVacuna = new ModeloVacunas();
       this.formVacuna.getjTableEliminar2().setModel(modeloVacuna);
    }

    public void limpiarCamposEdicion(boolean conNullos)
    {
        Date fechaActual = conNullos ? null : Calendar.getInstance().getTime();
        formVacuna.getDateBCGE().setDate(fechaActual);
        formVacuna.getDateAntiPolioIE().setDate(fechaActual);
        formVacuna.getDateAntioPolio1aE().setDate(fechaActual);
        formVacuna.getDateAntioPolio2aE().setDate(fechaActual);
        formVacuna.getDateAntioPolio3aE().setDate(fechaActual);
        formVacuna.getDateAntiPolioRefuerzoE().setDate(fechaActual);
        formVacuna.getDatePentaValente1aE().setDate(fechaActual);
        formVacuna.getDatePentaValente2aE().setDate(fechaActual);
        formVacuna.getDatePentaValente3aE().setDate(fechaActual);
        formVacuna.getDatePentaValenteRefuerzoE().setDate(fechaActual);
        formVacuna.getDateSaranpionE().setDate(fechaActual);
    }
    
    public void habilitarControlesEdicion(boolean estadoHabilitacion)
    {        
        formVacuna.getDateBCGE().setEnabled(estadoHabilitacion);
        formVacuna.getDateAntiPolioIE().setEnabled(estadoHabilitacion);
        formVacuna.getDateAntioPolio1aE().setEnabled(estadoHabilitacion);
        formVacuna.getDateAntioPolio2aE().setEnabled(estadoHabilitacion);
        formVacuna.getDateAntioPolio3aE().setEnabled(estadoHabilitacion);
        formVacuna.getDateAntiPolioRefuerzoE().setEnabled(estadoHabilitacion);
        formVacuna.getDatePentaValente1aE().setEnabled(estadoHabilitacion);
        formVacuna.getDatePentaValente2aE().setEnabled(estadoHabilitacion);
        formVacuna.getDatePentaValente3aE().setEnabled(estadoHabilitacion);
        formVacuna.getDatePentaValenteRefuerzoE().setEnabled(estadoHabilitacion);
        formVacuna.getDateSaranpionE().setEnabled(estadoHabilitacion);
    }

    public void limpiarCamposNuevo()
    {
        Date fechaActual = null;
        formVacuna.getDateBCG().setDate(fechaActual);
        formVacuna.getDateAntipolioI().setDate(fechaActual);
        formVacuna.getDateAntipolio1a().setDate(fechaActual);
        formVacuna.getDateAntipolio2a().setDate(fechaActual);
        formVacuna.getDateAntipolio3a().setDate(fechaActual);
        formVacuna.getDateAntipolioRefuerzo().setDate(fechaActual);
        formVacuna.getDatePentaValente1a().setDate(fechaActual);
        formVacuna.getDatePentaValente2a().setDate(fechaActual);
        formVacuna.getDatePentaValente3a().setDate(fechaActual);
        formVacuna.getDatePentaValenteRefuerzo().setDate(fechaActual);
        formVacuna.getDateSarampion().setDate(fechaActual);
    }

    public void habilitarControlesNuevo(boolean estadoHabilitacion)
    {
        formVacuna.getDateBCG().setEnabled(estadoHabilitacion);
        formVacuna.getDateAntipolioI().setEnabled(estadoHabilitacion);
        formVacuna.getDateAntipolio1a().setEnabled(estadoHabilitacion);
        formVacuna.getDateAntipolio2a().setEnabled(estadoHabilitacion);
        formVacuna.getDateAntipolio3a().setEnabled(estadoHabilitacion);
        formVacuna.getDateAntipolioRefuerzo().setEnabled(estadoHabilitacion);
        formVacuna.getDatePentaValente1a().setEnabled(estadoHabilitacion);
        formVacuna.getDatePentaValente2a().setEnabled(estadoHabilitacion);
        formVacuna.getDatePentaValente3a().setEnabled(estadoHabilitacion);
        formVacuna.getDatePentaValenteRefuerzo().setEnabled(estadoHabilitacion);
        formVacuna.getDateSarampion().setEnabled(estadoHabilitacion);
    }

    public void insertUpdate(DocumentEvent e) {
        obtenerAfiliado();
    }

    public void removeUpdate(DocumentEvent e) {
        obtenerAfiliado();
    }

    public void changedUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void obtenerAfiliado()
    {
        if(formVacuna.getTxtNroCaso().getText().trim().isEmpty())
            return;
        int NumeroCaso = -1;
        try {

            try {
                NumeroCaso= Integer.parseInt(formVacuna.getTxtNroCaso().getText());
            } catch (NumberFormatException e) {
            }

            Afiliado afiliado = new Afiliado();
            afiliado.setNumero_caso(NumeroCaso);
            afiliado = (Afiliado) dao.obtenerObjeto(afiliado);
            if(afiliado != null  && !formVacuna.getTxtNroCaso().getText().isEmpty())
            {
                int edad = dao.ObtenerEdadActual(afiliado.getFecha_nacimiento(), dao.obtenerFechaHoraServidor());
                System.out.println("Edad actual del Afiliado " + afiliado.getNombreCompleto() + " " + edad);
                if(edad > 5)
                {
                    JOptionPane.showMessageDialog(formVacuna, "No puede trabajar con los datos de Vacuna de un afiliado que no es considerado NIÑO");
                    formVacuna.getTxtNombreAfiliado().setText("");
                    habilitarControlesNuevo(false);
                    return;
                }

                formVacuna.getTxtNombreAfiliado().setText(afiliado.getNombreCompleto());
                habilitarControlesNuevo(true);
                formVacuna.getDateBCG().grabFocus();
            }
            else
            {
                formVacuna.getTxtNombreAfiliado().setText("");
                habilitarControlesNuevo(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        char c = e.getKeyChar();
        if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE) ))) {
                formVacuna.getToolkit().beep();
                e.consume();
        }

    }
}
