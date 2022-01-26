/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Afiliado;
import bo.usfx.nuevoAmanecer.model.domain.Participa;
import bo.usfx.nuevoAmanecer.model.domain.Programas;
import bo.usfx.utils.ModeloAfiliadosParticipantesProgramas;
import bo.usfx.utils.ModeloFamilia;
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
import view.GuiProgramasAfiliados;



//INTEGRANTES
public class CGuiProgramasAfiliados extends  MouseAdapter implements ActionListener {
    private GuiProgramasAfiliados formProgramas;
    private CommonDao dao;
    private List<Participa> listaAfiliadosRegistrados;
    private List<Afiliado> listaAfiliadosNuevos;
    private Programas programaActual;
    private ModeloAfiliadosParticipantesProgramas modeloParticipantes;
    private ModeloFamilia modeloAfiliados;
    public void setProgramaActual(Programas programaActual) {
        this.programaActual = programaActual;
        modeloParticipantes = new ModeloAfiliadosParticipantesProgramas();
    }

    
    public CGuiProgramasAfiliados(GuiProgramasAfiliados formProgramas) {
        this.formProgramas = formProgramas;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }


    public void actionPerformed(ActionEvent event) {
        String accion = event.getActionCommand();
        if(accion.compareTo(formProgramas.getBtnAgregarAfiliados().getActionCommand())== 0)
        {
            formProgramas.getBtnAgregarAfiliados().setVisible(false);
            formProgramas.getPnlAfiliadosNuevos().setVisible(true);
        }
        if(accion.compareTo(formProgramas.getBtnCancelar().getActionCommand())== 0)
        {
            this.formProgramas.setVisible(false);
        }
        if(accion.compareTo(formProgramas.getBtnConfirmar().getActionCommand())== 0)
        {
            try {
                List<Participa> listadoAntiguoParticipantes = dao.findObjects(new Participa(-1, programaActual.getCodigo_programa()));
                Collections.sort(listadoAntiguoParticipantes);
                for (Participa participa : modeloParticipantes.getDatos()) {
                    //si El participante del modelo no se encuentra en la bd
                    if(Collections.binarySearch(listadoAntiguoParticipantes, participa) < 0)
                    {
                        dao.insertar(participa);
                    }
                }

                for (Participa participa : listadoAntiguoParticipantes) {
                    if(!modeloParticipantes.existeAfiliado(participa))
                    {
                        dao.delete(participa);
                    }
                }
                JOptionPane.showMessageDialog(formProgramas, "Actualización correcta", "Asignación de Afiliados", JOptionPane.INFORMATION_MESSAGE);
                this.formProgramas.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(CGuiProgramasAfiliados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(accion.compareTo(formProgramas.getBtnEliminarAfiliado().getActionCommand())== 0)
        {
            if(formProgramas.getjTableAfiliadosRegistrados().getSelectedRow() < 0)
            {
                JOptionPane.showMessageDialog(formProgramas, "Aún no ha seleccionado ningun afiliado", "Asginación de Afiliados", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if(modeloParticipantes != null && modeloParticipantes.getRowCount() > 0
                    && JOptionPane.showConfirmDialog(formProgramas,
                    "¿Se encuentra seguro de eliminar el registro?", "Eliminar Afiliado", JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION)
            {
                Participa participaEliminar = modeloParticipantes.removeParticipa(formProgramas.getjTableAfiliadosRegistrados().getSelectedRow());
                try {
                    dao.delete(participaEliminar);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(formProgramas, "No se pudo eliminar el registro. Ocurrió la siguiente Excepcion " + ex.getMessage(), "Asignación de Encargados", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        }
        if(accion.compareTo("buscarAfiliado")== 0)
        {
            if(formProgramas.getTxtTextoBusqueda().getText().trim().isEmpty())
            {
                JOptionPane.showMessageDialog(formProgramas, "Aún no ha ingresado un texto de busqueda", "Gestion de Programas", JOptionPane.ERROR_MESSAGE);
                formProgramas.getTxtTextoBusqueda().selectAll();
                formProgramas.getTxtTextoBusqueda().grabFocus();
                return;
            }
            
            System.out.println("buscar Afiliado");
            Afiliado afiliado = new Afiliado();
            afiliado.setNombres(formProgramas.getTxtTextoBusqueda().getText());
            int CodigoPersona = -1;
            try {
                CodigoPersona = Integer.parseInt(formProgramas.getTxtTextoBusqueda().getText());                
            } catch (Exception e) {
            }

            afiliado.setCodigo_persona(CodigoPersona);
            
            try {
                listaAfiliadosNuevos = dao.findObjects(afiliado, programaActual.getCodigo_programa());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            modeloAfiliados.clear();
            if(listaAfiliadosNuevos != null && listaAfiliadosNuevos.size() > 0)
            {
                for (Afiliado afiliadolista : listaAfiliadosNuevos) {
                    modeloAfiliados.addFamilia(afiliadolista);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(formProgramas, "No se encontró ningún Afiliado", "Registro de Afiliados en Programas", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }


    public void onFormShown()
    {
        formProgramas.getBtnAgregarAfiliados().setVisible(true);
        formProgramas.getBtnConfirmar().setVisible(false);
        formProgramas.getPnlAfiliadosNuevos().setVisible(false);
        Participa afiliadosParticipantes = new Participa();
        afiliadosParticipantes.setCodigo_persona(-1);
        afiliadosParticipantes.setCodigo_programa(programaActual.getCodigo_programa());
        formProgramas.getLblResumenDatosPrograma().setText(programaActual.getResumenPrograma());
        modeloAfiliados = new ModeloFamilia();
        modeloParticipantes = new ModeloAfiliadosParticipantesProgramas();

        formProgramas.getjTableAfiliadosNuevos().setModel(modeloAfiliados);
        formProgramas.getjTableAfiliadosRegistrados().setModel(modeloParticipantes);
        formProgramas.getjTableAfiliadosNuevos().addMouseListener(this);
        try {
            listaAfiliadosRegistrados = dao.getObjects("Participa", afiliadosParticipantes);
            System.out.println("Listar Participantes de progra " + programaActual + ", Total = " +  listaAfiliadosRegistrados.size());
            if(listaAfiliadosRegistrados != null)
            {
                for (Participa afiliado : listaAfiliadosRegistrados) {
                    modeloParticipantes.addParticipa(afiliado);
                }
                formProgramas.getBtnEliminarAfiliado().setVisible(true);
            }
            else
            {
                formProgramas.getBtnEliminarAfiliado().setVisible(false);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getClickCount() == 2) {
            if(formProgramas.getjTableAfiliadosNuevos().isFocusOwner() &&
                    modeloAfiliados != null &&
                    modeloAfiliados.getRowCount() > 0)
            {
                Afiliado afiliadoNuevo = (Afiliado) modeloAfiliados.getFamilia(formProgramas.getjTableAfiliadosNuevos().getSelectedRow());
                Participa afiliadoParticipante = new  Participa(afiliadoNuevo.getCodigo_persona(), programaActual.getCodigo_programa());
                afiliadoParticipante.setAfiliado(afiliadoNuevo);

                if(!modeloParticipantes.existeAfiliado(afiliadoParticipante))
                {
                    modeloParticipantes.addParticipa(afiliadoParticipante);
                    formProgramas.getBtnConfirmar().setVisible(true);
                    formProgramas.getBtnConfirmar().setEnabled(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(formProgramas, "El Afiliado ya se encuentra asociado al programa", "Afiliado Repetido", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }
}
