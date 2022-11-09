/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windowControllerTest;

import java.util.concurrent.TimeoutException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.Assert;
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
 * @author Emil Nuñez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpWindowTest extends ApplicationTest {

    private ImageView btnImgDarkMode;
    private Image lmImg = new Image("ui/sol_light_mode.png");
    private Image dmImg = new Image("ui/sol_dark_mode.png");

    /**
     * Prepare windowController
     *
     * @throws TimeoutException
     */
    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Application.class);
    }

    /**
     * Clear the TestFields
     */
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

    /**
     * Test the window open
     */
    @Test
    public void test1_openSignUp() {
        clickOn("#btnSignUp");
        verifyThat("#paneSignUpWindow", isVisible());
    }

    /**
     * test change of color of the window
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
     * test the initial state of the window, if the fields are all clear
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
     * test if the btnSignUp is disabled
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
     * test if the btnSignUp is enabled
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
     * test if the user exceptions throws
     */
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

    /**
     * test if the email exceptions throws
     */
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

    /**
     * test if the password exceptions throws
     */
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

    /**
     * test if the repeatpassword exceptions throws
     */
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

    /**
     * test if the fullname exception throws
     */
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

    /**
     * test the exception when
     */
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
    
    /**
     * test the exception when
     */
    @Test
    public void testB5_SignUp() {
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
        write("Prueba");
        clickOn("#btnSignUp");
        verifyThat("Aceptar", NodeMatchers.isVisible());

    }

}
