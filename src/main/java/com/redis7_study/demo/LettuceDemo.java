package com.redis7_study.demo;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LettuceDemo {
    public static void main(String[] args) {
        // 1.使用构建器链式编程来builder RedisUI
//        RedisURI uri = RedisURI.builder().redis("127.0.0.1").withPort(6379).withAuthentication("default","密码").build();
        RedisURI uri = RedisURI.Builder
                .redis("127.0.0.1",6379)
                .build();

        // 2. 创建连接客户端
        RedisClient redisClient = RedisClient.create(uri);
        StatefulRedisConnection conn = redisClient.connect();

        // 3. 通过conn创建操作的command
        RedisCommands commands = conn.sync();

        System.out.println(commands.ping());

        // ========代码=========


        // String
        commands.set("k1","v1");
        System.out.println(commands.get("k1"));

        // list
        commands.lpush("list1","l1","l2","l3");
        List<String> list = commands.lrange("list1", 0, -1);
        list.forEach(System.out::println);

        // set
        commands.sadd("set1","s1","s2","s3");
        Set<String> set = commands.smembers("set1");
        set.forEach(System.out::println);

        // hash
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("tel","123");
        hashMap.put("addr","addr");
        commands.hmset("hash1",hashMap);
        Map<String,String> map = commands.hgetall("hash1");
        for (String k:map.keySet()){
            System.out.println("hash  k="+k+"  v="+map.get(k));
        }

        // zset
        commands.zadd("zset1",100.0,"z1",90.0,"z2",110.0,"z3");
        List<String> zset1 = commands.zrange("zset1", 0, 10);
        zset1.forEach(System.out::println);


        // 4. 关闭释放资源
        conn.close();
        redisClient.shutdown();
    }
}
