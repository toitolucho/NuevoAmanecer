/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.utils;
import bo.usfx.nuevoAmanecer.model.domain.ActividadEducativa;
import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import bo.usfx.nuevoAmanecer.model.domain.Ocupaciones;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ModeloUsuarios implements TableModel {
	private LinkedList<Usuarios> datos = new LinkedList<Usuarios>();
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
			return String.class;
		case 2:
			return String.class;
		case 3:
			return Date.class;
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
			return "Nombre Completo";
		case 1:
			return "Direccion";
		case 2:
			return "Telefono";
		case 3:
			return "Fecha Reg.";
		case 4:
			return "Nombre Cuenta";
                
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
		Usuarios aux;

		// Se obtiene la Usuarios de la fila indicada
		aux = (Usuarios) (datos.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		switch (columnIndex) {
		case 0:
			return aux.getNombreCompleto();
		case 1:
			return aux.getDireccion();
		case 2:
			return aux.getTelefono();
		case 3:
			return aux.getFecha_registro();
                case 4:
			return aux.getNombre_cuenta();
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
		// Obtiene la Usuarios de la fila indicada
		Usuarios aux;
		aux = (Usuarios) (datos.get(rowIndex));

		// Cambia el campo de Usuarios que indica columnIndex, poniendole el
		// aValue que se nos pasa.
		switch (columnIndex) {
		case 0:

			break;
		case 1:
			aux.setDireccion((String) arg0);
			break;
		case 2:
			aux.setTelefono(((String) arg0));
			break;
		case 3:
			aux.setFecha_registro(((Date) arg0));
			break;
		case 4:
			aux.setNombre_cuenta(((String) arg0));
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
	 * Añade una Usuarios al final de la tabla
	 */
	public void addUsuarios(Usuarios nuevaUsuarios) {
		if (nuevaUsuarios != null)
			// Añade la Usuarios al modelo
			datos.add(nuevaUsuarios);
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
	 * Borra del modelo la Usuarios en la fila indicada
	 */
	public Usuarios removeUsuarios(int fila) {
		// Se borra la fila
		@SuppressWarnings("unused")
		Usuarios per = datos.remove(fila);

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
			this.removeUsuarios(0);
		}

	}

	/**
	 * Retorna la Usuarios de la Lista de datos de acuerdo a la fila que se pasa
	 * como parametro
	 *
	 * @param fila
	 * @return
	 */
	public Usuarios getUsuarios(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera Usuarios de la lista de datos
	 *
	 * @return
	 */
	public Usuarios getFirtsUsuarios() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima Usuarios de la lista de datos
	 *
	 * @return
	 */
	public Usuarios getLastUsuarios() {
		return datos.getLast();
	}

	public void editarUsuarios(Usuarios Usuarios, int fila) {
		this.removeUsuarios(fila);
                this.addUsuarios(Usuarios);
	}
}
