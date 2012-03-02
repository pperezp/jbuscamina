/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bd;

import archivo.*;

import java.sql.*;

/**
 *
 * @author Administrador
 */
public class Conexion {
    public static Connection con;
    public static Statement sen;
    public static ResultSet rs;
    
    public static void conectar(){
         try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://fatal-exception.sytes.net/jbuscamina", "root", "123456");
        }catch(SQLException e1){
            mensajes.Mensajes.mensajeError("Error de Conexion. ID Error: 1");
        }catch(Exception e2){
            mensajes.Mensajes.mensajeError("Error de Conexion. ID Error: 2");
        }
    }
}
