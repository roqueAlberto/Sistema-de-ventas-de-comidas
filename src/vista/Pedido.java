/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.Comida;

/**
 *
 * @author Roque
 */
public class Pedido extends javax.swing.JPanel implements Runnable {

    Comida comida = new Comida();
    //Modelo para el combo
    // DefaultComboBoxModel modelo_comida = new DefaultComboBoxModel(comida.mostrarComidas());

    
    int total = 0;
    
   

    public DefaultTableModel modelo = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int f, int c) {
            if (c == 6) {
                return true;
            } else {
                return false;
            }
        }
    };
    //*****************************************

    //Propiedades para la Hora
    String hora, minuto, segundo;
    Thread hilo;

    public Pedido() {
        initComponents();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        modelo.addColumn("Nombre");
        modelo.addColumn("Domicilio");
        modelo.addColumn("Hora");
        modelo.addColumn("Menú");
        modelo.addColumn("Tipo");
        modelo.addColumn("Cantidad");
        modelo.addColumn("SubTotal");

        //Agregando el modelo(con las columnas) a la tabla
        tabla.setModel(modelo);

        tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(50);

        // cb_comida.setModel(modelo_comida);
        //Manejo de Fecha
        lb_fecha.setText(obtenerFecha());

        //Manejo de Hora
        hilo = new Thread(this);
        hilo.start();

        tf_dateAlreves.setText(devolverFecha());
        tf_dateAlreves.setVisible(false);

      

    }

    public void insertarDatos() {

        String d[] = new String[7];

        Integer cantidad = (Integer) sp_cantidad.getValue();

        int entero_precio = Integer.parseInt(tf_precio.getText());
        int entero_cantidad = cantidad;
        int total = entero_precio * entero_cantidad;

        if (tf_nom.getText().equals("")) {
            d[0] = "---";

        } else {
            d[0] = tf_nom.getText();

        }

        if (tf_dom.getText().equals("")) {
            d[1] = "---";
        } else {
            d[1] = tf_dom.getText();
        }

        d[2] = lb_horario.getText();
        d[3] = cb_comida.getSelectedItem().toString();
        d[4] = cb_tipo.getSelectedItem().toString();
        d[5] = String.valueOf(cantidad);
        d[6] = String.valueOf(total);

        modelo.addRow(d);

        tabla.setModel(modelo);
        
        

    }

    public static String obtenerFecha() {
        Date fecha = new Date();
        SimpleDateFormat formato_fecha = new SimpleDateFormat("dd/MM/YYYY");

        return formato_fecha.format(fecha);
    }

    public static String devolverFecha() {
        Date fecha_actual = new Date();
        SimpleDateFormat formato_fecha_actual = new SimpleDateFormat("YYYY-MM-dd");

        return formato_fecha_actual.format(fecha_actual);

    }

    public void obtenerHora() {

        Calendar calendario = new GregorianCalendar();
        Date Hora = new Date();

        calendario.setTime(Hora);

        hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        minuto = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundo = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPedidos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tf_nom = new javax.swing.JTextField();
        tf_dom = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cb_comida = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cb_tipo = new javax.swing.JComboBox<>();
        lb_precio = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        sp_cantidad = new javax.swing.JSpinner();
        jPanel4 = new javax.swing.JPanel();
        tf_precio = new javax.swing.JTextField();
        btn_agregarP = new keeptoo.KButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        btn_entregar = new keeptoo.KButton();
        btn_cancelarPedido = new keeptoo.KButton();
        lb_canPedidos = new javax.swing.JLabel();
        lb_fecha = new javax.swing.JLabel();
        lb_horario = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tf_dateAlreves = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        PanelPedidos.setBackground(new java.awt.Color(247, 247, 247));
        PanelPedidos.setPreferredSize(new java.awt.Dimension(700, 714));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel2.setText("Realizar pedidos");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Dirección");

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Nombre ");

        tf_nom.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        tf_nom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tf_nom.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        tf_dom.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        tf_dom.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Sección");

        cb_comida.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        cb_comida.setForeground(new java.awt.Color(51, 51, 51));
        cb_comida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_comida.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        cb_comida.setName("  "); // NOI18N

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Tipo");

        cb_tipo.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        cb_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cb_tipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));

        lb_precio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_precio.setText("$");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Cantidad");

        sp_cantidad.setFont(new java.awt.Font("Yu Gothic", 1, 11)); // NOI18N
        sp_cantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 99, 1));

        jPanel4.setBackground(new java.awt.Color(79, 138, 139));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );

        tf_precio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tf_precio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tf_precio.setBorder(null);
        tf_precio.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(tf_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_dom, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(191, 191, 191))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel6)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(cb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lb_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2)
                                    .addComponent(tf_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sp_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(111, 111, 111)))
                            .addComponent(cb_comida, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_comida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tf_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lb_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel4)
                        .addGap(6, 6, 6)
                        .addComponent(tf_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(tf_dom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sp_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(27, 27, 27))
        );

        btn_agregarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anadir.png"))); // NOI18N
        btn_agregarP.setText(" Agregar");
        btn_agregarP.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_agregarP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_agregarP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_agregarP.setIconTextGap(8);
        btn_agregarP.setkBackGroundColor(new java.awt.Color(79, 138, 139));
        btn_agregarP.setkEndColor(new java.awt.Color(0, 204, 204));
        btn_agregarP.setkHoverEndColor(new java.awt.Color(79, 138, 139));
        btn_agregarP.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        btn_agregarP.setkHoverStartColor(new java.awt.Color(79, 138, 139));
        btn_agregarP.setkSelectedColor(new java.awt.Color(153, 153, 153));
        btn_agregarP.setkStartColor(new java.awt.Color(79, 138, 139));

        jScrollPane1.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tabla.setBackground(new java.awt.Color(247, 247, 247));
        tabla.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.setGridColor(new java.awt.Color(247, 247, 247));
        tabla.setRowHeight(20);
        tabla.setSelectionBackground(new java.awt.Color(0, 102, 102));
        jScrollPane1.setViewportView(tabla);

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel9.setText("Cantidad de pedidos :");

        btn_entregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/confirm.png"))); // NOI18N
        btn_entregar.setText("  Entregado");
        btn_entregar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_entregar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_entregar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_entregar.setIconTextGap(5);
        btn_entregar.setkEndColor(new java.awt.Color(0, 204, 204));
        btn_entregar.setkHoverEndColor(new java.awt.Color(79, 138, 139));
        btn_entregar.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        btn_entregar.setkHoverStartColor(new java.awt.Color(79, 138, 139));
        btn_entregar.setkSelectedColor(new java.awt.Color(153, 153, 153));
        btn_entregar.setkStartColor(new java.awt.Color(79, 138, 139));

        btn_cancelarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/error.png"))); // NOI18N
        btn_cancelarPedido.setText("  Cancelar");
        btn_cancelarPedido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelarPedido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_cancelarPedido.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cancelarPedido.setIconTextGap(7);
        btn_cancelarPedido.setkEndColor(new java.awt.Color(0, 204, 204));
        btn_cancelarPedido.setkHoverEndColor(new java.awt.Color(79, 138, 139));
        btn_cancelarPedido.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        btn_cancelarPedido.setkHoverStartColor(new java.awt.Color(79, 138, 139));
        btn_cancelarPedido.setkSelectedColor(new java.awt.Color(153, 153, 153));
        btn_cancelarPedido.setkStartColor(new java.awt.Color(79, 138, 139));

        lb_canPedidos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        lb_fecha.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lb_fecha.setForeground(new java.awt.Color(102, 102, 102));

        lb_horario.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lb_horario.setForeground(new java.awt.Color(102, 102, 102));

        jSeparator1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logopizzeria.png"))); // NOI18N

        javax.swing.GroupLayout PanelPedidosLayout = new javax.swing.GroupLayout(PanelPedidos);
        PanelPedidos.setLayout(PanelPedidosLayout);
        PanelPedidosLayout.setHorizontalGroup(
            PanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPedidosLayout.createSequentialGroup()
                .addGroup(PanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPedidosLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelPedidosLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(PanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelPedidosLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(btn_entregar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_cancelarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelPedidosLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lb_canPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelPedidosLayout.createSequentialGroup()
                        .addGroup(PanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelPedidosLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPedidosLayout.createSequentialGroup()
                                .addGroup(PanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelPedidosLayout.createSequentialGroup()
                                        .addComponent(lb_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tf_dateAlreves, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelPedidosLayout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addComponent(jLabel2)
                                        .addGap(77, 77, 77)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(PanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_horario, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_agregarP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelPedidosLayout.setVerticalGroup(
            PanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPedidosLayout.createSequentialGroup()
                .addGroup(PanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPedidosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelPedidosLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(PanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tf_dateAlreves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lb_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(PanelPedidosLayout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(lb_horario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(PanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelPedidosLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelPedidosLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(8, 8, 8)
                .addGroup(PanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelPedidosLayout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(btn_agregarP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(PanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPedidosLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelPedidosLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)
                .addGroup(PanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelPedidosLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btn_entregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(btn_cancelarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(PanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(lb_canPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelPedidos;
    public keeptoo.KButton btn_agregarP;
    public keeptoo.KButton btn_cancelarPedido;
    public keeptoo.KButton btn_entregar;
    public javax.swing.JComboBox<String> cb_comida;
    public javax.swing.JComboBox<String> cb_tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JLabel lb_canPedidos;
    public javax.swing.JLabel lb_fecha;
    public javax.swing.JLabel lb_horario;
    public javax.swing.JLabel lb_precio;
    public javax.swing.JSpinner sp_cantidad;
    public javax.swing.JTable tabla;
    public javax.swing.JTextField tf_dateAlreves;
    public javax.swing.JTextField tf_dom;
    public javax.swing.JTextField tf_nom;
    public javax.swing.JTextField tf_precio;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        Thread current = Thread.currentThread();

        while (current == hilo) {

            obtenerHora();
            lb_horario.setText(hora + ":" + minuto);
        }
    }
}
