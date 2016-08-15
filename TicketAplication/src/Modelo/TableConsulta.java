package Modelo;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TableConsulta {

    public static DefaultTableModel Modelo_Tabla_Consulta(JTable table, ArrayList<Ticket> lista) {
        DefaultTableModel modeltable = (DefaultTableModel) table.getModel();
        modeltable.setRowCount(0);
        for (Ticket ticket : lista) {
            modeltable.addRow(new Object[]{ticket.getID(), ticket.getNombre_Cliente(), ticket.getCedula(), ticket.getTelefono(), ticket.getModelo()});
        }
        return modeltable; 
    }
    

}
