package factories;

import dataAccess.UserImplementation;
import interfaces.Userable;

/**
 *
 * @author Nicolas Rodriguez
 */
public class UserFactory {

    private static Userable user = new UserImplementation();

    public static Userable getAccessUser() {
        return user;
    }
}
