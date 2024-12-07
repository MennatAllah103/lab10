/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserManagementBackend;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserDataBase {

    private static ArrayList<User> Users = new ArrayList<>();
    public static UserDataBase database=null;
    private static User currentUser;  // Static field to track the current logged-in user


    private UserDataBase() {
       
        Users = ReadUserFromFile();

    }
     public static UserDataBase getDatabase()
     {
       if(database==null)
           database=new UserDataBase();
          
           return database;
     }

    public static void SaveUserToFile(ArrayList<User> user) {
        JSONArray usersArray = new JSONArray();
        for (User u : user) {
            JSONObject j = new JSONObject();
            j.put("userId", u.getUserId());
            j.put("email", u.getEmail());
            j.put("dateOfBirth", u.getDateOfBirth());
            j.put("password", u.getPassword());
            j.put("username", u.getUsername());
            j.put("status", u.getStatus() ? "online" : "offline");
            j.put("profilePhoto", u.getProfilePhoto());
            j.put("coverPhoto", u.getCoverPhoto());
            j.put("bio", u.getBio());
            
            usersArray.put(j);
        }
        try {
            FileWriter file = new FileWriter("users.json");
            file.write(usersArray.toString(4));
            file.close();
        } catch (IOException e) {
            System.out.println("Error saving users to file");
        }
    }

    public ArrayList<User> ReadUserFromFile() {
         ArrayList<User> users=new ArrayList<>(); 
        try {
            String json = new String(Files.readAllBytes(Paths.get("users.json")));
            JSONArray usersArray = new JSONArray(json);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJson = usersArray.getJSONObject(i);
                String email = userJson.getString("email");
                String id = userJson.getString("userId");
                LocalDate date = LocalDate.parse(userJson.getString("dateOfBirth"), formatter);
                String password = userJson.getString("password");
                String username = userJson.getString("username");
                String status = userJson.getString("status");
                String profilePhoto = userJson.optString("profilePhoto", "defaultProfilePhoto.jpg");
                String coverPhoto = userJson.optString("coverPhoto", "defaultCoverPhoto.jpg");
                String bio = userJson.optString("bio","");
                // Use Builder to create User instance
            User user = new User.Builder(email, date, password, username)
                    .status(status.equals("online"))
                    .profilePhoto(profilePhoto)
                    .coverPhoto(coverPhoto)
                    .bio(bio)
                    .build();

            user.setUserId(id); // Set the userId separately as it's generated in the Builder
            users.add(user);
            }

        } catch (IOException e) {
            System.err.println("Error reading users from file: " + e.getMessage());
        } catch (JSONException e) {
            System.err.println("Error parsing JSON data: " + e.getMessage());
        }
        return users;
    }

  public boolean addUser(User user) {
    // Ensure the current users are loaded from the file (if not already loaded)
    if (Users.isEmpty()) {
        Users = ReadUserFromFile();
    }

    // Check if the email is already registered
    for (User existingUser : Users) {
        if (existingUser.getEmail().equals(user.getEmail())) {
            System.err.println("Email already registered.");
            return false;
        }
    }
    
     for (User existingUser : Users) {
        if (existingUser.getUsername().equals(user.getUsername())) {
            System.err.println("Username already registered.");
            return false;
        }
    }

    Users.add(user);
    SaveUserToFile(Users);
    return true;
}


public void updateStatus(String userId, boolean status) {
    for (User user : Users) { 
        if (user.getUserId().equals(userId)) {
            user.setStatus(status);
            SaveUserToFile(Users); 
            return; 
        }
    }
    System.out.println("User Id not found");
}

    public User getUserById(String userId) {
        for (User u : Users) {
            if (u.getUserId().equals(userId)) {
                return u;
            }
        }

        return null;
    }

    public User getUserByEmail(String email) {
        for (User u : Users) {
            if (u.getEmail().equals(email)) {
                return u;
            }

        }
        return null;
    }
    public User getUserByUsername(String username) {
        for (User u : Users) {
            if (u.getUsername().equals(username)) {
                return u;
            }

        }
        return null;
    }

    
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    } 
}
