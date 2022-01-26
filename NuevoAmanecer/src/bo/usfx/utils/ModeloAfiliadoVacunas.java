/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.utils;

import bo.usfx.nuevoAmanecer.model.domain.AfiliadoVacuna;
import bo.usfx.nuevoAmanecer.model.domain.Vacunas2;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ModeloAfiliadoVacunas implements TableModel {
	private LinkedList<AfiliadoVacuna> datos = new LinkedList<AfiliadoVacuna>();
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
			return Date.class;
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
			return "Vacuna";
		case 1:
			return "Fecha";
		case 2:
			return "Observaciones";
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
		AfiliadoVacuna aux;

		// Se obtiene la AfiliadoVacuna de la fila indicada
		aux = (AfiliadoVacuna) (datos.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		switch (columnIndex) {
		case 0:
			return aux.getVacuna().getNombre_vacuna2();
		case 1:
			return aux.getFecha_vacuna2();
		case 2:
			return aux.getObservaciones();
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
		// Obtiene la AfiliadoVacuna de la fila indicada
		AfiliadoVacuna aux;
		aux = (AfiliadoVacuna) (datos.get(rowIndex));

		// Cambia el campo de AfiliadoVacuna que indica columnIndex, poniendole el
		// aValue que se nos pasa.
		switch (columnIndex) {
		case 0:
//                        aux.setCodigo_vacuna2((Integer) arg0);
			break;
		case 1:
			aux.setFecha_vacuna2(((Date) arg0));
			break;
		case 2:
			aux.setObservaciones(((String) arg0));
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
	 * Añade una AfiliadoVacuna al final de la tabla
	 */
	public void addAfiliadoVacuna(AfiliadoVacuna nuevaAfiliadoVacuna) {
		if (nuevaAfiliadoVacuna != null)
			// Añade la AfiliadoVacuna al modelo
			datos.add(nuevaAfiliadoVacuna);
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
	 * Borra del modelo la AfiliadoVacuna en la fila indicada
	 */
	public AfiliadoVacuna removeAfiliadoVacuna(int fila) {
		// Se borra la fila
		@SuppressWarnings("unused")
		AfiliadoVacuna per = datos.remove(fila);

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
			this.removeAfiliadoVacuna(0);
		}

	}

	/**
	 * Retorna la AfiliadoVacuna de la Lista de datos de acuerdo a la fila que se pasa
	 * como parametro
	 *
	 * @param fila
	 * @return
	 */
	public AfiliadoVacuna getAfiliadoVacuna(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera AfiliadoVacuna de la lista de datos
	 *
	 * @return
	 */
	public AfiliadoVacuna getFirtsAfiliadoVacuna() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima AfiliadoVacuna de la lista de datos
	 *
	 * @return
	 */
	public AfiliadoVacuna getLastAfiliadoVacuna() {
		return datos.getLast();
	}

	public void editarAfiliadoVacuna(AfiliadoVacuna AfiliadoVacuna, int fila) {
//            this.removeAfiliadoVacuna(fila);
//            this.addAfiliadoVacuna(AfiliadoVacuna);
		// datos.remove(fila);
//		// datos.add(fila, AfiliadoVacuna);
		this.setValueAt(AfiliadoVacuna.getFecha_vacuna2(), fila, 1);
		this.setValueAt(AfiliadoVacuna.getObservaciones(), fila, 2);
//		this.setValueAt(AfiliadoVacuna.getAppaterno(), fila, 3);
//		this.setValueAt(AfiliadoVacuna.getApmaterno(), fila, 4);
//		this.setValueAt(AfiliadoVacuna.getTelefono(), fila, 5);
//		this.setValueAt(AfiliadoVacuna.getDireccion(), fila, 6);
//		this.setValueAt(AfiliadoVacuna.getEmail(), fila, 7);
//		this.setValueAt(AfiliadoVacuna.getParticular(), fila, 8);
//		this.setValueAt(AfiliadoVacuna.getSexo(), fila, 9);
	}

        public boolean existeVacuna(Vacunas2 vacuna)
        {
            AfiliadoVacuna vacunaAfiliado = new AfiliadoVacuna();
            vacunaAfiliado.setVacuna(vacuna);
            return Collections.binarySearch(datos, vacunaAfiliado) >= 0;
        }
}
