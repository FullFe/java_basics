/**
 * Class for send push-notifications to console
 */
package ru.skillbox.notification_sender;

import ru.skillbox.notification.PushNotification;
import ru.skillbox.notification.Notification;

import java.util.List;

public class PushNotificationSender implements NotificationSender{
    public static final String TITLE = "PUSH";
    public static final String TITLESTR = "title";
    public static final String RECEIVESTR = "receiver";
    public static final String MESSAGESTR = "message";

    /**
     * Method creates a visual representation of the notification object and outputs it to the console
     * @param notification Notification as object
     */
    @Override
    public void send(Notification notification) {
        PushNotification pushNotification = (PushNotification) notification;


        String res = TITLE + "\n" +
                TITLESTR + ": " + pushNotification.getNotificationTitle()+ "\n" +
                RECEIVESTR + ": "  + pushNotification.getAccount() + "\n" +
                MESSAGESTR + ": "  + pushNotification.getMessage()+ "\n";

        System.out.println(res);
    }
    /**
     * Method creates a visual representation of list with notification objects and outputs them to the console
     * @param notifications List of notification objects
     */
    @Override
    public void send(List notifications) {
        for (Object notification : notifications) {
            send((PushNotification) notification);
        }
    }
}
