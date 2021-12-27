package io.kyberorg.example.dao;

import io.kyberorg.example.model.Settings;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SettingsRedisDao {
    public static final String HASH_NAME = "Settings";
    private final RedisTemplate<String, Settings> redisTemplate;

    private HashOperations<String, String, Settings> hashOperations;

    @PostConstruct
    private void init() {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void save(final Settings settings) {
        hashOperations.put(HASH_NAME, settings.getSessionId(), settings);
    }

    public Optional<Settings> get(String sessionId) {
        return Optional.ofNullable(hashOperations.get(HASH_NAME, sessionId));
    }

}
