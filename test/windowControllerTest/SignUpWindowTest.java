/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windowControllerTest;

import java.util.concurrent.TimeoutException;
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

    public void ClearStageSignUp() {
        doubleClickOn("#tfUsername");
        eraseText(1);
        for (int i = 0; i < 2; i++) {
            doubleClickOn("#tfEmail");
            eraseText(1);
        }
        doubleClickOn("#tfFullName");
        eraseText(1);
        doubleClickOn("#tfPassword");
        eraseText(1);
        doubleClickOn("#tfRepeatPassword");
        eraseText(1);
    }

    //Test de SignUp
    @Test
    public void test1_openSignUp() {
        clickOn("#btnSignUp");
        verifyThat("#paneSignUpWindow", isVisible());
    }

//    @Test
//    public void test2_darkMode() {
//        clickOn("#btnDarkMode");
//        verifyThat("#btnImgDarkMode", NodeMatchers.isVisible());
//        clickOn("#btnDarkMode");
//        verifyThat("#btnImgDarkMode", NodeMatchers.isVisible());
//    }

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
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        clickOn("#tfUsername");
        eraseText(10);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#btnSignUp");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }
    
    
    //Test de SignUp
    @Test
    public void test9_EmailErrorException() {
        ClearStageSignUp();
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfEmail");
        write("Fallo");
        clickOn("#tfFullName");
        write("Prueba");
        clickOn("#tfPassword");
        write("Prueba");
        clickOn("#tfRepeatPassword");
        write("Prueba");
        clickOn("#btnSignUp");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    //Test de SignUp
    @Test
    public void testB1_PasswordErrorException() {
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
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        clickOn("#tfPassword");
        eraseText(10);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#btnSignUp");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    //Test de SignUp
    @Test
    public void testB2_RepeatPasswordErrorException() {
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
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        clickOn("#tfRepeatPassword");
        eraseText(10);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#btnSignUp");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    //Test de SignUp
    @Test
    public void testB3_FullNameErrorException() {
        ClearStageSignUp();
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfEmail");
        write("prueba@gmail.com");
        clickOn("#tfFullName");
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#tfPassword");
        write("Prueba");
        clickOn("#tfRepeatPassword");
        write("Prueba");
        clickOn("#btnSignUp");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    //Test de SignUp
    @Test
    public void testB4_PasswordDoesntMatchException() {
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
        write("Aceptar");
        clickOn("#btnSignUp");
        verifyThat("Aceptar", NodeMatchers.isVisible());
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
