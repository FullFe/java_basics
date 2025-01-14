/**
 * Class for send email notifications to console
 */
package ru.skillbox.notification_sender;

import ru.skillbox.notification.EmailNotification;
import ru.skillbox.notification.Notification;

import java.util.List;

public class EmailNotificationSender implements NotificationSender{
    public static final String TITLE = "EMAIL";
    public static final String SUBJECTSTR = "subject";
    public static final String RECEIVESTR = "receivers";
    public static final String MESSAGESTR = "message";

    /**
     * Method creates a visual representation of the notification object and outputs it to the console
     * @param notification Notification as object
     */
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

    /**
     * Method creates a visual representation of list with notification objects and outputs them to the console
     * @param notifications List of notification objects
     */
    @Override
    public void send(List notifications) {
        for (Object notification : notifications) {
            send((EmailNotification) notification);
        }
    }
}
