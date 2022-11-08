package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import factoryTest.UserFactoryTest;
import dataAccess.UserImplementation;

/**
 * This class executes all the methods of all the test classes in the tests folder.
 * 
 * @author Nicolás Rodríguez
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    UserFactoryTest.class,
    UserImplementation.class})
public class TestSuite {
}
