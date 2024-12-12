/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class NotificationManager {

    private final NotificationDatabase database = NotificationDatabase.getInstance();

    public void createNotification(String userId, String type, String message) {
        Notification notification = new Notification.Builder(
                generateUniqueNotificationId(),
                userId,
                type,
                message
        ).build();

        database.addNotification(notification);
    }

    public ArrayList<Notification> getNotificationsForUser(String userId) {
        ArrayList<Notification> userNotifications = new ArrayList<>();
        for (Notification n : database.getNotifications()) {
            if (n.getUserId().equals(userId)) {
                userNotifications.add(n);
            }
        }
        return userNotifications;
    }

    public ArrayList<Notification> getUnreadNotificationsForUser(String userId) {
        ArrayList<Notification> unreadNotifications = new ArrayList<>();
        for (Notification n : getNotificationsForUser(userId)) {
            if (n.getStatus().equals("Unread")) {
                unreadNotifications.add(n);
            }
        }
        return unreadNotifications;
    }

    private Notification findNotificationById(String notificationId) { // helper function
        for (Notification n : database.getNotifications()) {
            if (n.getNotificationId().equals(notificationId)) {
                return n;
            }
        }
        return null;
    }

    public void markNotificationAsRead(String notificationId) {
        Notification notification = findNotificationById(notificationId);
        if (notification != null) {
            Notification updatedNotification = new Notification.Builder(
                    notification.getNotificationId(),
                    notification.getUserId(),
                    notification.getType(),
                    notification.getMessage()
            )
                    .status("Read")
                    .timeStamp(notification.getTimeStamp())
                    .build();

            database.addNotification(updatedNotification);
        }
    }

    private String generateUniqueNotificationId() {
        return "NOTIF-" + System.currentTimeMillis();
    }
}
