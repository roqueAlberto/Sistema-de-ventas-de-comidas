/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public interface ClienteDAO {

    public boolean insertar(Cliente cliente, int fila);

    public int obtener_ultimoCliente();

}
