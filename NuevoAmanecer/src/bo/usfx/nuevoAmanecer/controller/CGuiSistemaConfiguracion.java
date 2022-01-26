package bo.usfx.nuevoAmanecer.controller;

import bo.usfx.nuevoAmanecer.model.dao.CommonDao;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import view.GuiSistemaConfiguracion;

import bo.usfx.nuevoAmanecer.model.domain.Usuarios;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import view.GuiFamilia;

public class CGuiSistemaConfiguracion extends InternalFrameAdapter implements
        ActionListener {

    GuiSistemaConfiguracion formSistemaConfiguracion = null;
    String rutaLocal = "";
    private int idFormulario;
    private CommonDao dao;
    private Usuarios usuario;

    public CGuiSistemaConfiguracion(GuiSistemaConfiguracion formSistema) {
        this.formSistemaConfiguracion = formSistema;

        // this.formSistemaConfiguracion.addInternalFrameListener(this);
    }

    @SuppressWarnings({"deprecation", "unchecked"})
    @Override
    public void actionPerformed(ActionEvent event) {
        // TODO Auto-generated method stub
        String accion = event.getActionCommand();
        if (accion.compareTo("backup") == 0) {

            String servidor, nombreBaseDatos, usuario, password;
            servidor = formSistemaConfiguracion.getJtxtNombreServidor().getText();
            nombreBaseDatos = formSistemaConfiguracion.getJtxtNombreBaseDatos().getText();
            usuario = formSistemaConfiguracion.getJtxtUsuario().getText();
            password = formSistemaConfiguracion.getJPasswordField().getText();
            String path = null;

            if (servidor.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        formSistemaConfiguracion,
                        "Aún no ha ingresado el nombre o IP del host del Servidor",
                        "Datos Incompletos", JOptionPane.ERROR_MESSAGE);

                formSistemaConfiguracion.getJtxtNombreServidor().grabFocus();
                return;
            }
            if (nombreBaseDatos.trim().isEmpty()) {
                JOptionPane.showMessageDialog(formSistemaConfiguracion,
                        "Aún no ha ingresado el nombre de la Base de Datos",
                        "Datos Incompletos", JOptionPane.ERROR_MESSAGE);

                formSistemaConfiguracion.getJtxtNombreBaseDatos().grabFocus();
                return;
            }
            if (usuario.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        formSistemaConfiguracion,
                        "Aún no ha ingresado el usuario del Gestor de Base de Datos",
                        "Datos Incompletos", JOptionPane.ERROR_MESSAGE);
                formSistemaConfiguracion.getJtxtNombreServidor().grabFocus();
                return;
            }


            //Para PostGreSQL
            String URL = "jdbc:postgresql://" + servidor + "/" + nombreBaseDatos;
            //Cargamos el Driver para Postgresql
            try {
                Class.forName("org.postgresql.Driver");

            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(formSistemaConfiguracion, "No se puede encontrar el driver para la base de Datos", "Copia de Seguridad", JOptionPane.ERROR_MESSAGE);
                System.err.println("No puedo encontrar el driver para la base de datos.");
                System.err.println(ex.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(formSistemaConfiguracion, "No se puede cargar el puente JDBC-ODBC-POSTGRESQL", "Copia de Seguridad", JOptionPane.ERROR_MESSAGE);
            }

            //Establecer la Conexion
            try {
                Connection conn = DriverManager.getConnection(URL, usuario, password);

            } catch (SQLException E) {
                System.out.println("No se encontraron parametros para la conexion a la BD");
                System.out.println("Excepción del SQL: " + E.getMessage());
                System.out.println("Estado del SQL: " + E.getSQLState());
                System.out.println("Error del Proveedor: " + E.getErrorCode());
                JOptionPane.showMessageDialog(formSistemaConfiguracion, "No se encontraron parametros para la conexion a la BD, probablemente se encuentra mal provistos", "Copia de Seguridad", JOptionPane.ERROR_MESSAGE);
                return;
            }


            if (formSistemaConfiguracion.getJcheckSeleccionarDirectorio().isSelected()) {
                JFileChooser chooser = new JFileChooser();
                int option = -1;
                if (formSistemaConfiguracion.getJcheckPorFecha().isSelected()) {

                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    option = chooser.showOpenDialog(formSistemaConfiguracion);
                    if (option == JFileChooser.APPROVE_OPTION) {
                        path = chooser.getSelectedFile().getAbsolutePath().replace('\\', '/');
                    } else {
                        JOptionPane.showMessageDialog(
                                formSistemaConfiguracion,
                                "Aún no ha seleccionado un Nombre valido para la copia de Respaldo",
                                "Nombre no Valido",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    TimeZone zonah = TimeZone.getTimeZone("GMT+1");
                    // Calendar Calendario = GregorianCalendar.getInstance(
                    // zonah, new java.util.Locale("es"));
                    Calendar Calendario = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat(
                            "dd-MM-yyyy-HH-mm");
                    StringBuffer date = new StringBuffer();
                    date.append(df.format(Calendario.getTime()));
                    System.out.println(date.toString());
                    java.io.File file = new java.io.File(path);

                    if (file.exists()) {
                        System.out.println("Existe el directorio " + path);
                        StringBuffer fechafile = new StringBuffer();
                        fechafile.append(path + "/");
                        fechafile.append(date.toString());
                        fechafile.append(".backup");
                        java.io.File ficherofile = new java.io.File(
                                fechafile.toString());
                        // Probamos a ver si existe ese ultimo dato
                        if (ficherofile.exists()) {
                            // Lo Borramos
                            ficherofile.delete();
                        }
                        path = fechafile.toString();
                    }
                } else {
                    option = chooser.showSaveDialog(formSistemaConfiguracion);
                    if (option == JFileChooser.APPROVE_OPTION) {
                        path = chooser.getSelectedFile().getAbsolutePath().replace('\\', '/')
                                + ".backup";
                    } else {
                        JOptionPane.showMessageDialog(
                                formSistemaConfiguracion,
                                "No selecciono un Nombre valido para la Copia de Seguridad",
                                "Archivo No confirmado",
                                JOptionPane.ERROR_MESSAGE);

                        return;
                    }
                }

            } else {
                Class myClass = GuiFamilia.class;
                URL url = myClass.getResource("GuiFamilia.class");
                System.out.println("ubicación de la calse persona "
                        + url.getPath());
                rutaLocal = url.getPath().substring(1).replaceAll("bin/view/GuiFamilia.class", "db/").trim();
                if (rutaLocal.contains("%20")) {
                    rutaLocal = rutaLocal.replace("%20", " ");
                }
                path = rutaLocal;
                if (formSistemaConfiguracion.getJcheckPorFecha().isSelected()) {
                    TimeZone zonah = TimeZone.getTimeZone("GMT+1");
                    // Calendar Calendario = GregorianCalendar.getInstance(
                    // zonah, new java.util.Locale("es"));
                    Calendar Calendario = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat(
                            "dd-MM-yyyy-HH-mm");
                    StringBuffer date = new StringBuffer();
                    date.append(df.format(Calendario.getTime()));
                    System.out.println(date.toString());
                    java.io.File file = new java.io.File(path);
                    /*
                     * Preguntamos si el archivo de resguardo ya se encuentra
                     * creado
                     */
                    if (file.exists()) {

                        StringBuffer fechafile = new StringBuffer();
                        fechafile.append(path);
                        fechafile.append(date.toString());
                        fechafile.append(".backup");
                        java.io.File ficherofile = new java.io.File(
                                fechafile.toString());
                        // Probamos a ver si existe ese ultimo dato
                        if (ficherofile.exists()) {
                            // Lo Borramos
                            ficherofile.delete();
                        }
                        path = fechafile.toString();
                    }

                } else {
                    String respuestaFinal = JOptionPane.showInputDialog(
                            formSistemaConfiguracion,
                            "Introduzca el Nombre del Archivo de Resguardo : ",
                            "Nombre del Archivo", 1);
                    if (respuestaFinal == null
                            || respuestaFinal.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(
                                formSistemaConfiguracion,
                                "No introducio el Nombre del Archivo de Resguardo",
                                "Respuesta no Valida",
                                JOptionPane.ERROR_MESSAGE);

                        return;
                    }
                    path += respuestaFinal + ".backup";
                }
            }

            try {

                System.out.println(path);
                @SuppressWarnings("unused")
                Runtime rutinaSistema = Runtime.getRuntime();
                Process proceso;
                ProcessBuilder pbConstructorProceso;

                rutinaSistema = Runtime.getRuntime();
                // pb = new
                // ProcessBuilder("D:/Program Files (x86)/PostgreSQL/8.4/bin/pg_dump.exe",
                // "-v", "-D", "-f", path, "-U", usuario, nombreBaseDatos);
                // System.out.println(rutaLocal + ",  Path =" +
                // System.getenv());
                for (String string : System.getenv().toString().substring(1).split(",")) {
                    System.out.println(string);
                }
                pbConstructorProceso = new ProcessBuilder(
                        "C:/Program Files/PostgreSQL/9.0/bin/pg_dump.exe",
                        "-f", path, "-F", "c", "-Z", "9", "-v", "-o", "-h",
                        servidor, "-U", usuario, nombreBaseDatos);
                // pbConstructorProceso = new
                // ProcessBuilder(System.getenv("ProgramFiles").replace('\\',
                // '/') + "/PostgreSQL/8.4/bin/pg_dump.exe", "-f", path, "-F",
                // "c", "-Z", "9", "-v", "-o", "-h",servidor, "-U", usuario,
                // nombreBaseDatos);
                pbConstructorProceso.environment().put("PGPASSWORD", password);
                pbConstructorProceso.redirectErrorStream(true);
                proceso = pbConstructorProceso.start();

                InputStream imputStreamDatosEntrada = proceso.getInputStream();
                InputStreamReader imputStreamReaderLectorDatos = new InputStreamReader(
                        imputStreamDatosEntrada);
                BufferedReader br = new BufferedReader(
                        imputStreamReaderLectorDatos);
                String lineaComandos;
                ArrayList<String> listaComandos = new ArrayList<String>();
                int contador = 0;
                while ((lineaComandos = br.readLine()) != null) {
                    listaComandos.add(lineaComandos);
                    contador++;
                }
                int valueProgresBar = 0;
                formSistemaConfiguracion.getJProgressBar().setMaximum(contador);
                formSistemaConfiguracion.getJProgressBar().setValue(0);
                formSistemaConfiguracion.getJProgressBar().setMinimum(0);
                for (String comando : listaComandos) {
                    Thread.sleep(50);
                    System.out.println(comando);

                    formSistemaConfiguracion.jlblOperaciones.setText(comando);
                    formSistemaConfiguracion.jlblOperaciones.invalidate();
                    formSistemaConfiguracion.jlblOperaciones.validate();
                    Rectangle labelRect = formSistemaConfiguracion.jlblOperaciones.getBounds();
                    labelRect.x = 0;
                    labelRect.y = 0;
                    formSistemaConfiguracion.jlblOperaciones.paintImmediately(labelRect);

                    formSistemaConfiguracion.getJProgressBar().setValue(
                            valueProgresBar);
                    formSistemaConfiguracion.getJProgressBar().setString(
                            String.valueOf(valueProgresBar) + "%");
                    Rectangle progressRect = formSistemaConfiguracion.getJProgressBar().getBounds();
                    progressRect.x = 0;
                    progressRect.y = 0;
                    formSistemaConfiguracion.getJProgressBar().paintImmediately(progressRect);
                    // formSistemaConfiguracion.getJProgressBar().updateUI();
                    valueProgresBar++;
                }
                formSistemaConfiguracion.jlblOperaciones.setText("Operación concluida");
                formSistemaConfiguracion.getJProgressBar().setString(
                        String.valueOf("Tarea Compleatada"));
                JOptionPane.showMessageDialog(
                        formSistemaConfiguracion,
                        "Se concluyó correctamente el resguardo de Seguridad de la Base de Datos",
                        "Operación Satisfactoria",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                System.out.println("Ocurrio un error \n" + e + "\n"
                        + e.getMessage());
                JOptionPane.showMessageDialog(formSistemaConfiguracion,
                        "Ocurrio la siguiente Excepcion " + e.getMessage(),
                        "Error en el Respaldo", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    // public void cargarBitacora(String tipoOperacion)
    // {
    // bitacora.setIdpersona(this.usuario.getIdpersona());
    // bitacora.setId_sistema_formulario(idFormulario);
    // bitacora.setTipo_operacion(tipoOperacion);
    // }
    public void setDao(CommonDao dao) {
        this.dao = dao;
    }

    public CommonDao getDao() {
        return dao;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public void internalFrameClosing(InternalFrameEvent event) {
        this.formSistemaConfiguracion.getJtxtNombreServidor().setText("");
        this.formSistemaConfiguracion.getJtxtUsuario().setText("");
        this.formSistemaConfiguracion.getJPasswordField().setText("");
    }
}
