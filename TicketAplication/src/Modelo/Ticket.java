package Modelo;

import java.sql.Date;

public class Ticket {
    private int ID;
    private String Telefono;
    private String Dia_Recibido;
    private String Nombre_Cliente;
    private String Cedula;
    private String Modelo;
    private String Serie;
    private String Observaciones;
    private String Estado;

    public Ticket(String Telefono, String Dia_Recibido, String Nombre_Cliente, String Cedula, String Modelo, String Serie, String Observaciones) {
        this.Telefono = Telefono;
        this.Dia_Recibido = Dia_Recibido;
        this.Nombre_Cliente = Nombre_Cliente;
        this.Cedula = Cedula;
        this.Modelo = Modelo;
        this.Serie = Serie;
        this.Observaciones = Observaciones;
        this.Estado = "EN_PROCESO";
    }

    public Ticket() {
        this.Telefono = "";
        this.Dia_Recibido = null;
        this.Nombre_Cliente = "";
        this.Cedula = "";
        this.Modelo = "";
        this.Serie = "";
        this.Observaciones = "";
        this.Estado = "EN_PROCESO";
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDia_Recibido() {
        return Dia_Recibido;
    }

    public void setDia_Recibido(String Dia_Recibido) {
        this.Dia_Recibido = Dia_Recibido;
    }

    public String getNombre_Cliente() {
        return Nombre_Cliente;
    }

    public void setNombre_Cliente(String Nombre_Cliente) {
        this.Nombre_Cliente = Nombre_Cliente;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String Serie) {
        this.Serie = Serie;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    public String getEstado() {
        return Estado;
    }

    public void PagarTicket() {
        this.Estado = "PAGADO";
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    @Override
    public String toString(){
    return ID+"-"+Nombre_Cliente+"-"+ Cedula+"-"+Telefono+"-"+Modelo;
    }

}
