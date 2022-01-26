package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.dao.ibatis.SqlMapCommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.utils.FormUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

import view.GuiAutenticacion;
import view.GuiPrincipal;


public class CGuiAutenticacion implements ActionListener, KeyListener {

	private GuiAutenticacion formAutenticacion = null;
	private static CommonDao dao;
	private Usuarios usuario = null;
	private Date fechaHoraServidor;
	/**
	 * Constructor de la Clase
	 * 
	 * @param formAutenticacion
	 */
	public CGuiAutenticacion(GuiAutenticacion formAutenticacion) {
		super();
		this.formAutenticacion = formAutenticacion;
		usuario = new Usuarios();
                usuario.setCodigo_usuario(-1);
		setDao(new SqlMapCommonDao());
		
	}

	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();		
		if (action.compareTo("aceptar") == 0) {
			autenticar();
		}
		if (action.compareTo("cancelar") == 0) {
			System.exit(0);
		}

	}

	public void setFormAutenticacion(GuiAutenticacion formAutenticacion) {
		this.formAutenticacion = formAutenticacion;
	}

	public GuiAutenticacion getFormAutenticacion() {
		return formAutenticacion;
	}

	public static void setDao(CommonDao dao) {
		CGuiAutenticacion.dao = dao;
	}

	public static CommonDao getDao() {
		return dao;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			autenticar();
		}
	}

	@SuppressWarnings("deprecation")
	public void autenticar() {
                if(formAutenticacion.getJtxtUsuario().getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(formAutenticacion,
                            "Aún no ha ingresado la cuenta de Usuario",
                            formAutenticacion.getTitle(), JOptionPane.INFORMATION_MESSAGE);
                    formAutenticacion.getJtxtUsuario().grabFocus();
                    return;
                }
                if(formAutenticacion.getJtxtPassword().getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(formAutenticacion,
                            "Aún no ha ingresado la Contraseña del Usuario",
                            formAutenticacion.getTitle(), JOptionPane.INFORMATION_MESSAGE);
                    formAutenticacion.getJtxtPassword().grabFocus();
                    return;
                }

		if (!formAutenticacion.getJtxtPassword().getText().isEmpty()
				&& !formAutenticacion.getJtxtUsuario().getText().isEmpty()) {
			usuario.setNombre_cuenta(formAutenticacion.getJtxtUsuario().getText());
			usuario.setContrasenia(formAutenticacion.getJtxtPassword().getText());
			try {
				if (dao.obtenerObjeto(usuario) != null) {
					
					// Mostrar el principal y los menus habilitados para este
					// usuario
					
					usuario = (Usuarios) dao.obtenerObjeto(usuario);
					dao.ControlarEdadesAfiliados(usuario);
					
					fechaHoraServidor = dao.obtenerFechaHoraServidor();
					GuiPrincipal formPrincipal = new GuiPrincipal();
					formPrincipal.control.setDao(dao);
					formPrincipal.control.setUsuario(usuario);
					formPrincipal.control.setFechaHoraServidor(fechaHoraServidor);
//                                        formPrincipal.control.onFormShwon();
//					FormUtilities.maximizar(formPrincipal);
					// FormUtilities.centrar(formPrincipal);
					this.formAutenticacion.dispose();
					formPrincipal.setVisible(true);
//					formPrincipal.control.mostrarDatosConfiguracion();
				} else
					FormUtilities
							.showMessage(
									"Autenticacion Incorrecta",
									"Probablemente sus datos esten escritos Incorrectamente",
									formAutenticacion);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				FormUtilities.showMessage("Error en la Conecci�n", e.getMessage(), formAutenticacion);
				e.printStackTrace();
			}
		} else {
			FormUtilities.showMessage("Error de Autenticacion",
					"Probablemente sus datos esten escritos Incorrectamente",
					formAutenticacion);
		}
	}
}
