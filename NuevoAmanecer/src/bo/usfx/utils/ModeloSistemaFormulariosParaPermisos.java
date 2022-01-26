package bo.usfx.utils;


import bo.usfx.nuevoAmanecer.model.domain.SistemaFormularios;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class ModeloSistemaFormulariosParaPermisos implements TableModel {
	private LinkedList<SistemaFormularios> datos = new LinkedList<SistemaFormularios>();
	private LinkedList<TableModelListener> listeners = new LinkedList<TableModelListener>();
	public boolean esParaPermisos = false;
	public boolean esEditable = false;
	
	public ModeloSistemaFormulariosParaPermisos(boolean esParaPermisos)
	{
		this.esParaPermisos = esParaPermisos;
	}
	
	public ModeloSistemaFormulariosParaPermisos(boolean esParaPermisos, boolean esEditable)
	{
		this.esParaPermisos = esParaPermisos;
		this.esEditable = esEditable;
	}
	
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		listeners.add(arg0);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		if(esParaPermisos)
		{
			switch (columnIndex) {
			case 0:			
				return SistemaFormularios.class;
			case 1:
				return Boolean.class;			
			default:
				return Object.class;
			}
		}
		else
			switch (columnIndex) {
			case 0:			
				return Integer.class;
			case 1:
				return String.class;
			case 2:
				return String.class;		
			case 3:
				return Boolean.class;
			default:
				return Object.class;
			}
		
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return esParaPermisos ? 2 : 4;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		// Devuelve el nombre de cada columna. Este texto aparecer� en la
		// cabecera de la tabla.
		if(esParaPermisos)
			switch (columnIndex) {
			case 0:
				return "Formulario";
			case 1:			
				return "Seleccionado?";			
			default:
				return null;
			}
		else
			switch (columnIndex) {
			case 0:
				return "Id.";
			case 1:			
				return "Nombre";
			case 2:			
				return "Descripcion";
			case 3:			
				return "Habilitado?";		
			default:
				return null;
			}
			
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return datos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		SistemaFormularios aux;

		// Se obtiene la SistemaFormularios de la fila indicada
		aux = (SistemaFormularios) (datos.get(rowIndex));

		// Se obtiene el campo apropiado seg�n el valor de columnIndex
		if(esParaPermisos)
			switch (columnIndex) {
			case 0:
				return aux;
			case 1:
				return aux.isEstado();							
			default:
				return null;
			}
		else
			switch (columnIndex) {
			case 0:
				return aux.getCodigo_formulario();
			case 1:
				return aux.getNombre_formulario();
			case 2:			
				return aux.getDescripcion();
			case 3:
				return aux.isEstado();
			default:
				return null;
			}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		if(esParaPermisos)
			return column > 0;
		else
			return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		listeners.remove(arg0);
	}

	@Override
	public void setValueAt(Object arg0, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		// Obtiene la SistemaFormularios de la fila indicada
		SistemaFormularios aux;
		aux = (SistemaFormularios) (datos.get(rowIndex));

		// Cambia el campo de SistemaFormularios que indica columnIndex, poniendole el
		// aValue que se nos pasa.
		
		if(esParaPermisos)
			switch (columnIndex) {
			case 0:
//				aux.setPeriodoTiempo((PeriodoTiempo) arg0);
				break;
			case 1:
				aux.setEstado((Boolean) arg0 );							
				break;				
			default:
				break;
			}

		// Avisa a los suscriptores del cambio, creando un TableModelEvent ...
		TableModelEvent evento = new TableModelEvent(this, rowIndex, rowIndex,
				columnIndex);

		// ... y pas�ndoselo a los suscriptores.
		avisaSuscriptores(evento);

	}

	/**
	 * A�ade una SistemaFormularios al final de la tabla
	 */
	public void addSistemaFormularios(SistemaFormularios nuevaSistemaFormularios) {
		if (nuevaSistemaFormularios != null)
			// A�ade la SistemaFormularios al modelo
			datos.add(nuevaSistemaFormularios);
		else
			datos.add(null);
		// Avisa a los suscriptores creando un TableModelEvent...
		TableModelEvent evento;
		evento = new TableModelEvent(this, this.getRowCount() - 1, this
				.getRowCount() - 1, TableModelEvent.ALL_COLUMNS,
				TableModelEvent.INSERT);
		// ... y avisando a los suscriptores
		avisaSuscriptores(evento);
	}

	/**
	 * Borra del modelo la SistemaFormularios en la fila indicada
	 */
	public SistemaFormularios removeSistemaFormularios(int fila) {
		SistemaFormularios per = datos.remove(fila);

		// Y se avisa a los suscriptores, creando un TableModelEvent...
		TableModelEvent evento = new TableModelEvent(this, fila, fila,
				TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);

		// ... y pas�ndoselo a los suscriptores
		avisaSuscriptores(evento);
		return per;
	}

	/**
	 * Pasa a los suscriptores el evento.
	 */
	private void avisaSuscriptores(TableModelEvent evento) {
		int i;

		// Bucle para todos los suscriptores en la lista, se llama al metodo
		// tableChanged() de los mismos, pas�ndole el evento.
		for (i = 0; i < listeners.size(); i++)
			((TableModelListener) listeners.get(i)).tableChanged(evento);
	}

	public void clear() {
		while (this.getRowCount() > 0) {
			this.removeSistemaFormularios(0);
		}

	}

	/**
	 * Retorna la SistemaFormularios de la Lista de datos de acuerdo a la fila que se pasa
	 * como parametro
	 * 
	 * @param fila
	 * @return
	 */
	public SistemaFormularios getSistemaFormularios(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera SistemaFormularios de la lista de datos
	 * 
	 * @return
	 */
	public SistemaFormularios getFirtsSistemaFormularios() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima SistemaFormularios de la lista de datos
	 * 
	 * @return
	 */
	public SistemaFormularios getLastSistemaFormularios() {
		return datos.getLast();
	}

	public void editarSistemaFormularios(SistemaFormularios SistemaFormularios, int fila) {
		// datos.remove(fila);
		// datos.add(fila, SistemaFormularios);
//		this.setValueAt(SistemaFormularios.getIdpersona(), fila, 0);
//		this.setValueAt(SistemaFormularios.getCi(), fila, 1);
//		this.setValueAt(SistemaFormularios.getNombre(), fila, 2);
//		this.setValueAt(SistemaFormularios.getAppaterno(), fila, 3);
//		this.setValueAt(SistemaFormularios.getApmaterno(), fila, 4);
//		this.setValueAt(SistemaFormularios.getTelefono(), fila, 5);
//		this.setValueAt(SistemaFormularios.getDireccion(), fila, 6);
//		this.setValueAt(SistemaFormularios.getMaxlogro(), fila, 7);
//		this.setValueAt(SistemaFormularios.getFechaing(), fila, 8);
//		this.setValueAt(SistemaFormularios.getSexo(), fila, 9);
		//this.setValueAt(SistemaFormularios.getCargo(), fila, 10);
	}
	
	public void SeleccionarTodos(boolean estado)
	{
		for(int i = 0; i< datos.size(); i++)
			this.setValueAt(estado, i, 1);
	}
	
	public LinkedList<SistemaFormularios> getData()
	{
		return datos;
	}
	
	public List<SistemaFormularios> getDataAsList()
	{
		ArrayList<SistemaFormularios> listaSistemaFormulario = new ArrayList<SistemaFormularios>();
		for (SistemaFormularios sistemaFormularios : datos) {
			listaSistemaFormulario.add(sistemaFormularios);
		}
		
		return listaSistemaFormulario;
	}
	
	public void setDataFromArrayList(List<SistemaFormularios> listaFormulariosNuevos)
        {
            this.datos.clear();
            for (SistemaFormularios sistemaFormularios2 : listaFormulariosNuevos) {
                this.addSistemaFormularios(sistemaFormularios2);
            }
        }

        public boolean existeAlgunElmentoSeleccionado(){
            for (SistemaFormularios sistemaFormularios : datos) {
                if(sistemaFormularios.isEstado())
                    return true;
            }
            return false;
        }
}
