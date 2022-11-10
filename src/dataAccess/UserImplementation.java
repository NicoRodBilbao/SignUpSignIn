package dataAccess;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.logging.Logger;
import java.util.ResourceBundle;

import model.*;
import model.Package; // If not specified Package is redundant (java.lang.Package)
import exceptions.*;

import interfaces.Userable;

/**
 * This class connects with a server and can either sign up a user or sign in as
 * a user.
 *
 * @author Nicolás Rodríguez
 */
public class UserImplementation implements Userable {

    protected ResourceBundle configFile = ResourceBundle.getBundle("dataAccess.config");

    protected Integer PORT = Integer.parseInt(configFile.getString("PORT")); // Take the port from the config file
    protected String HOST = (configFile.getString("HOST")); // Take the host to connect from the config file
    private Socket skClient;
    protected static final Logger LOGGER = Logger.getLogger(UserImplementation.class.getName());

    /**
     * This method sends a request to search for a User by their username to a
     * HOST specified through a PORT specified.
     *
     * @param username The username of the User we are looking for
     * @return returns the object User if found
     * @throws exceptions.TimeOutException Server doesn't respond
     * @throws exceptions.UserDoesNotExistException User couldn't be found
     */
    @Override
    public User login(String username) throws TimeOutException, UserDoesNotExistException {
        LOGGER.info("Initializing signUp.");
        try {
            // Creating the variables necessary for the connection with the server
            skClient = new Socket(HOST, PORT);
            InputStream input = skClient.getInputStream();
            OutputStream output = skClient.getOutputStream();
            ObjectInputStream auxIn = new ObjectInputStream(input);
            ObjectOutputStream auxOut = new ObjectOutputStream(output);
            // Creating a Package to send to the server
            User user = new User();
            user.setLogin(username);
            Package pack = new Package(user, Action.LOGIN, Message.OK);
            // Sends the object to the server
            auxOut.writeObject(pack);
            // We get an answer in case we get any exception
            pack = (Package) auxIn.readObject();
            skClient.close();
            if (pack.getMessage().equals(Message.USERDOESNOTEXIST)) { // User does not exist
                throw new UserDoesNotExistException();
            }
            return pack.getUser();
        } catch (ConnectException e) { // Connection time out
            throw new TimeOutException();
        } catch (IOException e) {
            LOGGER.severe("Input Output exception thrown." + e.getMessage() + e.getClass().getName());
        } catch (ClassNotFoundException e) {
            LOGGER.severe("The class was not found." + e.getMessage());
        }
        return null;
    }

    /**
     * This method sends a request to register a User to a HOST specified
     * through a PORT specified.
     *
     * @param user The user that will be sent to be singed up
     * @throws exceptions.TimeOutException Server doesn't respond
     * @throws exceptions.UserAlreadyExistsException User cannot be registered
     * @throws exceptions.EmailAlreadyExistsException Email cannot be registered
     */
    @Override
    public void signUp(User user) throws TimeOutException, UserAlreadyExistsException, EmailAlreadyExistsException {
        LOGGER.info("Initializing signUp.");
        try {
            // Creating the vatiables necessary for the connection with the server
            skClient = new Socket(HOST, PORT);
            InputStream input = skClient.getInputStream();
            OutputStream output = skClient.getOutputStream();
            ObjectInputStream auxIn = new ObjectInputStream(input);
            ObjectOutputStream auxOut = new ObjectOutputStream(output);
            // Creating a Package to send to the server
            Package pack = new Package(user, Action.REGISTER, Message.OK);
            // Sends the object to the server
            auxOut.writeObject(pack);
            // We get an answer in case we get any exception
            pack = (Package) auxIn.readObject();
            skClient.close();
            if (pack.getMessage().equals(Message.USERALREADYEXISTS)) { // User already exists
                throw new UserAlreadyExistsException();
            }
            if (pack.getMessage().equals(Message.EMAILALREADYEXISTS)) { // Email already exists
                throw new EmailAlreadyExistsException();
            }
        } catch (ConnectException e) { // Connection time out
            throw new TimeOutException();
        } catch (IOException e) {
            LOGGER.severe("Input Output exception thrown." + e.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.severe("The class was not found." + e.getMessage());
        }
    }
}
