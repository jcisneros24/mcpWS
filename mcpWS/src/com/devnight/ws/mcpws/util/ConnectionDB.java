package com.devnight.ws.mcpws.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionDB {
     public Connection getConnection()  {
        Connection cn = null;
        String conector = "com.mysql.jdbc.Driver";     
             
        String server = "localhost";
        String port = "3306";
        String db = "xroot";
        String user = "root";
        String password = "";
        
        String url = "jdbc:mysql://"+server+":"+port+"/"+db;
        
        try{
            Class.forName(conector);
            cn= DriverManager.getConnection(url, user, password);           
            
        	}catch (final SQLException e) {
            cn=null;
	            if(e.getErrorCode() == 0){
	                JOptionPane.showMessageDialog(null, "El servidor de base de datos no responde.\n"
	                                                    + "Verifique que el servidor de base de datos este activo.\n"
	                                                    + "Tambien Verifique que exista la base de datos bdsistemas", 
	                                                    "Error: Coneccion Fallida.", JOptionPane.ERROR_MESSAGE);
	            }
        	}catch (final ClassNotFoundException e) {
            cn=null;
            	//throw new SQLException("Error de conexion:" + e.getMessage());
        	} 
        return cn;
    }
}
