/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.utils;
import bo.usfx.nuevoAmanecer.model.domain.ActividadEducativa;
import bo.usfx.nuevoAmanecer.model.domain.Vacunas;
import bo.usfx.nuevoAmanecer.model.domain.Ocupaciones;
import bo.usfx.nuevoAmanecer.model.domain.Vacunas;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ModeloVacunas implements TableModel {
	private LinkedList<Vacunas> datos = new LinkedList<Vacunas>();
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
			return Date.class;
		case 1:
			return Date.class;
		case 2:
			return Date.class;
		case 3:
			return Date.class;
		case 4:
			return Date.class;
                case 5:
			return Date.class;
                case 6:
                        return Date.class;
                case 7:
                        return Date.class;
                case 8:
                        return Date.class;
                case 9:
                        return Date.class;
		default:
			// Devuelve una clase Object por defecto.
			return Object.class;
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		// Devuelve el nombre de cada columna. Este texto aparecerá en la
		// cabecera de la tabla.
		switch (columnIndex) {
		case 0:
			return "BCG";
		case 1:
			return "Antipolio i";
		case 2:
			return "Antipolio 1a";
		case 3:
			return "Antipolio 2a";
		case 4:
			return "Antipolio 3a";
                case 5:
			return "Antipolio Refuerzo";
                    case 6:
                        return "PentaValente 1a";
                case 7:
                        return "PentaValente 2a";
                case 8:
                        return "PentaValente 3a";
                case 9:
                        return "PentaValente Refuerzo";
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
		Vacunas aux;

		// Se obtiene la Vacunas de la fila indicada
		aux = (Vacunas) (datos.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		switch (columnIndex) {
		case 0:
			return aux.getBcg();
		case 1:
			return aux.getAntipolio_i();
		case 2:
			return aux.getAntipolio_1a();
		case 3:
			return aux.getAntipolio_2a();
                case 4:
			return aux.getAntipolio_3a();
		case 5:
			return aux.getAntipolio_refuerzo();
                case 6:
			return aux.getPentavalente_1a();
		case 7:
			return aux.getPentavalente_2a();
                case 8:
			return aux.getPentavalente_3a();
		case 9:
			return aux.getPentavalente_ref();
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
//		listeners.remove(arg0);
	}

	@Override
	public void setValueAt(Object arg0, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		// Obtiene la Vacunas de la fila indicada
		Vacunas aux;
		aux = (Vacunas) (datos.get(rowIndex));

		// Cambia el campo de Vacunas que indica columnIndex, poniendole el
		// aValue que se nos pasa.
		switch (columnIndex) {
		case 0:

			break;
		case 1:
			aux.setNumero_caso((Integer) arg0);
			break;
		case 2:
//			aux.setFecha_registro(((Date) arg0));
			break;
		case 3:
//			aux.setPeso(((Float) arg0));
			break;
		case 4:
//			aux.setTalla(((Float) arg0));
			break;
                case 5:
//			aux.setSven(((String) arg0));
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
	 * Añade una Vacunas al final de la tabla
	 */
	public void addVacunas(Vacunas nuevaVacunas) {
		if (nuevaVacunas != null)
			// Añade la Vacunas al modelo
			datos.add(nuevaVacunas);
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
	 * Borra del modelo la Vacunas en la fila indicada
	 */
	public Vacunas removeVacunas(int fila) {
		// Se borra la fila
		@SuppressWarnings("unused")
		Vacunas per = datos.remove(fila);

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
			this.removeVacunas(0);
		}

	}

	/**
	 * Retorna la Vacunas de la Lista de datos de acuerdo a la fila que se pasa
	 * como parametro
	 *
	 * @param fila
	 * @return
	 */
	public Vacunas getVacunas(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera Vacunas de la lista de datos
	 *
	 * @return
	 */
	public Vacunas getFirtsVacunas() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima Vacunas de la lista de datos
	 *
	 * @return
	 */
	public Vacunas getLastVacunas() {
		return datos.getLast();
	}

	public void editarVacunas(Vacunas Vacunas, int fila) {
		// datos.remove(fila);
//		// datos.add(fila, Vacunas);
//		this.setValueAt(Vacunas.getIdVacunas(), fila, 0);
//		this.setValueAt(Vacunas.getCi(), fila, 1);
//		this.setValueAt(Vacunas.getNombre(), fila, 2);
//		this.setValueAt(Vacunas.getAppaterno(), fila, 3);
//		this.setValueAt(Vacunas.getApmaterno(), fila, 4);
//		this.setValueAt(Vacunas.getTelefono(), fila, 5);
//		this.setValueAt(Vacunas.getDireccion(), fila, 6);
//		this.setValueAt(Vacunas.getEmail(), fila, 7);
//		this.setValueAt(Vacunas.getParticular(), fila, 8);
//		this.setValueAt(Vacunas.getSexo(), fila, 9);
	}
}
