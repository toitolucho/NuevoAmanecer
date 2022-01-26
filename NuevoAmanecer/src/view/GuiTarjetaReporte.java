/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GuiTarjetaReporte.java
 *
 * Created on 31-may-2011, 3:35:17
 */

package view;

import bo.usfx.nuevoAmanecer.controller.CGuiTarjetaReporte;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

/**
 *
 * @author Luis Molina
 */
public class GuiTarjetaReporte extends javax.swing.JDialog {

    /** Creates new form GuiTarjetaReporte */
    public GuiTarjetaReporte(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        control = new CGuiTarjetaReporte(this);
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
        pnlBotones = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlOpciones = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        rbtnConEDA = new javax.swing.JRadioButton();
        rbtnSinEDA = new javax.swing.JRadioButton();
        rbtnEDATodas = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        rbtnConExcretas = new javax.swing.JRadioButton();
        rbtnSinExcretas = new javax.swing.JRadioButton();
        rbtnExcretasTodas = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        rbtnConIRA = new javax.swing.JRadioButton();
        rbtnSinIRA = new javax.swing.JRadioButton();
        rbtnIRATodas = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        rbtnConAgua = new javax.swing.JRadioButton();
        rbtnSinAgua = new javax.swing.JRadioButton();
        rbtnAguaTodas = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        rbtnConCocina = new javax.swing.JRadioButton();
        rbtnSinCocina = new javax.swing.JRadioButton();
        rbtnCocinaTodas = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        rbtnPorVivienda = new javax.swing.JRadioButton();
        cboxPorVivienda = new javax.swing.JComboBox();
        checkPorVivienda = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        rbtnPorTipoVivienda = new javax.swing.JRadioButton();
        cboxPorTipoVivienda = new javax.swing.JComboBox();
        checkPorTipoVivienda = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        rbtnPorMaterialVivienda = new javax.swing.JRadioButton();
        cboxPorMaterialVivienda = new javax.swing.JComboBox();
        checkPorMaterialVivienda = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Salud Publicca Detalle");

        pnlBotones.setBackground(new java.awt.Color(255, 243, 189));
        pnlBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlBotones.setPreferredSize(new java.awt.Dimension(400, 40));
        pnlBotones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnAceptar.setText("Aceptar");
        btnAceptar.setActionCommand("aceptar");
        btnAceptar.addActionListener(control);
        pnlBotones.add(btnAceptar);

        btnCancelar.setText("Cancelar");
        btnCancelar.setActionCommand("cancelar");
        btnCancelar.addActionListener(control);
        pnlBotones.add(btnCancelar);

        getContentPane().add(pnlBotones, java.awt.BorderLayout.PAGE_END);

        pnlOpciones.setBackground(new java.awt.Color(204, 222, 235));
        pnlOpciones.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Seleccione una Opcion"));
        pnlOpciones.setLayout(new java.awt.GridLayout(8, 0));

        jPanel1.setBackground(new java.awt.Color(204, 222, 235));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        rbtnConEDA.setBackground(new java.awt.Color(204, 222, 235));
        buttonGroup1.add(rbtnConEDA);
        rbtnConEDA.setText("Con EDA");
        rbtnConEDA.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel1.add(rbtnConEDA);

        rbtnSinEDA.setBackground(new java.awt.Color(204, 222, 235));
        buttonGroup1.add(rbtnSinEDA);
        rbtnSinEDA.setText("Sin EDA");
        rbtnSinEDA.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel1.add(rbtnSinEDA);

        rbtnEDATodas.setBackground(new java.awt.Color(204, 222, 235));
        buttonGroup1.add(rbtnEDATodas);
        rbtnEDATodas.setText("Todos");
        jPanel1.add(rbtnEDATodas);

        pnlOpciones.add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(204, 222, 235));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        rbtnConExcretas.setBackground(new java.awt.Color(204, 222, 235));
        buttonGroup1.add(rbtnConExcretas);
        rbtnConExcretas.setText("Con Excretas");
        rbtnConExcretas.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel2.add(rbtnConExcretas);

        rbtnSinExcretas.setBackground(new java.awt.Color(204, 222, 235));
        buttonGroup1.add(rbtnSinExcretas);
        rbtnSinExcretas.setText("Sin Excretas");
        rbtnSinExcretas.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel2.add(rbtnSinExcretas);

        rbtnExcretasTodas.setBackground(new java.awt.Color(204, 222, 235));
        buttonGroup1.add(rbtnExcretasTodas);
        rbtnExcretasTodas.setText("Todos");
        jPanel2.add(rbtnExcretasTodas);

        pnlOpciones.add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(204, 222, 235));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        rbtnConIRA.setBackground(new java.awt.Color(204, 222, 235));
        buttonGroup1.add(rbtnConIRA);
        rbtnConIRA.setText("Con Ira");
        rbtnConIRA.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel3.add(rbtnConIRA);

        rbtnSinIRA.setBackground(new java.awt.Color(204, 222, 235));
        buttonGroup1.add(rbtnSinIRA);
        rbtnSinIRA.setText("Sin Ira");
        rbtnSinIRA.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel3.add(rbtnSinIRA);

        rbtnIRATodas.setBackground(new java.awt.Color(204, 222, 235));
        buttonGroup1.add(rbtnIRATodas);
        rbtnIRATodas.setText("Todos");
        jPanel3.add(rbtnIRATodas);

        pnlOpciones.add(jPanel3);

        jPanel4.setBackground(new java.awt.Color(204, 222, 235));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        rbtnConAgua.setBackground(new java.awt.Color(204, 222, 235));
        buttonGroup1.add(rbtnConAgua);
        rbtnConAgua.setText("Con Agua");
        rbtnConAgua.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel4.add(rbtnConAgua);

        rbtnSinAgua.setBackground(new java.awt.Color(204, 222, 235));
        buttonGroup1.add(rbtnSinAgua);
        rbtnSinAgua.setText("Sin Agua");
        rbtnSinAgua.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel4.add(rbtnSinAgua);

        rbtnAguaTodas.setBackground(new java.awt.Color(204, 222, 235));
        buttonGroup1.add(rbtnAguaTodas);
        rbtnAguaTodas.setText("Todos");
        jPanel4.add(rbtnAguaTodas);

        pnlOpciones.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(204, 222, 235));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        rbtnConCocina.setBackground(new java.awt.Color(204, 222, 235));
        buttonGroup1.add(rbtnConCocina);
        rbtnConCocina.setText("Con Cocina");
        rbtnConCocina.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel5.add(rbtnConCocina);

        rbtnSinCocina.setBackground(new java.awt.Color(204, 222, 235));
        buttonGroup1.add(rbtnSinCocina);
        rbtnSinCocina.setText("Sin Cocina");
        rbtnSinCocina.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel5.add(rbtnSinCocina);

        rbtnCocinaTodas.setBackground(new java.awt.Color(204, 222, 235));
        buttonGroup1.add(rbtnCocinaTodas);
        rbtnCocinaTodas.setText("Todos");
        jPanel5.add(rbtnCocinaTodas);

        pnlOpciones.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(204, 222, 235));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        rbtnPorVivienda.setBackground(new java.awt.Color(204, 222, 235));
        buttonGroup1.add(rbtnPorVivienda);
        rbtnPorVivienda.setText("Por Vivienda");
        rbtnPorVivienda.setPreferredSize(new java.awt.Dimension(150, 23));
        rbtnPorVivienda.setActionCommand("PorVivienda");
        rbtnPorVivienda.addActionListener(control);
        jPanel6.add(rbtnPorVivienda);

        cboxPorVivienda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Propia", "Alquilada", "Anticretico", "Mixto", "Cedida por servicio", "Parentesco", "Otras" }));
        cboxPorVivienda.setPreferredSize(new java.awt.Dimension(120, 20));
        jPanel6.add(cboxPorVivienda);

        checkPorVivienda.setBackground(new java.awt.Color(204, 222, 235));
        checkPorVivienda.setText("Todos");
        checkPorVivienda.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel6.add(checkPorVivienda);

        pnlOpciones.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(204, 222, 235));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        rbtnPorTipoVivienda.setBackground(new java.awt.Color(204, 222, 235));
        buttonGroup1.add(rbtnPorTipoVivienda);
        rbtnPorTipoVivienda.setText("Por Tipo de Vivienda");
        rbtnPorTipoVivienda.setPreferredSize(new java.awt.Dimension(150, 23));
        rbtnPorTipoVivienda.setActionCommand("PorTipoVivienda");
        rbtnPorTipoVivienda.addActionListener(control);
        jPanel7.add(rbtnPorTipoVivienda);

        cboxPorTipoVivienda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Casa", "Habitación", "Depto.", "Choza", "Otro" }));
        cboxPorTipoVivienda.setPreferredSize(new java.awt.Dimension(120, 20));
        jPanel7.add(cboxPorTipoVivienda);

        checkPorTipoVivienda.setBackground(new java.awt.Color(204, 222, 235));
        checkPorTipoVivienda.setText("Todos");
        checkPorTipoVivienda.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel7.add(checkPorTipoVivienda);

        pnlOpciones.add(jPanel7);

        jPanel8.setBackground(new java.awt.Color(204, 222, 235));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        rbtnPorMaterialVivienda.setBackground(new java.awt.Color(204, 222, 235));
        buttonGroup1.add(rbtnPorMaterialVivienda);
        rbtnPorMaterialVivienda.setText("Por Material de Vivienda");
        rbtnPorMaterialVivienda.setPreferredSize(new java.awt.Dimension(150, 23));
        rbtnPorMaterialVivienda.setActionCommand("PorMaterialVivienda");
        rbtnPorMaterialVivienda.addActionListener(control);
        jPanel8.add(rbtnPorMaterialVivienda);

        cboxPorMaterialVivienda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Ladrillo", "Adobe", "Adobe rebocado", "Piedra", "Madera", "Carton", "Otros" }));
        cboxPorMaterialVivienda.setPreferredSize(new java.awt.Dimension(120, 20));
        jPanel8.add(cboxPorMaterialVivienda);

        checkPorMaterialVivienda.setBackground(new java.awt.Color(204, 222, 235));
        checkPorMaterialVivienda.setText("Todos");
        checkPorMaterialVivienda.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel8.add(checkPorMaterialVivienda);

        pnlOpciones.add(jPanel8);

        getContentPane().add(pnlOpciones, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GuiTarjetaReporte dialog = new GuiTarjetaReporte(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox cboxPorMaterialVivienda;
    private javax.swing.JComboBox cboxPorTipoVivienda;
    private javax.swing.JComboBox cboxPorVivienda;
    private javax.swing.JCheckBox checkPorMaterialVivienda;
    private javax.swing.JCheckBox checkPorTipoVivienda;
    private javax.swing.JCheckBox checkPorVivienda;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlOpciones;
    private javax.swing.JRadioButton rbtnAguaTodas;
    private javax.swing.JRadioButton rbtnCocinaTodas;
    private javax.swing.JRadioButton rbtnConAgua;
    private javax.swing.JRadioButton rbtnConCocina;
    private javax.swing.JRadioButton rbtnConEDA;
    private javax.swing.JRadioButton rbtnConExcretas;
    private javax.swing.JRadioButton rbtnConIRA;
    private javax.swing.JRadioButton rbtnEDATodas;
    private javax.swing.JRadioButton rbtnExcretasTodas;
    private javax.swing.JRadioButton rbtnIRATodas;
    private javax.swing.JRadioButton rbtnPorMaterialVivienda;
    private javax.swing.JRadioButton rbtnPorTipoVivienda;
    private javax.swing.JRadioButton rbtnPorVivienda;
    private javax.swing.JRadioButton rbtnSinAgua;
    private javax.swing.JRadioButton rbtnSinCocina;
    private javax.swing.JRadioButton rbtnSinEDA;
    private javax.swing.JRadioButton rbtnSinExcretas;
    private javax.swing.JRadioButton rbtnSinIRA;
    // End of variables declaration//GEN-END:variables
    public CGuiTarjetaReporte control;

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JComboBox getCboxPorMaterialVivienda() {
        return cboxPorMaterialVivienda;
    }

    public JComboBox getCboxPorTipoVivienda() {
        return cboxPorTipoVivienda;
    }

    public JComboBox getCboxPorVivienda() {
        return cboxPorVivienda;
    }

    public JCheckBox getCheckPorMaterialVivienda() {
        return checkPorMaterialVivienda;
    }

    public JCheckBox getCheckPorTipoVivienda() {
        return checkPorTipoVivienda;
    }

    public JCheckBox getCheckPorVivienda() {
        return checkPorVivienda;
    }

    public JRadioButton getRbtnConAgua() {
        return rbtnConAgua;
    }

    public JRadioButton getRbtnConCocina() {
        return rbtnConCocina;
    }

    public JRadioButton getRbtnConEDA() {
        return rbtnConEDA;
    }

    public JRadioButton getRbtnConExcretas() {
        return rbtnConExcretas;
    }

    public JRadioButton getRbtnConIRA() {
        return rbtnConIRA;
    }

    public JRadioButton getRbtnPorMaterialVivienda() {
        return rbtnPorMaterialVivienda;
    }

    public JRadioButton getRbtnPorTipoVivienda() {
        return rbtnPorTipoVivienda;
    }

    public JRadioButton getRbtnPorVivienda() {
        return rbtnPorVivienda;
    }

    public JRadioButton getRbtnSinAgua() {
        return rbtnSinAgua;
    }

    public JRadioButton getRbtnSinCocina() {
        return rbtnSinCocina;
    }

    public JRadioButton getRbtnSinEDA() {
        return rbtnSinEDA;
    }

    public JRadioButton getRbtnSinExcretas() {
        return rbtnSinExcretas;
    }

    public JRadioButton getRbtnSinIRA() {
        return rbtnSinIRA;
    }

    public JRadioButton getRbtnAguaTodas() {
        return rbtnAguaTodas;
    }

    public JRadioButton getRbtnCocinaTodas() {
        return rbtnCocinaTodas;
    }

    public JRadioButton getRbtnEDATodas() {
        return rbtnEDATodas;
    }

    public JRadioButton getRbtnExcretasTodas() {
        return rbtnExcretasTodas;
    }

    public JRadioButton getRbtnIRATodas() {
        return rbtnIRATodas;
    }



    
}