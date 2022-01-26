/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.utils;
import bo.usfx.nuevoAmanecer.model.domain.SaludPublica;
import bo.usfx.nuevoAmanecer.model.domain.SaludPublica;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ModeloSaludPublica implements TableModel {
	private LinkedList<SaludPublica> datos = new LinkedList<SaludPublica>();
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
			return Boolean.class;
		case 3:
			return Boolean.class;
		case 4:
			return Boolean.class;
                case 5:
			return Boolean.class;
                case 6:
                        return Integer.class;
                case 7:
                        return Integer.class;
                case 8:
                        return String.class;
                case 9:
                        return String.class;
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
			return "Nro Familia";
		case 1:
			return "Fecha";
		case 2:
			return "IRA";
		case 3:
			return "IDA";
		case 4:
			return "Acc. AGUA";
                case 5:
			return "Discretas";
                case 6:
                        return "Nro Cuartos";
                case 7:
                        return "Nro Dormitorios";
                case 8:
                        return "Tipo Vivienda";
                case 9:
                        return "Material Vivienda";
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
		SaludPublica aux;

		// Se obtiene la SaludPublica de la fila indicada
		aux = (SaludPublica) (datos.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		switch (columnIndex) {

		case 0:
			return aux.getNumero_familia();
                case 1:
                        return aux.getFecha_registro();
		case 2:
			return aux.isIra();
		case 3:
			return aux.isEda();
		case 4:
			return aux.isAgua();
                case 5:
			return aux.isExcretas();
		case 6:
			return aux.getNumero_habitaciones();
                case 7:
			return aux.getDormitorios();
		case 8:
			return aux.getTipo_vivienda();
                case 9:
			return aux.getMaterial_vivienda();
		
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
		// Obtiene la SaludPublica de la fila indicada
		SaludPublica aux;
		aux = (SaludPublica) (datos.get(rowIndex));

		// Cambia el campo de SaludPublica que indica columnIndex, poniendole el
		// aValue que se nos pasa.
		switch (columnIndex) {
		case 0:

			break;
		case 1:
//			aux.setNumero_caso((Integer) arg0);
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
	 * Añade una SaludPublica al final de la tabla
	 */
	public void addSaludPublica(SaludPublica nuevaSaludPublica) {
		if (nuevaSaludPublica != null)
			// Añade la SaludPublica al modelo
			datos.add(nuevaSaludPublica);
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
	 * Borra del modelo la SaludPublica en la fila indicada
	 */
	public SaludPublica removeSaludPublica(int fila) {
		// Se borra la fila
		@SuppressWarnings("unused")
		SaludPublica per = datos.remove(fila);

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
			this.removeSaludPublica(0);
		}

	}

	/**
	 * Retorna la SaludPublica de la Lista de datos de acuerdo a la fila que se pasa
	 * como parametro
	 *
	 * @param fila
	 * @return
	 */
	public SaludPublica getSaludPublica(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera SaludPublica de la lista de datos
	 *
	 * @return
	 */
	public SaludPublica getFirtsSaludPublica() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima SaludPublica de la lista de datos
	 *
	 * @return
	 */
	public SaludPublica getLastSaludPublica() {
		return datos.getLast();
	}

	public void editarSaludPublica(SaludPublica SaludPublica, int fila) {
		// datos.remove(fila);
//		// datos.add(fila, SaludPublica);
//		this.setValueAt(SaludPublica.getIdSaludPublica(), fila, 0);
//		this.setValueAt(SaludPublica.getCi(), fila, 1);
//		this.setValueAt(SaludPublica.getNombre(), fila, 2);
//		this.setValueAt(SaludPublica.getAppaterno(), fila, 3);
//		this.setValueAt(SaludPublica.getApmaterno(), fila, 4);
//		this.setValueAt(SaludPublica.getTelefono(), fila, 5);
//		this.setValueAt(SaludPublica.getDireccion(), fila, 6);
//		this.setValueAt(SaludPublica.getEmail(), fila, 7);
//		this.setValueAt(SaludPublica.getParticular(), fila, 8);
//		this.setValueAt(SaludPublica.getSexo(), fila, 9);
	}
}
