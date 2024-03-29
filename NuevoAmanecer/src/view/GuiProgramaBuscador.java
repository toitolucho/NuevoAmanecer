/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GuiProgramaBuscador.java
 *
 * Created on 22-feb-2011, 17:50:28
 */

package view;

import bo.usfx.nuevoAmanecer.controller.CGuiProgramaBuscador;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;


public class GuiProgramaBuscador extends javax.swing.JDialog {

    /** Creates new form GuiProgramaBuscador */
    public GuiProgramaBuscador(JFrame owner, boolean modal) {
        super(owner, modal);
        control = new CGuiProgramaBuscador(this);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTextoBusqueda = new javax.swing.JTextField();
        dateFechaBusqueda = new com.toedter.calendar.JDateChooser();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCasos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 222, 235));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setText("Texto de Busqueda:");
        jPanel1.add(jLabel1);

        txtTextoBusqueda.setPreferredSize(new java.awt.Dimension(200, 20));
        txtTextoBusqueda.setActionCommand("buscar");
        txtTextoBusqueda.addActionListener(control);
        jPanel1.add(txtTextoBusqueda);
        jPanel1.add(dateFechaBusqueda);

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Buscar.png"))); // NOI18N
        btnBuscar.setMnemonic('B');
        btnBuscar.setText("Buscar");
        btnBuscar.setToolTipText("Buscar registros bajo la información prveida en el Texto de Busqueda");
        btnBuscar.setActionCommand("buscar");
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(control);
        jPanel1.add(btnBuscar);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBackground(new java.awt.Color(204, 222, 235));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados Obtenidos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jTableCasos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableCasos);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 243, 189));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Ok.png"))); // NOI18N
        btnCerrar.setMnemonic('A');
        btnCerrar.setText("Aceptar");
        btnCerrar.setToolTipText("Cerrar ventana");
        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrar.setActionCommand("cerrar");
        btnCerrar.addActionListener(control);
        jPanel2.add(btnCerrar);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiProgramaBuscador(null, false).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private com.toedter.calendar.JDateChooser dateFechaBusqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCasos;
    private javax.swing.JTextField txtTextoBusqueda;
    // End of variables declaration//GEN-END:variables
    public CGuiProgramaBuscador control;

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnCerrar() {
        return btnCerrar;
    }

    

    public JTable getjTableCasos() {
        return jTableCasos;
    }

    public JTextField getTxtTextoBusqueda() {
        return txtTextoBusqueda;
    }

    public JDateChooser getDateFechaBusqueda() {
        return dateFechaBusqueda;
    }

    
    
}
