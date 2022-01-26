
package view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import bo.usfx.nuevoAmanecer.controller.CGuiAutenticacion;
import java.awt.Color;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import java.awt.Cursor;


public class GuiAutenticacion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JPanel jpnlCentro = null;
    private JPanel jpnlBotones = null;
    private JLabel jlblUsuario = null;
    private JTextField jtxtUsuario = null;
    private JLabel jlblPassword = null;
    private JPasswordField jtxtPassword = null;
    private JButton jbtnAceptar = null;
    private JButton jbtnCancelar = null;
    private CGuiAutenticacion control = null;
    private JLabel jlblImagen = null;

    /**
     * Constructor de la Clase
     * @throws HeadlessException
     */
    public GuiAutenticacion() throws HeadlessException {
        super();
        this.setVisible(false);
        control = new CGuiAutenticacion(this);
        initialize();
      //  this.getJtxtPassword().setText("facilitador");
      //  this.getJtxtUsuario().setText("facilitador");
    }

    /**
     * Constructor de la Clase
     * @param arg0
     */
    public GuiAutenticacion(GraphicsConfiguration arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
        control = new CGuiAutenticacion(this);
        initialize();
    }

    /**
     * Constructor de la Clase
     * @param arg0
     * @throws HeadlessException
     */
    public GuiAutenticacion(String arg0) throws HeadlessException {
        super(arg0);
        // TODO Auto-generated constructor stub
        control = new CGuiAutenticacion(this);
        initialize();
    }

    /**
     * Constructor de la Clase
     * @param arg0
     * @param arg1
     */
    public GuiAutenticacion(String arg0, GraphicsConfiguration arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
        control = new CGuiAutenticacion(this);
        initialize();
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.setResizable(false);
        this.setSize(new Dimension(421, 232));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(435, 211);
        this.setContentPane(getJContentPane());
        this.setTitle("Autenticacion de Usuario");
        this.setVisible(true);
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jlblImagen = new JLabel();
            jlblImagen.setText("");
            jlblImagen.setIcon(new ImageIcon(getClass().getResource("/imagenes/LOGOS/CristhianChildren.png")));
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getJpnlCentro(), BorderLayout.CENTER);
            jContentPane.add(getJpnlBotones(), BorderLayout.SOUTH);
            jContentPane.add(jlblImagen, BorderLayout.WEST);
            jContentPane.setBackground(new Color(204,222,235));
        }
        return jContentPane;
    }

    /**
     * This method initializes jpnlCentro
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJpnlCentro() {
        if (jpnlCentro == null) {
            FlowLayout flowLayout1 = new FlowLayout();
            flowLayout1.setHgap(10);
            flowLayout1.setVgap(40);
            jlblPassword = new JLabel();
            jlblPassword.setText("Contraseña: ");
            jlblPassword.setFont(new Font("Meiryo", Font.BOLD, 12));
            jlblPassword.setPreferredSize(new Dimension(85, 20));
            jlblPassword.setDisplayedMnemonic(KeyEvent.VK_C);
            jlblUsuario = new JLabel();
            jlblUsuario.setText("Cuenta : ");
            jlblUsuario.setDisplayedMnemonic(KeyEvent.VK_L);
            jlblUsuario.setFont(new Font("Meiryo", Font.BOLD, 12));
            jlblUsuario.setPreferredSize(new Dimension(85, 20));
            jpnlCentro = new JPanel();
            jpnlCentro.setLayout(flowLayout1);
            jpnlCentro.add(jlblUsuario, null);
            jpnlCentro.add(getJtxtUsuario(), null);
            jpnlCentro.add(jlblPassword, null);
            jpnlCentro.add(getJtxtPassword(), null);
            jpnlCentro.setBackground(new Color(204,222,235));
        }
        return jpnlCentro;
    }

    /**
     * This method initializes jpnlBotones
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJpnlBotones() {
        if (jpnlBotones == null) {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setHgap(15);
            jpnlBotones = new JPanel();
            jpnlBotones.setPreferredSize(new Dimension(30, 40));
            jpnlBotones.setLayout(flowLayout);
            jpnlBotones.add(getJbtnAceptar(), null);
            jpnlBotones.add(getJbtnCancelar(), null);
            jpnlBotones.setBackground(new Color(255,243,189));
        }
        return jpnlBotones;
    }

    /**
     * This method initializes jtxtUsuario
     *
     * @return javax.swing.JTextField
     */
    public JTextField getJtxtUsuario() {
        if (jtxtUsuario == null) {
            jtxtUsuario = new JTextField();
            jtxtUsuario.setPreferredSize(new Dimension(200, 28));
            jtxtUsuario.addKeyListener(control);
        }
        return jtxtUsuario;
    }

    /**
     * This method initializes jtxtPassword
     *
     * @return javax.swing.JPasswordField
     */
    public JPasswordField getJtxtPassword() {
        if (jtxtPassword == null) {
            jtxtPassword = new JPasswordField();
            jtxtPassword.setPreferredSize(new Dimension(200, 28));
            jtxtPassword.addKeyListener(control);
        }
        return jtxtPassword;
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
            jbtnAceptar.setToolTipText("Ingresar al Sistema Autenticandose");
            jbtnAceptar.setPreferredSize(new Dimension(115, 30));
            jbtnAceptar.setIcon(new ImageIcon(getClass().getResource("/imagenes/yast_security.png")));
            jbtnAceptar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            jbtnAceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
            jbtnCancelar.setToolTipText("Salir del sistema");
            jbtnCancelar.setPreferredSize(new Dimension(115, 30));
            jbtnCancelar.setIcon(new ImageIcon(getClass().getResource("/imagenes/botones/exit.png")));
            jbtnCancelar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            jbtnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            jbtnCancelar.setActionCommand("cancelar");
            jbtnCancelar.addActionListener(control);
        }
        return jbtnCancelar;
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

