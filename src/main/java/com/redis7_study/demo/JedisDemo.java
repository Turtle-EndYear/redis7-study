package com.redis7_study.demo;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JedisDemo {
    public static void main(String[] args) {
        // 1. connection
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        // 2. 指定访问服务器的密码
//        jedis.auth("");

        // 3. 获得jedis客户端，可以访问redis
        System.out.println(jedis.ping());

        // 实操
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);

        // String
        jedis.set("k1", "v1");
        System.out.println(jedis.get("k1"));
        System.out.println(jedis.ttl("k1"));
        jedis.mset("k2", "v2", "k3", "v3");
        System.out.println(jedis.mget("k2", "k3"));

        // list
        jedis.lpush("list1", "l1", "l2");
        jedis.rpush("list1", "l3", "l4");
        List<String> list = jedis.lrange("list1", 0, -1);
        for (String temp: list) {
            System.out.println(temp);
        }

        // set
        jedis.sadd("orders", "s1", "s2");
        jedis.sadd("orders", "s3");
        Set<String> set1 = jedis.smembers("orders");
        for (Iterator<String> iterator = set1.iterator(); iterator.hasNext(); ) {
            String string = iterator.next();
            System.out.println(string);
        }
        // 删除 orders 中的 s2
        jedis.srem("orders", "s2");
        System.out.println(jedis.smembers("orders"));

        // hash
        jedis.hset("hash1", "username", "z3");
        System.out.println(jedis.hget("hash1", "username"));
        HashMap<String, String> map = new HashMap<>();
        map.put("tel", "123");
        map.put("addr", "addr");
        jedis.hmset("hash2", map);
        List<String> result = jedis.hmget("hash2", "tel", "addr");
        for (String temp : result) {
            System.out.println(temp);
        }

        // zset
        jedis.zadd("zset1",10,"v1");
        jedis.zadd("zset1",20,"v2");
        jedis.zadd("zset1",30,"v3");
        List<String> zset1 = jedis.zrange("zset1", 0, -1);
        zset1.forEach(System.out::println);
        /*
        等效于：
        for (ElementType element : zset1) {
            System.out.println(element);
        }
         */
    }
}
