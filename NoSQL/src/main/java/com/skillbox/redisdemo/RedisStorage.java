package com.skillbox.redisdemo;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Date;

import static java.lang.System.out;

public class RedisStorage {

    // Объект для работы с Redis
    private RedissonClient redisson;

    // Объект для работы с ключами
    private RKeys rKeys;

    // Объект для работы с Sorted Set'ом
    private RList<String> onlineUsers;

    private final static String KEY = "USERS";


    // Пример вывода всех ключей
    public void listKeys() {
        Iterable<String> keys = rKeys.getKeys();
        for(String key: keys) {
            out.println("KEY: " + key + ", type:" + rKeys.getType(key));
        }
    }

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        onlineUsers = redisson.getList(KEY);
        rKeys.delete(KEY);
    }

    void shutdown() {
        redisson.shutdown();
    }

    void fillList(){
        onlineUsers.clear();
        for(int i = 1; i < 21; i++) {
            onlineUsers.add(String.valueOf(i));
        }

    }

    void leftPop(){
        String firstItem = onlineUsers.get(0);
        out.println("На главной странице показываем пользователя "+firstItem);
        onlineUsers.remove(0);
        onlineUsers.add(firstItem);
    }
    void vipPop(int vip){
        String vipItem = onlineUsers.get(vip);
        out.println("Vip пользователь оплатил пропуск");
        out.println("На главной странице показываем пользователя "+vipItem);

        onlineUsers.remove(vipItem);
        onlineUsers.add(vipItem);
    }
    int listLength(){
        return onlineUsers.size();
    }

    // Фиксирует посещение пользователем страницы

}
