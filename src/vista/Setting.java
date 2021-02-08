/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;


import java.awt.Color;

import modelo.TipoComida;

/**
 *
 * @author Roque
 */
public class Setting extends javax.swing.JPanel {

  
    
    
    /**
     * Creates new form Setting
     */
    public Setting() {
        initComponents();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Setting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Setting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Setting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Setting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
 
        panelModificar1.setVisible(true);
        panelAgregar1.setVisible(false);
      
        
        panelModificar1.validate();
        jLabel1.setForeground(new Color(79, 138, 139));
        
        jLabel2.setForeground(new Color(0, 0, 0));
        
       
        
        btn_guardar.setVisible(true);
        btn_agregar.setVisible(false);
        
     
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ButtonMod = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ButtonAgre = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        PanelMod = new javax.swing.JPanel();
        panelModificar1 = new vista.PanelModificar();
        panelAgregar1 = new vista.PanelAgregar();
        btn_guardar = new keeptoo.KButton();
        btn_agregar = new keeptoo.KButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        ButtonMod.setBackground(new java.awt.Color(255, 255, 255));
        ButtonMod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonMod.setPreferredSize(new java.awt.Dimension(90, 35));
        ButtonMod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonModMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificar.png"))); // NOI18N
        jLabel1.setText("Modificar");
        jLabel1.setPreferredSize(new java.awt.Dimension(66, 17));

        javax.swing.GroupLayout ButtonModLayout = new javax.swing.GroupLayout(ButtonMod);
        ButtonMod.setLayout(ButtonModLayout);
        ButtonModLayout.setHorizontalGroup(
            ButtonModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );
        ButtonModLayout.setVerticalGroup(
            ButtonModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        ButtonAgre.setBackground(new java.awt.Color(255, 255, 255));
        ButtonAgre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonAgre.setPreferredSize(new java.awt.Dimension(90, 35));
        ButtonAgre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonAgreMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add.png"))); // NOI18N
        jLabel2.setText("Agregar");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel2.setPreferredSize(new java.awt.Dimension(90, 35));

        javax.swing.GroupLayout ButtonAgreLayout = new javax.swing.GroupLayout(ButtonAgre);
        ButtonAgre.setLayout(ButtonAgreLayout);
        ButtonAgreLayout.setHorizontalGroup(
            ButtonAgreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ButtonAgreLayout.setVerticalGroup(
            ButtonAgreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ButtonAgreLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        PanelMod.setBackground(new java.awt.Color(255, 255, 255));
        PanelMod.setPreferredSize(new java.awt.Dimension(530, 158));
        PanelMod.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        PanelMod.add(panelModificar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        PanelMod.add(panelAgregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btn_guardar.setBackground(new java.awt.Color(255, 255, 255));
        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/saveblack.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.setToolTipText("");
        btn_guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_guardar.setIconTextGap(25);
        btn_guardar.setkBackGroundColor(new java.awt.Color(79, 138, 139));
        btn_guardar.setkBorderRadius(18);
        btn_guardar.setkEndColor(new java.awt.Color(0, 204, 204));
        btn_guardar.setkHoverEndColor(new java.awt.Color(255, 255, 255));
        btn_guardar.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btn_guardar.setkHoverStartColor(new java.awt.Color(79, 138, 139));
        btn_guardar.setkPressedColor(new java.awt.Color(79, 138, 139));
        btn_guardar.setkSelectedColor(new java.awt.Color(79, 138, 139));
        btn_guardar.setkStartColor(new java.awt.Color(79, 138, 139));

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/addblack.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_agregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_agregar.setIconTextGap(25);
        btn_agregar.setkBackGroundColor(new java.awt.Color(79, 138, 139));
        btn_agregar.setkBorderRadius(18);
        btn_agregar.setkEndColor(new java.awt.Color(0, 204, 204));
        btn_agregar.setkHoverEndColor(new java.awt.Color(79, 138, 139));
        btn_agregar.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        btn_agregar.setkHoverStartColor(new java.awt.Color(79, 138, 139));
        btn_agregar.setkPressedColor(new java.awt.Color(79, 138, 139));
        btn_agregar.setkSelectedColor(new java.awt.Color(79, 138, 139));
        btn_agregar.setkStartColor(new java.awt.Color(79, 138, 139));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(79, 138, 139));
        jLabel4.setText("Ajustes");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/setting.png"))); // NOI18N

        jSeparator1.setBackground(new java.awt.Color(51, 51, 51));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setToolTipText("");

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setPreferredSize(new java.awt.Dimension(235, 4));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(538, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(ButtonMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonAgre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(78, 78, 78))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(ButtonMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonAgre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PanelMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(122, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonModMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonModMouseClicked
        
        panelModificar1.setVisible(true);
        panelAgregar1.setVisible(false);
        
        
        panelModificar1.validate();
        
       
        
        jLabel1.setForeground(new Color(79, 138, 139));
      
        jLabel2.setForeground(new Color(0, 0, 0));
        
        btn_guardar.setVisible(true);
        btn_agregar.setVisible(false);
       
        
    }//GEN-LAST:event_ButtonModMouseClicked

    private void ButtonAgreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonAgreMouseClicked
        
        
        panelAgregar1.setVisible(true);
        panelModificar1.setVisible(false);
        
        
        panelAgregar1.validate();
        
       
       
        
        jLabel2.setForeground(new Color(79, 138, 139));
        
        jLabel1.setForeground(new Color(0, 0, 0));
        
        
        btn_agregar.setVisible(true);
        btn_guardar.setVisible(false);
        
        
        
        
        
    }//GEN-LAST:event_ButtonAgreMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ButtonAgre;
    private javax.swing.JPanel ButtonMod;
    public javax.swing.JPanel PanelMod;
    public keeptoo.KButton btn_agregar;
    public keeptoo.KButton btn_guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    public vista.PanelAgregar panelAgregar1;
    public vista.PanelModificar panelModificar1;
    // End of variables declaration//GEN-END:variables
}
