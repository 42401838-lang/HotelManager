package teoria.hotelmanager;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Habitacion;
import models.Reservacion;

/**
 * Controlador encargado de gestionar las reservaciones.
 *
 * @author Juana Alexandra Contreras Mendez
 * @author Jorge Armando González Oropeza
 */
public class ReservacionController {

    /**
     * Label donde se muestra la información de la habitación.
     */
    @FXML
    private Label lblHabitacion;

    /**
     * Label donde se muestra el precio de la habitación.
     */
    @FXML
    private Label lblPrecio;

    /**
     * Campo para ingresar el nombre del huésped.
     */
    @FXML
    private TextField txtHuesped;

    /**
     * Selector de fecha para el Check-In.
     */
    @FXML
    private DatePicker dpCheckIn;

    /**
     * Selector de fecha para el Check-Out.
     */
    @FXML
    private DatePicker dpCheckOut;

    /**
     * Label utilizado para mostrar mensajes de error.
     */
    @FXML
    private Label lblError;

    /**
     * Habitación seleccionada para reservar.
     */
    private Habitacion habitacion;

    /**
     * Referencia al controlador principal.
     */
    private habitacionesController principalController;

    /**
     * Configura la habitación y el controlador principal.
     */
    public void setHabitacion(Habitacion h, habitacionesController pc) {

        this.habitacion = h;
        this.principalController = pc;

        // Muestra información de la habitación
        lblHabitacion.setText(h.getNumero() + " - " + h.getTipo());
        lblPrecio.setText("$" + (int) h.getPrecio() + " por noche");

        // Check-In: no permite seleccionar fechas anteriores a hoy
        dpCheckIn.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                // Bloquea fechas anteriores
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });

        // Check-Out: restricción inicial
        dpCheckOut.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                // Bloquea fechas anteriores
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });

        // Actualiza restricciones del Check-Out según el Check-In
        dpCheckIn.valueProperty().addListener((obs, oldVal, newVal) -> {

            dpCheckOut.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);

                    // Bloquea fechas inválidas
                    if (date.isBefore(LocalDate.now())
                            || (newVal != null && !date.isAfter(newVal))) {

                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }
                }
            });

            // Limpia Check-Out si es inválido
            if (dpCheckOut.getValue() != null
                    && newVal != null
                    && !dpCheckOut.getValue().isAfter(newVal)) {

                dpCheckOut.setValue(null);
            }
        });
    }

    /**
     * Confirma la reservación de la habitación.
     */
    @FXML
    private void confirmarReservacion() {

        // Verifica que el nombre no esté vacío
        if (txtHuesped.getText().trim().isEmpty()) {
            lblError.setText("⚠ Ingresa el nombre del huésped.");
            return;
        }

        // Verifica que exista fecha de Check-In
        if (dpCheckIn.getValue() == null) {
            lblError.setText("⚠ Selecciona la fecha de Check-In.");
            return;
        }

        // Verifica que la fecha no sea anterior a hoy
        if (dpCheckIn.getValue().isBefore(LocalDate.now())) {
            lblError.setText("⚠ La fecha de Check-In no puede ser anterior a hoy.");
            return;
        }

        // Verifica que exista fecha de Check-Out
        if (dpCheckOut.getValue() == null) {
            lblError.setText("⚠ Selecciona la fecha de Check-Out.");
            return;
        }

        // Verifica que la fecha no sea anterior a hoy
        if (dpCheckOut.getValue().isBefore(LocalDate.now())) {
            lblError.setText("⚠ La fecha de Check-Out no puede ser anterior a hoy.");
            return;
        }

        // Verifica que Check-Out sea posterior al Check-In
        if (!dpCheckOut.getValue().isAfter(dpCheckIn.getValue())) {
            lblError.setText("⚠ Check-Out debe ser posterior al Check-In.");
            return;
        }

        // Crea una nueva reservación
        Reservacion r = new Reservacion(
                habitacion.getTipo(),
                habitacion.getPrecio(),
                txtHuesped.getText().trim(),
                dpCheckIn.getValue().toString(),
                dpCheckOut.getValue().toString()
        );

        // Cambia el estado de la habitación
        habitacion.setEstado("Reservada");

        // Actualiza las tarjetas y contadores
        principalController.actualizarTarjetas();

        // Cierra la ventana actual
        ((Stage) txtHuesped.getScene().getWindow()).close();
    }

    /**
     * Cierra la ventana sin guardar cambios.
     */
    @FXML
    private void cancelar() {
        ((Stage) txtHuesped.getScene().getWindow()).close();
    }
}
