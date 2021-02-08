/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Vector;

/**
 *
 * @author Roque
 */
public interface TipoDAO {
    
    public Vector<String> listar(String seccion);
    public int getID(String nomSeccion, String nomTipo);
    public int getID(String nomTipo);
    public int getPrecios(String nom_menu, String nom_tipo);
    public boolean modificar(TipoComida tc);
    public boolean insertar(TipoComida tipo);
}
