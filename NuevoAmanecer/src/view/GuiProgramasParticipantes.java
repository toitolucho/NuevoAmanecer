/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GuiProgramasParticipantes.java
 *
 * Created on 20-dic-2010, 12:00:40
 */

package view;

import bo.usfx.nuevoAmanecer.controller.CGuiProgramasIntegrantes;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;


public class GuiProgramasParticipantes extends javax.swing.JDialog {

    /** Creates new form GuiProgramasParticipantes */
    public GuiProgramasParticipantes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        control = new CGuiProgramasIntegrantes(this);
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

        pnlDatosPrograma = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblResumenDatosPrograma = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableParticipantesRegistrados = new javax.swing.JTable();
        pnlBotones = new javax.swing.JPanel();
        btnEliminarParticipante = new javax.swing.JButton();
        btnAgregarParticipantes = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlAfiliadosNuevos = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTextoBusqueda = new javax.swing.JTextField();
        btnBuscarNuevoAfiliado = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableParticipantesNuevos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlDatosPrograma.setBackground(new java.awt.Color(204, 222, 235));
        pnlDatosPrograma.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Programa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        pnlDatosPrograma.setPreferredSize(new java.awt.Dimension(615, 250));
        pnlDatosPrograma.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(204, 222, 235));
        jPanel1.setPreferredSize(new java.awt.Dimension(669, 35));

        lblResumenDatosPrograma.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblResumenDatosPrograma.setForeground(new java.awt.Color(63, 120, 204));
        lblResumenDatosPrograma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Programas.png"))); // NOI18N
        lblResumenDatosPrograma.setText("jLabel2");
        jPanel1.add(lblResumenDatosPrograma);

        pnlDatosPrograma.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBackground(new java.awt.Color(204, 222, 235));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Listado de Participantes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jTableParticipantesRegistrados.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableParticipantesRegistrados);

        pnlDatosPrograma.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlDatosPrograma, java.awt.BorderLayout.PAGE_START);

        pnlBotones.setBackground(new java.awt.Color(255, 243, 189));
        pnlBotones.setPreferredSize(new java.awt.Dimension(615, 50));

        btnEliminarParticipante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Eliminar.png"))); // NOI18N
        btnEliminarParticipante.setText("Eliminar Integrantes");
        btnEliminarParticipante.setActionCommand("eliminar");
        btnEliminarParticipante.setMnemonic('E');
        btnEliminarParticipante.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminarParticipante.addActionListener(control);

        btnAgregarParticipantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Agregar.png"))); // NOI18N
        btnAgregarParticipantes.setText("Agregar Integrantes");
        btnAgregarParticipantes.setActionCommand("agregarAfiliado");
        btnAgregarParticipantes.setMnemonic('A');
        btnAgregarParticipantes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgregarParticipantes.addActionListener(control);

        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Ok.png"))); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.setActionCommand("confirmar");
        btnConfirmar.setMnemonic('f');
        btnConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirmar.addActionListener(control);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Eliminar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setActionCommand("cancelar");
        btnCancelar.setMnemonic('C');
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(control);

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotonesLayout.createSequentialGroup()
                .addContainerGap(164, Short.MAX_VALUE)
                .addComponent(btnEliminarParticipante)
                .addGap(5, 5, 5)
                .addComponent(btnAgregarParticipantes)
                .addGap(5, 5, 5)
                .addComponent(btnConfirmar)
                .addGap(5, 5, 5)
                .addComponent(btnCancelar)
                .addContainerGap())
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminarParticipante)
                    .addComponent(btnAgregarParticipantes)
                    .addComponent(btnConfirmar)
                    .addComponent(btnCancelar))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getContentPane().add(pnlBotones, java.awt.BorderLayout.PAGE_END);

        pnlAfiliadosNuevos.setBackground(new java.awt.Color(204, 222, 235));
        pnlAfiliadosNuevos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Asignación y Busqueda de Nuevos Participantes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        pnlAfiliadosNuevos.setPreferredSize(new java.awt.Dimension(669, 300));
        pnlAfiliadosNuevos.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(204, 222, 235));
        jPanel2.setPreferredSize(new java.awt.Dimension(657, 30));

        jLabel1.setText("Texto de Busqueda :");

        txtTextoBusqueda.setPreferredSize(new java.awt.Dimension(350, 20));
        txtTextoBusqueda.setActionCommand("buscarAfiliado");
        txtTextoBusqueda.addActionListener(control);

        btnBuscarNuevoAfiliado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/Buscar.png"))); // NOI18N
        btnBuscarNuevoAfiliado.setText("Buscar");
        btnBuscarNuevoAfiliado.setActionCommand("buscarIntegrante");
        btnBuscarNuevoAfiliado.setMnemonic('B');
        btnBuscarNuevoAfiliado.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBuscarNuevoAfiliado.addActionListener(control);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(txtTextoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnBuscarNuevoAfiliado)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtTextoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscarNuevoAfiliado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlAfiliadosNuevos.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jTableParticipantesNuevos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTableParticipantesNuevos);

        pnlAfiliadosNuevos.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlAfiliadosNuevos, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GuiProgramasParticipantes dialog = new GuiProgramasParticipantes(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAgregarParticipantes;
    private javax.swing.JButton btnBuscarNuevoAfiliado;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnEliminarParticipante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableParticipantesNuevos;
    private javax.swing.JTable jTableParticipantesRegistrados;
    private javax.swing.JLabel lblResumenDatosPrograma;
    private javax.swing.JPanel pnlAfiliadosNuevos;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDatosPrograma;
    private javax.swing.JTextField txtTextoBusqueda;
    // End of variables declaration//GEN-END:variables
    public CGuiProgramasIntegrantes control;

    public JButton getBtnAgregarParticipantes() {
        return btnAgregarParticipantes;
    }

    public JButton getBtnBuscarNuevoAfiliado() {
        return btnBuscarNuevoAfiliado;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JButton getBtnConfirmar() {
        return btnConfirmar;
    }

    public JButton getBtnEliminarParticipante() {
        return btnEliminarParticipante;
    }

    public JTable getjTableParticipantesNuevos() {
        return jTableParticipantesNuevos;
    }

    public JTable getjTableParticipantesRegistrados() {
        return jTableParticipantesRegistrados;
    }

    public JLabel getLblResumenDatosPrograma() {
        return lblResumenDatosPrograma;
    }

    public JPanel getPnlAfiliadosNuevos() {
        return pnlAfiliadosNuevos;
    }

    public JPanel getPnlBotones() {
        return pnlBotones;
    }

    public JPanel getPnlDatosPrograma() {
        return pnlDatosPrograma;
    }

    public JTextField getTxtTextoBusqueda() {
        return txtTextoBusqueda;
    }

    
}
