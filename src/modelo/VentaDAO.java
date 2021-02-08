/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Roque
 */
public interface VentaDAO {

    public void insertar(Venta v);

    public int obtenerUltimaventa();

    public void insertarDetalleventa(Venta v, int tipo);
    
    public DefaultTableModel listar_fechaActual();
    public DefaultTableModel listar(String f_seleccionada);
     public DefaultTableModel mostrarTodo();

}
