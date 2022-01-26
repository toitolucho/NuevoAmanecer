/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package bo.usfx.utils;
import bo.usfx.nuevoAmanecer.model.domain.Afiliado;
import bo.usfx.nuevoAmanecer.model.domain.Participa;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ModeloAfiliadosParticipantesProgramas implements TableModel {
	private LinkedList<Participa> datos = new LinkedList<Participa>();
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
			return "Nro Caso";
		case 1:
			return "Nombre Completo";
		case 2:
			return "Nombre Corto";
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
		Participa aux;

		// Se obtiene la Participa de la fila indicada
		aux = (Participa) (datos.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		switch (columnIndex) {
		case 0:
			return aux.getAfiliado().getNumero_caso();
		case 1:
			return aux.getAfiliado().getNombreCompleto();
		case 2:
			return aux.getAfiliado().getNombre_corto();
		case 3:
			return aux.getAfiliado().getRepresentacionCadenaSexo();
                
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
		// Obtiene la Participa de la fila indicada
		

		// Avisa a los suscriptores del cambio, creando un TableModelEvent ...
		TableModelEvent evento = new TableModelEvent(this, rowIndex, rowIndex,
				columnIndex);

		// ... y pasándoselo a los suscriptores.
		avisaSuscriptores(evento);

	}

	/**
	 * Añade una Participa al final de la tabla
	 */
	public void addParticipa(Participa nuevaParticipa) {
		if (nuevaParticipa != null)
			// Añade la Participa al modelo
			datos.add(nuevaParticipa);
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
	 * Borra del modelo la Participa en la fila indicada
	 */
	public Participa removeParticipa(int fila) {
		// Se borra la fila
		@SuppressWarnings("unused")
		Participa per = datos.remove(fila);

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
			this.removeParticipa(0);
		}

	}

	/**
	 * Retorna la Participa de la Lista de datos de acuerdo a la fila que se pasa
	 * como parametro
	 *
	 * @param fila
	 * @return
	 */
	public Participa getParticipa(int fila) {
		return datos.get(fila);
	}

	/**
	 * Devuelve la Primera Participa de la lista de datos
	 *
	 * @return
	 */
	public Participa getFirtsParticipa() {
		return datos.getFirst();
	}

	/**
	 * Devuelve la Ultima Participa de la lista de datos
	 *
	 * @return
	 */
	public Participa getLastParticipa() {
		return datos.getLast();
	}

	public void editarParticipa(Participa Participa, int fila) {
		// datos.remove(fila);
//		// datos.add(fila, Participa);
//		this.setValueAt(Participa.getIdParticipa(), fila, 0);
//		this.setValueAt(Participa.getCi(), fila, 1);
//		this.setValueAt(Participa.getNombre(), fila, 2);
//		this.setValueAt(Participa.getAppaterno(), fila, 3);
//		this.setValueAt(Participa.getApmaterno(), fila, 4);
//		this.setValueAt(Participa.getTelefono(), fila, 5);
//		this.setValueAt(Participa.getDireccion(), fila, 6);
//		this.setValueAt(Participa.getEmail(), fila, 7);
//		this.setValueAt(Participa.getParticular(), fila, 8);
//		this.setValueAt(Participa.getSexo(), fila, 9);
	}

        public boolean existeAfiliado(Participa afiliadoParticipante)
        {
            Collections.sort(datos);
            return Collections.binarySearch(datos, afiliadoParticipante) >= 0;
        }

    public LinkedList<Participa> getDatos() {
        return datos;
    }
}
