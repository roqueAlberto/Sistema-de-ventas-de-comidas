package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;




public class Ajustes  {

    
    Statement st;

    public boolean buscarDatos(TipoComida tipo) {
        

        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = Conexiones.getConnection();

        try {

            ps = conexion.prepareStatement("SELECT * FROM tipo_menu WHERE id_tipo = ?");

            ps.setInt(1, tipo.getId());

            rs = ps.executeQuery();

            if (rs.next()) {

                tipo.setNombre(rs.getString(2));

                tipo.setPrecio(rs.getInt(3));
                
                rs.close();

                return true;
            } else {
                rs.close();
                return false;
            }

        } catch (Exception e) {
            System.err.println("Error al buscar" + e);
            return false;

        }

    }

    public boolean modificarTipoComida(TipoComida tipo) {

        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = Conexiones.getConnection();

        try {
            ps = conexion.prepareStatement("UPDATE tipo_menu SET desc_tipo=?,precio=? WHERE id_tipo = ?");

            ps.setString(1, tipo.getNombre());
            ps.setInt(2, tipo.getPrecio());
            ps.setInt(3, tipo.getId());

            int resultado = ps.executeUpdate();

            if (resultado > 0) {

                
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al modificar los datos del tipo de comida");
            return false;

        }

    }

    public boolean insertarDatosenTipo(TipoComida tipo) {

        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = Conexiones.getConnection();

        try {
            ps = conexion.prepareStatement("INSERT INTO tipo_menu (desc_tipo,precio,rela_menu) VALUES (?,?,?)");

            ps.setString(1, tipo.getNombre());
            ps.setInt(2, tipo.getPrecio());
            ps.setInt(3, tipo.getRela_menu());

            int resultado = ps.executeUpdate();

            if (resultado > 0) {

                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Imposible añadir un nuevo tipo de comida en la Base de Datos");
            return false;

        }

    }
    
 

    public boolean modificarMenu(Comida comida) {

        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = Conexiones.getConnection();

        try {
            ps = conexion.prepareStatement("UPDATE menu SET nombre = ? WHERE id_comida = ?");

            ps.setString(1, comida.getNombre());
            ps.setInt(2, comida.getId());

            int resultado = ps.executeUpdate();

            if (resultado > 0) {

                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            
            return false;

        }

    }

    public boolean agregarMenu(Comida comida) {

        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = Conexiones.getConnection();

        try {
            ps = conexion.prepareStatement("INSERT INTO menu (nombre) values (?)");

            ps.setString(1, comida.getNombre());

            int resultado = ps.executeUpdate();

            if (resultado > 0) {

                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            
            return false;

        }

    }
    
 
}
