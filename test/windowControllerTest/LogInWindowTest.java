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
public class LogInWindowTest extends ApplicationTest {

    private ImageView btnImgDarkMode;
    private Image lmImg = new Image("ui/sol_light_mode.png");
    private Image dmImg = new Image("ui/sol_dark_mode.png");
   
    
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
     * Initial stage of the window
     */
    @Test
    public void test1_InitialStateLogIn() {
        
        verifyThat("#tfUsername", hasText(""));
        verifyThat("#tfPassword", hasText(""));
        verifyThat("#btnLogIn", isDisabled());
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
     * test the button login is disabled
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
     * test the button login is enabled
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
     * test the errors of tfUsername
     */
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

    /**
     * test the errors of tfPassword
     */
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

    /**
     * test the error of the Login
     */
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

    /**
     * test the Login
     */
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

}
