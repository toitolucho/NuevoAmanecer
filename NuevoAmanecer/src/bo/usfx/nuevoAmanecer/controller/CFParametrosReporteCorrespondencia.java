/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import view.GuiParametrosReporteAfiiados;
import view.GuiParametrosReporteCorrespondencia;

/**
 *
 * @author Luis Molina
 */
public class CFParametrosReporteCorrespondencia implements ActionListener{
    JDialog formParametros;
    public boolean operacionConfirmada = false;
    public CFParametrosReporteCorrespondencia(JDialog formParametros)
    {
        this.formParametros = formParametros;
    }

    public CFParametrosReporteCorrespondencia()
    {
        
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Acion de botones " + e.getActionCommand());
        if(e.getSource() instanceof JButton)
        {
            if(((JButton) e.getSource()).getActionCommand().compareTo("aceptar") == 0)
            {
                
                
                formParametros.setVisible(false);
                operacionConfirmada = true;
            }
            if(((JButton) e.getSource()).getActionCommand().compareTo("cancelar") == 0)
            {
                formParametros.setVisible(false);
            }
        }
        if(e.getSource() instanceof JRadioButton)
        {
            JRadioButton opcionSeleccionada = (JRadioButton) e.getSource();
            if(opcionSeleccionada.getActionCommand().compareTo("fechas") == 0)
            {
                GuiParametrosReporteAfiiados form = (GuiParametrosReporteAfiiados) this.formParametros;
                form.getLblAl().setVisible(true);
                form.getLblDel().setVisible(true);
                form.getjDateChooser1().setVisible(true);
                form.getjDateChooser2().setVisible(true);
            }
            else
            {
                GuiParametrosReporteAfiiados form = (GuiParametrosReporteAfiiados) this.formParametros;
                form.getLblAl().setVisible(false);
                form.getLblDel().setVisible(false);
                form.getjDateChooser1().setVisible(false);
                form.getjDateChooser2().setVisible(false);
            }
        }
    }

}
