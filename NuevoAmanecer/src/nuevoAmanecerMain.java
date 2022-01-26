
import bo.usfx.utils.ThemesLoader;
import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.GuiAutenticacion;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class nuevoAmanecerMain {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {

     //   Splash splash = new Splash("/imagenes/Inicio.png", true);
     //   splash.inicializar();

        GuiAutenticacion formAutenticacion = new GuiAutenticacion();
        formAutenticacion.setLocationRelativeTo(null);
        NimRODLookAndFeel nf;
        NimRODTheme nt;
        nf = new NimRODLookAndFeel();
        nt = new NimRODTheme();
        NimRODLookAndFeel.setCurrentTheme(nt);
        ThemesLoader tlCargador = new ThemesLoader();
        UIManager.setLookAndFeel(nf);

        Class sasdedeClass = nuevoAmanecerMain.class;
        URL url = sasdedeClass.getResource("nuevoAmanecerMain.class");
        String rutaLocal = url.getPath().substring(1).replaceAll("nuevoAmanecerMain.class", "theme3.theme").trim();
        if (rutaLocal.contains("%20")) {
            rutaLocal = rutaLocal.replace("%20", " ");
        }
        tlCargador.seleccionarTemadeArchivo(formAutenticacion, rutaLocal);
        JFrame.setDefaultLookAndFeelDecorated(true);       

        formAutenticacion.setVisible(true);


        
       }
}
