/**
 * Class for sms notifications
 */
package ru.skillbox.notification;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;

@Getter
public class SmsNotification implements Notification {
    /**
     * Message to receiver
     */
    @Setter
    private String message;
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
        return message;
    }

    /**
     * Constructor with 1 param
     * @param message message for notification
     */
    public SmsNotification(@NonNull String message) {
        this.message = message;
        this.receivers = new ArrayList<>();
    }


    /**
     * Adds phone to list.
     * And checks the correctness of the entered data
     * @param receivers phone number
     */
    public void addReceivers(@NonNull String receivers) {
        String regexPhone = "\\+7[0-9]+";
        if (receivers.matches(regexPhone) && receivers.length() == 12) {
            this.receivers.add(receivers);
        }
    }
    /**
     * Adds phone to list.
     * And checks the correctness of the entered data
     * @param receivers phone number
     */
    public void removeReceivers(@NonNull String receivers) {
        String regexPhone = "7[0-9]+";
        if (receivers.matches(regexPhone)&& receivers.length() == 12) {
            this.receivers.remove(receivers);
        }
    }
    /**
     * Adds phone to list.
     * And checks the correctness of the entered data
     * @param index index of phone in list
     */
    public void removeReceivers(int index){
        if (index >= 0 && index < this.receivers.size()) {
            this.receivers.remove(index);
        }
    }


}
