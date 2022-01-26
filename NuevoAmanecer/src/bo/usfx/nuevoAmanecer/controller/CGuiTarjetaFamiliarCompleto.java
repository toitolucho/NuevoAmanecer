/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.ActividadEducativa;
import bo.usfx.nuevoAmanecer.model.domain.Afiliado;
import bo.usfx.nuevoAmanecer.model.domain.AfiliadoVacuna;
import bo.usfx.nuevoAmanecer.model.domain.Casos;
import bo.usfx.nuevoAmanecer.model.domain.CausasMuerte;
import bo.usfx.nuevoAmanecer.model.domain.Eccd;
import bo.usfx.nuevoAmanecer.model.domain.EventosVitales;
import bo.usfx.nuevoAmanecer.model.domain.Familia;
import bo.usfx.nuevoAmanecer.model.domain.Familiar;
import bo.usfx.nuevoAmanecer.model.domain.Ocupaciones;
import bo.usfx.nuevoAmanecer.model.domain.Padrinos;
import bo.usfx.nuevoAmanecer.model.domain.SaludPublica;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormularios;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormulariosPermisosUsuarios;
import bo.usfx.nuevoAmanecer.model.domain.Tarjeta;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.nuevoAmanecer.model.domain.Vacunas2;
import bo.usfx.utils.ComparadorIdFormulario;
import bo.usfx.utils.CopyDirectory;
import bo.usfx.utils.FormUtilities;
import bo.usfx.utils.ImageFileView;
import bo.usfx.utils.ImageFilter;
import bo.usfx.utils.ImagePreview;
import bo.usfx.utils.ModeloAfiliadoVacunas;
import bo.usfx.utils.ModeloEccd;
import bo.usfx.utils.ModeloFamilia;
import bo.usfx.utils.ObjetoCodigoDescripcion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.GuiEccd;
import view.GuiGeneralidades;
import view.GuiOcupaciones;
import view.GuiPatrocinadorBuscador;
import view.GuiSaludPublica;
import view.GuiTarjeta;
import view.GuiTarjetaBuscador;
import view.GuiTarjetaFamiliarCompleto;
import view.GuiVacuna;

/**
 *
 * @author Luis Molina
 */
public class CGuiTarjetaFamiliarCompleto extends MouseAdapter implements ActionListener, ListSelectionListener, KeyListener{

    GuiTarjetaFamiliarCompleto formTajetaFamiliar;
    private CommonDao dao;
    private Connection conexion;
    private Usuarios usuario;
    private final String TituloFormulario = "Gestion Kardex Tarjeta Familiar";
    private ModeloFamilia modeloMiembroFamiliar;
    private ModeloAfiliadoVacunas modeloVacuna;
    private ModeloEccd modeloEccd;
    private List<Tarjeta> listaTarjetas;
    private List<Familia> listaMiembrosFamilia;
    private Familia miembroFamiliarActual = null;
    private List<Ocupaciones> listadoOcupaciones;
    private List<EventosVitales> listadoEventosVitales;
    private List<ActividadEducativa> listadoActividadEducativa;
    private List<CausasMuerte> listadoCausasMuerte;
    private List<Vacunas2> listadoVacunas;
    private String[] listaParentescoMasculino;
    private String[] listaParentescoFemenino;
    private String TipoOperacion ="";
    private String TipoOperacionVacunas ="";
    private String TipoOperacionEccd ="";
    private boolean esRegistroAfiliado = false;
    java.text.SimpleDateFormat df = new java.text.SimpleDateFormat( "dd/MM/yyyy" );
    boolean edicionFotografia = false;
    String rutaLocal, nombreFotografia, rutaFotografia, TipoOperacionTarjeta = "";

    public CGuiTarjetaFamiliarCompleto(GuiTarjetaFamiliarCompleto formTarjetaFamiliar)
    {
        this.formTajetaFamiliar = formTarjetaFamiliar;
        this.modeloEccd = new ModeloEccd();
        this.modeloMiembroFamiliar = new ModeloFamilia();
        this.modeloVacuna = new ModeloAfiliadoVacunas();

        //new String[] { "Seleccionar", "Bisabuelo", "Abuelo", "Padre", "Hijo", "Hermano", "Tio", "Sobrino", "Nieto ", "Hijo adoptivo", "Padrastro", "Hijastro", "Padre Adoptivo", "Hermanastro", "Otro" }));
        this.listaParentescoMasculino = new String[]{"Bisabuelo", "Abuelo", "Padre", "Hijo", "Hermano", "Tio", "Sobrino", "Nieto ", "Hijo adoptivo", "Padrastro", "Hijastro", "Padre Adoptivo", "Hermanastro", "Otro","No Tiene", "Apoderado"};
        this.listaParentescoFemenino = new String[]{"Bisabuela", "Abuela", "Madre", "Hija", "Hermana", "Tia", "Sobrina", "Nieta", "Hija Adoptiva", "Madrastra", "Hijastra", "Madre Adoptiva", "Hermanastra", "Otro","No Tiene","Apoderada"};

        rutaLocal = FormUtilities.obtenerRutaLocal();
        
    }

    public void limpiarCamposMiembroFamilia()
    {
        this.formTajetaFamiliar.getTxtNroCaso().setText("");
        this.formTajetaFamiliar.getTxtNroNino().setText("");        
        this.formTajetaFamiliar.getTxtCi().setText("");
        this.formTajetaFamiliar.getTxtNombreCorto().setText("");
        this.formTajetaFamiliar.getTxtNombres().setText("");
        this.formTajetaFamiliar.getTxtPaterno().setText("");
        this.formTajetaFamiliar.getTxtMaterno().setText("");
        this.formTajetaFamiliar.getLblCodigoEstadoPersona().setText("");

        this.formTajetaFamiliar.getRbtnFemenino().setSelected(false);
        this.formTajetaFamiliar.getRbtnMasculino().setSelected(false);
        this.formTajetaFamiliar.getRbtnNino().setSelected(false);
        this.formTajetaFamiliar.getRbtnMadre().setSelected(false);
        this.formTajetaFamiliar.getCheckAlfabetizado().setSelected(false);
        this.formTajetaFamiliar.getCheckConCertificado().setSelected(false);
        this.formTajetaFamiliar.getDateFechaAlfabetizacion().setDate(null);
        this.formTajetaFamiliar.getDateFechaNacimiento().setDate(null);
        this.formTajetaFamiliar.getcBoxActividadEducativa().setSelectedIndex(-1);
        this.formTajetaFamiliar.getcBoxOcupacion().setSelectedIndex(-1);
        this.formTajetaFamiliar.getcBoxEstadoActividad().setSelectedIndex(-1);
        this.formTajetaFamiliar.getcBoxParentesco().setSelectedIndex(-1);
        this.formTajetaFamiliar.getcBoxEventoVital().setSelectedIndex(-1);
        this.formTajetaFamiliar.getcBoxCausaEventoVital().setSelectedIndex(-1);
        this.formTajetaFamiliar.getLblPadrinoAsignado().setText("");

    }

    public void habilitarCamposMiembroFamilia(boolean estadoHabilitacion)
    {
        this.formTajetaFamiliar.getTxtNroCaso().setEditable(false);
        this.formTajetaFamiliar.getTxtNroNino().setEditable(estadoHabilitacion);
        this.formTajetaFamiliar.getTxtCi().setEditable(estadoHabilitacion);
        this.formTajetaFamiliar.getTxtNombreCorto().setEditable(estadoHabilitacion);
        this.formTajetaFamiliar.getTxtNombres().setEditable(estadoHabilitacion);
        this.formTajetaFamiliar.getTxtPaterno().setEditable(estadoHabilitacion);
        this.formTajetaFamiliar.getTxtMaterno().setEditable(estadoHabilitacion);

        this.formTajetaFamiliar.getRbtnFemenino().setEnabled(estadoHabilitacion);
        this.formTajetaFamiliar.getRbtnMasculino().setEnabled(estadoHabilitacion);
        this.formTajetaFamiliar.getRbtnNino().setEnabled(estadoHabilitacion);
        this.formTajetaFamiliar.getRbtnMadre().setEnabled(estadoHabilitacion);
        this.formTajetaFamiliar.getCheckAlfabetizado().setEnabled(estadoHabilitacion);
        this.formTajetaFamiliar.getCheckConCertificado().setEnabled(estadoHabilitacion);
        this.formTajetaFamiliar.getDateFechaAlfabetizacion().setEnabled(estadoHabilitacion);
        this.formTajetaFamiliar.getDateFechaNacimiento().setEnabled(estadoHabilitacion);
        this.formTajetaFamiliar.getcBoxActividadEducativa().setEnabled(estadoHabilitacion);
        this.formTajetaFamiliar.getcBoxOcupacion().setEnabled(estadoHabilitacion);
        this.formTajetaFamiliar.getcBoxEstadoActividad().setEnabled(estadoHabilitacion);
        this.formTajetaFamiliar.getcBoxParentesco().setEnabled(estadoHabilitacion);
        this.formTajetaFamiliar.getcBoxEventoVital().setEnabled(estadoHabilitacion);
        this.formTajetaFamiliar.getcBoxCausaEventoVital().setEnabled(estadoHabilitacion);
        this.formTajetaFamiliar.getBtnImagen().setEnabled(estadoHabilitacion);
        this.formTajetaFamiliar.getBtnAgregarOcupacion().setEnabled(estadoHabilitacion);

    }

    public void limpiarCamposVacunas()
    {
        this.formTajetaFamiliar.getcBoxVacuna().setSelectedIndex(-1);
        this.formTajetaFamiliar.getTxtVacunasObservaciones().setText("");
        this.formTajetaFamiliar.getDateFechaVacuna().setDate(null);
    }

    public void habilitarCamposVacunas(boolean estadoHabilitacion)
    {
        this.formTajetaFamiliar.getcBoxVacuna().setEnabled(TipoOperacionVacunas == "N" ? estadoHabilitacion : false);
        this.formTajetaFamiliar.getTxtVacunasObservaciones().setEditable(estadoHabilitacion);
        this.formTajetaFamiliar.getDateFechaVacuna().setEnabled(estadoHabilitacion);
    }

    public void limpiarCamposECCD()
    {
        this.formTajetaFamiliar.getTxtPeso().setText("");
        this.formTajetaFamiliar.getTxtTalla().setText("");
        this.formTajetaFamiliar.getTxtPesoTalla().setText("");
        this.formTajetaFamiliar.getTxtSeven().setText("");
        this.formTajetaFamiliar.getDateECCDRegistro().setDate(null);
    }

    public void habilitarCamposECCD(boolean estadoHabilitacion)
    {
        this.formTajetaFamiliar.getTxtPeso().setEditable(estadoHabilitacion);
        this.formTajetaFamiliar.getTxtTalla().setEditable(estadoHabilitacion);
        this.formTajetaFamiliar.getDateECCDRegistro().setEnabled(estadoHabilitacion);
    }

    public void habilitarBotonesMiembroFamilia(boolean nuevo, boolean editar, boolean aceptar,
            boolean cancelar, boolean eliminar, boolean asignarPadrino)
    {
        this.formTajetaFamiliar.getBtnNuevo().setEnabled(nuevo);
        this.formTajetaFamiliar.getBtnEditar().setEnabled(editar);
        this.formTajetaFamiliar.getBtnAceptar().setEnabled(aceptar);
        this.formTajetaFamiliar.getBtnCancelar().setEnabled(cancelar);
        this.formTajetaFamiliar.getBtnEliminar().setEnabled(eliminar);
        this.formTajetaFamiliar.getBtnAsignarPadrino().setEnabled(asignarPadrino);
    }

    public void habilitarBotonesVacuna(boolean nuevo, boolean editar, boolean aceptar,
            boolean cancelar, boolean eliminar)
    {
        this.formTajetaFamiliar.getBtnVacunaNuevo().setEnabled(nuevo);
        this.formTajetaFamiliar.getBtnVacunaEditar().setEnabled(editar);
        this.formTajetaFamiliar.getBtnVacunaAceptar().setEnabled(aceptar);
        this.formTajetaFamiliar.getBtnVacunaCancelar().setEnabled(cancelar);
        this.formTajetaFamiliar.getBtnVacunaEliminar().setEnabled(eliminar);
        
    }

    public void habilitarBotonesECCD(boolean nuevo, boolean editar, boolean aceptar,
            boolean cancelar, boolean eliminar)
    {
        this.formTajetaFamiliar.getBtnEccdNuevo().setEnabled(nuevo);
        this.formTajetaFamiliar.getBtnEccdEditar().setEnabled(editar);
        this.formTajetaFamiliar.getBtnEccdAceptar().setEnabled(aceptar);
        this.formTajetaFamiliar.getBtnEccdCancelar().setEnabled(cancelar);
        this.formTajetaFamiliar.getBtnEccdEliminar().setEnabled(eliminar);
    }

    public void cargarMiembroFamiliarECCD(Eccd eccdMiembroFamiliar)
    {
        if (eccdMiembroFamiliar != null) {
            formTajetaFamiliar.getTxtPeso().setText(String.valueOf(eccdMiembroFamiliar.getPeso()));
            formTajetaFamiliar.getTxtTalla().setText(String.valueOf(eccdMiembroFamiliar.getTalla()));
            
            formTajetaFamiliar.getTxtPesoTalla().setText(String.valueOf(eccdMiembroFamiliar.getPeso_talla()));
            this.formTajetaFamiliar.getDateECCDRegistro().setDate(eccdMiembroFamiliar.getFecha_registro());

//            formTajetaFamiliar.getcBoxSeven().removeAllItems();
//            formTajetaFamiliar.getcBoxSeven().addItem(eccdMiembroFamiliar.getSven());
            formTajetaFamiliar.getTxtSeven().setText(eccdMiembroFamiliar.getSven());
            habilitarCamposECCD(false);
            habilitarBotonesECCD(miembroFamiliarActual != null && miembroFamiliarActual.getCodigo_estado_familia().compareTo("H") == 0,
                    true, false, false, false);
//            if (eccdMiembroFamiliar.getSven().compareTo("NS") == 0) {
//                formTajetaFamiliar.getcBoxSeven().setSelectedIndex(0);
//
//            }
//            if (eccdMiembroFamiliar.getSven().compareTo("N") == 0) {
//                formTajetaFamiliar.getcBoxSeven().setSelectedIndex(1);
//            }
//            if (eccdMiembroFamiliar.getSven().compareTo("DL") == 0) {
//                formTajetaFamiliar.getcBoxSeven().setSelectedIndex(2);
//            }
//            if (eccdMiembroFamiliar.getSven().compareTo("DM") == 0) {
//                formTajetaFamiliar.getcBoxSeven().setSelectedIndex(3);
//            }
//            if (eccdMiembroFamiliar.getSven().compareTo("DS") == 0) {
//                formTajetaFamiliar.getcBoxSeven().setSelectedIndex(4);
//            }
        } else {
            limpiarCamposECCD();
            habilitarCamposECCD(false);
            habilitarBotonesECCD(true, false, false, false, false);
        }
    }

    public void cargarMiembroFamiliarsVacunas(AfiliadoVacuna vacunaMiembroFamiliar)
    {
        habilitarCamposVacunas(false);
        if(vacunaMiembroFamiliar !=null)
        {
            
            int indice = Collections.binarySearch(listadoVacunas, vacunaMiembroFamiliar.getVacuna());

            this.formTajetaFamiliar.getcBoxVacuna().setSelectedIndex(indice >= 0 ? indice : -1);
            this.formTajetaFamiliar.getTxtVacunasObservaciones().setText(vacunaMiembroFamiliar.getObservaciones());
            this.formTajetaFamiliar.getDateFechaVacuna().setDate(vacunaMiembroFamiliar.getFecha_vacuna2());
            habilitarBotonesVacuna(miembroFamiliarActual != null && miembroFamiliarActual.getCodigo_estado_familia().compareTo("H") == 0,
                    true, false, false, true);
        }
        else
        {
            limpiarCamposVacunas();
            
            habilitarBotonesVacuna(true, false, false, false, false);
        }
    }
    public void cargarMiembroFamiliar(Familia miembroFamiliar)
    {
        if(miembroFamiliar != null)
        {
            try {
                boolean esAfiliado = dao.esAfiliado(miembroFamiliar.getCodigo_persona());
                boolean estaHabilitado = miembroFamiliar.getCodigo_estado_familia()!= null && !miembroFamiliar.getCodigo_estado_familia().isEmpty() && miembroFamiliar.getCodigo_estado_familia().compareTo("H") == 0;
                cargarEventosVitales("T");
                esRegistroAfiliado = esAfiliado;
                habilitarCamposMiembroFamilia(false);
                formTajetaFamiliar.getLblCodigoEstadoPersona().setText( estaHabilitado ? "" : "Afiliado Indisponible (Dado de Baja)");
                habilitarBotonesMiembroFamilia(true, estaHabilitado, false, false, true, esAfiliado);
                if (esAfiliado) {
                    Afiliado afiliado = new Afiliado(miembroFamiliar, 1, "", "", false, miembroFamiliar.getCodigo_persona(), -1);                    
                    afiliado.setNumero_caso(-1);
                    afiliado = (Afiliado) dao.obtenerObjeto(afiliado);
                    if(afiliado != null)
                    {
                        miembroFamiliarActual = afiliado;
                        this.formTajetaFamiliar.getTxtNroCaso().setText(String.valueOf(afiliado.getNumero_caso()));
                        this.formTajetaFamiliar.getTxtNroNino().setText(afiliado.getNumero_nino()< 0 ? "" : String.valueOf(afiliado.getNumero_nino()));
                        this.formTajetaFamiliar.getTxtNombreCorto().setText(afiliado.getNombre_corto());

                        this.formTajetaFamiliar.getRbtnNino().setSelected(afiliado.isVacuna_nino());
                        this.formTajetaFamiliar.getRbtnMadre().setSelected(false);

                        AfiliadoVacuna vacunaAfiliado = new AfiliadoVacuna();
                        vacunaAfiliado.setCodigo_persona(miembroFamiliar.getCodigo_persona());
                        List<AfiliadoVacuna> listaAfiliadoVacunas = (List<AfiliadoVacuna>) dao.getObjects("AfiliadoVacuna", vacunaAfiliado);
                        modeloVacuna.clear();
                        if (listaAfiliadoVacunas != null) {
                            for (AfiliadoVacuna vacunas : listaAfiliadoVacunas) {
                                modeloVacuna.addAfiliadoVacuna(vacunas);
                            }
                        }
                        habilitarCamposVacunas(false);
                        limpiarCamposVacunas();
                        habilitarBotonesVacuna(estaHabilitado, false, false, false, false);

                        Eccd eccdAfiliado = new Eccd();
                        eccdAfiliado.setNumero_caso(afiliado.getNumero_caso());
                        List<Eccd> listaECCD = (List<Eccd>) dao.getObjects("Eccd", eccdAfiliado);
                        modeloEccd.clear();
                        if (listaECCD != null) {
                            for (Eccd eccd : listaECCD) {
                                modeloEccd.addEccd(eccd);
                            }
                        }
                        habilitarCamposECCD(false);
                        limpiarCamposECCD();
                        habilitarBotonesECCD(estaHabilitado, false, false, false, false);


                        //cargamos datos de su padrino
                        Casos caso = new Casos(null, afiliado.getNumero_caso(), -1);
                        caso = (Casos) dao.obtenerObjeto(caso);
                        if (caso != null) {
                            Padrinos patrocinador = new Padrinos();
                            patrocinador.setCodigo_padrino(caso.getCodigo_padrino());
                            patrocinador = (Padrinos) dao.obtenerObjeto(patrocinador);
                            formTajetaFamiliar.getLblPadrinoAsignado().setText("Patrocinador :" + patrocinador.getNombreCompleto());
                        } else {
                            formTajetaFamiliar.getLblPadrinoAsignado().setText("Afiliado en espera de asignación de PATROCINADOR");
                        }
                        this.formTajetaFamiliar.getjTabbedPaneKardexTarjeta().setBorder(javax.swing.BorderFactory.createTitledBorder(
                            null, "Kardex de Afiliado: " + afiliado.getNombreCompleto() + ", Nro Caso: " + String.valueOf(afiliado.getNumero_caso()),
                            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
                    } else{
                        this.formTajetaFamiliar.getjTabbedPaneKardexTarjeta().setBorder(javax.swing.BorderFactory.createTitledBorder(
                            null, "Kardex de Afiliado: " + miembroFamiliar.getNombreCompleto(),
                            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
                        habilitarBotonesMiembroFamilia(true, false, false, false, false, false);
                        habilitarBotonesECCD(false, false, false, false, false);
                        habilitarBotonesVacuna(false, false, false, false, false);
                    }


                    //Opciones de Visualizacion                   
                    //addTab("ECCD", new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/eccd.png")), pnlTabPageECCD);
                    //addTab("Buscar", new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Buscar.png")), pnlTabPageBusqueda);
                    if(!formTajetaFamiliar.getPnlTabPageVacunas().isShowing())
                    {
                        this.formTajetaFamiliar.getjTabbedPaneKardexTarjeta().addTab("Kardex de Vacuna", new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/vacuna.png")),formTajetaFamiliar.getPnlTabPageVacunas());
                        this.formTajetaFamiliar.getjTabbedPaneKardexTarjeta().remove(formTajetaFamiliar.getPnlTabPageBusqueda());
                    }
                    if(!formTajetaFamiliar.getPnlTabPageECCD().isShowing())
                    {
                        this.formTajetaFamiliar.getjTabbedPaneKardexTarjeta().addTab("ECCD", new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/eccd.png")),formTajetaFamiliar.getPnlTabPageECCD());
                        this.formTajetaFamiliar.getjTabbedPaneKardexTarjeta().addTab("Buscar", new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Buscar.png")), formTajetaFamiliar.getPnlTabPageBusqueda());
                    }
                    this.formTajetaFamiliar.getjTabbedPaneKardexTarjeta().setSelectedComponent(formTajetaFamiliar.getPnlTabPageBusqueda());
                }
                else
                {
                    Familiar familiar = new Familiar(miembroFamiliar, false);                    
                    familiar= (Familiar) dao.obtenerObjeto(familiar);

                    this.formTajetaFamiliar.getTxtNroCaso().setText("");
                    this.formTajetaFamiliar.getTxtNroNino().setText("");
                    this.formTajetaFamiliar.getTxtNombreCorto().setText("");
                    this.formTajetaFamiliar.getRbtnNino().setSelected(false);
                    if(familiar != null)
                    {
                        miembroFamiliarActual = familiar;
                        
                        this.formTajetaFamiliar.getRbtnMadre().setSelected(familiar.isVacuna_madre());
                    } else
                    {
                        habilitarBotonesMiembroFamilia(true, false, false, false, false, false);
                        habilitarBotonesECCD(false, false, false, false, false);
                        habilitarBotonesVacuna(false, false, false, false, false);
                    }
                    

                    this.formTajetaFamiliar.getjTabbedPaneKardexTarjeta().setBorder(javax.swing.BorderFactory.createTitledBorder(
                            null, "Kardex de Familiar: " + miembroFamiliar.getNombreCompleto(),
                            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
//                    if(formTajetaFamiliar.getPnlTabPageECCD().isShowing())
                        this.formTajetaFamiliar.getjTabbedPaneKardexTarjeta().remove(formTajetaFamiliar.getPnlTabPageECCD());
//                    if(formTajetaFamiliar.getPnlTabPageVacunas().isShowing())
                        this.formTajetaFamiliar.getjTabbedPaneKardexTarjeta().remove(formTajetaFamiliar.getPnlTabPageVacunas());

                    formTajetaFamiliar.getLblPadrinoAsignado().setText("");
                }
                
                
                this.formTajetaFamiliar.getTxtCi().setText(miembroFamiliar.getCi()<0 ? "" : String.valueOf(miembroFamiliar.getCi()));
                
                this.formTajetaFamiliar.getTxtNombres().setText(miembroFamiliar.getNombres());
                this.formTajetaFamiliar.getTxtPaterno().setText(miembroFamiliar.getApellido_paterno());
                this.formTajetaFamiliar.getTxtMaterno().setText(miembroFamiliar.getApellido_materno());
                if (miembroFamiliar.getSexo().compareTo("F") == 0) {
                    this.formTajetaFamiliar.getRbtnFemenino().setSelected(true);
                } else {
                    this.formTajetaFamiliar.getRbtnMasculino().setSelected(true);
                }
                
                this.formTajetaFamiliar.getCheckAlfabetizado().setSelected(miembroFamiliar.isAlfabetizado());
                this.formTajetaFamiliar.getCheckConCertificado().setSelected(miembroFamiliar.isCon_certificado());
                this.formTajetaFamiliar.getDateFechaAlfabetizacion().setDate(miembroFamiliar.getFecha_alfetizacion());
                this.formTajetaFamiliar.getDateFechaNacimiento().setDate(miembroFamiliar.getFecha_nacimiento());
                
                
                this.formTajetaFamiliar.getcBoxEstadoActividad().setSelectedItem(miembroFamiliar.getTipo_ocu());

                this.formTajetaFamiliar.getcBoxParentesco().removeAllItems();
                this.formTajetaFamiliar.getcBoxParentesco().addItem(miembroFamiliar.getParentesco());                
                this.formTajetaFamiliar.getcBoxParentesco().setSelectedItem(miembroFamiliar.getParentesco());

                
                int indice = -1;
                if(formTajetaFamiliar.getcBoxActividadEducativa() != null && listadoActividadEducativa != null)
                {
                    indice = Collections.binarySearch(listadoActividadEducativa, miembroFamiliar.getActividadEducativa());
                    formTajetaFamiliar.getcBoxActividadEducativa().setSelectedIndex(indice >= 0 ? indice : -1);
                }
                System.out.println("Codigo ocupacion " + miembroFamiliar.getCodigo_ocupacion());
                if(miembroFamiliar.getCodigo_ocupacion() >= 0 && miembroFamiliar.getOcupacion() !=null)
                {
                    indice = Collections.binarySearch(listadoOcupaciones, miembroFamiliar.getOcupacion());
                    formTajetaFamiliar.getcBoxOcupacion().setSelectedIndex(indice >= 0 ? indice : -1);
                }
                if(miembroFamiliar.getEventoVital() != null)
                {
                    indice = Collections.binarySearch(listadoEventosVitales, miembroFamiliar.getEventoVital());
                    formTajetaFamiliar.getcBoxEventoVital().setSelectedIndex(indice >= 0 ? indice : -1);
                }

                if(miembroFamiliar.getCausaMuerte() != null)
                {
//                    System.out.println("Cantidad Lista" + listadoCausasMuerte.size() + ", cantida Combo " + formTajetaFamiliar.getcBoxCausaEventoVital().getItemCount());
//                    System.out.println("Primera elemento lista" + listadoCausasMuerte.get(0) + ", Cantidad Combo"+ formTajetaFamiliar.getcBoxCausaEventoVital().getItemAt(0));
                    indice = Collections.binarySearch(listadoCausasMuerte, miembroFamiliar.getCausaMuerte());
                    formTajetaFamiliar.getcBoxCausaEventoVital().setSelectedIndex(indice >= 0 ? indice : -1);
                }
                else
                {
                    formTajetaFamiliar.getcBoxCausaEventoVital().setSelectedIndex(-1);
                }
              
           
//PARA LA IMAGEN                
                if (miembroFamiliar.getRuta_archivo_imagen() == "" || miembroFamiliar.getRuta_archivo_imagen() == null) {
			formTajetaFamiliar.getLblImagen().setIcon(new ImageIcon(getClass().getResource("/fotografiasPersonas/persona.png")));
		} else {
			if (new File(rutaLocal + miembroFamiliar.getRuta_archivo_imagen()).exists()) {
				formTajetaFamiliar.getLblImagen().setIcon(new ImageIcon(rutaLocal + miembroFamiliar.getRuta_archivo_imagen()));
			}

		}
                if(formTajetaFamiliar.getLblImagen().getIcon() != null)
                    ((ImageIcon) formTajetaFamiliar.getLblImagen().getIcon()).setImage(((ImageIcon) formTajetaFamiliar.getLblImagen().getIcon()).getImage().getScaledInstance(300, 220, 0));
            } catch (SQLException ex) {
                Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else
        {
            limpiarCamposMiembroFamilia();
            limpiarCamposVacunas();
            limpiarCamposECCD();
            habilitarCamposMiembroFamilia(false);

            habilitarBotonesMiembroFamilia(true, false, false, false, false, false);
            this.formTajetaFamiliar.getjTabbedPaneKardexTarjeta().remove(formTajetaFamiliar.getPnlTabPageECCD());
            this.formTajetaFamiliar.getjTabbedPaneKardexTarjeta().remove(formTajetaFamiliar.getPnlTabPageVacunas());

        }
    }

    public void onFormShown()
    {
        this.formTajetaFamiliar.getjTableBusqueda().setModel(modeloMiembroFamiliar);
        this.formTajetaFamiliar.getjTableVacunas().setModel(modeloVacuna);
        this.formTajetaFamiliar.getjTableECCD().setModel(modeloEccd);
        this.formTajetaFamiliar.getjTableBusqueda().getSelectionModel().addListSelectionListener(this);
        this.formTajetaFamiliar.getjTableECCD().getSelectionModel().addListSelectionListener(this);
        this.formTajetaFamiliar.getjTableVacunas().getSelectionModel().addListSelectionListener(this);
        this.formTajetaFamiliar.getjTableBusqueda().addMouseListener(this);
        this.formTajetaFamiliar.getBtnAceptarTarjeta2().setVisible(false);
        cargarMiembroFamiliar(null);

        ButtonGroup bgSexo = new ButtonGroup();
//        ButtonGroup bgVacuna = new ButtonGroup();
        bgSexo.add(formTajetaFamiliar.getRbtnFemenino());
        bgSexo.add(formTajetaFamiliar.getRbtnMasculino());
//        bgVacuna.add(formTajetaFamiliar.getRbtnNino());
//        bgVacuna.add(formTajetaFamiliar.getRbtnMadre());
        formTajetaFamiliar.getcBoxListaTarjetas().removeAllItems();
        try {
            listaTarjetas = dao.getObjects("Tarjeta", new Tarjeta());
            System.out.println("Tamaño lista Original " + listaTarjetas.size());
            for (int indice = 0; indice < listaTarjetas.size(); indice++) {
                Tarjeta tarjeta = listaTarjetas.get(indice);
                if(tarjeta.getCodigo_estado_tarjeta().compareTo("H") == 0)
                {
                    listaTarjetas.remove(tarjeta);
                }
                else
                    indice++;
            }
            System.out.println("Tamaño lista Modificada " + listaTarjetas.size());
            Collections.sort(listaTarjetas);
            for (Tarjeta tarjeta : listaTarjetas) {                
                    formTajetaFamiliar.getcBoxListaTarjetas().addItem(tarjeta);
            }

            listadoOcupaciones = dao.getObjects("Ocupaciones");
            Collections.sort(listadoOcupaciones);
            listadoEventosVitales = dao.getObjects("EventosVitales");
            Collections.sort(listadoEventosVitales);
            listadoActividadEducativa = dao.getObjects("ActividadEducativa");
            Collections.sort(listadoActividadEducativa);
            listadoCausasMuerte = dao.getObjects("CausasMuerte");
            Collections.sort(listadoCausasMuerte);

            
            formTajetaFamiliar.getcBoxOcupacion().removeAllItems();
            for(Ocupaciones ocupacion : listadoOcupaciones)
            {
                formTajetaFamiliar.getcBoxOcupacion().addItem(ocupacion);
            }

//            formTajetaFamiliar.getcBoxEventoVital().removeAllItems();
//            for(EventosVitales eventoVital : listadoEventosVitales)
//            {
//                formTajetaFamiliar.getcBoxEventoVital().addItem(eventoVital);
//            }

            formTajetaFamiliar.getcBoxActividadEducativa().removeAllItems();
            for(ActividadEducativa actividadesEducativas : listadoActividadEducativa)
            {
                formTajetaFamiliar.getcBoxActividadEducativa().addItem(actividadesEducativas);
                
            }

            formTajetaFamiliar.getcBoxCausaEventoVital().removeAllItems();
            for(CausasMuerte causaMuerte : listadoCausasMuerte)
            {
                formTajetaFamiliar.getcBoxCausaEventoVital().addItem(causaMuerte);
            }
            cargarEventosVitales("T");

            //ECCD this.formEccd.getcBoxSevenE().addItem(new ObjetoCodigoDescripcion("NS", "NUTRICION SUPERIOR"));
//            this.formTajetaFamiliar.getcBoxSeven().addItem(new ObjetoCodigoDescripcion("N", "NUTRICION NORMAL"));
//            this.formTajetaFamiliar.getcBoxSeven().addItem(new ObjetoCodigoDescripcion("DL", "DESNUTRICION LEVE 1ER GRADO"));
//            this.formTajetaFamiliar.getcBoxSeven().addItem(new ObjetoCodigoDescripcion("DM", "DESNUTRICION MODERADA 2DO GRADO"));
//            this.formTajetaFamiliar.getcBoxSeven().addItem(new ObjetoCodigoDescripcion("DS", "DESNUTRICION SEVERA 3ER GRADO"));

            listadoVacunas = dao.getObjects("Vacunas2", new Vacunas2());
            formTajetaFamiliar.getcBoxVacuna().removeAllItems();
            Collections.sort(listadoVacunas);
            for (Vacunas2 vacunas2 : listadoVacunas) {
                formTajetaFamiliar.getcBoxVacuna().addItem(vacunas2);
            }
            formTajetaFamiliar.getTxtPlanoVivienda().setEnabled(false);
            configuracionFormulario();

        } catch (SQLException ex) {            
            ex.printStackTrace();
        }

    }

    //opcionCargado = 'A'->Afiliados, 'F'->familiar, 'T'->Todos
    public void cargarEventosVitales(String opcionCargado)
    {
        this.formTajetaFamiliar.getcBoxEventoVital().removeAllItems();
        if(opcionCargado.compareTo("A") == 0)
        {
            for (EventosVitales eventosVitales : listadoEventosVitales) {
                if(eventosVitales.getNombre_evento_vital().compareTo("Personas que se fueron") != 0
                        && eventosVitales.getNombre_evento_vital().compareTo("Muertes") != 0
                        && eventosVitales.getNombre_evento_vital().compareTo("Embarazo") != 0
                        )
                    this.formTajetaFamiliar.getcBoxEventoVital().addItem(eventosVitales);
            }
        }

        if(opcionCargado.compareTo("F") == 0)
        {
             for (EventosVitales eventosVitales : listadoEventosVitales) {
                if(eventosVitales.getNombre_evento_vital().compareTo("Personas que se fueron") != 0
                        && eventosVitales.getNombre_evento_vital().compareTo("Muertes") != 0
                        &&(eventosVitales.getNombre_evento_vital().compareTo("Embarazo") !=0 || formTajetaFamiliar.getRbtnFemenino().isSelected())
                        )
                    this.formTajetaFamiliar.getcBoxEventoVital().addItem(eventosVitales);
            }
        }

        if(opcionCargado.compareTo("T") == 0)
        {
            for (EventosVitales eventosVitales : listadoEventosVitales) {
                this.formTajetaFamiliar.getcBoxEventoVital().addItem(eventosVitales);
            }
        }

    }

    public boolean validarDatosMiembroFamiliar()
    {
        if(formTajetaFamiliar.getTxtCi().getText().trim().isEmpty()
                &&JOptionPane.showConfirmDialog(formTajetaFamiliar, "Se encuentra seguro de dejar en Blanco el C.I.?",
                TituloFormulario, JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
        {
            formTajetaFamiliar.getTxtCi().grabFocus();
            formTajetaFamiliar.getTxtCi().selectAll();
            return false;
        }

        if(esRegistroAfiliado && formTajetaFamiliar.getTxtNroCaso().getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "No puede dejar en blanco el Nro de Caso del Afiliado", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getTxtNroCaso().grabFocus();
            formTajetaFamiliar.getTxtNroCaso().selectAll();
        }

        if(formTajetaFamiliar.getTxtNombres().getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "No puede dejar en blanco el Nombre del Miembro Familiar", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getTxtNombres().grabFocus();
            formTajetaFamiliar.getTxtNombres().selectAll();
            return false;
        }
//        if(formTajetaFamiliar.getTxtPaterno().getText().trim().isEmpty())
//        {
//            JOptionPane.showMessageDialog(formTajetaFamiliar, "No puede dejar en blanco el Primer Apellido del Miembro Familiar", TituloFormulario, JOptionPane.ERROR_MESSAGE);
//            formTajetaFamiliar.getTxtPaterno().grabFocus();
//            formTajetaFamiliar.getTxtPaterno().selectAll();
//            return false;
//        }
//        if(formTajetaFamiliar.getTxtMaterno().getText().trim().isEmpty()
//                &&JOptionPane.showConfirmDialog(formTajetaFamiliar, "Se encuentra seguro de dejar en Blanco Segundo Apellido?",
//                TituloFormulario, JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
//        {
//            formTajetaFamiliar.getTxtMaterno().grabFocus();
//            formTajetaFamiliar.getTxtMaterno().selectAll();
//            return false;
//        }

        if(formTajetaFamiliar.getTxtPaterno().getText().trim().isEmpty()
                && formTajetaFamiliar.getTxtMaterno().getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "debe ingresar al menos un apellido del Miembro Familiar", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getTxtPaterno().grabFocus();
            formTajetaFamiliar.getTxtPaterno().selectAll();
            return false;
        }
        if(formTajetaFamiliar.getTxtPaterno().getText().trim().isEmpty()
                &&JOptionPane.showConfirmDialog(formTajetaFamiliar, "Se encuentra seguro de dejar en Blanco Primer Apellido?",
                TituloFormulario, JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
        {
            formTajetaFamiliar.getTxtPaterno().grabFocus();
            formTajetaFamiliar.getTxtPaterno().selectAll();
            return false;
        }
        if(formTajetaFamiliar.getTxtMaterno().getText().trim().isEmpty()
                &&JOptionPane.showConfirmDialog(formTajetaFamiliar, "Se encuentra seguro de dejar en Blanco Segundo Apellido?",
                TituloFormulario, JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
        {
            formTajetaFamiliar.getTxtMaterno().grabFocus();
            formTajetaFamiliar.getTxtMaterno().selectAll();
            return false;
        }




        if(!formTajetaFamiliar.getRbtnFemenino().isSelected() && !formTajetaFamiliar.getRbtnMasculino().isSelected())
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "Aún no ha seleccionado el Sexo", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getRbtnMasculino().grabFocus();
            return false;
        }

        if(formTajetaFamiliar.getDateFechaNacimiento().getDate() == null)
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "Aún no ha ingresado la Fecha de Nacimiento", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getDateFechaNacimiento().grabFocus();
            return false;
        }



        Date fechaHoraActual = null;
        try {
            fechaHoraActual = dao.obtenerFechaHoraServidor();
        } catch (SQLException ex) {
            Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        Date FechaNacimiento = formTajetaFamiliar.getDateFechaNacimiento().getDate();
        int EdadActual = -1;
        try {
            EdadActual = dao.ObtenerEdadActual(FechaNacimiento, dao.obtenerFechaHoraServidor());
        } catch (SQLException ex) {
            Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(FechaNacimiento.after(fechaHoraActual))
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "La Fecha de Nacimiento Introducida no es Valida", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getDateFechaNacimiento().setDate(null);
            return false;
        }

        if(EdadActual > 110)
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "La edad de la Persona no es valida", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getDateFechaNacimiento().setDate(null);
            return false;
        }

        if(esRegistroAfiliado && EdadActual >= 21)
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar,
                    "No puede registrar a esta Persona como Afiliado, ya que su Edad Sobrepasa el Limite de 20 años",
                    TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getDateFechaNacimiento().setDate(null);
            return false;
        }


        if(EdadActual < 16 && formTajetaFamiliar.getCheckAlfabetizado().isSelected())
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "No puede Registrar a la Persona Como Alfabetizada, debido a que no cumple con el"
                    + "requisito de al menos tener 16 anios", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getDateFechaAlfabetizacion().grabFocus();
            formTajetaFamiliar.getDateFechaAlfabetizacion().setDate(null);
            formTajetaFamiliar.getCheckAlfabetizado().setSelected(false);
            return false;
        }

        if(formTajetaFamiliar.getCheckAlfabetizado().isSelected() &&
                formTajetaFamiliar.getDateFechaAlfabetizacion().getDate() == null)
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "Aun no ha ingresado la fecha de Alfabetización", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getRbtnMasculino().grabFocus();
            return false;
        }
        
        if(formTajetaFamiliar.getCheckAlfabetizado().isSelected() && formTajetaFamiliar.getDateFechaAlfabetizacion().getDate().after(fechaHoraActual))
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "La Fecha de Alfabetización Introducida no es Valida", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            return false;
        }

//            int AniosAlfatetizacion = dao.ObtenerEdadActual(formTajetaFamiliar.getDateFechaAlfabetizacion().getDate(), fechaHoraActual);
        Calendar FechaMinimaAlfabetizacion = Calendar.getInstance();
        FechaMinimaAlfabetizacion.setTime(formTajetaFamiliar.getDateFechaNacimiento().getDate());
        FechaMinimaAlfabetizacion.set(Calendar.YEAR, FechaMinimaAlfabetizacion.get(Calendar.YEAR) + 15);
        if(formTajetaFamiliar.getCheckAlfabetizado().isSelected() &&
                formTajetaFamiliar.getDateFechaAlfabetizacion().getDate() != null
                && formTajetaFamiliar.getDateFechaAlfabetizacion().getDate().before(FechaMinimaAlfabetizacion.getTime()))
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "La Fecha de Alfabetización no es coherente debido a probablemente sobrepasa la Minima requerida de al menos tener 15 años" ,
                    TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getDateFechaAlfabetizacion().grabFocus();
            return false;
        }
            




//            if(AniosAlfatetizacion - 15 > EdadActual)
//            {
//                JOptionPane.showMessageDialog(formTajetaFamiliar, this, rutaLocal, EdadActual);
//            }

        

        if(EdadActual <= 20 && formTajetaFamiliar.getcBoxActividadEducativa().getSelectedIndex() < 0 )
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "Aún no ha seleccionado la actividad Educativa de la Persona", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getcBoxActividadEducativa().grabFocus();
            return false;
        }
        if(EdadActual > 20 && formTajetaFamiliar.getcBoxActividadEducativa().getSelectedIndex() >= 0 )
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "El registro de Actividad Educativa es unicamente para personas menores a  20 años", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getcBoxActividadEducativa().setSelectedIndex(-1);
            return false;
        }



        if(EdadActual >= 7 && formTajetaFamiliar.getcBoxOcupacion().getSelectedIndex() < 0)
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "Aún no ha seleccionado la Ocupación de la Persona", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getcBoxOcupacion().grabFocus();
            return false;
        }

        if(EdadActual < 7 && formTajetaFamiliar.getcBoxOcupacion().getSelectedIndex() >= 0)
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "El miembro familiar actual, no cuenta con una edad adecuada para tener una ocupación Laboral", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getcBoxOcupacion().setSelectedIndex(-1);
            return false;
        }

        if(EdadActual >= 7 && esRegistroAfiliado
                && (formTajetaFamiliar.getcBoxOcupacion().getSelectedItem().toString().compareTo("Chofer")== 0
                || formTajetaFamiliar.getcBoxOcupacion().getSelectedItem().toString().compareTo("Pofesor")== 0))
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "Un Afiliado No puede registrarse como Chofer como su Ocupacion", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getcBoxOcupacion().grabFocus();
            return false;
        }

        if(EdadActual >= 7
                && formTajetaFamiliar.getcBoxEstadoActividad().isEnabled()
                && formTajetaFamiliar.getcBoxEstadoActividad().getSelectedIndex() < 0)
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "Seleccione el Estado de la Ocupacion Actual de la Persona",
                    TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getcBoxEstadoActividad().grabFocus();
            return false;
        }

        if(formTajetaFamiliar.getcBoxParentesco().getSelectedIndex() < 0)
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "Aún no ha seleccionado el parentesco de la Persona",
                    TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getcBoxParentesco().grabFocus();
            return false;
        }

        if(formTajetaFamiliar.getRbtnMasculino().isSelected())
        {
            //"Bisabuelo", "Abuelo", "Padre", "Hijo", "Hermano", "Tio", "Sobrino", "Nieto ", "Hijo adoptivo", "Padrastro", "Hijastro", "Padre Adoptivo", "Hermanastro", "Otro"
            String parentescoSeleccionado = formTajetaFamiliar.getcBoxParentesco().getSelectedItem().toString();
            if(esRegistroAfiliado &&
                    (
                    parentescoSeleccionado.compareTo("Bisabuelo") == 0
                    || parentescoSeleccionado.compareTo("Abuelo") == 0
                    || parentescoSeleccionado.compareTo("Padrastro") == 0
                    || parentescoSeleccionado.compareTo("Padre Adoptivo") == 0
                    || parentescoSeleccionado.compareTo("Padre") == 0
                    )
              )
            {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "El afiliado no puede ser registrado con ese Parentesco", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                formTajetaFamiliar.getcBoxParentesco().setSelectedIndex(-1);
                return false;
            }
            if(!esRegistroAfiliado && EdadActual <= 12 &&
                    (
                     parentescoSeleccionado.compareTo("Bisabuelo") == 0
                    || parentescoSeleccionado.compareTo("Abuelo") == 0
                    || parentescoSeleccionado.compareTo("Padrastro") == 0
                    || parentescoSeleccionado.compareTo("Padre Adoptivo") == 0
                    || parentescoSeleccionado.compareTo("Padre") == 0
                    )
              )
            {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "El Miembro familiar no puede ser registrado con ese Parentesco", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }else if(formTajetaFamiliar.getRbtnFemenino().isSelected()){
            String parentescoSeleccionado = formTajetaFamiliar.getcBoxParentesco().getSelectedItem().toString();
            if(esRegistroAfiliado &&
                    (
                    parentescoSeleccionado.compareTo("Bisabuela") == 0
                    || parentescoSeleccionado.compareTo("Abuela") == 0
                    || parentescoSeleccionado.compareTo("Madrastra") == 0
                    || parentescoSeleccionado.compareTo("Madre Adoptiva") == 0
                    || parentescoSeleccionado.compareTo("Madre") == 0
                    )
              )
            {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "El afiliado no puede ser registrado con ese Parentesco", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                formTajetaFamiliar.getcBoxParentesco().setSelectedIndex(-1);
                return false;
            }
            if(!esRegistroAfiliado && EdadActual <= 12 &&
                    (
                    parentescoSeleccionado.compareTo("Bisabuela") == 0
                    || parentescoSeleccionado.compareTo("Abuela") == 0
                    || parentescoSeleccionado.compareTo("Madrastra") == 0
                    || parentescoSeleccionado.compareTo("Madre Adoptiva") == 0
                    || parentescoSeleccionado.compareTo("Madre") == 0
                    )
              )
            {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "El Miembro familiar no puede ser registrado con ese Parentesco", TituloFormulario, JOptionPane.ERROR_MESSAGE);                
                return false;
            }
        }

        if(formTajetaFamiliar.getcBoxEventoVital().getSelectedIndex()>= 0 && formTajetaFamiliar.getcBoxEventoVital().getSelectedItem().toString().compareTo("Muertes")==0
                && formTajetaFamiliar.getcBoxCausaEventoVital().getSelectedIndex() < 0)
        {
             JOptionPane.showMessageDialog(formTajetaFamiliar, "Aún no ha seleccionado la razón que produjo el Evento Vital Muerte",
                    TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getcBoxCausaEventoVital().grabFocus();
            return false;
        }

        if(!(EdadActual >= 1 && EdadActual <=2)
                && formTajetaFamiliar.getRbtnNino().isSelected())
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "La Vacuna del Afiliado es Solo para niños entre 1 y 2 años",
                    TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getRbtnNino().setSelected(false);
            formTajetaFamiliar.getRbtnNino().grabFocus();
            return false;
        }
        if(esRegistroAfiliado && this.formTajetaFamiliar.getcBoxParentesco().getSelectedItem().toString().compareTo("Madre")==0
                && this.formTajetaFamiliar.getRbtnFemenino().isSelected()
                && this.formTajetaFamiliar.getRbtnMadre().isSelected()
                && this.formTajetaFamiliar.getcBoxEventoVital().getSelectedItem().toString().compareTo("Embarazo") == 0
                )
        {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "La Vacuna Madre es Solo para Familiares Madres Embarazadas",
                    TituloFormulario, JOptionPane.ERROR_MESSAGE);
            formTajetaFamiliar.getRbtnNino().setSelected(false);
            formTajetaFamiliar.getRbtnNino().grabFocus();
            return false;
        }

        if((EdadActual > 1) && formTajetaFamiliar.getcBoxEventoVital().getSelectedIndex() >= 0
                &&formTajetaFamiliar.getcBoxEventoVital().getSelectedItem().toString().compareTo("Nacidos Vivos")==0)
         {
            JOptionPane.showMessageDialog(formTajetaFamiliar, "No puede registrar a una persona que este evento vital ya que el mismo solo es aplicado a niños menores a 1 año");
            return false;
        }

        if(esRegistroAfiliado &&
                formTajetaFamiliar.getcBoxEventoVital().getSelectedIndex() >= 0
                && formTajetaFamiliar.getcBoxEventoVital().getSelectedItem().toString().compareTo("Muertes")==0
                && JOptionPane.showConfirmDialog(formTajetaFamiliar, "Esta a punto de registrar un Afiliado con Evento Vital 'Muerte'. ¿Dsea Continuar?",
                TituloFormulario, JOptionPane.YES_NO_OPTION)== JOptionPane.NO_OPTION
                )
        {
            return false;
        }
        return true;
    }
    public void actionPerformed(ActionEvent event) {
        String accion = event.getActionCommand();
        if(accion.compareTo("agregarOcupacion") == 0){
            GuiOcupaciones formOcupaciones =new GuiOcupaciones(formTajetaFamiliar, true);
            formOcupaciones.control.setDao(dao);
            formOcupaciones.control.onFormShown();
            formOcupaciones.setVisible(true);
            try {
                listadoOcupaciones = dao.getObjects("Ocupaciones");
            } catch (SQLException ex) {
                Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
            }
            Collections.sort(listadoOcupaciones);


            formTajetaFamiliar.getcBoxOcupacion().removeAllItems();
            for(Ocupaciones ocupacion : listadoOcupaciones)
            {
                formTajetaFamiliar.getcBoxOcupacion().addItem(ocupacion);
            }

        }

        if(accion.compareTo("ValidarAlfabetizado") == 0 && TipoOperacion != "")
        {
            try {
                formTajetaFamiliar.getDateFechaAlfabetizacion().setEnabled(formTajetaFamiliar.getCheckAlfabetizado().isSelected());
                formTajetaFamiliar.getDateFechaAlfabetizacion().setDate(formTajetaFamiliar.getCheckAlfabetizado().isSelected() ? dao.obtenerFechaHoraServidor() : null);
            } catch (SQLException ex) {
                Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(accion.compareTo("validarSexoMasculino") == 0 && TipoOperacion != "")
        {
            Object indiceSeleccionadoParentesco  = formTajetaFamiliar.getcBoxParentesco().getSelectedItem().toString();
            Object indiceSeleccionadoEventoVital  = this.formTajetaFamiliar.getcBoxEventoVital().getSelectedItem();
            
            
            formTajetaFamiliar.getcBoxParentesco().setModel(
                        new javax.swing.DefaultComboBoxModel(
                        listaParentescoMasculino));
            if(TipoOperacion == "I")
                this.formTajetaFamiliar.getcBoxParentesco().setSelectedIndex(-1);
            else if(indiceSeleccionadoParentesco != null)
            {
                int indice = -1;
                for (int i = 0; i< listaParentescoMasculino.length; i++) {
                    if(listaParentescoMasculino[i].compareTo(indiceSeleccionadoParentesco.toString()) == 0)
                    {
                        indice = i;
                        break;
                    }
                }
                this.formTajetaFamiliar.getcBoxParentesco().setSelectedIndex(indice);
            }
            cargarEventosVitales(esRegistroAfiliado ? "A" : "F");
            formTajetaFamiliar.getRbtnMadre().setEnabled(false);
            formTajetaFamiliar.getRbtnMadre().setSelected(false);
            if(TipoOperacion == "I")
                this.formTajetaFamiliar.getcBoxEventoVital().setSelectedIndex(-1);
            else
                this.formTajetaFamiliar.getcBoxEventoVital().setSelectedItem(indiceSeleccionadoEventoVital);

        }

        if(accion.compareTo("validarSexoFemenino") == 0 && TipoOperacion != "")
        {
            Object indiceSeleccionadoParentesco  = formTajetaFamiliar.getcBoxParentesco().getSelectedItem();
            Object indiceSeleccionadoEventoVital  = this.formTajetaFamiliar.getcBoxEventoVital().getSelectedItem();

            formTajetaFamiliar.getcBoxParentesco().setModel(
                        new javax.swing.DefaultComboBoxModel(
                        listaParentescoFemenino));
            if(TipoOperacion == "I")
                this.formTajetaFamiliar.getcBoxParentesco().setSelectedIndex(-1);
            else if(indiceSeleccionadoParentesco != null)
            {
                int indice = -1;
                for (int i = 0; i< listaParentescoFemenino.length; i++) {
                    if(listaParentescoFemenino[i].compareTo(indiceSeleccionadoParentesco.toString()) == 0)
                    {
                        indice = i;
                        break;
                    }
                }
                this.formTajetaFamiliar.getcBoxParentesco().setSelectedIndex(indice);
            }
            cargarEventosVitales(esRegistroAfiliado ? "A" : "F");
            formTajetaFamiliar.getRbtnMadre().setEnabled( !esRegistroAfiliado);
            if(TipoOperacion == "I")
                this.formTajetaFamiliar.getcBoxEventoVital().setSelectedIndex(-1);
            else
            {
                this.formTajetaFamiliar.getcBoxEventoVital().setSelectedItem(indiceSeleccionadoEventoVital);
            }


        }

        if(accion.compareTo("validarEventoVital") == 0 && TipoOperacion!= "")
        {
            if (formTajetaFamiliar.getcBoxEventoVital().getSelectedIndex() >= 0) {
                if (formTajetaFamiliar.getcBoxEventoVital().getSelectedItem().toString().compareTo("Muertes") == 0) {
                    formTajetaFamiliar.getcBoxCausaEventoVital().setEnabled(true);
                } else {
                    formTajetaFamiliar.getcBoxCausaEventoVital().setEnabled(false);
                }
            }

        }

        if(accion.compareTo("validarOcupacion") == 0 && TipoOperacion != "")
        {
            this.formTajetaFamiliar.getcBoxEstadoActividad().setEnabled(formTajetaFamiliar.getcBoxOcupacion().getSelectedIndex()>= 0);
            this.formTajetaFamiliar.getcBoxEstadoActividad().setSelectedIndex(-1);


        }

//        if(accion.compareTo("nuevaTarjeta") == 0)
//        {
//            GuiGeneralidades formTarjeta = new GuiGeneralidades();
////            formTarjeta.control.setConexion(conexion);
//            formTarjeta.control.setDao(dao);
////            formTarjeta.control.setUsuario(usuario);
//            formTarjeta.setVisible(true);
//
//            formTajetaFamiliar.getcBoxListaTarjetas().removeAllItems();
//            try {
//                listaTarjetas = dao.getObjects("Tarjeta", new Tarjeta());
//                for (Tarjeta tarjeta : listaTarjetas) {
//                formTajetaFamiliar.getcBoxListaTarjetas().addItem(tarjeta);
//            }
//            } catch (SQLException ex) {
//                Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
//            }
            

//        }//verSaludPublica
         if(accion.compareTo("verSaludPublica") == 0){
            try {
                if (formTajetaFamiliar.getcBoxListaTarjetas().getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(formTajetaFamiliar, "Aun no existe ninguna Tarjeta Seleccionada", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                    return;
                }
                GuiSaludPublica formSaludPublica = new GuiSaludPublica();
                formSaludPublica.control.setDao(dao);
                SaludPublica saludPublica = new SaludPublica();
                int numero_familia = Integer.parseInt(formTajetaFamiliar.getcBoxListaTarjetas().getSelectedItem().toString());
                saludPublica.setNumero_familia(numero_familia);
                saludPublica = (SaludPublica) dao.obtenerObjeto(saludPublica);
                formSaludPublica.control.onFormShown();
                if(saludPublica != null)
                {
                    formSaludPublica.control.cargarDatosSaludPublica(saludPublica);
                    formSaludPublica.control.SeleccionarPaginaEdicion(saludPublica.getNumero_familia());
                } else
                {
                    formSaludPublica.control.SeleccionarPaginaInsertar(numero_familia);
                }
                
                formSaludPublica.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
            }
         }

        if(accion.compareTo("nuevoFamiliar")==0)
        {
            if(formTajetaFamiliar.getcBoxListaTarjetas().getSelectedIndex()< 0)
            {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "No ha seleccionado el Numero de Tarjeta Familiar", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                formTajetaFamiliar.getjTabbedPaneKardexTarjeta().setSelectedComponent(formTajetaFamiliar.getPnlTabPageBusqueda());
                return;
            }
            String input = "";
            String[] tipoFamilia = new String[]{"Registrar Familiar", "Registrar Afiliado"};

            input = (String) JOptionPane.showInputDialog(formTajetaFamiliar,
                    "Seleccione el tipo de Persona que va a Registrar", TituloFormulario, JOptionPane.INFORMATION_MESSAGE,
                    null, tipoFamilia, "Registrar Afiliado");
            if(input != null)
            {
                habilitarBotonesMiembroFamilia(false, false, true, true, false, false);
                habilitarCamposMiembroFamilia(true);
                limpiarCamposMiembroFamilia();
                TipoOperacion = "N";
                this.formTajetaFamiliar.getjTabbedPaneKardexTarjeta().remove(formTajetaFamiliar.getPnlTabPageBusqueda());
                this.formTajetaFamiliar.getcBoxCausaEventoVital().setEnabled(false);
                this.formTajetaFamiliar.getcBoxCausaEventoVital().setSelectedIndex(-1);
                this.formTajetaFamiliar.getcBoxEstadoActividad().setEnabled(false);
                this.formTajetaFamiliar.getcBoxEstadoActividad().setSelectedIndex(-1);
                this.formTajetaFamiliar.getDateFechaAlfabetizacion().setEnabled(false);
                this.formTajetaFamiliar.getRbtnMadre().setSelected(false);
                this.formTajetaFamiliar.getRbtnNino().setSelected(false);
                this.formTajetaFamiliar.getRbtnMasculino().setSelected(true);
                formTajetaFamiliar.getcBoxParentesco().setModel(
                        new javax.swing.DefaultComboBoxModel(
                        listaParentescoMasculino));
                this.formTajetaFamiliar.getcBoxParentesco().setSelectedIndex(-1);

                System.out.println(input);
                if(input.compareTo("Registrar Afiliado") == 0)
                {
                    try {
                        int numeroCasoNuevo = dao.obtenerUltimoObjetoTabla(new Afiliado(), "") + 1;
                        this.formTajetaFamiliar.getTxtNroCaso().setText(String.valueOf(numeroCasoNuevo));

                        if (!dao.sePuedeAgregarAfiliado(Integer.parseInt(formTajetaFamiliar.getcBoxListaTarjetas().getSelectedItem().toString()))) {
                            JOptionPane.showMessageDialog(formTajetaFamiliar, "El registro Actual en la Tarjeta Familiar " + formTajetaFamiliar.getcBoxListaTarjetas().getSelectedItem().toString()
                                    +" no es posible, debido a que ya existen 2 Afilados registrados en la misma", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                            this.actionPerformed(new ActionEvent(formTajetaFamiliar.getBtnCancelar(), -1, "cancelarFamilia"));
                            return;
                        }
                        } catch (SQLException ex) {
                        Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        this.formTajetaFamiliar.getjTabbedPaneKardexTarjeta().setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro de un Nuevo Afiliado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
                        this.formTajetaFamiliar.getRbtnMadre().setSelected(false);
                        this.formTajetaFamiliar.getRbtnMadre().setEnabled(false);
                        this.formTajetaFamiliar.getTxtNombreCorto().setEnabled(true);
                        this.formTajetaFamiliar.getTxtNroCaso().setEnabled(true);
                        this.formTajetaFamiliar.getTxtNroNino().setEnabled(true);
                        this.formTajetaFamiliar.getRbtnNino().setSelected(false);
                        this.formTajetaFamiliar.getRbtnNino().setEnabled(true);
                        this.formTajetaFamiliar.getRbtnMasculino().setSelected(false);
//                        this.formTajetaFamiliar.getRbtnFemenino().setSelected(false);
                        esRegistroAfiliado = true;
                        cargarEventosVitales("A");
                    
                }
                
                if(input.compareTo("Registrar Familiar") == 0)
                {
                    this.formTajetaFamiliar.getjTabbedPaneKardexTarjeta().setBorder(javax.swing.BorderFactory.createTitledBorder(
                            null, "Registro de un Nuevo Familiar",
                            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
                    this.formTajetaFamiliar.getTxtNombreCorto().setEnabled(false);
                    this.formTajetaFamiliar.getTxtNroCaso().setEnabled(false);
                    this.formTajetaFamiliar.getTxtNroNino().setEnabled(false);
                    this.formTajetaFamiliar.getRbtnNino().setSelected(false);
                    this.formTajetaFamiliar.getRbtnNino().setEnabled(false);
                    this.formTajetaFamiliar.getRbtnMadre().setEnabled(false);
                    this.formTajetaFamiliar.getRbtnMadre().setSelected(false);
                    esRegistroAfiliado = false;
                    cargarEventosVitales("F");
                }
                
                this.formTajetaFamiliar.getcBoxEventoVital().setSelectedIndex(-1);
                
            }else
            {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "No  ha Seleccionado ninguna opción, se Cancelara la operación Actual", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            }

        }
        if(accion.compareTo("cancelarFamilia")==0)
        {
            int IndiceFila = formTajetaFamiliar.getjTableBusqueda().getSelectedRow();
            habilitarBotonesMiembroFamilia(true, IndiceFila >= 0 , false, false, IndiceFila >= 0, false);
            habilitarCamposMiembroFamilia(false);
            TipoOperacion = "";
            limpiarCamposMiembroFamilia();
            this.formTajetaFamiliar.getjTabbedPaneKardexTarjeta().addTab("Buscar", new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Buscar.png")),formTajetaFamiliar.getPnlTabPageBusqueda());
            if(IndiceFila >= 0)
            {
                formTajetaFamiliar.getjTableBusqueda().setRowSelectionInterval(0, 0);
                formTajetaFamiliar.getjTableBusqueda().setRowSelectionInterval(IndiceFila, IndiceFila);
            }

        }
        if(accion.compareTo("asignarPadrino")==0)
        {
            if (miembroFamiliarActual == null) {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "Aún no ha seleccionado ningúna persona para su Edición",
                        "Validación de datos", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (!(miembroFamiliarActual instanceof Afiliado)) {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "La persona seleccionada no es un Afiliado",
                        "Validación de datos", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            try {
                Afiliado afiliado;
                afiliado = (Afiliado) miembroFamiliarActual;

                GuiPatrocinadorBuscador formPatrocinadorBuscador = new GuiPatrocinadorBuscador(formTajetaFamiliar, true);
                formPatrocinadorBuscador.control.setDao(dao);
                formPatrocinadorBuscador.control.onFormShown();
                FormUtilities.centrar2(formPatrocinadorBuscador, formTajetaFamiliar);
                Casos casoAfiliado = new Casos(null, afiliado.getNumero_caso(), -1);
                if (dao.existsId(casoAfiliado)
                        ) 
                {
                    if(JOptionPane.showConfirmDialog(formTajetaFamiliar, "El Afiliado ya tiene "
                        + "Asignado un Patrocinador.¿Desea modificar su Patrocinador?", "Asignación de Padrinos",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                        return;
                    formPatrocinadorBuscador.setVisible(true);
                    if (formPatrocinadorBuscador.control.getPatrocinadorSeleccionado() != null) {
                        casoAfiliado.setCodigo_padrino(formPatrocinadorBuscador.control.getPatrocinadorSeleccionado().getCodigo_padrino());
                        dao.edit(casoAfiliado);
                        formTajetaFamiliar.getLblPadrinoAsignado().setText("Patrocinador :" + formPatrocinadorBuscador.control.getPatrocinadorSeleccionado().getNombreCompleto());
                        JOptionPane.showMessageDialog(formTajetaFamiliar,
                                "Registro actualizado correctamente", "Asignación de Padrinos",
                                JOptionPane.INFORMATION_MESSAGE);
                        afiliado.setTipo("Patrocinado");

                    } else {
                        JOptionPane.showMessageDialog(formTajetaFamiliar, "No seleccionó ningun Patrocinador",
                                "Asignación de Padrinos", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    formPatrocinadorBuscador.setVisible(true);
                    if (formPatrocinadorBuscador.control.getPatrocinadorSeleccionado() != null) {
                        casoAfiliado.setCodigo_padrino(formPatrocinadorBuscador.control.getPatrocinadorSeleccionado().getCodigo_padrino());
                        dao.insertar(casoAfiliado);
                        formTajetaFamiliar.getLblPadrinoAsignado().setText("Patrocinador :" + formPatrocinadorBuscador.control.getPatrocinadorSeleccionado().getNombreCompleto());
                        JOptionPane.showMessageDialog(formTajetaFamiliar,
                                "Registro guardado correctamente", "Asignación de Padrinos",
                                JOptionPane.INFORMATION_MESSAGE);

                        afiliado.setTipo("Patrocinado");                        
                    } else {
                        JOptionPane.showMessageDialog(formTajetaFamiliar, "No seleccionó ningun Patrocinador",
                                "Asignación de Padrinos", JOptionPane.ERROR_MESSAGE);
                    }
                }


            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "No se pudo realizar correctamente  la actualizacion el registro", "Registro de Tarjeta Familiar", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex);
            }
        }

        if(accion.compareTo("edtiarFamilia") == 0)
        {
            if(miembroFamiliarActual != null)
            {
                TipoOperacion = "E";
                habilitarBotonesMiembroFamilia(false, false, true, true, false, true);
                habilitarCamposMiembroFamilia(true);
                formTajetaFamiliar.getTxtNroCaso().setEnabled(false);
                this.formTajetaFamiliar.getTxtCi().grabFocus();
                if(!(miembroFamiliarActual instanceof Afiliado)){                    
                    formTajetaFamiliar.getTxtNroNino().setEnabled(false);
                    formTajetaFamiliar.getTxtNombreCorto().setEnabled(false);
                    this.formTajetaFamiliar.getRbtnNino().setEnabled(false);
                    this.formTajetaFamiliar.getRbtnNino().setSelected(false);
                    formTajetaFamiliar.getTxtNroCaso().grabFocus();
                    this.formTajetaFamiliar.getBtnAsignarPadrino().setEnabled(false);
                }else
                {
                    formTajetaFamiliar.getTxtNroNino().setEnabled(true);
                    formTajetaFamiliar.getTxtNombreCorto().setEnabled(true);
                }
                this.actionPerformed(new ActionEvent(formTajetaFamiliar.getRbtnMasculino(), 2, formTajetaFamiliar.getRbtnMasculino().getActionCommand()));
                cargarEventosVitales("T");
                
                
                EventosVitales eventoVital = new EventosVitales();
                eventoVital.setCodigo_evento_vital(miembroFamiliarActual.getCodigo_evento_vital());
                int indice = Collections.binarySearch(listadoEventosVitales, eventoVital);
                this.formTajetaFamiliar.getcBoxEventoVital().setSelectedIndex(indice >= 0 ? indice : -1);

                

                if(miembroFamiliarActual.getEventoVital() != null)
                {
                    indice = Collections.binarySearch(listadoEventosVitales, miembroFamiliarActual.getEventoVital());
                    formTajetaFamiliar.getcBoxEventoVital().setSelectedIndex(indice >= 0 ? indice : -1);
                }

                this.formTajetaFamiliar.getcBoxParentesco().removeAllItems();
                if(miembroFamiliarActual.getSexo().compareTo("F") == 0){
                    formTajetaFamiliar.getcBoxParentesco().setModel(
                        new javax.swing.DefaultComboBoxModel(
                        listaParentescoFemenino));
                }else{
                    formTajetaFamiliar.getcBoxParentesco().setModel(
                        new javax.swing.DefaultComboBoxModel(
                        listaParentescoMasculino));
                }
                this.formTajetaFamiliar.getcBoxParentesco().setSelectedItem(miembroFamiliarActual.getParentesco());
                if(miembroFamiliarActual.getCodigo_ocupacion() < 0)
                    this.formTajetaFamiliar.getcBoxOcupacion().setSelectedIndex(-1);
            }
        }
        if(accion.compareTo("eliminarFamilia") == 0)
        {
            if(miembroFamiliarActual != null
                    && JOptionPane.showConfirmDialog(formTajetaFamiliar,
                    "¿Se encuentra seguro de Eliminar el Registro", TituloFormulario, JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION
                    ){
                try {
                    dao.delete(miembroFamiliarActual);
                    limpiarCamposMiembroFamilia();
                    habilitarBotonesMiembroFamilia(true, false, false, false, false, false);
                    if(formTajetaFamiliar.getjTableBusqueda().getSelectedRow() >= 0)
                        modeloMiembroFamiliar.removeFamilia(formTajetaFamiliar.getjTableBusqueda().getSelectedRow());
                    JOptionPane.showMessageDialog(formTajetaFamiliar, "Operación realizada correctamente", TituloFormulario, JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(formTajetaFamiliar, "Ocurrio la Siguiente excepcion "+ex, TituloFormulario, JOptionPane.ERROR_MESSAGE);

                }               
                CopyDirectory.deleteFile(rutaLocal + nombreFotografia);
                
            }
        }

        
        if(accion.compareTo("cerrar")==0){
            if(TipoOperacion != "" &&
                    JOptionPane.showConfirmDialog(formTajetaFamiliar, "¿Se encuentra seguro de Cancelar la operacion Actual?",
                    TituloFormulario, JOptionPane.YES_NO_OPTION)== JOptionPane.NO_OPTION)
                return;
            this.formTajetaFamiliar.setVisible(false);
            this.formTajetaFamiliar.dispose();
        }

        if(accion.compareTo("aceptarFamilia")==0){
            if(formTajetaFamiliar.getcBoxListaTarjetas().getSelectedIndex() < 0)
            {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "Aún no tiene Registrado ninguna Tarjeta Familiar", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(validarDatosMiembroFamiliar())
            {
                int NumeroFamilia = Integer.parseInt(formTajetaFamiliar.getcBoxListaTarjetas().getSelectedItem().toString());
                int ci = (this.formTajetaFamiliar.getTxtCi().getText().trim().isEmpty()) ?
                    -1: Integer.parseInt(this.formTajetaFamiliar.getTxtCi().getText().trim());
                String nombresFamiliar = formTajetaFamiliar.getTxtNombres().getText().trim();
                String ap_Paterno = this.formTajetaFamiliar.getTxtPaterno().getText().trim();
                String ap_Materno = this.formTajetaFamiliar.getTxtMaterno().getText().trim();
                Date fechaNacimiento = this.formTajetaFamiliar.getDateFechaNacimiento().getDate();
                String sexo = this.formTajetaFamiliar.getRbtnFemenino().isSelected() ? "F" : "M";
                boolean conCertificado = this.formTajetaFamiliar.getCheckConCertificado().isSelected();
                boolean esAlfabetizado = this.formTajetaFamiliar.getCheckAlfabetizado().isSelected();
                Date fechaAlfabetizacion = this.formTajetaFamiliar.getDateFechaAlfabetizacion().getDate();
                ActividadEducativa actividadEducativa = this.formTajetaFamiliar.getcBoxActividadEducativa().getSelectedIndex() >= 0 ?
                        (ActividadEducativa) this.formTajetaFamiliar.getcBoxActividadEducativa().getSelectedItem() : null;
                Ocupaciones ocupacion = this.formTajetaFamiliar.getcBoxOcupacion().getSelectedIndex() >= 0 ?
                    (Ocupaciones)this.formTajetaFamiliar.getcBoxOcupacion().getSelectedItem() : null;
                String estadoOcupacion = this.formTajetaFamiliar.getcBoxEstadoActividad().getSelectedIndex() >= 0 ?
                        this.formTajetaFamiliar.getcBoxEstadoActividad().getSelectedItem().toString() : null;
                String parentesco = this.formTajetaFamiliar.getcBoxParentesco().getSelectedItem().toString();
                EventosVitales eventoVital = this.formTajetaFamiliar.getcBoxEventoVital().getSelectedIndex() >= 0 ?
                    (EventosVitales)this.formTajetaFamiliar.getcBoxEventoVital().getSelectedItem() : null;
                CausasMuerte causaEventoVitalMuerte = this.formTajetaFamiliar.getcBoxCausaEventoVital().getSelectedIndex() >= 0 ?
                    (CausasMuerte)this.formTajetaFamiliar.getcBoxCausaEventoVital().getSelectedItem() : null;
                Familia familiaRegistro = new Familia(-1, ci, nombresFamiliar, ap_Paterno, ap_Materno, parentesco,
                        sexo, fechaNacimiento, conCertificado, esAlfabetizado, fechaAlfabetizacion,
                        actividadEducativa != null ? actividadEducativa.getCodigo_actividad_educactiva() : null,
                        ocupacion!= null ? ocupacion.getCodigo_ocupacion() : -1,
                        estadoOcupacion,
                        eventoVital != null ? eventoVital.getCodigo_evento_vital() : null,
                        causaEventoVitalMuerte != null ? causaEventoVitalMuerte.getCodigo_causa_muerte() : null,
                        NumeroFamilia);                
                if (nombreFotografia!= null && !nombreFotografia.isEmpty())
			familiaRegistro.setRuta_archivo_imagen(nombreFotografia);
                familiaRegistro.setCodigo_estado_familia("H");
                Familiar miembroFamiliarRegistro = null;
                Afiliado afiliadoRegistro = null;

                //pARA LA IMAGEN
                CopyDirectory copiar = new CopyDirectory();
                if (edicionFotografia && nombreFotografia != null && rutaLocal!= null) {
                    try {
                        if (new File(rutaLocal, nombreFotografia).exists()) {
                            if (JOptionPane.YES_OPTION == JOptionPane.showInternalOptionDialog(this.formTajetaFamiliar, "" + "Existe un archivo ya registrado con el mismo nombre,  Desea Sobreescribirlo?", "Registro de Fotografia del Jugador", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null)) {
                                if (!nombreFotografia.isEmpty()
                                        && !new File(rutaLocal).isDirectory()) {
                                    CopyDirectory.deleteFile(rutaLocal
                                            + nombreFotografia);
                                }
                            } else {                                
                                JOptionPane.showMessageDialog(formTajetaFamiliar,
                                        "No se pudo Completar satisfactoriamente la Operacion de Insertado, debido a que intenta ingresar un fotografia repetida",
                                        TituloFormulario, JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                        if (new File(rutaLocal).isDirectory() && !nombreFotografia.isEmpty()) {
                            copiar.copyDirectory(new File(rutaFotografia, nombreFotografia), new File(rutaLocal, nombreFotografia));
                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    edicionFotografia = false;
                }


                try {
                    int ultimoIdFamilia = -1;
                    if (esRegistroAfiliado) {

                        int numeroCaso = Integer.parseInt(formTajetaFamiliar.getTxtNroCaso().getText());
                        int numeroNino = formTajetaFamiliar.getTxtNroNino().getText().isEmpty()? -1: Integer.parseInt(formTajetaFamiliar.getTxtNroNino().getText());
                        String nombreCorto = formTajetaFamiliar.getTxtNombreCorto().getText();
                        boolean vacunaNino = formTajetaFamiliar.getRbtnNino().isSelected();
                        afiliadoRegistro = new Afiliado(familiaRegistro, numeroCaso, "No Patrocinado", nombreCorto, vacunaNino, -1, numeroNino);
                        afiliadoRegistro.setNumero_nino(numeroNino);
                        if (TipoOperacion == "N") {
                            if(dao.VerificarExistenciaDescripcion(afiliadoRegistro))
                            {
                                JOptionPane.showMessageDialog(formTajetaFamiliar, "La persona que desea registrar, ya es parte de una Tarjeta Familiar", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            dao.insertar(afiliadoRegistro);
                            ultimoIdFamilia = dao.obtenerUltimoObjetoTabla(familiaRegistro,"");
                            afiliadoRegistro.setCodigo_persona(ultimoIdFamilia);
                        } else {
                            afiliadoRegistro.setCodigo_persona(miembroFamiliarActual.getCodigo_persona());
                            dao.edit(afiliadoRegistro);
                            if(formTajetaFamiliar.getjTableBusqueda().getSelectedRow() >= 0)
                            {
                                modeloMiembroFamiliar.editarFamilia(afiliadoRegistro, formTajetaFamiliar.getjTableBusqueda().getSelectedRow());
                            }
                        }

                        miembroFamiliarActual = afiliadoRegistro;
                    } else
                    {
                        boolean vacunaMadre = formTajetaFamiliar.getRbtnMadre().isSelected();
                        miembroFamiliarRegistro = new Familiar(familiaRegistro, vacunaMadre);
                        if (TipoOperacion == "N") {

                            if(dao.VerificarExistenciaDescripcion(miembroFamiliarRegistro))
                            {
                                JOptionPane.showMessageDialog(this.formTajetaFamiliar, "La persona que desea registrar, ya es parte de una Tarjeta Familiar",
                                        TituloFormulario, JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            
                            dao.insertar(miembroFamiliarRegistro);
                            ultimoIdFamilia = dao.obtenerUltimoObjetoTabla(familiaRegistro,"");
                            miembroFamiliarRegistro.setCodigo_persona(ultimoIdFamilia);

                            

                        } else {
                            miembroFamiliarRegistro.setCodigo_persona(miembroFamiliarActual.getCodigo_persona());
                            dao.edit(miembroFamiliarRegistro);      
                            if(formTajetaFamiliar.getjTableBusqueda().getSelectedRow() >= 0)
                                modeloMiembroFamiliar.editarFamilia(miembroFamiliarRegistro, formTajetaFamiliar.getjTableBusqueda().getSelectedRow());
                        }
                        miembroFamiliarActual = miembroFamiliarRegistro;
                    }
                    habilitarBotonesMiembroFamilia(true, true, false, false, true, esRegistroAfiliado);
                    habilitarCamposMiembroFamilia(false);
                    TipoOperacion ="";
                    JOptionPane.showMessageDialog(formTajetaFamiliar, "Operación realizada correctamente", TituloFormulario, JOptionPane.INFORMATION_MESSAGE);
                    cargarMiembroFamiliar(miembroFamiliarActual);
                    accion ="listarMiembros";
                    formTajetaFamiliar.getjTabbedPaneKardexTarjeta().setSelectedComponent(formTajetaFamiliar.getPnlTabPageDatosGenerales());
                    
                } catch (SQLException ex) {
//                    Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
                    if(JOptionPane.showConfirmDialog(formTajetaFamiliar, "No se pudo culminar satisfactoriamente la operación actual, probablmente existen campos ya registrados y se estan repitiendo"
                            +"¿Desea ver detalladamente la excepción?", TituloFormulario, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    {
                        JOptionPane.showMessageDialog(formTajetaFamiliar, "Ocurrio la siguiente excepcion " + ex.getMessage(), TituloFormulario, JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            else
            {
//                JOptionPane.showMessageDialog(formTajetaFamiliar, "Revise sus datos, ", accion, messageType);
            }
        }

        //para La Imagen
        if(accion.compareTo(formTajetaFamiliar.getBtnImagen().getActionCommand()) == 0)
        {
            edicionFotografia = true;
            String ruta = "";
            javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
            fc.setAcceptAllFileFilterUsed(false);

            fc.removeChoosableFileFilter(new ImageFilter());
            fc.addChoosableFileFilter(new ImageFilter());
            fc.setFileView(new ImageFileView());
            fc.setAccessory(new ImagePreview(fc));
            try {
                int option = fc.showOpenDialog(this.formTajetaFamiliar);
                if (option == javax.swing.JFileChooser.APPROVE_OPTION) {
                    if (fc.getSelectedFile() != null) {
                        ruta = fc.getSelectedFile().getAbsolutePath();
                        nombreFotografia = fc.getSelectedFile().getName();
                        rutaFotografia = fc.getSelectedFile().getParent();
//                        formTajetaFamiliar.getJtxtPathImagen().setText(ruta);
                        formTajetaFamiliar.getLblImagen().setIcon(
                                new ImageIcon(ruta));

                        ((ImageIcon) formTajetaFamiliar.getLblImagen().getIcon()).setImage(((ImageIcon) formTajetaFamiliar.getLblImagen().getIcon()).getImage().getScaledInstance(200, 200,
                                0));
                        formTajetaFamiliar.getLblImagen().updateUI();
                    } else {
                        // System.out.println("La seleccion ha sido cancelado"
                        // );
                    }

                }
            } catch (Exception e) {
            }
        }
        if(accion.compareTo("BuscarTarjeta") == 0)
        {
            GuiTarjetaBuscador formBuscador = new GuiTarjetaBuscador(formTajetaFamiliar, true);
            formBuscador.control.setDao(dao);
            formBuscador.control.onFormShown();
            formBuscador.setVisible(true);
            Tarjeta tarjetaSeleccionada = formBuscador.control.getTarjetaSeleccionada();
            if (tarjetaSeleccionada != null) {
                int indice = Collections.binarySearch(listaTarjetas, tarjetaSeleccionada);

                System.out.println("Tarjeta Seleccionada " + tarjetaSeleccionada.getNumero_familia() + ", indice encontrado " + indice);
                if (indice >= 0) {
                    formTajetaFamiliar.getcBoxListaTarjetas().setSelectedIndex(indice);
                }
                else{
                    JOptionPane.showMessageDialog(formBuscador, "La Familia seleccionada se encuentra dada de baja", TituloFormulario, JOptionPane.INFORMATION_MESSAGE);
                }
            }
            formBuscador.dispose();
        }

        if(accion.compareTo("CancelarTarjeta") == 0)
        {
            limpiarControlesTarjeta();
            formTajetaFamiliar.getTxtTarjetaDescripcion().setEnabled(false);
            formTajetaFamiliar.getcBoxTarjetaSeleccionarComunidad().setEnabled(false);
            formTajetaFamiliar.getTxtPlanoVivienda().setEnabled(false);
            formTajetaFamiliar.getcBoxListaTarjetas().setEnabled(true);
            boolean existenRegistros = formTajetaFamiliar.getcBoxListaTarjetas().getItemCount() > 0;
            habilitarBotonesTarjeta(true, false, false, existenRegistros, existenRegistros, true);
            TipoOperacionTarjeta = "";
            if(existenRegistros)
                formTajetaFamiliar.getcBoxListaTarjetas().setSelectedIndex(0);
//            accion = "listarMiembros";
        }

        if(accion.compareTo("listarMiembros")==0)
        {
            if(formTajetaFamiliar.getcBoxListaTarjetas().getSelectedIndex()>=0)
            {
                
                modeloMiembroFamiliar.clear();
                Familia familiaBusqueda = new Afiliado();
                familiaBusqueda.setNumero_familia(Integer.parseInt(formTajetaFamiliar.getcBoxListaTarjetas().getSelectedItem().toString()));

                try {
                    Tarjeta tarjeta = (Tarjeta) dao.obtenerObjeto(new Tarjeta(familiaBusqueda.getNumero_familia()));

                    formTajetaFamiliar.getLblNroFamilia().setText("Familia Nro: " + tarjeta.getNumero_familia());
                    formTajetaFamiliar.getLblTarjetaFechaRegistro().setText("Fecha Registro: " + df.format(tarjeta.getFecha_registro_tarjeta()));
                    formTajetaFamiliar.getcBoxTarjetaSeleccionarComunidad().setSelectedItem(tarjeta.getComunidad().trim());
                    formTajetaFamiliar.getTxtTarjetaDescripcion().setText(tarjeta.getDescripcion());
                    formTajetaFamiliar.getTxtPlanoVivienda().setText(tarjeta.getPlano_vivienda());

                    listaMiembrosFamilia = dao.getObjects("Familia", familiaBusqueda);
                    for (Familia familia : listaMiembrosFamilia) {
                        modeloMiembroFamiliar.addFamilia(familia);
                    }

                    if(listaMiembrosFamilia.size() == 0){
                        limpiarCamposECCD();
                        limpiarCamposMiembroFamilia();
                        limpiarCamposVacunas();

                        habilitarBotonesMiembroFamilia(true, false, false, false, false, false);
                        habilitarBotonesECCD(false, false, false, false, false);
                        habilitarBotonesVacuna(false, false, false, false, false);

                    }


                    if(!formTajetaFamiliar.getPnlTabPageBusqueda().isShowing())
                        formTajetaFamiliar.getjTabbedPaneKardexTarjeta().addTab("Buscar", new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Buscar.png")),formTajetaFamiliar.getPnlTabPageBusqueda());
                    formTajetaFamiliar.getjTabbedPaneKardexTarjeta().setSelectedComponent(formTajetaFamiliar.getPnlTabPageBusqueda());
                } catch (SQLException ex) {
                    Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(formTajetaFamiliar,
                            "No se pudo realizar la operación solicitada, ocurrio la siguiente excepcion " +ex.getMessage(),
                            TituloFormulario, JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if(accion.compareTo("ModificarTarjeta") == 0)
        {
            try {

                formTajetaFamiliar.getTxtTarjetaDescripcion().setEnabled(true);
                formTajetaFamiliar.getcBoxTarjetaSeleccionarComunidad().setEnabled(true);
                formTajetaFamiliar.getTxtPlanoVivienda().setEnabled(true);

                

                formTajetaFamiliar.getcBoxListaTarjetas().setEnabled(false);
                this.formTajetaFamiliar.getTxtTextoBusqueda().setEnabled(false);
                this.formTajetaFamiliar.getjTableBusqueda().setEnabled(false);
                formTajetaFamiliar.getTxtTarjetaDescripcion().grabFocus();
                TipoOperacionTarjeta = "E";
                habilitarBotonesTarjeta(false, true, true, false, false, false);
                System.out.println("Modificar");

            } catch (Exception ex) {
                Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(accion.compareTo("nuevaTarjeta") == 0)
        {
            try {
                Tarjeta tarjeta = new Tarjeta();
                int UltimoIdTarjeta = dao.obtenerUltimoObjetoTabla(tarjeta, "");
                this.formTajetaFamiliar.getLblNroFamilia().setText("Familia Nro: " + (UltimoIdTarjeta + 1));
                formTajetaFamiliar.getTxtTarjetaDescripcion().setEnabled(true);
                formTajetaFamiliar.getcBoxTarjetaSeleccionarComunidad().setEnabled(true);
                formTajetaFamiliar.getTxtPlanoVivienda().setEnabled(true);
                limpiarCamposMiembroFamilia();
                limpiarCamposECCD();
                limpiarCamposVacunas();
                habilitarBotonesECCD(false, false, false, false, false);
                habilitarBotonesMiembroFamilia(false, false, false, false, false, false);
                habilitarBotonesVacuna(false, false, false, false, false);
                habilitarCamposECCD(false);
                habilitarCamposMiembroFamilia(false);
                habilitarCamposVacunas(false);

//                this.formTajetaFamiliar.getBtnBuscar().setEnabled(false);
                habilitarBotonesTarjeta(false, true, true, false, false, false);

                formTajetaFamiliar.getcBoxListaTarjetas().setEnabled(false);
                this.formTajetaFamiliar.getTxtTextoBusqueda().setEnabled(false);                
                this.formTajetaFamiliar.getjTableBusqueda().setEnabled(false);
                formTajetaFamiliar.getTxtTarjetaDescripcion().grabFocus();
                limpiarControlesTarjeta();
                formTajetaFamiliar.getcBoxListaTarjetas().setSelectedIndex(-1);

                TipoOperacionTarjeta = "I";

            } catch (SQLException ex) {
                Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(accion.compareTo("aceptarTarjeta") ==0)
        {
            if(formTajetaFamiliar.getcBoxTarjetaSeleccionarComunidad().getSelectedIndex() <= 0)
            {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "Aún no ha seleccionado la comunidad de la Tarjeta", TituloFormulario, JOptionPane.INFORMATION_MESSAGE);
                formTajetaFamiliar.getcBoxTarjetaSeleccionarComunidad().grabFocus();
                return;
            }
            try {
                Tarjeta tarjeta  = new Tarjeta();
                if(TipoOperacionTarjeta.compareTo("I") == 0)
                {
                    int UltimoIdTarjeta = dao.obtenerUltimoObjetoTabla(tarjeta, "");
                    tarjeta = new Tarjeta(UltimoIdTarjeta + 1, Calendar.getInstance().getTime(),
                            formTajetaFamiliar.getcBoxTarjetaSeleccionarComunidad().getSelectedItem().toString(), " ", 3, " ",
                            formTajetaFamiliar.getTxtPlanoVivienda().getText(), 1);
                    tarjeta.setDescripcion(formTajetaFamiliar.getTxtTarjetaDescripcion().getText());
                    dao.insertar(tarjeta);
                    listaTarjetas.add(tarjeta);
                    Collections.sort(listaTarjetas);
                    formTajetaFamiliar.getcBoxListaTarjetas().removeAllItems();
                    for (Tarjeta tarjeta1 : listaTarjetas) {
                        formTajetaFamiliar.getcBoxListaTarjetas().addItem(tarjeta1);
                    }

                    formTajetaFamiliar.getcBoxListaTarjetas().setSelectedItem(tarjeta);
                }
                 else{
                    tarjeta = (Tarjeta)formTajetaFamiliar.getcBoxListaTarjetas().getSelectedItem();
                    tarjeta.setDescripcion(formTajetaFamiliar.getTxtTarjetaDescripcion().getText());
                    tarjeta.setComunidad(formTajetaFamiliar.getcBoxTarjetaSeleccionarComunidad().getSelectedItem().toString());
                    tarjeta.setPlano_vivienda(formTajetaFamiliar.getTxtPlanoVivienda().getText());

                    dao.edit(tarjeta);
                 }
                
                
                formTajetaFamiliar.getTxtTarjetaDescripcion().setEnabled(false);
                formTajetaFamiliar.getcBoxTarjetaSeleccionarComunidad().setEnabled(false);
                formTajetaFamiliar.getTxtPlanoVivienda().setEnabled(false);
                
                habilitarBotonesECCD(false, false, false, false, false);
                habilitarBotonesMiembroFamilia(true, false, false, false, false, false);
                habilitarBotonesVacuna(false, false, false, false, false);
                
//                this.formTajetaFamiliar.getBtnAceptarTarjeta().setEnabled(false);
//                this.formTajetaFamiliar.getBtnAceptarTarjeta().setVisible(false);
//                this.formTajetaFamiliar.getBtnNuevaTarjeta().setEnabled(true);
//                this.formTajetaFamiliar.getBtnBuscar().setEnabled(true);

                habilitarBotonesTarjeta(true, false, false, true, true, true);
                formTajetaFamiliar.getcBoxListaTarjetas().setEnabled(true);
                this.formTajetaFamiliar.getTxtTextoBusqueda().setEnabled(true);
                
                this.formTajetaFamiliar.getjTableBusqueda().setEnabled(true);
                formTajetaFamiliar.getjTabbedPaneKardexTarjeta().setSelectedComponent(formTajetaFamiliar.getPnlTabPageDatosGenerales());
                TipoOperacionTarjeta = "";
                JOptionPane.showMessageDialog(formTajetaFamiliar, "Registro Satisfactorio", TituloFormulario, JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(formTajetaFamiliar, "Ocurrio la siguiente excepcion " + ex.getMessage(), TituloFormulario, JOptionPane.ERROR_MESSAGE);
            }
        }

        if(accion.compareTo("DarBajaNuevaTarjeta") == 0
                && JOptionPane.showConfirmDialog(formTajetaFamiliar, "¿Se encuentra seguro de dar de baja a la familia actual?", TituloFormulario, JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION)
        {
            try {
                int numeroFamilia = Integer.parseInt(formTajetaFamiliar.getcBoxListaTarjetas().getSelectedItem().toString());
                Tarjeta tarjetaBaja = new Tarjeta();
                tarjetaBaja.setNumero_familia(numeroFamilia);
                tarjetaBaja.setCodigo_estado_tarjeta("B");
                dao.edit(tarjetaBaja);

                

                habilitarBotonesECCD(false, false, false, false, false);
                habilitarBotonesVacuna(false, false, false, false, false);
                habilitarBotonesMiembroFamilia(false, false, false, false, false, false);

                formTajetaFamiliar.getcBoxListaTarjetas().removeItemAt(formTajetaFamiliar.getcBoxListaTarjetas().getSelectedIndex());

                limpiarCamposECCD();
                limpiarCamposMiembroFamilia();
                limpiarCamposVacunas();
                limpiarControlesTarjeta();

            } catch (SQLException ex) {
//                Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(formTajetaFamiliar, "No se pudo concluir la operacion", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            }
        }

        //listarMiembros
        if(accion.compareTo("buscarMiembroFamiliar")==0)
        {
            try {
                if (formTajetaFamiliar.getcBoxListaTarjetas().getItemCount() == 0) {
                    JOptionPane.showMessageDialog(formTajetaFamiliar, "Aún no existe ninguna tarjeta registrada", TituloFormulario, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (formTajetaFamiliar.getTxtTextoBusqueda().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(formTajetaFamiliar, "Aún no ha ingresado ningun texto para su busqueda", TituloFormulario, JOptionPane.INFORMATION_MESSAGE);
                    formTajetaFamiliar.getTxtTextoBusqueda().grabFocus();
                    return;
                }
                Familia miembroFamiliar = new Familia();
                miembroFamiliar.setNombres(formTajetaFamiliar.getTxtTextoBusqueda().getText());
                try {
                    miembroFamiliar.setNumero_familia(Integer.parseInt(formTajetaFamiliar.getcBoxListaTarjetas().getSelectedItem().toString()));
                    miembroFamiliar.setCodigo_persona(Integer.parseInt(formTajetaFamiliar.getTxtTextoBusqueda().getText()));                    
                } catch (NumberFormatException e) {
                }
                listaMiembrosFamilia = dao.findObjects(miembroFamiliar, miembroFamiliar.getNumero_familia());
                modeloMiembroFamiliar.clear();
                for (Familia familia : listaMiembrosFamilia) {
                    modeloMiembroFamiliar.addFamilia(familia);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "Ocurrio la siguiente excepción " +ex.getMessage(), TituloFormulario, JOptionPane.ERROR_MESSAGE);
//                ex.printStackTrace();
            }
        }


        //PARA VACUNAS
        if (accion.compareTo("nuevaVacuna") == 0) {
            if (miembroFamiliarActual != null) {
                try {
                    int edad = dao.ObtenerEdadActual(miembroFamiliarActual.getFecha_nacimiento(), dao.obtenerFechaHoraServidor());
                    if(edad > 5)
                    {
                        JOptionPane.showMessageDialog(formTajetaFamiliar, "El AFiliado superar la edad de 5 años, razón por la cual no se puede registrar una vacuna", TituloFormulario, JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } catch (SQLException ex) {
//                    Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
                }
                TipoOperacionVacunas = "N";
                habilitarBotonesVacuna(false, false, true, true, false);
                habilitarCamposVacunas(true);
                limpiarCamposVacunas();
                
            }

        }

        if(accion.compareTo("editarVacuna") ==0)
        {
            TipoOperacionVacunas ="E";
            habilitarBotonesVacuna(false, false, true, true, false);
            habilitarCamposVacunas(true);
            
        }
        if(accion.compareTo("aceptarVacuna") ==0){
            if(miembroFamiliarActual == null)
            {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "Actualmente no existe ningun Afiliado seleccionado", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                return;
            }

            Vacunas2 vacunaSeleccionada = (Vacunas2) formTajetaFamiliar.getcBoxVacuna().getSelectedItem();

            if(vacunaSeleccionada == null)
            {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "Aun no ha seleccionado ninguna vacuna", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(modeloVacuna.existeVacuna(vacunaSeleccionada) && TipoOperacionVacunas == "N")
            {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "La vacuna Seleccionada ya se encuentra registrada", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(formTajetaFamiliar.getDateFechaVacuna().getDate() == null){
                JOptionPane.showMessageDialog(formTajetaFamiliar, "Aún no ha seleccionado la Fecha de la Vacuna", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                return;
            }

            Date fechaHoraActual = null;
            try {
                fechaHoraActual = dao.obtenerFechaHoraServidor();
            } catch (SQLException ex) {
//                Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
            }
            Calendar calendarioActual = Calendar.getInstance();
            calendarioActual.setTime(fechaHoraActual);
            int EdadActual = 0;
            int EdadMesesActual = 0;
            try {
                EdadActual = dao.ObtenerEdadActual(miembroFamiliarActual.getFecha_nacimiento(), fechaHoraActual);
                EdadMesesActual = dao.ObtenerEdadMeses(miembroFamiliarActual.getFecha_nacimiento(), fechaHoraActual);
            } catch (SQLException ex) {
//                Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
            }
            Calendar fechaMinima = Calendar.getInstance();
            fechaMinima.set(Calendar.YEAR, -EdadActual);

            if(formTajetaFamiliar.getDateFechaVacuna().getCalendar().after(calendarioActual)
                        || formTajetaFamiliar.getDateFechaVacuna().getCalendar().before(fechaMinima)
                        || formTajetaFamiliar.getDateFechaVacuna().getCalendar() == null
                        )
                {
                JOptionPane.showMessageDialog(formTajetaFamiliar, 
                        "La Fecha seleccionada no puede ser Mayor a la Fecha Actual, ni sobrepasar el Limite de tiempo de la Edad del Afiliado", 
                        TituloFormulario, JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(formTajetaFamiliar.getDateFechaVacuna().getDate().before(miembroFamiliarActual.getFecha_nacimiento()))
            {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "La fecha de registro de Vacuna no puede estar antes de la Fecha de Nacimiento del Afiliado",
                        TituloFormulario, JOptionPane.ERROR_MESSAGE);
                return;
            }

//            if(EdadMesesActual < vacunaSeleccionada.getEdad_meses_minima()
//                    || EdadMesesActual > vacunaSeleccionada.getEdad_meses_maxima())
//            {
//                JOptionPane.showMessageDialog(formTajetaFamiliar, "Acutalmente el afiliado tiene " + EdadMesesActual +" meses de edad, la misma no se encuentra en el Limite de tiempo de la Vacuna seleccionada (" +
//                        vacunaSeleccionada.getNombre_vacuna2() +"["+ vacunaSeleccionada.getLimiteDeMeses()+ "])", TituloFormulario, JOptionPane.ERROR_MESSAGE);
//                return;
//            }



            
            if(formTajetaFamiliar.getcBoxVacuna().getSelectedIndex() >= 0
                    && formTajetaFamiliar.getDateFechaVacuna().getDate() != null)
            {
                AfiliadoVacuna afiliadoVacuna = new AfiliadoVacuna(miembroFamiliarActual.getCodigo_persona(),
                        ((Vacunas2) formTajetaFamiliar.getcBoxVacuna().getSelectedItem()).getCodigo_vacuna2()
                        , formTajetaFamiliar.getDateFechaVacuna().getDate(),
                        formTajetaFamiliar.getTxtVacunasObservaciones().getText());
                afiliadoVacuna.setVacuna(((Vacunas2) formTajetaFamiliar.getcBoxVacuna().getSelectedItem()));
                try {
                    if (TipoOperacionVacunas == "N") {

                        dao.insertar(afiliadoVacuna);
                        modeloVacuna.addAfiliadoVacuna(afiliadoVacuna);
                        formTajetaFamiliar.getjTableVacunas().setRowSelectionInterval(modeloVacuna.getRowCount() - 1, modeloVacuna.getRowCount() - 1);

                    } else {
                        int indice = formTajetaFamiliar.getjTableVacunas().getSelectedRow();
                        if(indice >= 0)
                        {
                            modeloVacuna.editarAfiliadoVacuna(afiliadoVacuna, indice);
                        }
                        dao.edit(afiliadoVacuna);
                    }

                    JOptionPane.showMessageDialog(formTajetaFamiliar, "Operación realizada correctamente", TituloFormulario, JOptionPane.INFORMATION_MESSAGE);
                    TipoOperacionVacunas="";
                    habilitarBotonesVacuna(true, true, false, false, false);
                    habilitarCamposVacunas(false);
                } catch (SQLException ex) {
//                    Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(formTajetaFamiliar, "Ocurrio la siguiente excepcion" + ex.getMessage(), TituloFormulario, JOptionPane.ERROR_MESSAGE);
                }

            }else{
                JOptionPane.showMessageDialog(formTajetaFamiliar, "Aún no ha terminado de llenar todos los datos", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            }

        }
        if(accion.compareTo("cancelarVacuna") ==0){
            int indiceSeleccionado = formTajetaFamiliar.getjTableVacunas().getSelectedRow();
            limpiarCamposVacunas();
            habilitarBotonesVacuna(true, indiceSeleccionado >= 0, false, false, indiceSeleccionado >= 0);
            habilitarCamposVacunas(false);            
            TipoOperacionVacunas = "";
        }
        if(accion.compareTo("eliminarVacuna") ==0)
        {
            if(formTajetaFamiliar.getjTableVacunas().getSelectedRow() >= 0
                    && JOptionPane.showConfirmDialog(formTajetaFamiliar, "¿Se encuentra seguro de eliminar el registro actual?", TituloFormulario, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                try {
                    dao.delete(modeloVacuna.getAfiliadoVacuna(formTajetaFamiliar.getjTableVacunas().getSelectedRow()));
                    modeloVacuna.removeAfiliadoVacuna(formTajetaFamiliar.getjTableVacunas().getSelectedRow());
                    habilitarBotonesVacuna(true, false, false, false, false);
                } catch (SQLException ex) {
                    Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(formTajetaFamiliar, "Aún no ha seleccionado ningun elemento", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            }
        }

        //para ECCD
        if(accion.compareTo("nuevoECCD") ==0){
            try {
                    int edad = dao.ObtenerEdadActual(miembroFamiliarActual.getFecha_nacimiento(), dao.obtenerFechaHoraServidor());
                    if(edad > 5)
                    {
                        JOptionPane.showMessageDialog(formTajetaFamiliar, "El AFiliado superar la edad de 5 años, razón por la cual no se puede registrar su ECCD", TituloFormulario, JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
                }

            habilitarBotonesECCD(false, false, true, true, false);
            habilitarCamposECCD(true);
            limpiarCamposECCD();
            TipoOperacionEccd ="N";
        }
        if(accion.compareTo("editarECCD") ==0){
            habilitarBotonesECCD(false, false, true, true, false);
            habilitarCamposECCD(true);
            TipoOperacionEccd ="E";
        }
        if(accion.compareTo("aceptarECCD") ==0){
            if(validarDatosEccd())
            {
                float peso = Float.parseFloat(formTajetaFamiliar.getTxtPeso().getText());
                float talla = Float.parseFloat(formTajetaFamiliar.getTxtTalla().getText());
                String pesoTalla = formTajetaFamiliar.getTxtPesoTalla().getText();
                String seven = formTajetaFamiliar.getTxtSeven().getText();
                Date fechaHoraServidor = null;
                try {
                    fechaHoraServidor = dao.obtenerFechaHoraServidor();
                    fechaHoraServidor = formTajetaFamiliar.getDateECCDRegistro().getDate();
                    Eccd eccdRegistro = new Eccd(fechaHoraServidor, peso, talla, pesoTalla, seven, ((Afiliado)miembroFamiliarActual).getNumero_caso());
                    
                    if(TipoOperacionEccd =="N")
                    {
                        if(dao.obtenerObjeto(eccdRegistro) != null)
                        {
                            JOptionPane.showMessageDialog(formTajetaFamiliar, "No puede ingresar en la misma fecha o gestion un nuevo registro", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        dao.insertar(eccdRegistro);
                        eccdRegistro = (Eccd) dao.obtenerObjeto(eccdRegistro);
                        modeloEccd.addEccd(eccdRegistro);

                    } else{
                        dao.edit(eccdRegistro);
                        eccdRegistro = (Eccd) dao.obtenerObjeto(eccdRegistro);
                        int IndiceSeleccionado = formTajetaFamiliar.getjTableECCD().getSelectedRow();
                        if(IndiceSeleccionado>= 0)
                        {
                            modeloEccd.removeEccd(IndiceSeleccionado);
                            modeloEccd.addEccd(eccdRegistro);
                            formTajetaFamiliar.getjTableECCD().clearSelection();
                        }
                    }

                    cargarMiembroFamiliarECCD(eccdRegistro);
//                    habilitarBotonesECCD(true, false, false, false, false);
//                    habilitarCamposECCD(false);
                    TipoOperacionEccd ="";
                    JOptionPane.showMessageDialog(formTajetaFamiliar, "Operación realizada Correctamente", TituloFormulario, JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(formTajetaFamiliar, "Ocurrio la siguiente Excepcion "+ ex.getMessage(), TituloFormulario, JOptionPane.ERROR_MESSAGE);
                }
                


            }
        }
        if(accion.compareTo("cancelarECCD") ==0){
            int indiceSeleccionado = formTajetaFamiliar.getjTableECCD().getSelectedRow();
            limpiarCamposECCD();
            habilitarBotonesECCD(true, indiceSeleccionado >= 0, false, false, indiceSeleccionado >= 0);
            habilitarCamposECCD(false);
            TipoOperacionEccd = "";
        }
        if(accion.compareTo("eliminarECCD") ==0){
             if(formTajetaFamiliar.getjTableECCD().getSelectedRow() >= 0
                    && JOptionPane.showConfirmDialog(formTajetaFamiliar, "¿Se encuentra seguro de eliminar el registro actual?", TituloFormulario, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                try {
                    dao.delete(modeloEccd.getEccd(formTajetaFamiliar.getjTableECCD().getSelectedRow()));
                    modeloEccd.removeEccd(formTajetaFamiliar.getjTableECCD().getSelectedRow());
                    habilitarBotonesECCD(true, false, false, false, false);
                } catch (SQLException ex) {
                    Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(formTajetaFamiliar, "Aún no ha seleccionado ningun elemento", TituloFormulario, JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void habilitarBotonesTarjeta(boolean nuevo, boolean aceptar, boolean cancelar, boolean modificar, boolean darBaja, boolean buscar)
    {
        this.formTajetaFamiliar.getBtnNuevaTarjeta().setEnabled(nuevo);
        this.formTajetaFamiliar.getBtnAceptarTarjeta().setEnabled(aceptar);
        this.formTajetaFamiliar.getBtnCancelarTarjeta().setEnabled(cancelar);
        this.formTajetaFamiliar.getBtnDarBajaNuevaTarjeta().setEnabled(darBaja);
        this.formTajetaFamiliar.getBtnBuscarTarjeta().setEnabled(buscar);
        this.formTajetaFamiliar.getBtnModificarTarjeta().setEnabled(modificar);

    }
    public void limpiarControlesTarjeta()
    {
        this.formTajetaFamiliar.getTxtTarjetaDescripcion().setText("");
        this.formTajetaFamiliar.getcBoxTarjetaSeleccionarComunidad().setSelectedIndex(-1);
        this.formTajetaFamiliar.getTxtPlanoVivienda().setText("");
    }

    public boolean validarDatosEccd()
    {
        try {
            if (miembroFamiliarActual == null) {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "Aun no tiene seleccionado ningun Afiliado", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if(!(miembroFamiliarActual instanceof Afiliado))
            {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "La Persona actual no es un Afiliado", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (formTajetaFamiliar.getTxtPeso().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "No ha ingresado el Peso", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                formTajetaFamiliar.getTxtPeso().grabFocus();
                formTajetaFamiliar.getTxtPeso().selectAll();
                return false;
            }
            if (formTajetaFamiliar.getTxtTalla().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "No ha ingresado la Talla", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                formTajetaFamiliar.getTxtTalla().grabFocus();
                formTajetaFamiliar.getTxtTalla().selectAll();
                return false;
            }
            if(formTajetaFamiliar.getDateECCDRegistro().getDate() == null)
            {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "No ha ingresado la Fecha", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                formTajetaFamiliar.getDateECCDRegistro().grabFocus();
                return false;
            }

            Date FechaHoraSevidor = dao.obtenerFechaHoraServidor();
            int EdadActual = dao.ObtenerEdadActual(miembroFamiliarActual.getFecha_nacimiento(), FechaHoraSevidor);
            double pesoActual = Double.parseDouble(formTajetaFamiliar.getTxtPeso().getText());

            if(EdadActual > 5)
            {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "El afiliado Actual sobrespasa la Edad Minima para el seguimiento de su ECCD", TituloFormulario, JOptionPane.ERROR_MESSAGE);
                return false;
            }


            Date fechaHoraActual = null;
            try {
                fechaHoraActual = dao.obtenerFechaHoraServidor();
            } catch (SQLException ex) {
                Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
            }
            Calendar calendarioActual = Calendar.getInstance();
            calendarioActual.setTime(fechaHoraActual);

            int EdadMesesActual = 0;
            try {
                EdadMesesActual = dao.ObtenerEdadMeses(miembroFamiliarActual.getFecha_nacimiento(), fechaHoraActual);
            } catch (SQLException ex) {
                Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
            }
            Calendar fechaMinima = Calendar.getInstance();
            fechaMinima.set(Calendar.YEAR, -EdadActual);

            if(formTajetaFamiliar.getDateECCDRegistro().getCalendar().after(calendarioActual)
                        || formTajetaFamiliar.getDateECCDRegistro().getCalendar().before(fechaMinima)
                        || formTajetaFamiliar.getDateECCDRegistro().getCalendar() == null
                        )
                {
                JOptionPane.showMessageDialog(formTajetaFamiliar,
                        "La Fecha seleccionada no puede ser Mayor a la Fecha Actual, ni sobrepasar el Limite de tiempo de la Edad del Afiliado",
                        TituloFormulario, JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if(formTajetaFamiliar.getDateECCDRegistro().getDate().before(miembroFamiliarActual.getFecha_nacimiento()))
            {
                JOptionPane.showMessageDialog(formTajetaFamiliar, "La fecha de registro de ECCD no puede estar antes de la Fecha de Nacimiento del Afiliado",
                        TituloFormulario, JOptionPane.ERROR_MESSAGE);
                return false;
            }

//            if(miembroFamiliarActual != null)
//            {
//                formTajetaFamiliar.getTxtSeven().setText(dao.ObtenerPesoEdad(miembroFamiliarActual.getFecha_nacimiento(),
//                        miembroFamiliarActual.getSexo().trim(), Float.parseFloat(formTajetaFamiliar.getTxtPeso().getText())));
//                formTajetaFamiliar.getTxtPesoTalla().setText(dao.ObtenerTallaEdad(miembroFamiliarActual.getFecha_nacimiento(),
//                        miembroFamiliarActual.getSexo().trim(), Float.parseFloat(formTajetaFamiliar.getTxtPeso().getText())));
//            }
            
//            if(EdadActual >= 0 && EdadActual <= 1)
//            {
//                if((pesoActual >= 2 && pesoActual <= 3)
//                        ||(pesoActual >= 8 && pesoActual <= 10))
//                {
//                     formTajetaFamiliar.getcBoxSeven().addItem("N");
//                } else if((pesoActual < 2))
//                {
//                    formTajetaFamiliar.getcBoxSeven().addItem("DL");
//                    formTajetaFamiliar.getTxtPesoTalla().setText("5");
//                }
//
//                else if(pesoActual > 10)
//                    formTajetaFamiliar.getcBoxSeven().addItem("NS");
//            }
//
//            if(EdadActual > 1 && EdadActual <= 2)
//            {
//                if((pesoActual > 8 && pesoActual <= 10)
//                        ||(pesoActual > 10 && pesoActual <= 13))
//                {
//                     formTajetaFamiliar.getcBoxSeven().addItem("N");
//                } else if((pesoActual < 8))
//                {
//                    formTajetaFamiliar.getcBoxSeven().addItem("DL");
//                    formTajetaFamiliar.getTxtPesoTalla().setText("10");
//                }
//
//                else if(pesoActual > 13)
//                    formTajetaFamiliar.getcBoxSeven().addItem("NS");
//            }
//
//            if(EdadActual > 2 && EdadActual <= 3)
//            {
//                if((pesoActual > 10 && pesoActual <= 13)
//                        ||(pesoActual > 12 && pesoActual <= 15))
//                {
//                     formTajetaFamiliar.getcBoxSeven().addItem("N");
//                } else if((pesoActual < 10))
//                {
//                    formTajetaFamiliar.getcBoxSeven().addItem("DL");
//                    formTajetaFamiliar.getTxtPesoTalla().setText("12");
//                }
//
//                else if(pesoActual > 15)
//                    formTajetaFamiliar.getcBoxSeven().addItem("NS");
//            }
//
//            if(EdadActual > 3 && EdadActual <= 4)
//            {
//                if((pesoActual > 11 && pesoActual <= 15)
//                        ||(pesoActual > 13 && pesoActual <= 17))
//                {
//                     formTajetaFamiliar.getcBoxSeven().addItem("N");
//                } else if((pesoActual < 11))
//                {
//                    formTajetaFamiliar.getcBoxSeven().addItem("DL");
//                    formTajetaFamiliar.getTxtPesoTalla().setText("14");
//                }
//
//                else if(pesoActual > 17)
//                    formTajetaFamiliar.getcBoxSeven().addItem("NS");
//            }
//
//            if(EdadActual > 4 && EdadActual <= 5)
//            {
//                if((pesoActual > 13 && pesoActual <= 17)
//                        ||(pesoActual > 14 && pesoActual <= 19))
//                {
//                     formTajetaFamiliar.getcBoxSeven().addItem("N");
//                } else if((pesoActual < 13))
//                {
//                    formTajetaFamiliar.getcBoxSeven().addItem("DL");
//                    formTajetaFamiliar.getTxtPesoTalla().setText("16");
//                }
//
//                else if(pesoActual > 19)
//                    formTajetaFamiliar.getcBoxSeven().addItem("NS");
//            }


            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CGuiTarjetaFamiliarCompleto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return; // if you don't want to handle intermediate selections
        }
        ListSelectionModel rowSM = (ListSelectionModel) e.getSource();
        int indice = rowSM.getMinSelectionIndex();
        if (this.formTajetaFamiliar.getjTableBusqueda().isFocusOwner()) {
            
            if (indice >= 0) {
                if(modeloMiembroFamiliar.getFamilia(indice).getCodigo_estado_familia().compareTo("B") != 0)
                    cargarMiembroFamiliar(modeloMiembroFamiliar.getFamilia(indice));
                else
                {
                    JOptionPane.showMessageDialog(formTajetaFamiliar, "El miembro Familiar actual se encuentra dado de Baja", TituloFormulario, JOptionPane.INFORMATION_MESSAGE);
                    habilitarBotonesECCD(false, false, false, false, false);
                    habilitarBotonesMiembroFamilia(true, false, false, false, false, false);
                    habilitarBotonesVacuna(false, false, false, false, false);
                }
                    
            } else {
                cargarMiembroFamiliar(null);
            }        
        }

        if (this.formTajetaFamiliar.getjTableECCD().isFocusOwner()) {            
            if (indice >= 0) {
                cargarMiembroFamiliarECCD(modeloEccd.getEccd(indice));
            } else {
                cargarMiembroFamiliarECCD(null);
            }
        }

        if (this.formTajetaFamiliar.getjTableVacunas().isFocusOwner()) {
            if (indice >= 0) {
                cargarMiembroFamiliarsVacunas(modeloVacuna.getAfiliadoVacuna(indice));
            } else {
                cargarMiembroFamiliarsVacunas(null);
            }
        }
    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

        char c = e.getKeyChar();


        if (e.getSource() instanceof JTextField) {
            JTextField componente = (JTextField) e.getSource();
            if (componente.equals(formTajetaFamiliar.getTxtCi())
                    || componente.equals(formTajetaFamiliar.getTxtNroCaso())
                    || componente.equals(formTajetaFamiliar.getTxtNroNino())) {

                if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE)))) {
                    formTajetaFamiliar.getToolkit().beep();
                    e.consume();
                }
            } else if (componente.equals(formTajetaFamiliar.getTxtNombreCorto())
                    || componente.equals(formTajetaFamiliar.getTxtNombreCorto())
                    || componente.equals(formTajetaFamiliar.getTxtNombres())
                    || componente.equals(formTajetaFamiliar.getTxtPaterno())
                    || componente.equals(formTajetaFamiliar.getTxtMaterno())) {
                if (!((Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE)
                        || (c == KeyEvent.VK_SPACE)))) {
                    formTajetaFamiliar.getToolkit().beep();
                    e.consume();
                }
            }

            if (componente.equals(formTajetaFamiliar.getTxtPeso())
                    || componente.equals(formTajetaFamiliar.getTxtTalla())) {
                if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_COMMA)) || (c == KeyEvent.VK_PERIOD))) {
                    formTajetaFamiliar.getToolkit().beep();
                    e.consume();
                }
            }
        } else {
        }

        if (e.getSource().equals(formTajetaFamiliar.getcBoxOcupacion())) {
            if (c == KeyEvent.VK_ESCAPE) {
                this.formTajetaFamiliar.getcBoxOcupacion().setSelectedIndex(-1);
            }
            return;
        }
    }

    public void keyPressed(KeyEvent e) {
        
    }

    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2 
                && this.formTajetaFamiliar.getjTableBusqueda().isFocusOwner()
                && this.formTajetaFamiliar.getjTableBusqueda().getSelectedRow() != -1)
        {
            int indice = this.formTajetaFamiliar.getjTableBusqueda().getSelectedRow();            
            cargarMiembroFamiliar(modeloMiembroFamiliar.getFamilia(indice));
            formTajetaFamiliar.getjTabbedPaneKardexTarjeta().setSelectedIndex(0);
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

    public void configuracionFormulario() {
        if (usuario != null) {
            int indice = -1;
            SistemaFormularios formulario = new SistemaFormularios();
            formulario.setNombre_formulario(FormUtilities.getClassName(formTajetaFamiliar.getClass()));
            SistemaFormulariosPermisosUsuarios permisosUsuarios = new SistemaFormulariosPermisosUsuarios();
            permisosUsuarios.setSistemaFormulario(formulario);

            indice = Collections.binarySearch(usuario.getListadoInterfacesPermisos(), permisosUsuarios, new ComparadorIdFormulario());
            if (indice >= 0) {
                permisosUsuarios = usuario.getListadoInterfacesPermisos().get(indice);
                this.formTajetaFamiliar.getBtnBuscar().setVisible(permisosUsuarios.isPermitir_navegar());                
                this.formTajetaFamiliar.getTxtTextoBusqueda().setVisible(permisosUsuarios.isPermitir_navegar());

                this.formTajetaFamiliar.getBtnEliminar().setVisible(permisosUsuarios.isPemitir_eliminar());
                this.formTajetaFamiliar.getBtnNuevo().setVisible(permisosUsuarios.isPermitir_insertar());
                this.formTajetaFamiliar.getBtnEditar().setVisible(permisosUsuarios.isPermitir_editar());

            } else {
                System.out.println("no tiene permisos, nombre de la Clase " + formulario.getNombre_formulario());
            }


            formulario.setNombre_formulario("GuiEccd");            
            SistemaFormulariosPermisosUsuarios permisosECCD = new SistemaFormulariosPermisosUsuarios();
            permisosECCD.setSistemaFormulario(formulario);
            indice = Collections.binarySearch(usuario.getListadoInterfacesPermisos(), permisosECCD, new ComparadorIdFormulario());
            if (indice >= 0) {
                permisosECCD = usuario.getListadoInterfacesPermisos().get(indice);
                this.formTajetaFamiliar.getjTableECCD().setVisible(permisosECCD.isPermitir_navegar());

                this.formTajetaFamiliar.getBtnEccdEliminar().setVisible(permisosECCD.isPemitir_eliminar());
                this.formTajetaFamiliar.getBtnEccdNuevo().setVisible(permisosECCD.isPermitir_insertar());
                this.formTajetaFamiliar.getBtnEccdEditar().setVisible(permisosECCD.isPermitir_editar());

            } else {
                System.out.println("no tiene permisos, nombre de la Clase " + formulario.getNombre_formulario());
            }

            formulario.setNombre_formulario("GuiVacuna");            
            SistemaFormulariosPermisosUsuarios permisosVacuna = new SistemaFormulariosPermisosUsuarios();
            permisosVacuna.setSistemaFormulario(formulario);
            indice = Collections.binarySearch(usuario.getListadoInterfacesPermisos(), permisosVacuna, new ComparadorIdFormulario());
            if (indice >= 0) {
                permisosVacuna = usuario.getListadoInterfacesPermisos().get(indice);
                this.formTajetaFamiliar.getjTableVacunas().setVisible(permisosVacuna.isPermitir_navegar());

                this.formTajetaFamiliar.getBtnVacunaEliminar().setVisible(permisosVacuna.isPemitir_eliminar());
                this.formTajetaFamiliar.getBtnVacunaNuevo().setVisible(permisosVacuna.isPermitir_insertar());
                this.formTajetaFamiliar.getBtnVacunaEditar().setVisible(permisosVacuna.isPermitir_editar());

            } else {
                System.out.println("no tiene permisos, nombre de la Clase " + formulario.getNombre_formulario());
            }

            //"GuiTarjeta"

            formulario.setNombre_formulario("GuiTarjeta");            
            SistemaFormulariosPermisosUsuarios permisosTarjeta = new SistemaFormulariosPermisosUsuarios();
            permisosTarjeta.setSistemaFormulario(formulario);
            indice = Collections.binarySearch(usuario.getListadoInterfacesPermisos(), permisosTarjeta, new ComparadorIdFormulario());
            if (indice >= 0) {
                permisosTarjeta = usuario.getListadoInterfacesPermisos().get(indice);
//                this.formTajetaFamiliar.getjTableVacunas().setVisible(permisosUsuarios.isPermitir_navegar());

                this.formTajetaFamiliar.getBtnNuevaTarjeta().setVisible(permisosTarjeta.isPermitir_insertar());
                this.formTajetaFamiliar.getBtnAceptarTarjeta2().setVisible(permisosTarjeta.isPermitir_editar());

            } else {
                System.out.println("no tiene permisos, nombre de la Clase " + formulario.getNombre_formulario());
            }
        }
    }
  
    

}
