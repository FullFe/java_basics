package ru.skillbox.notification_sender;

import ru.skillbox.notification.EmailNotification;
import ru.skillbox.notification.Notification;

import java.util.List;

public class EmailNotificationSender implements NotificationSender{
    public static final String TITLE = "EMAIL";
    public static final String SUBJECTSTR = "subject";
    public static final String RECEIVESTR = "receivers";
    public static final String MESSAGESTR = "message";

    @Override
    public void send(Notification notification) {
        EmailNotification emailNotification = (EmailNotification) notification;
        StringBuilder arrString = new StringBuilder();
        for (String receiver : emailNotification.getReceivers()) {
            arrString.append(receiver);
            arrString.append(", ");
        }

        String res = TITLE + "\n" +
                SUBJECTSTR + ": " + emailNotification.getSubject() + "\n" +
                RECEIVESTR + ": "  + arrString + "\n" +
                MESSAGESTR + ": "  + emailNotification.getMessage()+ "\n";

        System.out.println(res);
    }

    @Override
    public void send(List notifications) {
        for (Object notification : notifications) {
            send((EmailNotification) notification);
        }
    }
}
