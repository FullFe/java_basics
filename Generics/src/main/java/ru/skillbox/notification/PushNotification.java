package ru.skillbox.notification;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class PushNotification implements Notification {
    private String message;
    private String notificationTitle;
    private String account;

    @Override
    public String formattedMessage() {
        if (message.isEmpty()) {
            return "";
        }
        return "\ud83d\udc4b "+message;
    }

    public PushNotification(@NonNull String message, @NonNull String notificationTitle, @NonNull String account) {
        this.message = message;
        this.notificationTitle = notificationTitle;
        this.account = account;
    }
}
