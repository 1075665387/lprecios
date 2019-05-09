
 
package lprecios;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class modelodeudas {
    
    Connection connect;
    String url = "precios.db";
     public void conectar() {
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:" + url);
            if (connect != null) {
                System.out.println("Conectado");
            }
        } catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos\n" + ex.getMessage());
        }
    }

    public void cerrar() {
        try {
            connect.close();
        } catch (SQLException ex) {
            System.out.println("Error en el cierre" + ex.getMessage());
        }
    }
    
    public String fecha(){
       String dato;
       java.util.Date fechaa = new java.util.Date();
       dato=fechaa.toLocaleString();
       return dato;
   }
    
    public void Exito_Falla_guardando(String msj) {
        JOptionPane.showMessageDialog(null, msj);
    }
    
    public int crearpdf(String nombre, String valores[][], String Encabezados[],
            String total) {
        String Ruta = "documentos pdf\\" + nombre + ".pdf";
        int result = 0;
        int a = valores.length;//Columnas
        int b = valores[0].length;//Filas
        File file;
        PdfWriter writer;
        PdfDocument pdf;
        Document document;
        try {
            
            file = new File(Ruta);
            file.getParentFile().mkdirs();
            writer = new PdfWriter(Ruta);
            pdf = new PdfDocument(writer);
            document = new Document(pdf);
            ImageData image = ImageDataFactory.create("logopdf.png");
            com.itextpdf.layout.element.Image imageModel=new com.itextpdf.layout.element.Image(image);
            imageModel.setHorizontalAlignment(HorizontalAlignment.CENTER);

            document.add(imageModel);
            
            Text nit=new Text("NIT. 1075665387-2 ");
            nit.setFontColor(Color.BLUE);
            nit.setFontSize(16);
            Paragraph preface = new Paragraph(nit);
            //centrar el texto
            preface.setTextAlignment(TextAlignment.CENTER);
            document.add(preface);
            
            Table tabla = new Table(b);
            
            //tabla.setMargin(5);
            
            for(int i=0; i<Encabezados.length;i++){
                tabla.addCell(new Cell().add(new Paragraph(" "+ Encabezados [i])));
            }
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    Cell celda = new Cell();
                    celda.add(new Paragraph("" + valores[i][j]));
                    tabla.addCell(celda);
                }
            }

            document.add(tabla);
            
            //agragamos nueva tabla para el total
            Table tabla2 = new Table(2);
            //tabla2.setMargin(5);
            tabla2.setFontColor(Color.BLUE);
            tabla2.setTextAlignment(TextAlignment.CENTER);
            
            String valores2[]={"Total a Pagar",""+total};
             for (int i = 0; i < 2; i++) {
                
                    Cell celda3 = new Cell();
                    celda3.add(new Paragraph("" + valores2[i]));
                    tabla2.addCell(celda3);
                
            }
            document.add(tabla2);

            document.close();
            result = 1;
        } catch (IOException ioex) {
            System.err.println("" + ioex.getMessage());
        }
        return result;
    

    }
    
     public int guardar_deuda(String nom, String concep,String valor,String fecha) {
        int estado;
        try {
            PreparedStatement st
                    = connect.prepareStatement("insert into deudas (nombre,concepto,valor,fecha)"
                            + " values (?,?,?,?)");
            st.setString(1, nom);
            st.setString(2, concep);
            st.setString(3, valor);
            st.setString(4, fecha);
            
            st.execute();
            estado = 1;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            estado = 0;
        }
        return estado;
    }
     
     public DefaultTableModel Buscar_deuda(String Dato) {
      ResultSet rs;
        Statement s;
        DefaultTableModel modelo= new DefaultTableModel();
        String campoDB = "";

        try {
            
            String filtro=""+Dato+"_%";
            campoDB = "select * from deudas where nombre like '"+filtro+"%' ";

            
            PreparedStatement st = connect.prepareStatement(campoDB );
            
            rs = st.executeQuery();
             ResultSetMetaData rsmd=rs.getMetaData();
            //obtenemos numero de columnas
            int canc=rsmd.getColumnCount();
            for(int i=1;i<=canc;i++){
                //cargarmos columnas en modelo
                   modelo.addColumn(rsmd.getColumnLabel(i));
                   
               }
            while (rs.next()) {

                 //creamos array
               Object[] filas = new Object[canc];
               //cargamos datos a modelo
               for(int i=0;i<canc;i++){
                   filas[i]=rs.getObject(i+1);
                   
               }
               modelo.addRow(filas);
                

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return modelo;


    }
     
       public int sumaTotal(JTable tab){
         
        int scol4=0,col4=0;
        int fil = tab.getRowCount();
        
        for(int i=0;i<fil;i++)
        {if(tab.getValueAt(i,3).toString().equals("")){
                col4=0;
                
            }else{col4= Integer.parseInt(String.valueOf(tab.getValueAt(i,3)));
         scol4=scol4+col4;
              }
         }
        return scol4;
      }
     
     public DefaultTableModel mostrardeuda() {
        ResultSet rs;
        Statement s;
        DefaultTableModel modelo= new DefaultTableModel();
        
         try {
             //consulta a mostrar
            PreparedStatement st = connect.prepareStatement("select * from deudas ");
            rs = st.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            //obtenemos numero de columnas
            int canc=rsmd.getColumnCount();
            for(int i=1;i<=canc;i++){
                //cargarmos columnas en modelo
                   modelo.addColumn(rsmd.getColumnLabel(i));
                   
               }
            
            while (rs.next()) {
                //creamos array
               Object[] filas = new Object[canc];
               //cargamos datos a modelo
               for(int i=0;i<canc;i++){
                   filas[i]=rs.getObject(i+1);
                   
               }
               modelo.addRow(filas);
               
         
          }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return modelo;

    }
     
     public int Borrar_deuda(int id) {
        int estado;
        try {
            PreparedStatement st
                    = connect.prepareStatement(
                            "delete from deudas where id_deudas=?");
            st.setInt(1, id);
            st.executeUpdate();
            estado = 1;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            estado = 0;
        }
        return estado;
    }
     
     public int actualizar_deuda(int id,String nom, String compra,String venta
             ) {
        int estado;
        try {
            PreparedStatement st
                    = connect.prepareStatement("update deudas set nombre=?,concepto=?,"
                            + "valor=?"
                            + " where id_deudas=?");
            st.setString(1, nom);
            st.setString(2, compra);
            st.setString(3, venta);
            st.setInt(4, id);
           
            st.executeUpdate();
            estado = 1;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            estado = 0;
        }
        return estado;
    }
     public void retorno(){
        vistamenu1 v= new vistamenu1();
        modelomenu1 m= new modelomenu1();
        controlmenu1 c= new controlmenu1(m,v);
    }
}
