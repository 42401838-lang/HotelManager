package teoria.hotelmanager;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Habitacion;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador encargado de administrar las habitaciones y las reservaciones del
 * sistema.
 *
 * @author Juana Alexandra Contreras Mendez
 * @author Jorge Armando González Oropeza
 */
public class habitacionesController implements Initializable {

    // Labels de información
    @FXML
    private Label lblRol;
    @FXML
    private Label lblDisponibles;
    @FXML
    private Label lblReservados;
    @FXML
    private Label lblOcupadas;
    @FXML
    private Label lblUsuario;
    // Botones de reservación
    @FXML
    private Button btnReservar;
    @FXML
    private Button btnReservar1;
    @FXML
    private Button lblReservar2;
    @FXML
    private Button lblReservar3;
    @FXML
    private Button lblReservar4;
    @FXML
    private Button lblReservar5;
    // Badges que muestran el estado de cada habitación
    @FXML
    private Label badge101;
    @FXML
    private Label badge102;
    @FXML
    private Label badge201;
    @FXML
    private Label badge202;
    @FXML
    private Label badge301;
    @FXML
    private Label badge302;

    // Arreglo que almacena las habitaciones del hotel
    private Habitacion[] habitaciones = {
        new Habitacion(101, "Suite Ejecutiva", 2, 150, true, true, true),
        new Habitacion(102, "Habitación Doble", 2, 100, true, true, false),
        new Habitacion(201, "Suite Presidencial", 4, 250, true, true, true),
        new Habitacion(202, "Individual", 1, 80, true, false, false),
        new Habitacion(301, "Familiar", 4, 180, true, true, true),
        new Habitacion(302, "Suite Deluxe", 3, 200, true, true, true)
    };

    /**
     * Método que se ejecuta automáticamente al iniciar la vista.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Actualiza tarjetas y estadísticas
        actualizarTarjetas();
    }

    /**
     * Muestra el nombre y rol del usuario.
     */
    public void setUsuario(String nombre) {

        // Muestra el nombre del usuario
        lblUsuario.setText(nombre);

        // Verifica si el usuario es administrador
        if (nombre.equals("admin")) {

            lblRol.setText("Administrador");

        } else {

            lblRol.setText("Cliente");
        }
    }

    /**
     * Abre la reservación de la habitación 101.
     */
    @FXML
    private void reservar101() {
        abrirReservacion(habitaciones[0]);
    }

    /**
     * Abre la reservación de la habitación 102.
     */
    @FXML
    private void reservar102() {
        abrirReservacion(habitaciones[1]);
    }

    /**
     * Abre la reservación de la habitación 201.
     */
    @FXML
    private void reservar201() {
        abrirReservacion(habitaciones[2]);
    }

    /**
     * Abre la reservación de la habitación 202.
     */
    @FXML
    private void reservar202() {
        abrirReservacion(habitaciones[3]);
    }

    /**
     * Abre la reservación de la habitación 301.
     */
    @FXML
    private void reservar301() {
        abrirReservacion(habitaciones[4]);
    }

    /**
     * Abre la reservación de la habitación 302.
     */
    @FXML
    private void reservar302() {
        abrirReservacion(habitaciones[5]);
    }

    /**
     * Abre la ventana modal para realizar una reservación.
     */
    private void abrirReservacion(Habitacion h) {

        try {

            // Carga la vista de reservación
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/teoria/hotelmanager/habitacion-view.fxml"));

            Parent root = loader.load();

            // Obtiene el controlador de reservación
            ReservacionController rc = loader.getController();

            // Envía la habitación seleccionada
            rc.setHabitacion(h, this);

            // Crea una nueva ventana modal
            Stage modal = new Stage();

            // Bloquea interacción con otras ventanas
            modal.initModality(Modality.APPLICATION_MODAL);

            // Título de la ventana
            modal.setTitle("Reservar Habitación");

            // Asigna la escena
            modal.setScene(new Scene(root));

            // Evita redimensionar la ventana
            modal.setResizable(false);

            // Muestra la ventana
            modal.showAndWait();

        } catch (IOException e) {

            // Muestra error en consola
            e.printStackTrace();
        }
    }

    /**
     * Actualiza botones, badges y estadísticas.
     */
    public void actualizarTarjetas() {

        actualizarBoton(btnReservar, badge101, habitaciones[0]);
        actualizarBoton(btnReservar1, badge102, habitaciones[1]);
        actualizarBoton(lblReservar2, badge201, habitaciones[2]);
        actualizarBoton(lblReservar3, badge202, habitaciones[3]);
        actualizarBoton(lblReservar4, badge301, habitaciones[4]);
        actualizarBoton(lblReservar5, badge302, habitaciones[5]);

        // Actualiza contadores
        actualizarEstadisticas();
    }

    /**
     * Actualiza el estado visual de un botón y badge.
     */
    private void actualizarBoton(Button btn, Label badge, Habitacion h) {

        // Verifica si la habitación está disponible
        boolean disponible = h.getEstado().equals("Disponible");

        // Actualiza el texto del botón
        btn.setText(disponible ? "Reservar Ahora" : "Reservada");

        // Habilita o deshabilita el botón
        btn.setDisable(!disponible);

        // Cambia el estilo del botón
        btn.setStyle(disponible
                ? "-fx-background-color: #4f46e5; -fx-text-fill: white; -fx-background-radius: 6; -fx-cursor: hand;"
                : "-fx-background-color: #e5e7eb; -fx-text-fill: #9ca3af; -fx-background-radius: 6;");

        // Actualiza el texto del badge
        badge.setText(disponible ? "Disponible" : "Reservada");

        // Cambia el estilo del badge
        badge.setStyle(disponible
                ? "-fx-background-color: #DCFCE7; -fx-text-fill: #15803D; -fx-font-size: 12px; "
                + "-fx-font-weight: bold; -fx-padding: 6 15; -fx-background-radius: 20;"
                : "-fx-background-color: #FEF3C7; -fx-text-fill: #92400E; -fx-font-size: 12px; "
                + "-fx-font-weight: bold; -fx-padding: 6 15; -fx-background-radius: 20;");
    }

    /**
     * Actualiza las estadísticas de habitaciones.
     */
    private void actualizarEstadisticas() {

        int disponibles = 0, reservadas = 0;

        // Recorre todas las habitaciones
        for (Habitacion h : habitaciones) {

            // Cuenta habitaciones disponibles
            if (h.getEstado().equals("Disponible")) {

                disponibles++;

            } else {

                reservadas++;
            }
        }

        // Muestra habitaciones disponibles
        lblDisponibles.setText(String.valueOf(disponibles));

        // Muestra habitaciones reservadas
        lblReservados.setText(String.valueOf(reservadas));

        // Muestra habitaciones ocupadas
        lblOcupadas.setText(String.valueOf(reservadas));
    }

    /**
     * Cierra completamente la aplicación.
     */
    @FXML
    private void cerrarSesion() {
        // Finaliza el programa
        System.exit(0);
    }
}
