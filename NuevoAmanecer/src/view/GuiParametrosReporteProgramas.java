/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FParametrosReporteProgramas.java
 *
 * Created on 10-abr-2011, 6:06:38
 */

package view;

import bo.usfx.nuevoAmanecer.controller.CFPanelSistmeaFormulariosPermisosUsuarios;
import bo.usfx.nuevoAmanecer.controller.CFParametrosReporteCorrespondencia;
import javax.swing.JButton;
import javax.swing.JRadioButton;

/**
 *
 * @author Luis Molina
 */
public class GuiParametrosReporteProgramas extends javax.swing.JDialog {

    /** Creates new form FParametrosReporteProgramas */
    public GuiParametrosReporteProgramas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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
        rBtnFinalizados = new javax.swing.JRadioButton();
        rBtnEjecucion = new javax.swing.JRadioButton();
        rBtntodos = new javax.swing.JRadioButton();

        buttonGroup1.add(rBtnEjecucion);
        buttonGroup1.add(rBtnFinalizados);
        buttonGroup1.add(rBtntodos);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Programas Sociales");

        pnlRangoFechas = new FPanelRangoFechas();
        pnlInferior.setBackground(new java.awt.Color(204, 222, 235));
        pnlInferior.setPreferredSize(new java.awt.Dimension(400, 65));

        btnCancelar.setText("Cancelar");
        btnCancelar.setActionCommand("cancelar");
        btnCancelar.addActionListener(control);

        btnAceptar.setText("Aceptar");
        btnAceptar.setActionCommand("aceptar");
        btnAceptar.addActionListener(control);

        rBtnFinalizados.setText("Finalizados");

        rBtnEjecucion.setText("En Ejecución");

        rBtntodos.setText("Todos");

        javax.swing.GroupLayout pnlInferiorLayout = new javax.swing.GroupLayout(pnlInferior);
        pnlInferior.setLayout(pnlInferiorLayout);
        pnlInferiorLayout.setHorizontalGroup(
            pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInferiorLayout.createSequentialGroup()
                .addComponent(rBtnFinalizados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rBtnEjecucion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rBtntodos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addContainerGap())
        );
        pnlInferiorLayout.setVerticalGroup(
            pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInferiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar)
                    .addComponent(rBtnFinalizados)
                    .addComponent(rBtnEjecucion)
                    .addComponent(rBtntodos))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        getContentPane().add(pnlInferior, java.awt.BorderLayout.PAGE_END);
        getContentPane().add(pnlRangoFechas, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GuiParametrosReporteProgramas dialog = new GuiParametrosReporteProgramas(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel pnlInferior;
    public FPanelRangoFechas pnlRangoFechas;
    private javax.swing.JRadioButton rBtnEjecucion;
    private javax.swing.JRadioButton rBtnFinalizados;
    private javax.swing.JRadioButton rBtntodos;
    // End of variables declaration//GEN-END:variables
    public CFParametrosReporteCorrespondencia control;

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public FPanelRangoFechas getPnlRangoFechas() {
        return pnlRangoFechas;
    }

    public JRadioButton getrBtnEjecucion() {
        return rBtnEjecucion;
    }

    public JRadioButton getrBtnFinalizados() {
        return rBtnFinalizados;
    }

    public JRadioButton getrBtntodos() {
        return rBtntodos;
    }
    

}