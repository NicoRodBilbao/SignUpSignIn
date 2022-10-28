package windowController;
        
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import model.User;

/**
 *
 * @author joana, markel
 */
public class ApplicationWindowController {

	@FXML
	private User user;
	@FXML
	private Label lblWelcome;
	@FXML
	private Button btnLogOut;
        @FXML
        private Button btnDarkMode;
	
	private Stage stage;
	
	public void setStage(Stage stage) {
	    this.stage = stage;
	}
	
	public void initStage(Parent root) {
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Welcome");
		stage.setResizable(false);
		stage.setOnShowing(this::handleWindowShowing);
		btnLogOut.setOnAction(this::handleLogOutAction);
                btnDarkMode.setOnAction(this::toggleTheme);
                stage.setOnCloseRequest(this::handleExitAction);
		stage.show();
	}
	
	private void handleWindowShowing(WindowEvent event) {
		String welcomeText = lblWelcome.getText();
		lblWelcome.setText(welcomeText.replace("Username", user.getLogin()));
		btnLogOut.setTooltip(
				new Tooltip("Pulsa para salir"));
	}
        
        private void toggleTheme(ActionEvent event) {
                //TODO
        }
        
        private void handleLogOutAction(ActionEvent event) {
                //TODO
        }
	
	private void handleExitAction(WindowEvent event) {
		Platform.exit();
	}

        public void initData(User u) {
                this.user = u;
        }
	
}
