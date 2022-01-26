/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package bo.usfx.utils;
import bo.usfx.nuevoAmanecer.model.domain.ActividadEducativa;
import bo.usfx.nuevoAmanecer.model.domain.Correspondencia;
import bo.usfx.nuevoAmanecer.model.domain.Ocupaciones;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ModeloCorrespondencia implements TableModel {
	private LinkedList<Correspondencia> datos = new LinkedList<Correspondencia>();
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
			return Integer.class;
		case 3:
			return Integer.class;
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
			return "Fecha";
		case 2:
			return "Nro Caso";
		case 3:
			return "Nro Patrocinador";
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
		Correspondencia aux;

		// Se obtiene la Correspondencia de la fila indicada
		aux = (Correspondencia) (datos.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		switch (columnIndex) {
		case 0:
			return aux.getCodigo_correspondencia();
		case 1:
			return aux.getFecha();
		case 2:
			return aux.getNumero_caso();
		case 3:
			return aux.getCodigo_padrino();
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
		// Obtiene la Correspondencia de la fila indicada
		Correspondencia aux;
		aux = (Correspondencia) (datos.get(rowIndex));

		// Cambia el campo de Correspondencia que indica columnIndex, poniendole el
		// aValue que se nos pasa.
		switch (columnIndex) {
		case 0:
                        aux.setCodigo_correspondencia((Integer) arg0);
			break;
		case 1:
			aux.setFecha(((Date) arg0));
			break;
		case 2:
			aux.setNumero_caso(((Integer) arg0));
			break;
		case 3:
			aux.setCodigo_padrino(((Integer) arg0));
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
	 * Añade una Correspondencia al final de la tabla
	 */
	public void addCorrespondencia(Correspondencia nuevaCorrespondencia) {
		if (nuevaCorrespondencia != null)
			// Añade la Correspondencia al modelo
			datos.add(nuevaCorrespondencia);
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
	 * Borra del modelo la Correspondencia en la fila indicada
	 */
	public Correspondencia removeCorrespondencia(int fila) {
		// Se borra la fila
		@SuppressWarnings("unused")
		Correspondencia per = datos.remove(fila);

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
			this.removeCorrespondencia(0);
		}

	}

	/**
	 * Retorna la Correspondencia de la Lista de datos de acuerdo a la fila que se pasa
	 * como parametro
	 *
	 * @param fila
	 * @return
	 */
	public Correspondencia getCorrespondencia(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera Correspondencia de la lista de datos
	 *
	 * @return
	 */
	public Correspondencia getFirtsCorrespondencia() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima Correspondencia de la lista de datos
	 *
	 * @return
	 */
	public Correspondencia getLastCorrespondencia() {
		return datos.getLast();
	}

	public void editarCorrespondencia(Correspondencia Correspondencia, int fila) {
		// datos.remove(fila);
//		// datos.add(fila, Correspondencia);
//		this.setValueAt(Correspondencia.getIdCorrespondencia(), fila, 0);
//		this.setValueAt(Correspondencia.getCi(), fila, 1);
//		this.setValueAt(Correspondencia.getNombre(), fila, 2);
//		this.setValueAt(Correspondencia.getAppaterno(), fila, 3);
//		this.setValueAt(Correspondencia.getApmaterno(), fila, 4);
//		this.setValueAt(Correspondencia.getTelefono(), fila, 5);
//		this.setValueAt(Correspondencia.getDireccion(), fila, 6);
//		this.setValueAt(Correspondencia.getEmail(), fila, 7);
//		this.setValueAt(Correspondencia.getParticular(), fila, 8);
//		this.setValueAt(Correspondencia.getSexo(), fila, 9);
	}
}
