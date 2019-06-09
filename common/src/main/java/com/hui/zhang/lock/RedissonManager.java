package com.hui.zhang.lock;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redisson 配置
 */
@Configuration
public class RedissonManager {

    @Value("${spring.redis.clusters}")
    private String cluster;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedissonClient getRedisson() {
        String[] nodes = cluster.split(",");
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = "redis://" + nodes[i];
        }
        RedissonClient redisson = null;
        Config config = new Config();
        config.useClusterServers()
                .setScanInterval(1000)
                .addNodeAddress(nodes)
                .setPassword(password);
        redisson = Redisson.create(config);
        return redisson;
    }
}
