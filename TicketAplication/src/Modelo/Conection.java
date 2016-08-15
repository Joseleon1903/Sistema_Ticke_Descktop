package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conection {

    private Connection mi_Conexion = null;
  //classe conexion que sirve para conectarse a la base de dato SQL server 2012 que devuelve una conexion
    public Connection getConection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            mi_Conexion = DriverManager.getConnection("jdbc:sqlserver://JOSEEDUARDO-PC\\SQLSERVER:1433;databaseName=Sistema_Ticket", "JOSEEDUARDO-PC", "sqlserver1");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mi_Conexion;
    }

    public void Desconectar() {
        this.mi_Conexion = null;
    }
}
