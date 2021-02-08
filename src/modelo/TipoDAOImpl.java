/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Conexion.Pool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Roque
 */
public class TipoDAOImpl implements TipoDAO{

    Connection conexion;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    private Connection getConnection() throws SQLException {

        return Pool.getConexion();
    }

    @Override
    public Vector<String> listar(String seccion) {

        Vector<String> lista_tipo = new Vector<String>();
        TipoComida tipo;

        try {

            conexion = getConnection();
            ps = conexion.prepareStatement("SELECT id_tipo,desc_tipo,precio FROM tipo_comida,seccion"
                    + " WHERE tipo_comida.rela_seccion = seccion.id_seccion AND seccion.desc_seccion ='" + seccion + "'");
            rs = ps.executeQuery();

            tipo = new TipoComida();
            tipo.setId(0);
            tipo.setDescripcion("---");
            lista_tipo.add(tipo.getDescripcion());

            while (rs.next()) {

                tipo = new TipoComida();
                tipo.setId(rs.getInt(1));
                tipo.setDescripcion(rs.getString(2));
                tipo.setPrecio(rs.getInt(3));
                
                lista_tipo.add(tipo.getDescripcion());

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de comidas");
            JOptionPane.showMessageDialog(null, e.getMessage());
            
           

        } finally {
            try {
                ps.close();
                rs.close();
                Pool.closeConexion(conexion);
                
                
            } catch (SQLException e) {
            }

        }
        return lista_tipo;
    }
    
    

    @Override
    public int getID(String nomSeccion, String nomTipo) {

        String consulta = "SELECT id_tipo FROM tipo_comida,seccion WHERE tipo_comida.rela_seccion = seccion.id_seccion "
                + "AND tipo_comida.desc_tipo = '" + nomTipo + "' AND  seccion.desc_seccion = '" + nomSeccion + "'";

        int id_tipo = 0;

        try {

             conexion = getConnection();
            ps = conexion.prepareStatement(consulta);
            rs = ps.executeQuery();

            if (rs.next()) {

                id_tipo = rs.getInt("id_tipo");

            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al obtener la lista de comidas");
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                Pool.closeConexion(conexion);
            } catch (SQLException e) {
            }

        }

        return id_tipo;

    }
    
    //Sobrecarga del metodo 'getID'
    @Override
     public int getID(String nomTipo) {

        String consulta = "SELECT id_tipo FROM tipo_comida WHERE desc_tipo = ?";

        int id_tipo = 0;

        try {

            conexion = getConnection();
            ps = conexion.prepareStatement(consulta);
            ps.setString(1,nomTipo);
            rs = ps.executeQuery();

            if (rs.next()) {

                id_tipo = rs.getInt(1);

            }
        } catch (SQLException e) {

        
        } finally {
            try {
                ps.close();
                rs.close();
                Pool.closeConexion(conexion);
            } catch (SQLException e) {
            }

        }

        return id_tipo;

    }
    
    @Override
    public int getPrecios(String nom_menu, String nom_tipo) {
        
        
        String consulta;
        int precio = 0;
        
        consulta = "SELECT precio FROM tipo_comida,seccion"
                + " WHERE tipo_comida.rela_seccion = seccion.id_seccion "
                + "AND tipo_comida.desc_tipo = '"+nom_tipo+"' AND seccion.desc_seccion = '"+nom_menu+"'";
        
        try {
            conexion = getConnection();
             ps = conexion.prepareStatement(consulta);
             rs = ps.executeQuery();
             
             if(rs.next()){
                 
                 precio = rs.getInt(1);
                 
             }
            
        } catch (SQLException error) {
            
             JOptionPane.showMessageDialog(null, "Error al obtener la lista de comidas");
             JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            try {
                ps.close();
                rs.close();
                Pool.closeConexion(conexion);
            } catch (SQLException e) {
            }

        }
        
        return precio;
    }
    
    @Override
    public boolean modificar(TipoComida tc){
        
        int result;
        boolean veri = false;
        
        try {
            
            conexion = getConnection();
            
             ps = conexion.prepareStatement("UPDATE tipo_comida SET desc_tipo=?,precio=? WHERE id_tipo = ?");

            ps.setString(1, tc.getDescripcion());
            ps.setInt(2, tc.getPrecio());
            ps.setInt(3, tc.getId());
            
           result = ps.executeUpdate();
           
            if (result>0) {
                
               veri = true;
                
            }
            
           
            
           
            
        } catch (SQLException e) {
            
            
            
        }finally{
            
            try {
                
                ps.close();
                Pool.closeConexion(conexion);
                
            } catch (SQLException e) {
            }
        }
        
        return veri;
    }
    
    
    @Override
     public boolean insertar(TipoComida tipo) {
         
         int result;
         boolean veri = false;


        try {
            
            conexion = getConnection();
            ps = conexion.prepareStatement("INSERT INTO tipo_comida (desc_tipo,precio,rela_seccion) VALUES (?,?,?)");

            ps.setString(1, tipo.getDescripcion());
            ps.setInt(2, tipo.getPrecio());
            ps.setInt(3, tipo.getRela_seccion());

            result = ps.executeUpdate();

            if (result > 0) {

                veri =true;
            } 

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Imposible añadir un nuevo tipo de comida en la Base de Datos");
            

        }finally{
            
            try {
                
                ps.close();
                Pool.closeConexion(conexion);
                
            } catch (SQLException e) {
            }
        }
        
        return veri;

    }

}
