/*
 Se realizaran todos aquellos metodos que esten relacionados con la Clase Seccion
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import Conexion.Pool;
import java.awt.List;
import java.util.ArrayList;

public class SeccionDAOImpl implements SeccionDAO{

    Seccion seccion;
    Connection conexion;
    PreparedStatement ps = null;
    ResultSet rs = null;

    private Connection getConnection() throws SQLException {

        return Pool.getConexion();
    }

    @Override
    public Vector<String> listar() {

        Vector<String> lista_s = new Vector<String>();

        Seccion sec;

        try {
            conexion = getConnection();
            ps = conexion.prepareStatement("select desc_seccion from seccion");
            rs = ps.executeQuery();

            sec = new Seccion();

            sec.setDescripcion("Seleccionar");

            lista_s.add(sec.getDescripcion());

            while (rs.next()) {

                sec = new Seccion();

                sec.setDescripcion(rs.getString(1));
                lista_s.add(sec.getDescripcion());

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de seccion");

        } finally {
            try {
                ps.close();
                rs.close();
                Pool.closeConexion(conexion);
            } catch (SQLException e) {
            }

        }
        return lista_s;
    }

   

    @Override
    public int obtenerID(String desc_seccion) {

        int id_seccion = 0;

        String consulta = "SELECT id_seccion FROM seccion WHERE desc_seccion = '" + desc_seccion + "'";

        try {

            conexion = getConnection();
            ps = conexion.prepareStatement(consulta);
            rs = ps.executeQuery();

            if (rs.next()) {

                id_seccion = rs.getInt(1);

            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al obtener la lista de seccion");

        } finally {
            try {
                ps.close();
                rs.close();
                Pool.closeConexion(conexion);
            } catch (SQLException e) {
            }

        }

        return id_seccion;

    }

    @Override
    public boolean agregar(Seccion s) {

        int result;
        boolean verificacion = false;

        try {

            conexion = getConnection();
            ps = conexion.prepareStatement("INSERT INTO seccion (desc_seccion) values (?)");

            ps.setString(1, s.getDescripcion());

            result = ps.executeUpdate();

            if (result > 0) {

                verificacion = true;
            }

        } catch (SQLException e) {

        } finally {
            try {

                ps.close();
                Pool.closeConexion(conexion);

            } catch (SQLException e) {
            }
        }

        return verificacion;
    }

    @Override
    public boolean modificar(Seccion s) {

        try {

            conexion = getConnection();
            ps = conexion.prepareStatement("UPDATE seccion SET desc_seccion = ? WHERE id_seccion = ?");

            ps.setString(1, s.getDescripcion());
            ps.setInt(2, s.getId());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            return false;

        } finally {

            try {
                ps.close();
                Pool.closeConexion(conexion);
            } catch (SQLException e) {
            }
        }

    }

}
