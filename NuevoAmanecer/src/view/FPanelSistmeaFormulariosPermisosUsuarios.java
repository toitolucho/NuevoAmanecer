package view;

import bo.usfx.nuevoAmanecer.controller.CFPanelSistmeaFormulariosPermisosUsuarios;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class FPanelSistmeaFormulariosPermisosUsuarios extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jpnlSuperior = null;
	private JPanel jpnlInferior = null;
	private JScrollPane jScrollPane = null;
	private JTable jTableUsuarioPermisos = null;
	private JLabel jLabel = null;
	private JTextField jtxtNombreUsuario = null;
	private JTextField jtxtCargo = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JScrollPane jScrollPane1 = null;
	private JTextArea jTextAreaDescripcion = null;
	private JButton jbtnAceptar = null;
	private JButton jbtnCancelar = null;
	private JButton jbtnAnadirFormularios = null;
	private JButton jbtnEliminarFormulario = null;
	public CFPanelSistmeaFormulariosPermisosUsuarios control = null;
	private JButton jbtnModificar = null;
	/**
	 * This is the default constructor
	 */
	public FPanelSistmeaFormulariosPermisosUsuarios() {
		super();
		control = new CFPanelSistmeaFormulariosPermisosUsuarios(this);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(716, 426);
		this.setLayout(new BorderLayout());
		this.add(getJpnlSuperior(), BorderLayout.NORTH);
		this.add(getJpnlInferior(), BorderLayout.SOUTH);
		this.add(getJScrollPane(), BorderLayout.CENTER);
	}

	/**
	 * This method initializes jpnlSuperior	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJpnlSuperior() {
		if (jpnlSuperior == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Descripcion : ");
			jLabel1 = new JLabel();
			jLabel1.setText("Cargo :");
			jLabel1.setPreferredSize(new Dimension(111, 16));
			jLabel = new JLabel();
			jLabel.setText("Nombre Completo : ");
			jpnlSuperior = new JPanel();
			jpnlSuperior.setLayout(new FlowLayout());
			jpnlSuperior.setPreferredSize(new Dimension(10, 130));
			jpnlSuperior.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Datos del Usuario", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), Color.red));
			jpnlSuperior.add(jLabel, null);
			jpnlSuperior.add(getJtxtNombreUsuario(), null);
//			jpnlSuperior.add(jLabel1, null);
//			jpnlSuperior.add(getJtxtCargo(), null);
//			jpnlSuperior.add(jLabel2, null);
//			jpnlSuperior.add(getJScrollPane1(), null);
		}
		return jpnlSuperior;
	}

	/**
	 * This method initializes jpnlInferior	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJpnlInferior() {
		if (jpnlInferior == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.CENTER);
			flowLayout.setHgap(10);
			jpnlInferior = new JPanel();
			jpnlInferior.setLayout(flowLayout);
			jpnlInferior.setPreferredSize(new Dimension(10, 40));
			jpnlInferior.add(getJbtnModificar(), null);
			jpnlInferior.add(getJbtnAceptar(), null);
			jpnlInferior.add(getJbtnAnadirFormularios(), null);
			jpnlInferior.add(getJbtnEliminarFormulario(), null);
			jpnlInferior.add(getJbtnCancelar(), null);
		}
		return jpnlInferior;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Listado de Formularios y sus permisos", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), Color.blue));
			jScrollPane.setViewportView(getJTableUsuarioPermisos());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTableUsuarioPermisos	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTableUsuarioPermisos() {
		if (jTableUsuarioPermisos == null) {
			jTableUsuarioPermisos = new JTable();
			jTableUsuarioPermisos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jTableUsuarioPermisos.setRowHeight(18);
			jTableUsuarioPermisos.setRowSelectionAllowed(false);
			jTableUsuarioPermisos.setShowVerticalLines(false);
			jTableUsuarioPermisos.setCellSelectionEnabled(true);
		}
		return jTableUsuarioPermisos;
	}

	/**
	 * This method initializes jtxtNombreUsuario	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJtxtNombreUsuario() {
		if (jtxtNombreUsuario == null) {
			jtxtNombreUsuario = new JTextField();
			jtxtNombreUsuario.setPreferredSize(new Dimension(500, 28));
		}
		return jtxtNombreUsuario;
	}

	/**
	 * This method initializes jtxtCargo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJtxtCargo() {
		if (jtxtCargo == null) {
			jtxtCargo = new JTextField();
			jtxtCargo.setPreferredSize(new Dimension(195, 28));
		}
		return jtxtCargo;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setPreferredSize(new Dimension(220, 60));
			jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jScrollPane1.setViewportView(getJTextAreaDescripcion());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTextAreaDescripcion	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getJTextAreaDescripcion() {
		if (jTextAreaDescripcion == null) {
			jTextAreaDescripcion = new JTextArea();
		}
		return jTextAreaDescripcion;
	}

	/**
	 * This method initializes jbtnAceptar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getJbtnAceptar() {
		if (jbtnAceptar == null) {
			jbtnAceptar = new JButton();
			jbtnAceptar.setText("Aceptar");
			jbtnAceptar.setActionCommand("aceptar");
			jbtnAceptar.setToolTipText("Aceptar los cambios realizados");
//			jbtnAceptar.setIcon(new ImageIcon(getClass().getResource("/imagenes/CONFIRMAR.png")));
			jbtnAceptar.setPreferredSize(new Dimension(93, 35));
			jbtnAceptar.setMnemonic('A');
			jbtnAceptar.addActionListener(control);
		}
		return jbtnAceptar;
	}

	/**
	 * This method initializes jbtnCancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getJbtnCancelar() {
		if (jbtnCancelar == null) {
			jbtnCancelar = new JButton();
			jbtnCancelar.setText("Cancelar");
			jbtnCancelar.setActionCommand("cancelar");
			jbtnCancelar.setToolTipText("Cancelar los cambios realizados");
//			jbtnCancelar.setIcon(new ImageIcon(getClass().getResource("/imagenes/cancel.png")));
			jbtnCancelar.setMnemonic('C');
			jbtnCancelar.addActionListener(control);
		}
		return jbtnCancelar;
	}

	/**
	 * This method initializes jbtnAnadirFormularios	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getJbtnAnadirFormularios() {
		if (jbtnAnadirFormularios == null) {
			jbtnAnadirFormularios = new JButton();
			jbtnAnadirFormularios.setText("A�adir Formularios");
			jbtnAnadirFormularios.setActionCommand("anadir");
			jbtnAnadirFormularios.setToolTipText("A�adir Formularios faltantes");
//			jbtnAnadirFormularios.setIcon(new ImageIcon(getClass().getResource("/imagenes/A�ADIR.png")));
			jbtnAnadirFormularios.setPreferredSize(new Dimension(160, 35));
			jbtnAnadirFormularios.setMnemonic('d');
			jbtnAnadirFormularios.addActionListener(control);
			
		}
		return jbtnAnadirFormularios;
	}

	/**
	 * This method initializes jbtnEliminarFormulario	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getJbtnEliminarFormulario() {
		if (jbtnEliminarFormulario == null) {
			jbtnEliminarFormulario = new JButton();
			jbtnEliminarFormulario.setText("Eliminar Formulario");
			jbtnEliminarFormulario.setActionCommand("eliminar");
			jbtnEliminarFormulario.setToolTipText("Eliminar el Formulario seleccionado");
//			jbtnEliminarFormulario.setIcon(new ImageIcon(getClass().getResource("/imagenes/delete.png")));
			jbtnEliminarFormulario.setMnemonic('E');
			jbtnEliminarFormulario.addActionListener(control);
		}
		return jbtnEliminarFormulario;
	}

	/**
	 * This method initializes jbtnModificar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getJbtnModificar() {
		if (jbtnModificar == null) {
			jbtnModificar = new JButton();
			jbtnModificar.setText("Modificar");
			jbtnModificar.setActionCommand("modificar");
			jbtnModificar.setToolTipText("Modificar los Permisos Actuales del usuario");
//			jbtnModificar.setIcon(new ImageIcon(getClass().getResource("/imagenes/korganizer.png")));
			jbtnModificar.setPreferredSize(new Dimension(103, 35));
			jbtnModificar.setMnemonic('M');
			jbtnModificar.addActionListener(control);
		}
		return jbtnModificar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
