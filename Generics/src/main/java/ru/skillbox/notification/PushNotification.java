/**
 * Class for push notifications
 */
package ru.skillbox.notification;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter

public class PushNotification implements Notification {
    /**
     * Contain message to account
     */
    private String message;
    /**
     * Contain title of message
     */
    private String notificationTitle;
    /**
     * Contain account nickname
     */
    private String account;
    /**
     * Adds features to the message
     * @return String with message
     */
    @Override
    public String formattedMessage() {
        if (message.isEmpty()) {
            return "";
        }
        return "\ud83d\udc4b "+message;
    }
    /**
     * Constructor with 3 params
     * @param message message for notification
     * @param notificationTitle title for this message
     * @param account account nickname
     */
    public PushNotification(@NonNull String message, @NonNull String notificationTitle, @NonNull String account) {
        this.message = message;
        this.notificationTitle = notificationTitle;
        this.account = account;
    }
}
