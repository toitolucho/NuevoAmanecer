/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.utils;
import bo.usfx.nuevoAmanecer.model.domain.ActividadEducativa;
import bo.usfx.nuevoAmanecer.model.domain.Eccd;
import bo.usfx.nuevoAmanecer.model.domain.Ocupaciones;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ModeloEccd implements TableModel {
	private LinkedList<Eccd> datos = new LinkedList<Eccd>();
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
			return Date.class;
		case 2:
			return Float.class;
		case 3:
			return Float.class;
		case 4:
			return String.class;
                case 5:
			return String.class;
		default:
			// Devuelve una clase Object por defecto.
			return Object.class;
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		// Devuelve el nombre de cada columna. Este texto aparecerá en la
		// cabecera de la tabla.
		switch (columnIndex) {
		case 0:
			return "Nro Caso";
		case 1:
			return "Fecha Reg.";
		case 2:
			return "Peso";
		case 3:
			return "Talla";
		case 4:
			return "Peso/Talla";
                case 5:
			return "SVEN";
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
		Eccd aux;

		// Se obtiene la Eccd de la fila indicada
		aux = (Eccd) (datos.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		switch (columnIndex) {
		case 0:
			return aux.getNumero_caso();
		case 1:
			return aux.getFecha_registro();
		case 2:
			return aux.getPeso();
		case 3:
			return aux.getTalla();
                case 4:
			return aux.getPeso_talla();
		case 5:
			return aux.getSven();
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
		// Obtiene la Eccd de la fila indicada
		Eccd aux;
		aux = (Eccd) (datos.get(rowIndex));

		// Cambia el campo de Eccd que indica columnIndex, poniendole el
		// aValue que se nos pasa.
		switch (columnIndex) {
		case 0:

			break;
		case 1:
			aux.setNumero_caso((Integer) arg0);
			break;
		case 2:
			aux.setFecha_registro(((Date) arg0));
			break;
		case 3:
			aux.setPeso(((Float) arg0));
			break;
		case 4:
			aux.setPeso_talla(((String) arg0));
			break;
                case 5:
			aux.setSven(((String) arg0));
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
	 * Añade una Eccd al final de la tabla
	 */
	public void addEccd(Eccd nuevaEccd) {
		if (nuevaEccd != null)
			// Añade la Eccd al modelo
			datos.add(nuevaEccd);
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
	 * Borra del modelo la Eccd en la fila indicada
	 */
	public Eccd removeEccd(int fila) {
		// Se borra la fila
		@SuppressWarnings("unused")
		Eccd per = datos.remove(fila);

		// Y se avisa a los suscriptores, creando un TableModelEvent...
		TableModelEvent evento = new TableModelEvent(this, fila, fila,
				TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);

		// ... y pasándoselo a los suscriptores
		avisaSuscriptores(evento);
		return per;
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
			this.removeEccd(0);
		}

	}

	/**
	 * Retorna la Eccd de la Lista de datos de acuerdo a la fila que se pasa
	 * como parametro
	 *
	 * @param fila
	 * @return
	 */
	public Eccd getEccd(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera Eccd de la lista de datos
	 *
	 * @return
	 */
	public Eccd getFirtsEccd() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima Eccd de la lista de datos
	 *
	 * @return
	 */
	public Eccd getLastEccd() {
		return datos.getLast();
	}

	public void editarEccd(Eccd Eccd, int fila) {
		// datos.remove(fila);
//		// datos.add(fila, Eccd);
//		this.setValueAt(Eccd.getIdEccd(), fila, 0);
//		this.setValueAt(Eccd.getCi(), fila, 1);
//		this.setValueAt(Eccd.getNombre(), fila, 2);
//		this.setValueAt(Eccd.getAppaterno(), fila, 3);
//		this.setValueAt(Eccd.getApmaterno(), fila, 4);
//		this.setValueAt(Eccd.getTelefono(), fila, 5);
//		this.setValueAt(Eccd.getDireccion(), fila, 6);
//		this.setValueAt(Eccd.getEmail(), fila, 7);
//		this.setValueAt(Eccd.getParticular(), fila, 8);
//		this.setValueAt(Eccd.getSexo(), fila, 9);
	}
}
