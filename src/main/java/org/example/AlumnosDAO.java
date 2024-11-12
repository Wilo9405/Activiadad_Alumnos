package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnosDAO {

    private final Connection connection;

    public AlumnosDAO() {
        this.connection = DBConnection.getConnection();
    }

    public boolean alumnoExists(String dni) {
        String sql = "SELECT COUNT(*) FROM alumnos WHERE dni = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, dni);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public int insertAlumno(Alumno alumno) throws SQLException {

        if (alumnoExists(alumno.getDni())) {
            return 0;
        }

        String querySQL = "INSERT INTO alumnos (nombre, apellido,dni,curso) VALUES(?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        preparedStatement.setString(1, alumno.getNombre());
        preparedStatement.setString(2, alumno.getApellido());
        preparedStatement.setString(3, alumno.getDni());
        preparedStatement.setString(4, alumno.getCurso());

        return preparedStatement.executeUpdate();

    }

    public void printAlumnos() throws SQLException {
        String sql = "SELECT * FROM alumnos";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("id"));
            System.out.println("Nombre: " + resultSet.getString("nombre"));
            System.out.println("Apellido: " + resultSet.getString("apellido"));
            System.out.println("DNI: " + resultSet.getString("dni"));
            System.out.println("Curso: " + resultSet.getString("curso"));
            System.out.println();
        }

    }

//    public int countAlumnos() throws SQLException {
//
//        String sql = "SELECT COUNT(*) FROM alumnos";
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//        ResultSet resultSet = statement.executeQuery();
//
//        if (resultSet.next()) {
//            return resultSet.getInt(1);
//        }
//
//        return 0;
//    }
}

//    public void deleteAllAlumnos() throws SQLException {
//        String deleteSQL = "DELETE FROM alumnos"; // Borra todos los registros
//        String resetSequenceSQL = "ALTER SEQUENCE alumnos_id_seq RESTART WITH 1"; // Reinicia la secuencia
//
//        // Ejecuta la eliminación
//        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
//            int rowsAffected = preparedStatement.executeUpdate();
//            System.out.println(rowsAffected + " registros eliminados.");
//        }
//
//        // Reinicia la secuencia
//        try (PreparedStatement preparedStatement = connection.prepareStatement(resetSequenceSQL)) {
//            preparedStatement.executeUpdate();
//            System.out.println("Secuencia reiniciada.");
//        }
//    }

//
//}
//    public void deleteAllAlumnos() throws SQLException {
//        Connection connection = DBConnection.getConnection();
//        String deleteSQL = "DELETE FROM alumnos";
//
//        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
//        int rowsAffected = preparedStatement.executeUpdate();
//
//        System.out.println(rowsAffected + " registros eliminados.");
//    }

//    public void readAllAlumnos() throws SQLException {
//        String sql = "SELECT id, nombre, apellido, dni, curso FROM alumnos";
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//        ResultSet resultSet = statement.executeQuery();
//        while(resultSet.next()){
//        System.out.println("El id es: " + resultSet.getInt("id") + "y el nombre es " + resultSet.getString("nombre"));
//        }
//
//    }


