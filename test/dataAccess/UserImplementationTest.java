/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import exceptions.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Action;
import model.Message;
import model.User;
import model.UserPrivilege;
import model.UserStatus;
import model.Package;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nikol
 */
public class UserImplementationTest {
    
    private final UserImplementation UI = new UserImplementation();
    
    public UserImplementationTest() {
    }

    /**
     * Test of signUp method, of class UserImplementation.
     */
    @Test
    public void testSignUp() {
        try {
            User user = new User(0, "TEST", "test@gmail.com", "TEST", UserStatus.ENABLED, UserPrivilege.USER, "TEST");
            UI.signUp(user);
            assertTrue(true);
        } catch (TimeOutException ex) {
            assertTrue(false);
        } catch (UserAlreadyExistsException ex) {
            assertTrue(false);
        } catch (EmailAlreadyExistsException ex) {
            assertTrue(false);
        }
    }
    
    /**
     * Test of signUp method, of class UserImplementation exception UserAlreadyExistsException.
     */
    @Test (expected = UserAlreadyExistsException.class)
    public void testSignUpUserAlreadyExists() {
        try {
            User user = new User(0, "TEST", "test1@gmail.com", "TEST", UserStatus.ENABLED, UserPrivilege.USER, "TEST");
            UI.signUp(user);
            assertTrue(false);
        } catch (TimeOutException ex) {
            assertTrue(false);
        } catch (UserAlreadyExistsException ex) {
            assertTrue(true);
        } catch (EmailAlreadyExistsException ex) {
            assertTrue(false);
        }
    }
    
    /**
     * Test of signUp method, of class UserImplementation exception EmailAlreadyExistsException.
     */
    @Test (expected = EmailAlreadyExistsException.class)
    public void testSignUpEmailAlreadyExists() throws Exception {
        try {
            User user = new User(0, "TEST1", "test@gmail.com", "TEST", UserStatus.ENABLED, UserPrivilege.USER, "TEST");
            UI.signUp(user);
            assertTrue(false);
        } catch (TimeOutException ex) {
            assertTrue(false);
        } catch (UserAlreadyExistsException ex) {
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
        } catch (TimeOutException ex) {
            assertTrue(false);
        } catch (UserDoesNotExistException ex) {
            assertTrue(false);
        }
    }
    
    /**
     * Test of login method, of class UserImplementation.
     */
    @Test (expected = UserDoesNotExistException.class)
    public void testLoginUserDoesNotExist()  {
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
