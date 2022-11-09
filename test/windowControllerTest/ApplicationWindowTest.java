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
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import signupsigninclient.Application;

/**
 *
 * @author Emil Nuñez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationWindowTest extends ApplicationTest {

    private ImageView btnImgDarkMode;
    private Image lmImg = new Image("ui/sol_light_mode.png");
    private Image dmImg = new Image("ui/sol_dark_mode.png");

    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Application.class);
    }

    /**
     * test Application window open
     */
    @Test
    public void test1_OpenApplicationWindow() {
        clickOn("#tfUsername");
        write("Prueba");
        clickOn("#tfPassword");
        write("Prueba");
        clickOn("#btnLogIn");
        verifyThat("#paneApplicationWindow", isVisible());
    }

    /**
     * Test initial state of the window with the "Prueba" user
     */
    @Test
    public void test2_InitialStateLogIn() {
        verifyThat("#btnLogOut", isEnabled());
        verifyThat("#lblWelcome", isVisible());
        verifyThat("Prueba", NodeMatchers.isVisible());
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
     * Test log out button
     */
    @Test
    public void test4_buttonLogOut() {
        clickOn("#btnLogOut");
        verifyThat("#paneLogInWindow", isVisible());
    }
}
