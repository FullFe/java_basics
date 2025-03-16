package com.skillbox.redisdemo;

import java.text.SimpleDateFormat;

import static java.lang.System.out;

public class RedisTest {

    // Запуск докер-контейнера:
    // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis

    // Для теста будем считать неактивными пользователей, которые не заходили 2 секунды
    private static final int DELETE_SECONDS_AGO = 2;

    // Допустим пользователи делают 500 запросов к сайту в секунду
    private static final int RPS = 500;

    // И всего на сайт заходило 1000 различных пользователей
    private static final int USERS = 1000;

    // Также мы добавим задержку между посещениями
    private static final int SLEEP = 1; // 1 миллисекунда

    private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {

        RedisStorage redis = new RedisStorage();
        redis.init();

        long startTime = System.currentTimeMillis();
        long duration = 6000; // 10 секунд в миллисекундах
        boolean running = true;

        // Эмулируем 10 секунд работы сайта
        while(running) {
            // Выполним 500 запросов
        redis.fillList();
        int counter = 20;
        int chance = 0;
        boolean vipExist = false;
        for (int i = 0; i < redis.listLength(); i++) {
            int randomMember = (int) (Math.random()*counter);
            int randomIt = (int) (Math.random()*10);
            if(randomIt <= chance && !vipExist) {
                redis.vipPop(randomMember);
                chance = 0;
                vipExist = true;
            }else{
                redis.leftPop();
            }
            if(i == 10 && vipExist){
                vipExist = false;
            }
            chance++;
            counter--;
        }



        out.println("------------------------------------------------");
        Thread.sleep(1000);
            if (System.currentTimeMillis() - startTime >= duration) {
                running = false; // Останавливаем цикл
            }


        }
        redis.shutdown();
    }
}
