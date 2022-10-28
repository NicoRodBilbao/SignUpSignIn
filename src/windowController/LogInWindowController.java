package windowController;

import exceptions.*;
import factories.UserFactory;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.input.KeyEvent;
import model.User;

/**
 *
 * @author Nicolas Rodriguez
 */
public class LogInWindowController{
    Stage primaryStage;
    protected Logger logger = Logger.getLogger(LogInWindowController.class.getName());
    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Button btnLogIn, btnSignUp;
    @FXML
    private Label lblUsername, lblPassword;
    @FXML
    private Separator decorUsername, decorPassword;
            
        
    private User user;
    
    
    public void initStage(Parent root) {
        logger.info("Initializing login stage");
    
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Signin");
        primaryStage.setResizable(false);
        primaryStage.setOnShowing(this::windowShowing);
        tfUsername.focusedProperty().addListener(this::focusChanged);
        tfPassword.focusedProperty().addListener(this::focusChanged);
        tfUsername.textProperty().addListener((event) -> this.textChange(KeyEvent.KEY_TYPED));
        tfPassword.textProperty().addListener((event) -> this.textChange(KeyEvent.KEY_TYPED));
        btnLogIn.setDisable(true);
        primaryStage.show();
        
    }
    public void logIn(ActionEvent event) throws IncorrectPasswordException, UserDoesNotExistException, IncorrectUserException, TimeOutException{
        try {
            if (tfUsername.getText().length() > 30 || tfUsername.getText().contains(" ")){
                decorUsername.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                throw new IncorrectUserException();
            }
            if( tfPassword.getText().length() > 30 || tfPassword.getText().contains(" ")){
                decorPassword.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                throw new IncorrectPasswordException();
            }else{
                User loggedUser;
                loggedUser = UserFactory.getAccessUser().login(tfUsername.getText());
                if(!loggedUser.getPassword().equals(tfPassword.getText())){
                    decorPassword.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                    throw new IncorrectPasswordException();
                }
            }
        }catch(IncorrectUserException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait();
        }catch (IncorrectPasswordException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait();
        
        }/*catch(TimeOutException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }*/catch(Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait();
        }
        
        /*Se validara los campos de usuario y contraseÃ±a
        En caso de que no se valide el campo de usuario con mÃ¡s de 30 caracteres o que haya espacios en blanco, llama al IncorrectUserException
        En caso de que la contraseña no sea vÃ¡lida con mÃ¡s de 30 caracteres o que haya espacios en blanco, llama al IncorrectPasswordException
        En caso de que no valide correctamente
        TimeOutException si tarda en dar una respuesta en 2 segundos
        IncorrectPasswordException si la contraseÃ±a es incorrecta 
        UserDoesNotExistException si el usuario no existe
        El decorUsername se mostrarÃ¡ en rojo en caso de que falle tfUsername
        DecorPassword se mostrarÃ¡ en rojo en caso de que falle tfPassword
        En caso de que valide correctamente, se le cerrara la ventana y abrira la ventana de WelcomeWindow*/
        
    }
    
    
    public void signUp(Stage stage) throws Exception{
        Platform.exit();
        // Carga el document FXML y obtiene un objeto Parent
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/SignUpWindow.fxml"));
        // Crea una escena a partir del Parent
        Parent root = (Parent)loader.load();
        SignUpWindowController controller = (SignUpWindowController) loader.getController();
        // Establece la escena en el escensario (Stage) y la muestra
        //controller.setStage(stage);
        //controller.initStage(root);
    }
    
    
    private void windowShowing(WindowEvent event){
        tfUsername.requestFocus();
        decorUsername.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
        btnLogIn.setDisable(true);
        btnSignUp.setDisable(false);
    }

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }
    
    private void focusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue){
        if(newValue){
            if(tfPassword.isFocused()){
                decorUsername.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
                decorPassword.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        if(oldValue){
            if(tfUsername.isFocused()){
                decorUsername.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
                decorPassword.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }

    private void textChange(EventType<KeyEvent> KEY_TYPED) {
        if(!tfUsername.getText().trim().isEmpty() && !tfPassword.getText().trim().isEmpty()){
            btnLogIn.setDisable(false);
        }
        if(tfUsername.getText().trim().isEmpty() || tfPassword.getText().trim().isEmpty()){
            btnLogIn.setDisable(true);
        }
    }

}
