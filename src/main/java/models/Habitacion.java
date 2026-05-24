/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Clase que representa una habitación del hotel.
 * @author Juana Alexandra Contreras Mendez
 * @author Jorge Armando González Oropeza
 */
public class Habitacion {
    //Declaración de campos
    private int numero;
    private String tipo;
    private int capacidad;
    private double precio;
    private boolean wifi, tv, desayuno;
    private String estado; // Disponible o Reservada

    /**
     * Constructor de la clase Habitacion.
     */
    public Habitacion(int numero, String tipo, int capacidad,
            double precio, boolean wifi, boolean tv, boolean desayuno) {

        this.numero = numero;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.precio = precio;
        this.wifi = wifi;
        this.tv = tv;
        this.desayuno = desayuno;
        this.estado = "Disponible";
    }

    // Métodos getters
    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isWifi() {
        return wifi;
    }

    public boolean isTv() {
        return tv;
    }

    public boolean isDesayuno() {
        return desayuno;
    }

    public String getEstado() {
        return estado;
    }

    /**
     * Método setter para cambiar el estado de la habitación.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
