package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class Comida {

    private int id;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return nombre;
    }

    public Vector<String> mostrarComidas() {

        Connection conexion = Conexiones.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Vector<String> datos = new Vector<String>();

        //Crearemos una variable de la clase Comida
        Comida com;

        try {

            ps = conexion.prepareStatement("select nombre from menu");
            rs = ps.executeQuery();

            com = new Comida();
          
            com.setNombre("Seleccionar");

            datos.add(com.getNombre());

            while (rs.next()) {

                com = new Comida();
                
                com.setNombre(rs.getString("nombre"));
                datos.add(com.getNombre());

            }

        } catch (Exception e) {
            System.err.println("Error en comida" + e);

        }

        return datos;
    }

    public int obtenerID(String nomSeccion) {

        Connection conexion = Conexiones.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String consulta = "SELECT id_comida FROM menu WHERE nombre = '" + nomSeccion + "'";

        try {

            ps = conexion.prepareStatement(consulta);
            rs = ps.executeQuery();

            if (rs.next()) {

                id = rs.getInt("id_comida");

            }
        } catch (Exception e) {

            System.out.println("Ocurrio un error al obtener el ID");
        }

        return id;

    }
}
