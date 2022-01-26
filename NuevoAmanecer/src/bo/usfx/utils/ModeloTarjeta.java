/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.utils;
import bo.usfx.nuevoAmanecer.model.domain.ActividadEducativa;
import bo.usfx.nuevoAmanecer.model.domain.Tarjeta;
import bo.usfx.nuevoAmanecer.model.domain.Ocupaciones;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ModeloTarjeta implements TableModel {
	private LinkedList<Tarjeta> datos = new LinkedList<Tarjeta>();
	private LinkedList<TableModelListener> listeners = new LinkedList<TableModelListener>();
        private boolean mostrarDescripcion = false;
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
//		case 3:
//			return String.class;
//		case 4:
//			return Integer.class;
//                case 5:
//			return String.class;
//                case 6:
//                        return Integer.class;
		default:
			// Devuelve una clase Object por defecto.
			return Object.class;
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
            if(!mostrarDescripcion)
		return 4;
            else
                return 5;

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
			return "Fecha Registro";
		case 2:
			return "Comunidad";
		case 3:
			return "Estado";
		case 4:
			return "Descripcion";
                case 5:
			return "Estado";
                case 6:
			return "Nro Proyecto";
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
		Tarjeta aux;

		// Se obtiene la Tarjeta de la fila indicada
		aux = (Tarjeta) (datos.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		switch (columnIndex) {
		case 0:
			return aux.getNumero_familia();
		case 1:
			return aux.getFecha_registro_tarjeta();
		case 2:
			return aux.getComunidad();
		case 3:
			return aux.getCodigo_estado_tarjeta();
                case 4:
			return aux.getDescripcion();
		case 5:
			return aux.getCodigo_estado_tarjeta();
                case 6:
			return aux.getNumero_proyecto();
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
		// Obtiene la Tarjeta de la fila indicada
		Tarjeta aux;
		aux = (Tarjeta) (datos.get(rowIndex));

		
		// Avisa a los suscriptores del cambio, creando un TableModelEvent ...
		TableModelEvent evento = new TableModelEvent(this, rowIndex, rowIndex,
				columnIndex);

		// ... y pasándoselo a los suscriptores.
		avisaSuscriptores(evento);

	}

	/**
	 * Añade una Tarjeta al final de la tabla
	 */
	public void addTarjeta(Tarjeta nuevaTarjeta) {
		if (nuevaTarjeta != null)
			// Añade la Tarjeta al modelo
			datos.add(nuevaTarjeta);
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
	 * Borra del modelo la Tarjeta en la fila indicada
	 */
	public Tarjeta removeTarjeta(int fila) {
		// Se borra la fila
		@SuppressWarnings("unused")
		Tarjeta per = datos.remove(fila);

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
			this.removeTarjeta(0);
		}

	}

	/**
	 * Retorna la Tarjeta de la Lista de datos de acuerdo a la fila que se pasa
	 * como parametro
	 *
	 * @param fila
	 * @return
	 */
	public Tarjeta getTarjeta(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera Tarjeta de la lista de datos
	 *
	 * @return
	 */
	public Tarjeta getFirtsTarjeta() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima Tarjeta de la lista de datos
	 *
	 * @return
	 */
	public Tarjeta getLastTarjeta() {
		return datos.getLast();
	}

    public boolean isMostrarDescripcion() {
        return mostrarDescripcion;
    }

    public void setMostrarDescripcion(boolean mostrarDescripcion) {
        this.mostrarDescripcion = mostrarDescripcion;
    }

        

}
