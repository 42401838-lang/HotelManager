package teoria.hotelmanager;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.Usuario;

/**
 * Controlador encargado del inicio de sesión.
 *
 * @author Juana Alexandra Contreras Mendez
 * @author Jorge Armando González Oropeza
 */
public class loginController {

    // Campo para ingresar el usuario
    @FXML
    private TextField txtUsuario;
    // Campo para ingresar la contraseña
    @FXML
    private PasswordField txtPassword;
    // Label para mostrar mensajes de error
    @FXML
    private Label lblError;

    // Arreglo de usuarios registrados
    private Usuario[] usuarios = {
        new Usuario("admin", "admin123"),
        new Usuario("cliente", "cliente123"),
        new Usuario("usuario", "user123")
    };

    /**
     * Método que se ejecuta automáticamente al iniciar la vista.
     */
    @FXML
    private void initialize() {

        // Limpia el mensaje de error
        lblError.setText("");

        // Limpia errores al escribir en el usuario
        txtUsuario.textProperty().addListener((obs, oldVal, newVal)
                -> lblError.setText(""));

        // Limpia errores al escribir en la contraseña
        txtPassword.textProperty().addListener((obs, oldVal, newVal)
                -> lblError.setText(""));
    }

    /**
     * Verifica las credenciales e inicia sesión.
     */
    @FXML
    private void onAbrirLoginClick() throws IOException {

        // Obtiene el texto ingresado
        String usuario = txtUsuario.getText().trim();
        String password = txtPassword.getText().trim();

        // Verifica que el usuario no esté vacío
        if (usuario.isEmpty()) {

            lblError.setText("⚠ Ingresa tu usuario.");
            return;
        }

        // Verifica que la contraseña no esté vacía
        if (password.isEmpty()) {

            lblError.setText("⚠ Ingresa tu contraseña.");
            return;
        }

        // Recorre el arreglo de usuarios
        for (Usuario u : usuarios) {

            // Valida usuario y contraseña
            if (u.validarUsuario(usuario, password)) {

                // Carga la ventana principal
                FXMLLoader loader = new FXMLLoader(
                        App.class.getResource("reservar-view.fxml"));

                Parent root = loader.load();

                // Obtiene el controlador principal
                habitacionesController hc = loader.getController();

                // Envía el nombre del usuario
                hc.setUsuario(usuario);

                // Cambia la vista actual
                App.getScene().setRoot(root);

                // Maximiza la ventana
                App.getStage().setMaximized(true);

                return;
            }
        }

        // Mensaje de error si las credenciales son incorrectas
        lblError.setText("⚠ Usuario o contraseña incorrectos.");

        // Cambia el estilo del mensaje
        lblError.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

        // Limpia la contraseña
        txtPassword.clear();

        // Regresa el foco al campo contraseña
        txtPassword.requestFocus();
    }
}
