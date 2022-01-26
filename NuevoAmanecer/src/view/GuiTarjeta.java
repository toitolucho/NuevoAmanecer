/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import bo.usfx.nuevoAmanecer.controller.CGuiTarjeta;
import com.toedter.calendar.JCalendar;
import java.awt.Cursor;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author Silvana
 */
public class GuiTarjeta extends javax.swing.JFrame {

    /** Creates new form GUIGestion */
    public GuiTarjeta() {
        control = new CGuiTarjeta(this);
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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnECCD = new javax.swing.JToggleButton();
        btnVacuna = new javax.swing.JToggleButton();
        btnSaludPublica = new javax.swing.JToggleButton();
        btnFamilia = new javax.swing.JToggleButton();
        btnGeneralidades = new javax.swing.JToggleButton();
        btnObservaciones = new javax.swing.JToggleButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnDarBajaTarjetaFamiliar = new javax.swing.JButton();
        btnDarBajaAfiliado = new javax.swing.JButton();
        btnDarBajaFamiliar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnVerTarjetaFamiliar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 222, 235));

        jPanel2.setBackground(new java.awt.Color(204, 222, 235));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        btnECCD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Eccd.png"))); // NOI18N
        btnECCD.setText(" ECCD");
        btnECCD.setMaximumSize(new java.awt.Dimension(135, 25));
        btnECCD.setMinimumSize(new java.awt.Dimension(135, 25));
        btnECCD.setPreferredSize(new java.awt.Dimension(135, 25));
        btnECCD.setActionCommand("eccd");
        btnECCD.addActionListener(control);
        btnECCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnECCDActionPerformed(evt);
            }
        });

        btnVacuna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Vacuna.png"))); // NOI18N
        btnVacuna.setText(" VACUNA");
        btnVacuna.setMaximumSize(new java.awt.Dimension(135, 25));
        btnVacuna.setMinimumSize(new java.awt.Dimension(135, 25));
        btnVacuna.setPreferredSize(new java.awt.Dimension(135, 25));
        btnVacuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVacunaActionPerformed(evt);
            }
        });
        btnVacuna.setActionCommand("vacuna");
        btnVacuna.addActionListener(control);

        btnSaludPublica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/SaludPublica.png"))); // NOI18N
        btnSaludPublica.setText("SALUD PÚBLICA");
        btnSaludPublica.setMaximumSize(new java.awt.Dimension(135, 25));
        btnSaludPublica.setMinimumSize(new java.awt.Dimension(135, 25));
        btnSaludPublica.setPreferredSize(new java.awt.Dimension(135, 25));
        btnSaludPublica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaludPublicaActionPerformed(evt);
            }
        });
        btnSaludPublica.setActionCommand("saludPublica");
        btnSaludPublica.addActionListener(control);

        btnFamilia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Familia.png"))); // NOI18N
        btnFamilia.setText("FAMILIA");
        btnFamilia.setMaximumSize(new java.awt.Dimension(135, 25));
        btnFamilia.setMinimumSize(new java.awt.Dimension(135, 25));
        btnFamilia.setPreferredSize(new java.awt.Dimension(135, 25));
        btnFamilia.setActionCommand("familia");
        btnFamilia.addActionListener(control);
        btnFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFamiliaActionPerformed(evt);
            }
        });

        btnGeneralidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Generalidades.png"))); // NOI18N
        btnGeneralidades.setText("GENERALIDADES");
        btnGeneralidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneralidadesActionPerformed(evt);
            }
        });
        btnGeneralidades.setActionCommand("generalidades");
        btnGeneralidades.addActionListener(control);

        btnObservaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Observaciones.png"))); // NOI18N
        btnObservaciones.setText("OBSERVACIONES");
        btnObservaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObservacionesActionPerformed(evt);
            }
        });
        btnObservaciones.setActionCommand("observaciones");
        btnObservaciones.addActionListener(control);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnGeneralidades)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnECCD, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVacuna, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSaludPublica, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnObservaciones)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGeneralidades, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnECCD, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVacuna, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSaludPublica, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 222, 235));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jPanel4.setBackground(new java.awt.Color(204, 222, 235));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 243, 189), 2), "Gestionar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Guardar.png"))); // NOI18N
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistrar.setMnemonic('R');
        btnRegistrar.setActionCommand("registrar");
        btnRegistrar.addActionListener(control);

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Modificar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnModificar.setMnemonic('M');
        btnModificar.setActionCommand("modificar");
        btnModificar.addActionListener(control);

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Eliminar.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminar.setMnemonic('E');
        btnEliminar.setActionCommand("eliminar");
        btnEliminar.addActionListener(control);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnEliminar)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnRegistrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 222, 235));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 243, 189), 2), "Dar de Baja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        btnDarBajaTarjetaFamiliar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/TarjetaFamiliar2.png"))); // NOI18N
        btnDarBajaTarjetaFamiliar.setText("TARJETA FAMILIAR");
        btnDarBajaTarjetaFamiliar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDarBajaTarjetaFamiliar.setMnemonic('T');
        btnDarBajaTarjetaFamiliar.setActionCommand("DarBajaTarjetaFamiliar");
        btnDarBajaTarjetaFamiliar.addActionListener(control);

        btnDarBajaAfiliado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Afiliado.png"))); // NOI18N
        btnDarBajaAfiliado.setText("AFILIADO");
        btnDarBajaAfiliado.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDarBajaAfiliado.setMnemonic('A');
        btnDarBajaAfiliado.setActionCommand("DarBajaAfiliado");
        btnDarBajaAfiliado.addActionListener(control);

        btnDarBajaFamiliar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Familiar.png"))); // NOI18N
        btnDarBajaFamiliar.setText("FAMILIAR");
        btnDarBajaFamiliar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDarBajaFamiliar.setMnemonic('F');
        btnDarBajaFamiliar.setActionCommand("DarBajaFamiliar");
        btnDarBajaFamiliar.addActionListener(control);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnDarBajaFamiliar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDarBajaTarjetaFamiliar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDarBajaAfiliado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(btnDarBajaTarjetaFamiliar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDarBajaAfiliado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDarBajaFamiliar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 222, 235));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 243, 189), 2), "Ver", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        btnVerTarjetaFamiliar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/TarjetaFamiliar2.png"))); // NOI18N
        btnVerTarjetaFamiliar.setText("TARJETA FAMILIAR");
        btnVerTarjetaFamiliar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVerTarjetaFamiliar.setMnemonic('T');
        btnVerTarjetaFamiliar.setActionCommand("VerTarjetaFamiliar");
        btnVerTarjetaFamiliar.addActionListener(control);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVerTarjetaFamiliar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(btnVerTarjetaFamiliar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(173, Short.MAX_VALUE))
        );

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/BarraPequena - copia.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/BarraPequena - copia.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.getAccessibleContext().setAccessibleName("jLabel7");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnECCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnECCDActionPerformed
        // TODO add your handling code here:
//        GuiEccd ECCD=new GuiEccd();
//        ECCD.setLocation(225,120);
//        ECCD.setVisible(true);
    }//GEN-LAST:event_btnECCDActionPerformed

    private void btnVacunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVacunaActionPerformed
        // TODO add your handling code here:
//        GuiVacuna Vacunacion=new GuiVacuna();
//        Vacunacion.setLocation(225,120);
//        Vacunacion.setVisible(true);
    }//GEN-LAST:event_btnVacunaActionPerformed

    private void btnSaludPublicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaludPublicaActionPerformed
        // TODO add your handling code here:
//        GuiSaludPublica SaludP=new GuiSaludPublica();
//        SaludP.setLocation(280,120);
//        SaludP.setVisible(true);
    }//GEN-LAST:event_btnSaludPublicaActionPerformed

    private void btnFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFamiliaActionPerformed
        // TODO add your handling code here:
//        GuiFamilia familia=new GuiFamilia();
//        familia.setLocation(225,120);
//        familia.setVisible(true);
    }//GEN-LAST:event_btnFamiliaActionPerformed

    private void btnGeneralidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneralidadesActionPerformed
        // TODO add your handling code here:
//        GuiGeneralidades generalidades=new GuiGeneralidades();
//        generalidades.setLocation(225,120);
//        generalidades.setVisible(true);
    }//GEN-LAST:event_btnGeneralidadesActionPerformed

    private void btnObservacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObservacionesActionPerformed
        // TODO add your handling code here:
//        GuiObservaciones observaciones=new GuiObservaciones();
//        observaciones.setLocation(225,120);
//        observaciones.setVisible(true);
    }//GEN-LAST:event_btnObservacionesActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiTarjeta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDarBajaAfiliado;
    private javax.swing.JButton btnDarBajaFamiliar;
    private javax.swing.JButton btnDarBajaTarjetaFamiliar;
    private javax.swing.JToggleButton btnECCD;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JToggleButton btnFamilia;
    private javax.swing.JToggleButton btnGeneralidades;
    private javax.swing.JButton btnModificar;
    private javax.swing.JToggleButton btnObservaciones;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JToggleButton btnSaludPublica;
    private javax.swing.JToggleButton btnVacuna;
    private javax.swing.JButton btnVerTarjetaFamiliar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
    public CGuiTarjeta control;
   

    public JToggleButton getBtnECCD() {
        return btnECCD;
    }

    public JToggleButton getBtnFamilia() {
        return btnFamilia;
    }

    public JToggleButton getBtnGeneralidades() {
        return btnGeneralidades;
    }

    public JToggleButton getBtnObservaciones() {
        return btnObservaciones;
    }

    public JToggleButton getBtnSaludPublica() {
        return btnSaludPublica;
    }

    public JToggleButton getBtnVacuna() {
        return btnVacuna;
    }

    public ButtonGroup getButtonGroup1() {
        return buttonGroup1;
    }

    public JButton getBtnDarBajaAfiliado() {
        return btnDarBajaAfiliado;
    }

    public JButton getBtnDarBajaFamiliar() {
        return btnDarBajaFamiliar;
    }

    public JButton getBtnDarBajaTarjetaFamiliar() {
        return btnDarBajaTarjetaFamiliar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnVerTarjetaFamiliar() {
        return btnVerTarjetaFamiliar;
    }

    


}