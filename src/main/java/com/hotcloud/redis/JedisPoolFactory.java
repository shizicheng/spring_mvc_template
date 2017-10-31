package com.hotcloud.redis;

import com.hotcloud.util.ConfigUtil;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolFactory {
    private static int MAX_IDLE = 200;
    private static int MAX_TOTAL = 300;
    private static JedisPool pool = null;
    private static JedisPoolConfig config = new JedisPoolConfig();
    private static String host = "127.0.0.1";
    private static String psw = "";

    private JedisPoolFactory() {
    }

    public static JedisPool getInstance() {
        if (pool == null) {
            synchronized (JedisPoolFactory.class) {
                if (pool == null) {
                    host = ConfigUtil.config.get("REDIS_HOST");
                    psw = ConfigUtil.config.get("REDIS_PSW");
                    config.setMaxIdle(JedisPoolFactory.MAX_IDLE);
                    // 最大连接数, 应用自己评估，不要超过AliCloudDB for Redis每个实例最大的连接数
                    config.setMaxTotal(JedisPoolFactory.MAX_TOTAL);
                    config.setTestOnBorrow(false);
                    config.setTestOnReturn(false);
                    pool = new JedisPool(config, host, 6379, 3000, psw);
                }
            }
        }
        return pool;
    }
}
