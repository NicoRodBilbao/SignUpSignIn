package windowController;

import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import model.User;

/**
 *
 * @author joana, markel
 */
public class ApplicationWindowController extends Application {
	
	private User user;
	private Label lblWelcome;
	private Button btnLogOut;
    
	public ApplicationWindowController(User user) {
		this.user = user;
	}
	
	public void initStage(Parent root) {
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Welcome");
		stage.setResizable(false);
		stage.setOnShowing(this::setUsername);
		btnLogOut.setOnAction(this::logOut);
		stage.show();
	}
	
	private void setUsername() {
		String welcomeText = lblWelcome.getText();
		lblWelcome.setText(welcomeText.replace("Username", user.getLogin()));
	}
	
	private void logOut() {
		//TODO kill the app
	}
	
    @Override
    public void start(Stage primaryStage) {
    	FXMLLoader loader = new FXMLLoader(
    			getClass.getResource("ui/ApplicationWindow"));
    	Parent root = (Parent) loader.load();
    	
    }
    
}
