/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.utils;


import java.util.Date;
import java.util.LinkedList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import bo.usfx.nuevoAmanecer.model.domain.Encargados;

public class ModeloEncargado  implements TableModel {
	private LinkedList<Encargados> datos = new LinkedList<Encargados>();
	private LinkedList<TableModelListener> listeners = new LinkedList<TableModelListener>();

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
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return String.class;

		default:
			// Devuelve una clase Object por defecto.
			return Object.class;
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		// Devuelve el nombre de cada columna. Este texto aparecerá en la
		// cabecera de la tabla.
		switch (columnIndex) {
		case 0:
			return "Ci Cod.";
		case 1:
			return "Nombre Completo";
		case 2:
			return "Domicilio";

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
		Encargados aux;

		// Se obtiene la Asociacion de la fila indicada
		aux = (Encargados) (datos.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		switch (columnIndex) {
		case 0:
			return aux.getCi();
		case 1:
			return aux.getNombreCompleto();
		case 2:
			return aux.getDomicilio();

		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
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
		// Obtiene la Asociacion de la fila indicada
		Encargados aux;
		aux = (Encargados) (datos.get(rowIndex));

		// Cambia el campo de Asociacion que indica columnIndex, poniendole el
		// aValue que se nos pasa.
		switch (columnIndex) {
		case 0:
			aux.setCi((Integer) arg0);
			break;
		case 1:
			aux.setNombres((String) arg0);
			break;
		case 2:
			aux.setDomicilio(((String) arg0));
		default:
			break;
		}

		// Avisa a los suscriptores del cambio, creando un TableModelEvent ...
		TableModelEvent evento = new TableModelEvent(this, rowIndex, rowIndex,
				columnIndex);

		// ... y pasándoselo a los suscriptores.
		avisaSuscriptores(evento);

	}

	/**
	 * Añade una Asociacion al final de la tabla
	 */
	public void addEncargados(Encargados nuevaEncargados) {
		if (nuevaEncargados != null)
			// Añade la Asociacion al modelo
			datos.add(nuevaEncargados);
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
	 * Borra del modelo la Asociacion en la fila indicada
	 */
	public Encargados removeEncargados(int fila) {
		// Se borra la fila

		System.out.println("From removeAsociacion" + fila);
		Encargados Encargados = datos.remove(fila);

		// Y se avisa a los suscriptores, creando un TableModelEvent...
		TableModelEvent evento = new TableModelEvent(this, fila, fila,
				TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);

		// ... y pasándoselo a los suscriptores
		avisaSuscriptores(evento);
		return Encargados;
	}

        public void editEcargado(Encargados encargadoEdicion, int fila)
        {
            this.setValueAt(encargadoEdicion.getCi(), fila, 0);
            this.setValueAt(encargadoEdicion.getNombreCompleto(), fila, 1);
            this.setValueAt(encargadoEdicion.getDomicilio(), fila, 2);
        }

	/**
	 * Pasa a los suscriptores el evento.
	 */
	private void avisaSuscriptores(TableModelEvent evento) {
		int i;

		// Bucle para todos los suscriptores en la lista, se llama al metodo
		// tableChanged() de los mismos, pasándole el evento.
		for (i = 0; i < listeners.size(); i++)
			((TableModelListener) listeners.get(i)).tableChanged(evento);
	}

	public void clear() {
		while (this.getRowCount() > 0) {
			this.removeEncargados(0);
		}

	}

	/**
	 * Retorna la Asociacion de la Lista de datos de acuerdo a la fila que se
	 * pasa como parametro
	 *
	 * @param fila
	 * @return
	 */
	public Encargados getEncargados(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera Asociacion de la lista de datos
	 *
	 * @return
	 */
	public Encargados getFirtsEncargados() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima Asociacion de la lista de datos
	 *
	 * @return
	 */
	public Encargados getLastEncargados() {
		return datos.getLast();
	}
}
