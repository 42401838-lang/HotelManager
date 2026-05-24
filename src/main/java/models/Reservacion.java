/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Clase que representa una reservación del hotel.
 *
 * @author Juana Alexandra Contreras Mendez
 * @author Jorge Armando González Oropeza
 */
public class Reservacion {

    // Nombre o identificación de la habitación reservada
    private String nombreHabitacion;

    // Costo total de la reservación
    private double costo;

    // Nombre del huésped
    private String nombreHuesped;

    // Fecha de entrada
    private String checkIn;

    // Fecha de salida
    private String checkOut;

    /**
     * Constructor de la clase Reservacion.
     */
    public Reservacion(String nombreHabitacion, double costo,
            String nombreHuesped, String checkIn, String checkOut) {

        // Inicializa el nombre de la habitación
        this.nombreHabitacion = nombreHabitacion;

        // Inicializa el costo de la reservación
        this.costo = costo;

        // Inicializa el nombre del huésped
        this.nombreHuesped = nombreHuesped;

        // Inicializa la fecha de entrada
        this.checkIn = checkIn;

        // Inicializa la fecha de salida
        this.checkOut = checkOut;
    }

    /**
     * Obtiene el nombre de la habitación.
     */
    public String getNombreHabitacion() {
        return nombreHabitacion;
    }

    /**
     * Obtiene el costo de la reservación.
     */
    public double getCosto() {
        return costo;
    }

    /**
     * Obtiene el nombre del huésped.
     */
    public String getNombreHuesped() {
        return nombreHuesped;
    }

    /**
     * Obtiene la fecha de entrada.
     */
    public String getCheckIn() {
        return checkIn;
    }

    /**
     * Obtiene la fecha de salida.
     */
    public String getCheckOut() {
        return checkOut;
    }
}
