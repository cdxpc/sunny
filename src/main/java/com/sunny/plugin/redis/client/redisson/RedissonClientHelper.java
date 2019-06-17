package com.sunny.plugin.redis.client.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述：
 *
 * @author cdxpc <cdxpc2018@163.com>
 * @date 2019/6/16 10:35
 * @since 1.0.0v
 */
public class RedissonClientHelper {

    private final static Map<String, RedissonClient> ID_CACHE = new ConcurrentHashMap<>();

    private RedissonClientHelper(){}

    static RedissonClient init(int dbIndex) {
        String ip = "127.0.0.1";
        int port = 6379;

        String id = ip + ":" + port + ":" + dbIndex;

        RedissonClient rc = ID_CACHE.get(id);

        if(rc == null) {

            Config config = new Config();

            config.useSingleServer()
                    .setAddress("redis://" + ip + ":" + port)
                    .setConnectionPoolSize(500)
                    .setConnectionMinimumIdleSize(30)
                    .setConnectTimeout(5000)
                    .setIdleConnectionTimeout(30000)
                    .setPingConnectionInterval(1500)
                    .setDatabase(dbIndex);

            rc = Redisson.create(config);
            ID_CACHE.putIfAbsent(id, rc);
        }

        return ID_CACHE.get(id);
    }

    public static RedissonClient client(int dbIndex) {
        return init(dbIndex);
    }

    public static void main(String[] args) {
        RedissonClient c1 = client(0);
        RedissonClient c2 = client(2);
        RedissonClient c3 = client(0);
        RedissonClient c4 = client(1);
        RedissonClient c5 = client(2);
        RedissonClient c6 = client(0);

        System.out.println(c1.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());
        System.out.println(c4.hashCode());
        System.out.println(c5.hashCode());
        System.out.println(c6.hashCode());
    }

}
