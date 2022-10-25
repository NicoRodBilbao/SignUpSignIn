package windowController;
        
import javafx.application.Application;
import static javafx.application.Application.launch;

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
		btnLogOut.setOnAction(this::handleButtonLogOutAction);
		stage.show();
	}
	
	private void handleWindowShowing(WindowEvent event) {
		String welcomeText = lblWelcome.getText();
		lblWelcome.setText(welcomeText.replace("Username", user.getLogin()));
		btnLogOut.setTooltip(
				new Tooltip("Pulsa para salir"));
	}
	
	private void handleButtonLogOutAction(ActionEvent event) {
		//TODO kill the app
	}

    public void initData(User u) {
        this.user = u;
    }
	
}
