/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Clase que representa un usuario del sistema.
 * @author Juana Alexandra Contreras Mendez
 * @author Jorge Armando González Oropeza
 */
public class Usuario {

    // Nombre de usuario
    private String username;

    // Contraseña del usuario
    private String password;

    /**
     * Constructor de la clase Usuario.
     */
    public Usuario(String username, String password) {

        // Inicializa el nombre de usuario
        this.username = username;

        // Inicializa la contraseña
        this.password = password;
    }

    /**
     * Valida las credenciales del usuario.
     */
    public boolean validarUsuario(String user, String pass) {

        // Compara el usuario y contraseña ingresados
        return this.username.equals(user) && this.password.equals(pass);
    }

    /**
     * Obtiene el nombre de usuario.
     */
    public String getUsername() {
        return username;
    }
}
