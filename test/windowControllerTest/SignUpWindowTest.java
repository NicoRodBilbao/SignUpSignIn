/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package windowControllerTest;

import org.testfx.api.FxToolkit;
import windowController.SignUpWindowController;
import java.util.concurrent.TimeoutException;
import javafx.stage.Stage;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
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
    @Test
    public void test1_InitialStateLogIn() {
        verifyThat("#tfUsername", hasText(""));
        verifyThat("#tfPassword", hasText(""));
        verifyThat("#btnLogIn", isDisabled());
    }
    
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
    
    @Test
    public void test3_buttonLogInEnabled() {
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfPassword");
        write("Prueba");
        verifyThat("#btnLogIn", isEnabled());
    }
    
    @Test
    public void test4_openSignUp() {
        clickOn("#btnSignUp");
    }
    
    @Test
    public void test5_initialStateSignUp() {
         verifyThat("#tfUsername", hasText(""));
        verifyThat("#tfEmail", hasText(""));
        verifyThat("#tfFullName", hasText(""));
        verifyThat("#tfPassword", hasText(""));
        verifyThat("#tfRepeatPassword", hasText(""));
        verifyThat("#btnSignUp", isDisabled());
    }
    
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
    
    @Test
    public void test15_SignUp() {
        clickOn("#btnSignUp");
    }
}
