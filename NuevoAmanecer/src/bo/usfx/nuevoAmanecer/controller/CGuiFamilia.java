/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.ActividadEducativa;
import bo.usfx.nuevoAmanecer.model.domain.Afiliado;
import bo.usfx.nuevoAmanecer.model.domain.Casos;
import bo.usfx.nuevoAmanecer.model.domain.CausasMuerte;
import bo.usfx.nuevoAmanecer.model.domain.EventosVitales;
import bo.usfx.nuevoAmanecer.model.domain.Familia;
import bo.usfx.nuevoAmanecer.model.domain.Familiar;
import bo.usfx.nuevoAmanecer.model.domain.Ocupaciones;
import bo.usfx.nuevoAmanecer.model.domain.Padrinos;
import bo.usfx.nuevoAmanecer.model.domain.Tarjeta;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.utils.FormUtilities;
import bo.usfx.utils.ModeloFamilia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.GuiFamilia;
import view.GuiPatrocinadorBuscador;


public class CGuiFamilia extends KeyAdapter implements ActionListener, ListSelectionListener{
    private GuiFamilia formFamilia;
    private int NumeroProyecto;
    private CommonDao dao;
    ModeloFamilia modeloFamiliaNuevo, modeloFamiliaEdicion, modeloFamiliaEliminacion;

    ArrayList<Tarjeta> ListadoTarjetas;
    ArrayList<Ocupaciones> listadoOcupaciones;
    ArrayList<EventosVitales> listadoEventosVitales;
    ArrayList<ActividadEducativa> listadoActividadEducativa;
    ArrayList<CausasMuerte> listadoCausasMuerte;
    private Usuarios usuario;
    private int idFormulario;
    private Connection conexion;

    public CommonDao getDao() {
        return dao;
    }

    public void setNumeroProyecto(int NumeroProyecto) {
        this.NumeroProyecto = NumeroProyecto;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    public CGuiFamilia(GuiFamilia formFamilia) {
        this.formFamilia = formFamilia;
        this.formFamilia.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modeloFamiliaEdicion = new ModeloFamilia();
        modeloFamiliaEliminacion = new ModeloFamilia();
        modeloFamiliaNuevo = new ModeloFamilia();

        

    }

    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();
        if(accion.compareTo("modificarAfiliado") == 0)
        {
//            if(formFamilia.getjTableListadoFamiliaModificar().getSelectedRow() >= 0)
//            {
//                formFamilia.getTxtNumeroNinoE().setEnabled(true);
//                formFamilia.getTxtNumeroCasoE().setEnabled(true);
//                formFamilia.getcBoxTipoNinoE().setEnabled(true);
//                formFamilia.getTxtNombreCortoE().setEnabled(true);
//                formFamilia.getCheckVacunaNinoE().setEnabled(true);
//                formFamilia.getCheckVacunaMadreE().setEnabled(false);
//
//
//                formFamilia.getCheckVacunaMadreE().setSelected(false);
//                formFamilia.getTxtNumeroCasoE().setText("");
//                formFamilia.getcBoxTipoNinoE().setSelectedIndex(0);
//                formFamilia.getTxtNombreCortoE().setText("");
//                formFamilia.getCheckVacunaNinoE().setSelected(false);
//            }


            modeloFamiliaEdicion.clear();

            formFamilia.getTxtNumeroNinoE().setEnabled(true);
            formFamilia.getTxtNumeroCasoE().setEnabled(true);
            formFamilia.getcBoxTipoNinoE().setEnabled(true);
            formFamilia.getTxtNombreCortoE().setEnabled(true);
            formFamilia.getCheckVacunaNinoE().setEnabled(true);
            formFamilia.getCheckVacunaMadreE().setEnabled(false);


            formFamilia.getCheckVacunaMadreE().setSelected(false);
            formFamilia.getTxtNumeroCasoE().setText("");
            formFamilia.getcBoxTipoNinoE().setSelectedIndex(0);
            formFamilia.getTxtNombreCortoE().setText("");
            formFamilia.getCheckVacunaNinoE().setSelected(false);
            formFamilia.getTxtTextoBusquedaE().setText(" ");
            formFamilia.getTxtTextoBusquedaE().selectAll();
            formFamilia.getTxtTextoBusquedaE().grabFocus();
            accion = "buscarE";
            formFamilia.getBtnAsignarPadrino().setEnabled(true);

        }

        if(accion.compareTo("modificarFamiliar") == 0)
        {
//            if(formFamilia.getjTableListadoFamiliaModificar().getSelectedRow() >= 0)
//            {
//                formFamilia.getTxtNumeroNinoE().setEnabled(false);
//                formFamilia.getTxtNumeroCasoE().setEnabled(false);
//                formFamilia.getcBoxTipoNinoE().setEnabled(false);
//                formFamilia.getTxtNombreCortoE().setEnabled(false);
//                formFamilia.getCheckVacunaNinoE().setEnabled(false);
//                formFamilia.getCheckVacunaMadreE().setEnabled(true);
//
//                formFamilia.getCheckVacunaMadreE().setSelected(false);
//                formFamilia.getTxtNumeroCasoE().setText("");
//                formFamilia.getcBoxTipoNinoE().setSelectedIndex(0);
//                formFamilia.getTxtNombreCortoE().setText("");
//                formFamilia.getCheckVacunaNino().setSelected(false);
//            }

            modeloFamiliaEdicion.clear();

            formFamilia.getTxtNumeroNinoE().setEnabled(false);
            formFamilia.getTxtNumeroCasoE().setEnabled(false);
            formFamilia.getcBoxTipoNinoE().setEnabled(false);
            formFamilia.getTxtNombreCortoE().setEnabled(false);
            formFamilia.getCheckVacunaNinoE().setEnabled(false);
            formFamilia.getCheckVacunaMadreE().setEnabled(true);

            formFamilia.getCheckVacunaMadreE().setSelected(false);
            formFamilia.getTxtNumeroCasoE().setText("");
            formFamilia.getcBoxTipoNinoE().setSelectedIndex(0);
            formFamilia.getTxtNombreCortoE().setText("");
            formFamilia.getCheckVacunaNino().setSelected(false);
            formFamilia.getTxtTextoBusquedaE().setText(" ");
            formFamilia.getTxtTextoBusquedaE().selectAll();
            formFamilia.getTxtTextoBusquedaE().grabFocus();
            accion = "buscarE";
            formFamilia.getBtnAsignarPadrino().setEnabled(false);
        }



        if(accion.compareTo(formFamilia.getBtnBuscarE().getActionCommand()) == 0)
        {
            
            if(formFamilia.getTxtTextoBusquedaE().getText().isEmpty())
            {
                JOptionPane.showMessageDialog(formFamilia, "Aún no aingresado un texto para buscarlo", "Datos Incompletos", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                String textoBusqueda = formFamilia.getTxtTextoBusquedaE().getText();
                List<Familia> listaPersonas = null;
                formFamilia.getLblDatosPatrocinador().setText("");
                if(formFamilia.getRbtnModificarAfiliado().isSelected())
                {
                    Afiliado afiliado = new Afiliado();
                    afiliado.setNombres(textoBusqueda);

                    try {
                        afiliado.setCodigo_persona(Integer.parseInt(textoBusqueda));
                        afiliado.setNumero_caso(Integer.parseInt(textoBusqueda));
                    } catch (NumberFormatException ex) {
                         afiliado.setCodigo_persona(-1);
                        afiliado.setNumero_caso(-1);
                    }
                    listaPersonas = dao.findObjects(afiliado);
//                    listaPersonas = dao.getObjects("Afiliado");
                }
                else if(formFamilia.getRbtnModificarFamiliar().isSelected())
                {
                    Familiar familiar = new Familiar();
                    familiar.setNombres(textoBusqueda);

                    try {
                        familiar.setCodigo_persona(Integer.parseInt(textoBusqueda));

                    } catch (NumberFormatException exx) {
                         familiar.setCodigo_persona(-1);
                    }
                    listaPersonas = dao.findObjects(familiar);
                }
                System.out.println("buscar para edicion");
                cargarFamiliaParaTarjeta(listaPersonas, 'E');
                if(listaPersonas == null || listaPersonas.size() == 0)
                    JOptionPane.showMessageDialog(formFamilia, "No existen ningun registro bajo la descripción dada", "Busqueda de Miembros de Libreta", JOptionPane.INFORMATION_MESSAGE);
                else
                {
                    habilitarControlesEdicion(true);
                }
            } catch (Exception ext) {
                System.out.println(ext);
                ext.printStackTrace();
            }
        }
        System.out.println("ación ejecutada " + accion);
        if(accion.compareTo("registrarAfiliado") == 0)
        {
            if(!formFamilia.getBtnNuevoFamilia().isEnabled())
            {
                formFamilia.getTxtNumeroNino().setEnabled(true);
                formFamilia.getTxtNumeroCaso().setEnabled(true);
                formFamilia.getcBoxTipoNino().setEnabled(true);
                formFamilia.getTxtNombreCorto().setEnabled(true);
                formFamilia.getCheckVacunaNino().setEnabled(true);
                formFamilia.getCheckVacunaMadre().setEnabled(false);

                formFamilia.getCboxCausasMuerte().setEnabled(false);
                formFamilia.getCboxEventosVitales().removeAllItems();
                for(EventosVitales eventoVital : listadoEventosVitales)
                {
                    if(eventoVital.getNombre_evento_vital().compareTo("Personas que se fueron") != 0
                            && eventoVital.getNombre_evento_vital().compareTo("Embarazo") != 0
                            && eventoVital.getNombre_evento_vital().compareTo("Muertes") != 0)
                    formFamilia.getCboxEventosVitales().addItem(eventoVital);
                    
                }


                formFamilia.getCheckVacunaMadre().setSelected(false);
                formFamilia.getTxtNumeroCaso().setText("");
                formFamilia.getcBoxTipoNino().setSelectedIndex(0);
                formFamilia.getTxtNombreCorto().setText("");
                formFamilia.getCheckVacunaNino().setSelected(false);
                formFamilia.getTxtNumeroNino().setText("");
                try {
                int Numerofamilia = dao.obtenerUltimoObjetoTabla(new Familia(),"");
                Numerofamilia = Numerofamilia == -1 ? 1 : Numerofamilia +1;
                this.formFamilia.getTxtNumeroCaso().setText(String.valueOf(Numerofamilia));
                } catch (SQLException ex) {
//                    Logger.getLogger(CGuiFamilia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if(accion.compareTo("registrarFamiliar") == 0)
        {
            if(!formFamilia.getBtnNuevoFamilia().isEnabled())
            {
                formFamilia.getTxtNumeroNino().setEnabled(false);
                formFamilia.getTxtNumeroCaso().setEnabled(false);
                formFamilia.getcBoxTipoNino().setEnabled(false);
                formFamilia.getTxtNombreCorto().setEnabled(false);
                formFamilia.getCheckVacunaNino().setEnabled(false);
                formFamilia.getCheckVacunaMadre().setEnabled(true);

                formFamilia.getCboxCausasMuerte().setEnabled(false);
                formFamilia.getCboxEventosVitales().removeAllItems();
                for(EventosVitales eventoVital : listadoEventosVitales)
                {
                    if(eventoVital.getNombre_evento_vital().compareTo("Personas que se fueron") != 0                            
                            && eventoVital.getNombre_evento_vital().compareTo("Muertes") != 0)
                    formFamilia.getCboxEventosVitales().addItem(eventoVital);

                }

                formFamilia.getCheckVacunaMadre().setSelected(false);
                formFamilia.getTxtNumeroCaso().setText("");
                formFamilia.getTxtNumeroNino().setText("");
                formFamilia.getcBoxTipoNino().setSelectedIndex(0);
                formFamilia.getTxtNombreCorto().setText("");
                formFamilia.getCheckVacunaNino().setSelected(false);
            }
        }


        

        if(accion.compareTo(formFamilia.getBtnBuscarEliminacion().getActionCommand()) == 0)
        {
            if(formFamilia.getTxtTextoBusquedaEliminacion().getText().isEmpty())
            {
                JOptionPane.showMessageDialog(formFamilia, "Aún no aingresado un texto para buscarlo", "Datos Incompletos", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                String textoBusqueda = formFamilia.getTxtTextoBusquedaEliminacion().getText();
                List<Familia> listaPersonas = null;
                if(formFamilia.getrBtnEliminarAfiliado().isSelected())
                {
                    Afiliado afiliado = new Afiliado();
                    afiliado.setNombres(textoBusqueda);

                    try {
                        afiliado.setCodigo_persona(Integer.parseInt(textoBusqueda));
                        afiliado.setNumero_caso(Integer.parseInt(textoBusqueda));
                    } catch (NumberFormatException ex) {
                         afiliado.setCodigo_persona(-1);
                        afiliado.setNumero_caso(-1);
                    }
                    listaPersonas = dao.findObjects(afiliado);
//                    listaPersonas = dao.getObjects("Afiliado");
                }
                else if(formFamilia.getrBtnEliminarFamiliar().isSelected())
                {
                    Familiar familiar = new Familiar();
                    familiar.setNombres(textoBusqueda);

                    try {
                        familiar.setCodigo_persona(Integer.parseInt(textoBusqueda));

                    } catch (NumberFormatException exx) {
                         familiar.setCodigo_persona(-1);
                    }
                    listaPersonas = dao.findObjects(familiar);

                }

                cargarFamiliaParaTarjeta(listaPersonas, 'X');
                if(listaPersonas == null || listaPersonas.size() == 0)
                    JOptionPane.showMessageDialog(formFamilia, "No existen ningun registro bajo la descripción dada", "Busqueda de Miembros de Libreta", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ext) {
                System.out.println(ext);
                ext.printStackTrace();
            }
        }
        if(accion.compareTo(formFamilia.getBtnEliminar().getActionCommand()) == 0)
        {
            if(modeloFamiliaEliminacion.getRowCount() > 0)
            {
                int indiceEliminar= formFamilia.getjTableListadoFamiliaEliminar().getSelectedRow();
                if(indiceEliminar >= 0)
                {
                    if(JOptionPane.showConfirmDialog(formFamilia, "¿Se encuentra Seguro de eliminar el Registro?", "Eliminación de Familia", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                        return;
                    try {
                        Familia familia = modeloFamiliaEliminacion.getFamilia(indiceEliminar);
                        if(formFamilia.getrBtnEliminarAfiliado().isSelected())
                        {
                            Afiliado afiliado = (Afiliado) familia;
                            dao.delete(afiliado);


                        }
                         else if(formFamilia.getrBtnEliminarFamiliar().isSelected())
                        {
                             Familiar familiar = (Familiar) familia;
                             dao.delete(familiar);
                        }
                        JOptionPane.showMessageDialog(formFamilia, "Registro eliminado satisfactoriamente", "Eliminacion", JOptionPane.INFORMATION_MESSAGE);
                        modeloFamiliaEliminacion.removeFamilia(indiceEliminar);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(formFamilia, "No se pudo eliminar el Registro", "Eliminacion", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println(ex);
                        ex.printStackTrace();
                    }
                }
                else
                    JOptionPane.showMessageDialog(formFamilia, "No existen ningun registro seleccionado para su Eliminacion", "Eliminacion", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(formFamilia, "No existen ningun registro seleccionado para su Eliminacion", "Eliminacion", JOptionPane.INFORMATION_MESSAGE);
        }

        if(accion.compareTo("alfabetizacionCheck")== 0)
        {
            //Masculino
            if(!formFamilia.getBtnNuevoFamilia().isEnabled())
            {
                formFamilia.getjDChFechaAlfabetizacion().setEnabled(formFamilia.getCheckAlfabetizado().isSelected());
            }
        }

        if(accion.compareTo("eventoVitalN")== 0 && !formFamilia.getBtnNuevoFamilia().isEnabled())
        {
            if(formFamilia.getCboxEventosVitales().getSelectedIndex() >= 0 && formFamilia.getCboxEventosVitales().getSelectedItem().toString().compareTo("Ninguno")== 0 )
            {
                formFamilia.getCboxCausasMuerte().setEnabled(false);
                formFamilia.getCboxCausasMuerte().setSelectedIndex(-1);
            } else {
                formFamilia.getCboxCausasMuerte().setEnabled(true);
            }
        }

        if(accion.compareTo("eventoVitalE")== 0 && !formFamilia.getBtnModificar().isEnabled())
        {
            if(formFamilia.getcBoxEventoVitalE().getSelectedIndex() >= 0 && formFamilia.getcBoxEventoVitalE().getSelectedItem().toString().compareTo("Ninguno")== 0 )
            {
                formFamilia.getcBoxCausaMuerteE().setEnabled(false);
                formFamilia.getcBoxCausaMuerteE().setSelectedIndex(-1);
            } else {
                formFamilia.getcBoxCausaMuerteE().setEnabled(true);
            }
        }

        if(accion.compareTo("sexoN")== 0)
        {
            //Masculino
            if(formFamilia.getcBoxSexo().getSelectedIndex() == 2 && !formFamilia.getBtnNuevoFamilia().isEnabled())
            {
                formFamilia.getcBoxParentesco().setModel(
                    new javax.swing.DefaultComboBoxModel(
                    new String[] { "Seleccionar", "Bisabuelo", "Abuelo", "Padre", "Hijo", "Hermano", "Tio", "Sobrino", "Nieto ", "Hijo adoptivo", "Padrastro", "Hijastro", "Padre Adoptivo", "Hermanastro", "Otro" }));
                formFamilia.getCheckVacunaMadre().setEnabled(false);
                formFamilia.getCheckVacunaMadre().setSelected(false);
                formFamilia.getcBoxParentesco().setEnabled(true);
            }
            //Femenino
            if(formFamilia.getcBoxSexo().getSelectedIndex() == 1 && !formFamilia.getBtnNuevoFamilia().isEnabled())
            {
                formFamilia.getcBoxParentesco().setModel(
                        new javax.swing.DefaultComboBoxModel(
                        new String[] { "Seleccionar", "Bisabuela", "Abuela", "Madre", "Hija", "Hermana", "Tia", "Sobrina", "Nieta", "Hija Adoptiva", "Madrastra", "Hijastra", "Madre Adoptiva", "Hermanastra", "Otro" }));
                formFamilia.getCheckVacunaMadre().setEnabled(true);
                formFamilia.getCheckVacunaMadre().setSelected(false);
                formFamilia.getcBoxParentesco().setEnabled(true);
            }
            //Primer Indice de Seleccionar
            if(formFamilia.getcBoxSexo().getSelectedIndex() == 0)
            {
                formFamilia.getcBoxParentesco().setModel(
                        new javax.swing.DefaultComboBoxModel(
                        new String[] { "Seleccionar", "Bisabuela", "Abuela", "Madre", "Hija", "Hermana", "Tia", "Sobrina", "Nieta", "Hija Adoptiva", "Madrastra", "Hijastra", "Madre Adoptiva", "Hermanastra", "Bisabuelo", "Abuelo", "Padre", "Hijo", "Hermano", "Tio", "Sobrino", "Nieto ", "Hijo adoptivo", "Padrastro", "Hijastro", "Padre Adoptivo", "Hermanastro", "Otro" }));
                formFamilia.getCheckVacunaMadre().setEnabled(true);
                formFamilia.getCheckVacunaMadre().setSelected(false);
                formFamilia.getcBoxParentesco().setEnabled(false);
            }
        }


        if(accion.compareTo("seleccionarOcupacionN") == 0)
        {
            if((formFamilia.getcBoxOcupacion().getSelectedItem() != null
                    && formFamilia.getcBoxOcupacion().getSelectedItem().toString().compareTo("Ninguno") == 0)
                     || formFamilia.getcBoxOcupacion().getSelectedItem() == null
                    )

            {
                formFamilia.getCheckTipoOcupacionEventual().setEnabled(false);
                formFamilia.getCheckTipoOcupacionFija().setEnabled(false);
            }
            else
            {
                formFamilia.getCheckTipoOcupacionEventual().setEnabled(true);
                formFamilia.getCheckTipoOcupacionFija().setEnabled(true);
            }
            System.out.println("seleccionarOcupacionN");
        }

        if(accion.compareTo("seleccionarOcupacionE") == 0)
        {
            if((formFamilia.getcBoxOcupacionE().getSelectedItem() != null
                    && formFamilia.getcBoxOcupacionE().getSelectedItem().toString().compareTo("Ninguno") == 0)
                     || formFamilia.getcBoxOcupacionE().getSelectedItem() == null
                    )

            {
                formFamilia.getCheckTipoOcupacionEventualE().setEnabled(false);
                formFamilia.getCheckTipoOcupacionFijaE().setEnabled(false);
            }
            else
            {
                formFamilia.getCheckTipoOcupacionEventualE().setEnabled(true);
                formFamilia.getCheckTipoOcupacionFijaE().setEnabled(true);
            }
        }

//        if(accion.compareTo(formFamilia.getBtnEliminarTodo().getActionCommand()) == 0)
//        {
//
//        }
        if(accion.compareTo(formFamilia.getBtnModificar().getActionCommand()) == 0)
        {
            if(formFamilia.getjTableListadoFamiliaModificar().getSelectedRow() < 0)
            {
                JOptionPane.showMessageDialog(formFamilia, "Aún no ha seleccionado ningúna persona para su Edición", "Validación de datos", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if(!validarCamposEdicion())
            {
                JOptionPane.showMessageDialog(formFamilia, "Le Faltan llenar campos", "Validación de datos", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            

            String ci = formFamilia.getTxtCiE().getText();
            String nombres = formFamilia.getTxtNombresE().getText();
            String paterno = formFamilia.getTxtPaternoE().getText();
            String materno = formFamilia.getTxtMaternoE().getText();
            String parentesco = formFamilia.getcBoxParentescoE().getSelectedItem().toString();
            String sexo = formFamilia.getCboxSexoE().getSelectedItem().toString().substring(0,1);
            Date fechaNacimiento = formFamilia.getjDCHFechaNacimientoE().getDate();
            boolean conCertificado = formFamilia.getcBoxCertificadoNacimientoE().getSelectedIndex() == 2;
            boolean alfabetizado = formFamilia.getCheckAlfabetizadoE().isSelected();
            Date fechaAlfabetizacion = formFamilia.getjDChFechaAlfabetizacionE().getDate();
            ActividadEducativa actividadEducativa = (ActividadEducativa)formFamilia.getcBoxActividadEducativaE().getSelectedItem();
            Ocupaciones ocupaciones = ((Ocupaciones) formFamilia.getcBoxOcupacionE().getSelectedItem());
            EventosVitales eventoVital = ((EventosVitales) formFamilia.getcBoxEventoVitalE().getSelectedItem());
            CausasMuerte causaMuerte =((CausasMuerte) formFamilia.getcBoxCausaMuerteE().getSelectedItem());
            Tarjeta tarjeta = ((Tarjeta)formFamilia.getCboxListadoTarjetas().getSelectedItem());

            Familia familia = modeloFamiliaEdicion.getFamilia(formFamilia.getjTableListadoFamiliaModificar().getSelectedRow());            
            familia.setApellido_materno(materno);
            familia.setApellido_paterno(paterno);
            familia.setCi(Integer.valueOf(ci));
            familia.setCodigo_actividad_educactiva(actividadEducativa.getCodigo_actividad_educactiva());
            familia.setCodigo_causa_muerte(causaMuerte.getCodigo_causa_muerte());
            familia.setCodigo_evento_vital(eventoVital.getCodigo_evento_vital());
            familia.setCodigo_ocupacion(ocupaciones.getCodigo_ocupacion());
            familia.setCon_certificado(conCertificado);
            familia.setFecha_alfetizacion(fechaAlfabetizacion);
            familia.setFecha_nacimiento(fechaNacimiento);
            familia.setNombres(nombres);
            familia.setNumero_familia( ((Tarjeta)formFamilia.getCboxListadoTarjetas().getSelectedItem()).getNumero_familia());
            familia.setParentesco(parentesco);
            familia.setSexo(sexo);
            familia.setTipo_ocu(formFamilia.getCheckTipoOcupacionEventual().isSelected() ? "EVENTUAL" : "FIJA");
            familia.setOcupacion(ocupaciones);
            familia.setEventoVital(eventoVital);
            familia.setActividadEducativa(actividadEducativa);
            familia.setCausaMuerte(causaMuerte);
            familia.setCodigo_persona(modeloFamiliaEdicion.getFamilia(formFamilia.getjTableListadoFamiliaModificar().getSelectedRow()).getCodigo_persona());

//            System.out.print("Codigo a Modificar " + modeloFamiliaEdicion.getFamilia(formFamilia.getjTableListadoFamiliaModificar().getSelectedRow()).getCodigo_persona());

            Familiar familiar;
            Afiliado afiliado;
            try {
//                int numeroFamilia = dao.obtenerUltimoObjetoTabla(familia);

                if(formFamilia.getRbtnModificarAfiliado().isSelected())
                {
                    afiliado = new Afiliado(familia, Integer.valueOf(formFamilia.getTxtNumeroCasoE().getText()),
                    formFamilia.getcBoxTipoNinoE().getSelectedItem().toString(), formFamilia.getTxtNombreCortoE().getText(),
                            formFamilia.getCheckVacunaNinoE().isSelected(),
                            familia.getCodigo_persona(), Integer.parseInt(formFamilia.getTxtNumeroNinoE().getText()));
//                    System.out.println(afiliado.getNombre_corto() + ", Codigo Persona " + afiliado.getCodigo_persona());
                    try {
                        afiliado.setNumero_nino( Integer.parseInt(formFamilia.getTxtNumeroNinoE().getText()));
                    } catch (NumberFormatException ete) {
                    }
                    dao.edit(afiliado);
                }
                else if(formFamilia.getRbtnModificarFamiliar().isSelected())
                {
                    familiar = new Familiar(familia, formFamilia.getCheckVacunaMadreE().isSelected());
                    dao.edit(familiar);
//                    System.out.println(familiar.getNombreCompleto() + ", Codigo Persona " + familiar.getCodigo_persona());
                }
//                System.out.println(familia);
//                dao.insertar(familia);


                habilitarControlesEdicion(false);
                
                JOptionPane.showMessageDialog(formFamilia, "Registro actualizado correctamente", "Registro de Tarjeta Familiar", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(formFamilia, "No se pudo realizar correctamente  la actualizacion el registro", "Registro de Tarjeta Familiar", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex);
            }
        }
        if(accion.compareTo(formFamilia.getrBtnEliminarAfiliado().getActionCommand()) == 0)
        {

        }

        if(accion.compareTo(formFamilia.getBtnAsignarPadrino().getActionCommand()) == 0)
        {
            if(formFamilia.getjTableListadoFamiliaModificar().getSelectedRow() < 0)
            {
                JOptionPane.showMessageDialog(formFamilia, "Aún no ha seleccionado ningúna persona para su Edición",
                        "Validación de datos", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if(!(modeloFamiliaEdicion.getFamilia(formFamilia.getjTableListadoFamiliaModificar().getSelectedRow()) instanceof Afiliado))
            {
                JOptionPane.showMessageDialog(formFamilia, "La persona seleccionado no es un Afiliado",
                        "Validación de datos", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            try {
                Afiliado afiliado;


                if(formFamilia.getRbtnModificarAfiliado().isSelected())
                {
                    afiliado = new Afiliado(Integer.valueOf(formFamilia.getTxtNumeroCasoE().getText()),
                    formFamilia.getcBoxTipoNinoE().getSelectedItem().toString(), formFamilia.getTxtNombreCortoE().getText(),
                            formFamilia.getCheckVacunaNinoE().isSelected(),
                            modeloFamiliaEdicion.getFamilia(formFamilia.getjTableListadoFamiliaModificar().getSelectedRow()).getCodigo_persona());                    

                    GuiPatrocinadorBuscador formPatrocinadorBuscador = new GuiPatrocinadorBuscador(formFamilia,true);
                    formPatrocinadorBuscador.control.setDao(dao);
                    formPatrocinadorBuscador.control.onFormShown();
                    FormUtilities.centrar2(formPatrocinadorBuscador, formFamilia);
                    Casos casoAfiliado = new Casos(null, afiliado.getNumero_caso(), -1);
                    if(dao.existsId(casoAfiliado)
                            && JOptionPane.showConfirmDialog(formFamilia, "El Afiliado ya tiene " +
                                "Asignado un Patrocinador.¿Desea modificar su Patrocinador?", "Asignación de Padrinos",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    {
                        formPatrocinadorBuscador.setVisible(true);
                        if(formPatrocinadorBuscador.control.getPatrocinadorSeleccionado() != null)
                        {
                            casoAfiliado.setCodigo_padrino(formPatrocinadorBuscador.control.getPatrocinadorSeleccionado().getCodigo_padrino());
                            dao.edit(casoAfiliado);
                            formFamilia.getLblDatosPatrocinador().setText("Patrocinador :" + formPatrocinadorBuscador.control.getPatrocinadorSeleccionado().getNombreCompleto());
                            JOptionPane.showMessageDialog(formFamilia,
                                    "Registro actualizado correctamente", "Asignación de Padrinos",
                                    JOptionPane.INFORMATION_MESSAGE);
                            afiliado.setTipo("Patrocinado");
                            modeloFamiliaEdicion.editarFamilia(afiliado, formFamilia.getjTableListadoFamiliaModificar().getSelectedRow());
                            formFamilia.getcBoxTipoNinoE().setSelectedIndex(1);

                        }
                        else
                        {
                            JOptionPane.showMessageDialog(formFamilia, "No seleccionó ningun Patrocinador",
                                "Asignación de Padrinos", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else
                    {
                        formPatrocinadorBuscador.setVisible(true);
                        if(formPatrocinadorBuscador.control.getPatrocinadorSeleccionado() != null)
                        {
                            casoAfiliado.setCodigo_padrino(formPatrocinadorBuscador.control.getPatrocinadorSeleccionado().getCodigo_padrino());
                            dao.insertar(casoAfiliado);
                            formFamilia.getLblDatosPatrocinador().setText("Patrocinador :" + formPatrocinadorBuscador.control.getPatrocinadorSeleccionado().getNombreCompleto());
                            JOptionPane.showMessageDialog(formFamilia,
                                    "Registro guardado correctamente", "Asignación de Padrinos",
                                    JOptionPane.INFORMATION_MESSAGE);

                            afiliado.setTipo("Patrocinado");
                            modeloFamiliaEdicion.editarFamilia(afiliado, formFamilia.getjTableListadoFamiliaModificar().getSelectedRow());
                            formFamilia.getcBoxTipoNinoE().setSelectedIndex(1);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(formFamilia, "No seleccionó ningun Patrocinador",
                                "Asignación de Padrinos", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    
                }
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(formFamilia, "No se pudo realizar correctamente  la actualizacion el registro", "Registro de Tarjeta Familiar", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex);
            }
        }

        if(accion.compareTo(formFamilia.getCboxListadoTarjetas().getActionCommand()) == 0)
        {
            if( formFamilia.getCboxListadoTarjetas() != null && formFamilia.getCboxListadoTarjetas().getItemCount() > 0
                    && formFamilia.getCboxListadoTarjetas().getSelectedItem() != null)
            {
            Familia fam = new Familia();
            fam.setNumero_familia(((Tarjeta)formFamilia.getCboxListadoTarjetas().getSelectedItem()).getNumero_familia());
            
                try {
                    List<Familia> listadoPersonasMiembroTarjeta = dao.getObjects("Familia", fam);
                    cargarFamiliaParaTarjeta(listadoPersonasMiembroTarjeta, 'I');
                    formFamilia.getTxtNumero().setText(String.valueOf(modeloFamiliaNuevo.getRowCount() + 1));
                    System.out.println("Codigo Persona " + fam.getCodigo_persona() +  ",  Numero Familia " + fam.getNumero_familia()+", cantidad de Miembros " + listadoPersonasMiembroTarjeta.size());
                } catch (SQLException ex) {
                    Logger.getLogger(CGuiFamilia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if(accion.compareTo(formFamilia.getBtnNuevoFamilia().getActionCommand()) == 0)
        {
            formFamilia.getCboxListadoTarjetas().grabFocus();
            formFamilia.getRbtnRegistrarAfiliado().setSelected(true);
            limpiarCamposNuevo();
            habilitarControlesNuevo(true);
            this.formFamilia.getBtnRegistrarFamilia().setEnabled(true);
            this.formFamilia.getBtnNuevoFamilia().setEnabled(false);
            this.formFamilia.getjDChFechaAlfabetizacion().setEnabled(false);
            this.formFamilia.getcBoxParentesco().setEnabled(false);
            this.formFamilia.getCheckVacunaMadre().setEnabled(false);
//            this.formFamilia.getCheckVacunaNino().setEnabled(false);
            try {
                int Numerofamilia = dao.obtenerUltimoObjetoTabla(new Familia(),"");
                Numerofamilia = Numerofamilia == -1 ? 1 : Numerofamilia +1;
//                this.formFamilia.getTxtNumeroCaso().setText(String.valueOf(Numerofamilia));

                formFamilia.getTxtNumero().setText(String.valueOf(modeloFamiliaNuevo.getRowCount()+1));

                this.formFamilia.getTxtCodigo().setText(String.valueOf(Numerofamilia));
            } catch (SQLException ex) {
                Logger.getLogger(CGuiFamilia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(accion.compareTo(formFamilia.getBtnRegistrarFamilia().getActionCommand()) == 0)
        {
            if(!validarCamposNuevo())
            {
                JOptionPane.showMessageDialog(formFamilia, "Le Faltan llenar campos", "Validación de datos", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String ci = formFamilia.getTxtCi().getText();
            String nombres = formFamilia.getTxtNombres().getText();
            String paterno = formFamilia.getTxtPaterno().getText();
            String materno = formFamilia.getTxtMaterno().getText();
            String parentesco = formFamilia.getcBoxParentesco().getSelectedItem().toString();
            String sexo = formFamilia.getcBoxSexo().getSelectedItem().toString().substring(0,1);
            Date fechaNacimiento = formFamilia.getjDCHFechaNacimiento().getDate();
            boolean conCertificado = formFamilia.getcBoxCertificadoNacimiiento().getSelectedIndex() == 2;
            boolean alfabetizado = formFamilia.getCheckAlfabetizado().isSelected();
            Date fechaAlfabetizacion = formFamilia.getjDChFechaAlfabetizacion().getDate();
            ActividadEducativa actividadEducativa = (ActividadEducativa)formFamilia.getCboxActividadEducativa().getSelectedItem();
            Ocupaciones ocupaciones = formFamilia.getcBoxOcupacion().getSelectedItem() != null ? 
                ((Ocupaciones) formFamilia.getcBoxOcupacion().getSelectedItem()) : null;
            EventosVitales eventoVital = formFamilia.getCboxEventosVitales().getSelectedItem() != null ?
                    ((EventosVitales) formFamilia.getCboxEventosVitales().getSelectedItem()) : null;
            CausasMuerte causaMuerte = formFamilia.getCboxCausasMuerte().getSelectedItem() != null ?
                    ((CausasMuerte) formFamilia.getCboxCausasMuerte().getSelectedItem()) : null;
            Tarjeta tarjeta = ((Tarjeta)formFamilia.getCboxListadoTarjetas().getSelectedItem());
//            Familia familia = new Familia(1, NumeroProyecto, formFamilia.getTxtNombres().getText(),
//                    formFamilia.getTxtPaterno().getText(),
//                    formFamilia.getTxtMaterno().getText(),
//                    formFamilia.getcBoxParentesco().getSelectedItem().toString(),
//                    formFamilia.getcBoxSexo().getSelectedItem().toString().substring(0,1),
//                    formFamilia.getjDCHFechaNacimiento().getDate(),
//                    formFamilia.getcBoxCertificadoNacimiiento().getSelectedIndex() == 1,
//                    formFamilia.getCheckAlfabetizado().isSelected(),
//                    formFamilia.getjDChFechaAlfabetizacion().getDate(),
//                    formFamilia.getCboxActividadEducativa().getSelectedItem().toString(),
//                    ((Ocupaciones) formFamilia.getcBoxOcupacion().getSelectedItem()).getCodigo_ocupacion() ,
//                    "TipoOcupacion",
//                    ((EventosVitales) formFamilia.getCboxEventosVitales().getSelectedItem()).getCodigo_evento_vital(),
//                    ((CausasMuerte) formFamilia.getCboxCausasMuerte().getSelectedItem()).getCodigo_causa_muerte(),
//                    ((Tarjeta)formFamilia.getCboxListadoTarjetas().getSelectedItem()).getNumero_familia());
            
//            Familia familia = new Familia(2,Integer.parseInt(ci), nombres, paterno, materno, parentesco,
//                    sexo, fechaNacimiento, conCertificado, alfabetizado, fechaAlfabetizacion,
//                    actividadEducativa, ocupaciones.getCodigo_ocupacion(),"", eventoVital.getCodigo_evento_vital(),
//                    causaMuerte.getCodigo_causa_muerte(), ((Tarjeta)formFamilia.getCboxListadoTarjetas().getSelectedItem()).getNumero_familia());
//
            Familia familia = new Familia();
            familia.setCodigo_persona(1);
            familia.setApellido_materno(materno);
            familia.setApellido_paterno(paterno);
            familia.setCi(Integer.valueOf(ci));
//            familia.setCodigo_actividad_educactiva(actividadEducativa.getCodigo_actividad_educactiva());
//            familia.setCodigo_causa_muerte(causaMuerte.getCodigo_causa_muerte());
//            familia.setCodigo_evento_vital(eventoVital.getCodigo_evento_vital());
//            familia.setCodigo_ocupacion(ocupaciones.getCodigo_ocupacion());
//            familia.setActividadEducativa(actividadEducativa);
//            familia.setCausaMuerte(causaMuerte);
//            familia.setEventoVital(eventoVital);
//            familia.setOcupacion(ocupaciones);
            familia.setCon_certificado(conCertificado);
            familia.setFecha_alfetizacion(fechaAlfabetizacion);
            familia.setFecha_nacimiento(fechaNacimiento);
            familia.setNombres(nombres);
            familia.setNumero_familia( ((Tarjeta)formFamilia.getCboxListadoTarjetas().getSelectedItem()).getNumero_familia());
            familia.setParentesco(parentesco);
            familia.setSexo(sexo);
            familia.setTipo_ocu(formFamilia.getCheckTipoOcupacionEventual().isSelected() ? "EVENTUAL" : "FIJA");
            familia.setOcupacion(ocupaciones);
            familia.setEventoVital(eventoVital);
            familia.setActividadEducativa(actividadEducativa);
            familia.setCausaMuerte(causaMuerte);

            Familiar familiar;
            Afiliado afiliado;
            try {
//                int numeroFamilia = dao.obtenerUltimoObjetoTabla(familia);

                if(formFamilia.getRbtnRegistrarAfiliado().isSelected())
                {
                    afiliado = new Afiliado(familia, Integer.valueOf(formFamilia.getTxtNumeroCaso().getText()),
                    formFamilia.getcBoxTipoNino().getSelectedItem().toString(), formFamilia.getTxtNombreCorto().getText(), formFamilia.getCheckVacunaNino().isSelected(),
                            1, Integer.parseInt(formFamilia.getTxtNumeroNino().getText()));
                    try {
                        afiliado.setNumero_nino(Integer.valueOf(formFamilia.getTxtNumeroNino().getText()));
                    } catch (NumberFormatException exe) {
                    }
                    dao.insertar(afiliado);
                }
                else if(formFamilia.getRbtnRegistrarFamiliar().isSelected())
                {
                    familiar = new Familiar(familia, formFamilia.getCheckVacunaMadre().isSelected());
                    dao.insertar(familiar);
                }
                System.out.println(familia);
//                dao.insertar(familia);


                habilitarControlesNuevo(false);
                formFamilia.getBtnNuevoFamilia().setEnabled(true);
                formFamilia.getBtnRegistrarFamilia().setEnabled(false);
                modeloFamiliaNuevo.addFamilia(familia);
                JOptionPane.showMessageDialog(formFamilia, "Registro realizado correctamente", "Registro de Tarjeta Familiar", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(formFamilia, "No se pudo realizar correctamente el registro", "Registro de Tarjeta Familiar", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex);
            }

        }
    }

    public void cargarFamiliaParaTarjeta(List<Familia> listaFamilia, char tipoOperacion)
    {
        if(listaFamilia != null)
        {
            switch(tipoOperacion)
            {
                case 'I'://insercion
                    modeloFamiliaNuevo.clear();
                    for (Familia familia : listaFamilia) {
                        modeloFamiliaNuevo.addFamilia(familia);
                    }
                    break;
                case 'E'://edicion
                    modeloFamiliaEdicion.clear();
                    for (Familia familia : listaFamilia) {
                        modeloFamiliaEdicion.addFamilia(familia);
                    }
                    break;
                case 'X'://eliminacion
                    modeloFamiliaEliminacion.clear();
                    for (Familia familia : listaFamilia) {
                        modeloFamiliaEliminacion.addFamilia(familia);
                    }
                    break;
            }
        }
        else
        {
//            JOptionPane.showMessageDialog(formFamilia, "No se Encontró ningún registro con la Información provista", "Registro de Familia", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void limpiarCamposNuevo()
    {
        this.formFamilia.getTxtCi().setText("");
        this.formFamilia.getTxtCodigo().setText("");
        this.formFamilia.getTxtMaterno().setText("");
        this.formFamilia.getTxtNombreCorto().setText("");
        this.formFamilia.getTxtNombres().setText("");
        this.formFamilia.getTxtNumeroCaso().setText("");
        this.formFamilia.getTxtNumeroNino().setText("");
        this.formFamilia.getTxtPaterno().setText("");
        this.formFamilia.getCboxActividadEducativa().setSelectedIndex(-1);
        this.formFamilia.getCboxCausasMuerte().setSelectedIndex(-1);
        this.formFamilia.getCboxEventosVitales().setSelectedIndex(-1);
        this.formFamilia.getcBoxOcupacion().setSelectedIndex(-1);
        this.formFamilia.getCboxListadoTarjetas().setSelectedIndex(-1);
        this.formFamilia.getcBoxTipoNino().setSelectedIndex(0);
        this.formFamilia.getcBoxParentesco().setSelectedIndex(0);
        this.formFamilia.getcBoxSexo().setSelectedIndex(0);
        this.formFamilia.getjDCHFechaNacimiento().setDate(null);
        this.formFamilia.getcBoxCertificadoNacimiiento().setSelectedIndex(0);
        this.formFamilia.getCheckAlfabetizado().setSelected(false);
        this.formFamilia.getjDChFechaAlfabetizacion().setDate(null);
    }

    public void limpiarCamposEdicion()
    {
        this.formFamilia.getTxtCiE().setText("");
        this.formFamilia.getTxtCodigoE().setText("");
        this.formFamilia.getTxtMaternoE().setText("");
        this.formFamilia.getTxtNombreCortoE().setText("");
        this.formFamilia.getTxtNombresE().setText("");
        this.formFamilia.getTxtNumeroCasoE().setText("");
        this.formFamilia.getTxtNumeroNinoE().setText("");
        this.formFamilia.getTxtPaternoE().setText("");
        this.formFamilia.getcBoxActividadEducativaE().setSelectedIndex(-1);
        this.formFamilia.getcBoxCausaMuerteE().setSelectedIndex(-1);
        this.formFamilia.getcBoxEventoVitalE().setSelectedIndex(-1);
        this.formFamilia.getcBoxOcupacionE().setSelectedIndex(-1);
        this.formFamilia.getcBoxTipoNinoE().setSelectedIndex(-1);
        this.formFamilia.getcBoxParentescoE().setSelectedIndex(-1);
        this.formFamilia.getCboxSexoE().setSelectedIndex(-1);
        this.formFamilia.getCheckAlfabetizadoE().setSelected(false);
    }

    public void habilitarControlesNuevo(boolean estadoHabilitacion)
    {
        this.formFamilia.getRbtnRegistrarAfiliado().setEnabled(estadoHabilitacion);
        this.formFamilia.getRbtnRegistrarFamiliar().setEnabled(estadoHabilitacion);
        this.formFamilia.getTxtCi().setEditable(estadoHabilitacion);
        this.formFamilia.getTxtCodigo().setEditable(estadoHabilitacion);
        this.formFamilia.getTxtMaterno().setEditable(estadoHabilitacion);
        this.formFamilia.getTxtNombreCorto().setEditable(estadoHabilitacion);
        this.formFamilia.getTxtNombres().setEditable(estadoHabilitacion);
        this.formFamilia.getTxtNumeroCaso().setEditable(estadoHabilitacion);
        this.formFamilia.getTxtNumeroNino().setEditable(estadoHabilitacion);
        this.formFamilia.getTxtPaterno().setEditable(estadoHabilitacion);
        this.formFamilia.getCboxActividadEducativa().setEnabled(estadoHabilitacion);
        this.formFamilia.getCboxCausasMuerte().setEnabled(estadoHabilitacion);
        this.formFamilia.getCboxEventosVitales().setEnabled(estadoHabilitacion);
        this.formFamilia.getcBoxOcupacion().setEnabled(estadoHabilitacion);
        this.formFamilia.getCboxListadoTarjetas().setEnabled(estadoHabilitacion);
        this.formFamilia.getcBoxTipoNino().setEnabled(estadoHabilitacion);
        this.formFamilia.getcBoxParentesco().setEnabled(estadoHabilitacion);
        this.formFamilia.getcBoxSexo().setEnabled(estadoHabilitacion);
        this.formFamilia.getjDCHFechaNacimiento().setEnabled(estadoHabilitacion);
        this.formFamilia.getcBoxCertificadoNacimiiento().setEnabled(estadoHabilitacion);
        this.formFamilia.getCheckAlfabetizado().setEnabled(estadoHabilitacion);
        this.formFamilia.getjDChFechaAlfabetizacion().setEnabled(estadoHabilitacion);
    }

    public void habilitarControlesEdicion(boolean estadoHabilitacion)
    {
        this.formFamilia.getRbtnModificarAfiliado().setEnabled(estadoHabilitacion);
        this.formFamilia.getRbtnModificarFamiliar().setEnabled(estadoHabilitacion);
        this.formFamilia.getTxtCiE().setEditable(estadoHabilitacion);
        this.formFamilia.getTxtCodigoE().setEditable(estadoHabilitacion);
        this.formFamilia.getTxtMaternoE().setEditable(estadoHabilitacion);
        this.formFamilia.getTxtNombreCortoE().setEditable(estadoHabilitacion);
        this.formFamilia.getTxtNombresE().setEditable(estadoHabilitacion);
        this.formFamilia.getTxtNumeroCasoE().setEditable(estadoHabilitacion);
        this.formFamilia.getTxtNumeroNinoE().setEditable(estadoHabilitacion);
        this.formFamilia.getTxtPaternoE().setEditable(estadoHabilitacion);
        this.formFamilia.getcBoxActividadEducativaE().setEnabled(estadoHabilitacion);
        this.formFamilia.getcBoxCausaMuerteE().setEnabled(estadoHabilitacion);
        this.formFamilia.getcBoxEventoVitalE().setEnabled(estadoHabilitacion);
        this.formFamilia.getcBoxOcupacionE().setEnabled(estadoHabilitacion);
//        this.formFamilia.getCboxListadoTarjetas().setEnabled(estadoHabilitacion);
        this.formFamilia.getcBoxTipoNinoE().setEnabled(formFamilia.getRbtnModificarAfiliado().isSelected());
        this.formFamilia.getcBoxParentescoE().setEnabled(estadoHabilitacion);
        this.formFamilia.getCboxSexoE().setEnabled(estadoHabilitacion);
        this.formFamilia.getjDCHFechaNacimientoE().setEnabled(estadoHabilitacion);
        this.formFamilia.getcBoxCertificadoNacimientoE().setEnabled(estadoHabilitacion);
        this.formFamilia.getCheckAlfabetizadoE().setEnabled(estadoHabilitacion);
        this.formFamilia.getjDChFechaAlfabetizacionE().setEnabled(estadoHabilitacion);
    }

    public boolean validarCamposNuevo()
    {
        //MASCULINO Y EMBARAZO
        if(formFamilia.getcBoxSexo().getSelectedIndex() == 2
                && formFamilia.getCboxEventosVitales().getSelectedItem() != null
                && formFamilia.getCboxEventosVitales().getSelectedItem().toString().compareTo("Embarazo") ==0 )
        {
            JOptionPane.showMessageDialog(formFamilia, "El evento Vital no corresponde el sexo seleccionado, una Persona con sexo Masculino no puede Estar 'Embarazada'", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getCboxEventosVitales().grabFocus();
            return false;
        }

        if(this.formFamilia.getCboxListadoTarjetas().getItemCount() == 0)
        {
            JOptionPane.showMessageDialog(formFamilia, "No tiene registrada aún ninguna tarjeta", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if(this.formFamilia.getCboxListadoTarjetas().getSelectedIndex() == -1 )
        {
            JOptionPane.showMessageDialog(formFamilia, "aún no ha registrado ningún Nro de Tarjeta", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            this.formFamilia.getCboxListadoTarjetas().grabFocus();
            return false;
        }

        if(this.formFamilia.getTxtNombres().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formFamilia, "El Campo nombre se encuentra vacio", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getTxtNombres().selectAll();
            formFamilia.getTxtNombres().grabFocus();
            return false;
        }

        if(this.formFamilia.getTxtPaterno().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formFamilia, "El Apellido Paterno se encuentra vacio", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);            
            formFamilia.getTxtPaterno().selectAll();
            formFamilia.getTxtPaterno().grabFocus();
            return false;
        }        

        if(this.formFamilia.getTxtMaterno().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formFamilia, "El Apellido Materno se encuentra vacio", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            if(JOptionPane.showConfirmDialog(formFamilia, "Desea dejar en blanco este campo", "Validación de datos", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
            {
                formFamilia.getTxtMaterno().selectAll();
                formFamilia.getTxtMaterno().grabFocus();
                return false;
            }
        }

        if(this.formFamilia.getTxtNombreCorto().getText().isEmpty() && formFamilia.getRbtnRegistrarAfiliado().isSelected())
        {
            JOptionPane.showMessageDialog(formFamilia, "El campo Nombre Corto se encuentra vacio", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            if(JOptionPane.showConfirmDialog(formFamilia, "Desea dejar en blanco este campo", "Validación de datos", JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION)
            {
                formFamilia.getTxtPaterno().selectAll();
                formFamilia.getTxtPaterno().grabFocus();
                return false;
            }
        }
        if(this.formFamilia.getcBoxTipoNino().getSelectedIndex() == 0  && this.formFamilia.getcBoxTipoNino().isEnabled())
        {
            JOptionPane.showMessageDialog(formFamilia, "Aún no ha Seleccionado el Tipo de Niño", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getcBoxTipoNino().grabFocus();
            return false;
        }
        if(this.formFamilia.getcBoxSexo().getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(formFamilia, "Aún no ha Seleccionado el Sexo", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getcBoxSexo().grabFocus();
            return false;
        }


        if(this.formFamilia.getcBoxParentesco().getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(formFamilia, "Aún no ha Seleccionado el Parentesco", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getcBoxParentesco().grabFocus();
            return false;
        }

        
        if(formFamilia.getjDCHFechaNacimiento().getDate() == null)
        {
            JOptionPane.showMessageDialog(formFamilia, "Aún no ha ingresado la Fecha de Nacimiento", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getjDCHFechaNacimiento().grabFocus();
            return false;
        }

        if(formFamilia.getjDCHFechaNacimiento().getCalendar().after(Calendar.getInstance()))
        {
            JOptionPane.showMessageDialog(formFamilia, "La Fecha Introducida no es valida, debido a que la fecha es Superior a la Actual", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getjDCHFechaNacimiento().grabFocus();
            return false;
        }

        if(formFamilia.getcBoxCertificadoNacimiiento().getSelectedIndex() == -1)
        {
            JOptionPane.showMessageDialog(formFamilia, "Aún no ha mensionado si el miembro actual cuenta con Certificado de Nacimiento", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getcBoxCertificadoNacimiiento().grabFocus();
            return false;
        }

        if(formFamilia.getRbtnRegistrarAfiliado().isSelected())
        {
            int EdadActual = -1;
            try {
                EdadActual = dao.ObtenerEdadActual(formFamilia.getjDCHFechaNacimiento().getDate(), Calendar.getInstance().getTime());
            } catch (SQLException ex) {
                
            }
            System.out.println("Edad Actual " + EdadActual);
            if(EdadActual == -1 || EdadActual > 20)
            {
                JOptionPane.showMessageDialog(formFamilia, "La persona que desea registrar no puede ser ingresada como Afiliada, debido que ha sobrepasado la Edad Maxima de 21 Años", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
                formFamilia.getjDCHFechaNacimiento().grabFocus();
                return false;
            }
            
        }

        if(this.formFamilia.getCboxActividadEducativa().getSelectedIndex() == -1)
        {
            JOptionPane.showMessageDialog(formFamilia, "Aún no ha Seleccionado el La Actividad Educativa", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getCboxActividadEducativa().grabFocus();
            return false;
        }

        if(this.formFamilia.getcBoxOcupacion().getSelectedIndex() == -1)
        {
            if(JOptionPane.showConfirmDialog(formFamilia, "Aún no ha Seleccionado la Ocupación. ¿Se encuentra seguro de dejar este campo en blanco?", "Validación de Datos", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION )
            {
                formFamilia.getcBoxOcupacion().grabFocus();
                return false;
            }
        }

        if(this.formFamilia.getCboxEventosVitales().getSelectedIndex() == -1 && this.formFamilia.getCboxEventosVitales().isEnabled())
        {
            JOptionPane.showMessageDialog(formFamilia, "Aún no ha Seleccionado el Tipo de Evento Vital", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getCboxEventosVitales().grabFocus();
            return false;
        }

        if(this.formFamilia.getCboxCausasMuerte().getSelectedIndex() == -1 && this.formFamilia.getCboxCausasMuerte().isEnabled())
        {
            JOptionPane.showMessageDialog(formFamilia, "Aún no ha Seleccionado la Causa Muerte", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getCboxCausasMuerte().grabFocus();
            return false;
        }
        return true;
    }


     public boolean validarCamposEdicion()
    {
        if(this.formFamilia.getTxtNombresE().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formFamilia, "El Campo nombre se encuentra vacio", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getTxtNombresE().selectAll();
            formFamilia.getTxtNombresE().grabFocus();
            return false;
        }

        if(this.formFamilia.getTxtPaternoE().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formFamilia, "El Apellido Paterno se encuentra vacio", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getTxtPaternoE().selectAll();
            formFamilia.getTxtPaternoE().grabFocus();
            return false;
        }

        if(this.formFamilia.getTxtMaternoE().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(formFamilia, "El Apellido Materno se encuentra vacio", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            if(JOptionPane.showConfirmDialog(formFamilia, "Desea dejar en blanco este campo", "Validación de datos", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
            {
                formFamilia.getTxtMaternoE().selectAll();
                formFamilia.getTxtMaternoE().grabFocus();
                return false;
            }
        }

        if(this.formFamilia.getTxtNombreCortoE().getText().isEmpty() && formFamilia.getRbtnRegistrarAfiliado().isSelected())
        {
            JOptionPane.showMessageDialog(formFamilia, "El campo Nombre Corto se encuentra vacio", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            if(JOptionPane.showConfirmDialog(formFamilia, "Desea dejar en blanco este campo", "Validación de datos", JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION)
            {
                formFamilia.getTxtNombreCortoE().selectAll();
                formFamilia.getTxtNombreCortoE().grabFocus();
                return false;
            }
        }
        if(this.formFamilia.getcBoxTipoNinoE().getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(formFamilia, "Aún no ha Seleccionado el Tipo de Niño", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getcBoxTipoNinoE().grabFocus();
            return false;
        }

        if(formFamilia.getjDCHFechaNacimientoE().getDate() == null)
        {
            JOptionPane.showMessageDialog(formFamilia, "Aún no ha ingresado la Fecha de Nacimiento", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getjDCHFechaNacimientoE().grabFocus();
            return false;
        }

        //MASCULINO Y EMBARAZO
        if(formFamilia.getCboxSexoE().getSelectedIndex() == 2
                && formFamilia.getcBoxEventoVitalE().getSelectedItem() != null
                && formFamilia.getcBoxEventoVitalE().getSelectedItem().toString().compareTo("Embarazo") ==0 )
        {
            JOptionPane.showMessageDialog(formFamilia, "El evento Vital no corresponde el sexo seleccionado, una Persona con sexo Masculino no puede Estar 'Embarazada'", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getcBoxEventoVitalE().grabFocus();
            return false;
        }

        if(this.formFamilia.getcBoxParentescoE().getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(formFamilia, "Aún no ha Seleccionado el Parentesco", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getcBoxParentescoE().grabFocus();
            return false;
        }

        if(this.formFamilia.getCboxSexoE().getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(formFamilia, "Aún no ha Seleccionado el Sexo", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getCboxSexoE().grabFocus();
            return false;
        }

        if(this.formFamilia.getcBoxActividadEducativaE().getSelectedIndex() == -1)
        {
            JOptionPane.showMessageDialog(formFamilia, "Aún no ha Seleccionado el La Actividad Educativa", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getcBoxActividadEducativaE().grabFocus();
            return false;
        }

        if(this.formFamilia.getcBoxOcupacionE().getSelectedIndex() == -1 && this.formFamilia.getcBoxOcupacionE().isEnabled())
        {
            JOptionPane.showMessageDialog(formFamilia, "Aún no ha Seleccionado la Ocupación", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getcBoxOcupacionE().grabFocus();
            return false;
        }
        if(formFamilia.getcBoxCertificadoNacimientoE().getSelectedIndex() == -1)
        {
            JOptionPane.showMessageDialog(formFamilia, "Aún no ha mensionado si el miembro actual cuenta con Certificado de Nacimiento", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getcBoxCertificadoNacimientoE().grabFocus();
            return false;
        }

        if(formFamilia.getRbtnModificarAfiliado().isSelected())
        {
            int EdadActual = -1;
            try {
                EdadActual = dao.ObtenerEdadActual(formFamilia.getjDCHFechaNacimientoE().getDate(), Calendar.getInstance().getTime());
            } catch (SQLException ex) {

            }
            System.out.println("Edad Actual " + EdadActual);
            if(EdadActual == -1 || EdadActual > 20)
            {
                JOptionPane.showMessageDialog(formFamilia, "La persona que desea registrar no puede ser ingresada como Afiliada, debido que ha sobrepasado la Edad Maxima de 21 Años", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
                formFamilia.getjDCHFechaNacimientoE().grabFocus();
                return false;
            }

        }

        if(this.formFamilia.getcBoxEventoVitalE().getSelectedIndex() == -1 && this.formFamilia.getcBoxEventoVitalE().isEnabled())
        {
            JOptionPane.showMessageDialog(formFamilia, "Aún no ha Seleccionado el Tipo de Evento Vital", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getcBoxEventoVitalE().grabFocus();
            return false;
        }

        if(this.formFamilia.getcBoxCausaMuerteE().getSelectedIndex() == -1 && this.formFamilia.getcBoxCausaMuerteE().isEnabled())
        {
            JOptionPane.showMessageDialog(formFamilia, "Aún no ha Seleccionado la Causa Muerte", "Validación de Datos", JOptionPane.INFORMATION_MESSAGE);
            formFamilia.getcBoxCausaMuerteE().grabFocus();
            return false;
        }
        return true;
    }



    public void cargarDatosFamilia(Familia familia)
    {
        if(familia != null)
        {
            formFamilia.getTxtCiE().setText(String.valueOf(familia.getCi()));
            formFamilia.getTxtMaternoE().setText(familia.getApellido_materno());
            formFamilia.getTxtNombresE().setText(familia.getNombres());
            formFamilia.getTxtPaternoE().setText(familia.getApellido_paterno());
            formFamilia.getcBoxParentescoE().setSelectedItem(familia.getParentesco());
            formFamilia.getCboxSexoE().setSelectedIndex(familia.getSexo().compareTo("F")== 0 ? 1 : 2);
            formFamilia.getjDCHFechaNacimientoE().setDate(familia.getFecha_nacimiento());
            formFamilia.getjDChFechaAlfabetizacionE().setDate(familia.getFecha_alfetizacion());
            formFamilia.getcBoxCertificadoNacimientoE().setSelectedIndex(familia.isCon_certificado() ? 2 : 1);
            formFamilia.getCheckAlfabetizadoE().setSelected(familia.isAlfabetizado());
            int indice = -1;
            if(familia.getActividadEducativa() != null && listadoActividadEducativa != null)
            {
                indice = Collections.binarySearch(listadoActividadEducativa, familia.getActividadEducativa());
                formFamilia.getcBoxActividadEducativaE().setSelectedIndex(indice >= 0 ? indice : -1);
            }
            if(familia.getOcupacion() !=null)
            {
                indice = Collections.binarySearch(listadoOcupaciones, familia.getOcupacion());
                formFamilia.getcBoxOcupacionE().setSelectedIndex(indice >= 0 ? indice : -1);
            }
            if(familia.getEventoVital() != null)
            {
                indice = Collections.binarySearch(listadoEventosVitales, familia.getEventoVital());
                formFamilia.getcBoxEventoVitalE().setSelectedIndex(indice >= 0 ? indice : -1);
            }
            
            if(familia.getCausaMuerte() != null)
            {
                indice = Collections.binarySearch(listadoCausasMuerte, familia.getCausaMuerte());
                formFamilia.getcBoxCausaMuerteE().setSelectedIndex(indice >= 0 ? indice : -1);
            }
            else
            {
                formFamilia.getcBoxCausaMuerteE().setSelectedIndex(-1);
            }

            if( familia instanceof Familiar)
            {
                Familiar familiar = (Familiar)familia;
                formFamilia.getCheckVacunaMadreE().setSelected(familiar.isVacuna_madre());
            }
            else if(familia instanceof Afiliado)
            {
                Afiliado afiliado = (Afiliado) familia;
                formFamilia.getCheckVacunaNinoE().setSelected(afiliado.isVacuna_nino());
                formFamilia.getTxtNumeroCasoE().setText(String.valueOf(afiliado.getNumero_caso()));
                formFamilia.getTxtNumeroNinoE().setText(String.valueOf(afiliado.getNumero_nino()));
                formFamilia.getTxtNumero().setText(String.valueOf(afiliado.getNumero_familia()));
                formFamilia.getcBoxTipoNinoE().setSelectedItem(afiliado.getTipo());
                formFamilia.getTxtNombreCortoE().setText(afiliado.getNombre_corto());

                Casos caso = new Casos(null, afiliado.getNumero_caso(), -1);
                try {
                    caso = (Casos) dao.obtenerObjeto(caso);
                    if(caso != null)
                    {
                        Padrinos patrocinador = new Padrinos();
                        patrocinador.setCodigo_padrino(caso.getCodigo_padrino());
                        patrocinador = (Padrinos) dao.obtenerObjeto(patrocinador);
                        formFamilia.getLblDatosPatrocinador().setText("Patrocinador :" + patrocinador.getNombreCompleto());
                    }
                    else
                    {
                        formFamilia.getLblDatosPatrocinador().setText("Afiliado en espera de asignación de PATROCINADOR");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CGuiFamilia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void onFormShown()
    {
        try {
            limpiarCamposNuevo();
            habilitarControlesNuevo(false);
            this.formFamilia.getCboxListadoTarjetas().setSelectedIndex(-1);
            ListadoTarjetas = (ArrayList<Tarjeta>) dao.getObjects("Tarjeta", new Tarjeta());
            listadoOcupaciones = (ArrayList<Ocupaciones>) dao.getObjects("Ocupaciones");
            listadoEventosVitales = (ArrayList<EventosVitales>)dao.getObjects("EventosVitales");
            listadoActividadEducativa = (ArrayList<ActividadEducativa>)dao.getObjects("ActividadEducativa");
            listadoCausasMuerte = (ArrayList<CausasMuerte>)dao.getObjects("CausasMuerte");

            formFamilia.getCboxListadoTarjetas().removeAllItems();
            for(Tarjeta tarjetas : ListadoTarjetas)
            {
                formFamilia.getCboxListadoTarjetas().addItem(tarjetas);
                System.out.println(tarjetas);
            }

            formFamilia.getcBoxOcupacion().removeAllItems();
            formFamilia.getcBoxOcupacionE().removeAllItems();
            for(Ocupaciones ocupacion : listadoOcupaciones)
            {
                formFamilia.getcBoxOcupacion().addItem(ocupacion);
                formFamilia.getcBoxOcupacionE().addItem(ocupacion);
            }

            formFamilia.getCboxEventosVitales().removeAllItems();
            formFamilia.getcBoxEventoVitalE().removeAllItems();
            for(EventosVitales eventoVital : listadoEventosVitales)
            {
                formFamilia.getCboxEventosVitales().addItem(eventoVital);
                formFamilia.getcBoxEventoVitalE().addItem(eventoVital);
            }

            formFamilia.getCboxActividadEducativa().removeAllItems();
            formFamilia.getcBoxActividadEducativaE().removeAllItems();
            for(ActividadEducativa actividadesEducativas : listadoActividadEducativa)
            {
                formFamilia.getCboxActividadEducativa().addItem(actividadesEducativas);
                formFamilia.getcBoxActividadEducativaE().addItem(actividadesEducativas);
            }

            formFamilia.getCboxCausasMuerte().removeAllItems();
            formFamilia.getcBoxCausaMuerteE().removeAllItems();
            for(CausasMuerte causaMuerte : listadoCausasMuerte)
            {
                formFamilia.getCboxCausasMuerte().addItem(causaMuerte);
                formFamilia.getcBoxCausaMuerteE().addItem(causaMuerte);

            }

        } catch (SQLException ex) {
            Logger.getLogger(CGuiFamilia.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.formFamilia.getBtnRegistrarFamilia().setEnabled(false);
        this.formFamilia.getBtnNuevoFamilia().setEnabled(true);

        formFamilia.getjTableListadoFamiliaNuevo().setModel(modeloFamiliaNuevo);
        formFamilia.getjTableListadoFamiliaModificar().setModel(modeloFamiliaEdicion);
        formFamilia.getjTableListadoFamiliaEliminar().setModel(modeloFamiliaEliminacion);

        formFamilia.getjTableListadoFamiliaModificar().getSelectionModel().addListSelectionListener(this);
        formFamilia.getRbtnRegistrarAfiliado().setSelected(false);
        formFamilia.getRbtnRegistrarFamiliar().setSelected(false);

        ButtonGroup bgTipoOcupacionN = new ButtonGroup();
        ButtonGroup bgTipoOcupacionE = new ButtonGroup();

        bgTipoOcupacionN.add(formFamilia.getCheckTipoOcupacionEventual());
        bgTipoOcupacionN.add(formFamilia.getCheckTipoOcupacionFija());
        bgTipoOcupacionE.add(formFamilia.getCheckTipoOcupacionEventualE());
        bgTipoOcupacionE.add(formFamilia.getCheckTipoOcupacionFijaE());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        JTextField componente = (JTextField) e.getSource();
        char c = e.getKeyChar();
        if (componente.equals(formFamilia.getTxtCi()) ||
              componente.equals(formFamilia.getTxtNumeroCaso()) ||
              componente.equals(formFamilia.getTxtNumeroCasoE()) ||
              componente.equals(formFamilia.getTxtNumeroNino()) ||
              componente.equals(formFamilia.getTxtNumeroNinoE()) ||
              componente.equals(formFamilia.getTxtCodigo()) ||
              componente.equals(formFamilia.getTxtCodigoE()) ||
              componente.equals(formFamilia.getTxtCiE())) {
                
                if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                                || (c == KeyEvent.VK_DELETE) ))) {
                        formFamilia.getToolkit().beep();
                        e.consume();
                }
        }else if(componente.equals(formFamilia.getTxtNombreCorto())
                || componente.equals(formFamilia.getTxtNombreCortoE())
                || componente.equals(formFamilia.getTxtNombres())
                || componente.equals(formFamilia.getTxtNombresE())
                || componente.equals(formFamilia.getTxtPaterno())
                || componente.equals(formFamilia.getTxtPaternoE())
                || componente.equals(formFamilia.getTxtMaterno())
                || componente.equals(formFamilia.getTxtMaternoE()))
        {
            if (!((Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE)
                                || (c == KeyEvent.VK_DELETE)
                                || (c == KeyEvent.VK_SPACE)))) {
                        formFamilia.getToolkit().beep();
                        e.consume();
                }
        }
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
			return; // if you don't want to handle intermediate selections
        ListSelectionModel rowSM = (ListSelectionModel) e.getSource();

        if(this.formFamilia.getjTableListadoFamiliaModificar().isFocusOwner())
		{
			int indice = rowSM.getMinSelectionIndex();
			if(indice >= 0)
				cargarDatosFamilia(modeloFamiliaEdicion.getFamilia(indice));
			else
				limpiarCamposNuevo();
		}
		else
		{
//			indiceCalendario = rowSM.getMinSelectionIndex();
//			System.out.println("pintar calendario");
//			pintarCalendario(indiceCalendario);
		}
    }

//    public void configuracionFormulario() {
//        if (usuario != null) {
//            int indice = -1;
//            SistemaFormularios formulario = new SistemaFormularios();
//            formulario.setNombre_formulario(FormUtilities.getClassName(formFamilia.getClass()));
//            SistemaFormulariosPermisosUsuarios permisosCorrespondencia = new SistemaFormulariosPermisosUsuarios();
//            permisosCorrespondencia.setSistemaFormulario(formulario);
//
//            indice = Collections.binarySearch(usuario.getListadoInterfacesPermisos(), permisosCorrespondencia, new ComparadorIdFormulario());
//            if (indice >= 0) {
//                permisosCorrespondencia = usuario.getListadoInterfacesPermisos().get(indice);
//
////
//                if(!permisosCorrespondencia.isPermitir_insertar())
//                    formFamilia.getjTabbedPane1().removeTabAt(0);
//
//                if(!permisosCorrespondencia.isPermitir_editar())
//                    formFamilia.getjTabbedPane1().removeTabAt(1);
//
//                if(!permisosCorrespondencia.isPermitir_anular() && !permisosCorrespondencia.isPermitir_navegar())
//                    formFamilia.getjTabbedPane1().removeTabAt(2);
//                else
//                {
//                    formFamilia.getBtnEliminar().setVisible(permisosCorrespondencia.isPemitir_eliminar());
//                    formFamilia.getBtnBuscarX().setVisible(permisosCorrespondencia.isPermitir_navegar());
//                }
//                formFamilia.getBtnVerReporteCorrespondencia().setVisible(permisosCorrespondencia.isPermitir_reportes());
//            } else {
//                System.out.println("no tiene permisos, nombre de la Clase " + formulario.getNombre_formulario());
//            }
//        }
//    }

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
