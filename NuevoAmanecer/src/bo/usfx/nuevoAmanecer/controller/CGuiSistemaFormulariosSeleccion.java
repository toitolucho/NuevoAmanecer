package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.domain.SistemaFormularios;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.utils.ModeloSistemaFormulariosParaPermisos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;


import view.GuiSistemaFormulariosSeleccion;

public class CGuiSistemaFormulariosSeleccion implements ActionListener,
		TableModelListener{
	
	GuiSistemaFormulariosSeleccion formSistemaFormulariosSeleccion;
	ModeloSistemaFormulariosParaPermisos modeloSistemaFormulariosParaPermisos = null;
	public boolean operacionConfirmada = false;
	
	private List<SistemaFormularios> listaSistemaFormularios = null;	
	private Usuarios usuario;
	
	public CGuiSistemaFormulariosSeleccion(GuiSistemaFormulariosSeleccion formSistemaFormulariosSeleccion)
	{
		this.formSistemaFormulariosSeleccion = formSistemaFormulariosSeleccion;
		modeloSistemaFormulariosParaPermisos = new ModeloSistemaFormulariosParaPermisos(true);
		this.formSistemaFormulariosSeleccion.getJTableFormularios().setModel(modeloSistemaFormulariosParaPermisos);
		this.formSistemaFormulariosSeleccion.getJTableFormularios().getColumnModel().getColumn(0).setPreferredWidth(300);
		this.formSistemaFormulariosSeleccion.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stubS
		String accion = e.getActionCommand();
		if(accion.compareTo(formSistemaFormulariosSeleccion.getJbtnAceptar().getActionCommand()) == 0)
		{
			this.operacionConfirmada = true;
			this.formSistemaFormulariosSeleccion.setVisible(false);
		}
		
		if(accion.compareTo(formSistemaFormulariosSeleccion.getJbtnCancelar().getActionCommand()) == 0)
		{
			this.operacionConfirmada = false;
			this.formSistemaFormulariosSeleccion.setVisible(false);
		}
		
		if(accion.compareTo(formSistemaFormulariosSeleccion.getJcheckSeleccionarTodos().getActionCommand()) == 0)
		{
			modeloSistemaFormulariosParaPermisos.SeleccionarTodos(formSistemaFormulariosSeleccion.getJcheckSeleccionarTodos().isSelected());
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub

	}
	
	public void onFormShow(){
		for (SistemaFormularios sistemaForm : listaSistemaFormularios) {
			this.modeloSistemaFormulariosParaPermisos.addSistemaFormularios(sistemaForm);
		}
	}

	public void setListaSistemaFormularios(List<SistemaFormularios> listaSistemaFormularios) {
		this.listaSistemaFormularios = listaSistemaFormularios;
		
		for (SistemaFormularios sistemaFormularios : listaSistemaFormularios) {
			modeloSistemaFormulariosParaPermisos.addSistemaFormularios(sistemaFormularios);
		}
	}

	public List<SistemaFormularios> getListaSistemaFormularios() {
		listaSistemaFormularios = modeloSistemaFormulariosParaPermisos.getDataAsList();
		return listaSistemaFormularios;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public Usuarios getUsuario() {
		return usuario;
	}
	
}
