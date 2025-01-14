/**
 * Class for email notifications
 * @author Alex Prokofiev
 */
package ru.skillbox.notification;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
@Getter
public class EmailNotification implements Notification{
    /**
     * Message to receiver
     */
    @Setter
    private String message;
    /**
     * Contain subject of message
     */
    @Setter
    private String subject;
    /**
     * List of receivers
     */
    private final ArrayList<String> receivers;

    /**
     * Adds features to the message
     * @return String with message
     */
    @Override
    public String formattedMessage() {
        return "<p>" + message +
                "</p>";
    }

    /**
     * Constructor with 2 params
     * @param message message for notification
     * @param subject subject of this message
     */
    public EmailNotification(@NonNull String message, @NonNull String subject) {
        this.message = message;
        this.subject = subject;
        this.receivers = new ArrayList<>();
    }

    /**
     * Adds email to list.
     * And checks the correctness of the entered data
     * @param receivers email of receiver
     */
    public void addReceivers(@NonNull String receivers) {
        String regex = "[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z.]+";
        if (receivers.matches(regex)) {
            this.receivers.add(receivers);
        }
    }

    /**
     * Removes email from list
     * @param receivers email of receiver
     */
    public void removeReceivers(@NonNull String receivers) {
        String regex = "[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z.]+";
        if (receivers.matches(regex)) {
            this.receivers.remove(receivers);

        }
    }
}
