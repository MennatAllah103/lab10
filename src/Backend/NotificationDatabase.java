/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class NotificationDatabase {
    
    private static NotificationDatabase instance; 
    private final ArrayList<Notification> notifications = new ArrayList<>();
    private final String filePath = "notifications.json"; 

   
    private NotificationDatabase() {
        loadNotificationsFromFile(); 
    }

    
    public static NotificationDatabase getInstance() {
        if (instance == null) {
            instance = new NotificationDatabase();
        }
        return instance;
    }

   
    public void addNotification(Notification notification) {
        notifications.add(notification);
        saveNotificationsToFile(); 
    }
    
    public void deleteNotification(Notification notification){
        boolean removed = notifications.remove(notification);
        if (removed) {
            saveNotificationsToFile();
        } 
    }
    
    public ArrayList<Notification> getNotifications() {
        return new ArrayList<>(notifications); // return a copy for safety
    }

    private void saveNotificationsToFile() {
        JSONArray notificationsArray = new JSONArray();
        for (Notification n : notifications) {
            JSONObject j = new JSONObject();
            j.put("notificationId", n.getNotificationId());
            j.put("userId", n.getUserId());
            j.put("type", n.getType());
            j.put("message", n.getMessage());
            j.put("timeStamp", n.getTimeStamp().toString()); // convert LocalDateTime to String

            notificationsArray.put(j);
        }

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(notificationsArray.toString(4)); 
        } catch (IOException e) {
            System.err.println("Error saving notifications to file: " + e.getMessage());
        }
    }

 
    private void loadNotificationsFromFile() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray notificationsArray = new JSONArray(json);

            for (int i = 0; i < notificationsArray.length(); i++) {
                JSONObject notificationJson = notificationsArray.getJSONObject(i);

                String notificationId = notificationJson.getString("notificationId");
                String userId = notificationJson.getString("userId");
                String type = notificationJson.getString("type");
                String message = notificationJson.getString("message");
                LocalDateTime timeStamp = LocalDateTime.parse(notificationJson.getString("timeStamp"));

                Notification notification = new Notification.Builder(notificationId, userId, type, message)
                        .timeStamp(timeStamp)
                        .build();

                notifications.add(notification);
            }
        } catch (IOException e) {
            System.err.println("File not found or unreadable. Starting with an empty notification list.");
        } catch (Exception e) {
            System.err.println("Error loading notifications: " + e.getMessage());
        }
    }
    
 
}
