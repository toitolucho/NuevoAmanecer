/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.utils;

import bo.usfx.nuevoAmanecer.model.domain.Integra;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class ModeloIntegrantesIntegraProgramas implements TableModel {
    private LinkedList<Integra> datos = new LinkedList<Integra>();
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
			return "Código Integrante";
		case 1:
			return "Nombre Completo";
		case 2:
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
		Integra aux;

		// Se obtiene la Integra de la fila indicada
		aux = (Integra) (datos.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		switch (columnIndex) {
		case 0:
			return aux.getCodigo_integrante();
		case 1:
			return aux.getIntegrante().getNombreCompleto();
		case 2:
			return aux.getIntegrante().getSexoCadena();

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
		// Obtiene la Integra de la fila indicada


		// Avisa a los suscriptores del cambio, creando un TableModelEvent ...
		TableModelEvent evento = new TableModelEvent(this, rowIndex, rowIndex,
				columnIndex);

		// ... y pasándoselo a los suscriptores.
		avisaSuscriptores(evento);

	}

	/**
	 * Añade una Integra al final de la tabla
	 */
	public void addIntegra(Integra nuevaIntegra) {
		if (nuevaIntegra != null)
			// Añade la Integra al modelo
			datos.add(nuevaIntegra);
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
	 * Borra del modelo la Integra en la fila indicada
	 */
	public Integra removeIntegra(int fila) {
		// Se borra la fila
		@SuppressWarnings("unused")
		Integra per = datos.remove(fila);

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
			this.removeIntegra(0);
		}

	}

	/**
	 * Retorna la Integra de la Lista de datos de acuerdo a la fila que se pasa
	 * como parametro
	 *
	 * @param fila
	 * @return
	 */
	public Integra getIntegra(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera Integra de la lista de datos
	 *
	 * @return
	 */
	public Integra getFirtsIntegra() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima Integra de la lista de datos
	 *
	 * @return
	 */
	public Integra getLastIntegra() {
		return datos.getLast();
	}

	public void editarIntegra(Integra Integra, int fila) {
		// datos.remove(fila);
//		// datos.add(fila, Integra);
//		this.setValueAt(Integra.getIdIntegra(), fila, 0);
//		this.setValueAt(Integra.getCi(), fila, 1);
//		this.setValueAt(Integra.getNombre(), fila, 2);
//		this.setValueAt(Integra.getAppaterno(), fila, 3);
//		this.setValueAt(Integra.getApmaterno(), fila, 4);
//		this.setValueAt(Integra.getTelefono(), fila, 5);
//		this.setValueAt(Integra.getDireccion(), fila, 6);
//		this.setValueAt(Integra.getEmail(), fila, 7);
//		this.setValueAt(Integra.getParticular(), fila, 8);
//		this.setValueAt(Integra.getSexo(), fila, 9);
	}

        public boolean existeIntegrante(Integra afiliadoIntegrante)
        {
            Collections.sort(datos);
            return Collections.binarySearch(datos, afiliadoIntegrante) >= 0;
        }

    public LinkedList<Integra> getDatos() {
        return datos;
    }
}




