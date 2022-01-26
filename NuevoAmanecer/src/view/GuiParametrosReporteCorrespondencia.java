/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FParametrosReporteCorrespondencia.java
 *
 * Created on 10-abr-2011, 4:20:23
 */

package view;

import bo.usfx.nuevoAmanecer.controller.CFParametrosReporteCorrespondencia;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JCheckBox;

/**
 *
 * @author Luis Molina
 */
public class GuiParametrosReporteCorrespondencia extends javax.swing.JDialog {

    public GuiParametrosReporteCorrespondencia(Frame owner, boolean modal) {
        super(owner, modal);
        control = new CFParametrosReporteCorrespondencia(this);
        initComponents();
    }



    /** Creates new form FParametrosReporteCorrespondencia */
    public GuiParametrosReporteCorrespondencia() {
        control = new CFParametrosReporteCorrespondencia(this);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlInferior = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        checSeleccionarAfiliado = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Parametros para Reporte de Correspondencia");

        pnlRangoFechas = new FPanelRangoFechas();
        getContentPane().add(pnlRangoFechas, java.awt.BorderLayout.CENTER);
        pnlInferior.setBackground(new java.awt.Color(204, 222, 235));
        pnlInferior.setPreferredSize(new java.awt.Dimension(400, 70));

        btnCancelar.setText("Cancelar");
        btnCancelar.setActionCommand("cancelar");
        btnCancelar.addActionListener(control);

        btnAceptar.setText("Aceptar");
        btnAceptar.setActionCommand("aceptar");
        btnAceptar.addActionListener(control);

        checSeleccionarAfiliado.setText("Seleccionar Afiliado");

        javax.swing.GroupLayout pnlInferiorLayout = new javax.swing.GroupLayout(pnlInferior);
        pnlInferior.setLayout(pnlInferiorLayout);
        pnlInferiorLayout.setHorizontalGroup(
            pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInferiorLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(checSeleccionarAfiliado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar)
                .addGap(19, 19, 19))
        );
        pnlInferiorLayout.setVerticalGroup(
            pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInferiorLayout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar)
                    .addComponent(checSeleccionarAfiliado))
                .addContainerGap())
        );

        getContentPane().add(pnlInferior, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiParametrosReporteCorrespondencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox checSeleccionarAfiliado;
    private javax.swing.JPanel pnlInferior;
    private view.FPanelRangoFechas pnlRangoFechas;
    // End of variables declaration//GEN-END:variables
    public CFParametrosReporteCorrespondencia control;
    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JCheckBox getChecSeleccionarAfiliado() {
        return checSeleccionarAfiliado;
    }

    public FPanelRangoFechas getPnlRangoFechas() {
        return pnlRangoFechas;
    }

    
}