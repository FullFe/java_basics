/**
 * Class for send sms notifications to console
 */
package ru.skillbox.notification_sender;

import ru.skillbox.notification.SmsNotification;

import java.util.List;

public class SmsNotificationSender implements NotificationSender<SmsNotification> {
    public static final String TITLE = "SMS";
    public static final String RECEIVESTR = "receivers";
    public static final String MESSAGESTR = "message";

    /**
     * Method creates a visual representation of the notification object and outputs it to the console
     * @param notification Notification as object
     */
    @Override
    public void send(SmsNotification notification) {
        StringBuilder arrString = new StringBuilder();
        for (String receiver : (notification).getReceivers()) {
            arrString.append(receiver);
            arrString.append(", ");
        }
        String res = TITLE + "\n" +
                RECEIVESTR + ": " + arrString + "\n"+
                MESSAGESTR + ": " + (notification).getMessage()+ "\n";
        System.out.println(res);


    }
    /**
     * Method creates a visual representation of list with notification objects and outputs them to the console
     * @param notifications List of notification objects
     */
    @Override
    public void send(List<SmsNotification> notifications) {
        for (SmsNotification notification : notifications) {
            send(notification);
        }
    }
}
