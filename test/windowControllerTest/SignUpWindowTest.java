/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package windowControllerTest;

import org.testfx.api.FxToolkit;
import windowController.SignUpWindowController;
import java.util.concurrent.TimeoutException;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import signupsigninclient.Application;
import windowController.LogInWindowController;

/**
 *
 * @author Nicolas Rodriguez
*/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)   
public class SignUpWindowTest extends ApplicationTest {
    
    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Application.class);    
   }
    
     public void ClearStageLogIn() {
        clickOn("#tfUsername");
        eraseText(20);
        clickOn("#tfPassword");
        eraseText(20);
        clickOn("#btnLogIn");
        eraseText(20);
        
    }
    
    public void ClearStageSignUp() {
        clickOn("#tfUsername");
        eraseText(20);
        clickOn("#tfEmail");
        eraseText(20);
        clickOn("#tfFullName");
        eraseText(20);
        clickOn("#tfPassword");
        eraseText(20);
        clickOn("#tfRepeatPassword");
        eraseText(20);
        clickOn("#btnSignUp");
        eraseText(20);
    }
    
    
    //Test de LogIn
    @Test
    public void test1_InitialStateLogIn() {
        verifyThat("#tfUsername", hasText(""));
        verifyThat("#tfPassword", hasText(""));
        verifyThat("#btnLogIn", isDisabled());
    }
    
    //Test de LogIn
    @Test
    public void test2_buttonLogInDisabled() {
        verifyThat("#btnLogIn", isDisabled());
        clickOn("#tfUsername");
        write("Prueba");
        verifyThat("#btnLogIn", isDisabled());
        eraseText(6);
        clickOn("#tfPassword");
        write("Prueba");
        verifyThat("#btnLogIn", isDisabled());
        eraseText(6);
    }
    
    //Test de LogIn
    @Test
    public void test3_buttonLogInEnabled() {
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfPassword");
        write("Prueba");
        verifyThat("#btnLogIn", isEnabled());
    }
    
    //Test de SignUp
    @Test
    public void test4_openSignUp() {
        clickOn("#btnSignUp");
        verifyThat("#paneSignUpWindow" , isVisible());
    }
    
    //Test de SignUp
    @Test
    public void test5_initialStateSignUp() {
        verifyThat("#tfUsername", hasText(""));
        verifyThat("#tfEmail", hasText(""));
        verifyThat("#tfFullName", hasText(""));
        verifyThat("#tfPassword", hasText(""));
        verifyThat("#tfRepeatPassword", hasText(""));
        verifyThat("#btnSignUp", isDisabled());
    }
    
     //Test de SignUp
    @Test
    public void test6_SignInDisabled() {
        clickOn("#tfUsername");
        write("Prueba");
        verifyThat("#btnSignUp", isDisabled());
        eraseText(6);
        clickOn("#tfEmail");
        write("prueba@gmail.com");
        verifyThat("#btnSignUp", isDisabled());
        eraseText(16);
        clickOn("#tfFullName");
        write("Prueba");
        verifyThat("#btnSignUp", isDisabled());
        eraseText(6);
        clickOn("#tfPassword");
        write("Prueba");
        verifyThat("#btnSignUp", isDisabled());
        eraseText(6);
        clickOn("#tfRepeatPassword");
        write("Prueba");
        verifyThat("#btnSignUp", isDisabled());
        eraseText(6);
    }
    
     //Test de SignUp
    @Test
    public void test7_SignInEnabled() {
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfEmail");
        write("prueba@gmail.com");
        clickOn("#tfFullName");
        write("Prueba");
        clickOn("#tfPassword");
        write("Prueba");
        clickOn("#tfRepeatPassword");
        write("Prueba");
        verifyThat("#btnSignUp", isEnabled());
    }
    
    //Test de SignUp
     @Test
    public void test8_UserErrorException() {
        ClearStageSignUp();
        clickOn("#tfUsername");
        write("Pru eba");
        clickOn("#tfEmail");
        write("prueba@gmail.com");
        clickOn("#tfFullName");
        write("Prueba");
        clickOn("#tfPassword");
        write("Prueba");
        clickOn("#tfRepeatPassword");
        write("Prueba");
        clickOn("#btnSignUp");
        verifyThat("Aceptar" , NodeMatchers.isVisible());
        clickOn("Aceptar");
        clickOn("#tfUsername");
        eraseText(30);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#btnSignUp");
        verifyThat("Aceptar" , NodeMatchers.isVisible());
        clickOn("Aceptar");
    }
    
    //Test de SignUp
    @Test
    public void test9_PasswordErrorException() {
        ClearStageSignUp();
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfEmail");
        write("prueba@gmail.com");
        clickOn("#tfFullName");
        write("Prueba");
        clickOn("#tfPassword");
        write("Pru eba");
        clickOn("#tfRepeatPassword");
        write("Prueba");
        clickOn("#btnSignUp");
        verifyThat("Aceptar" , NodeMatchers.isVisible());
        clickOn("Aceptar");
        clickOn("#tfPassword");
        eraseText(30);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#btnSignUp");
        verifyThat("Aceptar" , NodeMatchers.isVisible());
        clickOn("Aceptar");
    }
    
    //Test de SignUp
    @Test
    public void testB1_RepeatPasswordErrorException() {
        ClearStageSignUp();
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfEmail");
        write("prueba@gmail.com");
        clickOn("#tfFullName");
        write("Prueba");
        clickOn("#tfPassword");
        write("Prueba");
        clickOn("#tfRepeatPassword");
        write("Pr ueba");
        clickOn("#btnSignUp");
        verifyThat("Aceptar" , NodeMatchers.isVisible());
        clickOn("Aceptar");
        clickOn("#tfRepeatPassword");
        eraseText(30);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#btnSignUp");
        verifyThat("Aceptar" , NodeMatchers.isVisible());
        clickOn("Aceptar");
    }
    
    //Test de SignUp
    @Test
    public void testB2_FullNameErrorException() {
        ClearStageSignUp();
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfEmail");
        write("prueba@gmail.com");
        clickOn("#tfFullName");
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#tfPassword");
        write("Prueba");
        clickOn("#tfRepeatPassword");
        write("Prueba");
        clickOn("#btnSignUp");
        verifyThat("Aceptar" , NodeMatchers.isVisible());
        clickOn("Aceptar");
    }
    
    //Test de LogIn
    @Test
    public void testB3_LogInUsernameErrorException() {
        ClearStageLogIn();
        clickOn("#tfUsername");
        write("Pru eba");
        clickOn("#tfPassword");
        write("Prueba");
        clickOn("#btnLogIn");
        verifyThat("Aceptar" , NodeMatchers.isVisible());
        clickOn("Aceptar");
        clickOn("#tfUsername");
        eraseText(10);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#btnLogIn");
        verifyThat("Aceptar" , NodeMatchers.isVisible());
        clickOn("Aceptar");
    }
    
    //Test de LogIn
    @Test
    public void testB4_LogInPasswordErrorException() {
        ClearStageLogIn();
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfPassword");
        write("Pru eba");
        clickOn("#btnLogIn");
        verifyThat("Aceptar" , NodeMatchers.isVisible());
        clickOn("Aceptar");
        clickOn("#tfPassword");
        eraseText(10);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#btnLogIn");
        verifyThat("Aceptar" , NodeMatchers.isVisible());
        clickOn("Aceptar");
    }
    
    //Test de LogIn
     @Test
    public void testB5_LogInErrorException() {
        ClearStageLogIn();
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfPassword");
        write("Contraseña");
        clickOn("#btnLogIn");
        verifyThat("Aceptar" , NodeMatchers.isVisible());
        clickOn("Aceptar");
    }
    
//    //Test de SignUp y LogIn
//    //Comprobar que funciona bien
//    @Test
//    public void testB6_SignUp() {
//        
//        //Sign up the user "prueba"
//        clickOn("#tfUsername");
//        eraseText(6);
//        write("Prueba");
//        clickOn("#tfEmail");
//        eraseText(20);
//        write("prueba@gmail.com");
//        clickOn("#tfFullName");
//        eraseText(6);
//        write("Prueba");
//        clickOn("#tfPassword");
//        eraseText(6);
//        write("Prueba");
//        clickOn("#tfRepeatPassword");
//        eraseText(6);
//        write("Prueba");
//        clickOn("#btnSignUp");
//
//        //Go back to log in
//        clickOn("#btnGoBack");
//        clickOn("#tfUsername");
//        eraseText(6);
//        write("Prueba");
//        clickOn("#tfPassword");
//        eraseText(6);
//        write("Prueba");
//        clickOn("#btnLogIn");
//        verifyThat("#paneApplicationWindow", isVisible());
//        
//    }
//    
//    //Test de WelcomeWindow
//    //Comprobar que funciona bien
//     @Test
//    public void testB7_UserLogOut() {
//        clickOn("#btnLogOut");
//        verifyThat("#paneLogInWindow" , isVisible());
//    }

}
