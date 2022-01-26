/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FParametrosReporteAfiiados.java
 *
 * Created on 10-abr-2011, 7:14:24
 */

package view;

import bo.usfx.nuevoAmanecer.controller.CFParametrosReporteCorrespondencia;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 *
 * @author Luis Molina
 */
public class GuiParametrosReporteAfiiados extends javax.swing.JDialog {

    /** Creates new form FParametrosReporteAfiiados */
    public GuiParametrosReporteAfiiados(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        control = new CFParametrosReporteCorrespondencia(this);
        initComponents();
        lblAl.setVisible(false);
        lblDel.setVisible(false);
        jDateChooser1.setVisible(false);
        jDateChooser2.setVisible(false);
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
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        lblDel = new javax.swing.JLabel();
        lblAl = new javax.swing.JLabel();
        pnlCentral = new javax.swing.JPanel();
        rBtnAfiliadosPatrocinados = new javax.swing.JRadioButton();
        rBtnAfiliadosNoPatrocinados = new javax.swing.JRadioButton();
        rBtnRangoFechaRegistroTarjeta = new javax.swing.JRadioButton();
        rBtnRangoFechaRegistroCaso = new javax.swing.JRadioButton();
        rbtnPatrocinioCancelado = new javax.swing.JRadioButton();
        rbtnFamilias = new javax.swing.JRadioButton();
        rbtnPatrinos = new javax.swing.JRadioButton();
        rbtnListaMaestra = new javax.swing.JRadioButton();

        buttonGroup1.add(rBtnAfiliadosNoPatrocinados);
        buttonGroup1.add(rBtnAfiliadosPatrocinados);
        buttonGroup1.add(rBtnRangoFechaRegistroCaso);
        buttonGroup1.add(rBtnRangoFechaRegistroTarjeta);
        buttonGroup1.add(rbtnFamilias);
        buttonGroup1.add(rbtnPatrinos);
        buttonGroup1.add(rbtnPatrocinioCancelado);
        buttonGroup1.add(rbtnListaMaestra);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Patrocinio");

        pnlInferior.setBackground(new java.awt.Color(255, 243, 189));
        pnlInferior.setPreferredSize(new java.awt.Dimension(400, 50));

        btnCancelar.setText("Cancelar");
        btnCancelar.setActionCommand("cancelar");
        btnCancelar.addActionListener(control);

        btnAceptar.setText("Aceptar");
        btnAceptar.setActionCommand("aceptar");
        btnAceptar.addActionListener(control);

        lblDel.setText("Del");

        lblAl.setText("Al");

        javax.swing.GroupLayout pnlInferiorLayout = new javax.swing.GroupLayout(pnlInferior);
        pnlInferior.setLayout(pnlInferiorLayout);
        pnlInferiorLayout.setHorizontalGroup(
            pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInferiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAl)
                .addGap(5, 5, 5)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar)
                .addGap(19, 19, 19))
        );
        pnlInferiorLayout.setVerticalGroup(
            pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInferiorLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDel)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCancelar)
                        .addComponent(btnAceptar))
                    .addComponent(lblAl)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(pnlInferior, java.awt.BorderLayout.PAGE_END);

        pnlCentral.setBackground(new java.awt.Color(204, 222, 235));

        rBtnAfiliadosPatrocinados.setBackground(new java.awt.Color(204, 222, 235));
        rBtnAfiliadosPatrocinados.setSelected(true);
        rBtnAfiliadosPatrocinados.setText("Afiliados Patrocinados");
        rBtnAfiliadosPatrocinados.addActionListener(control);

        rBtnAfiliadosNoPatrocinados.setBackground(new java.awt.Color(204, 222, 235));
        rBtnAfiliadosNoPatrocinados.setText("Afiliados No Patrocinados");
        rBtnAfiliadosNoPatrocinados.addActionListener(control);

        rBtnRangoFechaRegistroTarjeta.setBackground(new java.awt.Color(204, 222, 235));
        rBtnRangoFechaRegistroTarjeta.setText("En Rango Fechas de Registro Tarjeta");
        rBtnRangoFechaRegistroTarjeta.setActionCommand("fechas");
        rBtnRangoFechaRegistroTarjeta.addActionListener(control);

        rBtnRangoFechaRegistroCaso.setBackground(new java.awt.Color(204, 222, 235));
        rBtnRangoFechaRegistroCaso.setText("En Rango Fechas de Registro de Caso");
        rBtnRangoFechaRegistroCaso.setActionCommand("fechas");
        rBtnRangoFechaRegistroCaso.addActionListener(control);

        rbtnPatrocinioCancelado.setBackground(new java.awt.Color(204, 222, 235));
        rbtnPatrocinioCancelado.setText("Afiliados con Patrocinio Cancelado");
        rbtnPatrocinioCancelado.addActionListener(control);

        rbtnFamilias.setBackground(new java.awt.Color(204, 222, 235));
        rbtnFamilias.setText("Familias");
        rbtnFamilias.addActionListener(control);

        rbtnPatrinos.setBackground(new java.awt.Color(204, 222, 235));
        rbtnPatrinos.setText("Padrinos");
        rbtnPatrinos.addActionListener(control);

        rbtnListaMaestra.setBackground(new java.awt.Color(204, 222, 235));
        rbtnListaMaestra.setText("Lista Maestra");
        rbtnListaMaestra.addActionListener(control);

        javax.swing.GroupLayout pnlCentralLayout = new javax.swing.GroupLayout(pnlCentral);
        pnlCentral.setLayout(pnlCentralLayout);
        pnlCentralLayout.setHorizontalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rBtnAfiliadosPatrocinados)
                    .addComponent(rBtnAfiliadosNoPatrocinados)
                    .addComponent(rBtnRangoFechaRegistroTarjeta)
                    .addComponent(rBtnRangoFechaRegistroCaso)
                    .addComponent(rbtnPatrocinioCancelado)
                    .addComponent(rbtnFamilias)
                    .addComponent(rbtnPatrinos)
                    .addComponent(rbtnListaMaestra))
                .addContainerGap(194, Short.MAX_VALUE))
        );
        pnlCentralLayout.setVerticalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rBtnAfiliadosPatrocinados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rBtnAfiliadosNoPatrocinados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rBtnRangoFechaRegistroTarjeta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rBtnRangoFechaRegistroCaso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtnPatrocinioCancelado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtnFamilias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtnPatrinos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtnListaMaestra)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnlCentral, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GuiParametrosReporteAfiiados dialog = new GuiParametrosReporteAfiiados(new javax.swing.JFrame(), true);
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
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel lblAl;
    private javax.swing.JLabel lblDel;
    private javax.swing.JPanel pnlCentral;
    private javax.swing.JPanel pnlInferior;
    private javax.swing.JRadioButton rBtnAfiliadosNoPatrocinados;
    private javax.swing.JRadioButton rBtnAfiliadosPatrocinados;
    private javax.swing.JRadioButton rBtnRangoFechaRegistroCaso;
    private javax.swing.JRadioButton rBtnRangoFechaRegistroTarjeta;
    private javax.swing.JRadioButton rbtnFamilias;
    private javax.swing.JRadioButton rbtnListaMaestra;
    private javax.swing.JRadioButton rbtnPatrinos;
    private javax.swing.JRadioButton rbtnPatrocinioCancelado;
    // End of variables declaration//GEN-END:variables
    public CFParametrosReporteCorrespondencia control;

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JDateChooser getjDateChooser1() {
        return jDateChooser1;
    }

    public JDateChooser getjDateChooser2() {
        return jDateChooser2;
    }

    public JLabel getLblAl() {
        return lblAl;
    }

    public JLabel getLblDel() {
        return lblDel;
    }

    public JRadioButton getrBtnAfiliadosNoPatrocinados() {
        return rBtnAfiliadosNoPatrocinados;
    }

    public JRadioButton getrBtnAfiliadosPatrocinados() {
        return rBtnAfiliadosPatrocinados;
    }

    public JRadioButton getrBtnRangoFechaRegistroCaso() {
        return rBtnRangoFechaRegistroCaso;
    }

    public JRadioButton getrBtnRangoFechaRegistroTarjeta() {
        return rBtnRangoFechaRegistroTarjeta;
    }

    public JRadioButton getRbtnFamilias() {
        return rbtnFamilias;
    }

    public JRadioButton getRbtnPatrinos() {
        return rbtnPatrinos;
    }

    public JRadioButton getRbtnPatrocinioCancelado() {
        return rbtnPatrocinioCancelado;
    }

    public JRadioButton getRbtnListaMaestra() {
        return rbtnListaMaestra;
    }


}