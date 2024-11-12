package org.example;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AlumnosDAO dao = new AlumnosDAO();
        String xmlPath = ".\\src\\main\\java\\org\\example\\alumnos.xml";
        List<Alumno> alumnos = XMLParser.parseaAlumnosXML(xmlPath);
        int alumnosInsertados = 0;

        try {

            for (Alumno alumno : alumnos) {
                int result = dao.insertAlumno(alumno);
                if (result > 0) {
                    alumnosInsertados++; // Solo incrementa si se insertó un nuevo alumno
                }
            }

            System.out.println("Alumnos insertados: " + alumnosInsertados);
            dao.printAlumnos(); // Imprime los alumnos en la base de datos

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
}
