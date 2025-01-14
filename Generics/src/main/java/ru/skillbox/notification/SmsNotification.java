package ru.skillbox.notification;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;

@Getter
public class SmsNotification implements Notification {
    @Setter
    private String message;
    private final ArrayList<String> receivers;
    @Override
    public String formattedMessage() {
        return message;
    }

    public SmsNotification(@NonNull String message) {
        this.message = message;
        this.receivers = new ArrayList<>();
    }



    public void addReceivers(@NonNull String receivers) {
        String regexPhone = "\\+7[0-9]+";
        if (receivers.matches(regexPhone) && receivers.length() == 12) {
            this.receivers.add(receivers);
        }
    }
    public void removeReceivers(@NonNull String receivers) {
        String regexPhone = "7[0-9]+";
        if (receivers.matches(regexPhone)&& receivers.length() == 12) {
            this.receivers.remove(receivers);
        }
    }
    public void removeReceivers(int index){
        if (index >= 0 && index < this.receivers.size()) {
            this.receivers.remove(index);
        }
    }


}
