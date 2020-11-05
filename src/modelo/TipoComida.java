/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class TipoComida extends Comida {

    private int id_tipo;
    private int precio;
    private int rela_menu;

    public int getRela_menu() {
        return rela_menu;
    }

    public void setRela_menu(int rela_menu) {
        this.rela_menu = rela_menu;
    }

    public int obtenerPrecio(String nom_menu, String nom_tipo) {
        
        Connection conexion = Conexiones.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String consulta;
        
        consulta = "SELECT precio FROM tipo_menu,menu"
                + " WHERE tipo_menu.rela_menu = menu.id_comida "
                + "AND tipo_menu.desc_tipo = '"+nom_tipo+"' AND menu.nombre = '"+nom_menu+"'";
        
        try {
             ps = conexion.prepareStatement(consulta);
             rs = ps.executeQuery();
             
             if(rs.next()){
                 
                 precio = rs.getInt("precio");
                 
             }
            
        } catch (Exception error) {
            
            System.out.println("Ocurrio un error en la consulta del precio");
        }
        
        return precio;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String toString() {
        return this.getNombre();
    }

    public Vector<String> mostrarTipoComida(String comida) {

        Connection conexion = Conexiones.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        Vector<String> dat = new Vector<String>();

        //Crearemos una variable de la clase Comida
        TipoComida tipo;

        try {

            ps = conexion.prepareStatement("SELECT id_tipo,desc_tipo,precio FROM tipo_menu,menu"
                    + " WHERE tipo_menu.rela_menu = menu.id_comida AND menu.nombre ='"+comida+"'");
            rs = ps.executeQuery();

            tipo = new TipoComida();
            tipo.setId(0);
            tipo.setNombre("---");
            dat.add(tipo.getNombre());

            while (rs.next()) {

                tipo = new TipoComida();
                tipo.setId(rs.getInt("id_tipo"));
                tipo.setNombre(rs.getString("desc_tipo"));
                tipo.setPrecio(rs.getInt("precio"));
                dat.add(tipo.getNombre());

            }

        } catch (Exception e) {
            System.err.println("Error en tipo Comida" + e);

        }

        return dat;
    }
    
     public int obtenerID(String nomSeccion, String nomTipo) {

        Connection conexion = Conexiones.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String consulta = "SELECT id_tipo FROM tipo_menu,menu WHERE tipo_menu.rela_menu = menu.id_comida "
                + "AND tipo_menu.desc_tipo = '"+nomTipo+"' AND  menu.nombre = '"+nomSeccion+"'";

        try {

            ps = conexion.prepareStatement(consulta);
            rs = ps.executeQuery();

            if (rs.next()) {

               id_tipo = rs.getInt("id_tipo");

            }
        } catch (Exception e) {

            System.out.println("Ocurrio un error al obtener el ID");
        }

        return id_tipo;

    }
    
   

}
