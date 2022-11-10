package dataAccessTest;

import org.junit.Test;
import static org.junit.Assert.*;

import dataAccess.UserImplementation;

import exceptions.*;
import model.*;
import org.junit.runner.RunWith;
import suite.Order;
import suite.OrderedRunner;

/**
 * This test class tests that the Client side can connect with the Server, thus
 * proving that the data introduced matches the one returned or registers a
 * User.
 *
 * @author Nicolás Rodríguez
 */
@RunWith(OrderedRunner.class)
public class UserImplementationTest {

    /**
     * Test of signUp method, of class UserImplementation.
     */
    @Order (order=1)
    @Test
    public void testSignUp() {
        try {
            User user = new User(0, "PruebaUI", "pruebaui@gmail.com", "Prueba", UserStatus.ENABLED, UserPrivilege.USER, "Prueba1");
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
        @Order (order=2)
        @Test
    public void testSignUpUserAlreadyExists() {
        try {
            User user = new User(0, "PruebaUI", "pruebaui1@gmail.com", "TEST", UserStatus.ENABLED, UserPrivilege.USER, "Prueba2");
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
    @Test
        @Order (order=3)
    public void testSignUpEmailAlreadyExists() {
        try {
            User user = new User(0, "PruebaUI1", "pruebaui@gmail.com", "Prueba", UserStatus.ENABLED, UserPrivilege.USER, "Prueba3");
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
    
        @Order (order=4)
        @Test
    public void testLogin() {
        try {
            assertEquals(new UserImplementation().login("PruebaUI").getLogin(), "PruebaUI");
            assertTrue(true);
        } catch (TimeOutException | UserDoesNotExistException ex) {
            assertTrue(false);
        }
    }

    /**
     * Test of login method, of class UserImplementation.
     */
    
        @Order (order=5)
        @Test
    public void testLoginUserDoesNotExist() {
        try {
            assertEquals(new UserImplementation().login("PruebaUI1").getLogin(), "PruebaUI");
            assertTrue(false);
        } catch (TimeOutException ex) {
            assertTrue(false);
        } catch (UserDoesNotExistException ex) {
            assertTrue(true);
        }

    }

}
