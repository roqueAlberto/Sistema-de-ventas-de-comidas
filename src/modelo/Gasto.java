/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Conexiones;
import modelo.TipoComida;
import modelo.Compra;

public class Gasto {

    PreparedStatement ps;
    ResultSet rs;

    public boolean datosCompra(ArrayList<Compra> compra) {

        Connection conexion = Conexiones.getConnection();

        try {
            
            int insercion = 0;

            ps = conexion.prepareStatement("INSERT INTO compras (descripcion_com,sub_total,fecha) VALUES (?,?,?)");
            
            for(Compra c : compra) {
                
                    
                    ps.setString(1, c.getDescripcion());
                    ps.setInt(2, c.getCosto());
                    ps.setDate(3, c.getFecha());
            insercion=ps.executeUpdate();
                
            }

            

            

            if (insercion > 0) {

                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede insertar datos");
            System.out.println(e);
            return false;

        }

    }

}
