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

    /**
     * Test of signUp method, of class UserImplementation.
     */
    @Test
    public void testSignUp() {
        try {
            User user = new User(0, "Prueba", "prueba@gmail.com", "Prueba", UserStatus.ENABLED, UserPrivilege.USER, "Prueba");
            new UserImplementation().signUp(user);
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
            User user = new User(0, "Prueba1", "prueba1@gmail.com", "TEST", UserStatus.ENABLED, UserPrivilege.USER, "Prueba");
            new UserImplementation().signUp(user);
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
            User user = new User(0, "Prueba1", "prueba@gmail.com", "Prueba", UserStatus.ENABLED, UserPrivilege.USER, "Prueba");
            new UserImplementation().signUp(user);
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
            assertEquals(new UserImplementation().login("Prueba").getLogin(), "Prueba");
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
            assertEquals(new UserImplementation().login("Prueba1").getLogin(), "Prueba");
            assertTrue(false);
        } catch (TimeOutException ex) {
            assertTrue(false);
        } catch (UserDoesNotExistException ex) {
            assertTrue(true);
        }

    }

}
