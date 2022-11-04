package factoryTest;

import dataAccess.UserImplementation;
import factories.UserFactory;
import interfaces.Userable;
import org.junit.Test;
import static org.junit.Assert.*;

/**
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
