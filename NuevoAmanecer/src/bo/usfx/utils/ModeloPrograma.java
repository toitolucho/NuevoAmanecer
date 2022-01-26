/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.utils;
import bo.usfx.nuevoAmanecer.model.domain.ActividadEducativa;
import bo.usfx.nuevoAmanecer.model.domain.Programas;
import bo.usfx.nuevoAmanecer.model.domain.Ocupaciones;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ModeloPrograma implements TableModel {
	private LinkedList<Programas> datos = new LinkedList<Programas>();
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
		case 4:
			return Date.class;
                case 5:
			return Date.class;
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
			return "Cod.";
		case 1:
			return "Nombre";
		case 2:
			return "Descripción";
		case 3:
			return "Justificación";
		case 4:
			return "Del";
                case 5:
			return "Al";
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
		Programas aux;

		// Se obtiene la Programas de la fila indicada
		aux = (Programas) (datos.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		switch (columnIndex) {
		case 0:
			return aux.getCodigo_programa();
		case 1:
			return aux.getNombre_actividad();
		case 2:
			return aux.getDescripcion();
		case 3:
			return aux.getJustificacion();
                case 4:
			return aux.getFecha_programa();
		case 5:
			return aux.getFecha_culminacion();
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
		// Obtiene la Programas de la fila indicada
		Programas aux;
		aux = (Programas) (datos.get(rowIndex));

		// Cambia el campo de Programas que indica columnIndex, poniendole el
		// aValue que se nos pasa.
		switch (columnIndex) {
		case 0:
                        aux.setCodigo_programa((Integer) arg0);
			break;
		case 1:
			aux.setNombre_actividad(((String) arg0));
			break;
		case 2:
			aux.setDescripcion(((String) arg0));
			break;
		case 3:
			aux.setJustificacion(((String) arg0));
			break;
		case 4:
			aux.setFecha_programa(((Date) arg0));
			break;
                case 5:
			aux.setFecha_culminacion(((Date) arg0));
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
	 * Añade una Programas al final de la tabla
	 */
	public void addProgramas(Programas nuevaProgramas) {
		if (nuevaProgramas != null)
			// Añade la Programas al modelo
			datos.add(nuevaProgramas);
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
	 * Borra del modelo la Programas en la fila indicada
	 */
	public Programas removeProgramas(int fila) {
		// Se borra la fila
		@SuppressWarnings("unused")
		Programas per = datos.remove(fila);

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
			this.removeProgramas(0);
		}

	}

	/**
	 * Retorna la Programas de la Lista de datos de acuerdo a la fila que se pasa
	 * como parametro
	 *
	 * @param fila
	 * @return
	 */
	public Programas getProgramas(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera Programas de la lista de datos
	 *
	 * @return
	 */
	public Programas getFirtsProgramas() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima Programas de la lista de datos
	 *
	 * @return
	 */
	public Programas getLastProgramas() {
		return datos.getLast();
	}

	public void editarProgramas(Programas Programas, int fila) {
		// datos.remove(fila);
//		// datos.add(fila, Programas);
//		this.setValueAt(Programas.getIdProgramas(), fila, 0);
//		this.setValueAt(Programas.getCi(), fila, 1);
//		this.setValueAt(Programas.getNombre(), fila, 2);
//		this.setValueAt(Programas.getAppaterno(), fila, 3);
//		this.setValueAt(Programas.getApmaterno(), fila, 4);
//		this.setValueAt(Programas.getTelefono(), fila, 5);
//		this.setValueAt(Programas.getDireccion(), fila, 6);
//		this.setValueAt(Programas.getEmail(), fila, 7);
//		this.setValueAt(Programas.getParticular(), fila, 8);
//		this.setValueAt(Programas.getSexo(), fila, 9);
	}
}
