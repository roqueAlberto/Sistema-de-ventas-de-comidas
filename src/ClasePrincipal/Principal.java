package ClasePrincipal;

import controlador.Controlador;

import javax.swing.DefaultComboBoxModel;
import modelo.*;

import vista.Menu;
import vista.Pedido;
import vista.Setting;

public class Principal {

    public static void main(String[] args) {
 
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 

     /*   java.awt.EventQueue.invokeLater(new Runnable() {
      /*      public void run() { */

               
                Comida comida = new Comida();
                TipoComida tipo = new TipoComida();
                GestionComida gc = new GestionComida();
                Cliente cliente = new Cliente();
                Compra compra = new Compra();
                GestionVentas gv = new GestionVentas();

                Ajustes ajuste = new Ajustes();
                Gasto gasto = new Gasto();
                Menu menu = new Menu();
                

                Controlador controlador = new Controlador(menu, comida, tipo, gc, cliente, compra, gasto, ajuste, gv);
                controlador.iniciar();
                menu.setLocationRelativeTo(null);
                menu.setVisible(true);
                

           // } 
    //    });

    }

}
