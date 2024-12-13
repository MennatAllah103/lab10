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

    public void deleteNotification(Notification notification) {
        database.deleteNotification(notification);
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

    private Notification findNotificationById(String notificationId) { // helper function
        for (Notification n : database.getNotifications()) {
            if (n.getNotificationId().equals(notificationId)) {
                return n;
            }
        }
        return null;
    }

    private String generateUniqueNotificationId() {
        return "NOTIF-" + System.currentTimeMillis();
    }
}
