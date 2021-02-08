/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Conexion.Pool;
import java.util.ArrayList;

/**
 *
 * @author Roque
 */
public class GastoDAOImpl  implements GastoDAO{

    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;

    private Connection getConnection() throws SQLException {

        return Pool.getConexion();
    }

    @Override
    public DefaultTableModel listar() {

        String[] columnas = {"Fecha", "Total"};

        DefaultTableModel modelo_gasto = new DefaultTableModel(null, columnas);

        String[] registros = new String[2];
        String consulta = "SELECT fecha,sum(monto) FROM compras GROUP BY fecha";

        try {

            conexion = getConnection();

            ps = conexion.prepareStatement(consulta);
            rs = ps.executeQuery();

            while (rs.next()) {

                registros[0] = rs.getString(1);
                registros[1] = String.valueOf(rs.getInt(2));

                modelo_gasto.addRow(registros);

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "No se pueden mostrar los datos");
            System.out.println(e);

        } finally {

            try {

                ps.close();
                rs.close();
                Pool.closeConexion(conexion);

            } catch (SQLException e) {
            }
        }
        return modelo_gasto;

    }

    @Override
    public boolean insertar(ArrayList<Gasto> gasto) {

        int resultado = 0;
        boolean verificacion = false;

        try {

            conexion = getConnection();

            ps = conexion.prepareStatement("INSERT INTO compras (descripcion_com,monto,fecha) VALUES (?,?,?)");

            for (Gasto c : gasto) {

                ps.setString(1, c.getDesc_gasto());
                ps.setInt(2, c.getMonto());
                ps.setString(3, c.getFecha());
                resultado = ps.executeUpdate();

            }

            if (resultado > 0) {

                verificacion = true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede insertar datos");

        } finally {

            try {

                ps.close();
                Pool.closeConexion(conexion);

            } catch (SQLException e) {
            }
        }
        
        return verificacion;

    }

}
