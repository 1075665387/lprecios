
package lprecios;

import java.util.Date;


public class modelomenu1 {
    
    public void inventario(){
        vistainventario v=new vistainventario();
        modeloinventario m= new modeloinventario();
        controlinventario c=new controlinventario(m,v);
      
    }
    public void deudas(){
        vistadeudas v=new vistadeudas();
        modelodeudas m= new modelodeudas();
        controldeudas c=new controldeudas(m,v);
        
    }
    
   
}
