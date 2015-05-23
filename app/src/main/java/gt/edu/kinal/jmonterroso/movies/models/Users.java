package gt.edu.kinal.jmonterroso.movies.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Users implements Serializable {

    private static final long serialVersionUID = 5680898935329497057L;
    private String nombre;
    private String apellido;
    private String contrasena;
    private int telefono;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Alumno [nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono  + " contraseña="+ contrasena +"]";
    }
}