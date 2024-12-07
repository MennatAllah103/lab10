/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProfileManagementBackend;

import java.util.ArrayList;
import UserManagementBackend.*;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author hp
 */
public class ProfileManager {

    private UserDataBase userDatabase = UserDataBase.getDatabase(); // whole class as member

    public void updatePhoto(String filePath, String photoType) {
        User currentUser = UserDataBase.getCurrentUser();

        if (currentUser != null) {
            ArrayList<User> users = userDatabase.ReadUserFromFile();

            if (photoType.equalsIgnoreCase("profile")) {
                currentUser.setProfilePhoto(filePath);
            } else if (photoType.equalsIgnoreCase("cover")) {
                currentUser.setCoverPhoto(filePath); // for consistency between what is displayed and whats saved
            }

            for (User user : users) {
                if (user.getUserId().equals(currentUser.getUserId())) {
                    if (photoType.equalsIgnoreCase("profile")) {
                        user.setProfilePhoto(filePath);
                    } else if (photoType.equalsIgnoreCase("cover")) {
                        user.setCoverPhoto(filePath);
                    }
                }
            }
            userDatabase.SaveUserToFile(users);
        }
    }

    public void updateBio(String bio) {
        User currentUser = userDatabase.getCurrentUser();

        if (currentUser != null) {
            ArrayList<User> users = userDatabase.ReadUserFromFile();

            currentUser.setBio(bio); //  also for consistency

            for (User user : users) {
                if (user.getUserId().equals(currentUser.getUserId())) {
                    user.setBio(bio);
                }
            }
            userDatabase.SaveUserToFile(users);
        }
    }

    public void updatePassword(String password) throws NoSuchAlgorithmException {

        User currentUser = UserDataBase.getCurrentUser();

        if (currentUser != null) {
            ArrayList<User> users = userDatabase.ReadUserFromFile();

            String hashedPassword = PasswordHashing.hashPassword(password);
            
            currentUser.setPassword(hashedPassword);

            for (User user : users) {
                if (user.getUserId().equals(currentUser.getUserId())) {
                    user.setPassword(hashedPassword);
                }
            }
            userDatabase.SaveUserToFile(users);
        }     

    }
    
}
