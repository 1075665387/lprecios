/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lprecios;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Rafael
 */
public class colorFilas extends DefaultTableCellRenderer{
    private Component component;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        component= super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Color c = new Color(0,139,139);
        
        if(row%2==0){
           component.setBackground(c);
           component.setForeground(Color.WHITE);
        }else{
           component.setBackground(Color.white);
           component.setForeground(Color.BLACK);
        }
        return component;
    }
    
    
    
}
