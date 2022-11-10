package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import factoryTest.UserFactoryTest;
import windowControllerTest.*;

/**
 * This class executes all the methods of all the test classes in the tests
 * folder.
 *
 * @author Nicolás Rodríguez
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    UserFactoryTest.class,
    SignUpWindowTest.class,
    LogInWindowTest.class,
    ApplicationWindowTest.class,
    SignUpTest.class}) // UserImplementation cannot be tested on the suite due to use of OrderedRunner
public class TestSuite {
}
