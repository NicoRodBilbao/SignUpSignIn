package factoryTest;

import org.junit.Test;
import static org.junit.Assert.*;

import dataAccess.UserImplementation;
import factories.UserFactory;
import interfaces.Userable;

/**
 * This test class tests that the factory creates an Interface that's an intstance
 * of a data access object.
 *
 * @author Nicolás Rodríguez
 */
public class UserFactoryTest {

    /**
     * Test of getAccessUser method, of class UserFactory.
     */
    @Test
    public void testGetAccessUser() {
        assertTrue(UserFactory.getAccessUser() instanceof UserImplementation);
    }
    
    /**
     * Test of getAccessUser method, of class UserFactory.
     */
    @Test
    public void testGetAccessUserInterface() {
        assertTrue(UserFactory.getAccessUser() instanceof Userable);
    }
}
