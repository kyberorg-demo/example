package io.kyberorg.example.dao;

import io.kyberorg.example.model.redis.Settings;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class SettingsRedisDao {
    private final RedisTemplate<String, Settings> redisTemplate;

    private HashOperations<String, String, Settings> hashOperations;
    private String hashId;

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
        hashId = Settings.class.getAnnotation(RedisHash.class).value();
    }

    public Optional<Settings> findById(final String id) {
        return Optional.ofNullable(hashOperations.get(hashId, id));
    }

    public void save(final Settings settings) {
        hashOperations.put(hashId, settings.getId(), settings);
    }
}
