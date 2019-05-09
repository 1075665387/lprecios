
package lprecios;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import principal.MaterialTextField;
import rsbuttom.RSButtonMetro;

public class vistadeudas extends JFrame{
    JTextField tpnombre,tpdocumento,tpfecha,tpnombre_libro,tenombre_p1,tenombre_p2,
            tenombre_l1,tenombre_l2,tefecha,tedocu,tecantidad,tb_total;

    JPanel panel1, panel2, pentrega, pprestamo, pagregar, pbuscar, pactualizar,
            pborrar;

    JButton bentrega, bbexcel,
            bprestar,bp_fecha,bdevolver;
    ImageIcon i1,ifondopan,ImageIcon,ibor,iact,ibus,iingresar,iretorno,iacerca,isalir;
    JLabel Lnro,Lnroborrar, fondopanel, linv, lpres, lagre, lanombre,lavalorven, 
            lavalorcom, lac_nombre,lfondop,lmenu,la_fecha,
            lbor,lact,lbus,lingresar,lretorno,lacerca,lsalir,lac_valor_c,lac_valor_v,lb_total;

    JScrollPane scrollbus, scrollact,scrollbor,scrollprestar1,scrollprestar2,scrollentrega;
    JTable tablabuscar, tablaact,tablabor,tablaprestar1,tablaprestar2,tablaentrega;
    JSpinner scantidad;
    Image Image;
    
    RSButtonMetro bprestamo, brenovar, bagregar, beliminar, bmodificar, bbuscar,bacercade,bsalir
            , bbmostrar, bbpdf,baagregar, bb_borrar,bac_actu;
    
    MaterialTextField  tbnombre,tanombre,tavalorcom,tavalorven,ta_fecha,
            tb_nombre, tb_precioc,
            tb_nombre2, tb_preciov,tac_nombre, tac2_nombre, tac_valor_c, tac_valor_v;

    vistadeudas() {
        setTitle("Deudas");
        setLayout(null);//desactivamos el diseño de fabbrica
        setSize(1000, 700);
        this.setLocationRelativeTo(null);//centrado
        this.setResizable(false);
        //cambiar icono de java crear nuevo paquete y poner la imagen hay
        ImageIcon  = new ImageIcon(getClass().getResource("/imagenes/logo.png"));
        Image = ImageIcon.getImage();
        this.setIconImage(Image);

        Lnro = new JLabel();
        Lnroborrar = new JLabel();

        panelescudo();
        panelmenu();
        pbuscar();

        pagregar();
        pagregar.setVisible(false);
       
        pactualizar();
        pactualizar.setVisible(false);
        pborrar();
        pborrar.setVisible(false);

        //adicionamos 
        
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    public void panelescudo() {
        panel1 = new JPanel();
        panel1.setBounds(0, 0, 160, 160);

        i1 = new ImageIcon("logo.png");
        fondopanel = new JLabel(i1);
        fondopanel.setBounds(0, 0, 160, 160);

        this.getContentPane().add(panel1);//agregamos panel al jframe
        panel1.add(fondopanel);

    }

    public void panelmenu() {
        panel2 = new JPanel();
        panel2.setLayout(null);
        Color c1 = new Color(214, 234, 248);
        
        panel2.setBounds(0, 160, 160, 680);
        
        lmenu= new JLabel("seleccione una opción");
        
        ibor=new ImageIcon("eliminar.png");
        iact=new ImageIcon("actualizar.png");
        ibus=new ImageIcon("buscar.png");
        iingresar=new ImageIcon("agregar.png");
        isalir=new ImageIcon("salir.png");
        iretorno=new ImageIcon("manterior.png");
        iacerca=new ImageIcon("acerca.png");
        
        lbor=new JLabel(ibor);
        lact=new JLabel(iact);
        lbus=new JLabel(ibus);
        lingresar=new JLabel(iingresar);
        lsalir=new JLabel(isalir);
        lretorno=new JLabel(iretorno);
        lacerca=new JLabel(iacerca);
  
        bprestamo = new RSButtonMetro();
        bprestamo.setText("Menú Anterior ");
        //bentrega = new JButton("Entregar ");
        
        //brenovar = new JButton("Renovar Prestamo");
        bagregar = new RSButtonMetro();
        bagregar.setText("Agregar ");
        beliminar = new RSButtonMetro();
        beliminar.setText("Eliminar ");
        bmodificar = new RSButtonMetro();
        bmodificar.setText("Actualizar ");
        bbuscar = new RSButtonMetro();
        bbuscar.setText("Buscar ");
        bacercade = new RSButtonMetro();
        bacercade.setText("Acerca de");
        bsalir = new RSButtonMetro();
        bsalir.setText("Salir");
       
        lmenu.setBounds(10, 10, 140, 30);
        
        lbus.setBounds(0, 50, 40, 40);
        bbuscar.setBounds(40, 50, 120, 40);
        
        lingresar.setBounds(0, 100, 40, 40);
        bagregar.setBounds(40, 100, 120, 40);

        //brenovar.setBounds(0, 130, 140, 30);
        
        lbor.setBounds(0, 150, 40, 40);
        beliminar.setBounds(40, 150, 120, 40);
        
        lact.setBounds(0, 200, 40, 40);
        bmodificar.setBounds(40, 200, 120, 40);
        
        lretorno.setBounds(0, 250, 40, 40);
        bprestamo.setBounds(40, 250, 120, 40);
       // bentrega.setBounds(40, 300, 120, 40);
        
        lacerca.setBounds(0, 300, 40, 40);
        bacercade.setBounds(40, 300, 120, 40);
        
        lsalir.setBounds(0, 350, 40, 40);
        bsalir.setBounds(40, 350, 120, 40);

        this.getContentPane().add(panel2);
        //panel2.add(bentrega);
        panel2.add(bprestamo);
        panel2.add(lretorno);
        
        panel2.add(bagregar);
        
        panel2.add(lingresar);
        panel2.add(beliminar);
        
        panel2.add(lbor);
        panel2.add(bmodificar);
        
        panel2.add(lact);
        panel2.add(bbuscar);
        
        panel2.add(lbus);
        panel2.add(lmenu);
        
        panel2.add(bacercade);
        panel2.add(lacerca);
        
        panel2.add(bsalir);
        panel2.add(lsalir);
    }

  

    public void pagregar() {
        pagregar = new JPanel();
        pagregar.setLayout(null);

        Color c = new Color(0,139,139);
        

        lagre = new JLabel("Agregar Deuda");
        
        
        
        ifondopan = new ImageIcon("fondopanel.png");
        lfondop = new JLabel(ifondopan);
        lfondop.setBounds(0, 0, 880, 700);

        tanombre = new MaterialTextField();
        tanombre.setBackground(c);
        tanombre.setForeground(Color.WHITE);
        tanombre.setCaretColor(Color.WHITE);
        tanombre.setLabel("Nombre");
        tanombre.setText("");
        
        tavalorcom = new MaterialTextField();
        tavalorcom.setBackground(c);
        tavalorcom.setForeground(Color.WHITE);
        tavalorcom.setCaretColor(Color.WHITE);
        tavalorcom.setLabel("Concepto");
        tavalorcom.setText("");
        
        tavalorven = new MaterialTextField();
        tavalorven.setBackground(c);
        tavalorven.setForeground(Color.WHITE);
        tavalorven.setCaretColor(Color.WHITE);
        tavalorven.setLabel("Valor");
        tavalorven.setText("");
        
        ta_fecha = new MaterialTextField();
        ta_fecha.setBackground(c);
        ta_fecha.setForeground(Color.WHITE);
        ta_fecha.setCaretColor(Color.WHITE);
        ta_fecha.setLabel("Fecha");
        ta_fecha.setText("");

        baagregar = new RSButtonMetro();
        baagregar.setText("Guardar");

        pagregar.setBounds(120, 0, 880, 700);
        
        lagre.setBounds(300, 15, 300, 40);
        lagre.setFont(new Font("Arial", Font.PLAIN, 30));
        lagre.setForeground(Color.BLUE);

        
        tanombre.setBounds(310, 110, 300, 46);

        
        tavalorcom.setBounds(310, 166, 300, 46);

        
        tavalorven.setBounds(310, 222, 300, 46);

        
        ta_fecha.setBounds(310, 278, 300, 46);

        baagregar.setBounds(310, 334, 100, 40);
        baagregar.setBackground(c);
        
        

        this.getContentPane().add(pagregar);
        pagregar.add(lagre);
        
        pagregar.add(tanombre);
        
        pagregar.add(tavalorcom);
        
        pagregar.add(tavalorven);
        
        pagregar.add(ta_fecha);
        pagregar.add(baagregar);
        pagregar.add(lfondop);

    }

    public void pbuscar() {
        pbuscar = new JPanel();
        pbuscar.setLayout(null);

        Color c = new Color(0,139,139);
        

        lagre = new JLabel("Buscar Deuda");
        lagre.setForeground(Color.BLUE);
        lagre.setFont(new Font("Arial", Font.PLAIN, 30));
    
        tbnombre = new MaterialTextField();
        tbnombre.setBackground(c);
        tbnombre.setForeground(Color.WHITE);
        tbnombre.setCaretColor(Color.WHITE);
        tbnombre.setLabel("Escriba el Nombre");
        tbnombre.setText("");
        
        lb_total = new JLabel("Total:");
        lb_total .setBackground(c);
        lb_total .setForeground(Color.RED);
        lb_total .setFont(new Font("Arial", Font.PLAIN, 35));
        
        tb_total = new JTextField("      0");
        tb_total .setBackground(Color.WHITE);
        tb_total.setEditable(false);
        tb_total .setForeground(Color.RED);
        tb_total .setFont(new Font("Arial", Font.PLAIN, 30));

        bbmostrar = new RSButtonMetro();
        bbmostrar.setText("Mostrar Todo");


        tablabuscar = new JTable();
        
        scrollbus = new JScrollPane(tablabuscar);

        bbpdf = new RSButtonMetro();
        bbpdf.setText("Exportar a pdf");
        bbexcel = new JButton("Exportar a excel");
        bbexcel.setBackground(c);

        pbuscar.setBounds(120, 0, 880, 700);
        lagre.setBounds(300, 10, 300, 40);
        lagre.setFont(new Font("Arial", Font.PLAIN, 30));

        
        tbnombre.setBounds(310, 110, 250, 46);

        bbmostrar.setBounds(310, 180, 150, 40);

        scrollbus.setBounds(50, 230, 800, 230);
        
        lb_total.setBounds(640, 475, 100, 50);
        tb_total.setBounds(730, 475, 120, 50);
        
        bbpdf.setBounds(310, 510, 150, 40);
        bbexcel.setBounds(440, 550, 150, 40);
        
        ifondopan = new ImageIcon("fondopanel.png");
        lfondop = new JLabel(ifondopan);
        lfondop.setBounds(0, 0, 880, 700);

        this.getContentPane().add(pbuscar);
        pbuscar.add(lagre);
      
        pbuscar.add(tbnombre);
        pbuscar.add(tb_total);
        pbuscar.add(lb_total);
        pbuscar.add(bbmostrar);
        pbuscar.add(scrollbus);
        pbuscar.add(bbpdf);
        //pbuscar.add(bbexcel);
       
        pbuscar.add(lfondop);

    }

    public void pactualizar() {
        pactualizar = new JPanel();
        pactualizar.setLayout(null);

        Color c = new Color(0,139,139);
       

        lagre = new JLabel("Actualizar Deuda");
        lagre.setForeground(Color.BLUE);
        lagre.setFont(new Font("Arial", Font.PLAIN, 30));

        
        tac_nombre = new MaterialTextField();
        tac_nombre.setBackground(c);
        tac_nombre.setForeground(Color.WHITE);
        tac_nombre.setCaretColor(Color.WHITE);
        tac_nombre.setLabel("Escriba el Nombre");
        tac_nombre.setText("");
        
        tac2_nombre = new MaterialTextField();
        tac2_nombre.setBackground(c);
        tac2_nombre.setForeground(Color.WHITE);
        tac2_nombre.setCaretColor(Color.WHITE);
        tac2_nombre.setLabel("Nombre");
        tac2_nombre.setText("");
        
        tac_valor_c = new MaterialTextField();
        tac_valor_c.setBackground(c);
        tac_valor_c.setForeground(Color.WHITE);
        tac_valor_c.setCaretColor(Color.WHITE);
        tac_valor_c.setLabel("Valor Compra");
        tac_valor_c.setText("");
        
        tac_valor_v = new MaterialTextField();
        tac_valor_v.setBackground(c);
        tac_valor_v.setForeground(Color.WHITE);
        tac_valor_v.setCaretColor(Color.WHITE);
        tac_valor_v.setLabel("Valor Venta");
        tac_valor_v.setText("");

        tablaact = new JTable();
        tablaact.setBackground(c);
        scrollact = new JScrollPane(tablaact);

        bac_actu = new RSButtonMetro();
        bac_actu.setText("Actualizar");

        pactualizar.setBounds(120, 0, 880, 700);
        
        lagre.setBounds(300, 10, 300, 40);
        
        tac_nombre.setBounds(310, 70, 250, 46);

        scrollact.setBounds(50, 130, 790, 200);

        tac2_nombre.setBounds(310, 350, 270, 46);
  
        tac_valor_c.setBounds(310, 406, 270, 46);
        
        tac_valor_v.setBounds(310, 462, 270, 46);
        

        bac_actu.setBounds(350, 574, 150, 40);
        
        ifondopan = new ImageIcon("fondopanel.png");
        lfondop = new JLabel(ifondopan);
        lfondop.setBounds(0, 0, 880, 700);

        this.getContentPane().add(pactualizar);
        pactualizar.add(lagre);
        
        pactualizar.add(tac_nombre);
      
        
        pactualizar.add(scrollact);
        pactualizar.add(bac_actu);
        
        pactualizar.add(tac2_nombre);
       
       
        pactualizar.add(tac_valor_c);
        
        
        
        pactualizar.add(tac_valor_v);
        
        
        pactualizar.add(lfondop);

    }

    public void pborrar() {
        pborrar = new JPanel();
        pborrar.setLayout(null);

        Color c = new Color(0,139,139);
        pborrar.setBackground(c);

        lagre = new JLabel("Borrar Deuda");
        lagre.setForeground(Color.BLUE);
        lagre.setFont(new Font("Arial", Font.PLAIN, 30));
 
        tb_nombre = new MaterialTextField();
        tb_nombre.setBackground(c);
        tb_nombre.setForeground(Color.WHITE);
        tb_nombre.setCaretColor(Color.WHITE);
        tb_nombre.setLabel("Ingrese el Nombre");
        tb_nombre.setText("");
        
        tb_nombre2 = new MaterialTextField();
        tb_nombre2.setEditable(false);
        tb_nombre2.setBackground(c);
        tb_nombre2.setForeground(Color.WHITE);
        tb_nombre2.setLabel("Nombre");
        tb_nombre2.setText("");
        
        tb_precioc = new MaterialTextField();
        tb_precioc.setEditable(false);
        tb_precioc.setBackground(c);
        tb_precioc.setForeground(Color.WHITE);
        tb_precioc.setLabel("Valor Compra");
        tb_precioc.setText("");
        
        tb_preciov = new MaterialTextField();
        tb_preciov.setEditable(false);
        tb_preciov.setBackground(c);
        tb_preciov.setForeground(Color.WHITE);
        tb_preciov.setLabel("Valor Venta");
        tb_preciov.setText("");
        

        tablabor = new JTable();
        tablabor.setBackground(c);
        scrollbor = new JScrollPane(tablabor);

        bb_borrar = new RSButtonMetro();
        bb_borrar.setText("Borrar");

        pborrar.setBounds(120, 0, 880, 700);
        lagre.setBounds(300, 10, 300, 40);
        

        tb_nombre.setBounds(310, 80, 250, 46);


        scrollbor.setBounds(50, 130, 790, 200);

        
        tb_nombre2.setBounds(310, 350, 270, 46);

        
        tb_precioc.setBounds(310, 406, 270, 46);

        
        tb_preciov.setBounds(310, 462, 270, 46);


        bb_borrar.setBounds(350, 518, 150, 40);
        
        ifondopan = new ImageIcon("fondopanel.png");
        lfondop = new JLabel(ifondopan);
        lfondop.setBounds(0, 0, 880, 700);

        this.getContentPane().add(pborrar);
        pborrar.add(lagre);
        
        pborrar.add(tb_nombre);
        
        
        pborrar.add(tb_precioc);
        pborrar.add(scrollbor);
        pborrar.add(bb_borrar);
        
        pborrar.add(tb_nombre2);
        
       
        
        pborrar.add(tb_preciov);

       
        pborrar.add(lfondop);

    }
}
