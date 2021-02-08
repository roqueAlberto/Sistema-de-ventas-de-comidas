/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import Conexion.Pool;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ClienteDAOImpl implements ClienteDAO{

    Seccion seccion;
    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;

    private Connection getConnection() throws SQLException {

        return Pool.getConexion();
    }

    @Override
    public boolean insertar(Cliente cliente, int fila) {

        try {
            conexion = getConnection();
            ps = conexion.prepareStatement("INSERT INTO cliente (nombre,domicilio) values (?,?)");
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDomicilio());

            int resultado = ps.executeUpdate();

            if (resultado > 0) {

                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al cargar los datos del cliente ");
            return false;

        } finally {

            try {

                ps.close();
                Pool.closeConexion(conexion);

            } catch (SQLException e) {
            }
        }

    }

    @Override
    public int obtener_ultimoCliente() {

        
        int ultimo_cliente = 0;

        try {
            conexion = getConnection();
            ps = conexion.prepareStatement("SELECT id_cliente FROM cliente  GROUP BY id_cliente DESC LIMIT 1");

            rs = ps.executeQuery();

            if (rs.next()) {

                ultimo_cliente = rs.getInt(1);

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al obtener el Id del cliente : ");

        } finally {

            try {

                ps.close();
                rs.close();
                Pool.closeConexion(conexion);

            } catch (SQLException e) {
            }
        }

        return ultimo_cliente;

    }

   

}
