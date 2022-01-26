package view;

import bo.usfx.nuevoAmanecer.model.domain.SistemaFormularios;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;


public class JTableToolTips extends JTable {	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnToolTips = {"Nombre del Formulario, mantenga el cursor sobre el mismo para ver su Descripcion",
    		"Marque o Desmarque para Agregar o quitar el formulario en la lista de Permisos del Usuario"};
    
    public JTableToolTips()
    {
    	super();
    }
	
	public String getToolTipText(MouseEvent e) {
        String tip = null;
        java.awt.Point p = e.getPoint();
        int rowIndex = rowAtPoint(p);
        int colIndex = columnAtPoint(p);
        int realColumnIndex = convertColumnIndexToModel(colIndex);

        if (realColumnIndex == 0) { //Nombre del Formulario
            tip = ((SistemaFormularios) getValueAt(rowIndex, colIndex)).getDescripcion();
//        } else if (realColumnIndex == 4) { //Veggie column
//            TableModel model = getModel();
//            String firstName = (String)model.getValueAt(rowIndex,0);
//            String lastName = (String)model.getValueAt(rowIndex,1);
//            Boolean veggie = (Boolean)model.getValueAt(rowIndex,4);
//            if (Boolean.TRUE.equals(veggie)) {
//                tip = firstName + " " + lastName
//                      + " is a vegetarian";
//            } else {
//                tip = firstName + " " + lastName
//                      + " is not a vegetarian";
//            }
        } else { 
            //You can omit this part if you know you don't 
            //have any renderers that supply their own tool 
            //tips.
            tip = super.getToolTipText(e);
        }
        return tip;
    }

    //Implement table header tool tips. 
    protected JTableHeader createDefaultTableHeader() {
        return new JTableHeader(columnModel) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public String getToolTipText(MouseEvent e) {
                
                java.awt.Point p = e.getPoint();
                int index = columnModel.getColumnIndexAtX(p.x);
                int realIndex = columnModel.getColumn(index).getModelIndex();
                return columnToolTips[realIndex];
            }
        };
    }
}
