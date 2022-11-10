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
 * This test class tests the correct functioning of the SignUp Window. It tests
 * all possible issues when signing up.
 *
 * @author Emil Nuñez / Nicolás Rodríguez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpWindowTest extends ApplicationTest {

    public ImageView btnImgDarkMode;
    public Image lmImg = new Image("ui/sol_light_mode.png");
    public Image dmImg = new Image("ui/sol_dark_mode.png");
    public String iuException = new IncorrectUserException().getMessage();
    public String ieException = new IncorrectEmailException().getMessage();
    public String ipException = new IncorrectPasswordException().getMessage();
    public String ifnException = new IncorrectFullNameException().getMessage();
    public String pdmException = new PasswordDoesntMatchException().getMessage();
    public String correctSignIn = "The user has been successfully registered.";

    /**
     * this method sets up the window controller.
     *
     * @throws TimeoutException
     */
    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Application.class);
    }

    /**
     * This method clears all the TestFields.
     */
    public void clearStageSignUp() {
        doubleClickOn("#tfUsername");
        eraseText(1);
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

    /**
     * Tests that the window opens.
     */
    @Test
    public void test1_openSignUp() {
        clickOn("#btnSignUp");
        verifyThat("#paneSignUpWindow", isVisible());
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
     * Tests the initial state of the window.
     */
    @Test
    public void test5_initialStateSignUp() {
        verifyThat("#tfUsername", hasText(""));
        verifyThat("#tfEmail", hasText(""));
        verifyThat("#tfFullName", hasText(""));
        verifyThat("#tfPassword", hasText(""));
        verifyThat("#tfRepeatPassword", hasText(""));
        verifyThat("#btnSignUp", isDisabled());
    }

    /**
     * Tests that the btnSignUp is disabled.
     */
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

    /**
     * Tests that the btnSignUp is enabled.
     */
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

    /**
     * Tests that IncorrectUserException is thrown and caught.
     */
    @Test
    public void test8_IncorrectUserException() {
        clearStageSignUp();
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
        verifyThat(iuException, isVisible());

        clickOn("Aceptar");
        clickOn("#tfUsername");
        eraseText(10);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#btnSignUp");
        verifyThat(iuException, isVisible());

        clickOn("Aceptar");
    }

    /**
     * Tests that IncorrectEmailException is thrown and caught.
     */
    @Test
    public void test9_IncorrectEmailException() {
        clearStageSignUp();
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
        verifyThat(ieException, isVisible());

        clickOn("Aceptar");
    }

    /**
     * Tests that IncorrectPasswordException is thrown and caught.
     */
    @Test
    public void testB1_IncorrectPasswordException() {
        clearStageSignUp();
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
        verifyThat(ipException, isVisible());

        clickOn("Aceptar");
        clickOn("#tfPassword");
        eraseText(10);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#btnSignUp");
        verifyThat(ipException, isVisible());

        clickOn("Aceptar");
    }

    /**
     * Tests that IncorrectPasswordException on Repeat Password is thrown and
     * caught.
     */
    @Test
    public void testB2_RepeatPasswordException() {
        clearStageSignUp();
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
        verifyThat(ipException, isVisible());

        clickOn("Aceptar");
        clickOn("#tfRepeatPassword");
        eraseText(10);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        clickOn("#btnSignUp");
        verifyThat(ipException, isVisible());

        clickOn("Aceptar");
    }

    /**
     * Tests that FullNameErrorException is thrown and caught.
     */
    @Test
    public void testB3_FullNameErrorException() {
        clearStageSignUp();
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
        verifyThat(ifnException, isVisible());

        clickOn("Aceptar");
    }

    /**
     * Tests that PasswordDoesntMatchException is thrown and caught.
     */
    @Test
    public void testB4_PasswordDoesntMatchException() {
        clearStageSignUp();
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
        verifyThat(pdmException, isVisible());
    }

    /**
     * Tests that there are no exceptions when siging up.
     */
    @Test
    public void testB5_SignUp() {
        clearStageSignUp();
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
        clickOn("#btnSignUp");
        verifyThat(correctSignIn, isVisible());
    }

}
