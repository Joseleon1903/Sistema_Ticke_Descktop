/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Controlador;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion_DB {

    //Consultas relizadas a las base de Dato SQL server
    private Connection Conexion = null;
    private Controlador Mi_Controlador;
    private String SentenciaAgregarTicket = "insert into TICKET values(?,?,?," + "GETDATE()" + ",?,?,?,?)";
    private String SentenciaListaTicket = "select ID , NOMBRE_CLIENTE, CEDULA , TELEFONO, MODELO from TICKET";
    private String SentenciaBuscarTicket = "select * from TICKET where CEDULA = ? ";
    private String SentenciaDespacharTicket = "update TICKET set ESTADO = ? where CEDULA = ?";
    private String SentenciaActualizarTicket = "update TICKET set NOMBRE_CLIENTE= ?, CEDULA= ?, TELEFONO = ?, MODELO=?, SERIE= ?, OBSERVACIONES= ? where ID= ?";
    private String SentenciaBuscarLista = "select ID , NOMBRE_CLIENTE, CEDULA , TELEFONO, MODELO from TICKET where ";

    public void setControlador(Controlador Mi_Controlador) {
        this.Mi_Controlador = Mi_Controlador;
    }

    public void AgregarTicket(Conection conection, Ticket ticket) {
        Conexion = conection.getConection();
        try {
            PreparedStatement sentencia = Conexion.prepareCall(SentenciaAgregarTicket);
            sentencia.setString(1, ticket.getNombre_Cliente());
            sentencia.setString(2, ticket.getCedula());
            sentencia.setString(3, ticket.getTelefono());
            sentencia.setString(4, ticket.getModelo());
            sentencia.setString(5, ticket.getSerie());
            sentencia.setString(6, ticket.getObservaciones());
            sentencia.setString(7, ticket.getEstado());
            sentencia.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(Conexion_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        conection.Desconectar();
    }

    public ArrayList<Ticket> getListaTicket(Conection conexion) {
        ArrayList<Ticket> Array = new ArrayList<Ticket>();
        Conexion = conexion.getConection();
        Ticket ticket;
        try {
            Statement statement = Conexion.createStatement();
            ResultSet rs = statement.executeQuery(SentenciaListaTicket);
            while (rs.next()) {
                ticket = new Ticket();
                ticket.setID(Integer.parseInt(rs.getString(1)));
                ticket.setNombre_Cliente(rs.getString(2));
                ticket.setCedula(rs.getString(3));
                ticket.setTelefono(rs.getString(4));
                ticket.setModelo(rs.getString(5));
                Array.add(ticket);
            }
            rs.close();
            conexion.Desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion_DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Array;
    }

    public Ticket GetTicket(Conection conection, String ID) {
        Conexion = conection.getConection();
        Ticket Mi_Ticket = new Ticket();
        PreparedStatement preStat;
        try {
            preStat = Conexion.prepareCall(SentenciaBuscarTicket);
            preStat.setString(1, ID);
            ResultSet rs = preStat.executeQuery();
            while (rs.next()) {
                Mi_Ticket.setID(Integer.parseInt(rs.getString(1)));
                Mi_Ticket.setNombre_Cliente(rs.getString(2));
                Mi_Ticket.setCedula(rs.getString(3));
                Mi_Ticket.setTelefono(rs.getString(4));
                Mi_Ticket.setDia_Recibido(rs.getString(5));
                Mi_Ticket.setModelo(rs.getString(6));
                Mi_Ticket.setSerie(rs.getString(7));
                Mi_Ticket.setObservaciones(rs.getString(8));
                Mi_Ticket.setEstado(rs.getString(9));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("" + ID);
            Logger.getLogger(Conexion_DB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return Mi_Ticket;
    }

    public Boolean ActualizarTicket(Conection conection, Ticket tk) {
        Boolean Exito = false;
        String Estado_Ticket = tk.getEstado();
        Conexion = conection.getConection();
        try {
            PreparedStatement st = Conexion.prepareCall(SentenciaDespacharTicket);
            st.setString(1, Estado_Ticket);
            st.setString(2, tk.getCedula());
            st.execute();
            Exito = true;
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Exito;
    }

    public Boolean ActualizarTicket_VS(Conection conection, Ticket ticketVisualizado) {
        Boolean Exito = false;
        Conexion = conection.getConection();
        try {
            PreparedStatement st = Conexion.prepareCall(SentenciaActualizarTicket);
            st.setString(1, ticketVisualizado.getNombre_Cliente());
            st.setString(2, ticketVisualizado.getCedula());
            st.setString(3, ticketVisualizado.getTelefono());
            st.setString(4, ticketVisualizado.getModelo());
            st.setString(5, ticketVisualizado.getSerie());
            st.setString(6, ticketVisualizado.getObservaciones());
            st.setInt(7, ticketVisualizado.getID());
            System.out.println("" + ticketVisualizado.getID());
            st.execute();
            Exito = true;
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Exito;
    }

    public ArrayList<Ticket> ResultBuscar(String text, String Filtro, Conection Con) {
        ArrayList<Ticket> result = new ArrayList<Ticket>();
        System.out.println("Sercht: " + text + "Filtro :" + Filtro);
        Conexion = Con.getConection();
        ResultSet rs;
        try {
            Statement preStat = Conexion.createStatement();
            if (Filtro.equals("ID")) {
                rs = preStat.executeQuery(SentenciaBuscarLista + Filtro + "=" + text);
            } else {
                rs = preStat.executeQuery(SentenciaBuscarLista + Filtro + "= '" + text + "'");
            }
            while (rs.next()) {
                Ticket Mi_Ticket = new Ticket();
                Mi_Ticket.setID(Integer.parseInt(rs.getString(1)));
                Mi_Ticket.setNombre_Cliente(rs.getString(2));
                Mi_Ticket.setCedula(rs.getString(3));
                Mi_Ticket.setTelefono(rs.getString(4));
                Mi_Ticket.setModelo(rs.getString(5));
                result.add(Mi_Ticket);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
