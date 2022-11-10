package windowControllerTest;

import java.util.concurrent.TimeoutException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

import signupsigninclient.Application;

import exceptions.*;

/**
 * This test class tests that the correct functioning of the LogIn window. It
 * tests all possible issues when logging in.
 *
 * @author Emil Nuñez / Nicolás Rodríguez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogInWindowTest extends ApplicationTest {

    private ImageView btnImgDarkMode;
    private Image lmImg = new Image("ui/sol_light_mode.png");
    private Image dmImg = new Image("ui/sol_dark_mode.png");

    private String iuException = new IncorrectUserException().getMessage();
    private String ipException = new IncorrectPasswordException().getMessage();

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

    /**
     * Tests that the window opens initializing it correctly.
     */
    @Test
    public void test1_InitialStateLogIn() {

        verifyThat("#tfUsername", hasText(""));
        verifyThat("#tfPassword", hasText(""));
        verifyThat("#btnLogIn", isDisabled());
    }

    /**
     * Tests the functionality of the dark mode.
     */
    @Test
    public void test2_darkMode() {
        btnImgDarkMode = lookup("#btnImgDarkMode").query();
        clickOn("#btnDarkMode");
        Assert.assertTrue(btnImgDarkMode.getImage().getPixelReader().getArgb(53, 18) == lmImg.getPixelReader().getArgb(53, 18));
        clickOn("#btnDarkMode");
        Assert.assertTrue(btnImgDarkMode.getImage().getPixelReader().getArgb(53, 18) == dmImg.getPixelReader().getArgb(53, 18));
    }

    /**
     * Tests that the login button is disabled.
     */
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

    /**
     * Tests that Log in button is enabled.
     */
    @Test
    public void test4_buttonLogInEnabled() {
        ClearStageLogIn();
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfPassword");
        write("Prueba");
        verifyThat("#btnLogIn", isEnabled());
    }

    /**
     * Tests that IncorrectUsernameException is thrown and caught.
     */
    @Test
    public void test5_UsernameErrorException() {
        ClearStageLogIn();
        clickOn("#tfUsername");
        write("Pru eba");
        clickOn("#tfPassword");
        write("Prueba");
        clickOn("#btnLogIn");
        verifyThat(iuException, isVisible());
        clickOn("Aceptar");
        clickOn("#tfUsername");
        eraseText(10);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#btnLogIn");
        verifyThat(iuException, isVisible());
        clickOn("Aceptar");
    }

    /**
     * Tests that IncorrectPasswordExceptions is thrown and caught.
     */
    @Test
    public void test6_PasswordErrorException() {
        ClearStageLogIn();
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfPassword");
        write("Pru eba");
        clickOn("#btnLogIn");
        verifyThat(ipException, isVisible());
        clickOn("Aceptar");
        clickOn("#tfPassword");
        eraseText(10);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#btnLogIn");
        verifyThat(ipException, isVisible());
        clickOn("Aceptar");
        clickOn("#tfPassword");
        eraseText(20);
    }

    /**
     * Tests that IncorrectPasswordException is thrown and caught when the
     * password doesn't match.
     */
    @Test
    public void test7_LogInErrorException() {
        ClearStageLogIn();
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfPassword");
        write("Contraseña");
        clickOn("#btnLogIn");
        verifyThat(ipException, isVisible());
        clickOn("Aceptar");
    }

    /**
     * Tests the correct functioning of the log in.
     */
    @Test
    public void test8_LogIn() {
        ClearStageLogIn();
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfPassword");
        write("Prueba");
        clickOn("#btnLogIn");
        verifyThat("Welcome Prueba to our application's main page!", isVisible());
        closeCurrentWindow();
    }

}
