/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Integra;
import bo.usfx.nuevoAmanecer.model.domain.Integrantes;
import bo.usfx.nuevoAmanecer.model.domain.Participa;
import bo.usfx.nuevoAmanecer.model.domain.Programas;
import bo.usfx.utils.ModeloFamilia;
import bo.usfx.utils.ModeloIntegrantes;
import bo.usfx.utils.ModeloIntegrantesIntegraProgramas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.GuiProgramasParticipantes;


public class CGuiProgramasIntegrantes extends MouseAdapter implements ActionListener {

    private GuiProgramasParticipantes formProgramas;
    private CommonDao dao;
    private List<Integra> listaIntegrantesRegistrados;
    private List<Integrantes> listaIntegrantesNuevos;
    private Programas programaActual;
    private ModeloIntegrantesIntegraProgramas modeloParticipantes;
    private ModeloIntegrantes modeloIntegrantes;

    public void setProgramaActual(Programas programaActual) {
        this.programaActual = programaActual;
        modeloParticipantes = new ModeloIntegrantesIntegraProgramas();
    }

    public CGuiProgramasIntegrantes(GuiProgramasParticipantes formProgramas) {
        this.formProgramas = formProgramas;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    public void actionPerformed(ActionEvent event) {
        String accion = event.getActionCommand();
        if (accion.compareTo(formProgramas.getBtnAgregarParticipantes().getActionCommand()) == 0) {
            formProgramas.getBtnAgregarParticipantes().setVisible(false);
            formProgramas.getPnlAfiliadosNuevos().setVisible(true);
        }
        if (accion.compareTo(formProgramas.getBtnCancelar().getActionCommand()) == 0) {
            this.formProgramas.setVisible(false);
        }
        if (accion.compareTo(formProgramas.getBtnConfirmar().getActionCommand()) == 0) {
            try {
                List<Integra> listadoAntiguoParticipantes = dao.findObjects(new Integra(-1, programaActual.getCodigo_programa()));
                Collections.sort(listadoAntiguoParticipantes);
                for (Integra integrante : modeloParticipantes.getDatos()) {
                    //si El participante del modelo no se encuentra en la bd
                    if (Collections.binarySearch(listadoAntiguoParticipantes, integrante) < 0) {
                        dao.insertar(integrante);
                    }
                }

                for (Integra integrante : listadoAntiguoParticipantes) {
                    if (!modeloParticipantes.existeIntegrante(integrante)) {
                        dao.delete(integrante);
                    }
                }
                JOptionPane.showMessageDialog(formProgramas, "Actualización correcta", "Asignación de Integrantes", JOptionPane.INFORMATION_MESSAGE);
                this.formProgramas.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(CGuiProgramasIntegrantes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.compareTo(formProgramas.getBtnEliminarParticipante().getActionCommand()) == 0) {
            if(formProgramas.getjTableParticipantesRegistrados().getSelectedRow() < 0)
            {
                JOptionPane.showMessageDialog(formProgramas, "Aún no ha seleccionado ningun Participante", "Asginación de Participantes", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (modeloParticipantes != null && modeloParticipantes.getRowCount() > 0
                    && JOptionPane.showConfirmDialog(formProgramas,
                    "¿Se encuentra seguro de eliminar el registro?", "Eliminar Integrante", JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION) {
                Integra integraEliminar = modeloParticipantes.removeIntegra(formProgramas.getjTableParticipantesRegistrados().getSelectedRow());
                try {
                    dao.delete(integraEliminar);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(formProgramas, "No se pudo eliminar el registro. Ocurrió la siguiente Excepcion " + ex.getMessage(), "Asignación de Encargados", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        }
        if (accion.compareTo("buscarIntegrante") == 0) {
            if(formProgramas.getTxtTextoBusqueda().getText().trim().isEmpty())
            {
                JOptionPane.showMessageDialog(formProgramas, "Aún no ha ingresado un texto de busqueda", "Gestion de Programas", JOptionPane.ERROR_MESSAGE);
                formProgramas.getTxtTextoBusqueda().selectAll();
                formProgramas.getTxtTextoBusqueda().grabFocus();
                return;
            }

            System.out.println("buscar Integrante");
            Integrantes integranteBusqueda = new Integrantes();
            integranteBusqueda.setNombres(formProgramas.getTxtTextoBusqueda().getText());
            int CodigoPersona = -1;
            try {
                CodigoPersona = Integer.parseInt(formProgramas.getTxtTextoBusqueda().getText());
            } catch (Exception e) {
            }

            integranteBusqueda.setCodigo_integrante(CodigoPersona);

            try {
                listaIntegrantesNuevos = dao.findObjects(integranteBusqueda, programaActual.getCodigo_programa());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            modeloIntegrantes.clear();
            if (listaIntegrantesNuevos != null && listaIntegrantesNuevos.size() > 0) {
                for (Integrantes Integrantelista : listaIntegrantesNuevos) {
                    modeloIntegrantes.addIntegrantes(Integrantelista);
                }
            } else {
                JOptionPane.showMessageDialog(formProgramas, "No se encontró ningún Integrante", "Registro de Integrantes en Programas", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    public void onFormShown() {
        formProgramas.getBtnAgregarParticipantes().setVisible(true);
        formProgramas.getBtnConfirmar().setVisible(false);
        formProgramas.getPnlAfiliadosNuevos().setVisible(false);
        Integra IntegrantesParticipantes = new Integra();
        IntegrantesParticipantes.setCodigo_integrante(-1);
        IntegrantesParticipantes.setCodigo_programa(programaActual.getCodigo_programa());
        formProgramas.getLblResumenDatosPrograma().setText(programaActual.getResumenPrograma());
        modeloIntegrantes = new ModeloIntegrantes();
        modeloParticipantes = new ModeloIntegrantesIntegraProgramas();

        formProgramas.getjTableParticipantesNuevos().setModel(modeloIntegrantes);
        formProgramas.getjTableParticipantesRegistrados().setModel(modeloParticipantes);
        formProgramas.getjTableParticipantesNuevos().addMouseListener(this);
        try {
            listaIntegrantesRegistrados = dao.getObjects("Integra", IntegrantesParticipantes);
            System.out.println("Listar Participantes de progra " + programaActual + ", Total = " + listaIntegrantesRegistrados.size());
            if (listaIntegrantesRegistrados != null) {
                for (Integra integrantes : listaIntegrantesRegistrados) {
                    modeloParticipantes.addIntegra(integrantes);
                }
                formProgramas.getBtnEliminarParticipante().setVisible(true);
            } else {
                formProgramas.getBtnEliminarParticipante().setVisible(false);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getClickCount() == 2) {
            if (formProgramas.getjTableParticipantesNuevos().isFocusOwner()
                    && modeloIntegrantes != null
                    && modeloIntegrantes.getRowCount() > 0) {
                Integrantes IntegranteNuevo = (Integrantes) modeloIntegrantes.getIntegrantes(formProgramas.getjTableParticipantesNuevos().getSelectedRow());
                Integra IntegranteParticipante = new Integra(programaActual.getCodigo_programa(), IntegranteNuevo.getCodigo_integrante());
                IntegranteParticipante.setIntegrante(IntegranteNuevo);

                if (!modeloParticipantes.existeIntegrante(IntegranteParticipante)) {
                    modeloParticipantes.addIntegra(IntegranteParticipante);
                    formProgramas.getBtnConfirmar().setVisible(true);
                    formProgramas.getBtnConfirmar().setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(formProgramas, "El Integrante ya se encuentra asociado al programa", "Integrante Repetido", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }
}
