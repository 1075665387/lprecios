
package lprecios;
import java.awt.event.*;

public class controlmenu1 implements ActionListener{
    modelomenu1 m;
    vistamenu1 ve;
    
     controlmenu1(modelomenu1 mod,vistamenu1 vise){
        
        this.m=mod;
        this.ve=vise;
        this.ve.b1.addActionListener(this);
        this.ve.b2.addActionListener(this);
       
        this.ve.b4.addActionListener(this);
    }
         @Override
    public void actionPerformed(ActionEvent ae) {
        
     if(ae.getSource()==this.ve.b1)
        {
          this.m.inventario();
          this.ve.dispose();

        }
     if(ae.getSource()==this.ve.b2)
        {
          this.m.deudas();
          this.ve.dispose();

        }
     if(ae.getSource()==this.ve.b4)
        {
          System.exit(0);

        }
    }
    
}
