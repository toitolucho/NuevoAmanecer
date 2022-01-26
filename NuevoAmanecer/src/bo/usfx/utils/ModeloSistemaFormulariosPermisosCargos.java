package bo.usfx.utils;

import bo.usfx.nuevoAmanecer.model.domain.SistemaFormularios;
import bo.usfx.nuevoAmanecer.model.domain.SistemaFormulariosPermisosCargos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class ModeloSistemaFormulariosPermisosCargos    implements TableModel {
	private LinkedList<SistemaFormulariosPermisosCargos> datos = new LinkedList<SistemaFormulariosPermisosCargos>();
	private LinkedList<TableModelListener> listeners = new LinkedList<TableModelListener>();        
	public boolean permitirEdicion =false;
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		listeners.add(arg0);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:
			return SistemaFormularios.class;
		case 1:
			return Boolean.class;
		case 2:
			return Boolean.class;
		case 3:
			return Boolean.class;
//		case 4:
//			return Boolean.class;
//		case 5:
//			return Boolean.class;
//		case 6:
//			return Boolean.class;

		default:
			return Object.class;
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;//cambiar a 7 en caso de aumentar, navegacion, anulacion, y reportes
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		// Devuelve el nombre de cada columna. Este texto aparecer� en la
		// cabecera de la tabla.
		switch (columnIndex) {
		case 0:
			return "Formulario";
		case 1:
			return "Insertar";
		case 2:
			return "Editar";
		case 3:
			return "Eliminar";
//		case 4:
//			return "Navegacion";
//		case 5:
//			return "Reportes";
//		case 6:
//			return "Anulacion";

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
		SistemaFormulariosPermisosCargos aux;

		// Se obtiene la SistemaFormulariosPermisosCargos de la fila indicada
		aux = (SistemaFormulariosPermisosCargos) (datos.get(rowIndex));

		// Se obtiene el campo apropiado seg�n el valor de columnIndex
		switch (columnIndex) {
		case 0:
			return aux.getSistemaFormulario();
		case 1:
			return aux.isPermitir_insertar();
		case 2:
			return aux.isPermitir_editar();
		case 3:
			return aux.isPemitir_eliminar();
//		case 4:
//			return aux.isPermitir_navegar();
//		case 5:
//			return aux.isPermitir_reportes();
//		case 6:
//			return aux.isPermitir_anular();
		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		if(!permitirEdicion)
			return false;
		else
		{
			return column > 0;
		}
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		listeners.remove(arg0);
	}

	@Override
	public void setValueAt(Object arg0, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		// Obtiene la SistemaFormulariosPermisosCargos de la fila indicada
		SistemaFormulariosPermisosCargos aux;
		aux = (SistemaFormulariosPermisosCargos) (datos.get(rowIndex));

		// Cambia el campo de SistemaFormulariosPermisosCargos que indica columnIndex, poniendole el
		// aValue que se nos pasa.
		switch (columnIndex) {
		case 0:
//			aux.setPeriodoTiempo((PeriodoTiempo) arg0);
			break;
		case 1:
			aux.setPermitir_insertar((Boolean) arg0);
			break;
		case 2:
			aux.setPermitir_editar((Boolean) arg0);
			break;
		case 3:
			aux.setPemitir_eliminar((Boolean) arg0);
			break;
//		case 4:
//			aux.setPermitir_navegar((Boolean) arg0);
//			break;
//		case 5:
//			aux.setPermitir_reportes((Boolean) arg0);
//			break;
//		case 6:
//			aux.setPermitir_anular((Boolean) arg0);
//			break;
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
	 * A�ade una SistemaFormulariosPermisosCargos al final de la tabla
	 */
	public void addSistemaFormulariosPermisosCargos(SistemaFormulariosPermisosCargos nuevaSistemaFormulariosPermisosCargos) {
		if (nuevaSistemaFormulariosPermisosCargos != null)
			// A�ade la SistemaFormulariosPermisosCargos al modelo
			datos.add(nuevaSistemaFormulariosPermisosCargos);
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

	public void addSistemaFormulariosPermisosCargos(SistemaFormularios nuevaSistemaFormularios, String CodigoCargo) {
		if (nuevaSistemaFormularios != null)
			// A�ade la SistemaFormulariosPermisosCargos al modelo
			datos.add( new SistemaFormulariosPermisosCargos(nuevaSistemaFormularios, CodigoCargo));
		else
			datos.add(null);
		// Avisa a los suscriptores creando un TableModelEvent...
		TableModelEvent evento;
		evento = new TableModelEvent(this, this.getRowCount() - 1, this
				.getRowCount() - 1, TableModelEvent.ALL_COLUMNS,
				TableModelEvent.INSERT);
		// ... y avisando a los suscriptores
		avisaSuscriptores(evento);
		Collections.sort(datos);
	}

	/**
	 * Borra del modelo la SistemaFormulariosPermisosCargos en la fila indicada
	 */
	public SistemaFormulariosPermisosCargos removeSistemaFormulariosPermisosCargos(SistemaFormularios nuevaSistemaFormularios) {
		// Se borra la fila
		@SuppressWarnings("unused")
		SistemaFormulariosPermisosCargos aux = new SistemaFormulariosPermisosCargos(nuevaSistemaFormularios, "");

		int fila = Collections.binarySearch(datos, aux);
		SistemaFormulariosPermisosCargos per = datos.remove(fila);

		// Y se avisa a los suscriptores, creando un TableModelEvent...
		TableModelEvent evento = new TableModelEvent(this, fila, fila,
				TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);

		// ... y pas�ndoselo a los suscriptores
		avisaSuscriptores(evento);
		return per;
	}


	public SistemaFormulariosPermisosCargos removeSistemaFormulariosPermisosCargos(int fila) {
		// Se borra la fila
		@SuppressWarnings("unused")
		SistemaFormulariosPermisosCargos per = datos.remove(fila);

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
			this.removeSistemaFormulariosPermisosCargos(0);
		}

	}

	/**
	 * Retorna la SistemaFormulariosPermisosCargos de la Lista de datos de acuerdo a la fila que se pasa
	 * como parametro
	 *
	 * @param fila
	 * @return
	 */
	public SistemaFormulariosPermisosCargos getSistemaFormulariosPermisosCargos(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera SistemaFormulariosPermisosCargos de la lista de datos
	 *
	 * @return
	 */
	public SistemaFormulariosPermisosCargos getFirtsSistemaFormulariosPermisosCargos() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima SistemaFormulariosPermisosCargos de la lista de datos
	 *
	 * @return
	 */
	public SistemaFormulariosPermisosCargos getLastSistemaFormulariosPermisosCargos() {
		return datos.getLast();
	}

	public void editarSistemaFormulariosPermisosCargos(SistemaFormulariosPermisosCargos SistemaFormulariosPermisosCargos, int fila) {
		// datos.remove(fila);
		// datos.add(fila, SistemaFormulariosPermisosCargos);
		this.setValueAt(SistemaFormulariosPermisosCargos.isPermitir_insertar(), fila, 1);
		this.setValueAt(SistemaFormulariosPermisosCargos.isPemitir_eliminar(), fila, 2);
		this.setValueAt(SistemaFormulariosPermisosCargos.isPermitir_editar(), fila, 3);
//		this.setValueAt(SistemaFormulariosPermisosCargos.isPermitir_navegar(), fila, 4);
//		this.setValueAt(SistemaFormulariosPermisosCargos.isPermitir_reportes(), fila, 5);
//		this.setValueAt(SistemaFormulariosPermisosCargos.isPermitir_anular(), fila, 6);
	}

	public List<SistemaFormulariosPermisosCargos> getDataAsArrayList()
	{
		List<SistemaFormulariosPermisosCargos> listaSistemaFormulariosPermisosCargos = new ArrayList<SistemaFormulariosPermisosCargos>();
		for (SistemaFormulariosPermisosCargos SistemaFormulariosPermisosCargos : datos) {
			listaSistemaFormulariosPermisosCargos.add(SistemaFormulariosPermisosCargos);
		}
		return listaSistemaFormulariosPermisosCargos;
	}

	public LinkedList<SistemaFormulariosPermisosCargos> getData()
	{
		return datos;
	}

	public void setDataFromArrayList(List<SistemaFormulariosPermisosCargos> listaSistemaFormulariosPermisosCargos)
	{
		this.datos.clear();
		for (SistemaFormulariosPermisosCargos SistemaFormulariosPermisosCargos : listaSistemaFormulariosPermisosCargos) {
			this.addSistemaFormulariosPermisosCargos(SistemaFormulariosPermisosCargos);
		}
	}

	public boolean existeSistemaFormulariosPermisosCargos(SistemaFormulariosPermisosCargos SistemaFormulariosPermisosCargos)
	{
		Collections.sort(datos);
		return Collections.binarySearch(datos, SistemaFormulariosPermisosCargos) >= 0;
	}

	public boolean existeSistemaFormulario(SistemaFormularios sistemaFormulario)
	{
		Collections.sort(datos);
		return Collections.binarySearch(datos, new SistemaFormulariosPermisosCargos(sistemaFormulario, "")) >= 0;
	}

    public void setPermitirEdicion(boolean permitirEdicion) {
        this.permitirEdicion = permitirEdicion;
    }

        
}
