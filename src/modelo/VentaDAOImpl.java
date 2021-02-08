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

import Conexion.Pool;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Roque
 */
public class VentaDAOImpl implements VentaDAO{

    Seccion seccion;
    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;
   
    
    
    //====FECHA ACTUAL===
    java.util.Date date = new java.util.Date();
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-YYYY");
    String fecha_a = formato.format(date);
    
    //===================

    private Connection getConnection() throws SQLException {

        return Pool.getConexion();
    }

   

    @Override
    public void insertar(Venta v) {

        try {

            conexion = getConnection();
            ps = conexion.prepareStatement("INSERT INTO venta (fecha,hora,rela_cliente) values (?,?,?)");
            ps.setString(1, v.getFecha() );
            ps.setString(2, v.getHora());
            ps.setInt(3, v.getRela_cliente());

            ps.executeUpdate();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Hubo un error al realizar la venta");

        } finally {

            try {

                ps.close();
                Pool.closeConexion(conexion);

            } catch (SQLException e) {
            }
        }

    }

    @Override
    public int obtenerUltimaventa() {

        int ultima_venta = 0;

        try {

            conexion = getConnection();
            ps = conexion.prepareStatement("SELECT id_venta FROM venta  GROUP BY id_venta DESC LIMIT 1");

            rs = ps.executeQuery();

            if (rs.next()) {
                ultima_venta = rs.getInt(1);

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Se  encontro un error al obtener el id de la venta");
            JOptionPane.showMessageDialog(null, e.getMessage());

        } finally {

            try {

                ps.close();
                rs.close();
                Pool.closeConexion(conexion);

            } catch (SQLException e) {
            }
        }

        return ultima_venta;
    }

    @Override
    public void insertarDetalleventa(Venta v, int tipo) {

        try {

            conexion = getConnection();
            ps = conexion.prepareStatement("INSERT INTO detalle_venta (rela_tipocomida,rela_venta,cantidad,total) values(?,?,?,?)");

            ps.setInt(1, tipo);
            ps.setInt(2, v.getId_venta());

            ps.setInt(3, v.getCantidad());

            ps.setInt(4, v.getTotal());

            ps.executeUpdate();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Ocurrio un error al cargar los datos");
        
           
           

        } finally {

            try {

                ps.close();
                Pool.closeConexion(conexion);

            } catch (SQLException e) {
            }

        }

    }

    @Override
    public DefaultTableModel listar_fechaActual() {

        String[] columnas = {"Fecha", "Hora", "Seccion", "Tipo", "Cantidad", "SubTotal"};

        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        String[] registros = new String[6];

       

        String consulta = "select fecha,hora,desc_seccion,desc_tipo,cantidad,total FROM venta INNER JOIN detalle_venta ON venta.id_venta = detalle_venta.rela_venta INNER JOIN tipo_comida ON tipo_comida.id_tipo = detalle_venta.rela_tipocomida INNER JOIN seccion ON tipo_comida.rela_seccion = seccion.id_seccion AND venta.fecha = ? order by venta.hora";

        try {

            conexion = getConnection();

            ps = conexion.prepareStatement(consulta);
            ps.setString(1, fecha_a);
            rs = ps.executeQuery();

            while (rs.next()) {

                registros[0] = rs.getString(1);
                registros[1] = rs.getString(2);
                registros[2] = rs.getString(3);
                registros[3] = rs.getString(4);
                registros[4] = String.valueOf(rs.getInt(5));
                registros[5] = String.valueOf(rs.getInt(6));
                modelo.addRow(registros);

            }

        

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
          

        } finally {

            try {

                ps.close();
                rs.close();
                Pool.closeConexion(conexion);

            } catch (SQLException e) {
            }
        }
        return modelo;

    }

    @Override
    public DefaultTableModel listar(String f_seleccionada) {

        String[] columnas = {"Fecha", "Hora", "Seccion", "Tipo", "Cantidad", "SubTotal"};

        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        String[] registros = new String[6];

        String consulta = "select fecha,hora,desc_seccion,desc_tipo,cantidad,total FROM venta INNER JOIN detalle_venta ON venta.id_venta = detalle_venta.rela_venta INNER JOIN tipo_comida ON tipo_comida.id_tipo = detalle_venta.rela_tipocomida INNER JOIN seccion on tipo_comida.rela_seccion = seccion.id_seccion AND venta.fecha = ? ";

        try {

            conexion = getConnection();

            ps = conexion.prepareStatement(consulta);
            ps.setString(1, f_seleccionada);
            rs = ps.executeQuery();

            while (rs.next()) {

                registros[0] = rs.getString(1);
                registros[1] = rs.getString(2);
                registros[2] = rs.getString(3);
                registros[3] = rs.getString(4);
                registros[4] = String.valueOf(rs.getInt(5));
                registros[5] = String.valueOf(rs.getInt(6));
                modelo.addRow(registros);

            }

         

        } catch (SQLException e) {

            System.err.println("Error" + e);
          

        } finally {

            try {

                ps.close();
                rs.close();
                Pool.closeConexion(conexion);

            } catch (SQLException e) {
            }
        }
        
        return modelo;

    }
    
    
    @Override
    public DefaultTableModel mostrarTodo() {

        String [] columnas = {"Fecha","Total"};
        
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        
        String[] registros = new String[2];
         String consulta = " select venta.fecha,sum(detalle_venta.total) "
                   + "FROM venta,detalle_venta WHERE venta.id_venta = detalle_venta.rela_venta GROUP BY venta.fecha";


        try {
            
           
           conexion = getConnection();
          

            ps = conexion.prepareStatement(consulta);
            rs = ps.executeQuery();

            while (rs.next()) {

                
                registros[0] = rs.getString(1);
                registros[1] = String.valueOf(rs.getInt(2));
               
                modelo.addRow(registros);

            }
     
           
           

        } catch (SQLException e) {

            System.err.println("Error" +e);
           

        }finally{
            
            try {
                
                ps.close();
                rs.close();
                Pool.closeConexion(conexion);
                
            } catch (SQLException e) {
            }
        }
         return modelo;

    }

}
