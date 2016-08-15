package Controlador;

import Modelo.*;
import Vista.*;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Controlador {

    private Principal Mi_Prinicipal;
    private Generar_Ticket Mi_Generar;
    private Consultar_Ticket Mi_Consulta;
    private Conexion_DB Mi_Conexion_DB;
    private Ventana_Detalles Mi_ventan_detalles;

    public void setPrincipal(Principal Mi_Principal) {
        this.Mi_Prinicipal = Mi_Principal;
    }

    public void setConsultarTicket(Consultar_Ticket Mi_Consulta) {
        this.Mi_Consulta = Mi_Consulta;
    }

    public void setGenerarTicket(Generar_Ticket Mi_Generar) {
        this.Mi_Generar = Mi_Generar;
    }

    public void AbrirVentanaGenerarTicket() {
        Mi_Generar.setLocation(Mi_Generar.getParent().getX() + 50, Mi_Generar.getParent().getY() - 10);
        Mi_Generar.setVisible(true);
    }

    public void CerrarVentanaGenerarTicket() {
        Mi_Generar.setVisible(false);
    }

    public void AbrirVentanaConsulta() {
        Mi_Consulta.setLocation(Mi_Consulta.getParent().getX() + 50, Mi_Consulta.getParent().getY() - 10);
        Mi_Consulta.setVisible(true);
    }

    public void CerrarVentanaConsulta() {
        Mi_Consulta.setVisible(false);
    }

    public void AgregarTicket(Ticket ticket) {
        Mi_Conexion_DB.AgregarTicket(new Conection(), ticket);
    }

    public void setConexion_DB(Conexion_DB Mi_Conexion) {
        this.Mi_Conexion_DB = Mi_Conexion;
    }

    public DefaultTableModel TablaConsulta(JTable Mi_Table) {
        return TableConsulta.Modelo_Tabla_Consulta(Mi_Table, Mi_Conexion_DB.getListaTicket(new Conection()));
    }

    public void setVentanaDetalles(Ventana_Detalles Mi_ventana_Detalles) {
        this.Mi_ventan_detalles = Mi_ventana_Detalles;
    }

    public void AbrirVentanaDetalles(String ID) {
        CerrarVentanaConsulta();
        Ticket tk = Mi_Conexion_DB.GetTicket(new Conection(), ID);
        Mi_ventan_detalles.setTicket(tk);
        Mi_ventan_detalles.setLocation(Mi_ventan_detalles.getParent().getX() + 50, Mi_ventan_detalles.getParent().getY() - 10);
        Mi_ventan_detalles.setVisible(true);
    }

    public void CerrarVentanaDetalles() {
        Mi_ventan_detalles.setVisible(false);
    }

    public boolean DespacharTicket(Ticket ticketDepachado) {
        return Mi_Conexion_DB.ActualizarTicket(new Conection(), ticketDepachado);
    }

    public Boolean ActualizarTicket(Ticket ticketVisualizado) {
        return Mi_Conexion_DB.ActualizarTicket_VS(new Conection(), ticketVisualizado);
    }

    public TableModel getTableBuscar(String text, String Filtro) {
        ArrayList<Ticket> array = Mi_Conexion_DB.ResultBuscar(text, Filtro, new Conection());
        for (Ticket ticket : array) {
            System.out.println(ticket.toString());
        }
        return TableConsulta.Modelo_Tabla_Consulta(Mi_Consulta.getTable(), array);
    }

}
