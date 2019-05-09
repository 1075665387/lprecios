
package lprecios;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;
import rsbuttom.RSButtonMetro;

public class vistamenu1 extends JFrame{
    JLabel l1, l2, l3, l4;
    //JButton b1, b2, b4;
    ImageIcon ImageIcon;
    Color c1;
    Image Image;
    //material de diseño
    RSButtonMetro b1,b2, b4;
    

    vistamenu1() {
        setSize(240, 280);
        this.setResizable(false);
        //no mostrar botones  de maximizar y minimizar
        this.setUndecorated(true);
        
        //cambiar icono de java crear nuevo paquete y poner la imagen hay
        ImageIcon  = new ImageIcon(getClass().getResource("/imagenes/logo.png"));
        Image = ImageIcon.getImage();
        this.setIconImage(Image);
        
        setTitle("menu 1");
        setLayout(null);
        
        c1= new Color(0,139,139);
      
        l1 = new JLabel("Menú Principal");
        l1.setFont(new Font("Tahoma", Font.BOLD, 20));
        

        b1 = new RSButtonMetro();
        b1.setText("inventario");
        b1.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 25));
        b1.setColorPressed(c1);
        b1.setToolTipText("En esta opción podrá acceder al menú inventario");
        
        b2 = new RSButtonMetro();
        b2.setText("Deudas");
        b2.setColorPressed(c1);
        b2.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 25));
        
        b2.setToolTipText("En esta opción podrá acceder al menú deudas");
        
        b4 = new RSButtonMetro();
        b4.setColorPressed(c1);
        b4.setText("salir");
        
        b4.setToolTipText("Salir");
        
        
        
        
        l1.setBounds(40,20,400,40);
       
        
        b1.setBounds(20,70,200,70);
        b2.setBounds(20,150,200,70);
        
        b4.setBounds(150,230,70,40);
        this.add(b1);
        this.add(b2);
        
        this.add(b4);
        this.add(l1);
     
        
        this.setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(3);
    }
    
}
