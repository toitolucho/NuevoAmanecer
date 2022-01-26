package view;

import bo.usfx.nuevoAmanecer.controller.CGuiEmpleadosUsuariosAdministracion;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;



import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GuiEmpleadosUsuariosAdministracion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jpnlBusqueda = null;
	private FPanelSistmeaFormulariosPermisosUsuarios jpnlUsuariosAdminitracion = null;
	private JScrollPane jScrollPane = null;
	private JTable jTableUsuarios = null;
	private JPanel jpnlCamposBusqueda = null;
	private JLabel jLabel = null;
	private JTextField jtxtTextoBusqueda = null;
	private JButton jbtnBuscar = null;
	public CGuiEmpleadosUsuariosAdministracion control = null;

	/**
	 * This is the xxx default constructor
	 */
	public GuiEmpleadosUsuariosAdministracion() {
		super();
		control = new CGuiEmpleadosUsuariosAdministracion(this);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(696, 600);
		this.setTitle("Administracion de Usuarios");
		this.setContentPane(getJContentPane());
//		this.setClosable(true);
//		this.setIconifiable(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJTabbedPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("Busqueda de Usuarios", null, getJpnlBusqueda(), null);
			jTabbedPane.addTab("Permisos de Usuario", null, getJpnlUsuariosAdminitracion(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jpnlBusqueda	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJpnlBusqueda() {
		if (jpnlBusqueda == null) {
			jpnlBusqueda = new JPanel();
			jpnlBusqueda.setLayout(new BorderLayout());
			jpnlBusqueda.add(getJScrollPane(), BorderLayout.CENTER);
			jpnlBusqueda.add(getJpnlCamposBusqueda(), BorderLayout.NORTH);
		}
		return jpnlBusqueda;
	}

	/**
	 * This method initializes jpnlUsuariosAdminitracion	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public FPanelSistmeaFormulariosPermisosUsuarios getJpnlUsuariosAdminitracion() {
		if (jpnlUsuariosAdminitracion == null) {
			jpnlUsuariosAdminitracion = new FPanelSistmeaFormulariosPermisosUsuarios();			
		}
		return jpnlUsuariosAdminitracion;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTableUsuarios());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTableUsuarios	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTableUsuarios() {
		if (jTableUsuarios == null) {
			jTableUsuarios = new JTable();
			
		}
		return jTableUsuarios;
	}

	/**
	 * This method initializes jpnlCamposBusqueda	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJpnlCamposBusqueda() {
		if (jpnlCamposBusqueda == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			flowLayout.setHgap(15);
			jLabel = new JLabel();
			jLabel.setText("Datos del Usuario :");
			jpnlCamposBusqueda = new JPanel();
			jpnlCamposBusqueda.setLayout(flowLayout);
			jpnlCamposBusqueda.setPreferredSize(new Dimension(0, 50));
			jpnlCamposBusqueda.add(jLabel, null);
			jpnlCamposBusqueda.add(getJtxtTextoBusqueda(), null);
			jpnlCamposBusqueda.add(getJbtnBuscar(), null);
		}
		return jpnlCamposBusqueda;
	}

	/**
	 * This method initializes jtxtTextoBusqueda	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJtxtTextoBusqueda() {
		if (jtxtTextoBusqueda == null) {
			jtxtTextoBusqueda = new JTextField();
			jtxtTextoBusqueda.setPreferredSize(new Dimension(450, 28));
			jtxtTextoBusqueda.setActionCommand("buscarUsuario");
			jtxtTextoBusqueda.addActionListener(control);
			jtxtTextoBusqueda.getDocument().addDocumentListener(control);
		}
		return jtxtTextoBusqueda;
	}

	/**
	 * This method initializes jbtnBuscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getJbtnBuscar() {
		if (jbtnBuscar == null) {
			jbtnBuscar = new JButton();
			jbtnBuscar.setText("");
			jbtnBuscar.setMnemonic(KeyEvent.VK_B);
			jbtnBuscar.setToolTipText("Buscar el Usuario a partir del Parametro de Entrada");
//			jbtnBuscar.setIcon(new ImageIcon(getClass().getResource("/imagenes/find.png")));
			jbtnBuscar.setActionCommand("buscarUsuario");
			jbtnBuscar.addActionListener(control);
		}
		return jbtnBuscar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
