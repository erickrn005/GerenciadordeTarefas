package br.com.dominio.controller.DAOcontroller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bancoconector { 

    private static final String URL = "jdbc:postgresql://localhost:5433/GerenciadeTarefas";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root1234";

    public static Connection getConnection() throws SQLException {
        try {
           
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Driver not found");
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
