package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {

    private String url = "jdbc:mysql://localhost:3306/aplicacionjj?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String user = "root";
    private String pass = "";

    public Connection getConnection() {

        Connection conexion = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion = (Connection) DriverManager.getConnection(url, user, pass);

        } catch (Exception ex) {
            
            JOptionPane.showMessageDialog(null, "Error en la conexion" + ex);

           

        }

        return conexion;
    }

}
