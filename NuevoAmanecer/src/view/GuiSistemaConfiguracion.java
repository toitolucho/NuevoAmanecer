package view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import bo.usfx.nuevoAmanecer.controller.CGuiSistemaConfiguracion;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class GuiSistemaConfiguracion extends JFrame {

	private JPanel jContentPane = null;
	private JPanel jpnlBotonesRestoreBackup = null;
	private JButton jbtnBackup = null;
	private JButton jbtnRestaurar = null;
	private JPanel jpnlCentral = null;
	private JLabel jLabel = null;
	private JTextField jtxtNombreServidor = null;
	private JLabel jLabel1 = null;
	private JTextField jtxtNombreBaseDatos = null;
	private JLabel jLabel2 = null;
	private JTextField jtxtUsuario = null;
	private JLabel jLabel3 = null;
	private JPasswordField jPasswordField = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanel = null;
	public CGuiSistemaConfiguracion control = null;
	private JCheckBox jcheckPorFecha = null;
	private JCheckBox jcheckSeleccionarDirectorio = null;
	public JLabel jlblOperaciones = null;
	private JProgressBar jProgressBar = null;
	/**
	 * This is the xxx default constructor
	 */
	public GuiSistemaConfiguracion() {
		super();
		control  = new CGuiSistemaConfiguracion(this);
		initialize();
                this.jtxtNombreBaseDatos.setText("bdnuevoamanecer");
                this.jtxtNombreBaseDatos.setEnabled(false);
                this.jtxtNombreServidor.setText("192.168.23.1");
                this.jtxtUsuario.setText("postgres");
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(421, 411);
		this.setTitle("Opciones de Configuración");
		this.setContentPane(getJContentPane());
		this.getJbtnRestaurar().setVisible(false);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJTabbedPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jpnlBotonesRestoreBackup	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpnlBotonesRestoreBackup() {
		if (jpnlBotonesRestoreBackup == null) {
			jpnlBotonesRestoreBackup = new JPanel();
			jpnlBotonesRestoreBackup.setLayout(new FlowLayout());
			jpnlBotonesRestoreBackup.setPreferredSize(new Dimension(10, 45));
			jpnlBotonesRestoreBackup.add(getJbtnBackup(), null);
			jpnlBotonesRestoreBackup.add(getJbtnRestaurar(), null);
		}
		return jpnlBotonesRestoreBackup;
	}

	/**
	 * This method initializes jbtnBackup	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbtnBackup() {
		if (jbtnBackup == null) {
			jbtnBackup = new JButton();
			jbtnBackup.setText("Copia de Seguridad prueba de datos");
			jbtnBackup.setActionCommand("backup");
			jbtnBackup.setToolTipText("Realizar una Copia de Suguridad de la Base de Datos");
			jbtnBackup.addActionListener(control);
		}
		return jbtnBackup;
	}

	/**
	 * This method initializes jbtnRestaurar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbtnRestaurar() {
		if (jbtnRestaurar == null) {
			jbtnRestaurar = new JButton();
			jbtnRestaurar.setText("Restaurar");
			jbtnRestaurar.setActionCommand("restaurar");
			jbtnRestaurar.setToolTipText("Restaurar una Copia de seguridad");
			jbtnRestaurar.addActionListener(control);
		}
		return jbtnRestaurar;
	}

	/**
	 * This method initializes jpnlCentral	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJpnlCentral() {
		if (jpnlCentral == null) {
			jlblOperaciones = new JLabel();
			jlblOperaciones.setText("                                                                         ");
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setVgap(8);
			flowLayout.setAlignment(FlowLayout.LEFT);
			jLabel3 = new JLabel();
			jLabel3.setText("Password :");
			jLabel3.setPreferredSize(new Dimension(140, 16));
			jLabel2 = new JLabel();
			jLabel2.setText("Usuario :");
			jLabel2.setPreferredSize(new Dimension(140, 16));
			jLabel1 = new JLabel();
			jLabel1.setText("Nombre Base de Datos :");
			jLabel1.setPreferredSize(new Dimension(140, 16));
			jLabel = new JLabel();
			jLabel.setText("Nombre Host (IP) :");
			jLabel.setPreferredSize(new Dimension(140, 16));
			jpnlCentral = new JPanel();
			jpnlCentral.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Datos del Gestor de Base de datos", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.ABOVE_TOP, new Font("sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
			jpnlCentral.setLayout(flowLayout);
			jpnlCentral.add(jLabel, null);
			jpnlCentral.add(getJtxtNombreServidor(), null);
			jpnlCentral.add(jLabel1, null);
			jpnlCentral.add(getJtxtNombreBaseDatos(), null);
			jpnlCentral.add(jLabel2, null);
			jpnlCentral.add(getJtxtUsuario(), null);
			jpnlCentral.add(jLabel3, null);
			jpnlCentral.add(getJPasswordField(), null);
			jpnlCentral.add(getJcheckPorFecha(), null);
			jpnlCentral.add(getJcheckSeleccionarDirectorio(), null);
			jpnlCentral.add(jlblOperaciones, null);
			jpnlCentral.add(getJProgressBar(), null);
		}
		return jpnlCentral;
	}

	/**
	 * This method initializes jtxtNombreServidor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJtxtNombreServidor() {
		if (jtxtNombreServidor == null) {
			jtxtNombreServidor = new JTextField();
			jtxtNombreServidor.setPreferredSize(new Dimension(220, 28));
			jtxtNombreServidor.setToolTipText("Introduzca el nombre del HOST o su Dirección IP");

		}
		return jtxtNombreServidor;
	}

	/**
	 * This method initializes jtxtNombreBaseDatos	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJtxtNombreBaseDatos() {
		if (jtxtNombreBaseDatos == null) {
			jtxtNombreBaseDatos = new JTextField();
			jtxtNombreBaseDatos.setPreferredSize(new Dimension(220, 28));
			jtxtNombreBaseDatos.setEditable(false);
			jtxtNombreBaseDatos.setText("bdnuevoamanecer");
			jtxtNombreBaseDatos.setToolTipText("Nombre de la Base de Datos");
		}
		return jtxtNombreBaseDatos;
	}

	/**
	 * This method initializes jtxtUsuario	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJtxtUsuario() {
		if (jtxtUsuario == null) {
			jtxtUsuario = new JTextField();
			jtxtUsuario.setPreferredSize(new Dimension(220, 28));
			jtxtUsuario.setToolTipText("Introduzca el Nombre de la cuenta del Usuario en el Gestor de Base de Datos");
		}
		return jtxtUsuario;
	}

	/**
	 * This method initializes jPasswordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	public JPasswordField getJPasswordField() {
		if (jPasswordField == null) {
			jPasswordField = new JPasswordField();
			jPasswordField.setPreferredSize(new Dimension(220, 28));
			jPasswordField.setToolTipText("Introduzca la contraseña del Usuario");
		}
		return jPasswordField;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("Backup & Restaurar", null, getJPanel(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BorderLayout());
			jPanel.add(getJpnlCentral(), BorderLayout.CENTER);
			jPanel.add(getJpnlBotonesRestoreBackup(), BorderLayout.SOUTH);
		}
		return jPanel;
	}

	/**
	 * This method initializes jcheckPorFecha	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getJcheckPorFecha() {
		if (jcheckPorFecha == null) {
			jcheckPorFecha = new JCheckBox();
			jcheckPorFecha.setText("Nombre del Resguardo con la Fecha de Hoy");
		}
		return jcheckPorFecha;
	}

	/**
	 * This method initializes jcheckSeleccionarDirectorio	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getJcheckSeleccionarDirectorio() {
		if (jcheckSeleccionarDirectorio == null) {
			jcheckSeleccionarDirectorio = new JCheckBox();
			jcheckSeleccionarDirectorio.setText("Seleccionar Directorio                 ");
		}
		return jcheckSeleccionarDirectorio;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setPreferredSize(new Dimension(385, 22));
		}
		return jProgressBar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
