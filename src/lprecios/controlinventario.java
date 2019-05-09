/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lprecios;

import java.awt.Color;
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
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Rafael
 */
public class controlinventario implements ActionListener, KeyListener, ChangeListener, MouseListener {

    modeloinventario m;
    vistainventario v;
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo2 = new DefaultTableModel();
    DefaultTableModel modelo3 = new DefaultTableModel();
    DefaultTableModel modeloventa = new DefaultTableModel();

    controlinventario(modeloinventario mod, vistainventario vise) {

        this.m = mod;
        this.v = vise;
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
        this.v.bvender.addActionListener(this);
        this.v.Afecha.addActionListener(this);
        this.v.bbpdf.addActionListener(this);
        this.v.agregar.addActionListener(this);
        this.v.eliminar.addActionListener(this);
        this.v.limpiar.addActionListener(this);
        this.v.exportar.addActionListener(this);

        this.v.tbnombre.addKeyListener(this);
        this.v.tac_nombre.addKeyListener(this);
        this.v.tb_nombre.addKeyListener(this);
        this.v.tvbuscar.addKeyListener(this);

        this.v.tablaact.addMouseListener(this);
        this.v.tablabor.addMouseListener(this);
        this.v.lista.addMouseListener(this);

        this.v.spin.addChangeListener(this);

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

        if (e.getSource() == this.v.Afecha) {
            this.v.tvfec.setText(" " + this.m.fecha());
        }

        if (e.getSource() == this.v.bsalir) {
            System.exit(0);
        }

        if (e.getSource() == this.v.exportar) {

            if (this.v.tvcliente.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el Nombre del Cliente");
            } else {

                int a = this.v.tabla.getRowCount();
                int b = this.v.tabla.getColumnCount();
                if (a == 0 || b == 0) {
                    JOptionPane.showMessageDialog(null, "tabla vacía");
                } else {

                    String datos[][] = new String[a][b];
                    for (int i = 0; i < a; i++) {
                        for (int j = 0; j < b; j++) {
                            datos[i][j] = this.v.tabla.getValueAt(i, j).toString();
                        }
                    }
                    Date d = new Date();
                    //obtengo el mes
                    Month mes = LocalDate.now().getMonth();
                    //lo paso a español
                    String mesesp = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

                    int Year = LocalDate.now().getYear();

                    String nombre = "factura-" + d.getDate() + "-" + mesesp + "-" + Year + " Hora " + d.getHours() + "-" + d.getMinutes()
                            + "-" + d.getSeconds();
                    String titulos[] = {"Producto", "Precio Unidad",
                        "Cantidad", "Total"};
                    //enviamos nit fecha y hora
                    String fec = "NIT. 1075665387-2       Fecha y Hora:" + this.v.tvfec.getText();
                    String cliente = "CLIENTE:  " + this.v.tvcliente.getText();
                    // toupoercase es para convertir el texto a mayuscula 
                    int res = this.m.crearpdfventas(nombre, datos, titulos, this.v.tvtotalpagar.getText(),
                            fec, cliente.toUpperCase());
                    if (res == 0) {
                        this.m.Exito_Falla_guardando("error al crear PDF");
                    } else {
                        //limpiar tabla
                        for (int i = 0; i < this.v.tabla.getRowCount(); i++) {
                            modeloventa.removeRow(i);
                            i -= 1;
                        }
                        this.v.tabla.setModel(new DefaultTableModel());
                        this.v.tvtotalpagar.setText("");
                        this.v.tvcliente.setText("");
                        this.m.Exito_Falla_guardando("Se generó la factura");
                        //abrir el documento
                        try {
                            File path = new File("documentos pdf\\" + nombre + ".pdf");
                            Desktop.getDesktop().open(path);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }

            }
        }

        if (e.getSource() == this.v.beliminar) {
            this.v.pagregar.setVisible(false);
            this.v.pbuscar.setVisible(false);
            this.v.pactualizar.setVisible(false);
            this.v.pvender.setVisible(false);
            this.v.pborrar.setVisible(true);

        }

        if (e.getSource() == this.v.agregar) {
            if ((this.v.tvbuscar.getText().equals("")) || (this.v.spin.getValue().toString().equals(""))
                    || (this.v.tvvalor.getText().equals("")) || (this.v.tvtotal.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "Busca un producto");
            } else {

                this.v.tabla.setModel(modeloventa);
                this.v.tabla.setDefaultRenderer(Object.class, new colorFilas());


                if (modeloventa.getColumnCount() == 0) {

                    modeloventa.addColumn("PRODUCTO");
                    modeloventa.addColumn("PRECIO UNIDAD");
                    modeloventa.addColumn("CANTIDAD");
                    modeloventa.addColumn("TOTAL");

                    /*centrar el texto en la tabla
                    DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                    Alinear.setHorizontalAlignment(SwingConstants.CENTER);
                    this.v.tabla.getColumnModel().getColumn(0).setCellRenderer(Alinear);
                    this.v.tabla.getColumnModel().getColumn(1).setCellRenderer(Alinear);
                    this.v.tabla.getColumnModel().getColumn(2).setCellRenderer(Alinear);
                    this.v.tabla.getColumnModel().getColumn(3).setCellRenderer(Alinear);*/
                    
                    this.v.tabla.setDefaultRenderer(Object.class, new colorFilas());

                    String nombre = this.v.tvbuscar.getText();
                    String costo = this.v.tvvalor.getText();
                    String cant = this.v.spin.getValue().toString();
                    String total = this.v.tvtotal.getText();

                    Object[] fila = new Object[4];

                    fila[0] = nombre;
                    fila[1] = costo;
                    fila[2] = cant;
                    fila[3] = total;
                    modeloventa.addRow(fila);
                    this.v.tvtotalpagar.setText("" + this.m.sumaTotal(this.v.tabla));

                    this.v.tvbuscar.setText("");
                    this.v.tvtotal.setText("");

                    this.v.spin.setValue(((SpinnerNumberModel) this.v.spin.getModel()).getMinimum());

                } else {

                    /*entrar el texto en la tabla
                    DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                    Alinear.setHorizontalAlignment(SwingConstants.CENTER);
                    this.v.tabla.getColumnModel().getColumn(0).setCellRenderer(Alinear);
                    this.v.tabla.getColumnModel().getColumn(1).setCellRenderer(Alinear);
                    this.v.tabla.getColumnModel().getColumn(2).setCellRenderer(Alinear);
                    this.v.tabla.getColumnModel().getColumn(3).setCellRenderer(Alinear);*/
                    
                    this.v.tabla.setDefaultRenderer(Object.class, new colorFilas());

                    String nombre = this.v.tvbuscar.getText();
                    String costo = this.v.tvvalor.getText();
                    String cant = this.v.spin.getValue().toString();
                    String total = this.v.tvtotal.getText();

                    Object[] fila = new Object[4];

                    fila[0] = nombre;
                    fila[1] = costo;
                    fila[2] = cant;
                    fila[3] = total;
                    modeloventa.addRow(fila);
                    this.v.tvtotalpagar.setText("" + this.m.sumaTotal(this.v.tabla));

                    this.v.tvbuscar.setText("");
                    this.v.tvtotal.setText("");

                    this.v.spin.setValue(((SpinnerNumberModel) this.v.spin.getModel()).getMinimum());

                }

            }

        }

        if (e.getSource() == this.v.bbpdf) {
            int a = this.v.tablabuscar.getRowCount();
            int b = this.v.tablabuscar.getColumnCount();
            if (a == 0 || b == 0) {
                JOptionPane.showMessageDialog(null, "tabla vacía");
            } else {

                String datos[][] = new String[a][b];
                for (int i = 0; i < a; i++) {
                    for (int j = 0; j < b; j++) {
                        datos[i][j] = this.v.tablabuscar.getValueAt(i, j).toString();
                    }
                }
                Date d = new Date();
                //obtengo el mes
                Month mes = LocalDate.now().getMonth();
                //lo paso a español
                String mesesp = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

                int Year = LocalDate.now().getYear();

                String nombre = "productos-" + d.getDate() + "-" + mesesp + "-" + Year + " Hora " + d.getHours() + "-" + d.getMinutes()
                        + "-" + d.getSeconds();
                String titulos[] = {"Id_inventario", "Nombre", "Valor Compra",
                    "Valor Venta", "Proveedor"};
                int res = this.m.crearpdf(nombre, datos, titulos);
                if (res == 0) {
                    this.m.Exito_Falla_guardando("error al crear PDF");
                } else {
                    this.m.Exito_Falla_guardando("Creaciónn de PDF exitosa");
                    //abrir el documento
                    try {
                        File path = new File("documentos pdf\\" + nombre + ".pdf");
                        Desktop.getDesktop().open(path);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        }

        if (e.getSource() == this.v.bagregar) {

            this.v.pbuscar.setVisible(false);
            this.v.pactualizar.setVisible(false);
            this.v.pborrar.setVisible(false);
            this.v.pvender.setVisible(false);
            this.v.pagregar.setVisible(true);

        }

        if (e.getSource() == this.v.bbuscar) {

            this.v.pagregar.setVisible(false);
            this.v.pactualizar.setVisible(false);
            this.v.pborrar.setVisible(false);
            this.v.pvender.setVisible(false);
            this.v.pbuscar.setVisible(true);

        }

        if (e.getSource() == this.v.bmodificar) {

            this.v.pagregar.setVisible(false);
            this.v.pbuscar.setVisible(false);
            this.v.pborrar.setVisible(false);
            this.v.pvender.setVisible(false);
            this.v.pactualizar.setVisible(true);

        }

        if (e.getSource() == this.v.eliminar) {
            //obtenemos la fila seleccionada
            int fila_selecionada = this.v.tabla.getSelectedRow();

            if (fila_selecionada < 0) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");

            } else {

                //la eliminamos
                modeloventa.removeRow(fila_selecionada);

                this.v.tvtotalpagar.setText("" + this.m.sumaTotal(this.v.tabla));
            }

        }

        if (e.getSource() == this.v.limpiar) {

            int fila_selecionada = this.v.tabla.getRowCount();

            if (fila_selecionada == 0) {
                JOptionPane.showMessageDialog(null, "tabla vacia");

            } else {
                this.v.tvbuscar.setText("");
                this.v.tvtotal.setText("");
                this.v.spin.setValue(((SpinnerNumberModel) this.v.spin.getModel()).getMinimum());
                this.v.tvtotalpagar.setText("");
                this.v.tvcliente.setText("");
                //limpiar tabla
                for (int i = 0; i < this.v.tabla.getRowCount(); i++) {
                    modeloventa.removeRow(i);
                    i -= 1;
                }
                this.v.tabla.setModel(new DefaultTableModel());
            }
        }

        if (e.getSource() == this.v.bvender) {

            this.v.pagregar.setVisible(false);
            this.v.pbuscar.setVisible(false);
            this.v.pborrar.setVisible(false);
            this.v.pactualizar.setVisible(false);
            this.v.pvender.setVisible(true);
            this.v.tvfec.setText("    " + this.m.fecha());

        }

        if (e.getSource() == this.v.bac_actu) {

            this.m.conectar();
            if ((this.v.Lnro.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "Dale buscar y después seleciona una fila");
            } else {

                int re = this.m.actualizar_producto(Integer.parseInt(this.v.Lnro.getText()),
                        this.v.tac2_nombre.getText(),
                        this.v.tac_valor_c.getText(),
                        this.v.tac_valor_v.getText(),
                        this.v.tact_prove.getText()
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
                    this.v.tact_prove.setText("");

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
                int s = this.m.guardar_producto(this.v.tanombre.getText(),
                        this.v.tavalorcom.getText(), this.v.tavalorven.getText(),
                        this.v.ca_prove.getSelectedItem().toString());
                if (s == 1) {
                    JOptionPane.showMessageDialog(null, "Guardado exitoso");
                    this.v.tanombre.setText("");
                    this.v.tavalorcom.setText("");
                    this.v.tavalorven.setText("");
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
                int res = this.m.Borrar_producto(Integer.parseInt(this.v.Lnroborrar.getText()));
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
            this.v.tablabuscar.setDefaultRenderer(Object.class, new colorFilas());
            modelo = this.m.mostrarproducto();
            this.v.tablabuscar.setModel(modelo);
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
            } else {
                this.v.tablabuscar.setDefaultRenderer(Object.class, new colorFilas());
                modelo = this.m.Buscar_producto(this.v.tbnombre.getText());
                this.v.tablabuscar.setModel(modelo);

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
                modelo3 = this.m.Buscar_producto(this.v.tb_nombre.getText());
                this.v.tablabor.setDefaultRenderer(Object.class, new colorFilas());
                this.v.tablabor.setModel(modelo3);

                this.m.cerrar();
            }
        }

        if (e.getSource() == this.v.tvbuscar) {
            if (this.v.tvbuscar.getText().equals("")) {
                this.v.spanel2.setVisible(false);
            } else {

                this.m.conectar();

                String s = this.v.tvbuscar.getText();
                String[] l = this.m.cargarproductos(s);
                this.v.lista.setListData(l);

                this.v.spanel2.setVisible(true);

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
                modelo2 = this.m.Buscar_producto(this.v.tac_nombre.getText());
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
                this.v.tact_prove.setText(String.valueOf(this.v.tablaact.getValueAt(fil, 4)));

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

        if (e.getSource() == this.v.lista) {

            String mensaje;
            if (this.v.lista.getSelectedIndex() == -1) {
                mensaje = "No ha seleccionado nada.";
            } else {
                mensaje = this.v.lista.getSelectedValue().toString();
            }

            this.v.tvbuscar.setText(mensaje);

            this.v.spanel2.setVisible(false);
            this.m.conectar();
            if ((this.v.tvbuscar.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "Ingresa el Nombre");
            } else {
                String data[] = this.m.Buscar_ventas(this.v.tvbuscar.getText());

                this.v.tvvalor.setText(data[0]);

                //obtiene resultado
                int a, b, resu;
                a = Integer.parseInt(this.v.tvvalor.getText());
                b = Integer.parseInt(this.v.spin.getValue().toString());
                resu = a * b;
                this.v.tvtotal.setText("" + resu);

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

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == this.v.spin) {
            if (this.v.tvvalor.getText().equals("")) {
                this.v.spin.setValue(((SpinnerNumberModel) this.v.spin.getModel()).getMinimum());

            } else {
                //obtiene resultado
                int a, b, resu;
                a = Integer.parseInt(this.v.tvvalor.getText());
                b = Integer.parseInt(this.v.spin.getValue().toString());
                resu = a * b;
                this.v.tvtotal.setText("" + resu);
            }
        }
    }

}
