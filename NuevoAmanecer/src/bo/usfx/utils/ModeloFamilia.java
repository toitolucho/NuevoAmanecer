/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.utils;
import bo.usfx.nuevoAmanecer.model.domain.ActividadEducativa;
import bo.usfx.nuevoAmanecer.model.domain.Familia;
import bo.usfx.nuevoAmanecer.model.domain.Ocupaciones;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ModeloFamilia implements TableModel {
	private LinkedList<Familia> datos = new LinkedList<Familia>();
	private LinkedList<TableModelListener> listeners = new LinkedList<TableModelListener>();
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat( "dd / MM / yyyy" );
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
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return Ocupaciones.class;
                case 5:
			return ActividadEducativa.class;
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
			return "Nombre Completo";
		case 1:
			return "Parentesco";
		case 2:
			return "Sexo";
		case 3:
			return "Fecha Nac.";
		case 4:
			return "Ocupacion";
                case 5:
			return "Educación";
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
		Familia aux;

		// Se obtiene la Familia de la fila indicada
		aux = (Familia) (datos.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		switch (columnIndex) {
		case 0:
			return aux.getNombreCompleto();
		case 1:
			return aux.getParentesco();
		case 2:
			return aux.getSexo();
		case 3:
			return df.format(aux.getFecha_nacimiento());
                case 4:
			return aux.getOcupacion();
		case 5:
			return aux.getActividadEducativa();
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
		// Obtiene la Familia de la fila indicada
		Familia aux;
		aux = (Familia) (datos.get(rowIndex));

		// Cambia el campo de Familia que indica columnIndex, poniendole el
		// aValue que se nos pasa.
		switch (columnIndex) {
		case 0:
			
			break;
		case 1:
			aux.setParentesco((String) arg0);
			break;
		case 2:
			aux.setSexo(((String) arg0));
			break;
		case 3:
//			aux.setFecha_nacimiento(((Date) arg0));
			break;
		case 4:
			aux.setOcupacion(((Ocupaciones) arg0));
			break;
                case 5:
			aux.setActividadEducativa(((ActividadEducativa) arg0));
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
	 * Añade una Familia al final de la tabla
	 */
	public void addFamilia(Familia nuevaFamilia) {
		if (nuevaFamilia != null)
			// Añade la Familia al modelo
			datos.add(nuevaFamilia);
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
	 * Borra del modelo la Familia en la fila indicada
	 */
	public Familia removeFamilia(int fila) {
		// Se borra la fila
		@SuppressWarnings("unused")
		Familia per = datos.remove(fila);

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
			this.removeFamilia(0);
		}

	}

	/**
	 * Retorna la Familia de la Lista de datos de acuerdo a la fila que se pasa
	 * como parametro
	 *
	 * @param fila
	 * @return
	 */
	public Familia getFamilia(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera Familia de la lista de datos
	 *
	 * @return
	 */
	public Familia getFirtsFamilia() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima Familia de la lista de datos
	 *
	 * @return
	 */
	public Familia getLastFamilia() {
		return datos.getLast();
	}

	public void editarFamilia(Familia Familias, int fila) {
            this.removeFamilia(fila);
            this.addFamilia(Familias);
		// datos.remove(fila);
//		// datos.add(fila, Familia);
//		this.setValueAt(Familia.getIdFamilia(), fila, 0);
//		this.setValueAt(Familia.getCi(), fila, 1);
//		this.setValueAt(Familia.getNombre(), fila, 2);
//		this.setValueAt(Familia.getAppaterno(), fila, 3);
//		this.setValueAt(Familia.getApmaterno(), fila, 4);
//		this.setValueAt(Familia.getTelefono(), fila, 5);
//		this.setValueAt(Familia.getDireccion(), fila, 6);
//		this.setValueAt(Familia.getEmail(), fila, 7);
//		this.setValueAt(Familia.getParticular(), fila, 8);
//		this.setValueAt(Familia.getSexo(), fila, 9);
	}
}
