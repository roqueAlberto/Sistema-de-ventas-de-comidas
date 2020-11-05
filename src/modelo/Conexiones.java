package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexiones {

    private static Connection conn = null;
    private String url;
    private String user;
    private String pass;
    public  static Conexiones conexiones;

    private Conexiones() {

        url = "jdbc:mysql://localhost:3306/aplicacionjj?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        user = "root";
        pass = "";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection(url, user, pass);

        } catch (Exception ex) {

            System.err.println("Error en la conexion" + ex);

        }

    }

    public static Connection getConnection() {

        if (conn == null) {
            
            new Conexiones();
         
        }

        return conn;
    }
}
