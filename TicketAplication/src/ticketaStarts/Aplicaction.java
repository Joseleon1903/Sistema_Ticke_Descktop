package ticketaStarts;

import Controlador.Controlador;
import Modelo.Conexion_DB;
import Vista.Consultar_Ticket;
import Vista.Generar_Ticket;
import Vista.Principal;
import Vista.Ventana_Detalles;

public class Aplicaction {

    public void Inicio_Sistema() {
        System.out.println("----Sistema Starts----");
        Controlador Mi_Controlador = new Controlador();
        Principal Mi_Principal = new Principal();
        Consultar_Ticket Mi_Consulta = new Consultar_Ticket(Mi_Principal, true);
        Generar_Ticket Mi_Generar = new Generar_Ticket(Mi_Principal, true);
        Conexion_DB Mi_Conexion = new Conexion_DB();
        Ventana_Detalles Mi_ventana_Detalles = new Ventana_Detalles(Mi_Principal, true);
        
        
        //relacionando con el controlador 
        Mi_Principal.setControlador(Mi_Controlador);
        Mi_Consulta.setControlador(Mi_Controlador);
        Mi_Generar.setControlador(Mi_Controlador);
        Mi_Conexion.setControlador(Mi_Controlador);
        Mi_ventana_Detalles.setControlador(Mi_Controlador);
        
        //relacionando el controlador
        Mi_Controlador.setPrincipal(Mi_Principal);
        Mi_Controlador.setConsultarTicket(Mi_Consulta);
        Mi_Controlador.setGenerarTicket(Mi_Generar);
        Mi_Controlador.setConexion_DB(Mi_Conexion);
        Mi_Controlador.setVentanaDetalles(Mi_ventana_Detalles);

        //iniciando Sistema
        Mi_Principal.setVisible(true);

    }

}
