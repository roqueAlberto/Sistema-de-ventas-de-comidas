package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import vista.Venta;


public class GestionVentas  {

    
    private String fecha ;

   
    
    public void setFecha(String fecha){
        this.fecha = fecha;
        
    }
      

    public DefaultTableModel cargarRegistro() {
        
        

        String [] columnas = {"Fecha","Hora","Seccion","Tipo","Cantidad","SubTotal"};
        
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        
        String[] registros = new String[6];

        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = Conexiones.getConnection();

        try {
            
            //String fecha = devolverFecha();

            
            String sql = "SELECT distinct fecha,hora,nombre,desc_tipo,cantidad,precio_total FROM venta,menu,tipo_menu,venta_menu "
                    + "WHERE venta_menu.rela_tipo = tipo_menu.id_tipo AND venta_menu.rela_venta = venta.id_venta"
                    + " AND tipo_menu.rela_menu = menu.id_comida AND venta.fecha = '"+fecha+"'";
            

            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                
                registros[0] = rs.getString("fecha");
                registros[1] = rs.getString("hora");
                registros[2] = rs.getString("nombre");
                registros[3] = rs.getString("desc_tipo");
                registros[4] = String.valueOf(rs.getInt("cantidad"));
                registros[5] = String.valueOf(rs.getInt("precio_total"));
                modelo.addRow(registros);

            }
            
            rs.close();

           

        } catch (Exception e) {

            System.err.println("Error" +e);
            return modelo;

        }
         return modelo;

    }
    
    
    
    
    public DefaultTableModel cargarRegistroInicio() {

        String [] columnas = {"Fecha","Hora","Seccion","Tipo","Cantidad","SubTotal"};
        
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        
        String[] registros = new String[6];
        
        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = Conexiones.getConnection();

        try {
            
            String fecha_actual = devolverFecha();

            
            String sql = "SELECT distinct fecha,hora,nombre,desc_tipo,cantidad,precio_total FROM venta,menu,tipo_menu,venta_menu "
                    + "WHERE venta_menu.rela_tipo = tipo_menu.id_tipo AND venta_menu.rela_venta = venta.id_venta"
                    + " AND tipo_menu.rela_menu = menu.id_comida AND venta.fecha = '"+fecha_actual+"' ORDER BY venta.hora ";

            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                
                registros[0] = rs.getString("fecha");
                registros[1] = rs.getString("hora");
                registros[2] = rs.getString("nombre");
                registros[3] = rs.getString("desc_tipo");
                registros[4] = String.valueOf(rs.getInt("cantidad"));
                registros[5] = String.valueOf(rs.getInt("precio_total"));
                modelo.addRow(registros);

            }

           rs.close();

        } catch (Exception e) {

            System.err.println("Error" +e);
            return modelo;

        }
         return modelo;

    }
    
    
    
    
    
    public DefaultTableModel mostrarTodo() {

        String [] columnas = {"Fecha","Total"};
        
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        
        String[] registros = new String[2];
        

        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = Conexiones.getConnection();

        try {
            
           /*   String sql = "SELECT distinct fecha,hora,nombre,desc_tipo,cantidad,precio_total FROM venta,menu,tipo_menu,venta_menu "
                    + "WHERE venta_menu.rela_tipo = tipo_menu.id_tipo AND venta_menu.rela_venta = venta.id_venta"
                    + " AND tipo_menu.rela_menu = menu.id_comida ORDER BY fecha desc "; */
           
           String consulta = " select venta.fecha,sum(venta_menu.precio_total) "
                   + "FROM venta,venta_menu WHERE venta.id_venta = venta_menu.rela_venta GROUP BY venta.fecha";

            ps = conexion.prepareStatement(consulta);
            rs = ps.executeQuery();

            while (rs.next()) {

                
                registros[0] = rs.getString("venta.fecha");
                registros[1] = String.valueOf(rs.getInt("sum(venta_menu.precio_total)"));
               
                modelo.addRow(registros);

            }
     
            rs.close();
           

        } catch (Exception e) {

            System.err.println("Error" +e);
            return modelo;

        }
         return modelo;

    }
    
    
    
    public int sumarTotal(Venta v){
        
        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = Conexiones.getConnection();
        int suma = 0;
        
        try{
            
            ps = conexion.prepareStatement("SELECT sum(precio_total) FROM venta_menu ");
            rs = ps.executeQuery();
            
             if(rs.next()){
                 
                // System.out.println( rs.getInt("sum(precio_total)"));
                 suma = rs.getInt("sum(precio_total)");
                 
             }
            rs.close();
            return suma;
            
        }catch(Exception e) {
            
            System.err.println("No se puede sumar" +e);
            
            return suma;
            
        }
        
     
        
        
    }
    
    
     public static String devolverFecha(){
          Date fecha_actual = new Date();
          SimpleDateFormat formato_fecha_actual = new SimpleDateFormat("YYYY-MM-dd");
           
         return formato_fecha_actual.format(fecha_actual);
         
     }

}
