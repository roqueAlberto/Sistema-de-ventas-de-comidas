
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Comida;
import modelo.GestionComida;

import modelo.TipoComida;
import modelo.Compra;
import vista.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import modelo.Ajustes;
import modelo.Gasto;
import modelo.GestionVentas;

/**
 *
 * @author Roque
 */
public class Controlador implements ItemListener, ActionListener, MouseListener {

    Comida comida;
    TipoComida tipo;
    Menu menu;
    Compra c;
    Compra com;
    GestionComida gc;
    Cliente cliente;

    Ajustes ajuste;

    Gasto gasto;
    GestionVentas gv;
    

    public Controlador(Menu menu, Comida comida, TipoComida tipo, GestionComida gc, Cliente cliente, Compra c, Gasto gasto, Ajustes ajuste, GestionVentas gv) {

        this.comida = comida;
        this.tipo = tipo;
        this.menu = menu;
        this.gc = gc;
        this.cliente = cliente;
        this.c = c;
        this.gasto = gasto;
        this.ajuste = ajuste;
        this.gv = gv;

        menu.pedido1.cb_comida.addItemListener(this);
        menu.pedido1.cb_tipo.addItemListener(this);
        menu.setting1.panelModificar1.cb_seccionM.addItemListener(this);
        menu.setting1.panelModificar1.cb_platoM.addItemListener(this);
        menu.setting1.panelModificar1.lb_editar.addMouseListener(this);
        menu.setting1.btn_guardar.addActionListener(this);
        menu.setting1.btn_agregar.addActionListener(this);
        menu.setting1.panelAgregar1.cb_agregarS.addItemListener(this);

        menu.pedido1.tabla.addMouseListener(this);
        menu.pedido1.btn_agregarP.addActionListener(this);
        menu.pedido1.btn_cancelarPedido.addActionListener(this);
        menu.pedido1.btn_entregar.addActionListener(this);
        menu.compra1.btn_g.addActionListener(this);

        menu.setting1.panelAgregar1.btn_addS.addActionListener(this);

    }

    public void iniciar() {

       menu.pedido1.setVisible(true);

        DefaultComboBoxModel<String> modelo_string = new DefaultComboBoxModel<String>(comida.mostrarComidas());

        menu.pedido1.cb_comida.setModel(modelo_string);

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        if (e.getSource() == menu.pedido1.cb_comida) {

            if (e.getStateChange() == ItemEvent.SELECTED) {

                String com = menu.pedido1.cb_comida.getSelectedItem().toString();
                

                DefaultComboBoxModel<String> modelo_tipo = new DefaultComboBoxModel<String>(tipo.mostrarTipoComida(com));
                menu.pedido1.cb_tipo.setModel(modelo_tipo);

                menu.pedido1.tf_precio.setText("");

                

            }
            
        } 

            if (e.getSource() == menu.pedido1.cb_tipo) {

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    
                    String nom_menu = menu.pedido1.cb_comida.getSelectedItem().toString();
                    String nom_tipo = menu.pedido1.cb_tipo.getSelectedItem().toString();
                    

                   // TipoComida t = (TipoComida) menu.pedido1.cb_tipo.getSelectedItem();

                  //  menu.pedido1.tf_precio.setText(String.valueOf(t.getPrecio()));
                  menu.pedido1.tf_precio.setText(String.valueOf(tipo.obtenerPrecio(nom_menu,nom_tipo)));

                    if (menu.pedido1.cb_tipo.getSelectedIndex() == 0) {

                        menu.pedido1.tf_precio.setText("");

                    }

                }
            }

            //.............................SETTING.........................................
            if (e.getSource() == menu.setting1.panelModificar1.cb_seccionM) {
                
                
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    //Limpiar jTextField
                    menu.setting1.panelModificar1.tf_tipoM.setText("");
                    menu.setting1.panelModificar1.tf_precioM.setText("");
                    
                  String com = menu.setting1.panelModificar1.cb_seccionM.getSelectedItem().toString();
                    
                    DefaultComboBoxModel<String> modelo_tipo = new DefaultComboBoxModel<String>(tipo.mostrarTipoComida(com));
                    menu.setting1.panelModificar1.cb_platoM.setModel(modelo_tipo);

                    menu.setting1.panelModificar1.tf_idMenu.setText(String.valueOf(comida.obtenerID(com)));
                    
                   
                }

            }

            if (e.getSource() == menu.setting1.panelModificar1.cb_platoM) {

                if (e.getStateChange() == ItemEvent.SELECTED) {

                    String nom_menuM = menu.setting1.panelModificar1.cb_seccionM.getSelectedItem().toString();
                    String nom_tipoM = menu.setting1.panelModificar1.cb_platoM.getSelectedItem().toString();

                   
                    menu.setting1.panelModificar1.tf_precioM.setText(String.valueOf(tipo.obtenerPrecio(nom_menuM,nom_tipoM)));
                    menu.setting1.panelModificar1.tf_tipoM.setText(nom_tipoM);
                    menu.setting1.panelModificar1.tf_idTipo.setText(String.valueOf(tipo.obtenerID(nom_menuM, nom_tipoM)));

                }
            }

            if (e.getSource() == menu.setting1.panelAgregar1.cb_agregarS) {
                
                String nomCom = menu.setting1.panelAgregar1.cb_agregarS.getSelectedItem().toString();

                //Comida com = (Comida) menu.setting1.panelAgregar1.cb_agregarS.getSelectedItem();

                menu.setting1.panelAgregar1.lb_idMenu.setText(String.valueOf(comida.obtenerID(nomCom)));

            }

        }
    

    @Override
    public void actionPerformed(ActionEvent e) {

        //Si se pulsa el boton Agregar
        if (e.getSource() == menu.pedido1.btn_agregarP) {

            if (menu.pedido1.cb_comida.getSelectedIndex() > 0 && menu.pedido1.cb_tipo.getSelectedIndex() > 0) {

                int opcion = JOptionPane.showConfirmDialog(null, "¿Solicito algo más la misma persona?");
              

                if (opcion == 0) {

                    menu.pedido1.insertarDatos();
                    menu.pedido1.sp_cantidad.setValue(1);
                    menu.pedido1.cb_comida.setSelectedIndex(0);
                    menu.pedido1.cb_tipo.setSelectedIndex(0);
                    

                } else if (opcion == 1) {
                    menu.pedido1.insertarDatos();
                    menu.pedido1.tf_nom.setText("");
                    menu.pedido1.tf_dom.setText("");
                    menu.pedido1.cb_comida.setSelectedIndex(0);
                    menu.pedido1.cb_tipo.setSelectedIndex(0);

                    menu.pedido1.sp_cantidad.setValue(1);

                }

            } else {

              //  JOptionPane.showMessageDialog(null, "Complete los campos necesarios");
                JOptionPane.showMessageDialog(null, "Complete los campos necesarios","Error", 0);
            }

            //Cantidad de filas
            int cant_pedidos = menu.pedido1.modelo.getRowCount();
            int cantidad_p = 0;

            for (int fila = 0; fila < menu.pedido1.tabla.getRowCount(); fila++) {
                cantidad_p += Integer.parseInt(menu.pedido1.tabla.getValueAt(fila, 5).toString());

            }

            //Mostrar la cantidad de pedidos(rows)
            menu.pedido1.lb_canPedidos.setText(String.valueOf(cantidad_p));

        }

        //*************************************
        if (e.getSource() == menu.pedido1.btn_cancelarPedido) {

            int fila = menu.pedido1.tabla.getSelectedRow();

            if (fila >= 0) {

                menu.pedido1.modelo.removeRow(fila);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione pedido a eliminar");
            }

            //Cantidad de filas
            int cant_pedidos = menu.pedido1.modelo.getRowCount();

            //Mostrar la cantidad de pedidos(rows)
            menu.pedido1.lb_canPedidos.setText(String.valueOf(cant_pedidos));

        }

        //  try {
        if (e.getSource() == menu.pedido1.btn_entregar) {

            try {

                //Extracion de datos
                int fila = menu.pedido1.tabla.getSelectedRow();
                String nombre = menu.pedido1.tabla.getValueAt(fila, 0).toString();
                String domicilio = menu.pedido1.tabla.getValueAt(fila, 1).toString();
                String tipo_plato = menu.pedido1.tabla.getValueAt(fila, 4).toString();

                //--------------------------------
                String fecha_cap = menu.pedido1.tabla.getValueAt(fila, 1).toString();

                //Asignacion a variables cliente.
                cliente.setNombre(nombre);
                cliente.setDomicilio(domicilio);

                //Asignacion a variables tipoPlato.
                tipo.setNombre(tipo_plato);

                if (gc.cargarDatosenBDCliente(cliente, fila)) {

                    gc.getIdCliente(cliente, menu, fila);
                    gc.insertVenta(menu, fila);
                    gc.obtenerIdVenta();
                    gc.cargarDatosenBDVenta(menu, fila, tipo);

                    JOptionPane.showMessageDialog(null, "¡Pedido entregado!");

                    menu.venta1.tablaV.setModel(gv.cargarRegistroInicio());
                    menu.venta1.obtenerTotal();

                    menu.pedido1.modelo.removeRow(fila);
                 
                    
                    
                   

                } else {
                    JOptionPane.showMessageDialog(null, "¡Error en la entrega!");
                }

            } catch (Exception eAgreP) {

                JOptionPane.showMessageDialog(null, "¡Accion denegada!");

            }

            //Cantidad de filas
            int cant_pedidos = menu.pedido1.modelo.getRowCount();

            //Mostrar la cantidad de pedidos(rows)
            menu.pedido1.lb_canPedidos.setText(String.valueOf(cant_pedidos));
        }

        //.............COMPRA......................
        if (e.getSource() == menu.compra1.btn_g) {

            int total = 0;
            
            ArrayList<Compra> descri = new ArrayList<Compra>();

            for (int i = 0; i < menu.compra1.tabla_compra.getRowCount(); i++) {
                
                com = new Compra();
                
                com.setDescripcion(menu.compra1.tabla_compra.getValueAt(i, 0).toString());
                com.setCosto(Integer.parseInt(menu.compra1.tabla_compra.getValueAt(i, 1).toString()));
                com.setFecha(Date.valueOf(menu.compra1.lb_fechaAlreves.getText()));
                
                
                
                descri.add(com);
                
            
            }

            menu.compra1.modelo_compra.setRowCount(0);
            menu.compra1.lb_total.setText("---");

            if (gasto.datosCompra(descri)) {

                JOptionPane.showMessageDialog(null, "Datos guardados");

            }

        }

        //.............AJUSTE......................
        if (e.getSource() == menu.setting1.btn_guardar) {

            try {

                tipo.setNombre(menu.setting1.panelModificar1.tf_tipoM.getText());
                tipo.setPrecio(Integer.parseInt(menu.setting1.panelModificar1.tf_precioM.getText()));
                tipo.setId(Integer.parseInt(menu.setting1.panelModificar1.tf_idTipo.getText()));

                if (ajuste.modificarTipoComida(tipo)) {
                    JOptionPane.showMessageDialog(null, "¡Modificacion Exitosa!");
                    menu.setting1.panelModificar1.cb_platoM.setSelectedIndex(0);
                    menu.setting1.panelModificar1.cb_seccionM.setSelectedIndex(0);
                    menu.setting1.panelModificar1.tf_idMenu.setText("");
                    menu.setting1.panelModificar1.tf_idTipo.setText("");
                    menu.setting1.panelModificar1.tf_tipoM.setText("");
                    menu.setting1.panelModificar1.tf_precioM.setText("");

                }

            } catch (Exception buModificar) {

                JOptionPane.showMessageDialog(null, "¡Por favor complete los datos correctamente!");
            }

        }

        if (e.getSource() == menu.setting1.btn_agregar) {

            try {

                tipo.setRela_menu(Integer.parseInt(menu.setting1.panelAgregar1.lb_idMenu.getText()));
                tipo.setNombre(menu.setting1.panelAgregar1.tf_agregar.getText());
                tipo.setPrecio(Integer.parseInt(menu.setting1.panelAgregar1.tf_precioA.getText()));

                if (ajuste.insertarDatosenTipo(tipo)) {
                    JOptionPane.showMessageDialog(null, "¡Agregado con exito!");
                    menu.setting1.panelAgregar1.cb_agregarS.setSelectedIndex(0);
                    menu.setting1.panelAgregar1.lb_idMenu.setText("");
                    menu.setting1.panelAgregar1.tf_agregar.setText("");
                    menu.setting1.panelAgregar1.tf_precioA.setText("");

                }

            } catch (Exception bug) {

                JOptionPane.showMessageDialog(null, "¡Por favor complete los datos correctamente!");
            }

        }

        if (e.getSource() == menu.setting1.panelAgregar1.btn_addS) {

            try {

                String nom_Seccion;

                nom_Seccion = JOptionPane.showInputDialog(null, "Ingrese nombre para la nueva seccion");

                comida.setNombre(nom_Seccion);

                if (ajuste.agregarMenu(comida)) {

                    JOptionPane.showMessageDialog(null, "Sección agregada");
                    menu.setting1.panelAgregar1.cb_agregarS.setSelectedIndex(0);
                    menu.setting1.panelAgregar1.tf_agregar.setText("");
                    menu.setting1.panelAgregar1.tf_precioA.setText("");
                    menu.setting1.panelAgregar1.lb_idMenu.setText("");

                    //ACTUALIZO LOS COMBOBOX
                  
          DefaultComboBoxModel<String> modelo_string = new DefaultComboBoxModel<String>(comida.mostrarComidas());

                    menu.pedido1.cb_comida.setModel(modelo_string);
                    menu.setting1.panelModificar1.cb_seccionM.setModel(modelo_string);
                    menu.setting1.panelAgregar1.cb_agregarS.setModel(modelo_string);

                    
                }

            } catch (Exception agregarS) {

                JOptionPane.showMessageDialog(null, "No es posible agregar una nueva seccion");

            }

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == menu.setting1.panelModificar1.lb_editar) {

            if (menu.setting1.panelModificar1.cb_seccionM.getSelectedIndex() > 0) {

                menu.setting1.panelModificar1.cb_platoM.setEnabled(false);

               String newNom = JOptionPane.showInputDialog("Editar sección", menu.setting1.panelModificar1.cb_seccionM.getSelectedItem().toString());
               
                
                
               comida.setNombre(newNom);
               comida.setId(Integer.parseInt(menu.setting1.panelModificar1.tf_idMenu.getText()));

               if (ajuste.modificarMenu(comida)) {
                   JOptionPane.showMessageDialog(null, "Seccion modificada con exito");
                    menu.setting1.panelModificar1.cb_seccionM.setSelectedIndex(0);
                    menu.setting1.panelModificar1.cb_platoM.setSelectedIndex(0);
                    menu.setting1.panelModificar1.tf_idMenu.setText("");
                    menu.setting1.panelModificar1.tf_idTipo.setText("");
                    menu.setting1.panelModificar1.tf_precioM.setText("");
                    menu.setting1.panelModificar1.tf_tipoM.setText("");
                    
                    //ACTUALIZAR COMBOS
                   DefaultComboBoxModel<String> modelo_string = new DefaultComboBoxModel<String>(comida.mostrarComidas());
                   menu.setting1.panelModificar1.cb_seccionM.setModel(modelo_string);
                   menu.pedido1.cb_comida.setModel(modelo_string);
                   menu.setting1.panelAgregar1.cb_agregarS.setModel(modelo_string);
                   

                } else {

                   menu.setting1.panelModificar1.cb_seccionM.setSelectedIndex(0);

               }

                menu.setting1.panelModificar1.cb_platoM.setEnabled(true);


            }
        }

        
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
