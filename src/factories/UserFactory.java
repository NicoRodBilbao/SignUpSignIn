package factories;

import dataAccess.UserImplementation;
import interfaces.Userable;

/**
 * This class creates an interface Userable and instantiates it as
 * UserImplementation.
 *
 * @author Nicolás Rodríguez
 */
public class UserFactory {

    private static Userable user = new UserImplementation();

    /**
     * This method creates an interface instanced as a Data Access Object.
     *
     * @return interface Userable as UserImplementation
     */
    public static Userable getAccessUser() {
        return user;
    }
}
