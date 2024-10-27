package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {


    private static final String url ="jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String pass ="Wilo503@";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            String createTableQuery = "CREATE TABLE IF NOT EXISTS alumnos (" +
                    "id SERIAL PRIMARY KEY," +
                    "nombre VARCHAR(50)," +
                    "apellido VARCHAR(50)," +
                    "dni CHARACTER(20) UNIQUE," +
                    "curso VARCHAR(20)" +
                    ");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableQuery);
//            System.out.println("tabla creada");
        return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





}
