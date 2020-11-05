/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import vista.Menu;

public class GestionComida {

//    PreparedStatement ps;
//    ResultSet rs;
    Statement st;
    private int id_cliente;
    private int id_venta;

    Conexion conexion;

    public boolean obtenerID(Comida comida) {
        
        PreparedStatement ps;
        ResultSet rs ;
        Connection conn = Conexiones.getConnection();

        try {

            ps = conn.prepareStatement("select id_comida from menu where nombre = ?");

            ps.setString(1, comida.getNombre());

            rs = ps.executeQuery();

            if (rs.next()) {

                comida.setId(rs.getInt(1));
                
                rs.close();

                return true;
            } else {
                rs.close();
                return false;
            }
            

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al insertar datos");
            return false;

        }

    }

    public boolean cargarDatosenBDCliente(Cliente cliente, int fila) {
        
        PreparedStatement ps;
        

        Connection conn = Conexiones.getConnection();

        try {

            ps = conn.prepareStatement("INSERT INTO cliente (nombre,domicilio) values (?,?)");
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDomicilio());

            int resultado = ps.executeUpdate();

            if (resultado > 0) {

                return true;
            } else {
                return false;
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al cargar los datos del cliente ");
            return false;

        }

    }

    public boolean getIdCliente(Cliente c, Menu menu, int fila) {
        
        PreparedStatement ps;
    ResultSet rs;
        
        
        Connection conn = Conexiones.getConnection();

        try {

            ps = conn.prepareStatement("SELECT id_cliente FROM cliente WHERE nombre = ? AND domicilio = ?");
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDomicilio());

            rs = ps.executeQuery();

            if (rs.next()) {

                id_cliente = (rs.getInt("id_cliente"));
                
                rs.close();

                return true;

            } else {
                
                rs.close();
                return false;
            }

        } catch (Exception e) {
           
            JOptionPane.showMessageDialog(null, "Error al obtener el Id del cliente : ");
            
            return false;

        }

    }

    public boolean insertVenta(Menu menu, int fila) {

        PreparedStatement ps;
   
        Connection conn = Conexiones.getConnection();

        try {
            
            Date fecha = Date.valueOf(menu.pedido1.tf_dateAlreves.getText());

            ps = conn.prepareStatement("INSERT INTO venta (fecha,hora,rela_cliente) values (?,?,?)");
            ps.setDate(1, fecha);
            ps.setString(2, menu.pedido1.tabla.getValueAt(fila, 2).toString());
            ps.setInt(3, id_cliente);

            int resultado = ps.executeUpdate();

            if (resultado > 0) {

                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
         
            JOptionPane.showMessageDialog(null, "Hubo un error en la seccion ventas");
            return false;

        }

    }

    public boolean obtenerIdVenta() {

        PreparedStatement ps;
         ResultSet rs;
        Connection conn = Conexiones.getConnection();
        

        try {
            
            int id_ultimo= 0;

            ps = conn.prepareStatement("SELECT  MAX(id_venta)  FROM venta ");
           // ps.setInt(1, id_cliente);
           

            rs = ps.executeQuery();

            if (rs.next()) {
                id_venta = rs.getInt(("MAX(id_venta)"));
              //  id_venta = rs.getInt("id_venta");
              
              rs.close();
                return true;

            } else {
                
                rs.close();
                return false;
            }

        } catch (Exception e) {
           
            JOptionPane.showMessageDialog(null, "Hemos encontrado un error al obtener el id de la venta");
            return false;

        }

    }

    //*********************************************************************
    public boolean cargarDatosenBDVenta(Menu menu, int fila, TipoComida tipo) {
        
        PreparedStatement ps;
        ResultSet rs;

        Connection conn = Conexiones.getConnection();

        try {
            ps = conn.prepareStatement("SELECT id_tipo FROM tipo_menu where desc_tipo = ?");
            ps.setString(1, tipo.getNombre());

            rs = ps.executeQuery();

            if (rs.next()) {

                tipo.setId(rs.getInt("id_tipo"));
              rs.close();
            }

        } catch (Exception e) {
   
            JOptionPane.showMessageDialog(null, "Ocurrio un error al obtener los datos");
            return false;

        } finally {
            try {

                ps = conn.prepareStatement("INSERT INTO venta_menu (rela_tipo,rela_venta,cantidad,precio_total) values(?,?,?,?)");

                ps.setInt(1, tipo.getId());
                ps.setInt(2, id_venta);

                int cantidad = Integer.parseInt(menu.pedido1.tabla.getValueAt(fila, 5).toString());
                ps.setInt(3, cantidad);

                int precio = Integer.parseInt(menu.pedido1.tabla.getValueAt(fila, 6).toString());
                ps.setInt(4, precio);

                int r = ps.executeUpdate();

                if (r > 0) {

                    return true;
                } else {
                    return false;
                }

            } catch (Exception e) {

               
                JOptionPane.showMessageDialog(null, "Ocurrio un error al cargar los datos");
                return false;

            }

        }

    }

}
