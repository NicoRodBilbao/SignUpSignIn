package signupsigninclient;

import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import windowController.LogInWindowController;

/**
 * This is the main class of the application, it executes the first window.
 *
 * @author Nicolas Rodriguez
 */
public class Application extends javafx.application.Application {

    /**
     * The main method of the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method initiates the first window LogInWindow, loading its
     * controller.
     *
     * @param stage the main stage of the application, it will contain
     * LogInWindow
     * @throws Exception any possible uncaught exception the application throws
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Carga el document FXML y obtiene un objeto Parent
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/LogInWindow.fxml"));
        // Crea una escena a partir del Parent
        Parent root = (Parent) loader.load();
        LogInWindowController controller = (LogInWindowController) loader.getController();
        // Establece la escena en el escensario (Stage) y la muestra
        controller.setStage(stage);
        controller.initStage(root);
    }
}
