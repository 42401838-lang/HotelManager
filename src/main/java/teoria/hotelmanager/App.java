package teoria.hotelmanager;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación Hotel Manager. Se encarga de iniciar la
 * interfaz gráfica.
 * @author Juana Alexandra Contreras Mendez
 * @author Jorge Armando González Oropeza
 */
public class App extends Application {

    // Escena principal de la aplicación
    private static Scene scene;

    // Ventana principal de la aplicación
    private static Stage stage;

    /**
     * Método principal que inicia JavaFX.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        // Guarda la ventana principal
        stage = primaryStage;

        // Carga la vista login-view.fxml
        scene = new Scene(loadFXML("login-view"));

        // Establece la escena en la ventana
        stage.setScene(scene);

        // Ajusta automáticamente el tamaño
        stage.sizeToScene();

        // Evita redimensionar la ventana
        stage.setResizable(false);

        // Título de la ventana
        stage.setTitle("Inicio de Sesión");

        // Muestra la ventana
        stage.show();
    }

    /**
     * Cambia la vista actual de la aplicación.
     */
    public static void setRoot(String fxml) throws IOException {

        // Cambia el contenido de la escena
        scene.setRoot(loadFXML(fxml));

        // Configuración para pantallas diferentes al login
        if (!fxml.equals("login-view")) {

            // Permite redimensionar la ventana
            stage.setResizable(true);

            // Maximiza la ventana
            stage.setMaximized(true);
        }
    }

    /**
     * Carga un archivo FXML.
     */
    private static Parent loadFXML(String fxml) throws IOException {

        // Crea el cargador del archivo FXML
        FXMLLoader fxmlLoader
                = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        // Retorna la vista cargada
        return fxmlLoader.load();
    }

    /**
     * Método principal del programa.
     */
    public static void main(String[] args) {

        // Inicia la aplicación JavaFX
        launch();
    }

    /**
     * Obtiene la escena principal.
     */
    public static Scene getScene() {
        return scene;
    }

    /**
     * Obtiene la ventana principal.
     */
    public static Stage getStage() {
        return stage;
    }
}
