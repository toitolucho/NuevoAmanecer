/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Afiliado;
import bo.usfx.nuevoAmanecer.model.domain.Encargados;
import bo.usfx.nuevoAmanecer.model.domain.Imparte;
import bo.usfx.nuevoAmanecer.model.domain.Programas;
import bo.usfx.utils.ModeloEncargadosImpartidoresProgramas;
import bo.usfx.utils.ModeloEncargado;
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
import view.GuiProgramasEncargados;


public class CGuiProgramasEncargados extends  MouseAdapter implements ActionListener {
    private GuiProgramasEncargados formProgramas;
    private CommonDao dao;
    private List<Imparte> listaEncargadosRegistrados;
    private List<Encargados> listaEncargadosNuevos;
    private Programas programaActual;
    private ModeloEncargadosImpartidoresProgramas modeloEncargadosImpartidores;
    private ModeloEncargado modeloEncargados;
    public void setProgramaActual(Programas programaActual) {
        this.programaActual = programaActual;
        modeloEncargadosImpartidores = new ModeloEncargadosImpartidoresProgramas();
        modeloEncargados = new ModeloEncargado();
    }


    public CGuiProgramasEncargados(GuiProgramasEncargados formProgramas) {
        this.formProgramas = formProgramas;
    }

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }


    public void actionPerformed(ActionEvent event) {
        String accion = event.getActionCommand();
        if(accion.compareTo(formProgramas.getBtnAgregarEncargados().getActionCommand())== 0)
        {
            formProgramas.getBtnAgregarEncargados().setVisible(false);
            formProgramas.getPnlAfiliadosNuevos().setVisible(true);
        }
        if(accion.compareTo(formProgramas.getBtnCancelar().getActionCommand())== 0)
        {
            this.formProgramas.setVisible(false);
        }
        if(accion.compareTo(formProgramas.getBtnConfirmar().getActionCommand())== 0)
        {
            try {                
                List<Imparte> listadoAntiguoImpartentes = dao.findObjects(new Imparte( programaActual.getCodigo_programa(), -1));
                Collections.sort(listadoAntiguoImpartentes);
                for (Imparte impartidor : modeloEncargadosImpartidores.getDatos()) {
                    if(Collections.binarySearch(listadoAntiguoImpartentes, impartidor) < 0)
                    {                        
                        dao.insertar(impartidor);
                    }
                }

                for (Imparte impartidor : listadoAntiguoImpartentes) {
                    if(!modeloEncargadosImpartidores.existeAfiliado(impartidor))
                    {
                        dao.delete(impartidor);
                    }
                }
                JOptionPane.showMessageDialog(formProgramas, "Actualización correcta", "Asignación de Encargados", JOptionPane.INFORMATION_MESSAGE);
                this.formProgramas.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(CGuiProgramasEncargados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(accion.compareTo(formProgramas.getBtnEliminarEncargado().getActionCommand())== 0)
        {
            if(formProgramas.getjTableEncargadosRegistrados().getSelectedRow() < 0)
            {
                JOptionPane.showMessageDialog(formProgramas, "Aún no ha seleccionado ningun Encargado", "Asginación de Afiliados", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if(modeloEncargadosImpartidores != null && modeloEncargadosImpartidores.getRowCount() > 0
                    && JOptionPane.showConfirmDialog(formProgramas,
                    "¿Se encuentra seguro de eliminar el registro?", "Eliminar Encargado", JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION)
            {
                Imparte imparteEliminar = modeloEncargadosImpartidores.removeImparte(formProgramas.getjTableEncargadosRegistrados().getSelectedRow());
                try {
                    dao.delete(imparteEliminar);
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
            Encargados afiliado = new Encargados();
            afiliado.setNombres(formProgramas.getTxtTextoBusqueda().getText());
            int CodigoPersona = -1;
            try {
                CodigoPersona = Integer.parseInt(formProgramas.getTxtTextoBusqueda().getText());
            } catch (Exception e) {
                
            }

            afiliado.setCi(CodigoPersona);

            try {
                listaEncargadosNuevos = dao.findObjects(afiliado, programaActual.getCodigo_programa());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            modeloEncargados.clear();
            if(listaEncargadosNuevos != null && listaEncargadosNuevos.size() > 0)
            {
                for (Encargados encargadosNuevos : listaEncargadosNuevos) {
                    modeloEncargados.addEncargados(encargadosNuevos);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(formProgramas, "No se encontró ningún Encargado", "Registro de Encargados en Programas", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }


    public void onFormShown()
    {
        formProgramas.getBtnAgregarEncargados().setVisible(true);
        formProgramas.getBtnConfirmar().setVisible(false);
        formProgramas.getPnlAfiliadosNuevos().setVisible(false);
        Imparte EncargadosImpartentes = new Imparte();
        EncargadosImpartentes.setCi(-1);
        EncargadosImpartentes.setCodigo_programa(programaActual.getCodigo_programa());
        formProgramas.getLblResumenDatosPrograma().setText(programaActual.getResumenPrograma());
        modeloEncargados = new ModeloEncargado();
        modeloEncargadosImpartidores = new ModeloEncargadosImpartidoresProgramas();

        formProgramas.getjTableEncargadosNuevos().setModel(modeloEncargados);
        formProgramas.getjTableEncargadosRegistrados().setModel(modeloEncargadosImpartidores);
        formProgramas.getjTableEncargadosNuevos().addMouseListener(this);
        try {
            listaEncargadosRegistrados = dao.getObjects("Imparte", EncargadosImpartentes);
            System.out.println("Listar Impartentes de progra " + programaActual + ", Total = " +  listaEncargadosRegistrados.size());
            if(listaEncargadosRegistrados != null)
            {
                for (Imparte afiliado : listaEncargadosRegistrados) {
                    modeloEncargadosImpartidores.addImparte(afiliado);
                }
                formProgramas.getBtnEliminarEncargado().setVisible(true);
            }
            else
            {
                formProgramas.getBtnEliminarEncargado().setVisible(false);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getClickCount() == 2) {
            if(formProgramas.getjTableEncargadosNuevos().isFocusOwner() &&
                    modeloEncargados != null &&
                    modeloEncargados.getRowCount() > 0)
            {
                Encargados encargadoNuevo = (Encargados) modeloEncargados.getEncargados(formProgramas.getjTableEncargadosNuevos().getSelectedRow());
                Imparte afiliadoImpartente = new  Imparte(programaActual.getCodigo_programa(),encargadoNuevo.getCi());
                afiliadoImpartente.setEncargado(encargadoNuevo);

                if(!modeloEncargadosImpartidores.existeAfiliado(afiliadoImpartente))
                {
                    modeloEncargadosImpartidores.addImparte(afiliadoImpartente);
                    formProgramas.getBtnConfirmar().setVisible(true);
                    formProgramas.getBtnConfirmar().setEnabled(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(formProgramas, "El Encargado ya se encuentra asociado al programa", "Afiliado Repetido", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }
}
