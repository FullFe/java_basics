package ru.skillbox.notification;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
@Getter
public class EmailNotification implements Notification{
    @Setter
    private String message;
    @Setter
    private String subject;
    private final ArrayList<String> receivers;
    @Override
    public String formattedMessage() {
        return "<p>" + message +
                "</p>";
    }

    public EmailNotification(@NonNull String message, @NonNull String subject) {
        this.message = message;
        this.subject = subject;
        this.receivers = new ArrayList<>();
    }
    public void addReceivers(@NonNull String receivers) {
        String regex = "[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z.]+";
        if (receivers.matches(regex)) {
            this.receivers.add(receivers);
        }
    }
    public void removeReceivers(@NonNull String receivers) {
        String regex = "[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z.]+";
        if (receivers.matches(regex)) {
            this.receivers.remove(receivers);

        }
    }
}
