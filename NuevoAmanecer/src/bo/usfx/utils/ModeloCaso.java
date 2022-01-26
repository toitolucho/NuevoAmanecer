/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package bo.usfx.utils;
import bo.usfx.nuevoAmanecer.model.domain.Casos;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ModeloCaso implements TableModel {
	private LinkedList<Casos> datos = new LinkedList<Casos>();
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
                    return String.class;
		case 3:
                    return String.class;
                case 4:
                    return String.class;
		default:
			// Devuelve una clase Object por defecto.
			return Object.class;
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
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
			return "Fecha";
		case 2:
			return "Afiliado";
		case 3:
			return "Patrocinador 1";
                case 4:
			return "Patrocinador 2";
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
		Casos aux;

		// Se obtiene la Casos de la fila indicada
		aux = (Casos) (datos.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		switch (columnIndex) {
		case 0:
			return aux.getNumero_caso();
		case 1:
			return aux.getFecha_registro();
		case 2:
			return aux.getAfiliado().getNombreCompleto();
		case 3:
			return aux.getPatrocinador1().getNombreCompleto();
                case 4:
                        return aux.getPatrocinador1().getNombreCompleto2();
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
		// Obtiene la Casos de la fila indicada
		Casos aux;
		aux = (Casos) (datos.get(rowIndex));

		// Cambia el campo de Casos que indica columnIndex, poniendole el
		// aValue que se nos pasa.
		switch (columnIndex) {
		case 0:
                        aux.setNumero_caso((Integer) arg0);
			break;
		case 1:
			aux.setFecha_registro(((Date) arg0));
			break;
		case 2:
//			aux.setNumero_caso(((Integer) arg0));
			break;
		case 3:
//			aux.setCodigo_padrino(((Integer) arg0));
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
	 * Añade una Casos al final de la tabla
	 */
	public void addCasos(Casos nuevaCasos) {
		if (nuevaCasos != null)
			// Añade la Casos al modelo
			datos.add(nuevaCasos);
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
	 * Borra del modelo la Casos en la fila indicada
	 */
	public Casos removeCasos(int fila) {
		// Se borra la fila
		@SuppressWarnings("unused")
		Casos per = datos.remove(fila);

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
			this.removeCasos(0);
		}

	}

	/**
	 * Retorna la Casos de la Lista de datos de acuerdo a la fila que se pasa
	 * como parametro
	 *
	 * @param fila
	 * @return
	 */
	public Casos getCasos(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera Casos de la lista de datos
	 *
	 * @return
	 */
	public Casos getFirtsCasos() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima Casos de la lista de datos
	 *
	 * @return
	 */
	public Casos getLastCasos() {
		return datos.getLast();
	}

	public void editarCasos(Casos Casos, int fila) {
		// datos.remove(fila);
//		// datos.add(fila, Casos);
//		this.setValueAt(Casos.getIdCasos(), fila, 0);
//		this.setValueAt(Casos.getCi(), fila, 1);
//		this.setValueAt(Casos.getNombre(), fila, 2);
//		this.setValueAt(Casos.getAppaterno(), fila, 3);
//		this.setValueAt(Casos.getApmaterno(), fila, 4);
//		this.setValueAt(Casos.getTelefono(), fila, 5);
//		this.setValueAt(Casos.getDireccion(), fila, 6);
//		this.setValueAt(Casos.getEmail(), fila, 7);
//		this.setValueAt(Casos.getParticular(), fila, 8);
//		this.setValueAt(Casos.getSexo(), fila, 9);
	}
}
