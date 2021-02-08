/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Roque
 */
public interface GastoDAO {
    
    public DefaultTableModel listar();
    public boolean insertar(ArrayList<Gasto> gasto);
    
}
