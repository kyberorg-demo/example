package io.kyberorg.example.dao;

import io.kyberorg.example.model.Settings;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * Dao for {@link Settings}.
 */
@RequiredArgsConstructor
@Repository
public class SettingsRedisDao extends RedisDao {
    private final RedisTemplate<String, Settings> redisTemplate;

    private String hashName;
    private HashOperations<String, String, Settings> hashOperations;

    @PostConstruct
    private void init() {
        this.hashOperations = redisTemplate.opsForHash();
        this.hashName = getHashName(Settings.class);
    }

    /**
     * Create or update {@link  Settings}.
     *
     * @param settings object to store.
     */
    public void save(final Settings settings) {
        hashOperations.put(hashName, settings.getSessionId(), settings);
    }

    /**
     * Retrieve {@link Settings} by its ID.
     *
     * @param sessionId string with session id used as key.
     * @return {@link Optional} which contains {@link Settings} or not.
     */
    public Optional<Settings> get(final String sessionId) {
        return Optional.ofNullable(hashOperations.get(hashName, sessionId));
    }

}
