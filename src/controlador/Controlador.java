package controlador;

import java.awt.HeadlessException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import modelo.Cliente;

import modelo.TipoComida;

import vista.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import modelo.ClienteDAO;

import modelo.ClienteDAOImpl;
import modelo.Gasto;
import modelo.GastoDAO;
import modelo.GastoDAOImpl;

import modelo.Seccion;
import modelo.SeccionDAO;
import modelo.SeccionDAOImpl;
import modelo.TipoDAO;
import modelo.TipoDAOImpl;
import modelo.VentaDAOImpl;
import modelo.Venta;
import modelo.VentaDAO;

/**
 *
 * @author Roque
 */
public class Controlador implements ItemListener, ActionListener, MouseListener {

    TipoComida tipo;
    Menu menu;
    Cliente cliente;
    Venta venta_m;

    Gasto gasto;
    Seccion seccion;

    //Instanciacion de objetos DAO
    TipoDAO tipo_DAO = new TipoDAOImpl();
    SeccionDAO seccion_DAO = new SeccionDAOImpl();
    ClienteDAO cliente_DAO = new ClienteDAOImpl();
    VentaDAO venta_DAO = new VentaDAOImpl();
    GastoDAO gasto_DAO = new GastoDAOImpl();

    //====FECHA ACTUAL===
    java.util.Date date = new java.util.Date();
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-YYYY");
    String fecha_a = formato.format(date);

    //===================
    DefaultComboBoxModel<String> modelo_seccion;

    public Controlador(Menu menu, TipoComida tipo, Cliente cliente, Seccion seccion, Venta venta_m) {

        this.tipo = tipo;
        this.menu = menu;
        this.cliente = cliente;
        this.seccion = seccion;
        this.venta_m = venta_m;

    }

    public void iniciar() {

        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
        menu.pedido1.setVisible(true);

        modelo_seccion = new DefaultComboBoxModel<>(seccion_DAO.listar());

        menu.pedido1.cb_comida.setModel(modelo_seccion);

        //  INICIAR EVENTOS DE COMPONENTES
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

    // *** EVENTO ITEM ** //
    @Override
    public void itemStateChanged(ItemEvent e) {

        if (e.getSource() == menu.pedido1.cb_comida) {  // Al seleccionar una seccion...

            if (e.getStateChange() == ItemEvent.SELECTED) {

                String sec_seleccionado = menu.pedido1.cb_comida.getSelectedItem().toString();

                //listar los tipos de plato que pertenecen a la seccion.
                DefaultComboBoxModel<String> modelo_tipo = new DefaultComboBoxModel<>(tipo_DAO.listar(sec_seleccionado));
                menu.pedido1.cb_tipo.setModel(modelo_tipo);

                menu.pedido1.tf_precio.setText("");

            }

        }

        // ********************
        if (e.getSource() == menu.pedido1.cb_tipo) {   // Al seleccionar un tipo de plato...

            if (e.getStateChange() == ItemEvent.SELECTED) {

                String sec_selec = menu.pedido1.cb_comida.getSelectedItem().toString();
                String tipo_selec = menu.pedido1.cb_tipo.getSelectedItem().toString();

                menu.pedido1.tf_precio.setText(String.valueOf(tipo_DAO.getPrecios(sec_selec, tipo_selec))); //se indica el precio correspondiente.

                // En caso de que el tipo de plato sea 'Seleccionar' , su precio sera vacio.
                if (menu.pedido1.cb_tipo.getSelectedIndex() == 0) {

                    menu.pedido1.tf_precio.setText("");

                }

            }
        }

        // ***************************
        //.............................SETTING.........................................
        if (e.getSource() == menu.setting1.panelModificar1.cb_seccionM) {

            if (e.getStateChange() == ItemEvent.SELECTED) {

                //Limpiar cajas de textos
                menu.setting1.panelModificar1.tf_tipoM.setText("");
                menu.setting1.panelModificar1.tf_precioM.setText("");

                String sec = menu.setting1.panelModificar1.cb_seccionM.getSelectedItem().toString();

                DefaultComboBoxModel<String> modelo_tipo = new DefaultComboBoxModel<>(tipo_DAO.listar(sec));
                menu.setting1.panelModificar1.cb_platoM.setModel(modelo_tipo);

                menu.setting1.panelModificar1.tf_idMenu.setText(String.valueOf(seccion_DAO.obtenerID(sec)));

            }

        }

        if (e.getSource() == menu.setting1.panelModificar1.cb_platoM) {

            if (e.getStateChange() == ItemEvent.SELECTED) {

                String nom_menuM = menu.setting1.panelModificar1.cb_seccionM.getSelectedItem().toString();
                String nom_tipoM = menu.setting1.panelModificar1.cb_platoM.getSelectedItem().toString();

                menu.setting1.panelModificar1.tf_precioM.setText(String.valueOf(tipo_DAO.getPrecios(nom_menuM, nom_tipoM)));
                menu.setting1.panelModificar1.tf_tipoM.setText(nom_tipoM);
                menu.setting1.panelModificar1.tf_idTipo.setText(String.valueOf(tipo_DAO.getID(nom_menuM, nom_tipoM)));

            }
        }

        if (e.getSource() == menu.setting1.panelAgregar1.cb_agregarS) {

            String nom_seccion = menu.setting1.panelAgregar1.cb_agregarS.getSelectedItem().toString();

            //Comida com = (Comida) menu.setting1.panelAgregar1.cb_agregarS.getSelectedItem();
            menu.setting1.panelAgregar1.lb_idMenu.setText(String.valueOf(seccion_DAO.obtenerID(nom_seccion)));

        }

    }

    // **** Evento al pulsar un Button ***** //
    @Override
    public void actionPerformed(ActionEvent e) {

        // Boton Agregar
        if (e.getSource() == menu.pedido1.btn_agregarP) {

            if (menu.pedido1.cb_comida.getSelectedIndex() > 0 && menu.pedido1.cb_tipo.getSelectedIndex() > 0) {

                int opcion = JOptionPane.showConfirmDialog(null, "¿Solicito algo más la misma persona?");

                //Si la opcion elegida es 'SI'
                if (opcion == 0) {

                    menu.pedido1.insertarDatos();
                    menu.pedido1.sp_cantidad.setValue(1);
                    menu.pedido1.cb_comida.setSelectedIndex(0);
                    menu.pedido1.cb_tipo.setSelectedIndex(0);

                    //Si la opcion elegida es 'NO'
                } else if (opcion == 1) {
                    menu.pedido1.insertarDatos();
                    menu.pedido1.tf_nom.setText("");
                    menu.pedido1.tf_dom.setText("");
                    menu.pedido1.cb_comida.setSelectedIndex(0);
                    menu.pedido1.cb_tipo.setSelectedIndex(0);

                    menu.pedido1.sp_cantidad.setValue(1);

                }

            } else {

                JOptionPane.showMessageDialog(null, "Complete los campos necesarios");
            }

            getCantidadPedido();

        } //  ====  FIN Boton Agregar  ====

        // BOTON CANCELAR
        if (e.getSource() == menu.pedido1.btn_cancelarPedido) {

            int fila = menu.pedido1.tabla.getSelectedRow();

            if (fila >= 0) {

                menu.pedido1.modelo_tabla.removeRow(fila);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione pedido a eliminar");
            }

            getCantidadPedido();

        }

        //BOTON ENTREGAR
        if (e.getSource() == menu.pedido1.btn_entregar) {

            try {

                //Extracion de datos de la tabla
                int reg_seleccionado = menu.pedido1.tabla.getSelectedRow();

                //--------------------------------
                //Asignacion a variables cliente.
                cliente.setNombre(menu.pedido1.tabla.getValueAt(reg_seleccionado, 0).toString());
                cliente.setDomicilio(menu.pedido1.tabla.getValueAt(reg_seleccionado, 1).toString());

                //Asignacion a variables Tipo de comida.
                String nom_tipo = menu.pedido1.tabla.getValueAt(reg_seleccionado, 4).toString();
                int id_tipo = tipo_DAO.getID(nom_tipo);

                //Capturar total y cantidad
                venta_m.setCantidad(Integer.parseInt(menu.pedido1.tabla.getValueAt(reg_seleccionado, 5).toString()));
                venta_m.setTotal(Integer.parseInt(menu.pedido1.tabla.getValueAt(reg_seleccionado, 6).toString()));

                //Capturar fecha actual
                venta_m.setFecha(fecha_a);
                venta_m.setHora(menu.pedido1.tabla.getValueAt(reg_seleccionado, 2).toString());

                if (cliente_DAO.insertar(cliente, reg_seleccionado)) {

                    venta_m.setRela_cliente(cliente_DAO.obtener_ultimoCliente());

                    venta_DAO.insertar(venta_m);

                    venta_m.setId_venta(venta_DAO.obtenerUltimaventa());

                    venta_DAO.insertarDetalleventa(venta_m, id_tipo);

                    JOptionPane.showMessageDialog(null, "¡Pedido entregado!");

                    menu.pedido1.modelo_tabla.removeRow(reg_seleccionado);

                } else {
                    JOptionPane.showMessageDialog(null, "¡Error en la entrega!");
                }

            } catch (HeadlessException | NumberFormatException eAgreP) {

                JOptionPane.showMessageDialog(null, "¡Accion denegada!");

            }

            establecerVenta();

            getCantidadPedido();
        }// FIN PEDIDO

        //.............COMPRAS......................
        if (e.getSource() == menu.compra1.btn_g) {

            guardarCompra();

        }

        //.............AJUSTE......................
        if (e.getSource() == menu.setting1.btn_guardar) {

            try {

                tipo.setDescripcion(menu.setting1.panelModificar1.tf_tipoM.getText());
                tipo.setPrecio(Integer.parseInt(menu.setting1.panelModificar1.tf_precioM.getText()));
                tipo.setId(Integer.parseInt(menu.setting1.panelModificar1.tf_idTipo.getText()));

                if (tipo_DAO.modificar(tipo)) {
                    JOptionPane.showMessageDialog(null, "¡Modificacion Exitosa!");
                    menu.setting1.panelModificar1.cb_platoM.setSelectedIndex(0);
                    menu.setting1.panelModificar1.cb_seccionM.setSelectedIndex(0);
                    menu.setting1.panelModificar1.tf_idMenu.setText("");
                    menu.setting1.panelModificar1.tf_idTipo.setText("");
                    menu.setting1.panelModificar1.tf_tipoM.setText("");
                    menu.setting1.panelModificar1.tf_precioM.setText("");

                }

            } catch (HeadlessException | NumberFormatException buModificar) {

                JOptionPane.showMessageDialog(null, "¡Por favor complete los datos correctamente!");
            }

        }

        if (e.getSource() == menu.setting1.btn_agregar) {

            try {

                tipo.setRela_seccion(Integer.parseInt(menu.setting1.panelAgregar1.lb_idMenu.getText()));
                tipo.setDescripcion(menu.setting1.panelAgregar1.tf_agregar.getText());
                tipo.setPrecio(Integer.parseInt(menu.setting1.panelAgregar1.tf_precioA.getText()));

                if (tipo_DAO.insertar(tipo)) {
                    JOptionPane.showMessageDialog(null, "¡Agregado con exito!");
                    menu.setting1.panelAgregar1.cb_agregarS.setSelectedIndex(0);
                    menu.setting1.panelAgregar1.lb_idMenu.setText("");
                    menu.setting1.panelAgregar1.tf_agregar.setText("");
                    menu.setting1.panelAgregar1.tf_precioA.setText("");

                }

            } catch (HeadlessException | NumberFormatException bug) {

                JOptionPane.showMessageDialog(null, "¡Por favor complete los datos correctamente!");
            }

        }

        // AGREGAR SECCION 
        if (e.getSource() == menu.setting1.panelAgregar1.btn_addS) {

            try {

                seccion.setDescripcion(JOptionPane.showInputDialog(null, "Ingrese nombre para la nueva seccion"));

                if (seccion_DAO.agregar(seccion)) {

                    JOptionPane.showMessageDialog(null, "Sección agregada");
                    menu.setting1.panelAgregar1.cb_agregarS.setSelectedIndex(0);
                    menu.setting1.panelAgregar1.tf_agregar.setText("");
                    menu.setting1.panelAgregar1.tf_precioA.setText("");
                    menu.setting1.panelAgregar1.lb_idMenu.setText("");

                    //Actualizar combo 
                    modelo_seccion = new DefaultComboBoxModel<>(seccion_DAO.listar());

                    menu.pedido1.cb_comida.setModel(modelo_seccion);
                    menu.setting1.panelModificar1.cb_seccionM.setModel(modelo_seccion);
                    menu.setting1.panelAgregar1.cb_agregarS.setModel(modelo_seccion);

                }

            } catch (HeadlessException agregarS) {

                JOptionPane.showMessageDialog(null, "No es posible agregar una nueva seccion");

            }

        }

    }  // ******* FIN EVENTO ACTION *********//

    // *** EVENTO AL DAR CLICK ***//
    @Override
    public void mouseClicked(MouseEvent e) {

        // EDITAR SECCION
        if (e.getSource() == menu.setting1.panelModificar1.lb_editar) {

            if (menu.setting1.panelModificar1.cb_seccionM.getSelectedIndex() > 0) {

                menu.setting1.panelModificar1.cb_platoM.setEnabled(false);

                String newNom = JOptionPane.showInputDialog("Editar sección", menu.setting1.panelModificar1.cb_seccionM.getSelectedItem().toString());

                seccion.setDescripcion(newNom);
                seccion.setId(Integer.parseInt(menu.setting1.panelModificar1.tf_idMenu.getText()));

                if (seccion_DAO.modificar(seccion)) {
                    JOptionPane.showMessageDialog(null, "Seccion modificada con exito");
                    menu.setting1.panelModificar1.cb_seccionM.setSelectedIndex(0);
                    menu.setting1.panelModificar1.cb_platoM.setSelectedIndex(0);
                    menu.setting1.panelModificar1.tf_idMenu.setText("");
                    menu.setting1.panelModificar1.tf_idTipo.setText("");
                    menu.setting1.panelModificar1.tf_precioM.setText("");
                    menu.setting1.panelModificar1.tf_tipoM.setText("");

                    //Actualizacion comboBox
                    modelo_seccion = new DefaultComboBoxModel<>(seccion_DAO.listar());
                    menu.setting1.panelModificar1.cb_seccionM.setModel(modelo_seccion);
                    menu.pedido1.cb_comida.setModel(modelo_seccion);
                    menu.setting1.panelAgregar1.cb_agregarS.setModel(modelo_seccion);

                } else {

                    menu.setting1.panelModificar1.cb_seccionM.setSelectedIndex(0);

                }

                menu.setting1.panelModificar1.cb_platoM.setEnabled(true);

            }
        }

    } // ******* FIN EVENTO CLICK *****//

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

    //METODOS ABSTRACTOS
    public void getCantidadPedido() {

        //Cantidad de productos en pedido
        int cant_pedidos = 0;

        for (int fila = 0; fila < menu.pedido1.tabla.getRowCount(); fila++) {
            cant_pedidos += Integer.parseInt(menu.pedido1.tabla.getValueAt(fila, 5).toString());

        }

        //Mostrar la cantidad de pedidos
        menu.pedido1.lb_canPedidos.setText(String.valueOf(cant_pedidos));

    }

    public void establecerVenta() {

        menu.venta1.tablaV.setModel(venta_DAO.listar_fechaActual());
        menu.venta1.lb_caja.setText(String.valueOf("$ " + menu.venta1.obtenerTotal()));
        menu.venta1.lb_caja.setVisible(true);

    }

    public void guardarCompra() {

        ArrayList<Gasto> lista_gasto = new ArrayList<>();
        Gasto g;

        for (int i = 0; i < menu.compra1.tabla_compra.getRowCount(); i++) {

            g = new Gasto();

            g.setDesc_gasto(menu.compra1.tabla_compra.getValueAt(i, 0).toString());
            g.setMonto(Integer.parseInt(menu.compra1.tabla_compra.getValueAt(i, 1).toString()));
            g.setFecha(fecha_a);

            lista_gasto.add(g);

        }

        menu.compra1.modelo_compra.setRowCount(0);
        menu.compra1.lb_total.setText("---");

        if (gasto_DAO.insertar(lista_gasto)) {

            JOptionPane.showMessageDialog(null, "Datos guardados");

        }

    }

}
