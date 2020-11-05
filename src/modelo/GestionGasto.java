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

/**
 *
 * @author Roque
 */
public class GestionGasto {
    
    
    PreparedStatement ps;
    ResultSet rs;
    
    
    
     public DefaultTableModel mostrarTodo() {

        String [] columnas = {"Fecha","Total"};
        
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        
        String[] registros = new String[2];

        Connection conexion = Conexiones.getConnection();

        try {
            
      
            String consulta = "SELECT fecha,sum(sub_total) FROM compras GROUP BY fecha";

            ps = conexion.prepareStatement(consulta);
            rs = ps.executeQuery();

            while (rs.next()) {

                registros[0] = rs.getString("fecha");
                registros[1] = String.valueOf(rs.getInt("sum(sub_total)"));
                
                modelo.addRow(registros);
                

            }
            
            rs.close();

           

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "No se pueden mostrar los datos");
            System.out.println(e);
            return modelo;

        }
         return modelo;

    }
    
}
