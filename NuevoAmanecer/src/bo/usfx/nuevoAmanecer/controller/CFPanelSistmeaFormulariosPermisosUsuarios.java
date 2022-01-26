package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormularios;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormulariosPermisosUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.FPanelSistmeaFormulariosPermisosUsuarios;
import view.GuiSistemaFormulariosSeleccion;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.utils.FormUtilities;
import bo.usfx.utils.ModeloSistemaFormulariosPermisosUsuarios;

public class CFPanelSistmeaFormulariosPermisosUsuarios implements
		ActionListener, ListSelectionListener {
	
	private FPanelSistmeaFormulariosPermisosUsuarios panelPermisos;
	private CommonDao dao;
	private Usuarios usuario = null;
	
	int indice = 0;
	boolean esPrimeraVez = false;
	
	ModeloSistemaFormulariosPermisosUsuarios modeloSistemaFormulariosPermisosUsuarios = null;
	
	List<SistemaFormulariosPermisosUsuarios> listaSistemaFormulariosPermisosUsuarios = null;
	List<SistemaFormularios> listaSistemaFormularios = null; 
	private JInternalFrame parentInteranlFrame;
	private JFrame	parentFrame;
	private int idFormulario;
	
	
	public CFPanelSistmeaFormulariosPermisosUsuarios(FPanelSistmeaFormulariosPermisosUsuarios panel)
	{
		this.setPanelPermisos(panel);
		modeloSistemaFormulariosPermisosUsuarios = new ModeloSistemaFormulariosPermisosUsuarios();
		this.panelPermisos.getJTableUsuarioPermisos().setModel(modeloSistemaFormulariosPermisosUsuarios);
		this.panelPermisos.getJTableUsuarioPermisos().getColumnModel().getColumn(0).setPreferredWidth(250);
		this.panelPermisos.getJTableUsuarioPermisos().getColumnModel().getColumn(1).setPreferredWidth(100);
		this.panelPermisos.getJTableUsuarioPermisos().getColumnModel().getColumn(2).setPreferredWidth(100);
		this.panelPermisos.getJTableUsuarioPermisos().getColumnModel().getColumn(3).setPreferredWidth(100);
		this.panelPermisos.getJTableUsuarioPermisos().getColumnModel().getColumn(4).setPreferredWidth(100);
		this.panelPermisos.getJTableUsuarioPermisos().getColumnModel().getColumn(5).setPreferredWidth(100);
		this.panelPermisos.getJTableUsuarioPermisos().getColumnModel().getColumn(6).setPreferredWidth(100);
		
				
				
	}
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String accion = e.getActionCommand();
		System.out.println("Accion Seleccionada" + accion);
		if(accion.compareTo(panelPermisos.getJbtnAceptar().getActionCommand()) == 0)
		{
			try {
				int indice = -1;
				List<SistemaFormulariosPermisosUsuarios> listaAuxiliar = usuario.getListadoInterfacesPermisos();
				for (SistemaFormulariosPermisosUsuarios sistemaFormulariosPermisosUsuarios : listaAuxiliar) {
					indice = Collections.binarySearch(modeloSistemaFormulariosPermisosUsuarios.getData(), sistemaFormulariosPermisosUsuarios);
					if(indice < 0) //no existe
					{// se debe eliminar todos los permisos de este formulario						
						dao.delete(sistemaFormulariosPermisosUsuarios);						
					}
				}
				//Actualizamos los permisos actuales
				for (SistemaFormulariosPermisosUsuarios sistemaFormulariosPermisosUsuarios : modeloSistemaFormulariosPermisosUsuarios.getData()) {
					dao.edit(sistemaFormulariosPermisosUsuarios);
					
				}
//				cargarBitacora("A");
				usuario.setListadoInterfacesPermisos(modeloSistemaFormulariosPermisosUsuarios.getDataAsArrayList());
				habilitarBotones(true, false, false, false, false);
				modeloSistemaFormulariosPermisosUsuarios.permitirEdicion = false;
				FormUtilities.showMessage("OPERACION SATISFACTORIA", "Se guardaron correctamente los cambios realizados de los Permisos del usuario", parentInteranlFrame);				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				FormUtilities.showMessage("ERROR", "No se Pudo guardar Satisfactoriamente los cambios n" + e1.getMessage() , this.parentInteranlFrame);
			}
				
		}
		
		//a�adir Formularios o quitar los formularios
		if(accion.compareTo(panelPermisos.getJbtnAnadirFormularios().getActionCommand()) == 0)
		{
			GuiSistemaFormulariosSeleccion formSistemaFormulariosSeleccion = new GuiSistemaFormulariosSeleccion(parentInteranlFrame);

			
			formSistemaFormulariosSeleccion.control.setUsuario(getUsuario());
			
			if(!esPrimeraVez)
			{
//				listaSistemaFormularios.clear();
				for (SistemaFormularios sistemaForm : listaSistemaFormularios) {					
					sistemaForm.setEstado(modeloSistemaFormulariosPermisosUsuarios.existeSistemaFormulario(sistemaForm));
				}
				
			}
			formSistemaFormulariosSeleccion.control.setListaSistemaFormularios(listaSistemaFormularios);
			esPrimeraVez = false;
			formSistemaFormulariosSeleccion.setLocationRelativeTo(parentInteranlFrame);
			formSistemaFormulariosSeleccion.setVisible(true);
			
			if(formSistemaFormulariosSeleccion.control.operacionConfirmada)
			{
				for (SistemaFormularios sistemaForm : formSistemaFormulariosSeleccion.control.getListaSistemaFormularios()) {
					if(!modeloSistemaFormulariosPermisosUsuarios.existeSistemaFormulario(sistemaForm) && sistemaForm.isEstado())
					{
						this.modeloSistemaFormulariosPermisosUsuarios.addSistemaFormulariosPermisosUsuarios(sistemaForm, usuario.getCodigo_usuario());
					}
					
					if(modeloSistemaFormulariosPermisosUsuarios.existeSistemaFormulario(sistemaForm) && !sistemaForm.isEstado())
					{						
						this.modeloSistemaFormulariosPermisosUsuarios.removeSistemaFormulariosPermisosUsuarios(sistemaForm);
					}
				}
			}			
			formSistemaFormulariosSeleccion.dispose();
		}
		
		//cancelar la accion
		if(accion.compareTo(panelPermisos.getJbtnCancelar().getActionCommand()) == 0)
		{
			if(modeloSistemaFormulariosPermisosUsuarios != null && modeloSistemaFormulariosPermisosUsuarios.getRowCount() > 0)
			{
				modeloSistemaFormulariosPermisosUsuarios.setDataFromArrayList(usuario.getListadoInterfacesPermisos());
				try {
					listaSistemaFormularios = dao.getPermisosFormularios(new SistemaFormularios(), usuario.getCodigo_usuario());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				modeloSistemaFormulariosPermisosUsuarios.permitirEdicion = false;
				this.habilitarBotones(true, false, false, false, false);
			}
			
		}
		
		//eliminar un formulario
		if(accion.compareTo(panelPermisos.getJbtnEliminarFormulario().getActionCommand()) == 0)
		{
			int filaSeleccionada = panelPermisos.getJTableUsuarioPermisos().getSelectedRow();
			SistemaFormularios sistemaForm = null;
			if(filaSeleccionada >= 0)
				sistemaForm = modeloSistemaFormulariosPermisosUsuarios.removeSistemaFormulariosPermisosUsuarios(filaSeleccionada).getSistemaFormulario();
			Collections.sort(listaSistemaFormularios);
			if(sistemaForm != null && Collections.binarySearch(listaSistemaFormularios, sistemaForm) >= 0)
			{			
				filaSeleccionada = Collections.binarySearch(listaSistemaFormularios, sistemaForm);
				listaSistemaFormularios.get(filaSeleccionada).setEstado(false);
			}
		}
		
		//Modificar los permisos
		if(accion.compareTo(panelPermisos.getJbtnModificar().getActionCommand()) == 0)
		{
			System.out.println("modificar Permisos");
			habilitarBotones(false, true, true, true, true);
			this.modeloSistemaFormulariosPermisosUsuarios.permitirEdicion = true;
		}
	}
	
	public void habilitarBotones(boolean modificar, boolean aceptar, boolean anadir, boolean eliminar, boolean cancelar)
	{
		this.panelPermisos.getJbtnAceptar().setEnabled(aceptar);
		this.panelPermisos.getJbtnAnadirFormularios().setEnabled(anadir);
		this.panelPermisos.getJbtnCancelar().setEnabled(cancelar);
		this.panelPermisos.getJbtnEliminarFormulario().setEnabled(eliminar);
		this.panelPermisos.getJbtnModificar().setEnabled(modificar);
	}
	
	public void llenarTxtFields(Usuarios empleadoUsuario)
	{
		this.panelPermisos.getJtxtNombreUsuario().setText(empleadoUsuario.getNombreCompleto());
//		this.panelPermisos.getJtxtCargo().setText(empleadoUsuario.getCargo().getNombre());
//		this.panelPermisos.getJTextAreaDescripcion().setText(empleadoUsuario.getDescripcion());
		
		modeloSistemaFormulariosPermisosUsuarios.setDataFromArrayList(empleadoUsuario.getListadoInterfacesPermisos());
		try {
			listaSistemaFormularios = dao.getPermisosFormularios(new SistemaFormularios(), empleadoUsuario.getCodigo_usuario());
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		esPrimeraVez = true;
		this.usuario = empleadoUsuario;
	}
	
	
	
	
	
	public void onPanelShow()
	{
		modeloSistemaFormulariosPermisosUsuarios.setDataFromArrayList(usuario.getListadoInterfacesPermisos());
	}
	
	public void setPanelPermisos(FPanelSistmeaFormulariosPermisosUsuarios panelPermisos) {
		this.panelPermisos = panelPermisos;
	}
	public FPanelSistmeaFormulariosPermisosUsuarios getPanelPermisos() {
		return panelPermisos;
	}
	public void setDao(CommonDao dao) {
		this.dao = dao;
	}
	public CommonDao getDao() {
		return dao;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	public Usuarios getUsuario() {
		return usuario;
	}
	public void valueChanged(ListSelectionEvent e) {
//		// TODO Auto-generated method stub
//		if (e.getValueIsAdjusting())
//			return; // if you don't want to handle intermediate selections
//		ListSelectionModel rowSM = (ListSelectionModel) e.getSource();
//		indice = rowSM.getMinSelectionIndex();
//		System.out.println("Indice Actual" + indice);
//		if(indice >= 0)
//			llenarDatos(indice);
//		else
//			clearFields();
	}
	
	public void clearFields()
	{
		this.panelPermisos.getJtxtCargo().setText("");
		this.panelPermisos.getJtxtNombreUsuario().setText("");
		this.panelPermisos.getJTextAreaDescripcion().setText("");
		
		modeloSistemaFormulariosPermisosUsuarios.clear();
		
	}
	public void setParentInteranlFrame(JInternalFrame parentInteranlFrame) {
		this.parentInteranlFrame = parentInteranlFrame;
	}
	public JInternalFrame getParentInteranlFrame() {
		return parentInteranlFrame;
	}
	public void setParentFrame(JFrame parentFrame) {
		this.parentFrame = parentFrame;
	}
	public JFrame getParentFrame() {
		return parentFrame;
	}
	

	public void setIdFormulario(int idFormulario) {
		this.idFormulario = idFormulario;
	}
	


	
}
