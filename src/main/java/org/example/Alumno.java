package org.example;

public class Alumno {

    private String nombre;
    private  String apellido;
    private String dni;
    private String curso;


    public Alumno(String nombre, String apellido,String dni,String curso) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.curso = curso;

    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getCurso() {
        return curso;
    }
}
