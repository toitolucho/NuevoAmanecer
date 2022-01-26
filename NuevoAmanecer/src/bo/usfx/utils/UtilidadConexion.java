package bo.usfx.utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


public class UtilidadConexion {
	public Connection obtenerConeccion()
	{
		try {
			String ruta = getLocalDirName().replace("utils", "nuevoAmanecer") + "/model/dao/config/project.properties";
            ResourceBundle bdl = new PropertyResourceBundle(new FileInputStream(ruta));            
            String url = bdl.getString("jdbc.urlPgSQL");
            String controlador = bdl.getString("jdbc.driverPgSQLClassName");
            String usuario = bdl.getString("jdbc.usernamePgSQL");
            String password = bdl.getString("jdbc.passwordPgSQL");
            FormUtilities.nroSolicitudInicio = Integer.valueOf(bdl.getString("nroSolicitudInicio"));
            FormUtilities.nroAutorizacionAlquilerInicio = Integer.valueOf(bdl.getString("nroAutorizacionAlquilerInicio"));
            FormUtilities.horaInicioNocturno = Integer.valueOf(bdl.getString("horaInicioNocturno"));
            FormUtilities.tiempoToleranciaReserva = Integer.valueOf(bdl.getString("tiempoToleranciaReserva"));
            
//            System.out.println("Datos Configuracion");
//            System.out.println(FormUtilities.nroSolicitudInicio);
//            System.out.println(FormUtilities.nroAutorizacionAlquilerInicio);
//            System.out.println(FormUtilities.horaInicioNocturno);
//            System.out.println(FormUtilities.tiempoToleranciaReserva);
            
//            Class.forName(controlador).newInstance( );
            Class.forName(controlador);
            Connection conn = DriverManager.getConnection(url,usuario, password);  
            return conn;
        }
        catch(Exception e) {            
            e.printStackTrace();
            return null;
        }
	}
	
	public String getClassName()
	   {
	      String thisClassName;      
	      //Build a string with executing class's name
	      thisClassName = this.getClass().getName();
	      thisClassName = thisClassName.substring(thisClassName.lastIndexOf(".") + 1,thisClassName.length());
	      thisClassName += ".class";  //this is the name of the bytecode file that is executing      
	      return thisClassName;
	   }
	
	public String getLocalDirName()
	   {
	      String localDirName;      
	      //Use that name to get a URL to the directory we are executing in
	      java.net.URL myURL = this.getClass().getResource(getClassName());  //Open a URL to the our .class file      
	      //Clean up the URL and make a String with absolute path name
	      localDirName = myURL.getPath();  //Strip path to URL object out
	      localDirName = myURL.getPath().replaceAll("%20", " ");  //change %20 chars to spaces      
	      //Get the current execution directory
	      localDirName = localDirName.substring(0,localDirName.lastIndexOf("/"));  //clean off the file	name      
	      return localDirName;
	   }

}
