/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Afiliado;
import bo.usfx.nuevoAmanecer.model.domain.AfiliadoVacuna;
import bo.usfx.nuevoAmanecer.model.domain.Eccd;
import bo.usfx.nuevoAmanecer.model.domain.Familia;
import bo.usfx.nuevoAmanecer.model.domain.Familiar;
import bo.usfx.nuevoAmanecer.model.domain.Tarjeta;
import bo.usfx.nuevoAmanecer.model.domain.Vacunas;
import bo.usfx.utils.ModeloAfiliadoVacunas;
import bo.usfx.utils.ModeloEccd;
import bo.usfx.utils.ModeloFamilia;
import bo.usfx.utils.ModeloTarjeta;
import bo.usfx.utils.ModeloVacunas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.GuiVerTarjetaFamiliar;


public class CGuiVerTarjetaFamiliar  extends KeyAdapter implements ActionListener, ListSelectionListener{
    GuiVerTarjetaFamiliar formTarjeta;

    ModeloTarjeta modeloTarjeta;
    ModeloFamilia modeloFamilia;
    ModeloEccd modeloEccd;
    ModeloAfiliadoVacunas modeloVacunas;

    Tarjeta tarjetaFamiliar = new Tarjeta();
    
    List<Familiar> listaMiembrosTarjeta;
    List<Eccd> listaEccd;
    List<AfiliadoVacuna> listaVacunas;
    private CommonDao dao;

    public void setDao(CommonDao dao) {
        this.dao = dao;
    }
    
    public CGuiVerTarjetaFamiliar(GuiVerTarjetaFamiliar formTarjeta)
    {
        this.formTarjeta = formTarjeta;
        modeloEccd = new ModeloEccd();
        modeloFamilia = new ModeloFamilia();
        modeloTarjeta = new ModeloTarjeta();
        modeloVacunas = new ModeloAfiliadoVacunas();
    }

    public void actionPerformed(ActionEvent event) {
        String accion = event.getActionCommand();
        if(accion.compareTo("buscar") == 0)
        {
            formTarjeta.getTxtNumeroTarjetaBusqueda().grabFocus();
            formTarjeta.getTxtNumeroTarjetaBusqueda().selectAll();
            try {
                if (formTarjeta.getTxtNumeroTarjetaBusqueda().getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(formTarjeta, "Aún no ha ingresado el Número de familia que desea buscar", "Tarjeta Familiar", JOptionPane.ERROR_MESSAGE);                    
                    return;
                }
                tarjetaFamiliar = new Tarjeta();
                try {
                    tarjetaFamiliar.setNumero_familia(Integer.parseInt(formTarjeta.getTxtNumeroTarjetaBusqueda().getText()));
                } catch (NumberFormatException e) {
                }
                tarjetaFamiliar = (Tarjeta) dao.obtenerObjeto(tarjetaFamiliar);
                modeloTarjeta.clear();
                modeloEccd.clear();
                modeloFamilia.clear();
                modeloVacunas.clear();
                if(tarjetaFamiliar != null)
                {                    
                    modeloTarjeta.addTarjeta(tarjetaFamiliar);
                    formTarjeta.getLblNroTarjeta().setText("TARJETA FAMILIAR Nro " + String.valueOf(tarjetaFamiliar.getNumero_familia()) );
                    formTarjeta.getTxtDireccion().setText(tarjetaFamiliar.getPlano_vivienda());
                    formTarjeta.getTxtObservaciones().setText(tarjetaFamiliar.getObservaciones());

                    Familia familia = new Familia();
                    familia.setCodigo_persona(-1);
                    familia.setNumero_familia(tarjetaFamiliar.getNumero_familia());
                    System.out.println("Nro de Familia " + familia.getNumero_familia());
                    listaMiembrosTarjeta = dao.getObjects("Familia", familia);
                    for (Familia miembros : listaMiembrosTarjeta) {
                        modeloFamilia.addFamilia(miembros);
                    }
                }
                 else{
                    formTarjeta.getTxtDireccion().setText("");
                    formTarjeta.getTxtObservaciones().setText("");
                    formTarjeta.getLblNroTarjeta().setText("");
                    JOptionPane.showMessageDialog(formTarjeta, "No se encontrón ningún registro con la información provista", "Tarjeta Familiar", JOptionPane.INFORMATION_MESSAGE);
                 }
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(formTarjeta, "Ocurrió la siguiente excepción " +ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
            return; // if you don't want to handle intermediate selections
        ListSelectionModel rowSM = (ListSelectionModel) e.getSource();
        
        if(this.formTarjeta.getjTableListadoMiembrosTarjeta().isFocusOwner() && rowSM.getMinSelectionIndex() >= 0)
        {
            
            //cargar los datos
            Familia familia = modeloFamilia.getFamilia(rowSM.getMinSelectionIndex());
            modeloEccd.clear();
            modeloVacunas.clear();
//            if(familia instanceof Afiliado)
//            {
            System.out.println("Value Changed " + rowSM.getMinSelectionIndex());
            try {
                Afiliado afiliado = new Afiliado();
                afiliado.setCodigo_persona(familia.getCodigo_persona());
                afiliado = (Afiliado) dao.obtenerObjeto(afiliado);
//                afiliado.setCodigo_persona(familia.getCodigo_persona());
                if (afiliado != null) {
                    System.out.println("\nNro de caso " + afiliado.getNumero_caso() + ", Codigo Persona" + afiliado.getCodigo_persona());

                    Eccd eccd = new Eccd();
                    eccd.setNumero_caso(afiliado.getNumero_caso());

                    listaEccd = dao.findObjects(eccd);
                    for (Eccd eccd2 : listaEccd) {
                        modeloEccd.addEccd(eccd2);
                    }

                    AfiliadoVacuna vacuna = new AfiliadoVacuna();
                    vacuna.setCodigo_persona(afiliado.getCodigo_persona());
                    listaVacunas = dao.findObjects(vacuna);
                    for (AfiliadoVacuna vacunitas : listaVacunas) {
                        modeloVacunas.addAfiliadoVacuna(vacunitas);
                    }
                }else{
                    System.out.println("AFiliado nulo");
                }

            } catch (SQLException ex) {
                Logger.getLogger(CGuiVerTarjetaFamiliar.class.getName()).log(Level.SEVERE, null, ex);
            }
//            }
        }
    }

    public void keyTyped(KeyEvent e) {
//        // TODO Auto-generated method stub
        JTextField componente = (JTextField) e.getSource();
        char c = e.getKeyChar();
        if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                || (c == KeyEvent.VK_DELETE)))) {
            formTarjeta.getToolkit().beep();
            e.consume();
        }

    }

    public void onFormShown()
    {
        this.formTarjeta.getjTableECCD().setModel(modeloEccd);
        this.formTarjeta.getjTableListadoMiembrosTarjeta().setModel(modeloFamilia);
        this.formTarjeta.getjTableTarjeta().setModel(modeloTarjeta);
        this.formTarjeta.getjTableVacunas().setModel(modeloVacunas);

        this.formTarjeta.getjTableListadoMiembrosTarjeta().getSelectionModel().addListSelectionListener(this);

        
    }
}
