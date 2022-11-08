package dataAccessTest;

import org.junit.Test;
import static org.junit.Assert.*;

import dataAccess.UserImplementation;

import exceptions.*;
import model.*;

/**
 * This test class tests that the Client side can connect with the Server, thus
 * proving that the data introduced matches the one returned or registers a
 * User.
 *
 * @author Nicolás Rodríguez
 */
public class UserImplementationTest {

    private final UserImplementation UI = new UserImplementation();

    /**
     * Test of signUp method, of class UserImplementation.
     */
    @Test
    public void testSignUp() {
        try {
            User user = new User(0, "TEST", "test@gmail.com", "TEST", UserStatus.ENABLED, UserPrivilege.USER, "TEST");
            UI.signUp(user);
            assertTrue(true);
        } catch (TimeOutException | UserAlreadyExistsException | EmailAlreadyExistsException ex) {
            assertTrue(false);
        }
    }

    /**
     * Test of signUp method, of class UserImplementation exception
     * UserAlreadyExistsException.
     */
    @Test(expected = UserAlreadyExistsException.class)
    public void testSignUpUserAlreadyExists() {
        try {
            User user = new User(0, "TEST", "test1@gmail.com", "TEST", UserStatus.ENABLED, UserPrivilege.USER, "TEST");
            UI.signUp(user);
            assertTrue(false);
        } catch (TimeOutException | EmailAlreadyExistsException ex) {
            assertTrue(false);
        } catch (UserAlreadyExistsException ex) {
            assertTrue(true);
        }
    }

    /**
     * Test of signUp method, of class UserImplementation exception
     * EmailAlreadyExistsException.
     */
    @Test(expected = EmailAlreadyExistsException.class)
    public void testSignUpEmailAlreadyExists() throws Exception {
        try {
            User user = new User(0, "TEST1", "test@gmail.com", "TEST", UserStatus.ENABLED, UserPrivilege.USER, "TEST");
            UI.signUp(user);
            assertTrue(false);
        } catch (TimeOutException | UserAlreadyExistsException ex) {
            assertTrue(false);
        } catch (EmailAlreadyExistsException ex) {
            assertTrue(true);
        }
    }

    /**
     * Test of login method, of class UserImplementation.
     */
    @Test
    public void testLogin() {
        try {
            assertEquals(UI.login("TEST").getLogin(), "TEST");
            assertTrue(true);
        } catch (TimeOutException | UserDoesNotExistException ex) {
            assertTrue(false);
        }
    }

    /**
     * Test of login method, of class UserImplementation.
     */
    @Test(expected = UserDoesNotExistException.class)
    public void testLoginUserDoesNotExist() {
        try {
            assertEquals(UI.login("TEST1").getLogin(), "TEST");
            assertTrue(false);
        } catch (TimeOutException ex) {
            assertTrue(false);
        } catch (UserDoesNotExistException ex) {
            assertTrue(true);
        }

    }

}
