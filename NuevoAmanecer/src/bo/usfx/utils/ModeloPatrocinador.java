/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.utils;



import java.util.Date;
import java.util.LinkedList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import bo.usfx.nuevoAmanecer.model.domain.Padrinos;

public class ModeloPatrocinador  implements TableModel {
	private LinkedList<Padrinos> datos = new LinkedList<Padrinos>();
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
			return Integer.class;
		
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
			return "Ci Cod.";
		case 1:
			return "Nombre Completo";
		case 2:
			return "Nro Patrocinio";
		
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
		Padrinos aux;

		// Se obtiene la Asociacion de la fila indicada
		aux = (Padrinos) (datos.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		switch (columnIndex) {
		case 0:
			return aux.getCodigo_padrino();
		case 1:
			return aux.getNombreCompleto();
		case 2:
			return aux.getNumero_padrino();
		
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
		// Obtiene la Asociacion de la fila indicada
		Padrinos aux;
		aux = (Padrinos) (datos.get(rowIndex));

		// Cambia el campo de Asociacion que indica columnIndex, poniendole el
		// aValue que se nos pasa.
		switch (columnIndex) {
		case 0:
//			aux.setCodigo_integrante((Integer) arg0);
			break;
		case 1:
//			aux.setDescripcion((String) arg0);
			break;
		case 2:
			//aux.setPadrinostangible(((Boolean) arg0));
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
	 * Añade una Asociacion al final de la tabla
	 */
	public void addPadrinos(Padrinos nuevaPadrinos) {
		if (nuevaPadrinos != null)
			// Añade la Asociacion al modelo
			datos.add(nuevaPadrinos);
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
	 * Borra del modelo la Asociacion en la fila indicada
	 */
	public Padrinos removePadrinos(int fila) {
		// Se borra la fila

		System.out.println("From removeAsociacion" + fila);
		Padrinos Padrinos = datos.remove(fila);

		// Y se avisa a los suscriptores, creando un TableModelEvent...
		TableModelEvent evento = new TableModelEvent(this, fila, fila,
				TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);

		// ... y pasándoselo a los suscriptores
		avisaSuscriptores(evento);
		return Padrinos;
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
			this.removePadrinos(0);
		}

	}

	/**
	 * Retorna la Asociacion de la Lista de datos de acuerdo a la fila que se
	 * pasa como parametro
	 *
	 * @param fila
	 * @return
	 */
	public Padrinos getPadrinos(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera Asociacion de la lista de datos
	 *
	 * @return
	 */
	public Padrinos getFirtsPadrinos() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima Asociacion de la lista de datos
	 *
	 * @return
	 */
	public Padrinos getLastPadrinos() {
		return datos.getLast();
	}
}
