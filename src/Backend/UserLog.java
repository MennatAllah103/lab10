/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.UUID;

/**
 *
 * @author daree
 */
public class UserLog {

    UserDataBase database;

    public UserLog(UserDataBase database) {

        this.database = UserDataBase.getDatabase();
    }

    public boolean signup(String email, String password, LocalDate dateOfBirth, String username) {
        try {
            // Check if email is already registered (assuming a method exists for this)
            if (database.getUserByEmail(email) != null) {
                // Email already exists in the database
                return false;
            }

            // Hash the password
            String hashedPassword = PasswordHashing.hashPassword(password);

            // Create a new User object using the builder pattern
            User user = new User.Builder(email, dateOfBirth, hashedPassword, username)
                    .status(false)
                    .profilePhoto("defaultProfilePhoto.jpeg") // Set default profile photo path
                    .coverPhoto("defaultCoverPhoto.jpg")
                    .build();

            // Attempt to add the user to the database
            boolean addUser = database.addUser(user);

            // Return whether the user was successfully added
            return addUser;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isValidEmail(String email) {
        // Check if the email is null
        if (email == null) {
            // System.out.println("Email is NULL. Please provide a valid email.");
            return false;  // Invalid if the email is null
        }

        int length = email.length();
        int atSymbolIndex = -1;
        int dotIndex = -1;

        // Find the index of the '@' symbol
        for (int i = 0; i < length; i++) {
            if (email.charAt(i) == '@') {
                atSymbolIndex = i;
                break;
            }
        }

        // Check if the '@' symbol is present
        if (atSymbolIndex == -1) {
            // System.out.println("Invalid email. The email must contain the '@' symbol.");
            return false;
        }

        // Check if there is at least one character before and after '@'
        if (atSymbolIndex <= 0 || atSymbolIndex >= length - 1) {
            //System.out.println("Invalid email. The '@' symbol should have at least one character before and after it.");
            return false;
        }

        // Find the index of the '.' symbol after '@'
        for (int i = atSymbolIndex + 1; i < length; i++) {
            if (email.charAt(i) == '.') {
                dotIndex = i;
                break;
            }
        }

        // Check if the dot is directly after the '@' symbol
        if (dotIndex == atSymbolIndex + 1) {
            // System.out.println("Invalid email. The '.' cannot be directly after the '@' symbol.");
            return false;
        }

        // Check for a valid domain (at least one '.' after '@')
        if (dotIndex == -1 || dotIndex >= length - 1) {
            // System.out.println("Invalid email. The domain should have at least one character after '.'");
            return false;
        }

        return true;  // Valid email
    }

    public static boolean isValidPassword(String password, String Confirm) {
        if (password.equals(Confirm)) {
            return true;
        }
        return false;
    }

    public void logOut(User user) {

        database.updateStatus(user.getUserId(), false);

    }

    public User login(String email, String password, String username) {
        try {
            User user = database.getUserByEmail(email);
            if (user == null) {

                return null;
            }

            if (!user.getUsername().equals(username)) {

                return null;
            }

            if (!PasswordHashing.validatePassword(password, user.getPassword())) {

                return null;
            }

            database.updateStatus(user.getUserId(), true);
            return user;
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error hashing password: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

}
