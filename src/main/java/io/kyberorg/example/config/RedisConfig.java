package io.kyberorg.example.config;

import io.kyberorg.example.model.Settings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Slf4j
@Configuration
public class RedisConfig {

    private static final String TAG = "[" + RedisConfig.class.getSimpleName() + "]";

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.database}")
    private int database;

    @Bean
    JedisConnectionFactory redisConnectionFactory() {
        log.info("{} Connecting Redis at {}:{}. Database: {}", TAG, host, port, database);
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setDatabase(database);
        if (StringUtils.isNotBlank(password)) {
            log.debug("{} Authorizing at Redis with password {}", TAG, password);
            redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        }

        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfigurationBuilder =
                JedisClientConfiguration.builder();

        jedisClientConfigurationBuilder.connectTimeout(Duration.ofMillis(timeout));

        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfigurationBuilder.build());
    }

    @Bean
    RedisTemplate<String, Settings> settingsRedisTemplate() {
        RedisTemplate<String, Settings> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
