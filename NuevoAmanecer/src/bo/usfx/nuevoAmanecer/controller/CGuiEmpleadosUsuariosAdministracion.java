package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.GuiEmpleadosUsuariosAdministracion;

import bo.usfx.utils.FormUtilities;
import bo.usfx.utils.ModeloUsuarios;


public class CGuiEmpleadosUsuariosAdministracion extends MouseAdapter implements ActionListener, DocumentListener, ListSelectionListener  {
	
	
	ModeloUsuarios modeloEmpleado = null;
	GuiEmpleadosUsuariosAdministracion formFEmpleadosUsuariosAdministracion;
	int indice = 0;
	List<Usuarios> listaEmpleado = null;
	
	private Usuarios usuario = null;
	private CommonDao dao;
	private Connection conexion = null;
	private int idFormulario;
	
	
	public CGuiEmpleadosUsuariosAdministracion(GuiEmpleadosUsuariosAdministracion form)
	{
		this.formFEmpleadosUsuariosAdministracion = form;
		modeloEmpleado = new ModeloUsuarios();
		this.formFEmpleadosUsuariosAdministracion.getJTableUsuarios().setModel(modeloEmpleado);
		this.formFEmpleadosUsuariosAdministracion.getJTableUsuarios().getSelectionModel().addListSelectionListener(this);
		this.formFEmpleadosUsuariosAdministracion.getJTableUsuarios().addMouseListener(this);
		
		this.formFEmpleadosUsuariosAdministracion.getJpnlUsuariosAdminitracion().control.setDao(dao);
		this.formFEmpleadosUsuariosAdministracion.getJpnlUsuariosAdminitracion().control.setUsuario(getUsuario());
		this.formFEmpleadosUsuariosAdministracion.getJpnlUsuariosAdminitracion().control.setIdFormulario(idFormulario);
//		this.formFEmpleadosUsuariosAdministracion.getJpnlUsuariosAdminitracion().control.setParentInteranlFrame(formFEmpleadosUsuariosAdministracion);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String accion = e.getActionCommand();
		
		if(accion.compareTo("buscarUsuario") == 0)
		{
			buscarPersona();
		}
		
	}

	public void buscarPersona() {
				
		Usuarios empleado = new Usuarios();
//		empleado.set(formFEmpleadosUsuariosAdministracion.getJtxtTextoBusqueda().getText());
		empleado.setNombres(formFEmpleadosUsuariosAdministracion.getJtxtTextoBusqueda().getText());


		
		modeloEmpleado.clear();
		try {
			listaEmpleado = dao.findObjects(empleado);
		} catch (SQLException e) {
			e.printStackTrace();
			FormUtilities.showMessage("Registro No encontrado", "No se encontr� ningun empleado con la descripci�n dada", formFEmpleadosUsuariosAdministracion);
			
		}
		if (listaEmpleado.size() > 0) {
			formFEmpleadosUsuariosAdministracion.getJTableUsuarios().getSelectionModel().removeListSelectionListener(this);
			modeloEmpleado.clear();
			for (int i = 0; i < listaEmpleado.size(); i++) {
				modeloEmpleado.addUsuarios(listaEmpleado.get(i));
			}
			
			formFEmpleadosUsuariosAdministracion.getJTableUsuarios().getSelectionModel().addListSelectionListener(this);

		}
		else
		{
			FormUtilities.showMessage("Registro No encontrado", "No se encontr� ningun empleado con la descripci�n dada", formFEmpleadosUsuariosAdministracion);			
			modeloEmpleado.clear();
		}
		formFEmpleadosUsuariosAdministracion.getJtxtTextoBusqueda().grabFocus();
		formFEmpleadosUsuariosAdministracion.getJtxtTextoBusqueda().selectAll();
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if (e.getValueIsAdjusting())
			return; // if you don't want to handle intermediate selections
		ListSelectionModel rowSM = (ListSelectionModel) e.getSource();
		indice = rowSM.getMinSelectionIndex();		
		if(indice >= 0){
			Usuarios empleado = modeloEmpleado.getUsuarios(indice);
			this.formFEmpleadosUsuariosAdministracion.getJpnlUsuariosAdminitracion().control.setUsuario(empleado);
			this.formFEmpleadosUsuariosAdministracion.getJpnlUsuariosAdminitracion().control.setDao(dao);
			this.formFEmpleadosUsuariosAdministracion.getJpnlUsuariosAdminitracion().control.setIdFormulario(idFormulario);
			this.formFEmpleadosUsuariosAdministracion.getJpnlUsuariosAdminitracion().control.llenarTxtFields(empleado);
			this.formFEmpleadosUsuariosAdministracion.getJpnlUsuariosAdminitracion().control.habilitarBotones(true, false, false, false, false);
			
		}			
		else
			this.formFEmpleadosUsuariosAdministracion.getJpnlUsuariosAdminitracion().control.clearFields();
	}
	
	public void mousePressed(MouseEvent e) {
        if(e.getClickCount() == 2)
        {
            this.formFEmpleadosUsuariosAdministracion.getJTabbedPane().setSelectedIndex(1);
        }
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setDao(CommonDao dao) {
		this.dao = dao;
	}

	public CommonDao getDao() {
		return dao;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public Connection getConexion() {
		return conexion;
	}
	
	
	public void setIdFormulario(int idFormulario) {
		this.idFormulario = idFormulario;
	}
}
