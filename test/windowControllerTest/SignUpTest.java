package windowControllerTest;

import java.util.concurrent.TimeoutException;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

import signupsigninclient.Application;

/**
 * This class is an integrated test that goes through the whole program without
 * any thrown exceptions.
 *
 * @author Emil Nuñez / Nicolás Rodríguez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpTest extends ApplicationTest {

    /**
     * Prepares the windowController for the tests to begin.
     *
     * @throws TimeoutException
     */
    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Application.class);
    }

    /**
     * Test that the sign up and login works with a user "Prueba".
     */
    @Test
    public void test1_SignUp() {
        // Sign Up
        clickOn("#btnSignUp");
        clickOn("#tfUsername");
        write("PruebaGUI");
        clickOn("#tfEmail");
        write("pruebagui@gmail.com");
        clickOn("#tfFullName");
        write("Prueba");
        clickOn("#tfPassword");
        write("Prueba");
        clickOn("#tfRepeatPassword");
        write("Prueba");
        clickOn("#btnSignUp");
        clickOn("Aceptar");
        // Go back to log in
        clickOn("#btnGoBack");
        // Log in
        clickOn("#tfUsername");
        write("PruebaGUI");
        clickOn("#tfPassword");
        write("Prueba");
        clickOn("#btnLogIn");
        // Verification
        verifyThat("#paneApplicationWindow", isVisible());
        closeCurrentWindow();
    }

}
