package signupsigninclient;

import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author Nicolas Rodriguez
 */
public class Application extends javafx.application.Application {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
         // Carga el document FXML y obtiene un objeto Parent
        Parent root = FXMLLoader.load(getClass().getResource("ui.LogIn.fxml"));
        // Crea una escena a partir del Parent
        Scene scene = new Scene(root);
        // Establece la escena en el escensario (Stage) y la muestra
        stage.setScene(scene);
        stage.show();
    }
}
