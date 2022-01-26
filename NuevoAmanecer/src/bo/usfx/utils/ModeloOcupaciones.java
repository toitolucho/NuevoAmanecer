/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.utils;


import java.util.LinkedList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import bo.usfx.nuevoAmanecer.model.domain.Ocupaciones;
import java.util.List;

public class ModeloOcupaciones  implements TableModel {
	private LinkedList<Ocupaciones> datos = new LinkedList<Ocupaciones>();
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
			return String.class;
		case 1:
			return String.class;
		
		default:
			// Devuelve una clase Object por defecto.
			return Object.class;
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		// Devuelve el nombre de cada columna. Este texto aparecerá en la
		// cabecera de la tabla.
		switch (columnIndex) {
		case 0:
			return "Nro";
		case 1:
			return "Ocupacion";

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
		Ocupaciones aux;

		// Se obtiene la Asociacion de la fila indicada
		aux = (Ocupaciones) (datos.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		switch (columnIndex) {
		case 0:
			return aux.getCodigo_ocupacion();
		case 1:
			return aux.getNombre_ocupacion();
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
		Ocupaciones aux;
		aux = (Ocupaciones) (datos.get(rowIndex));

		// Cambia el campo de Asociacion que indica columnIndex, poniendole el
		// aValue que se nos pasa.
		switch (columnIndex) {
		case 0:
			aux.setCodigo_ocupacion((Integer) arg0);
			break;
		case 1:
			aux.setNombre_ocupacion((String) arg0);
			break;
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
	public void addOcupaciones(Ocupaciones nuevaOcupaciones) {
		if (nuevaOcupaciones != null)
			// Añade la Asociacion al modelo
			datos.add(nuevaOcupaciones);
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
	public Ocupaciones removeOcupaciones(int fila) {
		Ocupaciones Ocupaciones = datos.remove(fila);

		// Y se avisa a los suscriptores, creando un TableModelEvent...
		TableModelEvent evento = new TableModelEvent(this, fila, fila,
				TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);

		// ... y pasándoselo a los suscriptores
		avisaSuscriptores(evento);
		return Ocupaciones;
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
			this.removeOcupaciones(0);
		}

	}

	/**
	 * Retorna la Asociacion de la Lista de datos de acuerdo a la fila que se
	 * pasa como parametro
	 *
	 * @param fila
	 * @return
	 */
	public Ocupaciones getOcupaciones(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera Asociacion de la lista de datos
	 *
	 * @return
	 */
	public Ocupaciones getFirtsOcupaciones() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima Asociacion de la lista de datos
	 *
	 * @return
	 */
	public Ocupaciones getLastOcupaciones() {
		return datos.getLast();
	}

        public void setDatosFromLista(List<Ocupaciones> listaNuevosOcupaciones)
        {
            clear();
            for (Ocupaciones OcupacionesNuevo : listaNuevosOcupaciones) {
                this.addOcupaciones(OcupacionesNuevo);
            }
        }
        public void editarOcupaciones(Ocupaciones OcupacionesEditar, int fila) {
            this.setValueAt(OcupacionesEditar.getNombre_ocupacion(), fila, 1);

    }
}
