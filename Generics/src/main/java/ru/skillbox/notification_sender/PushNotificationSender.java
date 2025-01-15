/**
 * Class for send push-notifications to console
 */
package ru.skillbox.notification_sender;

import ru.skillbox.notification.PushNotification;

import java.util.List;

public class PushNotificationSender implements NotificationSender<PushNotification>{
    public static final String TITLE = "PUSH";
    public static final String TITLESTR = "title";
    public static final String RECEIVESTR = "receiver";
    public static final String MESSAGESTR = "message";

    /**
     * Method creates a visual representation of the notification object and outputs it to the console
     * @param notification Notification as object
     */
    @Override
    public void send(PushNotification notification) {


        String res = TITLE + "\n" +
                TITLESTR + ": " + notification.getNotificationTitle()+ "\n" +
                RECEIVESTR + ": "  + notification.getAccount() + "\n" +
                MESSAGESTR + ": "  + notification.getMessage()+ "\n";

        System.out.println(res);
    }
    /**
     * Method creates a visual representation of list with notification objects and outputs them to the console
     * @param notifications List of notification objects
     */
    @Override
    public void send(List<PushNotification> notifications) {
        for (PushNotification notification : notifications) {
            send(notification);
        }
    }
}
