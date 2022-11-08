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
 * This test class tests that the functionality of the LogInWindow is correct.
 *
 * @author Emil Nuñez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogInWindowTest extends ApplicationTest {

    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Application.class);
    }

    public void ClearStageLogIn() {
        doubleClickOn("#tfUsername");
        eraseText(1);
        doubleClickOn("#tfPassword");
        eraseText(1);
    }

    //Test de LogIn
    @Test
    public void test1_InitialStateLogIn() {
        verifyThat("#tfUsername", hasText(""));
        verifyThat("#tfPassword", hasText(""));
        verifyThat("#btnLogIn", isDisabled());
    }

//    @Test
//    public void test2_darkMode() {
//        clickOn("#btnDarkMode");
//        verifyThat("#btnImgDarkMode", NodeMatchers.isVisible());
//        clickOn("#btnDarkMode");
//        verifyThat("#btnImgDarkMode", NodeMatchers.isVisible());
//    }
    
    
    //Test de LogIn
    @Test
    public void test3_buttonLogInDisabled() {
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
    public void test4_buttonLogInEnabled() {
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfPassword");
        write("Prueba");
        verifyThat("#btnLogIn", isEnabled());
    }

    //Test de LogIn
    @Test
    public void test5_UsernameErrorException() {
        ClearStageLogIn();
        clickOn("#tfUsername");
        write("Pru eba");
        clickOn("#tfPassword");
        write("Prueba");
        clickOn("#btnLogIn");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        clickOn("#tfUsername");
        eraseText(10);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#btnLogIn");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    //Test de LogIn
    @Test
    public void test6_PasswordErrorException() {
        ClearStageLogIn();
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfPassword");
        write("Pru eba");
        clickOn("#btnLogIn");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        clickOn("#tfPassword");
        eraseText(10);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#btnLogIn");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        clickOn("#tfPassword");
        eraseText(20);
    }

    //Test de LogIn
    @Test
    public void test7_LogInErrorException() {
        ClearStageLogIn();
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfPassword");
        write("Contraseña");
        clickOn("#btnLogIn");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    //Test de LogIn
    @Test
    public void test8_LogIn() {
        ClearStageLogIn();
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfPassword");
        write("Prueba");
        clickOn("#btnLogIn");
        verifyThat("#paneWelcomeWindow", isVisible());
    }

    
    //Test de SignUp y LogIn
    @Test
    public void testB6_SignUp() {
        
        //Sign up the user "prueba"
        clickOn("#tfUsername");
        eraseText(6);
        write("Prueba");
        clickOn("#tfEmail");
        eraseText(20);
        write("prueba@gmail.com");
        clickOn("#tfFullName");
        eraseText(6);
        write("Prueba");
        clickOn("#tfPassword");
        eraseText(6);
        write("Prueba");
        clickOn("#tfRepeatPassword");
        eraseText(6);
        write("Prueba");
        clickOn("#btnSignUp");

        //Go back to log in
        clickOn("#btnGoBack");
        clickOn("#tfUsername");
        eraseText(6);
        write("Prueba");
        clickOn("#tfPassword");
        eraseText(6);
        write("Prueba");
        clickOn("#btnLogIn");
        verifyThat("#paneApplicationWindow", isVisible());
    }
    
    //Test de WelcomeWindow
    //Comprobar que funciona bien
     @Test
    public void testB7_UserLogOut() {
        clickOn("#btnLogOut");
        verifyThat("#paneLogInWindow" , isVisible());
    }
}
