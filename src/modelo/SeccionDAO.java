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
public interface SeccionDAO {

    public Vector<String> listar();

    public int obtenerID(String desc_seccion);

    public boolean agregar(Seccion s);

    public boolean modificar(Seccion s);

}
