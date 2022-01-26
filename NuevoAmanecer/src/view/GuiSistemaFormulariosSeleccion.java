package view;

import bo.usfx.nuevoAmanecer.controller.CGuiSistemaFormulariosSeleccion;
import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.Font;
import java.awt.Color;

public class GuiSistemaFormulariosSeleccion extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jpnlBotones = null;
	private JButton jbtnAceptar = null;
	private JButton jbtnCancelar = null;
	private JCheckBox jcheckSeleccionarTodos = null;
	private JScrollPane jScrollPane = null;
	private JTable jTableFormularios = null;
	public CGuiSistemaFormulariosSeleccion control = null;
	
	/**
	 * @param owner
	 */
	public GuiSistemaFormulariosSeleccion(Frame owner) {
		super(owner);
		control = new CGuiSistemaFormulariosSeleccion(this);
		initialize();
	}
	
	public GuiSistemaFormulariosSeleccion (JInternalFrame owner) {
		super(JOptionPane.getFrameForComponent(owner), true);
		control = new CGuiSistemaFormulariosSeleccion(this);
		initialize();
	}	

	public GuiSistemaFormulariosSeleccion()
	{
		super();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(411, 356);
		this.setTitle("Seleccion de Formularios");
		this.setContentPane(getJContentPane());
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
			jContentPane.add(getJpnlBotones(), BorderLayout.SOUTH);
			jContentPane.add(getJcheckSeleccionarTodos(), BorderLayout.NORTH);
			jContentPane.add(getJScrollPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jpnlBotones	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJpnlBotones() {
		if (jpnlBotones == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			jpnlBotones = new JPanel();
			jpnlBotones.setPreferredSize(new Dimension(10, 40));
			jpnlBotones.setLayout(flowLayout);
			jpnlBotones.add(getJbtnAceptar(), null);
			jpnlBotones.add(getJbtnCancelar(), null);
		}
		return jpnlBotones;
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
			jbtnAceptar.setToolTipText("Aceptar la Seleccion de los Formularios");
			jbtnAceptar.setMnemonic(KeyEvent.VK_A);
			jbtnAceptar.setActionCommand("aceptar");
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
			jbtnCancelar.setMnemonic(KeyEvent.VK_C);
			jbtnCancelar.setToolTipText("Cancelar la Selecci�n Actual de los Formularios");
			jbtnCancelar.setActionCommand("cancelar");
			jbtnCancelar.addActionListener(control);
		}
		return jbtnCancelar;
	}

	/**
	 * This method initializes jcheckSeleccionarTodos	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getJcheckSeleccionarTodos() {
		if (jcheckSeleccionarTodos == null) {
			jcheckSeleccionarTodos = new JCheckBox();
			jcheckSeleccionarTodos.setText("Seleccionar Todos");
			jcheckSeleccionarTodos.setHorizontalAlignment(SwingConstants.RIGHT);
			jcheckSeleccionarTodos.setToolTipText("Seleccionar Todos los Formularios o realizar la operacion contraria");
			jcheckSeleccionarTodos.setMnemonic(KeyEvent.VK_T);
			jcheckSeleccionarTodos.setActionCommand("seleccionar");
			jcheckSeleccionarTodos.addActionListener(control);
			
		}
		return jcheckSeleccionarTodos;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Marque o Desmarque el Formulario para asignar Permisos", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jScrollPane.setViewportView(getJTableFormularios());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTableFormularios	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTableFormularios() {
		if (jTableFormularios == null) {
			jTableFormularios = new JTableToolTips();
			jTableFormularios.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jTableFormularios.setIntercellSpacing(new Dimension(2, 2));
			jTableFormularios.setRowHeight(18);
			jTableFormularios.setRowSelectionAllowed(false);
			jTableFormularios.setShowHorizontalLines(false);
			jTableFormularios.setCellSelectionEnabled(true);
		}
		return jTableFormularios;
	}

}  //  @jve:decl-index=0:visual-constraint="20,11"
