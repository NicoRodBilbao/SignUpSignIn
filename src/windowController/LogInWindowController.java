
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.User;

/**
 *
 * @author Markel Fernandez, Joana
 */
public class LogInWindowController{
    Stage primaryStage;
    protected Logger logger = Logger.getLogger(LogInWindowController.class.getName());
    
    private final Image dmImg = new Image("ui/sol_dark_mode.png");
    private final Image lmImg = new Image("ui/sol_light_mode.png");
    @FXML
    private Pane paneMain;
    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Button btnLogIn, btnSignUp, btnDarkMode;
    @FXML
    private Label lblUsername, lblPassword, lblTitle;
    @FXML
    private Separator decorUsername, decorPassword;
    @FXML
    private ImageView btnImgDarkMode;
            
        
    private User user;
    @FXML
    private Rectangle decoLogo;
    @FXML
    private ImageView imgLogo;
    
    
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
            //En caso de que no se valide el campo de usuario con más de 30 caracteres o que haya espacios en blanco, llama al IncorrectUserException
            // El decorUsername se mostrará en rojo en caso de que falle tfUsername
            if (tfUsername.getText().length() > 30 || tfUsername.getText().contains(" ")){
                decorUsername.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                throw new IncorrectUserException();
            }
            //En caso de que la contrase�a no sea válida con más de 30 caracteres o que haya espacios en blanco, llama al IncorrectPasswordException
            //DecorPassword se mostrará en rojo en caso de que falle tfPassword
            if( tfPassword.getText().length() > 30 || tfPassword.getText().contains(" ")){
                decorPassword.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                throw new IncorrectPasswordException();
            }else{
                user = UserFactory.getAccessUser().login(tfUsername.getText());
                if(!user.getPassword().equals(tfPassword.getText())){
                    decorPassword.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                    throw new IncorrectPasswordException();
                }else{
                    primaryStage.close();
                    Stage stage = new Stage();
                    // Carga el document FXML y obtiene un objeto Parent
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/ApplicationWindow.fxml"));
                    // Crea una escena a partir del Parent
                    Parent root = (Parent)loader.load();
                    ApplicationWindowController controller = (ApplicationWindowController) loader.getController();
                    // Establece la escena en el escensario (Stage) y la muestra
                    controller.setStage(stage);
                    controller.initData(user);
                    controller.initStage(root);
                }
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait();
        }
        
    }
    
    
    public void signUp() throws Exception{
        try{
            Stage stage = new Stage();
            primaryStage.close();
            // Carga el document FXML y obtiene un objeto Parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/SignUpWindow.fxml"));
            // Crea una escena a partir del Parent
            Parent root = (Parent)loader.load();
            SignUpWindowController controller = (SignUpWindowController) loader.getController();
            // Establece la escena en el escensario (Stage) y la muestra
            controller.setStage(stage);
            controller.initStage(root);
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait();
        }
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
    @FXML
    /**
     * This method changes atributes of the window in order to change the visual
     * theme
     *
     * @param event The observed event
     */
    private void handleDarkModeButtonAction(ActionEvent event) {
        int i = 53, j = 18;
        // Si pulsas el bot�n, comprueba el tema que tiene la ventana. 
        // En caso de que la imagen sea sol_dark_mode, se cambiar� por el sol_light_mode.
        if (btnImgDarkMode.getImage().getPixelReader().getArgb(i, j) == dmImg.getPixelReader().getArgb(i, j)) {
            btnImgDarkMode.setImage(lmImg);
            // Los background de los tfUsername, tfEmail, tfFullName, tfPassword y tfRepeatPassword cambiar�n de #3A3A3A a #DDDDDD
            tfUsername.setBackground(new Background(new BackgroundFill(Color.valueOf("#DDDDDD"), CornerRadii.EMPTY, Insets.EMPTY)));
            tfPassword.setBackground(new Background(new BackgroundFill(Color.valueOf("#DDDDDD"), CornerRadii.EMPTY, Insets.EMPTY)));
            // y la letra pasar� de WHITE a BLACK.
            tfUsername.setStyle("-fx-text-inner-color:BLACK");
            tfPassword.setStyle("-fx-text-inner-color:BLACK");
            // Las label lblUsername, lblEmail, lblFullName, lblPassword, 
            //lblRepeatPassword cambiaran de color de la letra de WHITE a BLACK.
            lblUsername.setTextFill(Color.BLACK);
            lblPassword.setTextFill(Color.BLACK);
            lblTitle.setTextFill(Color.BLACK);
            // El fondo cambia el color de #333333, a WHITE.
            paneMain.setStyle("-fx-background-color:WHITE");
            btnDarkMode.setStyle("-fx-background-color:WHITE");
        } // En caso de que la imagen sea sol_light_mode, se cambiar� por el sol_dark_mode.
        else {
            btnImgDarkMode.setImage(dmImg);
            // En caso contrario, los background de los tfUsername, tfEmail, tfFullName, tfPassword y tfRepeatPassword cambiar�n de #DDDDDD a #3A3A3A
            tfUsername.setBackground(new Background(new BackgroundFill(Color.valueOf("#3A3A3A"), CornerRadii.EMPTY, Insets.EMPTY)));
            tfPassword.setBackground(new Background(new BackgroundFill(Color.valueOf("#3A3A3A"), CornerRadii.EMPTY, Insets.EMPTY)));
            // la letra pasar� de BLACK a WHITE
            tfUsername.setStyle("-fx-text-inner-color:WHITE");
            tfPassword.setStyle("-fx-text-inner-color:WHITE");
            // En caso contrario, las label lblUsername, lblEmail, lblFullName, 
            // lblPassword, lblRepeatPassword cambiaran de color de la letra de BLACK a WHITE.
            lblUsername.setTextFill(Color.WHITE);
            lblPassword.setTextFill(Color.WHITE);
            lblTitle.setTextFill(Color.WHITE);
            // En caso contrario, pasar� a #333333
            paneMain.setStyle("-fx-background-color:#333333");
            btnDarkMode.setStyle("-fx-background-color:#333333");
        }
    }
}

