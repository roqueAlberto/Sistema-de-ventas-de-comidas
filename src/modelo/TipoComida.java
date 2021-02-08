/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


public class TipoComida extends Seccion {

    private int id_tipo;
    private int precio;
    private int rela_seccion;

    public int getRela_seccion() {
        return rela_seccion;
    }

    public void setRela_seccion(int rela_seccion) {
        this.rela_seccion = rela_seccion;
    }

   

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String toString() {
        return this.getDescripcion();
    }

    
    
  
    
   

}
