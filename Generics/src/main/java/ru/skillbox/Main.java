package ru.skillbox;

import ru.skillbox.notification.EmailNotification;
import ru.skillbox.notification.PushNotification;
import ru.skillbox.notification.SmsNotification;
import ru.skillbox.notification_sender.EmailNotificationSender;
import ru.skillbox.notification_sender.PushNotificationSender;
import ru.skillbox.notification_sender.SmsNotificationSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TODO Добавить javadoc


public class Main {
    public static void main(String[] args) {

        ArrayList<String> titleList = new ArrayList<>(Arrays.asList("Успешная сдача", "Проигрыш",
                "Заплатка", "Шутка"));
        ArrayList<String> messageList = new ArrayList<>(Arrays.asList("Хорошая работа!", "Неудача, попробуй в следующий раз",
                "Ваше обращение очень важно для нас", "Никого нет дома"));


        emailTest(messageList, titleList);
        System.out.println("\n##############################\n");
        pushTest(messageList, titleList);
        System.out.println("\n##############################\n");
        smsTest(messageList);
    }
    public static void emailTest(ArrayList<String> messageList,  ArrayList<String> titleList){
        ArrayList<String> emailsList = new ArrayList<>(Arrays.asList("masha@java.skillbox.ru", "oleg@java.skillbox.ru", "onelove@pivo.ru"
                , "viktor@java.skillbox.ru"));

        List<EmailNotification> emails = new ArrayList<>();

        for(int i = 0; i < emailsList.size(); i++){
            EmailNotification temp = new EmailNotification(messageList.get(i), titleList.get(i));
            temp.addReceivers(emailsList.get(i));
            int j = i+1;
            while(j<emailsList.size()){
                temp.addReceivers(emailsList.get(j));
                j++;
            }
            emails.add(temp);
        }
        EmailNotificationSender sender = new EmailNotificationSender();
        sender.send(emails.get(0));
        emails.remove(0);
        System.out.println("\n-------------------------------\n");
        System.out.println("Вывод списком");
        sender.send(emails);

    }
    public static void pushTest(ArrayList<String> messageList,  ArrayList<String> titleList){
        ArrayList<String> receiversList = new ArrayList<>(Arrays.asList("o.yanovich", "uselessmouth", "pewdiepie", "Kuplinov"));

        List<PushNotification> push = new ArrayList<>();

        for(int i = 0; i < receiversList.size(); i++){
            PushNotification temp = new PushNotification(messageList.get(i), titleList.get(i), receiversList.get(i));
            push.add(temp);
        }

        PushNotificationSender sender = new PushNotificationSender();
        sender.send(push.get(0));
        push.remove(0);
        System.out.println("\n-------------------------------\n");
        System.out.println("Вывод списком");
        sender.send(push);
    }
    public static void smsTest(ArrayList<String> messageList){
        ArrayList<String> phoneList = new ArrayList<>(Arrays.asList("+70001234567", "+79160152286", "+75559301234", "+71239301234"));

        List<SmsNotification> smsList = new ArrayList<>();

        for(int i = 0; i < phoneList.size(); i++){
            SmsNotification temp = new SmsNotification(messageList.get(i));
            temp.addReceivers(phoneList.get(i));
            int j = i+1;
            while(j<phoneList.size()){
                temp.addReceivers(phoneList.get(j));
                j++;
            }
            smsList.add(temp);
        }

        SmsNotificationSender sender = new SmsNotificationSender();
        sender.send(smsList.get(0));
        smsList.remove(0);
        System.out.println("\n-------------------------------\n");
        System.out.println("Вывод списком");
        sender.send(smsList);
    }
}
