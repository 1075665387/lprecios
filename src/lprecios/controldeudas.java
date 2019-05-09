package lprecios;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class controldeudas implements ActionListener, KeyListener, MouseListener {

    vistadeudas v;
    modelodeudas m;

    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo2 = new DefaultTableModel();
    DefaultTableModel modelo3 = new DefaultTableModel();

    controldeudas(modelodeudas mod, vistadeudas vis) {
        this.m = mod;
        this.v = vis;

        this.v.bprestamo.addActionListener(this);
        this.v.bacercade.addActionListener(this);
        this.v.bsalir.addActionListener(this);
        this.v.bagregar.addActionListener(this);
        this.v.beliminar.addActionListener(this);
        this.v.bbuscar.addActionListener(this);
        this.v.bmodificar.addActionListener(this);
        this.v.baagregar.addActionListener(this);
        this.v.bbmostrar.addActionListener(this);
        this.v.bac_actu.addActionListener(this);
        this.v.bb_borrar.addActionListener(this);
        this.v.bbpdf.addActionListener(this);

        this.v.tbnombre.addKeyListener(this);
        this.v.tac_nombre.addKeyListener(this);
        this.v.tb_nombre.addKeyListener(this);

        this.v.tablaact.addMouseListener(this);
        this.v.tablabor.addMouseListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.v.bprestamo) {
            this.m.retorno();
            this.v.dispose();
        }

        if (e.getSource() == this.v.bacercade) {
            JOptionPane.showMessageDialog(null, "Elkin Pulido Versión 3.0");
        }

        if (e.getSource() == this.v.bsalir) {
            System.exit(0);
        }

        if (e.getSource() == this.v.beliminar) {
            this.v.pagregar.setVisible(false);
            this.v.pbuscar.setVisible(false);
            this.v.pactualizar.setVisible(false);
            this.v.pborrar.setVisible(true);

        }
        
        if (e.getSource() == this.v.bbpdf) {
            int a = this.v.tablabuscar.getRowCount();
            int b = this.v.tablabuscar.getColumnCount();
            if(a==0 || b==0){
                 JOptionPane.showMessageDialog(null, "tabla vacía");
             }else{
            
            String datos[][] = new String[a][b];
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    datos[i][j] = this.v.tablabuscar.getValueAt(i, j).toString();
                }
            }
            Date d= new Date();
            //obtengo el mes
            Month mes = LocalDate.now().getMonth();
            //lo paso a español
            String mesesp = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
            
            int Year = LocalDate.now().getYear();
            
            
            String nombre = "deudas-"+d.getDate()+"-"+mesesp+"-"+Year+" Hora "+d.getHours()+"-"+d.getMinutes()
                    +"-"+d.getSeconds();
            String titulos[] = {"Id_deudas", "Nombre", "Concepto",
                 "Valor","Fecha"};
            int res = this.m.crearpdf(nombre, datos, titulos,this.v.tb_total.getText());
            if (res == 0) {
                this.m.Exito_Falla_guardando("error al crear PDF");
            } else {
                this.m.Exito_Falla_guardando("Creaciónn de PDF exitosa");
                //abrir el documento
                try {
                     File path = new File ("documentos pdf\\"+nombre+".pdf");
                      Desktop.getDesktop().open(path);
                      }catch (IOException ex) {
                       ex.printStackTrace();
                    }
            }
            }
        }

        if (e.getSource() == this.v.bagregar) {
            
            this.v.ta_fecha.setText(this.m.fecha());
            this.v.ta_fecha.setEditable(false);
            this.v.pbuscar.setVisible(false);
            this.v.pactualizar.setVisible(false);
            this.v.pborrar.setVisible(false);
            this.v.pagregar.setVisible(true);

        }

        if (e.getSource() == this.v.bbuscar) {

            this.v.pagregar.setVisible(false);
            this.v.pactualizar.setVisible(false);
            this.v.pborrar.setVisible(false);
            this.v.pbuscar.setVisible(true);

        }

        if (e.getSource() == this.v.bmodificar) {

            this.v.pagregar.setVisible(false);
            this.v.pbuscar.setVisible(false);
            this.v.pborrar.setVisible(false);
            this.v.pactualizar.setVisible(true);

        }

        if (e.getSource() == this.v.bac_actu) {

            this.m.conectar();
            if ((this.v.Lnro.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "Dale buscar y después seleciona una fila");
            } else {

                int re = this.m.actualizar_deuda(Integer.parseInt(this.v.Lnro.getText()),
                        this.v.tac2_nombre.getText(),
                        this.v.tac_valor_c.getText(),
                        this.v.tac_valor_v.getText()
                );

                if (re == 1) {
                    JOptionPane.showMessageDialog(null, "Actualización completa");
                    //limpiar tabla
                    for (int i = 0; i < this.v.tablaact.getRowCount(); i++) {
                        modelo2.removeRow(i);
                        i -= 1;
                    }
                    this.v.tablaact.setModel(new DefaultTableModel());

                    this.v.tac2_nombre.setText("");
                    this.v.tac_valor_c.setText("");
                    this.v.tac_valor_v.setText("");
                    this.v.tac_nombre.setText("");

                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo realizar el cambio");
                }
                this.v.Lnro.setText("");
            }

        }

        if (e.getSource() == this.v.baagregar) {

            if ((this.v.tanombre.getText().equals("")) || (this.v.tavalorcom.getText().equals(""))
                    || (this.v.tavalorven.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "llena todos los campos");

            } else {
                this.m.conectar();
                int s = this.m.guardar_deuda(this.v.tanombre.getText(),
                        this.v.tavalorcom.getText(), this.v.tavalorven.getText(),
                        this.v.ta_fecha.getText());
                if (s == 1) {
                    JOptionPane.showMessageDialog(null, "Guardado exitoso");
                    this.v.tanombre.setText("");
                    this.v.tavalorcom.setText("");
                    this.v.tavalorven.setText("");
                    this.v.ta_fecha.setText(this.m.fecha());
                } else {
                    JOptionPane.showMessageDialog(null, "error al guardar");
                }
                this.m.cerrar();
            }

        }

        if (e.getSource() == this.v.bb_borrar) {

            this.m.conectar();

            if ((this.v.Lnroborrar.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "buscar y después seleciona una fila: ");
            } else {
                int res = this.m.Borrar_deuda(Integer.parseInt(this.v.Lnroborrar.getText()));
                this.m.cerrar();
                if (res == 1) {
                    JOptionPane.showMessageDialog(null, "Ha sido borrado");
                    //limpiar tabla
                    for (int i = 0; i < this.v.tablabor.getRowCount(); i++) {
                        modelo3.removeRow(i);
                        i -= 1;
                    }
                    this.v.tablabor.setModel(new DefaultTableModel());

                    this.v.tb_nombre2.setText("");
                    this.v.tb_precioc.setText("");
                    this.v.tb_nombre.setText("");
                    this.v.tb_preciov.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo borrar");
                }
                this.v.Lnroborrar.setText("");
            }

        }

        if (e.getSource() == this.v.bbmostrar) {

            this.m.conectar();

            modelo = this.m.mostrardeuda();
            this.v.tablabuscar.setDefaultRenderer(Object.class, new colorFilas());
            this.v.tablabuscar.setModel(modelo);
            int suma = this.m.sumaTotal(this.v.tablabuscar);
                this.v.tb_total.setText(" " + suma);
            this.m.cerrar();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getSource() == this.v.tbnombre) {

            this.m.conectar();
            if ((this.v.tbnombre.getText().equals(""))) {
                //limpiar tabla
                for (int i = 0; i < this.v.tablabuscar.getRowCount(); i++) {
                    modelo.removeRow(i);
                    i -= 1;
                }
                this.v.tablabuscar.setModel(new DefaultTableModel());
                this.v.tb_total.setText("      0");
            } else {
                modelo = this.m.Buscar_deuda(this.v.tbnombre.getText());
                this.v.tablabuscar.setModel(modelo);
                this.v.tablabuscar.setDefaultRenderer(Object.class, new colorFilas());
                int suma = this.m.sumaTotal(this.v.tablabuscar);
                this.v.tb_total.setText(" " + suma);
                

                this.m.cerrar();
            }
        }

        if (e.getSource() == this.v.tb_nombre) {

            this.m.conectar();
            if ((this.v.tb_nombre.getText().equals(""))) {
                //limpiar tabla
                for (int i = 0; i < this.v.tablabor.getRowCount(); i++) {
                    modelo3.removeRow(i);
                    i -= 1;
                }
                this.v.tablabor.setModel(new DefaultTableModel());
            } else {
                modelo3 = this.m.Buscar_deuda(this.v.tb_nombre.getText());
                this.v.tablabor.setDefaultRenderer(Object.class, new colorFilas());
                this.v.tablabor.setModel(modelo3);

                this.m.cerrar();
            }
        }

        if (e.getSource() == this.v.tac_nombre) {

            this.m.conectar();
            if ((this.v.tac_nombre.getText().equals(""))) {
                //limpiar tabla
                for (int i = 0; i < this.v.tablaact.getRowCount(); i++) {
                    modelo2.removeRow(i);
                    i -= 1;
                }
                this.v.tablaact.setModel(new DefaultTableModel());
            } else {
                modelo2 = this.m.Buscar_deuda(this.v.tac_nombre.getText());
                this.v.tablaact.setDefaultRenderer(Object.class, new colorFilas());
                this.v.tablaact.setModel(modelo2);

                this.m.cerrar();
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == this.v.tablaact) {
            this.m.conectar();
            int fil = this.v.tablaact.getSelectedRow();

            if (fil >= 0) {
                this.v.Lnro.setText(String.valueOf(this.v.tablaact.getValueAt(fil, 0))); //guarda el id a actualizar
                this.v.tac2_nombre.setText(String.valueOf(this.v.tablaact.getValueAt(fil, 1)));
                this.v.tac_valor_c.setText(String.valueOf(this.v.tablaact.getValueAt(fil, 2)));
                this.v.tac_valor_v.setText(String.valueOf(this.v.tablaact.getValueAt(fil, 3)));

            } else {
                JOptionPane.showMessageDialog(null, "Selecione una fila");
            }
        }

        if (e.getSource() == this.v.tablabor) {
            this.m.conectar();
            int fil = this.v.tablabor.getSelectedRow();

            if (fil >= 0) {
                this.v.Lnroborrar.setText(String.valueOf(this.v.tablabor.getValueAt(fil, 0))); //guarda el id a actualizar
                this.v.tb_nombre2.setText(String.valueOf(this.v.tablabor.getValueAt(fil, 1)));
                this.v.tb_precioc.setText(String.valueOf(this.v.tablabor.getValueAt(fil, 2)));
                this.v.tb_preciov.setText(String.valueOf(this.v.tablabor.getValueAt(fil, 3)));

            } else {
                JOptionPane.showMessageDialog(null, "Selecione una fila");
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
