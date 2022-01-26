/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.utils;

import bo.usfx.nuevoAmanecer.model.domain.Imparte;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ModeloEncargadosImpartidoresProgramas implements TableModel {
    private LinkedList<Imparte> datos = new LinkedList<Imparte>();
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
			return "Código Encargado";
		case 1:
			return "Nombre Completo";
		case 2:
			return "Profesión";
		case 3:
			return "Sexo";

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
		Imparte aux;

		// Se obtiene la Imparte de la fila indicada
		aux = (Imparte) (datos.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		switch (columnIndex) {
		case 0:
			return aux.getCi();
		case 1:
			return aux.getEncargado().getNombreCompleto();
		case 2:
			return aux.getEncargado().getProfesion();
		case 3:
			return aux.getEncargado().getSexo().equals("F") ? "FEMENINO" : "MASCULINO";

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
		// Obtiene la Imparte de la fila indicada


		// Avisa a los suscriptores del cambio, creando un TableModelEvent ...
		TableModelEvent evento = new TableModelEvent(this, rowIndex, rowIndex,
				columnIndex);

		// ... y pasándoselo a los suscriptores.
		avisaSuscriptores(evento);

	}

	/**
	 * Añade una Imparte al final de la tabla
	 */
	public void addImparte(Imparte nuevaImparte) {
		if (nuevaImparte != null)
			// Añade la Imparte al modelo
			datos.add(nuevaImparte);
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
	 * Borra del modelo la Imparte en la fila indicada
	 */
	public Imparte removeImparte(int fila) {
		// Se borra la fila
		@SuppressWarnings("unused")
		Imparte per = datos.remove(fila);

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
			this.removeImparte(0);
		}

	}

	/**
	 * Retorna la Imparte de la Lista de datos de acuerdo a la fila que se pasa
	 * como parametro
	 *
	 * @param fila
	 * @return
	 */
	public Imparte getImparte(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera Imparte de la lista de datos
	 *
	 * @return
	 */
	public Imparte getFirtsImparte() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima Imparte de la lista de datos
	 *
	 * @return
	 */
	public Imparte getLastImparte() {
		return datos.getLast();
	}

	public void editarImparte(Imparte Imparte, int fila) {
		// datos.remove(fila);
//		// datos.add(fila, Imparte);
//		this.setValueAt(Imparte.getIdImparte(), fila, 0);
//		this.setValueAt(Imparte.getCi(), fila, 1);
//		this.setValueAt(Imparte.getNombre(), fila, 2);
//		this.setValueAt(Imparte.getAppaterno(), fila, 3);
//		this.setValueAt(Imparte.getApmaterno(), fila, 4);
//		this.setValueAt(Imparte.getTelefono(), fila, 5);
//		this.setValueAt(Imparte.getDireccion(), fila, 6);
//		this.setValueAt(Imparte.getEmail(), fila, 7);
//		this.setValueAt(Imparte.getParticular(), fila, 8);
//		this.setValueAt(Imparte.getSexo(), fila, 9);
	}

        public boolean existeAfiliado(Imparte afiliadoImpartente)
        {
            Collections.sort(datos);
            return Collections.binarySearch(datos, afiliadoImpartente) >= 0;
        }

    public LinkedList<Imparte> getDatos() {
        return datos;
    }
}
