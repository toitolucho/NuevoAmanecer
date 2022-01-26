/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.utils;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import view.GuiPrincipal;


public class FormUtilities {

	public static int nroSolicitudInicio = 1;
	public static int nroAutorizacionAlquilerInicio = 1;
	public static int horaInicioNocturno = 19;
	public static int tiempoToleranciaReserva = 8;

	public static void centrarLogo(JDesktopPane contenedor, JLabel Logo)
	{
		Toolkit toolkit = contenedor.getToolkit();
		Dimension screenSize = toolkit.getScreenSize();
//		Logo.setLocation((screenSize.width-contenedor.getSize().width)/2,(screenSize.height-contenedor.getSize().height)/2);
		Logo.setBounds(contenedor.getLocationOnScreen().x, contenedor.getLocationOnScreen().y, contenedor.getSize().width, contenedor.getSize().height);
	}

        public static String formatearDate(Date fechaHora)
        {
            String CadenaRetorno = "";
            if (fechaHora != null) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = "";
                fecha = df.format(fechaHora);
                CadenaRetorno = fecha;
            }
            return CadenaRetorno;
        }

	public static void centrar(JFrame ventana)//JDesktopPane
	{
		//ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		ventana.setLocation((screenSize.width-ventana.getSize().width)/2,(screenSize.height-ventana.getSize().height)/2);
	}

        public static void centrar2(Window ventanaHija, Window parent)
        {
            ventanaHija.setLocationRelativeTo(parent);
        }

	public static void centrar(JInternalFrame ventana)
	{
		//ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//ventana.getParent().getWidth()JDesktopPane
		System.out.println(" Nombre componente : " + ventana.getParent().getName());
		Toolkit toolkit = ventana.getParent().getToolkit().getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		ventana.setLocation((screenSize.width-ventana.getSize().width)/2,(screenSize.height-ventana.getSize().height)/2 - 100);
	}

	public static void centrar(JDialog ventana)
	{
		//ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		ventana.setLocation((screenSize.width-ventana.getSize().width)/2,(screenSize.height-ventana.getSize().height)/2);
	}

	public static void maximizar(JFrame ventana)
	{
//		Toolkit toolkit = Toolkit.getDefaultToolkit();
//		Dimension screenSize = toolkit.getScreenSize();
//		ventana.setSize(new Dimension(screenSize.width,screenSize.height - 30));
//
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		/*
		 * The next line determines if the taskbar (win) is covered if
		 * unremarked, the task will not be covered by the maximized JFRAME.
		 */
		ventana.setMaximizedBounds(env.getMaximumWindowBounds());
		ventana.setExtendedState(ventana.getExtendedState() | ventana.MAXIMIZED_BOTH);
	}

	public static void showMessage(String titulo,String mensaje, JFrame ventana)
	{
		JOptionPane.showMessageDialog(ventana, mensaje,titulo,JOptionPane.INFORMATION_MESSAGE);
	}
	public static void showMessage(String titulo,String mensaje, JInternalFrame ventana)
	{
		JOptionPane.showInternalMessageDialog(ventana, mensaje,titulo,JOptionPane.INFORMATION_MESSAGE);
	}
	public static void showMessage(String titulo,String mensaje, JDialog ventana)
	{
		JOptionPane.showMessageDialog(ventana, mensaje,titulo,JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showMessage(String titulo,String mensaje, Component ventana)
	{
		JOptionPane.showMessageDialog(ventana, mensaje,titulo,JOptionPane.INFORMATION_MESSAGE);
	}

	public static int showConfirmDialog(String titulo,String mensaje, Component ventana, boolean fromDialog)
	{
		if(!fromDialog)
			return JOptionPane.showConfirmDialog(ventana, mensaje, titulo,
				  JOptionPane.YES_NO_OPTION);
		else
		{
			final JOptionPane optionPane = new JOptionPane(
	                mensaje,
	                JOptionPane.QUESTION_MESSAGE,
	                JOptionPane.YES_NO_OPTION);

			final JDialog dialog = new JDialog((JDialog) ventana, "Confirmación", true);
			dialog.setContentPane(optionPane);
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

			optionPane.addPropertyChangeListener(
				    new PropertyChangeListener() {
				        public void propertyChange(PropertyChangeEvent e) {
				            String prop = e.getPropertyName();

				            if (dialog.isVisible()
				             && (e.getSource() == optionPane)
				             && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
				                dialog.setVisible(false);
				            }
				        }
				    });
				dialog.pack();
				dialog.setLocationRelativeTo(ventana);
				dialog.setVisible(true);

				return  ((Integer)optionPane.getValue()).intValue();
		}
	}



	public static String getClassName(Class c) {
	    String FQClassName = c.getName();
	    int firstChar;
	    firstChar = FQClassName.lastIndexOf ('.') + 1;
	    if ( firstChar > 0 ) {
	      FQClassName = FQClassName.substring ( firstChar );
	      }
	    return FQClassName;
	    }

        public static String obtenerRutaLocal()
        {            
            return GuiPrincipal.class.getResource("GuiPrincipal.class").getPath().substring(1).replaceAll("view/GuiPrincipal.class", "fotografiasPersonas/").trim().replace("%20", " ");
        }

        public static String obtenerRutaLocalReportes()
        {
            return GuiPrincipal.class.getResource("GuiPrincipal.class").getPath().substring(1).replaceAll("view/GuiPrincipal.class", "").trim().replace("%20", " ");
        }

}
