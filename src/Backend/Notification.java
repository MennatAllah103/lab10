/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.time.LocalDateTime;

/**
 *
 * @author hp
 */
public class Notification {

    String type; // e.g group activity, friend request, new post
    String message;
    LocalDateTime timeStamp;
    String notificationId;
    String userId;

    private Notification(Builder builder) {
        this.notificationId = builder.notificationId;
        this.userId = builder.userId;
        this.type = builder.type;
        this.message = builder.message;
        this.timeStamp = builder.timeStamp;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public String getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public static class Builder {

        private final String notificationId;
        private final String userId;
        private final String type;
        private final String message;
        private LocalDateTime timeStamp = java.time.Instant.now()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDateTime();

        public Builder(String notificationId, String userId, String type, String message) {
            this.notificationId = notificationId;
            this.userId = userId;
            this.type = type;
            this.message = message;
        }

        public Builder timeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Notification build() {
            return new Notification(this);
        }
    }

}
