package com.hui.zhang.redis;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.Collections;

public class JRedisLock {

    private Jedis jedis = null;

    private static final String LOCK_SUCCESS = "OK";

    private static final Long UNLOCK_SUCCESS = 1l;

    public boolean lock(String key, String value, long timeout) {
        String result = jedis.set(key, value, "NX", "EX", 1);

        if (StringUtils.isEmpty(result)) {
            return false;
        }
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    public boolean unlock(String key, String value) {
        String luaScript = "if redis.call(\"get\",KEYS[1]) == ARGV[1] " +
                "then return redis.call(\"del\",KEYS[1]) else return 0 end)";
        Object result = jedis.eval(luaScript, Collections.singletonList(key), Collections.singletonList(value));

        if (result == UNLOCK_SUCCESS) {
            return true;
        }
        return false;
    }

}
