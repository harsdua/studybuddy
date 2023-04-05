
package com.hd.studybuddy.controller.managers;

import com.hd.studybuddy.model.Database;

/**
 * This class handles the communication with the database for login and register purposes
 */
public class LoginManager {

    static Database db = Database.getSingleton();

	public LoginManager() {
		LoginManager.db.createTables();
	}

    /**
     * Check if the given username exists
     */
    public boolean isValidUser(String userName) {
        String[] user = db.getUser(userName);
        return user[0] != null;
    }

    /**
     * Check if the given username is not already taken
     */
    public boolean isValidNewUser(String user) {
        return !isValidUser(user);
    }

    /**
     * Check if the given password is strong enough
     */
    public boolean isValidPassword(String pass) {
        return pass.length() >= 7;
    }

    /**
     * This method is used to verify the validity of a registration with some other methods.
     * @param user This is the name of a user
     * @param pass This is password of a user
     * @return true if the user was added to the database, false otherwise
     */
    public boolean register(String user, String pass) {
        if (isValidNewUser(user) && isValidPassword(pass)) {
            db.addUser(user, pass);
            return true;
        }
        return false;
    }

    /**
     * Check if the user enters the correct password
     */
    public boolean verifyLoginCredentials(String user, String pass) {
        if (isValidUser(user)) {
            return db.getUser(user)[1].equals(pass);
        }
        return false;
    }

    /**
     * Remove the given user
     */
    public void deleteUser(String user) {
        db.deleteUser(user);
    }

    /**
     * Change the current user's password to the new one
     */
    public void modifyPassword(String user, String pass) {
        if (isValidUser(user) && isValidPassword(pass)) {
            db.modifyPassword(user, pass);
        }
    }
}
