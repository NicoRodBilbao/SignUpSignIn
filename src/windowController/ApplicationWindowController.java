package windowController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import exceptions.IncorrectUserException;
import model.User;

/**
 * This class is the controller for the ApplicationWindow The window shows a
 * greeting message to the logged in user.
 *
 * @author joana, markel
 */
public class ApplicationWindowController {

    private final Image dmImg = new Image("ui/sol_dark_mode.png");
    private final Image lmImg = new Image("ui/sol_light_mode.png");
    protected final Logger LOGGER = Logger.getLogger(SignUpWindowController.class.getName());

    @FXML
    private Pane paneApplicationWindow;
    @FXML
    private User user;
    @FXML
    private Label lblWelcome;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnDarkMode;
    @FXML
    private ImageView btnImgDarkMode;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * This method initializes the window.
     *
     * @param root There parent of all the children on the scene
     */
    public void initStage(Parent root) {
        LOGGER.info("Initializing the ApplicationWindow");
        try {
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Welcome");
            stage.setResizable(false);
            stage.setOnShowing(this::handleWindowShowing);
            btnLogOut.setOnAction(this::handleLogOutAction);
            btnDarkMode.setOnAction(this::handleDarkModeButtonAction);
            stage.setOnCloseRequest(this::handleExitAction);
            stage.show();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void handleWindowShowing(WindowEvent event) {
        LOGGER.info("Beggining ApplicationWindowController::handleWindowShowing");
        try {
            String welcomeText = lblWelcome.getText();
            lblWelcome.setText(welcomeText.replace("Username", user.getLogin()));
            btnLogOut.setTooltip(
                    new Tooltip("Pulsa para salir"));
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void handleDarkModeButtonAction(ActionEvent event) {
        LOGGER.info("Beggining ApplicationWindowController::handleDarkModeButtonAction");
        try {
            int i = 53, j = 18;
            // Si pulsas el botón, comprueba el tema que tiene la ventana. 
            // En caso de que la imagen sea sol_dark_mode, se cambiará por el sol_light_mode.
            if (btnImgDarkMode.getImage().getPixelReader().getArgb(i, j) == dmImg.getPixelReader().getArgb(i, j)) {
                btnImgDarkMode.setImage(lmImg);
                lblWelcome.setTextFill(Color.BLACK);
                // El fondo cambia el color de #333333, a WHITE.
                paneApplicationWindow.setStyle("-fx-background-color:WHITE");
                btnDarkMode.setStyle("-fx-background-color:WHITE");
            } // En caso de que la imagen sea sol_light_mode, se cambiará por el sol_dark_mode.
            else {
                btnImgDarkMode.setImage(dmImg);
                lblWelcome.setTextFill(Color.WHITE);
                paneApplicationWindow.setStyle("-fx-background-color:#333333");
                btnDarkMode.setStyle("-fx-background-color:#333333");
            }
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void handleLogOutAction(ActionEvent event) {
        LOGGER.info("Beggining ApplicationWindowController::handleLogOutAction");
        try {
            // Carga el document FXML y obtiene un objeto Parent
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/ui/LogInWindow.fxml"));
            Parent root = (Parent) loader.load();

            LogInWindowController controller
                    = (LogInWindowController) loader.getController();
            controller.setStage(stage);
            controller.initStage(root);
        } catch (IOException e) {
            showError(e.getMessage());
        }
    }

    private void handleExitAction(WindowEvent event) {
        LOGGER.info("Beggining ApplicationWindowController::handleExitAction");
        try {
            Platform.exit();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void showError(String msg) {
        LOGGER.log(Level.SEVERE, "ERROR: {0}", msg);
        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK);
    }

    /**
     * This methods allows for passing a user to the window controller, so the
     * their data can be shown.
     *
     * @param u The user that has logged in
     */
    public void initData(User u) {
        LOGGER.info("Beggining ApplicationWindowController::initData");
        try {
            //TODO Parametrizar excepciones
            if (u == null) {
                throw new IncorrectUserException();
            }
            this.user = u;
        } catch (IncorrectUserException e) {
            showError(e.getMessage());
        }
    }

}
