/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.utils;

import bo.usfx.nuevoAmanecer.model.domain.Vacunas2;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ModeloVacunas2 implements TableModel {
	private LinkedList<Vacunas2> datos = new LinkedList<Vacunas2>();
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
                case 3:
                        return String.class;
		default:
			// Devuelve una clase Object por defecto.
			return Object.class;
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		// Devuelve el nombre de cada columna. Este texto aparecerá en la
		// cabecera de la tabla.
		switch (columnIndex) {
		case 0:
			return "Cod.";
		case 1:
			return "Nombre";
		case 2:
			return "Limite de Meses";
                case 3:
			return "Descripcion";
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
		Vacunas2 aux;

		// Se obtiene la Vacunas2 de la fila indicada
		aux = (Vacunas2) (datos.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		switch (columnIndex) {
		case 0:
			return aux.getCodigo_vacuna2();
		case 1:
			return aux.getNombre_vacuna2();
                case 2:
			return aux.getLimiteDeMeses();
		case 3:
			return aux.getDescripcion_vacuna2();
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
		// Obtiene la Vacunas2 de la fila indicada
		Vacunas2 aux;
		aux = (Vacunas2) (datos.get(rowIndex));

		// Cambia el campo de Vacunas2 que indica columnIndex, poniendole el
		// aValue que se nos pasa.
		switch (columnIndex) {
		case 0:
                        aux.setCodigo_vacuna2((Integer) arg0);
			break;
		case 1:
			aux.setNombre_vacuna2(((String) arg0));
			break;
		case 3:
			aux.setDescripcion_vacuna2(((String) arg0));
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
	 * Añade una Vacunas2 al final de la tabla
	 */
	public void addVacunas2(Vacunas2 nuevaVacunas2) {
		if (nuevaVacunas2 != null)
			// Añade la Vacunas2 al modelo
			datos.add(nuevaVacunas2);
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
	 * Borra del modelo la Vacunas2 en la fila indicada
	 */
	public Vacunas2 removeVacunas2(int fila) {
		// Se borra la fila
		@SuppressWarnings("unused")
		Vacunas2 per = datos.remove(fila);

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
			this.removeVacunas2(0);
		}

	}

	/**
	 * Retorna la Vacunas2 de la Lista de datos de acuerdo a la fila que se pasa
	 * como parametro
	 *
	 * @param fila
	 * @return
	 */
	public Vacunas2 getVacunas2(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera Vacunas2 de la lista de datos
	 *
	 * @return
	 */
	public Vacunas2 getFirtsVacunas2() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima Vacunas2 de la lista de datos
	 *
	 * @return
	 */
	public Vacunas2 getLastVacunas2() {
		return datos.getLast();
	}

	public void editarVacunas2(Vacunas2 Vacunas2, int fila) {
		// datos.remove(fila);
//		// datos.add(fila, Vacunas2);		
		this.setValueAt(Vacunas2.getDescripcion_vacuna2(), fila, 2);
		this.setValueAt(Vacunas2.getNombre_vacuna2(), fila, 1);
//		this.setValueAt(Vacunas2.getAppaterno(), fila, 3);
//		this.setValueAt(Vacunas2.getApmaterno(), fila, 4);
//		this.setValueAt(Vacunas2.getTelefono(), fila, 5);
//		this.setValueAt(Vacunas2.getDireccion(), fila, 6);
//		this.setValueAt(Vacunas2.getEmail(), fila, 7);
//		this.setValueAt(Vacunas2.getParticular(), fila, 8);
//		this.setValueAt(Vacunas2.getSexo(), fila, 9);
	}
}
