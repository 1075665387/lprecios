/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lprecios;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import static javax.swing.BorderFactory.createTitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import principal.MaterialTextField;
import rsbuttom.RSButtonMetro;

/**
 *
 * @author Rafael
 */
public class vistainventario extends JFrame{
    JLabel l2,l3,l4,l5,l7,l8,l10,fondo,lnit,lid,lvcliente;
    JTextField tvbuscar,t2,tvvalor,tvtotal,tvtotalpagar ,tvfec,tvnit,tvcliente;
    JButton buscar,buscar2,eliminar,agregar,exportar,limpiar,Afecha;
    ImageIcon ibuscar,ventasd;
    
    JPanel p1,p2,p3,p4;
    JScrollPane spanel,spanel2;
    Color j;
    SpinnerModel sm;
    JSpinner spin;
    JTable tabla;
    JList lista;
    String titulos[] = {  },
            contenido[][] = {};
    JTextField tpnombre,tpdocumento,tpfecha,tpnombre_libro,tenombre_p1,tenombre_p2,
            tenombre_l1,tenombre_l2,tefecha,tedocu,tecantidad;

    JPanel panel1, panel2, pentrega, pprestamo, pagregar, pbuscar, pactualizar,
            pborrar,pvender;

    JButton bentrega, brenovar
            , bbexcel,
            bprestar,bp_fecha;
    ImageIcon i1,ifondopan,ImageIcon,ibor,iact,ibus,iingresar,iretorno,iacerca,isalir,ivender;
    JLabel Lnro,Lnroborrar, fondopanel, linv, lpres, lagre, lanombre,lavalorven, 
            lavalorcom, lac_nombre,lfondop,lmenu,
            lbor,lact,lbus,lingresar,lretorno,lacerca,lsalir,lac_valor_c,lac_valor_v,
            la_prove,lact_prove,lvender;

    JScrollPane scrollbus, scrollact,scrollbor,scrollprestar1,scrollprestar2,scrollentrega;
    JTable tablabuscar, tablaact,tablabor,tablaprestar1,tablaprestar2,tablaentrega;
    JSpinner scantidad;
    Image Image;
    
    JComboBox ca_prove;
    //material de diseño
    RSButtonMetro bbuscar, bagregar, beliminar, bmodificar,bvender,bacercade,bsalir,bdevolver, bprestamo
            ,bbmostrar,bbpdf,baagregar, bb_borrar,bac_actu;
    
    MaterialTextField tbnombre,tanombre,tavalorcom, tavalorven,tb_nombre, tb_precioc,
            tb_nombre2, tb_preciov,tact_prove,tac_nombre, tac2_nombre, tac_valor_c, tac_valor_v;

    vistainventario() {
        setTitle("Inventario");
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
        pvender();
        pvender.setVisible(false);

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
        ivender=new ImageIcon("vender.png");
        
        lbor=new JLabel(ibor);
        lact=new JLabel(iact);
        lbus=new JLabel(ibus);
        lingresar=new JLabel(iingresar);
        lsalir=new JLabel(isalir);
        lretorno=new JLabel(iretorno);
        lacerca=new JLabel(iacerca);
        lvender=new JLabel(ivender);
  
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
        bbuscar.setText("Buscar");
        
        bacercade = new RSButtonMetro();
        bacercade.setText("Acerca de");
        bsalir = new RSButtonMetro();
        bsalir.setText("Salir");
        bvender = new RSButtonMetro();
        bvender.setText("Vender");
       
        lmenu.setBounds(10, 10, 140, 30);
        
        lbus.setBounds(0, 50, 40, 40);
        bbuscar.setBounds(40, 50, 120, 40);
        
        
        lingresar.setBounds(0, 100, 40, 40);
        bagregar.setBounds(40, 100, 120, 40);

        lbor.setBounds(0, 150, 40, 40);
        beliminar.setBounds(40, 150, 120, 40);
        
        lact.setBounds(0, 200, 40, 40);
        bmodificar.setBounds(40, 200, 120, 40);
        
        lvender.setBounds(0, 250, 40, 40);
        bvender.setBounds(40, 250, 120, 40);
        
        lretorno.setBounds(0, 300, 40, 40);
        bprestamo.setBounds(40, 300, 120, 40);
       
        
        lacerca.setBounds(0, 350, 40, 40);
        bacercade.setBounds(40, 350, 120, 40);
        
        lsalir.setBounds(0, 400, 40, 40);
        bsalir.setBounds(40, 400, 120, 40);

        this.getContentPane().add(panel2);
        
        panel2.add(bprestamo);
        panel2.add(lretorno);
        
        panel2.add(bagregar);
        
        panel2.add(lingresar);
        panel2.add(beliminar);
        
        panel2.add(lbor);
        panel2.add(bmodificar);
        
        panel2.add(lvender);
        panel2.add(bvender);
        
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
        

        lagre = new JLabel("Agregar Producto");
  
        
        
        la_prove = new JLabel("Proveedor:");
        la_prove.setForeground(Color.BLUE);
        la_prove.setFont(new Font("Arial", Font.PLAIN, 20));
        
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
        tavalorcom.setLabel("Valor Compra");
        tavalorcom.setText("");
        
        tavalorven = new MaterialTextField();
        tavalorven.setBackground(c);
        tavalorven.setForeground(Color.WHITE);
        tavalorven.setCaretColor(Color.WHITE);
        tavalorven.setLabel("Valor Venta");
        tavalorven.setText("");
        
        String[] prove={"Distri LC","Alejandro","Dulces"};
        
        ca_prove = new JComboBox(prove);
        ca_prove.setBackground(c);
        ca_prove.setFont(new Font("Arial", Font.PLAIN, 15));
        ca_prove.setForeground(Color.WHITE);

        baagregar = new RSButtonMetro();
        baagregar.setText("Guardar");

        pagregar.setBounds(120, 0, 880, 700);
        
        lagre.setBounds(300, 15, 300, 40);
        lagre.setFont(new Font("Arial", Font.PLAIN, 30));
        lagre.setForeground(Color.BLUE);

        
        tanombre.setBounds(310, 110, 300, 46);

        
        tavalorcom.setBounds(310, 166, 300, 46);

        
        tavalorven.setBounds(310, 222, 300, 46);

        la_prove.setBounds(180, 278, 130, 46);
        ca_prove.setBounds(310, 278, 300, 46);

        baagregar.setBounds(310, 334, 100, 40);
        baagregar.setBackground(c);
        
        

        this.getContentPane().add(pagregar);
        pagregar.add(lagre);
        
        pagregar.add(tanombre);
        
        pagregar.add(tavalorcom);
        
        pagregar.add(tavalorven);
        pagregar.add(la_prove);
        pagregar.add(ca_prove);
       
        pagregar.add(baagregar);
        pagregar.add(lfondop);

    }

    public void pbuscar() {
        pbuscar = new JPanel();
        pbuscar.setLayout(null);

        Color c = new Color(0,139,139);
        

        lagre = new JLabel("Buscar Producto");
        lagre.setForeground(Color.BLUE);
        lagre.setFont(new Font("Arial", Font.PLAIN, 30));
        
        
       
        tbnombre = new MaterialTextField();
        tbnombre.setBackground(c);
        tbnombre.setForeground(Color.WHITE);
        tbnombre.setCaretColor(Color.WHITE);
        tbnombre.setLabel("Escriba el Nombre");
        tbnombre.setText("");

        bbmostrar = new RSButtonMetro();
        bbmostrar.setText("Mostrar Todo");

        tablabuscar = new JTable();
        tablabuscar.setBackground(c);
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

        bbpdf.setBounds(310, 520, 150, 40);
        bbexcel.setBounds(440, 520, 150, 40);
        
        ifondopan = new ImageIcon("fondopanel.png");
        lfondop = new JLabel(ifondopan);
        lfondop.setBounds(0, 0, 880, 700);

        this.getContentPane().add(pbuscar);
        pbuscar.add(lagre);
       
        pbuscar.add(tbnombre);
        
        
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
       

        lagre = new JLabel("Actualizar Producto");
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
        
        tact_prove = new MaterialTextField();
        tact_prove.setBackground(c);
        tact_prove.setForeground(Color.WHITE);
        tact_prove.setCaretColor(Color.WHITE);
        tact_prove.setLabel("Proveedor");
        tact_prove.setText("");

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
        
        tact_prove.setBounds(310, 518, 270, 46);

       
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
        
        pactualizar.add(tact_prove);
       
        pactualizar.add(tac_valor_c);
        
        
        
        pactualizar.add(tac_valor_v);
        
        
        pactualizar.add(lfondop);

    }

    public void pborrar() {
        pborrar = new JPanel();
        pborrar.setLayout(null);

        Color c = new Color(0,139,139);
        pborrar.setBackground(c);

        lagre = new JLabel("Borrar Producto");
        lagre.setForeground(Color.BLUE);
        lagre.setFont(new Font("Arial", Font.PLAIN, 30));;
      
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
    
    public void pvender() {
        pvender = new JPanel();
        pvender.setLayout(null);

        Color c = new Color(214, 234, 248);
        
        lagre = new JLabel("Ventas");
        lagre.setForeground(Color.BLUE);
        lagre.setFont(new Font("Arial", Font.PLAIN, 40));
        
         
        l2 = new JLabel("Fecha:");
        l2.setForeground(Color.BLUE);
        l2.setFont(new Font("Arial", Font.PLAIN, 20));
        
        lnit = new JLabel("Nit:");
        lnit.setForeground(Color.BLUE);
        lnit.setFont(new Font("Arial", Font.PLAIN, 20));
        
        l3 = new JLabel("Ingrese el Nombre:");
        l3.setForeground(Color.BLUE);
        l3.setFont(new Font("Arial", Font.PLAIN, 20));
      
        l5 = new JLabel("Valor:");
        l5.setForeground(Color.BLUE);
        l5.setFont(new Font("Arial", Font.PLAIN, 20));
        
        l7 = new JLabel("Cantidad:");
        l7.setForeground(Color.BLUE);
        l7.setFont(new Font("Arial", Font.PLAIN, 20));
        
        l8 = new JLabel("Total:");
        l8.setForeground(Color.BLUE);
        l8.setFont(new Font("Arial", Font.PLAIN, 20));
        
        lvcliente = new JLabel("Cliente:");
        lvcliente.setForeground(Color.BLUE);
        lvcliente.setFont(new Font("Arial", Font.PLAIN, 20));
        
        l10 = new JLabel("Total:");
        l10.setForeground(Color.RED);
        l10.setFont(new Font("Arial", Font.PLAIN, 30));
        
        lid = new JLabel();
        
        tvfec = new JTextField();
        tvfec.setForeground(Color.BLUE);
        tvfec.setFont(new Font("Arial", Font.PLAIN, 15));
        tvfec.setBackground(c);
        tvfec.setEditable(false);
        
        tvnit = new JTextField("1075665387-2");
        tvnit.setForeground(Color.BLUE);
        tvnit.setFont(new Font("Arial", Font.PLAIN, 15));
        tvnit.setBackground(c);
        tvnit.setEditable(false);
        
        tvbuscar = new JTextField();
        tvbuscar.setForeground(Color.BLUE);
        tvbuscar.setFont(new Font("Arial", Font.PLAIN, 15));
        
        tvvalor = new JTextField();
        tvvalor.setForeground(Color.BLUE);
        tvvalor.setFont(new Font("Arial", Font.PLAIN, 15));
        tvvalor.setBackground(c);
        tvvalor.setEditable(false);
        
        tvcliente = new JTextField();
        tvcliente.setForeground(Color.BLUE);
        tvcliente.setFont(new Font("Arial", Font.PLAIN, 15));
        tvcliente.setBackground(c);
        
        tvtotal = new JTextField();
        tvtotal.setForeground(Color.BLUE);
        tvtotal.setFont(new Font("Arial", Font.PLAIN, 15));
        tvtotal.setBackground(c);
        tvtotal.setEditable(false);
        
        tvtotalpagar  = new JTextField("      0");
        tvtotalpagar .setForeground(Color.RED);
        tvtotalpagar .setFont(new Font("Arial", Font.PLAIN, 30));
        tvtotalpagar .setBackground(c);
        tvtotalpagar.setEditable(false);
        
        j = new Color(227, 0, 82);
        
        lista =new JList();
        lista.setForeground(Color.WHITE);
        lista.setFont(new Font("Arial", Font.PLAIN, 16));
        lista.setBackground(j);
        
        spanel2 = new JScrollPane(lista);
        
        ibuscar= new ImageIcon("buscar.png");
       
        
        tabla = new JTable(contenido, titulos);
        tabla.setBackground(c);
        spanel = new JScrollPane(tabla);
        
        exportar = new JButton("Imprimir Factura");
        exportar.setToolTipText("En esta opción podrá generar la factura en PDF.");
        exportar.setBackground(j);
        exportar.setForeground(Color.WHITE);
        exportar.setFont(new Font("Arial", Font.PLAIN, 20));
      
        agregar = new JButton("Vender");
        agregar.setToolTipText("En esta opción podrá agregar al carrito la venta");
        agregar.setBackground(j);
        agregar.setForeground(Color.WHITE);
        agregar.setFont(new Font("Arial", Font.PLAIN, 20));
        
        eliminar = new JButton("Eliminar");
        eliminar.setToolTipText("En esta opción podrá eliminar un producto");
        eliminar.setBackground(j);
        eliminar.setForeground(Color.WHITE);
        eliminar.setFont(new Font("Arial", Font.PLAIN, 20));
        
        limpiar= new JButton("Limpiar");
        limpiar.setToolTipText("En esta opción podrá limpiar las casillas");
        limpiar.setBackground(j);
        limpiar.setForeground(Color.WHITE);
        limpiar.setFont(new Font("Arial", Font.PLAIN, 20));
        
        Afecha = new JButton("Actualizar Hora");
        Afecha.setBackground(j);
        Afecha.setFont(new Font("Arial", Font.PLAIN, 15));
        Afecha.setForeground(Color.WHITE);
        
        
        p1 = new JPanel();
        p1.setBackground(c);
        p1.setBorder(createTitledBorder("Datos de la Compra"));
        
        p2 = new JPanel();
        p2.setBackground(c);
        p2.setBorder(createTitledBorder("Buscar Producto"));
        
        p3 = new JPanel();
        p3.setBackground(c);
        p3.setBorder(createTitledBorder("Venta"));
        
        p4 = new JPanel();
        p4.setBackground(c);
        p4.setBorder(createTitledBorder("Factura"));
       
        sm = new SpinnerNumberModel(1, 1, 300, 1);//valor inicial,minimo,max,unidades enrte cada uno
        spin = new JSpinner(sm);
        spin.setForeground(Color.BLUE);
        spin.setFont(new Font("Arial", Font.PLAIN, 15));
        spin.setBackground(c);
        
        lvcliente.setBounds(70,70,100,30);
        tvcliente.setBounds(140,70,150,30);
        
        l2.setBounds(300,70,100,30);
        tvfec.setBounds(360,70,170,30);
        Afecha.setBounds(540,70,140,30);
        
        lnit.setBounds(690,70,60,30);
        tvnit.setBounds(720,70,100,30);
        
        p1.setBounds(50,50,790,70);

        
        l3.setBounds(70,160,200,30);
        tvbuscar.setBounds(240,160,340,30);
        
        spanel2.setBounds(240,195,340,100);
        spanel2.setVisible(false);
        
        
        l5.setBounds(600,160,70,30);
        tvvalor.setBounds(660,160,100,30);
        
        
        p2.setBounds(50,130,790,170);
        
        
        l7.setBounds(70,330,100,30);
        spin.setBounds(170,330,50,30);
        l8.setBounds(230,330,100,30);
        tvtotal.setBounds(300,330,140,30);
        agregar.setBounds(450,330,100,30);
        eliminar.setBounds(560,330,110,30);
        limpiar.setBounds(680,330,100,30);
        p3.setBounds(50,310,790,60);
        spanel.setBounds(50, 380, 790, 130);
        
        l10.setBounds(640,535,80,40);
        tvtotalpagar.setBounds(720,535,115,40);
        p4.setBounds(50,520,790,110);
        exportar.setBounds(350,580,180,40);
        pvender.setBounds(120, 0, 880, 700);
        lagre.setBounds(350, 10, 300, 40);
        
        ifondopan = new ImageIcon("fondopanel.png");
        lfondop = new JLabel(ifondopan);
        lfondop.setBounds(0, 0, 880, 700);

        this.getContentPane().add(pvender);
        pvender.add(lvcliente);
        pvender.add(tvcliente);
        pvender.add(lagre);
        pvender.add(l2);
        pvender.add(l3);
        pvender.add(tvnit);
        pvender.add(lnit);
        pvender.add(l5);
        
        pvender.add(l7);
        pvender.add(l8);
        
        pvender.add(l10);
        pvender.add(tvbuscar);
        pvender.add(tvvalor);
        
        pvender.add(tvtotal);
        pvender.add(tvtotalpagar);
        pvender.add(spanel2);
        
        pvender.add(Afecha);
        
        pvender.add(spin);
        pvender.add(eliminar);
        pvender.add(limpiar);
        pvender.add(exportar);
        pvender.add(agregar);
        pvender.add(tvfec);
        pvender.add(p1);
        pvender.add(p2);
        pvender.add(p3);
        pvender.add(p4);
        
        pvender.add(spanel);
        
        pvender.add(lfondop);
        

    }
}
